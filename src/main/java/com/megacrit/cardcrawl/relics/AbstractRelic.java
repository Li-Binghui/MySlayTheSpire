/*      */ package com.megacrit.cardcrawl.relics;
/*      */ 
/*      */ import com.badlogic.gdx.Gdx;
/*      */ import com.badlogic.gdx.graphics.Color;
/*      */ import com.badlogic.gdx.graphics.Texture;
/*      */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*      */ import com.badlogic.gdx.math.Interpolation;
/*      */ import com.badlogic.gdx.math.MathUtils;
/*      */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*      */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*      */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*      */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*      */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*      */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*      */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*      */ import com.megacrit.cardcrawl.core.GameCursor;
/*      */ import com.megacrit.cardcrawl.core.Settings;
/*      */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*      */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*      */ import com.megacrit.cardcrawl.helpers.GameDataStringBuilder;
/*      */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*      */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*      */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*      */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*      */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*      */ import com.megacrit.cardcrawl.helpers.ShaderHelper;
/*      */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*      */ import com.megacrit.cardcrawl.helpers.TipTracker;
/*      */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*      */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*      */ import com.megacrit.cardcrawl.localization.RelicStrings;
/*      */ import com.megacrit.cardcrawl.localization.TutorialStrings;
/*      */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*      */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*      */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*      */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*      */ import com.megacrit.cardcrawl.stances.AbstractStance;
/*      */ import com.megacrit.cardcrawl.ui.FtueTip;
/*      */ import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
/*      */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*      */ import com.megacrit.cardcrawl.vfx.FloatyEffect;
/*      */ import com.megacrit.cardcrawl.vfx.GlowRelicParticle;
/*      */ import com.megacrit.cardcrawl.vfx.SmokePuffEffect;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Scanner;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class AbstractRelic
/*      */   implements Comparable<AbstractRelic>
/*      */ {
/*   58 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Relic Tip");
/*   59 */   public static final String[] MSG = tutorialStrings.TEXT;
/*   60 */   public static final String[] LABEL = tutorialStrings.LABEL; public final String name; public final String relicId; private final RelicStrings relicStrings; public final String[] DESCRIPTIONS;
/*   61 */   public static final String USED_UP_MSG = MSG[2];
/*      */   
/*      */   public boolean energyBased = false;
/*      */   public boolean usedUp = false;
/*      */   public boolean grayscale = false;
/*      */   public String description;
/*   67 */   public String flavorText = "missing"; public int cost;
/*   68 */   public int counter = -1;
/*      */   public RelicTier tier;
/*   70 */   public ArrayList<PowerTip> tips = new ArrayList<>(); public Texture img; public Texture largeImg;
/*      */   public Texture outlineImg;
/*      */   public static final String IMG_DIR = "images/relics/";
/*      */   public static final String OUTLINE_DIR = "images/relics/outline/";
/*      */   private static final String L_IMG_DIR = "images/largeRelics/";
/*   75 */   public String imgUrl = "";
/*      */   
/*      */   public static final int RAW_W = 128;
/*      */   
/*   79 */   public static int relicPage = 0;
/*   80 */   private static float offsetX = 0.0F;
/*   81 */   public static final int MAX_RELICS_PER_PAGE = (int)(Settings.WIDTH / 75.0F * Settings.scale); public float currentX;
/*      */   public float currentY;
/*      */   public float targetX;
/*      */   public float targetY;
/*   85 */   private static final float START_X = 64.0F * Settings.scale;
/*   86 */   private static final float START_Y = Settings.isMobile ? (Settings.HEIGHT - 132.0F * Settings.scale) : (Settings.HEIGHT - 102.0F * Settings.scale);
/*      */   
/*   88 */   public static final float PAD_X = 72.0F * Settings.scale;
/*   89 */   public static final Color PASSIVE_OUTLINE_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.33F);
/*   90 */   private Color flashColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*   91 */   private Color goldOutlineColor = new Color(1.0F, 0.9F, 0.4F, 0.0F);
/*      */   
/*      */   public boolean isSeen = false;
/*   94 */   public float scale = Settings.scale;
/*      */   
/*      */   protected boolean pulse = false;
/*      */   private float animationTimer;
/*   98 */   private float glowTimer = 0.0F;
/*   99 */   public float flashTimer = 0.0F;
/*      */   private static final float FLASH_ANIM_TIME = 2.0F;
/*      */   private static final float DEFAULT_ANIM_SCALE = 4.0F;
/*  102 */   private FloatyEffect f_effect = new FloatyEffect(10.0F, 0.2F);
/*      */   public boolean isDone = false;
/*      */   public boolean isAnimating = false;
/*      */   public boolean isObtained = false;
/*  106 */   private LandingSound landingSFX = LandingSound.CLINK;
/*  107 */   public Hitbox hb = new Hitbox(PAD_X, PAD_X);
/*      */   private static final float OBTAIN_SPEED = 6.0F;
/*      */   private static final float OBTAIN_THRESHOLD = 0.5F;
/*  110 */   private float rotation = 0.0F;
/*      */   public boolean discarded = false;
/*      */   private String assetURL;
/*      */   
/*  114 */   public enum LandingSound { CLINK, FLAT, HEAVY, MAGICAL, SOLID; }
/*      */ 
/*      */   
/*      */   public enum RelicTier {
/*  118 */     DEPRECATED, STARTER, COMMON, UNCOMMON, RARE, SPECIAL, BOSS, SHOP;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public AbstractRelic(String setId, String imgName, RelicTier tier, LandingSound sfx) {
/*  124 */     this.relicId = setId;
/*  125 */     this.relicStrings = CardCrawlGame.languagePack.getRelicStrings(this.relicId);
/*  126 */     this.DESCRIPTIONS = this.relicStrings.DESCRIPTIONS;
/*  127 */     this.imgUrl = imgName;
/*  128 */     ImageMaster.loadRelicImg(setId, imgName);
/*  129 */     this.img = ImageMaster.getRelicImg(setId);
/*  130 */     this.outlineImg = ImageMaster.getRelicOutlineImg(setId);
/*  131 */     this.name = this.relicStrings.NAME;
/*  132 */     this.description = getUpdatedDescription();
/*  133 */     this.flavorText = this.relicStrings.FLAVOR;
/*  134 */     this.tier = tier;
/*  135 */     this.landingSFX = sfx;
/*  136 */     this.assetURL = "images/relics/" + imgName;
/*  137 */     this.tips.add(new PowerTip(this.name, this.description));
/*  138 */     initializeTips();
/*      */   }
/*      */   
/*      */   public void usedUp() {
/*  142 */     this.grayscale = true;
/*  143 */     this.usedUp = true;
/*  144 */     this.description = MSG[2];
/*  145 */     this.tips.clear();
/*  146 */     this.tips.add(new PowerTip(this.name, this.description));
/*  147 */     initializeTips();
/*      */   }
/*      */   
/*      */   public void spawn(float x, float y) {
/*  151 */     if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom)) {
/*  152 */       AbstractDungeon.effectsQueue.add(new SmokePuffEffect(x, y));
/*      */     }
/*  154 */     this.currentX = x;
/*  155 */     this.currentY = y;
/*  156 */     this.isAnimating = true;
/*  157 */     this.isObtained = false;
/*      */     
/*  159 */     if (this.tier == RelicTier.BOSS) {
/*  160 */       this.f_effect.x = 0.0F;
/*  161 */       this.f_effect.y = 0.0F;
/*  162 */       this.targetX = x;
/*  163 */       this.targetY = y;
/*  164 */       this.glowTimer = 0.0F;
/*      */     } else {
/*  166 */       this.f_effect.x = 0.0F;
/*  167 */       this.f_effect.y = 0.0F;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getPrice() {
    switch (this.tier) {
        case STARTER:
            return 300;
        case COMMON:
            return 150;
        case UNCOMMON:
            return 250;
        case RARE:
            return 300;
        case SHOP:
            return 150;
        case SPECIAL:
            return 400;
        case BOSS:
            return 999;
        case DEPRECATED:
            return -1;
        default:
            return -1;
    }

/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reorganizeObtain(AbstractPlayer p, int slot, boolean callOnEquip, int relicAmount) {
/*  202 */     this.isDone = true;
/*  203 */     this.isObtained = true;
/*  204 */     p.relics.add(this);
/*  205 */     this.currentX = START_X + slot * PAD_X;
/*  206 */     this.currentY = START_Y;
/*  207 */     this.targetX = this.currentX;
/*  208 */     this.targetY = this.currentY;
/*  209 */     this.hb.move(this.currentX, this.currentY);
/*  210 */     if (callOnEquip) {
/*  211 */       onEquip();
/*  212 */       relicTip();
/*      */     } 
/*  214 */     UnlockTracker.markRelicAsSeen(this.relicId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void instantObtain(AbstractPlayer p, int slot, boolean callOnEquip) {
/*  225 */     if (this.relicId.equals("Circlet") && p != null && p.hasRelic("Circlet")) {
/*  226 */       AbstractRelic circ = p.getRelic("Circlet");
/*  227 */       circ.counter++;
/*  228 */       circ.flash();
/*      */       
/*  230 */       this.isDone = true;
/*  231 */       this.isObtained = true;
/*  232 */       this.discarded = true;
/*      */     } else {
/*  234 */       this.isDone = true;
/*  235 */       this.isObtained = true;
/*      */       
/*  237 */       if (slot >= p.relics.size()) {
/*  238 */         p.relics.add(this);
/*      */       } else {
/*  240 */         p.relics.set(slot, this);
/*      */       } 
/*      */       
/*  243 */       this.currentX = START_X + slot * PAD_X;
/*  244 */       this.currentY = START_Y;
/*  245 */       this.targetX = this.currentX;
/*  246 */       this.targetY = this.currentY;
/*  247 */       this.hb.move(this.currentX, this.currentY);
/*      */       
/*  249 */       if (callOnEquip) {
/*  250 */         onEquip();
/*  251 */         relicTip();
/*      */       } 
/*      */       
/*  254 */       UnlockTracker.markRelicAsSeen(this.relicId);
/*  255 */       getUpdatedDescription();
/*      */       
/*  257 */       if (AbstractDungeon.topPanel != null) {
/*  258 */         AbstractDungeon.topPanel.adjustRelicHbs();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void instantObtain() {
/*  264 */     if (this.relicId == "Circlet" && AbstractDungeon.player.hasRelic("Circlet")) {
/*  265 */       AbstractRelic circ = AbstractDungeon.player.getRelic("Circlet");
/*  266 */       circ.counter++;
/*  267 */       circ.flash();
/*      */     } else {
/*  269 */       playLandingSFX();
/*  270 */       this.isDone = true;
/*  271 */       this.isObtained = true;
/*  272 */       this.currentX = START_X + AbstractDungeon.player.relics.size() * PAD_X;
/*  273 */       this.currentY = START_Y;
/*  274 */       this.targetX = this.currentX;
/*  275 */       this.targetY = this.currentY;
/*  276 */       flash();
/*      */       
/*  278 */       AbstractDungeon.player.relics.add(this);
/*  279 */       this.hb.move(this.currentX, this.currentY);
/*  280 */       onEquip();
/*  281 */       relicTip();
/*  282 */       UnlockTracker.markRelicAsSeen(this.relicId);
/*      */     } 
/*  284 */     if (AbstractDungeon.topPanel != null) {
/*  285 */       AbstractDungeon.topPanel.adjustRelicHbs();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void obtain() {
/*  293 */     if (this.relicId == "Circlet" && AbstractDungeon.player.hasRelic("Circlet")) {
/*  294 */       AbstractRelic circ = AbstractDungeon.player.getRelic("Circlet");
/*  295 */       circ.counter++;
/*  296 */       circ.flash();
/*  297 */       this.hb.hovered = false;
/*      */     } else {
/*  299 */       this.hb.hovered = false;
/*  300 */       int row = AbstractDungeon.player.relics.size();
/*  301 */       this.targetX = START_X + row * PAD_X;
/*  302 */       this.targetY = START_Y;
/*  303 */       AbstractDungeon.player.relics.add(this);
/*  304 */       relicTip();
/*  305 */       UnlockTracker.markRelicAsSeen(this.relicId);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getColumn() {
/*  310 */     return AbstractDungeon.player.relics.indexOf(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void relicTip() {
/*  315 */     if (TipTracker.relicCounter < 20) {
/*  316 */       TipTracker.relicCounter++;
/*  317 */       if (TipTracker.relicCounter >= 1 && !((Boolean)TipTracker.tips.get("RELIC_TIP")).booleanValue()) {
/*  318 */         AbstractDungeon.ftue = new FtueTip(LABEL[0], MSG[0], 360.0F * Settings.scale, 760.0F * Settings.scale, FtueTip.TipType.RELIC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  324 */         TipTracker.neverShowAgain("RELIC_TIP");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCounter(int counter) {
/*  331 */     this.counter = counter;
/*      */   }
/*      */   
/*      */   public void update() {
/*  335 */     updateFlash();
/*      */     
/*  337 */     if (!this.isDone) {
/*      */       
/*  339 */       if (this.isAnimating) {
/*  340 */         this.glowTimer -= Gdx.graphics.getDeltaTime();
/*  341 */         if (this.glowTimer < 0.0F) {
/*  342 */           this.glowTimer = 0.5F;
/*  343 */           AbstractDungeon.effectList.add(new GlowRelicParticle(this.img, this.currentX + this.f_effect.x, this.currentY + this.f_effect.y, this.rotation));
/*      */         } 
/*      */         
/*  346 */         this.f_effect.update();
/*  347 */         if (this.hb.hovered) {
/*  348 */           this.scale = Settings.scale * 1.5F;
/*      */         } else {
/*  350 */           this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale * 1.1F);
/*      */         }
/*      */       
/*  353 */       } else if (this.hb.hovered) {
/*  354 */         this.scale = Settings.scale * 1.25F;
/*      */       } else {
/*  356 */         this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  361 */       if (this.isObtained) {
/*  362 */         if (this.rotation != 0.0F) {
/*  363 */           this.rotation = MathUtils.lerp(this.rotation, 0.0F, Gdx.graphics.getDeltaTime() * 6.0F * 2.0F);
/*      */         }
/*      */         
/*  366 */         if (this.currentX != this.targetX) {
/*  367 */           this.currentX = MathUtils.lerp(this.currentX, this.targetX, Gdx.graphics.getDeltaTime() * 6.0F);
/*  368 */           if (Math.abs(this.currentX - this.targetX) < 0.5F) {
/*  369 */             this.currentX = this.targetX;
/*      */           }
/*      */         } 
/*  372 */         if (this.currentY != this.targetY) {
/*  373 */           this.currentY = MathUtils.lerp(this.currentY, this.targetY, Gdx.graphics.getDeltaTime() * 6.0F);
/*  374 */           if (Math.abs(this.currentY - this.targetY) < 0.5F) {
/*  375 */             this.currentY = this.targetY;
/*      */           }
/*      */         } 
/*      */         
/*  379 */         if (this.currentY == this.targetY && this.currentX == this.targetX) {
/*  380 */           this.isDone = true;
/*  381 */           if (AbstractDungeon.topPanel != null) {
/*  382 */             AbstractDungeon.topPanel.adjustRelicHbs();
/*      */           }
/*  384 */           this.hb.move(this.currentX, this.currentY);
/*  385 */           if (this.tier == RelicTier.BOSS && AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.TreasureRoomBoss) {
/*  386 */             AbstractDungeon.overlayMenu.proceedButton.show();
/*      */           }
/*  388 */           onEquip();
/*      */         } 
/*  390 */         this.scale = Settings.scale;
/*      */       } 
/*      */       
/*  393 */       if (this.hb != null) {
/*  394 */         this.hb.update();
/*      */         
/*  396 */         if (this.hb.hovered && (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.NEOW_UNLOCK) {
/*      */ 
/*      */           
/*  399 */           if (InputHelper.justClickedLeft && !this.isObtained) {
/*  400 */             InputHelper.justClickedLeft = false;
/*  401 */             this.hb.clickStarted = true;
/*      */           } 
/*      */           
/*  404 */           if ((this.hb.clicked || CInputActionSet.select.isJustPressed()) && !this.isObtained) {
/*  405 */             CInputActionSet.select.unpress();
/*  406 */             this.hb.clicked = false;
/*      */             
/*  408 */             if (!Settings.isTouchScreen) {
/*  409 */               bossObtainLogic();
/*      */             } else {
/*  411 */               AbstractDungeon.bossRelicScreen.confirmButton.show();
/*  412 */               AbstractDungeon.bossRelicScreen.confirmButton.isDisabled = false;
/*  413 */               AbstractDungeon.bossRelicScreen.touchRelic = this;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  418 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*  419 */         updateAnimation();
/*      */       }
/*      */     } else {
/*  422 */       if (AbstractDungeon.player != null && AbstractDungeon.player.relics.indexOf(this) / MAX_RELICS_PER_PAGE == relicPage) {
/*      */         
/*  424 */         this.hb.update();
/*      */       } else {
/*  426 */         this.hb.hovered = false;
/*      */       } 
/*      */       
/*  429 */       if (this.hb.hovered && AbstractDungeon.topPanel.potionUi.isHidden) {
/*  430 */         this.scale = Settings.scale * 1.25F;
/*  431 */         CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
/*      */       } else {
/*  433 */         this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
/*      */       } 
/*  435 */       updateRelicPopupClick();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void bossObtainLogic() {
/*  440 */     if (!this.relicId.equals("HolyWater") && !this.relicId.equals("Black Blood") && !this.relicId.equals("Ring of the Serpent") && 
/*  441 */       !this.relicId.equals("FrozenCore")) {
/*  442 */       obtain();
/*      */     }
/*  444 */     this.isObtained = true;
/*  445 */     this.f_effect.x = 0.0F;
/*  446 */     this.f_effect.y = 0.0F;
/*      */   }
/*      */   
/*      */   private void updateRelicPopupClick() {
/*  450 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/*  451 */       this.hb.clickStarted = true;
/*      */     }
/*  453 */     if (this.hb.clicked || (this.hb.hovered && CInputActionSet.select.isJustPressed())) {
/*  454 */       CardCrawlGame.relicPopup.open(this, AbstractDungeon.player.relics);
/*  455 */       CInputActionSet.select.unpress();
/*  456 */       this.hb.clicked = false;
/*  457 */       this.hb.clickStarted = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateDescription(AbstractPlayer.PlayerClass c) {}
/*      */   
/*      */   public String getUpdatedDescription() {
/*  465 */     return "";
/*      */   }
/*      */   
/*      */   public void playLandingSFX() {
/*  469 */     switch (this.landingSFX) {
/*      */       case CLINK:
/*  471 */         CardCrawlGame.sound.play("RELIC_DROP_CLINK");
/*      */         return;
/*      */       case FLAT:
/*  474 */         CardCrawlGame.sound.play("RELIC_DROP_FLAT");
/*      */         return;
/*      */       case SOLID:
/*  477 */         CardCrawlGame.sound.play("RELIC_DROP_ROCKY");
/*      */         return;
/*      */       case HEAVY:
/*  480 */         CardCrawlGame.sound.play("RELIC_DROP_HEAVY");
/*      */         return;
/*      */       case MAGICAL:
/*  483 */         CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
/*      */         return;
/*      */     } 
/*  486 */     CardCrawlGame.sound.play("RELIC_DROP_CLINK");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateAnimation() {
/*  495 */     if (this.animationTimer != 0.0F) {
/*  496 */       this.animationTimer -= Gdx.graphics.getDeltaTime();
/*  497 */       if (this.animationTimer < 0.0F) {
/*  498 */         this.animationTimer = 0.0F;
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateFlash() {
/*  504 */     if (this.flashTimer != 0.0F) {
/*  505 */       this.flashTimer -= Gdx.graphics.getDeltaTime();
/*  506 */       if (this.flashTimer < 0.0F) {
/*  507 */         if (this.pulse) {
/*  508 */           this.flashTimer = 1.0F;
/*      */         } else {
/*  510 */           this.flashTimer = 0.0F;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEvokeOrb(AbstractOrb ammo) {}
/*      */ 
/*      */   
/*      */   public void onPlayCard(AbstractCard c, AbstractMonster m) {}
/*      */ 
/*      */   
/*      */   public void onPreviewObtainCard(AbstractCard c) {}
/*      */ 
/*      */   
/*      */   public void onObtainCard(AbstractCard c) {}
/*      */ 
/*      */   
/*      */   public void onGainGold() {}
/*      */ 
/*      */   
/*      */   public void onLoseGold() {}
/*      */ 
/*      */   
/*      */   public void onSpendGold() {}
/*      */ 
/*      */   
/*      */   public void onEquip() {}
/*      */ 
/*      */   
/*      */   public void onUnequip() {}
/*      */ 
/*      */   
/*      */   public void atPreBattle() {}
/*      */ 
/*      */   
/*      */   public void atBattleStart() {}
/*      */ 
/*      */   
/*      */   public void onSpawnMonster(AbstractMonster monster) {}
/*      */ 
/*      */   
/*      */   public void atBattleStartPreDraw() {}
/*      */ 
/*      */   
/*      */   public void atTurnStart() {}
/*      */ 
/*      */   
/*      */   public void atTurnStartPostDraw() {}
/*      */ 
/*      */   
/*      */   public void onPlayerEndTurn() {}
/*      */ 
/*      */   
/*      */   public void onBloodied() {}
/*      */ 
/*      */   
/*      */   public void onNotBloodied() {}
/*      */ 
/*      */   
/*      */   public void onManualDiscard() {}
/*      */ 
/*      */   
/*      */   public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {}
/*      */ 
/*      */   
/*      */   public void onVictory() {}
/*      */ 
/*      */   
/*      */   public void onMonsterDeath(AbstractMonster m) {}
/*      */ 
/*      */   
/*      */   public void onBlockBroken(AbstractCreature m) {}
/*      */ 
/*      */   
/*      */   public int onPlayerGainBlock(int blockAmount) {
/*  588 */     return blockAmount;
/*      */   }
/*      */   
/*      */   public int onPlayerGainedBlock(float blockAmount) {
/*  592 */     return MathUtils.floor(blockAmount);
/*      */   }
/*      */   
/*      */   public int onPlayerHeal(int healAmount) {
/*  596 */     return healAmount;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onMeditate() {}
/*      */ 
/*      */   
/*      */   public void onEnergyRecharge() {}
/*      */ 
/*      */   
/*      */   public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {}
/*      */   
/*      */   public boolean canUseCampfireOption(AbstractCampfireOption option) {
/*  609 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onRest() {}
/*      */ 
/*      */   
/*      */   public void onRitual() {}
/*      */ 
/*      */   
/*      */   public void onEnterRestRoom() {}
/*      */ 
/*      */   
/*      */   public void onRefreshHand() {}
/*      */ 
/*      */   
/*      */   public void onShuffle() {}
/*      */ 
/*      */   
/*      */   public void onSmith() {}
/*      */ 
/*      */   
/*      */   public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {}
/*      */   
/*      */   public int onAttacked(DamageInfo info, int damageAmount) {
/*  634 */     return damageAmount;
/*      */   }
/*      */   
/*      */   public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
/*  638 */     return damageAmount;
/*      */   }
/*      */   
/*      */   public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
/*  642 */     return damageAmount;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onExhaust(AbstractCard card) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void onTrigger() {}
/*      */ 
/*      */   
/*      */   public void onTrigger(AbstractCreature target) {}
/*      */ 
/*      */   
/*      */   public boolean checkTrigger() {
/*  658 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onEnterRoom(AbstractRoom room) {}
/*      */ 
/*      */   
/*      */   public void justEnteredRoom(AbstractRoom room) {}
/*      */ 
/*      */   
/*      */   public void onCardDraw(AbstractCard drawnCard) {}
/*      */ 
/*      */   
/*      */   public void onChestOpen(boolean bossChest) {}
/*      */ 
/*      */   
/*      */   public void onChestOpenAfter(boolean bossChest) {}
/*      */ 
/*      */   
/*      */   public void onDrawOrDiscard() {}
/*      */ 
/*      */   
/*      */   public void onMasterDeckChange() {}
/*      */   
/*      */   public float atDamageModify(float damage, AbstractCard c) {
/*  683 */     return damage;
/*      */   }
/*      */   
/*      */   public int changeNumberOfCardsInReward(int numberOfCards) {
/*  687 */     return numberOfCards;
/*      */   }
/*      */   
/*      */   public int changeRareCardRewardChance(int rareCardChance) {
/*  691 */     return rareCardChance;
/*      */   }
/*      */   
/*      */   public int changeUncommonCardRewardChance(int uncommonCardChance) {
/*  695 */     return uncommonCardChance;
/*      */   }
/*      */   
/*      */   public void renderInTopPanel(SpriteBatch sb) {
/*  699 */     if (Settings.hideRelics) {
/*      */       return;
/*      */     }
/*      */     
/*  703 */     renderOutline(sb, true);
/*  704 */     if (this.grayscale) {
/*  705 */       ShaderHelper.setShader(sb, ShaderHelper.Shader.GRAYSCALE);
/*      */     }
/*  707 */     sb.setColor(Color.WHITE);
/*  708 */     sb.draw(this.img, this.currentX - 64.0F + offsetX, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  725 */     if (this.grayscale) {
/*  726 */       ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
/*      */     }
/*  728 */     renderCounter(sb, true);
/*  729 */     renderFlash(sb, true);
/*  730 */     this.hb.render(sb);
/*      */   }
/*      */   
/*      */   public void render(SpriteBatch sb) {
/*  734 */     if (Settings.hideRelics) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  739 */     renderOutline(sb, false);
/*  740 */     if (!this.isObtained && (!AbstractDungeon.isScreenUp || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD || AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP)) {
/*      */ 
/*      */       
/*  743 */       if (this.hb.hovered) {
/*  744 */         renderBossTip(sb);
/*      */       }
/*      */       
/*  747 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*  748 */         if (this.hb.hovered) {
/*  749 */           sb.setColor(PASSIVE_OUTLINE_COLOR);
/*  750 */           sb.draw(this.outlineImg, this.currentX - 64.0F + this.f_effect.x, this.currentY - 64.0F + this.f_effect.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  768 */           sb.setColor(PASSIVE_OUTLINE_COLOR);
/*  769 */           sb.draw(this.outlineImg, this.currentX - 64.0F + this.f_effect.x, this.currentY - 64.0F + this.f_effect.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  790 */     if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*  791 */       if (!this.isObtained) {
/*  792 */         sb.setColor(Color.WHITE);
/*  793 */         sb.draw(this.img, this.currentX - 64.0F + this.f_effect.x, this.currentY - 64.0F + this.f_effect.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  811 */         sb.setColor(Color.WHITE);
/*  812 */         sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  829 */         renderCounter(sb, false);
/*      */       } 
/*      */     } else {
/*  832 */       sb.setColor(Color.WHITE);
/*  833 */       sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  851 */       renderCounter(sb, false);
/*      */     } 
/*      */     
/*  854 */     if (this.isDone) {
/*  855 */       renderFlash(sb, false);
/*      */     }
/*      */     
/*  858 */     this.hb.render(sb);
/*      */   }
/*      */   
/*      */   public void renderLock(SpriteBatch sb, Color outlineColor) {
/*  862 */     sb.setColor(outlineColor);
/*  863 */     sb.draw(ImageMaster.RELIC_LOCK_OUTLINE, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  881 */     sb.setColor(Color.WHITE);
/*  882 */     sb.draw(ImageMaster.RELIC_LOCK, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  900 */     if (this.hb.hovered) {
/*  901 */       String unlockReq = (String)UnlockTracker.unlockReqs.get(this.relicId);
/*  902 */       if (unlockReq == null) {
/*  903 */         unlockReq = "Missing unlock req.";
/*      */       }
/*  905 */       unlockReq = LABEL[2];
/*      */       
/*  907 */       if (InputHelper.mX < 1400.0F * Settings.scale) {
/*  908 */         if (CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.RELIC_VIEW && InputHelper.mY < Settings.HEIGHT / 5.0F)
/*      */         {
/*  910 */           TipHelper.renderGenericTip(InputHelper.mX + 60.0F * Settings.scale, InputHelper.mY + 100.0F * Settings.scale, LABEL[3], unlockReq);
/*      */         
/*      */         }
/*      */         else
/*      */         {
/*      */           
/*  916 */           TipHelper.renderGenericTip(InputHelper.mX + 60.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, LABEL[3], unlockReq);
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  923 */         TipHelper.renderGenericTip(InputHelper.mX - 350.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, LABEL[3], unlockReq);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  930 */       float tmpX = this.currentX;
/*  931 */       float tmpY = this.currentY;
/*      */       
/*  933 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*  934 */         tmpX += this.f_effect.x;
/*  935 */         tmpY += this.f_effect.y;
/*      */       } 
/*      */       
/*  938 */       sb.setColor(Color.WHITE);
/*  939 */       sb.draw(ImageMaster.RELIC_LOCK, tmpX - 64.0F, tmpY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  958 */     this.hb.render(sb);
/*      */   }
/*      */   
/*      */   public void render(SpriteBatch sb, boolean renderAmount, Color outlineColor) {
/*  962 */     if (this.isSeen) {
/*  963 */       renderOutline(outlineColor, sb, false);
/*      */     } else {
/*  965 */       renderOutline(Color.LIGHT_GRAY, sb, false);
/*      */     } 
/*      */     
/*  968 */     if (this.isSeen) {
/*  969 */       sb.setColor(Color.WHITE);
/*      */     }
/*  971 */     else if (this.hb.hovered) {
/*  972 */       sb.setColor(Settings.HALF_TRANSPARENT_BLACK_COLOR);
/*      */     } else {
/*  974 */       sb.setColor(Color.BLACK);
/*      */     } 
/*      */ 
/*      */     
/*  978 */     if (AbstractDungeon.screen != null && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NEOW_UNLOCK) {
/*  979 */       if (this.largeImg == null)
/*      */       {
/*  981 */         sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 2.0F + 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  989 */             MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) / 15.0F, Settings.scale * 2.0F + 
/*  990 */             MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) / 15.0F, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */         
/*  999 */         sb.draw(this.largeImg, this.currentX - 128.0F, this.currentY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale * 1.0F + 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1007 */             MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) / 30.0F, Settings.scale * 1.0F + 
/* 1008 */             MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) / 30.0F, this.rotation, 0, 0, 256, 256, false, false);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1018 */       sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1035 */       if (this.relicId.equals("Circlet")) {
/* 1036 */         renderCounter(sb, false);
/*      */       }
/*      */     } 
/*      */     
/* 1040 */     if (this.hb.hovered && !CardCrawlGame.relicPopup.isOpen) {
/* 1041 */       if (!this.isSeen) {
/* 1042 */         if (InputHelper.mX < 1400.0F * Settings.scale) {
/* 1043 */           TipHelper.renderGenericTip(InputHelper.mX + 60.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, LABEL[1], MSG[1]);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1049 */           TipHelper.renderGenericTip(InputHelper.mX - 350.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, LABEL[1], MSG[1]);
/*      */         } 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/* 1057 */       renderTip(sb);
/*      */     } 
/*      */ 
/*      */     
/* 1061 */     this.hb.render(sb);
/*      */   }
/*      */   
/*      */   public void renderWithoutAmount(SpriteBatch sb, Color c) {
/* 1065 */     renderOutline(c, sb, false);
/* 1066 */     sb.setColor(Color.WHITE);
/* 1067 */     sb.draw(this.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1085 */     if (this.hb.hovered) {
/* 1086 */       renderTip(sb);
/*      */       
/* 1088 */       float tmpX = this.currentX;
/* 1089 */       float tmpY = this.currentY;
/* 1090 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/* 1091 */         tmpX += this.f_effect.x;
/* 1092 */         tmpY += this.f_effect.y;
/*      */       } 
/*      */       
/* 1095 */       sb.setColor(Color.WHITE);
/* 1096 */       sb.draw(this.img, tmpX - 64.0F, tmpY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1115 */     this.hb.render(sb);
/*      */   }
/*      */   
/*      */   public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
/* 1119 */     if (this.counter > -1) {
/* 1120 */       if (inTopPanel) {
/* 1121 */         FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */             
/* 1124 */             Integer.toString(this.counter), offsetX + this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1129 */         FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, 
/*      */ 
/*      */             
/* 1132 */             Integer.toString(this.counter), this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderOutline(Color c, SpriteBatch sb, boolean inTopPanel) {
/* 1141 */     sb.setColor(c);
/* 1142 */     if (AbstractDungeon.screen != null && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NEOW_UNLOCK) {
/* 1143 */       sb.draw(this.outlineImg, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale * 2.0F + 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1151 */           MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) / 15.0F, Settings.scale * 2.0F + 
/* 1152 */           MathUtils.cosDeg((float)(System.currentTimeMillis() / 5L % 360L)) / 15.0F, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1161 */     else if (this.hb.hovered && Settings.isControllerMode) {
/* 1162 */       sb.setBlendFunction(770, 1);
/* 1163 */       this.goldOutlineColor.a = 0.6F + MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L)) / 5.0F;
/* 1164 */       sb.setColor(this.goldOutlineColor);
/* 1165 */       sb.draw(this.outlineImg, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1182 */       sb.setBlendFunction(770, 771);
/*      */     } else {
/* 1184 */       sb.draw(this.outlineImg, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderOutline(SpriteBatch sb, boolean inTopPanel) {
/* 1206 */     float tmpX = this.currentX - 64.0F;
/* 1207 */     if (inTopPanel) {
/* 1208 */       tmpX += offsetX;
/*      */     }
/*      */     
/* 1211 */     if (this.hb.hovered && Settings.isControllerMode) {
/* 1212 */       sb.setBlendFunction(770, 1);
/* 1213 */       this.goldOutlineColor.a = 0.6F + MathUtils.cosDeg((float)(System.currentTimeMillis() / 2L % 360L)) / 5.0F;
/* 1214 */       sb.setColor(this.goldOutlineColor);
/* 1215 */       sb.draw(this.outlineImg, tmpX, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1232 */       sb.setBlendFunction(770, 771);
/*      */     } else {
/*      */       
/* 1235 */       sb.setColor(PASSIVE_OUTLINE_COLOR);
/* 1236 */       sb.draw(this.outlineImg, tmpX, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderFlash(SpriteBatch sb, boolean inTopPanel) {
/* 1257 */     float tmp = Interpolation.exp10In.apply(0.0F, 4.0F, this.flashTimer / 2.0F);
/*      */     
/* 1259 */     sb.setBlendFunction(770, 1);
/* 1260 */     this.flashColor.a = this.flashTimer * 0.2F;
/* 1261 */     sb.setColor(this.flashColor);
/* 1262 */     float tmpX = this.currentX - 64.0F;
/* 1263 */     if (inTopPanel) {
/* 1264 */       tmpX += offsetX;
/*      */     }
/*      */     
/* 1267 */     sb.draw(this.img, tmpX, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale + tmp, this.scale + tmp, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1285 */     sb.draw(this.img, tmpX, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale + tmp * 0.66F, this.scale + tmp * 0.66F, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1303 */     sb.draw(this.img, tmpX, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale + tmp / 3.0F, this.scale + tmp / 3.0F, this.rotation, 0, 0, 128, 128, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1321 */     sb.setBlendFunction(770, 771);
/*      */   }
/*      */   
/*      */   public void beginPulse() {
/* 1325 */     this.flashTimer = 1.0F;
/*      */   }
/*      */   
/*      */   public void beginLongPulse() {
/* 1329 */     this.flashTimer = 1.0F;
/* 1330 */     this.pulse = true;
/*      */   }
/*      */   
/*      */   public void stopPulse() {
/* 1334 */     this.pulse = false;
/*      */   }
/*      */   
/*      */   public void flash() {
/* 1338 */     this.flashTimer = 2.0F;
/*      */   }
/*      */   
/*      */   public void renderBossTip(SpriteBatch sb) {
/* 1342 */     TipHelper.queuePowerTips(Settings.WIDTH * 0.63F, Settings.HEIGHT * 0.63F, this.tips);
/*      */   }
/*      */   
/*      */   public void renderTip(SpriteBatch sb) {
/* 1346 */     if (InputHelper.mX < 1400.0F * Settings.scale) {
/* 1347 */       if (CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.RELIC_VIEW) {
/* 1348 */         TipHelper.queuePowerTips(180.0F * Settings.scale, Settings.HEIGHT * 0.7F, this.tips);
/* 1349 */       } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP && this.tips.size() > 2 && 
/* 1350 */         !AbstractDungeon.player.hasRelic(this.relicId)) {
/* 1351 */         TipHelper.queuePowerTips(InputHelper.mX + 60.0F * Settings.scale, InputHelper.mY + 180.0F * Settings.scale, this.tips);
/*      */ 
/*      */       
/*      */       }
/* 1355 */       else if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(this.relicId)) {
/* 1356 */         TipHelper.queuePowerTips(InputHelper.mX + 60.0F * Settings.scale, InputHelper.mY - 30.0F * Settings.scale, this.tips);
/*      */ 
/*      */       
/*      */       }
/* 1360 */       else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
/* 1361 */         TipHelper.queuePowerTips(360.0F * Settings.scale, InputHelper.mY + 50.0F * Settings.scale, this.tips);
/*      */       } else {
/* 1363 */         TipHelper.queuePowerTips(InputHelper.mX + 50.0F * Settings.scale, InputHelper.mY + 50.0F * Settings.scale, this.tips);
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1369 */       TipHelper.queuePowerTips(InputHelper.mX - 350.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, this.tips);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canPlay(AbstractCard card) {
/* 1377 */     return true;
/*      */   }
/*      */   
/*      */   public static String gameDataUploadHeader() {
/* 1381 */     GameDataStringBuilder builder = new GameDataStringBuilder();
/*      */     
/* 1383 */     builder.addFieldData("name");
/* 1384 */     builder.addFieldData("relicID");
/* 1385 */     builder.addFieldData("color");
/* 1386 */     builder.addFieldData("description");
/* 1387 */     builder.addFieldData("flavorText");
/* 1388 */     builder.addFieldData("cost");
/* 1389 */     builder.addFieldData("tier");
/* 1390 */     builder.addFieldData("assetURL");
/*      */     
/* 1392 */     return builder.toString();
/*      */   }
/*      */   
/*      */   protected void initializeTips() {
/* 1396 */     Scanner desc = new Scanner(this.description);
/*      */     
/* 1398 */     while (desc.hasNext()) {
/* 1399 */       String s = desc.next();
/* 1400 */       if (s.charAt(0) == '#') {
/* 1401 */         s = s.substring(2);
/*      */       }
/*      */       
/* 1404 */       s = s.replace(',', ' ');
/* 1405 */       s = s.replace('.', ' ');
/* 1406 */       s = s.trim();
/* 1407 */       s = s.toLowerCase();
/*      */       
/* 1409 */       boolean alreadyExists = false;
/* 1410 */       if (GameDictionary.keywords.containsKey(s)) {
/* 1411 */         s = (String)GameDictionary.parentWord.get(s);
/* 1412 */         for (PowerTip t : this.tips) {
/* 1413 */           if (t.header.toLowerCase().equals(s)) {
/* 1414 */             alreadyExists = true;
/*      */             break;
/*      */           } 
/*      */         } 
/* 1418 */         if (!alreadyExists) {
/* 1419 */           this.tips.add(new PowerTip(TipHelper.capitalize(s), (String)GameDictionary.keywords.get(s)));
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1424 */     desc.close();
/*      */   }
/*      */   
/*      */   public String gameDataUploadData(String color) {
/* 1428 */     GameDataStringBuilder builder = new GameDataStringBuilder();
/*      */     
/* 1430 */     builder.addFieldData(this.name);
/* 1431 */     builder.addFieldData(this.relicId);
/* 1432 */     builder.addFieldData(color);
/* 1433 */     builder.addFieldData(this.description);
/* 1434 */     builder.addFieldData(this.flavorText);
/* 1435 */     builder.addFieldData(this.cost);
/* 1436 */     builder.addFieldData(this.tier.name());
/* 1437 */     builder.addFieldData(this.assetURL);
/*      */     
/* 1439 */     return builder.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1446 */     return this.name;
/*      */   }
/*      */ 
/*      */   
/*      */   public int compareTo(AbstractRelic arg0) {
/* 1451 */     return this.name.compareTo(arg0.name);
/*      */   }
/*      */   
/*      */   public String getAssetURL() {
/* 1455 */     return this.assetURL;
/*      */   }
/*      */   
/*      */   public HashMap<String, Serializable> getLocStrings() {
/* 1459 */     HashMap<String, Serializable> relicData = new HashMap<>();
/* 1460 */     relicData.put("name", this.name);
/* 1461 */     relicData.put("description", this.description);
/* 1462 */     return relicData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSpawn() {
/* 1471 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onUsePotion() {}
/*      */ 
/*      */   
/*      */   public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {}
/*      */ 
/*      */   
/*      */   public void onLoseHp(int damageAmount) {}
/*      */   
/*      */   public static void updateOffsetX() {
/* 1484 */     float target = (-relicPage * Settings.WIDTH) + relicPage * (PAD_X + 36.0F * Settings.xScale);
/* 1485 */     if (AbstractDungeon.player.relics.size() >= MAX_RELICS_PER_PAGE + 1) {
/* 1486 */       target += 36.0F * Settings.scale;
/*      */     }
/*      */     
/* 1489 */     if (offsetX != target) {
/* 1490 */       offsetX = MathHelper.uiLerpSnap(offsetX, target);
/*      */     }
/*      */   }
/*      */   
/*      */   public void loadLargeImg() {
/* 1495 */     if (this.largeImg == null) {
/* 1496 */       this.largeImg = ImageMaster.loadImage("images/largeRelics/" + this.imgUrl);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void addToBot(AbstractGameAction action) {
/* 1501 */     AbstractDungeon.actionManager.addToBottom(action);
/*      */   }
/*      */   
/*      */   protected void addToTop(AbstractGameAction action) {
/* 1505 */     AbstractDungeon.actionManager.addToTop(action);
/*      */   }
/*      */   
/*      */   public int onLoseHpLast(int damageAmount) {
/* 1509 */     return damageAmount;
/*      */   }
/*      */   
/*      */   public void wasHPLost(int damageAmount) {}
/*      */   
/*      */   public abstract AbstractRelic makeCopy();
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\AbstractRelic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */