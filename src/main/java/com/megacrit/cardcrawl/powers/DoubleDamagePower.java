/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class DoubleDamagePower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Double Damage"); public static final String POWER_ID = "Double Damage";
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private boolean justApplied = false;
/*    */   
/*    */   public DoubleDamagePower(AbstractCreature owner, int amount, boolean isSourceMonster) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "Double Damage";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amount;
/* 23 */     updateDescription();
/* 24 */     loadRegion("doubleDamage");
/* 25 */     this.justApplied = isSourceMonster;
/* 26 */     this.type = PowerType.BUFF;
/* 27 */     this.isTurnBased = true;
/* 28 */     this.priority = 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 33 */     if (this.justApplied) {
/* 34 */       this.justApplied = false;
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     if (this.amount == 0) {
/* 39 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Double Damage"));
/*    */     } else {
/* 41 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Double Damage", 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 47 */     if (this.amount == 1) {
/* 48 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 50 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 56 */     if (type == DamageInfo.DamageType.NORMAL) {
/* 57 */       return damage * 2.0F;
/*    */     }
/* 59 */     return damage;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\DoubleDamagePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */