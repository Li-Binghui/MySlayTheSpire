/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class ShockWaveEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private ShockWaveType type;
/*    */   private Color color;
/*    */   
/*    */   public ShockWaveEffect(float x, float y, Color color, ShockWaveType type) {
/* 19 */     this.x = x;
/* 20 */     this.y = y;
/* 21 */     this.type = type;
/* 22 */     this.color = color;
/*    */   }
/*    */   public void update() {
/*    */     int i;
/* 26 */     float speed = MathUtils.random(1000.0F, 1200.0F) * Settings.scale;
/* 27 */     switch (this.type) {
/*    */       case ADDITIVE:
/* 29 */         for (i = 0; i < 40; i++) {
/* 30 */           AbstractDungeon.effectsQueue.add(new BlurWaveAdditiveEffect(this.x, this.y, this.color.cpy(), speed));
/*    */         }
/*    */         break;
/*    */       case NORMAL:
/* 34 */         for (i = 0; i < 40; i++) {
/* 35 */           AbstractDungeon.effectsQueue.add(new BlurWaveNormalEffect(this.x, this.y, this.color.cpy(), speed));
/*    */         }
/*    */         break;
/*    */       case CHAOTIC:
/* 39 */         CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
/* 40 */         for (i = 0; i < 40; i++) {
/* 41 */           AbstractDungeon.effectsQueue.add(new BlurWaveChaoticEffect(this.x, this.y, this.color.cpy(), speed));
/*    */         }
/*    */         break;
/*    */     } 
/* 45 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public enum ShockWaveType {
/* 49 */     ADDITIVE, NORMAL, CHAOTIC;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ShockWaveEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */