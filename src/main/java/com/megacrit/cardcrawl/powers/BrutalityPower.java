/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class BrutalityPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Brutality"); public static final String POWER_ID = "Brutality";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public BrutalityPower(AbstractCreature owner, int drawAmount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Brutality";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = drawAmount;
/* 20 */     updateDescription();
/* 21 */     loadRegion("brutality");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 26 */     if (this.amount == 1) {
/* 27 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } else {
/* 29 */       this.description = DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.amount + DESCRIPTIONS[5];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 35 */     flash();
/* 36 */     addToBot((AbstractGameAction)new DrawCardAction(this.owner, this.amount));
/* 37 */     addToBot((AbstractGameAction)new LoseHPAction(this.owner, this.owner, this.amount));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\BrutalityPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */