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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/*    */
/*    */ public class CeilingDustCloudEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vY;
/*    */   private float vX;
/*    */   
/*    */   public CeilingDustCloudEffect(float x, float y) {
/* 18 */     if (this.img == null) {
/* 19 */       this.img = ImageMaster.vfxAtlas.findRegion("env/dustCloud");
/*    */     }
/*    */     
/* 22 */     this.x = x + MathUtils.random(-40.0F, 40.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 23 */     this.y = y - this.img.packedHeight / 2.0F;
/* 24 */     float randY = MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 25 */     y += randY;
/* 26 */     this.renderBehind = (randY < 0.0F);
/*    */     
/* 28 */     this.vY = MathUtils.random(0.0F, 20.0F) * Settings.scale;
/* 29 */     this.vX = MathUtils.random(-30.0F, 30.0F) * Settings.scale;
/* 30 */     this.duration = MathUtils.random(3.0F, 7.0F);
/* 31 */     this.scale = Settings.scale * MathUtils.random(0.1F, 0.7F);
/* 32 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 33 */     float c = MathUtils.random(0.1F, 0.3F);
/* 34 */     this.color = new Color(c + 0.1F, c, c, 0.0F);
/* 35 */     this.color.a = MathUtils.random(0.1F, 0.2F);
/* 36 */     this.startingAlpha = this.color.a;
/* 37 */     this.aV = MathUtils.random(-0.1F, 0.1F);
/*    */   }
/*    */   private float vYAccel; private float aV; private float startingAlpha; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 41 */     this.rotation += this.aV;
/* 42 */     this.y -= this.vY * Gdx.graphics.getDeltaTime();
/* 43 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 44 */     this.vY += this.vYAccel * Gdx.graphics.getDeltaTime();
/* 45 */     this.vX *= 0.99F;
/* 46 */     this.scale += Gdx.graphics.getDeltaTime() * 0.2F;
/*    */     
/* 48 */     if (this.duration < 3.0F) {
/* 49 */       this.color.a = Interpolation.fade.apply(this.startingAlpha, 0.0F, 1.0F - this.duration / 3.0F);
/*    */     }
/*    */     
/* 52 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 53 */     if (this.duration < 0.0F) {
/* 54 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 60 */     sb.setColor(this.color);
/* 61 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\CeilingDustCloudEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */