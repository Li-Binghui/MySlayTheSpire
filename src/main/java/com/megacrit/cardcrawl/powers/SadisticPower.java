/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class SadisticPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Sadistic";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Sadistic");
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public SadisticPower(AbstractCreature owner, int amount) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Sadistic";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amount;
/* 22 */     updateDescription();
/* 23 */     loadRegion("sadistic");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 28 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
/* 33 */     if (power.type == PowerType.DEBUFF && !power.ID.equals("Shackled") && source == this.owner && target != this.owner &&
/* 34 */       !target.hasPower("Artifact")) {
/* 35 */       flash();
/* 36 */       addToBot((AbstractGameAction)new DamageAction(target, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\SadisticPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */