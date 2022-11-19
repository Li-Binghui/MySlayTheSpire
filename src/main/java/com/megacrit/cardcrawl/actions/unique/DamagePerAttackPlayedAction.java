/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DamagePerAttackPlayedAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   
/*    */   public DamagePerAttackPlayedAction(AbstractCreature target, DamageInfo info, AttackEffect effect) {
/* 15 */     this.info = info;
/* 16 */     setValues(target, info);
/* 17 */     this.actionType = ActionType.DAMAGE;
/* 18 */     this.attackEffect = effect;
/*    */   }
/*    */   
/*    */   public DamagePerAttackPlayedAction(AbstractCreature target, DamageInfo info) {
/* 22 */     this(target, info, AttackEffect.NONE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 27 */     this.isDone = true;
/* 28 */     if (this.target != null && this.target.currentHealth > 0) {
/* 29 */       int count = 0;
/* 30 */       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
/* 31 */         if (c.type == AbstractCard.CardType.ATTACK) {
/* 32 */           count++;
/*    */         }
/*    */       } 
/*    */       
/* 36 */       count--;
/* 37 */       for (int i = 0; i < count; i++)
/* 38 */         addToTop((AbstractGameAction)new DamageAction(this.target, this.info, this.attackEffect)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\DamagePerAttackPlayedAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */