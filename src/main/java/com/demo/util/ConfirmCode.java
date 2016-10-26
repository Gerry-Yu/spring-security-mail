package com.demo.util;

import java.util.Random;

/**
 * Created by Pinggang Yu on 2016/10/26.
 */
public class ConfirmCode {
    static public String getConfirmCode(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDERGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        String res = "";
        for (int i=0;i<length;++i) {
            res += base.charAt(random.nextInt(base.length()));
        }
        return res;
    }
}
