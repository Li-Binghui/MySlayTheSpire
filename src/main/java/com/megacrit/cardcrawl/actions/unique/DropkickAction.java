/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DropkickAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   
/*    */   public DropkickAction(AbstractCreature target, DamageInfo info) {
/* 16 */     this.actionType = ActionType.BLOCK;
/* 17 */     this.target = target;
/* 18 */     this.info = info;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (this.target != null && this.target.hasPower("Vulnerable")) {
/* 24 */       addToTop((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 1));
/* 25 */       addToTop((AbstractGameAction)new GainEnergyAction(1));
/*    */     } 
/*    */     
/* 28 */     addToTop((AbstractGameAction)new DamageAction(this.target, this.info, AttackEffect.BLUNT_HEAVY));
/* 29 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\DropkickAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */