/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ 
/*    */ public class SteamScreenshots
/*    */   extends SteamInterface
/*    */ {
/*    */   public SteamScreenshots(SteamScreenshotsCallback callback) {
/*  9 */     super(SteamScreenshotsNative.createCallback(new SteamScreenshotsCallbackAdapter(callback)));
/*    */   }
/*    */   
/*    */   public SteamScreenshotHandle writeScreenshot(ByteBuffer rgb, int width, int height) {
/* 13 */     return new SteamScreenshotHandle(SteamScreenshotsNative.writeScreenshot(rgb, rgb.remaining(), width, height));
/*    */   }
/*    */   
/*    */   public SteamScreenshotHandle addScreenshotToLibrary(String file, String thumbnail, int width, int height) {
/* 17 */     return new SteamScreenshotHandle(SteamScreenshotsNative.addScreenshotToLibrary(file, thumbnail, width, height));
/*    */   }
/*    */   
/*    */   public void triggerScreenshot() {
/* 21 */     SteamScreenshotsNative.triggerScreenshot();
/*    */   }
/*    */   
/*    */   public void hookScreenshots(boolean hook) {
/* 25 */     SteamScreenshotsNative.hookScreenshots(hook);
/*    */   }
/*    */   
/*    */   public boolean setLocation(SteamScreenshotHandle screenshot, String location) {
/* 29 */     return SteamScreenshotsNative.setLocation(screenshot.handle, location);
/*    */   }
/*    */   
/*    */   public boolean tagUser(SteamScreenshotHandle screenshot, SteamID steamID) {
/* 33 */     return SteamScreenshotsNative.tagUser(screenshot.handle, steamID.handle);
/*    */   }
/*    */   
/*    */   public boolean tagPublishedFile(SteamScreenshotHandle screenshot, SteamPublishedFileID publishedFileID) {
/* 37 */     return SteamScreenshotsNative.tagPublishedFile(screenshot.handle, publishedFileID.handle);
/*    */   }
/*    */   
/*    */   public boolean isScreenshotsHooked() {
/* 41 */     return SteamScreenshotsNative.isScreenshotsHooked();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamScreenshots.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */