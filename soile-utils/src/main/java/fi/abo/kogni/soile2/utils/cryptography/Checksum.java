package fi.abo.kogni.soile2.utils.cryptography;

import org.apache.commons.codec.digest.DigestUtils;

public final class Checksum {
    
    public static String md5(String input) {
        return DigestUtils.md5Hex(input);
    }
    
    public static String md5(byte[] input) {
        return DigestUtils.md5Hex(input);
    }
    
    public static String sha1(String input) {
        return DigestUtils.sha1Hex(input);
    }
    
    public static String sha1(byte[] input) {
        return DigestUtils.sha1Hex(input);
    }
    
    public static String sha256(String input) {
        return DigestUtils.sha256Hex(input);
    }
    
    public static String sha256(byte[] input) {
        return DigestUtils.sha256Hex(input);
    }
    
    public static String sha512(String input) {
        return DigestUtils.sha512Hex(input);
    }
    
    public static String sha512(byte[] input) {
        return DigestUtils.sha512Hex(input);
    }
    
}
