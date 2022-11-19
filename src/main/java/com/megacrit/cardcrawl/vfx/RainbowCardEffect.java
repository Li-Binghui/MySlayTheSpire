/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ public class RainbowCardEffect
/*     */   extends AbstractGameEffect {
/*     */   float x;
/*     */   
/*     */   public RainbowCardEffect() {
/*  19 */     if (this.img == null) {
/*  20 */       this.img = ImageMaster.CRYSTAL_IMPACT;
/*     */     }
/*     */     
/*  23 */     this.x = AbstractDungeon.player.hb.cX - this.img.packedWidth / 2.0F;
/*  24 */     this.y = AbstractDungeon.player.hb.cY - this.img.packedHeight / 2.0F;
/*     */     
/*  26 */     this.startingDuration = 1.5F;
/*  27 */     this.duration = this.startingDuration;
/*  28 */     this.scale = Settings.scale;
/*  29 */     this.color = Color.CYAN.cpy();
/*  30 */     this.color.a = 0.0F;
/*  31 */     this.renderBehind = true;
/*     */   }
/*     */   float y; private TextureAtlas.AtlasRegion img;
/*     */   public void update() {
/*  35 */     if (this.duration == this.startingDuration) {
/*  36 */       CardCrawlGame.sound.playA("HEAL_3", 0.5F);
/*     */     }
/*     */     
/*  39 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  41 */     if (this.duration > this.startingDuration / 2.0F) {
/*  42 */       this.color.a = Interpolation.fade.apply(1.0F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;
/*     */     } else {
/*  44 */       this.color.a = Interpolation.fade.apply(0.01F, 1.0F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*     */     } 
/*     */     
/*  47 */     this.scale = Interpolation.elasticIn.apply(4.0F, 0.01F, this.duration / this.startingDuration) * Settings.scale;
/*     */     
/*  49 */     if (this.duration < 0.0F) {
/*  50 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  56 */     sb.setColor(new Color(1.0F, 0.2F, 0.2F, this.color.a));
/*  57 */     sb.setBlendFunction(770, 1);
/*  58 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 1.15F, this.scale * 1.15F, 0.0F);
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
/*  70 */     sb.setColor(new Color(1.0F, 1.0F, 0.2F, this.color.a));
/*  71 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, 0.0F);
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
/*  83 */     sb.setColor(new Color(0.2F, 1.0F, 0.2F, this.color.a));
/*  84 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.85F, this.scale * 0.85F, 0.0F);
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
/*  96 */     sb.setColor(new Color(0.2F, 0.7F, 1.0F, this.color.a));
/*  97 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.7F, this.scale * 0.7F, 0.0F);
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
/* 108 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\RainbowCardEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */