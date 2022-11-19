/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class NightTerrors extends AbstractDailyMod {
/*    */   public static final String ID = "Night Terrors";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Night Terrors");
/*  9 */   public static final String NAME = modStrings.NAME; public static final String DESC = modStrings.DESCRIPTION;
/*    */   public static final float HEAL_AMT = 1.0F;
/*    */   public static final int MAX_HP_LOSS = 5;
/*    */   
/*    */   public NightTerrors() {
/* 14 */     super("Night Terrors", NAME, DESC, "night_terrors.png", false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\NightTerrors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */