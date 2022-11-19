/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ class SteamRemoteStorageCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamRemoteStorageCallback>
/*    */ {
/*    */   SteamRemoteStorageCallbackAdapter(SteamRemoteStorageCallback callback) {
/*  7 */     super(callback);
/*    */   }
/*    */   
/*    */   void onFileShareResult(long fileHandle, String fileName, int result) {
/* 11 */     this.callback.onFileShareResult(new SteamUGCHandle(fileHandle), fileName, 
/* 12 */         SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onDownloadUGCResult(long fileHandle, int result) {
/* 16 */     this.callback.onDownloadUGCResult(new SteamUGCHandle(fileHandle), SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onPublishFileResult(long publishedFileID, boolean needsToAcceptWLA, int result) {
/* 20 */     this.callback.onPublishFileResult(new SteamPublishedFileID(publishedFileID), needsToAcceptWLA, 
/* 21 */         SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onUpdatePublishedFileResult(long publishedFileID, boolean needsToAcceptWLA, int result) {
/* 25 */     this.callback.onUpdatePublishedFileResult(new SteamPublishedFileID(publishedFileID), needsToAcceptWLA, 
/* 26 */         SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onPublishedFileSubscribed(long publishedFileID, int appID) {
/* 30 */     this.callback.onPublishedFileSubscribed(new SteamPublishedFileID(publishedFileID), appID);
/*    */   }
/*    */   
/*    */   void onPublishedFileUnsubscribed(long publishedFileID, int appID) {
/* 34 */     this.callback.onPublishedFileUnsubscribed(new SteamPublishedFileID(publishedFileID), appID);
/*    */   }
/*    */   
/*    */   void onPublishedFileDeleted(long publishedFileID, int appID) {
/* 38 */     this.callback.onPublishedFileDeleted(new SteamPublishedFileID(publishedFileID), appID);
/*    */   }
/*    */   
/*    */   void onFileWriteAsyncComplete(int result) {
/* 42 */     this.callback.onFileWriteAsyncComplete(SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onFileReadAsyncComplete(long fileReadAsync, int result, int offset, int read) {
/* 46 */     this.callback.onFileReadAsyncComplete(new SteamAPICall(fileReadAsync), 
/* 47 */         SteamResult.byValue(result), offset, read);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamRemoteStorageCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */