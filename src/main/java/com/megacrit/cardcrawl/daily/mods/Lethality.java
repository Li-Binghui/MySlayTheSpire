/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Lethality extends AbstractDailyMod {
/*    */   public static final String ID = "Lethality";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Lethality");
/*  9 */   public static final String NAME = modStrings.NAME; public static final String DESC = modStrings.DESCRIPTION;
/*    */   public static final int STR_AMT = 3;
/*    */   
/*    */   public Lethality() {
/* 13 */     super("Lethality", NAME, DESC, "lethal_enemies.png", false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Lethality.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */