package com.ucc.ctc.viewsModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ucc.ctc.models.Users;
import com.ucc.ctc.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private LiveData<Users> userLiveData;
    private UserRepository userRepository;

    public UserViewModel() {

        userRepository = UserRepository.getInstance();
    }

    public void init(String userId) {
        if (this.userLiveData != null) {
            return;
        }
        userLiveData = userRepository.getUserById(userId);
    }

    public LiveData<Users> getUserLiveData() {
        return userLiveData;
    }
}
