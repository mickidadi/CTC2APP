package com.ucc.ctc.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.ClientEntity;

public class ClientProfile extends AppCompatDialogFragment {

    private EditText firstName;
    private EditText middleName;
    private EditText lastName;
    private EditText clientId;
    private EditText sex;
    private EditText dateOfBirth;
    private EditText referenceCode;
    private EditText facilityId;

    private Button mSaveBtn;
    private UpdateClientDialog.UpdateClientListener mListener;
    private ClientEntity client;
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
        builder.setTitle("Client Profile");
         //##############################
        //# Start Binding data to profile
        //##############################
        firstName = view.findViewById(R.id.firstName);
        middleName = view.findViewById(R.id.middleName);
        lastName = view.findViewById(R.id.lastName);
        clientId=view.findViewById(R.id.clientId);
        dateOfBirth=view.findViewById( R.id.dateOfBirth );
        //referenceCode=view.findViewById( R.id.referenceCode );
        //facilityId=view.findViewById( R.id.facilityId);
        firstName.setText(client.getFirstName());
        middleName.setText(client.getFirstName());
        lastName.setText(client.getLastName());
        // sex.setText( client.getSex());
        clientId.setText( client.getClientId() );
        dateOfBirth.setText( client.getDateOfBirth());
        referenceCode.setText( client.getReferenceCode() );
        facilityId.setText( client.getFacilityId() );
        //##############################
        //# End Binding data to profile
        //##############################

        mSaveBtn = view.findViewById(R.id.btn_save);

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
                String creferenceCode=referenceCode.getText().toString();
                String cfacilityId=facilityId.getText().toString();
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
        mListener = (UpdateClientDialog.UpdateClientListener)context;
    }

    public interface UpdateClientListener{
        void updateNewClient(ClientEntity client);
    }
}
