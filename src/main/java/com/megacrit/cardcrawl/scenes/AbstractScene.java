/*     */ package com.megacrit.cardcrawl.scenes;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public abstract class AbstractScene
/*     */ {
/*  18 */   private static final Logger logger = LogManager.getLogger(AbstractScene.class.getName());
/*  19 */   private Color bgOverlayColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  20 */   private float bgOverlayTarget = 0.0F;
/*     */   protected final TextureAtlas atlas;
/*     */   protected final TextureAtlas.AtlasRegion bg;
/*     */   protected final TextureAtlas.AtlasRegion campfireBg;
/*  24 */   private float vertY = 0.0F;
/*     */   protected final TextureAtlas.AtlasRegion campfireGlow;
/*     */   protected final TextureAtlas.AtlasRegion campfireKindling;
/*  27 */   protected long ambianceSoundId = 0L; protected final TextureAtlas.AtlasRegion event; protected boolean isCamp = false;
/*  28 */   protected String ambianceSoundKey = null;
/*     */   protected String ambianceName;
/*     */   
/*     */   public AbstractScene(String atlasUrl) {
/*  32 */     this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
/*  33 */     this.bg = this.atlas.findRegion("bg");
/*  34 */     this.campfireBg = this.atlas.findRegion("campfire");
/*  35 */     this.campfireGlow = this.atlas.findRegion("mod/campfireGlow");
/*  36 */     this.campfireKindling = this.atlas.findRegion("mod/campfireKindling");
/*  37 */     this.event = this.atlas.findRegion("event");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  45 */     updateBgOverlay();
/*  46 */     if (this.vertY != 0.0F) {
/*  47 */       this.vertY = MathHelper.uiLerpSnap(this.vertY, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void updateBgOverlay() {
/*  52 */     if (this.bgOverlayColor.a != this.bgOverlayTarget) {
/*  53 */       this.bgOverlayColor.a = MathUtils.lerp(this.bgOverlayColor.a, this.bgOverlayTarget, Gdx.graphics.getDeltaTime() * 2.0F);
/*  54 */       if (Math.abs(this.bgOverlayColor.a - this.bgOverlayTarget) < 0.01F) {
/*  55 */         this.bgOverlayColor.a = this.bgOverlayTarget;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void nextRoom(AbstractRoom room) {
/*  65 */     this.bgOverlayColor = new Color(0.0F, 0.0F, 0.0F, 0.5F);
/*  66 */     this.bgOverlayTarget = 0.5F;
/*     */   }
/*     */   
/*     */   public void changeOverlay(float target) {
/*  70 */     this.bgOverlayTarget = target;
/*     */   }
/*     */   
/*     */   public abstract void renderCombatRoomBg(SpriteBatch paramSpriteBatch);
/*     */   
/*     */   public abstract void renderCombatRoomFg(SpriteBatch paramSpriteBatch);
/*     */   
/*     */   public abstract void renderCampfireRoom(SpriteBatch paramSpriteBatch);
/*     */   
/*     */   public abstract void randomizeScene();
/*     */   
/*     */   public void renderEventRoom(SpriteBatch sb) {
/*  82 */     sb.setColor(Color.WHITE);
/*  83 */     if (Settings.isFourByThree) {
/*  84 */       sb.draw(this.event
/*  85 */           .getTexture(), this.event.offsetX * Settings.scale, this.event.offsetY * Settings.scale, 0.0F, 0.0F, this.event.packedWidth, this.event.packedHeight, Settings.yScale, Settings.yScale, 0.0F, this.event
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  95 */           .getRegionX(), this.event
/*  96 */           .getRegionY(), this.event
/*  97 */           .getRegionWidth(), this.event
/*  98 */           .getRegionHeight(), false, false);
/*     */     }
/*     */     else {
/*     */       
/* 102 */       sb.draw(this.event
/* 103 */           .getTexture(), this.event.offsetX * Settings.scale, this.event.offsetY * Settings.scale, 0.0F, 0.0F, this.event.packedWidth, this.event.packedHeight, Settings.xScale, Settings.xScale, 0.0F, this.event
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 113 */           .getRegionX(), this.event
/* 114 */           .getRegionY(), this.event
/* 115 */           .getRegionWidth(), this.event
/* 116 */           .getRegionHeight(), false, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 123 */     this.atlas.dispose();
/*     */   }
/*     */   
/*     */   public void fadeOutAmbiance() {
/* 127 */     if (this.ambianceSoundKey != null) {
/* 128 */       logger.info("Fading out ambiance: " + this.ambianceSoundKey);
/* 129 */       CardCrawlGame.sound.fadeOut(this.ambianceSoundKey, this.ambianceSoundId);
/* 130 */       this.ambianceSoundKey = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fadeInAmbiance() {
/* 135 */     if (this.ambianceSoundKey == null) {
/* 136 */       logger.info("Fading in ambiance: " + this.ambianceName);
/* 137 */       this.ambianceSoundKey = this.ambianceName;
/* 138 */       this.ambianceSoundId = CardCrawlGame.sound.playAndLoop(this.ambianceName);
/* 139 */       updateAmbienceVolume();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void muteAmbienceVolume() {
/* 144 */     if (Settings.AMBIANCE_ON) {
/* 145 */       CardCrawlGame.sound.adjustVolume(this.ambianceName, this.ambianceSoundId, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateAmbienceVolume() {
/* 150 */     if (this.ambianceSoundId != 0L) {
/* 151 */       if (Settings.AMBIANCE_ON) {
/* 152 */         CardCrawlGame.sound.adjustVolume(this.ambianceName, this.ambianceSoundId);
/*     */       } else {
/* 154 */         CardCrawlGame.sound.adjustVolume(this.ambianceName, this.ambianceSoundId, 0.0F);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void renderAtlasRegionIf(SpriteBatch sb, TextureAtlas.AtlasRegion region, boolean condition) {
/* 160 */     if (condition) {
/* 161 */       if (Settings.isFourByThree) {
/* 162 */         sb.draw(region
/* 163 */             .getTexture(), region.offsetX * Settings.scale, region.offsetY * Settings.yScale, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.scale, Settings.yScale, 0.0F, region
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 173 */             .getRegionX(), region
/* 174 */             .getRegionY(), region
/* 175 */             .getRegionWidth(), region
/* 176 */             .getRegionHeight(), false, false);
/*     */       
/*     */       }
/* 179 */       else if (Settings.isLetterbox) {
/* 180 */         sb.draw(region
/* 181 */             .getTexture(), region.offsetX * Settings.xScale, region.offsetY * Settings.xScale, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.xScale, Settings.xScale, 0.0F, region
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 191 */             .getRegionX(), region
/* 192 */             .getRegionY(), region
/* 193 */             .getRegionWidth(), region
/* 194 */             .getRegionHeight(), false, false);
/*     */       }
/*     */       else {
/*     */         
/* 198 */         sb.draw(region
/* 199 */             .getTexture(), region.offsetX * Settings.scale, region.offsetY * Settings.scale, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.scale, Settings.scale, 0.0F, region
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 209 */             .getRegionX(), region
/* 210 */             .getRegionY(), region
/* 211 */             .getRegionWidth(), region
/* 212 */             .getRegionHeight(), false, false);
/*     */       } 
/*     */     }
/*     */   }
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
/*     */   protected void renderAtlasRegionIf(SpriteBatch sb, float x, float y, float angle, TextureAtlas.AtlasRegion region, boolean condition) {
/* 227 */     if (condition) {
/* 228 */       sb.draw(region
/* 229 */           .getTexture(), region.offsetX * Settings.scale + x, region.offsetY * Settings.scale + y, region.packedWidth / 2.0F, region.packedHeight / 2.0F, region.packedWidth, region.packedHeight, Settings.scale, Settings.scale, angle, region
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 239 */           .getRegionX(), region
/* 240 */           .getRegionY(), region
/* 241 */           .getRegionWidth(), region
/* 242 */           .getRegionHeight(), false, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderQuadrupleSize(SpriteBatch sb, TextureAtlas.AtlasRegion region, boolean condition) {
/* 249 */     if (condition)
/* 250 */       if (Settings.isFourByThree) {
/* 251 */         sb.draw(region
/* 252 */             .getTexture(), region.offsetX * Settings.scale * 2.0F, region.offsetY * Settings.yScale, 0.0F, 0.0F, (region.packedWidth * 2), (region.packedHeight * 2), Settings.scale, Settings.yScale, 0.0F, region
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 262 */             .getRegionX(), region
/* 263 */             .getRegionY(), region
/* 264 */             .getRegionWidth(), region
/* 265 */             .getRegionHeight(), false, false);
/*     */       }
/*     */       else {
/*     */         
/* 269 */         sb.draw(region
/* 270 */             .getTexture(), region.offsetX * Settings.xScale * 2.0F, region.offsetY * Settings.scale, 0.0F, 0.0F, (region.packedWidth * 2), (region.packedHeight * 2), Settings.xScale, Settings.scale, 0.0F, region
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 280 */             .getRegionX(), region
/* 281 */             .getRegionY(), region
/* 282 */             .getRegionWidth(), region
/* 283 */             .getRegionHeight(), false, false);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\scenes\AbstractScene.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */