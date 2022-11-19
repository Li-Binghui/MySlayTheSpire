/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.ChannelAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.orbs.Lightning;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class TempestAction
/*    */   extends AbstractGameAction {
/*    */   private boolean freeToPlayOnce = false;
/*    */   private AbstractPlayer p;
/* 15 */   private int energyOnUse = -1;
/*    */   private boolean upgraded;
/*    */   
/*    */   public TempestAction(AbstractPlayer p, int energyOnUse, boolean upgraded, boolean freeToPlayOnce) {
/* 19 */     this.p = p;
/* 20 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 21 */     this.actionType = ActionType.SPECIAL;
/* 22 */     this.energyOnUse = energyOnUse;
/* 23 */     this.upgraded = upgraded;
/* 24 */     this.freeToPlayOnce = freeToPlayOnce;
/*    */   }
/*    */ 
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
/* 44 */       for (int i = 0; i < effect; i++) {
/* 45 */         Lightning lightning = new Lightning();
/* 46 */         addToBot((AbstractGameAction)new ChannelAction((AbstractOrb)lightning));
/*    */       } 
/*    */       
/* 49 */       if (!this.freeToPlayOnce) {
/* 50 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 53 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\TempestAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */