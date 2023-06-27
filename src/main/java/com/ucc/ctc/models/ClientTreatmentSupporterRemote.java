package com.ucc.ctc.models;

import com.google.gson.annotations.SerializedName;

public class ClientTreatmentSupporterRemote {
    @SerializedName("Helper")
    private String helper;
    @SerializedName("HelperContact")
    private String helperContact;
    @SerializedName("ClientCommunityGroup")
    private String clientCommunityGroup;
    @SerializedName("DateJoined")
    private String dateJoined;
    @SerializedName("SMSConsent")
    private String SMSConsent;

    public ClientTreatmentSupporterRemote(String helper, String helperContact, String clientCommunityGroup, String dateJoined, String SMSConsent) {
        this.helper = helper;
        this.helperContact = helperContact;
        this.clientCommunityGroup = clientCommunityGroup;
        this.dateJoined = dateJoined;
        this.SMSConsent = SMSConsent;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    public String getHelperContact() {
        return helperContact;
    }

    public void setHelperContact(String helperContact) {
        this.helperContact = helperContact;
    }

    public String getClientCommunityGroup() {
        return clientCommunityGroup;
    }

    public void setClientCommunityGroup(String clientCommunityGroup) {
        this.clientCommunityGroup = clientCommunityGroup;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getSMSConsent() {
        return SMSConsent;
    }

    public void setSMSConsent(String SMSConsent) {
        this.SMSConsent = SMSConsent;
    }
}
