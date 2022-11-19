/*     */ package com.megacrit.cardcrawl.helpers.input;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.GameCursor;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InputHelper
/*     */ {
/*  17 */   private static final Logger logger = LogManager.getLogger(InputHelper.class.getName());
/*     */   
/*     */   public static int mX;
/*     */   
/*     */   public static int mY;
/*     */   
/*     */   public static boolean isMouseDown = false;
/*     */   
/*     */   public static boolean isMouseDown_R = false;
/*     */   private static boolean isPrevMouseDown = false;
/*     */   private static boolean isPrevMouseDown_R = false;
/*     */   public static boolean justClickedLeft = false;
/*  29 */   public static int scrollY = 0; public static boolean justClickedRight = false; public static boolean touchDown = false; public static boolean touchUp = false; public static boolean justReleasedClickLeft = false, justReleasedClickRight = false;
/*     */   public static boolean scrolledUp = false, scrolledDown = false;
/*     */   public static boolean pressedEscape = false;
/*     */   private static ScrollInputProcessor processor;
/*     */   private static boolean ignoreOneCycle = false;
/*     */   
/*     */   public static void initialize() {
/*  36 */     processor = new ScrollInputProcessor();
/*  37 */     Gdx.input.setInputProcessor(processor);
/*  38 */     logger.info("Setting input processor to Scroller");
/*  39 */     InputActionSet.load();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void regainInputFocus() {
/*  46 */     Gdx.input.setInputProcessor(processor);
/*  47 */     ignoreOneCycle = true;
/*     */   }
/*     */   
/*     */   public static void updateFirst() {
/*  51 */     if (ignoreOneCycle) {
/*  52 */       ignoreOneCycle = false;
/*     */       
/*     */       return;
/*     */     } 
/*  56 */     if (!Settings.isTouchScreen) {
/*  57 */       mX = Gdx.input.getX();
/*  58 */       if (mX > Settings.WIDTH) {
/*  59 */         mX = Settings.WIDTH;
/*  60 */       } else if (mX < 0) {
/*  61 */         mX = 0;
/*     */       } 
/*  63 */       mY = Settings.HEIGHT - Gdx.input.getY();
/*  64 */       if (mY > Settings.HEIGHT) {
/*  65 */         mY = Settings.HEIGHT;
/*  66 */       } else if (mY < 1) {
/*  67 */         mY = 1;
/*     */       } 
/*     */     } else {
/*  70 */       mX = Gdx.input.getX() + Settings.VERT_LETTERBOX_AMT;
/*  71 */       mY = Settings.HEIGHT - Gdx.input.getY() + Settings.HORIZ_LETTERBOX_AMT;
/*  72 */       if (mY < 1) {
/*  73 */         mY = 1;
/*     */       }
/*     */     } 
/*     */     
/*  77 */     isMouseDown = Gdx.input.isButtonPressed(0);
/*  78 */     isMouseDown_R = Gdx.input.isButtonPressed(1);
/*     */     
/*  80 */     if (Gdx.input.getDeltaX() != 0 && 
/*  81 */       AbstractDungeon.player != null && AbstractDungeon.player.isInKeyboardMode) {
/*  82 */       GameCursor.hidden = false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  87 */     if ((!isPrevMouseDown && isMouseDown) || touchDown) {
/*  88 */       CardCrawlGame.cursor.color.a = 0.7F;
/*  89 */       touchDown = false;
/*  90 */       justClickedLeft = true;
/*     */       
/*  92 */       if (Settings.isControllerMode) {
/*  93 */         leaveControllerMode();
/*     */       }
/*     */       
/*  96 */       if (Settings.isDebug) {
/*  97 */         logger.info("Clicked: (" + mX + "," + mY + ")");
/*     */       }
/*  99 */     } else if ((isPrevMouseDown && !isMouseDown) || touchUp) {
/* 100 */       touchUp = false;
/* 101 */       justReleasedClickLeft = true;
/*     */     } 
/*     */ 
/*     */     
/* 105 */     if (!isPrevMouseDown_R && isMouseDown_R) {
/* 106 */       justClickedRight = true;
/*     */       
/* 108 */       if (Settings.isControllerMode) {
/* 109 */         leaveControllerMode();
/*     */       }
/* 111 */     } else if (isPrevMouseDown_R && !isMouseDown_R) {
/* 112 */       justReleasedClickRight = true;
/*     */     } 
/*     */ 
/*     */     
/* 116 */     pressedEscape = InputActionSet.cancel.isJustPressed();
/*     */     
/* 118 */     isPrevMouseDown_R = isMouseDown_R;
/* 119 */     isPrevMouseDown = isMouseDown;
/*     */   }
/*     */   
/*     */   private static void leaveControllerMode() {
/* 123 */     if (Settings.isConsoleBuild) {
/* 124 */       logger.info("ENTERING TOUCH SCREEN MODE");
/* 125 */       Settings.isTouchScreen = true;
/*     */     } else {
/* 127 */       logger.info("LEAVING CONTROLLER MODE");
/* 128 */       Settings.isTouchScreen = Settings.TOUCHSCREEN_ENABLED;
/*     */     } 
/*     */     
/* 131 */     Settings.isControllerMode = false;
/* 132 */     GameCursor.hidden = false;
/*     */     
/* 134 */     if (AbstractDungeon.player != null && AbstractDungeon.isPlayerInDungeon()) {
/* 135 */       AbstractDungeon.player.viewingRelics = false;
/* 136 */       AbstractDungeon.topPanel.selectPotionMode = false;
/* 137 */       AbstractDungeon.player.releaseCard();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void updateLast() {
/* 142 */     justClickedLeft = false;
/* 143 */     justClickedRight = false;
/* 144 */     justReleasedClickLeft = false;
/* 145 */     justReleasedClickRight = false;
/* 146 */     scrolledUp = false;
/* 147 */     scrolledDown = false;
/*     */   }
/*     */   
/*     */   public static AbstractCard getCardSelectedByHotkey(CardGroup cards) {
/* 151 */     for (int i = 0; i < InputActionSet.selectCardActions.length && i < cards.size(); i++) {
/* 152 */       if (InputActionSet.selectCardActions[i].isJustPressed()) {
/* 153 */         return cards.group.get(i);
/*     */       }
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */   
/* 159 */   public static final int[] SHORTCUT_MODIFIER_KEYS = new int[] { 129, 130, 63 };
/*     */   
/*     */   public static boolean isShortcutModifierKeyPressed() {
/* 162 */     for (int keycode : SHORTCUT_MODIFIER_KEYS) {
/* 163 */       if (Gdx.input.isKeyPressed(keycode)) {
/* 164 */         return true;
/*     */       }
/*     */     } 
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isPasteJustPressed() {
/* 171 */     return (isShortcutModifierKeyPressed() && Gdx.input.isKeyJustPressed(50));
/*     */   }
/*     */   
/*     */   public static boolean didMoveMouse() {
/* 175 */     return (Gdx.input.getDeltaX() != 0 || Gdx.input.getDeltaY() != 0);
/*     */   }
/*     */   
/*     */   public static void moveCursorToNeutralPosition() {
/* 179 */     if (Settings.isTouchScreen) {
/* 180 */       Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
/* 181 */       CardCrawlGame.cursor.color.a = 0.0F;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\input\InputHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */