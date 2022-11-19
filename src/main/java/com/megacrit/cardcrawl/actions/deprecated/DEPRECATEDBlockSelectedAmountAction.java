/*    */ package com.megacrit.cardcrawl.actions.deprecated;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class DEPRECATEDBlockSelectedAmountAction
/*    */   extends AbstractGameAction {
/*    */   public DEPRECATEDBlockSelectedAmountAction(AbstractCreature target, AbstractCreature source, int multiplier) {
/* 11 */     setValues(target, source, multiplier);
/* 12 */     this.actionType = ActionType.BLOCK;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (this.duration == 0.5F) {
/* 18 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SHIELD));
/* 19 */       this.amount *= AbstractDungeon.handCardSelectScreen.numSelected;
/* 20 */       this.target.addBlock(this.amount);
/*    */     } 
/*    */     
/* 23 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\deprecated\DEPRECATEDBlockSelectedAmountAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */