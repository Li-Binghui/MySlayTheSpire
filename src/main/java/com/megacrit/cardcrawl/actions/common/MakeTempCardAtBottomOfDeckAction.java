/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ 
/*    */ public class MakeTempCardAtBottomOfDeckAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public MakeTempCardAtBottomOfDeckAction(int amount) {
/* 15 */     setValues(this.target, this.source, amount);
/* 16 */     this.actionType = ActionType.CARD_MANIPULATION;
/*    */     
/* 18 */     this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
/* 19 */     this.duration = this.startDuration;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == this.startDuration) {
/* 25 */       for (int i = 0; i < this.amount; i++) {
/* 26 */         AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeStatEquivalentCopy();
/* 27 */         UnlockTracker.markCardAsSeen(c.cardID);
/* 28 */         if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower"))
/*    */         {
/* 30 */           c.upgrade();
/*    */         }
/*    */         
/* 33 */         AbstractDungeon.player.drawPile.addToBottom(c);
/*    */       } 
/*    */       
/* 36 */       this.duration -= Gdx.graphics.getDeltaTime();
/*    */     } 
/*    */     
/* 39 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\MakeTempCardAtBottomOfDeckAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */