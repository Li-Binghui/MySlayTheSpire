/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Insanity extends AbstractDailyMod {
/*    */   public static final String ID = "Insanity";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Insanity");
/*  9 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public Insanity() {
/* 12 */     super("Insanity", NAME, DESC, "restless_journey.png", false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Insanity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */