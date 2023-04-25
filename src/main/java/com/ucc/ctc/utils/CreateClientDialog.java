package com.ucc.ctc.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.views.FingerClientActivity;
import java.util.Calendar;

public class CreateClientDialog extends AppCompatDialogFragment {

    private EditText firstName;
    private EditText middleName;
    private EditText lastName;
    private EditText clientId;
    private RadioGroup sex_radio_group,radioGroupfinger;
    private EditText dateOfBirth;
    private Button cancel,mSaveBtn;
    private ImageButton bt_close;
    private CreateClientListener mListener;
    private String selectedRadioButtonTextSex;
    private String selectedRadioButtonTextFinger;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate( R.layout.activity_client_registration,null);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("facilitySession", Context.MODE_PRIVATE);
        String facilityId = sharedPreferences.getString("facilityIdsession", "");

        builder.setView(view);
        builder.setCancelable(true);
       // builder.setTitle("Add New Client");
        //builder.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        firstName = view.findViewById(R.id.firstName);
        middleName = view.findViewById(R.id.middleName);
        lastName = view.findViewById(R.id.lastName);
        sex_radio_group=(RadioGroup)view.findViewById( R.id.sex_radio_group);
        RadioGroup radioGroup = view.findViewById(R.id.sex_radio_group);
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
        clientId=view.findViewById(R.id.clientId);
        dateOfBirth=view.findViewById( R.id.dateOfBirth);
        //finger Print
        //fingerprint=(RadioGroup)view.findViewById( R.id.fingerprint_radio_group);
        radioGroupfinger = view.findViewById(R.id.fingerprint_radio_group);
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
        EditText dateInput = view.findViewById(R.id.dateOfBirth);
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new instance of DatePickerDialog and show it
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the text field with the selected date
                        dateInput.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
        ///radioGroup=(RadioGroup)view.findViewById(R.id.radioGroup);
        mSaveBtn = view.findViewById(R.id.btn_save);
        cancel=view.findViewById( R.id.cancel_client_save);
        bt_close=view.findViewById( R.id.bt_close );
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
                    mListener.saveNewClient(client);
                          String fingerCaptured=selectedRadioButtonTextFinger.trim().toString();
                       if(fingerCaptured.equalsIgnoreCase("Yes")){
                          //Send to Finger Print Capture
                           Toast.makeText(getActivity()," Test "+fingerCaptured, Toast.LENGTH_LONG).show();
                           String fullname=fname+" "+mname+" "+lname;
                           getFingerPrint(clientIds,fullname);
                       //end
                       }else {
                           dismiss();
                       }
                }
            }
        });
        cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        } );
        bt_close.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        } );
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CreateClientListener)context;
    }
 public interface CreateClientListener{
        void saveNewClient(ClientEntity client);
    }
    public void getFingerPrint(String clientIds,String name){
      //  startActivity(new Intent( getActivity(), ClientFingerPrintActivity.class));
        setSessionData(name,clientIds);
        Intent fingerActivity = new Intent( getActivity(), FingerClientActivity.class);
        startActivity(fingerActivity);
    }
    public void setSessionData(String ClientName,String ClientId){
        //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ClientFingerSession", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("ClientFingerSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fingerClientName", ClientName);
        editor.putString( "fingerClientId",ClientId);
        editor.apply();
    }
}