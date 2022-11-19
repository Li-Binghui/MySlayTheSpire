/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class ColorlessCards extends AbstractDailyMod {
/*    */   public static final String ID = "Colorless Cards";
/*  8 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Colorless Cards");
/*  9 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public ColorlessCards() {
/* 12 */     super("Colorless Cards", NAME, DESC, "colorless.png", true);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\ColorlessCards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */