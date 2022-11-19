/*     */ package com.megacrit.cardcrawl.screens.custom;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ShaderHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
/*     */ 
/*     */ 
/*     */ public class CustomModeCharacterButton
/*     */ {
/*     */   private CharacterStrings charStrings;
/*     */   private Texture buttonImg;
/*     */   public AbstractPlayer c;
/*     */   public boolean selected = false;
/*     */   public boolean locked = false;
/*     */   public Hitbox hb;
/*     */   private static final int W = 128;
/*     */   public float x;
/*     */   public float y;
/*  29 */   private Color highlightColor = new Color(1.0F, 0.8F, 0.2F, 0.0F);
/*  30 */   private float drawScale = 1.0F;
/*     */   
/*     */   public CustomModeCharacterButton(AbstractPlayer c, boolean locked) {
/*  33 */     this.buttonImg = c.getCustomModeCharacterButtonImage();
/*  34 */     this.charStrings = c.getCharacterString();
/*  35 */     this.hb = Settings.isMobile ? new Hitbox(120.0F * Settings.scale, 120.0F * Settings.scale) : new Hitbox(80.0F * Settings.scale, 80.0F * Settings.scale);
/*     */     
/*  37 */     this.drawScale = Settings.isMobile ? (Settings.scale * 1.2F) : Settings.scale;
/*     */     
/*  39 */     this.locked = locked;
/*  40 */     this.c = c;
/*     */   }
/*     */   
/*     */   public void move(float x, float y) {
/*  44 */     this.x = x;
/*  45 */     this.y = y;
/*  46 */     this.hb.move(x, y);
/*     */   }
/*     */   
/*     */   public void update(float x, float y) {
/*  50 */     this.x = x;
/*  51 */     this.y = y;
/*  52 */     this.hb.move(x, y);
/*  53 */     updateHitbox();
/*     */   }
/*     */   
/*     */   private void updateHitbox() {
/*  57 */     this.hb.update();
/*  58 */     if (this.hb.justHovered) {
/*  59 */       CardCrawlGame.sound.playA("UI_HOVER", -0.3F);
/*     */     }
/*     */     
/*  62 */     if (InputHelper.justClickedLeft && !this.locked && this.hb.hovered) {
/*  63 */       CardCrawlGame.sound.playA("UI_CLICK_1", -0.4F);
/*  64 */       this.hb.clickStarted = true;
/*     */     } 
/*     */     
/*  67 */     if (this.hb.clicked) {
/*  68 */       this.hb.clicked = false;
/*     */       
/*  70 */       if (!this.selected) {
/*  71 */         CardCrawlGame.mainMenuScreen.customModeScreen.deselectOtherOptions(this);
/*  72 */         this.selected = true;
/*  73 */         CardCrawlGame.chosenCharacter = this.c.chosenClass;
/*  74 */         CardCrawlGame.mainMenuScreen.customModeScreen.confirmButton.isDisabled = false;
/*  75 */         CardCrawlGame.mainMenuScreen.customModeScreen.confirmButton.show();
/*     */ 
/*     */         
/*  78 */         CardCrawlGame.sound.playA(this.c.getCustomModeCharacterButtonSoundKey(), MathUtils.random(-0.2F, 0.2F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  84 */     renderOptionButton(sb);
/*  85 */     if (this.hb.hovered) {
/*  86 */       TipHelper.renderGenericTip(InputHelper.mX + 180.0F * Settings.scale, this.hb.cY + 40.0F * Settings.scale, this.charStrings.NAMES[0], this.charStrings.TEXT[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   private void renderOptionButton(SpriteBatch sb) {
/*  96 */     if (this.selected) {
/*  97 */       this.highlightColor.a = 0.25F + (MathUtils.cosDeg((float)(System.currentTimeMillis() / 4L % 360L)) + 1.25F) / 3.5F;
/*  98 */       sb.setColor(this.highlightColor);
/*  99 */       sb.draw(ImageMaster.FILTER_GLOW_BG, this.hb.cX - 64.0F, this.hb.cY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.drawScale, this.drawScale, 0.0F, 0, 0, 128, 128, false, false);
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
/* 118 */     if (this.locked) {
/* 119 */       ShaderHelper.setShader(sb, ShaderHelper.Shader.GRAYSCALE);
/*     */     }
/* 121 */     else if (this.selected || this.hb.hovered) {
/* 122 */       sb.setColor(Color.WHITE);
/*     */     } else {
/* 124 */       sb.setColor(Color.LIGHT_GRAY);
/*     */     } 
/*     */ 
/*     */     
/* 128 */     sb.draw(this.buttonImg, this.hb.cX - 64.0F, this.y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.drawScale, this.drawScale, 0.0F, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     if (this.locked)
/* 147 */       ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\custom\CustomModeCharacterButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */