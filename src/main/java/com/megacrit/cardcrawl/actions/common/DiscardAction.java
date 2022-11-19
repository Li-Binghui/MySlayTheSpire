/*     */ package com.megacrit.cardcrawl.actions.common;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ 
/*     */ public class DiscardAction extends AbstractGameAction {
/*  14 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
/*  15 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   private AbstractPlayer p;
/*     */   private boolean isRandom;
/*     */   private boolean endTurn;
/*     */   public static int numDiscarded;
/*  20 */   private static final float DURATION = Settings.ACTION_DUR_XFAST;
/*     */   
/*     */   public DiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
/*  23 */     this(target, source, amount, isRandom, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn) {
/*  32 */     this.p = (AbstractPlayer)target;
/*  33 */     this.isRandom = isRandom;
/*  34 */     setValues(target, source, amount);
/*  35 */     this.actionType = ActionType.DISCARD;
/*  36 */     this.endTurn = endTurn;
/*  37 */     this.duration = DURATION;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  42 */     if (this.duration == DURATION) {
/*  43 */       if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/*  44 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*  48 */       if (this.p.hand.size() <= this.amount) {
/*  49 */         this.amount = this.p.hand.size();
/*  50 */         int tmp = this.p.hand.size();
/*     */         
/*  52 */         for (int i = 0; i < tmp; i++) {
/*  53 */           AbstractCard c = this.p.hand.getTopCard();
/*  54 */           this.p.hand.moveToDiscardPile(c);
/*  55 */           if (!this.endTurn) {
/*  56 */             c.triggerOnManualDiscard();
/*     */           }
/*  58 */           GameActionManager.incrementDiscard(this.endTurn);
/*     */         } 
/*     */         
/*  61 */         AbstractDungeon.player.hand.applyPowers();
/*  62 */         tickDuration();
/*     */         
/*     */         return;
/*     */       } 
/*  66 */       if (this.isRandom)
/*  67 */       { for (int i = 0; i < this.amount; i++) {
/*  68 */           AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
/*  69 */           this.p.hand.moveToDiscardPile(c);
/*  70 */           c.triggerOnManualDiscard();
/*  71 */           GameActionManager.incrementDiscard(this.endTurn);
/*     */         }  }
/*  73 */       else { if (this.amount < 0) {
/*  74 */           AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
/*  75 */           AbstractDungeon.player.hand.applyPowers();
/*  76 */           tickDuration();
/*     */           return;
/*     */         } 
/*  79 */         numDiscarded = this.amount;
/*  80 */         if (this.p.hand.size() > this.amount) {
/*  81 */           AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
/*     */         }
/*     */         
/*  84 */         AbstractDungeon.player.hand.applyPowers();
/*  85 */         tickDuration();
/*     */ 
/*     */         
/*     */         return; }
/*     */     
/*     */     } 
/*     */     
/*  92 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/*     */ 
/*     */       
/*  95 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/*  96 */         this.p.hand.moveToDiscardPile(c);
/*  97 */         c.triggerOnManualDiscard();
/*  98 */         GameActionManager.incrementDiscard(this.endTurn);
/*     */       } 
/* 100 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*     */     } 
/*     */     
/* 103 */     tickDuration();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\DiscardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */