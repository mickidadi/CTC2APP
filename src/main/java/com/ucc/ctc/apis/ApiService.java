package com.ucc.ctc.apis;
import com.ucc.ctc.models.AdminHierarchyDivisionRootRemote;
import com.ucc.ctc.models.AdminHierarchyExtendedRootRemote;
import com.ucc.ctc.models.AdminHierarchyRemote;
import com.ucc.ctc.models.AdminHierarchyRootRemote;
import com.ucc.ctc.models.ClientRootRemote;
import com.ucc.ctc.models.UploadClientRemoteResponse;
import com.ucc.ctc.models.UserProfile;
import com.ucc.ctc.models.Users;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/afyaplus/index.php?r=site/profile")
    Call<Users> getUserById(@Query("userId") String userId);
    @GET("/afyaplus/index.php?r=site/getremoteuser")
    Call<UserProfile> getRemoteUser(@Query("username") String username, @Query( "password") String password);
    @GET("/afyaplus/index.php?r=site/getremoteclient")
    Call<ClientRootRemote> getRemoteClientDownload(@Query("username") String username, @Query( "password") String password);
    @GET("/afyaplus/index.php?r=site/getremoteadminhierarchy")
    Call<AdminHierarchyRootRemote> getRemoteAdminHierachyDownload();
    @GET("/afyaplus/index.php?r=site/getremoteadminhierarchyextended")
    Call<AdminHierarchyExtendedRootRemote> getRemoteAdminHierachyExtendedDownload();
    @GET("/afyaplus/index.php?r=site/getremoteadminhierarchydivision")
    Call<AdminHierarchyDivisionRootRemote> getRemoteAdminHierachyDivisionDownload();
    @POST("/afyaplus/index.php?r=site/upload-client")
    Call<UploadClientRemoteResponse> sendData(@Body JSONObject payload);
}