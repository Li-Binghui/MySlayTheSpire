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
/*    */ public class BlockedNumberEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 2.3F;
/* 14 */   private static final float GRAVITY_Y = 75.0F * Settings.scale; private float x; private float y;
/*    */   private String msg;
/* 16 */   private float scale = 1.0F;
/* 17 */   private float swayTimer = 0.0F;
/*    */   
/*    */   public BlockedNumberEffect(float x, float y, String msg) {
/* 20 */     this.duration = 2.3F;
/* 21 */     this.startingDuration = 2.3F;
/* 22 */     this.x = x;
/* 23 */     this.y = y;
/* 24 */     this.msg = msg;
/* 25 */     this.color = new Color(0.4F, 0.8F, 0.9F, 1.0F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 29 */     this.swayTimer -= Gdx.graphics.getDeltaTime() * 4.0F;
/* 30 */     this.x += MathUtils.cos(this.swayTimer) * 2.0F;
/* 31 */     this.y += GRAVITY_Y * Gdx.graphics.getDeltaTime();
/*    */     
/* 33 */     super.update();
/* 34 */     this.scale = Settings.scale * this.duration / 2.3F * 3.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 39 */     if (this.scale <= 0.0F) {
/* 40 */       this.scale = 0.01F;
/*    */     }
/* 42 */     FontHelper.damageNumberFont.getData().setScale(this.scale);
/* 43 */     FontHelper.renderFontCentered(sb, FontHelper.damageNumberFont, this.msg, this.x, this.y, this.color);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\BlockedNumberEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */