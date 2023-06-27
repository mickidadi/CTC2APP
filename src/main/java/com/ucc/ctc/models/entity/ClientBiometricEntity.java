package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//tblClientBiometry

@Entity(tableName ="tblClientBiometry")
public class ClientBiometricEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="ClientId")
    private String clientId;
    @ColumnInfo(name="BiometricTemplate")
    private byte[] biometricTemplate;
    @ColumnInfo(name="EntryDate")
    private String entryDate;
    @ColumnInfo(name="RowVersion")
    private String rowVersion;
    @ColumnInfo(name="CentralReferenceCode")
    private String centralReferenceCode;
    @ColumnInfo(name="IsDuplicate")
    private String isDuplicate;
    @ColumnInfo(name="Quality")
    private int quality;
    @ColumnInfo(name="ActionTag")
    private int actionTag;
    @ColumnInfo(name="BiometricsRegOrigin")
    private int biometricsRegOrigin;
    @ColumnInfo(name="ChangeTrackStatus")
    private int changeTrackStatus;
    @ColumnInfo(name="RecGUID")
    private String recGUID;
    @ColumnInfo(name="BiometricSpecificationId")
    private String biometricSpecificationId;

    public ClientBiometricEntity(String clientId, byte[] biometricTemplate, String entryDate, String rowVersion, String centralReferenceCode, String isDuplicate, int quality, int actionTag, int biometricsRegOrigin, int changeTrackStatus, String recGUID, String biometricSpecificationId) {
        this.clientId = clientId;
        this.biometricTemplate = biometricTemplate;
        this.entryDate = entryDate;
        this.rowVersion = rowVersion;
        this.centralReferenceCode = centralReferenceCode;
        this.isDuplicate = isDuplicate;
        this.quality = quality;
        this.actionTag = actionTag;
        this.biometricsRegOrigin = biometricsRegOrigin;
        this.changeTrackStatus = changeTrackStatus;
        this.recGUID = recGUID;
        this.biometricSpecificationId = biometricSpecificationId;
    }


    public byte[] getBiometricTemplate() {
        return biometricTemplate;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setBiometricTemplate(byte[] biometricTemplate) {
        this.biometricTemplate = biometricTemplate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(String rowVersion) {
        this.rowVersion = rowVersion;
    }

    public String getCentralReferenceCode() {
        return centralReferenceCode;
    }

    public void setCentralReferenceCode(String centralReferenceCode) {
        this.centralReferenceCode = centralReferenceCode;
    }

    public String getIsDuplicate() {
        return isDuplicate;
    }

    public void setIsDuplicate(String isDuplicate) {
        this.isDuplicate = isDuplicate;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getActionTag() {
        return actionTag;
    }

    public void setActionTag(int actionTag) {
        this.actionTag = actionTag;
    }

    public int getBiometricsRegOrigin() {
        return biometricsRegOrigin;
    }

    public void setBiometricsRegOrigin(int biometricsRegOrigin) {
        this.biometricsRegOrigin = biometricsRegOrigin;
    }

    public int getChangeTrackStatus() {
        return changeTrackStatus;
    }

    public void setChangeTrackStatus(int changeTrackStatus) {
        this.changeTrackStatus = changeTrackStatus;
    }

    public String getRecGUID() {
        return recGUID;
    }

    public void setRecGUID(String recGUID) {
        this.recGUID = recGUID;
    }

    public String getBiometricSpecificationId() {
        return biometricSpecificationId;
    }

    public void setBiometricSpecificationId(String biometricSpecificationId) {
        this.biometricSpecificationId = biometricSpecificationId;
    }
}
