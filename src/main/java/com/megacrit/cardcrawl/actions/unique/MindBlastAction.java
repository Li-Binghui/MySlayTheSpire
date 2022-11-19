/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class MindBlastAction
/*    */   extends AbstractGameAction {
/*    */   public MindBlastAction(AbstractCreature target) {
/* 13 */     setValues(target, (AbstractCreature)AbstractDungeon.player);
/* 14 */     this.duration = Settings.ACTION_DUR_FAST;
/* 15 */     this.actionType = ActionType.DAMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_FAST && 
/* 21 */       this.target != null) {
/* 22 */       DamageInfo info = new DamageInfo(this.source, AbstractDungeon.player.drawPile.size());
/* 23 */       info.applyPowers(this.source, this.target);
/* 24 */       addToTop((AbstractGameAction)new DamageAction(this.target, info, AttackEffect.NONE));
/*    */     } 
/*    */ 
/*    */     
/* 28 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\MindBlastAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */