/*    */ package com.megacrit.cardcrawl.integrations.steam;
/*    */ import com.codedisaster.steamworks.*;
/*    */
/*    */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*    */
/*    */ public class SRCallback implements SteamRemoteStorageCallback {
/*  7 */   private static final Logger logger = LogManager.getLogger(SRCallback.class.getName());
/*    */ 
/*    */   
/*    */   public void onFileShareResult(SteamUGCHandle fileHandle, String fileName, SteamResult result) {
/* 11 */     logger.info("The 'onFileShareResult' callback was called and returns: fileHandle=" + fileHandle
/* 12 */         .toString() + ", fileName=" + fileName + ", result=" + result
/* 13 */         .toString());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDownloadUGCResult(SteamUGCHandle fileHandle, SteamResult result) {
/* 18 */     logger.info("The 'onDownloadUGCResult' callback was called and returns: fileHandle=" + fileHandle
/* 19 */         .toString() + ", result=" + result
/* 20 */         .toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onPublishFileResult(SteamPublishedFileID publishedFileID, boolean needsToAcceptWLA, SteamResult result) {
/* 28 */     logger.info("The 'onPublishFileResult' callback was called and returns: publishedFileID=" + publishedFileID
/* 29 */         .toString() + ", needsToAcceptWLA=" + needsToAcceptWLA + ", result=" + result
/* 30 */         .toString());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdatePublishedFileResult(SteamPublishedFileID publishedFileID, boolean needsToAcceptWLA, SteamResult result) {
/* 38 */     logger.info("The 'onUpdatePublishedFileResult' callback was called and returns: publishedFileID=" + publishedFileID
/*    */         
/* 40 */         .toString() + ", needsToAcceptWLA=" + needsToAcceptWLA + ", result=" + result.toString());
/*    */   }
/*    */   
/*    */   public void onPublishedFileSubscribed(SteamPublishedFileID publishedFileID, int appID) {}
/*    */   
/*    */   public void onPublishedFileUnsubscribed(SteamPublishedFileID publishedFileID, int appID) {}
/*    */   
/*    */   public void onPublishedFileDeleted(SteamPublishedFileID publishedFileID, int appID) {}
/*    */   
/*    */   public void onFileWriteAsyncComplete(SteamResult result) {}
/*    */   
/*    */   public void onFileReadAsyncComplete(SteamAPICall fileReadAsync, SteamResult result, int offset, int read) {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\steam\SRCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */