package com.dedup4.storage.webapp.util;

import java.util.Random;

/**
 * @author Yang Mengmeng Created on May 18, 2016.
 */
public class PwdGenerator {

    private static final char[] chars = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static String generate() {
        StringBuilder pwd = new StringBuilder();
        Random rand = new Random(System.currentTimeMillis());
        int length = rand.nextInt(9) + 8;
        for (int i = 0; i < length; i++) {
            int randInt = Math.abs(rand.nextInt(36));
            pwd.append(chars[randInt]);
        }
        return pwd.toString();
    }

}
