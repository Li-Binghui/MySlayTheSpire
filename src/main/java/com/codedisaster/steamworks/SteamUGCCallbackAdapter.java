/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ class SteamUGCCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamUGCCallback>
/*    */ {
/*    */   SteamUGCCallbackAdapter(SteamUGCCallback callback) {
/*  7 */     super(callback);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void onUGCQueryCompleted(long handle, int numResultsReturned, int totalMatchingResults, boolean isCachedData, int result) {
/* 13 */     this.callback.onUGCQueryCompleted(new SteamUGCQuery(handle), numResultsReturned, totalMatchingResults, isCachedData, 
/* 14 */         SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onSubscribeItem(long publishedFileID, int result) {
/* 18 */     this.callback.onSubscribeItem(new SteamPublishedFileID(publishedFileID), SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onUnsubscribeItem(long publishedFileID, int result) {
/* 22 */     this.callback.onUnsubscribeItem(new SteamPublishedFileID(publishedFileID), SteamResult.byValue(result));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void onRequestUGCDetails(long publishedFileID, int result, String title, String description, long fileHandle, long previewFileHandle, String fileName, boolean cachedData, int votesUp, int votesDown, long ownerID, int timeCreated, int timeUpdated) {
/* 30 */     SteamUGCDetails details = new SteamUGCDetails();
/* 31 */     details.publishedFileID = publishedFileID;
/* 32 */     details.result = result;
/* 33 */     details.title = title;
/* 34 */     details.description = description;
/* 35 */     details.fileHandle = fileHandle;
/* 36 */     details.previewFileHandle = previewFileHandle;
/* 37 */     details.fileName = fileName;
/* 38 */     details.votesUp = votesUp;
/* 39 */     details.votesDown = votesDown;
/* 40 */     details.ownerID = ownerID;
/* 41 */     details.timeCreated = timeCreated;
/* 42 */     details.timeUpdated = timeUpdated;
/*    */     
/* 44 */     this.callback.onRequestUGCDetails(details, SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onCreateItem(long publishedFileID, boolean needsToAcceptWLA, int result) {
/* 48 */     this.callback.onCreateItem(new SteamPublishedFileID(publishedFileID), needsToAcceptWLA, SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onSubmitItemUpdate(long publishedFileID, boolean needsToAcceptWLA, int result) {
/* 52 */     this.callback.onSubmitItemUpdate(new SteamPublishedFileID(publishedFileID), needsToAcceptWLA, 
/* 53 */         SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onDownloadItemResult(int appID, long publishedFileID, int result) {
/* 57 */     this.callback.onDownloadItemResult(appID, new SteamPublishedFileID(publishedFileID), SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onUserFavoriteItemsListChanged(long publishedFileID, boolean wasAddRequest, int result) {
/* 61 */     this.callback.onUserFavoriteItemsListChanged(new SteamPublishedFileID(publishedFileID), wasAddRequest, 
/* 62 */         SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onSetUserItemVote(long publishedFileID, boolean voteUp, int result) {
/* 66 */     this.callback.onSetUserItemVote(new SteamPublishedFileID(publishedFileID), voteUp, SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onGetUserItemVote(long publishedFileID, boolean votedUp, boolean votedDown, boolean voteSkipped, int result) {
/* 70 */     this.callback.onGetUserItemVote(new SteamPublishedFileID(publishedFileID), votedUp, votedDown, voteSkipped, 
/* 71 */         SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onStartPlaytimeTracking(int result) {
/* 75 */     this.callback.onStartPlaytimeTracking(SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onStopPlaytimeTracking(int result) {
/* 79 */     this.callback.onStopPlaytimeTracking(SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onStopPlaytimeTrackingForAllItems(int result) {
/* 83 */     this.callback.onStopPlaytimeTrackingForAllItems(SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onDeleteItem(long publishedFileID, int result) {
/* 87 */     this.callback.onDeleteItem(new SteamPublishedFileID(publishedFileID), SteamResult.byValue(result));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUGCCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */