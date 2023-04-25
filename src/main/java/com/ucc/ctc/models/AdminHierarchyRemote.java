package com.ucc.ctc.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminHierarchyRemote {
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("country")
    private String country;
    @SerializedName("zone")
    private String zone;
    @SerializedName("region")
    private String region;
    @SerializedName("district")
    private String district;
    @SerializedName("council")
    private String council;
    @SerializedName("ward")
    private String ward;
    @SerializedName("village_mtaa")
    private String villageMtaa;

    public AdminHierarchyRemote(String nodeId, String country, String zone, String region, String district, String council, String ward, String villageMtaa) {
        this.nodeId = nodeId;
        this.country = country;
        this.zone = zone;
        this.region = region;
        this.district = district;
        this.council = council;
        this.ward = ward;
        this.villageMtaa = villageMtaa;
    }

    public String getNodeId() {
        return nodeId;
    }
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getZone() {
        return zone;
    }
    public void setZone(String zone) {
        this.zone = zone;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
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
  public String getVillageMtaa() {
        return villageMtaa;
    }
  public void setVillageMtaa(String villageMtaa) {
        this.villageMtaa = villageMtaa;
    }
}
