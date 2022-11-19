/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class WeakPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Weakened";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Weakened");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private boolean justApplied = false;
/*    */   private static final int EFFECTIVENESS_STRING = 25;
/*    */   
/*    */   public WeakPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
/* 21 */     this.name = NAME;
/* 22 */     this.ID = "Weakened";
/* 23 */     this.owner = owner;
/* 24 */     this.amount = amount;
/* 25 */     updateDescription();
/* 26 */     loadRegion("weak");
/*    */     
/* 28 */     if (isSourceMonster) {
/* 29 */       this.justApplied = true;
/*    */     }
/*    */     
/* 32 */     this.type = PowerType.DEBUFF;
/* 33 */     this.isTurnBased = true;
/*    */ 
/*    */     
/* 36 */     this.priority = 99;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 41 */     if (this.justApplied) {
/* 42 */       this.justApplied = false;
/*    */       
/*    */       return;
/*    */     } 
/* 46 */     if (this.amount == 0) {
/* 47 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Weakened"));
/*    */     } else {
/* 49 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Weakened", 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 55 */     if (this.amount == 1) {
/* 56 */       if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
/* 57 */         this.description = DESCRIPTIONS[0] + '(' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */       } else {
/*    */         
/* 60 */         this.description = DESCRIPTIONS[0] + '\031' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */       }
/*    */     
/* 63 */     } else if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
/* 64 */       this.description = DESCRIPTIONS[0] + '(' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
/*    */     }
/*    */     else {
/*    */       
/* 68 */       this.description = DESCRIPTIONS[0] + '\031' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 75 */     if (type == DamageInfo.DamageType.NORMAL) {
/* 76 */       if (!this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Crane")) {
/* 77 */         return damage * 0.6F;
/*    */       }
/* 79 */       return damage * 0.75F;
/*    */     } 
/* 81 */     return damage;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\WeakPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */