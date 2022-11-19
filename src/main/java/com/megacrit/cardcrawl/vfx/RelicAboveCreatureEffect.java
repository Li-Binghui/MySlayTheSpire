/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ 
/*     */ public class RelicAboveCreatureEffect
/*     */   extends AbstractGameEffect {
/*     */   private static final float TEXT_DURATION = 1.5F;
/*  16 */   private static final float STARTING_OFFSET_Y = 0.0F * Settings.scale;
/*  17 */   private static final float TARGET_OFFSET_Y = 60.0F * Settings.scale;
/*     */   
/*     */   private static final float LERP_RATE = 5.0F;
/*     */   private static final int W = 128;
/*     */   private float x;
/*  22 */   private Color outlineColor = new Color(0.0F, 0.0F, 0.0F, 0.0F); private float y; private float offsetY; private AbstractRelic relic;
/*  23 */   private Color shineColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   
/*     */   public RelicAboveCreatureEffect(float x, float y, AbstractRelic relic) {
/*  26 */     this.duration = 1.5F;
/*  27 */     this.startingDuration = 1.5F;
/*  28 */     this.relic = relic;
/*  29 */     this.x = x;
/*  30 */     this.y = y;
/*  31 */     this.color = Color.WHITE.cpy();
/*  32 */     this.offsetY = STARTING_OFFSET_Y;
/*  33 */     this.scale = Settings.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  38 */     if (this.duration > 1.0F) {
/*  39 */       this.color.a = Interpolation.exp5In.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
/*     */     }
/*  41 */     super.update();
/*  42 */     if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.DEFECT) {
/*  43 */       this.offsetY = MathUtils.lerp(this.offsetY, TARGET_OFFSET_Y + 80.0F * Settings.scale, Gdx.graphics
/*     */ 
/*     */           
/*  46 */           .getDeltaTime() * 5.0F);
/*     */     } else {
/*  48 */       this.offsetY = MathUtils.lerp(this.offsetY, TARGET_OFFSET_Y, Gdx.graphics.getDeltaTime() * 5.0F);
/*     */     } 
/*  50 */     this.y += Gdx.graphics.getDeltaTime() * 12.0F * Settings.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  55 */     this.color.a /= 2.0F;
/*  56 */     sb.setColor(this.outlineColor);
/*  57 */     sb.draw(this.relic.outlineImg, this.x - 64.0F, this.y - 64.0F + this.offsetY, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * (2.5F - this.duration), this.scale * (2.5F - this.duration), this.rotation, 0, 0, 128, 128, false, false);
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
/*  75 */     sb.setColor(this.color);
/*  76 */     sb.draw(this.relic.img, this.x - 64.0F, this.y - 64.0F + this.offsetY, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * (2.5F - this.duration), this.scale * (2.5F - this.duration), this.rotation, 0, 0, 128, 128, false, false);
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
/*  93 */     sb.setBlendFunction(770, 1);
/*  94 */     this.color.a /= 4.0F;
/*  95 */     sb.setColor(this.shineColor);
/*  96 */     sb.draw(this.relic.img, this.x - 64.0F, this.y - 64.0F + this.offsetY, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * (2.7F - this.duration), this.scale * (2.7F - this.duration), this.rotation, 0, 0, 128, 128, false, false);
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
/* 114 */     sb.draw(this.relic.img, this.x - 64.0F, this.y - 64.0F + this.offsetY, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * (3.0F - this.duration), this.scale * (3.0F - this.duration), this.rotation, 0, 0, 128, 128, false, false);
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
/* 132 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\RelicAboveCreatureEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */