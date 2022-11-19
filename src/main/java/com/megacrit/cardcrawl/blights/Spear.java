/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.BlightStrings;
/*    */ 
/*    */ public class Spear extends AbstractBlight {
/*    */   public static final String ID = "DeadlyEnemies";
/*  9 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("DeadlyEnemies");
/* 10 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/* 11 */   public float damageMod = 2.0F;
/*    */   
/*    */   public Spear() {
/* 14 */     super("DeadlyEnemies", NAME, DESC[0] + 'd' + DESC[1], "spear.png", true);
/* 15 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void incrementUp() {
/* 20 */     this.damageMod += 0.75F;
/* 21 */     this.increment++;
/* 22 */     this.counter++;
/* 23 */     this.description = DESC[0] + (int)((this.damageMod - 1.0F) * 100.0F) + DESC[1];
/* 24 */     this.tips.clear();
/* 25 */     this.tips.add(new PowerTip(this.name, this.description));
/* 26 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public float effectFloat() {
/* 31 */     return this.damageMod;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\Spear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */