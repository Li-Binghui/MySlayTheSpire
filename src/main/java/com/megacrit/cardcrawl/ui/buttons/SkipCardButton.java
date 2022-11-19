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
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ 
/*     */ public class SkipCardButton
/*     */ {
/*  21 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CardRewardScreen");
/*  22 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final int W = 512;
/*     */   private static final int H = 256;
/*  26 */   public static final float TAKE_Y = Settings.HEIGHT / 2.0F - 340.0F * Settings.scale;
/*  27 */   private static final float SHOW_X = Settings.WIDTH / 2.0F;
/*  28 */   private static final float HIDE_X = Settings.WIDTH / 2.0F;
/*  29 */   private float current_x = HIDE_X;
/*  30 */   private float target_x = this.current_x;
/*     */   private boolean isHidden = true;
/*  32 */   private Color textColor = Color.WHITE.cpy();
/*  33 */   private Color btnColor = Color.WHITE.cpy();
/*     */ 
/*     */   
/*     */   public boolean screenDisabled = false;
/*     */ 
/*     */   
/*  39 */   private static final float HITBOX_W = 260.0F * Settings.scale; private static final float HITBOX_H = 80.0F * Settings.scale;
/*  40 */   public Hitbox hb = new Hitbox(0.0F, 0.0F, HITBOX_W, HITBOX_H);
/*     */   
/*  42 */   private float controllerImgTextWidth = 0.0F;
/*     */   
/*     */   public SkipCardButton() {
/*  45 */     this.hb.move(Settings.WIDTH / 2.0F, TAKE_Y);
/*     */   }
/*     */   
/*     */   public void update() {
/*  49 */     if (this.isHidden) {
/*     */       return;
/*     */     }
/*     */     
/*  53 */     this.hb.update();
/*     */     
/*  55 */     if (this.hb.justHovered) {
/*  56 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/*  59 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/*  60 */       this.hb.clickStarted = true;
/*  61 */       CardCrawlGame.sound.play("UI_CLICK_1");
/*     */     } 
/*     */     
/*  64 */     if ((this.hb.clicked || InputActionSet.cancel.isJustPressed() || CInputActionSet.cancel.isJustPressed()) && !this.screenDisabled) {
/*     */       
/*  66 */       this.hb.clicked = false;
/*  67 */       AbstractDungeon.closeCurrentScreen();
/*     */     } 
/*     */     
/*  70 */     this.screenDisabled = false;
/*     */     
/*  72 */     if (this.current_x != this.target_x) {
/*  73 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/*  74 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/*  75 */         this.current_x = this.target_x;
/*  76 */         this.hb.move(this.current_x, TAKE_Y);
/*     */       } 
/*     */     } 
/*     */     
/*  80 */     this.textColor.a = MathHelper.fadeLerpSnap(this.textColor.a, 1.0F);
/*  81 */     this.btnColor.a = this.textColor.a;
/*     */   }
/*     */   
/*     */   public void hideInstantly() {
/*  85 */     this.current_x = HIDE_X;
/*  86 */     this.target_x = HIDE_X;
/*  87 */     this.isHidden = true;
/*  88 */     this.textColor.a = 0.0F;
/*  89 */     this.btnColor.a = 0.0F;
/*     */   }
/*     */   
/*     */   public void hide() {
/*  93 */     this.isHidden = true;
/*     */   }
/*     */   
/*     */   public void show() {
/*  97 */     this.isHidden = false;
/*  98 */     this.textColor.a = 0.0F;
/*  99 */     this.btnColor.a = 0.0F;
/* 100 */     this.current_x = HIDE_X;
/* 101 */     this.target_x = SHOW_X;
/* 102 */     this.hb.move(SHOW_X, TAKE_Y);
/*     */   }
/*     */   
/*     */   public void show(boolean singingBowl) {
/* 106 */     this.isHidden = false;
/* 107 */     this.textColor.a = 0.0F;
/* 108 */     this.btnColor.a = 0.0F;
/* 109 */     this.current_x = HIDE_X;
/* 110 */     this.target_x = SHOW_X - 165.0F * Settings.scale;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 114 */     if (this.isHidden) {
/*     */       return;
/*     */     }
/*     */     
/* 118 */     renderButton(sb);
/* 119 */     if (FontHelper.getSmartWidth(FontHelper.buttonLabelFont, TEXT[0], 9999.0F, 0.0F) > 200.0F * Settings.scale) {
/* 120 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[0], this.current_x, TAKE_Y, this.textColor, 0.8F);
/*     */     } else {
/* 122 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[0], this.current_x, TAKE_Y, this.textColor);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderButton(SpriteBatch sb) {
/* 127 */     sb.setColor(this.btnColor);
/* 128 */     sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.current_x - 256.0F, TAKE_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 146 */     if (this.hb.hovered && !this.hb.clickStarted) {
/* 147 */       sb.setBlendFunction(770, 1);
/* 148 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.3F));
/* 149 */       sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, this.current_x - 256.0F, TAKE_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 166 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 169 */     if (Settings.isControllerMode) {
/*     */       
/* 171 */       if (this.controllerImgTextWidth == 0.0F) {
/* 172 */         this.controllerImgTextWidth = FontHelper.getSmartWidth(FontHelper.buttonLabelFont, TEXT[0], 99999.0F, 0.0F) / 2.0F;
/*     */       }
/*     */       
/* 175 */       sb.setColor(Color.WHITE);
/* 176 */       sb.draw(CInputActionSet.cancel
/* 177 */           .getKeyImg(), this.current_x - 32.0F - this.controllerImgTextWidth - 38.0F * Settings.scale, TAKE_Y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\SkipCardButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */