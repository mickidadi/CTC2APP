package com.ucc.ctc.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.apis.ApiService;
import com.ucc.ctc.apis.FacilitySearchServiceApi;
import com.ucc.ctc.models.FacilitySearchResponse;
import com.ucc.ctc.models.UserProfile;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRemoteRepository {
    private static UserRemoteRepository instance;
    private ApiService apiServices;

    private UserRemoteRepository() {

        // Create a new instance of HttpLoggingInterceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

// Add the loggingInterceptor to your OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("https://technolemon.com/")
                //.baseUrl("http://10.45.1.174/")
                .addConverterFactory( GsonConverterFactory.create())
                .client(client)
                .build();
        apiServices = retrofit.create(ApiService.class);
    }

    public static synchronized UserRemoteRepository getInstance() {
        if (instance == null) {
            instance = new UserRemoteRepository();
        }
        return instance;
    }
  public LiveData<UserProfile> getUserRemote1(String username,String password) {
        MutableLiveData<UserProfile> data = new MutableLiveData<>();

        apiServices.getRemoteUser(username,password).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable ts) {
                data.setValue(null);
            }
        });
        return data;
    }
    public LiveData<UserProfile> getUserRemote(String username,String password,String url) {

        MutableLiveData<UserProfile> data = new MutableLiveData<>();
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
        apiServices = retrofit.create( ApiService.class);

        apiServices.getRemoteUser(username,password).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                Log.v("Test ", "Respond " +response.body());
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                data.setValue(null);
                Log.v("Failed Login ", " View Repository " );
            }
        });
        Log.v("Login Data", "View Repository");
        return data;
    }
}
