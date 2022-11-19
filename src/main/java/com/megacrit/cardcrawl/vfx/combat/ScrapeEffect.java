/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class ScrapeEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   
/*    */   public ScrapeEffect(float x, float y) {
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/* 18 */     this.color = Color.MAROON;
/* 19 */     this.color2 = Color.SCARLET;
/* 20 */     this.startingDuration = 0.1F;
/* 21 */     this.duration = this.startingDuration;
/*    */   }
/*    */   private float y; private Color color2;
/*    */   
/*    */   public void update() {
/* 26 */     if (MathUtils.randomBoolean()) {
/* 27 */       CardCrawlGame.sound.playA("ATTACK_DAGGER_5", MathUtils.random(0.0F, -0.3F));
/*    */     } else {
/* 29 */       CardCrawlGame.sound.playA("ATTACK_DAGGER_6", MathUtils.random(0.0F, -0.3F));
/*    */     } 
/*    */     
/* 32 */     float oX = -50.0F * Settings.scale;
/* 33 */     float oY = 20.0F * Settings.scale;
/* 34 */     float sX = -35.0F * Settings.scale;
/* 35 */     float sY = 20.0F * Settings.scale;
/* 36 */     float dX = -150.0F;
/* 37 */     float dY = -400.0F;
/* 38 */     float angle = 155.0F;
/*    */     
/* 40 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x + sX * 1.5F + oX, this.y + sY * 1.5F + oY, dX, dY, angle, this.color, this.color2));
/*    */     
/* 42 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x + sX * 0.5F + oX, this.y + sY * 0.5F + oY, dX, dY, angle, this.color, this.color2));
/*    */     
/* 44 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x - sX * 0.5F + oX, this.y - sY * 0.5F + oY, dX, dY, angle, this.color, this.color2));
/*    */     
/* 46 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x - sX * 1.5F + oX, this.y - sY * 1.5F + oY, dX, dY, angle, this.color, this.color2));
/*    */ 
/*    */     
/* 49 */     oX = 50.0F * Settings.scale;
/* 50 */     oY = 20.0F * Settings.scale;
/* 51 */     sX = 35.0F * Settings.scale;
/* 52 */     sY = 20.0F * Settings.scale;
/* 53 */     dX = 150.0F;
/* 54 */     dY = -400.0F;
/* 55 */     angle = -155.0F;
/*    */     
/* 57 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x + sX * 1.5F + oX, this.y + sY * 1.5F + oY, dX, dY, angle, this.color, this.color2));
/*    */     
/* 59 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x + sX * 0.5F + oX, this.y + sY * 0.5F + oY, dX, dY, angle, this.color, this.color2));
/*    */     
/* 61 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x - sX * 0.5F + oX, this.y - sY * 0.5F + oY, dX, dY, angle, this.color, this.color2));
/*    */     
/* 63 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x - sX * 1.5F + oX, this.y - sY * 1.5F + oY, dX, dY, angle, this.color, this.color2));
/*    */     
/* 65 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ScrapeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */