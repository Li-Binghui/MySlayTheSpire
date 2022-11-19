/*     */ package com.megacrit.cardcrawl.blights;
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.vfx.FloatyEffect;
import com.megacrit.cardcrawl.vfx.GlowRelicParticle;
import com.megacrit.cardcrawl.vfx.SmokePuffEffect;

import java.util.ArrayList;
import java.util.Scanner;
/*     */ 
/*     */ public class AbstractBlight {
/*     */   public String name;
/*     */   public String description;
/*     */   public String blightID;
/*     */   public Texture img;
/*     */   public Texture outlineImg;
/*     */   public boolean unique;
/*     */   public int increment;
/*     */   private static final String IMG_DIR = "images/blights/";
/*     */   private static final String OUTLINE_DIR = "images/blights/outline/";
/*     */   public float floatModAmount;
/*     */   public boolean isDone = false;
/*     */   public boolean isAnimating = false;
/*     */   public boolean isObtained = false;
/*     */   public int cost;
/*  47 */   public int counter = -1;
/*  48 */   public ArrayList<PowerTip> tips = new ArrayList<>();
/*  49 */   public String imgUrl = "";
/*     */   
/*     */   public static final int RAW_W = 128;
/*     */   public float currentX;
/*  53 */   private static final float START_X = 64.0F * Settings.xScale; public float currentY; public float targetX; public float targetY;
/*  54 */   private static final float START_Y = Settings.isMobile ? (Settings.HEIGHT - 206.0F * Settings.scale) : (Settings.HEIGHT - 176.0F * Settings.scale);
/*     */   
/*  56 */   private static final Color PASSIVE_OUTLINE_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.33F);
/*  57 */   private Color flashColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  58 */   private Color goldOutlineColor = new Color(1.0F, 0.9F, 0.4F, 0.0F);
/*     */   public boolean isSeen = false;
/*  60 */   public float scale = Settings.scale;
/*     */   
/*     */   public static final int MAX_BLIGHTS_PER_PAGE = 25;
/*  63 */   public Hitbox hb = new Hitbox(AbstractRelic.PAD_X, AbstractRelic.PAD_X);
/*     */   private static final float OBTAIN_SPEED = 6.0F;
/*     */   private static final float OBTAIN_THRESHOLD = 0.5F;
/*  66 */   private float rotation = 0.0F;
/*     */   
/*     */   public boolean discarded = false;
/*     */   
/*     */   protected boolean pulse = false;
/*  71 */   private float animationTimer = 0.0F;
/*  72 */   public float flashTimer = 0.0F;
/*     */   
/*     */   private static final float FLASH_ANIM_TIME = 2.0F;
/*     */   
/*     */   private static final float DEFAULT_ANIM_SCALE = 4.0F;
/*  77 */   private float glowTimer = 0.0F;
/*  78 */   private FloatyEffect f_effect = new FloatyEffect(10.0F, 0.2F);
/*     */ 
/*     */   
/*  81 */   private static float offsetX = 0.0F;
/*     */   
/*     */   public AbstractBlight(String setId, String name, String description, String imgName, boolean unique) {
/*  84 */     this.blightID = setId;
/*  85 */     this.name = name;
/*  86 */     this.description = description;
/*  87 */     this.unique = unique;
/*  88 */     this.img = ImageMaster.loadImage("images/blights/" + imgName);
/*  89 */     this.outlineImg = ImageMaster.loadImage("images/blights/outline/" + imgName);
/*  90 */     this.increment = 0;
/*  91 */     this.tips.add(new PowerTip(name, description));
/*     */   }
/*     */   
/*     */   public void spawn(float x, float y) {
/*  95 */     AbstractDungeon.effectsQueue.add(new SmokePuffEffect(x, y));
/*  96 */     this.currentX = x;
/*  97 */     this.currentY = y;
/*  98 */     this.isAnimating = true;
/*  99 */     this.isObtained = false;
/* 100 */     this.f_effect.x = 0.0F;
/* 101 */     this.f_effect.y = 0.0F;
/* 102 */     this.hb = new Hitbox(AbstractRelic.PAD_X, AbstractRelic.PAD_X);
/*     */   }
/*     */   
/*     */   public void renderInTopPanel(SpriteBatch sb) {
/* 106 */     if (Settings.hideRelics) {
/*     */       return;
/*     */     }
/*     */     
/* 110 */     renderOutline(sb, true);
/* 111 */     sb.setColor(Color.WHITE);
/* 112 */     sb.draw(this.img, this.currentX - 64.0F + offsetX, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
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
/* 129 */     renderCounter(sb, true);
/* 130 */     renderFlash(sb, true);
/* 131 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 135 */     if (Settings.hideRelics) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 140 */     if (this.isDone) {
/* 141 */       renderOutline(sb, false);
/*     */     }
/*     */     
/* 144 */     if (!this.isObtained && (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP)) {
/*     */ 
/*     */ 
/*     */       
/* 148 */       if (this.hb.hovered) {
/* 149 */         renderTip(sb);
/*     */       }
/*     */       
/* 152 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/* 153 */         if (this.hb.hovered) {
/* 154 */           sb.setColor(PASSIVE_OUTLINE_COLOR);
/* 155 */           sb.draw(this.outlineImg, this.currentX - 64.0F + this.f_effect.x, this.currentY - 64.0F + this.f_effect.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 173 */           sb.setColor(PASSIVE_OUTLINE_COLOR);
/* 174 */           sb.draw(this.outlineImg, this.currentX - 64.0F + this.f_effect.x, this.currentY - 64.0F + this.f_effect.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*     */         } 
/*     */       }
/*     */     } 
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
/* 195 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/* 196 */       if (!this.isObtained) {
/* 197 */         sb.setColor(Color.WHITE);
/* 198 */         sb.draw(this.img, this.currentX - 64.0F + this.f_effect.x, this.currentY - 64.0F + this.f_effect.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 216 */         sb.setColor(Color.WHITE);
/* 217 */         sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
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
/* 234 */         renderCounter(sb, false);
/*     */       } 
/*     */     } else {
/* 237 */       sb.setColor(Color.WHITE);
/* 238 */       sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
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
/* 256 */       renderCounter(sb, false);
/*     */     } 
/*     */     
/* 259 */     if (this.isDone) {
/* 260 */       renderFlash(sb, false);
/*     */     }
/*     */     
/* 263 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   protected void updateAnimation() {
/* 267 */     if (this.animationTimer != 0.0F) {
/* 268 */       this.animationTimer -= Gdx.graphics.getDeltaTime();
/* 269 */       if (this.animationTimer < 0.0F) {
/* 270 */         this.animationTimer = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, boolean renderAmount, Color outlineColor) {
/* 276 */     if (this.isSeen) {
/* 277 */       renderOutline(outlineColor, sb, false);
/*     */     } else {
/* 279 */       renderOutline(Color.LIGHT_GRAY, sb, false);
/*     */     } 
/*     */     
/* 282 */     if (this.isSeen) {
/* 283 */       sb.setColor(Color.WHITE);
/*     */     }
/* 285 */     else if (this.hb.hovered) {
/* 286 */       sb.setColor(Settings.HALF_TRANSPARENT_BLACK_COLOR);
/*     */     } else {
/* 288 */       sb.setColor(Color.BLACK);
/*     */     } 
/*     */ 
/*     */     
/* 292 */     sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
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
/* 310 */     if (this.hb.hovered) {
/* 311 */       renderTip(sb);
/*     */     }
/*     */     
/* 314 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
/* 318 */     if (this.counter > -1) {
/* 319 */       FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, 
/*     */ 
/*     */           
/* 322 */           Integer.toString(this.counter), this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderOutline(Color c, SpriteBatch sb, boolean inTopPanel) {
/* 330 */     sb.setColor(c);
/* 331 */     if (AbstractDungeon.screen != null && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NEOW_UNLOCK) {
/* 332 */       sb.draw(this.outlineImg, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 2.0F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 340 */           MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) / 15.0F, Settings.scale * 2.0F + 
/* 341 */           MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) / 15.0F, this.rotation, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 350 */     else if (this.hb.hovered && Settings.isControllerMode) {
/* 351 */       sb.setBlendFunction(770, 1);
/* 352 */       this.goldOutlineColor.a = 0.6F + MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L)) / 5.0F;
/* 353 */       sb.setColor(this.goldOutlineColor);
/* 354 */       sb.draw(this.outlineImg, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
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
/* 371 */       sb.setBlendFunction(770, 771);
/*     */     } else {
/* 373 */       sb.draw(this.outlineImg, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
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
/*     */   
/*     */   public void renderOutline(SpriteBatch sb, boolean inTopPanel) {
/* 395 */     if (this.hb.hovered && Settings.isControllerMode) {
/* 396 */       sb.setBlendFunction(770, 1);
/* 397 */       this.goldOutlineColor.a = 0.6F + MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L)) / 5.0F;
/* 398 */       sb.setColor(this.goldOutlineColor);
/* 399 */       sb.draw(this.outlineImg, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
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
/* 416 */       sb.setBlendFunction(770, 771);
/*     */     } else {
/*     */       
/* 419 */       sb.setColor(PASSIVE_OUTLINE_COLOR);
/* 420 */       sb.draw(this.outlineImg, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
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
/*     */   public void renderFlash(SpriteBatch sb, boolean inTopPanel) {
/* 441 */     float tmp = Interpolation.exp10In.apply(0.0F, 4.0F, this.flashTimer / 2.0F);
/*     */     
/* 443 */     sb.setBlendFunction(770, 1);
/* 444 */     this.flashColor.a = this.flashTimer * 0.2F;
/* 445 */     sb.setColor(this.flashColor);
/* 446 */     sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale + tmp, this.scale + tmp, this.rotation, 0, 0, 128, 128, false, false);
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
/* 464 */     sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale + tmp * 0.66F, this.scale + tmp * 0.66F, this.rotation, 0, 0, 128, 128, false, false);
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
/* 482 */     sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale + tmp / 3.0F, this.scale + tmp / 3.0F, this.rotation, 0, 0, 128, 128, false, false);
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
/* 500 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void flash() {
/* 504 */     this.flashTimer = 2.0F;
/*     */   }
/*     */   
/*     */   public void renderTip(SpriteBatch sb) {
/* 508 */     if (InputHelper.mX < 1400.0F * Settings.scale) {
/* 509 */       if (CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.RELIC_VIEW) {
/* 510 */         TipHelper.queuePowerTips(180.0F * Settings.scale, Settings.HEIGHT * 0.7F, this.tips);
/*     */       } else {
/* 512 */         TipHelper.queuePowerTips(InputHelper.mX + 50.0F * Settings.scale, InputHelper.mY + 50.0F * Settings.scale, this.tips);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 518 */       TipHelper.queuePowerTips(InputHelper.mX - 350.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, this.tips);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeTips() {
/* 526 */     Scanner desc = new Scanner(this.description);
/*     */     
/* 528 */     while (desc.hasNext()) {
/* 529 */       String s = desc.next();
/* 530 */       if (s.charAt(0) == '#') {
/* 531 */         s = s.substring(2);
/*     */       }
/*     */       
/* 534 */       s = s.replace(',', ' ');
/* 535 */       s = s.replace('.', ' ');
/* 536 */       s = s.trim();
/* 537 */       s = s.toLowerCase();
/*     */       
/* 539 */       boolean alreadyExists = false;
/* 540 */       if (GameDictionary.keywords.containsKey(s)) {
/* 541 */         s = (String)GameDictionary.parentWord.get(s);
/* 542 */         for (PowerTip t : this.tips) {
/* 543 */           if (t.header.toLowerCase().equals(s)) {
/* 544 */             alreadyExists = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 548 */         if (!alreadyExists) {
/* 549 */           this.tips.add(new PowerTip(TipHelper.capitalize(s), (String)GameDictionary.keywords.get(s)));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 554 */     desc.close();
/*     */   }
/*     */   
/*     */   public void obtain() {
/* 558 */     this.hb.hovered = false;
/* 559 */     int slot = AbstractDungeon.player.blights.size();
/* 560 */     this.targetX = START_X + slot * AbstractRelic.PAD_X;
/* 561 */     this.targetY = START_Y;
/* 562 */     AbstractDungeon.player.blights.add(this);
/*     */   }
/*     */   
/*     */   public void instantObtain(AbstractPlayer p, int slot, boolean callOnEquip) {
/* 566 */     this.isDone = true;
/* 567 */     this.isObtained = true;
/*     */     
/* 569 */     if (slot >= p.blights.size()) {
/* 570 */       p.blights.add(this);
/*     */     } else {
/* 572 */       p.blights.set(slot, this);
/*     */     } 
/*     */     
/* 575 */     this.currentX = START_X + slot * AbstractRelic.PAD_X;
/* 576 */     this.currentY = START_Y;
/* 577 */     this.targetX = this.currentX;
/* 578 */     this.targetY = this.currentY;
/* 579 */     this.hb.move(this.currentX, this.currentY);
/*     */     
/* 581 */     if (callOnEquip) {
/* 582 */       onEquip();
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateFlash() {
/* 587 */     if (this.flashTimer != 0.0F) {
/* 588 */       this.flashTimer -= Gdx.graphics.getDeltaTime();
/* 589 */       if (this.flashTimer < 0.0F) {
/* 590 */         if (this.pulse) {
/* 591 */           this.flashTimer = 1.0F;
/*     */         } else {
/* 593 */           this.flashTimer = 0.0F;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCounter(int counter) {
/* 600 */     this.counter = counter;
/*     */   }
/*     */   
/*     */   public void update() {
/* 604 */     updateFlash();
/*     */     
/* 606 */     if (!this.isDone) {
/*     */       
/* 608 */       if (this.isAnimating) {
/* 609 */         this.glowTimer -= Gdx.graphics.getDeltaTime();
/* 610 */         if (this.glowTimer < 0.0F) {
/* 611 */           this.glowTimer = 0.5F;
/* 612 */           AbstractDungeon.effectList.add(new GlowRelicParticle(this.img, this.currentX + this.f_effect.x, this.currentY + this.f_effect.y, this.rotation));
/*     */         } 
/*     */         
/* 615 */         this.f_effect.update();
/* 616 */         if (this.hb.hovered) {
/* 617 */           this.scale = Settings.scale * 1.5F;
/*     */         } else {
/* 619 */           this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale * 1.1F);
/*     */         }
/*     */       
/* 622 */       } else if (this.hb.hovered) {
/* 623 */         this.scale = Settings.scale * 1.25F;
/*     */       } else {
/* 625 */         this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 630 */       if (this.isObtained) {
/* 631 */         if (this.rotation != 0.0F) {
/* 632 */           this.rotation = MathUtils.lerp(this.rotation, 0.0F, Gdx.graphics.getDeltaTime() * 6.0F * 2.0F);
/*     */         }
/*     */         
/* 635 */         if (this.currentX != this.targetX) {
/* 636 */           this.currentX = MathUtils.lerp(this.currentX, this.targetX, Gdx.graphics.getDeltaTime() * 6.0F);
/* 637 */           if (Math.abs(this.currentX - this.targetX) < 0.5F) {
/* 638 */             this.currentX = this.targetX;
/*     */           }
/*     */         } 
/* 641 */         if (this.currentY != this.targetY) {
/* 642 */           this.currentY = MathUtils.lerp(this.currentY, this.targetY, Gdx.graphics.getDeltaTime() * 6.0F);
/* 643 */           if (Math.abs(this.currentY - this.targetY) < 0.5F) {
/* 644 */             this.currentY = this.targetY;
/*     */           }
/*     */         } 
/*     */         
/* 648 */         if (this.currentY == this.targetY && this.currentX == this.targetX) {
/* 649 */           this.isDone = true;
/* 650 */           onEquip();
/* 651 */           this.hb.move(this.currentX, this.currentY);
/*     */         } 
/* 653 */         this.scale = Settings.scale;
/*     */       } 
/*     */       
/* 656 */       if (this.hb != null) {
/* 657 */         this.hb.update();
/*     */         
/* 659 */         if (this.hb.hovered && (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NEOW_UNLOCK) {
/*     */ 
/*     */ 
/*     */           
/* 663 */           if (InputHelper.justClickedLeft && !this.isObtained) {
/* 664 */             InputHelper.justClickedLeft = false;
/* 665 */             this.hb.clickStarted = true;
/*     */           } 
/*     */           
/* 668 */           if ((this.hb.clicked || CInputActionSet.select.isJustPressed()) && !this.isObtained) {
/* 669 */             CInputActionSet.select.unpress();
/* 670 */             this.hb.clicked = false;
/*     */             
/* 672 */             if (!Settings.isTouchScreen) {
/* 673 */               bossObtainLogic();
/*     */             } else {
/* 675 */               AbstractDungeon.bossRelicScreen.confirmButton.show();
/* 676 */               AbstractDungeon.bossRelicScreen.confirmButton.isDisabled = false;
/* 677 */               AbstractDungeon.bossRelicScreen.touchBlight = this;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 682 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/* 683 */         updateAnimation();
/*     */       }
/*     */     } else {
/* 686 */       this.hb.update();
/*     */       
/* 688 */       if (this.hb.hovered && AbstractDungeon.topPanel.potionUi.isHidden) {
/* 689 */         this.scale = Settings.scale * 1.25F;
/*     */       } else {
/* 691 */         this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void bossObtainLogic() {
/* 697 */     if (AbstractDungeon.player.hasBlight(this.blightID)) {
/* 698 */       AbstractDungeon.player.getBlight(this.blightID).stack();
/* 699 */       this.isObtained = true;
/*     */     } else {
/* 701 */       obtain();
/* 702 */       InputHelper.justClickedLeft = false;
/* 703 */       this.isObtained = true;
/* 704 */       this.f_effect.x = 0.0F;
/* 705 */       this.f_effect.y = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayCard(AbstractCard card, AbstractMonster m) {}
/*     */ 
/*     */   
/*     */   public boolean canPlay(AbstractCard card) {
/* 715 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onVictory() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void atBattleStart() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void atTurnStart() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayerEndTurn() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBossDefeat() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCreateEnemy(AbstractMonster m) {}
/*     */ 
/*     */   
/*     */   public void effect() {}
/*     */ 
/*     */   
/*     */   public void onEquip() {}
/*     */ 
/*     */   
/*     */   public float effectFloat() {
/* 750 */     return this.floatModAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void incrementUp() {}
/*     */   
/*     */   public void setIncrement(int newInc) {
/* 757 */     this.increment = newInc;
/*     */   }
/*     */   
/*     */   public static String gameDataUploadHeader() {
/* 761 */     GameDataStringBuilder sb = new GameDataStringBuilder();
/* 762 */     sb.addFieldData("name");
/* 763 */     sb.addFieldData("text");
/* 764 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public String gameDataUploadData() {
/* 768 */     GameDataStringBuilder sb = new GameDataStringBuilder();
/* 769 */     sb.addFieldData(this.name);
/* 770 */     sb.addFieldData(this.description);
/* 771 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public void stack() {}
/*     */   
/*     */   public void updateDescription(AbstractPlayer.PlayerClass c) {}
/*     */   
/*     */   public void updateDescription() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\AbstractBlight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */