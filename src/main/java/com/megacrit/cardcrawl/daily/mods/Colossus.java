/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Colossus extends AbstractDailyMod {
/*    */   public static final String ID = "MonsterHunter";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("MonsterHunter");
/*  9 */   public static final String NAME = modStrings.NAME; public static final String DESC = modStrings.DESCRIPTION;
/*    */   public static final float modAmount = 1.5F;
/*    */   
/*    */   public Colossus() {
/* 13 */     super("MonsterHunter", NAME, DESC, "colossus.png", false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Colossus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */