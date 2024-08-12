package com.BitzNomad.identity_service.Utils;

import java.security.SecureRandom;

public class RandomPasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomPassword(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be greater than 0");
        }

        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALL_CHARACTERS.length());
            password.append(ALL_CHARACTERS.charAt(index));
        }

        return password.toString();
    }
}
