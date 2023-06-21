package com.ucc.ctc.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.ucc.ctc.R;
import com.ucc.ctc.models.entity.AdminHierarchyDivisionEntity;
import com.ucc.ctc.models.entity.AdminHierarchyEntity;
import com.ucc.ctc.models.entity.ClientPhysicalAddressEntity;
import com.ucc.ctc.views.FingerClientActivity;
import com.ucc.ctc.viewsModel.AdminHierarchyDivisionViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyViewModel;

import java.util.ArrayList;
import java.util.List;

public class UpdateClientPhysicalAddressDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {

    private EditText council;
    private EditText division;
    private EditText ward;
    private EditText village;
    private EditText villageChairperson;
    private EditText cellLeader;
    private EditText householdHead;
    private EditText clientPhone,clientcontact,householdcontact;
    private RadioGroup sms_radio_group,radioGroupfinger;
    private Button cancel,mSaveBtn;
    private ImageButton bt_close;
    private UpdateClientAddressListener mListener;
    private String selectedConsent;
    private String selectedRadioButtonTextFinger;
    private AdminHierarchyViewModel councilViewModel;
    private AdminHierarchyDivisionViewModel divisionViewModel;
    private  AdminHierarchyViewModel wardViewModel;
    private  AdminHierarchyViewModel villageViewModel;
    private  String councilName,divisionName,wardName,villageName;
    private SearchableSpinner council_spinner,division_spinner,ward_spinner,village_spinner;
   // private UpdateClientListener mListener;
    private ClientPhysicalAddressEntity clientAddress;
    String sex;
    public void setClient(ClientPhysicalAddressEntity client) {
          clientAddress = client;
        Log.v( "Update Zone Data :","In set client Data "+clientAddress.getVillageChairperson() );
       // return ;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate( R.layout.activity_client_physica_address,null);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("ClientFingerSession", Context.MODE_PRIVATE);
        String clientName = sharedPreferences.getString("fingerClientName", "");
        String clientId = sharedPreferences.getString("fingerClientId", "");

        builder.setView(view);
        builder.setCancelable(true);
        // builder.setTitle("Add New Client");
        //builder.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        council_spinner = view.findViewById(R.id.councilId);
        division_spinner = view.findViewById(R.id.divisionId);
        ward_spinner = view.findViewById(R.id.wardId);
        village_spinner = view.findViewById(R.id.villageId);
        //villageChairpersonId
        villageChairperson=view.findViewById(R.id.villageChairpersonId);
        //cellLeaderId
        cellLeader=view.findViewById( R.id.cellLeaderId);
        //householdHeadId
        householdHead=view.findViewById(R.id.householdHeadId);
        //household contactId
        householdcontact=view.findViewById( R.id.householdcontactId);
        //household contactId
        clientcontact=view.findViewById( R.id.clientcontactphoneId);
        //contact Phone
        // clientcontact=view.findViewById( R.id.);
        //SMS consent
        //villageChairpersonId
        villageChairperson.setText( clientAddress.getVillageChairperson() );
        //cellLeaderId
        cellLeader.setText( clientAddress.getCellLeader() );
        //householdHeadId
        householdHead.setText( clientAddress.getHouseholdHead() );
        //household contactId
        householdcontact.setText( clientAddress.getHouseholdcontact() );
        //household contactId
        clientcontact.setText( clientAddress.getClientPhone() );
        //contact Phone
        // clientcontact=view.findViewById( R.id.);
        //SMS consent
            if(clientAddress.getSMSConsent().equals("Yes")) {
                RadioButton radio_button_yes = view.findViewById( R.id.radio_button_yes );
                radio_button_yes.setChecked( true );
            }else if(clientAddress.getSMSConsent().equals("No")) {
                RadioButton radio_button_no = view.findViewById( R.id.radio_button_no );
                radio_button_no.setChecked( true );
            }
        RadioGroup radioGroup = view.findViewById(R.id.sms_radio_group);
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
        //Start Council
        councilViewModel = new ViewModelProvider(this).get( AdminHierarchyViewModel.class);
        // Observe the LiveData object and update the adapter whenever the data changes
        councilViewModel.getAllCouncil().observe(this, new Observer<List<AdminHierarchyEntity>>() {
            @Override
            public void onChanged(List<AdminHierarchyEntity> adminHierarchy) {
                // You can do this by setting the items to an ArrayAdapter and setting it to the Spinner
                //ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, getItemNames(adminHierarchy));
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, getItemNames(adminHierarchy));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // on below line we are getting the position of the item by the item name in our adapter.
                int spinnerPosition  = adapter.getPosition(clientAddress.getCouncil());
                   // on below line we are setting selection for our spinner to spinner position.
                council_spinner.setAdapter(adapter);

                if (spinnerPosition >= 0 && spinnerPosition < adapter.getCount()) {
                    council_spinner.setSelection(spinnerPosition);
                   /// searchableSpinner.setSelection(selectedIndex);
                } else {
                    Log.v( "position","position "+spinnerPosition+" "+clientAddress.getCouncil() );
                }

                Log.v( "position","position "+spinnerPosition+" "+clientAddress.getCouncil() );
            }
        });
        //End Council
        //Start find Division
        council_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected council from the parent dropdown
                // Observe the LiveData object and update the adapter whenever the data changes
                // On selecting a spinner item
                // String item = parent.getItemAtPosition(position).toString();
                councilName = parent.getItemAtPosition( position ).toString();
                int divisionId=5;
                //Start Council
                divisionViewModel = new ViewModelProvider(UpdateClientPhysicalAddressDialog.this).get( AdminHierarchyDivisionViewModel.class);
                divisionViewModel.getDivisionById(divisionId);
                divisionViewModel.getDivisionLiveDataLiveData().observe(UpdateClientPhysicalAddressDialog.this, new Observer<List<AdminHierarchyDivisionEntity>>() {
                    @Override
                    public void onChanged(List<AdminHierarchyDivisionEntity> adminHierarchy) {
                        // You can do this by setting the items to an ArrayAdapter and setting it to the Spinner
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item, getItemDivision(adminHierarchy));
                        division_spinner.setTitle("Select Division");
                        division_spinner.setAdapter(adapter);
                    }
                });
                //End Division
                // Observe the LiveData object and update the adapter whenever the data changes
                // int divisionId=5;
                //Start find Division
                division_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        divisionName = parent.getItemAtPosition( position ).toString();
                        wardViewModel = new ViewModelProvider( UpdateClientPhysicalAddressDialog.this ).get( AdminHierarchyViewModel.class );
                        wardViewModel.getWardById( "%" + divisionName + "%" ).observe( UpdateClientPhysicalAddressDialog.this, new Observer<List<AdminHierarchyEntity>>() {
                            @Override
                            public void onChanged(List<AdminHierarchyEntity> adminHierarchy) {
                                // You can do this by setting the items to an ArrayAdapter and setting it to the Spinner
                                ArrayAdapter<String> adapter = new ArrayAdapter<>( getContext(), android.R.layout.simple_spinner_dropdown_item, getItemWard( adminHierarchy ) );
                                ward_spinner.setTitle( "Select Ward" );
                                ward_spinner.setAdapter( adapter );
                            }
                        } );
                        //End Division
                        //Ward Data
                        ward_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                wardName = parent.getItemAtPosition( position ).toString();
                                wardViewModel = new ViewModelProvider( UpdateClientPhysicalAddressDialog.this ).get( AdminHierarchyViewModel.class );
                                wardViewModel.getVillageById("%" + wardName + "%" ).observe( UpdateClientPhysicalAddressDialog.this, new Observer<List<AdminHierarchyEntity>>() {
                                    @Override
                                    public void onChanged(List<AdminHierarchyEntity> adminHierarchy) {
                                        // You can do this by setting the items to an ArrayAdapter and setting it to the Spinner
                                        ArrayAdapter<String> adapter = new ArrayAdapter<>( getContext(), android.R.layout.simple_spinner_dropdown_item, getItemVillage( adminHierarchy ) );
                                        village_spinner.setTitle( "Select Village" );
                                        village_spinner.setAdapter( adapter );
                                    }
                                } );
                                //End Division
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                // Do nothing
                            }
                        });
                        //End Ward
                        //Village Data
                        ward_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                villageName = parent.getItemAtPosition( position ).toString();
                                //End Division
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                // Do nothing
                            }
                        });
                        //End Village
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Do nothing
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        mSaveBtn = view.findViewById(R.id.cpd_save);
       // cancel=view.findViewById( R.id.cpd_cancel);
        bt_close=view.findViewById( R.id.cpd_close );
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String councilName = council.getText().toString();
                //  String divisionName = division.getText().toString();
                ///String wardName = ward.getText().toString();
                // String villageName=village.getText().toString();
                String consentSMS=selectedConsent;
                String villageChairpersonName=villageChairperson.getText().toString();
                String cellLeaderName=cellLeader.getText().toString();
                String householdHeadName=householdHead.getText().toString();
                // String clientPhoneNumber=clientPhone.getText().toString();
                String clientContactPhone=clientcontact.getText().toString();
                String clientPhoneNumber=clientContactPhone;
                String householdContactPhone=householdcontact.getText().toString();
                //Update facility data info
                // String cfacilityId=facilityId;
                // clientPhoneNumber=clientContactPhone;
                //End update
                View focusView = null;
                boolean isValid=true;

                if (clientPhoneNumber.isEmpty()) {
                    clientPhone.setError("This field is required");
                    isValid=false;
                    focusView=clientPhone;
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
                    //SAVE DATA TO CLIENT ADDRESS
                    ClientPhysicalAddressEntity clientPhysicalAddress = new ClientPhysicalAddressEntity(clientId,councilName,
                            divisionName,wardName,villageName,villageChairpersonName,
                            cellLeaderName,householdHeadName,clientPhoneNumber,clientContactPhone,householdContactPhone,consentSMS);
                    clientPhysicalAddress.setId(clientAddress.getId());
                    mListener.updateNewClientAddress(clientPhysicalAddress);
                    dismiss();

                }
            }
        });
       /* cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        } );*/
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
        mListener = (UpdateClientPhysicalAddressDialog.UpdateClientAddressListener)context;
    }



    public interface UpdateClientAddressListener{
        void updateNewClientAddress(ClientPhysicalAddressEntity clientPhysicalAddressEntity);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private List<String> getItemNames(List<AdminHierarchyEntity> items) {
        List<String> itemNames = new ArrayList<>();
        for (AdminHierarchyEntity item : items) {
            if (items != null) {
                itemNames.add( item.getCouncil() );
                Log.v( "Welcome", "Message IN : " + item.getCouncil());
            }
        }
        return itemNames;
    }
    private List<String> getItemDivision(List<AdminHierarchyDivisionEntity> items) {
        List<String> itemNames = new ArrayList<>();
        for (AdminHierarchyDivisionEntity item : items) {
            if (items != null) {
                itemNames.add( item.getWardName() );
                Log.v( "Welcome", "Message IN : " + item.getWardName());
            }
        }
        return itemNames;
    }
    private List<String> getItemWard(List<AdminHierarchyEntity> items) {
        List<String> itemNames = new ArrayList<>();
        for (AdminHierarchyEntity item : items) {
            if (items != null) {
                itemNames.add( item.getWard() );
                Log.v( "Welcome", "Message IN : " + item.getWard());
            }
        }
        return itemNames;
    }
    private List<String> getItemVillage(List<AdminHierarchyEntity> items) {
        List<String> itemNames = new ArrayList<>();
        for (AdminHierarchyEntity item : items) {
            if (items != null) {
                itemNames.add( item.getWard() );
                Log.v( "Welcome", "Message IN : " + item.getVillageMtaa());
            }
        }
        return itemNames;
    }
    private int getPosition(List<AdminHierarchyEntity> items,String selectedValue) {
        int i=0;
        int selectedIndex = -1;
        for (AdminHierarchyEntity item : items) {
            if (items != null) {
                if (item.getCouncil().equals(selectedValue)) {
                    selectedIndex = i;
                    break;
                }
                i=+1;
            }
        }
        return selectedIndex;
    }
}