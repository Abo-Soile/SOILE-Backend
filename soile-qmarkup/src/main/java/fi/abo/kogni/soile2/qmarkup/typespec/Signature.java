package fi.abo.kogni.soile2.qmarkup.typespec;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class Signature {
    public static String compute(String txt) {
        byte[] sha1 = compute_SHA1(txt);
        String hash = base64(sha1);
        return hash;
    }
    
    private static byte[] compute_SHA1(String txt) {
        return computeDigest("SHA-1", txt);
    }
    
    private static byte[] computeDigest(String algorithm, String txt) {
        byte[] digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(txt.getBytes("UTF-8"));
            digest = md.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return digest;
    }
    
    private static String base64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
}
