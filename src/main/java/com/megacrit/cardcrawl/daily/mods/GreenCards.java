/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class GreenCards extends AbstractDailyMod {
/*    */   public static final String ID = "Green Cards";
/*  9 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Green Cards");
/* 10 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public GreenCards() {
/* 13 */     super("Green Cards", NAME, DESC, "green.png", true, AbstractPlayer.PlayerClass.THE_SILENT);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\GreenCards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */