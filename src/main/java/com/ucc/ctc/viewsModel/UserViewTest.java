package com.ucc.ctc.viewsModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ucc.ctc.models.Users;
import com.ucc.ctc.repository.UserTestRepository;

public class UserViewTest extends ViewModel {
    private UserTestRepository userRepository;
    private LiveData<Users> user;

    public UserViewTest() {
        userRepository = new UserTestRepository();
    }

    public LiveData<Users> getUserById(String id) {
        if (user == null) {
            user = userRepository.getUserById( id );
        }
        return user;
    }
}
