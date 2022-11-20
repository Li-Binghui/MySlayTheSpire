package com.megacrit.cardcrawl.saveAndContinue;

import org.apache.commons.codec.binary.Base64;


public class SaveFileObfuscator {
    public static final String key = "key";

    public static String encode(String s, String key) {
        return base64Encode(xorWithKey(s.getBytes(), key.getBytes()));
    }

    public static String decode(String s, String key) {
        return new String(xorWithKey(base64Decode(s), key.getBytes()));
    }

    private static byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i % key.length]);
        }
        return out;
    }

    private static byte[] base64Decode(String s) {
        return Base64.decodeBase64(s);
    }

    private static String base64Encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }


    public static boolean isObfuscated(String data) {
        return !data.contains("{");
    }
}


