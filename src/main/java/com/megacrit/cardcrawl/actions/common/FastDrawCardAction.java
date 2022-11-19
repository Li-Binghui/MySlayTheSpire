/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.SoulGroup;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
/*    */ 
/*    */ public class FastDrawCardAction
/*    */   extends AbstractGameAction {
/*    */   private boolean shuffleCheck = false;
/*    */   
/*    */   public FastDrawCardAction(AbstractCreature source, int amount, boolean endTurnDraw) {
/* 16 */     if (endTurnDraw) {
/* 17 */       AbstractDungeon.effectList.add(new PlayerTurnEffect());
/* 18 */     } else if (AbstractDungeon.player.hasPower("No Draw")) {
/* 19 */       AbstractDungeon.player.getPower("No Draw").flash();
/* 20 */       setValues((AbstractCreature)AbstractDungeon.player, source, amount);
/* 21 */       this.isDone = true;
/* 22 */       this.duration = 0.0F;
/* 23 */       this.actionType = ActionType.WAIT;
/*    */       
/*    */       return;
/*    */     } 
/* 27 */     setValues((AbstractCreature)AbstractDungeon.player, source, amount);
/* 28 */     this.actionType = ActionType.DRAW;
/* 29 */     this.duration = Settings.ACTION_DUR_XFAST;
/*    */   }
/*    */   
/*    */   public FastDrawCardAction(AbstractCreature source, int amount) {
/* 33 */     this(source, amount, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 38 */     int deckSize = AbstractDungeon.player.drawPile.size();
/* 39 */     int discardSize = AbstractDungeon.player.discardPile.size();
/*    */ 
/*    */     
/* 42 */     if (SoulGroup.isActive()) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 47 */     if (deckSize + discardSize == 0) {
/* 48 */       this.isDone = true;
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */ 
/*    */     
/* 55 */     if (!this.shuffleCheck) {
/* 56 */       if (this.amount + AbstractDungeon.player.hand.size() > 10) {
/* 57 */         int handSizeAndDraw = 10 - this.amount + AbstractDungeon.player.hand.size();
/* 58 */         this.amount += handSizeAndDraw;
/* 59 */         AbstractDungeon.player.createHandIsFullDialog();
/*    */       } 
/* 61 */       if (this.amount > deckSize) {
/* 62 */         int tmp = this.amount - deckSize;
/* 63 */         addToTop(new FastDrawCardAction((AbstractCreature)AbstractDungeon.player, tmp));
/* 64 */         addToTop(new EmptyDeckShuffleAction());
/* 65 */         if (deckSize != 0) {
/* 66 */           addToTop(new FastDrawCardAction((AbstractCreature)AbstractDungeon.player, deckSize));
/*    */         }
/* 68 */         this.amount = 0;
/* 69 */         this.isDone = true;
/*    */       } 
/* 71 */       this.shuffleCheck = true;
/*    */     } 
/*    */     
/* 74 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 76 */     if (this.amount != 0 && this.duration < 0.0F) {
/* 77 */       this.duration = Settings.ACTION_DUR_XFAST;
/* 78 */       this.amount--;
/* 79 */       AbstractDungeon.player.draw();
/* 80 */       AbstractDungeon.player.hand.refreshHandLayout();
/*    */       
/* 82 */       if (this.amount == 0)
/* 83 */         this.isDone = true; 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\FastDrawCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */