package com.ucc.ctc.models.entity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="tblClientTreatmentSupporter")
public class ClientTreatmentSupporterEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="ClientId")
    private String clientId;
    @ColumnInfo(name="treatmentSupporterName")
    private String treatmentSupporterName;
    @ColumnInfo(name="treatmentSupporterPhone")
    private String treatmentSupporterPhone;
    @ColumnInfo(name="clientCommunityGroup")
    private String clientCommunityGroup;
    @ColumnInfo(name="dateJoined")
    private String dateJoined;
    @ColumnInfo(name="SMSConsent")
    private String SMSConsent;

    public ClientTreatmentSupporterEntity(String clientId, String treatmentSupporterName, String treatmentSupporterPhone, String clientCommunityGroup, String dateJoined, String SMSConsent) {
        this.clientId = clientId;
        this.treatmentSupporterName = treatmentSupporterName;
        this.treatmentSupporterPhone = treatmentSupporterPhone;
        this.clientCommunityGroup = clientCommunityGroup;
        this.dateJoined = dateJoined;
        this.SMSConsent = SMSConsent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTreatmentSupporterName() {
        return treatmentSupporterName;
    }

    public void setTreatmentSupporterName(String treatmentSupporterName) {
        this.treatmentSupporterName = treatmentSupporterName;
    }

    public String getTreatmentSupporterPhone() {
        return treatmentSupporterPhone;
    }

    public void setTreatmentSupporterPhone(String treatmentSupporterPhone) {
        this.treatmentSupporterPhone = treatmentSupporterPhone;
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
