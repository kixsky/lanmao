package com.lanmao.mech.auth;

import com.lanmao.core.share.dto.MechDTO;

public class LoginHolder {

    private static ThreadLocal<MechDTO> threadLocal = new ThreadLocal<>();

    public static MechDTO get() {
        return threadLocal.get();
    }

    public static void set(MechDTO user) {
        threadLocal.set(user);
    }
}
