/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class HeartBuffEffect extends AbstractGameEffect {
/*    */   float x;
/*    */   
/*    */   public HeartBuffEffect(float x, float y) {
/* 15 */     this.x = x;
/* 16 */     this.y = y;
/* 17 */     this.duration = 0.5F;
/* 18 */     this.scale = 0.0F;
/*    */   }
/*    */   float y;
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == 0.5F) {
/* 24 */       CardCrawlGame.sound.playA("BUFF_2", -0.6F);
/*    */     }
/* 26 */     this.scale -= Gdx.graphics.getDeltaTime();
/* 27 */     if (this.scale < 0.0F) {
/* 28 */       this.scale = 0.05F;
/* 29 */       AbstractDungeon.effectsQueue.add(new SwirlyBloodEffect(this.x + 
/*    */             
/* 31 */             MathUtils.random(-150.0F, 150.0F) * Settings.scale, this.y + 
/* 32 */             MathUtils.random(-150.0F, 150.0F) * Settings.scale));
/* 33 */       AbstractDungeon.effectsQueue.add(new SwirlyBloodEffect(this.x + 
/*    */             
/* 35 */             MathUtils.random(-150.0F, 150.0F) * Settings.scale, this.y + 
/* 36 */             MathUtils.random(-150.0F, 150.0F) * Settings.scale));
/*    */     } 
/*    */     
/* 39 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 40 */     if (this.duration < 0.0F)
/* 41 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\HeartBuffEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */