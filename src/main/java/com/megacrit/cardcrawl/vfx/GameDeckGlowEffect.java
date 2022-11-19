/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class GameDeckGlowEffect extends AbstractGameEffect {
/*    */   private float effectDuration;
/*    */   private float x;
/*    */   private float y;
/*    */   private float vY;
/* 15 */   private Color shadowColor = Color.BLACK.cpy(); private float vX; private float rotator; private boolean flipY; private boolean flipX;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public GameDeckGlowEffect(boolean isAbove) {
/* 19 */     this.effectDuration = MathUtils.random(2.0F, 5.0F);
/* 20 */     this.duration = this.effectDuration;
/* 21 */     this.startingDuration = this.effectDuration;
/* 22 */     this.vY = MathUtils.random(10.0F * Settings.scale, 20.0F * Settings.scale);
/* 23 */     this.vX = MathUtils.random(10.0F * Settings.scale, 20.0F * Settings.scale);
/* 24 */     this.flipY = MathUtils.randomBoolean();
/* 25 */     this.flipX = MathUtils.randomBoolean();
/*    */     
/* 27 */     this.color = Settings.CREAM_COLOR.cpy();
/* 28 */     float darkness = MathUtils.random(0.1F, 0.4F);
/* 29 */     this.color.r -= darkness;
/* 30 */     this.color.g -= darkness;
/* 31 */     this.color.b -= darkness;
/* 32 */     this.img = getImg();
/* 33 */     this.x = MathUtils.random(35.0F, 85.0F) * Settings.scale - (this.img.packedWidth / 2);
/* 34 */     this.y = MathUtils.random(35.0F, 85.0F) * Settings.scale - (this.img.packedHeight / 2);
/* 35 */     this.scale = Settings.scale * 0.75F;
/* 36 */     this.rotator = MathUtils.random(-120.0F, 120.0F);
/*    */   }
/*    */   
/*    */   private TextureAtlas.AtlasRegion getImg() {
/* 40 */     int roll = MathUtils.random(0, 5);
/* 41 */     switch (roll) {
/*    */       case 0:
/* 43 */         return ImageMaster.DECK_GLOW_1;
/*    */       case 1:
/* 45 */         return ImageMaster.DECK_GLOW_2;
/*    */       case 2:
/* 47 */         return ImageMaster.DECK_GLOW_3;
/*    */       case 3:
/* 49 */         return ImageMaster.DECK_GLOW_4;
/*    */       case 4:
/* 51 */         return ImageMaster.DECK_GLOW_5;
/*    */     } 
/* 53 */     return ImageMaster.DECK_GLOW_6;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 58 */     this.rotation += this.rotator * Gdx.graphics.getDeltaTime();
/* 59 */     if (this.vY != 0.0F) {
/* 60 */       if (this.flipY) {
/* 61 */         this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */       } else {
/* 63 */         this.y -= this.vY * Gdx.graphics.getDeltaTime();
/*    */       } 
/* 65 */       this.vY = MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() / 4.0F);
/* 66 */       if (this.vY < 0.5F) {
/* 67 */         this.vY = 0.0F;
/*    */       }
/*    */     } 
/*    */     
/* 71 */     if (this.vX != 0.0F) {
/* 72 */       if (this.flipX) {
/* 73 */         this.x += this.vX * Gdx.graphics.getDeltaTime();
/*    */       } else {
/* 75 */         this.x -= this.vX * Gdx.graphics.getDeltaTime();
/*    */       } 
/* 77 */       this.vX = MathUtils.lerp(this.vX, 0.0F, Gdx.graphics.getDeltaTime() / 4.0F);
/* 78 */       if (this.vX < 0.5F) {
/* 79 */         this.vX = 0.0F;
/*    */       }
/*    */     } 
/*    */     
/* 83 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 85 */     this.color.a = this.duration / this.effectDuration;
/* 86 */     if (this.duration < 0.0F) {
/* 87 */       this.isDone = true;
/*    */     }
/*    */     
/* 90 */     this.color.a /= 2.0F;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb, float x2, float y2) {
/* 94 */     if (this.img != null) {
/* 95 */       sb.setColor(this.color);
/* 96 */       sb.draw((TextureRegion)this.img, this.x + x2, this.y + y2, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\GameDeckGlowEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */