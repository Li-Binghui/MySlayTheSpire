/*     */ package com.megacrit.cardcrawl.screens.custom;
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.RunModStrings;

import java.util.HashSet;
/*     */ 
/*     */ public class CustomMod {
/*     */   public String ID;
/*     */   public String name;
/*     */   public String description;
/*     */   public String color;
/*     */   private String label;
/*     */   public boolean isDailyMod;
/*     */   public boolean selected = false;
/*     */   public Hitbox hb;
/*  22 */   private static float offset_x = 0.0F; private static float text_max_width;
/*  23 */   private static final float OFFSET_Y = 130.0F * Settings.scale; private static float line_spacing;
/*     */   public float height;
/*     */   private HashSet<CustomMod> mutuallyExclusive;
/*     */   
/*     */   public CustomMod(String setID, String color, boolean isDailyMod) {
/*  28 */     if (offset_x == 0.0F) {
/*  29 */       offset_x = CustomModeScreen.screenX + 120.0F * Settings.scale;
/*  30 */       line_spacing = Settings.BIG_TEXT_MODE ? (40.0F * Settings.scale) : (32.0F * Settings.scale);
/*  31 */       text_max_width = Settings.isMobile ? (1170.0F * Settings.scale) : (1050.0F * Settings.scale);
/*     */     } 
/*     */     
/*  34 */     this.color = color;
/*  35 */     this.ID = setID;
/*  36 */     RunModStrings modStrings = CardCrawlGame.languagePack.getRunModString(setID);
/*  37 */     this.name = modStrings.NAME;
/*  38 */     this.description = modStrings.DESCRIPTION;
/*  39 */     this.hb = new Hitbox(text_max_width, 70.0F * Settings.scale);
/*  40 */     this.isDailyMod = isDailyMod;
/*  41 */     this.label = FontHelper.colorString("[" + this.name + "]", color) + " " + this.description;
/*     */     
/*  43 */     this.height = -FontHelper.getSmartHeight(FontHelper.charDescFont, this.label, text_max_width, line_spacing) + 70.0F * Settings.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(float y) {
/*  48 */     this.hb.update();
/*  49 */     this.hb.move(offset_x + (text_max_width - 80.0F * Settings.scale) / 2.0F, y + OFFSET_Y);
/*     */     
/*  51 */     if (this.hb.justHovered) {
/*  52 */       playHoverSound();
/*     */     }
/*     */     
/*  55 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/*  56 */       this.hb.clickStarted = true;
/*     */     }
/*     */     
/*  59 */     if (this.hb.clicked) {
/*  60 */       this.hb.clicked = false;
/*  61 */       this.selected = !this.selected;
/*  62 */       playClickSound();
/*  63 */       if (this.selected) {
/*  64 */         disableMutuallyExclusiveMods();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  70 */     float scale = Settings.isMobile ? (Settings.scale * 1.2F) : Settings.scale;
/*     */     
/*  72 */     if (this.hb.hovered) {
/*  73 */       sb.draw(ImageMaster.CHECKBOX, offset_x - 32.0F, this.hb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, scale * 1.2F, scale * 1.2F, 0.0F, 0, 0, 64, 64, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  90 */       sb.setColor(Color.GOLD);
/*  91 */       sb.setBlendFunction(770, 1);
/*  92 */       sb.draw(ImageMaster.CHECKBOX, offset_x - 32.0F, this.hb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, scale * 1.2F, scale * 1.2F, 0.0F, 0, 0, 64, 64, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 109 */       sb.setBlendFunction(770, 771);
/* 110 */       sb.setColor(Color.WHITE);
/*     */     } else {
/* 112 */       sb.draw(ImageMaster.CHECKBOX, offset_x - 32.0F, this.hb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, scale, scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 131 */     if (this.selected) {
/* 132 */       sb.draw(ImageMaster.TICK, offset_x - 32.0F, this.hb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, scale, scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 151 */     if (this.hb.hovered) {
/* 152 */       FontHelper.renderSmartText(sb, FontHelper.charDescFont, this.label, offset_x + 46.0F * Settings.scale, this.hb.cY + 12.0F * Settings.scale, text_max_width, line_spacing, Settings.LIGHT_YELLOW_COLOR);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 162 */       FontHelper.renderSmartText(sb, FontHelper.charDescFont, this.label, offset_x + 40.0F * Settings.scale, this.hb.cY + 12.0F * Settings.scale, text_max_width, line_spacing, Settings.CREAM_COLOR);
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
/* 173 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   private void playClickSound() {
/* 177 */     CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
/*     */   }
/*     */   
/*     */   private void playHoverSound() {
/* 181 */     CardCrawlGame.sound.playV("UI_HOVER", 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMutualExclusionPair(CustomMod otherMod) {
/* 190 */     setMutualExclusion(otherMod);
/* 191 */     otherMod.setMutualExclusion(this);
/*     */   }
/*     */   
/*     */   private void setMutualExclusion(CustomMod otherMod) {
/* 195 */     if (this.mutuallyExclusive == null) {
/* 196 */       this.mutuallyExclusive = new HashSet<>();
/*     */     }
/* 198 */     this.mutuallyExclusive.add(otherMod);
/*     */   }
/*     */   
/*     */   private void disableMutuallyExclusiveMods() {
/* 202 */     if (this.mutuallyExclusive != null)
/* 203 */       for (CustomMod mods : this.mutuallyExclusive)
/* 204 */         mods.selected = false;  
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\custom\CustomMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */