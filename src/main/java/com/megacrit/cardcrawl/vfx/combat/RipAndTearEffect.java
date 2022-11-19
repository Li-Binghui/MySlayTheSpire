/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class RipAndTearEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public RipAndTearEffect(float x, float y, Color color1, Color color2) {
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/* 18 */     this.color = color1;
/* 19 */     this.color2 = color2;
/* 20 */     this.startingDuration = 0.1F;
/* 21 */     this.duration = this.startingDuration;
/*    */   }
/*    */   private Color color2; private static boolean flipped = false;
/*    */   
/*    */   public void update() {
/* 26 */     if (MathUtils.randomBoolean()) {
/* 27 */       CardCrawlGame.sound.playA("ATTACK_DAGGER_5", MathUtils.random(0.0F, -0.3F));
/*    */     } else {
/* 29 */       CardCrawlGame.sound.playA("ATTACK_DAGGER_6", MathUtils.random(0.0F, -0.3F));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 34 */     if (!flipped) {
/* 35 */       float baseAngle = 135.0F;
/* 36 */       AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x - 45.0F, this.y + 45.0F, -150.0F, -150.0F, baseAngle + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 42 */             MathUtils.random(-10.0F, 10.0F), this.color, this.color2));
/*    */ 
/*    */       
/* 45 */       AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x, this.y, -150.0F, -150.0F, baseAngle + 
/* 46 */             MathUtils.random(-10.0F, 10.0F), this.color, this.color2));
/* 47 */       AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x + 45.0F, this.y - 45.0F, -150.0F, -150.0F, baseAngle + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 53 */             MathUtils.random(-10.0F, 10.0F), this.color, this.color2));
/*    */     }
/*    */     else {
/*    */       
/* 57 */       float baseAngle = -135.0F;
/* 58 */       AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x - 45.0F, this.y - 45.0F, 150.0F, -150.0F, baseAngle + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 64 */             MathUtils.random(-10.0F, 10.0F), this.color, this.color2));
/*    */ 
/*    */       
/* 67 */       AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x, this.y, 150.0F, -150.0F, baseAngle + 
/* 68 */             MathUtils.random(-10.0F, 10.0F), this.color, this.color2));
/* 69 */       AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x + 40.0F, this.y + 40.0F, 150.0F, -150.0F, baseAngle + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 75 */             MathUtils.random(-10.0F, 10.0F), this.color, this.color2));
/*    */     } 
/*    */ 
/*    */     
/* 79 */     flipped = !flipped;
/*    */     
/* 81 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\RipAndTearEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */