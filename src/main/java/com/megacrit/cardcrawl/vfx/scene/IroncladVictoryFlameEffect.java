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
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class IroncladVictoryFlameEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 15 */   private boolean flipX = MathUtils.randomBoolean(); private float vX; private float vY;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public IroncladVictoryFlameEffect() {
/* 19 */     this.duration = 1.0F;
/* 20 */     this.startingDuration = this.duration;
/* 21 */     this.renderBehind = MathUtils.randomBoolean();
/*    */     
/* 23 */     switch (MathUtils.random(2)) {
/*    */       case 0:
/* 25 */         this.img = ImageMaster.FLAME_1;
/*    */         break;
/*    */       case 1:
/* 28 */         this.img = ImageMaster.FLAME_2;
/*    */         break;
/*    */       default:
/* 31 */         this.img = ImageMaster.FLAME_3;
/*    */         break;
/*    */     } 
/*    */     
/* 35 */     this.x = MathUtils.random(600.0F, 1320.0F) * Settings.xScale - this.img.packedWidth / 2.0F;
/* 36 */     this.y = -300.0F * Settings.scale - this.img.packedHeight / 2.0F;
/*    */     
/* 38 */     if (this.x > 960.0F * Settings.xScale) {
/* 39 */       this.vX = MathUtils.random(0.0F, -120.0F) * Settings.xScale;
/*    */     } else {
/* 41 */       this.vX = MathUtils.random(120.0F, 0.0F) * Settings.xScale;
/*    */     } 
/*    */     
/* 44 */     this.vY = MathUtils.random(600.0F, 800.0F) * Settings.scale;
/*    */     
/* 46 */     this
/*    */ 
/*    */       
/* 49 */       .color = new Color(MathUtils.random(0.4F, 0.8F), MathUtils.random(0.1F, 0.4F), MathUtils.random(0.4F, 0.9F), 0.8F);
/*    */ 
/*    */     
/* 52 */     this.renderBehind = false;
/* 53 */     this.scale = MathUtils.random(6.0F, 7.0F) * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 58 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 59 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 61 */     this.color.a = Interpolation.pow3Out.apply(0.0F, 0.8F, this.duration / this.startingDuration);
/*    */     
/* 63 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 64 */     this.scale += Gdx.graphics.getDeltaTime();
/*    */     
/* 66 */     if (this.duration < 0.0F) {
/* 67 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 73 */     sb.setColor(this.color);
/* 74 */     if (this.flipX && !this.img.isFlipX()) {
/* 75 */       this.img.flip(true, false);
/* 76 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 77 */       this.img.flip(true, false);
/*    */     } 
/*    */     
/* 80 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\IroncladVictoryFlameEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */