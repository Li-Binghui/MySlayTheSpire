/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class ConeEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public ConeEffect() {
/* 17 */     switch (MathUtils.random(1, 6)) {
/*    */       case 1:
/* 19 */         this.img = ImageMaster.CONE_3;
/*    */         break;
/*    */       default:
/* 22 */         if (MathUtils.randomBoolean()) {
/* 23 */           this.img = ImageMaster.CONE_1; break;
/*    */         } 
/* 25 */         this.img = ImageMaster.CONE_2;
/*    */         break;
/*    */     } 
/*    */ 
/*    */     
/* 30 */     this.x = Settings.WIDTH / 2.0F;
/* 31 */     this.y = Settings.HEIGHT / 2.0F - this.img.packedHeight / 2.0F;
/* 32 */     this.duration = MathUtils.random(2.0F, 5.0F);
/* 33 */     this.startingDuration = this.duration;
/* 34 */     this.rotation = MathUtils.random(360.0F);
/* 35 */     this.aV = MathUtils.random(-10.0F, 10.0F);
/* 36 */     this.aV *= 2.0F;
/* 37 */     this.color = new Color(1.0F, MathUtils.random(0.7F, 0.8F), 0.2F, 0.0F);
/* 38 */     this.scale = Settings.scale;
/*    */   }
/*    */   private float aV; private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public void update() {
/* 43 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/* 44 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 46 */     if (this.duration < 0.0F) {
/* 47 */       this.isDone = true;
/* 48 */     } else if (this.startingDuration - this.duration < 1.0F) {
/* 49 */       this.color.a = (this.startingDuration - this.duration) / 3.0F;
/* 50 */     } else if (this.duration < 1.0F) {
/* 51 */       this.color.a = Interpolation.fade.apply(0.0F, 0.33F, this.duration);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 57 */     sb.setColor(this.color);
/* 58 */     sb.draw((TextureRegion)this.img, this.x, this.y, 0.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 1.1F, this.scale * 1.1F, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ConeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */