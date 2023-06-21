package com.ucc.ctc.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.ucc.ctc.R;
import com.ucc.ctc.utils.Tools;
import com.ucc.ctc.utils.ViewAnimation;

public class DeviceSettingActivity extends AppCompatActivity {
    private NestedScrollView nested_scroll_view;
    private ImageButton bt_toggle_licensing_activation, bt_toggle_biometry_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_device_setting );
        LinearLayout facility_setting =(LinearLayout) findViewById( R.id.facility_setting_update);
        facility_setting.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeviceSettingActivity.this, FacilityPageActivity.class));
            }
        } );
        LinearLayout facility_data_syn =(LinearLayout) findViewById( R.id.device_sync_data);
        facility_data_syn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeviceSettingActivity.this, DownloadClientDataActivity.class));
            }
        } );
        LinearLayout facility_biometric =(LinearLayout) findViewById( R.id.device_biometric);
        facility_biometric.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeviceSettingActivity.this, LicenseActivationActivity.class));
            }
        } );
     }

}