package com.dedup4.storage.webapp.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yang Mengmeng Created on Apr 09, 2016.
 */
public class MultipartFileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultipartFileUtil.class);

    public static String md5(MultipartFile file) {
        try {
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            return new BigInteger(1, digest).toString(16);
        } catch (NoSuchAlgorithmException | IOException e) {
            LOGGER.error("Unable to calculate md5", e);
        }
        return StringUtils.EMPTY;
    }

}
