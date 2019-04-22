package com.ken.mall.utils.password;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
public class PasswordHelper {

    private static final int SALT_SIZE = 16;
    private static Random random = new Random();

    public PasswordHelper() {
    }

    public static String generateSalt() {
        byte[] saltByte = new byte[SALT_SIZE];
        random.nextBytes(saltByte);
        String salt = Hex.encodeHexString(saltByte).toUpperCase();
        return salt;
    }

    public static String encodePassword(String password, String salt) {
        if (password == null) {
            password = "";
        }

        MessageDigest digest = DigestUtils.getSha1Digest();
        byte[] bIn = password.getBytes(Charsets.UTF_16LE);
        byte[] bSalt = Base64.decodeBase64(salt);
        byte[] bAll = new byte[bIn.length + bSalt.length];
        System.arraycopy(bSalt, 0, bAll, 0, bSalt.length);
        System.arraycopy(bIn, 0, bAll, bSalt.length, bIn.length);
        digest.update(bAll);
        byte[] result = digest.digest();
        return Base64.encodeBase64String(result);
    }

    public static boolean verifyPassword(String password, String secret, String salt) {
        String s1 = encodePassword(password, salt);
        return s1.equals(secret);
    }
}
