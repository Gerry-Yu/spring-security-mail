package com.demo.controller;

import com.demo.model.User;
import com.demo.service.MailService;
import com.demo.service.UserService;
import com.demo.util.ConfirmCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Pinggang Yu on 2016/10/23.
 */
@Controller
public class UserController {
    @Resource
    UserService userService;
    @Resource
    MailService mailService;

    @RequestMapping("register")
    public String registerController(String emailAddress, String password) {

        User user = new User(emailAddress, password, emailAddress);
        String confirmCode = ConfirmCode.getConfirmCode(40);

        try {
            userService.registerUser(user, confirmCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailService.sendUserMail(user, confirmCode);
        return "redirect:common/emailConfirm.html";
    }

    @RequestMapping("registerConfirm")
    public String registerConfirm(String emailAddress, String confirmCode) {
        boolean flag = userService.userConfirm(emailAddress, confirmCode);
        if (flag) {
            return "redirect:login.html";
        } else {
            return "redirect:common/emailConfirmError.html";
        }
    }
}
