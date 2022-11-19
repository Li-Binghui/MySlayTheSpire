/*     */ package com.megacrit.cardcrawl.screens.select;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.blights.AbstractBlight;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
/*     */ import com.megacrit.cardcrawl.ui.buttons.ConfirmButton;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.BossChestShineEffect;
/*     */ import de.robojumper.ststwitch.TwitchPanel;
/*     */ import de.robojumper.ststwitch.TwitchVoteListener;
/*     */ import de.robojumper.ststwitch.TwitchVoteOption;
/*     */ import de.robojumper.ststwitch.TwitchVoter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Stream;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BossRelicSelectScreen
/*     */ {
/*  48 */   private static final Logger logger = LogManager.getLogger(BossRelicSelectScreen.class.getName());
/*  49 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("BossRelicSelectScreen");
/*  50 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private boolean isDone = false;
/*  53 */   public ArrayList<AbstractRelic> relics = new ArrayList<>();
/*  54 */   public ArrayList<AbstractBlight> blights = new ArrayList<>();
/*  55 */   private MenuCancelButton cancelButton = new MenuCancelButton();
/*  56 */   private static final String SELECT_MSG = TEXT[2];
/*     */   private Texture smokeImg;
/*  58 */   private float shineTimer = 0.0F;
/*     */   
/*     */   private static final float SHINE_INTERAL = 0.1F;
/*  61 */   private static final float BANNER_Y = AbstractDungeon.floorY + 460.0F * Settings.scale;
/*  62 */   private static final float SLOT_1_X = Settings.WIDTH / 2.0F + 4.0F * Settings.scale, SLOT_1_Y = AbstractDungeon.floorY + 360.0F * Settings.scale;
/*     */   
/*  64 */   private static final float SLOT_2_X = Settings.WIDTH / 2.0F - 116.0F * Settings.scale, SLOT_2_Y = AbstractDungeon.floorY + 225.0F * Settings.scale;
/*     */   
/*  66 */   private static final float SLOT_3_X = Settings.WIDTH / 2.0F + 124.0F * Settings.scale;
/*     */   
/*  68 */   private final float B_SLOT_1_X = 844.0F * Settings.scale, B_SLOT_1_Y = AbstractDungeon.floorY + 310.0F * Settings.scale;
/*  69 */   private final float B_SLOT_2_X = 1084.0F * Settings.scale;
/*     */ 
/*     */   
/*  72 */   public ConfirmButton confirmButton = new ConfirmButton();
/*  73 */   public AbstractRelic touchRelic = null;
/*  74 */   public AbstractBlight touchBlight = null;
/*     */ 
/*     */   
/*     */   static {
/*  78 */     TwitchVoter.registerListener(new TwitchVoteListener() {
/*     */           public void onTwitchAvailable() {
/*  80 */             AbstractDungeon.bossRelicScreen.updateVote();
/*     */           }
/*     */           
/*     */           public void onTwitchUnavailable() {
/*  84 */             AbstractDungeon.bossRelicScreen.updateVote();
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   boolean isVoting = false;
/*     */   boolean mayVote = false;
/*     */   
/*     */   public void update() {
/*  93 */     updateConfirmButton();
/*     */     
/*  95 */     this.shineTimer -= Gdx.graphics.getDeltaTime();
/*  96 */     if (this.shineTimer < 0.0F && !Settings.DISABLE_EFFECTS) {
/*  97 */       this.shineTimer = 0.1F;
/*  98 */       AbstractDungeon.topLevelEffects.add(new BossChestShineEffect());
/*  99 */       AbstractDungeon.topLevelEffects.add(new BossChestShineEffect(
/*     */             
/* 101 */             MathUtils.random(0.0F, Settings.WIDTH), 
/* 102 */             MathUtils.random(0.0F, Settings.HEIGHT - 128.0F * Settings.scale)));
/*     */     } 
/*     */     
/* 105 */     updateControllerInput();
/*     */     
/* 107 */     if (AbstractDungeon.actNum < 4 || !AbstractPlayer.customMods.contains("Blight Chests")) {
/* 108 */       for (AbstractRelic r : this.relics) {
/* 109 */         r.update();
/*     */         
/* 111 */         if (r.isObtained) {
/* 112 */           relicObtainLogic(r);
/*     */         }
/*     */       } 
/*     */     } else {
/* 116 */       for (AbstractBlight b : this.blights) {
/* 117 */         b.update();
/*     */         
/* 119 */         if (b.isObtained) {
/* 120 */           blightObtainLogic(b);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 125 */     if (this.isDone) {
/* 126 */       this.isDone = false;
/* 127 */       this.mayVote = false;
/* 128 */       updateVote();
/* 129 */       AbstractDungeon.overlayMenu.cancelButton.hide();
/* 130 */       this.relics.clear();
/* 131 */       AbstractDungeon.closeCurrentScreen();
/*     */     } 
/*     */     
/* 134 */     updateCancelButton();
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 138 */     if (!Settings.isControllerMode || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || AbstractDungeon.player.viewingRelics) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 143 */     boolean anyHovered = false;
/* 144 */     int index = 0;
/* 145 */     for (AbstractRelic r : this.relics) {
/* 146 */       if (r.hb.hovered) {
/* 147 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 150 */       index++;
/*     */     } 
/*     */     
/* 153 */     for (AbstractBlight b : this.blights) {
/* 154 */       if (b.hb.hovered) {
/* 155 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 158 */       index++;
/*     */     } 
/*     */     
/* 161 */     if (!anyHovered) {
/* 162 */       if (!this.relics.isEmpty()) {
/* 163 */         CInputHelper.setCursor(((AbstractRelic)this.relics.get(0)).hb);
/*     */       } else {
/* 165 */         CInputHelper.setCursor(((AbstractBlight)this.blights.get(0)).hb);
/*     */       }
/*     */     
/* 168 */     } else if (!this.relics.isEmpty()) {
/* 169 */       if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 170 */         if (index != 1) {
/* 171 */           CInputHelper.setCursor(((AbstractRelic)this.relics.get(1)).hb);
/*     */         }
/* 173 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 174 */         if (index != 2) {
/* 175 */           CInputHelper.setCursor(((AbstractRelic)this.relics.get(2)).hb);
/*     */         }
/* 177 */       } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 178 */         if (index != 0) {
/* 179 */           CInputHelper.setCursor(((AbstractRelic)this.relics.get(0)).hb);
/*     */         }
/* 181 */       } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && 
/* 182 */         index == 0) {
/* 183 */         CInputHelper.setCursor(((AbstractRelic)this.relics.get(1)).hb);
/*     */       }
/*     */     
/*     */     }
/* 187 */     else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed() || CInputActionSet.right
/* 188 */       .isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 189 */       if (index == 0) {
/* 190 */         CInputHelper.setCursor(((AbstractBlight)this.blights.get(1)).hb);
/*     */       } else {
/* 192 */         CInputHelper.setCursor(((AbstractBlight)this.blights.get(0)).hb);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void blightObtainLogic(AbstractBlight b) {
/* 201 */     HashMap<String, Object> choice = new HashMap<>();
/* 202 */     ArrayList<String> notPicked = new ArrayList<>();
/*     */ 
/*     */     
/* 205 */     choice.put("picked", b.blightID);
/* 206 */     TreasureRoomBoss curRoom = (TreasureRoomBoss)AbstractDungeon.getCurrRoom();
/*     */     
/* 208 */     curRoom.choseRelic = true;
/* 209 */     for (AbstractBlight otherBlights : this.blights) {
/* 210 */       if (otherBlights != b) {
/* 211 */         notPicked.add(otherBlights.blightID);
/*     */       }
/*     */     } 
/*     */     
/* 215 */     choice.put("not_picked", notPicked);
/* 216 */     CardCrawlGame.metricData.boss_relics.add(choice);
/*     */ 
/*     */     
/* 219 */     this.isDone = true;
/* 220 */     (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 99999.0F;
/* 221 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 222 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void relicObtainLogic(AbstractRelic r) {
/* 228 */     HashMap<String, Object> choice = new HashMap<>();
/* 229 */     ArrayList<String> notPicked = new ArrayList<>();
/*     */ 
/*     */     
/* 232 */     choice.put("picked", r.relicId);
/* 233 */     TreasureRoomBoss curRoom = (TreasureRoomBoss)AbstractDungeon.getCurrRoom();
/* 234 */     curRoom.choseRelic = true;
/*     */     
/* 236 */     for (AbstractRelic otherRelics : this.relics) {
/* 237 */       if (otherRelics != r) {
/* 238 */         notPicked.add(otherRelics.relicId);
/*     */       }
/*     */     } 
/*     */     
/* 242 */     choice.put("not_picked", notPicked);
/* 243 */     CardCrawlGame.metricData.boss_relics.add(choice);
/*     */ 
/*     */     
/* 246 */     this.isDone = true;
/* 247 */     (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 99999.0F;
/* 248 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/*     */ 
/*     */     
/* 251 */     if (r.relicId.equals("Black Blood") || r.relicId.equals("Ring of the Serpent") || r.relicId.equals("FrozenCore") || r.relicId
/* 252 */       .equals("HolyWater")) {
/* 253 */       r.instantObtain(AbstractDungeon.player, 0, true);
/* 254 */       (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void relicSkipLogic() {
/* 259 */     if (AbstractDungeon.getCurrRoom() instanceof TreasureRoomBoss && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*     */       
/* 261 */       TreasureRoomBoss r = (TreasureRoomBoss)AbstractDungeon.getCurrRoom();
/* 262 */       r.chest.close();
/*     */     } 
/* 264 */     AbstractDungeon.closeCurrentScreen();
/* 265 */     (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
/* 266 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 267 */     this.mayVote = false;
/* 268 */     updateVote();
/*     */   }
/*     */   
/*     */   private void updateCancelButton() {
/* 272 */     this.cancelButton.update();
/* 273 */     if (this.cancelButton.hb.clicked) {
/* 274 */       this.cancelButton.hb.clicked = false;
/* 275 */       relicSkipLogic();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateConfirmButton() {
/* 280 */     this.confirmButton.update();
/*     */     
/* 282 */     if (this.confirmButton.hb.clicked) {
/* 283 */       this.confirmButton.hb.clicked = false;
/* 284 */       if (this.touchRelic != null) {
/* 285 */         this.touchRelic.bossObtainLogic();
/*     */       }
/* 287 */       if (this.touchBlight != null) {
/* 288 */         this.touchBlight.bossObtainLogic();
/*     */       }
/*     */     } 
/*     */     
/* 292 */     if (InputHelper.justReleasedClickLeft && (this.touchRelic != null || this.touchBlight != null)) {
/* 293 */       this.touchRelic = null;
/* 294 */       this.touchBlight = null;
/* 295 */       this.confirmButton.hide();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void noPick() {
/* 300 */     ArrayList<String> notPicked = new ArrayList<>();
/* 301 */     HashMap<String, Object> choice = new HashMap<>();
/*     */     
/* 303 */     for (AbstractRelic otherRelics : this.relics) {
/* 304 */       notPicked.add(otherRelics.relicId);
/*     */     }
/* 306 */     choice.put("not_picked", notPicked);
/* 307 */     CardCrawlGame.metricData.boss_relics.add(choice);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 311 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 312 */       e.render(sb);
/*     */     }
/*     */     
/* 315 */     this.cancelButton.render(sb);
/* 316 */     this.confirmButton.render(sb);
/*     */     
/* 318 */     ((TreasureRoomBoss)AbstractDungeon.getCurrRoom()).chest.render(sb);
/* 319 */     AbstractDungeon.player.render(sb);
/*     */     
/* 321 */     sb.setColor(Color.WHITE);
/* 322 */     sb.draw(this.smokeImg, Settings.WIDTH / 2.0F - 490.0F * Settings.scale, AbstractDungeon.floorY - 58.0F * Settings.scale, this.smokeImg
/*     */ 
/*     */ 
/*     */         
/* 326 */         .getWidth() * Settings.scale, this.smokeImg
/* 327 */         .getHeight() * Settings.scale);
/*     */     
/* 329 */     for (AbstractRelic r : this.relics) {
/* 330 */       r.render(sb);
/*     */     }
/*     */     
/* 333 */     for (AbstractBlight b : this.blights) {
/* 334 */       b.render(sb);
/*     */     }
/*     */     
/* 337 */     if (AbstractDungeon.topPanel.twitch.isPresent()) {
/* 338 */       renderTwitchVotes(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderTwitchVotes(SpriteBatch sb) {
/* 343 */     if (!this.isVoting) {
/*     */       return;
/*     */     }
/*     */     
/* 347 */     if (getVoter().isPresent()) {
/* 348 */       TwitchVoter twitchVoter = getVoter().get();
/* 349 */       TwitchVoteOption[] options = twitchVoter.getOptions();
/* 350 */       int sum = ((Integer)Arrays.<TwitchVoteOption>stream(options).map(c -> Integer.valueOf(c.voteCount)).reduce(Integer.valueOf(0), Integer::sum)).intValue();
/* 351 */       for (int i = 0; i < this.relics.size(); i++) {
/* 352 */         String str = "#" + (i + 1) + ": " + (options[i + 1]).voteCount;
/* 353 */         if (sum > 0) {
/* 354 */           str = str + " (" + ((options[i + 1]).voteCount * 100 / sum) + "%)";
/*     */         }
/* 356 */         switch (i) {
/*     */           case 0:
/* 358 */             FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, str, SLOT_1_X, SLOT_1_Y - 75.0F * Settings.scale, Color.WHITE);
/*     */             break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 1:
/* 367 */             FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, str, SLOT_2_X, SLOT_2_Y - 75.0F * Settings.scale, Color.WHITE);
/*     */             break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 2:
/* 376 */             FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, str, SLOT_3_X, SLOT_2_Y - 75.0F * Settings.scale, Color.WHITE);
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 390 */       String s = "#0: " + (options[0]).voteCount;
/* 391 */       if (sum > 0) {
/* 392 */         s = s + " (" + ((options[0]).voteCount * 100 / sum) + "%)";
/*     */       }
/*     */       
/* 395 */       FontHelper.renderFont(sb, FontHelper.panelNameFont, s, 20.0F, 256.0F * Settings.scale, Color.WHITE);
/*     */       
/* 397 */       FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[4] + twitchVoter
/*     */ 
/*     */           
/* 400 */           .getSecondsRemaining() + TEXT[5], Settings.WIDTH / 2.0F, 192.0F * Settings.scale, Color.WHITE);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reopen() {
/* 409 */     this.confirmButton.hideInstantly();
/* 410 */     this.touchRelic = null;
/* 411 */     this.touchBlight = null;
/*     */     
/* 413 */     refresh();
/* 414 */     this.cancelButton.show(TEXT[3]);
/* 415 */     AbstractDungeon.dynamicBanner.appearInstantly(BANNER_Y, SELECT_MSG);
/* 416 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
/* 417 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 418 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*     */   }
/*     */ 
/*     */   
/*     */   public void openBlight(ArrayList<AbstractBlight> chosenBlights) {
/* 423 */     this.confirmButton.hideInstantly();
/* 424 */     this.touchRelic = null;
/* 425 */     this.touchBlight = null;
/*     */     
/* 427 */     refresh();
/* 428 */     this.blights.clear();
/* 429 */     AbstractDungeon.dynamicBanner.appear(BANNER_Y, TEXT[6]);
/* 430 */     this.smokeImg = ImageMaster.BOSS_CHEST_SMOKE;
/* 431 */     AbstractDungeon.isScreenUp = true;
/* 432 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
/* 433 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 434 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*     */     
/* 436 */     AbstractBlight r2 = chosenBlights.get(0);
/* 437 */     r2.spawn(this.B_SLOT_1_X, this.B_SLOT_1_Y);
/* 438 */     r2.hb.move(r2.currentX, r2.currentY);
/* 439 */     this.blights.add(r2);
/*     */     
/* 441 */     AbstractBlight r3 = chosenBlights.get(1);
/* 442 */     r3.spawn(this.B_SLOT_2_X, this.B_SLOT_1_Y);
/* 443 */     r3.hb.move(r3.currentX, r3.currentY);
/* 444 */     this.blights.add(r3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void open(ArrayList<AbstractRelic> chosenRelics) {
/* 449 */     this.confirmButton.hideInstantly();
/* 450 */     this.touchRelic = null;
/* 451 */     this.touchBlight = null;
/*     */     
/* 453 */     refresh();
/* 454 */     this.relics.clear();
/* 455 */     this.blights.clear();
/* 456 */     this.cancelButton.show(TEXT[3]);
/* 457 */     AbstractDungeon.dynamicBanner.appear(BANNER_Y, SELECT_MSG);
/* 458 */     this.smokeImg = ImageMaster.BOSS_CHEST_SMOKE;
/* 459 */     AbstractDungeon.isScreenUp = true;
/* 460 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
/* 461 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 462 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*     */ 
/*     */     
/* 465 */     AbstractRelic r = chosenRelics.get(0);
/* 466 */     r.spawn(SLOT_1_X, SLOT_1_Y);
/* 467 */     r.hb.move(r.currentX, r.currentY);
/* 468 */     this.relics.add(r);
/* 469 */     AbstractRelic r2 = chosenRelics.get(1);
/* 470 */     r2.spawn(SLOT_2_X, SLOT_2_Y);
/* 471 */     r2.hb.move(r2.currentX, r2.currentY);
/* 472 */     this.relics.add(r2);
/* 473 */     AbstractRelic r3 = chosenRelics.get(2);
/* 474 */     r3.spawn(SLOT_3_X, SLOT_2_Y);
/* 475 */     r3.hb.move(r3.currentX, r3.currentY);
/* 476 */     this.relics.add(r3);
/*     */ 
/*     */     
/* 479 */     for (AbstractRelic r1 : this.relics) {
/* 480 */       UnlockTracker.markRelicAsSeen(r1.relicId);
/*     */     }
/*     */     
/* 483 */     this.mayVote = true;
/* 484 */     updateVote();
/*     */   }
/*     */   
/*     */   public void refresh() {
/* 488 */     this.isDone = false;
/* 489 */     this.cancelButton = new MenuCancelButton();
/* 490 */     this.shineTimer = 0.0F;
/*     */   }
/*     */   
/*     */   public void hide() {
/* 494 */     AbstractDungeon.dynamicBanner.hide();
/* 495 */     AbstractDungeon.overlayMenu.cancelButton.hide();
/*     */   }
/*     */   
/*     */   private Optional<TwitchVoter> getVoter() {
/* 499 */     return TwitchPanel.getDefaultVoter();
/*     */   }
/*     */   
/*     */   private void updateVote() {
/* 503 */     if (getVoter().isPresent()) {
/* 504 */       TwitchVoter twitchVoter = getVoter().get();
/*     */       
/* 506 */       if (this.mayVote && twitchVoter.isVotingConnected() && !this.isVoting) {
/* 507 */         logger.info("Publishing Boss Relic Vote");
/* 508 */         this.isVoting = twitchVoter.initiateSimpleNumberVote(
/* 509 */             (String[])Stream.concat(Stream.of("skip"), this.relics.stream().map(AbstractRelic::toString)).toArray(x$0 -> new String[x$0]), this::completeVoting);
/*     */       
/*     */       }
/* 512 */       else if (this.isVoting && (!this.mayVote || !twitchVoter.isVotingConnected())) {
/* 513 */         twitchVoter.endVoting(true);
/* 514 */         this.isVoting = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void completeVoting(int option) {
/* 520 */     if (!this.isVoting) {
/*     */       return;
/*     */     }
/*     */     
/* 524 */     this.isVoting = false;
/* 525 */     if (getVoter().isPresent()) {
/* 526 */       TwitchVoter twitchVoter = getVoter().get();
/* 527 */       AbstractDungeon.topPanel.twitch.ifPresent(twitchPanel -> twitchPanel.connection.sendMessage("Voting on relic ended... chose " + (twitchVoter.getOptions()[option]).displayName));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 534 */     while (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/* 535 */       AbstractDungeon.closeCurrentScreen();
/*     */     }
/*     */     
/* 538 */     if (option == 0) {
/* 539 */       relicSkipLogic();
/* 540 */     } else if (option < this.relics.size() + 1) {
/*     */       
/* 542 */       AbstractRelic r = this.relics.get(option - 1);
/*     */       
/* 544 */       if (!r.relicId.equals("Black Blood") && !r.relicId.equals("Ring of the Serpent")) {
/* 545 */         r.obtain();
/*     */       }
/*     */       
/* 548 */       r.isObtained = true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\select\BossRelicSelectScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */