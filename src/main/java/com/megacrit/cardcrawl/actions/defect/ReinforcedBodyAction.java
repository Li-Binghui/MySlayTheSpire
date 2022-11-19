/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class ReinforcedBodyAction extends AbstractGameAction {
/*    */   public int[] multiDamage;
/*    */   private boolean freeToPlayOnce = false;
/*    */   private AbstractPlayer p;
/* 14 */   private int energyOnUse = -1;
/*    */   
/*    */   public ReinforcedBodyAction(AbstractPlayer p, int amount, boolean freeToPlayOnce, int energyOnUse) {
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
/* 38 */       for (int i = 0; i < effect; i++) {
/* 39 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this.p, (AbstractCreature)this.p, this.amount));
/*    */       }
/*    */       
/* 42 */       if (!this.freeToPlayOnce) {
/* 43 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 46 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\ReinforcedBodyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */