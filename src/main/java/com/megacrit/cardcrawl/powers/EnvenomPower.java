/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class EnvenomPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Envenom";
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Envenom");
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public EnvenomPower(AbstractCreature owner, int newAmount) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Envenom";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = newAmount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("envenom");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
/* 32 */     if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
/* 33 */       flash();
/* 34 */       addToTop((AbstractGameAction)new ApplyPowerAction(target, this.owner, new PoisonPower(target, this.owner, this.amount), this.amount, true));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\EnvenomPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */