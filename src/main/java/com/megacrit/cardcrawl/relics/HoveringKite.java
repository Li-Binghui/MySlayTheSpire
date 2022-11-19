/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class HoveringKite extends AbstractRelic {
/*    */   public static final String ID = "HoveringKite";
/*    */   
/*    */   public HoveringKite() {
/* 12 */     super("HoveringKite", "kite.png", RelicTier.BOSS, LandingSound.MAGICAL);
/*    */   }
/*    */   private boolean triggeredThisTurn = false;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 22 */     this.triggeredThisTurn = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onManualDiscard() {
/* 27 */     if (!this.triggeredThisTurn) {
/* 28 */       this.triggeredThisTurn = true;
/* 29 */       flash();
/* 30 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 31 */       addToBot((AbstractGameAction)new GainEnergyAction(1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 37 */     return new HoveringKite();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\HoveringKite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */