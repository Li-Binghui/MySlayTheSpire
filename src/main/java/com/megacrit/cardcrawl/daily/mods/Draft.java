/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Draft extends AbstractDailyMod {
/*    */   public static final String ID = "Draft";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Draft");
/*  9 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public Draft() {
/* 12 */     super("Draft", NAME, DESC, "draft.png", true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Draft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */