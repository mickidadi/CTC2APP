package com.ucc.ctc.models;

import com.google.gson.annotations.SerializedName;

public class ClientVisitRemote {
    @SerializedName("PatientId")
    private String patientId;
    @SerializedName("VisitTypeCode")
    private String visitTypeCode;
    @SerializedName("VisitDate")
    private String visitDate;
    @SerializedName("NumDaysDispensed")
    private String numDaysDispensed;
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitTypeCode() {
        return visitTypeCode;
    }

    public void setVisitTypeCode(String visitTypeCode) {
        this.visitTypeCode = visitTypeCode;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getNumDaysDispensed() {
        return numDaysDispensed;
    }

    public void setNumDaysDispensed(String numDaysDispensed) {
        this.numDaysDispensed = numDaysDispensed;
    }
}
