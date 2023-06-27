
 package com.ucc.ctc.repository;

 import android.util.Log;

 import androidx.lifecycle.LiveData;
 import androidx.lifecycle.MutableLiveData;

 import com.ucc.ctc.apis.ApiService;
 import com.ucc.ctc.models.AdminHierarchyExtendedRootRemote;
 import com.ucc.ctc.models.AdminHierarchyRootRemote;

 import okhttp3.OkHttpClient;
 import okhttp3.logging.HttpLoggingInterceptor;
 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;
 import retrofit2.Retrofit;
 import retrofit2.converter.gson.GsonConverterFactory;

 public class AdminHierarchyExtendedRemoteRepository {
     private static AdminHierarchyExtendedRemoteRepository instance;
     private ApiService apiServices;

     private AdminHierarchyExtendedRemoteRepository() {

         // Create a new instance of HttpLoggingInterceptor
         HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

 // Add the loggingInterceptor to your OkHttpClient
         OkHttpClient client = new OkHttpClient.Builder()
                 .addInterceptor(loggingInterceptor)
                 .build();
         Retrofit retrofit = new Retrofit.Builder()
                 //.baseUrl("https://technolemon.com/")
                 .baseUrl("http://10.45.1.174/")
                 .addConverterFactory( GsonConverterFactory.create())
                 .client(client)
                 .build();
         apiServices = retrofit.create(ApiService.class);
     }

     public static synchronized AdminHierarchyExtendedRemoteRepository getInstance() {
         if (instance == null) {
             instance = new AdminHierarchyExtendedRemoteRepository();
         }
         return instance;
     }
     public LiveData<AdminHierarchyExtendedRootRemote> getAdminHierarchyRemote11() {
         MutableLiveData<AdminHierarchyExtendedRootRemote> data = new MutableLiveData<>();
         apiServices.getRemoteAdminHierachyExtendedDownload().enqueue(new Callback<AdminHierarchyExtendedRootRemote>() {
             @Override
             public void onResponse(Call<AdminHierarchyExtendedRootRemote> call, Response<AdminHierarchyExtendedRootRemote> response) {
                 if (response.isSuccessful()) {
                     data.setValue(response.body());
                 }
             }
             @Override
             public void onFailure(Call<AdminHierarchyExtendedRootRemote> call, Throwable ts) {
                 data.setValue(null);
             }
         });
         return data;
     }
     public LiveData<AdminHierarchyExtendedRootRemote> getAdminHierarchyRemote(String url) {

         MutableLiveData<AdminHierarchyExtendedRootRemote> data = new MutableLiveData<>();
         // Create a new instance of HttpLoggingInterceptor
         HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

         // Add the loggingInterceptor to your OkHttpClient
         OkHttpClient client = new OkHttpClient.Builder()
                 .addInterceptor(loggingInterceptor)
                 .build();

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("http://"+url)
                 .addConverterFactory( GsonConverterFactory.create())
                 .client(client)
                 .build();
         apiServices = retrofit.create(ApiService.class);
         apiServices.getRemoteAdminHierachyExtendedDownload ().enqueue(new Callback<AdminHierarchyExtendedRootRemote>() {
             @Override
             public void onResponse(Call<AdminHierarchyExtendedRootRemote> call, Response<AdminHierarchyExtendedRootRemote> response) {
                 Log.v("Test ", "Respond " +response.body());
                 if (response.body() != null) {
                     data.setValue(response.body());
                 }
             }
             @Override
             public void onFailure(Call<AdminHierarchyExtendedRootRemote> call, Throwable t) {
                 data.setValue(null);
                 Log.v("Failed Login ", " View Repository " );
             }
         });
         Log.v("Login Data", "View Repository");
         return data;
     }
 }
