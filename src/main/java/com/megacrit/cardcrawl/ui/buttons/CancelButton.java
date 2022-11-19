/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.rooms.RestRoom;
/*     */ import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MenuPanelScreen;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CancelButton
/*     */ {
/*  25 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Cancel Button");
/*  26 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final int W = 512;
/*     */   private static final int H = 256;
/*  30 */   private static final Color HOVER_BLEND_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.4F);
/*  31 */   private static final float SHOW_X = 256.0F * Settings.scale; private static final float DRAW_Y = 128.0F * Settings.scale;
/*  32 */   public static final float HIDE_X = SHOW_X - 400.0F * Settings.scale;
/*  33 */   public float current_x = HIDE_X;
/*  34 */   private float target_x = this.current_x;
/*     */   public boolean isHidden = true;
/*  36 */   private float glowAlpha = 0.0F;
/*  37 */   private Color glowColor = Settings.GOLD_COLOR.cpy();
/*     */ 
/*     */   
/*  40 */   public String buttonText = "NOT_SET";
/*  41 */   private static final float TEXT_OFFSET_X = -136.0F * Settings.scale;
/*  42 */   private static final float TEXT_OFFSET_Y = 57.0F * Settings.scale;
/*     */ 
/*     */   
/*  45 */   private static final float HITBOX_W = 300.0F * Settings.scale; private static final float HITBOX_H = 100.0F * Settings.scale;
/*  46 */   public Hitbox hb = new Hitbox(0.0F, 0.0F, HITBOX_W, HITBOX_H);
/*     */   
/*     */   public CancelButton() {
/*  49 */     this.hb.move(SHOW_X - 106.0F * Settings.scale, DRAW_Y + 60.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  53 */     if (!this.isHidden) {
/*  54 */       updateGlow();
/*  55 */       this.hb.update();
/*     */       
/*  57 */       if (InputHelper.justClickedLeft && this.hb.hovered) {
/*  58 */         this.hb.clickStarted = true;
/*  59 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */       } 
/*  61 */       if (this.hb.justHovered) {
/*  62 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*     */       
/*  65 */       if (this.hb.clicked || ((InputHelper.pressedEscape || CInputActionSet.cancel.isJustPressed()) && this.current_x != HIDE_X)) {
/*     */         
/*  67 */         AbstractDungeon.screenSwap = false;
/*  68 */         InputHelper.pressedEscape = false;
/*  69 */         this.hb.clicked = false;
/*  70 */         hide();
/*     */         
/*  72 */         if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/*  73 */           this.hb.clicked = false;
/*     */           
/*  75 */           if (CardCrawlGame.mainMenuScreen.statsScreen.screenUp) {
/*  76 */             CardCrawlGame.mainMenuScreen.statsScreen.hide(); return;
/*     */           } 
/*  78 */           if (CardCrawlGame.mainMenuScreen.isSettingsUp) {
/*  79 */             CardCrawlGame.mainMenuScreen.lighten();
/*  80 */             CardCrawlGame.mainMenuScreen.isSettingsUp = false;
/*  81 */             CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/*     */             
/*  83 */             if (!CardCrawlGame.mainMenuScreen.panelScreen.panels.isEmpty()) {
/*  84 */               CardCrawlGame.mainMenuScreen.panelScreen.open(MenuPanelScreen.PanelScreen.SETTINGS);
/*     */             }
/*  86 */             hide(); return;
/*     */           } 
/*  88 */           if (this.buttonText.equals(TEXT[0])) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */         
/*  93 */         if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
/*  94 */           CardCrawlGame.sound.play("MAP_CLOSE", 0.05F);
/*     */         }
/*     */         
/*  97 */         if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID && (AbstractDungeon.gridSelectScreen.forUpgrade || AbstractDungeon.gridSelectScreen.forTransform || AbstractDungeon.gridSelectScreen.forPurge)) {
/*     */ 
/*     */           
/* 100 */           if (AbstractDungeon.gridSelectScreen.confirmScreenUp) {
/* 101 */             AbstractDungeon.gridSelectScreen.cancelUpgrade();
/*     */           } else {
/* 103 */             AbstractDungeon.closeCurrentScreen();
/* 104 */             if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
/* 105 */               RestRoom r = (RestRoom)AbstractDungeon.getCurrRoom();
/* 106 */               r.campfireUI.reopen();
/*     */             } 
/*     */             return;
/*     */           } 
/*     */         } else {
/* 111 */           if (AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() instanceof TreasureRoomBoss && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
/*     */             
/* 113 */             TreasureRoomBoss r = (TreasureRoomBoss)AbstractDungeon.getCurrRoom();
/* 114 */             r.chest.close();
/*     */           } 
/* 116 */           AbstractDungeon.closeCurrentScreen();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     if (this.current_x != this.target_x) {
/* 122 */       this.current_x = MathUtils.lerp(this.current_x, this.target_x, Gdx.graphics.getDeltaTime() * 9.0F);
/* 123 */       if (Math.abs(this.current_x - this.target_x) < Settings.UI_SNAP_THRESHOLD) {
/* 124 */         this.current_x = this.target_x;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateGlow() {
/* 130 */     this.glowAlpha += Gdx.graphics.getDeltaTime() * 3.0F;
/* 131 */     if (this.glowAlpha < 0.0F) {
/* 132 */       this.glowAlpha *= -1.0F;
/*     */     }
/* 134 */     float tmp = MathUtils.cos(this.glowAlpha);
/* 135 */     if (tmp < 0.0F) {
/* 136 */       this.glowColor.a = -tmp / 2.0F + 0.3F;
/*     */     } else {
/* 138 */       this.glowColor.a = tmp / 2.0F + 0.3F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hovered() {
/* 143 */     return this.hb.hovered;
/*     */   }
/*     */   
/*     */   public void hide() {
/* 147 */     if (!this.isHidden) {
/* 148 */       this.hb.hovered = false;
/* 149 */       InputHelper.justClickedLeft = false;
/* 150 */       this.target_x = HIDE_X;
/* 151 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void hideInstantly() {
/* 156 */     if (!this.isHidden) {
/* 157 */       this.hb.hovered = false;
/* 158 */       InputHelper.justClickedLeft = false;
/* 159 */       this.target_x = HIDE_X;
/* 160 */       this.current_x = this.target_x;
/* 161 */       this.isHidden = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show(String buttonText) {
/* 166 */     if (this.isHidden) {
/* 167 */       this.glowAlpha = 0.0F;
/* 168 */       this.current_x = HIDE_X;
/* 169 */       this.target_x = SHOW_X;
/* 170 */       this.isHidden = false;
/* 171 */       this.buttonText = buttonText;
/*     */     } else {
/* 173 */       this.current_x = HIDE_X;
/* 174 */       this.buttonText = buttonText;
/*     */     } 
/* 176 */     this.hb.hovered = false;
/*     */   }
/*     */   
/*     */   public void showInstantly(String buttonText) {
/* 180 */     this.current_x = SHOW_X;
/* 181 */     this.target_x = SHOW_X;
/* 182 */     this.isHidden = false;
/* 183 */     this.buttonText = buttonText;
/* 184 */     this.hb.hovered = false;
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 188 */     sb.setColor(Color.WHITE);
/* 189 */     renderShadow(sb);
/* 190 */     sb.setColor(this.glowColor);
/* 191 */     renderOutline(sb);
/* 192 */     sb.setColor(Color.WHITE);
/* 193 */     renderButton(sb);
/*     */     
/* 195 */     if (this.hb.hovered && !this.hb.clickStarted) {
/* 196 */       sb.setBlendFunction(770, 1);
/* 197 */       sb.setColor(HOVER_BLEND_COLOR);
/* 198 */       renderButton(sb);
/* 199 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 202 */     Color tmpColor = Settings.LIGHT_YELLOW_COLOR;
/* 203 */     if (this.hb.clickStarted) {
/* 204 */       tmpColor = Color.LIGHT_GRAY;
/*     */     }
/* 206 */     if (Settings.isControllerMode) {
/* 207 */       FontHelper.renderFontLeft(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X - 30.0F * Settings.scale, DRAW_Y + TEXT_OFFSET_Y, tmpColor);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 215 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.current_x + TEXT_OFFSET_X, DRAW_Y + TEXT_OFFSET_Y, tmpColor);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     renderControllerUi(sb);
/*     */     
/* 226 */     if (!this.isHidden) {
/* 227 */       this.hb.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderShadow(SpriteBatch sb) {
/* 232 */     sb.draw(ImageMaster.CANCEL_BUTTON_SHADOW, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/*     */   private void renderOutline(SpriteBatch sb) {
/* 252 */     sb.draw(ImageMaster.CANCEL_BUTTON_OUTLINE, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/*     */   private void renderButton(SpriteBatch sb) {
/* 272 */     sb.draw(ImageMaster.CANCEL_BUTTON, this.current_x - 256.0F, DRAW_Y - 128.0F, 256.0F, 128.0F, 512.0F, 256.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 256, false, false);
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
/*     */   private void renderControllerUi(SpriteBatch sb) {
/* 292 */     if (Settings.isControllerMode) {
/* 293 */       sb.setColor(Color.WHITE);
/* 294 */       sb.draw(CInputActionSet.cancel
/* 295 */           .getKeyImg(), this.current_x - 32.0F - 210.0F * Settings.scale, DRAW_Y - 32.0F + 57.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\CancelButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */