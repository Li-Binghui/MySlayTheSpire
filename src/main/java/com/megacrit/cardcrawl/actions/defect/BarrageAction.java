/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class BarrageAction
/*    */   extends AbstractGameAction {
/* 11 */   private DamageInfo info = null;
/*    */   private AbstractCreature target;
/*    */   
/*    */   public BarrageAction(AbstractCreature m, DamageInfo info) {
/* 15 */     this.info = info;
/* 16 */     this.target = m;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
/* 22 */       if (!(AbstractDungeon.player.orbs.get(i) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot)) {
/* 23 */         addToTop((AbstractGameAction)new DamageAction(this.target, this.info, AttackEffect.BLUNT_LIGHT, true));
/*    */       }
/*    */     } 
/*    */     
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\BarrageAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */