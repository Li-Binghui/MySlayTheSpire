/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class ShowCardAction extends AbstractGameAction {
/*  8 */   private AbstractCard card = null;
/*    */   private static final float PURGE_DURATION = 0.2F;
/*    */   
/*    */   public ShowCardAction(AbstractCard card) {
/* 12 */     setValues((AbstractCreature)AbstractDungeon.player, null, 1);
/* 13 */     this.card = card;
/* 14 */     this.duration = 0.2F;
/* 15 */     this.actionType = ActionType.SPECIAL;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == 0.2F) {
/*    */       
/* 22 */       if (AbstractDungeon.player.limbo.contains(this.card)) {
/* 23 */         AbstractDungeon.player.limbo.removeCard(this.card);
/*    */       }
/* 25 */       AbstractDungeon.player.cardInUse = null;
/*    */     } 
/* 27 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ShowCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */