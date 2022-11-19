/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.trials.AbstractTrial;
/*    */ import com.megacrit.cardcrawl.trials.AnyColorDraftTrial;
/*    */ import com.megacrit.cardcrawl.trials.CursedTrial;
/*    */ import com.megacrit.cardcrawl.trials.DraftTrial;
/*    */ import com.megacrit.cardcrawl.trials.HoarderTrial;
/*    */ import com.megacrit.cardcrawl.trials.InceptionTrial;
/*    */ import com.megacrit.cardcrawl.trials.LoseMaxHpTrial;
/*    */ import com.megacrit.cardcrawl.trials.MyTrueFormTrial;
/*    */ import com.megacrit.cardcrawl.trials.OneHpTrial;
/*    */ import com.megacrit.cardcrawl.trials.RandomModsTrial;
/*    */ import com.megacrit.cardcrawl.trials.SlowpokeTrial;
/*    */ import com.megacrit.cardcrawl.trials.SneckoTrial;
/*    */ import com.megacrit.cardcrawl.trials.StarterDeckTrial;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ public class TrialHelper
/*    */ {
/*    */   private static HashMap<String, TRIAL> trialKeysMap;
/*    */   
/*    */   private enum TRIAL
/*    */   {
/* 25 */     RANDOM_MODS, NO_CARD_DROPS, UNCEASING_TOP, LOSE_MAX_HP, SNECKO, SLOW, FORMS, DRAFT, MEGA_DRAFT, ONE_HP, MORE_CARDS, CURSED;
/*    */   }
/*    */   
/*    */   private static void initialize() {
/* 29 */     if (trialKeysMap != null) {
/*    */       return;
/*    */     }
/* 32 */     trialKeysMap = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 38 */     trialKeysMap.put(formatKey("RandomMods"), TRIAL.RANDOM_MODS);
/* 39 */     trialKeysMap.put(formatKey("DailyMods"), TRIAL.RANDOM_MODS);
/* 40 */     trialKeysMap.put(formatKey("StarterDeck"), TRIAL.NO_CARD_DROPS);
/* 41 */     trialKeysMap.put(formatKey("Inception"), TRIAL.UNCEASING_TOP);
/* 42 */     trialKeysMap.put(formatKey("FadeAway"), TRIAL.LOSE_MAX_HP);
/* 43 */     trialKeysMap.put(formatKey("PraiseSnecko"), TRIAL.SNECKO);
/* 44 */     trialKeysMap.put(formatKey("YoureTooSlow"), TRIAL.SLOW);
/* 45 */     trialKeysMap.put(formatKey("MyTrueForm"), TRIAL.FORMS);
/* 46 */     trialKeysMap.put(formatKey("Draft"), TRIAL.DRAFT);
/* 47 */     trialKeysMap.put(formatKey("MegaDraft"), TRIAL.MEGA_DRAFT);
/* 48 */     trialKeysMap.put(formatKey("1HitWonder"), TRIAL.ONE_HP);
/* 49 */     trialKeysMap.put(formatKey("MoreCards"), TRIAL.MORE_CARDS);
/* 50 */     trialKeysMap.put(formatKey("Cursed"), TRIAL.CURSED);
/*    */   }
/*    */   
/*    */   private static String formatKey(String key) {
/* 54 */     return SeedHelper.sterilizeString(key);
/*    */   }
/*    */   
/*    */   public static boolean isTrialSeed(String seed) {
/* 58 */     initialize();
/* 59 */     return trialKeysMap.containsKey(seed);
/*    */   }
/*    */   
/*    */   public static AbstractTrial getTrialForSeed(String seed) {
/* 63 */     initialize();
/* 64 */     if (seed == null) {
/* 65 */       return null;
/*    */     }
/* 67 */     TRIAL picked = trialKeysMap.get(seed);
/* 68 */     if (picked == null) {
/* 69 */       return null;
/*    */     }
/*    */     
/* 72 */     switch (picked) {
/*    */       case RANDOM_MODS:
/* 74 */         return (AbstractTrial)new RandomModsTrial();
/*    */       case NO_CARD_DROPS:
/* 76 */         return (AbstractTrial)new StarterDeckTrial();
/*    */       case UNCEASING_TOP:
/* 78 */         return (AbstractTrial)new InceptionTrial();
/*    */       case LOSE_MAX_HP:
/* 80 */         return (AbstractTrial)new LoseMaxHpTrial();
/*    */       case SNECKO:
/* 82 */         return (AbstractTrial)new SneckoTrial();
/*    */       case SLOW:
/* 84 */         return (AbstractTrial)new SlowpokeTrial();
/*    */       case FORMS:
/* 86 */         return (AbstractTrial)new MyTrueFormTrial();
/*    */       case DRAFT:
/* 88 */         return (AbstractTrial)new DraftTrial();
/*    */       case MEGA_DRAFT:
/* 90 */         return (AbstractTrial)new AnyColorDraftTrial();
/*    */       case ONE_HP:
/* 92 */         return (AbstractTrial)new OneHpTrial();
/*    */       case MORE_CARDS:
/* 94 */         return (AbstractTrial)new HoarderTrial();
/*    */       case CURSED:
/* 96 */         return (AbstractTrial)new CursedTrial();
/*    */     } 
/* 98 */     return null;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\TrialHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */