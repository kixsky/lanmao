package com.lanmao.common.utils;

import java.util.Random;

public class RandomUtils {


    /**
     *
     * 随机验证码
     * @return
     */
    public static String randomCode() {
        StringBuilder sb = new StringBuilder("");
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    public static String randomNumber(int count) {
        StringBuilder sb = new StringBuilder("");
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

}
