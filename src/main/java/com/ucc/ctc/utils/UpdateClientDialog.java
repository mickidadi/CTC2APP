package com.ucc.ctc.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.ClientEntity;

public class UpdateClientDialog extends AppCompatDialogFragment {

    private EditText firstName;
    private EditText middleName;
    private EditText lastName;
    private EditText clientId;
    RadioGroup sex_radio_group;
    RadioButton selectedSex;
    private EditText dateOfBirth;
    private Button mSaveBtn;
    private UpdateClientListener mListener;
    private ClientEntity client;
    String sex;
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate( R.layout.activity_client_registration,null);

        builder.setView(view);
        builder.setCancelable(true);
        builder.setTitle("Update Client");

        firstName = view.findViewById(R.id.firstName);
        middleName = view.findViewById(R.id.middleName);
        lastName = view.findViewById(R.id.lastName);
        sex_radio_group=view.findViewById( R.id.sex_radio_group);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("facilitySession", Context.MODE_PRIVATE);
        String facilityId = sharedPreferences.getString("facilityIdsession", "");
        //getCheckedRadioButtonId()
        int selectedRadioButtonId = sex_radio_group.getCheckedRadioButtonId();

        // Toast.makeText(MainActivity.this,radioSexButton.getText(),Toast.LENGTH_SHORT).show();
        if (selectedRadioButtonId != -1) {
            // RadioButton radioSexButton = (RadioButton) view.findViewById( selectedRadioButtonId );
            selectedSex= (RadioButton) view.findViewById(selectedRadioButtonId);
            //textView.setText(selectedRbText + " is Selected");
            sex=selectedSex.getText().toString();

        } else {
            sex="UN";
            Log.e( "miki", "Received an exception "+selectedRadioButtonId);
        }
        clientId=view.findViewById(R.id.clientId);
        dateOfBirth=view.findViewById( R.id.dateOfBirth );
        mSaveBtn = view.findViewById(R.id.btn_save);
        firstName.setText(client.getFirstName());
        middleName.setText(client.getFirstName());
        lastName.setText(client.getLastName());
       // sex.setText( client.getSex());
        clientId.setText( client.getClientId() );
        dateOfBirth.setText( client.getDateOfBirth());

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstName.getText().toString();
                String mname = middleName.getText().toString();
                String lname = lastName.getText().toString();
                String clientIds=clientId.getText().toString();
              //  String csex=sex.getText().toString();
                String csex="Male";
                String cdateOfBirth=dateOfBirth.getText().toString();
                String creferenceCode="";
                String cfacilityId=facilityId;
                if(fname.isEmpty()||lname.isEmpty()||clientIds.isEmpty()) {
                    return;
                }
                else {
                    //Client(String clientId, String firstName, String middleName, String lastName, String sex,
                    // Date dateOfBirth, String referenceCode, String facilityId)
                    ClientEntity currentClient = new ClientEntity(clientIds,fname,mname,lname,csex,cdateOfBirth,creferenceCode,cfacilityId,"");
                    currentClient.setId(client.getId());
                    mListener.updateNewClient(currentClient);
                    dismiss();
                }
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (UpdateClientListener)context;
    }

    public interface UpdateClientListener{
        void updateNewClient(ClientEntity client);
    }
}
