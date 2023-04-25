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
    @SerializedName("VisitInfo")
    private List<ClientVisitRemote> visitInfoList=null;

    public List<ClientVisitRemote> getVisitInfoList() {
        return visitInfoList;
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
