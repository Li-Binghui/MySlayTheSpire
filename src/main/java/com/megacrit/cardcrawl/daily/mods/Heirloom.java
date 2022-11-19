/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Heirloom extends AbstractDailyMod {
/*    */   public static final String ID = "Heirloom";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Heirloom");
/*  9 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public Heirloom() {
/* 12 */     super("Heirloom", NAME, DESC, "heirloom.png", true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Heirloom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */