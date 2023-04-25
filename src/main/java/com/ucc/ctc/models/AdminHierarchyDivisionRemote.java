package com.ucc.ctc.models;

import com.google.gson.annotations.SerializedName;

public class AdminHierarchyDivisionRemote {
    @SerializedName("RegionCode")
    private String regionCode;
    @SerializedName("DistrictCode")
    private String districtCode;
    @SerializedName("WardName")
    private String wardName;
    @SerializedName("NBSWardCode")
    private String NBSWardCode;

    public AdminHierarchyDivisionRemote(String regionCode, String districtCode, String wardName, String NBSWardCode) {
        this.regionCode = regionCode;
        this.districtCode = districtCode;
        this.wardName = wardName;
        this.NBSWardCode = NBSWardCode;
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
