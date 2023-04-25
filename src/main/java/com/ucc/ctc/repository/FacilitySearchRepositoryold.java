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
import retrofit2.converter.gson.GsonConverterFactory;

public class FacilitySearchRepositoryold {
    private static final String FACILITY_SEARCH_SERVICE_BASE_URL = "https://technolemon.com/";
    private static FacilitySearchRepositoryold instance;
    private FacilitySearchServiceApi facilitySearchService;
    private MutableLiveData<FacilitySearchResponse> facilitySearchResponseLiveData;

    public static synchronized FacilitySearchRepositoryold getInstance() {
        if (instance == null) {
            instance = new FacilitySearchRepositoryold();
        }
        return instance;
    }
    public FacilitySearchRepositoryold() {
        facilitySearchResponseLiveData= new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        facilitySearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(FACILITY_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create( FacilitySearchServiceApi.class);

    }
    public void searchFacility(String hfrId) {
        facilitySearchService.getFacility(hfrId)
                .enqueue(new Callback<FacilitySearchResponse>() {
                    @Override
                    public void onResponse(Call<FacilitySearchResponse> call, Response<FacilitySearchResponse> response) {
                        if (response.body() != null) {
                            facilitySearchResponseLiveData.postValue(response.body());
                            Log.v("tag","Mickidadi Successfully"  + response.body());

                        }else{
                            Log.v("tag","Mickidadi failed"  + response.body().getCode());
                        }
                    }

                    @Override
                    public void onFailure(Call<FacilitySearchResponse> call, Throwable t) {
                        facilitySearchResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<FacilitySearchResponse> getFacilitySearchResponseLiveData() {
        return facilitySearchResponseLiveData;
    }
}
