/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class PathVictoryAction
/*    */   extends AbstractGameAction {
/*    */   public PathVictoryAction() {
/* 14 */     if (AbstractDungeon.player.hasPower("No Draw")) {
/* 15 */       AbstractDungeon.player.getPower("No Draw").flash();
/* 16 */       setValues((AbstractCreature)AbstractDungeon.player, this.source, 1);
/* 17 */       this.isDone = true;
/* 18 */       this.duration = 0.0F;
/* 19 */       this.actionType = ActionType.WAIT;
/*    */       
/*    */       return;
/*    */     } 
/* 23 */     setValues((AbstractCreature)AbstractDungeon.player, this.source, this.amount);
/* 24 */     this.actionType = ActionType.DRAW;
/*    */     
/* 26 */     if (Settings.FAST_MODE) {
/* 27 */       this.duration = Settings.ACTION_DUR_XFAST;
/*    */     } else {
/* 29 */       this.duration = Settings.ACTION_DUR_FASTER;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 35 */     int deckSize = AbstractDungeon.player.drawPile.size();
/* 36 */     int discardSize = AbstractDungeon.player.discardPile.size();
/*    */ 
/*    */     
/* 39 */     if (SoulGroup.isActive()) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 44 */     if (deckSize + discardSize == 0) {
/* 45 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 49 */     if (AbstractDungeon.player.hand.size() == 10) {
/* 50 */       AbstractDungeon.player.createHandIsFullDialog();
/* 51 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 55 */     if (deckSize == 0 && discardSize != 0) {
/* 56 */       addToTop(new PathVictoryAction());
/* 57 */       addToTop((AbstractGameAction)new EmptyDeckShuffleAction());
/* 58 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 62 */     if (deckSize != 0) {
/* 63 */       AbstractCard c = AbstractDungeon.player.drawPile.getTopCard();
/* 64 */       c.setCostForTurn(0);
/* 65 */       AbstractDungeon.player.draw();
/* 66 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 67 */       this.isDone = true;
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\PathVictoryAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */