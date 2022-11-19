/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ 
/*     */ public class ConfirmButton
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
/*  37 */   public Hitbox hb = new Hitbox(0.0F, 0.0F, 300.0F * Settings.scale, 100.0F * Settings.scale);
/*     */   
/*     */   public ConfirmButton() {
/*  40 */     updateText(CardSelectConfirmButton.TEXT[0]);
/*  41 */     this.hb.move(SHOW_X + 106.0F * Settings.scale, DRAW_Y + 60.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public ConfirmButton(String label) {
/*  45 */     updateText(label);
/*  46 */     this.hb.move(SHOW_X + 106.0F * Settings.scale, DRAW_Y + 60.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void updateText(String label) {
/*  50 */     this.buttonText = label;
/*  51 */     this.controller_offset_x = FontHelper.getSmartWidth(FontHelper.buttonLabelFont, label, 99999.0F, 0.0F) / 2.0F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  55 */     if (!this.isHidden) {
/*  56 */       updateGlow();
/*  57 */       this.hb.update();
/*     */       
/*  59 */       if (InputHelper.justClickedLeft && this.hb.hovered && !this.isDisabled) {
/*  60 */         this.hb.clickStarted = true;
/*  61 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */       } 
/*  63 */       if (this.hb.justHovered && !this.isDisabled) {
/*  64 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*  66 */       this.isHovered = this.hb.hovered;
/*     */       
/*  68 */       if (CInputActionSet.select.isJustPressed()) {
/*  69 */         CInputActionSet.select.unpress();
/*  70 */         this.hb.clicked = true;
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     if (this.current_x != this.target_x) {
/*  75 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/*  76 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/*  77 */         this.current_x = this.target_x;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateGlow() {
/*  83 */     this.glowAlpha += Gdx.graphics.getDeltaTime() * 3.0F;
/*  84 */     if (this.glowAlpha < 0.0F) {
/*  85 */       this.glowAlpha *= -1.0F;
/*     */     }
/*  87 */     float tmp = MathUtils.cos(this.glowAlpha);
/*  88 */     if (tmp < 0.0F) {
/*  89 */       this.glowColor.a = -tmp / 2.0F + 0.3F;
/*     */     } else {
/*  91 */       this.glowColor.a = tmp / 2.0F + 0.3F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hideInstantly() {
/*  96 */     this.current_x = HIDE_X;
/*  97 */     this.target_x = HIDE_X;
/*  98 */     this.isHidden = true;
/*     */   }
/*     */   
/*     */   public void hide() {
/* 102 */     if (!this.isHidden) {
/* 103 */       this.target_x = HIDE_X;
/* 104 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show() {
/* 109 */     if (this.isHidden) {
/* 110 */       this.glowAlpha = 0.0F;
/* 111 */       this.target_x = SHOW_X;
/* 112 */       this.isHidden = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 117 */     sb.setColor(Color.WHITE);
/* 118 */     renderShadow(sb);
/* 119 */     sb.setColor(this.glowColor);
/* 120 */     renderOutline(sb);
/* 121 */     sb.setColor(Color.WHITE);
/* 122 */     renderButton(sb);
/*     */     
/* 124 */     if (this.hb.hovered && !this.isDisabled && !this.hb.clickStarted) {
/* 125 */       sb.setBlendFunction(770, 1);
/* 126 */       sb.setColor(HOVER_BLEND_COLOR);
/* 127 */       renderButton(sb);
/* 128 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */ 
/*     */     
/* 132 */     if (this.isDisabled) {
/* 133 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, TEXT_DISABLED_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 140 */     else if (this.hb.clickStarted) {
/* 141 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, Color.LIGHT_GRAY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 148 */     else if (this.hb.hovered) {
/* 149 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, Settings.LIGHT_YELLOW_COLOR);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 157 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, Settings.LIGHT_YELLOW_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     renderControllerUi(sb);
/*     */     
/* 168 */     if (!this.isHidden) {
/* 169 */       this.hb.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderShadow(SpriteBatch sb) {
/* 174 */     sb.draw(ImageMaster.CONFIRM_BUTTON_SHADOW, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 194 */     sb.draw(ImageMaster.CONFIRM_BUTTON_OUTLINE, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 214 */     sb.draw(ImageMaster.CONFIRM_BUTTON, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 234 */     if (Settings.isControllerMode) {
/* 235 */       sb.setColor(Color.WHITE);
/* 236 */       sb.draw(CInputActionSet.select
/* 237 */           .getKeyImg(), this.current_x - 32.0F - this.controller_offset_x + 96.0F * Settings.scale, DRAW_Y - 32.0F + 57.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\ConfirmButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */