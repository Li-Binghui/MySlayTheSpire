/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class HealNumberEffect extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 1.2F;
/*    */   private float x;
/* 14 */   private static final float OFFSET_Y = 150.0F * Settings.scale; private float y; private float vX; private float vY;
/* 15 */   private static final float GRAVITY_Y = -2000.0F * Settings.scale;
/*    */   private int number;
/* 17 */   private float scale = 1.0F;
/*    */   
/*    */   public HealNumberEffect(float x, float y, int number) {
/* 20 */     this.duration = 1.2F;
/* 21 */     this.startingDuration = 1.2F;
/* 22 */     this.x = x;
/* 23 */     this.y = y + OFFSET_Y;
/* 24 */     this.vX = MathUtils.random(100.0F * Settings.scale, 150.0F * Settings.scale);
/* 25 */     if (MathUtils.randomBoolean()) {
/* 26 */       this.vX = -this.vX;
/*    */     }
/* 28 */     this.vY = MathUtils.random(400.0F * Settings.scale, 500.0F * Settings.scale);
/*    */     
/* 30 */     this.number = number;
/* 31 */     this.color = Color.CHARTREUSE.cpy();
/*    */   }
/*    */   
/*    */   public void update() {
/* 35 */     this.x += Gdx.graphics.getDeltaTime() * this.vX;
/* 36 */     this.y += Gdx.graphics.getDeltaTime() * this.vY;
/* 37 */     this.vY += Gdx.graphics.getDeltaTime() * GRAVITY_Y;
/*    */     
/* 39 */     super.update();
/* 40 */     this.scale = Settings.scale * this.duration / 1.2F * 3.0F;
/* 41 */     if (this.scale <= 0.0F) {
/* 42 */       this.scale = 0.01F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 48 */     FontHelper.damageNumberFont.getData().setScale(this.scale);
/* 49 */     FontHelper.renderFontCentered(sb, FontHelper.damageNumberFont, Integer.toString(this.number), this.x, this.y, this.color);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\HealNumberEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */