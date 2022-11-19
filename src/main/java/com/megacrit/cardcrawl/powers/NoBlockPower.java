/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class NoBlockPower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("NoBlockPower"); public static final String POWER_ID = "NoBlockPower";
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private boolean justApplied = false;
/*    */   
/*    */   public NoBlockPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "NoBlockPower";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amount;
/* 22 */     updateDescription();
/* 23 */     loadRegion("noBlock");
/*    */     
/* 25 */     if (AbstractDungeon.actionManager.turnHasEnded && isSourceMonster) {
/* 26 */       this.justApplied = true;
/*    */     }
/*    */     
/* 29 */     this.type = PowerType.DEBUFF;
/* 30 */     this.isTurnBased = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 35 */     if (this.justApplied) {
/* 36 */       this.justApplied = false;
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     if (this.amount == 0) {
/* 41 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "NoBlockPower"));
/*    */     } else {
/* 43 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "NoBlockPower", 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 49 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public float modifyBlockLast(float blockAmount) {
/* 54 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\NoBlockPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */