package com.ucc.ctc.models;

import com.google.gson.annotations.SerializedName;

public class ClientBiometricRemote {
    @SerializedName("ClientBiometryId")
    private String clientBiometryId;
    @SerializedName("BiometricSpecificationId")
    private String biometricSpecificationId;
    @SerializedName("BiometricTemplate")
    private byte[] biometricTemplate;
    @SerializedName("EntryDate")
    private String entryDate;
    @SerializedName("RowVersion")
    private String rowVersion;

    @SerializedName("ClientId")
    private String clientId;
    @SerializedName("SessionId")
    private String sessionId;
    @SerializedName("CentralReferenceCode")
    private String centralReferenceCode;

    @SerializedName("isDuplicate")
    private String isDuplicate;
    @SerializedName("Quality")
    private int quality;

    @SerializedName("ActionTag")
    private int actionTag;
    @SerializedName("BiometricsRegOrigin")
    private int biometricsRegOrigin;

    @SerializedName("EntryTimeStamp")
    private String entryTimeStamp;
    @SerializedName("RecGUID")
    private String RecGUID;

    @SerializedName("ChangeTrackStatus")
    private int changeTrackStatus;
    @SerializedName("ExportSessionId")
    private String exportSessionId;

    public String getClientBiometryId() {
        return clientBiometryId;
    }

    public String getBiometricSpecificationId() {
        return biometricSpecificationId;
    }

    public byte[] getBiometricTemplate() {
        return biometricTemplate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getRowVersion() {
        return rowVersion;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getCentralReferenceCode() {
        return centralReferenceCode;
    }

    public String getIsDuplicate() {
        return isDuplicate;
    }

    public int getQuality() {
        return quality;
    }

    public int getActionTag() {
        return actionTag;
    }

    public int getBiometricsRegOrigin() {
        return biometricsRegOrigin;
    }

    public String getEntryTimeStamp() {
        return entryTimeStamp;
    }

    public String getRecGUID() {
        return RecGUID;
    }

    public int getChangeTrackStatus() {
        return changeTrackStatus;
    }

    public String getExportSessionId() {
        return exportSessionId;
    }
}
