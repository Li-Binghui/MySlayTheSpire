/*     */ package com.megacrit.cardcrawl.vfx;
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ public class TouchPickupGold extends AbstractGameEffect {
/*     */   private static final float RAW_IMG_WIDTH = 32.0F;
/*  16 */   private static final float IMG_WIDTH = 32.0F * Settings.scale;
/*     */   
/*     */   private TextureAtlas.AtlasRegion img;
/*     */   
/*     */   private boolean isPickupable = false;
/*     */   public boolean pickedup = false;
/*     */   private float x;
/*     */   private float y;
/*  24 */   private float vY = -0.2F; private float landingY; private boolean willBounce; private boolean hasBounced = true; private float bounceY; private float bounceX; private float vX = 0.0F;
/*     */   private float gravity;
/*     */   private Hitbox hitbox;
/*     */   
/*     */   public TouchPickupGold() {
/*  29 */     this.gravity = -0.3F;
/*  30 */     if (MathUtils.randomBoolean()) {
/*  31 */       this.img = ImageMaster.COPPER_COIN_1;
/*     */     } else {
/*  33 */       this.img = ImageMaster.COPPER_COIN_2;
/*     */     } 
/*     */     
/*  36 */     this.willBounce = (MathUtils.random(3) != 0);
/*  37 */     if (this.willBounce) {
/*  38 */       this.hasBounced = false;
/*  39 */       this.bounceY = MathUtils.random(1.0F, 4.0F);
/*  40 */       this.bounceX = MathUtils.random(-3.0F, 3.0F);
/*     */     } 
/*     */     
/*  43 */     this.y = Settings.HEIGHT * MathUtils.random(1.1F, 1.3F) - this.img.packedHeight / 2.0F;
/*  44 */     this.x = MathUtils.random(Settings.WIDTH * 0.3F, Settings.WIDTH * 0.95F) - this.img.packedWidth / 2.0F;
/*  45 */     this.landingY = MathUtils.random(AbstractDungeon.floorY - Settings.HEIGHT * 0.05F, AbstractDungeon.floorY + Settings.HEIGHT * 0.08F);
/*     */ 
/*     */     
/*  48 */     this.rotation = MathUtils.random(360.0F);
/*  49 */     this.scale = Settings.scale;
/*     */   }
/*     */   
/*     */   public TouchPickupGold(boolean centerOnPlayer) {
/*  53 */     this();
/*  54 */     if (centerOnPlayer) {
/*  55 */       this.x = MathUtils.random(AbstractDungeon.player.drawX - AbstractDungeon.player.hb_w, AbstractDungeon.player.drawX + AbstractDungeon.player.hb_w);
/*     */ 
/*     */       
/*  58 */       this.gravity = -0.7F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/*  63 */     if (!this.isPickupable) {
/*  64 */       this.x += this.vX * Gdx.graphics.getDeltaTime() * 60.0F;
/*  65 */       this.y += this.vY * Gdx.graphics.getDeltaTime() * 60.0F;
/*  66 */       this.vY += this.gravity;
/*     */       
/*  68 */       if (this.y < this.landingY) {
/*  69 */         if (this.hasBounced) {
/*  70 */           this.y = this.landingY;
/*  71 */           this.isPickupable = true;
/*  72 */           this.hitbox = new Hitbox(this.x - IMG_WIDTH * 2.0F, this.y - IMG_WIDTH * 2.0F, IMG_WIDTH * 4.0F, IMG_WIDTH * 4.0F);
/*     */         } else {
/*  74 */           if (MathUtils.random(1) == 0) {
/*  75 */             this.hasBounced = true;
/*     */           }
/*  77 */           this.y = this.landingY;
/*  78 */           this.vY = this.bounceY;
/*  79 */           this.vX = this.bounceX;
/*  80 */           this.bounceY *= 0.5F;
/*  81 */           this.bounceX *= 0.3F;
/*     */         }
/*     */       
/*     */       }
/*  85 */     } else if (!this.pickedup) {
/*  86 */       this.pickedup = true;
/*  87 */       this.isDone = true;
/*  88 */       playGainGoldSFX();
/*     */       
/*  90 */       AbstractDungeon.effectsQueue.add(new ShineLinesEffect(this.x, this.y));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void playGainGoldSFX() {
/*  96 */     int roll = MathUtils.random(2);
/*  97 */     switch (roll) {
/*     */       case 0:
/*  99 */         CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
/*     */         return;
/*     */       case 1:
/* 102 */         CardCrawlGame.sound.play("GOLD_GAIN_3", 0.1F);
/*     */         return;
/*     */     } 
/* 105 */     CardCrawlGame.sound.play("GOLD_GAIN_5", 0.1F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 111 */     sb.setColor(Color.WHITE);
/*     */     
/* 113 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     if (this.hitbox != null)
/* 126 */       this.hitbox.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\TouchPickupGold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */