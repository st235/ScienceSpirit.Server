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

    public static final class AdminRegistration {

        public static final String INDEX = "/admin";
        public static final String REGISTER = "/register";
    }

    public static final class ThemesAdministration {

        public static final String INDEX = "/themes/admin";
        public static final String CREATE = "/create";
    }

    public static final class QuestionAdministration {

        public static final String INDEX = "/question";
        public static final String CREATE = "/create";
    }

    public static final class Themes {

        public static final String INDEX = "/themes";
        public static final String GET_ALL = "/get/all";
    }
}
