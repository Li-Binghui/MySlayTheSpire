/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class EmptyStanceParticleEffect
/*    */   extends AbstractGameEffect {
/*    */   private float rotationSpeed;
/*    */   private Vector2 pos;
/*    */   
/*    */   public EmptyStanceParticleEffect(float x, float y) {
/* 21 */     if (img == null) {
/* 22 */       img = ImageMaster.STRIKE_BLUR;
/*    */     }
/*    */     
/* 25 */     this.startingDuration = 0.6F;
/* 26 */     this.duration = this.startingDuration;
/*    */     
/* 28 */     this.pos = new Vector2(x, y);
/*    */     
/* 30 */     this.rotationSpeed = MathUtils.random(120.0F, 150.0F);
/* 31 */     this.rotation = MathUtils.random(360.0F);
/* 32 */     this.scale = MathUtils.random(0.7F, 2.5F) * Settings.scale;
/*    */     
/* 34 */     this.color = new Color(MathUtils.random(0.2F, 0.4F), MathUtils.random(0.6F, 0.8F), 1.0F, 0.0F);
/* 35 */     this.renderBehind = MathUtils.randomBoolean(0.8F);
/*    */   }
/*    */   private Vector2 pos2; private Vector2 pos3; private static TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public void update() {
/* 40 */     this.pos2 = new Vector2(MathUtils.cosDeg(this.rotation), MathUtils.sinDeg(this.rotation));
/* 41 */     this.pos2.nor();
/* 42 */     this.pos2.x *= 10.0F;
/* 43 */     this.pos2.y *= 10.0F;
/* 44 */     this.pos3 = this.pos.sub(this.pos2);
/*    */     
/* 46 */     this.rotation += Gdx.graphics.getDeltaTime() * this.rotationSpeed;
/* 47 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 49 */     if (this.duration > this.startingDuration / 2.0F) {
/* 50 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.startingDuration / 2.0F) * 2.0F);
/*    */     } else {
/* 52 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 2.0F);
/*    */     } 
/*    */     
/* 55 */     if (this.duration < 0.0F) {
/* 56 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 62 */     if (this.pos3 != null) {
/* 63 */       sb.setBlendFunction(770, 1);
/* 64 */       sb.setColor(this.color);
/* 65 */       sb.draw((TextureRegion)img, this.pos3.x - img.packedWidth / 2.0F, this.pos3.y - img.packedHeight / 2.0F, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 73 */           MathUtils.random(-0.08F, 0.08F), this.scale + 
/* 74 */           MathUtils.random(-0.08F, 0.08F), this.rotation);
/*    */       
/* 76 */       sb.setBlendFunction(770, 771);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\EmptyStanceParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */