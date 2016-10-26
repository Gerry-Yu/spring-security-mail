package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.model.User;
import com.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Pinggang Yu on 2016/10/23.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Resource
    UserDao userDao;
    public int registerUser(User user, String confirmCode) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password_result = encoder.encode(user.getPassword());
        user.setPassword(password_result);
       try {
           userDao.insertUser(user);
           int userId = user.getUserId();
           userDao.insertAuthorities(userId, "ROLE_USER");
           userDao.insertUserConfirm(userId, confirmCode);
           return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean userConfirm(String emailAddress, String confirmCode) {
        String confirmCodeInDB = userDao.getConfirmCode(emailAddress); //throw Exception
        if (confirmCode.equals(confirmCodeInDB)) {
            userDao.setUserEnable(emailAddress);
            userDao.deleteUserConfirm(emailAddress);
            return true;
        }
        return false;
    }
}
