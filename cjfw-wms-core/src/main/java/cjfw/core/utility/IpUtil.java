package cjfw.core.utility;


import jakarta.servlet.http.HttpServletRequest;

/**
 * Copyright 2024. CJ OliveNetworks Co. all rights reserved.
 *
 * @author : th.jeon
 * @date : 2024.08.30
 * @description : Client IP 확인 기능을 구현한 Controller Class
 * @issues :
 * -----------------------------------------------------------
 * DATE                 AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2024.08.30        th.jeon       생성
 **/
public class IpUtil {

    public static String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}