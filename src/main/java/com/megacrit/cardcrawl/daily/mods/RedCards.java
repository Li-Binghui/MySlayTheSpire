/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class RedCards extends AbstractDailyMod {
/*    */   public static final String ID = "Red Cards";
/*  9 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Red Cards");
/* 10 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public RedCards() {
/* 13 */     super("Red Cards", NAME, DESC, "red.png", true, AbstractPlayer.PlayerClass.IRONCLAD);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\RedCards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */