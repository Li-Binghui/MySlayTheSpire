/*     */ package com.megacrit.cardcrawl.vfx;
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ public class GainPennyEffect extends AbstractGameEffect {
/*  15 */   private static final float GRAVITY = 2000.0F * Settings.scale;
/*  16 */   private static final float START_VY = 800.0F * Settings.scale;
/*  17 */   private static final float START_VY_JITTER = 400.0F * Settings.scale;
/*  18 */   private static final float START_VX = 200.0F * Settings.scale;
/*  19 */   private static final float START_VX_JITTER = 300.0F * Settings.scale;
/*  20 */   private static final float TARGET_JITTER = 50.0F * Settings.scale; private float rotationSpeed;
/*     */   private float x;
/*     */   private float y;
/*     */   private float vX;
/*  24 */   private float alpha = 0.0F; private float vY; private float targetX; private float targetY; private TextureAtlas.AtlasRegion img;
/*  25 */   private float suctionTimer = 0.7F;
/*     */ 
/*     */   
/*     */   private float staggerTimer;
/*     */ 
/*     */   
/*     */   private boolean showGainEffect;
/*     */ 
/*     */   
/*     */   private AbstractCreature owner;
/*     */ 
/*     */   
/*     */   public GainPennyEffect(AbstractCreature owner, float x, float y, float targetX, float targetY, boolean showGainEffect) {
/*  38 */     if (MathUtils.randomBoolean()) {
/*  39 */       this.img = ImageMaster.COPPER_COIN_1;
/*     */     } else {
/*  41 */       this.img = ImageMaster.COPPER_COIN_2;
/*     */     } 
/*     */     
/*  44 */     this.x = x - this.img.packedWidth / 2.0F;
/*  45 */     this.y = y - this.img.packedHeight / 2.0F;
/*  46 */     this.targetX = targetX + MathUtils.random(-TARGET_JITTER, TARGET_JITTER);
/*  47 */     this.targetY = targetY + MathUtils.random(-TARGET_JITTER, TARGET_JITTER * 2.0F);
/*  48 */     this.showGainEffect = showGainEffect;
/*  49 */     this.owner = owner;
/*     */     
/*  51 */     this.staggerTimer = MathUtils.random(0.0F, 0.5F);
/*  52 */     this.vX = MathUtils.random(START_VX - 50.0F * Settings.scale, START_VX_JITTER);
/*  53 */     this.rotationSpeed = MathUtils.random(500.0F, 2000.0F);
/*  54 */     if (MathUtils.randomBoolean()) {
/*  55 */       this.vX = -this.vX;
/*  56 */       this.rotationSpeed = -this.rotationSpeed;
/*     */     } 
/*     */     
/*  59 */     this.vY = MathUtils.random(START_VY, START_VY_JITTER);
/*  60 */     this.scale = Settings.scale;
/*  61 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public GainPennyEffect(float x, float y) {
/*  65 */     this((AbstractCreature)AbstractDungeon.player, x, y, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  70 */     if (this.staggerTimer > 0.0F) {
/*  71 */       this.staggerTimer -= Gdx.graphics.getDeltaTime();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  76 */     if (this.alpha != 1.0F) {
/*  77 */       this.alpha += Gdx.graphics.getDeltaTime() * 2.0F;
/*  78 */       if (this.alpha > 1.0F) {
/*  79 */         this.alpha = 1.0F;
/*     */       }
/*  81 */       this.color.a = this.alpha;
/*     */     } 
/*     */     
/*  84 */     this.rotation += Gdx.graphics.getDeltaTime() * this.rotationSpeed;
/*  85 */     this.x += Gdx.graphics.getDeltaTime() * this.vX;
/*  86 */     this.y += Gdx.graphics.getDeltaTime() * this.vY;
/*  87 */     this.vY -= Gdx.graphics.getDeltaTime() * GRAVITY;
/*     */     
/*  89 */     if (this.suctionTimer > 0.0F) {
/*  90 */       this.suctionTimer -= Gdx.graphics.getDeltaTime();
/*     */     } else {
/*  92 */       this.vY = MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() * 5.0F);
/*  93 */       this.vX = MathUtils.lerp(this.vX, 0.0F, Gdx.graphics.getDeltaTime() * 5.0F);
/*  94 */       this.x = MathUtils.lerp(this.x, this.targetX, Gdx.graphics.getDeltaTime() * 4.0F);
/*  95 */       this.y = MathUtils.lerp(this.y, this.targetY, Gdx.graphics.getDeltaTime() * 4.0F);
/*     */       
/*  97 */       if (Math.abs(this.x - this.targetX) < 20.0F) {
/*  98 */         this.isDone = true;
/*  99 */         if (MathUtils.randomBoolean()) {
/* 100 */           CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
/*     */         }
/*     */         
/* 103 */         if (!this.owner.isPlayer) {
/* 104 */           this.owner.gainGold(1);
/*     */         }
/*     */         
/* 107 */         AbstractDungeon.effectsQueue.add(new ShineLinesEffect(this.x, this.y));
/*     */         
/* 109 */         boolean textEffectFound = false;
/* 110 */         for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 111 */           if (e instanceof GainGoldTextEffect && (
/* 112 */             (GainGoldTextEffect)e).ping(1)) {
/* 113 */             textEffectFound = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         
/* 119 */         if (!textEffectFound) {
/* 120 */           for (AbstractGameEffect e : AbstractDungeon.effectsQueue) {
/* 121 */             if (e instanceof GainGoldTextEffect && (
/* 122 */               (GainGoldTextEffect)e).ping(1)) {
/* 123 */               textEffectFound = true;
/*     */             }
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 130 */         if (!textEffectFound && this.showGainEffect) {
/* 131 */           AbstractDungeon.effectsQueue.add(new GainGoldTextEffect(1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 139 */     if (this.staggerTimer > 0.0F) {
/*     */       return;
/*     */     }
/*     */     
/* 143 */     sb.setColor(this.color);
/* 144 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\GainPennyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */