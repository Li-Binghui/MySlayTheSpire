/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class FallingIceEffect extends AbstractGameEffect {
/*    */   private float waitTimer;
/*    */   private float x;
/*    */   private float y;
/* 19 */   private int frostCount = 0; private float vX; private float vY; private float floorY; private Texture img;
/*    */   
/*    */   public FallingIceEffect(int frostCount, boolean flipped) {
/* 22 */     this.frostCount = frostCount;
/*    */     
/* 24 */     switch (MathUtils.random(2)) {
/*    */       case 0:
/* 26 */         this.img = ImageMaster.FROST_ORB_RIGHT;
/*    */         break;
/*    */       case 1:
/* 29 */         this.img = ImageMaster.FROST_ORB_LEFT;
/*    */         break;
/*    */       default:
/* 32 */         this.img = ImageMaster.FROST_ORB_MIDDLE;
/*    */         break;
/*    */     } 
/*    */     
/* 36 */     this.waitTimer = MathUtils.random(0.0F, 0.5F);
/* 37 */     if (flipped) {
/* 38 */       this.x = MathUtils.random(420.0F, 1420.0F) * Settings.scale - 48.0F;
/* 39 */       this.vX = MathUtils.random(-600.0F, -900.0F);
/* 40 */       this.vX += frostCount * 5.0F;
/*    */     } else {
/* 42 */       this.x = MathUtils.random(500.0F, 1500.0F) * Settings.scale - 48.0F;
/* 43 */       this.vX = MathUtils.random(600.0F, 900.0F);
/* 44 */       this.vX -= frostCount * 5.0F;
/*    */     } 
/*    */     
/* 47 */     this.y = Settings.HEIGHT + MathUtils.random(100.0F, 300.0F) - 48.0F;
/* 48 */     this.vY = MathUtils.random(2500.0F, 4000.0F);
/* 49 */     this.vY -= frostCount * 10.0F;
/* 50 */     this.vY *= Settings.scale;
/* 51 */     this.vX *= Settings.scale;
/* 52 */     this.duration = 2.0F;
/* 53 */     this.scale = MathUtils.random(1.0F, 1.5F);
/* 54 */     this.scale += frostCount * 0.04F;
/* 55 */     this.vX *= this.scale;
/* 56 */     this.scale *= Settings.scale;
/* 57 */     this.color = new Color(0.9F, 0.9F, 1.0F, MathUtils.random(0.9F, 1.0F));
/* 58 */     Vector2 derp = new Vector2(this.vX, this.vY);
/* 59 */     if (flipped) {
/* 60 */       this.rotation = derp.angle() + 225.0F - frostCount / 3.0F;
/*    */     } else {
/* 62 */       this.rotation = derp.angle() - 45.0F + frostCount / 3.0F;
/*    */     } 
/* 64 */     this.renderBehind = MathUtils.randomBoolean();
/* 65 */     this.floorY = AbstractDungeon.floorY + MathUtils.random(-200.0F, 50.0F) * Settings.scale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 69 */     this.waitTimer -= Gdx.graphics.getDeltaTime();
/* 70 */     if (this.waitTimer > 0.0F) {
/*    */       return;
/*    */     }
/*    */     
/* 74 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 75 */     this.y -= this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 77 */     if (this.y < this.floorY) {
/* 78 */       float pitch = 0.8F;
/* 79 */       pitch -= this.frostCount * 0.025F;
/* 80 */       pitch += MathUtils.random(-0.2F, 0.2F);
/* 81 */       CardCrawlGame.sound.playA("ORB_FROST_EVOKE", pitch);
/*    */       
/* 83 */       for (int i = 0; i < 4; i++) {
/* 84 */         AbstractDungeon.effectsQueue.add(new IceShatterEffect(this.x, this.y));
/*    */       }
/* 86 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 91 */     if (this.waitTimer < 0.0F) {
/* 92 */       sb.setBlendFunction(770, 1);
/* 93 */       sb.setColor(this.color);
/* 94 */       sb.draw(this.img, this.x, this.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.rotation, 0, 0, 96, 96, false, false);
/* 95 */       sb.setBlendFunction(770, 771);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FallingIceEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */