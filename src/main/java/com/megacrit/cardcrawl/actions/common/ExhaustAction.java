/*     */ package com.megacrit.cardcrawl.actions.common;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ 
/*     */ public class ExhaustAction extends AbstractGameAction {
/*  13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
/*  14 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private AbstractPlayer p;
/*     */   
/*     */   private boolean isRandom;
/*     */   
/*     */   public ExhaustAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
/*  21 */     this.anyNumber = anyNumber;
/*  22 */     this.p = AbstractDungeon.player;
/*  23 */     this.canPickZero = canPickZero;
/*  24 */     this.isRandom = isRandom;
/*  25 */     this.amount = amount;
/*  26 */     this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
/*  27 */     this.actionType = ActionType.EXHAUST;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean anyNumber;
/*     */   private boolean canPickZero;
/*     */   public static int numExhausted;
/*     */   
/*     */   public ExhaustAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber) {
/*  36 */     this(amount, isRandom, anyNumber);
/*  37 */     this.target = target;
/*  38 */     this.source = source;
/*     */   }
/*     */   
/*     */   public ExhaustAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
/*  42 */     this(amount, isRandom, false, false);
/*  43 */     this.target = target;
/*  44 */     this.source = source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExhaustAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
/*  54 */     this(amount, isRandom, anyNumber, canPickZero);
/*  55 */     this.target = target;
/*  56 */     this.source = source;
/*     */   }
/*     */   
/*     */   public ExhaustAction(boolean isRandom, boolean anyNumber, boolean canPickZero) {
/*  60 */     this(99, isRandom, anyNumber, canPickZero);
/*     */   }
/*     */   
/*     */   public ExhaustAction(int amount, boolean canPickZero) {
/*  64 */     this(amount, false, false, canPickZero);
/*     */   }
/*     */   
/*     */   public ExhaustAction(int amount, boolean isRandom, boolean anyNumber) {
/*  68 */     this(amount, isRandom, anyNumber, false);
/*     */   }
/*     */   
/*     */   public ExhaustAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, float duration) {
/*  72 */     this(amount, isRandom, anyNumber, canPickZero);
/*  73 */     this.duration = this.startDuration = duration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  78 */     if (this.duration == this.startDuration) {
/*     */ 
/*     */       
/*  81 */       if (this.p.hand.size() == 0) {
/*  82 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*  86 */       if (!this.anyNumber && 
/*  87 */         this.p.hand.size() <= this.amount) {
/*  88 */         this.amount = this.p.hand.size();
/*  89 */         numExhausted = this.amount;
/*  90 */         int tmp = this.p.hand.size();
/*  91 */         for (int i = 0; i < tmp; i++) {
/*  92 */           AbstractCard c = this.p.hand.getTopCard();
/*  93 */           this.p.hand.moveToExhaustPile(c);
/*     */         } 
/*  95 */         CardCrawlGame.dungeon.checkForPactAchievement();
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 100 */       if (this.isRandom) {
/* 101 */         for (int i = 0; i < this.amount; i++) {
/* 102 */           this.p.hand.moveToExhaustPile(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
/*     */         }
/* 104 */         CardCrawlGame.dungeon.checkForPactAchievement();
/*     */       } else {
/* 106 */         numExhausted = this.amount;
/* 107 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
/* 108 */         tickDuration();
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 116 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 117 */         this.p.hand.moveToExhaustPile(c);
/*     */       }
/* 119 */       CardCrawlGame.dungeon.checkForPactAchievement();
/* 120 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*     */     } 
/*     */     
/* 123 */     tickDuration();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ExhaustAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */