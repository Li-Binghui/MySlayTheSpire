/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DamageRandomEnemyAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   
/*    */   public DamageRandomEnemyAction(DamageInfo info, AttackEffect effect) {
/* 12 */     this.info = info;
/* 13 */     this.actionType = ActionType.DAMAGE;
/* 14 */     this.attackEffect = effect;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 19 */     this.target = (AbstractCreature)AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
/* 20 */     if (this.target != null) {
/* 21 */       addToTop(new DamageAction(this.target, this.info, this.attackEffect));
/*    */     }
/* 23 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\DamageRandomEnemyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */