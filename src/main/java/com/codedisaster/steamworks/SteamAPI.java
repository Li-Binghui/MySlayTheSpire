/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class SteamAPI
/*    */ {
/*    */   private static boolean isRunning = false;
/*    */   private static boolean isNativeAPILoaded = false;
/*    */   
/*    */   public static void loadLibraries() throws SteamException {
/* 11 */     loadLibraries(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void loadLibraries(String libraryPath) throws SteamException {
/* 16 */     if (isNativeAPILoaded) {
/*    */       return;
/*    */     }
/*    */     
/* 20 */     if (libraryPath == null && SteamSharedLibraryLoader.DEBUG) {
/* 21 */       String sdkPath = SteamSharedLibraryLoader.getSdkRedistributableBinPath();
/* 22 */       SteamSharedLibraryLoader.loadLibrary("steam_api", sdkPath);
/*    */     } else {
/* 24 */       SteamSharedLibraryLoader.loadLibrary("steam_api", libraryPath);
/*    */     } 
/*    */     
/* 27 */     SteamSharedLibraryLoader.loadLibrary("steamworks4j", libraryPath);
/*    */     
/* 29 */     isNativeAPILoaded = true;
/*    */   }
/*    */   
/*    */   public static void skipLoadLibraries() {
/* 33 */     isNativeAPILoaded = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean restartAppIfNecessary(int appId) throws SteamException {
/* 38 */     if (!isNativeAPILoaded) {
/* 39 */       throw new SteamException("Native libraries not loaded.\nEnsure to call SteamAPI.loadLibraries() first!");
/*    */     }
/*    */     
/* 42 */     return nativeRestartAppIfNecessary(appId);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean init() throws SteamException {
/* 47 */     if (!isNativeAPILoaded) {
/* 48 */       throw new SteamException("Native libraries not loaded.\nEnsure to call SteamAPI.loadLibraries() first!");
/*    */     }
/*    */     
/* 51 */     isRunning = nativeInit();
/*    */     
/* 53 */     return isRunning;
/*    */   }
/*    */   
/*    */   public static void shutdown() {
/* 57 */     isRunning = false;
/* 58 */     nativeShutdown();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isSteamRunning() {
/* 70 */     return isSteamRunning(false);
/*    */   }
/*    */   
/*    */   public static boolean isSteamRunning(boolean checkNative) {
/* 74 */     return (isRunning && (!checkNative || isSteamRunningNative()));
/*    */   }
/*    */   
/*    */   public static void printDebugInfo(PrintStream stream) {
/* 78 */     stream.println("  Steam API initialized: " + isRunning);
/* 79 */     stream.println("  Steam client active: " + isSteamRunning());
/*    */   }
/*    */   
/*    */   static boolean isIsNativeAPILoaded() {
/* 83 */     return isNativeAPILoaded;
/*    */   }
/*    */   
/*    */   private static native boolean nativeRestartAppIfNecessary(int paramInt);
/*    */   
/*    */   public static native void releaseCurrentThreadMemory();
/*    */   
/*    */   private static native boolean nativeInit();
/*    */   
/*    */   private static native void nativeShutdown();
/*    */   
/*    */   public static native void runCallbacks();
/*    */   
/*    */   private static native boolean isSteamRunningNative();
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamAPI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */