		package com.ucc.ctc.views;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.CompoundButton;
        import android.widget.FrameLayout.LayoutParams;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;

        import com.google.android.material.checkbox.MaterialCheckBox;
        import com.neurotec.biometrics.NBiometricOperation;
        import com.neurotec.biometrics.NBiometricStatus;
        import com.neurotec.biometrics.NBiometricTask;
        import com.neurotec.biometrics.NEMatchingDetails;
        import com.neurotec.biometrics.NFMatchingDetails;
        import com.neurotec.biometrics.NFPosition;
        import com.neurotec.biometrics.NFRecord;
        import com.neurotec.biometrics.NFTemplate;
        import com.neurotec.biometrics.NFinger;
        import com.neurotec.biometrics.NLMatchingDetails;
        import com.neurotec.biometrics.NMatchingResult;
        import com.neurotec.biometrics.NSMatchingDetails;
        import com.neurotec.biometrics.NSubject;
        import com.neurotec.biometrics.NTemplate;
        import com.neurotec.biometrics.client.NBiometricClient;
        import com.neurotec.biometrics.view.NFingerView;
        import com.neurotec.biometrics.view.NFingerViewBase.ShownImage;
        import com.neurotec.devices.NDevice;
        import com.neurotec.devices.NDeviceType;
        import com.neurotec.devices.NFScanner;
        import com.neurotec.images.NImage;
        import com.neurotec.io.NBuffer;
        import com.neurotec.util.concurrent.CompletionHandler;
        import com.ucc.ctc.R;
        import com.ucc.ctc.licensing.LicensingManager;
        import com.ucc.ctc.models.FingerTemplateElement;
        import com.ucc.ctc.models.entity.ClientBiometricEntity;
        import com.ucc.ctc.multibiometric.multimodal.BiometricActivity;
        import com.ucc.ctc.multibiometric.multimodal.MultiModalActivity;
        import com.ucc.ctc.multibiometric.preferences.FingerPreferences;
        import com.ucc.ctc.util.IOUtils;
        import com.ucc.ctc.util.NImageUtils;
        import com.ucc.ctc.util.ResourceUtils;
        import com.ucc.ctc.utils.Finger;
        import com.ucc.ctc.utils.Util;
        import com.ucc.ctc.viewsModel.ClientBiometricViewModel;

        import java.io.File;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        public final class FingerClientActivity_old extends BiometricActivity {
        // ===========================================================
        // Private fields
        // ===========================================================

        private final int MODALITY_CODE_FACE = 1;
        private final int MODALITY_CODE_FINGER = 2;
        private final int MODALITY_CODE_IRIS = 3;
        private final int MODALITY_CODE_VOICE = 4;

        private List<NFRecord> mFingers;

        private TextView mFingerCounter;


        // ===========================================================
        // Private methods
        // ===========================================================
        // ===========================================================
        // Private static fields
        // ===========================================================

        private static final String TAG = FingerClientActivity_old.class.getSimpleName();
        private static final String BUNDLE_KEY_STATUS = "status";
        private static final String MODALITY_ASSET_DIRECTORY = "fingers";
        private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;

        // ===========================================================
        // Private fields
        // ===========================================================

        private NFingerView mFingerView;
        private Bitmap mDefaultBitmap;
        private TextView mStatus,finger_label_value,finger_label_clientName,cfm_CTCCode;
        private ImageView handImage;
        private Map<String, NFPosition> mFingerPositions;
        private LinearLayout captureControls;
        private LinearLayout stopControls;
        private LinearLayout successControls;
        private ClientBiometricViewModel clientBiometricViewModel;
        private String clientName;
        private String ClientId ;
        private List<FingerTemplateElement> arrayList = new ArrayList<>();

        // ===========================================================
        // Private methods
        // ===========================================================
        private DrawerLayout drawer;
        private void setFingerPosition() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder( FingerClientActivity_old.this);
        builderSingle.setTitle("Select Finger Position");
        builderSingle.setCancelable(false);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( FingerClientActivity_old.this, android.R.layout.select_dialog_multichoice);

        arrayAdapter.add( MultiModalActivity.toLowerCase(NFPosition.UNKNOWN.name()));

        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.LEFT_LITTLE_FINGER.name()));
        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.LEFT_RING_FINGER.name()));
        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.LEFT_MIDDLE_FINGER.name()));
        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.LEFT_INDEX_FINGER.name()));
        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.LEFT_THUMB.name()));

        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.RIGHT_LITTLE_FINGER.name()));
        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.RIGHT_RING_FINGER.name()));
        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.RIGHT_MIDDLE_FINGER.name()));
        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.RIGHT_INDEX_FINGER.name()));
        arrayAdapter.add(MultiModalActivity.toLowerCase(NFPosition.RIGHT_THUMB.name()));

        builderSingle.setNegativeButton("Close", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int item) {
        String strName = arrayAdapter.getItem(item);
        final String element = strName;
        Log.v( "Finger Tay  ","Mickidadi "+strName);
        subject.getTemplate().getFingers().getRecords().get(0).setPosition(mFingerPositions.get(strName));
        }
        });
        builderSingle.show();
        }

        private NFScanner getScanner() {
        NDevice fingerDevice = null;
        for (NDevice device : client.getDeviceManager().getDevices()) {
        if (device.getDeviceType().contains(NDeviceType.FSCANNER)) {
        if (device.getId().equals(PreferenceManager.getDefaultSharedPreferences(this).getString(FingerPreferences.FINGER_CAPTURING_DEVICE, "None"))) {
        return (NFScanner) device;
        } else if (fingerDevice == null){
        fingerDevice = device;
        }
        }
        }
        return (NFScanner) fingerDevice;
        }
        private NSubject createSubjectFromImage(Uri uri) {
        NSubject subject = null;
        try {
        NImage image = NImageUtils.fromUri(this, uri);
        subject = new NSubject();
        NFinger finger = new NFinger();
        finger.setImage(image);
        subject.getFingers().add(finger);
        } catch (Exception e){
        Log.i(TAG, "Failed to load file as NImage");
        }
        return subject;
        }

        private NSubject createSubjectFromMemory(Uri uri) {
        NSubject subject = null;
        try {
        subject = NSubject.fromMemory( IOUtils.toByteBuffer(this, uri));
        } catch (Exception e) {
        Log.i(TAG, "Failed to load finger from file");
        }
        return subject;
        }

        // ===========================================================
        // Protected methods
        // ===========================================================

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clientBiometricViewModel=new ClientBiometricViewModel(getApplication());
        drawer = findViewById(R.id.drawer_finger_layout);
        //setContentView(R.layout.activity_client_finger_main_biometric);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ClientFingerSession", Context.MODE_PRIVATE);
        clientName = sharedPreferences.getString("fingerClientName", "");
        ClientId = sharedPreferences.getString("fingerClientId", "");
        // ===========================================================
        // Protected methods
        // ===========================================================

        mFingers = new ArrayList<NFRecord>();
        mFingerCounter = (TextView) findViewById(R.id.finger_counter);
        //LinearLayout left_container = findViewById(R.id.left_fingers_checkbox);
        List<String> selectedItems_left;
        //LinearLayout right_container = findViewById(R.id.right_fingers_checkbox);
        // ===========================================================
        // Protected methods
        // ===========================================================
        try {

        PreferenceManager.setDefaultValues(this, R.xml.finger_preferences, false);
        LinearLayout layout = ((LinearLayout) findViewById(R.id.multimodal_biometric_layout));
        captureControls = (LinearLayout) findViewById(R.id.multimodal_capture_controls);
        successControls = (LinearLayout) findViewById(R.id.multimodal_success_controls);
        stopControls = (LinearLayout) findViewById(R.id.multimodal_stop_controls);
        handImage = findViewById(R.id.hand_image);
        finger_label_value=findViewById( R.id.finger_label_value );

        finger_label_clientName=findViewById( R.id.finger_label_clientName );
        //finger_label_clientName.setText(clientName);
        cfm_CTCCode=findViewById( R.id.cfm_CTCCode );
        //cfm_CTCCode.setText(ClientId);
        mFingerPositions = new HashMap<String, NFPosition>();

        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.UNKNOWN.name()), NFPosition.UNKNOWN);
        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.LEFT_LITTLE_FINGER.name()), NFPosition.LEFT_LITTLE_FINGER);
        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.LEFT_RING_FINGER.name()), NFPosition.LEFT_RING_FINGER);
        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.LEFT_MIDDLE_FINGER.name()), NFPosition.LEFT_MIDDLE_FINGER);
        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.LEFT_INDEX_FINGER.name()), NFPosition.LEFT_INDEX_FINGER);
        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.LEFT_THUMB.name()), NFPosition.LEFT_THUMB);

        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.RIGHT_LITTLE_FINGER.name()), NFPosition.RIGHT_LITTLE_FINGER);
        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.RIGHT_RING_FINGER.name()), NFPosition.RIGHT_RING_FINGER);
        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.RIGHT_MIDDLE_FINGER.name()), NFPosition.RIGHT_MIDDLE_FINGER);
        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.RIGHT_INDEX_FINGER.name()), NFPosition.RIGHT_INDEX_FINGER);
        mFingerPositions.put(MultiModalActivity.toLowerCase(NFPosition.RIGHT_THUMB.name()), NFPosition.RIGHT_THUMB);

        // ===========================================================
        // CUSTOM FINGER SETTING
        // ===========================================================
        selectedItems_left = new ArrayList<>();
        for (String item_left : Util.getLeftFinger()) {
        MaterialCheckBox checkBox_left = new MaterialCheckBox(this);
        checkBox_left.setText(item_left);
        if(item_left=="LEFT_INDEX_FINGER") {
        checkBox_left.setChecked( true ); // Set checkbox to be selected by default
        selectedItems_left.add(item_left);
        finger_label_value.setText(item_left);
        getFinger(item_left);
        }
        checkBox_left.setTextSize( 11 );
        //left_container.addView(checkBox_left);

        checkBox_left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // selectedItems_left.add(item_left);
            selectedItems_left.add(item_left);
            Finger selectedFinger = Finger.valueOf(item_left); // Replace with code to get selected finger
            finger_label_value.setText(item_left);
            //subject.getTemplate().getFingers().getRecords().get(0).setPosition(mFingerPositions.get(selectedFinger));
            switch (selectedFinger) {

                case LEFT_THUMB:
                    handImage.setImageResource(R.mipmap.left_thumb_finger );
                    //mDefaultBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.left_thumb_finger);
                    break;
                case LEFT_INDEX_FINGER:
                    handImage.setImageResource(R.mipmap.left_index_finger );
                    //mDefaultBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.left_index_finger);
                    break;
                case LEFT_MIDDLE_FINGER:
                    handImage.setImageResource(R.mipmap.left_middle_finger );
                    //mDefaultBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.left_middle_finger);
                    break;
                case LEFT_RING_FINGER:
                    handImage.setImageResource(R.mipmap.left_ring_finger );
                    //mDefaultBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.left_ring_finger);
                    break;
                case LEFT_LITTLE_FINGER:
                    //mDefaultBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.left_pinky_finger);
                    handImage.setImageResource(R.mipmap.left_pinky_finger );
                    break;
            }
        } else {
            // Find the index of the item with value
            if(selectedItems_left.size()!=0) {
                int indexToRemove = selectedItems_left.indexOf(item_left);
                // Remove the item at that index
                selectedItems_left.remove( indexToRemove );
                if(selectedItems_left.size()!=0) {
                    finger_label_value.setText( selectedItems_left.get( 0 ) );
                    getFinger(selectedItems_left.get( 0 ));
                }else{
                    finger_label_value.setText("Finished");
                }
            }
            handImage.setImageResource(R.mipmap.left_hand_empty);
            //mDefaultBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.left_hand_empty);
            finger_label_value.setText("");
        }
        }
        });
        }
        //End finger
        //Start Right Finger
        for (String item_right : Util.getRightFinger()) {
        MaterialCheckBox checkBox_right = new MaterialCheckBox(this);
        checkBox_right.setText(item_right);
        checkBox_right.setTextSize( 11 );
        if(item_right=="RIGHT_INDEX_FINGER") {
        checkBox_right.setChecked( true ); // Set checkbox to be selected by default
        selectedItems_left.add(item_right);
        finger_label_value.setText(item_right);
        getFinger(item_right);
        }
        //right_container.addView(checkBox_right);

        checkBox_right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            selectedItems_left.add(item_right);
            Finger selectedFinger = Finger.valueOf(item_right); // Replace with code to get selected finger
            //subject.getTemplate().getFingers().getRecords().get(0).setPosition( NFPosition.valueOf(item_right) );
            finger_label_value.setText(item_right);
            switch (selectedFinger) {
                case RIGHT_THUMB:
                    handImage.setImageResource(R.mipmap.right_thumb_finger );
                    break;
                case RIGHT_INDEX_FINGER:
                    handImage.setImageResource(R.mipmap.right_index_finger );
                    break;
                case RIGHT_MIDDLE_FINGER:
                    handImage.setImageResource(R.mipmap.right_middle_finger );
                    break;
                case RIGHT_RING_FINGER:
                    handImage.setImageResource(R.mipmap.right_ring_finger );
                    break;
                case RIGHT_LITTLE_FINGER:
                    handImage.setImageResource(R.mipmap.right_pinky_finger );
                    break;
            }
        } else {
            // Find the index of the item with value
            if(selectedItems_left.size()!=0) {
                int indexToRemove = selectedItems_left.indexOf(item_right);
                // Remove the item at that index
                selectedItems_left.remove( indexToRemove );
                   if(selectedItems_left.size()!=0) {
                       finger_label_value.setText( selectedItems_left.get( 0 ) );
                       getFinger( selectedItems_left.get( 0 ) );
                   }
            }
            handImage.setImageResource(R.mipmap.right_hands_empty);
            finger_label_value.setText("");
        }
        Log.d("MyTag", "My array: " +selectedItems_left);
        }
        });
        }
        mFingerView = new NFingerView(this);
        layout.addView(mFingerView);

        mStatus = new TextView(this);
        mStatus.setText("Status");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mStatus.setLayoutParams(params);
        layout.addView(mStatus);

        mDefaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.menu_finger);
        if (savedInstanceState == null) {
        NFinger finger = new NFinger();
        finger.setImage(NImage.fromBitmap(mDefaultBitmap));
        mFingerView.setFinger(finger);
        }
        Button add = (Button) findViewById( R.id.multimodal_button_add);

        add.setText("Add");
        add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent intent = new Intent();
        Bundle b = new Bundle();
        String finger_display=finger_label_value.getText().toString();
        subject.getTemplate().getFingers().getRecords().get(0).setPosition( NFPosition.valueOf(finger_display) );
        // Create an ArrayList to store custom elements
        byte[] nFTemplate = subject.getTemplate().getFingers().save().toByteArray();
        //String fingerPosition =finger_display;
        FingerTemplateElement element1 = new FingerTemplateElement(nFTemplate, finger_display);
        arrayList.add(element1);
              if(arrayList.size()==2) {
                  String message="Finger Data Saved Successfully";
                for (FingerTemplateElement elements : arrayList) {
        ClientBiometricEntity facility = new ClientBiometricEntity(ClientId,elements.getByteTemplate(),"",
                "V1",elements.getFingerPosition(),"1",1,
                1,1,0,"");
                    clientBiometricViewModel.insert(facility);
                        //end register Facility

                    Log.v( "Finger Payload ", "Mickidadi " + elements.getByteTemplate() + " " + elements.getFingerPosition() );
                }
                showInfo(message);
            }
        //End
        int count=0;
        int quality =0;
        String fingerId = "UN";
        String finger_label =null;

        b.putByteArray(RECORD_REQUEST_FINGER, Arrays.copyOf(nFTemplate, nFTemplate.length));
        intent.putExtras(b);
        setResult(RESULT_OK, intent);
        //#################################
        ActivityResult(-1, intent);
        //#################################
        //finger_label_value.setText(" Template Data :"+count);
        captureControls.setVisibility(View.VISIBLE);
        stopControls.setVisibility(View.GONE);
        successControls.setVisibility(View.GONE);
        Log.d("MyTag", "My array before: " +selectedItems_left);
        String right_pinky_fingers ="Finished";
        if(selectedItems_left.size()!=0) {
        int indexToRemove = selectedItems_left.indexOf(finger_display);
        // Remove the item at that index
        selectedItems_left.remove(indexToRemove);
        if(selectedItems_left.size()!=0) {
            right_pinky_fingers = selectedItems_left.get( 0 );
            finger_label_value.setText( right_pinky_fingers );
            getFinger(right_pinky_fingers);
        }else{
            finger_label_value.setText("Finished");
        }
        }else{
        finger_label_value.setText("Finished");
        }
        ///  subject.getTemplate().getFingers().getRecords().get(0).setPosition( NFPosition.valueOf( right_pinky_fingers ) );

        Log.v( "Position Name IN :",right_pinky_fingers);
        //  return;
        startFinger();
        Log.d("MyTag", "My array: after :" +selectedItems_left);
        }
        });
        Button enrollSubject = (Button) findViewById(R.id.multimodal_button_enroll_client);
        enrollSubject.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Log.v( "Subject","Message"+mFingers);
        if (ClientId == "" || ClientId.isEmpty()) {
        showError("Missing subject ID");
        } else {
            NSubject subject = createSubjectFromRecords(mFingers);

               Log.v("Mickidadi ", String.valueOf( arrayList ) );
            for (NFRecord record :subject.getTemplate().getFingers().getRecords()) {
                //Get finger Quality Template
                int quality =record.getQuality();
               // Get the finger position from the record
                String position = record.getPosition().name();

            }

        }
        }
        });
        Button setPosition = (Button) findViewById(R.id.multimodal_button_unbound);
        setPosition.setText("Set Position");
        setPosition.setVisibility(View.VISIBLE);
        setPosition.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        //setFingerPosition();
        drawer.openDrawer( GravityCompat.END);
        }
        });

        } catch (Exception e) {
        showError(e);
        }
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        // If permission is granted, do something
        createDirectory();
        } else {
        // If permission is not granted, show a message or do something else
        Toast.makeText(this, "Permission denied to write to external storage", Toast.LENGTH_SHORT).show();
        }
        }
        }

        private void createDirectory() {
        //String directoryPath = getExternalStorageDirectory(null) + "/templates";
        File directory = new File(getExternalFilesDir(null), "templates");
        //File directory = new File(directoryPath);

        if (!directory.exists()) {
        boolean success = directory.mkdir();
        if (success) {
        Log.d("TAG", "Directory created successfully"+directory.getAbsolutePath());
        } else {
        Log.e("TAG", "Failed to create directory"+directory.getAbsolutePath());
        }
        } else {
        Log.d("TAG", "Directory already exists"+directory.getAbsolutePath());
        }
        }
        @Override
        protected void onSaveInstanceState(Bundle outState) {
        outState.putString(BUNDLE_KEY_STATUS, TextUtils.isEmpty(mStatus.getText()) ? "" : mStatus.getText().toString());
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
        return FingerPreferences.class;
        }

        @Override
        protected void updatePreferences(NBiometricClient client) {
        FingerPreferences.updateClient(client, this);
        }

        @Override
        protected boolean isCheckForDuplicates() {
        return FingerPreferences.isCheckForDuplicates(this);
        }

        @Override
        protected String getModalityAssetDirectory() {
        return MODALITY_ASSET_DIRECTORY;
        }

        @Override
        protected void onFileSelected(Uri uri) throws Exception {
        NSubject subject = null;
        mFingerView.setShownImage(FingerPreferences.isReturnBinarizedImage(this) ? ShownImage.RESULT : ShownImage.ORIGINAL);
        subject = createSubjectFromImage(uri);

        boolean isDetectLiveness = FingerPreferences.isDetectLiveness(this);
        client.setFingersDetectLiveness(isDetectLiveness);

        if (subject == null) {
        subject = createSubjectFromMemory(uri);
        }

        if (subject != null) {
        if (subject.getFingers() != null && subject.getFingers().get(0) != null) {
        mFingerView.setFinger(subject.getFingers().get(0));
        }
        extract(subject);
        } else {
        showInfo(R.string.msg_failed_to_load_image_or_standard);
        }
        }

        @Override
        protected void onStartCapturing() {
        NFScanner scanner = getScanner();
        if (scanner == null) {
        showError(R.string.msg_capturing_device_is_unavailable);
        } else {
        client.setFingerScanner(scanner);
        NSubject subject = new NSubject();
        NFinger finger = new NFinger();
        boolean isDetectLiveness = FingerPreferences.isDetectLiveness(this);
        client.setFingersDetectLiveness(isDetectLiveness);
        finger.addPropertyChangeListener(biometricPropertyChanged);
        mFingerView.setShownImage(FingerPreferences.isReturnBinarizedImage(this) ? ShownImage.RESULT : ShownImage.ORIGINAL);
        mFingerView.setFinger(finger);
        //finger_label_quality.setText( "Finger Quality ");
        subject.getFingers().add(finger);
        capture(subject, null);

        }
        }

        @Override
        protected void onStatusChanged(final NBiometricStatus value) {
        runOnUiThread(() -> {
        mStatus.setText(value == null ? "" : ResourceUtils.getEnum( FingerClientActivity_old.this, value));
        });
        }

        public static List<String> mandatoryComponents() {
        return Arrays.asList( LicensingManager.LICENSE_FINGER_DETECTION,
        LicensingManager.LICENSE_FINGER_EXTRACTION,
        LicensingManager.LICENSE_FINGER_MATCHING,
        LicensingManager.LICENSE_FINGER_DEVICES_SCANNERS);
        }

        public static List<String> additionalComponents() {
        return Arrays.asList(LicensingManager.LICENSE_FINGER_WSQ,
        LicensingManager.LICENSE_FINGER_STANDARDS_FINGER_TEMPLATES,
        LicensingManager.LICENSE_FINGER_STANDARDS_FINGERS);
        //			LicensingManager.LICENSE_FINGER_QUALITY_ASSESSMENT,
        //			LicensingManager.LICENSE_FINGER_SEGMENTS_DETECTION);
        }
        public void getFinger(String finger){

        Finger selectedFinger = Finger.valueOf(finger); // Replace with code to get selected finger
        // finger_label_value.setText(item_right);
        switch (selectedFinger) {
        case RIGHT_THUMB:
        handImage.setImageResource( R.mipmap.right_thumb_finger );
        break;
        case RIGHT_INDEX_FINGER:
        handImage.setImageResource(R.mipmap.right_index_finger );
        break;
        case RIGHT_MIDDLE_FINGER:
        handImage.setImageResource(R.mipmap.right_middle_finger );
        break;
        case RIGHT_RING_FINGER:
        handImage.setImageResource(R.mipmap.right_ring_finger );
        break;
        case RIGHT_LITTLE_FINGER:
        handImage.setImageResource(R.mipmap.right_pinky_finger );
        break;
        case LEFT_THUMB:
        handImage.setImageResource(R.mipmap.left_thumb_finger );
        break;
        case LEFT_INDEX_FINGER:
        handImage.setImageResource(R.mipmap.left_index_finger );
        break;
        case LEFT_MIDDLE_FINGER:
        handImage.setImageResource(R.mipmap.left_middle_finger );
        break;
        case LEFT_RING_FINGER:
        handImage.setImageResource(R.mipmap.left_ring_finger );
        break;
        case LEFT_LITTLE_FINGER:
        handImage.setImageResource(R.mipmap.left_pinky_finger );
        break;
        }
        }
        public void startFinger(){
        mDefaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.menu_finger);
        //if (savedInstanceState == null) {
        NFinger finger = new NFinger();
        finger.setImage(NImage.fromBitmap(mDefaultBitmap));
        mFingerView.setFinger(finger);
        //}
        }
        public void ActivityResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
        if (data != null) {
        Bundle b = data.getExtras();
        byte[] template = b.getByteArray("finger");
        NFTemplate nFTemplate = new NFTemplate(new NBuffer(template));
        if (nFTemplate != null) {
            for (NFRecord rec : nFTemplate.getRecords()) {
                mFingers.add(rec);

            }
        }
        }
        }
        updateRecordCount(mFingers);
        }
        private void updateRecordCount(List<NFRecord> fingers) {
        if (fingers != null) {
        mFingerCounter.setText(String.valueOf(fingers.size()));
        }

        }
        private NSubject createSubjectFromRecords(List<NFRecord>  fingers ) {
        if (!fingers.isEmpty()) {
        NSubject subject = new NSubject();
        NTemplate template = new NTemplate();
        NFTemplate fingerTemplate = new NFTemplate();
        for (NFRecord record : fingers) {
        fingerTemplate.getRecords().add(record);
        }
        template.setFingers(fingerTemplate);
        subject.setTemplate(template);
        return subject;
        } else {
        return null;
        }
        }
        private CompletionHandler<NBiometricTask, NBiometricOperation> completionHandler = new CompletionHandler<NBiometricTask, NBiometricOperation>() {
        @Override
        public void completed(NBiometricTask task, NBiometricOperation operation) {
        try {

        String message = null;
        NBiometricStatus status = task.getStatus();
        Log.i(TAG, String.format("Operation: %s, Status: %s", operation, status));

        if (status == NBiometricStatus.CANCELED) return;

        if (task.getError() != null) {
        showError(task.getError());
        } else {
        switch (operation) {
        case ENROLL:
        case ENROLL_WITH_DUPLICATE_CHECK: {
            if (status == NBiometricStatus.OK) {
                message = getString(R.string.msg_enrollment_succeeded);
            } else {
                message = getString( R.string.msg_enrollment_failed, status.toString());
            }
        } break;
        case IDENTIFY: {
            if (status == NBiometricStatus.OK) {
                StringBuilder sb = new StringBuilder();
                NSubject subject = task.getSubjects().get(0);
                for (NMatchingResult result : subject.getMatchingResults()) {
                    sb.append("MATCHED WITH: " + getString(R.string.msg_identification_results, result.getId())).append('\n');
                    sb.append("\tMatching score: " + result.getScore() + "\n");
                    if(result.getMatchingDetails() != null && !result.getMatchingDetails().getFaces().isEmpty()) {
                        int index = 0;
                        sb.append("\tFaces fused score: " + result.getMatchingDetails().getFacesScore() + "\n");
                        for (NLMatchingDetails score : result.getMatchingDetails().getFaces()) {
                            sb.append("\t\tNL " + index + " with: " + score.getMatchedIndex() + " Score: " + score.getScore() + "\n");
                            index++;
                        }
                    }
                    if(result.getMatchingDetails() != null && !result.getMatchingDetails().getFingers().isEmpty()) {
                        int index = 0;
                        sb.append("\tFingers fused score: " + result.getMatchingDetails().getFingersScore() + "\n");
                        for (NFMatchingDetails score : result.getMatchingDetails().getFingers()) {
                            sb.append("\t\tNF " + index + " with: " + score.getMatchedIndex() + " Score: " + score.getScore() + "\n");
                            index++;
                        }
                    }
                    if(result.getMatchingDetails() != null && !result.getMatchingDetails().getIrises().isEmpty()) {
                        int index = 0;
                        sb.append("\tIrises fused score: " + result.getMatchingDetails().getIrisesScore() + "\n");
                        for (NEMatchingDetails score : result.getMatchingDetails().getIrises()) {
                            sb.append("\t\tNI " + index + " with: " + score.getMatchedIndex() + " Score: " + score.getScore() + "\n");
                            index++;
                        }
                    }
                    if(result.getMatchingDetails() != null && !result.getMatchingDetails().getVoices().isEmpty()) {
                        int index = 0;
                        sb.append("\tVoices fused score: " + result.getMatchingDetails().getVoicesScore() + "\n");
                        for (NSMatchingDetails score : result.getMatchingDetails().getVoices()) {
                            sb.append("\t\tNS " + index + " with: " + score.getMatchedIndex() + " Score: " + score.getScore() + "\n");
                            index++;
                        }
                    }
                    sb.append("\n");
                }
                message = sb.toString();
            } else {
                message = getString(R.string.msg_no_matches);
            }
        } break;
        case VERIFY: {
            if (status == NBiometricStatus.OK) {
                StringBuilder sb = new StringBuilder();
                message = getString(R.string.msg_verification_succeeded);
            } else {
                message = getString(R.string.msg_verification_failed, status.toString());
            }
        } break;
        default: {
            throw new AssertionError("Invalid NBiometricOperation");
        }
        }
        }
        showInfo(message);
        } catch (Exception e) {
        Log.e(TAG, e.getMessage(), e);
        }
        }

        @Override
        public void failed(Throwable throwable, NBiometricOperation nBiometricOperation) {
        }
        };

        }
