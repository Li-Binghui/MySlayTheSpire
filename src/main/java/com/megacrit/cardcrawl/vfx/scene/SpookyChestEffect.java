/*    */ package com.megacrit.cardcrawl.vfx.scene;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class SpookyChestEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 16 */   private boolean flipX = MathUtils.randomBoolean(); private float vX; private float vY; private float aV; private boolean flipY = MathUtils.randomBoolean();
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public SpookyChestEffect() {
/* 20 */     this.duration = MathUtils.random(4.0F, 7.0F);
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
/* 35 */     this.x = AbstractChest.CHEST_LOC_X - this.img.packedWidth / 2.0F;
/* 36 */     this.y = AbstractChest.CHEST_LOC_Y - this.img.packedWidth / 2.0F - 150.0F * Settings.scale;
/* 37 */     this.vX = MathUtils.random(-100.0F, 100.0F) * Settings.scale;
/* 38 */     this.vY = MathUtils.random(-30.0F, 30.0F) * Settings.scale;
/* 39 */     this.aV = MathUtils.random(-100.0F, 100.0F);
/*    */     
/* 41 */     float tmp = MathUtils.random(0.4F, 0.9F);
/* 42 */     this.color = new Color();
/* 43 */     this.color.r = tmp * 0.75F;
/* 44 */     this.color.g = tmp;
/* 45 */     this.color.b = tmp;
/* 46 */     this.renderBehind = true;
/* 47 */     this.scale = MathUtils.random(0.8F, 1.2F) * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 52 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 53 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 54 */     this.vX *= 0.99F;
/* 55 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/*    */     
/* 57 */     if (this.startingDuration - this.duration < 1.5F) {
/* 58 */       this.color.a = Interpolation.fade.apply(0.0F, 0.4F, (this.startingDuration - this.duration) / 1.5F);
/* 59 */     } else if (this.duration < 4.0F) {
/* 60 */       this.color.a = Interpolation.fade.apply(0.4F, 0.0F, 1.0F - this.duration / 4.0F);
/*    */     } 
/* 62 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 63 */     this.scale += Gdx.graphics.getDeltaTime() / 3.0F;
/* 64 */     if (this.duration < 0.0F) {
/* 65 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 71 */     sb.setColor(this.color);
/* 72 */     if (this.flipX && !this.img.isFlipX()) {
/* 73 */       this.img.flip(true, false);
/* 74 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 75 */       this.img.flip(true, false);
/*    */     } 
/* 77 */     if (this.flipY && !this.img.isFlipY()) {
/* 78 */       this.img.flip(false, true);
/* 79 */     } else if (!this.flipY && this.img.isFlipY()) {
/* 80 */       this.img.flip(false, true);
/*    */     } 
/*    */     
/* 83 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\SpookyChestEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */