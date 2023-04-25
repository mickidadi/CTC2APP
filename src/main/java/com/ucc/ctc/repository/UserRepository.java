package com.ucc.ctc.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.apis.ApiService;
import com.ucc.ctc.models.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {
    private static UserRepository instance;
    private ApiService apiServices;

    private UserRepository() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://technolemon.com/")
                .addConverterFactory( GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiService.class);
    }

    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
    public LiveData<Users> getUserById(String userId) {
        MutableLiveData<Users> data = new MutableLiveData<>();
        apiServices.getUserById(userId).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
