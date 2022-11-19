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
/*    */ public class GhostlyFireEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public GhostlyFireEffect(float x, float y) {
/* 20 */     this.img = getImg();
/* 21 */     this.x = x + MathUtils.random(-2.0F, 2.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 22 */     this.y = y + MathUtils.random(-2.0F, 2.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 23 */     this.vX = MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 24 */     this.vY = MathUtils.random(20.0F, 150.0F) * Settings.scale;
/* 25 */     this.duration = 1.0F;
/* 26 */     this.color = Color.CHARTREUSE.cpy();
/* 27 */     this.color.a = 0.0F;
/* 28 */     this.scale = Settings.scale * MathUtils.random(5.0F, 6.0F);
/*    */   }
/*    */   private float vX; private float vY; private static final float DUR = 1.0F;
/*    */   private TextureAtlas.AtlasRegion getImg() {
/* 32 */     switch (MathUtils.random(0, 2)) {
/*    */       case 0:
/* 34 */         return ImageMaster.TORCH_FIRE_1;
/*    */       case 1:
/* 36 */         return ImageMaster.TORCH_FIRE_2;
/*    */     } 
/* 38 */     return ImageMaster.TORCH_FIRE_3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 44 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 45 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 46 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 47 */     if (this.duration < 0.0F) {
/* 48 */       this.isDone = true;
/*    */     }
/*    */     
/* 51 */     if (this.scale > 0.1F) {
/* 52 */       this.scale -= Gdx.graphics.getDeltaTime() / 4.0F;
/*    */     }
/* 54 */     this.color.a = this.duration / 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 59 */     sb.setBlendFunction(770, 1);
/* 60 */     sb.setColor(this.color);
/* 61 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 69 */         MathUtils.random(0.95F, 1.05F), this.scale * 
/* 70 */         MathUtils.random(0.95F, 1.05F), this.rotation);
/*    */     
/* 72 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\GhostlyFireEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */