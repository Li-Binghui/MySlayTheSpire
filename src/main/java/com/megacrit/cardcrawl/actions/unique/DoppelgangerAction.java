/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class DoppelgangerAction
/*    */   extends AbstractGameAction {
/*    */   private boolean freeToPlayOnce;
/*    */   private boolean upgraded;
/*    */   
/*    */   public DoppelgangerAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
/* 19 */     this.p = p;
/* 20 */     this.upgraded = upgraded;
/* 21 */     this.freeToPlayOnce = freeToPlayOnce;
/* 22 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 23 */     this.actionType = ActionType.SPECIAL;
/* 24 */     this.energyOnUse = energyOnUse;
/*    */   }
/*    */   private AbstractPlayer p; private int energyOnUse;
/*    */   
/*    */   public void update() {
/* 29 */     int effect = EnergyPanel.totalCount;
/* 30 */     if (this.energyOnUse != -1) {
/* 31 */       effect = this.energyOnUse;
/*    */     }
/*    */     
/* 34 */     if (this.p.hasRelic("Chemical X")) {
/* 35 */       effect += 2;
/* 36 */       this.p.getRelic("Chemical X").flash();
/*    */     } 
/*    */     
/* 39 */     if (this.upgraded) {
/* 40 */       effect++;
/*    */     }
/*    */     
/* 43 */     if (effect > 0) {
/* 44 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.p, (AbstractCreature)this.p, (AbstractPower)new EnergizedPower((AbstractCreature)this.p, effect), effect));
/* 45 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.p, (AbstractCreature)this.p, (AbstractPower)new DrawCardNextTurnPower((AbstractCreature)this.p, effect), effect));
/*    */       
/* 47 */       if (!this.freeToPlayOnce) {
/* 48 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 51 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\DoppelgangerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */