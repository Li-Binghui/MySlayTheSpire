/*    */ package com.megacrit.cardcrawl.core;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class EnergyManager
/*    */ {
/*    */   public int energy;
/*    */   public int energyMaster;
/*    */   
/*    */   public EnergyManager(int e) {
/* 15 */     this.energyMaster = e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void prep() {
/* 22 */     this.energy = this.energyMaster;
/* 23 */     EnergyPanel.totalCount = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void recharge() {
/* 31 */     if (AbstractDungeon.player.hasRelic("Ice Cream")) {
/* 32 */       if (EnergyPanel.totalCount > 0) {
/* 33 */         AbstractDungeon.player.getRelic("Ice Cream").flash();
/* 34 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, AbstractDungeon.player
/* 35 */               .getRelic("Ice Cream")));
/*    */       } 
/* 37 */       EnergyPanel.addEnergy(this.energy);
/* 38 */     } else if (AbstractDungeon.player.hasPower("Conserve")) {
/* 39 */       if (EnergyPanel.totalCount > 0) {
/* 40 */         AbstractDungeon.actionManager.addToTop((AbstractGameAction)new ReducePowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, "Conserve", 1));
/*    */       }
/*    */       
/* 43 */       EnergyPanel.addEnergy(this.energy);
/*    */     } else {
/* 45 */       EnergyPanel.setEnergy(this.energy);
/*    */     } 
/* 47 */     AbstractDungeon.actionManager.updateEnergyGain(this.energy);
/*    */   }
/*    */   
/*    */   public void use(int e) {
/* 51 */     EnergyPanel.useEnergy(e);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\core\EnergyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */