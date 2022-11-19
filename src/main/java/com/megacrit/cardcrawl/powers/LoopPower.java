/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ public class LoopPower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Loop"); public static final String POWER_ID = "Loop";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public LoopPower(AbstractCreature owner, int amt) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "Loop";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = amt;
/* 19 */     updateDescription();
/* 20 */     loadRegion("loop");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 25 */     if (!AbstractDungeon.player.orbs.isEmpty()) {
/* 26 */       flash();
/* 27 */       for (int i = 0; i < this.amount; i++) {
/* 28 */         ((AbstractOrb)AbstractDungeon.player.orbs.get(0)).onStartOfTurn();
/* 29 */         ((AbstractOrb)AbstractDungeon.player.orbs.get(0)).onEndOfTurn();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 37 */     if (this.amount <= 1) {
/* 38 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 40 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\LoopPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */