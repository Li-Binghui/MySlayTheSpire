/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class PlatedArmorPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Plated Armor";
/* 16 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Plated Armor");
/* 17 */   public static final String NAME = powerStrings.NAME;
/* 18 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private static final int DECREMENT_AMT = 1;
/*    */   
/*    */   public PlatedArmorPower(AbstractCreature owner, int amt) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "Plated Armor";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = amt;
/* 27 */     updateDescription();
/* 28 */     loadRegion("platedarmor");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 32 */     this.fontScale = 8.0F;
/* 33 */     this.amount += stackAmount;
/* 34 */     if (this.amount > 999) {
/* 35 */       this.amount = 999;
/*    */     }
/* 37 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 42 */     CardCrawlGame.sound.play("POWER_PLATED", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 47 */     if (this.owner.isPlayer) {
/* 48 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 50 */       this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void wasHPLost(DamageInfo info, int damageAmount) {
/* 56 */     if (info.owner != null && info.owner != this.owner && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
/*    */       
/* 58 */       flash();
/* 59 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Plated Armor", 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRemove() {
/* 65 */     if (!this.owner.isPlayer) {
/* 66 */       addToBot((AbstractGameAction)new ChangeStateAction((AbstractMonster)this.owner, "ARMOR_BREAK"));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
/* 72 */     flash();
/* 73 */     addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\PlatedArmorPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */