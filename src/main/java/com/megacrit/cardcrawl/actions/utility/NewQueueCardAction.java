/*     */ package com.megacrit.cardcrawl.actions.utility;
/*     */ 
/*     */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NewQueueCardAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   private AbstractCard card;
/*     */   private boolean randomTarget;
/*     */   private boolean immediateCard;
/*     */   private boolean autoplayCard;
/*  28 */   private static final Logger logger = LogManager.getLogger(NewQueueCardAction.class.getName());
/*     */   
/*     */   public NewQueueCardAction(AbstractCard card, AbstractCreature target, boolean immediateCard, boolean autoplayCard) {
/*  31 */     this.card = card;
/*  32 */     this.target = target;
/*  33 */     this.immediateCard = immediateCard;
/*  34 */     this.autoplayCard = autoplayCard;
/*  35 */     this.randomTarget = false;
/*     */   }
/*     */   
/*     */   public NewQueueCardAction(AbstractCard card, AbstractCreature target, boolean immediateCard) {
/*  39 */     this(card, target, immediateCard, false);
/*     */   }
/*     */   
/*     */   public NewQueueCardAction(AbstractCard card, AbstractCreature target) {
/*  43 */     this(card, target, false);
/*     */   }
/*     */   
/*     */   public NewQueueCardAction(AbstractCard card, boolean randomTarget, boolean immediateCard, boolean autoplayCard) {
/*  47 */     this(card, (AbstractCreature)null, immediateCard, autoplayCard);
/*  48 */     this.randomTarget = randomTarget;
/*     */   }
/*     */   
/*     */   public NewQueueCardAction(AbstractCard card, boolean randomTarget, boolean immediateCard) {
/*  52 */     this(card, randomTarget, immediateCard, false);
/*     */   }
/*     */   
/*     */   public NewQueueCardAction(AbstractCard card, boolean randomTarget) {
/*  56 */     this(card, randomTarget, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public NewQueueCardAction() {
/*  61 */     this((AbstractCard)null, (AbstractCreature)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  66 */     if (this.card == null) {
/*     */       
/*  68 */       if (!queueContainsEndTurnCard()) {
/*  69 */         AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem());
/*     */       }
/*  71 */     } else if (!queueContains(this.card)) {
/*  72 */       if (this.randomTarget) {
/*  73 */         AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(this.card, true, 
/*  74 */               EnergyPanel.getCurrentEnergy(), false, this.autoplayCard), this.immediateCard);
/*     */       }
/*     */       else {
/*     */         
/*  78 */         if (!(this.target instanceof AbstractMonster) && this.target != null) {
/*  79 */           logger.info("WARNING: NewQueueCardAction does not contain an AbstractMonster!");
/*  80 */           this.isDone = true;
/*     */           return;
/*     */         } 
/*  83 */         AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(this.card, (AbstractMonster)this.target, 
/*     */ 
/*     */ 
/*     */               
/*  87 */               EnergyPanel.getCurrentEnergy(), false, this.autoplayCard), this.immediateCard);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  93 */     this.isDone = true;
/*     */   }
/*     */   
/*     */   private boolean queueContains(AbstractCard card) {
/*  97 */     for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
/*  98 */       if (i.card == card) {
/*  99 */         return true;
/*     */       }
/*     */     } 
/* 102 */     return false;
/*     */   }
/*     */   
/*     */   private boolean queueContainsEndTurnCard() {
/* 106 */     for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
/* 107 */       if (i.card == null) {
/* 108 */         return true;
/*     */       }
/*     */     } 
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\NewQueueCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */