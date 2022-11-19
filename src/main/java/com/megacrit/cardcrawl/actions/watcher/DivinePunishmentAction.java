/*    */ package com.megacrit.cardcrawl.actions.watcher;
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
/*    */ public class DivinePunishmentAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   private boolean freeToPlayOnce;
/*    */   private int energyOnUse;
/*    */   
/*    */   public DivinePunishmentAction(AbstractCard cardToGain, boolean freeToPlayOnce, int energyOnUse) {
/* 18 */     this.card = cardToGain;
/* 19 */     this.freeToPlayOnce = freeToPlayOnce;
/* 20 */     this.energyOnUse = energyOnUse;
/* 21 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 22 */     this.actionType = ActionType.SPECIAL;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 27 */     AbstractPlayer p = AbstractDungeon.player;
/*    */     
/* 29 */     int effect = EnergyPanel.totalCount;
/* 30 */     if (this.energyOnUse != -1) {
/* 31 */       effect = this.energyOnUse;
/*    */     }
/*    */     
/* 34 */     if (p.hasRelic("Chemical X")) {
/* 35 */       effect += 2;
/* 36 */       p.getRelic("Chemical X").flash();
/*    */     } 
/*    */     
/* 39 */     if (effect > 0) {
/* 40 */       for (int i = 0; i < effect; i++) {
/* 41 */         addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.card.makeStatEquivalentCopy()));
/*    */       }
/*    */       
/* 44 */       if (!this.freeToPlayOnce) {
/* 45 */         p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     } 
/* 48 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\DivinePunishmentAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */