package com.ucc.ctc.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.apis.ApiClient;
import com.ucc.ctc.apis.UserApi;
import com.ucc.ctc.models.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserTestRepository {
    private UserApi userApi;

    public UserTestRepository() {

        userApi = ApiClient.getRetrofitInstance().create(UserApi.class);
    }

    public LiveData<Users> getUserById(String id) {
        MutableLiveData<Users> data = new MutableLiveData<>();
        userApi.getUserById(id).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // handle error
            }
        });
        return data;
    }
}
