/*     */ package com.megacrit.cardcrawl.ui;
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
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;
/*     */ 
/*     */ public class MultiPageFtue
/*     */   extends FtueTip {
/*  20 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Main Tutorial");
/*     */   
/*  22 */   public static final String[] MSG = tutorialStrings.TEXT;
/*  23 */   public static final String[] LABEL = tutorialStrings.LABEL; private static final int W = 760; private static final int H = 580;
/*     */   private Texture img1;
/*     */   private Texture img2;
/*     */   private Texture img3;
/*  27 */   private Color screen = Color.valueOf("1c262a00"); private float x; private float x1;
/*     */   private float x2;
/*  29 */   private float scrollTimer = 0.0F; private float x3; private float targetX; private float startX;
/*     */   private static final float SCROLL_TIME = 0.3F;
/*  31 */   private int currentSlot = 0;
/*  32 */   private static final String msg1 = MSG[0];
/*  33 */   private static final String msg2 = MSG[1];
/*  34 */   private static final String msg3 = MSG[2];
/*     */ 
/*     */   
/*     */   public MultiPageFtue() {
/*  38 */     this.img1 = ImageMaster.loadImage("images/ui/tip/t1.png");
/*  39 */     this.img2 = ImageMaster.loadImage("images/ui/tip/t2.png");
/*  40 */     this.img3 = ImageMaster.loadImage("images/ui/tip/t3.png");
/*  41 */     AbstractDungeon.player.releaseCard();
/*  42 */     if (AbstractDungeon.isScreenUp) {
/*  43 */       AbstractDungeon.dynamicBanner.hide();
/*  44 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*     */     } 
/*  46 */     AbstractDungeon.isScreenUp = true;
/*  47 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.FTUE;
/*  48 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  49 */     this.x = 0.0F;
/*  50 */     this.x1 = 567.0F * Settings.scale;
/*  51 */     this.x2 = this.x1 + Settings.WIDTH;
/*  52 */     this.x3 = this.x2 + Settings.WIDTH;
/*  53 */     AbstractDungeon.overlayMenu.proceedButton.show();
/*  54 */     AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  59 */     if (this.screen.a != 0.8F) {
/*  60 */       this.screen.a += Gdx.graphics.getDeltaTime();
/*  61 */       if (this.screen.a > 0.8F) {
/*  62 */         this.screen.a = 0.8F;
/*     */       }
/*     */     } 
/*     */     
/*  66 */     if ((AbstractDungeon.overlayMenu.proceedButton.isHovered && InputHelper.justClickedLeft) || CInputActionSet.proceed
/*  67 */       .isJustPressed()) {
/*  68 */       CInputActionSet.proceed.unpress();
/*  69 */       if (this.currentSlot == -2) {
/*  70 */         CardCrawlGame.sound.play("DECK_CLOSE");
/*  71 */         AbstractDungeon.closeCurrentScreen();
/*  72 */         AbstractDungeon.overlayMenu.proceedButton.hide();
/*  73 */         AbstractDungeon.effectList.clear();
/*  74 */         AbstractDungeon.topLevelEffects.add(new BattleStartEffect(false));
/*     */         return;
/*     */       } 
/*  77 */       AbstractDungeon.overlayMenu.proceedButton.hideInstantly();
/*  78 */       AbstractDungeon.overlayMenu.proceedButton.show();
/*  79 */       CardCrawlGame.sound.play("DECK_CLOSE");
/*  80 */       this.currentSlot--;
/*  81 */       this.startX = this.x;
/*  82 */       this.targetX = (this.currentSlot * Settings.WIDTH);
/*  83 */       this.scrollTimer = 0.3F;
/*     */       
/*  85 */       if (this.currentSlot == -2) {
/*  86 */         AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[1]);
/*     */       }
/*     */     } 
/*     */     
/*  90 */     if (this.scrollTimer != 0.0F) {
/*  91 */       this.scrollTimer -= Gdx.graphics.getDeltaTime();
/*  92 */       if (this.scrollTimer < 0.0F) {
/*  93 */         this.scrollTimer = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/*  97 */     this.x = Interpolation.fade.apply(this.targetX, this.startX, this.scrollTimer / 0.3F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 104 */     sb.setColor(this.screen);
/* 105 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 107 */     sb.setColor(Color.WHITE);
/* 108 */     sb.draw(this.img1, this.x + this.x1 - 380.0F, Settings.HEIGHT / 2.0F - 290.0F, 380.0F, 290.0F, 760.0F, 580.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 760, 580, false, false);
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
/* 125 */     sb.draw(this.img2, this.x + this.x2 - 380.0F, Settings.HEIGHT / 2.0F - 290.0F, 380.0F, 290.0F, 760.0F, 580.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 760, 580, false, false);
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
/* 142 */     sb.draw(this.img3, this.x + this.x3 - 380.0F, Settings.HEIGHT / 2.0F - 290.0F, 380.0F, 290.0F, 760.0F, 580.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 760, 580, false, false);
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
/* 160 */     float offsetY = 0.0F;
/* 161 */     if (Settings.BIG_TEXT_MODE) {
/* 162 */       offsetY = 110.0F * Settings.scale;
/*     */     }
/*     */ 
/*     */     
/* 166 */     FontHelper.renderSmartText(sb, FontHelper.panelNameFont, msg1, this.x + this.x1 + 400.0F * Settings.scale, Settings.HEIGHT / 2.0F - 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 171 */         FontHelper.getSmartHeight(FontHelper.panelNameFont, msg1, 700.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F + offsetY, 700.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     FontHelper.renderSmartText(sb, FontHelper.panelNameFont, msg2, this.x + this.x2 + 400.0F * Settings.scale, Settings.HEIGHT / 2.0F - 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 186 */         FontHelper.getSmartHeight(FontHelper.panelNameFont, msg2, 700.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F + offsetY, 700.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     FontHelper.renderSmartText(sb, FontHelper.panelNameFont, msg3, this.x + this.x3 + 400.0F * Settings.scale, Settings.HEIGHT / 2.0F - 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 201 */         FontHelper.getSmartHeight(FontHelper.panelNameFont, msg3, 700.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F + offsetY, 700.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     FontHelper.renderFontCenteredWidth(sb, FontHelper.panelNameFont, LABEL[2], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 360.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     FontHelper.renderFontCenteredWidth(sb, FontHelper.tipBodyFont, LABEL[3] + 
/*     */ 
/*     */         
/* 220 */         Integer.toString(Math.abs(this.currentSlot - 1)) + LABEL[4], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 400.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     AbstractDungeon.overlayMenu.proceedButton.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\MultiPageFtue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */