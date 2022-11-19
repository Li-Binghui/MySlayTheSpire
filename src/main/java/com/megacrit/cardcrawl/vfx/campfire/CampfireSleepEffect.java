/*     */ package com.megacrit.cardcrawl.vfx.campfire;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CampfireSleepEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*  33 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CampfireSleepEffect");
/*  34 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   private static final float HEAL_AMOUNT = 0.3F;
/*     */   private static final float DUR = 3.0F;
/*     */   private static final float FAST_MODE_DUR = 1.5F;
/*     */   private boolean hasHealed = false;
/*     */   private int healAmount;
/*  40 */   private Color screenColor = AbstractDungeon.fadeColor.cpy();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CampfireSleepEffect() {
/*  46 */     if (Settings.FAST_MODE) {
/*  47 */       this.startingDuration = 1.5F;
/*     */     } else {
/*  49 */       this.startingDuration = 3.0F;
/*     */     } 
/*  51 */     this.duration = this.startingDuration;
/*     */     
/*  53 */     this.screenColor.a = 0.0F;
/*  54 */     ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
/*  55 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/*     */ 
/*     */     
/*  58 */     if (ModHelper.isModEnabled("Night Terrors")) {
/*  59 */       this.healAmount = (int)(AbstractDungeon.player.maxHealth * 1.0F);
/*  60 */       AbstractDungeon.player.decreaseMaxHealth(5);
/*     */     } else {
/*  62 */       this.healAmount = (int)(AbstractDungeon.player.maxHealth * 0.3F);
/*     */     } 
/*  64 */     if (AbstractDungeon.player.hasRelic("Regal Pillow")) {
/*  65 */       this.healAmount += 15;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  71 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  72 */     updateBlackScreenColor();
/*     */ 
/*     */     
/*  75 */     if (this.duration < this.startingDuration - 0.5F && !this.hasHealed) {
/*  76 */       playSleepJingle();
/*  77 */       this.hasHealed = true;
/*  78 */       if (AbstractDungeon.player.hasRelic("Regal Pillow")) {
/*  79 */         AbstractDungeon.player.getRelic("Regal Pillow").flash();
/*     */       }
/*     */       
/*  82 */       AbstractDungeon.player.heal(this.healAmount, false);
/*     */       
/*  84 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  85 */         r.onRest();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  90 */     if (this.duration < this.startingDuration / 2.0F) {
/*     */       
/*  92 */       if (AbstractDungeon.player.hasRelic("Dream Catcher")) {
/*  93 */         AbstractDungeon.player.getRelic("Dream Catcher").flash();
/*  94 */         ArrayList<AbstractCard> rewardCards = AbstractDungeon.getRewardCards();
/*  95 */         if (rewardCards != null && !rewardCards.isEmpty()) {
/*  96 */           AbstractDungeon.cardRewardScreen.open(rewardCards, null, TEXT[0]);
/*     */         }
/*     */       } 
/*     */       
/* 100 */       this.isDone = true;
/* 101 */       ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
/* 102 */       AbstractRoom.waitTimer = 0.0F;
/* 103 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playSleepJingle() {
/* 108 */     int roll = MathUtils.random(0, 2);
/*     */     
/* 110 */     switch (AbstractDungeon.id) {
/*     */       case "Exordium":
/* 112 */         if (roll == 0) {
/* 113 */           CardCrawlGame.sound.play("SLEEP_1-1"); break;
/* 114 */         }  if (roll == 1) {
/* 115 */           CardCrawlGame.sound.play("SLEEP_1-2"); break;
/*     */         } 
/* 117 */         CardCrawlGame.sound.play("SLEEP_1-3");
/*     */         break;
/*     */       
/*     */       case "TheCity":
/* 121 */         if (roll == 0) {
/* 122 */           CardCrawlGame.sound.play("SLEEP_2-1"); break;
/* 123 */         }  if (roll == 1) {
/* 124 */           CardCrawlGame.sound.play("SLEEP_2-2"); break;
/*     */         } 
/* 126 */         CardCrawlGame.sound.play("SLEEP_2-3");
/*     */         break;
/*     */       
/*     */       case "TheBeyond":
/* 130 */         if (roll == 0) {
/* 131 */           CardCrawlGame.sound.play("SLEEP_3-1"); break;
/* 132 */         }  if (roll == 1) {
/* 133 */           CardCrawlGame.sound.play("SLEEP_3-2"); break;
/*     */         } 
/* 135 */         CardCrawlGame.sound.play("SLEEP_3-3");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateBlackScreenColor() {
/* 148 */     if (this.duration > this.startingDuration - 0.5F) {
/* 149 */       this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.startingDuration - 0.5F) * 2.0F);
/*     */     
/*     */     }
/* 152 */     else if (this.duration < 1.0F) {
/* 153 */       this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
/*     */     } else {
/* 155 */       this.screenColor.a = 1.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 161 */     sb.setColor(this.screenColor);
/* 162 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireSleepEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */