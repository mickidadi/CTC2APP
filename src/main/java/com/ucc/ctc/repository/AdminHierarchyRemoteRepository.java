
 package com.ucc.ctc.repository;
 import androidx.lifecycle.LiveData;
 import androidx.lifecycle.MutableLiveData;
 import com.ucc.ctc.apis.ApiService;
 import com.ucc.ctc.models.AdminHierarchyRemote;
 import com.ucc.ctc.models.AdminHierarchyRootRemote;

 import okhttp3.OkHttpClient;
 import okhttp3.logging.HttpLoggingInterceptor;
 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;
 import retrofit2.Retrofit;
 import retrofit2.converter.gson.GsonConverterFactory;

 public class AdminHierarchyRemoteRepository {
     private static AdminHierarchyRemoteRepository instance;
     private ApiService apiServices;

     private AdminHierarchyRemoteRepository() {

         // Create a new instance of HttpLoggingInterceptor
         HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

 // Add the loggingInterceptor to your OkHttpClient
         OkHttpClient client = new OkHttpClient.Builder()
                 .addInterceptor(loggingInterceptor)
                 .build();
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("https://technolemon.com/")
                 .addConverterFactory( GsonConverterFactory.create())
                 .client(client)
                 .build();
         apiServices = retrofit.create(ApiService.class);
     }

     public static synchronized AdminHierarchyRemoteRepository getInstance() {
         if (instance == null) {
             instance = new AdminHierarchyRemoteRepository();
         }
         return instance;
     }
     public LiveData<AdminHierarchyRootRemote> getAdminHierarchyRemote() {
         MutableLiveData<AdminHierarchyRootRemote> data = new MutableLiveData<>();
         apiServices.getRemoteAdminHierachyDownload().enqueue(new Callback<AdminHierarchyRootRemote>() {
             @Override
             public void onResponse(Call<AdminHierarchyRootRemote> call, Response<AdminHierarchyRootRemote> response) {
                 if (response.isSuccessful()) {
                     data.setValue(response.body());
                 }
             }
             @Override
             public void onFailure(Call<AdminHierarchyRootRemote> call, Throwable ts) {
                 data.setValue(null);
             }
         });
         return data;
     }
 }
