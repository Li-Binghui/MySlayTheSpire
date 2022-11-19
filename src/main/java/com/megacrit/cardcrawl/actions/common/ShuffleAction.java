/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ public class ShuffleAction extends AbstractGameAction {
/*    */   private CardGroup group;
/*    */   private boolean triggerRelics;
/*    */   
/*    */   public ShuffleAction(CardGroup theGroup) {
/* 13 */     this(theGroup, false);
/*    */   }
/*    */   
/*    */   public ShuffleAction(CardGroup theGroup, boolean trigger) {
/* 17 */     setValues(null, null, 0);
/* 18 */     this.duration = 0.0F;
/* 19 */     this.actionType = ActionType.SHUFFLE;
/* 20 */     this.group = theGroup;
/* 21 */     this.triggerRelics = trigger;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (this.triggerRelics) {
/* 27 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 28 */         r.onShuffle();
/*    */       }
/*    */     }
/* 31 */     this.group.shuffle();
/* 32 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ShuffleAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */