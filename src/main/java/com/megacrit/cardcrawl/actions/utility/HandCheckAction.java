/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HandCheckAction
/*    */   extends AbstractGameAction
/*    */ {
/* 12 */   private AbstractPlayer player = AbstractDungeon.player;
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     this.player.hand.applyPowers();
/* 18 */     this.player.hand.glowCheck();
/* 19 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\HandCheckAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */