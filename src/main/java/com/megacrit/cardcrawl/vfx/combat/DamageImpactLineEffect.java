/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class DamageImpactLineEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 0.5F;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public DamageImpactLineEffect(float x, float y) {
/* 21 */     if (MathUtils.randomBoolean()) {
/* 22 */       this.img = ImageMaster.STRIKE_LINE;
/*    */     } else {
/* 24 */       this.img = ImageMaster.STRIKE_LINE_2;
/*    */     } 
/* 26 */     this.duration = 0.5F;
/* 27 */     this.startingDuration = 0.5F;
/* 28 */     this.x = x - this.img.packedWidth / 2.0F;
/* 29 */     this.y = y - this.img.packedHeight / 2.0F;
/* 30 */     this.speed = MathUtils.random(20.0F * Settings.scale, 40.0F * Settings.scale);
/*    */     
/* 32 */     this.speedVector = new Vector2(MathUtils.random(-1.0F, 1.0F), MathUtils.random(-1.0F, 1.0F));
/* 33 */     this.speedVector.nor();
/* 34 */     this.speedVector.angle();
/* 35 */     this.rotation = this.speedVector.angle();
/* 36 */     this.speedVector.x *= this.speed;
/* 37 */     this.speedVector.y *= this.speed;
/*    */     
/* 39 */     if (MathUtils.randomBoolean()) {
/* 40 */       this.color = Color.RED.cpy();
/*    */     } else {
/* 42 */       this.color = Color.ORANGE.cpy();
/*    */     } 
/*    */   }
/*    */   private Vector2 speedVector; private float speed; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 47 */     this.speed -= Gdx.graphics.getDeltaTime() * 60.0F;
/* 48 */     this.speedVector.nor();
/* 49 */     this.speedVector.x *= this.speed * Gdx.graphics.getDeltaTime() * 60.0F;
/* 50 */     this.speedVector.y *= this.speed * Gdx.graphics.getDeltaTime() * 60.0F;
/*    */ 
/*    */     
/* 53 */     this.x += this.speedVector.x;
/* 54 */     this.y += this.speedVector.y;
/*    */     
/* 56 */     this.scale = Settings.scale * this.duration / 0.5F;
/* 57 */     super.update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 62 */     if (!this.isDone) {
/* 63 */       sb.setColor(this.color);
/* 64 */       sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\DamageImpactLineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */