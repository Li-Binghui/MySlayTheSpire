/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class CannotChangeStancePower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("CannotChangeStancePower"); public static final String POWER_ID = "CannotChangeStancePower";
/*    */   
/*    */   public CannotChangeStancePower(AbstractCreature owner) {
/* 14 */     this.name = powerStrings.NAME;
/* 15 */     this.ID = "CannotChangeStancePower";
/* 16 */     this.owner = owner;
/* 17 */     updateDescription();
/* 18 */     loadRegion("no_stance");
/* 19 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 24 */     if (isPlayer) {
/* 25 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "CannotChangeStancePower"));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 31 */     this.description = powerStrings.DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\CannotChangeStancePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */