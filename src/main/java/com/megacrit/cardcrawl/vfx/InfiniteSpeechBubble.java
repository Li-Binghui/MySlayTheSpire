/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.DialogWord;
/*     */ 
/*     */ public class InfiniteSpeechBubble extends AbstractGameEffect {
/*     */   private static final int RAW_W = 512;
/*  15 */   private float shadow_offset = 0.0F;
/*  16 */   private static final float SHADOW_OFFSET = 16.0F * Settings.scale; private float x;
/*     */   private float y;
/*     */   private float scale_x;
/*  19 */   private static final float WAVY_DISTANCE = 2.0F * Settings.scale; private float scale_y; private float wavy_y; private float wavyHelper;
/*     */   private static final float SCALE_TIME = 0.3F;
/*  21 */   private float scaleTimer = 0.3F;
/*  22 */   private static final float ADJUST_X = 170.0F * Settings.scale;
/*  23 */   private static final float ADJUST_Y = 116.0F * Settings.scale;
/*     */ 
/*     */   
/*     */   private boolean facingRight;
/*     */ 
/*     */   
/*     */   private static final float FADE_TIME = 0.3F;
/*     */ 
/*     */   
/*     */   private SpeechTextEffect textEffect;
/*     */ 
/*     */   
/*     */   public InfiniteSpeechBubble(float x, float y, String msg) {
/*  36 */     this.textEffect = new SpeechTextEffect(x - 170.0F * Settings.scale, y + 124.0F * Settings.scale, Float.MAX_VALUE, msg, DialogWord.AppearEffect.BUMP_IN);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     AbstractDungeon.effectsQueue.add(this.textEffect);
/*     */     
/*  44 */     this.x = x - ADJUST_X;
/*  45 */     this.y = y + ADJUST_Y;
/*  46 */     this.scaleTimer = 0.3F;
/*  47 */     this.color = new Color(0.8F, 0.9F, 0.9F, 0.0F);
/*  48 */     this.duration = Float.MAX_VALUE;
/*  49 */     this.facingRight = true;
/*     */   }
/*     */   
/*     */   public void dismiss() {
/*  53 */     this.duration = 0.3F;
/*  54 */     this.textEffect.duration = 0.3F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  58 */     updateScale();
/*     */     
/*  60 */     this.wavyHelper += Gdx.graphics.getDeltaTime() * 4.0F;
/*  61 */     this.wavy_y = MathUtils.sin(this.wavyHelper) * WAVY_DISTANCE;
/*     */     
/*  63 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  64 */     if (this.duration < 0.0F) {
/*  65 */       this.isDone = true;
/*     */     }
/*     */     
/*  68 */     if (this.duration > 0.3F) {
/*  69 */       this.color.a = MathUtils.lerp(this.color.a, 1.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*     */     } else {
/*  71 */       this.color.a = MathUtils.lerp(this.color.a, 0.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*     */     } 
/*  73 */     this.shadow_offset = MathUtils.lerp(this.shadow_offset, SHADOW_OFFSET, Gdx.graphics.getDeltaTime() * 4.0F);
/*     */   }
/*     */   
/*     */   private void updateScale() {
/*  77 */     this.scaleTimer -= Gdx.graphics.getDeltaTime();
/*  78 */     if (this.scaleTimer < 0.0F) {
/*  79 */       this.scaleTimer = 0.0F;
/*     */     }
/*     */     
/*  82 */     if (Settings.isMobile) {
/*  83 */       this.scale_x = Interpolation.circleIn.apply(Settings.scale * 1.15F, Settings.scale * 0.5F, this.scaleTimer / 0.3F);
/*     */ 
/*     */ 
/*     */       
/*  87 */       this.scale_y = Interpolation.swingIn.apply(Settings.scale * 1.15F, Settings.scale * 0.8F, this.scaleTimer / 0.3F);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  92 */       this.scale_x = Interpolation.circleIn.apply(Settings.scale, Settings.scale * 0.5F, this.scaleTimer / 0.3F);
/*  93 */       this.scale_y = Interpolation.swingIn.apply(Settings.scale, Settings.scale * 0.8F, this.scaleTimer / 0.3F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  99 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a / 4.0F));
/* 100 */     sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F + this.shadow_offset, this.y - 256.0F + this.wavy_y - this.shadow_offset, 256.0F, 256.0F, 512.0F, 512.0F, this.scale_x, this.scale_y, this.rotation, 0, 0, 512, 512, this.facingRight, false);
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
/* 118 */     sb.setColor(this.color);
/* 119 */     sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F, this.y - 256.0F + this.wavy_y, 256.0F, 256.0F, 512.0F, 512.0F, this.scale_x, this.scale_y, this.rotation, 0, 0, 512, 512, this.facingRight, false);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\InfiniteSpeechBubble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */