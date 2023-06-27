package com.ucc.ctc.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClientRemote {
    @SerializedName("ClientId")
    private String clientId;
    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("MiddleName")
    private String middleName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("Sex")
    private String sex;
    @SerializedName("DateOfBirth")
    private String dateOfBirth;
    @SerializedName("ReferenceCode")
    private String referenceCode;
    @SerializedName("FacilityId")
    private String facilityId;
    @SerializedName("MaritalStatus")
    private String maritalStatus;
    @SerializedName("CommunitySupportOrg")
    private String communitySupportOrg;
    @SerializedName("FPConsent")
    private String FPConsent;
  //PATIENT DATA
  @SerializedName("PatientID")
  private String patientID;
    @SerializedName("DateFirstPositiveHIVTest")
    private String dateFirstPositiveHIVTest;
    @SerializedName("DateConfirmedHIVPositive")
    private String dateConfirmedHIVPositive;
    @SerializedName("ReferredFromID")
    private String referredFromId;
    @SerializedName("Notes")
    private String notes;
    @SerializedName("FileRef")
    private String fileRef;
    @SerializedName("DrugAllergies")
    private String drugAllergies;
    @SerializedName("PriorExposure")
    private String priorExposure;
    @SerializedName("TransferInId")
    private String transferInId;
    @SerializedName("TBID")
    private String TBID;
    @SerializedName("HBCPatientCode")
    private String HBCPatientCode;

    @SerializedName("TheHeight")
    private String theHeight;
    @SerializedName("UserNumber")
    private String userNumber;
    @SerializedName("DateReadyStartART")
    private String dateReadyStartART;
    @SerializedName("StatusAtEnrollment")
    private String statusAtEnrollment;

    @SerializedName("HTCNumber")
    private String HTCNumber;
    @SerializedName("AppointmentDate")
    private String appointmentDate;
    @SerializedName("IsAppointmentCancelled")
    private String isAppointmentCancelled;
    @SerializedName("DateJoinedGroup")
    private String dateJoinedGroup;

    @SerializedName("SomeID")
    private String someID;
    @SerializedName("MissingFPReasonID")
    private String missingFPReasonID;
    @SerializedName("ReferredFromFacilityCode")
    private String referredFromFacilityCode;
    @SerializedName("DateDiagnosedHIVPositive")
    private String dateDiagnosedHIVPositive;

    @SerializedName("VisitType")
    private String visitType;
    @SerializedName("ToFacility")
    private String toFacility;
    @SerializedName("CondomGiven")
    private String condomGiven;
    @SerializedName("TBScreeningDone")
    private String TBScreeningDone;

    @SerializedName("TBScreeningDetails")
    private String TBScreeningDetails;
    @SerializedName("Occupation")
    private String occupation;
    @SerializedName("ClientCategory")
    private String clientCategory;
    @SerializedName("ReasonForTesting")
    private String reasonForTesting;

    @SerializedName("Disabled")
    private String disabled;
    @SerializedName("DiscordantCouple")
    private String discordantCouple;
    @SerializedName("CondomsIssuedFemale")
    private String condomsIssuedFemale;
    @SerializedName("CondomsIssuedMale")
    private String condomsIssuedMale;

    @SerializedName("receivedResult")
    private String receivedResult;
    @SerializedName("ReferredToStatus")
    private String referredToStatus;
    @SerializedName("CTID")
    private String CTID;
    @SerializedName("FPRegDate")
    private String FPRegDate;

    @SerializedName("ClientUniqueIdentifierType")
    private String clientUniqueIdentifierType;
    @SerializedName("ClientUniqueIdentifierCode")
    private String clientUniqueIdentifierCode;
    @SerializedName("CentralReferenceCode")
    private String centralReferenceCode;
    @SerializedName("CTVisitId")
    private String CTVisitId;

    @SerializedName("BiometricsEnrollmentDate")
    private String biometricsEnrollmentDate;
    @SerializedName("LinkageDate")
    private String linkageDate;
    @SerializedName("IsBiometricLinkage")
    private String isBiometricLinkage;
    @SerializedName("RecordUID")
    private String recordUID;


    @SerializedName("ReferredFromOtherSpecify")
    private String referredFromOtherSpecify;
    @SerializedName("PriorDrugAllergies")
    private String priorDrugAllergies;
    @SerializedName("PriorExposureOtherSpecify")
    private String priorExposureOtherSpecify;

    @SerializedName("FactoredInfo")
    private String factoredInfo;
    @SerializedName("xEntryTimeStamp")
    private String xEntryTimeStamp;
    @SerializedName("IdInfoLastUpdated")
    private String idInfoLastUpdated;
    @SerializedName("ClientBiometryRegStatus")
    private String ClientBiometryRegStatus;

    @SerializedName("StatusAtEnrollmentOtherSpecify")
    private String statusAtEnrollmentOtherSpecify;
    @SerializedName("DateEnrolled")
    private String dateEnrolled;
    @SerializedName("JoinedSupportOrganisation")
    private String joinedSupportOrganisation;
    @SerializedName("VerificationStatus")
    private String verificationStatus;
    @SerializedName("VerificationStatusDate")
    private String verificationStatusDate;
    @SerializedName("ReportedToNationalLevel")
    private String ReportedToNationalLevel;

    @SerializedName("VisitInfo")
    private List<ClientVisitRemote> visitInfoList=null;
    @SerializedName("PhysicalAddressInfo")
    private List<ClientPhysicalAddressRemote> physicalAddressInfo=null;
    @SerializedName("TreatmentSupporterInfo")
    private List<ClientTreatmentSupporterRemote> treatmentSupporterInfo=null;
    @SerializedName("BiometricInfo")
    private List<ClientBiometricRemote> biometricInfo=null;

    public List<ClientVisitRemote> getVisitInfoList() {
        return visitInfoList;
    }

    public List<ClientPhysicalAddressRemote> getPhysicalAddressInfo() {
        return physicalAddressInfo;
    }
   public List<ClientTreatmentSupporterRemote> getTreatmentSupporterInfo() {
        return treatmentSupporterInfo;
    }
   public List<ClientBiometricRemote> getBiometricInfo() {
        return biometricInfo;
    }
    public String getPatientID() {
        return patientID;
    }

    public String getDateFirstPositiveHIVTest() {
        return dateFirstPositiveHIVTest;
    }

    public String getDateConfirmedHIVPositive() {
        return dateConfirmedHIVPositive;
    }

    public String getReferredFromId() {
        return referredFromId;
    }

    public String getNotes() {
        return notes;
    }

    public String getFileRef() {
        return fileRef;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getDrugAllergies() {
        return drugAllergies;
    }

    public String getCommunitySupportOrg() {
        return communitySupportOrg;
    }

    public String getPriorExposure() {
        return priorExposure;
    }

    public String getTransferInId() {
        return transferInId;
    }

    public String getTBID() {
        return TBID;
    }

    public String getHBCPatientCode() {
        return HBCPatientCode;
    }

    public String getTheHeight() {
        return theHeight;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getDateReadyStartART() {
        return dateReadyStartART;
    }

    public String getStatusAtEnrollment() {
        return statusAtEnrollment;
    }

    public String getHTCNumber() {
        return HTCNumber;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getIsAppointmentCancelled() {
        return isAppointmentCancelled;
    }

    public String getDateJoinedGroup() {
        return dateJoinedGroup;
    }

    public String getSomeID() {
        return someID;
    }

    public String getMissingFPReasonID() {
        return missingFPReasonID;
    }

    public String getReferredFromFacilityCode() {
        return referredFromFacilityCode;
    }

    public String getDateDiagnosedHIVPositive() {
        return dateDiagnosedHIVPositive;
    }

    public String getVisitType() {
        return visitType;
    }

    public String getToFacility() {
        return toFacility;
    }

    public String getCondomGiven() {
        return condomGiven;
    }

    public String getTBScreeningDone() {
        return TBScreeningDone;
    }

    public String getTBScreeningDetails() {
        return TBScreeningDetails;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getClientCategory() {
        return clientCategory;
    }

    public String getReasonForTesting() {
        return reasonForTesting;
    }

    public String getDisabled() {
        return disabled;
    }

    public String getDiscordantCouple() {
        return discordantCouple;
    }

    public String getCondomsIssuedFemale() {
        return condomsIssuedFemale;
    }

    public String getCondomsIssuedMale() {
        return condomsIssuedMale;
    }

    public String getReceivedResult() {
        return receivedResult;
    }

    public String getReferredToStatus() {
        return referredToStatus;
    }

    public String getCTID() {
        return CTID;
    }

    public String getFPRegDate() {
        return FPRegDate;
    }

    public String getClientUniqueIdentifierType() {
        return clientUniqueIdentifierType;
    }

    public String getClientUniqueIdentifierCode() {
        return clientUniqueIdentifierCode;
    }

    public String getCentralReferenceCode() {
        return centralReferenceCode;
    }

    public String getCTVisitId() {
        return CTVisitId;
    }

    public String getBiometricsEnrollmentDate() {
        return biometricsEnrollmentDate;
    }

    public String getLinkageDate() {
        return linkageDate;
    }

    public String getIsBiometricLinkage() {
        return isBiometricLinkage;
    }

    public String getRecordUID() {
        return recordUID;
    }

    public String getFPConsent() {
        return FPConsent;
    }

    public String getReferredFromOtherSpecify() {
        return referredFromOtherSpecify;
    }

    public String getPriorDrugAllergies() {
        return priorDrugAllergies;
    }

    public String getPriorExposureOtherSpecify() {
        return priorExposureOtherSpecify;
    }

    public String getFactoredInfo() {
        return factoredInfo;
    }

    public String getxEntryTimeStamp() {
        return xEntryTimeStamp;
    }

    public String getIdInfoLastUpdated() {
        return idInfoLastUpdated;
    }

    public String getClientBiometryRegStatus() {
        return ClientBiometryRegStatus;
    }

    public String getStatusAtEnrollmentOtherSpecify() {
        return statusAtEnrollmentOtherSpecify;
    }

    public String getDateEnrolled() {
        return dateEnrolled;
    }

    public String getJoinedSupportOrganisation() {
        return joinedSupportOrganisation;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public String getVerificationStatusDate() {
        return verificationStatusDate;
    }

    public String getReportedToNationalLevel() {
        return ReportedToNationalLevel;
    }



    public String getClientId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public String getFacilityId() {
        return facilityId;
    }
}
