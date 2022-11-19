/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class VigorPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Vigor";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Vigor");
/*    */   
/*    */   public VigorPower(AbstractCreature owner, int amount) {
/* 18 */     this.name = powerStrings.NAME;
/* 19 */     this.ID = "Vigor";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amount;
/* 22 */     updateDescription();
/* 23 */     loadRegion("vigor");
/* 24 */     this.type = PowerType.BUFF;
/* 25 */     this.isTurnBased = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 35 */     if (type == DamageInfo.DamageType.NORMAL) {
/* 36 */       return damage += this.amount;
/*    */     }
/* 38 */     return damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 43 */     if (card.type == AbstractCard.CardType.ATTACK) {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 48 */       flash();
/* 49 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Vigor"));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\VigorPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */