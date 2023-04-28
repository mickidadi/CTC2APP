package com.ucc.ctc.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.ucc.ctc.R;
import com.ucc.ctc.adapters.ClientRecyclerViewAdapter;
import com.ucc.ctc.models.FacilitySearchResponse;
import com.ucc.ctc.models.UserProfile;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.models.entity.ClientPhysicalAddressEntity;
import com.ucc.ctc.models.entity.ClientTreatmentSupporterEntity;
import com.ucc.ctc.models.entity.UserEntity;
import com.ucc.ctc.utils.ClientPhysicAddressDialog;
import com.ucc.ctc.utils.ClientTreatmentSupporterDialog;
import com.ucc.ctc.utils.CreateClientDialog;
import com.ucc.ctc.utils.Tools;
import com.ucc.ctc.utils.UpdateClientDialog;
import com.ucc.ctc.utils.ViewAnimation;
import com.ucc.ctc.viewsModel.ClientPhysicalAddressViewModel;
import com.ucc.ctc.viewsModel.ClientTreatmentSupporterViewModel;
import com.ucc.ctc.viewsModel.ClientViewModel;
import com.ucc.ctc.viewsModel.FacilitySearchViewModel;
import com.ucc.ctc.viewsModel.UserRemoteViewModel;

public class ClientProfileActivity extends AppCompatActivity implements  ClientPhysicAddressDialog.CreateClientAddressListener,
        ClientPhysicAddressDialog.UpdateClientAddressListener, ClientTreatmentSupporterDialog.CreateClientTreatmentSupporterListener {
    ImageView capture_finger_print,addClientAddress;
    private  MaterialButton action_button_treatment_supporter;
    private NestedScrollView nested_scroll_view;
    private ImageButton bt_toggle_client_address, bt_toggle_treatment_supporter,bt_toggle_community_group;
    private View lyt_expand_client_address, lyt_expand_treatment_supporter,lyt_expand_community_group,parent;
    private ClientPhysicalAddressViewModel clientPhysicalAddressViewModel;
    private ClientTreatmentSupporterViewModel clientTreatmentSupporterViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_client_profile);
        TextView profile_client_fullName =findViewById( R.id.profile_client_fullName);
        TextView gender =findViewById( R.id.profile_client_gender );
        //TextView profile_clientId =findViewById( R.id.profile_client_id );
        //intToolbar();
       // initToolbar();
        initComponent();
        Intent intent = getIntent();
        String fullName = intent.getStringExtra("fullName");
        String sex = intent.getStringExtra("sex");
        String clientId = intent.getStringExtra("clientId");
        profile_client_fullName.setText( fullName );
        gender.setText(sex);
        //profile_clientId.setText(clientId );
        clientTreatmentSupporterViewModel = ViewModelProviders.of(this).get(ClientTreatmentSupporterViewModel.class);
         //End
       // clientTreatmentSupporterViewModel.init( keyword );
        clientTreatmentSupporterViewModel.getClientTreatmentSupporterSearch(clientId).observe( this, new Observer<ClientTreatmentSupporterEntity>() {
            @Override
            public void onChanged(ClientTreatmentSupporterEntity supporterEntity) {
                if (supporterEntity != null) {
                    // Display client Treatment Supporter
                    TextView treatmentSupporterView =findViewById( R.id.treatmentSupporterView);
                    TextView treatmentSupporterPhoneView =findViewById( R.id.treatmentSupporterPhoneView );
                    TextView SMSConsentView =findViewById( R.id.SMSConsentView );
                    TextView clientCommunityGroupView =findViewById( R.id.clientCommunityGroupView );
                    TextView dateJoinedView =findViewById( R.id.dateJoinedView );
                    treatmentSupporterView.setText( supporterEntity.getTreatmentSupporterName() );
                    treatmentSupporterPhoneView.setText( supporterEntity.getTreatmentSupporterPhone() );
                    SMSConsentView.setText( supporterEntity.getSMSConsent() );
                    clientCommunityGroupView.setText( supporterEntity.getClientCommunityGroup() );
                    dateJoinedView.setText( supporterEntity.getDateJoined() );
                }
            }
        } );
        //END OF CLIENT TREATMENT SUPPORTER
        //START CLIENT TREATMENT ADDRESS
        clientPhysicalAddressViewModel = ViewModelProviders.of(this).get(ClientPhysicalAddressViewModel.class);
        //End
        // clientTreatmentSupporterViewModel.init( keyword );
        clientPhysicalAddressViewModel.getClientPhysicalAddressSearch(clientId).observe( this, new Observer<ClientPhysicalAddressEntity>() {
            @Override
            public void onChanged(ClientPhysicalAddressEntity supporterAddressEntity) {
                if (supporterAddressEntity != null) {
                    // Display client Physical Address
                    TextView councilView =findViewById( R.id.councilView);
                    TextView divisionView =findViewById( R.id.divisionView );
                    TextView wardView =findViewById( R.id.wardView );
                    TextView villageView =findViewById( R.id.villageView );
                    TextView villageChairpersonView =findViewById( R.id.villageChairpersonView );
                    TextView cellLeaderView=findViewById( R.id.cellLeaderView );
                    TextView householdHeadView=findViewById( R.id.householdHeadView );
                    TextView contacthouseholdHeadView=findViewById( R.id.contacthouseholdHeadView );
                    TextView contactClientPhoneView=findViewById( R.id.contactClientPhoneView );
                    TextView SMSConsentView=findViewById( R.id.SMSConsentView );

                    councilView.setText( supporterAddressEntity.getCouncil() );
                    divisionView.setText( supporterAddressEntity.getDivision());
                    wardView.setText( supporterAddressEntity.getWard() );
                    villageView.setText( supporterAddressEntity.getVillage() );
                    villageChairpersonView.setText( supporterAddressEntity.getVillageChairperson() );
                    cellLeaderView.setText( supporterAddressEntity.getCellLeader() );
                    householdHeadView.setText( supporterAddressEntity.getHouseholdHead() );
                    contacthouseholdHeadView.setText( supporterAddressEntity.getHouseholdcontact() );
                    contactClientPhoneView.setText( supporterAddressEntity.getClientPhone() );
                    SMSConsentView.setText( supporterAddressEntity.getSMSConsent() );
                }
            }
        } );
        //END TREATMENT
        capture_finger_print=findViewById( R.id.capture_finger_print );
          //CAPTURE FINGER PRINT
        capture_finger_print.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSessionData(fullName,clientId);
                Intent fingerActivity = new Intent( ClientProfileActivity.this, FingerClientActivity.class);
                startActivity(fingerActivity);
            }
        } );
        //END
        //End
        addClientAddress=findViewById( R.id.addClientAddress );
        addClientAddress.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSessionData(fullName,clientId);
                openClientAddressDialog();
            }
        } );
        action_button_treatment_supporter=findViewById( R.id.action_button_treatment_supporter );
        action_button_treatment_supporter.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSessionData(fullName,clientId);
                openClientTreatmentSupportDialog();
            }
        } );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_client_profile,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_allClient:
              //  clientViewModel.deleteAllClients();
            case R.id.ctc_enrollment:
                startActivity(new Intent(ClientProfileActivity.this, CTCEnrollmentActivity.class));
              //  snackBar("All Client Deleted");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
     private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_layouts);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Client Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.colorPrimaryLight);
    }
    public void setSessionData(String ClientName,String ClientId){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ClientFingerSession", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPreferences.edit();
          editor.putString("fingerClientName", ClientName);
          editor.putString( "fingerClientId",ClientId);
          editor.apply();


    }
    private void initComponent() {
        // Client Address item_section
        parent = findViewById(android.R.id.content);
        bt_toggle_client_address = (ImageButton) findViewById(R.id.bt_toggle_client_address);
        lyt_expand_client_address = findViewById(R.id.lyt_expand_client_address);
        bt_toggle_client_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionAddress(bt_toggle_client_address);
            }
        });
       // Treatment Supporter Section
        bt_toggle_treatment_supporter = (ImageButton) findViewById(R.id.bt_toggle_treatment_supporter);
        lyt_expand_treatment_supporter = (View)findViewById(R.id.lyt_expand_treatment_supporter);

        bt_toggle_treatment_supporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSectionSupporter(bt_toggle_treatment_supporter);
            }
        });
        // nested scrollview
        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
    }

    private void toggleSectionAddress(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_client_address, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_client_address);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_client_address);
        }
    }

    private void toggleSectionSupporter(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_treatment_supporter, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_treatment_supporter);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_treatment_supporter);
        }
    }
    private void toggleSectionCommunityGroup(View view) {
        boolean show = toggleArrow(view);
        if (show) {
            ViewAnimation.expand(lyt_expand_community_group, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt_expand_community_group);
                }
            });
        } else {
            ViewAnimation.collapse(lyt_expand_community_group);
        }
    }
    public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }
    private void openClientAddressDialog() {
        ClientPhysicAddressDialog createClientDialog = new ClientPhysicAddressDialog();
        createClientDialog.show(getSupportFragmentManager(),"Create Client Address");
     }
    @Override
    public void  saveNewClientAddress(ClientPhysicalAddressEntity clientPhysicalAddressEntity){
        clientPhysicalAddressViewModel.insert(clientPhysicalAddressEntity);
            snackBar("Client Physical Address  Saved");
    }
  public void snackBar(String message) {
        Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show();
    }
    private void openUpdateClientAddressDialog(ClientPhysicalAddressEntity clientPhysicalAddress) {
       // UpdateClientDialog updateClientDialog = new UpdateClientDialog();
       // updateClientDialog.setClient(clientPhysicalAddress);
       /// updateClientDialog.show(getSupportFragmentManager(),"Update Client");
    }
    private void openClientTreatmentSupportDialog() {
        ClientTreatmentSupporterDialog createClientSupporterDialog = new ClientTreatmentSupporterDialog();
        createClientSupporterDialog.show(getSupportFragmentManager(),"Create Treatment Supporter");
    }
    @Override
    public void saveNewClientTreatmentSupporter(ClientTreatmentSupporterEntity clientTreatmentSupporterEntity) {
        clientTreatmentSupporterViewModel.insert(clientTreatmentSupporterEntity);
        snackBar("Client Treatment Supporter Saved");
    }
  /* @Override
    public void updateNewClientAddress(ClientPhysicalAddressEntity clientPhysicalAddress) {
        clientPhysicalAddressViewModel.update(clientPhysicalAddress);
        snackBar("Client Updated");
    }*/

}