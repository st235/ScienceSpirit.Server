package com.github.sasd97.utils;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Alexander Dadukin on 11/29/2016.
 */

public class HashUtils {

    private HashUtils() {}

    private static final String emptyKey = "iWantToBreakFReE";

    private static final String[] keystore = new String[] {
            "hello,woRld", "queen", "i_ll_be_black"
    };

    public static String md5(@NotNull String data) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(data.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static String randomMd5() {
        return md5(randomData(emptyKey));
    }

    public static String randomMd5(@NotNull String data) {
        return md5(randomData(data));
    }

    public static String randomData(@NotNull String data) {
        return String.format("%s-%s-%d-%s",
                data,
                data.toLowerCase(),
                DateUtils.timestamp(),
                keystore[(int)(Math.random() * keystore.length)]);
    }
}
