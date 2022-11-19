/*    */ package com.megacrit.cardcrawl.vfx.scene;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TorchParticleLEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vY;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   public static boolean renderGreen = false;
/*    */   
/*    */   public TorchParticleLEffect(float x, float y) {
/* 26 */     this.duration = MathUtils.random(1.5F, 3.0F);
/* 27 */     this.startingDuration = this.duration;
/* 28 */     this.img = getImg();
/* 29 */     this.x = x - (this.img.packedWidth / 2) + MathUtils.random(-3.0F, 3.0F) * Settings.scale;
/* 30 */     this.y = y - (this.img.packedHeight / 2);
/* 31 */     this.scale = Settings.scale * MathUtils.random(2.0F, 3.0F);
/* 32 */     this.vY = MathUtils.random(1.0F, 10.0F);
/* 33 */     this.vY *= this.vY * Settings.scale;
/* 34 */     this.rotation = MathUtils.random(-20.0F, 20.0F);
/*    */     
/* 36 */     if (!renderGreen) {
/* 37 */       this
/*    */ 
/*    */         
/* 40 */         .color = new Color(MathUtils.random(0.6F, 1.0F), MathUtils.random(0.3F, 0.6F), MathUtils.random(0.0F, 0.3F), 0.01F);
/*    */     } else {
/*    */       
/* 43 */       this
/*    */ 
/*    */         
/* 46 */         .color = new Color(MathUtils.random(0.1F, 0.3F), MathUtils.random(0.5F, 0.9F), MathUtils.random(0.1F, 0.3F), 0.01F);
/*    */     } 
/*    */     
/* 49 */     this.renderBehind = true;
/*    */   }
/*    */   
/*    */   private TextureAtlas.AtlasRegion getImg() {
/* 53 */     switch (MathUtils.random(0, 2)) {
/*    */       case 0:
/* 55 */         return ImageMaster.TORCH_FIRE_1;
/*    */       case 1:
/* 57 */         return ImageMaster.TORCH_FIRE_2;
/*    */     } 
/* 59 */     return ImageMaster.TORCH_FIRE_3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 64 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 65 */     if (this.duration < 0.0F) {
/* 66 */       this.isDone = true;
/*    */     }
/* 68 */     this.color.a = Interpolation.fade.apply(0.0F, 0.75F, this.duration / this.startingDuration);
/* 69 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 74 */     sb.setBlendFunction(770, 1);
/* 75 */     sb.setColor(this.color);
/* 76 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
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
/* 87 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\TorchParticleLEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */