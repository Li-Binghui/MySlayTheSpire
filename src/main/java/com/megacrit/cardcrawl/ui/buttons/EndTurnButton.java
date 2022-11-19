/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.ShaderHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.TutorialStrings;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.EndTurnGlowEffect;
/*     */ import com.megacrit.cardcrawl.vfx.EndTurnLongPressBarFlashEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ public class EndTurnButton
/*     */ {
/*  33 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("End Turn Tip");
/*  34 */   public static final String[] MSG = tutorialStrings.TEXT;
/*  35 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*  36 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("End Turn Button");
/*  37 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */ 
/*     */   
/*  40 */   private String label = TEXT[0];
/*  41 */   public static final String END_TURN_MSG = TEXT[0];
/*  42 */   public static final String ENEMY_TURN_MSG = TEXT[1];
/*  43 */   private static final Color DISABLED_COLOR = new Color(0.7F, 0.7F, 0.7F, 1.0F);
/*  44 */   private static final float SHOW_X = 1640.0F * Settings.xScale, SHOW_Y = 210.0F * Settings.yScale;
/*  45 */   private static final float HIDE_X = SHOW_X + 500.0F * Settings.xScale;
/*  46 */   private float current_x = HIDE_X, current_y = SHOW_Y;
/*  47 */   private float target_x = this.current_x;
/*     */   
/*     */   private boolean isHidden = true;
/*     */   
/*     */   public boolean enabled = false;
/*     */   private boolean isDisabled = false;
/*     */   private Color textColor;
/*  54 */   private ArrayList<EndTurnGlowEffect> glowList = new ArrayList<>();
/*     */   private static final float GLOW_INTERVAL = 1.2F;
/*  56 */   private float glowTimer = 0.0F;
/*     */   
/*     */   public boolean isGlowing = false;
/*     */   public boolean isWarning = false;
/*  60 */   private Hitbox hb = new Hitbox(0.0F, 0.0F, 230.0F * Settings.scale, 110.0F * Settings.scale);
/*     */ 
/*     */   
/*  63 */   private float holdProgress = 0.0F;
/*     */   private static final float HOLD_DUR = 0.4F;
/*  65 */   private Color holdBarColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   
/*     */   public void update() {
/*  68 */     glow();
/*  69 */     updateHoldProgress();
/*     */     
/*  71 */     if (this.current_x != this.target_x) {
/*  72 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/*  73 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/*  74 */         this.current_x = this.target_x;
/*     */       }
/*     */     } 
/*     */     
/*  78 */     this.hb.move(this.current_x, this.current_y);
/*     */     
/*  80 */     if (this.enabled) {
/*  81 */       this.isDisabled = false;
/*  82 */       if (AbstractDungeon.isScreenUp || AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode)
/*     */       {
/*  84 */         this.isDisabled = true;
/*     */       }
/*     */       
/*  87 */       if (AbstractDungeon.player.hoveredCard == null) {
/*  88 */         this.hb.update();
/*     */       }
/*     */       
/*  91 */       if (!Settings.USE_LONG_PRESS && InputHelper.justClickedLeft && this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {
/*     */         
/*  93 */         this.hb.clickStarted = true;
/*  94 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */       } 
/*     */       
/*  97 */       if (this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {
/*  98 */         this.isWarning = showWarning();
/*  99 */         if (this.hb.justHovered && AbstractDungeon.player.hoveredCard == null) {
/* 100 */           CardCrawlGame.sound.play("UI_HOVER");
/* 101 */           for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 102 */             if (c.isGlowing) {
/* 103 */               c.superFlash(c.glowColor);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     if (this.holdProgress == 0.4F && !this.isDisabled && !AbstractDungeon.isScreenUp) {
/* 111 */       disable(true);
/* 112 */       this.holdProgress = 0.0F;
/* 113 */       AbstractDungeon.effectsQueue.add(new EndTurnLongPressBarFlashEffect());
/*     */     } 
/*     */ 
/*     */     
/* 117 */     if ((!Settings.USE_LONG_PRESS || (!Settings.isControllerMode && !InputActionSet.endTurn.isPressed())) && (this.hb.clicked || ((InputActionSet.endTurn
/* 118 */       .isJustPressed() || CInputActionSet.proceed.isJustPressed()) && !this.isDisabled && this.enabled))) {
/*     */       
/* 120 */       this.hb.clicked = false;
/*     */       
/* 122 */       if (!this.isDisabled && !AbstractDungeon.isScreenUp) {
/* 123 */         disable(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateHoldProgress() {
/* 129 */     if (!Settings.USE_LONG_PRESS || (!Settings.isControllerMode && !InputActionSet.endTurn.isPressed() && !InputHelper.isMouseDown)) {
/*     */       
/* 131 */       this.holdProgress -= Gdx.graphics.getDeltaTime();
/* 132 */       if (this.holdProgress < 0.0F) {
/* 133 */         this.holdProgress = 0.0F;
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 138 */     if (((this.hb.hovered && InputHelper.isMouseDown) || CInputActionSet.proceed.isPressed() || InputActionSet.endTurn
/* 139 */       .isPressed()) && !this.isDisabled && this.enabled) {
/* 140 */       this.holdProgress += Gdx.graphics.getDeltaTime();
/* 141 */       if (this.holdProgress > 0.4F) {
/* 142 */         this.holdProgress = 0.4F;
/*     */       }
/*     */     } else {
/* 145 */       this.holdProgress -= Gdx.graphics.getDeltaTime();
/* 146 */       if (this.holdProgress < 0.0F) {
/* 147 */         this.holdProgress = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean showWarning() {
/* 153 */     for (AbstractCard card : AbstractDungeon.player.hand.group) {
/* 154 */       if (card.isGlowing) {
/* 155 */         return true;
/*     */       }
/*     */     } 
/* 158 */     return false;
/*     */   }
/*     */   
/*     */   public void enable() {
/* 162 */     this.enabled = true;
/* 163 */     updateText(END_TURN_MSG);
/*     */   }
/*     */   
/*     */   public void disable(boolean isEnemyTurn) {
/* 167 */     InputHelper.moveCursorToNeutralPosition();
/* 168 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new NewQueueCardAction());
/*     */     
/* 170 */     this.enabled = false;
/* 171 */     this.hb.hovered = false;
/* 172 */     this.isGlowing = false;
/*     */     
/* 174 */     if (isEnemyTurn) {
/* 175 */       updateText(ENEMY_TURN_MSG);
/* 176 */       CardCrawlGame.sound.play("END_TURN");
/* 177 */       AbstractDungeon.player.endTurnQueued = true;
/* 178 */       AbstractDungeon.player.releaseCard();
/*     */     } else {
/* 180 */       updateText(END_TURN_MSG);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void disable() {
/* 185 */     this.enabled = false;
/* 186 */     this.hb.hovered = false;
/* 187 */     this.isGlowing = false;
/*     */   }
/*     */   
/*     */   public void updateText(String msg) {
/* 191 */     this.label = msg;
/*     */   }
/*     */   
/*     */   private void glow() {
/* 195 */     if (this.isGlowing && !this.isHidden) {
/* 196 */       if (this.glowTimer < 0.0F) {
/* 197 */         this.glowList.add(new EndTurnGlowEffect());
/* 198 */         this.glowTimer = 1.2F;
/*     */       } else {
/* 200 */         this.glowTimer -= Gdx.graphics.getDeltaTime();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 205 */     for (Iterator<EndTurnGlowEffect> i = this.glowList.iterator(); i.hasNext(); ) {
/* 206 */       AbstractGameEffect e = (AbstractGameEffect)i.next();
/* 207 */       e.update();
/* 208 */       if (e.isDone) {
/* 209 */         i.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hide() {
/* 215 */     if (!this.isHidden) {
/* 216 */       this.target_x = HIDE_X;
/* 217 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show() {
/* 222 */     if (this.isHidden) {
/* 223 */       this.target_x = SHOW_X;
/* 224 */       this.isHidden = false;
/* 225 */       if (this.isGlowing) {
/* 226 */         this.glowTimer = -1.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 232 */     if (!Settings.hideEndTurn) {
/* 233 */       Texture buttonImg; float tmpY = this.current_y;
/* 234 */       renderHoldEndTurn(sb);
/*     */ 
/*     */       
/* 237 */       if (this.isDisabled || !this.enabled) {
/* 238 */         if (this.label.equals(ENEMY_TURN_MSG)) {
/* 239 */           this.textColor = Settings.CREAM_COLOR;
/*     */         } else {
/* 241 */           this.textColor = Color.LIGHT_GRAY;
/*     */         } 
/*     */       } else {
/* 244 */         if (this.hb.hovered) {
/* 245 */           if (this.isWarning) {
/* 246 */             this.textColor = Settings.RED_TEXT_COLOR;
/*     */           } else {
/* 248 */             this.textColor = Color.CYAN;
/*     */           }
/*     */         
/* 251 */         } else if (this.isGlowing) {
/* 252 */           this.textColor = Settings.GOLD_COLOR;
/*     */         } else {
/* 254 */           this.textColor = Settings.CREAM_COLOR;
/*     */         } 
/*     */ 
/*     */         
/* 258 */         if (this.hb.hovered && !AbstractDungeon.isScreenUp && !Settings.isTouchScreen) {
/* 259 */           TipHelper.renderGenericTip(this.current_x - 90.0F * Settings.scale, this.current_y + 300.0F * Settings.scale, LABEL[0] + " (" + InputActionSet.endTurn
/*     */ 
/*     */               
/* 262 */               .getKeyString() + ")", MSG[0] + AbstractDungeon.player.gameHandSize + MSG[1]);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 267 */       if (this.hb.clickStarted && !AbstractDungeon.isScreenUp) {
/* 268 */         tmpY -= 2.0F * Settings.scale;
/* 269 */       } else if (this.hb.hovered && !AbstractDungeon.isScreenUp) {
/* 270 */         tmpY += 2.0F * Settings.scale;
/*     */       } 
/*     */       
/* 273 */       if (this.enabled) {
/* 274 */         if (this.isDisabled || (this.hb.clickStarted && this.hb.hovered)) {
/* 275 */           sb.setColor(DISABLED_COLOR);
/*     */         } else {
/* 277 */           sb.setColor(Color.WHITE);
/*     */         } 
/*     */       } else {
/* 280 */         ShaderHelper.setShader(sb, ShaderHelper.Shader.GRAYSCALE);
/*     */       } 
/*     */ 
/*     */       
/* 284 */       if (this.isGlowing && !this.hb.clickStarted) {
/* 285 */         buttonImg = ImageMaster.END_TURN_BUTTON_GLOW;
/*     */       } else {
/* 287 */         buttonImg = ImageMaster.END_TURN_BUTTON;
/*     */       } 
/* 289 */       if (this.hb.hovered && !this.isDisabled && !AbstractDungeon.isScreenUp) {
/* 290 */         sb.draw(ImageMaster.END_TURN_HOVER, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
/*     */       }
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
/* 309 */       sb.draw(buttonImg, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
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
/* 327 */       if (!this.enabled) {
/* 328 */         ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
/*     */       }
/*     */       
/* 331 */       renderGlowEffect(sb, this.current_x, this.current_y);
/*     */       
/* 333 */       if ((this.hb.hovered || this.holdProgress > 0.0F) && !this.isDisabled && !AbstractDungeon.isScreenUp) {
/* 334 */         sb.setBlendFunction(770, 1);
/* 335 */         sb.setColor(Settings.HALF_TRANSPARENT_WHITE_COLOR);
/* 336 */         sb.draw(buttonImg, this.current_x - 128.0F, tmpY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 256, 256, false, false);
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
/* 353 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */       
/* 356 */       if (Settings.isControllerMode && this.enabled) {
/* 357 */         sb.setColor(Color.WHITE);
/* 358 */         sb.draw(CInputActionSet.proceed
/* 359 */             .getKeyImg(), this.current_x - 32.0F - 42.0F * Settings.scale - 
/* 360 */             FontHelper.getSmartWidth(FontHelper.panelEndTurnFont, this.label, 99999.0F, 0.0F) / 2.0F, tmpY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */       } 
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
/* 381 */       FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, this.label, this.current_x - 0.0F * Settings.scale, tmpY - 3.0F * Settings.scale, this.textColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 389 */       if (!this.isHidden) {
/* 390 */         this.hb.render(sb);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderHoldEndTurn(SpriteBatch sb) {
/* 396 */     if (!Settings.USE_LONG_PRESS) {
/*     */       return;
/*     */     }
/*     */     
/* 400 */     this.holdBarColor.r = 0.0F;
/* 401 */     this.holdBarColor.g = 0.0F;
/* 402 */     this.holdBarColor.b = 0.0F;
/* 403 */     this.holdBarColor.a = this.holdProgress * 1.5F;
/* 404 */     sb.setColor(this.holdBarColor);
/* 405 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.current_x - 107.0F * Settings.scale, this.current_y + 53.0F * Settings.scale - 7.0F * Settings.scale, 525.0F * Settings.scale * this.holdProgress + 14.0F * Settings.scale, 20.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 412 */     this.holdBarColor.r = this.holdProgress * 2.5F;
/* 413 */     this.holdBarColor.g = 0.6F + this.holdProgress;
/* 414 */     this.holdBarColor.b = 0.6F;
/* 415 */     this.holdBarColor.a = 1.0F;
/* 416 */     sb.setColor(this.holdBarColor);
/* 417 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.current_x - 100.0F * Settings.scale, this.current_y + 53.0F * Settings.scale, 525.0F * Settings.scale * this.holdProgress, 6.0F * Settings.scale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderGlowEffect(SpriteBatch sb, float x, float y) {
/* 426 */     for (EndTurnGlowEffect e : this.glowList)
/* 427 */       e.render(sb, x, y); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\EndTurnButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */