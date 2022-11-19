/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class FTLAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private AbstractCreature target;
/* 13 */   private int cardPlayCount = 0;
/*    */   
/*    */   public FTLAction(AbstractCreature target, DamageInfo info, int cardPlayCount) {
/* 16 */     this.info = info;
/* 17 */     this.target = target;
/* 18 */     this.cardPlayCount = cardPlayCount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     addToBot((AbstractGameAction)new DamageAction(this.target, this.info, AttackEffect.SLASH_HORIZONTAL));
/* 24 */     if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1 < this.cardPlayCount) {
/* 25 */       addToTop((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 1));
/*    */     }
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\FTLAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */