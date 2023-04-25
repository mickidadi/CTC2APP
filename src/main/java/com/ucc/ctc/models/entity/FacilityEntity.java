package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblFacilities", indices = {@Index(value = {"FacilityId"}, unique = true)})
public class FacilityEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "FacilityId")
    private String facilityId;
    @ColumnInfo(name="CTCCode")
    private String CTCCode;
    @ColumnInfo(name="FacilityName")
    private String facilityName;
    @ColumnInfo(name="FacilityType")
    private String facilityType;
    @ColumnInfo(name="Region")
    private String region;
    @ColumnInfo(name = "Council")
    private String council;
    @ColumnInfo(name="Ward")
    private String ward;
    private String ctcWebUrl;
    public FacilityEntity(String facilityId, String CTCCode, String facilityName, String facilityType, String region, String council, String ward, String ctcWebUrl) {
        this.facilityId = facilityId;
        this.CTCCode = CTCCode;
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.region = region;
        this.council = council;
        this.ward = ward;
        this.ctcWebUrl=ctcWebUrl;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getCTCCode() {
        return CTCCode;
    }

    public void setCTCCode(String CTCCode) {
        this.CTCCode = CTCCode;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCouncil() {
        return council;
    }

    public void setCouncil(String council) {
        this.council = council;
    }

    public String getWard() {
        return ward;
    }
 public void setWard(String ward) {
        this.ward = ward;
    }

    public String getCtcWebUrl() {
        return ctcWebUrl;
    }

    public void setCtcWebUrl(String ctcWebUrl) {
        this.ctcWebUrl = ctcWebUrl;
    }

}
