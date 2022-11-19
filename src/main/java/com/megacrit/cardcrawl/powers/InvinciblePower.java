/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class InvinciblePower extends AbstractPower {
/*    */   public static final String POWER_ID = "Invincible";
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Invincible");
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private int maxAmt;
/*    */   
/*    */   public InvinciblePower(AbstractCreature owner, int amount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Invincible";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amount;
/* 20 */     this.maxAmt = amount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("heartDef");
/* 23 */     this.priority = 99;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
/* 39 */     if (damageAmount > this.amount) {
/* 40 */       damageAmount = this.amount;
/*    */     }
/* 42 */     this.amount -= damageAmount;
/* 43 */     if (this.amount < 0) {
/* 44 */       this.amount = 0;
/*    */     }
/*    */     
/* 47 */     updateDescription();
/* 48 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 53 */     this.amount = this.maxAmt;
/* 54 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 59 */     if (this.amount <= 0) {
/* 60 */       this.description = DESCRIPTIONS[2];
/*    */     } else {
/* 62 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\InvinciblePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */