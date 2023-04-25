package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "admin_hierarchy_extended")
public class AdminHierarchyExtendedEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "node_id")
    private String nodeId;
    @ColumnInfo(name="country")
    private String country;
    @ColumnInfo(name="zone")
    private String zone;
    @ColumnInfo(name="region")
    private String region;
    @ColumnInfo(name="district")
    private String district;
    @ColumnInfo(name="council")
    private String council;
    @ColumnInfo(name="ward")
    private String ward;
    @ColumnInfo(name="village_mtaa")
    private String villageMtaa;
    @ColumnInfo(name="leader_name")
    private String leaderName;
    @ColumnInfo(name="ward_node_id")
    private String wardNodeId;
    @ColumnInfo(name="EntryTimeStamp")
    private String entryTimeStamp;
    @ColumnInfo(name = "RowVersion")
    private String rowVersion;
    @ColumnInfo(name="ChangeTrackStatus")
    private String changeTrackStatus;
    @ColumnInfo(name="RecGUID")
    private String recGUID;
    @ColumnInfo(name="ExportSessionId")
    private String exportSessionId;

    public AdminHierarchyExtendedEntity(String nodeId, String country, String zone, String region, String district, String council, String ward, String villageMtaa, String leaderName, String wardNodeId, String entryTimeStamp, String rowVersion, String changeTrackStatus, String recGUID, String exportSessionId) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
