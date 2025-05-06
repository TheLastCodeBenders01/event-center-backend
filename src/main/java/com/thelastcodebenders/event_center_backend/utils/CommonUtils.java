package com.thelastcodebenders.event_center_backend.utils;

import java.security.SecureRandom;

public class CommonUtils {

    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomAlphabetString(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABETS.length());
            sb.append(ALPHABETS.charAt(index));
        }

        return sb.toString();
    }
}
