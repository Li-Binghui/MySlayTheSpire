/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class EnergyBlockAction
/*    */   extends AbstractGameAction {
/*    */   public EnergyBlockAction(boolean upgraded) {
/* 13 */     this.duration = Settings.ACTION_DUR_FAST;
/* 14 */     this.upg = upgraded;
/*    */   }
/*    */   private boolean upg = false;
/*    */   
/*    */   public void update() {
/* 19 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 20 */       if (this.upg) {
/* 21 */         addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, EnergyPanel.totalCount * 2));
/*    */       } else {
/*    */         
/* 24 */         addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, EnergyPanel.totalCount));
/*    */       } 
/*    */     }
/*    */     
/* 28 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\EnergyBlockAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */