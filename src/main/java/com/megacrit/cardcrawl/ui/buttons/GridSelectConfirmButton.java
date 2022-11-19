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
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ 
/*     */ public class GridSelectConfirmButton
/*     */ {
/*     */   private static final int W = 512;
/*     */   private static final int H = 256;
/*  19 */   private static final Color HOVER_BLEND_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.3F);
/*  20 */   private static final Color TEXT_DISABLED_COLOR = new Color(0.6F, 0.6F, 0.6F, 1.0F);
/*  21 */   private static final float SHOW_X = Settings.WIDTH - 256.0F * Settings.scale, DRAW_Y = 128.0F * Settings.scale;
/*  22 */   private static final float HIDE_X = SHOW_X + 400.0F * Settings.scale;
/*  23 */   private float current_x = HIDE_X;
/*  24 */   private float target_x = this.current_x;
/*  25 */   private float controller_offset_x = 0.0F;
/*     */   
/*     */   private boolean isHidden = true;
/*  28 */   private float glowAlpha = 0.0F; public boolean isDisabled = true; public boolean isHovered = false;
/*  29 */   private Color glowColor = Color.WHITE.cpy();
/*     */ 
/*     */   
/*  32 */   private String buttonText = "NOT_SET";
/*  33 */   private static final float TEXT_OFFSET_X = 136.0F * Settings.scale;
/*  34 */   private static final float TEXT_OFFSET_Y = 57.0F * Settings.scale;
/*     */ 
/*     */   
/*  37 */   private static final float HITBOX_W = 300.0F * Settings.scale; private static final float HITBOX_H = 100.0F * Settings.scale;
/*  38 */   public Hitbox hb = new Hitbox(0.0F, 0.0F, HITBOX_W, HITBOX_H);
/*     */   
/*     */   public GridSelectConfirmButton(String label) {
/*  41 */     updateText(label);
/*  42 */     this.hb.move(SHOW_X + 106.0F * Settings.scale, DRAW_Y + 60.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void updateText(String label) {
/*  46 */     this.buttonText = label;
/*  47 */     this.controller_offset_x = FontHelper.getSmartWidth(FontHelper.buttonLabelFont, label, 99999.0F, 0.0F) / 2.0F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  51 */     if (!this.isHidden) {
/*  52 */       updateGlow();
/*  53 */       this.hb.update();
/*     */       
/*  55 */       if (InputHelper.justClickedLeft && this.hb.hovered && !this.isDisabled) {
/*  56 */         this.hb.clickStarted = true;
/*  57 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */       } 
/*  59 */       if (this.hb.justHovered && !this.isDisabled) {
/*  60 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*  62 */       this.isHovered = this.hb.hovered;
/*     */       
/*  64 */       if (CInputActionSet.proceed.isJustPressed()) {
/*  65 */         CInputActionSet.proceed.unpress();
/*  66 */         this.hb.clicked = true;
/*     */       } 
/*     */     } 
/*     */     
/*  70 */     if (this.current_x != this.target_x) {
/*  71 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/*  72 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/*  73 */         this.current_x = this.target_x;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateGlow() {
/*  79 */     this.glowAlpha += Gdx.graphics.getDeltaTime() * 3.0F;
/*  80 */     if (this.glowAlpha < 0.0F) {
/*  81 */       this.glowAlpha *= -1.0F;
/*     */     }
/*  83 */     float tmp = MathUtils.cos(this.glowAlpha);
/*  84 */     if (tmp < 0.0F) {
/*  85 */       this.glowColor.a = -tmp / 2.0F + 0.3F;
/*     */     } else {
/*  87 */       this.glowColor.a = tmp / 2.0F + 0.3F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hideInstantly() {
/*  92 */     this.current_x = HIDE_X;
/*  93 */     this.target_x = HIDE_X;
/*  94 */     this.isHidden = true;
/*     */   }
/*     */   
/*     */   public void hide() {
/*  98 */     if (!this.isHidden) {
/*  99 */       this.target_x = HIDE_X;
/* 100 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show() {
/* 105 */     if (this.isHidden) {
/* 106 */       this.glowAlpha = 0.0F;
/* 107 */       this.target_x = SHOW_X;
/* 108 */       this.isHidden = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 113 */     sb.setColor(Color.WHITE);
/* 114 */     renderShadow(sb);
/* 115 */     sb.setColor(this.glowColor);
/* 116 */     renderOutline(sb);
/* 117 */     sb.setColor(Color.WHITE);
/* 118 */     renderButton(sb);
/*     */     
/* 120 */     if (this.hb.hovered && !this.isDisabled && !this.hb.clickStarted) {
/* 121 */       sb.setBlendFunction(770, 1);
/* 122 */       sb.setColor(HOVER_BLEND_COLOR);
/* 123 */       renderButton(sb);
/* 124 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */ 
/*     */     
/* 128 */     if (this.isDisabled) {
/* 129 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, TEXT_DISABLED_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 136 */     else if (this.hb.clickStarted) {
/* 137 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, Color.LIGHT_GRAY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 144 */     else if (this.hb.hovered) {
/* 145 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, Settings.LIGHT_YELLOW_COLOR);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 153 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, Settings.LIGHT_YELLOW_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     renderControllerUi(sb);
/*     */     
/* 164 */     if (!this.isHidden) {
/* 165 */       this.hb.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderShadow(SpriteBatch sb) {
/* 170 */     sb.draw(ImageMaster.CONFIRM_BUTTON_SHADOW, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/*     */   private void renderOutline(SpriteBatch sb) {
/* 190 */     sb.draw(ImageMaster.CONFIRM_BUTTON_OUTLINE, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/*     */   private void renderButton(SpriteBatch sb) {
/* 210 */     sb.draw(ImageMaster.CONFIRM_BUTTON, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/*     */   private void renderControllerUi(SpriteBatch sb) {
/* 230 */     if (Settings.isControllerMode) {
/* 231 */       sb.setColor(Color.WHITE);
/* 232 */       sb.draw(CInputActionSet.proceed
/* 233 */           .getKeyImg(), this.current_x - 32.0F - this.controller_offset_x + 96.0F * Settings.scale, DRAW_Y - 32.0F + 57.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\GridSelectConfirmButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */