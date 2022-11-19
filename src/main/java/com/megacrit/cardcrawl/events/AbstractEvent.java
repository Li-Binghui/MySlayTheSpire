/*     */ package com.megacrit.cardcrawl.events;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.utils.Disposable;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.MonsterHelper;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractEvent
/*     */   implements Disposable
/*     */ {
/*  31 */   private static final Logger logger = LogManager.getLogger(AbstractEvent.class.getName());
/*     */   
/*  33 */   protected Texture img = null;
/*  34 */   public RoomEventDialog roomEventText = new RoomEventDialog();
/*  35 */   public GenericEventDialog imageEventText = new GenericEventDialog();
/*     */   
/*     */   protected float drawX;
/*     */   protected float drawY;
/*  39 */   protected Color imgColor = Color.WHITE.cpy(); protected float imgWidth; protected float imgHeight;
/*  40 */   protected Hitbox hb = null;
/*  41 */   public float panelAlpha = 0.0F;
/*     */   
/*     */   public boolean hideAlpha = false;
/*     */   public boolean hasFocus = false;
/*  45 */   protected String body = null;
/*  46 */   public float waitTimer = 1.5F;
/*     */   protected boolean waitForInput = false;
/*     */   public boolean hasDialog = false;
/*  49 */   protected int screenNum = 0;
/*  50 */   public static EventType type = EventType.IMAGE;
/*     */   
/*     */   public static String NAME;
/*     */   
/*     */   public static String[] DESCRIPTIONS;
/*     */   
/*     */   public static String[] OPTIONS;
/*     */   
/*     */   public boolean combatTime = false;
/*     */   public boolean noCardsInRewards = false;
/*  60 */   public ArrayList<Integer> optionsSelected = new ArrayList<>();
/*     */   
/*     */   public enum EventType {
/*  63 */     TEXT, IMAGE, ROOM;
/*     */   }
/*     */   
/*     */   public AbstractEvent() {
/*  67 */     type = EventType.ROOM;
/*  68 */     if (Settings.FAST_MODE) {
/*  69 */       this.waitTimer = 0.1F;
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
/*     */   protected void initializeImage(String imgUrl, float x, float y) {
/*  81 */     this.img = ImageMaster.loadImage(imgUrl);
/*  82 */     this.drawX = x;
/*  83 */     this.drawY = y;
/*  84 */     this.imgWidth = this.img.getWidth() * Settings.xScale;
/*  85 */     this.imgHeight = this.img.getHeight() * Settings.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {}
/*     */   
/*     */   public void enterCombat() {
/*  92 */     this.roomEventText.clear();
/*  93 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMBAT;
/*  94 */     (AbstractDungeon.getCurrRoom()).monsters.init();
/*  95 */     AbstractRoom.waitTimer = 0.1F;
/*  96 */     AbstractDungeon.player.preBattlePrep();
/*  97 */     this.hasFocus = false;
/*  98 */     this.roomEventText.hide();
/*     */   }
/*     */   
/*     */   protected abstract void buttonEffect(int paramInt);
/*     */   
/*     */   public void updateDialog() {
/* 104 */     this.imageEventText.update();
/* 105 */     this.roomEventText.update();
/*     */   }
/*     */   
/*     */   public void update() {
/* 109 */     if (this.waitTimer > 0.0F) {
/* 110 */       this.waitTimer -= Gdx.graphics.getDeltaTime();
/* 111 */       if (this.waitTimer < 0.0F && this.hasDialog) {
/* 112 */         this.roomEventText.show(this.body);
/* 113 */         this.waitTimer = 0.0F;
/*     */       }
/*     */     
/* 116 */     } else if ((AbstractDungeon.getCurrRoom()).phase != AbstractRoom.RoomPhase.COMBAT && !this.hideAlpha) {
/* 117 */       this.panelAlpha = MathHelper.fadeLerpSnap(this.panelAlpha, 0.66F);
/*     */     } else {
/* 119 */       this.panelAlpha = MathHelper.fadeLerpSnap(this.panelAlpha, 0.0F);
/*     */     } 
/*     */ 
/*     */     
/* 123 */     if (!RoomEventDialog.waitForInput) {
/* 124 */       buttonEffect(this.roomEventText.getSelectedOption());
/*     */     }
/*     */   }
/*     */   
/*     */   public void logInput(int buttonPressed) {
/* 129 */     this.optionsSelected.add(Integer.valueOf(buttonPressed));
/*     */   }
/*     */   
/*     */   protected void openMap() {
/* 133 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 134 */     AbstractDungeon.dungeonMapScreen.open(false);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 138 */     if (this.img != null) {
/* 139 */       sb.setColor(this.imgColor);
/* 140 */       sb.draw(this.img, this.drawX, this.drawY, this.imgWidth, this.imgHeight);
/*     */     } 
/*     */     
/* 143 */     if (this.hb != null) {
/* 144 */       this.hb.render(sb);
/* 145 */       if (this.img != null && this.hb.hovered) {
/* 146 */         sb.setBlendFunction(770, 1);
/* 147 */         sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.3F));
/* 148 */         sb.draw(this.img, this.drawX, this.drawY, this.imgWidth, this.imgHeight);
/* 149 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderText(SpriteBatch sb) {
/* 155 */     this.roomEventText.render(sb);
/* 156 */     this.imageEventText.render(sb);
/*     */   }
/*     */   
/*     */   public void renderRoomEventPanel(SpriteBatch sb) {
/* 160 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.panelAlpha));
/* 161 */     sb.draw(ImageMaster.EVENT_ROOM_PANEL, 0.0F, Settings.HEIGHT - 475.0F * Settings.scale, Settings.WIDTH, 300.0F * Settings.scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void showProceedScreen(String bodyText) {
/* 170 */     this.roomEventText.updateBodyText(bodyText);
/* 171 */     this.roomEventText.updateDialogOption(0, "[ #bProceed ]");
/* 172 */     this.roomEventText.clearRemainingOptions();
/* 173 */     this.screenNum = 99;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderAboveTopPanel(SpriteBatch sb) {}
/*     */ 
/*     */   
/*     */   public void reopen() {}
/*     */   
/*     */   public void postCombatLoad() {
/* 183 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMBAT;
/* 184 */     (AbstractDungeon.getCurrRoom()).isBattleOver = true;
/* 185 */     (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("Colosseum Nobs");
/* 186 */     this.hasFocus = false;
/* 187 */     GenericEventDialog.hide();
/* 188 */     AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;
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
/*     */   public static void logMetric(String eventName, String playerChoice, List<String> cardsObtained, List<String> cardsRemoved, List<String> cardsTransformed, List<String> cardsUpgraded, List<String> relicsObtained, List<String> potionsObtained, List<String> relicsLost, int damageTaken, int damageHealed, int hpLoss, int hpGain, int goldGain, int goldLoss) {
/* 208 */     HashMap<String, Object> choice = new HashMap<>();
/* 209 */     choice.put("event_name", eventName);
/* 210 */     choice.put("player_choice", playerChoice);
/* 211 */     choice.put("floor", Integer.valueOf(AbstractDungeon.floorNum));
/*     */ 
/*     */ 
/*     */     
/* 215 */     choice.put("cards_obtained", cardsObtained);
/* 216 */     choice.put("cards_removed", cardsRemoved);
/* 217 */     choice.put("cards_transformed", cardsTransformed);
/* 218 */     choice.put("cards_upgraded", cardsUpgraded);
/* 219 */     choice.put("relics_obtained", relicsObtained);
/* 220 */     choice.put("potions_obtained", potionsObtained);
/* 221 */     choice.put("relics_lost", relicsLost);
/*     */     
/* 223 */     choice.put("damage_taken", Integer.valueOf(damageTaken));
/* 224 */     choice.put("damage_healed", Integer.valueOf(damageHealed));
/* 225 */     choice.put("max_hp_loss", Integer.valueOf(hpLoss));
/* 226 */     choice.put("max_hp_gain", Integer.valueOf(hpGain));
/* 227 */     choice.put("gold_gain", Integer.valueOf(goldGain));
/* 228 */     choice.put("gold_loss", Integer.valueOf(goldLoss));
/* 229 */     CardCrawlGame.metricData.event_choices.add(choice);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricTransformCardsAtCost(String eventName, String playerChoice, List<String> cardsTransformed, List<String> cardsObtained, int cost) {
/* 238 */     logMetric(eventName, playerChoice, cardsObtained, null, cardsTransformed, null, null, null, null, 0, 0, 0, 0, 0, cost);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricRemoveCardsAtCost(String eventName, String playerChoice, List<String> cardsRemoved, int cost) {
/* 261 */     logMetric(eventName, playerChoice, null, cardsRemoved, null, null, null, null, null, 0, 0, 0, 0, 0, cost);
/*     */   }
/*     */   
/*     */   public static void logMetricRemoveCards(String eventName, String playerChoice, List<String> cardsRemoved) {
/* 265 */     logMetricRemoveCardsAtCost(eventName, playerChoice, cardsRemoved, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricObtainCardsLoseMapHP(String eventName, String playerChoice, List<String> cardsObtained, int maxHPLoss) {
/* 273 */     logMetric(eventName, playerChoice, cardsObtained, null, null, null, null, null, null, 0, 0, maxHPLoss, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricObtainCardsLoseRelic(String eventName, String playerChoice, List<String> cardsObtained, AbstractRelic relicLost) {
/* 281 */     List<String> tempList2 = new ArrayList<>();
/* 282 */     tempList2.add(relicLost.relicId);
/* 283 */     logMetric(eventName, playerChoice, cardsObtained, null, null, null, null, null, tempList2, 0, 0, 0, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricObtainCards(String eventName, String playerChoice, List<String> cardsObtained) {
/* 287 */     logMetricObtainCardsLoseMapHP(eventName, playerChoice, cardsObtained, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricUpgradeCardsAtCost(String eventName, String playerChoice, List<String> cardsUpgraded, int cost) {
/* 295 */     logMetric(eventName, playerChoice, null, null, null, cardsUpgraded, null, null, null, 0, 0, 0, 0, 0, cost);
/*     */   }
/*     */   
/*     */   public static void logMetricUpgradeCards(String eventName, String playerChoice, List<String> cardsUpgraded) {
/* 299 */     logMetricUpgradeCardsAtCost(eventName, playerChoice, cardsUpgraded, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricTransformCards(String eventName, String playerChoice, List<String> cardsTransformed, List<String> cardsObtained) {
/* 307 */     logMetricTransformCardsAtCost(eventName, playerChoice, cardsTransformed, cardsObtained, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricGainGoldAndDamage(String eventName, String playerChoice, int gold, int damage) {
/* 311 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, damage, 0, 0, 0, gold, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricGainGoldAndRelic(String eventName, String playerChoice, AbstractRelic relicGained, int gold) {
/* 319 */     List<String> tempList2 = new ArrayList<>();
/* 320 */     tempList2.add(relicGained.relicId);
/* 321 */     logMetric(eventName, playerChoice, null, null, null, null, tempList2, null, null, 0, 0, 0, 0, gold, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricGainGoldAndLoseRelic(String eventName, String playerChoice, AbstractRelic relicLost, int gold) {
/* 329 */     List<String> tempList2 = new ArrayList<>();
/* 330 */     tempList2.add(relicLost.relicId);
/* 331 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, tempList2, 0, 0, 0, 0, gold, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricGainGoldAndCard(String eventName, String playerChoice, AbstractCard cardGained, int gold) {
/* 339 */     List<String> tempList2 = new ArrayList<>();
/* 340 */     tempList2.add(cardGained.cardID);
/* 341 */     logMetric(eventName, playerChoice, tempList2, null, null, null, null, null, null, 0, 0, 0, 0, gold, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricObtainRelicAndLoseMaxHP(String eventName, String playerChoice, AbstractRelic relicGained, int hpLoss) {
/* 349 */     List<String> tempList2 = new ArrayList<>();
/* 350 */     tempList2.add(relicGained.relicId);
/* 351 */     logMetric(eventName, playerChoice, null, null, null, null, tempList2, null, null, 0, 0, hpLoss, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricObtainRelicAndDamage(String eventName, String playerChoice, AbstractRelic relicGained, int damage) {
/* 359 */     List<String> tempList2 = new ArrayList<>();
/* 360 */     tempList2.add(relicGained.relicId);
/* 361 */     logMetric(eventName, playerChoice, null, null, null, null, tempList2, null, null, damage, 0, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricObtainRelicAtCost(String eventName, String playerChoice, AbstractRelic relicGained, int cost) {
/* 369 */     List<String> tempList2 = new ArrayList<>();
/* 370 */     tempList2.add(relicGained.relicId);
/* 371 */     logMetric(eventName, playerChoice, null, null, null, null, tempList2, null, null, 0, 0, 0, 0, 0, cost);
/*     */   }
/*     */   
/*     */   public static void logMetricGainAndLoseGold(String eventName, String playerChoice, int goldGain, int goldLoss) {
/* 375 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, 0, 0, 0, 0, goldGain, goldLoss);
/*     */   }
/*     */   
/*     */   public static void logMetricGainGold(String eventName, String playerChoice, int gold) {
/* 379 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, 0, 0, 0, 0, gold, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricLoseGold(String eventName, String playerChoice, int gold) {
/* 383 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, gold);
/*     */   }
/*     */   
/*     */   public static void logMetricTakeDamage(String eventName, String playerChoice, int damage) {
/* 387 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, damage, 0, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricCardRemovalAtCost(String eventName, String playerChoice, AbstractCard cardRemoved, int cost) {
/* 395 */     List<String> tempList = new ArrayList<>();
/* 396 */     tempList.add(cardRemoved.cardID);
/* 397 */     logMetric(eventName, playerChoice, null, tempList, null, null, null, null, null, 0, 0, 0, 0, 0, cost);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricCardRemovalAndDamage(String eventName, String playerChoice, AbstractCard cardRemoved, int damage) {
/* 405 */     List<String> tempList = new ArrayList<>();
/* 406 */     tempList.add(cardRemoved.cardID);
/* 407 */     logMetric(eventName, playerChoice, null, tempList, null, null, null, null, null, damage, 0, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricCardRemovalHealMaxHPUp(String eventName, String playerChoice, AbstractCard cardRemoved, int heal, int maxUp) {
/* 416 */     List<String> tempList = new ArrayList<>();
/* 417 */     tempList.add(cardRemoved.cardID);
/* 418 */     logMetric(eventName, playerChoice, null, tempList, null, null, null, null, null, 0, heal, 0, maxUp, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricCardRemovalAndHeal(String eventName, String playerChoice, AbstractCard cardRemoved, int heal) {
/* 426 */     logMetricCardRemovalHealMaxHPUp(eventName, playerChoice, cardRemoved, heal, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricCardRemoval(String eventName, String playerChoice, AbstractCard cardRemoved) {
/* 430 */     logMetricCardRemovalAtCost(eventName, playerChoice, cardRemoved, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricCardUpgradeAndRemovalAtCost(String eventName, String playerChoice, AbstractCard cardUpgraded, AbstractCard cardRemoved, int cost) {
/* 439 */     List<String> tempList = new ArrayList<>();
/* 440 */     tempList.add(cardUpgraded.cardID);
/*     */     
/* 442 */     List<String> tempList2 = new ArrayList<>();
/* 443 */     tempList2.add(cardRemoved.cardID);
/* 444 */     logMetric(eventName, playerChoice, null, tempList2, null, tempList, null, null, null, 0, 0, 0, 0, 0, cost);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricCardUpgradeAndRemoval(String eventName, String playerChoice, AbstractCard cardUpgraded, AbstractCard cardRemoved) {
/* 452 */     logMetricCardUpgradeAndRemovalAtCost(eventName, playerChoice, cardUpgraded, cardRemoved, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricCardUpgradeAtCost(String eventName, String playerChoice, AbstractCard cardUpgraded, int cost) {
/* 460 */     List<String> tempList = new ArrayList<>();
/* 461 */     tempList.add(cardUpgraded.cardID);
/* 462 */     logMetric(eventName, playerChoice, null, null, null, tempList, null, null, null, 0, 0, 0, 0, 0, cost);
/*     */   }
/*     */   
/*     */   public static void logMetricCardUpgrade(String eventName, String playerChoice, AbstractCard cardUpgraded) {
/* 466 */     logMetricCardUpgradeAtCost(eventName, playerChoice, cardUpgraded, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricHealAtCost(String eventName, String playerChoice, int cost, int healAmount) {
/* 470 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, 0, healAmount, 0, 0, 0, cost);
/*     */   }
/*     */   
/*     */   public static void logMetricHealAndLoseMaxHP(String eventName, String playerChoice, int healAmount, int maxHPLoss) {
/* 474 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, 0, healAmount, maxHPLoss, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricHeal(String eventName, String playerChoice, int healAmount) {
/* 478 */     logMetricHealAtCost(eventName, playerChoice, 0, healAmount);
/*     */   }
/*     */   
/*     */   public static void logMetric(String eventName, String playerChoice) {
/* 482 */     logMetricHeal(eventName, playerChoice, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricIgnored(String eventName) {
/* 486 */     logMetric(eventName, "Ignored");
/*     */   }
/*     */   
/*     */   public static void logMetricMaxHPGain(String eventName, String playerChoice, int maxHPAmount) {
/* 490 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, 0, 0, 0, maxHPAmount, 0, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricMaxHPLoss(String eventName, String playerChoice, int hpLoss) {
/* 494 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, 0, 0, hpLoss, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricDamageAndMaxHPGain(String eventName, String playerChoice, int damage, int maxHPAmount) {
/* 498 */     logMetric(eventName, playerChoice, null, null, null, null, null, null, null, damage, 0, 0, maxHPAmount, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricObtainCardAndHeal(String eventName, String playerChoice, AbstractCard cardGained, int heal) {
/* 506 */     List<String> tempList = new ArrayList<>();
/* 507 */     tempList.add(cardGained.cardID);
/* 508 */     logMetric(eventName, playerChoice, tempList, null, null, null, null, null, null, 0, heal, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricObtainCardAndDamage(String eventName, String playerChoice, AbstractCard cardGained, int damage) {
/* 516 */     List<String> tempList = new ArrayList<>();
/* 517 */     tempList.add(cardGained.cardID);
/* 518 */     logMetric(eventName, playerChoice, tempList, null, null, null, null, null, null, damage, 0, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricObtainCardAndLoseCard(String eventName, String playerChoice, AbstractCard cardGained, AbstractCard cardLost) {
/* 526 */     List<String> tempList = new ArrayList<>();
/* 527 */     tempList.add(cardGained.cardID);
/*     */     
/* 529 */     List<String> tempList2 = new ArrayList<>();
/* 530 */     tempList2.add(cardLost.cardID);
/* 531 */     logMetric(eventName, playerChoice, tempList, tempList2, null, null, null, null, null, 0, 0, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricObtainCardAndRelic(String eventName, String playerChoice, AbstractCard cardGained, AbstractRelic relicGained) {
/* 539 */     List<String> tempList = new ArrayList<>();
/* 540 */     tempList.add(cardGained.cardID);
/*     */     
/* 542 */     List<String> tempList2 = new ArrayList<>();
/* 543 */     tempList2.add(relicGained.relicId);
/* 544 */     logMetric(eventName, playerChoice, tempList, null, null, null, tempList2, null, null, 0, 0, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricRemoveCardAndObtainRelic(String eventName, String playerChoice, AbstractCard cardRemoved, AbstractRelic relicGained) {
/* 552 */     List<String> tempList = new ArrayList<>();
/* 553 */     tempList.add(cardRemoved.cardID);
/*     */     
/* 555 */     List<String> tempList2 = new ArrayList<>();
/* 556 */     tempList2.add(relicGained.relicId);
/* 557 */     logMetric(eventName, playerChoice, null, tempList, null, null, tempList2, null, null, 0, 0, 0, 0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricTransformCardAtCost(String eventName, String playerChoice, AbstractCard cardTransformed, AbstractCard cardGained, int cost) {
/* 566 */     List<String> tempList = new ArrayList<>();
/* 567 */     tempList.add(cardTransformed.cardID);
/*     */     
/* 569 */     List<String> tempList2 = new ArrayList<>();
/* 570 */     tempList2.add(cardGained.cardID);
/*     */     
/* 572 */     logMetric(eventName, playerChoice, tempList2, null, tempList, null, null, null, null, 0, 0, 0, 0, 0, cost);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricTransformCard(String eventName, String playerChoice, AbstractCard cardTransformed, AbstractCard cardGained) {
/* 580 */     logMetricTransformCardAtCost(eventName, playerChoice, cardTransformed, cardGained, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logMetricRelicSwap(String eventName, String playerChoice, AbstractRelic relicGained, AbstractRelic relicLost) {
/* 588 */     List<String> tempList = new ArrayList<>();
/* 589 */     tempList.add(relicGained.relicId);
/*     */     
/* 591 */     List<String> tempList2 = new ArrayList<>();
/* 592 */     tempList2.add(relicLost.relicId);
/* 593 */     logMetric(eventName, playerChoice, null, null, null, null, tempList, null, tempList2, 0, 0, 0, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricObtainRelic(String eventName, String playerChoice, AbstractRelic relicGained) {
/* 597 */     List<String> tempList = new ArrayList<>();
/* 598 */     tempList.add(relicGained.relicId);
/* 599 */     logMetric(eventName, playerChoice, null, null, null, null, tempList, null, null, 0, 0, 0, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public static void logMetricObtainCard(String eventName, String playerChoice, AbstractCard cardGained) {
/* 603 */     List<String> tempList = new ArrayList<>();
/* 604 */     tempList.add(cardGained.cardID);
/* 605 */     logMetric(eventName, playerChoice, tempList, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0);
/*     */   }
/*     */   
/*     */   public HashMap<String, Serializable> getLocStrings() {
/* 609 */     HashMap<String, Serializable> data = new HashMap<>();
/* 610 */     data.put("name", NAME);
/* 611 */     data.put("moves", DESCRIPTIONS);
/* 612 */     data.put("dialogs", OPTIONS);
/* 613 */     return data;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 618 */     if (this.img != null) {
/* 619 */       logger.info("Disposed event img asset");
/* 620 */       this.img.dispose();
/* 621 */       this.img = null;
/*     */     } 
/* 623 */     this.imageEventText.clear();
/* 624 */     this.roomEventText.clear();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\AbstractEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */