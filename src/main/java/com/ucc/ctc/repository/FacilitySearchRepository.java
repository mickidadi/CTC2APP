package com.ucc.ctc.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.apis.FacilitySearchServiceApi;
import com.ucc.ctc.models.FacilitySearchResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FacilitySearchRepository {
    private static FacilitySearchRepository instance;
    private FacilitySearchServiceApi apiServices;

    private FacilitySearchRepository() {
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
        apiServices = retrofit.create( FacilitySearchServiceApi.class);
    }

    public static synchronized FacilitySearchRepository getInstance() {
        if (instance == null) {
            instance = new FacilitySearchRepository();
        }
        return instance;
    }
    public LiveData<FacilitySearchResponse> getFacilityById(String hfrId) {
        MutableLiveData<FacilitySearchResponse> data = new MutableLiveData<>();
        apiServices.getFacility(hfrId).enqueue(new Callback<FacilitySearchResponse>() {
            @Override
            public void onResponse(Call<FacilitySearchResponse> call, Response<FacilitySearchResponse> response) {
                Log.v("Test Mickidadi", "Mickidadi Responds" +response.body());
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FacilitySearchResponse> call, Throwable t) {
                data.setValue(null);
                Log.v("Failed Mickidadi ", " View Repository " +t);
            }
        });
        Log.v("All Mickidadi", "View Repository" +hfrId);
        return data;
    }
}
