/*     */ package com.megacrit.cardcrawl.rewards;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.FtueTip;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ObtainKeyEffect;
import com.megacrit.cardcrawl.vfx.RewardGlowEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
/*     */ public class RewardItem
/*     */ {
/*  50 */   private static final Logger logger = LogManager.getLogger(RewardItem.class.getName());
/*  51 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RewardItem");
/*  52 */   public static final String[] TEXT = uiStrings.TEXT;
/*  53 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Potion Tip");
/*  54 */   public static final String[] MSG = tutorialStrings.TEXT;
/*  55 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*     */   
/*     */   public RewardType type;
/*  58 */   public Texture outlineImg = null; public Texture img = null;
/*  59 */   public int goldAmt = 0;
/*  60 */   public int bonusGold = 0;
/*     */   public String text;
/*     */   public RewardItem relicLink;
/*     */   public AbstractRelic relic;
/*     */   public AbstractPotion potion;
/*     */   public ArrayList<AbstractCard> cards;
/*  66 */   private ArrayList<AbstractGameEffect> effects = new ArrayList<>();
/*     */   private boolean isBoss;
/*  68 */   public Hitbox hb = new Hitbox(460.0F * Settings.xScale, 90.0F * Settings.yScale); public float y;
/*  69 */   public float flashTimer = 0.0F;
/*     */   
/*     */   public boolean isDone = false;
/*     */   public boolean ignoreReward = false;
/*     */   public boolean redText = false;
/*  74 */   public static final float REWARD_ITEM_X = Settings.WIDTH * 0.41F; private static final float FLASH_DUR = 0.5F; private static final int ITEM_W = 464; private static final int ITEM_H = 98;
/*  75 */   private static final float REWARD_TEXT_X = Settings.WIDTH * 0.434F;
/*     */ 
/*     */   
/*  78 */   private Color reticleColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   
/*     */   public enum RewardType {
/*  81 */     CARD, GOLD, RELIC, POTION, STOLEN_GOLD, EMERALD_KEY, SAPPHIRE_KEY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RewardItem(RewardItem setRelicLink, RewardType type) {
/*  88 */     this.type = type;
/*     */     
/*  90 */     if (type == RewardType.SAPPHIRE_KEY) {
/*  91 */       this.text = TEXT[6];
/*  92 */       this.img = ImageMaster.loadImage("images/relics/sapphire_key.png");
/*  93 */       this.outlineImg = ImageMaster.loadImage("images/relics/outline/sapphire_key.png");
/*  94 */       this.relicLink = setRelicLink;
/*  95 */       setRelicLink.relicLink = this;
/*  96 */     } else if (type == RewardType.EMERALD_KEY) {
/*  97 */       this.text = TEXT[5];
/*  98 */       this.img = ImageMaster.loadImage("images/relics/emerald_key.png");
/*  99 */       this.outlineImg = ImageMaster.loadImage("images/relics/outline/emerald_key.png");
/*     */     } 
/*     */   }
/*     */   
/*     */   public RewardItem(int gold) {
/* 104 */     this.type = RewardType.GOLD;
/* 105 */     this.goldAmt = gold;
/* 106 */     applyGoldBonus(false);
/*     */   }
/*     */   
/*     */   public RewardItem(int gold, boolean theft) {
/* 110 */     this.type = RewardType.STOLEN_GOLD;
/* 111 */     this.goldAmt = gold;
/* 112 */     applyGoldBonus(theft);
/*     */   }
/*     */   
/*     */   private void applyGoldBonus(boolean theft) {
/* 116 */     int tmp = this.goldAmt;
/* 117 */     this.bonusGold = 0;
/*     */     
/* 119 */     if (theft) {
/* 120 */       this.text = this.goldAmt + TEXT[0];
/*     */     } else {
/* 122 */       if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.TreasureRoom)) {
/* 123 */         if (AbstractDungeon.player.hasRelic("Golden Idol")) {
/* 124 */           this.bonusGold += MathUtils.round(tmp * 0.25F);
/*     */         }
/*     */         
/* 127 */         if (ModHelper.isModEnabled("Midas")) {
/* 128 */           this.bonusGold += MathUtils.round(tmp * 2.0F);
/*     */         }
/*     */         
/* 131 */         if (ModHelper.isModEnabled("MonsterHunter")) {
/* 132 */           this.bonusGold += MathUtils.round(tmp * 1.5F);
/*     */         }
/*     */       } 
/*     */       
/* 136 */       if (this.bonusGold == 0) {
/* 137 */         this.text = this.goldAmt + TEXT[1];
/*     */       } else {
/* 139 */         this.text = this.goldAmt + TEXT[1] + " (" + this.bonusGold + ")";
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public RewardItem(AbstractRelic relic) {
/* 145 */     this.type = RewardType.RELIC;
/* 146 */     this.relic = relic;
/* 147 */     relic.hb = new Hitbox(80.0F * Settings.scale, 80.0F * Settings.scale);
/* 148 */     relic.hb.move(-1000.0F, -1000.0F);
/* 149 */     this.text = relic.name;
/*     */   }
/*     */   
/*     */   public RewardItem(AbstractPotion potion) {
/* 153 */     this.type = RewardType.POTION;
/* 154 */     this.potion = potion;
/* 155 */     this.text = potion.name;
/*     */   }
/*     */   
/*     */   public RewardItem() {
/* 159 */     this.type = RewardType.CARD;
/* 160 */     this.isBoss = AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
/* 161 */     this.cards = AbstractDungeon.getRewardCards();
/* 162 */     this.text = TEXT[2];
/*     */   }
/*     */   
/*     */   public RewardItem(AbstractCard.CardColor colorType) {
/* 166 */     this.type = RewardType.CARD;
/* 167 */     this.isBoss = AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
/* 168 */     if (colorType == AbstractCard.CardColor.COLORLESS) {
/* 169 */       this.cards = AbstractDungeon.getColorlessRewardCards();
/*     */     } else {
/* 171 */       this.cards = AbstractDungeon.getRewardCards();
/*     */     } 
/* 173 */     this.text = TEXT[2];
/*     */     
/* 175 */     for (AbstractCard c : this.cards) {
/* 176 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 177 */         r.onPreviewObtainCard(c);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementGold(int gold) {
/* 188 */     if (this.type == RewardType.GOLD) {
/* 189 */       this.goldAmt += gold;
/* 190 */       applyGoldBonus(false);
/* 191 */     } else if (this.type == RewardType.STOLEN_GOLD) {
/* 192 */       this.goldAmt += gold;
/* 193 */       applyGoldBonus(true);
/*     */     } else {
/* 195 */       logger.info("ERROR: Using increment() wrong for RewardItem");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void move(float y) {
/* 200 */     this.y = y;
/* 201 */     this.hb.move(Settings.WIDTH / 2.0F, y);
/* 202 */     if (this.type == RewardType.POTION) {
/* 203 */       this.potion.move(REWARD_ITEM_X, y);
/* 204 */     } else if (this.type == RewardType.RELIC) {
/* 205 */       this.relic.currentX = REWARD_ITEM_X;
/* 206 */       this.relic.currentY = y;
/* 207 */       this.relic.targetX = REWARD_ITEM_X;
/* 208 */       this.relic.targetY = y;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void flash() {
/* 213 */     this.flashTimer = 0.5F;
/*     */   }
/*     */   
/*     */   public void update() {
/* 217 */     if (this.flashTimer > 0.0F) {
/* 218 */       this.flashTimer -= Gdx.graphics.getDeltaTime();
/* 219 */       if (this.flashTimer < 0.0F) {
/* 220 */         this.flashTimer = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 224 */     this.hb.update();
/* 225 */     updateReticle();
/*     */     
/* 227 */     if (this.effects.size() == 0) {
/* 228 */       this.effects.add(new RewardGlowEffect(this.hb.cX, this.hb.cY));
/*     */     }
/* 230 */     for (Iterator<AbstractGameEffect> i = this.effects.iterator(); i.hasNext(); ) {
/* 231 */       AbstractGameEffect e = i.next();
/* 232 */       e.update();
/* 233 */       if (e.isDone) {
/* 234 */         i.remove();
/*     */       }
/*     */     } 
/*     */     
/* 238 */     if (this.hb.hovered) {
/* 239 */       switch (this.type) {
/*     */         case POTION:
/* 241 */           if (!AbstractDungeon.topPanel.potionCombine) {
/* 242 */             TipHelper.queuePowerTips(360.0F * Settings.scale, InputHelper.mY, this.potion.tips);
/*     */           }
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 250 */     if (this.relicLink != null) {
/* 251 */       this.relicLink.redText = this.hb.hovered;
/*     */     }
/*     */     
/* 254 */     if (this.hb.justHovered) {
/* 255 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/* 258 */     if (this.hb.hovered && InputHelper.justClickedLeft && !this.isDone) {
/* 259 */       CardCrawlGame.sound.playA("UI_CLICK_1", 0.1F);
/* 260 */       this.hb.clickStarted = true;
/*     */     } 
/*     */     
/* 263 */     if (this.hb.hovered && CInputActionSet.select.isJustPressed() && !this.isDone) {
/* 264 */       this.hb.clicked = true;
/* 265 */       CardCrawlGame.sound.playA("UI_CLICK_1", 0.1F);
/*     */     } 
/*     */     
/* 268 */     if (this.hb.clicked) {
/* 269 */       this.hb.clicked = false;
/* 270 */       this.isDone = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateReticle() {
/* 275 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 279 */     if (this.hb.hovered) {
/* 280 */       this.reticleColor.a += Gdx.graphics.getDeltaTime() * 3.0F;
/* 281 */       if (this.reticleColor.a > 1.0F) {
/* 282 */         this.reticleColor.a = 1.0F;
/*     */       }
/*     */     } else {
/* 285 */       this.reticleColor.a = 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean claimReward() {
/* 290 */     switch (this.type) {
/*     */       case GOLD:
/* 292 */         CardCrawlGame.sound.play("GOLD_GAIN");
/* 293 */         if (this.bonusGold == 0) {
/* 294 */           AbstractDungeon.player.gainGold(this.goldAmt);
/*     */         } else {
/* 296 */           AbstractDungeon.player.gainGold(this.goldAmt + this.bonusGold);
/*     */         } 
/* 298 */         return true;
/*     */       case STOLEN_GOLD:
/* 300 */         CardCrawlGame.sound.play("GOLD_GAIN");
/* 301 */         if (this.bonusGold == 0) {
/* 302 */           AbstractDungeon.player.gainGold(this.goldAmt);
/*     */         } else {
/* 304 */           AbstractDungeon.player.gainGold(this.goldAmt + this.bonusGold);
/*     */         } 
/* 306 */         return true;
/*     */       case POTION:
/* 308 */         if (AbstractDungeon.player.hasRelic("Sozu")) {
/* 309 */           AbstractDungeon.player.getRelic("Sozu").flash();
/* 310 */           return true;
/*     */         } 
/* 312 */         if (AbstractDungeon.player.obtainPotion(this.potion)) {
/* 313 */           if (!((Boolean)TipTracker.tips.get("POTION_TIP")).booleanValue()) {
/* 314 */             AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, this.potion);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 320 */             TipTracker.neverShowAgain("POTION_TIP");
/*     */           } 
/* 322 */           CardCrawlGame.metricData.addPotionObtainData(this.potion);
/* 323 */           return true;
/*     */         } 
/*     */         
/* 326 */         return false;
/*     */       case RELIC:
/* 328 */         if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
/* 329 */           return false;
/*     */         }
/*     */         
/* 332 */         if (!this.ignoreReward) {
/* 333 */           this.relic.instantObtain();
/* 334 */           CardCrawlGame.metricData.addRelicObtainData(this.relic);
/*     */         } 
/*     */         
/* 337 */         if (this.relicLink != null) {
/* 338 */           this.relicLink.isDone = true;
/* 339 */           this.relicLink.ignoreReward = true;
/*     */         } 
/*     */         
/* 342 */         return true;
/*     */       case CARD:
/* 344 */         if (AbstractDungeon.player.hasRelic("Question Card")) {
/* 345 */           AbstractDungeon.player.getRelic("Question Card").flash();
/*     */         }
/*     */         
/* 348 */         if (AbstractDungeon.player.hasRelic("Busted Crown")) {
/* 349 */           AbstractDungeon.player.getRelic("Busted Crown").flash();
/*     */         }
/*     */         
/* 352 */         if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/* 353 */           AbstractDungeon.cardRewardScreen.open(this.cards, this, TEXT[4]);
/* 354 */           AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/*     */         } 
/* 356 */         return false;
/*     */       case SAPPHIRE_KEY:
/* 358 */         if (!this.ignoreReward) {
/* 359 */           AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.BLUE));
/*     */         }
/*     */ 
/*     */         
/* 363 */         this.relicLink.isDone = true;
/* 364 */         this.relicLink.ignoreReward = true;
/* 365 */         this.img.dispose();
/* 366 */         this.outlineImg.dispose();
/* 367 */         return true;
/*     */       case EMERALD_KEY:
/* 369 */         AbstractDungeon.topLevelEffects.add(new ObtainKeyEffect(ObtainKeyEffect.KeyColor.GREEN));
/* 370 */         this.img.dispose();
/* 371 */         this.outlineImg.dispose();
/* 372 */         return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 377 */     logger.info("ERROR: Claim Reward() failed");
/* 378 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*     */     Color color;
/* 385 */     if (this.hb.hovered) {
/* 386 */       sb.setColor(new Color(0.4F, 0.6F, 0.6F, 1.0F));
/*     */     } else {
/* 388 */       sb.setColor(new Color(0.5F, 0.6F, 0.6F, 0.8F));
/*     */     } 
/*     */     
/* 391 */     if (this.hb.clickStarted) {
/* 392 */       sb.draw(ImageMaster.REWARD_SCREEN_ITEM, Settings.WIDTH / 2.0F - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.xScale * 0.98F, Settings.scale * 0.98F, 0.0F, 0, 0, 464, 98, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 410 */       sb.draw(ImageMaster.REWARD_SCREEN_ITEM, Settings.WIDTH / 2.0F - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.xScale, Settings.scale, 0.0F, 0, 0, 464, 98, false, false);
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
/* 429 */     if (this.flashTimer != 0.0F) {
/* 430 */       sb.setColor(0.6F, 1.0F, 1.0F, this.flashTimer * 1.5F);
/* 431 */       sb.setBlendFunction(770, 1);
/* 432 */       sb.draw(ImageMaster.REWARD_SCREEN_ITEM, Settings.WIDTH / 2.0F - 232.0F, this.y - 49.0F, 232.0F, 49.0F, 464.0F, 98.0F, Settings.xScale * 1.03F, Settings.scale * 1.15F, 0.0F, 0, 0, 464, 98, false, false);
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
/* 449 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 452 */     sb.setColor(Color.WHITE);
/*     */     
/* 454 */     switch (this.type) {
/*     */       case CARD:
/* 456 */         if (this.isBoss) {
/* 457 */           sb.draw(ImageMaster.REWARD_CARD_BOSS, REWARD_ITEM_X - 32.0F, this.y - 32.0F - 2.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 475 */         sb.draw(ImageMaster.REWARD_CARD_NORMAL, REWARD_ITEM_X - 32.0F, this.y - 32.0F - 2.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */         break;
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
/*     */       case GOLD:
/* 495 */         sb.draw(ImageMaster.UI_GOLD, REWARD_ITEM_X - 32.0F, this.y - 32.0F - 2.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */         break;
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
/*     */       case STOLEN_GOLD:
/* 514 */         sb.draw(ImageMaster.UI_GOLD, REWARD_ITEM_X - 32.0F, this.y - 32.0F - 2.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */         break;
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
/*     */       case RELIC:
/* 533 */         this.relic.renderWithoutAmount(sb, new Color(0.0F, 0.0F, 0.0F, 0.25F));
/*     */         
/* 535 */         if (this.hb.hovered) {
/* 536 */           if (this.relicLink != null) {
/* 537 */             ArrayList<PowerTip> tips = new ArrayList<>();
/* 538 */             tips.add(new PowerTip(this.relic.name, this.relic.description));
/*     */             
/* 540 */             if (this.relicLink.type == RewardType.SAPPHIRE_KEY) {
/* 541 */               tips.add(new PowerTip(TEXT[7], TEXT[8] + FontHelper.colorString(TEXT[6] + TEXT[9], "y")));
/*     */             }
/*     */             
/* 544 */             TipHelper.queuePowerTips(360.0F * Settings.scale, InputHelper.mY + 50.0F * Settings.scale, tips); break;
/*     */           } 
/* 546 */           this.relic.renderTip(sb);
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case SAPPHIRE_KEY:
/* 552 */         renderKey(sb);
/*     */         
/* 554 */         if (this.hb.hovered && this.relicLink != null) {
/* 555 */           TipHelper.renderGenericTip(360.0F * Settings.scale, InputHelper.mY + 50.0F * Settings.scale, TEXT[7], TEXT[8] + 
/*     */ 
/*     */ 
/*     */               
/* 559 */               FontHelper.colorString(this.relicLink.relic.name + TEXT[9], "y"));
/*     */         }
/*     */         break;
/*     */       case EMERALD_KEY:
/* 563 */         renderKey(sb);
/*     */         break;
/*     */       case POTION:
/* 566 */         this.potion.renderLightOutline(sb);
/* 567 */         this.potion.render(sb);
/* 568 */         this.potion.generateSparkles(this.potion.posX, this.potion.posY, true);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 577 */     if (this.hb.hovered) {
/* 578 */       color = Settings.GOLD_COLOR;
/*     */     } else {
/* 580 */       color = Settings.CREAM_COLOR;
/*     */     } 
/*     */     
/* 583 */     if (this.redText) {
/* 584 */       color = Settings.RED_TEXT_COLOR;
/*     */     }
/*     */     
/* 587 */     FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, this.text, REWARD_TEXT_X, this.y + 5.0F * Settings.scale, 1000.0F * Settings.scale, 0.0F, color);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 597 */     if (!this.hb.hovered) {
/* 598 */       for (AbstractGameEffect e : this.effects) {
/* 599 */         e.render(sb);
/*     */       }
/*     */     }
/*     */     
/* 603 */     if (Settings.isControllerMode) {
/* 604 */       renderReticle(sb, this.hb);
/*     */     }
/*     */     
/* 607 */     if (this.type == RewardType.SAPPHIRE_KEY) {
/* 608 */       renderRelicLink(sb);
/*     */     }
/* 610 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   public void renderReticle(SpriteBatch sb, Hitbox hb) {
/* 614 */     float offset = Interpolation.fade.apply(18.0F * Settings.scale, 12.0F * Settings.scale, this.reticleColor.a);
/* 615 */     sb.setColor(this.reticleColor);
/* 616 */     renderReticleCorner(sb, -hb.width / 2.0F + offset, hb.height / 2.0F - offset, hb, false, false);
/* 617 */     renderReticleCorner(sb, hb.width / 2.0F - offset, hb.height / 2.0F - offset, hb, true, false);
/* 618 */     renderReticleCorner(sb, -hb.width / 2.0F + offset, -hb.height / 2.0F + offset, hb, false, true);
/* 619 */     renderReticleCorner(sb, hb.width / 2.0F - offset, -hb.height / 2.0F + offset, hb, true, true);
/*     */   }
/*     */   
/*     */   private void renderReticleCorner(SpriteBatch sb, float x, float y, Hitbox hb, boolean flipX, boolean flipY) {
/* 623 */     sb.draw(ImageMaster.RETICLE_CORNER, hb.cX + x - 18.0F, hb.cY + y - 18.0F, 18.0F, 18.0F, 36.0F, 36.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 36, 36, flipX, flipY);
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
/*     */   private void renderRelicLink(SpriteBatch sb) {
/* 643 */     sb.setColor(Color.WHITE);
/* 644 */     sb.draw(ImageMaster.RELIC_LINKED, this.hb.cX - 64.0F, this.y - 64.0F + 52.0F * Settings.scale, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
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
/*     */   private void renderKey(SpriteBatch sb) {
/* 664 */     if (this.img != null && this.outlineImg != null) {
/* 665 */       sb.setColor(AbstractRelic.PASSIVE_OUTLINE_COLOR);
/* 666 */       sb.draw(this.outlineImg, REWARD_ITEM_X - 64.0F, this.y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
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
/* 684 */       sb.setColor(Color.WHITE);
/* 685 */       sb.draw(this.img, REWARD_ITEM_X - 64.0F, this.y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 128, 128, false, false);
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
/*     */   
/*     */   public void recordCardSkipMetrics() {
/* 706 */     if (this.cards != null && !this.cards.isEmpty()) {
/* 707 */       HashMap<String, Object> choice = new HashMap<>();
/* 708 */       ArrayList<String> notpicked = new ArrayList<>();
/* 709 */       for (AbstractCard card : this.cards) {
/* 710 */         notpicked.add(card.getMetricID());
/*     */       }
/* 712 */       choice.put("picked", "SKIP");
/* 713 */       choice.put("not_picked", notpicked);
/* 714 */       choice.put("floor", Integer.valueOf(AbstractDungeon.floorNum));
/* 715 */       CardCrawlGame.metricData.card_choices.add(choice);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rewards\RewardItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */