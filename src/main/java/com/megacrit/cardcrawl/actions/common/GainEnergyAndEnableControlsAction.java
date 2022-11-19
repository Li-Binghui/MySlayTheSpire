/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ public class GainEnergyAndEnableControlsAction
/*    */   extends AbstractGameAction {
/*    */   public GainEnergyAndEnableControlsAction(int amount) {
/* 13 */     setValues((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 0);
/*    */     
/* 15 */     this.energyGain = amount;
/*    */   }
/*    */   private int energyGain;
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == 0.5F) {
/* 21 */       AbstractDungeon.player.gainEnergy(this.energyGain);
/* 22 */       AbstractDungeon.actionManager.updateEnergyGain(this.energyGain);
/* 23 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 24 */         c.triggerOnGainEnergy(this.energyGain, false);
/*    */       }
/* 26 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 27 */         r.onEnergyRecharge();
/*    */       }
/* 29 */       for (AbstractPower p : AbstractDungeon.player.powers) {
/* 30 */         p.onEnergyRecharge();
/*    */       }
/* 32 */       AbstractDungeon.actionManager.turnHasEnded = false;
/*    */     } 
/*    */     
/* 35 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\GainEnergyAndEnableControlsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */