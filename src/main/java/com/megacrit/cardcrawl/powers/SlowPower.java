/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class SlowPower extends AbstractPower {
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Slow"); public static final String POWER_ID = "Slow";
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public SlowPower(AbstractCreature owner, int amount) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "Slow";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amount;
/* 23 */     updateDescription();
/* 24 */     loadRegion("slow");
/* 25 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 30 */     this.amount = 0;
/* 31 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 36 */     this.description = DESCRIPTIONS[0] + FontHelper.colorString(this.owner.name, "y") + DESCRIPTIONS[1];
/*    */     
/* 38 */     if (this.amount != 0) {
/* 39 */       this.description += DESCRIPTIONS[2] + (this.amount * 10) + DESCRIPTIONS[3];
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 45 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new SlowPower(this.owner, 1), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageReceive(float damage, DamageInfo.DamageType type) {
/* 50 */     if (type == DamageInfo.DamageType.NORMAL) {
/* 51 */       return damage * (1.0F + this.amount * 0.1F);
/*    */     }
/* 53 */     return damage;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\SlowPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */