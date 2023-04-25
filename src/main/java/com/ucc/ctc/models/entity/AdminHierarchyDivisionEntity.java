package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "admin_hierarchy_division")
public class AdminHierarchyDivisionEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="RegionCode")
    private String regionCode;
    @ColumnInfo(name="DistrictCode")
    private String districtCode;
    @ColumnInfo(name="WardName")
    private String wardName;
    @ColumnInfo(name="NBSWardCode")
    private String NBSWardCode;

    public AdminHierarchyDivisionEntity(String regionCode, String districtCode, String wardName, String NBSWardCode) {
        this.regionCode = regionCode;
        this.districtCode = districtCode;
        this.wardName = wardName;
        this.NBSWardCode = NBSWardCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getNBSWardCode() {
        return NBSWardCode;
    }

    public void setNBSWardCode(String NBSWardCode) {
        this.NBSWardCode = NBSWardCode;
    }
}
