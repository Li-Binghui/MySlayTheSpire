/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.localization.BlightStrings;
/*    */ 
/*    */ public class Shield extends AbstractBlight {
/*    */   public static final String ID = "ToughEnemies";
/*  9 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("ToughEnemies");
/* 10 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/* 11 */   public float toughMod = 1.5F;
/*    */   
/*    */   public Shield() {
/* 14 */     super("ToughEnemies", NAME, DESC[0] + '2' + DESC[1], "shield.png", true);
/* 15 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void incrementUp() {
/* 20 */     this.toughMod += 0.5F;
/* 21 */     this.increment++;
/* 22 */     this.counter++;
/* 23 */     this.description = DESC[0] + (int)((this.toughMod - 1.0F) * 100.0F) + DESC[1];
/* 24 */     this.tips.clear();
/* 25 */     this.tips.add(new PowerTip(this.name, this.description));
/* 26 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public float effectFloat() {
/* 31 */     return this.toughMod;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\Shield.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */