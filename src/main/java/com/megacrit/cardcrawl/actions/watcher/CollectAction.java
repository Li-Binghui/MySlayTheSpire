/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.CollectPower;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class CollectAction extends AbstractGameAction {
/*    */   private boolean freeToPlayOnce = false;
/*    */   private boolean upgraded;
/*    */   
/*    */   public CollectAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse, boolean upgraded) {
/* 17 */     this.p = p;
/* 18 */     this.freeToPlayOnce = freeToPlayOnce;
/* 19 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 20 */     this.actionType = ActionType.SPECIAL;
/* 21 */     this.energyOnUse = energyOnUse;
/* 22 */     this.upgraded = upgraded;
/*    */   }
/*    */   private AbstractPlayer p; private int energyOnUse;
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
/* 37 */     if (this.upgraded) {
/* 38 */       effect++;
/*    */     }
/*    */     
/* 41 */     if (effect > 0) {
/* 42 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.p, (AbstractCreature)this.p, (AbstractPower)new CollectPower((AbstractCreature)this.p, effect), effect));
/* 43 */       if (!this.freeToPlayOnce) {
/* 44 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 47 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\CollectAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */