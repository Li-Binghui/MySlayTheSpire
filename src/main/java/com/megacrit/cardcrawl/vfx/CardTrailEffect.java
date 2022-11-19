/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.utils.Pool;
/*    */ import com.megacrit.cardcrawl.cards.Soul;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class CardTrailEffect extends AbstractGameEffect implements Pool.Poolable {
/*    */   private static final float EFFECT_DUR = 0.5F;
/*    */   private static final float DUR_DIV_2 = 0.25F;
/* 17 */   private static TextureAtlas.AtlasRegion img = null;
/*    */   private static final int W = 12;
/* 19 */   private static final float SCALE_MULTI = Settings.scale * 22.0F; private static final int W_DIV_2 = 6;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public CardTrailEffect() {
/* 24 */     if (img == null) {
/* 25 */       img = ImageMaster.vfxAtlas.findRegion("combat/blurDot2");
/*    */     }
/* 27 */     this.renderBehind = false;
/*    */   }
/*    */   
/*    */   public void init(float x, float y) {
/* 31 */     this.duration = 0.5F;
/* 32 */     this.startingDuration = 0.5F;
/* 33 */     this.x = x - 6.0F;
/* 34 */     this.y = y - 6.0F;
/* 35 */     this.color = AbstractDungeon.player.getCardTrailColor();
/* 36 */     this.scale = 0.01F;
/* 37 */     this.isDone = false;
/*    */   }
/*    */   
/*    */   public void update() {
/* 41 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 43 */     if (this.duration < 0.25F) {
/* 44 */       this.scale = this.duration * SCALE_MULTI;
/*    */     } else {
/* 46 */       this.scale = (this.duration - 0.25F) * SCALE_MULTI;
/*    */     } 
/*    */     
/* 49 */     if (this.duration < 0.0F) {
/* 50 */       this.isDone = true;
/* 51 */       Soul.trailEffectPool.free(this);
/*    */     } else {
/* 53 */       this.color.a = Interpolation.fade.apply(0.0F, 0.18F, this.duration / 0.5F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 59 */     sb.setBlendFunction(770, 1);
/* 60 */     sb.setColor(this.color);
/* 61 */     sb.draw((TextureRegion)img, this.x, this.y, 6.0F, 6.0F, 12.0F, 12.0F, this.scale, this.scale, 0.0F);
/* 62 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */   
/*    */   public void reset() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\CardTrailEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */