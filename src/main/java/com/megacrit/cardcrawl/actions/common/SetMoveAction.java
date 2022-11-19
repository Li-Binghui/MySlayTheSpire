/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetMoveAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractMonster monster;
/*    */   private byte theNextMove;
/*    */   private AbstractMonster.Intent theNextIntent;
/*    */   private int theNextDamage;
/*    */   private String theNextName;
/*    */   private int theMultiplier;
/*    */   private boolean isMultiplier = false;
/*    */   
/*    */   public SetMoveAction(AbstractMonster monster, String moveName, byte nextMove, AbstractMonster.Intent intent, int baseDamage, int multiplierAmt, boolean multiplier) {
/* 23 */     this.theNextName = moveName;
/* 24 */     this.theNextMove = nextMove;
/* 25 */     this.theNextIntent = intent;
/* 26 */     this.theNextDamage = baseDamage;
/* 27 */     this.monster = monster;
/* 28 */     this.theMultiplier = multiplierAmt;
/* 29 */     this.isMultiplier = multiplier;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SetMoveAction(AbstractMonster monster, byte nextMove, AbstractMonster.Intent intent, int baseDamage, int multiplierAmt, boolean multiplier) {
/* 39 */     this(monster, null, nextMove, intent, baseDamage, multiplierAmt, multiplier);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SetMoveAction(AbstractMonster monster, String moveName, byte nextMove, AbstractMonster.Intent intent, int baseDamage) {
/* 48 */     this(monster, moveName, nextMove, intent, baseDamage, 0, false);
/*    */   }
/*    */   
/*    */   public SetMoveAction(AbstractMonster monster, String moveName, byte nextMove, AbstractMonster.Intent intent) {
/* 52 */     this(monster, moveName, nextMove, intent, -1);
/*    */   }
/*    */   
/*    */   public SetMoveAction(AbstractMonster monster, byte nextMove, AbstractMonster.Intent intent, int baseDamage) {
/* 56 */     this(monster, null, nextMove, intent, baseDamage);
/*    */   }
/*    */   
/*    */   public SetMoveAction(AbstractMonster monster, byte nextMove, AbstractMonster.Intent intent) {
/* 60 */     this(monster, null, nextMove, intent, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 65 */     this.monster.setMove(this.theNextName, this.theNextMove, this.theNextIntent, this.theNextDamage, this.theMultiplier, this.isMultiplier);
/* 66 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\SetMoveAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */