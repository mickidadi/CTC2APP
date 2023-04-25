package com.ucc.ctc.apis;

import com.ucc.ctc.models.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {
   @GET("/afyaplus/index.php?r=site/profile")
   Call<Users> getUserById(@Query("userId") String userId);

}