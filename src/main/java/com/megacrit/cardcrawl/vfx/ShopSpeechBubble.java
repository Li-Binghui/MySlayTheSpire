/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ public class ShopSpeechBubble extends AbstractGameEffect {
/*     */   private static final int RAW_W = 512;
/*  14 */   private static final float SHADOW_OFFSET = 16.0F * Settings.scale;
/*  15 */   private static final float WAVY_DISTANCE = 2.0F * Settings.scale;
/*  16 */   private static final float ADJUST_X = 170.0F * Settings.scale;
/*  17 */   private static final float ADJUST_Y = 116.0F * Settings.scale;
/*     */   
/*     */   public static final float FADE_TIME = 0.3F;
/*  20 */   private float shadow_offset = 0.0F;
/*     */   private float x;
/*     */   private float y;
/*  23 */   private float scaleTimer = 0.3F; private float wavy_y; private float wavyHelper;
/*     */   private boolean facingRight;
/*     */   public Hitbox hb;
/*  26 */   private Color shadowColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*     */   
/*     */   public ShopSpeechBubble(float x, float y, String msg, boolean isPlayer) {
/*  29 */     this(x, y, 2.0F, msg, isPlayer);
/*     */   }
/*     */   
/*     */   public ShopSpeechBubble(float x, float y, float duration, String msg, boolean isPlayer) {
/*  33 */     if (isPlayer) {
/*  34 */       this.x = x + ADJUST_X;
/*     */     } else {
/*  36 */       this.x = x - ADJUST_X;
/*     */     } 
/*     */     
/*  39 */     this.y = y + ADJUST_Y;
/*  40 */     this.scale = Settings.scale / 2.0F;
/*  41 */     this.color = new Color(0.8F, 0.9F, 0.9F, 0.0F);
/*  42 */     this.duration = duration;
/*  43 */     this.facingRight = !isPlayer;
/*     */     
/*  45 */     if (!this.facingRight) {
/*  46 */       this.hb = new Hitbox(x, y, 350.0F * Settings.scale, 270.0F * Settings.scale);
/*     */     } else {
/*  48 */       this.hb = new Hitbox(x - 350.0F * Settings.scale, y, 350.0F * Settings.scale, 270.0F * Settings.scale);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/*  53 */     updateScale();
/*  54 */     this.hb.update();
/*     */     
/*  56 */     this.wavyHelper += Gdx.graphics.getDeltaTime() * 4.0F;
/*  57 */     this.wavy_y = MathUtils.sin(this.wavyHelper) * WAVY_DISTANCE;
/*     */     
/*  59 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  60 */     if (this.duration < 0.0F) {
/*  61 */       this.isDone = true;
/*     */     }
/*     */     
/*  64 */     if (this.duration > 0.3F) {
/*  65 */       this.color.a = MathUtils.lerp(this.color.a, 1.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*     */     } else {
/*  67 */       this.color.a = MathUtils.lerp(this.color.a, 0.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*     */     } 
/*  69 */     this.shadow_offset = MathUtils.lerp(this.shadow_offset, SHADOW_OFFSET, Gdx.graphics.getDeltaTime() * 4.0F);
/*     */   }
/*     */   
/*     */   private void updateScale() {
/*  73 */     this.scaleTimer -= Gdx.graphics.getDeltaTime();
/*  74 */     if (this.scaleTimer < 0.0F) {
/*  75 */       this.scaleTimer = 0.0F;
/*     */     }
/*     */     
/*  78 */     if (Settings.isMobile) {
/*  79 */       this.scale = Interpolation.swingIn.apply(Settings.scale * 1.15F, Settings.scale / 2.0F, this.scaleTimer / 0.3F);
/*     */     } else {
/*  81 */       this.scale = Interpolation.swingIn.apply(Settings.scale, Settings.scale / 2.0F, this.scaleTimer / 0.3F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  88 */     this.color.a /= 4.0F;
/*  89 */     sb.setColor(this.shadowColor);
/*  90 */     sb.draw(ImageMaster.SHOP_SPEECH_BUBBLE_IMG, this.x - 256.0F + this.shadow_offset, this.y - 256.0F - this.shadow_offset + this.wavy_y, 256.0F, 256.0F, 512.0F, 512.0F, this.scale, this.scale, this.rotation, 0, 0, 512, 512, this.facingRight, false);
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
/*     */     
/* 109 */     sb.setColor(this.color);
/* 110 */     sb.draw(ImageMaster.SHOP_SPEECH_BUBBLE_IMG, this.x - 256.0F, this.y - 256.0F + this.wavy_y, 256.0F, 256.0F, 512.0F, 512.0F, this.scale, this.scale, this.rotation, 0, 0, 512, 512, this.facingRight, false);
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
/* 128 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ShopSpeechBubble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */