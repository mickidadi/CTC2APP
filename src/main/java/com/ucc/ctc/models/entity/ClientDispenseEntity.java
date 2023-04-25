package com.ucc.ctc.models.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblDispense")
public class ClientDispenseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String clientId;
    private String facilityId;

    public ClientDispenseEntity(String clientId, String facilityId) {
        this.clientId = clientId;
        this.facilityId = facilityId;
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

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }
}
