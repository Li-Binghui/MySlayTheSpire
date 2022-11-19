/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
/*    */ 
/*    */ public class MakeTempCardInDiscardAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractCard c;
/*    */   private int numCards;
/*    */   private boolean sameUUID;
/*    */   
/*    */   public MakeTempCardInDiscardAction(AbstractCard card, int amount) {
/* 19 */     UnlockTracker.markCardAsSeen(card.cardID);
/* 20 */     this.numCards = amount;
/* 21 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 22 */     this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
/* 23 */     this.duration = this.startDuration;
/* 24 */     this.c = card;
/* 25 */     this.sameUUID = false;
/*    */   }
/*    */   
/*    */   public MakeTempCardInDiscardAction(AbstractCard card, boolean sameUUID) {
/* 29 */     this(card, 1);
/* 30 */     this.sameUUID = sameUUID;
/*    */     
/* 32 */     if (!sameUUID && this.c.type != AbstractCard.CardType.CURSE && this.c.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower"))
/*    */     {
/* 34 */       this.c.upgrade();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 40 */     if (this.duration == this.startDuration) {
/*    */       
/* 42 */       if (this.numCards < 6) {
/* 43 */         for (int i = 0; i < this.numCards; i++) {
/* 44 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(makeNewCard()));
/*    */         }
/*    */       }
/* 47 */       this.duration -= Gdx.graphics.getDeltaTime();
/*    */     } 
/*    */     
/* 50 */     tickDuration();
/*    */   }
/*    */   
/*    */   private AbstractCard makeNewCard() {
/* 54 */     if (this.sameUUID) {
/* 55 */       return this.c.makeSameInstanceOf();
/*    */     }
/* 57 */     return this.c.makeStatEquivalentCopy();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\MakeTempCardInDiscardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */