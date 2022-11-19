/*     */ package com.megacrit.cardcrawl.helpers.controller;
/*     */ 
/*     */ import com.badlogic.gdx.Input;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CInputAction
/*     */ {
/*  12 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("InputKeyNames");
/*  13 */   public static final Map<String, String> TEXT_CONVERSIONS = uiStrings.TEXT_DICT;
/*     */   public int keycode;
/*     */   public boolean justPressed = false, pressed = false, justReleased = false;
/*     */   
/*     */   public CInputAction(int keycode) {
/*  18 */     this.keycode = keycode;
/*     */   }
/*     */   
/*     */   public int getKey() {
/*  22 */     return this.keycode;
/*     */   }
/*     */   
/*     */   public String getKeyString() {
/*  26 */     String keycodeStr = Input.Keys.toString(this.keycode);
/*  27 */     return TEXT_CONVERSIONS.getOrDefault(keycodeStr, keycodeStr);
/*     */   }
/*     */   
/*     */   public Texture getKeyImg() {
/*  31 */     switch (this.keycode) {
/*     */       case 0:
/*  33 */         return ImageMaster.CONTROLLER_A;
/*     */       case 1:
/*  35 */         return ImageMaster.CONTROLLER_B;
/*     */       case 2:
/*  37 */         return ImageMaster.CONTROLLER_X;
/*     */       case 3:
/*  39 */         return ImageMaster.CONTROLLER_Y;
/*     */       case 4:
/*  41 */         return ImageMaster.CONTROLLER_LB;
/*     */       case 5:
/*  43 */         return ImageMaster.CONTROLLER_RB;
/*     */       case 6:
/*  45 */         return ImageMaster.CONTROLLER_BACK;
/*     */       case 7:
/*  47 */         return ImageMaster.CONTROLLER_START;
/*     */       case 8:
/*  49 */         return ImageMaster.CONTROLLER_LS;
/*     */       case 9:
/*  51 */         return ImageMaster.CONTROLLER_RS;
/*     */       case 1000:
/*  53 */         return ImageMaster.CONTROLLER_LS_DOWN;
/*     */       case -1000:
/*  55 */         return ImageMaster.CONTROLLER_LS_UP;
/*     */       case 1001:
/*  57 */         return ImageMaster.CONTROLLER_LS_RIGHT;
/*     */       case -1001:
/*  59 */         return ImageMaster.CONTROLLER_LS_LEFT;
/*     */       case 1003:
/*  61 */         return ImageMaster.CONTROLLER_RS_RIGHT;
/*     */       case -1002:
/*  63 */         return ImageMaster.CONTROLLER_RS_UP;
/*     */       case 1002:
/*  65 */         return ImageMaster.CONTROLLER_RS_DOWN;
/*     */       case -1003:
/*  67 */         return ImageMaster.CONTROLLER_RS_LEFT;
/*     */       case 1004:
/*  69 */         return ImageMaster.CONTROLLER_LT;
/*     */       case -1004:
/*  71 */         return ImageMaster.CONTROLLER_RT;
/*     */       case 2000:
/*  73 */         return ImageMaster.CONTROLLER_D_DOWN;
/*     */       case -2000:
/*  75 */         return ImageMaster.CONTROLLER_D_UP;
/*     */       case 2001:
/*  77 */         return ImageMaster.CONTROLLER_D_RIGHT;
/*     */       case -2001:
/*  79 */         return ImageMaster.CONTROLLER_D_LEFT;
/*     */     } 
/*  81 */     return ImageMaster.CONTROLLER_A;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isJustPressed() {
/*  86 */     return this.justPressed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unpress() {
/*  93 */     this.justPressed = false;
/*     */   }
/*     */   
/*     */   public boolean isJustReleased() {
/*  97 */     return CInputHelper.isJustReleased(this.keycode);
/*     */   }
/*     */   
/*     */   public void remap(int newKeycode) {
/* 101 */     this.keycode = newKeycode;
/*     */   }
/*     */   
/*     */   public boolean isPressed() {
/* 105 */     return this.pressed;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\controller\CInputAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */