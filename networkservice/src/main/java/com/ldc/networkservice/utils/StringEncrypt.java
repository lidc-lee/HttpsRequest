package com.ldc.networkservice.utils;

import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by LinJK on 6/17/16.
 */
public class StringEncrypt {
    private static String _key = "G3z(%&mj";

    /**
     * Encrypt string.
     *
     * @param encryptString the encrypt string
     * @return the string
     * @throws Exception the exception
     */
    public static String Encrypt(String encryptString) throws Exception {
        byte[] iv = _key.getBytes("US-ASCII");
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(_key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

        return byte2HexString(encryptedData);
    }

    private static String byte2HexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String stmp = Integer.toHexString(b[i] & 0xff);
            if (stmp.length() == 1)
                sb.append("0" + stmp);
            else
                sb.append(stmp);
        }
        return sb.toString().toUpperCase(Locale.ENGLISH);
    }
}
