package com.rootlabs.client.service;

import com.rootlabs.client.entity.User;
import com.rootlabs.client.entity.VerificationToken;
import com.rootlabs.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);
}
