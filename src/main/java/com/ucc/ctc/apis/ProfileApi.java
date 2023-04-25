package com.ucc.ctc.apis;

import com.ucc.ctc.models.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileApi {
    @GET("/afyaplus/index.php?r=site/profile")
    Call<UserProfile> getProfile();
}