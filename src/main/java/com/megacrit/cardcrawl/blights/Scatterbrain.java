/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.BlightStrings;
/*    */ 
/*    */ public class Scatterbrain extends AbstractBlight {
/*    */   public static final String ID = "Scatterbrain";
/* 10 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("Scatterbrain");
/* 11 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   
/*    */   public Scatterbrain() {
/* 14 */     super("Scatterbrain", NAME, DESC[0] + '\001' + DESC[1], "scatter.png", false);
/* 15 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stack() {
/* 20 */     AbstractDungeon.player.masterHandSize--;
/* 21 */     this.counter++;
/* 22 */     updateDescription();
/* 23 */     flash();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 28 */     this.description = DESC[0] + this.counter + DESC[1];
/* 29 */     this.tips.clear();
/* 30 */     this.tips.add(new PowerTip(this.name, this.description));
/* 31 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 36 */     AbstractDungeon.player.masterHandSize--;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\Scatterbrain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */