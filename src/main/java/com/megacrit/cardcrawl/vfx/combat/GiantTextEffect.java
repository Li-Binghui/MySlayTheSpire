/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.purple.Judgement;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class GiantTextEffect
/*    */   extends AbstractGameEffect {
/* 15 */   private StringBuilder sBuilder = new StringBuilder("");
/* 16 */   private String targetString = Judgement.cardStrings.EXTENDED_DESCRIPTION[0];
/* 17 */   private int index = 0;
/*    */   private float x;
/*    */   
/*    */   public GiantTextEffect(float x, float y) {
/* 21 */     this.sBuilder.setLength(0);
/* 22 */     this.x = x;
/* 23 */     this.y = y + 100.0F * Settings.scale;
/* 24 */     this.color = Color.WHITE.cpy();
/* 25 */     this.duration = 1.0F;
/*    */   }
/*    */   private float y;
/*    */   
/*    */   public void update() {
/* 30 */     this.color.a = Interpolation.pow5Out.apply(0.0F, 0.8F, this.duration);
/* 31 */     this.color.a += MathUtils.random(-0.05F, 0.05F);
/* 32 */     this.color.a = MathUtils.clamp(this.color.a, 0.0F, 1.0F);
/*    */     
/* 34 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 35 */     if (this.index < this.targetString.length()) {
/* 36 */       this.sBuilder.append(this.targetString.charAt(this.index));
/* 37 */       this.index++;
/*    */     } 
/* 39 */     if (this.duration < 0.0F) {
/* 40 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 46 */     FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, Judgement.cardStrings.EXTENDED_DESCRIPTION[0], this.x, this.y, this.color, 2.5F - this.duration / 4.0F + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 53 */         MathUtils.random(0.05F));
/* 54 */     sb.setBlendFunction(770, 1);
/* 55 */     FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, Judgement.cardStrings.EXTENDED_DESCRIPTION[0], this.x, this.y, this.color, 0.05F + 2.5F - this.duration / 4.0F + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 62 */         MathUtils.random(0.05F));
/* 63 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\GiantTextEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */