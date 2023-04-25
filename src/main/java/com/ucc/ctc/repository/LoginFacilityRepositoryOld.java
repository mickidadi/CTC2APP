package com.ucc.ctc.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ucc.ctc.apis.UserLoginApi;
import com.ucc.ctc.dao.UserLoginDao;
import com.ucc.ctc.models.entity.UserEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFacilityRepositoryOld {
    private UserLoginDao userDao;
    private UserLoginApi userWebService;

    public LoginFacilityRepositoryOld(UserLoginDao userDao, UserLoginApi userWebService) {
        this.userDao = userDao;
        this.userWebService = userWebService;
    }
    public LiveData<UserEntity> getUser() {
       // return userDao.getUser();
        return null;
    }

    public LiveData<UserEntity> getUserFromRemote(String username) {
        MutableLiveData<UserEntity> userLiveData = new MutableLiveData<>();
        userWebService.getUserById(username).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.isSuccessful()) {
                    userLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Log.e("UserRepository", "Error fetching user from remote", t);
            }
        });
        return userLiveData;
    }
  public void insert(UserEntity user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserLoginDao userDao;

        public InsertUserAsyncTask(UserLoginDao userDao) {
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(UserEntity... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}

