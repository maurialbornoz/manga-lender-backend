package com.maurialbornoz.mangalenderbackend.config;

import com.maurialbornoz.mangalenderbackend.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_DATE = 864000000;
    public static final String LOGIN_URL = "/api/users/login";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }

}
