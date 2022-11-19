/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

import java.util.ArrayList;
/*     */ 
/*     */ public class AdrenalineEffect extends AbstractGameEffect {
/*     */   private Vector2 position;
/*     */   private Vector2 velocity;
/*  21 */   private TextureAtlas.AtlasRegion img = null;
/*  22 */   private ArrayList<Vector2> prevPositions = new ArrayList<>();
/*     */   private static boolean flipper = true;
/*     */   
/*     */   public AdrenalineEffect() {
/*  26 */     this.img = ImageMaster.GLOW_SPARK_2;
/*     */     
/*  28 */     this.duration = 1.5F;
/*  29 */     if (flipper) {
/*  30 */       this.position = new Vector2(-100.0F * Settings.scale - this.img.packedWidth / 2.0F, Settings.HEIGHT / 2.0F - this.img.packedHeight / 2.0F);
/*     */     }
/*     */     else {
/*     */       
/*  34 */       this.position = new Vector2(-50.0F * Settings.scale - this.img.packedWidth / 2.0F, Settings.HEIGHT / 2.0F - this.img.packedHeight / 2.0F);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  39 */     flipper = !flipper;
/*     */     
/*  41 */     this.velocity = new Vector2(3000.0F * Settings.scale, 0.0F);
/*  42 */     this.color = new Color(1.0F, 1.0F, 0.2F, 1.0F);
/*  43 */     this.scale = 3.0F * Settings.scale;
/*     */   }
/*     */   
/*     */   public void update() {
/*  47 */     if (this.duration == 1.5F) {
/*  48 */       CardCrawlGame.sound.playA("ATTACK_WHIFF_1", -0.6F);
/*  49 */       CardCrawlGame.sound.playA("ORB_LIGHTNING_CHANNEL", 0.6F);
/*  50 */       AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.BLUE.cpy(), true));
/*     */     } 
/*     */     
/*  53 */     if (this.position.x > Settings.WIDTH * 0.55F && this.position.y > Settings.HEIGHT / 2.0F - this.img.packedHeight / 2.0F) {
/*  54 */       this.velocity.y = 0.0F;
/*  55 */       this.position.y = Settings.HEIGHT / 2.0F - this.img.packedHeight / 2.0F;
/*  56 */       this.velocity.x = 3000.0F * Settings.scale;
/*  57 */     } else if (this.position.x > Settings.WIDTH * 0.5F) {
/*  58 */       this.velocity.y = 6000.0F * Settings.scale;
/*  59 */     } else if (this.position.x > Settings.WIDTH * 0.4F) {
/*  60 */       this.velocity.y = -6000.0F * Settings.scale;
/*  61 */     } else if (this.position.x > Settings.WIDTH * 0.35F) {
/*  62 */       this.velocity.y = 6000.0F * Settings.scale;
/*  63 */       this.velocity.x = 2000.0F * Settings.scale;
/*     */     } 
/*     */     
/*  66 */     this.prevPositions.add(this.position.cpy());
/*  67 */     this.position.mulAdd(this.velocity, Gdx.graphics.getDeltaTime());
/*  68 */     if (this.prevPositions.size() > 30) {
/*  69 */       this.prevPositions.remove(0);
/*     */     }
/*     */     
/*  72 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  73 */     if (this.duration < 0.0F) {
/*  74 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  80 */     sb.setBlendFunction(770, 1);
/*     */     
/*  82 */     for (int i = 0; i < this.prevPositions.size(); i++) {
/*  83 */       sb.setColor(new Color(1.0F, 0.9F, 0.3F, 1.0F));
/*  84 */       sb.draw((TextureRegion)this.img, ((Vector2)this.prevPositions
/*     */           
/*  86 */           .get(i)).x, ((Vector2)this.prevPositions
/*  87 */           .get(i)).y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale / 8.0F * (i * 0.05F + 1.0F) * 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  92 */           MathUtils.random(1.5F, 3.0F), this.scale / 8.0F * (i * 0.05F + 1.0F) * 
/*  93 */           MathUtils.random(0.5F, 2.0F), 0.0F);
/*     */     } 
/*     */ 
/*     */     
/*  97 */     sb.setColor(Color.RED);
/*  98 */     sb.draw((TextureRegion)this.img, this.position.x, this.position.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 2.5F, this.scale * 2.5F, 0.0F);
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
/* 110 */     sb.setBlendFunction(770, 771);
/* 111 */     sb.setColor(Color.YELLOW);
/* 112 */     sb.draw((TextureRegion)this.img, this.position.x, this.position.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, 0.0F);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\AdrenalineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */