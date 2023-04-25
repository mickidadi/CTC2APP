package com.ucc.ctc.viewsModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ucc.ctc.models.entity.UserEntity;
import com.ucc.ctc.repository.LoginFacilityRepository;

public class LoginFacilityViewModel extends AndroidViewModel {
    private LoginFacilityRepository userRepository;

    public LoginFacilityViewModel(@NonNull Application application) {
        super( application );
        userRepository = new LoginFacilityRepository(application);
    }
    public LiveData<UserEntity> login(String username, String password) {
        return userRepository.getUser(username, password);
    }
   /* private LoginFacilityRepositoryOld userRepository;
    private MutableLiveData<User> userLiveData;

    public LoginFacilityViewModel(LoginFacilityRepositoryOld userRepository) {
        this.userRepository = userRepository;
        userLiveData = new MutableLiveData<>();
    }

    public LiveData<User> getUser() {
        if (userLiveData.getValue() == null) {
            userLiveData = userRepository.getUser();
        }
        return userLiveData;
    }

    public void authenticateUser(String username, String password) {
        userRepository.getUserFromRemote(username).observe(LoginFacilityViewModel.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null && user.getPassword().equals(password)) {
                    userLiveData.setValue(user);
                    userRepository.insert(user);
                }
            }
        });
    }*/
}