/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class FlightPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Flight";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Flight");
/* 16 */   public static final String NAME = powerStrings.NAME;
/* 17 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private int storedAmount;
/*    */   
/*    */   public FlightPower(AbstractCreature owner, int amount) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "Flight";
/* 24 */     this.owner = owner;
/* 25 */     this.amount = amount;
/* 26 */     this.storedAmount = amount;
/* 27 */     updateDescription();
/* 28 */     loadRegion("flight");
/* 29 */     this.priority = 50;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 34 */     CardCrawlGame.sound.play("POWER_FLIGHT", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 39 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 44 */     this.amount = this.storedAmount;
/* 45 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
/* 50 */     return calculateDamageTakenAmount(damage, type);
/*    */   }
/*    */   
/*    */   private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type) {
/* 54 */     if (type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS) {
/* 55 */       return damage / 2.0F;
/*    */     }
/* 57 */     return damage;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 63 */     Boolean willLive = Boolean.valueOf((calculateDamageTakenAmount(damageAmount, info.type) < this.owner.currentHealth));
/* 64 */     if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0 && willLive
/* 65 */       .booleanValue()) {
/* 66 */       flash();
/* 67 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Flight", 1));
/*    */     } 
/* 69 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRemove() {
/* 74 */     addToBot((AbstractGameAction)new ChangeStateAction((AbstractMonster)this.owner, "GROUNDED"));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\FlightPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */