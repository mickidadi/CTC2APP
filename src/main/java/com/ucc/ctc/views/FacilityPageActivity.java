package com.ucc.ctc.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textview.MaterialTextView;
import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.ClientTreatmentSupporterEntity;
import com.ucc.ctc.models.entity.FacilityEntity;
import com.ucc.ctc.utils.Util;
import com.ucc.ctc.viewsModel.ClientTreatmentSupporterViewModel;
import com.ucc.ctc.viewsModel.FacilityViewModel;

import java.util.ArrayList;
import java.util.List;

public class FacilityPageActivity extends AppCompatActivity{

    public static final String facilityName = "facilityName";
    private FacilityViewModel facilityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_facility_page );

        MaterialTextView update_facility_name =findViewById( R.id.update_facility_name);
        MaterialTextView update_facility_type =findViewById( R.id.update_facility_type );
        EditText update_web_url =findViewById( R.id.update_ctc_web_url );
        TextView mobilefacilityId=findViewById( R.id.mobilefacilityId );

        TextView cfm_facility_hfr_code=findViewById( R.id.cfm_facility_hfr_code);
        TextView cfm_CTCCode=findViewById( R.id.cfm_CTCCode);

        TextView cfm_region_name=findViewById( R.id.cfm_region_name );
        TextView cfm_council_name=findViewById( R.id.cfm_council_name );
        TextView cfm_ward_name=findViewById( R.id.cfm_ward_name );


        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("facilitySession", Context.MODE_PRIVATE);
        //String name = sharedPreferences.getString("facilityNamesession", "");
        String facilityName = sharedPreferences.getString("facilityNamesession", "");
        String facilityId = sharedPreferences.getString("facilityIdsession", "");
        Log.v( "facility Id",facilityId );
        //Get facility data
        //String hfrId="1292012-8";
        facilityViewModel = ViewModelProviders.of(this).get( FacilityViewModel.class);
        facilityViewModel.getFacilityById(facilityId).observe( this, new Observer<FacilityEntity>() {
            @Override
            public void onChanged(FacilityEntity facilityEntity) {
                if (facilityEntity != null) {
                    // Display Facility Data
                    Log.v( "facility Name",facilityEntity.getFacilityName() );

                    update_facility_name.setText( facilityEntity.getFacilityName() );
                    update_facility_type.setText( facilityEntity.getFacilityType());
                    update_web_url.setText( facilityEntity.getCtcWebUrl());

                    String Id=""+facilityEntity.getId();
                    mobilefacilityId.setText(Id);
                    cfm_facility_hfr_code.setText( facilityEntity.getFacilityId() );
                    cfm_CTCCode.setText(facilityEntity.getCTCCode() );
                    cfm_region_name.setText( facilityEntity.getRegion() );
                    cfm_council_name.setText( facilityEntity.getCouncil() );
                    cfm_ward_name.setText( facilityEntity.getWard() );

                   }
            }
        } );
        Button   saveFacility =findViewById(R.id.facility_update);
        saveFacility.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Add a new Facility
                String mfacilityId=mobilefacilityId.getText().toString();
                String ctcUrls=update_web_url.getText().toString();
                String facility_hfr_code=cfm_facility_hfr_code.getText().toString();
                String CTCCode=cfm_CTCCode.getText().toString();
                String region_name=cfm_region_name.getText().toString();
                String council_name=cfm_council_name.getText().toString();
                String ward_name=cfm_ward_name.getText().toString();
                String facility_name =update_facility_name.getText().toString();

                String facility_type=update_facility_type.getText().toString();
                boolean isValid=true;
                View focusView = null;
                if (ctcUrls.isEmpty()) {
                    update_web_url.setError("This field is required");
                    isValid=false;
                    focusView=update_web_url;
                }
                 if (mfacilityId.isEmpty()) {
                     update_web_url.setError("This field is required");
                    isValid=false;
                    focusView=update_web_url;
                }
                if (!Util.isValidIPAddress(ctcUrls)) {
                    update_web_url.setError("Invalid IP address format.");
                    isValid=false;
                    focusView=update_web_url;
                }
                if(isValid) {
                    FacilityEntity facility = new FacilityEntity( facility_hfr_code, CTCCode, facility_name, facility_type, region_name, council_name, ward_name, ctcUrls );
                    facility.setId( Integer.parseInt( mfacilityId ) );
                    facilityViewModel.update(facility);
                    //end register Facility
                    Toast.makeText(FacilityPageActivity.this, "Facility Updated Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    focusView.requestFocus();
                //    return;
                }

       }
        } );
}

}