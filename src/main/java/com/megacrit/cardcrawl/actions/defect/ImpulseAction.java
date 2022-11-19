/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImpulseAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 18 */     if (this.duration == Settings.ACTION_DUR_FAST && 
/* 19 */       !AbstractDungeon.player.orbs.isEmpty()) {
/* 20 */       for (AbstractOrb o : AbstractDungeon.player.orbs) {
/* 21 */         o.onStartOfTurn();
/* 22 */         o.onEndOfTurn();
/*    */       } 
/*    */       
/* 25 */       if (AbstractDungeon.player.hasRelic("Cables") && !(AbstractDungeon.player.orbs.get(0) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot)) {
/*    */         
/* 27 */         ((AbstractOrb)AbstractDungeon.player.orbs.get(0)).onStartOfTurn();
/* 28 */         ((AbstractOrb)AbstractDungeon.player.orbs.get(0)).onEndOfTurn();
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 33 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\ImpulseAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */