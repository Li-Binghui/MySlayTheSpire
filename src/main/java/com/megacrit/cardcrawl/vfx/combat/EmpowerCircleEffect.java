/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class EmpowerCircleEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public EmpowerCircleEffect(float x, float y) {
/* 21 */     if (MathUtils.randomBoolean()) {
/* 22 */       this.img = ImageMaster.POWER_UP_1;
/*    */     } else {
/* 24 */       this.img = ImageMaster.POWER_UP_2;
/*    */     } 
/*    */     
/* 27 */     this.x = x - this.img.packedWidth / 2.0F;
/* 28 */     this.y = y - this.img.packedHeight / 2.0F;
/* 29 */     this.vX = MathUtils.random(-6000.0F * Settings.scale, 6000.0F * Settings.scale);
/* 30 */     this.vY = MathUtils.random(-6000.0F * Settings.scale, 6000.0F * Settings.scale);
/* 31 */     this.rotation = (new Vector2(this.vX, this.vY)).angle();
/*    */     
/* 33 */     if (MathUtils.randomBoolean()) {
/* 34 */       this.color = Settings.CREAM_COLOR.cpy();
/*    */     } else {
/* 36 */       this.color = Color.SLATE.cpy();
/*    */     } 
/*    */     
/* 39 */     this.renderBehind = true;
/*    */   }
/*    */   private float vX; private float vY; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 43 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 44 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 45 */     this.vX = MathHelper.fadeLerpSnap(this.vX, 0.0F);
/* 46 */     this.vY = MathHelper.fadeLerpSnap(this.vY, 0.0F);
/*    */     
/* 48 */     this.scale = Settings.scale * this.duration / this.startingDuration;
/* 49 */     super.update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 54 */     if (!this.isDone) {
/* 55 */       sb.setColor(this.color);
/* 56 */       sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 64 */           MathUtils.random(0.9F, 1.1F), this.scale * 
/* 65 */           MathUtils.random(0.9F, 1.1F), this.rotation);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\EmpowerCircleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */