/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class BlueCards extends AbstractDailyMod {
/*    */   public static final String ID = "Blue Cards";
/*  9 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Blue Cards");
/* 10 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public BlueCards() {
/* 13 */     super("Blue Cards", NAME, DESC, "blue.png", true, AbstractPlayer.PlayerClass.DEFECT);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\BlueCards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */