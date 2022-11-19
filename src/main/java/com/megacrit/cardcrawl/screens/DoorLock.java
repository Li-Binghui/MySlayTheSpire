/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.DoorFlashEffect;
/*     */ 
/*     */ public class DoorLock
/*     */ {
/*  17 */   private Color glowColor = Color.WHITE.cpy();
/*  18 */   private Texture lockImg = null; private boolean glowing; private boolean unlockAnimation = false; private Texture glowImg = null;
/*     */   private boolean usedFlash = false;
/*  20 */   private float x = 0.0F; private float y; private float unlockTimer = 2.0F; private float startY;
/*     */   private float targetY;
/*     */   private LockColor c;
/*     */   
/*  24 */   public enum LockColor { RED, GREEN, BLUE; }
/*     */ 
/*     */   
/*     */   public DoorLock(LockColor c, boolean glowing, boolean eventVersion) {
/*  28 */     this.c = c;
/*  29 */     this.glowing = glowing;
/*     */     
/*  31 */     if (eventVersion) {
/*  32 */       this.startY = -48.0F * Settings.scale;
/*     */     } else {
/*  34 */       this.startY = 0.0F * Settings.scale;
/*     */     } 
/*  36 */     this.y = this.startY;
/*     */     
/*  38 */     switch (c) {
/*     */       case RED:
/*  40 */         this.lockImg = ImageMaster.loadImage("images/ui/door/lock_red.png");
/*  41 */         this.glowImg = ImageMaster.loadImage("images/ui/door/glow_red.png");
/*  42 */         glowing = CardCrawlGame.playerPref.getBoolean(AbstractPlayer.PlayerClass.IRONCLAD.name() + "_WIN", false);
/*  43 */         if (eventVersion) {
/*  44 */           this.targetY = -748.0F * Settings.scale; break;
/*     */         } 
/*  46 */         this.targetY = -700.0F * Settings.scale;
/*     */         break;
/*     */       
/*     */       case GREEN:
/*  50 */         this.lockImg = ImageMaster.loadImage("images/ui/door/lock_green.png");
/*  51 */         this.glowImg = ImageMaster.loadImage("images/ui/door/glow_green.png");
/*  52 */         glowing = CardCrawlGame.playerPref.getBoolean(AbstractPlayer.PlayerClass.THE_SILENT.name() + "_WIN", false);
/*  53 */         if (eventVersion) {
/*  54 */           this.targetY = 752.0F * Settings.scale; break;
/*     */         } 
/*  56 */         this.targetY = 800.0F * Settings.scale;
/*     */         break;
/*     */       
/*     */       case BLUE:
/*  60 */         this.lockImg = ImageMaster.loadImage("images/ui/door/lock_blue.png");
/*  61 */         this.glowImg = ImageMaster.loadImage("images/ui/door/glow_blue.png");
/*  62 */         glowing = CardCrawlGame.playerPref.getBoolean(AbstractPlayer.PlayerClass.DEFECT.name() + "_WIN", false);
/*  63 */         if (eventVersion) {
/*  64 */           this.targetY = -748.0F * Settings.scale; break;
/*     */         } 
/*  66 */         this.targetY = -700.0F * Settings.scale;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  76 */     updateUnlockAnimation();
/*     */   }
/*     */   
/*     */   private void updateUnlockAnimation() {
/*  80 */     if (this.unlockAnimation) {
/*  81 */       this.unlockTimer -= Gdx.graphics.getDeltaTime();
/*  82 */       if (this.unlockTimer < 0.0F) {
/*  83 */         this.unlockTimer = 0.0F;
/*  84 */         this.unlockAnimation = false;
/*     */       } 
/*     */       
/*  87 */       switch (this.c) {
/*     */         case RED:
/*  89 */           this.x = Interpolation.pow5In.apply(-1000.0F * Settings.scale, 0.0F, this.unlockTimer / 2.0F);
/*  90 */           this.y = Interpolation.pow5In.apply(this.targetY, this.startY, this.unlockTimer / 2.0F);
/*     */           break;
/*     */         case GREEN:
/*  93 */           this.y = Interpolation.pow5In.apply(800.0F * Settings.scale, this.startY, this.unlockTimer / 2.0F);
/*     */           break;
/*     */         case BLUE:
/*  96 */           this.x = Interpolation.pow5In.apply(1000.0F * Settings.scale, 0.0F, this.unlockTimer / 2.0F);
/*  97 */           this.y = Interpolation.pow5In.apply(this.targetY, this.startY, this.unlockTimer / 2.0F);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unlock() {
/* 106 */     this.unlockAnimation = true;
/* 107 */     this.unlockTimer = 2.0F;
/* 108 */     this.x = 0.0F;
/* 109 */     this.y = this.startY;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 113 */     if (this.lockImg == null) {
/*     */       return;
/*     */     }
/*     */     
/* 117 */     renderLock(sb);
/* 118 */     renderGlow(sb);
/*     */   }
/*     */   
/*     */   private void renderLock(SpriteBatch sb) {
/* 122 */     sb.setColor(Color.WHITE);
/* 123 */     sb.draw(this.lockImg, Settings.WIDTH / 2.0F - 960.0F + this.x, Settings.HEIGHT / 2.0F - 600.0F + this.y, 960.0F, 600.0F, 1920.0F, 1200.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1920, 1200, false, false);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderGlow(SpriteBatch sb) {
/* 143 */     if (this.glowing) {
/* 144 */       this.glowColor.a = (MathUtils.cosDeg((float)(System.currentTimeMillis() / 4L % 360L)) + 3.0F) / 4.0F;
/* 145 */       sb.setColor(this.glowColor);
/* 146 */       sb.setBlendFunction(770, 1);
/* 147 */       sb.draw(this.glowImg, Settings.WIDTH / 2.0F - 960.0F + this.x, Settings.HEIGHT / 2.0F - 600.0F + this.y, 960.0F, 600.0F, 1920.0F, 1200.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1920, 1200, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 164 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 172 */     this.usedFlash = false;
/* 173 */     this.unlockAnimation = false;
/* 174 */     this.x = 0.0F;
/* 175 */     this.y = this.startY;
/* 176 */     this.unlockTimer = 2.0F;
/*     */   }
/*     */   
/*     */   public void flash(boolean eventVersion) {
/* 180 */     if (!this.usedFlash) {
/* 181 */       CardCrawlGame.sound.playA("ATTACK_MAGIC_SLOW_2", 1.0F);
/* 182 */       this.usedFlash = true;
/* 183 */       CardCrawlGame.mainMenuScreen.doorUnlockScreen.effects.add(new DoorFlashEffect(this.glowImg, eventVersion));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 188 */     this.lockImg.dispose();
/* 189 */     this.glowImg.dispose();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\DoorLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */