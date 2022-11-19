/*     */ package com.megacrit.cardcrawl.actions.common;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
/*     */ 
/*     */ public class MakeTempCardInHandAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   private AbstractCard c;
/*  17 */   private static final float PADDING = 25.0F * Settings.scale;
/*     */   private boolean isOtherCardInCenter = true;
/*     */   private boolean sameUUID = false;
/*     */   
/*     */   public MakeTempCardInHandAction(AbstractCard card, boolean isOtherCardInCenter) {
/*  22 */     UnlockTracker.markCardAsSeen(card.cardID);
/*  23 */     this.amount = 1;
/*  24 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  25 */     this.c = card;
/*     */     
/*  27 */     if (this.c.type != AbstractCard.CardType.CURSE && this.c.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower"))
/*     */     {
/*  29 */       this.c.upgrade();
/*     */     }
/*     */     
/*  32 */     this.isOtherCardInCenter = isOtherCardInCenter;
/*     */   }
/*     */   
/*     */   public MakeTempCardInHandAction(AbstractCard card) {
/*  36 */     this(card, 1);
/*     */   }
/*     */   
/*     */   public MakeTempCardInHandAction(AbstractCard card, int amount) {
/*  40 */     UnlockTracker.markCardAsSeen(card.cardID);
/*  41 */     this.amount = amount;
/*  42 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  43 */     this.c = card;
/*     */     
/*  45 */     if (this.c.type != AbstractCard.CardType.CURSE && this.c.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower"))
/*     */     {
/*  47 */       this.c.upgrade();
/*     */     }
/*     */   }
/*     */   
/*     */   public MakeTempCardInHandAction(AbstractCard card, int amount, boolean isOtherCardInCenter) {
/*  52 */     this(card, amount);
/*  53 */     this.isOtherCardInCenter = isOtherCardInCenter;
/*     */   }
/*     */   
/*     */   public MakeTempCardInHandAction(AbstractCard card, boolean isOtherCardInCenter, boolean sameUUID) {
/*  57 */     this(card, 1);
/*  58 */     this.isOtherCardInCenter = isOtherCardInCenter;
/*  59 */     this.sameUUID = sameUUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  64 */     if (this.amount == 0) {
/*  65 */       this.isDone = true;
/*     */       
/*     */       return;
/*     */     } 
/*  69 */     int discardAmount = 0;
/*  70 */     int handAmount = this.amount;
/*     */ 
/*     */     
/*  73 */     if (this.amount + AbstractDungeon.player.hand.size() > 10) {
/*  74 */       AbstractDungeon.player.createHandIsFullDialog();
/*  75 */       discardAmount = this.amount + AbstractDungeon.player.hand.size() - 10;
/*  76 */       handAmount -= discardAmount;
/*     */     } 
/*     */     
/*  79 */     addToHand(handAmount);
/*  80 */     addToDiscard(discardAmount);
/*     */     
/*  82 */     if (this.amount > 0) {
/*  83 */       addToTop((AbstractGameAction)new WaitAction(0.8F));
/*     */     }
/*     */     
/*  86 */     this.isDone = true;
/*     */   }
/*     */   
/*     */   private void addToHand(int handAmt) {
/*  90 */     switch (this.amount) {
/*     */       case 0:
/*     */         return;
/*     */       case 1:
/*  94 */         if (handAmt == 1) {
/*  95 */           if (this.isOtherCardInCenter) {
/*  96 */             AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(
/*     */                   
/*  98 */                   makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */           }
/*     */           else {
/*     */             
/* 102 */             AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard()));
/*     */           } 
/*     */         }
/*     */       
/*     */       case 2:
/* 107 */         if (handAmt == 1) {
/* 108 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(
/*     */                 
/* 110 */                 makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH * 0.5F, Settings.HEIGHT / 2.0F));
/*     */         
/*     */         }
/* 113 */         else if (handAmt == 2) {
/* 114 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(
/*     */                 
/* 116 */                 makeNewCard(), Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */           
/* 119 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(
/*     */                 
/* 121 */                 makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 127 */         if (handAmt == 1) {
/* 128 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(
/*     */                 
/* 130 */                 makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */         
/*     */         }
/* 133 */         else if (handAmt == 2) {
/* 134 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(
/*     */                 
/* 136 */                 makeNewCard(), Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */           
/* 139 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(
/*     */                 
/* 141 */                 makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */         
/*     */         }
/* 144 */         else if (handAmt == 3) {
/* 145 */           for (int j = 0; j < this.amount; j++) {
/* 146 */             AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(makeNewCard()));
/*     */           }
/*     */         } 
/*     */     } 
/*     */     
/* 151 */     for (int i = 0; i < handAmt; i++) {
/* 152 */       AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(
/*     */             
/* 154 */             makeNewCard(), 
/* 155 */             MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), 
/* 156 */             MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addToDiscard(int discardAmt) {
/* 163 */     switch (this.amount) {
/*     */       case 0:
/*     */         return;
/*     */       case 1:
/* 167 */         if (discardAmt == 1) {
/* 168 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 170 */                 makeNewCard(), Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */         }
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 176 */         if (discardAmt == 1) {
/* 177 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 179 */                 makeNewCard(), Settings.WIDTH * 0.5F - PADDING + AbstractCard.IMG_WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */         
/*     */         }
/* 182 */         else if (discardAmt == 2) {
/* 183 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 185 */                 makeNewCard(), Settings.WIDTH * 0.5F - PADDING + AbstractCard.IMG_WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */ 
/*     */           
/* 188 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 190 */                 makeNewCard(), Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 196 */         if (discardAmt == 1) {
/* 197 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 199 */                 makeNewCard(), Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5F));
/*     */         
/*     */         }
/* 202 */         else if (discardAmt == 2) {
/* 203 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 205 */                 makeNewCard(), Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */ 
/*     */           
/* 208 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 210 */                 makeNewCard(), Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5F));
/*     */         
/*     */         }
/* 213 */         else if (discardAmt == 3) {
/* 214 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 216 */                 makeNewCard(), Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */ 
/*     */           
/* 219 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 221 */                 makeNewCard(), Settings.WIDTH * 0.5F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5F));
/*     */ 
/*     */           
/* 224 */           AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */                 
/* 226 */                 makeNewCard(), Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5F));
/*     */         } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 232 */     for (int i = 0; i < discardAmt; i++) {
/* 233 */       AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(
/*     */             
/* 235 */             makeNewCard(), 
/* 236 */             MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), 
/* 237 */             MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AbstractCard makeNewCard() {
/* 245 */     if (this.sameUUID) {
/* 246 */       return this.c.makeSameInstanceOf();
/*     */     }
/* 248 */     return this.c.makeStatEquivalentCopy();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\MakeTempCardInHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */