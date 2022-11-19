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

import java.util.ArrayList;
/*    */ 
/*    */ public class DamageImpactCurvyEffect
/*    */   extends AbstractGameEffect {
/* 18 */   private Vector2 pos = new Vector2(); private float speed;
/*    */   private float speedStart;
/*    */   private float speedTarget;
/* 21 */   private ArrayList<Vector2> positions = new ArrayList<>(); private float waveIntensity; private float waveSpeed; private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public DamageImpactCurvyEffect(float x, float y, Color color, boolean renderBehind) {
/* 24 */     this.img = ImageMaster.STRIKE_LINE_2;
/* 25 */     this.duration = MathUtils.random(0.8F, 1.1F);
/* 26 */     this.startingDuration = this.duration;
/* 27 */     this.pos.x = x - this.img.packedWidth / 2.0F;
/* 28 */     this.pos.y = y - this.img.packedHeight / 2.0F;
/* 29 */     this.speed = MathUtils.random(400.0F, 900.0F) * Settings.scale;
/* 30 */     this.speedStart = this.speed;
/* 31 */     this.speedTarget = MathUtils.random(200.0F, 300.0F) * Settings.scale;
/* 32 */     this.color = color;
/* 33 */     this.renderBehind = renderBehind;
/* 34 */     this.rotation = MathUtils.random(360.0F);
/* 35 */     this.waveIntensity = MathUtils.random(5.0F, 30.0F);
/* 36 */     this.waveSpeed = MathUtils.random(-20.0F, 20.0F);
/* 37 */     this.speedTarget = MathUtils.random(0.1F, 0.5F);
/*    */   }
/*    */   
/*    */   public DamageImpactCurvyEffect(float x, float y) {
/* 41 */     this(x, y, Color.GOLDENROD, true);
/*    */   }
/*    */   
/*    */   public void update() {
/* 45 */     this.positions.add(this.pos);
/* 46 */     Vector2 tmp = new Vector2(MathUtils.cosDeg(this.rotation), MathUtils.sinDeg(this.rotation));
/* 47 */     tmp.x *= this.speed * Gdx.graphics.getDeltaTime();
/* 48 */     tmp.y *= this.speed * Gdx.graphics.getDeltaTime();
/* 49 */     this.speed = Interpolation.pow2OutInverse.apply(this.speedStart, this.speedTarget, 1.0F - this.duration / this.startingDuration);
/* 50 */     this.pos.x += tmp.x;
/* 51 */     this.pos.y += tmp.y;
/* 52 */     this.rotation += MathUtils.cos(this.duration * this.waveSpeed) * this.waveIntensity * Gdx.graphics.getDeltaTime() * 60.0F;
/* 53 */     this.scale = Settings.scale * this.duration / this.startingDuration * 0.75F;
/* 54 */     super.update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 59 */     sb.setBlendFunction(770, 1);
/* 60 */     Color tmp = this.color.cpy();
/* 61 */     tmp.a = 0.25F;
/*    */     
/* 63 */     for (int i = this.positions.size() - 1; i > 0; i--) {
/* 64 */       sb.setColor(tmp);
/* 65 */       tmp.a *= 0.95F;
/* 66 */       if (tmp.a > 0.05F) {
/* 67 */         sb.draw((TextureRegion)this.img, ((Vector2)this.positions
/*    */             
/* 69 */             .get(i)).x, ((Vector2)this.positions
/* 70 */             .get(i)).y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 2.0F, this.scale * 2.0F, this.rotation);
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 81 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\DamageImpactCurvyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */