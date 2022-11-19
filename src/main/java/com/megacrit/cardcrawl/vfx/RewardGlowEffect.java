/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class RewardGlowEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final int W = 64;
/*    */   private static final float DURATION = 1.1F;
/*    */   private float scale;
/*    */   
/*    */   public RewardGlowEffect(float x, float y) {
/* 17 */     this.x = x;
/* 18 */     this.y = y;
/* 19 */     this.color = Color.WHITE.cpy();
/* 20 */     this.duration = 1.1F;
/* 21 */     this.scale = Settings.scale;
/*    */   }
/*    */   private float x; private float y; private float angle;
/*    */   
/*    */   public void update() {
/* 26 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 27 */     if (this.duration < 0.0F) {
/* 28 */       this.isDone = true;
/*    */     }
/*    */     
/* 31 */     this.scale += Settings.scale * Gdx.graphics.getDeltaTime() / 20.0F;
/* 32 */     this.color.a = Interpolation.fade.apply(this.duration / 1.1F) / 12.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 37 */     sb.setColor(this.color);
/* 38 */     sb.setBlendFunction(770, 1);
/* 39 */     sb.draw(ImageMaster.REWARD_SCREEN_ITEM, this.x - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.xScale, this.scale + Settings.scale * 0.05F, 0.0F, 0, 0, 464, 98, false, false);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 56 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */ 
/*    */   
/*    */   public void dispose() {}
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb, Color tint) {
/* 64 */     sb.setColor(this.color);
/* 65 */     sb.setBlendFunction(770, 1);
/* 66 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale * Settings.scale / 2.0F, this.scale * Settings.scale / 2.0F, this.angle, 0, 0, 64, 64, false, false);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 83 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\RewardGlowEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */