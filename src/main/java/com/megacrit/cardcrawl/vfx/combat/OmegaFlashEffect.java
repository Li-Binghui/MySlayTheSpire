/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class OmegaFlashEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public OmegaFlashEffect(float x, float y) {
/* 18 */     this.img = AbstractPower.atlas.findRegion("128/omega");
/* 19 */     this.x = x - this.img.packedWidth / 2.0F;
/* 20 */     this.y = y - this.img.packedHeight / 2.0F;
/* 21 */     this.startingDuration = 0.5F;
/* 22 */     this.duration = this.startingDuration;
/* 23 */     this.color = Color.WHITE.cpy();
/*    */   }
/*    */   private TextureAtlas.AtlasRegion img; private boolean playedSound = false;
/*    */   
/*    */   public void update() {
/* 28 */     if (!this.playedSound) {
/* 29 */       CardCrawlGame.sound.playA("BLOCK_ATTACK", -0.5F);
/* 30 */       this.playedSound = true;
/*    */     } 
/*    */     
/* 33 */     super.update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 38 */     sb.setBlendFunction(770, 1);
/* 39 */     sb.setColor(this.color);
/* 40 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 48 */         MathUtils.random(2.9F, 3.1F), this.scale * 
/* 49 */         MathUtils.random(2.9F, 3.1F), this.rotation);
/*    */     
/* 51 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 59 */         MathUtils.random(2.9F, 3.1F), this.scale * 
/* 60 */         MathUtils.random(2.9F, 3.1F), this.rotation);
/*    */     
/* 62 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\OmegaFlashEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */