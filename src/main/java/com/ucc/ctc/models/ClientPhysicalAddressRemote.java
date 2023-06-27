package com.ucc.ctc.models;

import com.google.gson.annotations.SerializedName;

public class ClientPhysicalAddressRemote {
    @SerializedName("CouncilName")
    private String councilName;
    @SerializedName("DivisionName")
    private String divisionName;
    @SerializedName("WardName")
    private String wardName;
    @SerializedName("VillageMtaa")
    private String villageMtaa;
    @SerializedName("TenCellLeaderName")
    private String tenCellLeaderName;

    @SerializedName("TenCellLeaderNameContact")
    private String tenCellLeaderNameContact;
    @SerializedName("HouseholdHead")
    private String householdHead;
    @SerializedName("HouseholdHeadContact")
    private String householdHeadContact;

    @SerializedName("Contact")
    private String contact;
    @SerializedName("SMSConsent")
    private String SMSConsent;

    public String getCouncilName() {
        return councilName;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public String getWardName() {
        return wardName;
    }

    public String getVillageMtaa() {
        return villageMtaa;
    }

    public String getTenCellLeaderName() {
        return tenCellLeaderName;
    }

    public String getTenCellLeaderNameContact() {
        return tenCellLeaderNameContact;
    }

    public String getHouseholdHead() {
        return householdHead;
    }

    public String getHouseholdHeadContact() {
        return householdHeadContact;
    }

    public String getContact() {
        return contact;
    }

    public String getSMSConsent() {
        return SMSConsent;
    }
}
