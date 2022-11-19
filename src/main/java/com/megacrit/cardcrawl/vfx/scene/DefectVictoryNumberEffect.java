/*    */ package com.megacrit.cardcrawl.vfx.scene;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class DefectVictoryNumberEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/* 16 */   private String num = ""; private float y; private float incrementTimer;
/*    */   private boolean dontIncrement = false;
/*    */   
/*    */   public DefectVictoryNumberEffect() {
/* 20 */     this.renderBehind = true;
/* 21 */     this.x = MathUtils.random(0.0F, 1870.0F) * Settings.xScale;
/* 22 */     this.y = MathUtils.random(50.0F, 990.0F) * Settings.yScale;
/* 23 */     this.duration = MathUtils.random(2.0F, 4.0F);
/* 24 */     this.color = new Color(MathUtils.random(0.5F, 1.0F), MathUtils.random(0.5F, 1.0F), MathUtils.random(0.5F, 1.0F), 0.0F);
/* 25 */     this.scale = MathUtils.random(0.7F, 1.3F);
/* 26 */     this.incrementTimer = MathUtils.random(0.02F, 0.1F);
/*    */     
/* 28 */     switch (MathUtils.random(100)) {
/*    */       case 0:
/* 30 */         this.num = "H3110";
/* 31 */         this.dontIncrement = true;
/*    */         break;
/*    */       case 1:
/* 34 */         this.num = "D00T D00T";
/* 35 */         this.dontIncrement = true;
/*    */         break;
/*    */       case 2:
/* 38 */         this.num = "<ERR0R>";
/* 39 */         this.dontIncrement = true;
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 49 */     if (!this.dontIncrement) {
/* 50 */       this.incrementTimer -= Gdx.graphics.getDeltaTime();
/* 51 */       if (this.incrementTimer < 0.0F) {
/* 52 */         if (MathUtils.randomBoolean()) {
/* 53 */           this.num += "0";
/*    */         } else {
/* 55 */           this.num += "1";
/*    */         } 
/* 57 */         this.incrementTimer = MathUtils.random(0.1F, 0.4F);
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 62 */     if (this.duration < 0.0F) {
/* 63 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 67 */     if (this.duration < 1.0F) {
/* 68 */       this.color.a = Interpolation.bounceOut.apply(0.0F, 0.5F, this.duration);
/*    */     } else {
/* 70 */       this.color.a = MathHelper.slowColorLerpSnap(this.color.a, 0.5F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 77 */     sb.setBlendFunction(770, 1);
/* 78 */     FontHelper.energyNumFontBlue.getData().setScale(this.scale);
/* 79 */     FontHelper.renderFont(sb, FontHelper.energyNumFontBlue, this.num, this.x, this.y, this.color);
/* 80 */     FontHelper.energyNumFontBlue.getData().setScale(1.0F);
/* 81 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\DefectVictoryNumberEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */