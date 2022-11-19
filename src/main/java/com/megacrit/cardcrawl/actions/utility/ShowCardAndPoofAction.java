/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*    */ 
/*    */ public class ShowCardAndPoofAction extends AbstractGameAction {
/*  9 */   private AbstractCard card = null;
/*    */   private static final float PURGE_DURATION = 0.2F;
/*    */   
/*    */   public ShowCardAndPoofAction(AbstractCard card) {
/* 13 */     setValues((AbstractCreature)AbstractDungeon.player, null, 1);
/* 14 */     this.card = card;
/* 15 */     this.duration = 0.2F;
/* 16 */     this.actionType = ActionType.SPECIAL;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == 0.2F) {
/* 22 */       AbstractDungeon.effectList.add(new ExhaustCardEffect(this.card));
/* 23 */       if (AbstractDungeon.player.limbo.contains(this.card)) {
/* 24 */         AbstractDungeon.player.limbo.removeCard(this.card);
/*    */       }
/* 26 */       AbstractDungeon.player.cardInUse = null;
/*    */     } 
/* 28 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ShowCardAndPoofAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */