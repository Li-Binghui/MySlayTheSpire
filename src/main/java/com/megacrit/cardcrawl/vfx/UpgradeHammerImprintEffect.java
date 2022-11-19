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
/*    */ public class UpgradeHammerImprintEffect extends AbstractGameEffect {
/* 13 */   private TextureAtlas.AtlasRegion img = ImageMaster.UPGRADE_HAMMER_IMPACT;
/*    */   
/*    */   private static final float DUR = 0.7F;
/*    */   private float x;
/* 17 */   private Color shineColor = new Color(1.0F, 1.0F, 1.0F, 0.0F); private float y; private float hammerGlowScale;
/*    */   
/*    */   public UpgradeHammerImprintEffect(float x, float y) {
/* 20 */     this.x = x - (this.img.packedWidth / 2);
/* 21 */     this.y = y - (this.img.packedHeight / 2);
/* 22 */     this.color = Color.WHITE.cpy();
/* 23 */     this.color.a = 0.7F;
/* 24 */     this.duration = 0.7F;
/* 25 */     this.scale = Settings.scale / MathUtils.random(1.0F, 1.5F);
/* 26 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 27 */     this.hammerGlowScale = 1.0F - this.duration;
/* 28 */     this.hammerGlowScale *= this.hammerGlowScale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 32 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 33 */     if (this.duration < 0.0F) {
/* 34 */       this.isDone = true;
/*    */     }
/* 36 */     this.color.a = this.duration;
/*    */     
/* 38 */     this.hammerGlowScale = 1.7F - this.duration;
/* 39 */     this.hammerGlowScale *= this.hammerGlowScale * this.hammerGlowScale;
/* 40 */     this.scale += Gdx.graphics.getDeltaTime() / 20.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 45 */     sb.setBlendFunction(770, 1);
/* 46 */     sb.setColor(this.color);
/* 47 */     sb.draw((TextureRegion)this.img, this.x + 
/*    */         
/* 49 */         MathUtils.random(-2.0F, 2.0F) * Settings.scale, this.y + 
/* 50 */         MathUtils.random(-2.0F, 2.0F) * Settings.scale, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 58 */     this.color.a /= 10.0F;
/* 59 */     sb.setColor(this.shineColor);
/* 60 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.hammerGlowScale, this.hammerGlowScale, this.rotation);
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
/* 71 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\UpgradeHammerImprintEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */