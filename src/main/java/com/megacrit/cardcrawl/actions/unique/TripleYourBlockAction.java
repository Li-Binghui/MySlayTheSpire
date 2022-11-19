/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class TripleYourBlockAction
/*    */   extends AbstractGameAction {
/*    */   public TripleYourBlockAction(AbstractCreature target) {
/* 11 */     this.duration = 0.5F;
/* 12 */     this.actionType = ActionType.BLOCK;
/* 13 */     this.target = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 18 */     if (this.duration == 0.5F && 
/* 19 */       this.target != null && this.target.currentBlock > 0) {
/* 20 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SHIELD));
/* 21 */       this.target.addBlock(this.target.currentBlock * 2);
/*    */     } 
/*    */ 
/*    */     
/* 25 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\TripleYourBlockAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */