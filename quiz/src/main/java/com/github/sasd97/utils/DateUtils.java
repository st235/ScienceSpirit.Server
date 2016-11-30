package com.github.sasd97.utils;

import java.util.Date;

/**
 * Created by Alexadner Dadukin on 11/30/2016.
 */
public class DateUtils {

    private DateUtils() {}

    public static long timestamp() {
        return new Date().getTime() / 1000;
    }

    public static Date time(long timestamp) {
        return new Date(timestamp * 1000);
    }
}
