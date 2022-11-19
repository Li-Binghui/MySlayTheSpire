/*    */ package com.megacrit.cardcrawl.daily;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.TimeZone;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class TimeHelper
/*    */ {
/* 15 */   private static final Logger logger = LogManager.getLogger(TimeHelper.class.getName());
/*    */   public static boolean isTimeSet = false;
/*    */   private static long daySince1970;
/*    */   private static long timeServerTime;
/*    */   private static long endTimeMs;
/*    */   private static final float UPDATE_FREQ = -1.0F;
/* 21 */   private static float updateTimer = -1.0F;
/*    */   private static boolean offlineMode = false;
/* 23 */   private static DateFormat format = new SimpleDateFormat(
/* 24 */       (CardCrawlGame.languagePack.getUIString("DailyScreen")).TEXT[17]);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setTime(long unixTime, boolean isOfflineMode) {
/* 32 */     if (!isTimeSet) {
/* 33 */       offlineMode = isOfflineMode;
/* 34 */       timeServerTime = unixTime;
/* 35 */       daySince1970 = timeServerTime / 86400L;
/* 36 */       logger.info("Setting time to: " + timeServerTime);
/* 37 */       endTimeMs = (daySince1970 + 1L) * 86400L * 1000L;
/* 38 */       logger.info("Day was set!");
/* 39 */       isTimeSet = true;
/* 40 */       format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean isOfflineMode() {
/* 45 */     return offlineMode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static long daySince1970() {
/* 54 */     return daySince1970;
/*    */   }
/*    */   
/*    */   public static void update() {
/* 58 */     if (isTimeSet) {
/* 59 */       updateTimer -= Gdx.graphics.getDeltaTime();
/* 60 */       if (updateTimer < 0.0F) {
/* 61 */         updateTimer = -1.0F;
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String getTodayDate() {
/* 67 */     return format.format(new Date(System.currentTimeMillis()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getDate(long numDaysSince1970) {
/* 74 */     long unixTimestampMs = (daySince1970 - numDaysSince1970) * 86400L * 1000L;
/* 75 */     return format.format(new Date(System.currentTimeMillis() - unixTimestampMs));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getTimeLeft() {
/* 81 */     if (endTimeMs - System.currentTimeMillis() < 0L) {
/* 82 */       endTimeMs += 86400000L;
/* 83 */       daySince1970++;
/*    */     } 
/*    */     
/* 86 */     long remainingSec = (endTimeMs - System.currentTimeMillis()) / 1000L;
/* 87 */     long hours = remainingSec / 3600L;
/* 88 */     remainingSec %= 3600L;
/* 89 */     long minutes = remainingSec / 60L;
/* 90 */     return String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(remainingSec % 60L) });
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\TimeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */