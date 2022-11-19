/*    */ package com.megacrit.cardcrawl.vfx;
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
/*    */ 
/*    */ public class RoomShineEffect extends AbstractGameEffect {
/*    */   private float effectDuration;
/*    */   private float x;
/* 16 */   private TextureAtlas.AtlasRegion img = ImageMaster.ROOM_SHINE_1; private float y; private float vY; private float alpha;
/*    */   
/*    */   public RoomShineEffect() {
/* 19 */     this.effectDuration = MathUtils.random(2.0F, 3.0F);
/* 20 */     this.duration = this.effectDuration;
/* 21 */     this.startingDuration = this.effectDuration;
/* 22 */     this.x = MathUtils.random(50.0F, 1870.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 23 */     this.y = MathUtils.random(Settings.HEIGHT * 0.1F, Settings.HEIGHT * 0.85F) - this.img.packedHeight / 2.0F;
/* 24 */     this.vY = MathUtils.random(10.0F, 50.0F) * Settings.scale;
/* 25 */     this.alpha = MathUtils.random(0.5F, 1.0F);
/*    */     
/* 27 */     this.color = new Color(1.0F, 1.0F, MathUtils.random(0.6F, 0.9F), this.alpha);
/* 28 */     this.scale = MathUtils.random(0.5F, 1.5F) * Settings.scale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 32 */     if (this.vY != 0.0F) {
/* 33 */       this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 34 */       MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() * 10.0F);
/* 35 */       if (this.vY < 0.5F) {
/* 36 */         this.vY = 0.0F;
/*    */       }
/*    */     } 
/*    */     
/* 40 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 41 */     if (this.duration < 0.0F) {
/* 42 */       this.isDone = true;
/* 43 */     } else if (this.duration < this.effectDuration / 2.0F) {
/* 44 */       this.color.a = Interpolation.exp5In.apply(0.0F, this.alpha, this.duration / this.effectDuration / 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 50 */     sb.setColor(this.color);
/* 51 */     sb.setBlendFunction(770, 1);
/* 52 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 60 */         MathUtils.random(0.75F, 1.25F), this.scale * 
/* 61 */         MathUtils.random(0.75F, 1.25F), this.rotation);
/*    */     
/* 63 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\RoomShineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */