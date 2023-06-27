package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="tblClients")
public class ClientEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="ClientId")
    private String clientId;
    @ColumnInfo(name="FirstName")
    private String firstName;
    @ColumnInfo(name="MiddleName")
    private String middleName;
    @ColumnInfo(name="LastName")
    private String lastName;
    @ColumnInfo(name="Sex")
    private String sex;
    @ColumnInfo(name="DateOfBirth")
    private String dateOfBirth;
    @ColumnInfo(name="ReferenceCode")
    private String referenceCode;
    @ColumnInfo(name="FacilityId")
    private String facilityId;
    @ColumnInfo(name="FingerPrintConcept")

    private String fingerPrintConcept;
    public ClientEntity(String clientId, String firstName, String middleName, String lastName, String sex, String dateOfBirth, String referenceCode,String facilityId, String fingerPrintConcept) {

        this.clientId = clientId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.referenceCode = referenceCode;
        this.facilityId = facilityId;
        this.fingerPrintConcept=fingerPrintConcept;
    }
    public String getFingerPrintConcept() {
        return fingerPrintConcept;
    }
    public void setFingerPrintConcept(String fingerPrintConcept) {
        this.fingerPrintConcept = fingerPrintConcept;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }
public String getFullName(){
        return firstName+" "+middleName+" "+lastName;
}

}
