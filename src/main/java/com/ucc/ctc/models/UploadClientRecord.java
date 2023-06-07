package com.ucc.ctc.models;

public class UploadClientRecord extends Record{
    // Define fields based on the payload JSON structure
    private String clientId;
    private String firstName;
    private String middleName;
    private String lastName;
    // ...and other fields as required

    // Constructor, getters, and setters

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
}
