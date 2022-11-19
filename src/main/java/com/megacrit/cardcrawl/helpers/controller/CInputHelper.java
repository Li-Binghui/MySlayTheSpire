/*     */ package com.megacrit.cardcrawl.helpers.controller;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CInputHelper
/*     */ {
/*  22 */   private static final Logger logger = LogManager.getLogger(CInputHelper.class.getName());
/*  23 */   public static Array<Controller> controllers = null;
/*  24 */   public static Controller controller = null;
/*  25 */   public static ArrayList<CInputAction> actions = new ArrayList<>();
/*  26 */   public static CInputListener listener = null;
/*     */   private static boolean initializedController = false;
/*  28 */   public static ControllerModel model = null;
/*     */   
/*     */   public enum ControllerModel {
/*  31 */     XBOX_360, XBOX_ONE, PS3, PS4, STEAM, OTHER;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void loadSettings() {
/*  37 */     CInputActionSet.load();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initializeIfAble() {
/*  43 */     if (!initializedController && Display.isActive()) {
/*  44 */       initializedController = true;
/*  45 */       logger.info("[CONTROLLER] Utilizing DirectInput");
/*     */       
/*     */       try {
/*  48 */         controllers = Controllers.getControllers();
/*  49 */       } catch (Exception e) {
/*  50 */         logger.info(e.getMessage());
/*     */       } 
/*     */       
/*  53 */       if (controllers == null) {
/*  54 */         logger.info("[ERROR] getControllers() returned null");
/*     */         
/*     */         return;
/*     */       } 
/*  58 */       for (int i = 0; i < controllers.size; i++) {
/*  59 */         logger.info("CONTROLLER[" + i + "] " + ((Controller)controllers.get(i)).getName());
/*     */       }
/*     */       
/*  62 */       if (controllers.size != 0) {
/*  63 */         Settings.isControllerMode = true;
/*  64 */         Settings.isTouchScreen = false;
/*  65 */         listener = new CInputListener();
/*  66 */         controller = (Controller)controllers.first();
/*  67 */         controller.addListener((ControllerListener) listener);
/*     */ 
/*     */         
/*  70 */         if (controller.getName().contains("360")) {
/*  71 */           model = ControllerModel.XBOX_360;
/*  72 */           ImageMaster.loadControllerImages(ControllerModel.XBOX_360);
/*  73 */         } else if (controller.getName().contains("Xbox One")) {
/*  74 */           model = ControllerModel.XBOX_ONE;
/*  75 */           ImageMaster.loadControllerImages(ControllerModel.XBOX_ONE);
/*     */         } else {
/*     */           
/*  78 */           model = ControllerModel.XBOX_360;
/*  79 */           ImageMaster.loadControllerImages(ControllerModel.XBOX_360);
/*     */         } 
/*     */       } else {
/*  82 */         logger.info("[CONTROLLER] No controllers detected");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setController(Controller controllerToUse) {
/*  88 */     Settings.isControllerMode = true;
/*  89 */     Settings.isTouchScreen = false;
/*  90 */     controller = controllerToUse;
/*  91 */     controller.addListener((ControllerListener) listener);
/*  92 */     Controllers.removeListener((ControllerListener) listener);
/*     */   }
/*     */   
/*     */   public static void setCursor(Hitbox hb) {
/*  96 */     if (hb != null) {
/*  97 */       int y = Settings.HEIGHT - (int)hb.cY;
/*     */       
/*  99 */       if (y < 0) {
/* 100 */         y = 0;
/* 101 */       } else if (y > Settings.HEIGHT) {
/* 102 */         y = Settings.HEIGHT;
/*     */       } 
/*     */       
/* 105 */       Gdx.input.setCursorPosition((int)hb.cX, y);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void updateLast() {
/* 110 */     for (CInputAction a : actions) {
/* 111 */       a.justPressed = false;
/* 112 */       a.justReleased = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean listenerPress(int keycode) {
/* 117 */     for (CInputAction a : actions) {
/* 118 */       if (a.keycode == keycode) {
/* 119 */         a.justPressed = true;
/* 120 */         a.pressed = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 124 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean listenerRelease(int keycode) {
/* 128 */     for (CInputAction a : actions) {
/* 129 */       if (a.keycode == keycode) {
/* 130 */         a.justReleased = true;
/* 131 */         a.pressed = false;
/*     */         break;
/*     */       } 
/*     */     } 
/* 135 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isJustPressed(int keycode) {
/* 139 */     for (CInputAction a : actions) {
/* 140 */       if (a.keycode == keycode) {
/* 141 */         return a.justPressed;
/*     */       }
/*     */     } 
/* 144 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean isJustReleased(int keycode) {
/* 148 */     for (CInputAction a : actions) {
/* 149 */       if (a.keycode == keycode) {
/* 150 */         return a.justReleased;
/*     */       }
/*     */     } 
/* 153 */     return false;
/*     */   }
/*     */   
/*     */   public static void regainInputFocus() {
/* 157 */     CInputListener.remapping = false;
/*     */   }
/*     */   
/*     */   public static boolean isTopPanelActive() {
/* 161 */     return (AbstractDungeon.topPanel.selectPotionMode || AbstractDungeon.player.viewingRelics || !AbstractDungeon.topPanel.potionUi.isHidden);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\controller\CInputHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */