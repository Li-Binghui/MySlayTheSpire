/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class SpiritShieldAction
/*    */   extends AbstractGameAction {
/*    */   public SpiritShieldAction(int blockPerCard) {
/* 11 */     this.blockPerCard = blockPerCard;
/*    */   }
/*    */   private int blockPerCard;
/*    */   
/*    */   public void update() {
/* 16 */     if (!AbstractDungeon.player.hand.isEmpty()) {
/* 17 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, AbstractDungeon.player.hand.group
/*    */ 
/*    */ 
/*    */             
/* 21 */             .size() * this.blockPerCard));
/*    */     }
/*    */     
/* 24 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\SpiritShieldAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */