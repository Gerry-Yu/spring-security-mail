package com.demo.dao;

import com.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

/**
 * Created by Pinggang Yu on 2016/10/23.
 */
public interface UserDao {
    /*user register*/
    int insertUser(User user);
    int insertAuthorities(@Param("userId") int userId, @Param("authority") String authority);
    /*user email confirm*/
    int insertUserConfirm(@Param("userId") int userId, @Param("confirmCode") String confirmCode);
    String getConfirmCode(String emailAddress);
    void deleteUserConfirm(String emailAddress);
    void setUserEnable(String emailAddress);

    /*for spring-security*/
    Map loadUsersByUsername(String username);
    List<String> loadUserAuthorities(int userId);

}
