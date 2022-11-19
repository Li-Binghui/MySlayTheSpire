/*     */ package com.megacrit.cardcrawl.helpers.controller;
/*     */
/*     */ import com.badlogic.gdx.math.Vector3;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.screens.options.RemapInputElement;
/*     */ import net.java.games.input.ControllerEvent;
import net.java.games.input.ControllerListener;
import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Controller;

/*     */
/*     */ public class CInputListener
/*     */   implements ControllerListener {
/*  15 */   private static final Logger logger = LogManager.getLogger(CInputListener.class.getName());
/*     */   private static final float DEADZONE = 0.5F;
/*  17 */   private float[] axisValues = new float[] { 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F };
/*     */   public static boolean remapping = false;
/*  19 */   public static RemapInputElement element = null;
/*     */   
/*     */   public static void listenForRemap(RemapInputElement e) {
/*  22 */     element = e;
/*  23 */     remapping = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void connected(Controller controller) {
/*  28 */     logger.info("CONNECTED: " + controller.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void disconnected(Controller controller) {
/*  33 */     logger.info("DISCONNECTED: " + controller.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean buttonDown(Controller controller, int buttonCode) {
/*  38 */     if (!Settings.CONTROLLER_ENABLED) {
/*  39 */       return false;
/*     */     }
/*     */     
/*  42 */     if (remapping && element.cAction != null) {
/*  43 */       if (element.listener.willRemapController(element, element.cAction.keycode, buttonCode)) {
/*  44 */         element.cAction.remap(buttonCode);
/*     */       }
/*  46 */       remapping = false;
/*  47 */       element.hasInputFocus = false;
/*  48 */       InputHelper.regainInputFocus();
/*  49 */       CInputHelper.regainInputFocus();
/*  50 */       element = null;
/*  51 */       return false;
/*     */     } 
/*     */     
/*  54 */     if (!Settings.isControllerMode) {
/*  55 */       Settings.isControllerMode = true;
/*  56 */       Settings.isTouchScreen = false;
/*     */     } 
/*  58 */     CInputHelper.listenerPress(buttonCode);
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean buttonUp(Controller controller, int buttonCode) {
/*  64 */     CInputHelper.listenerRelease(buttonCode);
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean axisMoved(Controller controller, int axisCode, float value) {
/*  70 */     if (!Settings.CONTROLLER_ENABLED) {
/*  71 */       return false;
/*     */     }
/*     */     
/*  74 */     if (remapping && element.cAction != null) {
/*  75 */       if (value > 0.5F && this.axisValues[axisCode] < 0.5F) {
/*  76 */         if (element.listener.willRemapController(element, element.cAction.keycode, 1000 + axisCode)) {
/*  77 */           element.cAction.remap(1000 + axisCode);
/*     */         }
/*  79 */         remapping = false;
/*  80 */         element.hasInputFocus = false;
/*  81 */         InputHelper.regainInputFocus();
/*  82 */         CInputHelper.regainInputFocus();
/*  83 */         element = null;
/*  84 */         return false;
/*  85 */       }  if (value < -0.5F && this.axisValues[axisCode] > -0.5F) {
/*  86 */         if (element.listener.willRemapController(element, element.cAction.keycode, -1000 - axisCode)) {
/*  87 */           element.cAction.remap(-1000 - axisCode);
/*     */         }
/*  89 */         remapping = false;
/*  90 */         element.hasInputFocus = false;
/*  91 */         InputHelper.regainInputFocus();
/*  92 */         CInputHelper.regainInputFocus();
/*  93 */         element = null;
/*  94 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     if (value > 0.5F && this.axisValues[axisCode] < 0.5F) {
/*  99 */       CInputHelper.listenerPress(1000 + axisCode);
/* 100 */       if (!Settings.isControllerMode) {
/* 101 */         Settings.isControllerMode = true;
/* 102 */         Settings.isTouchScreen = false;
/* 103 */         logger.info("Entering controller mode: AXIS moved");
/*     */       } 
/* 105 */     } else if (value < -0.5F && this.axisValues[axisCode] > -0.5F) {
/* 106 */       CInputHelper.listenerPress(-1000 - axisCode);
/* 107 */       if (!Settings.isControllerMode) {
/* 108 */         Settings.isControllerMode = true;
/* 109 */         Settings.isTouchScreen = false;
/* 110 */         logger.info("Entering controller mode: AXIS moved");
/*     */       } 
/*     */     } 
/* 113 */     this.axisValues[axisCode] = value;
/*     */     
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean povMoved(Controller controller, int povCode, PovDirection value) {
/* 120 */     if (!Settings.CONTROLLER_ENABLED) {
/* 121 */       return false;
/*     */     }
/*     */     
/* 124 */     if (remapping && element.cAction != null) {
/* 125 */       int code = -2000;
/* 126 */       switch (value) {
/*     */         case north:
/* 128 */           code = -2000;
/*     */           break;
/*     */         case south:
/* 131 */           code = 2000;
/*     */           break;
/*     */         case northWest:
/*     */         case southWest:
/*     */         case west:
/* 136 */           code = -2001;
/*     */           break;
/*     */         case northEast:
/*     */         case southEast:
/*     */         case east:
/* 141 */           code = 2001;
/*     */           break;
/*     */         default:
/* 144 */           code = -2000;
/*     */           break;
/*     */       } 
/*     */       
/* 148 */       if (element.listener.willRemapController(element, element.cAction.keycode, code)) {
/* 149 */         element.cAction.remap(code);
/*     */       }
/* 151 */       remapping = false;
/* 152 */       element.hasInputFocus = false;
/* 153 */       InputHelper.regainInputFocus();
/* 154 */       CInputHelper.regainInputFocus();
/* 155 */       element = null;
/* 156 */       return false;
/*     */     } 
/*     */     
/* 159 */     if (!Settings.isControllerMode) {
/* 160 */       Settings.isControllerMode = true;
/* 161 */       Settings.isTouchScreen = false;
/* 162 */       logger.info("Entering controller mode: D-Pad pressed");
/*     */     } 
/*     */     
/* 165 */     switch (value) {
/*     */       case north:
/* 167 */         CInputHelper.listenerPress(-2000);
/*     */         break;
/*     */       case south:
/* 170 */         CInputHelper.listenerPress(2000);
/*     */         break;
/*     */       case northWest:
/*     */       case southWest:
/*     */       case west:
/* 175 */         CInputHelper.listenerPress(-2001);
/*     */         break;
/*     */       case northEast:
/*     */       case southEast:
/*     */       case east:
/* 180 */         CInputHelper.listenerPress(2001);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
/* 201 */     return false;
/*     */   }

    @Override
    public void controllerRemoved(ControllerEvent controllerEvent) {

    }

    @Override
    public void controllerAdded(ControllerEvent controllerEvent) {

    }
    /*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\controller\CInputListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */