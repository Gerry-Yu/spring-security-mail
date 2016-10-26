package com.demo.service;

import com.demo.model.User;

/**
 * Created by Pinggang Yu on 2016/10/23.
 */
public interface UserService {
    int registerUser(User user, String confirmCode);
    boolean userConfirm(String emailAddress, String confirmCode);
}
