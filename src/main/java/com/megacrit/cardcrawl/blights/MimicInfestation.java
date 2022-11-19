/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.BlightStrings;
/*    */ 
/*    */ public class MimicInfestation extends AbstractBlight {
/*    */   public static final String ID = "MimicInfestation";
/*  8 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("MimicInfestation");
/*  9 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public MimicInfestation() {
/* 12 */     super("MimicInfestation", NAME, DESC[0], "mimic.png", true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\MimicInfestation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */