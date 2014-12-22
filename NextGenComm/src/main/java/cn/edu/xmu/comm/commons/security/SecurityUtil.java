package cn.edu.xmu.comm.commons.security;

import cn.edu.xmu.comm.entity.User;
import org.apache.commons.codec.binary.Hex;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 安全工具
 *
 * @author Mengmeng Yang
 * @version 2014-12-21
 */
public class SecurityUtil {

    public static final int LENGTH = 32;
    private static final String MD5 = "MD5";
    private static SecureRandom random = new SecureRandom();


    /**
     * 加密用户
     *
     * @param user 待加密用户
     * @return 用户
     */
    public static User encryptUser(User user) {
        String salt = generateSalt(LENGTH);
        user.setSalt(salt);

        String originPwd = user.getPassword();
        String credentialsSalt = user.getCredentialsSalt();
        String encryptPwd = encrypt(originPwd, credentialsSalt);
        user.setPassword(encryptPwd);

        return user;
    }

    /**
     * 加密字符串
     *
     * @param source 待加密字符串
     * @param salt   盐
     * @return 已加密字符串
     */
    public static String encrypt(String source, String salt) {
        byte[] hashPassword = digest(source.getBytes(), salt.getBytes(), MD5, 2);
        return Hex.encodeHexString(hashPassword);
    }

    /**
     * 生成随机字符串
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String generateSalt(int length) {
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Hex.encodeHexString(bytes);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     */
    private static byte[] digest(byte[] source, byte[] salt, String algorithm, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            if (salt != null) {
                digest.update(salt);
            }
            byte[] result = digest.digest(source);
            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
