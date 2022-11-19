/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RedoAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractOrb orb;
/*    */   
/*    */   public void update() {
/* 17 */     if (!AbstractDungeon.player.orbs.isEmpty()) {
/* 18 */       this.orb = AbstractDungeon.player.orbs.get(0);
/* 19 */       if (this.orb instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot) {
/* 20 */         this.isDone = true;
/*    */       } else {
/* 22 */         addToTop(new ChannelAction(this.orb, false));
/* 23 */         addToTop(new EvokeOrbAction(1));
/*    */       } 
/*    */     } 
/*    */     
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\RedoAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */