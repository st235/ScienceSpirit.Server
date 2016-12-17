package com.github.sasd97.constants;

/**
 * Created by Alexadner Dadukin on 11/21/2016.
 */
public final class MethodConstants {

    private MethodConstants() {}

    public static final class Error {

        public static final String ERROR = "/error";
    }

    public static final class Registration {

        public static final String INDEX = "/register";
        public static final String FACEBOOK = "/facebook/{token}";
    }

    public static final class WebRegistration {

        public static final String INDEX = "/webregister";
        public static final String FACEBOOK = "/facebook";
        public static final String FACEBOOK_REDIRECT = "/redirect/facebook";
    }
}
