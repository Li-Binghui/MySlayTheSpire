/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class GainEnergyIfDiscardAction
/*    */   extends AbstractGameAction {
/*    */   public GainEnergyIfDiscardAction(int amount) {
/* 13 */     setValues((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 0);
/* 14 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */     
/* 16 */     this.energyGain = amount;
/*    */   }
/*    */   private int energyGain;
/*    */   
/*    */   public void update() {
/* 21 */     if (GameActionManager.totalDiscardedThisTurn > 0) {
/* 22 */       AbstractDungeon.player.gainEnergy(this.energyGain);
/* 23 */       AbstractDungeon.actionManager.updateEnergyGain(this.energyGain);
/* 24 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 25 */         c.triggerOnGainEnergy(this.energyGain, true);
/*    */       }
/*    */     } 
/*    */     
/* 29 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\GainEnergyIfDiscardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */