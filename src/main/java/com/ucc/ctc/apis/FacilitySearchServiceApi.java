package com.ucc.ctc.apis;

import com.ucc.ctc.models.FacilitySearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FacilitySearchServiceApi {
    @GET("/afyaplus/index.php?r=site/facility")
    Call<FacilitySearchResponse> getFacility(
            @Query("hfrId") String query
    );
}
