/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class GoldenSlashEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   
/*    */   public GoldenSlashEffect(float x, float y, boolean isVertical) {
/* 15 */     this.x = x;
/* 16 */     this.y = y;
/* 17 */     this.startingDuration = 0.1F;
/* 18 */     this.duration = this.startingDuration;
/* 19 */     this.isVertical = isVertical;
/*    */   }
/*    */   private float y; private boolean isVertical;
/*    */   
/*    */   public void update() {
/* 24 */     CardCrawlGame.sound.playA("ATTACK_IRON_2", -0.4F);
/* 25 */     CardCrawlGame.sound.playA("ATTACK_HEAVY", -0.4F);
/*    */     
/* 27 */     if (this.isVertical) {
/* 28 */       AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x, this.y - 30.0F * Settings.scale, 0.0F, -500.0F, 180.0F, 5.0F, Color.GOLD, Color.GOLD));
/*    */     } else {
/*    */       
/* 31 */       AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x, this.y - 30.0F * Settings.scale, -500.0F, -500.0F, 135.0F, 4.0F, Color.GOLD, Color.GOLD));
/*    */     } 
/*    */ 
/*    */     
/* 35 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\GoldenSlashEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */