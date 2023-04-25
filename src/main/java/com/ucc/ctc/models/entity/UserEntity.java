package com.ucc.ctc.models.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblUsers")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="FullName")
    private String fullName;
    @ColumnInfo(name="LoginName")
    private String loginName;
    @ColumnInfo(name="Password")
    private String password;
    @ColumnInfo(name="FacilityId")
    private String facilityId;

    public UserEntity(String fullName, String loginName, String password, String facilityId) {
        this.fullName = fullName;
        this.loginName = loginName;
        this.password = password;
        this.facilityId = facilityId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

}