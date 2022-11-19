/*    */ package com.megacrit.cardcrawl.vfx.scene;
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class SpookierChestEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 16 */   private boolean flipX = MathUtils.randomBoolean(); private float vX; private float vY; private float aV; private boolean flipY = MathUtils.randomBoolean();
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public SpookierChestEffect() {
/* 20 */     this.duration = 3.0F;
/* 21 */     this.startingDuration = this.duration;
/*    */     
/* 23 */     switch (MathUtils.random(2)) {
/*    */       case 0:
/* 25 */         this.img = ImageMaster.SMOKE_1;
/*    */         break;
/*    */       case 1:
/* 28 */         this.img = ImageMaster.SMOKE_2;
/*    */         break;
/*    */       default:
/* 31 */         this.img = ImageMaster.SMOKE_3;
/*    */         break;
/*    */     } 
/*    */     
/* 35 */     this.x = AbstractChest.CHEST_LOC_X + MathUtils.random(-30.0F, 30.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 36 */     this.y = AbstractChest.CHEST_LOC_Y - MathUtils.random(120.0F, 190.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 37 */     this.vX = MathUtils.random(-120.0F, 120.0F) * Settings.scale;
/* 38 */     this.vY = MathUtils.random(-50.0F, 100.0F) * Settings.scale;
/* 39 */     this.aV = MathUtils.random(-150.0F, 150.0F);
/*    */     
/* 41 */     float tmp = MathUtils.random(0.3F, 0.9F);
/* 42 */     this.color = new Color();
/* 43 */     this.color.r = tmp * MathUtils.random(0.7F, 1.0F);
/* 44 */     this.color.g = tmp * MathUtils.random(0.5F, 0.9F);
/* 45 */     this.color.b = tmp;
/* 46 */     this.renderBehind = true;
/* 47 */     this.scale = MathUtils.random(0.5F, 3.0F) * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 52 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 53 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 54 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/*    */     
/* 56 */     if (this.startingDuration - this.duration < 1.0F) {
/* 57 */       this.color.a = Interpolation.fade.apply(0.0F, 0.3F, (this.startingDuration - this.duration) / 1.0F);
/* 58 */     } else if (this.duration < 2.0F) {
/* 59 */       this.color.a = Interpolation.fade.apply(0.3F, 0.0F, 1.0F - this.duration / 2.0F);
/*    */     } 
/* 61 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 62 */     this.scale += Gdx.graphics.getDeltaTime();
/* 63 */     if (this.duration < 0.0F) {
/* 64 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 70 */     sb.setColor(this.color);
/* 71 */     if (this.flipX && !this.img.isFlipX()) {
/* 72 */       this.img.flip(true, false);
/* 73 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 74 */       this.img.flip(true, false);
/*    */     } 
/* 76 */     if (this.flipY && !this.img.isFlipY()) {
/* 77 */       this.img.flip(false, true);
/* 78 */     } else if (!this.flipY && this.img.isFlipY()) {
/* 79 */       this.img.flip(false, true);
/*    */     } 
/*    */     
/* 82 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\SpookierChestEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */