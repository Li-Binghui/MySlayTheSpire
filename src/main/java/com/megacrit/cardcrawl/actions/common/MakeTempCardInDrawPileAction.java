/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MakeTempCardInDrawPileAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractCard cardToMake;
/*    */   private boolean randomSpot;
/*    */   private boolean autoPosition;
/*    */   private boolean toBottom;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public MakeTempCardInDrawPileAction(AbstractCard card, int amount, boolean randomSpot, boolean autoPosition, boolean toBottom, float cardX, float cardY) {
/* 28 */     UnlockTracker.markCardAsSeen(card.cardID);
/* 29 */     setValues(this.target, this.source, amount);
/* 30 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 31 */     this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
/* 32 */     this.duration = this.startDuration;
/* 33 */     this.cardToMake = card;
/* 34 */     this.randomSpot = randomSpot;
/* 35 */     this.autoPosition = autoPosition;
/* 36 */     this.toBottom = toBottom;
/* 37 */     this.x = cardX;
/* 38 */     this.y = cardY;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MakeTempCardInDrawPileAction(AbstractCard card, int amount, boolean randomSpot, boolean autoPosition, boolean toBottom) {
/* 47 */     this(card, amount, randomSpot, autoPosition, toBottom, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/*    */   }
/*    */   
/*    */   public MakeTempCardInDrawPileAction(AbstractCard card, int amount, boolean shuffleInto, boolean autoPosition) {
/* 51 */     this(card, amount, shuffleInto, autoPosition, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 56 */     if (this.duration == this.startDuration) {
/*    */ 
/*    */       
/* 59 */       if (this.amount < 6) {
/* 60 */         for (int i = 0; i < this.amount; i++) {
/* 61 */           AbstractCard c = this.cardToMake.makeStatEquivalentCopy();
/*    */           
/* 63 */           if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower"))
/*    */           {
/* 65 */             c.upgrade();
/*    */           }
/*    */           
/* 68 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(c, this.x, this.y, this.randomSpot, this.autoPosition, this.toBottom));
/*    */         } 
/*    */       } else {
/*    */         
/* 72 */         for (int i = 0; i < this.amount; i++) {
/* 73 */           AbstractCard c = this.cardToMake.makeStatEquivalentCopy();
/*    */           
/* 75 */           if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower"))
/*    */           {
/* 77 */             c.upgrade();
/*    */           }
/* 79 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(c, this.randomSpot, this.toBottom));
/*    */         } 
/*    */       } 
/* 82 */       this.duration -= Gdx.graphics.getDeltaTime();
/*    */     } 
/*    */     
/* 85 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\MakeTempCardInDrawPileAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */