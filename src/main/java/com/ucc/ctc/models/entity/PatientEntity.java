package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/*@Entity(tableName = "tblPatients"
        /*foreignKeys = {
                @ForeignKey(entity = ClientEntity.class,
                        parentColumns = "ClientId",
                        childColumns = "ClientId",
                        onDelete = CASCADE, onUpdate = CASCADE)
        })  , indices = {@Index(value = {"FacilityId"}, unique = true)}*/
@Entity(tableName = "tblPatients")
public class PatientEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="PatientId")
    private String patientId;
    @ColumnInfo(name="ClientId")
    private String clientId;
    @ColumnInfo(name="FacilityId")
    private String facilityId;

    @ColumnInfo(name="DateFirstPositiveHIVTest")
    private String dateFirstPositiveHIVTest;
     @ColumnInfo(name="DateConfirmedHIVPositive")
    private String dateConfirmedHIVPositive;
     @ColumnInfo(name="ReferredFromID")
    private String referredFromId;
     @ColumnInfo(name="Notes")
    private String notes;
     @ColumnInfo(name="FileRef")
    private String fileRef;
     @ColumnInfo(name="DrugAllergies")
    private String drugAllergies;
     @ColumnInfo(name="PriorExposure")
    private String priorExposure;
     @ColumnInfo(name="TransferInId")
    private String transferInId;
     @ColumnInfo(name="TBID")
    private String TBID;
     @ColumnInfo(name="HBCPatientCode")
    private String HBCPatientCode;

     @ColumnInfo(name="TheHeight")
    private String theHeight;
     @ColumnInfo(name="UserNumber")
    private String userNumber;
     @ColumnInfo(name="DateReadyStartART")
    private String dateReadyStartART;
     @ColumnInfo(name="StatusAtEnrollment")
    private String statusAtEnrollment;

     @ColumnInfo(name="HTCNumber")
    private String HTCNumber;
     @ColumnInfo(name="AppointmentDate")
    private String appointmentDate;
     @ColumnInfo(name="IsAppointmentCancelled")
    private String isAppointmentCancelled;
     @ColumnInfo(name="DateJoinedGroup")
    private String dateJoinedGroup;

     @ColumnInfo(name="SomeID")
    private String someID;
     @ColumnInfo(name="MissingFPReasonID")
    private String missingFPReasonID;
     @ColumnInfo(name="ReferredFromFacilityCode")
    private String referredFromFacilityCode;
     @ColumnInfo(name="DateDiagnosedHIVPositive")
    private String dateDiagnosedHIVPositive;

     @ColumnInfo(name="VisitType")
    private String visitType;
     @ColumnInfo(name="ToFacility")
    private String toFacility;
     @ColumnInfo(name="CondomGiven")
    private String condomGiven;
     @ColumnInfo(name="TBScreeningDone")
    private String TBScreeningDone;

     @ColumnInfo(name="TBScreeningDetails")
    private String TBScreeningDetails;
     @ColumnInfo(name="Occupation")
    private String occupation;
     @ColumnInfo(name="ClientCategory")
    private String clientCategory;
     @ColumnInfo(name="ReasonForTesting")
    private String reasonForTesting;

     @ColumnInfo(name="Disabled")
    private String disabled;
     @ColumnInfo(name="DiscordantCouple")
    private String discordantCouple;
     @ColumnInfo(name="CondomsIssuedFemale")
    private String condomsIssuedFemale;
     @ColumnInfo(name="CondomsIssuedMale")
    private String condomsIssuedMale;

     @ColumnInfo(name="receivedResult")
    private String receivedResult;
     @ColumnInfo(name="ReferredToStatus")
    private String referredToStatus;
     @ColumnInfo(name="CTID")
    private String CTID;
     @ColumnInfo(name="FPRegDate")
    private String FPRegDate;

     @ColumnInfo(name="ClientUniqueIdentifierType")
    private String clientUniqueIdentifierType;
     @ColumnInfo(name="ClientUniqueIdentifierCode")
    private String clientUniqueIdentifierCode;
     @ColumnInfo(name="CentralReferenceCode")
    private String centralReferenceCode;
     @ColumnInfo(name="CTVisitId")
    private String CTVisitId;

     @ColumnInfo(name="BiometricsEnrollmentDate")
    private String biometricsEnrollmentDate;
     @ColumnInfo(name="LinkageDate")
    private String linkageDate;
     @ColumnInfo(name="IsBiometricLinkage")
    private String isBiometricLinkage;
     @ColumnInfo(name="RecordUID")
    private String recordUID;


     @ColumnInfo(name="ReferredFromOtherSpecify")
    private String referredFromOtherSpecify;
     @ColumnInfo(name="PriorDrugAllergies")
    private String priorDrugAllergies;
     @ColumnInfo(name="PriorExposureOtherSpecify")
    private String priorExposureOtherSpecify;

     @ColumnInfo(name="FactoredInfo")
    private String factoredInfo;
     @ColumnInfo(name="xEntryTimeStamp")
    private String xEntryTimeStamp;
     @ColumnInfo(name="IdInfoLastUpdated")
    private String idInfoLastUpdated;
     @ColumnInfo(name="ClientBiometryRegStatus")
    private String clientBiometryRegStatus;

     @ColumnInfo(name="StatusAtEnrollmentOtherSpecify")
    private String statusAtEnrollmentOtherSpecify;
     @ColumnInfo(name="DateEnrolled")
    private String dateEnrolled;
     @ColumnInfo(name="JoinedSupportOrganisation")
    private String joinedSupportOrganisation;
     @ColumnInfo(name="VerificationStatus")
    private String verificationStatus;
     @ColumnInfo(name="VerificationStatusDate")
    private String verificationStatusDate;
     @ColumnInfo(name="ReportedToNationalLevel")
    private String reportedToNationalLevel;
   public PatientEntity(String patientId, String clientId, String facilityId, String dateFirstPositiveHIVTest, String dateConfirmedHIVPositive, String referredFromId, String notes, String fileRef, String drugAllergies, String priorExposure, String transferInId, String TBID, String HBCPatientCode, String theHeight, String userNumber, String dateReadyStartART, String statusAtEnrollment, String HTCNumber, String appointmentDate, String isAppointmentCancelled, String dateJoinedGroup, String someID, String missingFPReasonID, String referredFromFacilityCode, String dateDiagnosedHIVPositive, String visitType, String toFacility, String condomGiven, String TBScreeningDone, String TBScreeningDetails, String occupation, String clientCategory, String reasonForTesting, String disabled, String discordantCouple, String condomsIssuedFemale, String condomsIssuedMale, String receivedResult, String referredToStatus, String CTID, String FPRegDate, String clientUniqueIdentifierType, String clientUniqueIdentifierCode, String centralReferenceCode, String CTVisitId, String biometricsEnrollmentDate, String linkageDate, String isBiometricLinkage, String recordUID, String referredFromOtherSpecify, String priorDrugAllergies, String priorExposureOtherSpecify, String factoredInfo, String xEntryTimeStamp, String idInfoLastUpdated, String clientBiometryRegStatus, String statusAtEnrollmentOtherSpecify, String dateEnrolled, String joinedSupportOrganisation, String verificationStatus, String verificationStatusDate, String reportedToNationalLevel) {
        this.patientId = patientId;
        this.clientId = clientId;
        this.facilityId = facilityId;
        this.dateFirstPositiveHIVTest = dateFirstPositiveHIVTest;
        this.dateConfirmedHIVPositive = dateConfirmedHIVPositive;
        this.referredFromId = referredFromId;
        this.notes = notes;
        this.fileRef = fileRef;
        this.drugAllergies = drugAllergies;
        this.priorExposure = priorExposure;
        this.transferInId = transferInId;
        this.TBID = TBID;
        this.HBCPatientCode = HBCPatientCode;
        this.theHeight = theHeight;
        this.userNumber = userNumber;
        this.dateReadyStartART = dateReadyStartART;
        this.statusAtEnrollment = statusAtEnrollment;
        this.HTCNumber = HTCNumber;
        this.appointmentDate = appointmentDate;
        this.isAppointmentCancelled = isAppointmentCancelled;
        this.dateJoinedGroup = dateJoinedGroup;
        this.someID = someID;
        this.missingFPReasonID = missingFPReasonID;
        this.referredFromFacilityCode = referredFromFacilityCode;
        this.dateDiagnosedHIVPositive = dateDiagnosedHIVPositive;
        this.visitType = visitType;
        this.toFacility = toFacility;
        this.condomGiven = condomGiven;
        this.TBScreeningDone = TBScreeningDone;
        this.TBScreeningDetails = TBScreeningDetails;
        this.occupation = occupation;
        this.clientCategory = clientCategory;
        this.reasonForTesting = reasonForTesting;
        this.disabled = disabled;
        this.discordantCouple = discordantCouple;
        this.condomsIssuedFemale = condomsIssuedFemale;
        this.condomsIssuedMale = condomsIssuedMale;
        this.receivedResult = receivedResult;
        this.referredToStatus = referredToStatus;
        this.CTID = CTID;
        this.FPRegDate = FPRegDate;
        this.clientUniqueIdentifierType = clientUniqueIdentifierType;
        this.clientUniqueIdentifierCode = clientUniqueIdentifierCode;
        this.centralReferenceCode = centralReferenceCode;
        this.CTVisitId = CTVisitId;
        this.biometricsEnrollmentDate = biometricsEnrollmentDate;
        this.linkageDate = linkageDate;
        this.isBiometricLinkage = isBiometricLinkage;
        this.recordUID = recordUID;
        this.referredFromOtherSpecify = referredFromOtherSpecify;
        this.priorDrugAllergies = priorDrugAllergies;
        this.priorExposureOtherSpecify = priorExposureOtherSpecify;
        this.factoredInfo = factoredInfo;
        this.xEntryTimeStamp = xEntryTimeStamp;
        this.idInfoLastUpdated = idInfoLastUpdated;
        this.clientBiometryRegStatus = clientBiometryRegStatus;
        this.statusAtEnrollmentOtherSpecify = statusAtEnrollmentOtherSpecify;
        this.dateEnrolled = dateEnrolled;
        this.joinedSupportOrganisation = joinedSupportOrganisation;
        this.verificationStatus = verificationStatus;
        this.verificationStatusDate = verificationStatusDate;
        this.reportedToNationalLevel = reportedToNationalLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getDateFirstPositiveHIVTest() {
        return dateFirstPositiveHIVTest;
    }

    public void setDateFirstPositiveHIVTest(String dateFirstPositiveHIVTest) {
        this.dateFirstPositiveHIVTest = dateFirstPositiveHIVTest;
    }

    public String getDateConfirmedHIVPositive() {
        return dateConfirmedHIVPositive;
    }

    public void setDateConfirmedHIVPositive(String dateConfirmedHIVPositive) {
        this.dateConfirmedHIVPositive = dateConfirmedHIVPositive;
    }

    public String getReferredFromId() {
        return referredFromId;
    }

    public void setReferredFromId(String referredFromId) {
        this.referredFromId = referredFromId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFileRef() {
        return fileRef;
    }

    public void setFileRef(String fileRef) {
        this.fileRef = fileRef;
    }

    public String getDrugAllergies() {
        return drugAllergies;
    }

    public void setDrugAllergies(String drugAllergies) {
        this.drugAllergies = drugAllergies;
    }

    public String getPriorExposure() {
        return priorExposure;
    }

    public void setPriorExposure(String priorExposure) {
        this.priorExposure = priorExposure;
    }

    public String getTransferInId() {
        return transferInId;
    }

    public void setTransferInId(String transferInId) {
        this.transferInId = transferInId;
    }

    public String getTBID() {
        return TBID;
    }

    public void setTBID(String TBID) {
        this.TBID = TBID;
    }

    public String getHBCPatientCode() {
        return HBCPatientCode;
    }

    public void setHBCPatientCode(String HBCPatientCode) {
        this.HBCPatientCode = HBCPatientCode;
    }

    public String getTheHeight() {
        return theHeight;
    }

    public void setTheHeight(String theHeight) {
        this.theHeight = theHeight;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getDateReadyStartART() {
        return dateReadyStartART;
    }

    public void setDateReadyStartART(String dateReadyStartART) {
        this.dateReadyStartART = dateReadyStartART;
    }

    public String getStatusAtEnrollment() {
        return statusAtEnrollment;
    }

    public void setStatusAtEnrollment(String statusAtEnrollment) {
        this.statusAtEnrollment = statusAtEnrollment;
    }

    public String getHTCNumber() {
        return HTCNumber;
    }

    public void setHTCNumber(String HTCNumber) {
        this.HTCNumber = HTCNumber;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getIsAppointmentCancelled() {
        return isAppointmentCancelled;
    }

    public void setIsAppointmentCancelled(String isAppointmentCancelled) {
        this.isAppointmentCancelled = isAppointmentCancelled;
    }

    public String getDateJoinedGroup() {
        return dateJoinedGroup;
    }

    public void setDateJoinedGroup(String dateJoinedGroup) {
        this.dateJoinedGroup = dateJoinedGroup;
    }

    public String getSomeID() {
        return someID;
    }

    public void setSomeID(String someID) {
        this.someID = someID;
    }

    public String getMissingFPReasonID() {
        return missingFPReasonID;
    }

    public void setMissingFPReasonID(String missingFPReasonID) {
        this.missingFPReasonID = missingFPReasonID;
    }

    public String getReferredFromFacilityCode() {
        return referredFromFacilityCode;
    }

    public void setReferredFromFacilityCode(String referredFromFacilityCode) {
        this.referredFromFacilityCode = referredFromFacilityCode;
    }

    public String getDateDiagnosedHIVPositive() {
        return dateDiagnosedHIVPositive;
    }

    public void setDateDiagnosedHIVPositive(String dateDiagnosedHIVPositive) {
        this.dateDiagnosedHIVPositive = dateDiagnosedHIVPositive;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getToFacility() {
        return toFacility;
    }

    public void setToFacility(String toFacility) {
        this.toFacility = toFacility;
    }

    public String getCondomGiven() {
        return condomGiven;
    }

    public void setCondomGiven(String condomGiven) {
        this.condomGiven = condomGiven;
    }

    public String getTBScreeningDone() {
        return TBScreeningDone;
    }

    public void setTBScreeningDone(String TBScreeningDone) {
        this.TBScreeningDone = TBScreeningDone;
    }

    public String getTBScreeningDetails() {
        return TBScreeningDetails;
    }

    public void setTBScreeningDetails(String TBScreeningDetails) {
        this.TBScreeningDetails = TBScreeningDetails;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getClientCategory() {
        return clientCategory;
    }

    public void setClientCategory(String clientCategory) {
        this.clientCategory = clientCategory;
    }

    public String getReasonForTesting() {
        return reasonForTesting;
    }

    public void setReasonForTesting(String reasonForTesting) {
        this.reasonForTesting = reasonForTesting;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDiscordantCouple() {
        return discordantCouple;
    }

    public void setDiscordantCouple(String discordantCouple) {
        this.discordantCouple = discordantCouple;
    }

    public String getCondomsIssuedFemale() {
        return condomsIssuedFemale;
    }

    public void setCondomsIssuedFemale(String condomsIssuedFemale) {
        this.condomsIssuedFemale = condomsIssuedFemale;
    }

    public String getCondomsIssuedMale() {
        return condomsIssuedMale;
    }

    public void setCondomsIssuedMale(String condomsIssuedMale) {
        this.condomsIssuedMale = condomsIssuedMale;
    }

    public String getReceivedResult() {
        return receivedResult;
    }

    public void setReceivedResult(String receivedResult) {
        this.receivedResult = receivedResult;
    }

    public String getReferredToStatus() {
        return referredToStatus;
    }

    public void setReferredToStatus(String referredToStatus) {
        this.referredToStatus = referredToStatus;
    }

    public String getCTID() {
        return CTID;
    }

    public void setCTID(String CTID) {
        this.CTID = CTID;
    }

    public String getFPRegDate() {
        return FPRegDate;
    }

    public void setFPRegDate(String FPRegDate) {
        this.FPRegDate = FPRegDate;
    }

    public String getClientUniqueIdentifierType() {
        return clientUniqueIdentifierType;
    }

    public void setClientUniqueIdentifierType(String clientUniqueIdentifierType) {
        this.clientUniqueIdentifierType = clientUniqueIdentifierType;
    }

    public String getClientUniqueIdentifierCode() {
        return clientUniqueIdentifierCode;
    }

    public void setClientUniqueIdentifierCode(String clientUniqueIdentifierCode) {
        this.clientUniqueIdentifierCode = clientUniqueIdentifierCode;
    }

    public String getCentralReferenceCode() {
        return centralReferenceCode;
    }

    public void setCentralReferenceCode(String centralReferenceCode) {
        this.centralReferenceCode = centralReferenceCode;
    }

    public String getCTVisitId() {
        return CTVisitId;
    }

    public void setCTVisitId(String CTVisitId) {
        this.CTVisitId = CTVisitId;
    }

    public String getBiometricsEnrollmentDate() {
        return biometricsEnrollmentDate;
    }

    public void setBiometricsEnrollmentDate(String biometricsEnrollmentDate) {
        this.biometricsEnrollmentDate = biometricsEnrollmentDate;
    }

    public String getLinkageDate() {
        return linkageDate;
    }

    public void setLinkageDate(String linkageDate) {
        this.linkageDate = linkageDate;
    }

    public String getIsBiometricLinkage() {
        return isBiometricLinkage;
    }

    public void setIsBiometricLinkage(String isBiometricLinkage) {
        this.isBiometricLinkage = isBiometricLinkage;
    }

    public String getRecordUID() {
        return recordUID;
    }

    public void setRecordUID(String recordUID) {
        this.recordUID = recordUID;
    }

    public String getReferredFromOtherSpecify() {
        return referredFromOtherSpecify;
    }

    public void setReferredFromOtherSpecify(String referredFromOtherSpecify) {
        this.referredFromOtherSpecify = referredFromOtherSpecify;
    }

    public String getPriorDrugAllergies() {
        return priorDrugAllergies;
    }

    public void setPriorDrugAllergies(String priorDrugAllergies) {
        this.priorDrugAllergies = priorDrugAllergies;
    }

    public String getPriorExposureOtherSpecify() {
        return priorExposureOtherSpecify;
    }

    public void setPriorExposureOtherSpecify(String priorExposureOtherSpecify) {
        this.priorExposureOtherSpecify = priorExposureOtherSpecify;
    }

    public String getFactoredInfo() {
        return factoredInfo;
    }

    public void setFactoredInfo(String factoredInfo) {
        this.factoredInfo = factoredInfo;
    }

    public String getXEntryTimeStamp() {
        return xEntryTimeStamp;
    }

    public void setXEntryTimeStamp(String xEntryTimeStamp) {
        this.xEntryTimeStamp = xEntryTimeStamp;
    }

    public String getIdInfoLastUpdated() {
        return idInfoLastUpdated;
    }

    public void setIdInfoLastUpdated(String idInfoLastUpdated) {
        this.idInfoLastUpdated = idInfoLastUpdated;
    }

    public String getClientBiometryRegStatus() {
        return clientBiometryRegStatus;
    }

    public void setClientBiometryRegStatus(String clientBiometryRegStatus) {
        this.clientBiometryRegStatus = clientBiometryRegStatus;
    }

    public String getStatusAtEnrollmentOtherSpecify() {
        return statusAtEnrollmentOtherSpecify;
    }

    public void setStatusAtEnrollmentOtherSpecify(String statusAtEnrollmentOtherSpecify) {
        this.statusAtEnrollmentOtherSpecify = statusAtEnrollmentOtherSpecify;
    }

    public String getDateEnrolled() {
        return dateEnrolled;
    }

    public void setDateEnrolled(String dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }

    public String getJoinedSupportOrganisation() {
        return joinedSupportOrganisation;
    }

    public void setJoinedSupportOrganisation(String joinedSupportOrganisation) {
        this.joinedSupportOrganisation = joinedSupportOrganisation;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getVerificationStatusDate() {
        return verificationStatusDate;
    }

    public void setVerificationStatusDate(String verificationStatusDate) {
        this.verificationStatusDate = verificationStatusDate;
    }

    public String getReportedToNationalLevel() {
        return reportedToNationalLevel;
    }

    public void setReportedToNationalLevel(String reportedToNationalLevel) {
        this.reportedToNationalLevel = reportedToNationalLevel;
    }
}
