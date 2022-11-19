/*     */ package com.megacrit.cardcrawl.vfx.cardManip;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Omamori;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
/*     */ 
/*     */ public class ShowCardAndObtainEffect extends AbstractGameEffect {
/*     */   private static final float EFFECT_DUR = 2.0F;
/*     */   private static final float FAST_DUR = 0.5F;
/*     */   private AbstractCard card;
/*  20 */   private static final float PADDING = 30.0F * Settings.scale;
/*     */   private boolean converge;
/*     */   
/*     */   public ShowCardAndObtainEffect(AbstractCard card, float x, float y, boolean convergeCards) {
/*  24 */     if (card.color == AbstractCard.CardColor.CURSE && AbstractDungeon.player.hasRelic("Omamori") && 
/*  25 */       (AbstractDungeon.player.getRelic("Omamori")).counter != 0) {
/*  26 */       ((Omamori)AbstractDungeon.player.getRelic("Omamori")).use();
/*  27 */       this.duration = 0.0F;
/*  28 */       this.isDone = true;
/*  29 */       this.converge = convergeCards;
/*     */     } 
/*     */     
/*  32 */     UnlockTracker.markCardAsSeen(card.cardID);
/*     */     
/*  34 */     CardHelper.obtain(card.cardID, card.rarity, card.color);
/*     */     
/*  36 */     this.card = card;
/*  37 */     if (Settings.FAST_MODE) {
/*  38 */       this.duration = 0.5F;
/*     */     } else {
/*  40 */       this.duration = 2.0F;
/*     */     } 
/*  42 */     identifySpawnLocation(x, y);
/*  43 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(card.target_x, card.target_y));
/*  44 */     card.drawScale = 0.01F;
/*  45 */     card.targetDrawScale = 1.0F;
/*     */   }
/*     */   
/*     */   public ShowCardAndObtainEffect(AbstractCard card, float x, float y) {
/*  49 */     this(card, x, y, true);
/*     */   }
/*     */   
/*     */   private void identifySpawnLocation(float x, float y) {
/*  53 */     int effectCount = 0;
/*  54 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/*  55 */       if (e instanceof ShowCardAndObtainEffect) {
/*  56 */         effectCount++;
/*     */       }
/*     */     } 
/*     */     
/*  60 */     this.card.current_x = x;
/*  61 */     this.card.current_y = y;
/*     */     
/*  63 */     if (this.converge) {
/*  64 */       this.card.target_y = Settings.HEIGHT * 0.5F;
/*  65 */       switch (effectCount) {
/*     */         case 0:
/*  67 */           this.card.target_x = Settings.WIDTH * 0.5F;
/*     */           return;
/*     */         case 1:
/*  70 */           this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*     */           return;
/*     */         case 2:
/*  73 */           this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*     */           return;
/*     */         case 3:
/*  76 */           this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */           return;
/*     */         case 4:
/*  79 */           this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */           return;
/*     */       } 
/*  82 */       this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/*  83 */       this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*     */     }
/*     */     else {
/*     */       
/*  87 */       this.card.target_x = this.card.current_x;
/*  88 */       this.card.target_y = this.card.current_y;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  94 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  95 */     this.card.update();
/*     */     
/*  97 */     if (this.duration < 0.0F) {
/*     */ 
/*     */       
/* 100 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 101 */         r.onObtainCard(this.card);
/*     */       }
/*     */       
/* 104 */       this.isDone = true;
/* 105 */       this.card.shrink();
/* 106 */       (AbstractDungeon.getCurrRoom()).souls.obtain(this.card, true);
/* 107 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 108 */         r.onMasterDeckChange();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 115 */     if (!this.isDone)
/* 116 */       this.card.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\cardManip\ShowCardAndObtainEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */