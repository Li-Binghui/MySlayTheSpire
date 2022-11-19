/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class ViolentAttackEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 17 */   private int count = 5;
/*    */   
/*    */   public ViolentAttackEffect(float x, float y, Color setColor) {
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/* 22 */     this.duration = 0.0F;
/* 23 */     this.color = setColor;
/* 24 */     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 30 */     if (this.duration < 0.0F) {
/* 31 */       CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(0.2F, 0.5F));
/* 32 */       AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x + 
/*    */             
/* 34 */             MathUtils.random(-100.0F, 100.0F) * Settings.scale, this.y + 
/* 35 */             MathUtils.random(-100.0F, 100.0F) * Settings.scale, 0.0F, 0.0F, 
/*    */ 
/*    */             
/* 38 */             MathUtils.random(360.0F), 
/* 39 */             MathUtils.random(2.5F, 4.0F), this.color, this.color));
/*    */ 
/*    */       
/* 42 */       if (MathUtils.randomBoolean()) {
/* 43 */         AbstractDungeon.effectsQueue.add(new FlashAtkImgEffect(this.x + 
/*    */               
/* 45 */               MathUtils.random(-150.0F, 150.0F) * Settings.scale, this.y + 
/* 46 */               MathUtils.random(-150.0F, 150.0F) * Settings.scale, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */       } else {
/*    */         
/* 49 */         AbstractDungeon.effectsQueue.add(new FlashAtkImgEffect(this.x + 
/*    */               
/* 51 */               MathUtils.random(-150.0F, 150.0F) * Settings.scale, this.y + 
/* 52 */               MathUtils.random(-150.0F, 150.0F) * Settings.scale, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */       } 
/*    */       
/* 55 */       this.duration = MathUtils.random(0.05F, 0.1F);
/* 56 */       this.count--;
/*    */     } 
/*    */     
/* 59 */     if (this.count == 0)
/* 60 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ViolentAttackEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */