/*      */ package com.megacrit.cardcrawl.screens;
/*      */ 
/*      */ import com.badlogic.gdx.Gdx;
/*      */ import com.badlogic.gdx.graphics.Color;
/*      */ import com.badlogic.gdx.graphics.Texture;
/*      */ import com.badlogic.gdx.graphics.g2d.BitmapFont;
/*      */ import com.badlogic.gdx.graphics.g2d.GlyphLayout;
/*      */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*      */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*      */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*      */ import com.badlogic.gdx.math.Interpolation;
/*      */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*      */ import com.megacrit.cardcrawl.cards.CardGroup;
/*      */ import com.megacrit.cardcrawl.cards.DescriptionLine;
/*      */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*      */ import com.megacrit.cardcrawl.core.Settings;
/*      */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*      */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*      */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*      */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*      */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*      */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*      */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*      */ import com.megacrit.cardcrawl.helpers.input.InputActionSet;
/*      */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*      */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*      */ import com.megacrit.cardcrawl.localization.UIStrings;
/*      */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*      */ import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SingleCardViewPopup
/*      */ {
/*   35 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup");
/*   36 */   public static final String[] TEXT = uiStrings.TEXT; public boolean isOpen = false;
/*      */   private CardGroup group;
/*      */   private AbstractCard card;
/*      */   private AbstractCard prevCard;
/*      */   private AbstractCard nextCard;
/*   41 */   private Texture portraitImg = null;
/*      */   private Hitbox nextHb;
/*   43 */   private float fadeTimer = 0.0F; private Hitbox prevHb; private Hitbox cardHb;
/*   44 */   private Color fadeColor = Color.BLACK.cpy(); private static final float LINE_SPACING = 1.53F; private float current_x;
/*      */   private float current_y;
/*      */   private float drawScale;
/*      */   private float card_energy_w;
/*      */   private static final float DESC_OFFSET_Y2 = -12.0F;
/*   49 */   private static final Color CARD_TYPE_COLOR = new Color(0.35F, 0.35F, 0.35F, 1.0F);
/*   50 */   private static final GlyphLayout gl = new GlyphLayout();
/*      */   
/*      */   public static boolean isViewingUpgrade = false;
/*      */   
/*      */   public static boolean enableUpgradeToggle = true;
/*   55 */   private Hitbox upgradeHb = new Hitbox(250.0F * Settings.scale, 80.0F * Settings.scale);
/*      */ 
/*      */   
/*   58 */   private Hitbox betaArtHb = null;
/*      */   private boolean viewBetaArt = false;
/*      */   
/*      */   public SingleCardViewPopup() {
/*   62 */     this.prevHb = new Hitbox(200.0F * Settings.scale, 70.0F * Settings.scale);
/*   63 */     this.nextHb = new Hitbox(200.0F * Settings.scale, 70.0F * Settings.scale);
/*      */   }
/*      */   
/*      */   public void open(AbstractCard card, CardGroup group) {
/*   67 */     CardCrawlGame.isPopupOpen = true;
/*      */     
/*   69 */     this.prevCard = null;
/*   70 */     this.nextCard = null;
/*   71 */     this.prevHb = null;
/*   72 */     this.nextHb = null;
/*      */     
/*   74 */     for (int i = 0; i < group.size(); i++) {
/*   75 */       if (group.group.get(i) == card) {
/*   76 */         if (i != 0) {
/*   77 */           this.prevCard = group.group.get(i - 1);
/*      */         }
/*   79 */         if (i != group.size() - 1) {
/*   80 */           this.nextCard = group.group.get(i + 1);
/*      */         }
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*   86 */     this.prevHb = new Hitbox(160.0F * Settings.scale, 160.0F * Settings.scale);
/*   87 */     this.nextHb = new Hitbox(160.0F * Settings.scale, 160.0F * Settings.scale);
/*   88 */     this.prevHb.move(Settings.WIDTH / 2.0F - 400.0F * Settings.scale, Settings.HEIGHT / 2.0F);
/*   89 */     this.nextHb.move(Settings.WIDTH / 2.0F + 400.0F * Settings.scale, Settings.HEIGHT / 2.0F);
/*      */     
/*   91 */     this.card_energy_w = 24.0F * Settings.scale;
/*   92 */     this.drawScale = 2.0F;
/*   93 */     this.cardHb = new Hitbox(550.0F * Settings.scale, 770.0F * Settings.scale);
/*   94 */     this.cardHb.move(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/*   95 */     this.card = card.makeStatEquivalentCopy();
/*      */     
/*   97 */     loadPortraitImg();
/*      */     
/*   99 */     this.group = group;
/*  100 */     this.isOpen = true;
/*  101 */     this.fadeTimer = 0.25F;
/*  102 */     this.fadeColor.a = 0.0F;
/*  103 */     this.current_x = Settings.WIDTH / 2.0F - 10.0F * Settings.scale;
/*  104 */     this.current_y = Settings.HEIGHT / 2.0F - 300.0F * Settings.scale;
/*      */     
/*  106 */     if (canToggleBetaArt()) {
/*  107 */       if (allowUpgradePreview()) {
/*  108 */         this.betaArtHb = new Hitbox(250.0F * Settings.scale, 80.0F * Settings.scale);
/*  109 */         this.betaArtHb.move(Settings.WIDTH / 2.0F + 270.0F * Settings.scale, 70.0F * Settings.scale);
/*  110 */         this.upgradeHb.move(Settings.WIDTH / 2.0F - 180.0F * Settings.scale, 70.0F * Settings.scale);
/*      */       } else {
/*  112 */         this.betaArtHb = new Hitbox(250.0F * Settings.scale, 80.0F * Settings.scale);
/*  113 */         this.betaArtHb.move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
/*      */       } 
/*  115 */       this.viewBetaArt = UnlockTracker.betaCardPref.getBoolean(card.cardID, false);
/*      */     } else {
/*  117 */       this.upgradeHb.move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
/*  118 */       this.betaArtHb = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean canToggleBetaArt() {
/*  123 */     if (UnlockTracker.isAchievementUnlocked("THE_ENDING")) {
/*  124 */       return true;
/*      */     }
/*  126 */     switch (this.card.color) {
/*      */       case RED:
/*  128 */         return UnlockTracker.isAchievementUnlocked("RUBY_PLUS");
/*      */       case GREEN:
/*  130 */         return UnlockTracker.isAchievementUnlocked("EMERALD_PLUS");
/*      */       case BLUE:
/*  132 */         return UnlockTracker.isAchievementUnlocked("SAPPHIRE_PLUS");
/*      */       case PURPLE:
/*  134 */         return UnlockTracker.isAchievementUnlocked("AMETHYST_PLUS");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  139 */     return false;
/*      */   }
/*      */   
/*      */   private void loadPortraitImg() {
/*  143 */     if (Settings.PLAYTESTER_ART_MODE || UnlockTracker.betaCardPref.getBoolean(this.card.cardID, false)) {
/*  144 */       this.portraitImg = ImageMaster.loadImage("images/1024PortraitsBeta/" + this.card.assetUrl + ".png");
/*      */     } else {
/*  146 */       this.portraitImg = ImageMaster.loadImage("images/1024Portraits/" + this.card.assetUrl + ".png");
/*  147 */       if (this.portraitImg == null) {
/*  148 */         this.portraitImg = ImageMaster.loadImage("images/1024PortraitsBeta/" + this.card.assetUrl + ".png");
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void open(AbstractCard card) {
/*  154 */     CardCrawlGame.isPopupOpen = true;
/*  155 */     this.prevCard = null;
/*  156 */     this.nextCard = null;
/*  157 */     this.prevHb = null;
/*  158 */     this.nextHb = null;
/*      */     
/*  160 */     this.card_energy_w = 24.0F * Settings.scale;
/*  161 */     this.drawScale = 2.0F;
/*  162 */     this.cardHb = new Hitbox(550.0F * Settings.scale, 770.0F * Settings.scale);
/*  163 */     this.cardHb.move(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/*  164 */     this.card = card.makeStatEquivalentCopy();
/*  165 */     loadPortraitImg();
/*  166 */     this.group = null;
/*  167 */     this.isOpen = true;
/*  168 */     this.fadeTimer = 0.25F;
/*  169 */     this.fadeColor.a = 0.0F;
/*  170 */     this.current_x = Settings.WIDTH / 2.0F - 10.0F * Settings.scale;
/*  171 */     this.current_y = Settings.HEIGHT / 2.0F - 300.0F * Settings.scale;
/*      */     
/*  173 */     this.betaArtHb = null;
/*  174 */     if (canToggleBetaArt()) {
/*  175 */       this.betaArtHb = new Hitbox(250.0F * Settings.scale, 80.0F * Settings.scale);
/*  176 */       this.betaArtHb.move(Settings.WIDTH / 2.0F + 270.0F * Settings.scale, 70.0F * Settings.scale);
/*  177 */       this.upgradeHb.move(Settings.WIDTH / 2.0F - 180.0F * Settings.scale, 70.0F * Settings.scale);
/*  178 */       this.viewBetaArt = UnlockTracker.betaCardPref.getBoolean(card.cardID, false);
/*      */     } else {
/*  180 */       this.upgradeHb.move(Settings.WIDTH / 2.0F, 70.0F * Settings.scale);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void close() {
/*  185 */     isViewingUpgrade = false;
/*  186 */     InputHelper.justReleasedClickLeft = false;
/*  187 */     CardCrawlGame.isPopupOpen = false;
/*  188 */     this.isOpen = false;
/*  189 */     if (this.portraitImg != null) {
/*  190 */       this.portraitImg.dispose();
/*  191 */       this.portraitImg = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void update() {
/*  196 */     this.cardHb.update();
/*  197 */     updateArrows();
/*  198 */     updateInput();
/*  199 */     updateFade();
/*  200 */     if (allowUpgradePreview()) {
/*  201 */       updateUpgradePreview();
/*      */     }
/*  203 */     if (this.betaArtHb != null && canToggleBetaArt()) {
/*  204 */       updateBetaArtToggler();
/*      */     }
/*      */   }
/*      */   
/*      */   private void updateBetaArtToggler() {
/*  209 */     this.betaArtHb.update();
/*      */     
/*  211 */     if (this.betaArtHb.hovered && InputHelper.justClickedLeft) {
/*  212 */       this.betaArtHb.clickStarted = true;
/*      */     }
/*      */     
/*  215 */     if (this.betaArtHb.clicked || CInputActionSet.topPanel.isJustPressed()) {
/*  216 */       CInputActionSet.topPanel.unpress();
/*  217 */       this.betaArtHb.clicked = false;
/*  218 */       this.viewBetaArt = !this.viewBetaArt;
/*  219 */       UnlockTracker.betaCardPref.putBoolean(this.card.cardID, this.viewBetaArt);
/*  220 */       UnlockTracker.betaCardPref.flush();
/*      */       
/*  222 */       if (this.portraitImg != null) {
/*  223 */         this.portraitImg.dispose();
/*      */       }
/*      */       
/*  226 */       loadPortraitImg();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateUpgradePreview() {
/*  231 */     this.upgradeHb.update();
/*      */     
/*  233 */     if (this.upgradeHb.hovered && InputHelper.justClickedLeft) {
/*  234 */       this.upgradeHb.clickStarted = true;
/*      */     }
/*      */     
/*  237 */     if (this.upgradeHb.clicked || CInputActionSet.proceed.isJustPressed()) {
/*  238 */       CInputActionSet.proceed.unpress();
/*  239 */       this.upgradeHb.clicked = false;
/*  240 */       isViewingUpgrade = !isViewingUpgrade;
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean allowUpgradePreview() {
/*  245 */     return (enableUpgradeToggle && this.card.color != AbstractCard.CardColor.CURSE && this.card.type != AbstractCard.CardType.STATUS);
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateArrows() {
/*  250 */     if (this.prevCard != null) {
/*  251 */       this.prevHb.update();
/*  252 */       if (this.prevHb.justHovered) {
/*  253 */         CardCrawlGame.sound.play("UI_HOVER");
/*      */       }
/*  255 */       if (this.prevHb.clicked || (this.prevCard != null && CInputActionSet.pageLeftViewDeck.isJustPressed())) {
/*  256 */         CInputActionSet.pageLeftViewDeck.unpress();
/*  257 */         openPrev();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  262 */     if (this.nextCard != null) {
/*  263 */       this.nextHb.update();
/*  264 */       if (this.nextHb.justHovered) {
/*  265 */         CardCrawlGame.sound.play("UI_HOVER");
/*      */       }
/*      */       
/*  268 */       if (this.nextHb.clicked || (this.nextCard != null && CInputActionSet.pageRightViewExhaust.isJustPressed())) {
/*  269 */         CInputActionSet.pageRightViewExhaust.unpress();
/*  270 */         openNext();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateInput() {
/*  276 */     if (InputHelper.justClickedLeft) {
/*  277 */       if (this.prevCard != null && this.prevHb.hovered) {
/*  278 */         this.prevHb.clickStarted = true;
/*  279 */         CardCrawlGame.sound.play("UI_CLICK_1"); return;
/*      */       } 
/*  281 */       if (this.nextCard != null && this.nextHb.hovered) {
/*  282 */         this.nextHb.clickStarted = true;
/*  283 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*  288 */     if (InputHelper.justClickedLeft) {
/*  289 */       if (!this.cardHb.hovered && !this.upgradeHb.hovered && (this.betaArtHb == null || (this.betaArtHb != null && !this.betaArtHb.hovered))) {
/*      */         
/*  291 */         close();
/*  292 */         InputHelper.justClickedLeft = false;
/*  293 */         FontHelper.ClearSCPFontTextures();
/*      */       } 
/*  295 */     } else if (InputHelper.pressedEscape || CInputActionSet.cancel.isJustPressed()) {
/*  296 */       CInputActionSet.cancel.unpress();
/*  297 */       InputHelper.pressedEscape = false;
/*  298 */       close();
/*  299 */       FontHelper.ClearSCPFontTextures();
/*      */     } 
/*      */ 
/*      */     
/*  303 */     if (this.prevCard != null && InputActionSet.left.isJustPressed()) {
/*  304 */       openPrev();
/*  305 */     } else if (this.nextCard != null && InputActionSet.right.isJustPressed()) {
/*  306 */       openNext();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void openPrev() {
/*  311 */     boolean tmp = isViewingUpgrade;
/*  312 */     close();
/*  313 */     open(this.prevCard, this.group);
/*  314 */     isViewingUpgrade = tmp;
/*  315 */     this.fadeTimer = 0.0F;
/*  316 */     this.fadeColor.a = 0.9F;
/*      */   }
/*      */   
/*      */   private void openNext() {
/*  320 */     boolean tmp = isViewingUpgrade;
/*  321 */     close();
/*  322 */     open(this.nextCard, this.group);
/*  323 */     isViewingUpgrade = tmp;
/*  324 */     this.fadeTimer = 0.0F;
/*  325 */     this.fadeColor.a = 0.9F;
/*      */   }
/*      */   
/*      */   private void updateFade() {
/*  329 */     this.fadeTimer -= Gdx.graphics.getDeltaTime();
/*  330 */     if (this.fadeTimer < 0.0F) {
/*  331 */       this.fadeTimer = 0.0F;
/*      */     }
/*  333 */     this.fadeColor.a = Interpolation.pow2In.apply(0.9F, 0.0F, this.fadeTimer * 4.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public void render(SpriteBatch sb) {
/*  338 */     AbstractCard copy = null;
/*  339 */     if (isViewingUpgrade) {
/*  340 */       copy = this.card.makeStatEquivalentCopy();
/*  341 */       this.card.upgrade();
/*  342 */       this.card.displayUpgrades();
/*      */     } 
/*      */     
/*  345 */     sb.setColor(this.fadeColor);
/*  346 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*  347 */     sb.setColor(Color.WHITE);
/*  348 */     renderCardBack(sb);
/*  349 */     renderPortrait(sb);
/*  350 */     renderFrame(sb);
/*  351 */     renderCardBanner(sb);
/*  352 */     renderCardTypeText(sb);
/*      */     
/*  354 */     if (Settings.lineBreakViaCharacter) {
/*  355 */       renderDescriptionCN(sb);
/*      */     } else {
/*  357 */       renderDescription(sb);
/*      */     } 
/*  359 */     renderTitle(sb);
/*  360 */     renderCost(sb);
/*  361 */     renderArrows(sb);
/*  362 */     renderTips(sb);
/*      */     
/*  364 */     this.cardHb.render(sb);
/*  365 */     if (this.nextHb != null) {
/*  366 */       this.nextHb.render(sb);
/*      */     }
/*  368 */     if (this.prevHb != null) {
/*  369 */       this.prevHb.render(sb);
/*      */     }
/*      */     
/*  372 */     FontHelper.cardTitleFont.getData().setScale(1.0F);
/*  373 */     if (canToggleBetaArt()) {
/*  374 */       renderBetaArtToggle(sb);
/*      */     }
/*      */     
/*  377 */     if (allowUpgradePreview()) {
/*  378 */       renderUpgradeViewToggle(sb);
/*  379 */       if (Settings.isControllerMode) {
/*  380 */         sb.draw(CInputActionSet.proceed
/*  381 */             .getKeyImg(), this.upgradeHb.cX - 132.0F * Settings.scale - 32.0F, -32.0F + 67.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*  400 */     if (this.betaArtHb != null && 
/*  401 */       Settings.isControllerMode) {
/*  402 */       sb.draw(CInputActionSet.topPanel
/*  403 */           .getKeyImg(), this.betaArtHb.cX - 132.0F * Settings.scale - 32.0F, -32.0F + 67.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*  422 */     if (copy != null) {
/*  423 */       this.card = copy;
/*      */     }
/*      */   }
/*      */   
/*      */   public void renderCardBack(SpriteBatch sb) {
/*  428 */     TextureAtlas.AtlasRegion tmpImg = getCardBackAtlasRegion();
/*      */     
/*  430 */     if (tmpImg != null) {
/*  431 */       renderHelper(sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, tmpImg);
/*      */     } else {
/*  433 */       Texture img = getCardBackImg();
/*  434 */       if (img != null) {
/*  435 */         sb.draw(img, Settings.WIDTH / 2.0F - 512.0F, Settings.HEIGHT / 2.0F - 512.0F, 512.0F, 512.0F, 1024.0F, 1024.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1024, 1024, false, false);
/*      */       }
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Texture getCardBackImg() {
/*  462 */     switch (this.card.type) {
/*      */       case ATTACK:
/*  464 */         switch (this.card.color) {
/*      */         
/*      */         } 
/*      */       
/*      */       case POWER:
/*  469 */         switch (this.card.color) {
/*      */         
/*      */         } 
/*      */         break;
/*      */     } 
/*  474 */     switch (this.card.color) {
/*      */     
/*      */     } 
/*      */ 
/*      */     
/*  479 */     return null;
/*      */   }
/*      */   
/*      */   private TextureAtlas.AtlasRegion getCardBackAtlasRegion() {
/*  483 */     switch (this.card.type) {
/*      */       case ATTACK:
/*  485 */         switch (this.card.color) {
/*      */           case RED:
/*  487 */             return ImageMaster.CARD_ATTACK_BG_RED_L;
/*      */           case GREEN:
/*  489 */             return ImageMaster.CARD_ATTACK_BG_GREEN_L;
/*      */           case BLUE:
/*  491 */             return ImageMaster.CARD_ATTACK_BG_BLUE_L;
/*      */           case PURPLE:
/*  493 */             return ImageMaster.CARD_ATTACK_BG_PURPLE_L;
/*      */           case COLORLESS:
/*  495 */             return ImageMaster.CARD_ATTACK_BG_GRAY_L;
/*      */         } 
/*      */       
/*      */       
/*      */       case POWER:
/*  500 */         switch (this.card.color) {
/*      */           case RED:
/*  502 */             return ImageMaster.CARD_POWER_BG_RED_L;
/*      */           case GREEN:
/*  504 */             return ImageMaster.CARD_POWER_BG_GREEN_L;
/*      */           case BLUE:
/*  506 */             return ImageMaster.CARD_POWER_BG_BLUE_L;
/*      */           case PURPLE:
/*  508 */             return ImageMaster.CARD_POWER_BG_PURPLE_L;
/*      */           case COLORLESS:
/*  510 */             return ImageMaster.CARD_POWER_BG_GRAY_L;
/*      */         } 
/*      */         
/*      */         break;
/*      */     } 
/*  515 */     switch (this.card.color) {
/*      */       case RED:
/*  517 */         return ImageMaster.CARD_SKILL_BG_RED_L;
/*      */       case GREEN:
/*  519 */         return ImageMaster.CARD_SKILL_BG_GREEN_L;
/*      */       case BLUE:
/*  521 */         return ImageMaster.CARD_SKILL_BG_BLUE_L;
/*      */       case PURPLE:
/*  523 */         return ImageMaster.CARD_SKILL_BG_PURPLE_L;
/*      */       case COLORLESS:
/*  525 */         return ImageMaster.CARD_SKILL_BG_GRAY_L;
/*      */       case CURSE:
/*  527 */         return ImageMaster.CARD_SKILL_BG_BLACK_L;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  532 */     return null;
/*      */   }
/*      */   
/*      */   private void renderPortrait(SpriteBatch sb) {
/*  536 */     TextureAtlas.AtlasRegion img = null;
/*      */     
/*  538 */     if (this.card.isLocked) {
/*  539 */       switch (this.card.type) {
/*      */         case ATTACK:
/*  541 */           img = ImageMaster.CARD_LOCKED_ATTACK_L;
/*      */           break;
/*      */         case POWER:
/*  544 */           img = ImageMaster.CARD_LOCKED_POWER_L;
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  549 */           img = ImageMaster.CARD_LOCKED_SKILL_L;
/*      */           break;
/*      */       } 
/*  552 */       renderHelper(sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 136.0F * Settings.scale, img);
/*      */     }
/*  554 */     else if (this.portraitImg != null) {
/*  555 */       sb.draw(this.portraitImg, Settings.WIDTH / 2.0F - 250.0F, Settings.HEIGHT / 2.0F - 190.0F + 136.0F * Settings.scale, 250.0F, 190.0F, 500.0F, 380.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 500, 380, false, false);
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
/*      */     }
/*  572 */     else if (this.card.jokePortrait != null) {
/*  573 */       sb.draw((TextureRegion)this.card.jokePortrait, Settings.WIDTH / 2.0F - this.card.portrait.packedWidth / 2.0F, Settings.HEIGHT / 2.0F - this.card.portrait.packedHeight / 2.0F + 140.0F * Settings.scale, this.card.portrait.packedWidth / 2.0F, this.card.portrait.packedHeight / 2.0F, this.card.portrait.packedWidth, this.card.portrait.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, 0.0F);
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
/*      */   private void renderFrame(SpriteBatch sb) {
/*  589 */     TextureAtlas.AtlasRegion tmpImg = null;
/*  590 */     float tOffset = 0.0F, tWidth = 0.0F;
/*      */     
/*  592 */     switch (this.card.type) {
/*      */       case ATTACK:
/*  594 */         tWidth = AbstractCard.typeWidthAttack;
/*  595 */         tOffset = AbstractCard.typeOffsetAttack;
/*  596 */         switch (this.card.rarity) {
/*      */           case UNCOMMON:
/*  598 */             tmpImg = ImageMaster.CARD_FRAME_ATTACK_UNCOMMON_L;
/*      */             break;
/*      */           case RARE:
/*  601 */             tmpImg = ImageMaster.CARD_FRAME_ATTACK_RARE_L;
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*  606 */         tmpImg = ImageMaster.CARD_FRAME_ATTACK_COMMON_L;
/*      */         break;
/*      */ 
/*      */       
/*      */       case POWER:
/*  611 */         tWidth = AbstractCard.typeWidthPower;
/*  612 */         tOffset = AbstractCard.typeOffsetPower;
/*  613 */         switch (this.card.rarity) {
/*      */           case UNCOMMON:
/*  615 */             tmpImg = ImageMaster.CARD_FRAME_POWER_UNCOMMON_L;
/*      */             break;
/*      */           case RARE:
/*  618 */             tmpImg = ImageMaster.CARD_FRAME_POWER_RARE_L;
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*  623 */         tmpImg = ImageMaster.CARD_FRAME_POWER_COMMON_L;
/*      */         break;
/*      */ 
/*      */       
/*      */       case CURSE:
/*  628 */         tWidth = AbstractCard.typeWidthCurse;
/*  629 */         tOffset = AbstractCard.typeOffsetCurse;
/*  630 */         switch (this.card.rarity) {
/*      */           case UNCOMMON:
/*  632 */             tmpImg = ImageMaster.CARD_FRAME_SKILL_UNCOMMON_L;
/*      */             break;
/*      */           case RARE:
/*  635 */             tmpImg = ImageMaster.CARD_FRAME_SKILL_RARE_L;
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*  640 */         tmpImg = ImageMaster.CARD_FRAME_SKILL_COMMON_L;
/*      */         break;
/*      */ 
/*      */       
/*      */       case STATUS:
/*  645 */         tWidth = AbstractCard.typeWidthStatus;
/*  646 */         tOffset = AbstractCard.typeOffsetStatus;
/*  647 */         switch (this.card.rarity) {
/*      */           case UNCOMMON:
/*  649 */             tmpImg = ImageMaster.CARD_FRAME_SKILL_UNCOMMON_L;
/*      */             break;
/*      */           case RARE:
/*  652 */             tmpImg = ImageMaster.CARD_FRAME_SKILL_RARE_L;
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*  657 */         tmpImg = ImageMaster.CARD_FRAME_SKILL_COMMON_L;
/*      */         break;
/*      */ 
/*      */       
/*      */       case SKILL:
/*  662 */         tWidth = AbstractCard.typeWidthSkill;
/*  663 */         tOffset = AbstractCard.typeOffsetSkill;
/*  664 */         switch (this.card.rarity) {
/*      */           case UNCOMMON:
/*  666 */             tmpImg = ImageMaster.CARD_FRAME_SKILL_UNCOMMON_L;
/*      */             break;
/*      */           case RARE:
/*  669 */             tmpImg = ImageMaster.CARD_FRAME_SKILL_RARE_L;
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*  674 */         tmpImg = ImageMaster.CARD_FRAME_SKILL_COMMON_L;
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  680 */     if (tmpImg != null) {
/*  681 */       renderHelper(sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, tmpImg);
/*      */     } else {
/*  683 */       Texture img = getFrameImg();
/*      */       
/*  685 */       tWidth = AbstractCard.typeWidthSkill;
/*  686 */       tOffset = AbstractCard.typeOffsetSkill;
/*      */       
/*  688 */       if (img != null) {
/*  689 */         sb.draw(img, Settings.WIDTH / 2.0F - 512.0F, Settings.HEIGHT / 2.0F - 512.0F, 512.0F, 512.0F, 1024.0F, 1024.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1024, 1024, false, false);
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
/*  707 */         renderHelper(sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, ImageMaster.CARD_FRAME_SKILL_COMMON_L);
/*      */       } 
/*      */     } 
/*      */     
/*  711 */     renderDynamicFrame(sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, tOffset, tWidth);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Texture getFrameImg() {
/*  720 */     switch (this.card.rarity) {
/*      */     
/*  722 */     }  return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderDynamicFrame(SpriteBatch sb, float x, float y, float typeOffset, float typeWidth) {
/*  727 */     if (typeWidth <= 1.1F) {
/*      */       return;
/*      */     }
/*      */     
/*  731 */     switch (this.card.rarity) {
/*      */ 
/*      */ 
/*      */       
/*      */       case COMMON:
/*      */       case BASIC:
/*      */       case CURSE:
/*      */       case SPECIAL:
/*  739 */         dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_MID_L, 0.0F, typeWidth);
/*  740 */         dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_LEFT_L, -typeOffset, 1.0F);
/*  741 */         dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_RIGHT_L, typeOffset, 1.0F);
/*      */         break;
/*      */       case UNCOMMON:
/*  744 */         dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_MID_L, 0.0F, typeWidth);
/*  745 */         dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_LEFT_L, -typeOffset, 1.0F);
/*  746 */         dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_RIGHT_L, typeOffset, 1.0F);
/*      */         break;
/*      */       case RARE:
/*  749 */         dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_MID_L, 0.0F, typeWidth);
/*  750 */         dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_LEFT_L, -typeOffset, 1.0F);
/*  751 */         dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_RIGHT_L, typeOffset, 1.0F);
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void dynamicFrameRenderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float xOffset, float xScale) {
/*  757 */     sb.draw((TextureRegion)img, Settings.WIDTH / 2.0F + img.offsetX - img.originalWidth / 2.0F + xOffset * this.drawScale, Settings.HEIGHT / 2.0F + img.offsetY - img.originalHeight / 2.0F, img.originalWidth / 2.0F - img.offsetX, img.originalHeight / 2.0F - img.offsetY, img.packedWidth, img.packedHeight, Settings.scale * xScale, Settings.scale, 0.0F);
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
/*      */   private void renderCardBanner(SpriteBatch sb) {
/*  771 */     TextureAtlas.AtlasRegion tmpImg = null;
/*      */     
/*  773 */     switch (this.card.rarity) {
/*      */       case UNCOMMON:
/*  775 */         tmpImg = ImageMaster.CARD_BANNER_UNCOMMON_L;
/*      */         break;
/*      */       case RARE:
/*  778 */         tmpImg = ImageMaster.CARD_BANNER_RARE_L;
/*      */         break;
/*      */       case COMMON:
/*  781 */         tmpImg = ImageMaster.CARD_BANNER_COMMON_L;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  786 */     if (tmpImg != null) {
/*  787 */       renderHelper(sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, tmpImg);
/*      */     } else {
/*  789 */       Texture img = getBannerImg();
/*  790 */       if (img != null) {
/*  791 */         sb.draw(img, Settings.WIDTH / 2.0F - 512.0F, Settings.HEIGHT / 2.0F - 512.0F, 512.0F, 512.0F, 1024.0F, 1024.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1024, 1024, false, false);
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
/*  809 */         tmpImg = ImageMaster.CARD_BANNER_COMMON_L;
/*  810 */         renderHelper(sb, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, tmpImg);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Texture getBannerImg() {
/*  821 */     switch (this.card.rarity) {
/*      */     
/*  823 */     }  return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getDynamicValue(char key) {
/*  828 */     switch (key) {
/*      */       case 'B':
/*  830 */         if (this.card.isBlockModified) {
/*  831 */           if (this.card.block >= this.card.baseBlock) {
/*  832 */             return "[#7fff00]" + Integer.toString(this.card.block) + "[]";
/*      */           }
/*  834 */           return "[#ff6563]" + Integer.toString(this.card.block) + "[]";
/*      */         } 
/*      */         
/*  837 */         return Integer.toString(this.card.baseBlock);
/*      */       
/*      */       case 'D':
/*  840 */         if (this.card.isDamageModified) {
/*  841 */           if (this.card.damage >= this.card.baseDamage) {
/*  842 */             return "[#7fff00]" + Integer.toString(this.card.damage) + "[]";
/*      */           }
/*  844 */           return "[#ff6563]" + Integer.toString(this.card.damage) + "[]";
/*      */         } 
/*      */         
/*  847 */         return Integer.toString(this.card.baseDamage);
/*      */       
/*      */       case 'M':
/*  850 */         if (this.card.isMagicNumberModified) {
/*  851 */           if (this.card.magicNumber >= this.card.baseMagicNumber) {
/*  852 */             return "[#7fff00]" + Integer.toString(this.card.magicNumber) + "[]";
/*      */           }
/*  854 */           return "[#ff6563]" + Integer.toString(this.card.magicNumber) + "[]";
/*      */         } 
/*      */         
/*  857 */         return Integer.toString(this.card.baseMagicNumber);
/*      */     } 
/*      */     
/*  860 */     return Integer.toString(-99);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderDescriptionCN(SpriteBatch sb) {
/*  868 */     if (this.card.isLocked || !this.card.isSeen) {
/*  869 */       FontHelper.renderFontCentered(sb, FontHelper.largeCardFont, "? ? ?", Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 195.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  879 */     BitmapFont font = FontHelper.SCP_cardDescFont;
/*  880 */     float draw_y = this.current_y + 100.0F * Settings.scale;
/*  881 */     draw_y += this.card.description.size() * font.getCapHeight() * 0.775F - font.getCapHeight() * 0.375F;
/*  882 */     float spacing = 1.53F * -font.getCapHeight() / Settings.scale / this.drawScale;
/*  883 */     GlyphLayout gl = new GlyphLayout();
/*      */     
/*  885 */     for (int i = 0; i < this.card.description.size(); i++) {
/*  886 */       float start_x = 0.0F;
/*      */       
/*  888 */       if (Settings.leftAlignCards) {
/*  889 */         start_x = this.current_x - 214.0F * Settings.scale;
/*      */       } else {
/*  891 */         start_x = this.current_x - ((DescriptionLine)this.card.description.get(i)).width * this.drawScale / 2.0F - 20.0F * Settings.scale;
/*      */       } 
/*      */       
/*  894 */       for (String tmp : ((DescriptionLine)this.card.description.get(i)).getCachedTokenizedTextCN()) {
/*  895 */         String updateTmp = null; int j;
/*  896 */         for (j = 0; j < tmp.length(); j++) {
/*  897 */           if (tmp.charAt(j) == 'D' || (tmp.charAt(j) == 'B' && !tmp.contains("[B]")) || tmp.charAt(j) == 'M') {
/*      */             
/*  899 */             updateTmp = tmp.substring(0, j);
/*  900 */             updateTmp = updateTmp + getDynamicValue(tmp.charAt(j));
/*  901 */             updateTmp = updateTmp + tmp.substring(j + 1);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  906 */         if (updateTmp != null) {
/*  907 */           tmp = updateTmp;
/*      */         }
/*      */ 
/*      */         
/*  911 */         for (j = 0; j < tmp.length(); j++) {
/*  912 */           if (tmp.charAt(j) == 'D' || (tmp.charAt(j) == 'B' && !tmp.contains("[B]")) || tmp.charAt(j) == 'M') {
/*      */             
/*  914 */             updateTmp = tmp.substring(0, j);
/*  915 */             updateTmp = updateTmp + getDynamicValue(tmp.charAt(j));
/*  916 */             updateTmp = updateTmp + tmp.substring(j + 1);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*  921 */         if (updateTmp != null) {
/*  922 */           tmp = updateTmp;
/*      */         }
/*      */ 
/*      */         
/*  926 */         if (!tmp.isEmpty() && tmp.charAt(0) == '*') {
/*  927 */           tmp = tmp.substring(1);
/*  928 */           String punctuation = "";
/*  929 */           if (tmp.length() > 1 && tmp.charAt(tmp.length() - 2) != '+' && !Character.isLetter(tmp
/*  930 */               .charAt(tmp.length() - 2))) {
/*  931 */             punctuation = punctuation + tmp.charAt(tmp.length() - 2);
/*  932 */             tmp = tmp.substring(0, tmp.length() - 2);
/*  933 */             punctuation = punctuation + ' ';
/*      */           } 
/*      */           
/*  936 */           gl.setText(font, tmp);
/*  937 */           FontHelper.renderRotatedText(sb, font, tmp, this.current_x, this.current_y, start_x - this.current_x + gl.width / 2.0F, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  944 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.GOLD_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  949 */           start_x = Math.round(start_x + gl.width);
/*  950 */           gl.setText(font, punctuation);
/*  951 */           FontHelper.renderRotatedText(sb, font, punctuation, this.current_x, this.current_y, start_x - this.current_x + gl.width / 2.0F, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  958 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  963 */           gl.setText(font, punctuation);
/*  964 */           start_x += gl.width;
/*      */         
/*      */         }
/*  967 */         else if (tmp.equals("[R]")) {
/*  968 */           gl.width = this.card_energy_w * this.drawScale;
/*  969 */           renderSmallEnergy(sb, AbstractCard.orb_red, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/*  973 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/*  975 */           start_x += gl.width;
/*  976 */         } else if (tmp.equals("[G]")) {
/*  977 */           gl.width = this.card_energy_w * this.drawScale;
/*  978 */           renderSmallEnergy(sb, AbstractCard.orb_green, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/*  982 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/*  984 */           start_x += gl.width;
/*  985 */         } else if (tmp.equals("[B]")) {
/*  986 */           gl.width = this.card_energy_w * this.drawScale;
/*  987 */           renderSmallEnergy(sb, AbstractCard.orb_blue, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/*  991 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/*  993 */           start_x += gl.width;
/*      */         
/*      */         }
/*  996 */         else if (tmp.equals("[W]")) {
/*  997 */           gl.width = this.card_energy_w * this.drawScale;
/*  998 */           renderSmallEnergy(sb, AbstractCard.orb_purple, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/* 1002 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/* 1004 */           start_x += gl.width;
/*      */         }
/*      */         else {
/*      */           
/* 1008 */           gl.setText(font, tmp);
/* 1009 */           FontHelper.renderRotatedText(sb, font, tmp, this.current_x, this.current_y, start_x - this.current_x + gl.width / 2.0F, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1016 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */           
/* 1020 */           start_x += gl.width;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1024 */     font.getData().setScale(1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderDescription(SpriteBatch sb) {
/* 1031 */     if (this.card.isLocked || !this.card.isSeen) {
/* 1032 */       FontHelper.renderFontCentered(sb, FontHelper.largeCardFont, "? ? ?", Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 195.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1042 */     BitmapFont font = FontHelper.SCP_cardDescFont;
/* 1043 */     float draw_y = this.current_y + 100.0F * Settings.scale;
/* 1044 */     draw_y += this.card.description.size() * font.getCapHeight() * 0.775F - font.getCapHeight() * 0.375F;
/* 1045 */     float spacing = 1.53F * -font.getCapHeight() / Settings.scale / this.drawScale;
/* 1046 */     GlyphLayout gl = new GlyphLayout();
/*      */     
/* 1048 */     for (int i = 0; i < this.card.description.size(); i++) {
/* 1049 */       float start_x = this.current_x - ((DescriptionLine)this.card.description.get(i)).width * this.drawScale / 2.0F;
/*      */       
/* 1051 */       for (String tmp : ((DescriptionLine)this.card.description.get(i)).getCachedTokenizedText()) {
/*      */ 
/*      */         
/* 1054 */         if (tmp.charAt(0) == '*') {
/* 1055 */           tmp = tmp.substring(1);
/* 1056 */           String punctuation = "";
/* 1057 */           if (tmp.length() > 1 && tmp.charAt(tmp.length() - 2) != '+' && !Character.isLetter(tmp
/* 1058 */               .charAt(tmp.length() - 2))) {
/* 1059 */             punctuation = punctuation + tmp.charAt(tmp.length() - 2);
/* 1060 */             tmp = tmp.substring(0, tmp.length() - 2);
/* 1061 */             punctuation = punctuation + ' ';
/*      */           } 
/*      */           
/* 1064 */           gl.setText(font, tmp);
/* 1065 */           FontHelper.renderRotatedText(sb, font, tmp, this.current_x, this.current_y, start_x - this.current_x + gl.width / 2.0F, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1072 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.GOLD_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1077 */           start_x = Math.round(start_x + gl.width);
/* 1078 */           gl.setText(font, punctuation);
/* 1079 */           FontHelper.renderRotatedText(sb, font, punctuation, this.current_x, this.current_y, start_x - this.current_x + gl.width / 2.0F, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1086 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1091 */           gl.setText(font, punctuation);
/* 1092 */           start_x += gl.width;
/*      */         
/*      */         }
/* 1095 */         else if (tmp.charAt(0) == '!') {
/* 1096 */           if (tmp.length() == 4) {
/* 1097 */             start_x += renderDynamicVariable(tmp.charAt(1), start_x, draw_y, i, font, sb, null);
/* 1098 */           } else if (tmp.length() == 5) {
/* 1099 */             start_x += renderDynamicVariable(tmp.charAt(1), start_x, draw_y, i, font, sb, Character.valueOf(tmp.charAt(3)));
/*      */           }
/*      */         
/*      */         }
/* 1103 */         else if (tmp.equals("[R] ")) {
/* 1104 */           gl.width = this.card_energy_w * this.drawScale;
/* 1105 */           renderSmallEnergy(sb, AbstractCard.orb_red, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/* 1109 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/* 1111 */           start_x += gl.width;
/*      */         
/*      */         }
/* 1114 */         else if (tmp.equals("[R]. ")) {
/* 1115 */           gl.width = this.card_energy_w * this.drawScale / Settings.scale;
/* 1116 */           renderSmallEnergy(sb, AbstractCard.orb_red, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/* 1120 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/* 1122 */           FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, start_x - this.current_x + this.card_energy_w * this.drawScale, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1129 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1134 */           start_x += gl.width;
/* 1135 */           gl.setText(font, LocalizedStrings.PERIOD);
/* 1136 */           start_x += gl.width;
/*      */         }
/* 1138 */         else if (tmp.equals("[G] ")) {
/* 1139 */           gl.width = this.card_energy_w * this.drawScale;
/* 1140 */           renderSmallEnergy(sb, AbstractCard.orb_green, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/* 1144 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/* 1146 */           start_x += gl.width;
/*      */         }
/* 1148 */         else if (tmp.equals("[G]. ")) {
/* 1149 */           gl.width = this.card_energy_w * this.drawScale;
/* 1150 */           renderSmallEnergy(sb, AbstractCard.orb_green, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/* 1154 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/* 1156 */           FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, start_x - this.current_x + this.card_energy_w * this.drawScale, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1163 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */           
/* 1167 */           start_x += gl.width;
/*      */         
/*      */         }
/* 1170 */         else if (tmp.equals("[B] ")) {
/* 1171 */           gl.width = this.card_energy_w * this.drawScale;
/* 1172 */           renderSmallEnergy(sb, AbstractCard.orb_blue, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/* 1176 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/* 1178 */           start_x += gl.width;
/*      */         }
/* 1180 */         else if (tmp.equals("[B]. ")) {
/* 1181 */           gl.width = this.card_energy_w * this.drawScale;
/* 1182 */           renderSmallEnergy(sb, AbstractCard.orb_blue, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/* 1186 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/* 1188 */           FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, start_x - this.current_x + this.card_energy_w * this.drawScale, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1195 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */           
/* 1199 */           start_x += gl.width;
/*      */         
/*      */         }
/* 1202 */         else if (tmp.equals("[W] ")) {
/* 1203 */           gl.width = this.card_energy_w * this.drawScale;
/* 1204 */           renderSmallEnergy(sb, AbstractCard.orb_purple, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/* 1208 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/* 1210 */           start_x += gl.width;
/*      */         }
/* 1212 */         else if (tmp.equals("[W]. ")) {
/* 1213 */           gl.width = this.card_energy_w * this.drawScale;
/* 1214 */           renderSmallEnergy(sb, AbstractCard.orb_purple, (start_x - this.current_x) / Settings.scale / this.drawScale, -87.0F - ((this.card.description
/*      */ 
/*      */ 
/*      */               
/* 1218 */               .size() - 4.0F) / 2.0F - i + 1.0F) * spacing);
/*      */           
/* 1220 */           FontHelper.renderRotatedText(sb, font, LocalizedStrings.PERIOD, this.current_x, this.current_y, start_x - this.current_x + this.card_energy_w * this.drawScale, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1227 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */           
/* 1231 */           start_x += gl.width;
/*      */         }
/*      */         else {
/*      */           
/* 1235 */           gl.setText(font, tmp);
/* 1236 */           FontHelper.renderRotatedText(sb, font, tmp, this.current_x, this.current_y, start_x - this.current_x + gl.width / 2.0F, i * 1.53F * 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1243 */               -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */           
/* 1247 */           start_x += gl.width;
/*      */         } 
/*      */       } 
/*      */     } 
/* 1251 */     font.getData().setScale(1.0F);
/*      */   }
/*      */   
/*      */   private void renderSmallEnergy(SpriteBatch sb, TextureAtlas.AtlasRegion region, float x, float y) {
/* 1255 */     sb.setColor(Color.WHITE);
/* 1256 */     sb.draw(region
/* 1257 */         .getTexture(), this.current_x + x * Settings.scale * this.drawScale + region.offsetX * Settings.scale - 4.0F * Settings.scale, this.current_y + y * Settings.scale * this.drawScale + 280.0F * Settings.scale, 0.0F, 0.0F, region.packedWidth, region.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, 0.0F, region
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1267 */         .getRegionX(), region
/* 1268 */         .getRegionY(), region
/* 1269 */         .getRegionWidth(), region
/* 1270 */         .getRegionHeight(), false, false);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderCardTypeText(SpriteBatch sb) {
/* 1276 */     String label = "";
/* 1277 */     switch (this.card.type) {
/*      */       case ATTACK:
/* 1279 */         label = TEXT[0];
/*      */         break;
/*      */       case SKILL:
/* 1282 */         label = TEXT[1];
/*      */         break;
/*      */       case POWER:
/* 1285 */         label = TEXT[2];
/*      */         break;
/*      */       case CURSE:
/* 1288 */         label = TEXT[3];
/*      */         break;
/*      */       case STATUS:
/* 1291 */         label = TEXT[7];
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1297 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, label, Settings.WIDTH / 2.0F + 3.0F * Settings.scale, Settings.HEIGHT / 2.0F - 40.0F * Settings.scale, CARD_TYPE_COLOR);
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
/*      */   private float renderDynamicVariable(char key, float start_x, float draw_y, int i, BitmapFont font, SpriteBatch sb, Character end) {
/* 1315 */     StringBuilder stringBuilder = new StringBuilder();
/* 1316 */     Color c = null;
/* 1317 */     int num = 0;
/*      */     
/* 1319 */     switch (key) {
/*      */       case 'D':
/* 1321 */         num = this.card.baseDamage;
/* 1322 */         if (this.card.upgradedDamage) {
/* 1323 */           c = Settings.GREEN_TEXT_COLOR; break;
/*      */         } 
/* 1325 */         c = Settings.CREAM_COLOR;
/*      */         break;
/*      */       
/*      */       case 'B':
/* 1329 */         num = this.card.baseBlock;
/* 1330 */         if (this.card.upgradedBlock) {
/* 1331 */           c = Settings.GREEN_TEXT_COLOR; break;
/*      */         } 
/* 1333 */         c = Settings.CREAM_COLOR;
/*      */         break;
/*      */       
/*      */       case 'M':
/* 1337 */         num = this.card.baseMagicNumber;
/* 1338 */         if (this.card.upgradedMagicNumber) {
/* 1339 */           c = Settings.GREEN_TEXT_COLOR; break;
/*      */         } 
/* 1341 */         c = Settings.CREAM_COLOR;
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1348 */     stringBuilder.append(Integer.toString(num));
/* 1349 */     gl.setText(font, stringBuilder.toString());
/* 1350 */     FontHelper.renderRotatedText(sb, font, stringBuilder
/*      */ 
/*      */         
/* 1353 */         .toString(), this.current_x, this.current_y, start_x - this.current_x + gl.width / 2.0F, i * 1.53F * 
/*      */ 
/*      */ 
/*      */         
/* 1357 */         -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, c);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1362 */     if (end != null) {
/* 1363 */       FontHelper.renderRotatedText(sb, font, 
/*      */ 
/*      */           
/* 1366 */           Character.toString(end.charValue()), this.current_x, this.current_y, start_x - this.current_x + gl.width + 10.0F * Settings.scale, i * 1.53F * 
/*      */ 
/*      */ 
/*      */           
/* 1370 */           -font.getCapHeight() + draw_y - this.current_y + -12.0F, 0.0F, true, Settings.CREAM_COLOR);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1376 */     stringBuilder.append(' ');
/* 1377 */     gl.setText(font, stringBuilder.toString());
/* 1378 */     return gl.width;
/*      */   }
/*      */   
/*      */   private void renderTitle(SpriteBatch sb) {
/* 1382 */     if (this.card.isLocked) {
/* 1383 */       FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, TEXT[4], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 338.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1390 */     else if (this.card.isSeen) {
/* 1391 */       if (!isViewingUpgrade || allowUpgradePreview()) {
/* 1392 */         FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, this.card.name, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 338.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/* 1400 */         FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, this.card.name, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 338.0F * Settings.scale, Settings.GREEN_TEXT_COLOR);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1410 */       FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, TEXT[5], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F + 338.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderCost(SpriteBatch sb) {
/* 1421 */     if (this.card.isLocked || !this.card.isSeen) {
/*      */       return;
/*      */     }
/* 1424 */     if (this.card.cost > -2) {
/* 1425 */       TextureAtlas.AtlasRegion tmpImg = null;
/* 1426 */       switch (this.card.color) {
/*      */         case RED:
/* 1428 */           tmpImg = ImageMaster.CARD_RED_ORB_L;
/*      */           break;
/*      */         case GREEN:
/* 1431 */           tmpImg = ImageMaster.CARD_GREEN_ORB_L;
/*      */           break;
/*      */         case BLUE:
/* 1434 */           tmpImg = ImageMaster.CARD_BLUE_ORB_L;
/*      */           break;
/*      */         case PURPLE:
/* 1437 */           tmpImg = ImageMaster.CARD_PURPLE_ORB_L;
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/* 1442 */           tmpImg = ImageMaster.CARD_GRAY_ORB_L;
/*      */           break;
/*      */       } 
/* 1445 */       if (tmpImg != null) {
/* 1446 */         renderHelper(sb, Settings.WIDTH / 2.0F - 270.0F * Settings.scale, Settings.HEIGHT / 2.0F + 380.0F * Settings.scale, tmpImg);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1454 */     Color c = null;
/* 1455 */     if (this.card.isCostModified) {
/* 1456 */       c = Settings.GREEN_TEXT_COLOR;
/*      */     } else {
/* 1458 */       c = Settings.CREAM_COLOR;
/*      */     } 
/* 1460 */     switch (this.card.cost) {
/*      */       case -2:
/*      */         return;
/*      */       case -1:
/* 1464 */         FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, "X", Settings.WIDTH / 2.0F - 292.0F * Settings.scale, Settings.HEIGHT / 2.0F + 404.0F * Settings.scale, c);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/* 1473 */         FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, 
/*      */ 
/*      */             
/* 1476 */             Integer.toString(this.card.cost), Settings.WIDTH / 2.0F - 284.0F * Settings.scale, Settings.HEIGHT / 2.0F + 404.0F * Settings.scale, c);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1482 */     FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, 
/*      */ 
/*      */         
/* 1485 */         Integer.toString(this.card.cost), Settings.WIDTH / 2.0F - 292.0F * Settings.scale, Settings.HEIGHT / 2.0F + 404.0F * Settings.scale, c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderHelper(SpriteBatch sb, float x, float y, TextureAtlas.AtlasRegion img) {
/* 1494 */     if (img == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1498 */     sb.draw((TextureRegion)img, x + img.offsetX - img.originalWidth / 2.0F, y + img.offsetY - img.originalHeight / 2.0F, img.originalWidth / 2.0F - img.offsetX, img.originalHeight / 2.0F - img.offsetY, img.packedWidth, img.packedHeight, Settings.scale, Settings.scale, 0.0F);
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
/*      */   private void renderArrows(SpriteBatch sb) {
/* 1517 */     if (this.prevCard != null) {
/* 1518 */       sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0F, this.prevHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
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
/* 1536 */       if (Settings.isControllerMode) {
/* 1537 */         sb.draw(CInputActionSet.pageLeftViewDeck
/* 1538 */             .getKeyImg(), this.prevHb.cX - 32.0F + 0.0F * Settings.scale, this.prevHb.cY - 32.0F + 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */       }
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
/* 1556 */       if (this.prevHb.hovered) {
/* 1557 */         sb.setBlendFunction(770, 1);
/* 1558 */         sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
/* 1559 */         sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0F, this.prevHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
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
/* 1576 */         sb.setColor(Color.WHITE);
/* 1577 */         sb.setBlendFunction(770, 771);
/*      */       } 
/*      */     } 
/*      */     
/* 1581 */     if (this.nextCard != null) {
/* 1582 */       sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0F, this.nextHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, true, false);
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
/* 1600 */       if (Settings.isControllerMode) {
/* 1601 */         sb.draw(CInputActionSet.pageRightViewExhaust
/* 1602 */             .getKeyImg(), this.nextHb.cX - 32.0F + 0.0F * Settings.scale, this.nextHb.cY - 32.0F + 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */       }
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
/* 1620 */       if (this.nextHb.hovered) {
/* 1621 */         sb.setBlendFunction(770, 1);
/* 1622 */         sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.5F));
/* 1623 */         sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0F, this.nextHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, true, false);
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
/* 1640 */         sb.setColor(Color.WHITE);
/* 1641 */         sb.setBlendFunction(770, 771);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderBetaArtToggle(SpriteBatch sb) {
/* 1647 */     if (this.betaArtHb == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1651 */     sb.setColor(Color.WHITE);
/* 1652 */     sb.draw(ImageMaster.CHECKBOX, this.betaArtHb.cX - 80.0F * Settings.scale - 32.0F, this.betaArtHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 1670 */     if (this.betaArtHb.hovered) {
/* 1671 */       FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[14], this.betaArtHb.cX - 45.0F * Settings.scale, this.betaArtHb.cY + 10.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 1679 */       FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[14], this.betaArtHb.cX - 45.0F * Settings.scale, this.betaArtHb.cY + 10.0F * Settings.scale, Settings.GOLD_COLOR);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1688 */     if (this.viewBetaArt) {
/* 1689 */       sb.setColor(Color.WHITE);
/* 1690 */       sb.draw(ImageMaster.TICK, this.betaArtHb.cX - 80.0F * Settings.scale - 32.0F, this.betaArtHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 1708 */     this.betaArtHb.render(sb);
/*      */   }
/*      */   
/*      */   private void renderUpgradeViewToggle(SpriteBatch sb) {
/* 1712 */     sb.setColor(Color.WHITE);
/* 1713 */     sb.draw(ImageMaster.CHECKBOX, this.upgradeHb.cX - 80.0F * Settings.scale - 32.0F, this.upgradeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 1731 */     if (this.upgradeHb.hovered) {
/* 1732 */       FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[6], this.upgradeHb.cX - 45.0F * Settings.scale, this.upgradeHb.cY + 10.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */       
/* 1740 */       FontHelper.renderFont(sb, FontHelper.cardTitleFont, TEXT[6], this.upgradeHb.cX - 45.0F * Settings.scale, this.upgradeHb.cY + 10.0F * Settings.scale, Settings.GOLD_COLOR);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1749 */     if (isViewingUpgrade) {
/* 1750 */       sb.setColor(Color.WHITE);
/* 1751 */       sb.draw(ImageMaster.TICK, this.upgradeHb.cX - 80.0F * Settings.scale - 32.0F, this.upgradeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 1769 */     this.upgradeHb.render(sb);
/*      */   }
/*      */   
/*      */   private void renderTips(SpriteBatch sb) {
/* 1773 */     ArrayList<PowerTip> t = new ArrayList<>();
/* 1774 */     if (this.card.isLocked) {
/* 1775 */       t.add(new PowerTip(TEXT[4], (String)GameDictionary.keywords.get(TEXT[4].toLowerCase())));
/* 1776 */     } else if (!this.card.isSeen) {
/* 1777 */       t.add(new PowerTip(TEXT[5], (String)GameDictionary.keywords.get(TEXT[5].toLowerCase())));
/*      */     } else {
/* 1779 */       for (String s : this.card.keywords) {
/* 1780 */         if (!s.equals("[R]") && !s.equals("[G]") && !s.equals("[B]") && !s.equals("[W]")) {
/* 1781 */           t.add(new PowerTip(TipHelper.capitalize(s), (String)GameDictionary.keywords.get(s)));
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1786 */     if (!t.isEmpty()) {
/* 1787 */       TipHelper.queuePowerTips(Settings.WIDTH / 2.0F + 340.0F * Settings.scale, 420.0F * Settings.scale, t);
/*      */     }
/*      */     
/* 1790 */     if (this.card.cardsToPreview != null)
/* 1791 */       this.card.renderCardPreviewInSingleView(sb); 
/*      */   }
/*      */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\SingleCardViewPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */