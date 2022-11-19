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
/*    */ public class VulnerablePower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Vulnerable";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Vulnerable");
/* 16 */   public static final String NAME = powerStrings.NAME;
/* 17 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private boolean justApplied = false;
/*    */   private static final float EFFECTIVENESS = 1.5F;
/*    */   private static final int EFFECTIVENESS_STRING = 50;
/*    */   
/*    */   public VulnerablePower(AbstractCreature owner, int amount, boolean isSourceMonster) {
/* 24 */     this.name = NAME;
/* 25 */     this.ID = "Vulnerable";
/* 26 */     this.owner = owner;
/* 27 */     this.amount = amount;
/* 28 */     updateDescription();
/* 29 */     loadRegion("vulnerable");
/*    */     
/* 31 */     if (AbstractDungeon.actionManager.turnHasEnded && isSourceMonster) {
/* 32 */       this.justApplied = true;
/*    */     }
/*    */     
/* 35 */     this.type = PowerType.DEBUFF;
/* 36 */     this.isTurnBased = true;
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
/* 47 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Vulnerable"));
/*    */     } else {
/* 49 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Vulnerable", 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 55 */     if (this.amount == 1) {
/* 56 */       if (this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
/* 57 */         this.description = DESCRIPTIONS[0] + '\031' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */       }
/* 59 */       else if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog")) {
/* 60 */         this.description = DESCRIPTIONS[0] + 'K' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */       } else {
/*    */         
/* 63 */         this.description = DESCRIPTIONS[0] + '2' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */       }
/*    */     
/* 66 */     } else if (this.owner != null && this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
/* 67 */       this.description = DESCRIPTIONS[0] + '\031' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
/*    */     }
/* 69 */     else if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog")) {
/* 70 */       this.description = DESCRIPTIONS[0] + 'K' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
/*    */     } else {
/*    */       
/* 73 */       this.description = DESCRIPTIONS[0] + '2' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float atDamageReceive(float damage, DamageInfo.DamageType type) {
/* 80 */     if (type == DamageInfo.DamageType.NORMAL) {
/*    */ 
/*    */       
/* 83 */       if (this.owner.isPlayer && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
/* 84 */         return damage * 1.25F;
/*    */       }
/*    */       
/* 87 */       if (this.owner != null && !this.owner.isPlayer && AbstractDungeon.player.hasRelic("Paper Frog")) {
/* 88 */         return damage * 1.75F;
/*    */       }
/*    */       
/* 91 */       return damage * 1.5F;
/*    */     } 
/* 93 */     return damage;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\VulnerablePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */