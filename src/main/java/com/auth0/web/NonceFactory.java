package com.auth0.web;

import java.util.Random;

/**
 * Create a token (nonce) that can be stored in State param
 * to correlate requests with callbacks and ensure validity.
 * Assists with CSRF prevention
 */
public class NonceFactory {

    private static final Random randomSource = new Random();

    public static String create() {
        byte random[] = new byte[16];
        StringBuilder buffer = new StringBuilder();
        randomSource.nextBytes(random);
        for (int j = 0; j < random.length; j++) {
            byte b1 = (byte) ((random[j] & 0xf0) >> 4);
            byte b2 = (byte) (random[j] & 0x0f);
            if (b1 < 10)
                buffer.append((char) ('0' + b1));
            else
                buffer.append((char) ('A' + (b1 - 10)));
            if (b2 < 10)
                buffer.append((char) ('0' + b2));
            else
                buffer.append((char) ('A' + (b2 - 10)));
        }
        return buffer.toString();
    }

}
