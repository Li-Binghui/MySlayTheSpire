/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ 
/*    */ public class TextAboveCreatureEffect extends AbstractGameEffect {
/*    */   private static final float TEXT_DURATION = 2.2F;
/* 13 */   private static final float STARTING_OFFSET_Y = 80.0F * Settings.scale;
/* 14 */   private static final float TARGET_OFFSET_Y = 120.0F * Settings.scale;
/*    */   
/*    */   private static final float LERP_RATE = 5.0F;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public TextAboveCreatureEffect(float x, float y, String msg, Color targetColor) {
/* 21 */     this.duration = 2.2F;
/* 22 */     this.startingDuration = 2.2F;
/* 23 */     this.msg = msg;
/* 24 */     this.x = x;
/* 25 */     this.y = y;
/* 26 */     this.targetColor = targetColor;
/* 27 */     this.color = Color.WHITE.cpy();
/* 28 */     this.offsetY = STARTING_OFFSET_Y;
/*    */   }
/*    */   private float offsetY; private String msg; private Color targetColor;
/*    */   
/*    */   public void update() {
/* 33 */     super.update();
/* 34 */     this.color.r = Interpolation.exp5In.apply(this.targetColor.r, 1.0F, this.duration / this.startingDuration);
/* 35 */     this.color.g = Interpolation.exp5In.apply(this.targetColor.g, 1.0F, this.duration / this.startingDuration);
/* 36 */     this.color.b = Interpolation.exp5In.apply(this.targetColor.b, 1.0F, this.duration / this.startingDuration);
/* 37 */     this.offsetY = MathUtils.lerp(this.offsetY, TARGET_OFFSET_Y, Gdx.graphics.getDeltaTime() * 5.0F);
/* 38 */     this.y += Gdx.graphics.getDeltaTime() * 12.0F * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 43 */     if (!this.isDone)
/* 44 */       FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.msg, this.x, this.y + this.offsetY, this.color, 1.2F); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\TextAboveCreatureEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */