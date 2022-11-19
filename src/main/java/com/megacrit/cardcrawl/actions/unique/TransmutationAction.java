/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class TransmutationAction
/*    */   extends AbstractGameAction {
/*    */   private boolean freeToPlayOnce;
/*    */   private boolean upgraded;
/*    */   private AbstractPlayer p;
/*    */   private int energyOnUse;
/*    */   
/*    */   public TransmutationAction(AbstractPlayer p, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
/* 19 */     this.p = p;
/* 20 */     this.upgraded = upgraded;
/* 21 */     this.freeToPlayOnce = freeToPlayOnce;
/* 22 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 23 */     this.actionType = ActionType.SPECIAL;
/* 24 */     this.energyOnUse = energyOnUse;
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
/* 39 */     if (effect > 0) {
/* 40 */       for (int i = 0; i < effect; i++) {
/* 41 */         AbstractCard c = AbstractDungeon.returnTrulyRandomColorlessCardInCombat().makeCopy();
/* 42 */         if (this.upgraded) {
/* 43 */           c.upgrade();
/*    */         }
/* 45 */         c.setCostForTurn(0);
/* 46 */         addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
/*    */       } 
/*    */       
/* 49 */       if (!this.freeToPlayOnce) {
/* 50 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 53 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\TransmutationAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */