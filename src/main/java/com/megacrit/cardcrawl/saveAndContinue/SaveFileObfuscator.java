/*    */ package com.megacrit.cardcrawl.saveAndContinue;
/*    */ 
/*    */ import org.apache.commons.codec.binary.Base64;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SaveFileObfuscator
/*    */ {
/*    */   public static final String key = "key";
/*    */   
/*    */   public static String encode(String s, String key) {
/* 14 */     return base64Encode(xorWithKey(s.getBytes(), key.getBytes()));
/*    */   }
/*    */   
/*    */   public static String decode(String s, String key) {
/* 18 */     return new String(xorWithKey(base64Decode(s), key.getBytes()));
/*    */   }
/*    */   
/*    */   private static byte[] xorWithKey(byte[] a, byte[] key) {
/* 22 */     byte[] out = new byte[a.length];
/* 23 */     for (int i = 0; i < a.length; i++) {
/* 24 */       out[i] = (byte)(a[i] ^ key[i % key.length]);
/*    */     }
/* 26 */     return out;
/*    */   }
/*    */   
/*    */   private static byte[] base64Decode(String s) {
/* 30 */     return Base64.decodeBase64(s);
/*    */   }
/*    */   
/*    */   private static String base64Encode(byte[] bytes) {
/* 34 */     return new String(Base64.encodeBase64(bytes));
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isObfuscated(String data) {
/* 39 */     return !data.contains("{");
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\saveAndContinue\SaveFileObfuscator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */