/*     */ package com.megacrit.cardcrawl.screens.runHistory;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.stats.BattleStats;
import com.megacrit.cardcrawl.screens.stats.CampfireChoice;
import com.megacrit.cardcrawl.screens.stats.CardChoiceStats;
import com.megacrit.cardcrawl.screens.stats.EventStats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RunPathElement
/*     */ {
/*     */   private static final boolean SHOW_ROOM_TYPE = true;
/*     */   private static final boolean SHOW_HP = true;
/*     */   private static final boolean SHOW_GOLD = true;
/*     */   private static final boolean SHOW_FIGHT_DETAILS = true;
/*     */   private static final boolean SHOW_EVENT_DETAILS = true;
/*     */   private static final boolean SHOW_EVENT_PLAYER_CHOICE = true;
/*     */   private static final boolean SHOW_CARD_PICK_DETAILS = true;
/*     */   private static final boolean SHOW_CARD_SKIP_INFO = true;
/*     */   private static final boolean SHOW_RELIC_OBTAIN_DETAILS = true;
/*     */   private static final boolean SHOW_POTION_OBTAIN_DETAILS = true;
/*     */   private static final boolean SHOW_CAMPFIRE_CHOICE_DETAILS = true;
/*     */   private static final boolean SHOW_PURCHASE_DETAILS = true;
/*  44 */   private static final float ICON_SIZE = 48.0F * Settings.scale;
/*     */   
/*     */   private static final float ICON_HOVER_SCALE = 2.0F;
/*  47 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RunHistoryPathNodes");
/*  48 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final String NEW_LINE = " NL ";
/*     */   private static final String TAB = " TAB ";
/*  52 */   private static final String TEXT_ERROR = TEXT[0];
/*  53 */   private static final String TEXT_MONSTER = TEXT[1];
/*  54 */   private static final String TEXT_ELITE = TEXT[2];
/*  55 */   private static final String TEXT_EVENT = TEXT[3];
/*  56 */   private static final String TEXT_BOSS = TEXT[4];
/*  57 */   private static final String TEXT_TREASURE = TEXT[5];
/*  58 */   private static final String TEXT_BOSS_TREASURE = TEXT[6];
/*  59 */   private static final String TEXT_CAMPFIRE = TEXT[7];
/*  60 */   private static final String TEXT_SHOP = TEXT[8];
/*  61 */   private static final String TEXT_UNKNOWN_MONSTER = TEXT[9];
/*  62 */   private static final String TEXT_UNKN0WN_SHOP = TEXT[10];
/*  63 */   private static final String TEXT_UNKNOWN_TREASURE = TEXT[11];
/*  64 */   private static final String TEXT_FLOOR_FORMAT = TEXT[12];
/*  65 */   private static final String TEXT_SIMPLE_FLOOR_FORMAT = TEXT[13];
/*     */   
/*  67 */   private static final String TEXT_DAMAGE_FORMAT = TEXT[14];
/*  68 */   private static final String TEXT_TURNS_FORMAT = TEXT[15];
/*  69 */   private static final String TEXT_COMBAT_HP_FORMAT = TEXT[16];
/*  70 */   private static final String TEXT_GOLD_FORMAT = TEXT[17];
/*     */   
/*  72 */   private static final String TEXT_OBTAIN_HEADER = TEXT[18];
/*  73 */   private static final String TEXT_SKIP_HEADER = TEXT[19];
/*  74 */   private static final String TEXT_MISSING_INFO = TEXT[20];
/*  75 */   private static final String TEXT_SINGING_BOWL_CHOICE = TEXT[21];
/*     */   
/*  77 */   private static final String TEXT_OBTAIN_TYPE_CARD = TEXT[22];
/*  78 */   private static final String TEXT_OBTAIN_TYPE_RELIC = TEXT[23];
/*  79 */   private static final String TEXT_OBTAIN_TYPE_POTION = TEXT[24];
/*  80 */   private static final String TEXT_OBTAIN_TYPE_SPECIAL = TEXT[25];
/*     */   
/*  82 */   private static final String TEXT_REST_OPTION = TEXT[26];
/*  83 */   private static final String TEXT_SMITH_OPTION = TEXT[27];
/*  84 */   private static final String TEXT_TOKE_OPTION = TEXT[28];
/*  85 */   private static final String TEXT_DIG_OPTION = TEXT[29];
/*  86 */   private static final String TEXT_LIFT_OPTION = TEXT[30];
/*  87 */   private static final String TEXT_RECALL_OPTION = TEXT[46];
/*  88 */   private static final String TEXT_PURCHASED = TEXT[31];
/*  89 */   private static final String TEXT_SPENT = TEXT[32];
/*  90 */   private static final String TEXT_TOOK = TEXT[33];
/*  91 */   private static final String TEXT_LOST = TEXT[34];
/*  92 */   private static final String TEXT_GENERIC_MAX_HP_FORMAT = TEXT[35];
/*  93 */   private static final String TEXT_HEALED = TEXT[36];
/*  94 */   private static final String TEXT_GAINED = TEXT[37];
/*  95 */   private static final String TEXT_IGNORED = TEXT[38];
/*  96 */   private static final String TEXT_GENERIC_HP_FORMAT = TEXT[39];
/*     */ 
/*     */   
/*  99 */   private static final String TEXT_EVENT_DAMAGE = TEXT[42];
/* 100 */   private static final String TEXT_UPGRADED = TEXT[43];
/* 101 */   private static final String TEXT_TRANSFORMED = TEXT[44];
/* 102 */   private static final String TEXT_LOST_RELIC = TEXT[45]; public Hitbox hb; public PathNodeType nodeType; private int floor; public int col; public int row; private Integer currentHP; private Integer maxHP; private Integer gold;
/* 103 */   private static final String TEXT_REMOVE_OPTION = TEXT_TOKE_OPTION; private BattleStats battleStats; private EventStats eventStats; private CardChoiceStats cardChoiceStats; private List<String> relicsObtained;
/*     */   private List<String> potionsObtained;
/*     */   private CampfireChoice campfireChoice;
/*     */   private List<String> shopPurchases;
/*     */   private List<String> shopPurges;
/*     */   
/* 109 */   public enum PathNodeType { ERROR, MONSTER, ELITE, EVENT, BOSS, TREASURE, BOSS_TREASURE, CAMPFIRE, SHOP, UNKNOWN_MONSTER, UNKNOWN_SHOP, UNKNOWN_TREASURE, HEART; }
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
/*     */ 
/*     */   
/* 129 */   private String cachedTooltip = null;
/*     */   
/*     */   public RunPathElement(String roomKey, int floorNum) {
/* 132 */     this.hb = new Hitbox(ICON_SIZE, ICON_SIZE);
/* 133 */     this.nodeType = pathNodeTypeForRoomKey(roomKey);
/* 134 */     this.floor = floorNum;
/*     */   }
/*     */   
/*     */   public void addHpData(Integer current, Integer max) {
/* 138 */     this.currentHP = current;
/* 139 */     this.maxHP = max;
/*     */   }
/*     */   
/*     */   public void addGoldData(Integer gold) {
/* 143 */     this.gold = gold;
/*     */   }
/*     */   
/*     */   public void addBattleData(BattleStats battle) {
/* 147 */     this.battleStats = battle;
/*     */   }
/*     */   
/*     */   public void addEventData(EventStats event) {
/* 151 */     this.eventStats = event;
/*     */   }
/*     */   
/*     */   public void addCardChoiceData(CardChoiceStats choice) {
/* 155 */     this.cardChoiceStats = choice;
/*     */   }
/*     */   
/*     */   public void addRelicObtainStats(List<String> relicKeys) {
/* 159 */     this.relicsObtained = relicKeys;
/*     */   }
/*     */   
/*     */   public void addRelicObtainStats(String relicKey) {
/* 163 */     addRelicObtainStats(Arrays.asList(new String[] { relicKey }));
/*     */   }
/*     */   
/*     */   public void addPotionObtainStats(List<String> potionKey) {
/* 167 */     this.potionsObtained = potionKey;
/*     */   }
/*     */   
/*     */   public void addCampfireChoiceData(CampfireChoice choice) {
/* 171 */     this.campfireChoice = choice;
/*     */   }
/*     */   
/*     */   public void addShopPurchaseData(ArrayList<String> keys) {
/* 175 */     this.shopPurchases = keys;
/*     */   }
/*     */   
/*     */   public void addPurgeData(ArrayList<String> keys) {
/* 179 */     this.shopPurges = keys;
/*     */   }
/*     */   
/*     */   public void update() {
/* 183 */     this.hb.update();
/* 184 */     if (this.hb.hovered) {
/* 185 */       float tipX = this.hb.x + 64.0F * Settings.scale;
/* 186 */       float tipY = this.hb.y + ICON_SIZE / 2.0F;
/*     */       
/* 188 */       String header = String.format(TEXT_SIMPLE_FLOOR_FORMAT, new Object[] { Integer.valueOf(this.floor) });
/* 189 */       TipHelper.renderGenericTip(tipX, tipY, header, getTipDescriptionText());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getTipHeaderWithRoomTypeText() {
/* 199 */     return String.format(TEXT_FLOOR_FORMAT, new Object[] { Integer.toString(this.floor), stringForType() });
/*     */   }
/*     */   
/*     */   private String getTipDescriptionText() {
/* 203 */     if (this.cachedTooltip != null) {
/* 204 */       return this.cachedTooltip;
/*     */     }
/*     */     
/* 207 */     StringBuilder sb = new StringBuilder();
/*     */ 
/*     */ 
/*     */     
/* 211 */     sb.append(stringForType());
/*     */ 
/*     */ 
/*     */     
/* 215 */     boolean displayHP = (this.currentHP != null && this.maxHP != null);
/* 216 */     if (displayHP) {
/* 217 */       if (sb.length() > 0) {
/* 218 */         sb.append(" NL ");
/*     */       }
/* 220 */       sb.append(String.format(TEXT_COMBAT_HP_FORMAT, new Object[] { this.currentHP, this.maxHP }));
/* 221 */       sb.append(" TAB ");
/*     */     } 
/*     */ 
/*     */     
/* 225 */     boolean displayGold = (this.gold != null);
/* 226 */     if (displayGold) {
/* 227 */       sb.append(String.format(TEXT_GOLD_FORMAT, new Object[] { this.gold }));
/*     */     }
/*     */ 
/*     */     
/* 231 */     if (this.eventStats != null) {
/* 232 */       if (sb.length() > 0) {
/* 233 */         sb.append(" NL ");
/*     */       }
/* 235 */       sb.append(localizedEventNameForKey(this.eventStats.event_name));
/*     */ 
/*     */ 
/*     */       
/* 239 */       if (!this.eventStats.player_choice.equals("Took Portal"))
/*     */       {
/*     */ 
/*     */         
/* 243 */         if (this.eventStats.max_hp_gain == 0 && this.eventStats.max_hp_loss == 0 && this.eventStats.gold_loss == 0 && this.eventStats.gold_gain == 0 && this.eventStats.damage_taken == 0 && this.eventStats.damage_healed == 0 && this.eventStats.cards_removed == null && this.eventStats.cards_obtained == null && this.eventStats.cards_transformed == null && this.eventStats.cards_upgraded == null && this.eventStats.relics_obtained == null && this.eventStats.relics_lost == null && this.eventStats.potions_obtained == null && this.battleStats == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 251 */           sb.append(" NL ");
/* 252 */           sb.append(" TAB ").append(TEXT_IGNORED);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 257 */       if (this.eventStats.relics_lost != null && !this.eventStats.relics_lost.isEmpty()) {
/* 258 */         sb.append(" NL ");
/* 259 */         for (int i = 0; i < this.eventStats.relics_lost.size(); i++) {
/* 260 */           String relicID = this.eventStats.relics_lost.get(i);
/* 261 */           String relicName = (RelicLibrary.getRelic(relicID)).name;
/* 262 */           sb.append(" TAB ").append(String.format(TEXT_LOST_RELIC, new Object[] { relicName }));
/* 263 */           if (i < this.eventStats.relics_lost.size() - 1) {
/* 264 */             sb.append(" NL ");
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 270 */       if (this.eventStats.max_hp_loss != 0) {
/* 271 */         sb.append(" NL ");
/* 272 */         sb.append(" TAB ").append(TEXT_LOST);
/* 273 */         sb.append(String.format(TEXT_GENERIC_MAX_HP_FORMAT, new Object[] { Integer.valueOf(this.eventStats.max_hp_loss) }));
/*     */       } 
/*     */ 
/*     */       
/* 277 */       if (this.eventStats.damage_taken != 0) {
/* 278 */         sb.append(" NL ");
/* 279 */         sb.append(" TAB ").append(TEXT_TOOK);
/* 280 */         sb.append(String.format(TEXT_EVENT_DAMAGE, new Object[] { Integer.valueOf(this.eventStats.damage_taken) }));
/*     */       } 
/*     */ 
/*     */       
/* 284 */       if (this.eventStats.gold_loss != 0) {
/* 285 */         sb.append(" NL ");
/* 286 */         sb.append(" TAB ").append(TEXT_SPENT);
/* 287 */         sb.append(String.format(TEXT_GOLD_FORMAT, new Object[] { Integer.valueOf(this.eventStats.gold_loss) }));
/*     */       } 
/*     */ 
/*     */       
/* 291 */       if (this.eventStats.max_hp_gain != 0) {
/* 292 */         sb.append(" NL ");
/* 293 */         sb.append(" TAB ").append(TEXT_GAINED);
/* 294 */         sb.append(String.format(TEXT_GENERIC_MAX_HP_FORMAT, new Object[] { Integer.valueOf(this.eventStats.max_hp_gain) }));
/*     */       } 
/*     */ 
/*     */       
/* 298 */       if (this.eventStats.damage_healed != 0) {
/* 299 */         sb.append(" NL ");
/* 300 */         sb.append(" TAB ").append(TEXT_HEALED);
/* 301 */         sb.append(String.format(TEXT_GENERIC_HP_FORMAT, new Object[] { Integer.valueOf(this.eventStats.damage_healed) }));
/*     */       } 
/*     */ 
/*     */       
/* 305 */       if (this.eventStats.gold_gain != 0) {
/* 306 */         sb.append(" NL ");
/* 307 */         sb.append(" TAB ").append(TEXT_GAINED);
/* 308 */         sb.append(String.format(TEXT_GOLD_FORMAT, new Object[] { Integer.valueOf(this.eventStats.gold_gain) }));
/*     */       } 
/*     */ 
/*     */       
/* 312 */       if (this.eventStats.cards_removed != null && !this.eventStats.cards_removed.isEmpty()) {
/* 313 */         sb.append(" NL ");
/* 314 */         for (int i = 0; i < this.eventStats.cards_removed.size(); i++) {
/* 315 */           String cardID = this.eventStats.cards_removed.get(i);
/* 316 */           String cardName = CardLibrary.getCardNameFromMetricID(cardID);
/* 317 */           sb.append(" TAB ").append(String.format(TEXT_REMOVE_OPTION, new Object[] { cardName }));
/* 318 */           if (i < this.eventStats.cards_removed.size() - 1) {
/* 319 */             sb.append(" NL ");
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 325 */       if (this.eventStats.cards_upgraded != null && !this.eventStats.cards_upgraded.isEmpty()) {
/* 326 */         sb.append(" NL ");
/* 327 */         for (int i = 0; i < this.eventStats.cards_upgraded.size(); i++) {
/* 328 */           String cardID = this.eventStats.cards_upgraded.get(i);
/* 329 */           String cardName = CardLibrary.getCardNameFromMetricID(cardID);
/* 330 */           sb.append(" TAB ").append(String.format(TEXT_UPGRADED, new Object[] { cardName }));
/* 331 */           if (i < this.eventStats.cards_upgraded.size() - 1) {
/* 332 */             sb.append(" NL ");
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 338 */       if (this.eventStats.cards_transformed != null && !this.eventStats.cards_transformed.isEmpty()) {
/* 339 */         sb.append(" NL ");
/* 340 */         for (int i = 0; i < this.eventStats.cards_transformed.size(); i++) {
/* 341 */           String cardID = this.eventStats.cards_transformed.get(i);
/* 342 */           String cardName = CardLibrary.getCardNameFromMetricID(cardID);
/* 343 */           sb.append(" TAB ").append(String.format(TEXT_TRANSFORMED, new Object[] { cardName }));
/* 344 */           if (i < this.eventStats.cards_transformed.size() - 1) {
/* 345 */             sb.append(" NL ");
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 350 */       if (((this.eventStats.cards_obtained != null && !this.eventStats.cards_obtained.isEmpty()) || (this.eventStats.relics_obtained != null && 
/* 351 */         !this.eventStats.relics_obtained.isEmpty()) || (this.eventStats.potions_obtained != null && 
/* 352 */         !this.eventStats.potions_obtained.isEmpty())) && this.relicsObtained == null && this.battleStats == null && this.cardChoiceStats == null && this.potionsObtained == null) {
/*     */ 
/*     */ 
/*     */         
/* 356 */         sb.append(" NL ").append(TEXT_OBTAIN_HEADER);
/*     */ 
/*     */         
/* 359 */         if (this.eventStats.relics_obtained != null && !this.eventStats.relics_obtained.isEmpty()) {
/* 360 */           if (sb.length() > 0) {
/* 361 */             sb.append(" NL ");
/*     */           }
/* 363 */           for (int i = 0; i < this.eventStats.relics_obtained.size(); i++) {
/* 364 */             String name = (RelicLibrary.getRelic((String)this.eventStats.relics_obtained.get(i))).name;
/* 365 */             if (i > 0) {
/* 366 */               sb.append(" NL ");
/*     */             }
/* 368 */             sb.append(" TAB ").append(TEXT_OBTAIN_TYPE_RELIC).append(name);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 373 */         if (this.eventStats.cards_obtained != null && !this.eventStats.cards_obtained.isEmpty() && 
/* 374 */           !this.eventStats.cards_obtained.isEmpty() && !this.eventStats.cards_obtained.equals("SKIP")) {
/* 375 */           if (sb.length() > 0) {
/* 376 */             sb.append(" NL ");
/*     */           }
/* 378 */           for (int i = 0; i < this.eventStats.cards_obtained.size(); i++) {
/* 379 */             String name = CardLibrary.getCardNameFromMetricID(this.eventStats.cards_obtained.get(i));
/* 380 */             if (i > 0) {
/* 381 */               sb.append(" NL ");
/*     */             }
/* 383 */             sb.append(" TAB ").append(TEXT_OBTAIN_TYPE_CARD).append(name);
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 389 */         if (this.eventStats.potions_obtained != null && !this.eventStats.potions_obtained.isEmpty()) {
/* 390 */           if (sb.length() > 0) {
/* 391 */             sb.append(" NL ");
/*     */           }
/* 393 */           for (String key : this.eventStats.potions_obtained) {
/* 394 */             sb.append(" TAB ").append(TEXT_OBTAIN_TYPE_POTION).append((PotionHelper.getPotion(key)).name);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 403 */     if (this.battleStats != null) {
/* 404 */       if (sb.length() > 0) {
/* 405 */         sb.append(" NL ");
/*     */       }
/* 407 */       sb.append(localizedEnemyNameForKey(this.battleStats.enemies));
/* 408 */       sb.append(" NL ");
/* 409 */       sb.append(" TAB ").append(String.format(TEXT_DAMAGE_FORMAT, new Object[] { Integer.valueOf(this.battleStats.damage) }));
/* 410 */       sb.append(" NL ");
/* 411 */       sb.append(" TAB ").append(String.format(TEXT_TURNS_FORMAT, new Object[] { Integer.valueOf(this.battleStats.turns) }));
/*     */     } 
/*     */     
/* 414 */     if (this.campfireChoice != null) {
/* 415 */       if (sb.length() > 0) {
/* 416 */         sb.append(" NL ");
/*     */       }
/* 418 */       switch (this.campfireChoice.key) {
/*     */         case "REST":
/* 420 */           sb.append(TEXT_REST_OPTION);
/*     */           break;
/*     */         case "SMITH":
/* 423 */           sb.append(
/* 424 */               String.format(TEXT_SMITH_OPTION, new Object[] { CardLibrary.getCardNameFromMetricID(this.campfireChoice.data) }));
/*     */           break;
/*     */         case "PURGE":
/* 427 */           sb.append(
/* 428 */               String.format(TEXT_TOKE_OPTION, new Object[] { CardLibrary.getCardNameFromMetricID(this.campfireChoice.data) }));
/*     */           break;
/*     */         case "DIG":
/* 431 */           sb.append(TEXT_DIG_OPTION);
/*     */           break;
/*     */         case "LIFT":
/* 434 */           sb.append(String.format(TEXT_LIFT_OPTION, new Object[] { this.campfireChoice.data, Integer.valueOf(3) }));
/*     */           break;
/*     */         case "RECALL":
/* 437 */           sb.append(TEXT_RECALL_OPTION);
/*     */           break;
/*     */         default:
/* 440 */           sb.append(TEXT_MISSING_INFO);
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 445 */     boolean showRelic = (this.relicsObtained != null);
/* 446 */     boolean showPotion = (this.potionsObtained != null);
/* 447 */     boolean showCards = (this.cardChoiceStats != null && !this.cardChoiceStats.picked.equals("SKIP"));
/*     */     
/* 449 */     if (showRelic || showPotion || showCards) {
/* 450 */       sb.append(" NL ").append(TEXT_OBTAIN_HEADER);
/*     */     }
/*     */ 
/*     */     
/* 454 */     if (showRelic) {
/* 455 */       if (sb.length() > 0) {
/* 456 */         sb.append(" NL ");
/*     */       }
/* 458 */       for (int i = 0; i < this.relicsObtained.size(); i++) {
/* 459 */         String name = (RelicLibrary.getRelic((String)this.relicsObtained.get(i))).name;
/* 460 */         if (i > 0) {
/* 461 */           sb.append(" NL ");
/*     */         }
/* 463 */         sb.append(" TAB ").append(TEXT_OBTAIN_TYPE_RELIC).append(name);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 468 */     if (showCards && 
/* 469 */       !this.cardChoiceStats.picked.isEmpty() && !this.cardChoiceStats.picked.equals("SKIP")) {
/*     */       String text;
/* 471 */       if (this.cardChoiceStats.picked.equals("Singing Bowl")) {
/* 472 */         text = TEXT_OBTAIN_TYPE_SPECIAL + TEXT_SINGING_BOWL_CHOICE;
/*     */       } else {
/* 474 */         text = TEXT_OBTAIN_TYPE_CARD + CardLibrary.getCardNameFromMetricID(this.cardChoiceStats.picked);
/*     */       } 
/* 476 */       if (sb.length() > 0) {
/* 477 */         sb.append(" NL ");
/*     */       }
/* 479 */       sb.append(" TAB ").append(text);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 484 */     if (showPotion) {
/* 485 */       if (sb.length() > 0) {
/* 486 */         sb.append(" NL ");
/*     */       }
/* 488 */       for (String key : this.potionsObtained) {
/* 489 */         sb.append(" TAB ").append(TEXT_OBTAIN_TYPE_POTION).append((PotionHelper.getPotion(key)).name);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 494 */     if (this.cardChoiceStats != null) {
/* 495 */       sb.append(" NL ");
/* 496 */       sb.append(TEXT_SKIP_HEADER);
/* 497 */       sb.append(" NL ");
/*     */       
/* 499 */       for (int i = 0; i < this.cardChoiceStats.not_picked.size(); i++) {
/* 500 */         String cardID = this.cardChoiceStats.not_picked.get(i);
/* 501 */         String cardName = CardLibrary.getCardNameFromMetricID(cardID);
/* 502 */         sb.append(" TAB ").append(TEXT_OBTAIN_TYPE_CARD).append(cardName);
/* 503 */         if (i < this.cardChoiceStats.not_picked.size() - 1) {
/* 504 */           sb.append(" NL ");
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 510 */     if (this.shopPurchases != null) {
/* 511 */       sb.append(" NL ").append(TEXT_PURCHASED);
/* 512 */       for (String key : this.shopPurchases) {
/*     */         
/* 514 */         String text = null;
/* 515 */         if (CardLibrary.isACard(key)) {
/* 516 */           text = TEXT_OBTAIN_TYPE_CARD + CardLibrary.getCardNameFromMetricID(key);
/* 517 */         } else if (RelicLibrary.isARelic(key)) {
/* 518 */           text = TEXT_OBTAIN_TYPE_RELIC + (RelicLibrary.getRelic(key)).name;
/* 519 */         } else if (PotionHelper.isAPotion(key)) {
/* 520 */           text = TEXT_OBTAIN_TYPE_POTION + (PotionHelper.getPotion(key)).name;
/*     */         } 
/*     */         
/* 523 */         if (text != null) {
/* 524 */           sb.append(" NL ").append(" TAB ").append(text);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 529 */     if (this.shopPurges != null) {
/* 530 */       for (String key : this.shopPurges) {
/* 531 */         sb.append(" NL ").append(String.format(TEXT_REMOVE_OPTION, new Object[] { CardLibrary.getCardNameFromMetricID(key) }));
/*     */       } 
/*     */     }
/*     */     
/* 535 */     if (sb.length() > 0) {
/* 536 */       this.cachedTooltip = sb.toString();
/* 537 */       return this.cachedTooltip;
/*     */     } 
/* 539 */     return TEXT_MISSING_INFO;
/*     */   }
/*     */   
/*     */   public boolean isHovered() {
/* 543 */     return this.hb.hovered;
/*     */   }
/*     */   
/*     */   public void position(float x, float y) {
/* 547 */     this.hb.move(x, y - ICON_SIZE);
/*     */   }
/*     */   
/*     */   public static float getApproximateWidth() {
/* 551 */     return ICON_SIZE;
/*     */   }
/*     */   
/*     */   public static float getApproximateHeight() {
/* 555 */     return ICON_SIZE;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 559 */     Texture image = imageForType(this.nodeType);
/* 560 */     if (isHovered()) {
/* 561 */       float hoverSize = ICON_SIZE * 2.0F;
/* 562 */       float offset = (hoverSize - ICON_SIZE) / 2.0F;
/* 563 */       sb.draw(image, this.hb.x - offset, this.hb.y - offset, hoverSize, hoverSize);
/*     */     } else {
/* 565 */       sb.draw(image, this.hb.x, this.hb.y, ICON_SIZE, ICON_SIZE);
/*     */     } 
/* 567 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   public String localizedEnemyNameForKey(String enemyId) {
/* 571 */     return MonsterHelper.getEncounterName(enemyId);
/*     */   }
/*     */   
/*     */   public String localizedEventNameForKey(String eventId) {
/* 575 */     return EventHelper.getEventName(eventId);
/*     */   }
/*     */   
/*     */   public PathNodeType pathNodeTypeForRoomKey(String roomKey) {
/* 579 */     switch (roomKey) {
/*     */       case "M":
/* 581 */         return PathNodeType.MONSTER;
/*     */       case "E":
/* 583 */         return PathNodeType.ELITE;
/*     */       case "?":
/* 585 */         return PathNodeType.EVENT;
/*     */       case "B":
/*     */       case "BOSS":
/* 588 */         return PathNodeType.BOSS;
/*     */       case "T":
/* 590 */         return PathNodeType.TREASURE;
/*     */       case "T!":
/* 592 */         return PathNodeType.BOSS_TREASURE;
/*     */       case "R":
/* 594 */         return PathNodeType.CAMPFIRE;
/*     */       case "$":
/* 596 */         return PathNodeType.SHOP;
/*     */       case "?(M)":
/* 598 */         return PathNodeType.UNKNOWN_MONSTER;
/*     */       case "?($)":
/* 600 */         return PathNodeType.UNKNOWN_SHOP;
/*     */       case "?(T)":
/* 602 */         return PathNodeType.UNKNOWN_TREASURE;
/*     */       
/*     */       case "?(_)":
/* 605 */         return PathNodeType.EVENT;
/*     */       case "<3":
/* 607 */         return PathNodeType.HEART;
/*     */     } 
/* 609 */     return PathNodeType.ERROR;
/*     */   }
/*     */ 
/*     */   
/*     */   public Texture imageForType(PathNodeType nodeType) {
/* 614 */     switch (nodeType) {
/*     */       case MONSTER:
/* 616 */         return ImageMaster.RUN_HISTORY_MAP_ICON_MONSTER;
/*     */       case ELITE:
/* 618 */         return ImageMaster.RUN_HISTORY_MAP_ICON_ELITE;
/*     */       case EVENT:
/* 620 */         return ImageMaster.RUN_HISTORY_MAP_ICON_EVENT;
/*     */       case BOSS:
/* 622 */         return ImageMaster.RUN_HISTORY_MAP_ICON_BOSS;
/*     */       case TREASURE:
/* 624 */         return ImageMaster.RUN_HISTORY_MAP_ICON_CHEST;
/*     */       case BOSS_TREASURE:
/* 626 */         return ImageMaster.RUN_HISTORY_MAP_ICON_BOSS_CHEST;
/*     */       case CAMPFIRE:
/* 628 */         return ImageMaster.RUN_HISTORY_MAP_ICON_REST;
/*     */       case SHOP:
/* 630 */         return ImageMaster.RUN_HISTORY_MAP_ICON_SHOP;
/*     */       case UNKNOWN_MONSTER:
/* 632 */         return ImageMaster.RUN_HISTORY_MAP_ICON_UNKNOWN_MONSTER;
/*     */       case UNKNOWN_SHOP:
/* 634 */         return ImageMaster.RUN_HISTORY_MAP_ICON_UNKNOWN_SHOP;
/*     */       case UNKNOWN_TREASURE:
/* 636 */         return ImageMaster.RUN_HISTORY_MAP_ICON_UNKNOWN_CHEST;
/*     */     } 
/* 638 */     return ImageMaster.RUN_HISTORY_MAP_ICON_EVENT;
/*     */   }
/*     */ 
/*     */   
/*     */   private String stringForType() {
/* 643 */     return stringForType(this.nodeType);
/*     */   }
/*     */   
/*     */   private String stringForType(PathNodeType type) {
/* 647 */     switch (type) {
/*     */       case MONSTER:
/* 649 */         return TEXT_MONSTER;
/*     */       case ELITE:
/* 651 */         return TEXT_ELITE;
/*     */       case EVENT:
/* 653 */         return TEXT_EVENT;
/*     */       case BOSS:
/* 655 */         return TEXT_BOSS;
/*     */       case TREASURE:
/* 657 */         return TEXT_TREASURE;
/*     */       case BOSS_TREASURE:
/* 659 */         return TEXT_BOSS_TREASURE;
/*     */       case CAMPFIRE:
/* 661 */         return TEXT_CAMPFIRE;
/*     */       case SHOP:
/* 663 */         return TEXT_SHOP;
/*     */       case UNKNOWN_MONSTER:
/* 665 */         return TEXT_UNKNOWN_MONSTER;
/*     */       case UNKNOWN_SHOP:
/* 667 */         return TEXT_UNKN0WN_SHOP;
/*     */       case UNKNOWN_TREASURE:
/* 669 */         return TEXT_UNKNOWN_TREASURE;
/*     */     } 
/* 671 */     return TEXT_ERROR;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\runHistory\RunPathElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */