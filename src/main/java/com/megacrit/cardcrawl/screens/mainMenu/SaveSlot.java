/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.stats.CharStat;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class SaveSlot
/*     */ {
/*  23 */   private static final Logger logger = LogManager.getLogger(SaveSlot.class.getName());
/*  24 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SaveSlot");
/*  25 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */ 
/*     */   
/*  28 */   private String name = "";
/*  29 */   private long playtime = 0L;
/*  30 */   private float completionPercentage = 0.0F;
/*     */   
/*     */   public boolean emptySlot = false;
/*     */   
/*  34 */   private int index = 0;
/*  35 */   private Texture iconImg = null;
/*     */   public Hitbox slotHb;
/*  37 */   public static Color uiColor = null;
/*     */   public Hitbox deleteHb;
/*     */   
/*     */   public SaveSlot(int slot) {
/*  41 */     if (slot == CardCrawlGame.saveSlot && !CardCrawlGame.playerName.equals(CardCrawlGame.saveSlotPref
/*  42 */         .getString(SaveHelper.slotName("PROFILE_NAME", slot), ""))) {
/*  43 */       logger.info("Migrating from legacy save.");
/*  44 */       migration(slot);
/*     */     } 
/*     */     
/*  47 */     this.name = CardCrawlGame.saveSlotPref.getString(SaveHelper.slotName("PROFILE_NAME", slot), "");
/*     */     
/*  49 */     if (this.name.equals("")) {
/*  50 */       this.emptySlot = true;
/*     */     }
/*     */     
/*  53 */     if (!this.emptySlot) {
/*  54 */       loadInfo(slot);
/*     */     }
/*     */ 
/*     */     
/*  58 */     this.index = slot;
/*  59 */     this.slotHb = new Hitbox(800.0F * Settings.scale, 260.0F * Settings.scale);
/*  60 */     this.slotHb.move(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 280.0F * Settings.scale - this.index * 280.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.renameHb = new Hitbox(90.0F * Settings.scale, 90.0F * Settings.scale);
/*  65 */     this.renameHb.move(1400.0F * Settings.xScale, this.slotHb.cY + 44.0F * Settings.scale);
/*     */     
/*  67 */     this.deleteHb = new Hitbox(90.0F * Settings.scale, 90.0F * Settings.scale);
/*  68 */     this.deleteHb.move(1400.0F * Settings.xScale, this.renameHb.cY - 91.0F * Settings.scale);
/*     */ 
/*     */     
/*  71 */     switch (this.index) {
/*     */       case 0:
/*  73 */         this.iconImg = ImageMaster.PROFILE_A;
/*     */         break;
/*     */       case 1:
/*  76 */         this.iconImg = ImageMaster.PROFILE_B;
/*     */         break;
/*     */       case 2:
/*  79 */         this.iconImg = ImageMaster.PROFILE_C;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Hitbox renameHb;
/*     */   
/*     */   private void loadInfo(int slot) {
/*  87 */     this.completionPercentage = CardCrawlGame.saveSlotPref.getFloat(SaveHelper.slotName("COMPLETION", slot), 0.0F);
/*  88 */     this.playtime = CardCrawlGame.saveSlotPref.getLong(SaveHelper.slotName("PLAYTIME", slot), 0L);
/*     */   }
/*     */   
/*     */   private void migration(int slot) {
/*  92 */     CardCrawlGame.saveSlotPref.putString(SaveHelper.slotName("PROFILE_NAME", slot), CardCrawlGame.playerName);
/*     */ 
/*     */     
/*  95 */     CardCrawlGame.saveSlotPref.putFloat(
/*  96 */         SaveHelper.slotName("COMPLETION", slot), 
/*  97 */         UnlockTracker.getCompletionPercentage());
/*  98 */     this.completionPercentage = CardCrawlGame.saveSlotPref.getFloat(SaveHelper.slotName("COMPLETION", slot), 0.0F);
/*     */ 
/*     */     
/* 101 */     CardCrawlGame.saveSlotPref.putLong(SaveHelper.slotName("PLAYTIME", slot), UnlockTracker.getTotalPlaytime());
/* 102 */     this.playtime = CardCrawlGame.saveSlotPref.getLong(SaveHelper.slotName("PLAYTIME", slot), 0L);
/*     */     
/* 104 */     CardCrawlGame.saveSlotPref.flush();
/*     */   }
/*     */   
/*     */   public void update() {
/* 108 */     if (!this.emptySlot) {
/* 109 */       this.deleteHb.update();
/* 110 */       this.renameHb.update();
/*     */ 
/*     */       
/* 113 */       if (this.slotHb.hovered && CInputActionSet.topPanel.isJustPressed()) {
/* 114 */         CInputActionSet.topPanel.unpress();
/* 115 */         this.deleteHb.clicked = true;
/* 116 */         this.deleteHb.hovered = true;
/*     */       } 
/*     */ 
/*     */       
/* 120 */       if (this.slotHb.hovered && CInputActionSet.proceed.isJustPressed()) {
/* 121 */         CInputActionSet.proceed.unpress();
/* 122 */         this.renameHb.clicked = true;
/* 123 */         this.renameHb.hovered = true;
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     if (!this.deleteHb.hovered && !this.renameHb.hovered) {
/* 128 */       this.slotHb.update();
/*     */       
/* 130 */       if (this.slotHb.hovered && CInputActionSet.select.isJustPressed()) {
/* 131 */         this.slotHb.clicked = true;
/* 132 */       } else if (this.slotHb.justHovered) {
/* 133 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       } 
/*     */       
/* 136 */       if (this.slotHb.hovered && InputHelper.justClickedLeft) {
/* 137 */         this.slotHb.clickStarted = true;
/* 138 */         CardCrawlGame.sound.play("UI_CLICK_1");
/* 139 */       } else if (this.slotHb.clicked) {
/* 140 */         this.slotHb.clicked = false;
/* 141 */         CardCrawlGame.saveSlot = this.index;
/* 142 */         if (this.name.equals("")) {
/* 143 */           CardCrawlGame.mainMenuScreen.saveSlotScreen.openRenamePopup(this.index, true);
/*     */         } else {
/* 145 */           CardCrawlGame.mainMenuScreen.saveSlotScreen.confirm(this.index);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 149 */       this.slotHb.unhover();
/*     */       
/* 151 */       if (this.deleteHb.justHovered) {
/* 152 */         CardCrawlGame.sound.play("UI_HOVER");
/* 153 */       } else if (this.deleteHb.hovered && InputHelper.justClickedLeft) {
/* 154 */         this.deleteHb.clickStarted = true;
/* 155 */         CardCrawlGame.sound.play("UI_CLICK_1");
/* 156 */       } else if (this.deleteHb.clicked) {
/* 157 */         this.deleteHb.clicked = false;
/* 158 */         CardCrawlGame.mainMenuScreen.saveSlotScreen.openDeletePopup(this.index);
/*     */       } 
/*     */       
/* 161 */       if (this.renameHb.justHovered) {
/* 162 */         CardCrawlGame.sound.play("UI_HOVER");
/* 163 */       } else if (this.renameHb.hovered && InputHelper.justClickedLeft) {
/* 164 */         this.renameHb.clickStarted = true;
/* 165 */         CardCrawlGame.sound.play("UI_CLICK_1");
/* 166 */       } else if (this.renameHb.clicked) {
/* 167 */         this.renameHb.clicked = false;
/* 168 */         CardCrawlGame.mainMenuScreen.saveSlotScreen.openRenamePopup(this.index, false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 175 */     renderSlotImage(sb);
/* 176 */     renderText(sb);
/*     */     
/* 178 */     if (!this.emptySlot) {
/* 179 */       renderDeleteIcon(sb);
/* 180 */       renderRenameIcon(sb);
/*     */     } 
/*     */     
/* 183 */     renderHbs(sb);
/*     */   }
/*     */   
/*     */   private void renderText(SpriteBatch sb) {
/* 187 */     Color c = Settings.GOLD_COLOR.cpy();
/* 188 */     c.a = uiColor.a;
/*     */     
/* 190 */     if (!this.emptySlot) {
/* 191 */       FontHelper.renderFontLeft(sb, FontHelper.buttonLabelFont, this.name, 740.0F * Settings.xScale, this.slotHb.cY + 26.0F * Settings.scale, c);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 200 */       FontHelper.renderFontLeft(sb, FontHelper.charDescFont, TEXT[0] + 
/*     */ 
/*     */           
/* 203 */           CharStat.formatHMSM((int)this.playtime), 740.0F * Settings.xScale, this.slotHb.cY - 24.0F * Settings.scale, uiColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 209 */       if (this.completionPercentage == 100.0F) {
/* 210 */         FontHelper.renderFontCentered(sb, FontHelper.charTitleFont, "100%", 1200.0F * Settings.xScale, this.slotHb.cY + 0.0F * Settings.scale, c);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 217 */       else if (this.completionPercentage == 0.0F) {
/* 218 */         FontHelper.renderFontCentered(sb, FontHelper.charTitleFont, "0%", 1200.0F * Settings.xScale, this.slotHb.cY + 0.0F * Settings.scale, c);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 226 */         FontHelper.renderFontCentered(sb, FontHelper.charTitleFont, 
/*     */ 
/*     */             
/* 229 */             String.format("%.1f", new Object[] { Float.valueOf(this.completionPercentage) }) + "%", 1200.0F * Settings.xScale, this.slotHb.cY + 0.0F * Settings.scale, c);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 235 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[1], Settings.WIDTH / 2.0F, this.slotHb.cY, c);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderSlotImage(SpriteBatch sb) {
/* 240 */     float scale = (Settings.xScale > Settings.yScale) ? Settings.xScale : Settings.yScale;
/*     */     
/* 242 */     if (this.slotHb.hovered) {
/* 243 */       scale *= 1.02F;
/*     */     }
/*     */     
/* 246 */     sb.draw(ImageMaster.PROFILE_SLOT, this.slotHb.cX - 400.0F, this.slotHb.cY - 130.0F, 400.0F, 130.0F, 800.0F, 260.0F, scale, scale * 0.9F, 0.0F, 0, 0, 800, 260, false, false);
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
/* 264 */     sb.draw(this.iconImg, this.slotHb.cX - 290.0F * Settings.xScale - 50.0F, this.slotHb.cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 100, 100, false, false);
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
/* 282 */     if (this.slotHb.hovered) {
/* 283 */       sb.setColor(Settings.HALF_TRANSPARENT_WHITE_COLOR);
/* 284 */       sb.setBlendFunction(770, 1);
/* 285 */       sb.draw(ImageMaster.PROFILE_SLOT, this.slotHb.cX - 400.0F, this.slotHb.cY - 130.0F, 400.0F, 130.0F, 800.0F, 260.0F, scale, scale * 0.9F, 0.0F, 0, 0, 800, 260, false, false);
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
/* 302 */       sb.setBlendFunction(770, 771);
/* 303 */       sb.setColor(uiColor);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderHbs(SpriteBatch sb) {
/* 308 */     if (CardCrawlGame.mainMenuScreen.saveSlotScreen.shown) {
/* 309 */       this.slotHb.render(sb);
/* 310 */       this.deleteHb.render(sb);
/* 311 */       this.renameHb.render(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderDeleteIcon(SpriteBatch sb) {
/* 316 */     float scale = Settings.scale;
/*     */     
/* 318 */     if (this.deleteHb.hovered) {
/* 319 */       scale = Settings.scale * 1.04F;
/*     */     }
/*     */ 
/*     */     
/* 323 */     sb.draw(ImageMaster.PROFILE_DELETE, this.deleteHb.cX - 50.0F, this.deleteHb.cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, scale, scale, 0.0F, 0, 0, 100, 100, false, false);
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
/* 341 */     if (this.deleteHb.hovered) {
/* 342 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.4F));
/* 343 */       sb.setBlendFunction(770, 1);
/* 344 */       sb.draw(ImageMaster.PROFILE_DELETE, this.deleteHb.cX - 50.0F, this.deleteHb.cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, scale, scale, 0.0F, 0, 0, 100, 100, false, false);
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
/* 361 */       sb.setBlendFunction(770, 771);
/* 362 */       sb.setColor(uiColor);
/*     */     } 
/*     */     
/* 365 */     if (this.slotHb.hovered && Settings.isControllerMode) {
/* 366 */       sb.draw(CInputActionSet.topPanel
/* 367 */           .getKeyImg(), this.deleteHb.cX - 32.0F + 82.0F * Settings.scale, this.deleteHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     }
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
/*     */   private void renderRenameIcon(SpriteBatch sb) {
/* 387 */     float scale = Settings.scale;
/*     */     
/* 389 */     if (this.renameHb.hovered) {
/* 390 */       scale = Settings.scale * 1.04F;
/*     */     }
/*     */ 
/*     */     
/* 394 */     sb.draw(ImageMaster.PROFILE_RENAME, this.renameHb.cX - 50.0F, this.renameHb.cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, scale, scale, 0.0F, 0, 0, 100, 100, false, false);
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
/* 412 */     if (this.renameHb.hovered) {
/* 413 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.4F));
/* 414 */       sb.setBlendFunction(770, 1);
/* 415 */       sb.draw(ImageMaster.PROFILE_RENAME, this.renameHb.cX - 50.0F, this.renameHb.cY - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, scale, scale, 0.0F, 0, 0, 100, 100, false, false);
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
/* 432 */       sb.setBlendFunction(770, 771);
/* 433 */       sb.setColor(uiColor);
/*     */     } 
/*     */     
/* 436 */     if (this.slotHb.hovered && Settings.isControllerMode) {
/* 437 */       sb.draw(CInputActionSet.proceed
/* 438 */           .getKeyImg(), this.renameHb.cX - 32.0F + 82.0F * Settings.scale, this.renameHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     }
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
/*     */   public void clear() {
/* 458 */     this.name = "";
/* 459 */     this.emptySlot = true;
/* 460 */     this.completionPercentage = 0.0F;
/* 461 */     this.playtime = 0L;
/* 462 */     this.deleteHb.unhover();
/*     */   }
/*     */   
/*     */   public String getName() {
/* 466 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String setName) {
/* 470 */     this.name = setName;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\SaveSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */