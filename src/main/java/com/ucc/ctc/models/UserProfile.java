package com.ucc.ctc.models;

import com.google.gson.annotations.SerializedName;

public class UserProfile {
    @SerializedName("LoginName")
    private String username;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("FacilityId" )
    private String facilityId;
    @SerializedName("Password")
    private String password;
    @SerializedName("Code")
    private int code;
    public UserProfile(String username, String fullName, String facilityId, String password,int code) {
        this.username = username;
        this.fullName = fullName;
        this.facilityId = facilityId;
        this.password = password;
        this.code=code;
    }
    public int getCode() {
        return code;
    }
    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public String getPassword() {
        return password;
    }

}
