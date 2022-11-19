/*     */ package com.megacrit.cardcrawl.ui.panels;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.GameDeckGlowEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import java.util.ArrayList;
import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DrawPilePanel
/*     */   extends AbstractPanel
/*     */ {
/*  35 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Draw Tip");
/*  36 */   public static final String[] MSG = tutorialStrings.TEXT;
/*  37 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*  38 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DrawPilePanel");
/*  39 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final int RAW_W = 128;
/*  42 */   private float scale = 1.0F;
/*  43 */   private static final float COUNT_CIRCLE_W = 128.0F * Settings.scale;
/*  44 */   private static final float DECK_X = 76.0F * Settings.scale - 64.0F;
/*  45 */   private static final float DECK_Y = 74.0F * Settings.scale - 64.0F;
/*  46 */   private static final float COUNT_X = 118.0F * Settings.scale;
/*  47 */   private static final float COUNT_Y = 48.0F * Settings.scale;
/*  48 */   private static final float COUNT_OFFSET_X = 54.0F * Settings.scale;
/*  49 */   private static final float COUNT_OFFSET_Y = -18.0F * Settings.scale;
/*  50 */   private Color glowColor = Color.WHITE.cpy();
/*  51 */   private float glowAlpha = 0.0F;
/*  52 */   private GlyphLayout gl = new GlyphLayout();
/*  53 */   private static final float DECK_TIP_X = 50.0F * Settings.scale;
/*  54 */   private static final float DECK_TIP_Y = 470.0F * Settings.scale;
/*  55 */   private BobEffect bob = new BobEffect(1.0F);
/*  56 */   private ArrayList<GameDeckGlowEffect> vfxBelow = new ArrayList<>();
/*     */   
/*  58 */   private static final float HITBOX_W = 120.0F * Settings.scale;
/*  59 */   private static final float HITBOX_W2 = 450.0F * Settings.scale;
/*     */   
/*  61 */   private Hitbox hb = new Hitbox(0.0F, 0.0F, HITBOX_W, HITBOX_W);
/*  62 */   private Hitbox bannerHb = new Hitbox(0.0F, 0.0F, HITBOX_W2, HITBOX_W);
/*     */   
/*     */   public DrawPilePanel() {
/*  65 */     super(0.0F, 0.0F, -300.0F * Settings.scale, -300.0F * Settings.scale, null, true);
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
/*     */   public void updatePositions() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial updatePositions : ()V
/*     */     //   4: aload_0
/*     */     //   5: getfield bob : Lcom/megacrit/cardcrawl/vfx/BobEffect;
/*     */     //   8: invokevirtual update : ()V
/*     */     //   11: aload_0
/*     */     //   12: invokespecial updateVfx : ()V
/*     */     //   15: aload_0
/*     */     //   16: getfield isHidden : Z
/*     */     //   19: ifne -> 40
/*     */     //   22: aload_0
/*     */     //   23: getfield hb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   26: invokevirtual update : ()V
/*     */     //   29: aload_0
/*     */     //   30: getfield bannerHb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   33: invokevirtual update : ()V
/*     */     //   36: aload_0
/*     */     //   37: invokespecial updatePop : ()V
/*     */     //   40: aload_0
/*     */     //   41: getfield hb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   44: getfield hovered : Z
/*     */     //   47: ifeq -> 116
/*     */     //   50: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.isScreenUp : Z
/*     */     //   53: ifeq -> 95
/*     */     //   56: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.screen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   59: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen.GAME_DECK_VIEW : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   62: if_acmpeq -> 95
/*     */     //   65: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.screen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   68: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen.HAND_SELECT : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   71: if_acmpeq -> 95
/*     */     //   74: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.screen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   77: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen.CARD_REWARD : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   80: if_acmpne -> 116
/*     */     //   83: invokestatic getCurrRoom : ()Lcom/megacrit/cardcrawl/rooms/AbstractRoom;
/*     */     //   86: getfield phase : Lcom/megacrit/cardcrawl/rooms/AbstractRoom$RoomPhase;
/*     */     //   89: getstatic com/megacrit/cardcrawl/rooms/AbstractRoom$RoomPhase.COMBAT : Lcom/megacrit/cardcrawl/rooms/AbstractRoom$RoomPhase;
/*     */     //   92: if_acmpne -> 116
/*     */     //   95: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.overlayMenu : Lcom/megacrit/cardcrawl/core/OverlayMenu;
/*     */     //   98: iconst_1
/*     */     //   99: putfield hoveredTip : Z
/*     */     //   102: getstatic com/megacrit/cardcrawl/helpers/input/InputHelper.justClickedLeft : Z
/*     */     //   105: ifeq -> 116
/*     */     //   108: aload_0
/*     */     //   109: getfield hb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   112: iconst_1
/*     */     //   113: putfield clickStarted : Z
/*     */     //   116: aload_0
/*     */     //   117: getfield hb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   120: getfield clicked : Z
/*     */     //   123: ifne -> 144
/*     */     //   126: getstatic com/megacrit/cardcrawl/helpers/input/InputActionSet.drawPile : Lcom/megacrit/cardcrawl/helpers/input/InputAction;
/*     */     //   129: invokevirtual isJustPressed : ()Z
/*     */     //   132: ifne -> 144
/*     */     //   135: getstatic com/megacrit/cardcrawl/helpers/controller/CInputActionSet.drawPile : Lcom/megacrit/cardcrawl/helpers/controller/CInputAction;
/*     */     //   138: invokevirtual isJustPressed : ()Z
/*     */     //   141: ifeq -> 203
/*     */     //   144: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.screen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   147: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen.GAME_DECK_VIEW : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   150: if_acmpne -> 203
/*     */     //   153: aload_0
/*     */     //   154: getfield hb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   157: iconst_0
/*     */     //   158: putfield clicked : Z
/*     */     //   161: aload_0
/*     */     //   162: getfield hb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   165: iconst_0
/*     */     //   166: putfield hovered : Z
/*     */     //   169: aload_0
/*     */     //   170: getfield bannerHb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   173: iconst_0
/*     */     //   174: putfield hovered : Z
/*     */     //   177: getstatic com/megacrit/cardcrawl/core/CardCrawlGame.sound : Lcom/megacrit/cardcrawl/audio/SoundMaster;
/*     */     //   180: ldc 'DECK_CLOSE'
/*     */     //   182: invokevirtual play : (Ljava/lang/String;)J
/*     */     //   185: pop2
/*     */     //   186: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.previousScreen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   189: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen.GAME_DECK_VIEW : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   192: if_acmpne -> 199
/*     */     //   195: aconst_null
/*     */     //   196: putstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.previousScreen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   199: invokestatic closeCurrentScreen : ()V
/*     */     //   202: return
/*     */     //   203: aload_0
/*     */     //   204: dup
/*     */     //   205: getfield glowAlpha : F
/*     */     //   208: getstatic com/badlogic/gdx/Gdx.graphics : Lcom/badlogic/gdx/Graphics;
/*     */     //   211: invokeinterface getDeltaTime : ()F
/*     */     //   216: ldc 3.0
/*     */     //   218: fmul
/*     */     //   219: fadd
/*     */     //   220: putfield glowAlpha : F
/*     */     //   223: aload_0
/*     */     //   224: getfield glowAlpha : F
/*     */     //   227: fconst_0
/*     */     //   228: fcmpg
/*     */     //   229: ifge -> 243
/*     */     //   232: aload_0
/*     */     //   233: dup
/*     */     //   234: getfield glowAlpha : F
/*     */     //   237: ldc -1.0
/*     */     //   239: fmul
/*     */     //   240: putfield glowAlpha : F
/*     */     //   243: aload_0
/*     */     //   244: getfield glowAlpha : F
/*     */     //   247: invokestatic cos : (F)F
/*     */     //   250: fstore_1
/*     */     //   251: fload_1
/*     */     //   252: fconst_0
/*     */     //   253: fcmpg
/*     */     //   254: ifge -> 271
/*     */     //   257: aload_0
/*     */     //   258: getfield glowColor : Lcom/badlogic/gdx/graphics/Color;
/*     */     //   261: fload_1
/*     */     //   262: fneg
/*     */     //   263: fconst_2
/*     */     //   264: fdiv
/*     */     //   265: putfield a : F
/*     */     //   268: goto -> 281
/*     */     //   271: aload_0
/*     */     //   272: getfield glowColor : Lcom/badlogic/gdx/graphics/Color;
/*     */     //   275: fload_1
/*     */     //   276: fconst_2
/*     */     //   277: fdiv
/*     */     //   278: putfield a : F
/*     */     //   281: aload_0
/*     */     //   282: getfield hb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   285: getfield clicked : Z
/*     */     //   288: ifne -> 309
/*     */     //   291: getstatic com/megacrit/cardcrawl/helpers/input/InputActionSet.drawPile : Lcom/megacrit/cardcrawl/helpers/input/InputAction;
/*     */     //   294: invokevirtual isJustPressed : ()Z
/*     */     //   297: ifne -> 309
/*     */     //   300: getstatic com/megacrit/cardcrawl/helpers/controller/CInputActionSet.drawPile : Lcom/megacrit/cardcrawl/helpers/controller/CInputAction;
/*     */     //   303: invokevirtual isJustPressed : ()Z
/*     */     //   306: ifeq -> 416
/*     */     //   309: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.overlayMenu : Lcom/megacrit/cardcrawl/core/OverlayMenu;
/*     */     //   312: getfield combatPanelsShown : Z
/*     */     //   315: ifeq -> 416
/*     */     //   318: invokestatic getMonsters : ()Lcom/megacrit/cardcrawl/monsters/MonsterGroup;
/*     */     //   321: ifnull -> 416
/*     */     //   324: invokestatic getMonsters : ()Lcom/megacrit/cardcrawl/monsters/MonsterGroup;
/*     */     //   327: invokevirtual areMonstersDead : ()Z
/*     */     //   330: ifne -> 416
/*     */     //   333: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.player : Lcom/megacrit/cardcrawl/characters/AbstractPlayer;
/*     */     //   336: getfield isDead : Z
/*     */     //   339: ifne -> 416
/*     */     //   342: aload_0
/*     */     //   343: getfield hb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   346: iconst_0
/*     */     //   347: putfield clicked : Z
/*     */     //   350: aload_0
/*     */     //   351: getfield hb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   354: iconst_0
/*     */     //   355: putfield hovered : Z
/*     */     //   358: aload_0
/*     */     //   359: getfield bannerHb : Lcom/megacrit/cardcrawl/helpers/Hitbox;
/*     */     //   362: iconst_0
/*     */     //   363: putfield hovered : Z
/*     */     //   366: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.dynamicBanner : Lcom/megacrit/cardcrawl/ui/buttons/DynamicBanner;
/*     */     //   369: invokevirtual hide : ()V
/*     */     //   372: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.player : Lcom/megacrit/cardcrawl/characters/AbstractPlayer;
/*     */     //   375: getfield hoveredCard : Lcom/megacrit/cardcrawl/cards/AbstractCard;
/*     */     //   378: ifnull -> 387
/*     */     //   381: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.player : Lcom/megacrit/cardcrawl/characters/AbstractPlayer;
/*     */     //   384: invokevirtual releaseCard : ()V
/*     */     //   387: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.isScreenUp : Z
/*     */     //   390: ifeq -> 408
/*     */     //   393: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.previousScreen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   396: ifnonnull -> 412
/*     */     //   399: getstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.screen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   402: putstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.previousScreen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   405: goto -> 412
/*     */     //   408: aconst_null
/*     */     //   409: putstatic com/megacrit/cardcrawl/dungeons/AbstractDungeon.previousScreen : Lcom/megacrit/cardcrawl/dungeons/AbstractDungeon$CurrentScreen;
/*     */     //   412: aload_0
/*     */     //   413: invokespecial openDrawPile : ()V
/*     */     //   416: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #70	-> 0
/*     */     //   #71	-> 4
/*     */     //   #72	-> 11
/*     */     //   #74	-> 15
/*     */     //   #75	-> 22
/*     */     //   #76	-> 29
/*     */     //   #77	-> 36
/*     */     //   #81	-> 40
/*     */     //   #84	-> 83
/*     */     //   #85	-> 95
/*     */     //   #86	-> 102
/*     */     //   #87	-> 108
/*     */     //   #92	-> 116
/*     */     //   #94	-> 153
/*     */     //   #95	-> 161
/*     */     //   #96	-> 169
/*     */     //   #97	-> 177
/*     */     //   #98	-> 186
/*     */     //   #99	-> 195
/*     */     //   #101	-> 199
/*     */     //   #102	-> 202
/*     */     //   #106	-> 203
/*     */     //   #107	-> 223
/*     */     //   #108	-> 232
/*     */     //   #110	-> 243
/*     */     //   #111	-> 251
/*     */     //   #112	-> 257
/*     */     //   #114	-> 271
/*     */     //   #119	-> 281
/*     */     //   #120	-> 318
/*     */     //   #121	-> 324
/*     */     //   #123	-> 342
/*     */     //   #124	-> 350
/*     */     //   #125	-> 358
/*     */     //   #126	-> 366
/*     */     //   #128	-> 372
/*     */     //   #129	-> 381
/*     */     //   #132	-> 387
/*     */     //   #133	-> 393
/*     */     //   #134	-> 399
/*     */     //   #137	-> 408
/*     */     //   #140	-> 412
/*     */     //   #142	-> 416
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   251	30	1	tmp	F
/*     */     //   0	417	0	this	Lcom/megacrit/cardcrawl/ui/panels/DrawPilePanel;
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
/*     */   private void openDrawPile() {
/* 145 */     AbstractPlayer p = AbstractDungeon.player;
/*     */     
/* 147 */     if (!p.drawPile.isEmpty()) {
/* 148 */       AbstractDungeon.gameDeckViewScreen.open();
/*     */     } else {
/* 150 */       AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, TEXT[0], true));
/*     */     } 
/*     */     
/* 153 */     this.hb.hovered = false;
/* 154 */     InputHelper.justClickedLeft = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateVfx() {
/* 161 */     for (Iterator<GameDeckGlowEffect> i = this.vfxBelow.iterator(); i.hasNext(); ) {
/* 162 */       AbstractGameEffect e = (AbstractGameEffect)i.next();
/* 163 */       e.update();
/* 164 */       if (e.isDone) {
/* 165 */         i.remove();
/*     */       }
/*     */     } 
/*     */     
/* 169 */     if (this.vfxBelow.size() < 25 && !Settings.DISABLE_EFFECTS) {
/* 170 */       this.vfxBelow.add(new GameDeckGlowEffect(false));
/*     */     }
/*     */   }
/*     */   
/*     */   private void updatePop() {
/* 175 */     this.scale = MathHelper.scaleLerpSnap(this.scale, Settings.scale);
/*     */   }
/*     */   
/*     */   public void pop() {
/* 179 */     this.scale = 1.75F * Settings.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 184 */     if (this.hb.hovered || (this.bannerHb.hovered && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GAME_DECK_VIEW)) {
/* 185 */       this.scale = 1.2F * Settings.scale;
/*     */     }
/*     */     
/* 188 */     for (GameDeckGlowEffect e : this.vfxBelow) {
/* 189 */       e.render(sb, this.current_x, this.current_y + this.bob.y * 0.5F);
/*     */     }
/*     */ 
/*     */     
/* 193 */     sb.setColor(Color.WHITE);
/* 194 */     sb.draw(ImageMaster.DECK_BTN_BASE, this.current_x + DECK_X, this.current_y + DECK_Y + this.bob.y / 2.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, 0.0F, 0, 0, 128, 128, false, false);
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
/* 213 */     String msg = Integer.toString(AbstractDungeon.player.drawPile.size());
/* 214 */     this.gl.setText(FontHelper.turnNumFont, msg);
/* 215 */     sb.setColor(Color.WHITE);
/* 216 */     sb.draw(ImageMaster.DECK_COUNT_CIRCLE, this.current_x + COUNT_OFFSET_X, this.current_y + COUNT_OFFSET_Y, COUNT_CIRCLE_W, COUNT_CIRCLE_W);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     if (Settings.isControllerMode) {
/* 224 */       sb.draw(CInputActionSet.drawPile
/* 225 */           .getKeyImg(), this.current_x - 32.0F + 30.0F * Settings.scale, this.current_y - 32.0F + 40.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 64, 64, false, false);
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
/* 243 */     FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, msg, this.current_x + COUNT_X, this.current_y + COUNT_Y);
/*     */     
/* 245 */     if (!this.isHidden) {
/* 246 */       this.hb.render(sb);
/* 247 */       if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GAME_DECK_VIEW) {
/* 248 */         this.bannerHb.render(sb);
/*     */       }
/*     */     } 
/*     */     
/* 252 */     if (this.hb.hovered && 
/* 253 */       !AbstractDungeon.isScreenUp && AbstractDungeon.getMonsters() != null && 
/* 254 */       !AbstractDungeon.getMonsters().areMonstersDead())
/*     */     {
/* 256 */       if (Settings.isConsoleBuild) {
/* 257 */         if (!AbstractDungeon.player.hasRelic("Frozen Eye")) {
/* 258 */           TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.drawPile
/*     */ 
/*     */               
/* 261 */               .getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[3]);
/*     */         } else {
/*     */           
/* 264 */           TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.drawPile
/*     */ 
/*     */               
/* 267 */               .getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[4]);
/*     */         }
/*     */       
/*     */       }
/* 271 */       else if (!AbstractDungeon.player.hasRelic("Frozen Eye")) {
/* 272 */         TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.drawPile
/*     */ 
/*     */             
/* 275 */             .getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[1]);
/*     */       } else {
/*     */         
/* 278 */         TipHelper.renderGenericTip(DECK_TIP_X, DECK_TIP_Y, LABEL[0] + " (" + InputActionSet.drawPile
/*     */ 
/*     */             
/* 281 */             .getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[2]);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\DrawPilePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */