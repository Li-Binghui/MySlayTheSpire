/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.BlightStrings;
/*    */ 
/*    */ public class Muzzle extends AbstractBlight {
/*    */   public static final String ID = "FullBelly";
/*  8 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("FullBelly");
/*  9 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public Muzzle() {
/* 12 */     super("FullBelly", NAME, DESC[0], "muzzle.png", true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\Muzzle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */