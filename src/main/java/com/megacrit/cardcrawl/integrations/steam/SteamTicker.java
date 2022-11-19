/*    */ package com.megacrit.cardcrawl.integrations.steam;
/*    */ 
/*    */ import com.codedisaster.steamworks.SteamAPI;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class SteamTicker
/*    */   implements Runnable {
/*  9 */   private static final Logger logger = LogManager.getLogger(SteamTicker.class.getName());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/* 16 */     logger.info("Steam Ticker initialized.");
/* 17 */     while (SteamAPI.isSteamRunning()) {
/*    */       try {
/* 19 */         Thread.sleep(66L);
/* 20 */       } catch (InterruptedException e) {
/* 21 */         e.printStackTrace();
/*    */       } 
/*    */ 
/*    */       
/* 25 */       SteamAPI.runCallbacks();
/*    */     } 
/*    */     
/* 28 */     logger.info("[ERROR] SteamAPI stopped running.");
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\steam\SteamTicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */