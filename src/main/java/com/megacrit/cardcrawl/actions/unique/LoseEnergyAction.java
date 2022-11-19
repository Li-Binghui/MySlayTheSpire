/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class LoseEnergyAction
/*    */   extends AbstractGameAction {
/*    */   public LoseEnergyAction(int amount) {
/* 11 */     setValues((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 0);
/* 12 */     this.energyLoss = amount;
/* 13 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */   private int energyLoss;
/*    */   
/*    */   public void update() {
/* 18 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 19 */       AbstractDungeon.player.loseEnergy(this.energyLoss);
/*    */     }
/*    */     
/* 22 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\LoseEnergyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */