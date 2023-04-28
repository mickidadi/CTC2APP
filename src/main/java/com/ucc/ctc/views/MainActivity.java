package com.ucc.ctc.views;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.neurotec.biometrics.NERecord;
import com.neurotec.biometrics.NFRecord;
import com.neurotec.biometrics.NLRecord;
import com.neurotec.biometrics.NSRecord;
import com.neurotec.biometrics.client.NBiometricClient;
import com.neurotec.lang.NCore;
import com.ucc.ctc.R;
import com.ucc.ctc.licensing.LicensingManager;
import com.ucc.ctc.multibiometric.Model;
import com.ucc.ctc.multibiometric.multimodal.FaceActivity;
import com.ucc.ctc.multibiometric.multimodal.FingerActivity;
import com.ucc.ctc.multibiometric.multimodal.IrisActivity;
import com.ucc.ctc.multibiometric.multimodal.MultiModalActivity;
import com.ucc.ctc.multibiometric.multimodal.VoiceActivity;
import com.ucc.ctc.util.ExceptionUtils;
import com.ucc.ctc.util.ToastManager;
import com.ucc.ctc.view.ErrorDialogFragment;
import com.ucc.ctc.view.InfoDialogFragment;

import java.util.ArrayList;
import java.util.List;

/*

        Developed by Mickidadi Kosiyanga
extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback

AppCompatActivity
        */

public class MainActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback,NavigationView.OnNavigationItemSelectedListener {
    // ===========================================================
    // private static fields
    // ===========================================================

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private static final String WARNING_PROCEED_WITH_NOT_GRANTED_PERMISSIONS = "Do you wish to proceed without granting all permissions?";
    private static final String WARNING_NOT_ALL_GRANTED = "Some permissions are not granted.";
    private static final String MESSAGE_ALL_PERMISSIONS_GRANTED = "All permissions granted";

    private static String TAG = MultiModalActivity.class.getSimpleName();

    // ===========================================================
    // Private fields
    // ===========================================================

    private final int MODALITY_CODE_FACE = 1;
    private final int MODALITY_CODE_FINGER = 2;
    private final int MODALITY_CODE_IRIS = 3;
    private final int MODALITY_CODE_VOICE = 4;

    private List<NLRecord> mFaces;
    private List<NFRecord> mFingers;
    private List<NERecord> mIris;
    private List<NSRecord> mVoice;
    private DrawerLayout drawer;
    // ===========================================================
    // Private fields
    // ===========================================================

    private ProgressDialog mProgressDialog;

    // ===========================================================
    // Protected methods
    // ===========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //  =====   Activity launcher

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
         NCore.setContext(this);
        //  =====   Toolbar setup

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("facilitySession", Context.MODE_PRIVATE);
        //String name = sharedPreferences.getString("facilityNamesession", "");
        String facilityName = sharedPreferences.getString("facilityNamesession", "");
        String facilityId = sharedPreferences.getString("facilityIdsession", "");
        ///Log.d("MyActivity", "Name: " + facilityName);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor( Color.WHITE);
        getSupportActionBar().setTitle(facilityName);
        //Toast.makeText(MainActivity.this, facilityId+" valid username "+facilityName, Toast.LENGTH_SHORT).show();
        //  =====   Drawer setup

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //  =====   Navigation Drawer

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //  =====   First Fragment

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        String[] neededPermissions = getNotGrantedPermissions();
        if(neededPermissions.length == 0) {
            new MainActivity.InitializationTask().execute();
        } else {
            requestPermissions(neededPermissions);
        }
        //LOGOUT btn_close_filter
       ImageButton btn_close_filter = findViewById( R.id.btn_close_filter);
        btn_close_filter.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        } );
        LinearLayout clientRegistration =(LinearLayout) findViewById( R.id.navs_registration);
          clientRegistration.setOnClickListener( new View.OnClickListener() {
              @Override
              public void onClick(View v) {
      startActivity(new Intent(MainActivity.this, ClientActivity.class));
              }
          } );


       LinearLayout navs_hts =(LinearLayout) findViewById( R.id.navs_hts);
        navs_hts.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MultiModalActivity.class ));
            }
        } );
        LinearLayout navs_recency =(LinearLayout) findViewById( R.id.navs_recency);
         navs_recency.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, ClientFingerPrintActivity.class));
                startActivity(new Intent(MainActivity.this, com.ucc.ctc.views.FingerActivity.class));
            }
        } );
      LinearLayout navs_ctc =(LinearLayout) findViewById( R.id.navs_ctc);
        navs_ctc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DownloadClientDataActivity.class));
            }
        } );
        LinearLayout navs_covid =(LinearLayout) findViewById( R.id.navs_covid);
        navs_covid.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DownloadClientDataActivity.class));
            }
        } );

        LinearLayout navs_data_sync =(LinearLayout) findViewById( R.id.navs_data_sync);
        navs_data_sync.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DownloadClientDataActivity.class));
            }
        } );

    }

    //  =====   Back drawer

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;



        }

        // Handle navigation view item clicks here. nav_registration

        int id = item.getItemId();
       if (id == R.id.nav_log) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        else if (id == R.id.nav_registration) {
            startActivity(new Intent(MainActivity.this, ClientActivity.class));
        }//
        else if (id == R.id.navs_hts) {
            startActivity(new Intent(MainActivity.this, MultiModalActivity.class ));
        }
       else if (id == R.id.nav_covid) {
           //UserProfileActivity
        //   startActivity(new Intent(MainActivity.this, ClientFingerPrintActivity.class));
           startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
       }
       else if (id == R.id.nav_data_sync) {
           startActivity(new Intent(MainActivity.this, DownloadClientDataActivity.class));
       }
       //nav_covid
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    final class InitializationTask extends AsyncTask<Object, Boolean, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress(R.string.msg_initializing);
        }

        @Override
        protected Boolean doInBackground(Object... params) {
            showProgress(R.string.msg_obtaining_licenses);
            try {
                LicensingManager.getInstance().obtain(MainActivity.this, getAdditionalComponentsInternal());
                if (LicensingManager.getInstance().obtain(MainActivity.this, getMandatoryComponentsInternal())) {
                    showToast(R.string.msg_licenses_obtained);
                } else {
                    showToast(R.string.msg_licenses_partially_obtained);
                }
            } catch (Exception e) {
                showError(e.getMessage(), false);
            }
            showProgress(R.string.msg_initializing_client);

            try {
                NBiometricClient client = Model.getInstance().getClient();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            hideProgress();
        }
    }
    protected void showProgress(int messageId) {
        showProgress(getString(messageId));
    }

    protected void showProgress(final String message) {
        hideProgress();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressDialog = ProgressDialog.show(MainActivity.this, "", message);
            }
        });
    }

    protected void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    protected void showToast(int messageId) {
        showToast(getString(messageId));
    }

    protected void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastManager.show(MainActivity.this, message);
            }
        });
    }

    protected void showError(String message, boolean close) {
        ErrorDialogFragment.newInstance(message, close).show(getFragmentManager(), "error");
    }

    protected void showError(int messageId) {
        showError(getString(messageId));
    }

    protected void showError(String message) {
        showError(message, false);
    }

    protected void showError(Throwable th) {
        Log.e(getClass().getSimpleName(), "Exception", th);
        showError( ExceptionUtils.getMessage(th), false);
    }

    protected void showInfo(int messageId) {
        showInfo(getString(messageId));
    }

    protected void showInfo(String message) {
        InfoDialogFragment.newInstance(message).show(getFragmentManager(), "info");
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideProgress();
    }
    // ===========================================================
    // Private methods
    // ===========================================================

    private static List<String> getMandatoryComponentsInternal() {
        List<String> components = new ArrayList<String>();
        for (String component : FaceActivity.mandatoryComponents()) {
            if (!components.contains(component)) {
                components.add(component);
            }
        }
        for (String component : com.ucc.ctc.multibiometric.multimodal.FingerActivity.mandatoryComponents()) {
            if (!components.contains(component)) {
                components.add(component);
            }
        }
        for (String component : IrisActivity.mandatoryComponents()) {
            if (!components.contains(component)) {
                components.add(component);
            }
        }
        for (String component : VoiceActivity.mandatoryComponents()) {
            if (!components.contains(component)) {
                components.add(component);
            }
        }
        return components;
    }

    private static List<String> getAdditionalComponentsInternal() {
        List<String> components = new ArrayList<String>();
        for (String component : FaceActivity.additionalComponents()) {
            if (!components.contains(component)) {
                components.add(component);
            }
        }
        for (String component : FingerActivity.additionalComponents()) {
            if (!components.contains(component)) {
                components.add(component);
            }
        }
        for (String component : IrisActivity.additionalComponents()) {
            if (!components.contains(component)) {
                components.add(component);
            }
        }
        for (String component : VoiceActivity.additionalComponents()) {
            if (!components.contains(component)) {
                components.add(component);
            }
        }
        return components;
    }
    private static List<String> getRequiredPermissions() {
        List<String> permissions = new ArrayList<String>();
        permissions.add( Manifest.permission.INTERNET);
        permissions.add(Manifest.permission.ACCESS_NETWORK_STATE);
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.RECORD_AUDIO);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (android.os.Build.VERSION.SDK_INT < 23) {
            permissions.add(Manifest.permission.WRITE_SETTINGS);
        }
        return permissions;
    }

    private String[] getNotGrantedPermissions() {
        List<String> neededPermissions = new ArrayList<String>();

        for (String permission : getRequiredPermissions()) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                neededPermissions.add(permission);
            }
        }
        return neededPermissions.toArray(new String[neededPermissions.size()]);
    }

    private void requestPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions,REQUEST_ID_MULTIPLE_PERMISSIONS);
    }
}
