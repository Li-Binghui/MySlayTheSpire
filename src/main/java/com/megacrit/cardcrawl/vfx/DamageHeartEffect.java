/*     */ package com.megacrit.cardcrawl.vfx;
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.combat.StrikeEffect;
/*     */ 
/*     */ public class DamageHeartEffect extends AbstractGameEffect {
/*  17 */   private static int blockSound = 0;
/*     */   
/*     */   public TextureAtlas.AtlasRegion img;
/*     */   private float x;
/*     */   private float y;
/*     */   private float sY;
/*  23 */   private float delayTimer = 2.0F; private float tY; private static final float DURATION = 0.6F; private AbstractGameAction.AttackEffect effect; private boolean triggered = false;
/*     */   private int damage;
/*     */   
/*     */   public DamageHeartEffect(float delay, float x, float y, AbstractGameAction.AttackEffect effect, int damage) {
/*  27 */     this.duration = 0.6F;
/*  28 */     this.delayTimer = delay;
/*  29 */     this.startingDuration = 0.6F;
/*  30 */     this.effect = effect;
/*  31 */     this.img = loadImage();
/*  32 */     this.color = Color.WHITE.cpy();
/*  33 */     this.scale = Settings.scale;
/*  34 */     this.damage = damage;
/*     */     
/*  36 */     this.x = x - this.img.packedWidth / 2.0F + MathUtils.random(-150.0F, 150.0F) * Settings.scale;
/*  37 */     y -= this.img.packedHeight / 2.0F + MathUtils.random(-150.0F, 150.0F) * Settings.scale;
/*     */     
/*  39 */     switch (effect) {
/*     */       case SHIELD:
/*  41 */         this.y = y + 80.0F * Settings.scale;
/*  42 */         this.sY = this.y;
/*  43 */         this.tY = y;
/*     */         return;
/*     */     } 
/*  46 */     this.y = y;
/*  47 */     this.sY = y;
/*  48 */     this.tY = y;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private TextureAtlas.AtlasRegion loadImage() {
/*  54 */     switch (this.effect) {
/*     */       case SLASH_DIAGONAL:
/*  56 */         return ImageMaster.ATK_SLASH_D;
/*     */       case SLASH_HEAVY:
/*  58 */         return ImageMaster.ATK_SLASH_HEAVY;
/*     */       case SLASH_HORIZONTAL:
/*  60 */         return ImageMaster.ATK_SLASH_H;
/*     */       case SLASH_VERTICAL:
/*  62 */         return ImageMaster.ATK_SLASH_V;
/*     */       case BLUNT_LIGHT:
/*  64 */         return ImageMaster.ATK_BLUNT_LIGHT;
/*     */       case BLUNT_HEAVY:
/*  66 */         this.rotation = MathUtils.random(360.0F);
/*  67 */         return ImageMaster.ATK_BLUNT_HEAVY;
/*     */       case FIRE:
/*  69 */         return ImageMaster.ATK_FIRE;
/*     */       case POISON:
/*  71 */         return ImageMaster.ATK_POISON;
/*     */       case SHIELD:
/*  73 */         return ImageMaster.ATK_SHIELD;
/*     */       case NONE:
/*  75 */         return null;
/*     */     } 
/*  77 */     return ImageMaster.ATK_SLASH_D;
/*     */   }
/*     */ 
/*     */   
/*     */   private void playSound(AbstractGameAction.AttackEffect effect) {
/*  82 */     switch (effect) {
/*     */       case SLASH_HEAVY:
/*  84 */         CardCrawlGame.sound.play("ATTACK_HEAVY");
/*     */       
/*     */       case BLUNT_LIGHT:
/*  87 */         CardCrawlGame.sound.play("BLUNT_FAST");
/*     */       
/*     */       case BLUNT_HEAVY:
/*  90 */         CardCrawlGame.sound.play("BLUNT_HEAVY");
/*     */       
/*     */       case FIRE:
/*  93 */         CardCrawlGame.sound.play("ATTACK_FIRE");
/*     */       
/*     */       case POISON:
/*  96 */         CardCrawlGame.sound.play("ATTACK_POISON");
/*     */       
/*     */       case SHIELD:
/*  99 */         playBlockSound();
/*     */       
/*     */       case NONE:
/*     */         return;
/*     */     } 
/* 104 */     CardCrawlGame.sound.play("ATTACK_FAST");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void playBlockSound() {
/* 113 */     if (blockSound == 0) {
/* 114 */       CardCrawlGame.sound.play("BLOCK_GAIN_1");
/* 115 */     } else if (blockSound == 1) {
/* 116 */       CardCrawlGame.sound.play("BLOCK_GAIN_2");
/*     */     } else {
/* 118 */       CardCrawlGame.sound.play("BLOCK_GAIN_3");
/*     */     } 
/*     */     
/* 121 */     blockSound++;
/* 122 */     if (blockSound > 2) {
/* 123 */       blockSound = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public void update() {
/* 128 */     if (this.delayTimer > 0.0F) {
/* 129 */       this.delayTimer -= Gdx.graphics.getDeltaTime();
/* 130 */       if (this.delayTimer < 0.0F) {
/* 131 */         playSound(this.effect);
/* 132 */         AbstractDungeon.effectsQueue.add(new StrikeEffect(null, this.x + this.img.packedWidth / 2.0F, this.y + this.img.packedHeight / 2.0F, this.damage));
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 138 */     switch (this.effect) {
/*     */       case SHIELD:
/* 140 */         this.duration -= Gdx.graphics.getDeltaTime();
/* 141 */         if (this.duration < 0.0F) {
/* 142 */           this.isDone = true;
/* 143 */           this.color.a = 0.0F;
/* 144 */         } else if (this.duration < 0.2F) {
/* 145 */           this.color.a = this.duration * 5.0F;
/*     */         } else {
/* 147 */           this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.duration * 0.75F / 0.6F);
/*     */         } 
/*     */         
/* 150 */         this.y = Interpolation.exp10In.apply(this.tY, this.sY, this.duration / 0.6F);
/*     */         
/* 152 */         if (this.duration < 0.4F && !this.triggered) {
/* 153 */           this.triggered = true;
/*     */         }
/*     */         return;
/*     */     } 
/* 157 */     super.update();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 164 */     if (this.delayTimer < 0.0F) {
/* 165 */       sb.setColor(this.color);
/* 166 */       sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\DamageHeartEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */