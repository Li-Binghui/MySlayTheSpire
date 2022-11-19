/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class VaultPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Vault";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Vault");
/*    */   private AbstractCreature source;
/*    */   
/*    */   public VaultPower(AbstractCreature target, AbstractCreature source, int amount) {
/* 19 */     this.name = powerStrings.NAME;
/* 20 */     this.ID = "Vault";
/* 21 */     this.owner = target;
/* 22 */     this.source = source;
/* 23 */     this.amount = amount;
/* 24 */     updateDescription();
/* 25 */     loadRegion("carddraw");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 35 */     flash();
/* 36 */     addToBot((AbstractGameAction)new DamageAction(this.owner, new DamageInfo(this.source, this.amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 37 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Vault"));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\VaultPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */