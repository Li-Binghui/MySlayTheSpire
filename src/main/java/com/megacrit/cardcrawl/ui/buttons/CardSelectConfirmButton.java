/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ 
/*     */ public class CardSelectConfirmButton
/*     */ {
/*  19 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Confirm Button");
/*  20 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final int W = 512;
/*     */   private static final int H = 256;
/*  24 */   private static final float TAKE_Y = 475.0F * Settings.scale;
/*  25 */   private static final float SHOW_X = Settings.WIDTH - 256.0F * Settings.scale;
/*  26 */   private static final float HIDE_X = SHOW_X + 400.0F * Settings.scale;
/*  27 */   private float current_x = HIDE_X;
/*  28 */   private float target_x = this.current_x;
/*     */   
/*     */   private boolean isHidden = true;
/*     */   public boolean isDisabled = true;
/*  32 */   private Color textColor = Color.WHITE.cpy();
/*  33 */   private Color btnColor = Color.WHITE.cpy();
/*  34 */   private float target_a = 0.0F;
/*     */ 
/*     */   
/*  37 */   private String buttonText = "NOT_SET";
/*     */ 
/*     */   
/*  40 */   private static final float HITBOX_W = 260.0F * Settings.scale; private static final float HITBOX_H = 80.0F * Settings.scale;
/*  41 */   public Hitbox hb = new Hitbox(0.0F, 0.0F, HITBOX_W, HITBOX_H);
/*     */   
/*     */   public CardSelectConfirmButton() {
/*  44 */     this.buttonText = TEXT[0];
/*  45 */     this.hb.move(Settings.WIDTH / 2.0F, TAKE_Y);
/*     */   }
/*     */   
/*     */   public void update() {
/*  49 */     if (!this.isHidden) {
/*  50 */       this.hb.update();
/*     */     }
/*     */     
/*  53 */     if (!this.isDisabled) {
/*  54 */       if (this.hb.justHovered) {
/*  55 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*  57 */       if (this.hb.hovered && InputHelper.justClickedLeft) {
/*  58 */         this.hb.clickStarted = true;
/*  59 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     if (this.current_x != this.target_x) {
/*  64 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/*  65 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/*  66 */         this.current_x = this.target_x;
/*     */       }
/*     */     } 
/*     */     
/*  70 */     this.textColor.a = MathHelper.fadeLerpSnap(this.textColor.a, this.target_a);
/*  71 */     this.btnColor.a = this.textColor.a;
/*     */   }
/*     */   
/*     */   public void hideInstantly() {
/*  75 */     this.current_x = HIDE_X;
/*  76 */     this.target_x = HIDE_X;
/*  77 */     this.isHidden = true;
/*  78 */     this.target_a = 0.0F;
/*  79 */     this.textColor.a = 0.0F;
/*     */   }
/*     */   
/*     */   public void hide() {
/*  83 */     if (!this.isHidden) {
/*  84 */       this.target_a = 0.0F;
/*  85 */       this.target_x = HIDE_X;
/*  86 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show() {
/*  91 */     if (this.isHidden) {
/*  92 */       this.textColor.a = 0.0F;
/*  93 */       this.target_a = 1.0F;
/*  94 */       this.target_x = SHOW_X;
/*  95 */       this.isHidden = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void disable() {
/* 100 */     if (!this.isDisabled) {
/* 101 */       this.hb.hovered = false;
/* 102 */       this.isDisabled = true;
/* 103 */       this.btnColor = Color.GRAY.cpy();
/* 104 */       this.textColor = Color.LIGHT_GRAY.cpy();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void enable() {
/* 109 */     if (this.isDisabled) {
/* 110 */       this.isDisabled = false;
/* 111 */       this.btnColor = Color.WHITE.cpy();
/* 112 */       this.textColor = Settings.CREAM_COLOR.cpy();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 117 */     sb.setColor(this.btnColor);
/*     */     
/* 119 */     renderButton(sb);
/*     */     
/* 121 */     if (this.hb.hovered && !this.isDisabled && !this.hb.clickStarted) {
/* 122 */       sb.setBlendFunction(770, 1);
/* 123 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.3F));
/* 124 */       renderButton(sb);
/* 125 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 128 */     if (!this.isHidden) {
/* 129 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, Settings.WIDTH / 2.0F, TAKE_Y, this.textColor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderButton(SpriteBatch sb) {
/* 140 */     if (!this.isHidden) {
/* 141 */       if (this.isDisabled) {
/* 142 */         sb.draw(ImageMaster.REWARD_SCREEN_TAKE_USED_BUTTON, Settings.WIDTH / 2.0F - 256.0F, TAKE_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 160 */         if (this.hb.clickStarted) {
/* 161 */           sb.setColor(Color.LIGHT_GRAY);
/*     */         }
/* 163 */         sb.draw(ImageMaster.REWARD_SCREEN_TAKE_BUTTON, Settings.WIDTH / 2.0F - 256.0F, TAKE_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/*     */       
/* 182 */       if (Settings.isControllerMode) {
/* 183 */         sb.draw(CInputActionSet.proceed
/* 184 */             .getKeyImg(), this.hb.cX - 32.0F - 100.0F * Settings.scale, this.hb.cY - 32.0F + 2.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 202 */       this.hb.render(sb);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\CardSelectConfirmButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */