/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
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
/*     */ public class CombatRewardScreen
/*     */ {
/*  34 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CombatRewardScreen");
/*  35 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  37 */   public ArrayList<RewardItem> rewards = new ArrayList<>();
/*  38 */   public ArrayList<AbstractGameEffect> effects = new ArrayList<>();
/*     */   public boolean hasTakenAll = false;
/*  40 */   private String labelOverride = null;
/*     */   
/*     */   private boolean mug = false;
/*     */   private boolean smoke = false;
/*     */   private static final float REWARD_ANIM_TIME = 0.2F;
/*  45 */   private float rewardAnimTimer = 0.2F;
/*  46 */   private float tipY = -100.0F * Settings.scale;
/*  47 */   private Color uiColor = Color.BLACK.cpy(); private static final int SHEET_W = 612;
/*     */   private static final int SHEET_H = 716;
/*     */   private String tip;
/*     */   
/*     */   public void update() {
/*  52 */     if (InputHelper.justClickedLeft && Settings.isDebug) {
/*  53 */       this.tip = CardCrawlGame.tips.getTip();
/*     */     }
/*     */     
/*  56 */     rewardViewUpdate();
/*  57 */     updateEffects();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateEffects() {
/*  62 */     for (Iterator<AbstractGameEffect> i = this.effects.iterator(); i.hasNext(); ) {
/*  63 */       AbstractGameEffect e = i.next();
/*  64 */       e.update();
/*  65 */       if (e.isDone) {
/*  66 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setupItemReward() {
/*  72 */     this.rewardAnimTimer = 0.2F;
/*  73 */     InputHelper.justClickedLeft = false;
/*  74 */     this.rewards = new ArrayList<>((AbstractDungeon.getCurrRoom()).rewards);
/*     */     
/*  76 */     if (((AbstractDungeon.getCurrRoom()).event == null || ((AbstractDungeon.getCurrRoom()).event != null && 
/*  77 */       !(AbstractDungeon.getCurrRoom()).event.noCardsInRewards)) && 
/*  78 */       !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.TreasureRoom) && 
/*  79 */       !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.RestRoom)) {
/*  80 */       if (ModHelper.isModEnabled("Vintage") && AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoom) {
/*  81 */         if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite || 
/*  82 */           AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/*  83 */           RewardItem cardReward = new RewardItem();
/*  84 */           if (cardReward.cards.size() > 0) {
/*  85 */             this.rewards.add(cardReward);
/*     */           }
/*     */         } 
/*     */       } else {
/*  89 */         RewardItem cardReward = new RewardItem();
/*  90 */         if (cardReward.cards.size() > 0) {
/*  91 */           this.rewards.add(cardReward);
/*     */         }
/*  93 */         if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoom && AbstractDungeon.player.hasRelic("Prayer Wheel") && 
/*  94 */           !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite) && 
/*  95 */           !(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss)) {
/*  96 */           cardReward = new RewardItem();
/*  97 */           if (cardReward.cards.size() > 0) {
/*  98 */             this.rewards.add(cardReward);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 105 */     AbstractDungeon.overlayMenu.proceedButton.show();
/* 106 */     this.hasTakenAll = false;
/* 107 */     positionRewards();
/*     */   }
/*     */   
/*     */   public void positionRewards() {
/* 111 */     for (int i = 0; i < this.rewards.size(); i++) {
/* 112 */       ((RewardItem)this.rewards.get(i)).move(Settings.HEIGHT / 2.0F + 124.0F * Settings.scale - i * 100.0F * Settings.scale);
/*     */     }
/* 114 */     if (this.rewards.isEmpty()) {
/* 115 */       this.hasTakenAll = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void rewardViewUpdate() {
/* 122 */     if (this.rewardAnimTimer != 0.0F) {
/* 123 */       this.rewardAnimTimer -= Gdx.graphics.getDeltaTime();
/* 124 */       if (this.rewardAnimTimer < 0.0F) {
/* 125 */         this.rewardAnimTimer = 0.0F;
/*     */       }
/* 127 */       this.uiColor.r = 1.0F - this.rewardAnimTimer / 0.2F;
/* 128 */       this.uiColor.g = 1.0F - this.rewardAnimTimer / 0.2F;
/* 129 */       this.uiColor.b = 1.0F - this.rewardAnimTimer / 0.2F;
/*     */     } 
/*     */     
/* 132 */     this.tipY = MathHelper.uiLerpSnap(this.tipY, Settings.HEIGHT / 2.0F - 460.0F * Settings.scale);
/* 133 */     updateControllerInput();
/*     */     
/* 135 */     boolean removedSomething = false;
/* 136 */     for (Iterator<RewardItem> i = this.rewards.iterator(); i.hasNext(); ) {
/* 137 */       RewardItem r = i.next();
/* 138 */       r.update();
/*     */       
/* 140 */       if (r.isDone) {
/* 141 */         if (r.claimReward()) {
/* 142 */           i.remove();
/* 143 */           removedSomething = true; continue;
/* 144 */         }  if (r.type == RewardItem.RewardType.POTION) {
/* 145 */           r.isDone = false;
/* 146 */           AbstractDungeon.topPanel.flashRed();
/* 147 */           this.tip = CardCrawlGame.tips.getPotionTip(); continue;
/*     */         } 
/* 149 */         r.isDone = false;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 154 */     if (removedSomething) {
/* 155 */       positionRewards();
/* 156 */       setLabel();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 161 */     if (!Settings.isControllerMode || this.rewards.isEmpty() || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || AbstractDungeon.player.viewingRelics) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 166 */     int index = 0;
/* 167 */     boolean anyHovered = false;
/* 168 */     for (RewardItem r : this.rewards) {
/* 169 */       if (r.hb.hovered) {
/* 170 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 173 */       index++;
/*     */     } 
/*     */     
/* 176 */     if (!anyHovered) {
/* 177 */       index = 0;
/* 178 */       Gdx.input.setCursorPosition(
/* 179 */           (int)((RewardItem)this.rewards.get(index)).hb.cX, Settings.HEIGHT - 
/* 180 */           (int)((RewardItem)this.rewards.get(index)).hb.cY);
/*     */     }
/* 182 */     else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 183 */       index--;
/* 184 */       if (index < 0) {
/* 185 */         index = this.rewards.size() - 1;
/*     */       }
/*     */       
/* 188 */       Gdx.input.setCursorPosition(
/* 189 */           (int)((RewardItem)this.rewards.get(index)).hb.cX, Settings.HEIGHT - 
/* 190 */           (int)((RewardItem)this.rewards.get(index)).hb.cY);
/* 191 */     } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 192 */       index++;
/* 193 */       if (index > this.rewards.size() - 1) {
/* 194 */         index = 0;
/*     */       }
/*     */       
/* 197 */       Gdx.input.setCursorPosition(
/* 198 */           (int)((RewardItem)this.rewards.get(index)).hb.cX, Settings.HEIGHT - 
/* 199 */           (int)((RewardItem)this.rewards.get(index)).hb.cY);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLabel() {
/* 205 */     if (this.rewards.size() == 0) {
/* 206 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
/* 207 */     } else if (this.rewards.size() == 1) {
/* 208 */       switch (((RewardItem)this.rewards.get(0)).type) {
/*     */         case CARD:
/* 210 */           AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[1]);
/*     */           break;
/*     */         case GOLD:
/* 213 */           AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[2]);
/*     */           break;
/*     */         case POTION:
/* 216 */           AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[3]);
/*     */           break;
/*     */         case RELIC:
/* 219 */           AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[4]);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } else {
/* 225 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[5]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 230 */     renderItemReward(sb);
/*     */     
/* 232 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[12] + this.tip, Settings.WIDTH / 2.0F, this.tipY, Color.LIGHT_GRAY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 240 */     for (AbstractGameEffect e : this.effects) {
/* 241 */       e.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderItemReward(SpriteBatch sb) {
/* 246 */     AbstractDungeon.overlayMenu.proceedButton.render(sb);
/* 247 */     sb.setColor(this.uiColor);
/* 248 */     sb.draw(ImageMaster.REWARD_SCREEN_SHEET, Settings.WIDTH / 2.0F - 306.0F, Settings.HEIGHT / 2.0F - 46.0F * Settings.scale - 358.0F, 306.0F, 358.0F, 612.0F, 716.0F, Settings.xScale, Settings.scale, 0.0F, 0, 0, 612, 716, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     for (RewardItem i : this.rewards) {
/* 267 */       i.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   public void reopen() {
/* 272 */     (AbstractDungeon.getCurrRoom()).rewardTime = true;
/* 273 */     this.rewardAnimTimer = 0.2F;
/* 274 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/* 275 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 276 */     AbstractDungeon.isScreenUp = true;
/* 277 */     if (this.labelOverride == null || this.mug || this.smoke) {
/* 278 */       if (this.mug || this.smoke) {
/* 279 */         AbstractDungeon.dynamicBanner.appear(this.labelOverride);
/*     */       } else {
/* 281 */         AbstractDungeon.dynamicBanner.appear(getRandomBannerLabel());
/*     */       } 
/* 283 */       AbstractDungeon.overlayMenu.proceedButton.show();
/*     */     } else {
/* 285 */       AbstractDungeon.dynamicBanner.appear(this.labelOverride);
/* 286 */       AbstractDungeon.overlayMenu.cancelButton.show(TEXT[6]);
/*     */     } 
/* 288 */     setLabel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(String setLabel) {
/* 297 */     (AbstractDungeon.getCurrRoom()).rewardTime = true;
/* 298 */     this.labelOverride = setLabel;
/* 299 */     this.mug = false;
/* 300 */     this.smoke = false;
/* 301 */     this.tip = CardCrawlGame.tips.getTip();
/* 302 */     this.tipY = Settings.HEIGHT - 1110.0F * Settings.scale;
/* 303 */     this.rewardAnimTimer = 0.5F;
/* 304 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 305 */     AbstractDungeon.isScreenUp = true;
/* 306 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/* 307 */     AbstractDungeon.dynamicBanner.appear(setLabel);
/* 308 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 309 */     setupItemReward();
/* 310 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/*     */ 
/*     */     
/* 313 */     AbstractDungeon.overlayMenu.cancelButton.show(TEXT[6]);
/*     */ 
/*     */     
/* 316 */     for (RewardItem r : this.rewards) {
/* 317 */       if (r.type == RewardItem.RewardType.RELIC) {
/* 318 */         UnlockTracker.markRelicAsSeen(r.relic.relicId);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void openCombat(String setLabel) {
/* 324 */     openCombat(setLabel, false);
/*     */   }
/*     */   
/*     */   public void openCombat(String setLabel, boolean smoked) {
/* 328 */     (AbstractDungeon.getCurrRoom()).rewardTime = true;
/* 329 */     this.labelOverride = setLabel;
/* 330 */     this.mug = !smoked;
/* 331 */     this.smoke = smoked;
/* 332 */     this.tip = CardCrawlGame.tips.getTip();
/* 333 */     this.tipY = Settings.HEIGHT - 1110.0F * Settings.scale;
/* 334 */     this.rewardAnimTimer = 0.5F;
/* 335 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 336 */     AbstractDungeon.isScreenUp = true;
/* 337 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/* 338 */     AbstractDungeon.dynamicBanner.appear(setLabel);
/* 339 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*     */     
/* 341 */     if (!this.smoke) {
/* 342 */       setupItemReward();
/*     */       
/* 344 */       for (RewardItem r : this.rewards) {
/* 345 */         if (r.type == RewardItem.RewardType.RELIC) {
/* 346 */           UnlockTracker.markRelicAsSeen(r.relic.relicId);
/*     */         }
/*     */       } 
/*     */     } else {
/* 350 */       AbstractDungeon.overlayMenu.proceedButton.show();
/*     */     } 
/* 352 */     setLabel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void open() {
/* 357 */     (AbstractDungeon.getCurrRoom()).rewardTime = true;
/* 358 */     this.tip = CardCrawlGame.tips.getTip();
/* 359 */     this.mug = false;
/* 360 */     this.smoke = false;
/* 361 */     this.tipY = Settings.HEIGHT - 1110.0F * Settings.scale;
/* 362 */     this.rewardAnimTimer = 0.5F;
/* 363 */     AbstractDungeon.topPanel.unhoverHitboxes();
/* 364 */     AbstractDungeon.isScreenUp = true;
/* 365 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/* 366 */     AbstractDungeon.dynamicBanner.appear(getRandomBannerLabel());
/* 367 */     this.labelOverride = null;
/* 368 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 369 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 370 */     setupItemReward();
/* 371 */     setLabel();
/*     */ 
/*     */     
/* 374 */     for (RewardItem r : this.rewards) {
/* 375 */       if (r.type == RewardItem.RewardType.RELIC) {
/* 376 */         UnlockTracker.markRelicAsSeen(r.relic.relicId);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getRandomBannerLabel() {
/* 382 */     ArrayList<String> list = new ArrayList<>();
/* 383 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.TreasureRoom) {
/* 384 */       list.add(TEXT[7]);
/* 385 */       list.add(TEXT[8]);
/*     */     } else {
/* 387 */       list.add(TEXT[9]);
/* 388 */       list.add(TEXT[10]);
/* 389 */       list.add(TEXT[11]);
/*     */     } 
/* 391 */     return list.get(MathUtils.random(list.size() - 1));
/*     */   }
/*     */   
/*     */   public void clear() {
/* 395 */     this.rewards.clear();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\CombatRewardScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */