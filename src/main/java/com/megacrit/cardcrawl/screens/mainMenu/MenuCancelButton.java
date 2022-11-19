/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
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
/*     */ public class MenuCancelButton
/*     */ {
/*     */   private static final int W = 512;
/*     */   private static final int H = 256;
/*  19 */   private static final Color HOVER_BLEND_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.4F);
/*  20 */   private static final float SHOW_X = 256.0F * Settings.scale; private static final float DRAW_Y = 128.0F * Settings.scale;
/*  21 */   public static final float HIDE_X = SHOW_X - 400.0F * Settings.scale;
/*  22 */   public float current_x = HIDE_X;
/*  23 */   private float target_x = this.current_x;
/*     */   public boolean isHidden = true;
/*  25 */   private float glowAlpha = 0.0F;
/*  26 */   private Color glowColor = Settings.GOLD_COLOR.cpy();
/*     */ 
/*     */   
/*  29 */   private String buttonText = "NOT_SET";
/*  30 */   private static final float TEXT_OFFSET_X = -136.0F * Settings.scale;
/*  31 */   private static final float TEXT_OFFSET_Y = 57.0F * Settings.scale;
/*     */ 
/*     */   
/*  34 */   public Hitbox hb = new Hitbox(300.0F * Settings.scale, 100.0F * Settings.scale);
/*     */   
/*     */   public MenuCancelButton() {
/*  37 */     this.hb.move(SHOW_X - 106.0F * Settings.scale, DRAW_Y + 60.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  41 */     if (!this.isHidden) {
/*  42 */       updateGlow();
/*  43 */       this.hb.update();
/*  44 */       if (InputHelper.justClickedLeft && this.hb.hovered) {
/*  45 */         this.hb.clickStarted = true;
/*  46 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */       } 
/*  48 */       if (this.hb.justHovered) {
/*  49 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*     */       
/*  52 */       if (CInputActionSet.cancel.isJustPressed()) {
/*  53 */         this.hb.clicked = true;
/*     */       }
/*     */     } 
/*  56 */     if (this.current_x != this.target_x) {
/*  57 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/*  58 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/*  59 */         this.current_x = this.target_x;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateGlow() {
/*  65 */     this.glowAlpha += Gdx.graphics.getDeltaTime() * 3.0F;
/*  66 */     if (this.glowAlpha < 0.0F) {
/*  67 */       this.glowAlpha *= -1.0F;
/*     */     }
/*  69 */     float tmp = MathUtils.cos(this.glowAlpha);
/*  70 */     if (tmp < 0.0F) {
/*  71 */       this.glowColor.a = -tmp / 2.0F + 0.3F;
/*     */     } else {
/*  73 */       this.glowColor.a = tmp / 2.0F + 0.3F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hovered() {
/*  78 */     return this.hb.hovered;
/*     */   }
/*     */   
/*     */   public void hide() {
/*  82 */     if (!this.isHidden) {
/*  83 */       this.hb.clicked = false;
/*  84 */       this.hb.hovered = false;
/*  85 */       InputHelper.justClickedLeft = false;
/*  86 */       this.target_x = HIDE_X;
/*  87 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hideInstantly() {
/*  92 */     if (!this.isHidden) {
/*  93 */       this.hb.hovered = false;
/*  94 */       InputHelper.justClickedLeft = false;
/*  95 */       this.target_x = HIDE_X;
/*  96 */       this.current_x = this.target_x;
/*  97 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show(String buttonText) {
/* 102 */     if (this.isHidden) {
/* 103 */       this.glowAlpha = 0.0F;
/* 104 */       this.current_x = HIDE_X;
/* 105 */       this.target_x = SHOW_X;
/* 106 */       this.isHidden = false;
/* 107 */       this.buttonText = buttonText;
/*     */     } else {
/* 109 */       this.current_x = HIDE_X;
/* 110 */       this.buttonText = buttonText;
/*     */     } 
/* 112 */     this.hb.hovered = false;
/*     */   }
/*     */   
/*     */   public void showInstantly(String buttonText) {
/* 116 */     this.current_x = SHOW_X;
/* 117 */     this.target_x = SHOW_X;
/* 118 */     this.isHidden = false;
/* 119 */     this.buttonText = buttonText;
/* 120 */     this.hb.hovered = false;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 124 */     sb.setColor(Color.WHITE);
/* 125 */     renderShadow(sb);
/* 126 */     sb.setColor(this.glowColor);
/* 127 */     renderOutline(sb);
/* 128 */     sb.setColor(Color.WHITE);
/* 129 */     renderButton(sb);
/*     */     
/* 131 */     if (this.hb.hovered && !this.hb.clickStarted) {
/* 132 */       sb.setBlendFunction(770, 1);
/* 133 */       sb.setColor(HOVER_BLEND_COLOR);
/* 134 */       renderButton(sb);
/* 135 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 138 */     Color tmpColor = Settings.LIGHT_YELLOW_COLOR;
/* 139 */     if (this.hb.clickStarted) {
/* 140 */       tmpColor = Color.LIGHT_GRAY;
/*     */     }
/* 142 */     if (Settings.isControllerMode) {
/* 143 */       FontHelper.renderFontLeft(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X - 30.0F * Settings.scale, DRAW_Y + TEXT_OFFSET_Y, tmpColor);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 151 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, tmpColor);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     renderControllerUi(sb);
/*     */     
/* 162 */     if (!this.isHidden) {
/* 163 */       this.hb.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderShadow(SpriteBatch sb) {
/* 168 */     sb.draw(ImageMaster.CANCEL_BUTTON_SHADOW, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 188 */     sb.draw(ImageMaster.CANCEL_BUTTON_OUTLINE, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 208 */     sb.draw(ImageMaster.CANCEL_BUTTON, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/* 228 */     if (Settings.isControllerMode) {
/* 229 */       sb.setColor(Color.WHITE);
/* 230 */       sb.draw(CInputActionSet.cancel
/* 231 */           .getKeyImg(), this.current_x - 32.0F - 210.0F * Settings.scale, DRAW_Y - 32.0F + 57.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\MenuCancelButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */