package com.ucc.ctc.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ucc.ctc.R;
import com.ucc.ctc.viewsModel.FacilitySearchViewModel;

public class FacilitySettingActivity extends AppCompatActivity {
private  EditText hfrId;
private EditText webUrl;
private TextView message;
private String url ;
private String keyword;
private FacilitySearchViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_facility_setting );
        final Button cancel=findViewById( R.id.cancel_web_extension_url );
        final Button save=findViewById( R.id.action_web_url_save );
              hfrId=findViewById( R.id.ctc_hfr_code);
              webUrl=findViewById( R.id.ctc_web_url );
              message=findViewById( R.id.ctc_facility_setting_message );
              //performSearch();

        cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FacilitySettingActivity.this,LoginFacilityActivity.class)  ;
                 startActivity(intent);
                finish();
            }
        } );
        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Facility confirmation Page
                //  performSearch();
                View focusView = null;
                boolean isValid=true;
                keyword = hfrId.getEditableText().toString();
                url = webUrl.getEditableText().toString();
                if (keyword.isEmpty()) {
                    hfrId.setError("This field is required");
                    isValid=false;
                    focusView=hfrId;
                }
                if (url.isEmpty()) {
                    webUrl.setError("This field is required");
                    isValid=false;
                    focusView=webUrl;
                }
                if (isValid) {
                    Intent intentSave = new Intent( FacilitySettingActivity.this, FacilityConfirmationPageActivity.class );
                    intentSave.putExtra( "hfrId", keyword );
                    startActivity( intentSave );
                    finish();
                    //end
                }else{
                    focusView.requestFocus();
                }
            }
        } );


    }

    private boolean CheckAllFields() {
        boolean isValid=true;
        if (keyword.isEmpty()) {
            hfrId.setError("This field is required");
            isValid=false;
        }
        if (url.isEmpty()) {
            webUrl.setError("This field is required");
            isValid=false;
        }
      return  isValid;
    }
}