package com.lanmao.user.auth;

import com.lanmao.core.share.dto.UserDTO;

public class LoginHolder {

    private static ThreadLocal<UserDTO> threadLocal = new ThreadLocal<>();

    public static UserDTO get() {
        return threadLocal.get();
    }

    public static void set(UserDTO user) {
        threadLocal.set(user);
    }
}
