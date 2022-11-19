/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import java.math.BigInteger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SeedHelper
/*     */ {
/*     */   private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
/*  16 */   public static String cachedSeed = null;
/*     */ 
/*     */   
/*  19 */   public static final int SEED_DEFAULT_LENGTH = getString(Long.MIN_VALUE).length();
/*     */ 
/*     */   
/*     */   public static void setSeed(String seedStr) {
/*  23 */     if (seedStr.isEmpty()) {
/*  24 */       Settings.seedSet = false;
/*  25 */       Settings.seed = null;
/*  26 */       Settings.specialSeed = null;
/*     */     } else {
/*  28 */       long seed = getLong(seedStr);
/*  29 */       Settings.seedSet = true;
/*  30 */       Settings.seed = Long.valueOf(seed);
/*  31 */       Settings.specialSeed = null;
/*  32 */       Settings.isDailyRun = false;
/*  33 */       cachedSeed = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String getUserFacingSeedString() {
/*  38 */     if (Settings.seed != null) {
/*  39 */       if (cachedSeed == null) {
/*  40 */         cachedSeed = getString(Settings.seed.longValue());
/*     */       }
/*  42 */       return cachedSeed;
/*     */     } 
/*  44 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getValidCharacter(String character, String textSoFar) {
/*  49 */     character = character.toUpperCase();
/*  50 */     if (character.equals("O")) {
/*  51 */       character = "0";
/*     */     }
/*  53 */     boolean isValid = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZ".contains(character);
/*  54 */     if (isValid) {
/*  55 */       return character;
/*     */     }
/*  57 */     return null;
/*     */   }
/*     */   
/*     */   public static String sterilizeString(String raw) {
/*  61 */     raw = raw.trim().toUpperCase();
/*     */     
/*  63 */     String pattern = "([A-Z]*[0-9]*)*";
/*  64 */     if (raw.matches("([A-Z]*[0-9]*)*")) {
/*  65 */       return raw.replace("O", "0");
/*     */     }
/*  67 */     return "";
/*     */   }
/*     */   
/*     */   public static String getString(long seed) {
/*  71 */     StringBuilder bldr = new StringBuilder();
/*     */ 
/*     */     
/*  74 */     BigInteger leftover = new BigInteger(Long.toUnsignedString(seed));
/*  75 */     BigInteger charCount = BigInteger.valueOf("0123456789ABCDEFGHIJKLMNPQRSTUVWXYZ".length());
/*     */     
/*  77 */     while (!leftover.equals(BigInteger.ZERO)) {
/*  78 */       BigInteger remainder = leftover.remainder(charCount);
/*  79 */       leftover = leftover.divide(charCount);
/*     */       
/*  81 */       int charIndex = remainder.intValue();
/*  82 */       char c = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZ".charAt(charIndex);
/*  83 */       bldr.insert(0, c);
/*     */     } 
/*  85 */     return bldr.toString();
/*     */   }
/*     */   
/*     */   public static long getLong(String seedStr) {
/*  89 */     long total = 0L;
/*  90 */     seedStr = seedStr.toUpperCase().replaceAll("O", "0");
/*  91 */     for (int i = 0; i < seedStr.length(); i++) {
/*  92 */       char toFind = seedStr.charAt(i);
/*  93 */       int remainder = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZ".indexOf(toFind);
/*  94 */       if (remainder == -1) {
/*  95 */         System.out.println("Character in seed is invalid: " + toFind);
/*     */       }
/*     */       
/*  98 */       total *= "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZ".length();
/*  99 */       total += remainder;
/*     */     } 
/* 101 */     return total;
/*     */   }
/*     */   
/*     */   public static long generateUnoffensiveSeed(Random rng) {
/* 105 */     String safeString = "fuck";
/*     */ 
/*     */     
/* 108 */     while (BadWordChecker.containsBadWord(safeString) || TrialHelper.isTrialSeed(safeString)) {
/* 109 */       long possible = rng.randomLong();
/* 110 */       safeString = getString(possible);
/*     */     } 
/* 112 */     return getLong(safeString);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\SeedHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */