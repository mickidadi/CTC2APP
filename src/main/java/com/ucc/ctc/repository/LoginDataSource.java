package com.ucc.ctc.repository;

import com.ucc.ctc.models.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {


           /* LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString()," Mickidadi Kosiyanga");
            return new Result.Success<>(fakeUser);*/
            return null;
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }
 public void logout() {

    }
}
