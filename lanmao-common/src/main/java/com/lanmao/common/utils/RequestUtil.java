package com.lanmao.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {


    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (isInvalidIp(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (isInvalidIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isInvalidIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isInvalidIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static Map<String, String> extractParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String[] values = entry.getValue();
            if (values.length <= 0) {
                continue;
            }
            params.put(entry.getKey(), entry.getValue()[0]);
        }
        return params;
    }

    private static boolean isInvalidIp(String ip) {
        return StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip);
    }
}
