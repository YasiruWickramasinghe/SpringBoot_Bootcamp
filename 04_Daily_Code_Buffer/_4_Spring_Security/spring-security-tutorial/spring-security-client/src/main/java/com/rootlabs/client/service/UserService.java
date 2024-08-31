package com.rootlabs.client.service;

import com.rootlabs.client.entity.User;
import com.rootlabs.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
}
