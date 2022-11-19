/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Midas extends AbstractDailyMod {
/*    */   public static final String ID = "Midas";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Midas");
/*  9 */   public static final String NAME = modStrings.NAME; public static final String DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public static final float MULTIPLIER = 2.0F;
/*    */   
/*    */   public Midas() {
/* 14 */     super("Midas", NAME, DESC, "midas.png", false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Midas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */