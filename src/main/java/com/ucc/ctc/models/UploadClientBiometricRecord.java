package com.ucc.ctc.models;
public class UploadClientBiometricRecord extends Record {
    // Define fields based on the payload JSON structure
    private int ClientBiometryId;
    private String clientId;
    private byte[] biometricTemplate;
    private int BiometricSpecificationId;
    // ...and other fields as required
    // Constructor, getters, and setters

    public int getClientBiometryId() {
        return ClientBiometryId;
    }

    public void setClientBiometryId(int clientBiometryId) {
        ClientBiometryId = clientBiometryId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public byte[] getBiometricTemplate() {
        return biometricTemplate;
    }

    public void setBiometricTemplate(byte[] biometricTemplate) {
        this.biometricTemplate = biometricTemplate;
    }

    public int getBiometricSpecificationId() {
        return BiometricSpecificationId;
    }

    public void setBiometricSpecificationId(int biometricSpecificationId) {
        BiometricSpecificationId = biometricSpecificationId;
    }

}
