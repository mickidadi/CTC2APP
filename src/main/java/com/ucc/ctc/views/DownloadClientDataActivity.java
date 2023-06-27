package com.ucc.ctc.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
import com.ucc.ctc.models.ClientBiometricRemote;
import com.ucc.ctc.models.ClientPhysicalAddressRemote;
import com.ucc.ctc.models.ClientRemote;
import com.ucc.ctc.models.ClientRootRemote;
import com.ucc.ctc.models.ClientTreatmentSupporterRemote;
import com.ucc.ctc.models.ClientVisitRemote;
import com.ucc.ctc.models.UploadClientRemoteResponse;
import com.ucc.ctc.models.entity.AdminHierarchyDivisionEntity;
import com.ucc.ctc.models.entity.AdminHierarchyEntity;
import com.ucc.ctc.models.entity.AdminHierarchyExtendedEntity;
import com.ucc.ctc.models.entity.ClientBiometricEntity;
import com.ucc.ctc.models.entity.ClientEntity;
import com.ucc.ctc.models.entity.ClientPhysicalAddressEntity;
import com.ucc.ctc.models.entity.ClientTreatmentSupporterEntity;
import com.ucc.ctc.models.entity.PatientEntity;
import com.ucc.ctc.models.entity.PatientVisitEntity;
import com.ucc.ctc.viewsModel.AdminHierarchyDivisionRemoteViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyDivisionViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyExtendedRemoteViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyExtendedViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyRemoteViewModel;
import com.ucc.ctc.viewsModel.AdminHierarchyViewModel;
import com.ucc.ctc.viewsModel.ClientBiometricViewModel;
import com.ucc.ctc.viewsModel.ClientDownloadViewModel;
import com.ucc.ctc.viewsModel.ClientPhysicalAddressViewModel;
import com.ucc.ctc.viewsModel.ClientTreatmentSupporterViewModel;
import com.ucc.ctc.viewsModel.ClientViewModel;
import com.ucc.ctc.viewsModel.PatientViewModel;
import com.ucc.ctc.viewsModel.PatientVisitViewModel;
import com.ucc.ctc.viewsModel.UploadClientViewModel;

import java.util.List;

public class DownloadClientDataActivity extends AppCompatActivity {

    private ClientDownloadViewModel clientDownloadViewModel;
    private ClientViewModel clientViewModel;
    private PatientVisitViewModel visitViewModel;
    private UploadClientViewModel uploadClientViewModel;
    private ClientTreatmentSupporterViewModel treatmentSupporterViewModel;
    private ClientPhysicalAddressViewModel clientPhysicalAddressViewModel;
    private ClientBiometricViewModel clientBiometricViewModel;

    private PatientViewModel patientViewModel;

    private ProgressDialog progressDialog;
    private String facilityUrl;

    private AdminHierarchyRemoteViewModel adminHierarchyRemoteViewModel;
    private AdminHierarchyDivisionRemoteViewModel adminHierarchyDivisionRemoteViewModel;
    private AdminHierarchyExtendedRemoteViewModel adminHierarchyExtendedRemoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_client_data);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("facilitySession", Context.MODE_PRIVATE);
         facilityUrl = sharedPreferences.getString("facilityUrl", "");
        ///Log.v( "Facility Url",facilityUrl );
        clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        visitViewModel = new ViewModelProvider(this).get(PatientVisitViewModel.class);

        uploadClientViewModel = new ViewModelProvider(this).get(UploadClientViewModel.class);
        treatmentSupporterViewModel = new ViewModelProvider(this).get(ClientTreatmentSupporterViewModel.class);
        clientPhysicalAddressViewModel = new ViewModelProvider(this).get(ClientPhysicalAddressViewModel.class);
        clientBiometricViewModel = new ViewModelProvider(this).get(ClientBiometricViewModel.class);
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
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
                clientDownloadViewModel.getRemoteClient(username, password,facilityUrl);
                clientDownloadViewModel.getClientLiveData().observe(DownloadClientDataActivity.this, new Observer<ClientRootRemote>() {
                    @Override
                    public void onChanged(ClientRootRemote clientRootRemote) {

                        if (clientRootRemote != null) {
                            processClientData(clientRootRemote.getClientInfoList());

                        }
                        progressDialog.dismiss();
                        Toast.makeText(DownloadClientDataActivity.this, "Client Download Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
        // Download AdminHierarchy
        ProgressDialog progressDialogAdmin = new ProgressDialog(this);
        progressDialogAdmin.setMessage("Download Loading...");
        progressDialogAdmin.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        Button downloadAdminHierarchyData=findViewById( R.id.downloadAdminHierarchyData );
        downloadAdminHierarchyData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialogAdmin.show();
                adminHierarchyRemoteViewModel = ViewModelProviders.of(DownloadClientDataActivity.this ).get( AdminHierarchyRemoteViewModel.class );
                adminHierarchyRemoteViewModel.getRemoteAdminHierarchy(facilityUrl);
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

                        }
                        progressDialogAdmin.dismiss();
                        Toast.makeText(DownloadClientDataActivity.this, "Data Download Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                } );

            }
        } );
        // Download AdminHierarchy Extended
        ProgressDialog progressDialogExtend = new ProgressDialog(this);
        progressDialogExtend.setMessage("Download Loading...");
        progressDialogExtend.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Button downloadAdminHierarchyExtendedData=findViewById( R.id.downloadAdminHierarchyExtendedData );
        downloadAdminHierarchyExtendedData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialogExtend.show();
                adminHierarchyExtendedRemoteViewModel = ViewModelProviders.of(DownloadClientDataActivity.this ).get(AdminHierarchyExtendedRemoteViewModel.class );
                adminHierarchyExtendedRemoteViewModel.getRemoteAdminHierarchyExtended(facilityUrl);
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
                        progressDialogExtend.dismiss();
                        Toast.makeText(DownloadClientDataActivity.this, "Data Download Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } );

            }
        } );
        // Download AdminHierarchy Division
        ProgressDialog progressDialogDivision = new ProgressDialog(this);
        progressDialogDivision.setMessage("Download Loading...");
        progressDialogDivision.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Button downloadDivisionData=findViewById( R.id.downloadDivisionData );
        downloadDivisionData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialogDivision.show();
                adminHierarchyDivisionRemoteViewModel = ViewModelProviders.of(DownloadClientDataActivity.this ).get( AdminHierarchyDivisionRemoteViewModel.class );
                adminHierarchyDivisionRemoteViewModel.getRemoteAdminHierarchyExtended(facilityUrl);
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

                        }
                        progressDialogDivision.dismiss();
                        Toast.makeText(DownloadClientDataActivity.this, "Data Download Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } );

            }
        } );
        // progressDialog.dismiss();
        ProgressDialog progressDialogUploadClient = new ProgressDialog(this);
        progressDialogUploadClient.setMessage("Download Loading...");
        progressDialogUploadClient.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        uploadClientData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialogUploadClient.show();

                uploadClientViewModel.sendDataToAPI(facilityUrl).observe(DownloadClientDataActivity.this, new Observer<UploadClientRemoteResponse>() {
                    @Override
                    public void onChanged(UploadClientRemoteResponse payload) {
                            if(payload==null){
                                Log.v("upload", "Failed to connect to /"+facilityUrl);
                            }else{
                                Log.v("upload", "Successfully Upload Data" );
                            }
                        progressDialogUploadClient.dismiss();
                    }
                });
                //finish();
            }
        });
    }

    private void processClientData(List<ClientRemote> clientList) {
        if(clientList.size()>0) {
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
                        clientRemote.getFPConsent()
                );
                clientViewModel.insert( clientEntity );
                //End Client Data
                //save Client Patient Data
                if (clientRemote.getPatientID() != "") {
                    PatientEntity patientData = new PatientEntity(
                            clientRemote.getPatientID(),
                            clientRemote.getClientId(),
                            clientRemote.getFacilityId(),
                            clientRemote.getDateFirstPositiveHIVTest(),
                            clientRemote.getDateConfirmedHIVPositive(),
                            clientRemote.getReferredFromId(),
                            clientRemote.getNotes(),
                            clientRemote.getFileRef(),
                            clientRemote.getDrugAllergies(),
                            clientRemote.getPriorExposure(),
                            clientRemote.getTransferInId(),
                            clientRemote.getTBID(),
                            clientRemote.getHBCPatientCode(),
                            clientRemote.getTheHeight(),
                            clientRemote.getUserNumber(),
                            clientRemote.getDateReadyStartART(),
                            clientRemote.getStatusAtEnrollment(),
                            clientRemote.getHTCNumber(),
                            clientRemote.getAppointmentDate(),
                            clientRemote.getIsAppointmentCancelled(),
                            clientRemote.getDateJoinedGroup(),
                            clientRemote.getSomeID(),
                            clientRemote.getMissingFPReasonID(),
                            clientRemote.getReferredFromFacilityCode(),
                            clientRemote.getDateDiagnosedHIVPositive(),
                            clientRemote.getVisitType(),
                            clientRemote.getToFacility(),
                            clientRemote.getCondomGiven(),
                            clientRemote.getTBScreeningDone(),
                            clientRemote.getTBScreeningDetails(),
                            clientRemote.getOccupation(),
                            clientRemote.getClientCategory(),
                            clientRemote.getReasonForTesting(),
                            clientRemote.getDisabled(),
                            clientRemote.getDiscordantCouple(),
                            clientRemote.getCondomsIssuedFemale(),
                            clientRemote.getCondomsIssuedMale(),
                            clientRemote.getReceivedResult(),
                            clientRemote.getReferredToStatus(),
                            clientRemote.getCTID(),
                            clientRemote.getFPRegDate(),
                            clientRemote.getClientUniqueIdentifierType(),
                            clientRemote.getClientUniqueIdentifierCode(),
                            clientRemote.getCentralReferenceCode(),
                            clientRemote.getCTVisitId(),
                            clientRemote.getBiometricsEnrollmentDate(),
                            clientRemote.getLinkageDate(),
                            clientRemote.getIsBiometricLinkage(),
                            clientRemote.getRecordUID(),
                            clientRemote.getReferredFromOtherSpecify(),
                            clientRemote.getPriorDrugAllergies(),
                            clientRemote.getPriorExposureOtherSpecify(),
                            clientRemote.getFactoredInfo(),
                            clientRemote.getxEntryTimeStamp(),
                            clientRemote.getIdInfoLastUpdated(),
                            clientRemote.getClientBiometryRegStatus(),
                            clientRemote.getStatusAtEnrollmentOtherSpecify(),
                            clientRemote.getDateEnrolled(),
                            clientRemote.getJoinedSupportOrganisation(),
                            clientRemote.getVerificationStatus(),
                            clientRemote.getVerificationStatusDate(),
                            clientRemote.getReportedToNationalLevel()
                    );
                    patientViewModel.insert( patientData );
                }
                //end
                if (clientRemote.getVisitInfoList().size() > 0) {
                    for (ClientVisitRemote visit : clientRemote.getVisitInfoList()) {
                        PatientVisitEntity visitEntity = new PatientVisitEntity(
                                visit.getPatientId(),
                                visit.getVisitTypeCode(),
                                visit.getVisitDate(),
                                visit.getNumDaysDispensed()
                        );
                        visitViewModel.insert( visitEntity );
                    }
                }
                //save Treatment Supporter
                List<ClientTreatmentSupporterRemote> listTreatment = clientRemote.getTreatmentSupporterInfo(); // Assuming getList() retrieves a List object
                        if (!listTreatment.isEmpty()) {
                    for (ClientTreatmentSupporterRemote treatmentSupporterRemote : clientRemote.getTreatmentSupporterInfo()) {
                        ClientTreatmentSupporterEntity clientTreatmentSupporterEntity = new ClientTreatmentSupporterEntity(
                                clientRemote.getClientId(),
                                treatmentSupporterRemote.getHelper(),
                                treatmentSupporterRemote.getHelperContact(),
                                treatmentSupporterRemote.getClientCommunityGroup(),
                                treatmentSupporterRemote.getDateJoined(),
                                treatmentSupporterRemote.getSMSConsent()

                        );
                        treatmentSupporterViewModel.insert( clientTreatmentSupporterEntity );
                    }
                }
                //end
                //save Client Physical Address Data
                if (clientRemote.getPhysicalAddressInfo().size() > 0) {
                    for (ClientPhysicalAddressRemote clientPhysicalAddressRemote : clientRemote.getPhysicalAddressInfo()) {
                        ClientPhysicalAddressEntity clientTreatmentSupporterEntity = new ClientPhysicalAddressEntity(
                                clientRemote.getClientId(),
                                clientPhysicalAddressRemote.getCouncilName(),
                                clientPhysicalAddressRemote.getDivisionName(),
                                clientPhysicalAddressRemote.getWardName(),
                                clientPhysicalAddressRemote.getVillageMtaa(),
                                "",
                                clientPhysicalAddressRemote.getTenCellLeaderName(),
                                clientPhysicalAddressRemote.getHouseholdHead(),
                                clientPhysicalAddressRemote.getContact(),
                                clientPhysicalAddressRemote.getContact(),
                                clientPhysicalAddressRemote.getHouseholdHeadContact(),
                                clientPhysicalAddressRemote.getSMSConsent()
                        );
                        clientPhysicalAddressViewModel.insert( clientTreatmentSupporterEntity );
                    }
                }
                //end
                //save Client Biometric Data
                if (clientRemote.getBiometricInfo().size() > 0) {
                    for (ClientBiometricRemote clientBiometricRemote : clientRemote.getBiometricInfo()) {
                        ClientBiometricEntity clientBiometric = new ClientBiometricEntity(
                                clientRemote.getClientId(),
                                clientBiometricRemote.getBiometricTemplate(),
                                clientBiometricRemote.getEntryDate(),
                                clientBiometricRemote.getRowVersion(),
                                clientBiometricRemote.getCentralReferenceCode(),
                                clientBiometricRemote.getIsDuplicate(),
                                clientBiometricRemote.getQuality(),
                                clientBiometricRemote.getActionTag(),
                                clientBiometricRemote.getBiometricsRegOrigin(),
                                clientBiometricRemote.getChangeTrackStatus(),
                                clientBiometricRemote.getRecGUID(),
                                clientBiometricRemote.getBiometricSpecificationId()
                        );
                        clientBiometricViewModel.insert( clientBiometric );
                    }
                }
                //end

            }
        }
    }
}
