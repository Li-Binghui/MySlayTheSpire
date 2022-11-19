/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.ui.DialogWord;
/*     */ 
/*     */ public class MegaSpeechBubble extends AbstractGameEffect {
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
/*     */   private boolean facingRight;
/*     */   private static final float DEFAULT_DURATION = 2.0F;
/*     */   private static final float FADE_TIME = 0.3F;
/*     */   
/*     */   public MegaSpeechBubble(float x, float y, String msg, boolean isPlayer) {
/*  29 */     this(x, y, 2.0F, msg, isPlayer);
/*     */   }
/*     */   
/*     */   public MegaSpeechBubble(float x, float y, float duration, String msg, boolean isPlayer) {
/*  33 */     float effect_x = -170.0F * Settings.scale;
/*  34 */     if (isPlayer) {
/*  35 */       effect_x = 170.0F * Settings.scale;
/*     */     }
/*     */     
/*  38 */     AbstractDungeon.effectsQueue.add(new MegaDialogTextEffect(x + effect_x, y + 124.0F * Settings.scale, duration, msg, DialogWord.AppearEffect.BUMP_IN));
/*     */ 
/*     */     
/*  41 */     if (isPlayer) {
/*  42 */       this.x = x + ADJUST_X;
/*     */     } else {
/*  44 */       this.x = x - ADJUST_X;
/*     */     } 
/*  46 */     this.y = y + ADJUST_Y;
/*  47 */     this.scale_x = Settings.scale * 0.7F;
/*  48 */     this.scale_y = Settings.scale * 0.7F;
/*  49 */     this.scaleTimer = 0.3F;
/*  50 */     this.color = new Color(0.8F, 0.9F, 0.9F, 0.0F);
/*  51 */     this.duration = duration;
/*  52 */     this.facingRight = !isPlayer;
/*     */   }
/*     */   
/*     */   public void update() {
/*  56 */     updateScale();
/*     */     
/*  58 */     this.wavyHelper += Gdx.graphics.getDeltaTime() * 5.0F;
/*  59 */     this.wavy_y = MathUtils.sin(this.wavyHelper) * WAVY_DISTANCE;
/*     */     
/*  61 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  62 */     if (this.duration < 0.0F) {
/*  63 */       this.isDone = true;
/*     */     }
/*     */     
/*  66 */     if (this.duration > 0.3F) {
/*  67 */       this.color.a = MathUtils.lerp(this.color.a, 1.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*     */     } else {
/*  69 */       this.color.a = MathUtils.lerp(this.color.a, 0.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*     */     } 
/*  71 */     this.shadow_offset = MathUtils.lerp(this.shadow_offset, SHADOW_OFFSET, Gdx.graphics.getDeltaTime() * 4.0F);
/*     */   }
/*     */   
/*     */   private void updateScale() {
/*  75 */     this.scaleTimer -= Gdx.graphics.getDeltaTime();
/*  76 */     if (this.scaleTimer < 0.0F) {
/*  77 */       this.scaleTimer = 0.0F;
/*     */     }
/*     */     
/*  80 */     if (Settings.isMobile) {
/*  81 */       this.scale_x = Interpolation.swingIn.apply(this.scale_x * 1.15F, Settings.scale, this.scaleTimer / 0.3F);
/*  82 */       this.scale_y = Interpolation.swingIn.apply(this.scale_y * 1.15F, Settings.scale, this.scaleTimer / 0.3F);
/*     */     } else {
/*  84 */       this.scale_x = Interpolation.swingIn.apply(this.scale_x, Settings.scale, this.scaleTimer / 0.3F);
/*  85 */       this.scale_y = Interpolation.swingIn.apply(this.scale_y, Settings.scale, this.scaleTimer / 0.3F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  93 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a / 4.0F));
/*  94 */     sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F + this.shadow_offset, this.y - 256.0F - this.shadow_offset + this.wavy_y, 256.0F, 256.0F, 512.0F, 512.0F, this.scale_x, this.scale_y, this.rotation, 0, 0, 512, 512, this.facingRight, false);
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
/* 112 */     sb.setColor(this.color);
/* 113 */     sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F, this.y - 256.0F + this.wavy_y, 256.0F, 256.0F, 512.0F, 512.0F, this.scale_x, this.scale_y, this.rotation, 0, 0, 512, 512, this.facingRight, false);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\MegaSpeechBubble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */