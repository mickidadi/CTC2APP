package com.ucc.ctc.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ucc.ctc.R;
import com.ucc.ctc.models.AdminHierarchyDivisionRemote;
import com.ucc.ctc.models.AdminHierarchyDivisionRootRemote;
import com.ucc.ctc.models.AdminHierarchyExtendedRemote;
import com.ucc.ctc.models.AdminHierarchyExtendedRootRemote;
import com.ucc.ctc.models.AdminHierarchyRemote;
import com.ucc.ctc.models.AdminHierarchyRootRemote;
import com.ucc.ctc.models.ClientRemote;
import com.ucc.ctc.models.ClientRootRemote;
import com.ucc.ctc.models.ClientVisitRemote;
import com.ucc.ctc.models.UploadClientRemoteResponse;
import com.ucc.ctc.models.entity.AdminHierarchyDivisionEntity;
import com.ucc.ctc.models.entity.AdminHierarchyEntity;
import com.ucc.ctc.models.entity.AdminHierarchyExtendedEntity;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.models.entity.PatientVisitEntity;
import com.ucc.ctc.viewsModel.AdminHierarchyDivisionRemoteViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyDivisionViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyExtendedRemoteViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyExtendedViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyRemoteViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyViewModel;
import com.ucc.ctc.viewsModel.ClientDownloadViewModel;
import com.ucc.ctc.viewsModel.ClientViewModel;
import com.ucc.ctc.viewsModel.PatientVisitViewModel;
import com.ucc.ctc.viewsModel.UploadClientViewModel;

import java.util.List;

public class DownloadClientDataActivity extends AppCompatActivity {

    private ClientDownloadViewModel clientDownloadViewModel;
    private ClientViewModel clientViewModel;
    private PatientVisitViewModel visitViewModel;
    private UploadClientViewModel uploadClientViewModel;
    private ProgressDialog progressDialog;

    private AdminHierarchyRemoteViewModel adminHierarchyRemoteViewModel;
    private AdminHierarchyDivisionRemoteViewModel adminHierarchyDivisionRemoteViewModel;
    private AdminHierarchyExtendedRemoteViewModel adminHierarchyExtendedRemoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_client_data);

        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        visitViewModel = new ViewModelProvider(this).get(PatientVisitViewModel.class);
        uploadClientViewModel = new ViewModelProvider(this).get(UploadClientViewModel.class);

        AdminHierarchyViewModel adminHierarchyViewModel=new AdminHierarchyViewModel(getApplication());
        AdminHierarchyDivisionViewModel adminHierarchyDivisionViewModel=new AdminHierarchyDivisionViewModel(getApplication());
        AdminHierarchyExtendedViewModel adminHierarchyExtendedViewModel=new AdminHierarchyExtendedViewModel(getApplication());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Download Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        Button download = findViewById(R.id.downloadClientData);
        Button uploadClientData = findViewById(R.id.uploadClientData);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String username = "2312313";
                String password = "3432423";

                clientDownloadViewModel = new ViewModelProvider(DownloadClientDataActivity.this).get(ClientDownloadViewModel.class);
                clientDownloadViewModel.getRemoteClient(username, password);
                clientDownloadViewModel.getClientLiveData().observe(DownloadClientDataActivity.this, new Observer<ClientRootRemote>() {
                    @Override
                    public void onChanged(ClientRootRemote clientRootRemote) {
                        progressDialog.dismiss();
                        if (clientRootRemote != null) {
                            processClientData(clientRootRemote.getClientInfoList());

                            Intent intentMessage = new Intent(DownloadClientDataActivity.this, ClientActivity.class);
                            startActivity(intentMessage);
                        }
                    }
                });
            }
        });
        // Download AdminHierarchy
        Button downloadAdminHierarchyData=findViewById( R.id.downloadAdminHierarchyData );
        downloadAdminHierarchyData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminHierarchyRemoteViewModel = ViewModelProviders.of(DownloadClientDataActivity.this ).get( AdminHierarchyRemoteViewModel.class );
                adminHierarchyRemoteViewModel.getRemoteAdminHierarchy();
                adminHierarchyRemoteViewModel.getAdminHierarchyRemoteLiveDataLiveData().observe( DownloadClientDataActivity.this, new Observer<AdminHierarchyRootRemote>() {
                    @Override
                    public void onChanged(AdminHierarchyRootRemote adminHierarchyRootRemote) {
                        Log.v("Welcome","Message IN ");
                        if (adminHierarchyRootRemote != null) {
                            //Starting Processing
                            List<AdminHierarchyRemote> adminHierarchyInfoList =adminHierarchyRootRemote.getAdminHierarchyInfoList();
                            for (AdminHierarchyRemote adminHierarchyRemote : adminHierarchyInfoList) {
                                ///Log.v("Welcome","Message IN "+patient.getClientId());
                                AdminHierarchyEntity adminHierarchyEntity = new AdminHierarchyEntity(
                                        adminHierarchyRemote.getNodeId(),adminHierarchyRemote.getCountry(),adminHierarchyRemote.getZone()
                                        ,adminHierarchyRemote.getRegion(),adminHierarchyRemote.getDistrict(),adminHierarchyRemote.getCouncil()
                                        ,adminHierarchyRemote.getWard(),adminHierarchyRemote.getVillageMtaa()
                                );
                                Log.v("Welcome","Message IN "+adminHierarchyEntity.getWard());
                                adminHierarchyViewModel.insert(adminHierarchyEntity);
                            }
                            //end processing
                            Intent intentMessage = new Intent( DownloadClientDataActivity.this, ClientActivity.class );
                            startActivity( intentMessage);
                        }
                    }
                } );

            }
        } );
        // Download AdminHierarchy Extended
        Button downloadAdminHierarchyExtendedData=findViewById( R.id.downloadAdminHierarchyExtendedData );
        downloadAdminHierarchyExtendedData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminHierarchyExtendedRemoteViewModel = ViewModelProviders.of(DownloadClientDataActivity.this ).get(AdminHierarchyExtendedRemoteViewModel.class );
                adminHierarchyExtendedRemoteViewModel.getRemoteAdminHierarchyExtended();
                adminHierarchyExtendedRemoteViewModel.getAdminHierarchyRemoteLiveDataLiveData().observe( DownloadClientDataActivity.this, new Observer<AdminHierarchyExtendedRootRemote>() {
                    @Override
                    public void onChanged(AdminHierarchyExtendedRootRemote adminHierarchyExtendedRootRemote) {
                        Log.v("Welcome","Message IN ");
                        if (adminHierarchyExtendedRootRemote != null) {
                            //Starting Processing
                            List<AdminHierarchyExtendedRemote> adminHierarchyExtendedInfoList =adminHierarchyExtendedRootRemote.getAdminHierarchyInfoList();
                            for (AdminHierarchyExtendedRemote adminHierarchyRemote : adminHierarchyExtendedInfoList) {
                                ///Log.v("Welcome","Message IN "+patient.getClientId());
                                AdminHierarchyExtendedEntity adminHierarchyEntity = new AdminHierarchyExtendedEntity(
                                        adminHierarchyRemote.getNodeId(),adminHierarchyRemote.getCountry(),adminHierarchyRemote.getZone()
                                        ,adminHierarchyRemote.getRegion(),adminHierarchyRemote.getDistrict(),adminHierarchyRemote.getCouncil()
                                        ,adminHierarchyRemote.getWard(),adminHierarchyRemote.getVillageMtaa(),
                                        adminHierarchyRemote.getLeaderName(),
                                        adminHierarchyRemote.getWardNodeId(),adminHierarchyRemote.getEntryTimeStamp()
                                        ,adminHierarchyRemote.getRowVersion(),adminHierarchyRemote.getChangeTrackStatus(),
                                        adminHierarchyRemote.getRecGUID(),adminHierarchyRemote.getExportSessionId()
                                );
                                Log.v("Welcome","Message IN "+adminHierarchyEntity.getCouncil());
                                adminHierarchyExtendedViewModel.insert(adminHierarchyEntity);
                            }
                            //end processing
                            Intent intentMessage = new Intent( DownloadClientDataActivity.this, ClientActivity.class );
                            startActivity( intentMessage);
                        }
                    }
                } );

            }
        } );
        // Download AdminHierarchy Division
        Button downloadDivisionData=findViewById( R.id.downloadDivisionData );
        downloadDivisionData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adminHierarchyDivisionRemoteViewModel = ViewModelProviders.of(DownloadClientDataActivity.this ).get( AdminHierarchyDivisionRemoteViewModel.class );
                adminHierarchyDivisionRemoteViewModel.getRemoteAdminHierarchyExtended();
                adminHierarchyDivisionRemoteViewModel.getAdminHierarchyDivisionRemoteLiveDataLiveData().observe( DownloadClientDataActivity.this, new Observer<AdminHierarchyDivisionRootRemote>() {
                    @Override
                    public void onChanged(AdminHierarchyDivisionRootRemote adminHierarchyDivisionRootRemote) {
                        Log.v("Welcome","Message IN ");
                        if (adminHierarchyDivisionRootRemote != null) {
                            //Starting Processing
                            List<AdminHierarchyDivisionRemote> adminHierarchyInfoList =adminHierarchyDivisionRootRemote.getAdminHierarchyDivisionInfoList();
                            for (AdminHierarchyDivisionRemote adminHierarchyRemote : adminHierarchyInfoList) {
                                ///Log.v("Welcome","Message IN "+patient.getClientId());
                                AdminHierarchyDivisionEntity adminHierarchyEntity = new AdminHierarchyDivisionEntity(
                                        adminHierarchyRemote.getRegionCode(),adminHierarchyRemote.getDistrictCode()
                                        ,adminHierarchyRemote.getWardName(),adminHierarchyRemote.getNBSWardCode()
                                );
                                Log.v("Welcome","Message IN "+adminHierarchyEntity.getWardName());
                                adminHierarchyDivisionViewModel.insert(adminHierarchyEntity);
                            }
                            //end processing
                            Intent intentMessage = new Intent( DownloadClientDataActivity.this, ClientActivity.class );
                            startActivity( intentMessage);
                        }
                    }
                } );

            }
        } );
        // progressDialog.dismiss();
        uploadClientData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                uploadClientViewModel.sendDataToAPI().observe(DownloadClientDataActivity.this, new Observer<UploadClientRemoteResponse>() {
                    @Override
                    public void onChanged(UploadClientRemoteResponse payload) {
                        Log.v("upload", payload.getFacilityId());
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    private void processClientData(List<ClientRemote> clientList) {
        for (ClientRemote clientRemote : clientList) {
            ClientEntity clientEntity = new ClientEntity(
                    clientRemote.getClientId(),
                    clientRemote.getFirstName(),
                    clientRemote.getMiddleName(),
                    clientRemote.getLastName(),
                    clientRemote.getSex(),
                    clientRemote.getDateOfBirth(),
                    clientRemote.getReferenceCode(),
                    clientRemote.getFacilityId(),
                    ""
            );
            clientViewModel.insert(clientEntity);

            for (ClientVisitRemote visit : clientRemote.getVisitInfoList()) {
                PatientVisitEntity visitEntity = new PatientVisitEntity(
                        visit.getPatientId(),
                        visit.getVisitTypeCode(),
                        visit.getVisitDate(),
                        visit.getNumDaysDispensed()
                );
                visitViewModel.insert(visitEntity);
            }
        }
    }
}
