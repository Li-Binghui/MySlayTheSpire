/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TranscendenceAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 17 */     if (this.duration == Settings.ACTION_DUR_MED) {
/* 18 */       AbstractPlayer p = AbstractDungeon.player;
/*    */       
/* 20 */       for (AbstractCard c : p.hand.group) {
/* 21 */         if (c.canUpgrade() && (c.retain || c.selfRetain)) {
/* 22 */           c.superFlash();
/* 23 */           c.upgrade();
/* 24 */           c.applyPowers();
/*    */         } 
/*    */       } 
/*    */       
/* 28 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\TranscendenceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */