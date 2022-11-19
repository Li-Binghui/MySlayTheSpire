/*     */ package com.megacrit.cardcrawl.screens.options;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import java.text.DecimalFormat;
/*     */ 
/*     */ public class Slider {
/*     */   private static final int BG_W = 250;
/*     */   private static final int BG_H = 24;
/*     */   private static final int S_W = 44;
/*  21 */   private static final float SLIDE_W = 230.0F * Settings.xScale;
/*  22 */   private static final float BG_X = 1350.0F * Settings.xScale;
/*  23 */   private static final float L_X = 1235.0F * Settings.xScale;
/*     */   
/*     */   private float x;
/*     */   private float y;
/*     */   private float volume;
/*  28 */   private static DecimalFormat df = new DecimalFormat("#"); public Hitbox hb; public Hitbox bgHb; private boolean sliderGrabbed = false;
/*     */   private SliderType type;
/*     */   
/*  31 */   public enum SliderType { MASTER, BGM, SFX; }
/*     */ 
/*     */   
/*     */   public Slider(float y, float volume, SliderType type) {
/*  35 */     this.type = type;
/*  36 */     this.y = y;
/*  37 */     this.volume = volume;
/*  38 */     this.hb = new Hitbox(42.0F * Settings.scale, 38.0F * Settings.scale);
/*  39 */     this.bgHb = new Hitbox(300.0F * Settings.scale, 38.0F * Settings.scale);
/*  40 */     this.bgHb.move(BG_X, y);
/*  41 */     this.x = L_X + SLIDE_W * volume;
/*     */   }
/*     */   
/*     */   public void update() {
/*  45 */     this.hb.update();
/*  46 */     this.bgHb.update();
/*  47 */     this.hb.move(this.x, this.y);
/*     */ 
/*     */     
/*  50 */     if (this.sliderGrabbed) {
/*  51 */       if (InputHelper.isMouseDown) {
/*  52 */         this.x = MathHelper.fadeLerpSnap(this.x, InputHelper.mX);
/*  53 */         if (this.x < L_X) {
/*  54 */           this.x = L_X;
/*  55 */         } else if (this.x > L_X + SLIDE_W) {
/*  56 */           this.x = L_X + SLIDE_W;
/*     */         } 
/*  58 */         this.volume = (this.x - L_X) / SLIDE_W;
/*  59 */         modifyVolume();
/*     */       } else {
/*  61 */         if (this.type == SliderType.SFX) {
/*  62 */           int roll = MathUtils.random(2);
/*  63 */           if (roll == 0) {
/*  64 */             CardCrawlGame.sound.play("ATTACK_DAGGER_1");
/*  65 */           } else if (roll == 1) {
/*  66 */             CardCrawlGame.sound.play("ATTACK_DAGGER_2");
/*  67 */           } else if (roll == 2) {
/*  68 */             CardCrawlGame.sound.play("ATTACK_DAGGER_3");
/*     */           } 
/*     */         } 
/*  71 */         this.sliderGrabbed = false;
/*  72 */         Settings.soundPref.flush();
/*     */       } 
/*  74 */     } else if (InputHelper.justClickedLeft) {
/*  75 */       if (this.hb.hovered) {
/*  76 */         this.sliderGrabbed = true;
/*  77 */       } else if (this.bgHb.hovered) {
/*  78 */         this.sliderGrabbed = true;
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     if (Settings.isControllerMode && 
/*  83 */       this.bgHb.hovered) {
/*  84 */       if (CInputActionSet.inspectLeft.isJustPressed()) {
/*  85 */         this.x -= 5.0F * Settings.scale;
/*  86 */         if (this.x < L_X) {
/*  87 */           this.x = L_X;
/*     */         }
/*  89 */         this.volume = (this.x - L_X) / SLIDE_W;
/*  90 */         modifyVolume();
/*  91 */       } else if (CInputActionSet.inspectRight.isJustPressed()) {
/*  92 */         this.x += 5.0F * Settings.scale;
/*  93 */         if (this.x > L_X + SLIDE_W) {
/*  94 */           this.x = L_X + SLIDE_W;
/*     */         }
/*  96 */         this.volume = (this.x - L_X) / SLIDE_W;
/*  97 */         modifyVolume();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void modifyVolume() {
/* 104 */     switch (this.type) {
/*     */       case MASTER:
/* 106 */         Settings.MASTER_VOLUME = this.volume;
/* 107 */         Settings.soundPref.putFloat("Master Volume", this.volume);
/*     */         
/* 109 */         CardCrawlGame.music.updateVolume();
/* 110 */         if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/* 111 */           CardCrawlGame.mainMenuScreen.updateAmbienceVolume(); break;
/* 112 */         }  if (AbstractDungeon.scene != null) {
/* 113 */           AbstractDungeon.scene.updateAmbienceVolume();
/*     */         }
/*     */         break;
/*     */       case BGM:
/* 117 */         Settings.MUSIC_VOLUME = this.volume;
/* 118 */         CardCrawlGame.music.updateVolume();
/* 119 */         Settings.soundPref.putFloat("Music Volume", this.volume);
/*     */         break;
/*     */       case SFX:
/* 122 */         Settings.SOUND_VOLUME = this.volume;
/* 123 */         if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/* 124 */           CardCrawlGame.mainMenuScreen.updateAmbienceVolume();
/* 125 */         } else if (AbstractDungeon.scene != null) {
/* 126 */           AbstractDungeon.scene.updateAmbienceVolume();
/*     */         } 
/* 128 */         Settings.soundPref.putFloat("Sound Volume", this.volume);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 136 */     sb.setColor(Color.WHITE);
/*     */     
/* 138 */     if (Settings.isControllerMode && this.bgHb.hovered) {
/* 139 */       sb.draw(ImageMaster.CONTROLLER_RS, this.bgHb.cX + 195.0F * Settings.scale, this.bgHb.cY - 46.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     }
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
/* 158 */     sb.draw(ImageMaster.OPTION_SLIDER_BG, BG_X - 125.0F, this.y - 12.0F, 125.0F, 12.0F, 250.0F, 24.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 250, 24, false, false);
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
/* 176 */     if (this.sliderGrabbed) {
/* 177 */       FontHelper.renderFontCentered(sb, FontHelper.tipBodyFont, df
/*     */ 
/*     */           
/* 180 */           .format((this.volume * 100.0F)) + '%', BG_X + 170.0F * Settings.scale, this.y, Settings.GREEN_TEXT_COLOR);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 185 */       FontHelper.renderFontCentered(sb, FontHelper.tipBodyFont, df
/*     */ 
/*     */           
/* 188 */           .format((this.volume * 100.0F)) + '%', BG_X + 170.0F * Settings.scale, this.y, Settings.BLUE_TEXT_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     sb.draw(ImageMaster.OPTION_SLIDER, this.x - 22.0F, this.y - 22.0F, 22.0F, 22.0F, 44.0F, 44.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 44, 44, false, false);
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
/* 212 */     this.hb.render(sb);
/* 213 */     this.bgHb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\Slider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */