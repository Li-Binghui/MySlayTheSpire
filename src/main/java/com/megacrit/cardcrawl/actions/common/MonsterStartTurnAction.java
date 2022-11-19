/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MonsterStartTurnAction
/*    */   extends AbstractGameAction
/*    */ {
/* 12 */   private static final float DURATION = Settings.ACTION_DUR_FAST;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == DURATION) {
/* 21 */       this.isDone = true;
/* 22 */       (AbstractDungeon.getCurrRoom()).monsters.applyPreTurnLogic();
/*    */     } 
/* 24 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\MonsterStartTurnAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */