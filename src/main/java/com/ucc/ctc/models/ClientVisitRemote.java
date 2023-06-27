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

    public String getVisitTypeCode() {
        return visitTypeCode;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getNumDaysDispensed() {
        return numDaysDispensed;
    }
}
