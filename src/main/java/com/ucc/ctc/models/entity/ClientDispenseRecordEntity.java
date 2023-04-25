package com.ucc.ctc.models.entity;

import androidx.room.Entity;

@Entity(tableName = "tblDispenseRecords")
public class ClientDispenseRecordEntity {
    private  int id;
    private String clientDispenseId;
    private String patientId;
    private String clientId;
    private String facilityId;

    public ClientDispenseRecordEntity(String clientDispenseId, String patientId, String clientId, String facilityId) {
        this.clientDispenseId = clientDispenseId;
        this.patientId = patientId;
        this.clientId = clientId;
        this.facilityId = facilityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientDispenseId() {
        return clientDispenseId;
    }

    public void setClientDispenseId(String clientDispenseId) {
        this.clientDispenseId = clientDispenseId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }
}
