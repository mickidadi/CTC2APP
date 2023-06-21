package com.ucc.ctc.views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.ClientTreatmentSupporterEntity;
import com.ucc.ctc.viewsModel.ClientPhysicalAddressViewModel;
import com.ucc.ctc.viewsModel.ClientTreatmentSupporterViewModel;

import java.util.Calendar;

public class ClientTreatmentSupporterActivity extends AppCompatActivity {
     private EditText treatmentSupporterName;
     private EditText treatmentSupporterPhone;
     private EditText clientCommunityGroup;
     private TextView dateJoined;
     private Button cancel,mSaveBtn;
     private ImageButton bt_close;
     private String selectedConsent;
     ClientTreatmentSupporterViewModel clientTreatmentSupporterViewModel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_treatment_supporter);
        clientTreatmentSupporterViewModel = new ViewModelProvider(this).get( ClientTreatmentSupporterViewModel.class);

        SharedPreferences sharedPreferences = this.getSharedPreferences("ClientFingerSession", Context.MODE_PRIVATE);
        String clientName = sharedPreferences.getString("fingerClientName", "");
        String clientId = sharedPreferences.getString("fingerClientId", "");

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        treatmentSupporterName = findViewById(R.id.treatmentSupporterName);
        treatmentSupporterPhone = findViewById(R.id.treatmentSupporterPhone);
        clientCommunityGroup = findViewById(R.id.clientCommunityGroup);
        dateJoined = findViewById(R.id.dateJoined);
        dateJoined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and show it
                DatePickerDialog datePickerDialog = new DatePickerDialog(ClientTreatmentSupporterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the text field with the selected date
                        dateJoined.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        //villageChairpersonId

        //SMS consent
        RadioGroup radioGroup = findViewById(R.id.sms_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    RadioButton selectedRadioButton = group.findViewById(checkedId);
                    selectedConsent = selectedRadioButton.getText().toString();
                    // Use the selectedRadioButtonText value as needed
                } else {
                    // No RadioButton is selected
                    selectedConsent="No";
                }
            }
        });
        ///radioGroup=(RadioGroup)view.findViewById(R.id.radioGroup);
        mSaveBtn = findViewById(R.id.cts_save);
      //  cancel=findViewById( R.id.cts_cancel);
        bt_close=findViewById( R.id.cts_close );

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String supporterName = treatmentSupporterName.getText().toString();
                String phone = treatmentSupporterPhone.getText().toString();
                String groupJoined = clientCommunityGroup.getText().toString();
                String dateJoin=dateJoined.getText().toString();
                String consentSMS=selectedConsent;

                //Update facility data info
                // String cfacilityId=facilityId;
                //End update
                View focusView = null;
                boolean isValid=true;

                if (supporterName.isEmpty()) {
                    treatmentSupporterName.setError("This field is required");
                    isValid=false;
                    focusView=treatmentSupporterName;
                }
                if ((radioGroup.getCheckedRadioButtonId() == -1)) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(0);
                    radioButton.setError("Please select an option");
                    //radioButton.requestFocus();
                    focusView=radioButton;
                }
                if (!isValid) {
                    focusView.requestFocus();
                }
                else {
                    //SAVE DATA TO CLIENT TREATMENT SUPPORTER
                    ClientTreatmentSupporterEntity clientTreatmentSupporter = new ClientTreatmentSupporterEntity(clientId,supporterName,phone,
                            groupJoined,dateJoin,consentSMS);
                    clientTreatmentSupporterViewModel.insert(clientTreatmentSupporter);
                   // mListener.saveNewClientTreatmentSupporter(clientTreatmentSupporter);
                      finish();

                }
            }
        });

        bt_close.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

    }

}