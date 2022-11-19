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
/*    */ public class WrathStanceChangeParticle extends AbstractGameEffect {
/* 16 */   private TextureAtlas.AtlasRegion img = ImageMaster.STRIKE_LINE;
/*    */   private float x;
/*    */   
/*    */   public WrathStanceChangeParticle(float playerX) {
/* 20 */     this.startingDuration = 1.0F;
/* 21 */     this.duration = this.startingDuration;
/* 22 */     this.color = new Color(1.0F, MathUtils.random(0.1F, 0.3F), 0.1F, 0.0F);
/* 23 */     this.x = MathUtils.random(-30.0F, 30.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 24 */     this.y = Settings.HEIGHT / 2.0F + MathUtils.random(-150.0F, 150.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 25 */     this.scale = MathUtils.random(2.2F, 2.5F) * Settings.scale;
/* 26 */     this.delayTimer = MathUtils.random(0.5F);
/* 27 */     this.rotation = MathUtils.random(89.0F, 91.0F);
/* 28 */     this.renderBehind = MathUtils.randomBoolean(0.9F);
/*    */   }
/*    */   private float y; private float delayTimer;
/*    */   public void update() {
/* 32 */     if (this.delayTimer > 0.0F) {
/* 33 */       this.delayTimer -= Gdx.graphics.getDeltaTime();
/*    */       
/*    */       return;
/*    */     } 
/* 37 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 38 */     if (this.duration < 0.0F) {
/* 39 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 43 */     if (this.duration > this.startingDuration / 2.0F) {
/* 44 */       this.color.a = Interpolation.pow3In.apply(0.6F, 0.0F, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 49 */       this.color.a = Interpolation.fade.apply(0.0F, 0.6F, this.duration / this.startingDuration / 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 55 */     if (this.delayTimer > 0.0F) {
/*    */       return;
/*    */     }
/*    */     
/* 59 */     sb.setColor(this.color);
/* 60 */     sb.setBlendFunction(770, 1);
/* 61 */     sb.draw((TextureRegion)this.img, AbstractDungeon.player.hb.cX + this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 69 */         MathUtils.random(2.9F, 3.1F), this.scale * 
/* 70 */         MathUtils.random(0.95F, 1.05F), this.rotation);
/*    */     
/* 72 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\stance\WrathStanceChangeParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */