/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class FlashAtkImgEffect extends AbstractGameEffect {
/*  16 */   private static int blockSound = 0;
/*     */   
/*     */   public TextureAtlas.AtlasRegion img;
/*     */   private float x;
/*     */   private float y;
/*     */   private float sY;
/*     */   
/*     */   public FlashAtkImgEffect(float x, float y, AbstractGameAction.AttackEffect effect, boolean mute) {
/*  24 */     this.duration = 0.6F;
/*  25 */     this.startingDuration = 0.6F;
/*  26 */     this.effect = effect;
/*  27 */     this.img = loadImage();
/*  28 */     if (this.img != null) {
/*  29 */       this.x = x - this.img.packedWidth / 2.0F;
/*  30 */       y -= this.img.packedHeight / 2.0F;
/*     */     } 
/*  32 */     this.color = Color.WHITE.cpy();
/*  33 */     this.scale = Settings.scale;
/*     */     
/*  35 */     if (!mute) {
/*  36 */       playSound(effect);
/*     */     }
/*     */     
/*  39 */     this.y = y;
/*  40 */     switch (effect) {
/*     */       case SHIELD:
/*  42 */         this.y = y + 80.0F * Settings.scale;
/*  43 */         this.sY = this.y;
/*  44 */         this.tY = y;
/*     */         return;
/*     */     } 
/*  47 */     this.y = y;
/*  48 */     this.sY = y;
/*  49 */     this.tY = y;
/*     */   }
/*     */   private float tY; private static final float DURATION = 0.6F; private AbstractGameAction.AttackEffect effect;
/*     */   private boolean triggered = false;
/*     */   
/*     */   public FlashAtkImgEffect(float x, float y, AbstractGameAction.AttackEffect effect) {
/*  55 */     this(x, y, effect, false);
/*     */   }
/*     */   
/*     */   private TextureAtlas.AtlasRegion loadImage() {
/*  59 */     switch (this.effect) {
/*     */       case SLASH_DIAGONAL:
/*  61 */         return ImageMaster.ATK_SLASH_D;
/*     */       case SLASH_HEAVY:
/*  63 */         return ImageMaster.ATK_SLASH_HEAVY;
/*     */       case SLASH_HORIZONTAL:
/*  65 */         return ImageMaster.ATK_SLASH_H;
/*     */       case SLASH_VERTICAL:
/*  67 */         return ImageMaster.ATK_SLASH_V;
/*     */       case BLUNT_LIGHT:
/*  69 */         return ImageMaster.ATK_BLUNT_LIGHT;
/*     */       case BLUNT_HEAVY:
/*  71 */         this.rotation = MathUtils.random(360.0F);
/*  72 */         return ImageMaster.ATK_BLUNT_HEAVY;
/*     */       case FIRE:
/*  74 */         return ImageMaster.ATK_FIRE;
/*     */       case POISON:
/*  76 */         return ImageMaster.ATK_POISON;
/*     */       case SHIELD:
/*  78 */         return ImageMaster.ATK_SHIELD;
/*     */       case NONE:
/*  80 */         return null;
/*     */     } 
/*  82 */     return ImageMaster.ATK_SLASH_D;
/*     */   }
/*     */ 
/*     */   
/*     */   private void playSound(AbstractGameAction.AttackEffect effect) {
/*  87 */     switch (effect) {
/*     */       case SLASH_HEAVY:
/*  89 */         CardCrawlGame.sound.play("ATTACK_HEAVY");
/*     */       
/*     */       case BLUNT_LIGHT:
/*  92 */         CardCrawlGame.sound.play("BLUNT_FAST");
/*     */       
/*     */       case BLUNT_HEAVY:
/*  95 */         CardCrawlGame.sound.play("BLUNT_HEAVY");
/*     */       
/*     */       case FIRE:
/*  98 */         CardCrawlGame.sound.play("ATTACK_FIRE");
/*     */       
/*     */       case POISON:
/* 101 */         CardCrawlGame.sound.play("ATTACK_POISON");
/*     */       
/*     */       case SHIELD:
/* 104 */         playBlockSound();
/*     */       
/*     */       case NONE:
/*     */         return;
/*     */     } 
/* 109 */     CardCrawlGame.sound.play("ATTACK_FAST");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void playBlockSound() {
/* 118 */     if (blockSound == 0) {
/* 119 */       CardCrawlGame.sound.play("BLOCK_GAIN_1");
/* 120 */     } else if (blockSound == 1) {
/* 121 */       CardCrawlGame.sound.play("BLOCK_GAIN_2");
/*     */     } else {
/* 123 */       CardCrawlGame.sound.play("BLOCK_GAIN_3");
/*     */     } 
/*     */     
/* 126 */     blockSound++;
/* 127 */     if (blockSound > 2) {
/* 128 */       blockSound = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public void update() {
/* 133 */     switch (this.effect) {
/*     */       case SHIELD:
/* 135 */         this.duration -= Gdx.graphics.getDeltaTime();
/* 136 */         if (this.duration < 0.0F) {
/* 137 */           this.isDone = true;
/* 138 */           this.color.a = 0.0F;
/* 139 */         } else if (this.duration < 0.2F) {
/* 140 */           this.color.a = this.duration * 5.0F;
/*     */         } else {
/* 142 */           this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.duration * 0.75F / 0.6F);
/*     */         } 
/*     */         
/* 145 */         this.y = Interpolation.exp10In.apply(this.tY, this.sY, this.duration / 0.6F);
/*     */         
/* 147 */         if (this.duration < 0.4F && !this.triggered) {
/* 148 */           this.triggered = true;
/*     */         }
/*     */         return;
/*     */     } 
/* 152 */     super.update();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 159 */     if (this.img != null) {
/* 160 */       sb.setColor(this.color);
/* 161 */       sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FlashAtkImgEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */