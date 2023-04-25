package com.ucc.ctc.models.entity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ucc.ctc.utils.ClientPhysicAddressDialog;
@Entity(tableName ="tblClientPhysicalAddress")
public class ClientPhysicalAddressEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="ClientId")
    private String clientId;
    @ColumnInfo(name="council")
    private String council;
    @ColumnInfo(name="division")
    private String division;
    @ColumnInfo(name="ward")
    private String ward;
    @ColumnInfo(name="village")
    private String village;
    @ColumnInfo(name="villageChairperson")
    private String villageChairperson;
    @ColumnInfo(name="cellLeader")
    private String cellLeader;
    @ColumnInfo(name="householdHead")
    private String householdHead;
    @ColumnInfo(name="clientPhone")
    private String clientPhone;
    @ColumnInfo(name="clientcontact")
    private String clientcontact;
    @ColumnInfo(name="householdcontact")
    private String householdcontact;
    @ColumnInfo(name="SMSConsent")
    private String SMSConsent;

    public ClientPhysicalAddressEntity(String clientId, String council, String division, String ward, String village, String villageChairperson, String cellLeader, String householdHead, String clientPhone, String clientcontact, String householdcontact, String SMSConsent) {
        this.clientId = clientId;
        this.council = council;
        this.division = division;
        this.ward = ward;
        this.village = village;
        this.villageChairperson = villageChairperson;
        this.cellLeader = cellLeader;
        this.householdHead = householdHead;
        this.clientPhone = clientPhone;
        this.clientcontact = clientcontact;
        this.householdcontact = householdcontact;
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

    public String getCouncil() {
        return council;
    }

    public void setCouncil(String council) {
        this.council = council;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getVillageChairperson() {
        return villageChairperson;
    }

    public void setVillageChairperson(String villageChairperson) {
        this.villageChairperson = villageChairperson;
    }

    public String getCellLeader() {
        return cellLeader;
    }

    public void setCellLeader(String cellLeader) {
        this.cellLeader = cellLeader;
    }

    public String getHouseholdHead() {
        return householdHead;
    }

    public void setHouseholdHead(String householdHead) {
        this.householdHead = householdHead;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientcontact() {
        return clientcontact;
    }

    public void setClientcontact(String clientcontact) {
        this.clientcontact = clientcontact;
    }

    public String getHouseholdcontact() {
        return householdcontact;
    }

    public void setHouseholdcontact(String householdcontact) {
        this.householdcontact = householdcontact;
    }

    public String getSMSConsent() {
        return SMSConsent;
    }

    public void setSMSConsent(String SMSConsent) {
        this.SMSConsent = SMSConsent;
    }
}
