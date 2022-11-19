/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class PlayTopCardAction extends AbstractGameAction {
/*    */   private boolean exhaustCards;
/*    */   
/*    */   public PlayTopCardAction(AbstractCreature target, boolean exhausts) {
/* 16 */     this.duration = Settings.ACTION_DUR_FAST;
/* 17 */     this.actionType = ActionType.WAIT;
/* 18 */     this.source = (AbstractCreature)AbstractDungeon.player;
/* 19 */     this.target = target;
/* 20 */     this.exhaustCards = exhausts;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 26 */       if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
/* 27 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 31 */       if (AbstractDungeon.player.drawPile.isEmpty()) {
/* 32 */         addToTop(new PlayTopCardAction(this.target, this.exhaustCards));
/* 33 */         addToTop(new EmptyDeckShuffleAction());
/* 34 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 38 */       if (!AbstractDungeon.player.drawPile.isEmpty()) {
/* 39 */         AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
/* 40 */         AbstractDungeon.player.drawPile.group.remove(card);
/* 41 */         (AbstractDungeon.getCurrRoom()).souls.remove(card);
/* 42 */         card.exhaustOnUseOnce = this.exhaustCards;
/* 43 */         AbstractDungeon.player.limbo.group.add(card);
/* 44 */         card.current_y = -200.0F * Settings.scale;
/* 45 */         card.target_x = Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
/* 46 */         card.target_y = Settings.HEIGHT / 2.0F;
/* 47 */         card.targetAngle = 0.0F;
/* 48 */         card.lighten(false);
/* 49 */         card.drawScale = 0.12F;
/* 50 */         card.targetDrawScale = 0.75F;
/*    */         
/* 52 */         card.applyPowers();
/* 53 */         addToTop((AbstractGameAction)new NewQueueCardAction(card, this.target, false, true));
/* 54 */         addToTop((AbstractGameAction)new UnlimboAction(card));
/* 55 */         if (!Settings.FAST_MODE) {
/* 56 */           addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_MED));
/*    */         } else {
/* 58 */           addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_FASTER));
/*    */         } 
/*    */       } 
/* 61 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\PlayTopCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */