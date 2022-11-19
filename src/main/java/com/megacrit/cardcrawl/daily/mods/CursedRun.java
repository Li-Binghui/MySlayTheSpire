/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class CursedRun extends AbstractDailyMod {
/*    */   public static final String ID = "Cursed Run";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Cursed Run");
/*  9 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public CursedRun() {
/* 12 */     super("Cursed Run", NAME, DESC, "cursed_run.png", false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\CursedRun.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */