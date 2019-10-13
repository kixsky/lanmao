package com.lanmao.web.auth;

public class LoginHolder {

    private static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static LoginUser get() {
        return threadLocal.get();
    }

    public static void set(LoginUser user) {
        threadLocal.set(user);
    }
}
