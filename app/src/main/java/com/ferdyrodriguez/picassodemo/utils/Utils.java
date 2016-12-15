package com.ferdyrodriguez.picassodemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ferdyrod on 12/7/16.
 */

public class Utils {

    public static final String BASE_MARVEL_URL = "http://gateway.marvel.com/";

    public static final String PUBLIC_KEY = "GetYourAPIKeyFromMarvel.com";
    public static final String PRIVATE_KEY = "etYourAPIKeyFromMarvel.com";


    public static String getTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static String getApiKey() {
        return PUBLIC_KEY;
    }

    public static String getKeyHash() {
        String hash = md5(getTimestamp() + PRIVATE_KEY + getApiKey());
        return hash;
    }

    public static String md5(String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
