/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class BrillianceAction extends AbstractGameAction {
/*    */   public int[] multiDamage;
/*    */   private boolean freeToPlayOnce = false;
/*    */   private AbstractPlayer p;
/* 14 */   private int energyOnUse = -1;
/*    */   
/*    */   public BrillianceAction(AbstractPlayer p, int amount, boolean freeToPlayOnce, int energyOnUse) {
/* 17 */     this.amount = amount;
/* 18 */     this.p = p;
/* 19 */     this.freeToPlayOnce = freeToPlayOnce;
/* 20 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 21 */     this.actionType = ActionType.SPECIAL;
/* 22 */     this.energyOnUse = energyOnUse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 27 */     int effect = EnergyPanel.totalCount;
/* 28 */     if (this.energyOnUse != -1) {
/* 29 */       effect = this.energyOnUse;
/*    */     }
/*    */     
/* 32 */     if (this.p.hasRelic("Chemical X")) {
/* 33 */       effect += 2;
/* 34 */       this.p.getRelic("Chemical X").flash();
/*    */     } 
/*    */     
/* 37 */     if (effect > 0) {
/* 38 */       addToBot((AbstractGameAction)new HealAction((AbstractCreature)this.p, (AbstractCreature)this.p, effect * this.amount));
/*    */       
/* 40 */       if (!this.freeToPlayOnce) {
/* 41 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 44 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\BrillianceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */