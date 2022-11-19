/*     */ package com.megacrit.cardcrawl.actions.defect;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*     */ import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.SoulGroup;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class ScrapeAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   private boolean shuffleCheck = false;
/*  23 */   private static final Logger logger = LogManager.getLogger(DrawCardAction.class.getName());
/*  24 */   public static ArrayList<AbstractCard> scrapedCards = new ArrayList<>();
/*     */   
/*     */   public ScrapeAction(AbstractCreature source, int amount, boolean endTurnDraw) {
/*  27 */     if (endTurnDraw) {
/*  28 */       AbstractDungeon.topLevelEffects.add(new PlayerTurnEffect());
/*  29 */     } else if (AbstractDungeon.player.hasPower("No Draw")) {
/*  30 */       AbstractDungeon.player.getPower("No Draw").flash();
/*  31 */       setValues((AbstractCreature)AbstractDungeon.player, source, amount);
/*  32 */       this.isDone = true;
/*  33 */       this.duration = 0.0F;
/*  34 */       this.actionType = ActionType.WAIT;
/*     */       
/*     */       return;
/*     */     } 
/*  38 */     setValues((AbstractCreature)AbstractDungeon.player, source, amount);
/*  39 */     this.actionType = ActionType.DRAW;
/*  40 */     if (Settings.FAST_MODE) {
/*  41 */       this.duration = Settings.ACTION_DUR_XFAST;
/*     */     } else {
/*  43 */       this.duration = Settings.ACTION_DUR_FASTER;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ScrapeAction(AbstractCreature source, int amount) {
/*  48 */     this(source, amount, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  53 */     if (this.amount <= 0) {
/*  54 */       this.isDone = true;
/*     */       
/*     */       return;
/*     */     } 
/*  58 */     int deckSize = AbstractDungeon.player.drawPile.size();
/*  59 */     int discardSize = AbstractDungeon.player.discardPile.size();
/*     */ 
/*     */     
/*  62 */     if (SoulGroup.isActive()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  67 */     if (deckSize + discardSize == 0) {
/*  68 */       this.isDone = true;
/*     */       
/*     */       return;
/*     */     } 
/*  72 */     if (AbstractDungeon.player.hand.size() == 10) {
/*  73 */       AbstractDungeon.player.createHandIsFullDialog();
/*  74 */       this.isDone = true;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/*  81 */     if (!this.shuffleCheck) {
/*  82 */       if (this.amount > deckSize) {
/*  83 */         int tmp = this.amount - deckSize;
/*  84 */         addToTop(new ScrapeAction((AbstractCreature)AbstractDungeon.player, tmp));
/*  85 */         addToTop((AbstractGameAction)new EmptyDeckShuffleAction());
/*  86 */         if (deckSize != 0) {
/*  87 */           addToTop(new ScrapeAction((AbstractCreature)AbstractDungeon.player, deckSize));
/*     */         }
/*  89 */         this.amount = 0;
/*  90 */         this.isDone = true;
/*     */       } 
/*  92 */       this.shuffleCheck = true;
/*     */     } 
/*     */     
/*  95 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */ 
/*     */     
/*  98 */     if (this.amount != 0 && this.duration < 0.0F) {
/*  99 */       if (Settings.FAST_MODE) {
/* 100 */         this.duration = Settings.ACTION_DUR_XFAST;
/*     */       } else {
/* 102 */         this.duration = Settings.ACTION_DUR_FASTER;
/*     */       } 
/* 104 */       this.amount--;
/*     */       
/* 106 */       if (!AbstractDungeon.player.drawPile.isEmpty()) {
/* 107 */         scrapedCards.add(AbstractDungeon.player.drawPile.getTopCard());
/* 108 */         AbstractDungeon.player.draw();
/* 109 */         AbstractDungeon.player.hand.refreshHandLayout();
/*     */       } else {
/* 111 */         logger.warn("Player attempted to draw from an empty drawpile mid-DrawAction?MASTER DECK: " + AbstractDungeon.player.masterDeck
/*     */             
/* 113 */             .getCardNames());
/* 114 */         this.isDone = true;
/*     */       } 
/*     */       
/* 117 */       if (this.amount == 0)
/* 118 */         this.isDone = true; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\ScrapeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */