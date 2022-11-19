/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ public class SingleRelicViewPopup
/*     */ {
/*  38 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SingleViewRelicPopup");
/*  39 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public boolean isOpen = false;
/*     */   
/*     */   private ArrayList<AbstractRelic> group;
/*     */   private AbstractRelic relic;
/*     */   private AbstractRelic prevRelic;
/*  46 */   private float fadeTimer = 0.0F; private AbstractRelic nextRelic; private static final int W = 128; private Texture relicFrameImg; private Texture largeImg;
/*  47 */   private Color fadeColor = Color.BLACK.cpy(); private Hitbox popupHb; private Hitbox prevHb;
/*     */   private Hitbox nextHb;
/*  49 */   private String rarityLabel = "";
/*     */   private static final String LARGE_IMG_DIR = "images/largeRelics/";
/*  51 */   private static final float DESC_LINE_SPACING = 30.0F * Settings.scale;
/*  52 */   private static final float DESC_LINE_WIDTH = 418.0F * Settings.scale;
/*     */   
/*     */   private final float RELIC_OFFSET_Y;
/*     */ 
/*     */   
/*     */   public SingleRelicViewPopup() {
/*  58 */     this.RELIC_OFFSET_Y = 76.0F * Settings.scale;
/*     */   }
/*     */   
/*     */   public void open(AbstractRelic relic, ArrayList<AbstractRelic> group) {
/*  62 */     CardCrawlGame.isPopupOpen = true;
/*  63 */     relic.playLandingSFX();
/*     */     
/*  65 */     this.prevRelic = null;
/*  66 */     this.nextRelic = null;
/*  67 */     this.prevHb = null;
/*  68 */     this.nextHb = null;
/*     */     
/*  70 */     for (int i = 0; i < group.size(); i++) {
/*  71 */       if (group.get(i) == relic) {
/*  72 */         if (i != 0) {
/*  73 */           this.prevRelic = group.get(i - 1);
/*     */         }
/*  75 */         if (i != group.size() - 1) {
/*  76 */           this.nextRelic = group.get(i + 1);
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  82 */     this.prevHb = new Hitbox(160.0F * Settings.scale, 160.0F * Settings.scale);
/*  83 */     this.nextHb = new Hitbox(160.0F * Settings.scale, 160.0F * Settings.scale);
/*  84 */     this.prevHb.move(Settings.WIDTH / 2.0F - 400.0F * Settings.scale, Settings.HEIGHT / 2.0F);
/*  85 */     this.nextHb.move(Settings.WIDTH / 2.0F + 400.0F * Settings.scale, Settings.HEIGHT / 2.0F);
/*     */     
/*  87 */     this.popupHb = new Hitbox(550.0F * Settings.scale, 680.0F * Settings.scale);
/*  88 */     this.popupHb.move(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/*  89 */     this.isOpen = true;
/*  90 */     this.group = group;
/*  91 */     this.relic = relic;
/*  92 */     this.fadeTimer = 0.25F;
/*  93 */     this.fadeColor.a = 0.0F;
/*  94 */     generateRarityLabel();
/*  95 */     generateFrameImg();
/*  96 */     this.relic.isSeen = UnlockTracker.isRelicSeen(relic.relicId);
/*  97 */     initializeLargeImg();
/*     */   }
/*     */   
/*     */   public void open(AbstractRelic relic) {
/* 101 */     CardCrawlGame.isPopupOpen = true;
/* 102 */     relic.playLandingSFX();
/*     */     
/* 104 */     this.prevRelic = null;
/* 105 */     this.nextRelic = null;
/* 106 */     this.prevHb = null;
/* 107 */     this.nextHb = null;
/*     */     
/* 109 */     this.popupHb = new Hitbox(550.0F * Settings.scale, 680.0F * Settings.scale);
/* 110 */     this.popupHb.move(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/* 111 */     this.isOpen = true;
/* 112 */     this.group = null;
/* 113 */     this.relic = relic;
/* 114 */     this.fadeTimer = 0.25F;
/* 115 */     this.fadeColor.a = 0.0F;
/* 116 */     generateRarityLabel();
/* 117 */     generateFrameImg();
/* 118 */     this.relic.isSeen = UnlockTracker.isRelicSeen(relic.relicId);
/* 119 */     initializeLargeImg();
/*     */   }
/*     */   
/*     */   private void initializeLargeImg() {
/* 123 */     this.largeImg = ImageMaster.loadImage("images/largeRelics/" + this.relic.imgUrl);
/*     */   }
/*     */   
/*     */   public void close() {
/* 127 */     CardCrawlGame.isPopupOpen = false;
/* 128 */     this.isOpen = false;
/* 129 */     InputHelper.justReleasedClickLeft = false;
/*     */     
/* 131 */     if (this.largeImg != null) {
/* 132 */       this.largeImg.dispose();
/* 133 */       this.largeImg = null;
/*     */     } 
/*     */     
/* 136 */     if (this.relicFrameImg != null) {
/* 137 */       this.relicFrameImg.dispose();
/* 138 */       this.relicFrameImg = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/* 143 */     this.popupHb.update();
/* 144 */     updateArrows();
/* 145 */     updateInput();
/* 146 */     updateFade();
/* 147 */     updateSoundEffect();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateArrows() {
/* 152 */     if (this.prevRelic != null) {
/* 153 */       this.prevHb.update();
/* 154 */       if (this.prevHb.justHovered) {
/* 155 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/* 157 */       if (this.prevHb.clicked || (this.prevRelic != null && CInputActionSet.pageLeftViewDeck.isJustPressed())) {
/* 158 */         openPrev();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 163 */     if (this.nextRelic != null) {
/* 164 */       this.nextHb.update();
/* 165 */       if (this.nextHb.justHovered) {
/* 166 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*     */       
/* 169 */       if (this.nextHb.clicked || (this.nextRelic != null && CInputActionSet.pageRightViewExhaust.isJustPressed())) {
/* 170 */         openNext();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateInput() {
/* 176 */     if (InputHelper.justClickedLeft) {
/* 177 */       if (this.prevRelic != null && this.prevHb.hovered) {
/* 178 */         this.prevHb.clickStarted = true;
/* 179 */         CardCrawlGame.sound.play("UI_CLICK_1"); return;
/*     */       } 
/* 181 */       if (this.nextRelic != null && this.nextHb.hovered) {
/* 182 */         this.nextHb.clickStarted = true;
/* 183 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 188 */     if (InputHelper.justReleasedClickLeft) {
/* 189 */       if (!this.popupHb.hovered) {
/* 190 */         close();
/* 191 */         FontHelper.ClearSRVFontTextures();
/*     */       } 
/* 193 */     } else if (InputHelper.pressedEscape || CInputActionSet.cancel.isJustPressed()) {
/* 194 */       CInputActionSet.cancel.unpress();
/* 195 */       InputHelper.pressedEscape = false;
/* 196 */       if (AbstractDungeon.screen == null || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NONE) {
/* 197 */         AbstractDungeon.isScreenUp = false;
/*     */       }
/* 199 */       close();
/* 200 */       FontHelper.ClearSRVFontTextures();
/*     */     } 
/*     */ 
/*     */     
/* 204 */     if (this.prevRelic != null && InputActionSet.left.isJustPressed()) {
/* 205 */       openPrev();
/* 206 */     } else if (this.nextRelic != null && InputActionSet.right.isJustPressed()) {
/* 207 */       openNext();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void openPrev() {
/* 212 */     close();
/* 213 */     open(this.prevRelic, this.group);
/* 214 */     this.fadeTimer = 0.0F;
/* 215 */     this.fadeColor.a = 0.9F;
/*     */   }
/*     */   
/*     */   private void openNext() {
/* 219 */     close();
/* 220 */     open(this.nextRelic, this.group);
/* 221 */     this.fadeTimer = 0.0F;
/* 222 */     this.fadeColor.a = 0.9F;
/*     */   }
/*     */   
/*     */   private void updateFade() {
/* 226 */     this.fadeTimer -= Gdx.graphics.getDeltaTime();
/* 227 */     if (this.fadeTimer < 0.0F) {
/* 228 */       this.fadeTimer = 0.0F;
/*     */     }
/* 230 */     this.fadeColor.a = Interpolation.pow2In.apply(0.9F, 0.0F, this.fadeTimer * 4.0F);
/*     */   }
/*     */   
/*     */   private void updateSoundEffect() {
/* 234 */     String key = null;
/*     */     
/* 236 */     if (this.relic instanceof com.megacrit.cardcrawl.relics.Tingsha) {
/* 237 */       key = "TINGSHA";
/* 238 */     } else if (this.relic instanceof com.megacrit.cardcrawl.relics.Damaru) {
/* 239 */       key = "DAMARU";
/* 240 */     } else if (this.relic instanceof com.megacrit.cardcrawl.relics.SingingBowl) {
/* 241 */       key = "SINGING_BOWL";
/* 242 */     } else if (this.relic instanceof com.megacrit.cardcrawl.relics.CallingBell) {
/* 243 */       key = "BELL";
/* 244 */     } else if (this.relic instanceof com.megacrit.cardcrawl.relics.ChemicalX) {
/* 245 */       key = "POTION_3";
/* 246 */     } else if (this.relic instanceof com.megacrit.cardcrawl.relics.Cauldron) {
/* 247 */       key = "POTION_1";
/* 248 */     } else if (this.relic instanceof com.megacrit.cardcrawl.relics.MembershipCard) {
/* 249 */       key = "SHOP_PURCHASE";
/* 250 */     } else if (this.relic instanceof com.megacrit.cardcrawl.relics.CharonsAshes) {
/* 251 */       key = "CARD_BURN";
/*     */     } 
/*     */     
/* 254 */     if (InputActionSet.selectCard_1.isJustPressed()) {
/* 255 */       CardCrawlGame.sound.playA(key, -0.2F);
/* 256 */     } else if (InputActionSet.selectCard_2.isJustPressed()) {
/* 257 */       CardCrawlGame.sound.playA(key, -0.15F);
/* 258 */     } else if (InputActionSet.selectCard_3.isJustPressed()) {
/* 259 */       CardCrawlGame.sound.playA(key, -0.1F);
/* 260 */     } else if (InputActionSet.selectCard_4.isJustPressed()) {
/* 261 */       CardCrawlGame.sound.playA(key, -0.05F);
/* 262 */     } else if (InputActionSet.selectCard_5.isJustPressed()) {
/* 263 */       CardCrawlGame.sound.playA(key, 0.0F);
/* 264 */     } else if (InputActionSet.selectCard_6.isJustPressed()) {
/* 265 */       CardCrawlGame.sound.playA(key, 0.05F);
/* 266 */     } else if (InputActionSet.selectCard_7.isJustPressed()) {
/* 267 */       CardCrawlGame.sound.playA(key, 0.1F);
/* 268 */     } else if (InputActionSet.selectCard_8.isJustPressed()) {
/* 269 */       CardCrawlGame.sound.playA(key, 0.15F);
/* 270 */     } else if (InputActionSet.selectCard_9.isJustPressed()) {
/* 271 */       CardCrawlGame.sound.playA(key, 0.2F);
/* 272 */     } else if (InputActionSet.selectCard_10.isJustPressed()) {
/* 273 */       CardCrawlGame.sound.playA(key, 0.25F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void generateRarityLabel() {
/* 278 */     switch (this.relic.tier) {
/*     */       case BOSS:
/* 280 */         this.rarityLabel = TEXT[0];
/*     */         break;
/*     */       case COMMON:
/* 283 */         this.rarityLabel = TEXT[1];
/*     */         break;
/*     */       case DEPRECATED:
/* 286 */         this.rarityLabel = TEXT[2];
/*     */         break;
/*     */       case RARE:
/* 289 */         this.rarityLabel = TEXT[3];
/*     */         break;
/*     */       case SHOP:
/* 292 */         this.rarityLabel = TEXT[4];
/*     */         break;
/*     */       case SPECIAL:
/* 295 */         this.rarityLabel = TEXT[5];
/*     */         break;
/*     */       case STARTER:
/* 298 */         this.rarityLabel = TEXT[6];
/*     */         break;
/*     */       case UNCOMMON:
/* 301 */         this.rarityLabel = TEXT[7];
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateFrameImg() {
/* 310 */     if (!this.relic.isSeen) {
/* 311 */       this.relicFrameImg = ImageMaster.loadImage("images/ui/relicFrameCommon.png");
/*     */       
/*     */       return;
/*     */     } 
/* 315 */     switch (this.relic.tier) {
/*     */       case BOSS:
/* 317 */         this.relicFrameImg = ImageMaster.loadImage("images/ui/relicFrameBoss.png");
/*     */         break;
/*     */       case COMMON:
/* 320 */         this.relicFrameImg = ImageMaster.loadImage("images/ui/relicFrameCommon.png");
/*     */         break;
/*     */       case DEPRECATED:
/* 323 */         this.relicFrameImg = ImageMaster.loadImage("images/ui/relicFrameCommon.png");
/*     */         break;
/*     */       case RARE:
/* 326 */         this.relicFrameImg = ImageMaster.loadImage("images/ui/relicFrameRare.png");
/*     */         break;
/*     */       case SHOP:
/* 329 */         this.relicFrameImg = ImageMaster.loadImage("images/ui/relicFrameRare.png");
/*     */         break;
/*     */       case SPECIAL:
/* 332 */         this.relicFrameImg = ImageMaster.loadImage("images/ui/relicFrameRare.png");
/*     */         break;
/*     */       case STARTER:
/* 335 */         this.relicFrameImg = ImageMaster.loadImage("images/ui/relicFrameCommon.png");
/*     */         break;
/*     */       case UNCOMMON:
/* 338 */         this.relicFrameImg = ImageMaster.loadImage("images/ui/relicFrameUncommon.png");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 347 */     sb.setColor(this.fadeColor);
/* 348 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 349 */     renderPopupBg(sb);
/* 350 */     renderFrame(sb);
/* 351 */     renderArrows(sb);
/* 352 */     renderRelicImage(sb);
/* 353 */     renderName(sb);
/* 354 */     renderRarity(sb);
/* 355 */     renderDescription(sb);
/* 356 */     renderQuote(sb);
/* 357 */     renderTips(sb);
/* 358 */     this.popupHb.render(sb);
/* 359 */     if (this.prevHb != null) {
/* 360 */       this.prevHb.render(sb);
/*     */     }
/* 362 */     if (this.nextHb != null) {
/* 363 */       this.nextHb.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderPopupBg(SpriteBatch sb) {
/* 368 */     sb.setColor(Color.WHITE);
/* 369 */     sb.draw(ImageMaster.RELIC_POPUP, Settings.WIDTH / 2.0F - 960.0F, Settings.HEIGHT / 2.0F - 540.0F, 960.0F, 540.0F, 1920.0F, 1080.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1920, 1080, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderFrame(SpriteBatch sb) {
/* 389 */     sb.draw(this.relicFrameImg, Settings.WIDTH / 2.0F - 960.0F, Settings.HEIGHT / 2.0F - 540.0F, 960.0F, 540.0F, 1920.0F, 1080.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1920, 1080, false, false);
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
/*     */   
/*     */   private void renderArrows(SpriteBatch sb) {
/* 414 */     if (this.prevRelic != null) {
/* 415 */       sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0F, this.prevHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 433 */       if (Settings.isControllerMode) {
/* 434 */         sb.draw(CInputActionSet.pageLeftViewDeck
/* 435 */             .getKeyImg(), this.prevHb.cX - 32.0F + 0.0F * Settings.scale, this.prevHb.cY - 32.0F + 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 453 */       if (this.prevHb.hovered) {
/* 454 */         sb.setBlendFunction(770, 1);
/* 455 */         sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
/* 456 */         sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0F, this.prevHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 473 */         sb.setColor(Color.WHITE);
/* 474 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */     } 
/*     */     
/* 478 */     if (this.nextRelic != null) {
/* 479 */       sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0F, this.nextHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, true, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 497 */       if (Settings.isControllerMode) {
/* 498 */         sb.draw(CInputActionSet.pageRightViewExhaust
/* 499 */             .getKeyImg(), this.nextHb.cX - 32.0F + 0.0F * Settings.scale, this.nextHb.cY - 32.0F + 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 517 */       if (this.nextHb.hovered) {
/* 518 */         sb.setBlendFunction(770, 1);
/* 519 */         sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
/* 520 */         sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0F, this.nextHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, true, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 537 */         sb.setColor(Color.WHITE);
/* 538 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderRelicImage(SpriteBatch sb) {
/* 544 */     if (UnlockTracker.isRelicLocked(this.relic.relicId)) {
/* 545 */       sb.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
/* 546 */       sb.draw(ImageMaster.RELIC_LOCK_OUTLINE, Settings.WIDTH / 2.0F - 64.0F, Settings.HEIGHT / 2.0F - 64.0F + this.RELIC_OFFSET_Y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 2.0F, Settings.scale * 2.0F, 0.0F, 0, 0, 128, 128, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 563 */       sb.setColor(Color.WHITE);
/* 564 */       sb.draw(ImageMaster.RELIC_LOCK, Settings.WIDTH / 2.0F - 64.0F, Settings.HEIGHT / 2.0F - 64.0F + this.RELIC_OFFSET_Y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 2.0F, Settings.scale * 2.0F, 0.0F, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 584 */     if (!this.relic.isSeen) {
/* 585 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.75F));
/*     */     } else {
/* 587 */       sb.setColor(new Color(0.0F, 0.0F, 0.0F, 0.5F));
/*     */     } 
/*     */     
/* 590 */     sb.draw(this.relic.outlineImg, Settings.WIDTH / 2.0F - 64.0F, Settings.HEIGHT / 2.0F - 64.0F + this.RELIC_OFFSET_Y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 2.0F, Settings.scale * 2.0F, 0.0F, 0, 0, 128, 128, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 608 */     if (!this.relic.isSeen) {
/* 609 */       sb.setColor(Color.BLACK);
/*     */     } else {
/* 611 */       sb.setColor(Color.WHITE);
/*     */     } 
/*     */     
/* 614 */     if (this.largeImg == null) {
/* 615 */       sb.draw(this.relic.img, Settings.WIDTH / 2.0F - 64.0F, Settings.HEIGHT / 2.0F - 64.0F + this.RELIC_OFFSET_Y, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 2.0F, Settings.scale * 2.0F, 0.0F, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 633 */       sb.draw(this.largeImg, Settings.WIDTH / 2.0F - 128.0F, Settings.HEIGHT / 2.0F - 128.0F + this.RELIC_OFFSET_Y, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderName(SpriteBatch sb) {
/* 654 */     if (UnlockTracker.isRelicLocked(this.relic.relicId)) {
/* 655 */       FontHelper.renderWrappedText(sb, FontHelper.SCP_cardDescFont, TEXT[8], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 265.0F * Settings.scale, 9999.0F, Settings.CREAM_COLOR, 0.9F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 664 */     else if (this.relic.isSeen) {
/* 665 */       FontHelper.renderWrappedText(sb, FontHelper.SCP_cardDescFont, this.relic.name, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 280.0F * Settings.scale, 9999.0F, Settings.CREAM_COLOR, 0.9F);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 675 */       FontHelper.renderWrappedText(sb, FontHelper.SCP_cardDescFont, TEXT[9], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 265.0F * Settings.scale, 9999.0F, Settings.CREAM_COLOR, 0.9F);
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
/*     */   private void renderRarity(SpriteBatch sb) {
/*     */     Color tmpColor;
/* 690 */     switch (this.relic.tier) {
/*     */       case BOSS:
/* 692 */         tmpColor = Settings.RED_TEXT_COLOR;
/*     */         break;
/*     */       case RARE:
/* 695 */         tmpColor = Settings.GOLD_COLOR;
/*     */         break;
/*     */       case UNCOMMON:
/* 698 */         tmpColor = Settings.BLUE_TEXT_COLOR;
/*     */         break;
/*     */       case COMMON:
/* 701 */         tmpColor = Settings.CREAM_COLOR;
/*     */         break;
/*     */       case STARTER:
/* 704 */         tmpColor = Settings.CREAM_COLOR;
/*     */         break;
/*     */       case SPECIAL:
/* 707 */         tmpColor = Settings.GOLD_COLOR;
/*     */         break;
/*     */       case SHOP:
/* 710 */         tmpColor = Settings.GOLD_COLOR;
/*     */         break;
/*     */       default:
/* 713 */         tmpColor = Settings.CREAM_COLOR;
/*     */         break;
/*     */     } 
/* 716 */     if (this.relic.isSeen) {
/* 717 */       if (Settings.language == Settings.GameLanguage.VIE) {
/* 718 */         FontHelper.renderWrappedText(sb, FontHelper.cardDescFont_N, TEXT[10] + this.rarityLabel, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 240.0F * Settings.scale, 9999.0F, tmpColor, 1.0F);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 728 */         FontHelper.renderWrappedText(sb, FontHelper.cardDescFont_N, this.rarityLabel + TEXT[10], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 240.0F * Settings.scale, 9999.0F, tmpColor, 1.0F);
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
/*     */   private void renderDescription(SpriteBatch sb) {
/* 742 */     if (UnlockTracker.isRelicLocked(this.relic.relicId)) {
/* 743 */       FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, TEXT[11], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 140.0F * Settings.scale, Settings.CREAM_COLOR, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 751 */     else if (this.relic.isSeen) {
/* 752 */       FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, this.relic.description, Settings.WIDTH / 2.0F - 200.0F * Settings.scale, Settings.HEIGHT / 2.0F - 140.0F * Settings.scale - 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 757 */           FontHelper.getSmartHeight(FontHelper.cardDescFont_N, this.relic.description, DESC_LINE_WIDTH, DESC_LINE_SPACING) / 2.0F, DESC_LINE_WIDTH, DESC_LINE_SPACING, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 766 */       FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, TEXT[12], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 140.0F * Settings.scale, Settings.CREAM_COLOR, 1.0F);
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
/*     */   private void renderQuote(SpriteBatch sb) {
/* 778 */     if (this.relic.isSeen) {
/* 779 */       if (this.relic.flavorText != null) {
/* 780 */         FontHelper.renderWrappedText(sb, FontHelper.SRV_quoteFont, this.relic.flavorText, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 310.0F * Settings.scale, DESC_LINE_WIDTH, Settings.CREAM_COLOR, 1.0F);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 790 */         FontHelper.renderWrappedText(sb, FontHelper.SRV_quoteFont, "\"Missing quote...\"", Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 300.0F * Settings.scale, DESC_LINE_WIDTH, Settings.CREAM_COLOR, 1.0F);
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
/*     */   private void renderTips(SpriteBatch sb) {
/* 804 */     if (this.relic.isSeen) {
/* 805 */       ArrayList<PowerTip> t = new ArrayList<>();
/* 806 */       if (this.relic.tips.size() > 1) {
/* 807 */         for (int i = 1; i < this.relic.tips.size(); i++) {
/* 808 */           t.add(this.relic.tips.get(i));
/*     */         }
/*     */       }
/*     */       
/* 812 */       if (!t.isEmpty())
/* 813 */         TipHelper.queuePowerTips(Settings.WIDTH / 2.0F + 340.0F * Settings.scale, 420.0F * Settings.scale, t); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\SingleRelicViewPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */