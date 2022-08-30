package fi.abo.kogni.soile2.utils.cryptography;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

// http://stackoverflow.com/questions/15948662/decrypting-in-java-with-blowfish
// http://stackoverflow.com/questions/16259118/encryption-and-decryption-using-blowfish-error-input-length-must-be-multiple-o

public final class Blowfish {

    private static final String ENCODING = "UTF8";
    private static final String CIPHER_NAME = "Blowfish";

    public static String encrypt(String clearText, String key) {

        try {
            final byte[] keyBytes = key.getBytes(ENCODING);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, CIPHER_NAME);
            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            final byte[] bytes = clearText.getBytes(ENCODING);
            final byte[] encrypted = cipher.doFinal(bytes);
            return Base64.encodeBase64String(encrypted);
        } catch (UnsupportedEncodingException 
                | NoSuchAlgorithmException
                | NoSuchPaddingException 
                | InvalidKeyException
                | IllegalBlockSizeException 
                | BadPaddingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String decrypt(String cipherText, String key) {
        try {
            byte[] encryptedData = Base64.decodeBase64(cipherText);
            final byte[] keyBytes = key.getBytes(ENCODING);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, CIPHER_NAME);
            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decrypted = cipher.doFinal(encryptedData);
            return new String(decrypted);
        } catch (UnsupportedEncodingException 
                | NoSuchAlgorithmException 
                | NoSuchPaddingException 
                | InvalidKeyException 
                | IllegalBlockSizeException 
                | BadPaddingException e) {
            e.printStackTrace();
            return "";
        }

    }

}
