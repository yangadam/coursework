package com.dedup4.storage.core.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5.class);

    public static int LEN = 16;

    static char[] hexChars = {
            '0', '1', '2', '3',
            '4', '5', '6', '7',
            '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f'};

    public static String getMD5Str(byte[] chunkBytes) {
        return toHexString(getMD5(chunkBytes));
    }

    public static String getMD5Str(byte[] chunkBytes, int startPos) {
        return getMD5Str(chunkBytes, startPos, chunkBytes.length - startPos);
    }

    public static String getMD5Str(byte[] chunkBytes, int startPos, int len) {
        Preconditions.checkArgument(len > 0);
        Preconditions.checkArgument(startPos + len <= chunkBytes.length);

        byte[] subChunkBytes = new byte[len];
        System.arraycopy(chunkBytes, startPos, subChunkBytes, 0, len);
        return getMD5Str(subChunkBytes);
    }

//	public static long md5Time = 0;

    public static byte[] getMD5(byte[] chunkBytes) {
        return getMD5(chunkBytes, chunkBytes.length);
    }

    public static byte[] getMD5(byte[] chunkBytes, int len) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(chunkBytes, 0, len);
            return md5.digest();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("", e);
            return StringUtils.EMPTY.getBytes();
        }
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte aB : b) {
            sb.append(hexChars[(aB & 0xf0) >>> 4]);
            sb.append(hexChars[aB & 0x0f]);
        }
        return sb.toString();
    }

}
