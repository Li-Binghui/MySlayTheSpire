/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DarkImpulseAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 19 */     if (this.duration == Settings.ACTION_DUR_FAST && 
/* 20 */       !AbstractDungeon.player.orbs.isEmpty()) {
/* 21 */       for (AbstractOrb o : AbstractDungeon.player.orbs) {
/* 22 */         if (o instanceof com.megacrit.cardcrawl.orbs.Dark) {
/* 23 */           o.onStartOfTurn();
/* 24 */           o.onEndOfTurn();
/*    */         } 
/*    */       } 
/*    */       
/* 28 */       if (AbstractDungeon.player.hasRelic("Cables") && !(AbstractDungeon.player.orbs.get(0) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot))
/*    */       {
/* 30 */         if (AbstractDungeon.player.orbs.get(0) instanceof com.megacrit.cardcrawl.orbs.Dark) {
/* 31 */           ((AbstractOrb)AbstractDungeon.player.orbs.get(0)).onStartOfTurn();
/* 32 */           ((AbstractOrb)AbstractDungeon.player.orbs.get(0)).onEndOfTurn();
/*    */         } 
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 38 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\DarkImpulseAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */