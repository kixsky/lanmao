package com.lanmao.web.auth;

import com.lanmao.core.share.dto.DepMemberDTO;

public class LoginHolder {

    private static ThreadLocal<DepMemberDTO> threadLocal = new ThreadLocal<>();

    public static DepMemberDTO get() {
        return threadLocal.get();
    }

    public static void set(DepMemberDTO user) {
        threadLocal.set(user);
    }
}
