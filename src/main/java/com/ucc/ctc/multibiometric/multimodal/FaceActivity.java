package com.ucc.ctc.multibiometric.multimodal;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;

import com.neurotec.biometrics.NBiometricCaptureOption;
import com.neurotec.biometrics.NBiometricOperation;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NBiometricTask;
import com.neurotec.biometrics.NFace;
import com.neurotec.biometrics.NSubject;
import com.neurotec.biometrics.NLivenessMode;
import com.neurotec.biometrics.client.NBiometricClient;
import com.neurotec.biometrics.standards.BDIFStandard;
import com.neurotec.biometrics.standards.FCRFaceImage;
import com.neurotec.biometrics.standards.FCRecord;
import com.neurotec.biometrics.view.NFaceView;
import com.neurotec.devices.NCamera;
import com.neurotec.devices.NDevice;
import com.neurotec.devices.NDeviceType;
import com.neurotec.images.NImage;
import com.neurotec.media.NMediaFormat;
import com.neurotec.media.NVideoFormat;
import com.ucc.ctc.R;
import com.ucc.ctc.licensing.LicensingManager;
import com.ucc.ctc.multibiometric.Model;
import com.ucc.ctc.multibiometric.preferences.FacePreferences;
import com.ucc.ctc.multibiometric.view.CameraControlsView;
import com.ucc.ctc.multibiometric.view.CameraControlsView.CameraControlsListener;
import com.ucc.ctc.multibiometric.view.CameraFormatFragment;
import com.ucc.ctc.multibiometric.view.CameraFormatFragment.CameraFormatSelectionListener;
import com.ucc.ctc.util.IOUtils;
import com.ucc.ctc.util.NImageUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FaceActivity extends BiometricActivity implements CameraControlsListener, CameraFormatSelectionListener {

	// ===========================================================
	// Private static fields
	// ===========================================================

	private static final String TAG = "FaceActivity";
	private static final String MODALITY_ASSET_DIRECTORY = "faces";

	private static final String PREVIEW_SIZE_SEPARATOR = "x";
	private static final String FPS_SEPARATOR = ":";
	private static final String NAME_SEPARATOR = " ";
	private static final String REGEX_FORMAT_PATTERN = "(.+)" + NAME_SEPARATOR + "(\\d+)" + PREVIEW_SIZE_SEPARATOR + "(\\d+)" + FPS_SEPARATOR + "(\\d+)";

	private static final String DEFAULT_VIDEO_FORMAT = "UNKNOWN 1280x720:30000";

	private static final int MINIMUM_BATTERY_LEVEL = 20;

	// ===========================================================
	// Private fields
	// ===========================================================

	private NFaceView mFaceView;
	private CameraControlsView controlsView;

	// ===========================================================
	// Private methods
	// ===========================================================

	private boolean checkBatteryLevel() {
		if (client.getFacesLivenessMode() == NLivenessMode.PASSIVE || client.getFacesLivenessMode() == NLivenessMode.PASSIVE_WITH_BLINK) {
			Intent batteryStatus = getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
			if (batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) < MINIMUM_BATTERY_LEVEL) {
				showError( R.string.msg_battery_level_error);
				return true;
			}
		}
		return false;
	}

	private void adjustBrightness(boolean setMax) {
		WindowManager.LayoutParams layout = getWindow().getAttributes();
		layout.screenBrightness = setMax ? 1f : -1f;
		runOnUiThread(() -> {
			getWindow().setAttributes(layout);
		});
	}

	private void startCapturing() {
		NSubject subject = new NSubject();
		NFace face = new NFace();
		face.addPropertyChangeListener(biometricPropertyChanged);
		EnumSet<NBiometricCaptureOption> options = EnumSet.of(NBiometricCaptureOption.STREAM);
		if (FacePreferences.isShowIcaoWarnings(this) || FacePreferences.isShowIcaoTextWarnings(this)) {
			mFaceView.setShowIcaoArrows(FacePreferences.isShowIcaoWarnings(this));
			mFaceView.setShowIcaoTextWarnings(FacePreferences.isShowIcaoTextWarnings(this));
		}

		face.setCaptureOptions(options);
		mFaceView.setFace(face);
		subject.getFaces().add(face);
		capture(subject, (FacePreferences.isShowIcaoWarnings(this) || FacePreferences.isShowIcaoTextWarnings(this)) ? EnumSet.of(NBiometricOperation.ASSESS_QUALITY) : null);
	}

	private void setCameraControlsVisible(final boolean value) {
		runOnUiThread(() -> {
			controlsView.setVisibility(value ? View.VISIBLE : View.GONE);
		});
	}

	private NSubject createSubjectFromImage(Uri uri) {
		NSubject subject = null;
		try {
			NImage image = NImageUtils.fromUri(this, uri);
			subject = new NSubject();
			NFace face = new NFace();
			face.setImage(image);
			subject.getFaces().add(face);
		} catch (Exception e){
			Log.i(TAG, "Failed to load file as NImage");
		}
		return subject;
	}

	private NSubject createSubjectFromFCRecord(Uri uri) {
		NSubject subject = null;
		try {
			FCRecord fcRecord = new FCRecord( IOUtils.toByteBuffer(this, uri), BDIFStandard.ISO);
			subject = new NSubject();
			for (FCRFaceImage img : fcRecord.getFaceImages()) {
				NFace face = new NFace();
				face.setImage(img.toNImage());
				subject.getFaces().add(face);
			}
		} catch (Exception e){
			Log.i(TAG, "Failed to load file as FCRecord");
		}
		return subject;
	}

	private NSubject createSubjectFromMemory(Uri uri) {
		NSubject subject = null;
		try {
			subject = NSubject.fromMemory(IOUtils.toByteBuffer(this, uri));
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return subject;
	}

	private static Object[] stringToVideoFormatValues() {
		Pattern pattern = Pattern.compile(REGEX_FORMAT_PATTERN);
		Matcher matcher = pattern.matcher(FaceActivity.DEFAULT_VIDEO_FORMAT);
		if (matcher.find()) {
			Object[] result = new Object[4];
			result[0] = matcher.group(1);			// name
			result[1] = Integer.parseInt(matcher.group(2));	// width
			result[2] = Integer.parseInt(matcher.group(3));	// height
			result[3] = Integer.parseInt(matcher.group(4));	// fps
			return result;
		} else {
			throw new IllegalArgumentException("Unable to parse video format " + FaceActivity.DEFAULT_VIDEO_FORMAT);
		}
	}

	private void setUpCamera() {
		for (NDevice device: Model.getInstance().getClient().getDeviceManager().getDevices()) {
			if (!device.getDeviceType().contains(NDeviceType.CAMERA)) continue;
			if (device.getDisplayName().contains("Front")) {
				client.setFaceCaptureDevice((NCamera) device);
				Object[] formatValues = stringToVideoFormatValues();
				for (NMediaFormat mediaFormat : ((NCamera) device).getFormats()) {
					NVideoFormat videoFormat = (NVideoFormat)mediaFormat;
					if (videoFormat.getWidth() == (int)formatValues[1] && videoFormat.getHeight() == (int)formatValues[2] && videoFormat.getFrameRate().numerator == (int)formatValues[3]) {
						((NCamera) device).setCurrentFormat(mediaFormat);
						Log.i(TAG, "Current video format: " + ((NCamera) device).getCurrentFormat());
						return;
					}
				}
				return;
			}
		}
	}

	// ===========================================================
	// Protected methods
	// ===========================================================

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			PreferenceManager.setDefaultValues(this, R.xml.face_preferences, false);
			LinearLayout layout = (LinearLayout) findViewById(R.id.multimodal_biometric_layout);

			controlsView = new CameraControlsView(this, this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

			controlsView.setLayoutParams(params);
			layout.addView(controlsView);

			mFaceView = new NFaceView(this);
			mFaceView.setShowAge(true);
			layout.addView(mFaceView);

			Button backButton = (Button) findViewById(R.id.multimodal_button_retry);
			backButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onBack();
				}
			});

			Button add = (Button) findViewById(R.id.multimodal_button_add);
			add.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					Bundle b = new Bundle();
					byte[] nLTemplate = subject.getTemplate().getFaces().save().toByteArray();
					b.putByteArray(RECORD_REQUEST_FACE , Arrays.copyOf(nLTemplate, nLTemplate.length));
					intent.putExtras(b);
					setResult( Activity.RESULT_OK, intent);
					finish();
				}
			});

			setUpCamera();
		} catch (Exception e) {
			showError(e);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected List<String> getAdditionalComponents() {
		return additionalComponents();
	}

	@Override
	protected List<String> getMandatoryComponents() {
		return mandatoryComponents();
	}

	@Override
	protected Class<?> getPreferences() {
		return FacePreferences.class;
	}

	@Override
	protected void updatePreferences(NBiometricClient client) {
		FacePreferences.updateClient(client, this);
		mFaceView.setShowFaceOval(
			client.getFacesLivenessMode() == NLivenessMode.PASSIVE ||
			client.getFacesLivenessMode() == NLivenessMode.PASSIVE_WITH_BLINK
		);
	}

	@Override
	protected boolean isCheckForDuplicates() {
		return FacePreferences.isCheckForDuplicates(this);
	}

	@Override
	protected String getModalityAssetDirectory() {
		return MODALITY_ASSET_DIRECTORY;
	}

	@Override
	protected void onStartCapturing() {
		if (checkBatteryLevel()) return;
		startCapturing();
	}

	@Override
	protected void onFileSelected(Uri uri) throws Exception {
		NSubject subject = createSubjectFromImage(uri);

		if (subject == null) {
			subject = createSubjectFromFCRecord(uri);
		}

		if (subject == null) {
			subject = createSubjectFromMemory(uri);
		}

		if (subject != null) {
			if (!subject.getFaces().isEmpty()) {
				mFaceView.setFace(subject.getFaces().get(0));
			}
			extract(subject);
		} else {
			showInfo("File did not contain valid information for subject");
		}
	}

	@Override
	protected void onOperationStarted(NBiometricOperation operation) {
		super.onOperationStarted(operation);
		if (operation == NBiometricOperation.CAPTURE) {
			setCameraControlsVisible(true);
		}
		adjustBrightness(true);
	}

	@Override
	protected void onOperationCompleted(NBiometricOperation operation, NBiometricTask task) {
		super.onOperationCompleted(operation, task);
		if (task != null && task.getStatus() == NBiometricStatus.OK && operation == NBiometricOperation.CREATE_TEMPLATE) {
			setCameraControlsVisible(false);
		}
		adjustBrightness(false);
	}

	// ===========================================================
	// 	Public methods
	// ===========================================================

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_FOCUS || keyCode == KeyEvent.KEYCODE_CAMERA) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onCameraFormatSelected(final NMediaFormat format) {
		new Thread(() ->{
			NCamera camera = Model.getInstance().getClient().getFaceCaptureDevice();
			if (camera != null) {
				camera.setCurrentFormat(format);
			}
		}).start();
	}

	@Override
	public void onSwitchCamera() {
		NCamera currentCamera = client.getFaceCaptureDevice();
		for (NDevice device : client.getDeviceManager().getDevices()) {
			if (device.getDeviceType().contains(NDeviceType.CAMERA)) {
				if (!device.equals(currentCamera)) {
					boolean isCapturing = currentCamera.isCapturing();
					if (isCapturing) onStopCapturing();
					int timeoutCounter = 0;
					while (currentCamera.isCapturing()) {
						try {
							Thread.sleep(10);
							timeoutCounter += 10;
							if (timeoutCounter > 3000) {
								showError("Stopping timeout occured");
								return;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					client.setFaceCaptureDevice((NCamera) device);
					if (isCapturing) onStartCapturing();
					break;
				}
			}
		}
	}

	@Override
	public void onChangeFormat() {
		CameraFormatFragment.newInstance().show(getFragmentManager(), "formats");
	}

	public static List<String> mandatoryComponents() {
		return Arrays.asList(LicensingManager.LICENSE_DEVICES_CAMERAS,
				LicensingManager.LICENSE_FACE_DETECTION,
				LicensingManager.LICENSE_FACE_EXTRACTION,
				LicensingManager.LICENSE_FACE_MATCHING);
	}

	public static List<String> additionalComponents() {
		return Arrays.asList(LicensingManager.LICENSE_FACE_STANDARDS,
				LicensingManager.LICENSE_FACE_MATCHING_FAST,
				LicensingManager.LICENSE_FACE_SEGMENTS_DETECTION);
	}
}
