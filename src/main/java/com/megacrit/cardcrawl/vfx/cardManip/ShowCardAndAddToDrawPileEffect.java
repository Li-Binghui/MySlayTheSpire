/*     */ package com.megacrit.cardcrawl.vfx.cardManip;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
/*     */ 
/*     */ public class ShowCardAndAddToDrawPileEffect
/*     */   extends AbstractGameEffect {
/*     */   private static final float EFFECT_DUR = 1.5F;
/*     */   private AbstractCard card;
/*  17 */   private static final float PADDING = 30.0F * Settings.scale;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean randomSpot = false;
/*     */ 
/*     */   
/*     */   private boolean cardOffset = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public ShowCardAndAddToDrawPileEffect(AbstractCard srcCard, float x, float y, boolean randomSpot, boolean cardOffset, boolean toBottom) {
/*  29 */     this.card = srcCard.makeStatEquivalentCopy();
/*  30 */     this.cardOffset = cardOffset;
/*  31 */     this.duration = 1.5F;
/*  32 */     this.randomSpot = randomSpot;
/*     */     
/*  34 */     if (cardOffset) {
/*  35 */       identifySpawnLocation(x, y);
/*     */     } else {
/*  37 */       this.card.target_x = x;
/*  38 */       this.card.target_y = y;
/*     */     } 
/*     */     
/*  41 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
/*  42 */     this.card.drawScale = 0.01F;
/*  43 */     this.card.targetDrawScale = 1.0F;
/*  44 */     if (this.card.type != AbstractCard.CardType.CURSE && this.card.type != AbstractCard.CardType.STATUS && AbstractDungeon.player
/*  45 */       .hasPower("MasterRealityPower")) {
/*  46 */       this.card.upgrade();
/*     */     }
/*  48 */     CardCrawlGame.sound.play("CARD_OBTAIN");
/*  49 */     if (toBottom) {
/*  50 */       AbstractDungeon.player.drawPile.addToBottom(this.card);
/*     */     }
/*  52 */     else if (randomSpot) {
/*  53 */       AbstractDungeon.player.drawPile.addToRandomSpot(this.card);
/*     */     } else {
/*  55 */       AbstractDungeon.player.drawPile.addToTop(this.card);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShowCardAndAddToDrawPileEffect(AbstractCard srcCard, float x, float y, boolean randomSpot, boolean cardOffset) {
/*  66 */     this(srcCard, x, y, randomSpot, cardOffset, false);
/*     */   }
/*     */   
/*     */   public ShowCardAndAddToDrawPileEffect(AbstractCard srcCard, float x, float y, boolean randomSpot) {
/*  70 */     this(srcCard, x, y, randomSpot, false);
/*     */   }
/*     */   
/*     */   public ShowCardAndAddToDrawPileEffect(AbstractCard srcCard, boolean randomSpot, boolean toBottom) {
/*  74 */     this.card = srcCard.makeStatEquivalentCopy();
/*  75 */     this.duration = 1.5F;
/*  76 */     this.randomSpot = randomSpot;
/*  77 */     this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/*  78 */     this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.8F, Settings.HEIGHT * 0.2F);
/*  79 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
/*  80 */     this.card.drawScale = 0.01F;
/*  81 */     this.card.targetDrawScale = 1.0F;
/*  82 */     if (toBottom) {
/*  83 */       AbstractDungeon.player.drawPile.addToBottom(srcCard);
/*     */     }
/*  85 */     else if (randomSpot) {
/*  86 */       AbstractDungeon.player.drawPile.addToRandomSpot(srcCard);
/*     */     } else {
/*  88 */       AbstractDungeon.player.drawPile.addToTop(srcCard);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void identifySpawnLocation(float x, float y) {
/*  94 */     int effectCount = 0;
/*  95 */     if (this.cardOffset) {
/*  96 */       effectCount = 1;
/*     */     }
/*  98 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/*  99 */       if (e instanceof ShowCardAndAddToDrawPileEffect) {
/* 100 */         effectCount++;
/*     */       }
/*     */     } 
/*     */     
/* 104 */     this.card.current_x = x;
/* 105 */     this.card.current_y = y;
/* 106 */     this.card.target_y = Settings.HEIGHT * 0.5F;
/*     */     
/* 108 */     switch (effectCount) {
/*     */       case 0:
/* 110 */         this.card.target_x = Settings.WIDTH * 0.5F;
/*     */         return;
/*     */       case 1:
/* 113 */         this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*     */         return;
/*     */       case 2:
/* 116 */         this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*     */         return;
/*     */       case 3:
/* 119 */         this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         return;
/*     */       case 4:
/* 122 */         this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         return;
/*     */     } 
/* 125 */     this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/* 126 */     this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 133 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 134 */     this.card.update();
/*     */     
/* 136 */     if (this.duration < 0.0F) {
/* 137 */       this.isDone = true;
/* 138 */       this.card.shrink();
/* 139 */       (AbstractDungeon.getCurrRoom()).souls.onToDeck(this.card, this.randomSpot, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 145 */     if (!this.isDone)
/* 146 */       this.card.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\cardManip\ShowCardAndAddToDrawPileEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */