package com.ucc.ctc.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ucc.ctc.dao.UserLoginDao;
import com.ucc.ctc.database.CTCDatabase;
import com.ucc.ctc.models.entity.UserEntity;

public class LoginFacilityRepository {
    private UserLoginDao userDao;
    //private static LoginFacilityRepository instance;

   /* private LoginFacilityRepository(Context context) {
        CTCDatabase database = CTCDatabase.getInstance(context);
        userDao = database.userLoginDao();
    }*/
    public LoginFacilityRepository(Application application)
    {
        CTCDatabase ctcDatabase = CTCDatabase.getInstance(application);
        userDao = ctcDatabase.userLoginDao();
    }
    public void insert(UserEntity user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public LiveData<UserEntity> getUser(String username, String password) {
        return userDao.getUser(username, password);
    }

    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserLoginDao userDao;

        private InsertUserAsyncTask(UserLoginDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
