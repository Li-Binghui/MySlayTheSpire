/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class WraithFormPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Wraith Form v2"); public static final String POWER_ID = "Wraith Form v2";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public WraithFormPower(AbstractCreature owner, int amount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Wraith Form v2";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amount;
/* 20 */     updateDescription();
/* 21 */     loadRegion("wraithForm");
/* 22 */     this.canGoNegative = true;
/* 23 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 28 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, new DexterityPower((AbstractCreature)AbstractDungeon.player, this.amount), this.amount));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 38 */     this.fontScale = 8.0F;
/* 39 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 44 */     this.description = DESCRIPTIONS[0] + -this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\WraithFormPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */