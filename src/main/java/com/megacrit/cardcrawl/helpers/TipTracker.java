/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class TipTracker
/*     */ {
/*  10 */   private static final Logger logger = LogManager.getLogger(TipTracker.class.getName());
/*     */   public static Prefs pref;
/*  12 */   public static HashMap<String, Boolean> tips = new HashMap<>();
/*     */   
/*     */   public static final String NEOW_SKIP = "NEOW_SKIP";
/*     */   
/*     */   public static final String NEOW_INTRO = "NEOW_INTRO";
/*     */   public static final String NO_FTUE_CHECK = "NO_FTUE";
/*     */   public static final String COMBAT_TIP = "COMBAT_TIP";
/*     */   public static final String BLOCK_TIP = "BLOCK TIP";
/*     */   public static final String POWER_TIP = "POWER_TIP";
/*     */   public static final String M_POWER_TIP = "M_POWER_TIP";
/*     */   public static final String ENERGY_USE_TIP = "ENERGY_USE_TIP";
/*  23 */   public static int energyUseCounter = 0;
/*     */   public static final String SHUFFLE_TIP = "SHUFFLE_TIP";
/*  25 */   public static int shuffleCounter = 0;
/*     */   public static final int SHUFFLE_THRESHOLD = 1;
/*     */   public static final String POTION_TIP = "POTION_TIP";
/*     */   public static final String CARD_REWARD_TIP = "CARD_REWARD_TIP";
/*     */   public static final String INTENT_TIP = "INTENT_TIP";
/*  30 */   public static int blockCounter = 0;
/*     */   public static final int BLOCK_THRESHOLD = 3;
/*     */   public static final String RELIC_TIP = "RELIC_TIP";
/*  33 */   public static int relicCounter = 0;
/*     */   
/*     */   public static void initialize() {
/*  36 */     pref = SaveHelper.getPrefs("STSTips");
/*  37 */     refresh();
/*     */   }
/*     */   
/*     */   public static void refresh() {
/*  41 */     tips.clear();
/*  42 */     tips.put("NEOW_SKIP", Boolean.valueOf(pref.getBoolean("NEOW_SKIP", false)));
/*  43 */     tips.put("NEOW_INTRO", Boolean.valueOf(pref.getBoolean("NEOW_INTRO", false)));
/*  44 */     tips.put("NO_FTUE", Boolean.valueOf(pref.getBoolean("NO_FTUE", false)));
/*  45 */     tips.put("COMBAT_TIP", Boolean.valueOf(pref.getBoolean("COMBAT_TIP", false)));
/*  46 */     tips.put("BLOCK TIP", Boolean.valueOf(pref.getBoolean("BLOCK TIP", false)));
/*  47 */     tips.put("POWER_TIP", Boolean.valueOf(pref.getBoolean("POWER_TIP", false)));
/*  48 */     tips.put("M_POWER_TIP", Boolean.valueOf(pref.getBoolean("M_POWER_TIP", false)));
/*  49 */     tips.put("ENERGY_USE_TIP", Boolean.valueOf(pref.getBoolean("ENERGY_USE_TIP", false)));
/*  50 */     if (((Boolean)tips.get("ENERGY_USE_TIP")).booleanValue()) {
/*  51 */       energyUseCounter = 9;
/*     */     } else {
/*  53 */       energyUseCounter = 0;
/*     */     } 
/*  55 */     tips.put("SHUFFLE_TIP", Boolean.valueOf(pref.getBoolean("SHUFFLE_TIP", false)));
/*  56 */     if (((Boolean)tips.get("SHUFFLE_TIP")).booleanValue()) {
/*  57 */       shuffleCounter = 99;
/*     */     } else {
/*  59 */       shuffleCounter = 0;
/*     */     } 
/*  61 */     shuffleCounter = 0;
/*  62 */     tips.put("POTION_TIP", Boolean.valueOf(pref.getBoolean("POTION_TIP", false)));
/*  63 */     tips.put("CARD_REWARD_TIP", Boolean.valueOf(pref.getBoolean("CARD_REWARD_TIP", false)));
/*  64 */     tips.put("INTENT_TIP", Boolean.valueOf(pref.getBoolean("INTENT_TIP", false)));
/*  65 */     blockCounter = 0;
/*  66 */     tips.put("RELIC_TIP", Boolean.valueOf(pref.getBoolean("RELIC_TIP", false)));
/*     */     
/*  68 */     if (((Boolean)tips.get("RELIC_TIP")).booleanValue()) {
/*  69 */       relicCounter = 99;
/*     */     } else {
/*  71 */       relicCounter = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void neverShowAgain(String key) {
/*  76 */     logger.info(key + " will never be shown again!");
/*  77 */     pref.putBoolean(key, true);
/*  78 */     tips.put(key, Boolean.valueOf(true));
/*  79 */     pref.flush();
/*     */   }
/*     */   
/*     */   public static void showAgain(String key) {
/*  83 */     logger.info(key + " is reactivated");
/*  84 */     pref.putBoolean(key, false);
/*  85 */     tips.put(key, Boolean.valueOf(false));
/*  86 */     pref.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void disableAllFtues() {
/*  91 */     neverShowAgain("BLOCK TIP");
/*  92 */     neverShowAgain("CARD_REWARD_TIP");
/*  93 */     neverShowAgain("COMBAT_TIP");
/*  94 */     neverShowAgain("ENERGY_USE_TIP");
/*  95 */     neverShowAgain("INTENT_TIP");
/*  96 */     neverShowAgain("M_POWER_TIP");
/*  97 */     neverShowAgain("NO_FTUE");
/*  98 */     neverShowAgain("POTION_TIP");
/*  99 */     neverShowAgain("POWER_TIP");
/* 100 */     neverShowAgain("RELIC_TIP");
/* 101 */     neverShowAgain("SHUFFLE_TIP");
/*     */   }
/*     */   
/*     */   public static void reset() {
/* 105 */     for (Map.Entry<String, Boolean> c : tips.entrySet())
/* 106 */       showAgain(c.getKey()); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\TipTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */