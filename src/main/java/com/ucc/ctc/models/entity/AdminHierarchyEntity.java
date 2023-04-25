package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "admin_hierarchy")
public class AdminHierarchyEntity {
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

    public AdminHierarchyEntity(String nodeId, String country, String zone, String region, String district, String council, String ward, String villageMtaa) {
        this.nodeId = nodeId;
        this.country = country;
        this.zone = zone;
        this.region = region;
        this.district = district;
        this.council = council;
        this.ward = ward;
        this.villageMtaa = villageMtaa;
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
