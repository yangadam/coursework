package cn.edu.xmu.comm.commons.security;

import cn.edu.xmu.comm.entity.User;

/**
 * Created by Yummy on 2014/12/14.
 */
public class PasswordUtil {

    public static void encryptPassword(User user) {
        byte[] salts = Digests.generateSalt(LENGTH);
        String salt = Encodes.encodeHex(salts);
        String password = encrypt(user.getPassword(), salt);
        user.setSalt(salt);
        user.setPassword(password);
    }

    public static String encrypt(String source, String salt) {
        byte[] hashPassword = Digests.md5(source.getBytes(), salt.getBytes(), 2);
        return Encodes.encodeHex(hashPassword);
    }

    public static final int LENGTH = 32;

}
