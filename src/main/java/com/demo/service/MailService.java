package com.demo.service;

import com.demo.model.User;

/**
 * Created by Pinggang Yu on 2016/10/24.
 */
public interface MailService {
    void sendUserMail(User user, String confirmCode);
}
