/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class GainEnergyAction
/*    */   extends AbstractGameAction {
/*    */   public GainEnergyAction(int amount) {
/* 12 */     setValues((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 0);
/* 13 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */     
/* 15 */     this.energyGain = amount;
/*    */   }
/*    */   private int energyGain;
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 21 */       AbstractDungeon.player.gainEnergy(this.energyGain);
/* 22 */       AbstractDungeon.actionManager.updateEnergyGain(this.energyGain);
/* 23 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 24 */         c.triggerOnGainEnergy(this.energyGain, true);
/*    */       }
/*    */     } 
/*    */     
/* 28 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\GainEnergyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */