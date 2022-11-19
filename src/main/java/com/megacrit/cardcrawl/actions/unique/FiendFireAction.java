/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class FiendFireAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private float startingDuration;
/*    */   
/*    */   public FiendFireAction(AbstractCreature target, DamageInfo info) {
/* 16 */     this.info = info;
/* 17 */     setValues(target, info);
/* 18 */     this.actionType = ActionType.WAIT;
/* 19 */     this.attackEffect = AttackEffect.FIRE;
/* 20 */     this.startingDuration = Settings.ACTION_DUR_FAST;
/* 21 */     this.duration = this.startingDuration;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     int count = AbstractDungeon.player.hand.size();
/*    */     int i;
/* 28 */     for (i = 0; i < count; i++) {
/* 29 */       addToTop((AbstractGameAction)new DamageAction(this.target, this.info, AttackEffect.FIRE));
/*    */     }
/* 31 */     for (i = 0; i < count; i++) {
/* 32 */       if (Settings.FAST_MODE) {
/* 33 */         addToTop((AbstractGameAction)new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
/*    */       } else {
/* 35 */         addToTop((AbstractGameAction)new ExhaustAction(1, true, true));
/*    */       } 
/*    */     } 
/*    */     
/* 39 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\FiendFireAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */