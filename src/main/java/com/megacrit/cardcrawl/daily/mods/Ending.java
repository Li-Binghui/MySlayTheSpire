/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Ending extends AbstractDailyMod {
/*    */   public static final String ID = "The Ending";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("The Ending");
/*  9 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public Ending() {
/* 12 */     super("The Ending", NAME, DESC, "endless.png", true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Ending.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */