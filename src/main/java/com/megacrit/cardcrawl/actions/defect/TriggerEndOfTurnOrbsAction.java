/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TriggerEndOfTurnOrbsAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 16 */     if (!AbstractDungeon.player.orbs.isEmpty()) {
/* 17 */       for (AbstractOrb o : AbstractDungeon.player.orbs) {
/* 18 */         o.onEndOfTurn();
/*    */       }
/*    */       
/* 21 */       if (AbstractDungeon.player.hasRelic("Cables") && !(AbstractDungeon.player.orbs.get(0) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot))
/*    */       {
/* 23 */         ((AbstractOrb)AbstractDungeon.player.orbs.get(0)).onEndOfTurn();
/*    */       }
/*    */     } 
/* 26 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\TriggerEndOfTurnOrbsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */