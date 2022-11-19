/*     */ package com.megacrit.cardcrawl.actions.common;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class DrawCardAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   private boolean shuffleCheck = false;
/*  20 */   private static final Logger logger = LogManager.getLogger(DrawCardAction.class.getName());
/*     */   
/*  22 */   public static ArrayList<AbstractCard> drawnCards = new ArrayList<>();
/*     */   private boolean clearDrawHistory = true;
/*  24 */   private AbstractGameAction followUpAction = null;
/*     */   
/*     */   public DrawCardAction(AbstractCreature source, int amount, boolean endTurnDraw) {
/*  27 */     if (endTurnDraw) {
/*  28 */       AbstractDungeon.topLevelEffects.add(new PlayerTurnEffect());
/*     */     }
/*     */     
/*  31 */     setValues((AbstractCreature)AbstractDungeon.player, source, amount);
/*  32 */     this.actionType = ActionType.DRAW;
/*  33 */     if (Settings.FAST_MODE) {
/*  34 */       this.duration = Settings.ACTION_DUR_XFAST;
/*     */     } else {
/*  36 */       this.duration = Settings.ACTION_DUR_FASTER;
/*     */     } 
/*     */   }
/*     */   
/*     */   public DrawCardAction(AbstractCreature source, int amount) {
/*  41 */     this(source, amount, false);
/*     */   }
/*     */   
/*     */   public DrawCardAction(int amount, boolean clearDrawHistory) {
/*  45 */     this(amount);
/*  46 */     this.clearDrawHistory = clearDrawHistory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DrawCardAction(int amount) {
/*  55 */     this((AbstractCreature)null, amount, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DrawCardAction(int amount, AbstractGameAction action) {
/*  64 */     this(amount, action, true);
/*     */   }
/*     */   
/*     */   public DrawCardAction(int amount, AbstractGameAction action, boolean clearDrawHistory) {
/*  68 */     this(amount, clearDrawHistory);
/*  69 */     this.followUpAction = action;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  74 */     if (this.clearDrawHistory) {
/*  75 */       this.clearDrawHistory = false;
/*  76 */       drawnCards.clear();
/*     */     } 
/*     */     
/*  79 */     if (AbstractDungeon.player.hasPower("No Draw")) {
/*  80 */       AbstractDungeon.player.getPower("No Draw").flash();
/*  81 */       endActionWithFollowUp();
/*     */       
/*     */       return;
/*     */     } 
/*  85 */     if (this.amount <= 0) {
/*  86 */       endActionWithFollowUp();
/*     */       
/*     */       return;
/*     */     } 
/*  90 */     int deckSize = AbstractDungeon.player.drawPile.size();
/*  91 */     int discardSize = AbstractDungeon.player.discardPile.size();
/*     */ 
/*     */     
/*  94 */     if (SoulGroup.isActive()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  99 */     if (deckSize + discardSize == 0) {
/* 100 */       endActionWithFollowUp();
/*     */       
/*     */       return;
/*     */     } 
/* 104 */     if (AbstractDungeon.player.hand.size() == 10) {
/* 105 */       AbstractDungeon.player.createHandIsFullDialog();
/* 106 */       endActionWithFollowUp();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 113 */     if (!this.shuffleCheck) {
/* 114 */       if (this.amount + AbstractDungeon.player.hand.size() > 10) {
/* 115 */         int handSizeAndDraw = 10 - this.amount + AbstractDungeon.player.hand.size();
/* 116 */         this.amount += handSizeAndDraw;
/* 117 */         AbstractDungeon.player.createHandIsFullDialog();
/*     */       } 
/* 119 */       if (this.amount > deckSize) {
/* 120 */         int tmp = this.amount - deckSize;
/* 121 */         addToTop(new DrawCardAction(tmp, this.followUpAction, false));
/* 122 */         addToTop(new EmptyDeckShuffleAction());
/* 123 */         if (deckSize != 0) {
/* 124 */           addToTop(new DrawCardAction(deckSize, false));
/*     */         }
/* 126 */         this.amount = 0;
/* 127 */         this.isDone = true;
/*     */         return;
/*     */       } 
/* 130 */       this.shuffleCheck = true;
/*     */     } 
/*     */     
/* 133 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */ 
/*     */     
/* 136 */     if (this.amount != 0 && this.duration < 0.0F) {
/* 137 */       if (Settings.FAST_MODE) {
/* 138 */         this.duration = Settings.ACTION_DUR_XFAST;
/*     */       } else {
/* 140 */         this.duration = Settings.ACTION_DUR_FASTER;
/*     */       } 
/* 142 */       this.amount--;
/*     */       
/* 144 */       if (!AbstractDungeon.player.drawPile.isEmpty()) {
/* 145 */         drawnCards.add(AbstractDungeon.player.drawPile.getTopCard());
/* 146 */         AbstractDungeon.player.draw();
/* 147 */         AbstractDungeon.player.hand.refreshHandLayout();
/*     */       } else {
/* 149 */         logger.warn("Player attempted to draw from an empty drawpile mid-DrawAction?MASTER DECK: " + AbstractDungeon.player.masterDeck
/*     */             
/* 151 */             .getCardNames());
/* 152 */         endActionWithFollowUp();
/*     */       } 
/*     */       
/* 155 */       if (this.amount == 0) {
/* 156 */         endActionWithFollowUp();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void endActionWithFollowUp() {
/* 162 */     this.isDone = true;
/* 163 */     if (this.followUpAction != null)
/* 164 */       addToTop(this.followUpAction); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\DrawCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */