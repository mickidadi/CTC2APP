
 package com.ucc.ctc.repository;

 import androidx.lifecycle.LiveData;
 import androidx.lifecycle.MutableLiveData;

 import com.ucc.ctc.apis.ApiService;
 import com.ucc.ctc.models.AdminHierarchyDivisionRootRemote;
 import com.ucc.ctc.models.AdminHierarchyExtendedRootRemote;

 import okhttp3.OkHttpClient;
 import okhttp3.logging.HttpLoggingInterceptor;
 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;
 import retrofit2.Retrofit;
 import retrofit2.converter.gson.GsonConverterFactory;

 public class AdminHierarchyDivisionRemoteRepository {
     private static AdminHierarchyDivisionRemoteRepository instance;
     private ApiService apiServices;

     private AdminHierarchyDivisionRemoteRepository() {

         // Create a new instance of HttpLoggingInterceptor
         HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

 // Add the loggingInterceptor to your OkHttpClient
         OkHttpClient client = new OkHttpClient.Builder()
                 .addInterceptor(loggingInterceptor)
                 .build();
         Retrofit retrofit = new Retrofit.Builder()
                 //.baseUrl("https://technolemon.com/")
                 .baseUrl("http://10.45.1.102/")
                 .addConverterFactory( GsonConverterFactory.create())
                 .client(client)
                 .build();
         apiServices = retrofit.create(ApiService.class);
     }

     public static synchronized AdminHierarchyDivisionRemoteRepository getInstance() {
         if (instance == null) {
             instance = new AdminHierarchyDivisionRemoteRepository();
         }
         return instance;
     }
     public LiveData<AdminHierarchyDivisionRootRemote> getAdminHierarchyRemote() {
         MutableLiveData<AdminHierarchyDivisionRootRemote> data = new MutableLiveData<>();

         apiServices.getRemoteAdminHierachyDivisionDownload().enqueue(new Callback<AdminHierarchyDivisionRootRemote>() {
             @Override
             public void onResponse(Call<AdminHierarchyDivisionRootRemote> call, Response<AdminHierarchyDivisionRootRemote> response) {
                 if (response.isSuccessful()) {
                     data.setValue(response.body());
                 }
             }
             @Override
             public void onFailure(Call<AdminHierarchyDivisionRootRemote> call, Throwable ts) {
                 data.setValue(null);
             }
         });
         return data;
     }
 }
