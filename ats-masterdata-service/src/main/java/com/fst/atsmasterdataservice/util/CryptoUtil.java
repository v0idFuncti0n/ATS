package com.fst.atsmasterdataservice.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class CryptoUtil {

    private static final String ALGO = "AES";
    private static final byte[] keyValue = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    @SuppressWarnings("restriction")
    public static String encrypt(String Data) {
        String encryptedValue = "";

        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal;
            encVal = c.doFinal(Data.getBytes());
            encryptedValue = Base64.getEncoder().encodeToString(encVal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedValue;
    }

    @SuppressWarnings("restriction")
    public static String decrypt(String encryptedData) {

        byte[] decValue = null;

        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
            decValue = c.doFinal(decodedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decValue == null ? null : new String(decValue);
    }

    private static Key generateKey() {
        return new SecretKeySpec(keyValue, ALGO);
    }

}
