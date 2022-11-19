/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.DialogWord;

import java.util.ArrayList;
/*     */ 
/*     */ public class ThoughtBubble
/*     */   extends AbstractGameEffect {
/*     */   private static final float DEFAULT_DURATION = 2.0F;
/*  14 */   private static final float OFFSET_X = 190.0F * Settings.scale, OFFSET_Y = 124.0F * Settings.scale;
/*  15 */   private static final float CLOUD_W = 100.0F * Settings.scale;
/*  16 */   private static final float CLOUD_H = 50.0F * Settings.scale;
/*     */   private ArrayList<CloudBubble> bubbles;
/*     */   
/*     */   public ThoughtBubble(float x, float y, String msg, boolean isPlayer) {
/*  20 */     this(x, y, 2.0F, msg, isPlayer);
/*     */   } public ThoughtBubble(float x, float y, float duration, String msg, boolean isPlayer) {
/*     */     float off_x;
/*     */     this.bubbles = new ArrayList<>();
/*  24 */     if (msg == null) {
/*  25 */       this.isDone = true;
/*     */       
/*     */       return;
/*     */     } 
/*  29 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/*  30 */       if (e instanceof ThoughtBubble && !e.equals(this)) {
/*  31 */         ((ThoughtBubble)e).killClouds();
/*     */       }
/*     */     } 
/*     */     
/*  35 */     this.duration = duration;
/*     */     
/*  37 */     y += OFFSET_Y;
/*  38 */     if (isPlayer) {
/*  39 */       x += OFFSET_X;
/*     */     } else {
/*  41 */       x -= OFFSET_X;
/*     */     } 
/*     */     
/*  44 */     AbstractDungeon.effectsQueue.add(new SpeechTextEffect(x, y, duration, msg, DialogWord.AppearEffect.BUMP_IN));
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.bubbles.add(new CloudBubble(x + CLOUD_W * 
/*     */           
/*  50 */           MathUtils.random(0.7F, 1.1F), y + CLOUD_H * 
/*  51 */           MathUtils.random(0.1F, 0.3F), 
/*  52 */           MathUtils.random(1.0F, 1.2F)));
/*  53 */     this.bubbles.add(new CloudBubble(x - CLOUD_W * 
/*     */           
/*  55 */           MathUtils.random(0.7F, 1.1F), y + CLOUD_H * 
/*  56 */           MathUtils.random(0.1F, 0.3F), 
/*  57 */           MathUtils.random(1.0F, 1.2F)));
/*  58 */     this.bubbles.add(new CloudBubble(x + CLOUD_W * 
/*     */           
/*  60 */           MathUtils.random(0.7F, 1.1F), y + CLOUD_H * 
/*  61 */           MathUtils.random(-0.1F, -0.3F), 
/*  62 */           MathUtils.random(0.9F, 1.1F)));
/*  63 */     this.bubbles.add(new CloudBubble(x - CLOUD_W * 
/*     */           
/*  65 */           MathUtils.random(0.7F, 1.1F), y + CLOUD_H * 
/*  66 */           MathUtils.random(-0.1F, -0.3F), 
/*  67 */           MathUtils.random(0.9F, 1.1F)));
/*  68 */     this.bubbles.add(new CloudBubble(x + CLOUD_W * 
/*     */           
/*  70 */           MathUtils.random(-0.2F, 0.2F), y + CLOUD_H * 
/*  71 */           MathUtils.random(0.65F, 0.72F), 
/*  72 */           MathUtils.random(0.9F, 1.1F)));
/*  73 */     this.bubbles.add(new CloudBubble(x + CLOUD_W * 
/*     */           
/*  75 */           MathUtils.random(-0.2F, 0.2F), y - CLOUD_H * 
/*  76 */           MathUtils.random(0.65F, 0.72F), 
/*  77 */           MathUtils.random(1.0F, 1.2F)));
/*     */ 
/*     */     
/*  80 */     this.bubbles.add(new CloudBubble(x + CLOUD_W * 
/*     */           
/*  82 */           MathUtils.random(0.3F, 0.8F), y + CLOUD_H * 
/*  83 */           MathUtils.random(0.3F, 0.7F), 
/*  84 */           MathUtils.random(0.9F, 1.1F)));
/*  85 */     this.bubbles.add(new CloudBubble(x - CLOUD_W * 
/*     */           
/*  87 */           MathUtils.random(0.3F, 0.8F), y + CLOUD_H * 
/*  88 */           MathUtils.random(0.3F, 0.7F), 
/*  89 */           MathUtils.random(0.9F, 1.1F)));
/*  90 */     this.bubbles.add(new CloudBubble(x + CLOUD_W * 
/*     */           
/*  92 */           MathUtils.random(0.3F, 0.8F), y - CLOUD_H * 
/*  93 */           MathUtils.random(0.3F, 0.7F), 
/*  94 */           MathUtils.random(0.9F, 1.1F)));
/*  95 */     this.bubbles.add(new CloudBubble(x - CLOUD_W * 
/*     */           
/*  97 */           MathUtils.random(0.3F, 0.8F), y - CLOUD_H * 
/*  98 */           MathUtils.random(0.3F, 0.7F), 
/*  99 */           MathUtils.random(0.9F, 1.1F)));
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (isPlayer) {
/* 104 */       off_x = OFFSET_X;
/*     */     } else {
/* 106 */       off_x = -OFFSET_X;
/*     */     } 
/* 108 */     this.bubbles.add(new CloudBubble(x - off_x * 
/*     */           
/* 110 */           MathUtils.random(-0.05F, -0.15F), y - OFFSET_Y * 
/* 111 */           MathUtils.random(0.67F, 0.72F), 
/* 112 */           MathUtils.random(0.4F, 0.45F)));
/* 113 */     this.bubbles.add(new CloudBubble(x - off_x * 
/*     */           
/* 115 */           MathUtils.random(0.07F, 0.15F), y - OFFSET_Y * 
/* 116 */           MathUtils.random(0.65F, 0.7F), 
/* 117 */           MathUtils.random(0.4F, 0.45F)));
/* 118 */     this.bubbles.add(new CloudBubble(x - off_x * 
/*     */           
/* 120 */           MathUtils.random(0.1F, 0.2F), y - OFFSET_Y * 
/* 121 */           MathUtils.random(0.9F, 1.02F), 
/* 122 */           MathUtils.random(0.35F, 0.4F)));
/* 123 */     this.bubbles.add(new CloudBubble(x - off_x * 
/*     */           
/* 125 */           MathUtils.random(0.3F, 0.35F), y - OFFSET_Y * 
/* 126 */           MathUtils.random(1.05F, 1.1F), 
/* 127 */           MathUtils.random(0.18F, 0.23F)));
/* 128 */     this.bubbles.add(new CloudBubble(x - off_x * 
/*     */           
/* 130 */           MathUtils.random(0.35F, 0.45F), y - OFFSET_Y * 
/* 131 */           MathUtils.random(1.1F, 1.2F), 
/* 132 */           MathUtils.random(0.1F, 0.13F)));
/* 133 */     this.bubbles.add(new CloudBubble(x - off_x * 
/*     */           
/* 135 */           MathUtils.random(0.45F, 0.5F), y - OFFSET_Y * 
/* 136 */           MathUtils.random(1.1F, 1.16F), 
/* 137 */           MathUtils.random(0.08F, 0.09F)));
/* 138 */     this.bubbles.add(new CloudBubble(x - off_x * 
/*     */           
/* 140 */           MathUtils.random(0.5F, 0.6F), y - OFFSET_Y * 
/* 141 */           MathUtils.random(1.1F, 1.16F), 
/* 142 */           MathUtils.random(0.08F, 0.09F)));
/* 143 */     this.bubbles.add(new CloudBubble(x - off_x * 
/*     */           
/* 145 */           MathUtils.random(0.6F, 0.65F), y - OFFSET_Y * 
/* 146 */           MathUtils.random(1.05F, 1.12F), 
/* 147 */           MathUtils.random(0.08F, 0.09F)));
/*     */   }
/*     */   
/*     */   public void update() {
/* 151 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 152 */     if (this.duration < 0.0F) {
/* 153 */       this.isDone = true;
/* 154 */     } else if (this.duration < 0.3F) {
/* 155 */       killClouds();
/*     */     } 
/*     */     
/* 158 */     for (CloudBubble b : this.bubbles) {
/* 159 */       b.update();
/*     */     }
/*     */   }
/*     */   
/*     */   private void killClouds() {
/* 164 */     for (CloudBubble b : this.bubbles) {
/* 165 */       b.kill();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 171 */     for (CloudBubble b : this.bubbles)
/* 172 */       b.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ThoughtBubble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */