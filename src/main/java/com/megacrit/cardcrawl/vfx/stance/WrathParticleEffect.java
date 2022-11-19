/*    */ package com.megacrit.cardcrawl.vfx.stance;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class WrathParticleEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public WrathParticleEffect() {
/* 20 */     this.img = ImageMaster.GLOW_SPARK;
/* 21 */     this.duration = MathUtils.random(1.3F, 1.8F);
/* 22 */     this.scale = MathUtils.random(0.6F, 1.0F) * Settings.scale;
/* 23 */     this.dur_div2 = this.duration / 2.0F;
/* 24 */     this.color = new Color(MathUtils.random(0.5F, 1.0F), 0.0F, MathUtils.random(0.0F, 0.2F), 0.0F);
/*    */     
/* 26 */     this.x = AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 2.0F - 30.0F * Settings.scale, AbstractDungeon.player.hb.width / 2.0F + 30.0F * Settings.scale);
/*    */ 
/*    */ 
/*    */     
/* 30 */     this.y = AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 2.0F - -10.0F * Settings.scale, AbstractDungeon.player.hb.height / 2.0F - 10.0F * Settings.scale);
/*    */ 
/*    */ 
/*    */     
/* 34 */     this.x -= this.img.packedWidth / 2.0F;
/* 35 */     this.y -= this.img.packedHeight / 2.0F;
/* 36 */     this.renderBehind = MathUtils.randomBoolean(0.2F + this.scale - 0.5F);
/* 37 */     this.rotation = MathUtils.random(-8.0F, 8.0F);
/*    */   }
/*    */   private float vY; private float dur_div2; private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public void update() {
/* 42 */     if (this.duration > this.dur_div2) {
/* 43 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
/*    */     } else {
/* 45 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.dur_div2);
/*    */     } 
/* 47 */     this.vY += Gdx.graphics.getDeltaTime() * 40.0F * Settings.scale;
/* 48 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 49 */     if (this.duration < 0.0F) {
/* 50 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 56 */     sb.setColor(this.color);
/* 57 */     sb.setBlendFunction(770, 1);
/* 58 */     sb.draw((TextureRegion)this.img, this.x, this.y + this.vY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.8F, (0.1F + (this.dur_div2 * 2.0F - this.duration) * 2.0F * this.scale) * Settings.scale, this.rotation);
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
/* 69 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\stance\WrathParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */