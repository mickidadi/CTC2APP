package com.ucc.ctc.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ucc.ctc.R;
import com.ucc.ctc.utils.Util;
import com.ucc.ctc.viewsModel.FacilitySearchViewModel;

import java.io.IOException;
import java.net.InetAddress;

public class FacilitySettingActivity extends AppCompatActivity {
private  EditText hfrId;
private EditText webUrl;
private String url ;
private String keyword;
private FacilitySearchViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_facility_setting );

        final Button save=findViewById( R.id.action_web_url_save );
              hfrId=findViewById( R.id.ctc_hfr_code);
              webUrl=findViewById( R.id.ctc_web_url );
               //performSearch();

        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Facility confirmation Page
                //  performSearch();
                View focusView = null;
                boolean isValid=true;
                keyword = hfrId.getEditableText().toString();
                url = webUrl.getEditableText().toString();
                  setSessionData(keyword,url);
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

                if (Util.isValidIPAddress(url)) {
                   /* if (isIPAddressAvailable(url)) {
                        //System.out.println("IP address is valid and available.");
                        webUrl.setError("IP address is valid and available.");
                        isValid=true;
                      //  focusView=webUrl;
                    } else {
                      //  System.out.println("IP address is valid but not available.");
                        webUrl.setError("IP address is valid but not available.");
                        isValid=false;
                        focusView=webUrl;
                    }*/
                } else {
                  //  System.out.println("Invalid IP address format.");
                    webUrl.setError("Invalid IP address format.");
                    isValid=false;
                    focusView=webUrl;
                }
                if (isValid) {
                    Intent intentSave = new Intent( FacilitySettingActivity.this, FacilityConfirmationPageActivity.class );
                    intentSave.putExtra( "hfrId", keyword );
                    intentSave.putExtra( "url", url );
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
    public void setSessionData(String url,String hfrId){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("facilityLoginSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("webUrl", url);
        editor.putString( "hfrId",hfrId);
        editor.apply();


    }
    public boolean isIPAddressAvailable(String ipAddress) {
        boolean isAvailable = false;
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            isAvailable = inetAddress.isReachable(3000); // Timeout in milliseconds
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isAvailable;
    }

}