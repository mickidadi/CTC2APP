package com.ucc.ctc.multibiometric;

import android.app.Application;
import android.util.Log;

import com.neurotec.licensing.NLicenseManager;
import com.neurotec.licensing.gui.LicensingPreferencesFragment;

public final class BiometricApplication extends Application {

	// ===========================================================
	// Private static fields
	// ===========================================================

	private static final String TAG = BiometricApplication.class.getSimpleName();

	// ===========================================================
	// Public static fields
	// ===========================================================

	public static final String APP_NAME = "multibiometric";

	// ===========================================================
	// Public methods
	// ===========================================================

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			NLicenseManager.setTrialMode(LicensingPreferencesFragment.isUseTrial(this));
			System.setProperty("jna.nounpack", "true");
			System.setProperty("java.io.tmpdir", getCacheDir().getAbsolutePath());
		} catch (Exception e) {
			Log.e(TAG, "Exception", e);
		}
	}
}
