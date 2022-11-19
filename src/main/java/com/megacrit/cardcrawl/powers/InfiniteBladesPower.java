/*    */ package com.megacrit.cardcrawl.powers;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.tempCards.Shiv;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class InfiniteBladesPower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Infinite Blades"); public static final String POWER_ID = "Infinite Blades";
/*    */   
/*    */   public InfiniteBladesPower(AbstractCreature owner, int bladeAmt) {
/* 15 */     this.name = powerStrings.NAME;
/* 16 */     this.ID = "Infinite Blades";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = bladeAmt;
/* 19 */     updateDescription();
/* 20 */     loadRegion("infiniteBlades");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 25 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 26 */       flash();
/* 27 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Shiv(), this.amount, false));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 33 */     this.fontScale = 8.0F;
/* 34 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 39 */     if (this.amount > 1) {
/* 40 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */     } else {
/* 42 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\InfiniteBladesPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */