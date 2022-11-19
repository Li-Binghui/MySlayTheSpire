/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class MulticastAction
/*    */   extends AbstractGameAction {
/*    */   private boolean freeToPlayOnce = false;
/*    */   private AbstractPlayer p;
/* 16 */   private int energyOnUse = -1;
/*    */   private boolean upgraded;
/*    */   
/*    */   public MulticastAction(AbstractPlayer p, int energyOnUse, boolean upgraded, boolean freeToPlayOnce) {
/* 20 */     this.p = p;
/* 21 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 22 */     this.actionType = ActionType.SPECIAL;
/* 23 */     this.energyOnUse = energyOnUse;
/* 24 */     this.upgraded = upgraded;
/* 25 */     this.freeToPlayOnce = freeToPlayOnce;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 30 */     if (AbstractDungeon.player.hasOrb()) {
/* 31 */       int effect = EnergyPanel.totalCount;
/* 32 */       if (this.energyOnUse != -1) {
/* 33 */         effect = this.energyOnUse;
/*    */       }
/*    */       
/* 36 */       if (this.p.hasRelic("Chemical X")) {
/* 37 */         effect += 2;
/* 38 */         this.p.getRelic("Chemical X").flash();
/*    */       } 
/*    */       
/* 41 */       if (this.upgraded) {
/* 42 */         effect++;
/*    */       }
/*    */       
/* 45 */       if (effect > 0) {
/* 46 */         for (int i = 0; i < effect - 1; i++) {
/* 47 */           addToBot((AbstractGameAction)new EvokeWithoutRemovingOrbAction(1));
/*    */         }
/* 49 */         addToBot((AbstractGameAction)new AnimateOrbAction(1));
/* 50 */         addToBot((AbstractGameAction)new EvokeOrbAction(1));
/*    */         
/* 52 */         if (!this.freeToPlayOnce) {
/* 53 */           this.p.energy.use(EnergyPanel.totalCount);
/*    */         }
/*    */       } 
/*    */     } 
/* 57 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\MulticastAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */