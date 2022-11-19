/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class ConstrictedPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Constricted"); public static final String POWER_ID = "Constricted";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public AbstractCreature source;
/*    */   
/*    */   public ConstrictedPower(AbstractCreature target, AbstractCreature source, int fadeAmt) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Constricted";
/* 20 */     this.owner = target;
/* 21 */     this.source = source;
/* 22 */     this.amount = fadeAmt;
/* 23 */     updateDescription();
/* 24 */     loadRegion("constricted");
/* 25 */     this.type = PowerType.DEBUFF;
/*    */ 
/*    */     
/* 28 */     this.priority = 105;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 33 */     CardCrawlGame.sound.play("POWER_CONSTRICTED", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 43 */     flashWithoutSound();
/* 44 */     playApplyPowerSfx();
/* 45 */     addToBot((AbstractGameAction)new DamageAction(this.owner, new DamageInfo(this.source, this.amount, DamageInfo.DamageType.THORNS)));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ConstrictedPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */