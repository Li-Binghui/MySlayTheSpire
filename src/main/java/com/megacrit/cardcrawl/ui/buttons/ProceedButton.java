/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.BitmapFont;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.TipTracker;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.TutorialStrings;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*     */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
/*     */ import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
/*     */ import com.megacrit.cardcrawl.rooms.TrueVictoryRoom;
/*     */ import com.megacrit.cardcrawl.rooms.VictoryRoom;
/*     */ import com.megacrit.cardcrawl.ui.FtueTip;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProceedButton
/*     */ {
/*  46 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Rewards Tip");
/*  47 */   public static final String[] MSG = tutorialStrings.TEXT;
/*  48 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*  49 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Proceed Button");
/*  50 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */ 
/*     */   
/*  53 */   private static final Color HOVER_BLEND_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.3F);
/*  54 */   private static final float SHOW_X = 1670.0F * Settings.xScale, DRAW_Y = 320.0F * Settings.scale;
/*  55 */   private static final float HIDE_X = SHOW_X + 500.0F * Settings.scale;
/*  56 */   private float current_x = HIDE_X, current_y = DRAW_Y;
/*  57 */   private float target_x = this.current_x;
/*  58 */   private float wavyTimer = 0.0F;
/*     */   private boolean isHidden = true;
/*  60 */   private String label = TEXT[0];
/*     */   private static final int W = 512;
/*  62 */   private BitmapFont font = FontHelper.buttonLabelFont;
/*     */   
/*     */   private boolean callingBellCheck = true;
/*     */   
/*  66 */   private static final float HITBOX_W = 280.0F * Settings.scale;
/*  67 */   private static final float HITBOX_H = 156.0F * Settings.scale;
/*     */ 
/*     */   
/*  70 */   private Hitbox hb = new Hitbox(SHOW_X, this.current_y, HITBOX_W, HITBOX_H);
/*  71 */   private static final float CLICKABLE_DIST = 25.0F * Settings.scale;
/*     */   
/*     */   public boolean isHovered = false;
/*     */   
/*     */   public ProceedButton() {
/*  76 */     this.hb.move(SHOW_X, this.current_y);
/*     */   }
/*     */   
/*     */   public void setLabel(String newLabel) {
/*  80 */     this.label = newLabel;
/*  81 */     if (FontHelper.getSmartWidth(FontHelper.buttonLabelFont, this.label, 9999.0F, 0.0F) > 160.0F * Settings.scale) {
/*  82 */       this.font = FontHelper.topPanelInfoFont;
/*     */     } else {
/*  84 */       this.font = FontHelper.buttonLabelFont;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/*  89 */     if (!this.isHidden) {
/*  90 */       this.wavyTimer += Gdx.graphics.getDeltaTime() * 3.0F;
/*  91 */       if (this.current_x - SHOW_X < CLICKABLE_DIST) {
/*  92 */         this.hb.update();
/*     */       }
/*     */       
/*  95 */       this.isHovered = this.hb.hovered;
/*     */       
/*  97 */       if (this.hb.hovered && InputHelper.justClickedLeft) {
/*  98 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*  99 */         this.hb.clickStarted = true;
/*     */       } 
/*     */ 
/*     */       
/* 103 */       if (this.hb.justHovered && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/* 104 */         for (RewardItem i : AbstractDungeon.combatRewardScreen.rewards) {
/* 105 */           i.flash();
/*     */         }
/*     */       }
/*     */       
/* 109 */       if (this.hb.clicked || CInputActionSet.proceed.isJustPressed()) {
/* 110 */         this.hb.clicked = false;
/* 111 */         AbstractRoom currentRoom = AbstractDungeon.getCurrRoom();
/*     */ 
/*     */         
/* 114 */         if (currentRoom instanceof MonsterRoomBoss) {
/* 115 */           if (AbstractDungeon.id.equals("TheBeyond")) {
/* 116 */             if (AbstractDungeon.ascensionLevel >= 20 && AbstractDungeon.bossList.size() == 2) {
/* 117 */               goToDoubleBoss();
/*     */             }
/* 119 */             else if (!Settings.isEndless) {
/* 120 */               goToVictoryRoomOrTheDoor();
/*     */             }
/*     */           
/* 123 */           } else if (AbstractDungeon.id.equals("TheEnding")) {
/* 124 */             goToTrueVictoryRoom();
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 129 */         if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD && 
/* 130 */           !(AbstractDungeon.getCurrRoom() instanceof TreasureRoomBoss)) {
/* 131 */           if (currentRoom instanceof MonsterRoomBoss) {
/* 132 */             goToTreasureRoom();
/* 133 */           } else if (currentRoom instanceof com.megacrit.cardcrawl.rooms.EventRoom) {
/* 134 */             if (!(currentRoom.event instanceof com.megacrit.cardcrawl.events.exordium.Mushrooms) && !(currentRoom.event instanceof com.megacrit.cardcrawl.events.city.MaskedBandits) && !(currentRoom.event instanceof com.megacrit.cardcrawl.events.exordium.DeadAdventurer) && !(currentRoom.event instanceof com.megacrit.cardcrawl.events.shrines.Lab) && !(currentRoom.event instanceof com.megacrit.cardcrawl.events.city.Colosseum) && !(currentRoom.event instanceof com.megacrit.cardcrawl.events.beyond.MysteriousSphere) && !(currentRoom.event instanceof com.megacrit.cardcrawl.events.beyond.MindBloom)) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 139 */               AbstractDungeon.closeCurrentScreen();
/* 140 */               hide();
/*     */             } else {
/* 142 */               AbstractDungeon.closeCurrentScreen();
/* 143 */               AbstractDungeon.dungeonMapScreen.open(false);
/* 144 */               AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/*     */             } 
/*     */           } else {
/*     */             
/* 148 */             if (!((Boolean)TipTracker.tips.get("CARD_REWARD_TIP")).booleanValue() && 
/* 149 */               !AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
/* 150 */               if (Settings.isConsoleBuild) {
/* 151 */                 AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[1], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.CARD_REWARD);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 157 */                 TipTracker.neverShowAgain("CARD_REWARD_TIP");
/*     */               } else {
/* 159 */                 AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.CARD_REWARD);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 165 */                 TipTracker.neverShowAgain("CARD_REWARD_TIP");
/*     */               } 
/*     */ 
/*     */               
/*     */               return;
/*     */             } 
/*     */ 
/*     */             
/* 173 */             int relicCount = 0;
/* 174 */             for (RewardItem i : AbstractDungeon.combatRewardScreen.rewards) {
/* 175 */               if (i.type == RewardItem.RewardType.RELIC) {
/* 176 */                 relicCount++;
/*     */               }
/*     */             } 
/*     */             
/* 180 */             if (relicCount == 3 && this.callingBellCheck) {
/* 181 */               this.callingBellCheck = false;
/* 182 */               if (!AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
/* 183 */                 AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.CARD_REWARD);
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 return;
/*     */               } 
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 194 */             if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.neow.NeowRoom) {
/* 195 */               (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*     */             }
/*     */             
/* 198 */             if (!AbstractDungeon.combatRewardScreen.hasTakenAll) {
/* 199 */               for (RewardItem item : AbstractDungeon.combatRewardScreen.rewards) {
/* 200 */                 if (item.type == RewardItem.RewardType.CARD) {
/* 201 */                   item.recordCardSkipMetrics();
/*     */                 }
/*     */               } 
/*     */             }
/* 205 */             AbstractDungeon.closeCurrentScreen();
/* 206 */             AbstractDungeon.dungeonMapScreen.open(false);
/* 207 */             AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
/*     */           } 
/* 209 */         } else if (currentRoom instanceof TreasureRoomBoss) {
/* 210 */           if (Settings.isDemo) {
/* 211 */             goToDemoVictoryRoom();
/*     */           } else {
/* 213 */             goToNextDungeon(currentRoom);
/*     */           } 
/* 215 */         } else if (!(currentRoom instanceof MonsterRoomBoss)) {
/* 216 */           AbstractDungeon.dungeonMapScreen.open(false);
/* 217 */           hide();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 222 */     if (this.current_x != this.target_x) {
/* 223 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/* 224 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/* 225 */         this.current_x = this.target_x;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void goToTreasureRoom() {
/* 231 */     CardCrawlGame.music.fadeOutTempBGM();
/* 232 */     MapRoomNode node = new MapRoomNode(-1, 15);
/* 233 */     node.room = (AbstractRoom)new TreasureRoomBoss();
/* 234 */     AbstractDungeon.nextRoom = node;
/* 235 */     AbstractDungeon.closeCurrentScreen();
/* 236 */     AbstractDungeon.nextRoomTransitionStart();
/* 237 */     hide();
/*     */   }
/*     */   
/*     */   private void goToTrueVictoryRoom() {
/* 241 */     CardCrawlGame.music.fadeOutBGM();
/*     */     
/* 243 */     MapRoomNode node = new MapRoomNode(3, 4);
/* 244 */     node.room = (AbstractRoom)new TrueVictoryRoom();
/* 245 */     AbstractDungeon.nextRoom = node;
/* 246 */     AbstractDungeon.closeCurrentScreen();
/* 247 */     AbstractDungeon.nextRoomTransitionStart();
/* 248 */     hide();
/*     */   }
/*     */   
/*     */   private void goToVictoryRoomOrTheDoor() {
/* 252 */     CardCrawlGame.music.fadeOutBGM();
/* 253 */     CardCrawlGame.music.fadeOutTempBGM();
/* 254 */     MapRoomNode node = new MapRoomNode(-1, 15);
/* 255 */     node.room = (AbstractRoom)new VictoryRoom(VictoryRoom.EventType.HEART);
/* 256 */     AbstractDungeon.nextRoom = node;
/* 257 */     AbstractDungeon.closeCurrentScreen();
/* 258 */     AbstractDungeon.nextRoomTransitionStart();
/* 259 */     hide();
/*     */   }
/*     */   
/*     */   private void goToDoubleBoss() {
/* 263 */     AbstractDungeon.bossKey = AbstractDungeon.bossList.get(0);
/* 264 */     CardCrawlGame.music.fadeOutBGM();
/* 265 */     CardCrawlGame.music.fadeOutTempBGM();
/* 266 */     MapRoomNode node = new MapRoomNode(-1, 15);
/* 267 */     node.room = (AbstractRoom)new MonsterRoomBoss();
/* 268 */     AbstractDungeon.nextRoom = node;
/* 269 */     AbstractDungeon.closeCurrentScreen();
/* 270 */     AbstractDungeon.nextRoomTransitionStart();
/* 271 */     hide();
/*     */   }
/*     */   
/*     */   private void goToDemoVictoryRoom() {
/* 275 */     MapRoomNode node = new MapRoomNode(-1, 15);
/* 276 */     node.room = (AbstractRoom)new VictoryRoom(VictoryRoom.EventType.NONE);
/* 277 */     AbstractDungeon.nextRoom = node;
/* 278 */     AbstractDungeon.closeCurrentScreen();
/* 279 */     AbstractDungeon.nextRoomTransitionStart();
/* 280 */     hide();
/*     */   }
/*     */   
/*     */   private void goToNextDungeon(AbstractRoom room) {
/* 284 */     if (!((TreasureRoomBoss)room).choseRelic) {
/* 285 */       AbstractDungeon.bossRelicScreen.noPick();
/*     */     }
/*     */ 
/*     */     
/* 289 */     int relicCount = 0;
/* 290 */     for (RewardItem i : AbstractDungeon.combatRewardScreen.rewards) {
/* 291 */       if (i.type == RewardItem.RewardType.RELIC) {
/* 292 */         relicCount++;
/*     */       }
/*     */     } 
/*     */     
/* 296 */     if (relicCount == 3 && this.callingBellCheck) {
/* 297 */       this.callingBellCheck = false;
/* 298 */       if (!AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
/* 299 */         AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, FtueTip.TipType.CARD_REWARD);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 310 */     CardCrawlGame.music.fadeOutBGM();
/* 311 */     CardCrawlGame.music.fadeOutTempBGM();
/* 312 */     AbstractDungeon.fadeOut();
/* 313 */     AbstractDungeon.isDungeonBeaten = true;
/* 314 */     hide();
/*     */   }
/*     */   
/*     */   public void hideInstantly() {
/* 318 */     this.current_x = HIDE_X;
/* 319 */     this.target_x = HIDE_X;
/* 320 */     this.isHidden = true;
/*     */   }
/*     */   
/*     */   public void hide() {
/* 324 */     if (!this.isHidden) {
/* 325 */       this.target_x = HIDE_X;
/* 326 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show() {
/* 331 */     if (this.isHidden) {
/* 332 */       this.target_x = SHOW_X;
/* 333 */       this.isHidden = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 338 */     if (Settings.hideEndTurn) {
/*     */       return;
/*     */     }
/*     */     
/* 342 */     sb.setColor(Color.WHITE);
/* 343 */     renderShadow(sb);
/*     */ 
/*     */     
/* 346 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/* 347 */       sb.setColor(new Color(1.0F, 0.9F, 0.2F, MathUtils.cos(this.wavyTimer) / 5.0F + 0.6F));
/*     */     } else {
/* 349 */       sb.setColor(Settings.QUARTER_TRANSPARENT_BLACK_COLOR);
/*     */     } 
/* 351 */     sb.draw(ImageMaster.PROCEED_BUTTON_OUTLINE, this.current_x - 256.0F, this.current_y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * 1.1F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 359 */         MathUtils.cos(this.wavyTimer) / 50.0F, Settings.scale * 1.1F + 
/* 360 */         MathUtils.cos(this.wavyTimer) / 50.0F, 0.0F, 0, 0, 512, 512, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 369 */     sb.setColor(Color.WHITE);
/* 370 */     renderButton(sb);
/*     */     
/* 372 */     if (Settings.isControllerMode) {
/* 373 */       sb.setColor(Color.WHITE);
/* 374 */       sb.draw(CInputActionSet.proceed
/* 375 */           .getKeyImg(), this.current_x - 32.0F - 38.0F * Settings.scale - 
/* 376 */           FontHelper.getSmartWidth(this.font, this.label, 99999.0F, 0.0F) / 2.0F, this.current_y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 393 */     if (this.hb.hovered) {
/* 394 */       sb.setBlendFunction(770, 1);
/* 395 */       sb.setColor(HOVER_BLEND_COLOR);
/* 396 */       renderButton(sb);
/* 397 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */ 
/*     */     
/* 401 */     if (this.hb.hovered && !this.hb.clickStarted) {
/* 402 */       FontHelper.renderFontCentered(sb, this.font, this.label, this.current_x, this.current_y, Settings.CREAM_COLOR);
/* 403 */     } else if (this.hb.clickStarted) {
/* 404 */       FontHelper.renderFontCentered(sb, this.font, this.label, this.current_x, this.current_y, Color.LIGHT_GRAY);
/*     */     } else {
/* 406 */       FontHelper.renderFontCentered(sb, this.font, this.label, this.current_x, this.current_y, Settings.LIGHT_YELLOW_COLOR);
/*     */     } 
/*     */     
/* 409 */     if (!this.isHidden) {
/* 410 */       this.hb.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderShadow(SpriteBatch sb) {
/* 415 */     sb.draw(ImageMaster.PROCEED_BUTTON_SHADOW, this.current_x - 256.0F, this.current_y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * 1.1F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 423 */         MathUtils.cos(this.wavyTimer) / 50.0F, Settings.scale * 1.1F + 
/* 424 */         MathUtils.cos(this.wavyTimer) / 50.0F, 0.0F, 0, 0, 512, 512, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderButton(SpriteBatch sb) {
/* 435 */     sb.draw(ImageMaster.PROCEED_BUTTON, this.current_x - 256.0F, this.current_y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale * 1.1F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 443 */         MathUtils.cos(this.wavyTimer) / 50.0F, Settings.scale * 1.1F + 
/* 444 */         MathUtils.cos(this.wavyTimer) / 50.0F, 0.0F, 0, 0, 512, 512, false, false);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\ProceedButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */