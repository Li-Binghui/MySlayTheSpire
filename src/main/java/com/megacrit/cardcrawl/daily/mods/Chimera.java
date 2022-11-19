/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class Chimera extends AbstractDailyMod {
/*    */   public static final String ID = "Chimera";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Chimera");
/*  9 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public Chimera() {
/* 12 */     super("Chimera", NAME, DESC, "chimera.png", true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\Chimera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */