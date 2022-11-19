/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Terminal extends AbstractDailyMod {
/*    */   public static final String ID = "Terminal";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Terminal");
/*  9 */   public static final String NAME = modStrings.NAME; public static final String DESC = modStrings.DESCRIPTION;
/*    */   public static final int ARMOR_AMT = 5;
/*    */   
/*    */   public Terminal() {
/* 13 */     super("Terminal", NAME, DESC, "tough_enemies.png", false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Terminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */