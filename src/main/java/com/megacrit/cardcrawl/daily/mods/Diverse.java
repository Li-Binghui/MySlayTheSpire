/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Diverse extends AbstractDailyMod {
/*    */   public static final String ID = "Diverse";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Diverse");
/*  9 */   public static final String NAME = modStrings.NAME; public static final String DESC = modStrings.DESCRIPTION;
/*    */   public static final int NON_DEFECT_MASTER_MAX_ORBS = 1;
/*    */   
/*    */   public Diverse() {
/* 13 */     super("Diverse", NAME, DESC, "diverse.png", true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Diverse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */