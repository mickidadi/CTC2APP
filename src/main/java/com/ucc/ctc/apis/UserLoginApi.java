package com.ucc.ctc.apis;

import com.ucc.ctc.models.entity.UserEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserLoginApi {
    @GET("/afyaplus/index.php?r=site/profile")
    Call<UserEntity> getUserById(@Query("userId") String userId);

}
