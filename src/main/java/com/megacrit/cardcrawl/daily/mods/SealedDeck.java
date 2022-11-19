/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class SealedDeck extends AbstractDailyMod {
/*    */   public static final String ID = "SealedDeck";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("SealedDeck");
/*  9 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public SealedDeck() {
/* 12 */     super("SealedDeck", NAME, DESC, "sealed_deck.png", true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\SealedDeck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */