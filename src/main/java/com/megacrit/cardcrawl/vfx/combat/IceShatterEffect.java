/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class IceShatterEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public IceShatterEffect(float x, float y) {
/* 19 */     if (MathUtils.randomBoolean()) {
/* 20 */       this.img = ImageMaster.FROST_ACTIVATE_VFX_1;
/*    */     } else {
/* 22 */       this.img = ImageMaster.FROST_ACTIVATE_VFX_2;
/*    */     } 
/*    */     
/* 25 */     this.x = x;
/* 26 */     this.y = y;
/* 27 */     this.vX = MathUtils.random(-300.0F, 300.0F) * Settings.scale;
/* 28 */     this.vY = MathUtils.random(-900.0F, 200.0F) * Settings.scale;
/* 29 */     this.duration = 0.5F;
/* 30 */     this.scale = MathUtils.random(0.75F, 1.25F) * Settings.scale;
/* 31 */     this.color = new Color(0.5F, 0.8F, 1.0F, MathUtils.random(0.9F, 1.0F));
/*    */     
/* 33 */     Vector2 derp = new Vector2(this.vX, this.vY);
/* 34 */     this.rotation = derp.angle() - 45.0F;
/*    */   }
/*    */   private float vX; private float vY; private Texture img;
/*    */   public void update() {
/* 38 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 39 */     this.y -= this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 41 */     this.rotation += Gdx.graphics.getDeltaTime() * this.vX;
/* 42 */     this.vY += 2000.0F * Settings.scale * Gdx.graphics.getDeltaTime();
/*    */     
/* 44 */     this.color.a = this.duration * 2.0F;
/* 45 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 46 */     if (this.duration < 0.0F) {
/* 47 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 52 */     sb.setBlendFunction(770, 1);
/* 53 */     sb.setColor(this.color);
/* 54 */     sb.draw(this.img, this.x, this.y, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/* 55 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\IceShatterEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */