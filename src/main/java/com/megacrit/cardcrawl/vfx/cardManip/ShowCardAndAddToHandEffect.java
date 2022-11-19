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
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
/*     */ 
/*     */ public class ShowCardAndAddToHandEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static final float EFFECT_DUR = 0.8F;
/*     */   private AbstractCard card;
/*  19 */   private static final float PADDING = 25.0F * Settings.scale;
/*     */   
/*     */   public ShowCardAndAddToHandEffect(AbstractCard card, float offsetX, float offsetY) {
/*  22 */     this.card = card;
/*  23 */     UnlockTracker.markCardAsSeen(card.cardID);
/*  24 */     card.current_x = Settings.WIDTH / 2.0F;
/*  25 */     card.current_y = Settings.HEIGHT / 2.0F;
/*  26 */     card.target_x = offsetX;
/*  27 */     card.target_y = offsetY;
/*  28 */     this.duration = 0.8F;
/*  29 */     card.drawScale = 0.75F;
/*  30 */     card.targetDrawScale = 0.75F;
/*  31 */     card.transparency = 0.01F;
/*  32 */     card.targetTransparency = 1.0F;
/*  33 */     card.fadingOut = false;
/*  34 */     playCardObtainSfx();
/*     */     
/*  36 */     if (card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS && AbstractDungeon.player
/*  37 */       .hasPower("MasterRealityPower")) {
/*  38 */       card.upgrade();
/*     */     }
/*     */     
/*  41 */     AbstractDungeon.player.hand.addToHand(card);
/*  42 */     card.triggerWhenCopied();
/*  43 */     AbstractDungeon.player.hand.refreshHandLayout();
/*  44 */     AbstractDungeon.player.hand.applyPowers();
/*  45 */     AbstractDungeon.player.onCardDrawOrDiscard();
/*     */     
/*  47 */     if (AbstractDungeon.player.hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {
/*  48 */       card.setCostForTurn(-9);
/*     */     }
/*     */   }
/*     */   
/*     */   public ShowCardAndAddToHandEffect(AbstractCard card) {
/*  53 */     this.card = card;
/*  54 */     identifySpawnLocation(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/*  55 */     this.duration = 0.8F;
/*  56 */     card.drawScale = 0.75F;
/*  57 */     card.targetDrawScale = 0.75F;
/*  58 */     card.transparency = 0.01F;
/*  59 */     card.targetTransparency = 1.0F;
/*  60 */     card.fadingOut = false;
/*     */     
/*  62 */     if (card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS && AbstractDungeon.player
/*  63 */       .hasPower("MasterRealityPower")) {
/*  64 */       card.upgrade();
/*     */     }
/*     */     
/*  67 */     AbstractDungeon.player.hand.addToHand(card);
/*  68 */     card.triggerWhenCopied();
/*  69 */     AbstractDungeon.player.hand.refreshHandLayout();
/*  70 */     AbstractDungeon.player.hand.applyPowers();
/*  71 */     AbstractDungeon.player.onCardDrawOrDiscard();
/*     */     
/*  73 */     if (AbstractDungeon.player.hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {
/*  74 */       card.setCostForTurn(-9);
/*     */     }
/*     */   }
/*     */   
/*     */   private void playCardObtainSfx() {
/*  79 */     int effectCount = 0;
/*  80 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/*  81 */       if (e instanceof ShowCardAndAddToHandEffect) {
/*  82 */         effectCount++;
/*     */       }
/*     */     } 
/*  85 */     if (effectCount < 2) {
/*  86 */       CardCrawlGame.sound.play("CARD_OBTAIN");
/*     */     }
/*     */   }
/*     */   
/*     */   private void identifySpawnLocation(float x, float y) {
/*  91 */     int effectCount = 0;
/*     */     
/*  93 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/*  94 */       if (e instanceof ShowCardAndAddToHandEffect) {
/*  95 */         effectCount++;
/*     */       }
/*     */     } 
/*     */     
/*  99 */     this.card.target_y = Settings.HEIGHT * 0.5F;
/*     */     
/* 101 */     switch (effectCount) {
/*     */       case 0:
/* 103 */         this.card.target_x = Settings.WIDTH * 0.5F;
/*     */         break;
/*     */       case 1:
/* 106 */         this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*     */         break;
/*     */       case 2:
/* 109 */         this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*     */         break;
/*     */       case 3:
/* 112 */         this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         break;
/*     */       case 4:
/* 115 */         this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         break;
/*     */       default:
/* 118 */         this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/* 119 */         this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*     */         break;
/*     */     } 
/*     */     
/* 123 */     this.card.current_x = this.card.target_x;
/* 124 */     this.card.current_y = this.card.target_y - 200.0F * Settings.scale;
/* 125 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
/*     */   }
/*     */   
/*     */   public void update() {
/* 129 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 130 */     this.card.update();
/*     */     
/* 132 */     if (this.duration < 0.0F) {
/* 133 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 140 */     if (!this.isDone)
/* 141 */       this.card.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\cardManip\ShowCardAndAddToHandEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */