package com.ucc.ctc.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.ucc.ctc.R;
import com.ucc.ctc.models.FacilitySearchResponse;
import com.ucc.ctc.models.entity.FacilityEntity;
import com.ucc.ctc.repository.FacilityRepository;
import com.ucc.ctc.viewsModel.FacilitySearchViewModel;
import com.ucc.ctc.viewsModel.FacilityViewModel;

import java.net.SocketTimeoutException;

public class FacilityConfirmationPageActivity extends AppCompatActivity {
    private FacilitySearchViewModel viewModel;
    private FacilityViewModel facilityViewModel;
    ///private FacilityRepository facilityRepository = new FacilityRepository(application);;
    private FacilityRepository facilityRepository = new FacilityRepository(getApplication());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_facility_confirmation_page );
        //  =====   Toolbar setup
       // Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("FACILITY CONFIRMATION PAGE");
        final ImageButton cancel =findViewById( R.id.cancel_facility_confirmation );
        final Button saveFacility=findViewById( R.id.action_facility_confirmation_save );
        //get text item
        TextView cfm_facility_name =findViewById( R.id.cfm_facility_name );
        TextView cfm_facility_hfr_code=findViewById( R.id.cfm_facility_hfr_code);
        TextView cfm_CTCCode=findViewById( R.id.cfm_CTCCode);
        TextView cfm_facility_type=findViewById( R.id.cfm_facility_type );
        TextView cfm_region_name=findViewById( R.id.cfm_region_name );
        TextView cfm_council_name=findViewById( R.id.cfm_council_name );
        TextView cfm_ward_name=findViewById( R.id.cfm_ward_name );
        TextView ctcUrl=findViewById( R.id.ctcUrl );
         //EndctcUrl
        Intent intent = getIntent();
        String keyword = intent.getStringExtra("hfrId");
        String url = intent.getStringExtra("url");
        //viewModel = ViewModelProviders.of( this ).get( FacilitySearchViewModel.class );
        // Perform your network request here#

        viewModel = new ViewModelProvider(this).get(FacilitySearchViewModel.class);
        viewModel.init(url, keyword );
        viewModel.getFacilityLiveData().observe( this, new Observer<FacilitySearchResponse>() {
            @Override
            public void onChanged(FacilitySearchResponse facility) {
                Log.v("Welcome","Message IN ");
                if (facility != null) {
                    cfm_facility_hfr_code.setText( facility.getFacilityId() );
                    cfm_CTCCode.setText( facility.getCTCCode() );
                    cfm_facility_name.setText( facility.getFacilityName());
                    cfm_facility_type.setText( facility.getFacilityType() );
                    cfm_region_name.setText( facility.getRegion() );
                    cfm_council_name.setText( facility.getCouncil() );
                    cfm_ward_name.setText( facility.getWard() );
                    ctcUrl.setText(url);
                    Log.v("Hello Test ","Display Facility Detail");
                }else{
                    cfm_facility_name.setText("Server Connection Failed" );
                }
            }
        } );
       cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCancel=new Intent(FacilityConfirmationPageActivity.this,FacilitySettingActivity.class);
                startActivity( intentCancel );


            }
        } );
        saveFacility.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Add a new Facility
                String facility_name =cfm_facility_name.getText().toString();
                String facility_hfr_code=cfm_facility_hfr_code.getText().toString();
                //String CTCCode=cfm_CTCCode.getText().toString();
                String CTCCode="777777777";
                String facility_type=cfm_facility_type.getText().toString();
                String region_name=cfm_region_name.getText().toString();
                String council_name=cfm_council_name.getText().toString();
                String ward_name=cfm_ward_name.getText().toString();
                String ctcUrls=ctcUrl.getText().toString();;
                //check if facility exist
               // Log.v("Hello Test ","Display facility Detail"+facility_name);
                // facilityId, String CTCCode, String facilityName, String facilityType,

                         if(!TextUtils.isEmpty( facility_hfr_code)||!TextUtils.isEmpty(facility_name)) {
                             FacilityEntity facility = new FacilityEntity( facility_hfr_code, CTCCode, facility_name, facility_type, region_name, council_name, ward_name, ctcUrls );
                             facilityRepository.insert(facility);
                             //end register Facility
                         }
                         Intent intentSave = new Intent( FacilityConfirmationPageActivity.this, LoginFacilityActivity.class );
                          startActivity( intentSave );

            }
        } );
    }
    private void showErrorMessage(String message) {
        View rootView = findViewById(android.R.id.content); // Replace with the root view of your activity or fragment
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }
}