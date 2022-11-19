/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class CertainFuture extends AbstractDailyMod {
/*    */   public static final String ID = "Uncertain Future";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Uncertain Future");
/*  9 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public CertainFuture() {
/* 12 */     super("Uncertain Future", NAME, DESC, "certain_future.png", false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\CertainFuture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */