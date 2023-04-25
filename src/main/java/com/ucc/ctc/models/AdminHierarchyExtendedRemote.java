package com.ucc.ctc.models;

import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class AdminHierarchyExtendedRemote {
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
    @SerializedName("leader_name")
    private String leaderName;
    @SerializedName("ward_node_id")
    private String wardNodeId;
    @SerializedName("EntryTimeStamp")
    private String entryTimeStamp;
    @SerializedName("RowVersion")
    private String rowVersion;
    @SerializedName("ChangeTrackStatus")
    private String changeTrackStatus;
    @SerializedName("RecGUID")
    private String recGUID;
    @SerializedName("ExportSessionId")
    private String exportSessionId;

    public AdminHierarchyExtendedRemote(String nodeId, String country, String zone, String region, String district, String council, String ward, String villageMtaa, String leaderName, String wardNodeId, String entryTimeStamp, String rowVersion, String changeTrackStatus, String recGUID, String exportSessionId) {
        this.nodeId = nodeId;
        this.country = country;
        this.zone = zone;
        this.region = region;
        this.district = district;
        this.council = council;
        this.ward = ward;
        this.villageMtaa = villageMtaa;
        this.leaderName = leaderName;
        this.wardNodeId = wardNodeId;
        this.entryTimeStamp = entryTimeStamp;
        this.rowVersion = rowVersion;
        this.changeTrackStatus = changeTrackStatus;
        this.recGUID = recGUID;
        this.exportSessionId = exportSessionId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getWardNodeId() {
        return wardNodeId;
    }

    public void setWardNodeId(String wardNodeId) {
        this.wardNodeId = wardNodeId;
    }

    public String getEntryTimeStamp() {
        return entryTimeStamp;
    }

    public void setEntryTimeStamp(String entryTimeStamp) {
        this.entryTimeStamp = entryTimeStamp;
    }

    public String getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(String rowVersion) {
        this.rowVersion = rowVersion;
    }

    public String getChangeTrackStatus() {
        return changeTrackStatus;
    }

    public void setChangeTrackStatus(String changeTrackStatus) {
        this.changeTrackStatus = changeTrackStatus;
    }

    public String getRecGUID() {
        return recGUID;
    }

    public void setRecGUID(String recGUID) {
        this.recGUID = recGUID;
    }

    public String getExportSessionId() {
        return exportSessionId;
    }

    public void setExportSessionId(String exportSessionId) {
        this.exportSessionId = exportSessionId;
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
