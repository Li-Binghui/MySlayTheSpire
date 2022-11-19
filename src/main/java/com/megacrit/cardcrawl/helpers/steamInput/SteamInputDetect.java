/*    */ package com.megacrit.cardcrawl.helpers.steamInput;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class SteamInputDetect
/*    */   implements Runnable {
/* 10 */   private static final Logger logger = LogManager.getLogger(SteamInputDetect.class.getName());
/*    */ 
/*    */   
/*    */   public void run() {
/* 14 */     int tries = 0;
/*    */     
/* 16 */     while (!Thread.currentThread().isInterrupted()) {
/*    */       try {
/* 18 */         tries++;
/*    */ 
/*    */         
/* 21 */         int num = 0;
/* 22 */         num = SteamInputHelper.controller.getConnectedControllers(SteamInputHelper.controllerHandles);
/*    */         
/* 24 */         if (num != 0) {
/* 25 */           SteamInputHelper.initActions(SteamInputHelper.controllerHandles[0]);
/* 26 */           Settings.isControllerMode = true;
/* 27 */           Settings.isTouchScreen = false;
/*    */           
/* 29 */           logger.info("Steam Input controller found!");
/*    */           
/* 31 */           SteamInputHelper.numControllers = 1;
/* 32 */           Thread.currentThread().interrupt();
/* 33 */         } else if (tries == 12) {
/* 34 */           SteamInputHelper.numControllers = 999;
/* 35 */           Thread.currentThread().interrupt();
/*    */         } 
/*    */         
/* 38 */         Thread.sleep(500L);
/* 39 */       } catch (InterruptedException e) {
/* 40 */         logger.info("Steam input detect thread interrupted!");
/* 41 */         Thread.currentThread().interrupt();
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     logger.info("Steam input detect thread will die now.");
/* 46 */     CardCrawlGame.sInputDetectThread = null;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\steamInput\SteamInputDetect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */