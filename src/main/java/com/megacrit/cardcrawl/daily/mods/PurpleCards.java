/*    */ package com.megacrit.cardcrawl.daily.mods;
/*    */ 
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.RunModStrings;
/*    */ 
/*    */ public class PurpleCards extends AbstractDailyMod {
/*    */   public static final String ID = "Purple Cards";
/*  9 */   private static final RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString("Purple Cards");
/* 10 */   public static final String NAME = modStrings.NAME, DESC = modStrings.DESCRIPTION;
/*    */   
/*    */   public PurpleCards() {
/* 13 */     super("Purple Cards", NAME, DESC, "purple.png", true, AbstractPlayer.PlayerClass.WATCHER);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\mods\PurpleCards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */