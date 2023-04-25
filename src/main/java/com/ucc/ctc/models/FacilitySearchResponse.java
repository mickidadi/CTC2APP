package com.ucc.ctc.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FacilitySearchResponse {

    @SerializedName("FacilityId")
    @Expose
    private String FacilityId;

    @SerializedName("CTCCode")
    @Expose
    private String CTCCode;
    @SerializedName( "FacilityType" )
    @Expose
    private String FacilityType;
    @SerializedName( "FacilityName" )
    @Expose
    private String FacilityName;
    @SerializedName( "Region" )
    @Expose
    private String Region;
    @SerializedName( "Council" )
    @Expose
    private String Council;
    @SerializedName( "Ward" )
    @Expose
    private String ward;
    @SerializedName( "code" )
    @Expose
    private String code;
    @SerializedName( "ctcWebUrl" )
    @Expose
    private String ctcWebUrl;

    public String getCtcWebUrl() {
        return ctcWebUrl;
    }
    public String getFacilityId() {
        return FacilityId;
    }

    public String getCTCCode() {
        return CTCCode;
    }

    public String getFacilityType() {
        return FacilityType;
    }
    public String getFacilityName() {
        return FacilityName;
    }
    public String getRegion() {
        return Region;
    }

    public String getCouncil() {
        return Council;
    }

    public String getWard() {
        return ward;
    }

    public String getCode() {
        return code;
    }
}