package com.ucc.ctc.viewsModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ucc.ctc.models.UserProfile;
import com.ucc.ctc.repository.UserRemoteRepository;

public class UserRemoteViewModel extends ViewModel {
    private LiveData<UserProfile> userLiveData;
    private UserRemoteRepository userRepository;

    public UserRemoteViewModel() {

        userRepository = UserRemoteRepository.getInstance();
    }
   public void getRemoteUser(String username,String password) {
        if (this.userLiveData!= null) {
            if (this.userLiveData.getValue().getUsername() == username) {
                return;
            }
        }
        userLiveData = userRepository.getUserRemote(username,password);
    }
    public LiveData<UserProfile> getUserLiveData() {
        return userLiveData;
    }
}
