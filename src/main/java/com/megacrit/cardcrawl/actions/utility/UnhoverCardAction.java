/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnhoverCardAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 17 */     if (this.duration == Settings.ACTION_DUR_FAST && 
/* 18 */       AbstractDungeon.player.hoveredCard != null) {
/* 19 */       AbstractDungeon.effectList.add(new ExhaustCardEffect(AbstractDungeon.player.hoveredCard));
/* 20 */       AbstractDungeon.player.hoveredCard = null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\UnhoverCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */