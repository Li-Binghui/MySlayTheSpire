/*    */ package com.megacrit.cardcrawl.helpers.input;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.InputProcessor;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class ScrollInputProcessor
/*    */   implements InputProcessor
/*    */ {
/* 12 */   private static final Logger logger = LogManager.getLogger(ScrollInputProcessor.class.getName());
/* 13 */   public static String lastPressed = "";
/* 14 */   public static String lastPressed2 = "";
/*    */   public static boolean lastPressedSwitch = true;
/*    */   
/*    */   public static void logLastPressed(String msg) {
/* 18 */     lastPressedSwitch = !lastPressedSwitch;
/* 19 */     if (lastPressedSwitch) {
/* 20 */       lastPressed = msg;
/*    */     } else {
/* 22 */       lastPressed2 = msg;
/*    */     } 
/*    */     
/* 25 */     if (Settings.isInfo) {
/* 26 */       logger.info(msg);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean keyDown(int keycode) {
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean keyUp(int keycode) {
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean keyTyped(char character) {
/* 42 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
/* 51 */     if (!Gdx.input.isButtonPressed(1)) {
/* 52 */       InputHelper.touchDown = true;
/*    */     }
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
/* 59 */     InputHelper.touchUp = true;
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean touchDragged(int screenX, int screenY, int pointer) {
/* 65 */     InputHelper.isMouseDown = true;
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean mouseMoved(int screenX, int screenY) {
/* 71 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean scrolled(int amount) {
/* 76 */     if (amount == -1) {
/* 77 */       InputHelper.scrolledUp = true;
/* 78 */     } else if (amount == 1) {
/* 79 */       InputHelper.scrolledDown = true;
/*    */     } 
/* 81 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\input\ScrollInputProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */