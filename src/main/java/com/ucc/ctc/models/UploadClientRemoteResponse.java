package com.ucc.ctc.models;

import com.google.gson.annotations.SerializedName;

public class UploadClientRemoteResponse {

    @SerializedName("FacilityId" )
    private String facilityId;
    @SerializedName("Code")
    private int code;

    public int getCode() {
        return code;
    }

    public String getFacilityId() {
        return facilityId;
    }


}
