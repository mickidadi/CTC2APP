package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*@Entity(tableName = "tblPatients"
        /*foreignKeys = {
                @ForeignKey(entity = ClientEntity.class,
                        parentColumns = "ClientId",
                        childColumns = "ClientId",
                        onDelete = CASCADE, onUpdate = CASCADE)
        })  , indices = {@Index(value = {"FacilityId"}, unique = true)}*/
@Entity(tableName = "tblPatients")
public class PatientEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="PatientId")
    private String patientId;
    @ColumnInfo(name="ClientId")
    private String clientId;
    @ColumnInfo(name="FacilityId")
    private String facilityId;


    public PatientEntity(String patientId, String clientId, String facilityId) {
        this.patientId = patientId;
        this.clientId = clientId;
        this.facilityId = facilityId;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
