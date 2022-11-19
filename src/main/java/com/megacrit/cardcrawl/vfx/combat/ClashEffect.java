/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
/*    */ 
/*    */ public class ClashEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   
/*    */   public ClashEffect(float x, float y) {
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/* 18 */     this.startingDuration = 0.1F;
/* 19 */     this.duration = this.startingDuration;
/*    */   }
/*    */   private float y;
/*    */   
/*    */   public void update() {
/* 24 */     CardCrawlGame.sound.playA("ATTACK_WHIFF_1", 0.4F);
/* 25 */     CardCrawlGame.sound.playA("ATTACK_IRON_1", -0.1F);
/* 26 */     CardCrawlGame.sound.playA("ATTACK_IRON_3", -0.1F);
/* 27 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x, this.y - 30.0F * Settings.scale, -500.0F, -500.0F, 135.0F, 4.0F, Color.SCARLET, Color.GOLD));
/*    */     
/* 29 */     AbstractDungeon.effectsQueue.add(new AnimatedSlashEffect(this.x, this.y - 30.0F * Settings.scale, 500.0F, -500.0F, 225.0F, 4.0F, Color.SKY, Color.CYAN));
/*    */     
/* 31 */     for (int i = 0; i < 15; i++) {
/* 32 */       AbstractDungeon.effectsQueue.add(new UpgradeShineParticleEffect(this.x + 
/*    */             
/* 34 */             MathUtils.random(-40.0F, 40.0F) * Settings.scale, this.y + 
/* 35 */             MathUtils.random(-40.0F, 40.0F) * Settings.scale));
/*    */     }
/* 37 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ClashEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */