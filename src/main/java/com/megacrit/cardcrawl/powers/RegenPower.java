/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.RegenAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class RegenPower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Regeneration"); public static final String POWER_ID = "Regeneration";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public RegenPower(AbstractCreature owner, int heal) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "Regeneration";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = heal;
/* 19 */     updateDescription();
/* 20 */     loadRegion("regen");
/* 21 */     this.type = PowerType.BUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 26 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 31 */     flashWithoutSound();
/* 32 */     addToTop((AbstractGameAction)new RegenAction(this.owner, this.amount));
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 37 */     super.stackPower(stackAmount);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\RegenPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */