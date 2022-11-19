/*     */ package com.megacrit.cardcrawl.screens.options;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.InputProcessor;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.HitboxListener;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputAction;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputListener;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputAction;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class RemapInputElement
/*     */   implements HitboxListener, InputProcessor
/*     */ {
/*  26 */   private static final Logger logger = LogManager.getLogger(RemapInputElement.class.getName());
/*     */   
/*     */   public Hitbox hb;
/*     */   
/*     */   public InputAction action;
/*     */   public CInputAction cAction;
/*     */   public boolean hasInputFocus = false;
/*     */   public boolean isHidden = false;
/*     */   public boolean isHeader = false;
/*     */   private String labelText;
/*     */   public RemapInputElementListener listener;
/*  37 */   public static final float ROW_HEIGHT = 58.0F * Settings.scale;
/*  38 */   public static final float ROW_WIDTH = 1048.0F * Settings.scale;
/*     */   
/*  40 */   public static final float ROW_RENDER_HEIGHT = 64.0F * Settings.scale;
/*  41 */   private static final float ROW_TEXT_Y_OFFSET = 12.0F * Settings.scale;
/*  42 */   private static final float ROW_TEXT_LEADING_OFFSET = 40.0F * Settings.scale;
/*     */   
/*  44 */   public static final float KEYBOARD_COLUMN_X_OFFSET = Settings.scale * 400.0F;
/*  45 */   public static final float CONTROLLER_COLUMN_X_OFFSET = Settings.scale * 250.0F;
/*     */   
/*  47 */   private static final Color ROW_BG_COLOR = new Color(588124159);
/*  48 */   private static final Color ROW_HOVER_COLOR = new Color(-193);
/*  49 */   private static final Color ROW_SELECT_COLOR = new Color(-1924910337);
/*     */   
/*  51 */   private static final Color TEXT_DEFAULT_COLOR = Settings.CREAM_COLOR;
/*  52 */   private static final Color TEXT_FOCUSED_COLOR = Settings.GREEN_TEXT_COLOR;
/*  53 */   private static final Color TEXT_HOVERED_COLOR = Settings.GOLD_COLOR;
/*     */   
/*     */   public RemapInputElement(RemapInputElementListener listener, String label, InputAction action) {
/*  56 */     this(listener, label, action, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RemapInputElement(RemapInputElementListener listener, String label, InputAction action, CInputAction controllerAction) {
/*  64 */     this.hb = new Hitbox(ROW_WIDTH, ROW_HEIGHT);
/*  65 */     this.labelText = label;
/*  66 */     this.action = action;
/*  67 */     this.cAction = controllerAction;
/*  68 */     this.listener = listener;
/*     */   }
/*     */   
/*     */   public void move(float x, float y) {
/*  72 */     this.hb.move(x, y);
/*     */   }
/*     */   
/*     */   public void update() {
/*  76 */     if (this.isHidden) {
/*     */       return;
/*     */     }
/*  79 */     this.hb.encapsulatedUpdate(this);
/*     */     
/*  81 */     if (this.hasInputFocus && (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed())) {
/*  82 */       CInputActionSet.select.unpress();
/*  83 */       logger.info("Lose focus");
/*  84 */       this.hasInputFocus = false;
/*  85 */       InputHelper.regainInputFocus();
/*  86 */       CInputHelper.regainInputFocus();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  91 */     if (this.isHidden) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  96 */     sb.setBlendFunction(770, 1);
/*  97 */     sb.setColor(getRowBgColor());
/*  98 */     sb.draw(ImageMaster.INPUT_SETTINGS_ROW, this.hb.x, this.hb.y, ROW_WIDTH, ROW_RENDER_HEIGHT);
/*  99 */     sb.setBlendFunction(770, 771);
/* 100 */     sb.setColor(Color.WHITE);
/*     */ 
/*     */     
/* 103 */     Color textColor = getTextColor();
/* 104 */     float textY = this.hb.cY + ROW_TEXT_Y_OFFSET;
/* 105 */     float textX = this.hb.x + ROW_TEXT_LEADING_OFFSET;
/* 106 */     FontHelper.renderFont(sb, FontHelper.topPanelInfoFont, this.labelText, textX, textY, textColor);
/* 107 */     textX += KEYBOARD_COLUMN_X_OFFSET;
/* 108 */     FontHelper.renderFont(sb, FontHelper.topPanelInfoFont, getKeyColumnText(), textX, textY, textColor);
/* 109 */     textX += CONTROLLER_COLUMN_X_OFFSET;
/* 110 */     if (!this.isHeader) {
/* 111 */       Texture img = getControllerColumnImg();
/* 112 */       if (img != null) {
/* 113 */         sb.draw(img, textX - 32.0F, textY - 32.0F - 10.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 64, 64, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
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
/* 132 */       FontHelper.renderFont(sb, FontHelper.topPanelInfoFont, getControllerColumnText(), textX, textY, textColor);
/*     */     } 
/* 134 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   protected Color getRowBgColor() {
/* 138 */     Color bgColor = ROW_BG_COLOR;
/* 139 */     if (this.hasInputFocus) {
/* 140 */       bgColor = ROW_SELECT_COLOR;
/* 141 */     } else if (this.hb.hovered) {
/* 142 */       bgColor = ROW_HOVER_COLOR;
/*     */     } 
/* 144 */     return bgColor;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Color getTextColor() {
/* 149 */     Color color = TEXT_DEFAULT_COLOR;
/* 150 */     if (this.hasInputFocus) {
/* 151 */       color = TEXT_FOCUSED_COLOR;
/* 152 */     } else if (this.hb.hovered) {
/* 153 */       color = TEXT_HOVERED_COLOR;
/*     */     } 
/* 155 */     return color;
/*     */   }
/*     */   
/*     */   protected String getKeyColumnText() {
/* 159 */     return (this.action != null) ? this.action.getKeyString() : "";
/*     */   }
/*     */   
/*     */   protected String getControllerColumnText() {
/* 163 */     return (this.cAction != null) ? this.cAction.getKeyString() : "";
/*     */   }
/*     */   
/*     */   protected Texture getControllerColumnImg() {
/* 167 */     Texture retVal = null;
/* 168 */     if (this.cAction != null) {
/* 169 */       retVal = this.cAction.getKeyImg();
/*     */     }
/* 171 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void hoverStarted(Hitbox hitbox) {
/* 178 */     CardCrawlGame.sound.play("UI_HOVER");
/*     */   }
/*     */ 
/*     */   
/*     */   public void startClicking(Hitbox hitbox) {
/* 183 */     CardCrawlGame.sound.play("UI_CLICK_1");
/*     */   }
/*     */ 
/*     */   
/*     */   public void clicked(Hitbox hitbox) {
/* 188 */     logger.info("BEGIN REMAPPING...");
/* 189 */     this.listener.didStartRemapping(this);
/* 190 */     Gdx.input.setInputProcessor(this);
/* 191 */     CInputListener.listenForRemap(this);
/* 192 */     this.hasInputFocus = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean keyDown(int keycode) {
/* 198 */     if (this.action != null && this.listener.willRemap(this, this.action.getKey(), keycode)) {
/* 199 */       this.action.remap(keycode);
/*     */     }
/* 201 */     this.hasInputFocus = false;
/* 202 */     InputHelper.regainInputFocus();
/* 203 */     CInputHelper.regainInputFocus();
/* 204 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean keyUp(int keycode) {
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean keyTyped(char character) {
/* 216 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean touchDragged(int screenX, int screenY, int pointer) {
/* 231 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseMoved(int screenX, int screenY) {
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean scrolled(int amount) {
/* 242 */     if (amount == -1) {
/* 243 */       InputHelper.scrolledUp = true;
/* 244 */     } else if (amount == 1) {
/* 245 */       InputHelper.scrolledDown = true;
/*     */     } 
/* 247 */     return false;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\RemapInputElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */