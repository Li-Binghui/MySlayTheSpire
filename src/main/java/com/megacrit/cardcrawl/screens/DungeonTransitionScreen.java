/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.options.ConfirmPopup;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DungeonTransitionScreen
/*     */ {
/*  22 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DungeonTransitionScreen");
/*  23 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public boolean isComplete = false;
/*     */   
/*     */   public boolean msgCreated = false;
/*     */   
/*     */   public boolean isFading = false;
/*     */   public float timer;
/*  31 */   private ConfirmPopup popup = null;
/*     */   
/*     */   public String name;
/*     */   public String levelNum;
/*     */   public String levelName;
/*     */   private String source;
/*     */   private boolean playSFX = false;
/*  38 */   private Color color = Settings.CREAM_COLOR.cpy(); private Color lvlColor = Settings.BLUE_TEXT_COLOR.cpy(); private float oscillateTimer;
/*  39 */   private float animTimer = 0.0F; private float continueFader;
/*  40 */   private Color continueColor = Settings.GOLD_COLOR.cpy();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DungeonTransitionScreen(String key) {
/*  51 */     if (!((Boolean)TipTracker.tips.get("NO_FTUE")).booleanValue()) {
/*  52 */       this.popup = new ConfirmPopup(TEXT[0], TEXT[1], ConfirmPopup.ConfirmType.SKIP_FTUE);
/*  53 */       this.popup.show();
/*     */     } 
/*     */     
/*  56 */     this.source = "";
/*  57 */     this.name = "";
/*     */ 
/*     */     
/*  60 */     this.timer = 2.0F;
/*  61 */     this.continueFader = 0.0F;
/*  62 */     this.oscillateTimer = 0.0F;
/*  63 */     this.continueColor.a = 0.0F;
/*  64 */     this.lvlColor.a = 0.0F;
/*  65 */     this.color.a = 0.0F;
/*  66 */     setAreaName(key);
/*  67 */     this.isComplete = true;
/*     */   }
/*     */   
/*     */   private void setAreaName(String key) {
/*  71 */     switch (key) {
/*     */       case "Exordium":
/*  73 */         this.levelNum = TEXT[2];
/*  74 */         this.levelName = TEXT[3];
/*     */         break;
/*     */       case "TheCity":
/*  77 */         this.levelNum = TEXT[4];
/*  78 */         this.levelName = TEXT[5];
/*     */         break;
/*     */       case "TheBeyond":
/*  81 */         this.levelNum = TEXT[6];
/*  82 */         this.levelName = TEXT[7];
/*     */         break;
/*     */       case "TheEnding":
/*  85 */         this.levelNum = TEXT[8];
/*  86 */         this.levelName = TEXT[9];
/*     */         break;
/*     */       default:
/*  89 */         this.levelNum = TEXT[8];
/*  90 */         this.levelName = TEXT[9];
/*     */         break;
/*     */     } 
/*     */     
/*  94 */     AbstractDungeon.name = this.levelName;
/*  95 */     AbstractDungeon.levelNum = this.levelNum;
/*     */   }
/*     */   
/*     */   private void oscillateColor() {
/*  99 */     this.oscillateTimer += Gdx.graphics.getDeltaTime() * 5.0F;
/* 100 */     this.continueColor.a = 0.33F + (MathUtils.cos(this.oscillateTimer) + 1.0F) / 3.0F;
/*     */     
/* 102 */     if (!this.isFading) {
/* 103 */       if (this.continueFader != 1.0F) {
/* 104 */         this.continueFader += Gdx.graphics.getDeltaTime() / 2.0F;
/* 105 */         if (this.continueFader > 1.0F) {
/* 106 */           this.continueFader = 1.0F;
/*     */         }
/*     */       }
/*     */     
/* 110 */     } else if (this.continueFader != 0.0F) {
/* 111 */       this.continueFader -= Gdx.graphics.getDeltaTime();
/* 112 */       if (this.continueFader < 0.0F) {
/* 113 */         this.continueFader = 0.0F;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 118 */     this.continueColor.a *= this.continueFader;
/*     */   }
/*     */   
/*     */   public void update() {
/* 122 */     if (this.popup != null && 
/* 123 */       this.popup.shown) {
/* 124 */       this.popup.update();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 129 */     if (this.msgCreated) {
/* 130 */       oscillateColor();
/*     */     }
/*     */     
/* 133 */     if (Settings.isDebug || InputHelper.justClickedLeft) {
/* 134 */       InputHelper.justClickedLeft = false;
/* 135 */       this.isComplete = true;
/*     */     } 
/*     */     
/* 138 */     if (this.isFading) {
/* 139 */       this.timer -= Gdx.graphics.getDeltaTime();
/* 140 */       if (this.timer < 0.0F) {
/* 141 */         this.isComplete = true;
/*     */       } else {
/* 143 */         this.color.a = this.timer;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 148 */     if (this.animTimer > 0.5F && !this.playSFX) {
/* 149 */       this.playSFX = true;
/* 150 */       CardCrawlGame.sound.play("DUNGEON_TRANSITION");
/*     */     } 
/*     */     
/* 153 */     if (!this.msgCreated) {
/* 154 */       this.animTimer += Gdx.graphics.getDeltaTime();
/* 155 */       if (this.animTimer > 4.0F) {
/* 156 */         this.msgCreated = true;
/* 157 */         this.animTimer = 4.0F;
/*     */       } 
/*     */       
/* 160 */       if (this.animTimer > 2.0F) {
/* 161 */         this.color.a = 1.0F;
/*     */       } else {
/* 163 */         this.color.a = this.animTimer / 2.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 169 */     this.lvlColor.a = this.color.a;
/* 170 */     FontHelper.renderFontCentered(sb, FontHelper.tipBodyFont, this.levelNum, Settings.WIDTH / 2.0F - 44.0F * Settings.scale, Settings.HEIGHT * 0.54F, this.lvlColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     FontHelper.renderFontCentered(sb, FontHelper.dungeonTitleFont, this.levelName, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, this.color);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 186 */     FontHelper.renderFontCentered(sb, FontHelper.tipBodyFont, "\"" + this.source + "\"", Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.44F, this.color);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     FontHelper.renderFontCenteredWidth(sb, FontHelper.tipBodyFont, TEXT[10], Settings.WIDTH / 2.0F, 100.0F * Settings.scale, this.continueColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     if (this.popup != null && this.popup.shown)
/* 203 */       this.popup.render(sb); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\DungeonTransitionScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */