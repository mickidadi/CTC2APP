package com.ucc.ctc.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.utils.CreateClientDialog;
import com.ucc.ctc.viewsModel.ClientViewModel;
import com.ucc.ctc.viewsModel.FacilitySearchViewModel;

import java.util.Calendar;
import java.text.DateFormat;

public class ClientCreateActivity extends AppCompatActivity {
    private EditText firstName;
    private EditText middleName;
    private EditText lastName;
    private EditText clientId;
    private RadioGroup sex_radio_group,radioGroupfinger;
    private EditText dateOfBirth;
    private Button cancel,mSaveBtn;
    private ImageButton bt_close;
    private CreateClientDialog.CreateClientListener mListener;
    private String selectedRadioButtonTextSex;
    private String selectedRadioButtonTextFinger;
    private DatePickerDialog.OnDateSetListener datePickerListener;
    private ClientViewModel clientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_client_registration);
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("facilitySession", Context.MODE_PRIVATE);
        String facilityId = sharedPreferences.getString("facilityIdsession", "");


        firstName = findViewById(R.id.firstName);
        middleName = findViewById(R.id.middleName);
        lastName = findViewById(R.id.lastName);
        sex_radio_group=(RadioGroup)findViewById( R.id.sex_radio_group);
        RadioGroup radioGroup = findViewById(R.id.sex_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    RadioButton selectedRadioButton = group.findViewById(checkedId);
                    selectedRadioButtonTextSex = selectedRadioButton.getText().toString();
                    // Use the selectedRadioButtonText value as needed
                } else {
                    // No RadioButton is selected
                    selectedRadioButtonTextSex="UN";
                }
            }
        });
        // radioGroup =view.findViewById(R.id.radioGroup);
        clientId=findViewById(R.id.clientId);
       // dateOfBirth=findViewById( R.id.dateOfBirth);
        //finger Print
        //fingerprint=(RadioGroup)view.findViewById( R.id.fingerprint_radio_group);
        radioGroupfinger = findViewById(R.id.fingerprint_radio_group);
        radioGroupfinger.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    RadioButton selectedRadioButtonfinger = group.findViewById(checkedId);
                    selectedRadioButtonTextFinger = selectedRadioButtonfinger.getText().toString();
                    // Use the selectedRadioButtonText value as needed
                } else {
                    // No RadioButton is selected
                    selectedRadioButtonTextFinger="UN";
                }
            }
        });
        //End finger Print
        TextView dateInput = findViewById(R.id.dateOfBirth);
        /*dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and show it
                DatePickerDialog datePickerDialog = new DatePickerDialog(ClientCreateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the text field with the selected date
                        dateInput.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });*/
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Handle the selected date
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, monthOfYear);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ClientCreateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the text field with the selected date
                        dateInput.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, monthOfYear, dayOfMonth);
                DateFormat dateFormat = DateFormat.getDateInstance();
                String selectedDateStr = dateFormat.format(selectedDate.getTime());

                dateInput.setText(selectedDateStr);
            }
        };
        ///radioGroup=(RadioGroup)view.findViewById(R.id.radioGroup);
        mSaveBtn = findViewById(R.id.btn_save);
       // cancel=findViewById( R.id.cancel_client_save);
       // bt_close=findViewById( R.id.bt_close );
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstName.getText().toString();
                String mname = middleName.getText().toString();
                String lname = lastName.getText().toString();
                String clientIds=clientId.getText().toString();
                String csex=selectedRadioButtonTextSex;
                String cdateOfBirth=dateOfBirth.getText().toString();
                String creferenceCode="";
                //Update facility data info
                String cfacilityId=facilityId;
                //End update
                View focusView = null;
                boolean isValid=true;

                if (fname.isEmpty()) {
                    firstName.setError("This field is required");
                    isValid=false;
                    focusView=firstName;
                }
                if (lname.isEmpty()) {
                    lastName.setError("This field is required");
                    isValid=false;
                    focusView=lastName;
                }

                if ((radioGroup.getCheckedRadioButtonId() == -1)) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(0);
                    radioButton.setError("Please select an option");
                    //radioButton.requestFocus();
                    focusView=radioButton;
                }
                if ((radioGroupfinger.getCheckedRadioButtonId() == -1)) {
                    RadioButton radioButtonFinger = (RadioButton) radioGroupfinger.getChildAt(0);
                    radioButtonFinger.setError("Please select an option");
                    //radioButton.requestFocus();
                    focusView=radioButtonFinger;
                }
                if (!isValid) {
                    focusView.requestFocus();
                }
                else {
                    //Client(String clientId, String firstName, String middleName, String lastName, String sex,
                    // Date dateOfBirth, String referenceCode, String facilityId)
                    ClientEntity client = new ClientEntity(clientIds,fname,mname,lname,csex,cdateOfBirth,creferenceCode,cfacilityId,selectedRadioButtonTextFinger);
                     clientViewModel.insert(client);
                    String fingerCaptured=selectedRadioButtonTextFinger.trim().toString();
                    if(fingerCaptured.equalsIgnoreCase("Yes")){
                        //Send to Finger Print Capture
                       // Toast.makeText(ClientCreateActivity.this," Test "+fingerCaptured, Toast.LENGTH_LONG).show();
                        String fullname=fname+" "+mname+" "+lname;
                        getFingerPrint(clientIds,fullname);
                        //end
                    }
                    finish();
                }
            }
        });
        // Observe the insert result LiveData to perform any necessary actions after insertion
       /* clientViewModel.getInsertResult().observe(this, insertResult -> {
            if (insertResult) {
                // Data inserted successfully, perform any necessary actions
                Toast.makeText(this, "Product inserted successfully", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity, for example
            } else {
                // Insertion failed, show an error message or handle the failure
                Toast.makeText(this, "Failed to insert product", Toast.LENGTH_SHORT).show();
            }
        });*/


    }
    public void getFingerPrint(String clientIds,String name){
        //  startActivity(new Intent( getActivity(), ClientFingerPrintActivity.class));
        setSessionData(name,clientIds);
        Intent fingerActivity = new Intent( ClientCreateActivity.this, FingerClientActivity.class);
        startActivity(fingerActivity);
    }
    public void setSessionData(String ClientName,String ClientId){
        //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ClientFingerSession", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ClientFingerSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fingerClientName", ClientName);
        editor.putString( "fingerClientId",ClientId);
        editor.apply();
    }
    private void showDatePickerDialog() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog with year selection
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                ClientCreateActivity.this,
                datePickerListener,
                year,
                month,
                day
        );

        // Set the date picker to show the year selection
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);

        // Show the dialog
        datePickerDialog.show();
    }
}