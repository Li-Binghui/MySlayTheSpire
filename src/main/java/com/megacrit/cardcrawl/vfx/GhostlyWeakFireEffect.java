/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class GhostlyWeakFireEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public GhostlyWeakFireEffect(float x, float y) {
/* 18 */     this.img = getImg();
/* 19 */     this.x = x + MathUtils.random(-2.0F, 2.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 20 */     this.y = y + MathUtils.random(-2.0F, 2.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 21 */     this.vX = MathUtils.random(-2.0F, 2.0F) * Settings.scale;
/* 22 */     this.vY = MathUtils.random(0.0F, 80.0F) * Settings.scale;
/* 23 */     this.duration = 1.0F;
/* 24 */     this.color = Color.SKY.cpy();
/* 25 */     this.color.a = 0.0F;
/* 26 */     this.scale = Settings.scale * MathUtils.random(2.0F, 3.0F);
/*    */   }
/*    */   private float vX; private float vY; private static final float DUR = 1.0F;
/*    */   private TextureAtlas.AtlasRegion getImg() {
/* 30 */     switch (MathUtils.random(0, 2)) {
/*    */       case 0:
/* 32 */         return ImageMaster.TORCH_FIRE_1;
/*    */       case 1:
/* 34 */         return ImageMaster.TORCH_FIRE_2;
/*    */     } 
/* 36 */     return ImageMaster.TORCH_FIRE_3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 42 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 43 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 44 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 45 */     if (this.duration < 0.0F) {
/* 46 */       this.isDone = true;
/*    */     }
/* 48 */     this.color.a = this.duration / 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 53 */     sb.setBlendFunction(770, 1);
/* 54 */     sb.setColor(this.color);
/* 55 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 66 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\GhostlyWeakFireEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */