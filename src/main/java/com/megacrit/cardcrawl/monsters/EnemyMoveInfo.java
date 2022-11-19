/*    */ package com.megacrit.cardcrawl.monsters;
/*    */ 
/*    */ 
/*    */ public class EnemyMoveInfo
/*    */ {
/*    */   public byte nextMove;
/*    */   public AbstractMonster.Intent intent;
/*    */   public int baseDamage;
/*    */   public int multiplier;
/*    */   public boolean isMultiDamage;
/*    */   
/*    */   public EnemyMoveInfo(byte nextMove, AbstractMonster.Intent intent, int intentBaseDmg, int multiplier, boolean isMultiDamage) {
/* 13 */     this.nextMove = nextMove;
/* 14 */     this.intent = intent;
/* 15 */     this.baseDamage = intentBaseDmg;
/* 16 */     this.multiplier = multiplier;
/* 17 */     this.isMultiDamage = isMultiDamage;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\monsters\EnemyMoveInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */