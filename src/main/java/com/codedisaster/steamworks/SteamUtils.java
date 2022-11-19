/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class SteamUtils extends SteamInterface {
/*     */   private final SteamUtilsCallbackAdapter callbackAdapter;
/*     */   
/*     */   public enum SteamAPICallFailure {
/*   9 */     None(-1),
/*  10 */     SteamGone(0),
/*  11 */     NetworkFailure(1),
/*  12 */     InvalidHandle(2),
/*  13 */     MismatchedCallback(3);
/*     */     
/*     */     private final int code;
/*  16 */     private static final SteamAPICallFailure[] values = values();
/*     */     
/*     */     SteamAPICallFailure(int code) {
/*  19 */       this.code = code;
/*     */     } static {
/*     */     
/*     */     } static SteamAPICallFailure byValue(int code) {
/*  23 */       for (SteamAPICallFailure value : values) {
/*  24 */         if (value.code == code) {
/*  25 */           return value;
/*     */         }
/*     */       } 
/*  28 */       return None;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum NotificationPosition {
/*  33 */     TopLeft,
/*  34 */     TopRight,
/*  35 */     BottomLeft,
/*  36 */     BottomRight;
/*     */   }
/*     */   
/*     */   public enum FloatingGamepadTextInputMode {
/*  40 */     ModeSingleLine,
/*  41 */     ModeMultipleLines,
/*  42 */     ModeEmail,
/*  43 */     ModeNumeric;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SteamUtils(SteamUtilsCallback callback) {
/*  49 */     this.callbackAdapter = new SteamUtilsCallbackAdapter(callback);
/*  50 */     setCallback(SteamUtilsNative.createCallback(this.callbackAdapter));
/*     */   }
/*     */   
/*     */   public int getSecondsSinceAppActive() {
/*  54 */     return SteamUtilsNative.getSecondsSinceAppActive();
/*     */   }
/*     */   
/*     */   public int getSecondsSinceComputerActive() {
/*  58 */     return SteamUtilsNative.getSecondsSinceComputerActive();
/*     */   }
/*     */   
/*     */   public SteamUniverse getConnectedUniverse() {
/*  62 */     return SteamUniverse.byValue(SteamUtilsNative.getConnectedUniverse());
/*     */   }
/*     */   
/*     */   public int getServerRealTime() {
/*  66 */     return SteamUtilsNative.getServerRealTime();
/*     */   }
/*     */   
/*     */   public int getImageWidth(int image) {
/*  70 */     return SteamUtilsNative.getImageWidth(image);
/*     */   }
/*     */   
/*     */   public int getImageHeight(int image) {
/*  74 */     return SteamUtilsNative.getImageHeight(image);
/*     */   }
/*     */   
/*     */   public boolean getImageSize(int image, int[] size) {
/*  78 */     return SteamUtilsNative.getImageSize(image, size);
/*     */   }
/*     */   
/*     */   public boolean getImageRGBA(int image, ByteBuffer dest) throws SteamException {
/*  82 */     checkBuffer(dest);
/*  83 */     return SteamUtilsNative.getImageRGBA(image, dest, dest.position(), dest.remaining());
/*     */   }
/*     */   
/*     */   public int getAppID() {
/*  87 */     return SteamUtilsNative.getAppID();
/*     */   }
/*     */   
/*     */   public void setOverlayNotificationPosition(NotificationPosition position) {
/*  91 */     SteamUtilsNative.setOverlayNotificationPosition(position.ordinal());
/*     */   }
/*     */   
/*     */   public boolean isAPICallCompleted(SteamAPICall handle, boolean[] result) {
/*  95 */     return SteamUtilsNative.isAPICallCompleted(handle.handle, result);
/*     */   }
/*     */   
/*     */   public SteamAPICallFailure getAPICallFailureReason(SteamAPICall handle) {
/*  99 */     return SteamAPICallFailure.byValue(SteamUtilsNative.getAPICallFailureReason(handle.handle));
/*     */   }
/*     */   
/*     */   public void setWarningMessageHook(SteamAPIWarningMessageHook messageHook) {
/* 103 */     this.callbackAdapter.setWarningMessageHook(messageHook);
/* 104 */     SteamUtilsNative.enableWarningMessageHook(this.callback, (messageHook != null));
/*     */   }
/*     */   
/*     */   public boolean isOverlayEnabled() {
/* 108 */     return SteamUtilsNative.isOverlayEnabled();
/*     */   }
/*     */   
/*     */   public boolean isSteamRunningOnSteamDeck() {
/* 112 */     return SteamUtilsNative.isSteamRunningOnSteamDeck();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean showFloatingGamepadTextInput(FloatingGamepadTextInputMode keyboardMode, int textFieldXPosition, int textFieldYPosition, int textFieldWidth, int textFieldHeight) {
/* 118 */     return SteamUtilsNative.showFloatingGamepadTextInput(keyboardMode.ordinal(), textFieldXPosition, textFieldYPosition, textFieldWidth, textFieldHeight);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean dismissFloatingGamepadTextInput() {
/* 123 */     return SteamUtilsNative.dismissFloatingGamepadTextInput();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */