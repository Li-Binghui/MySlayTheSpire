/*     */ package com.megacrit.cardcrawl.ui.buttons;
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
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
/*     */ 
/*     */ 
/*     */ public class SingingBowlButton
/*     */ {
/*  22 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CardRewardScreen");
/*  23 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final int W = 512;
/*     */   private static final int H = 256;
/*  27 */   private static final float SHOW_X = Settings.WIDTH / 2.0F + 165.0F * Settings.scale;
/*  28 */   private static final float HIDE_X = Settings.WIDTH / 2.0F;
/*  29 */   private float current_x = HIDE_X;
/*  30 */   private float target_x = this.current_x;
/*  31 */   private Color textColor = Color.WHITE.cpy();
/*  32 */   private Color btnColor = Color.WHITE.cpy();
/*     */   private boolean isHidden = true;
/*  34 */   private RewardItem rItem = null;
/*     */   
/*  36 */   private float controllerImgTextWidth = 0.0F;
/*     */ 
/*     */   
/*  39 */   private static final float HITBOX_W = 260.0F * Settings.scale; private static final float HITBOX_H = 80.0F * Settings.scale;
/*  40 */   public Hitbox hb = new Hitbox(0.0F, 0.0F, HITBOX_W, HITBOX_H);
/*     */   
/*     */   public SingingBowlButton() {
/*  43 */     this.hb.move(Settings.WIDTH / 2.0F, SkipCardButton.TAKE_Y);
/*     */   }
/*     */   
/*     */   public void update() {
/*  47 */     if (this.isHidden) {
/*     */       return;
/*     */     }
/*     */     
/*  51 */     this.hb.update();
/*     */     
/*  53 */     if (this.hb.justHovered) {
/*  54 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/*  57 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/*  58 */       this.hb.clickStarted = true;
/*  59 */       CardCrawlGame.sound.play("UI_CLICK_1");
/*     */     } 
/*     */     
/*  62 */     if (this.hb.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
/*  63 */       CInputActionSet.proceed.unpress();
/*  64 */       this.hb.clicked = false;
/*  65 */       onClick();
/*  66 */       AbstractDungeon.cardRewardScreen.closeFromBowlButton();
/*  67 */       AbstractDungeon.closeCurrentScreen();
/*  68 */       hide();
/*     */     } 
/*     */     
/*  71 */     if (this.current_x != this.target_x) {
/*  72 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/*  73 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/*  74 */         this.current_x = this.target_x;
/*  75 */         this.hb.move(this.current_x, SkipCardButton.TAKE_Y);
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     this.textColor.a = MathHelper.fadeLerpSnap(this.textColor.a, 1.0F);
/*  80 */     this.btnColor.a = this.textColor.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick() {
/*  87 */     AbstractDungeon.player.getRelic("Singing Bowl").flash();
/*  88 */     CardCrawlGame.sound.playA("SINGING_BOWL", MathUtils.random(-0.2F, 0.1F));
/*  89 */     AbstractDungeon.player.increaseMaxHp(2, true);
/*  90 */     AbstractDungeon.combatRewardScreen.rewards.remove(this.rItem);
/*     */   }
/*     */   
/*     */   public void hide() {
/*  94 */     if (!this.isHidden) {
/*  95 */       this.isHidden = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void show(RewardItem rItem) {
/* 100 */     this.isHidden = false;
/* 101 */     this.textColor.a = 0.0F;
/* 102 */     this.btnColor.a = 0.0F;
/* 103 */     this.current_x = HIDE_X;
/* 104 */     this.target_x = SHOW_X;
/* 105 */     this.rItem = rItem;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 109 */     if (this.isHidden) {
/*     */       return;
/*     */     }
/*     */     
/* 113 */     renderButton(sb);
/* 114 */     FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[2], this.current_x, SkipCardButton.TAKE_Y, this.textColor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHidden() {
/* 124 */     return this.isHidden;
/*     */   }
/*     */   
/*     */   private void renderButton(SpriteBatch sb) {
/* 128 */     sb.setColor(this.btnColor);
/* 129 */     sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.current_x - 256.0F, SkipCardButton.TAKE_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 147 */     if (this.hb.hovered && !this.hb.clickStarted) {
/* 148 */       sb.setBlendFunction(770, 1);
/* 149 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.3F));
/* 150 */       sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.current_x - 256.0F, SkipCardButton.TAKE_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 167 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 170 */     if (Settings.isControllerMode) {
/* 171 */       if (this.controllerImgTextWidth == 0.0F) {
/* 172 */         this.controllerImgTextWidth = FontHelper.getSmartWidth(FontHelper.buttonLabelFont, TEXT[2], 9999.0F, 0.0F);
/*     */       }
/*     */       
/* 175 */       sb.setColor(Color.WHITE);
/* 176 */       sb.draw(CInputActionSet.pageRightViewExhaust
/* 177 */           .getKeyImg(), this.current_x - 32.0F - this.controllerImgTextWidth / 2.0F - 38.0F * Settings.scale, SkipCardButton.TAKE_Y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 195 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\SingingBowlButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */