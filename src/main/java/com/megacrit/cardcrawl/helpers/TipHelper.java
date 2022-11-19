/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
/*     */ 
/*     */ public class TipHelper
/*     */ {
/*  19 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TipHelper");
/*  20 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  22 */   private static final Logger logger = LogManager.getLogger(TipHelper.class.getName());
/*     */   private static boolean renderedTipThisFrame = false;
/*     */   private static boolean isCard = false;
/*  25 */   private static ArrayList<String> KEYWORDS = new ArrayList<>(); private static float drawX; private static float drawY;
/*  26 */   private static ArrayList<PowerTip> POWER_TIPS = new ArrayList<>();
/*  27 */   private static String HEADER = null; private static String BODY = null;
/*     */   private static AbstractCard card;
/*  29 */   private static final Color BASE_COLOR = new Color(1.0F, 0.9725F, 0.8745F, 1.0F);
/*     */ 
/*     */   
/*  32 */   private static final float CARD_TIP_PAD = 12.0F * Settings.scale;
/*  33 */   private static final float SHADOW_DIST_Y = 14.0F * Settings.scale;
/*  34 */   private static final float SHADOW_DIST_X = 9.0F * Settings.scale;
/*  35 */   private static final float BOX_EDGE_H = 32.0F * Settings.scale;
/*  36 */   private static final float BOX_BODY_H = 64.0F * Settings.scale;
/*  37 */   private static final float BOX_W = 320.0F * Settings.scale;
/*     */ 
/*     */   
/*  40 */   private static GlyphLayout gl = new GlyphLayout();
/*     */   private static float textHeight;
/*  42 */   private static final float TEXT_OFFSET_X = 22.0F * Settings.scale;
/*  43 */   private static final float HEADER_OFFSET_Y = 12.0F * Settings.scale;
/*  44 */   private static final float ORB_OFFSET_Y = -8.0F * Settings.scale;
/*  45 */   private static final float BODY_OFFSET_Y = -20.0F * Settings.scale;
/*  46 */   private static final float BODY_TEXT_WIDTH = 280.0F * Settings.scale;
/*  47 */   private static final float TIP_DESC_LINE_SPACING = 26.0F * Settings.scale;
/*     */ 
/*     */   
/*  50 */   private static final float POWER_ICON_OFFSET_X = 40.0F * Settings.scale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void render(SpriteBatch sb) {
/*  58 */     if (!Settings.hidePopupDetails && 
/*  59 */       renderedTipThisFrame) {
/*     */ 
/*     */       
/*  62 */       if (AbstractDungeon.player != null && (AbstractDungeon.player.inSingleTargetMode || (AbstractDungeon.player.isDraggingCard && !Settings.isTouchScreen))) {
/*     */         
/*  64 */         HEADER = null;
/*  65 */         BODY = null;
/*  66 */         card = null;
/*  67 */         renderedTipThisFrame = false;
/*     */         
/*     */         return;
/*     */       } 
/*  71 */       if (Settings.isTouchScreen && AbstractDungeon.player != null && AbstractDungeon.player.isHoveringDropZone) {
/*     */         
/*  73 */         HEADER = null;
/*  74 */         BODY = null;
/*  75 */         card = null;
/*  76 */         renderedTipThisFrame = false;
/*     */         
/*     */         return;
/*     */       } 
/*  80 */       if (isCard && card != null) {
/*  81 */         if (card.current_x > Settings.WIDTH * 0.75F) {
/*  82 */           renderKeywords(card.current_x - AbstractCard.IMG_WIDTH / 2.0F - CARD_TIP_PAD - BOX_W, card.current_y + AbstractCard.IMG_HEIGHT / 2.0F - BOX_EDGE_H, sb, KEYWORDS);
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/*  88 */           renderKeywords(card.current_x + AbstractCard.IMG_WIDTH / 2.0F + CARD_TIP_PAD, card.current_y + AbstractCard.IMG_HEIGHT / 2.0F - BOX_EDGE_H, sb, KEYWORDS);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  94 */         card = null;
/*  95 */         isCard = false;
/*     */       
/*     */       }
/*  98 */       else if (HEADER != null) {
/*  99 */         textHeight = -FontHelper.getSmartHeight(FontHelper.tipBodyFont, BODY, BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - 7.0F * Settings.scale;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 104 */         renderTipBox(drawX, drawY, sb, HEADER, BODY);
/* 105 */         HEADER = null;
/*     */       }
/*     */       else {
/*     */         
/* 109 */         renderPowerTips(drawX, drawY, sb, POWER_TIPS);
/*     */       } 
/*     */       
/* 112 */       renderedTipThisFrame = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderGenericTip(float x, float y, String header, String body) {
/* 118 */     if (!Settings.hidePopupDetails) {
/* 119 */       if (!renderedTipThisFrame) {
/* 120 */         renderedTipThisFrame = true;
/* 121 */         HEADER = header;
/* 122 */         BODY = body;
/* 123 */         drawX = x;
/* 124 */         drawY = y;
/*     */       }
/* 126 */       else if (HEADER == null && !KEYWORDS.isEmpty()) {
/* 127 */         logger.info("! " + (String)KEYWORDS.get(0));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void queuePowerTips(float x, float y, ArrayList<PowerTip> powerTips) {
/* 137 */     if (!renderedTipThisFrame) {
/* 138 */       renderedTipThisFrame = true;
/* 139 */       drawX = x;
/* 140 */       drawY = y;
/* 141 */       POWER_TIPS = powerTips;
/*     */     }
/* 143 */     else if (HEADER == null && !KEYWORDS.isEmpty()) {
/* 144 */       logger.info("! " + (String)KEYWORDS.get(0));
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
/*     */   public static void renderTipForCard(AbstractCard c, SpriteBatch sb, ArrayList<String> keywords) {
/* 157 */     if (!renderedTipThisFrame) {
/* 158 */       isCard = true;
/* 159 */       card = c;
/* 160 */       convertToReadable(keywords);
/* 161 */       KEYWORDS = keywords;
/* 162 */       renderedTipThisFrame = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void convertToReadable(ArrayList<String> keywords) {
/* 167 */     ArrayList<String> add = new ArrayList<>();
/* 168 */     keywords.addAll(add);
/*     */   }
/*     */   
/*     */   private static void renderPowerTips(float x, float y, SpriteBatch sb, ArrayList<PowerTip> powerTips) {
/* 172 */     float originalY = y;
/* 173 */     boolean offsetLeft = false;
/* 174 */     if (x > Settings.WIDTH / 2.0F) {
/* 175 */       offsetLeft = true;
/*     */     }
/* 177 */     float offset = 0.0F;
/* 178 */     for (PowerTip tip : powerTips) {
/* 179 */       textHeight = getPowerTipHeight(tip);
/* 180 */       float offsetChange = textHeight + BOX_EDGE_H * 3.15F;
/*     */       
/* 182 */       if (offset + offsetChange >= Settings.HEIGHT * 0.7F) {
/* 183 */         y = originalY;
/* 184 */         offset = 0.0F;
/* 185 */         if (offsetLeft) {
/* 186 */           x -= 324.0F * Settings.scale;
/*     */         } else {
/* 188 */           x += 324.0F * Settings.scale;
/*     */         } 
/*     */       } 
/*     */       
/* 192 */       renderTipBox(x, y, sb, tip.header, tip.body);
/*     */ 
/*     */       
/* 195 */       gl.setText(FontHelper.tipHeaderFont, tip.header, Color.WHITE, 0.0F, -1, false);
/* 196 */       if (tip.img != null) {
/* 197 */         sb.setColor(Color.WHITE);
/* 198 */         sb.draw(tip.img, x + TEXT_OFFSET_X + gl.width + 5.0F * Settings.scale, y - 10.0F * Settings.scale, 32.0F * Settings.scale, 32.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 204 */       else if (tip.imgRegion != null) {
/* 205 */         sb.setColor(Color.WHITE);
/* 206 */         sb.draw((TextureRegion)tip.imgRegion, x + gl.width + POWER_ICON_OFFSET_X - tip.imgRegion.packedWidth / 2.0F, y + 5.0F * Settings.scale - tip.imgRegion.packedHeight / 2.0F, tip.imgRegion.packedWidth / 2.0F, tip.imgRegion.packedHeight / 2.0F, tip.imgRegion.packedWidth, tip.imgRegion.packedHeight, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F);
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
/* 219 */       y -= offsetChange;
/* 220 */       offset += offsetChange;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static float getPowerTipHeight(PowerTip powerTip) {
/* 225 */     return -FontHelper.getSmartHeight(FontHelper.tipBodyFont, powerTip.body, BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - 7.0F * Settings.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float calculateAdditionalOffset(ArrayList<PowerTip> powerTips, float hBcY) {
/* 230 */     if (powerTips.isEmpty()) {
/* 231 */       return 0.0F;
/*     */     }
/* 233 */     return (1.0F - hBcY / Settings.HEIGHT) * getTallestOffset(powerTips) - (getPowerTipHeight(powerTips.get(0)) + BOX_EDGE_H * 3.15F) / 2.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float calculateToAvoidOffscreen(ArrayList<PowerTip> powerTips, float hBcY) {
/* 239 */     if (powerTips.isEmpty()) {
/* 240 */       return 0.0F;
/*     */     }
/* 242 */     return Math.max(0.0F, getTallestOffset(powerTips) - hBcY);
/*     */   }
/*     */ 
/*     */   
/*     */   private static float getTallestOffset(ArrayList<PowerTip> powerTips) {
/* 247 */     float currentOffset = 0.0F;
/* 248 */     float maxOffset = 0.0F;
/* 249 */     for (PowerTip p : powerTips) {
/* 250 */       float offsetChange = getPowerTipHeight(p) + BOX_EDGE_H * 3.15F;
/* 251 */       if (currentOffset + offsetChange >= Settings.HEIGHT * 0.7F) {
/* 252 */         currentOffset = 0.0F;
/*     */       }
/* 254 */       currentOffset += offsetChange;
/* 255 */       if (currentOffset > maxOffset) {
/* 256 */         maxOffset = currentOffset;
/*     */       }
/*     */     } 
/* 259 */     return maxOffset;
/*     */   }
/*     */   
/*     */   private static void renderKeywords(float x, float y, SpriteBatch sb, ArrayList<String> keywords) {
/* 263 */     if (keywords.size() >= 4) {
/* 264 */       y += (keywords.size() - 1) * 62.0F * Settings.scale;
/*     */     }
/*     */     
/* 267 */     for (String s : keywords) {
/* 268 */       if (!GameDictionary.keywords.containsKey(s)) {
/* 269 */         logger.info("MISSING: " + s + " in Dictionary!"); continue;
/*     */       } 
/* 271 */       textHeight = -FontHelper.getSmartHeight(FontHelper.tipBodyFont, (String)GameDictionary.keywords
/*     */           
/* 273 */           .get(s), BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - 7.0F * Settings.scale;
/*     */ 
/*     */       
/* 276 */       renderBox(sb, s, x, y);
/* 277 */       y -= textHeight + BOX_EDGE_H * 3.15F;
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
/*     */   private static void renderTipBox(float x, float y, SpriteBatch sb, String title, String description) {
/* 292 */     float h = textHeight;
/*     */ 
/*     */     
/* 295 */     sb.setColor(Settings.TOP_PANEL_SHADOW_COLOR);
/* 296 */     sb.draw(ImageMaster.KEYWORD_TOP, x + SHADOW_DIST_X, y - SHADOW_DIST_Y, BOX_W, BOX_EDGE_H);
/* 297 */     sb.draw(ImageMaster.KEYWORD_BODY, x + SHADOW_DIST_X, y - h - BOX_EDGE_H - SHADOW_DIST_Y, BOX_W, h + BOX_EDGE_H);
/* 298 */     sb.draw(ImageMaster.KEYWORD_BOT, x + SHADOW_DIST_X, y - h - BOX_BODY_H - SHADOW_DIST_Y, BOX_W, BOX_EDGE_H);
/*     */ 
/*     */     
/* 301 */     sb.setColor(Color.WHITE);
/* 302 */     sb.draw(ImageMaster.KEYWORD_TOP, x, y, BOX_W, BOX_EDGE_H);
/* 303 */     sb.draw(ImageMaster.KEYWORD_BODY, x, y - h - BOX_EDGE_H, BOX_W, h + BOX_EDGE_H);
/* 304 */     sb.draw(ImageMaster.KEYWORD_BOT, x, y - h - BOX_BODY_H, BOX_W, BOX_EDGE_H);
/*     */ 
/*     */     
/* 307 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, title, x + TEXT_OFFSET_X, y + HEADER_OFFSET_Y, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, description, x + TEXT_OFFSET_X, y + BODY_OFFSET_Y, BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING, BASE_COLOR);
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
/*     */   public static void renderTipEnergy(SpriteBatch sb, TextureAtlas.AtlasRegion region, float x, float y) {
/* 329 */     sb.setColor(Color.WHITE);
/* 330 */     sb.draw(region
/* 331 */         .getTexture(), x + region.offsetX * Settings.scale, y + region.offsetY * Settings.scale, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.scale, Settings.scale, 0.0F, region
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 341 */         .getRegionX(), region
/* 342 */         .getRegionY(), region
/* 343 */         .getRegionWidth(), region
/* 344 */         .getRegionHeight(), false, false);
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
/*     */   private static void renderBox(SpriteBatch sb, String word, float x, float y) {
/* 358 */     float h = textHeight;
/*     */ 
/*     */     
/* 361 */     sb.setColor(Settings.TOP_PANEL_SHADOW_COLOR);
/* 362 */     sb.draw(ImageMaster.KEYWORD_TOP, x + SHADOW_DIST_X, y - SHADOW_DIST_Y, BOX_W, BOX_EDGE_H);
/* 363 */     sb.draw(ImageMaster.KEYWORD_BODY, x + SHADOW_DIST_X, y - h - BOX_EDGE_H - SHADOW_DIST_Y, BOX_W, h + BOX_EDGE_H);
/* 364 */     sb.draw(ImageMaster.KEYWORD_BOT, x + SHADOW_DIST_X, y - h - BOX_BODY_H - SHADOW_DIST_Y, BOX_W, BOX_EDGE_H);
/*     */ 
/*     */     
/* 367 */     sb.setColor(Color.WHITE);
/* 368 */     sb.draw(ImageMaster.KEYWORD_TOP, x, y, BOX_W, BOX_EDGE_H);
/* 369 */     sb.draw(ImageMaster.KEYWORD_BODY, x, y - h - BOX_EDGE_H, BOX_W, h + BOX_EDGE_H);
/* 370 */     sb.draw(ImageMaster.KEYWORD_BOT, x, y - h - BOX_BODY_H, BOX_W, BOX_EDGE_H);
/*     */     
/* 372 */     TextureAtlas.AtlasRegion currentOrb = (AbstractDungeon.player != null) ? AbstractDungeon.player.getOrb() : AbstractCard.orb_red;
/*     */ 
/*     */ 
/*     */     
/* 376 */     if (word.equals("[R]") || word.equals("[G]") || word.equals("[B]") || word.equals("[W]") || word.equals("[E]")) {
/*     */       
/* 378 */       if (word.equals("[R]")) {
/* 379 */         renderTipEnergy(sb, AbstractCard.orb_red, x + TEXT_OFFSET_X, y + ORB_OFFSET_Y);
/* 380 */       } else if (word.equals("[G]")) {
/* 381 */         renderTipEnergy(sb, AbstractCard.orb_green, x + TEXT_OFFSET_X, y + ORB_OFFSET_Y);
/* 382 */       } else if (word.equals("[B]")) {
/* 383 */         renderTipEnergy(sb, AbstractCard.orb_blue, x + TEXT_OFFSET_X, y + ORB_OFFSET_Y);
/* 384 */       } else if (word.equals("[W]")) {
/* 385 */         renderTipEnergy(sb, AbstractCard.orb_purple, x + TEXT_OFFSET_X, y + ORB_OFFSET_Y);
/* 386 */       } else if (word.equals("[E]")) {
/* 387 */         renderTipEnergy(sb, currentOrb, x + TEXT_OFFSET_X, y + ORB_OFFSET_Y);
/*     */       } 
/*     */       
/* 390 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, 
/*     */ 
/*     */           
/* 393 */           capitalize(TEXT[0]), x + TEXT_OFFSET_X * 2.5F, y + HEADER_OFFSET_Y, Settings.GOLD_COLOR);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 398 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, 
/*     */ 
/*     */           
/* 401 */           capitalize(word), x + TEXT_OFFSET_X, y + HEADER_OFFSET_Y, Settings.GOLD_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 408 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, GameDictionary.keywords
/*     */ 
/*     */         
/* 411 */         .get(word), x + TEXT_OFFSET_X, y + BODY_OFFSET_Y, BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING, BASE_COLOR);
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
/*     */   public static String capitalize(String input) {
/* 427 */     StringBuilder sb = new StringBuilder();
/* 428 */     for (int i = 0; i < input.length(); i++) {
/* 429 */       char tmp = input.charAt(i);
/* 430 */       if (i == 0) {
/* 431 */         tmp = Character.toUpperCase(tmp);
/*     */       } else {
/* 433 */         tmp = Character.toLowerCase(tmp);
/*     */       } 
/* 435 */       sb.append(tmp);
/*     */     } 
/* 437 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\TipHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */