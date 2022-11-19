/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class SteamRemoteStorage
/*     */   extends SteamInterface
/*     */ {
/*     */   public enum RemoteStoragePlatform {
/*   9 */     None(0),
/*  10 */     Windows(1),
/*  11 */     OSX(2),
/*  12 */     PS3(4),
/*  13 */     Linux(8),
/*  14 */     Reserved2(16),
/*  15 */     Android(32),
/*  16 */     IOS(64),
/*     */     
/*  18 */     All(-1);
/*     */     
/*     */     private final int mask;
/*  21 */     private static final RemoteStoragePlatform[] values = values();
/*     */     
/*     */     RemoteStoragePlatform(int mask) {
/*  24 */       this.mask = mask;
/*     */     } static {
/*     */     
/*     */     } static RemoteStoragePlatform[] byMask(int mask) {
/*  28 */       int bits = Integer.bitCount(mask);
/*  29 */       RemoteStoragePlatform[] result = new RemoteStoragePlatform[bits];
/*     */       
/*  31 */       int idx = 0;
/*  32 */       for (RemoteStoragePlatform value : values) {
/*  33 */         if ((value.mask & mask) != 0) {
/*  34 */           result[idx++] = value;
/*     */         }
/*     */       } 
/*     */       
/*  38 */       return result;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum UGCReadAction {
/*  43 */     ContinueReadingUntilFinished,
/*  44 */     ContinueReading,
/*  45 */     Close;
/*     */   }
/*     */   
/*     */   public enum PublishedFileVisibility {
/*  49 */     Public,
/*  50 */     FriendsOnly,
/*  51 */     Private;
/*     */   }
/*     */   
/*     */   public enum WorkshopFileType {
/*  55 */     Community,
/*  56 */     Microtransaction,
/*  57 */     Collection,
/*  58 */     Art,
/*  59 */     Video,
/*  60 */     Screenshot,
/*  61 */     Game,
/*  62 */     Software,
/*  63 */     Concept,
/*  64 */     WebGuide,
/*  65 */     IntegratedGuide,
/*  66 */     Merch,
/*  67 */     ControllerBinding,
/*  68 */     SteamworksAccessInvite,
/*  69 */     SteamVideo,
/*  70 */     GameManagedItem;
/*     */     
/*  72 */     private static final WorkshopFileType[] values = values(); static {
/*     */     
/*     */     } static WorkshopFileType byOrdinal(int ordinal) {
/*  75 */       return values[ordinal];
/*     */     }
/*     */   }
/*     */   
/*     */   public SteamRemoteStorage(SteamRemoteStorageCallback callback) {
/*  80 */     super(SteamRemoteStorageNative.createCallback(new SteamRemoteStorageCallbackAdapter(callback)));
/*     */   }
/*     */   
/*     */   public boolean fileWrite(String file, ByteBuffer data) throws SteamException {
/*  84 */     checkBuffer(data);
/*  85 */     return SteamRemoteStorageNative.fileWrite(file, data, data.position(), data.remaining());
/*     */   }
/*     */   
/*     */   public int fileRead(String file, ByteBuffer buffer) throws SteamException {
/*  89 */     checkBuffer(buffer);
/*  90 */     return SteamRemoteStorageNative.fileRead(file, buffer, buffer.position(), buffer.remaining());
/*     */   }
/*     */   
/*     */   public SteamAPICall fileWriteAsync(String file, ByteBuffer data) throws SteamException {
/*  94 */     checkBuffer(data);
/*  95 */     return new SteamAPICall(SteamRemoteStorageNative.fileWriteAsync(this.callback, file, data, data
/*  96 */           .position(), data.remaining()));
/*     */   }
/*     */   
/*     */   public SteamAPICall fileReadAsync(String file, int offset, int toRead) {
/* 100 */     return new SteamAPICall(SteamRemoteStorageNative.fileReadAsync(this.callback, file, offset, toRead));
/*     */   }
/*     */   
/*     */   public boolean fileReadAsyncComplete(SteamAPICall readCall, ByteBuffer buffer, int toRead) {
/* 104 */     return SteamRemoteStorageNative.fileReadAsyncComplete(readCall.handle, buffer, buffer.position(), toRead);
/*     */   }
/*     */   
/*     */   public boolean fileForget(String file) {
/* 108 */     return SteamRemoteStorageNative.fileForget(file);
/*     */   }
/*     */   
/*     */   public boolean fileDelete(String file) {
/* 112 */     return SteamRemoteStorageNative.fileDelete(file);
/*     */   }
/*     */   
/*     */   public SteamAPICall fileShare(String file) {
/* 116 */     return new SteamAPICall(SteamRemoteStorageNative.fileShare(this.callback, file));
/*     */   }
/*     */   
/*     */   public boolean setSyncPlatforms(String file, RemoteStoragePlatform remoteStoragePlatform) {
/* 120 */     return SteamRemoteStorageNative.setSyncPlatforms(file, remoteStoragePlatform.mask);
/*     */   }
/*     */   
/*     */   public SteamUGCFileWriteStreamHandle fileWriteStreamOpen(String name) {
/* 124 */     return new SteamUGCFileWriteStreamHandle(SteamRemoteStorageNative.fileWriteStreamOpen(name));
/*     */   }
/*     */   
/*     */   public boolean fileWriteStreamWriteChunk(SteamUGCFileWriteStreamHandle stream, ByteBuffer data) {
/* 128 */     return SteamRemoteStorageNative.fileWriteStreamWriteChunk(stream.handle, data, data
/* 129 */         .position(), data.remaining());
/*     */   }
/*     */   
/*     */   public boolean fileWriteStreamClose(SteamUGCFileWriteStreamHandle stream) {
/* 133 */     return SteamRemoteStorageNative.fileWriteStreamClose(stream.handle);
/*     */   }
/*     */   
/*     */   public boolean fileWriteStreamCancel(SteamUGCFileWriteStreamHandle stream) {
/* 137 */     return SteamRemoteStorageNative.fileWriteStreamCancel(stream.handle);
/*     */   }
/*     */   
/*     */   public boolean fileExists(String file) {
/* 141 */     return SteamRemoteStorageNative.fileExists(file);
/*     */   }
/*     */   
/*     */   public boolean filePersisted(String file) {
/* 145 */     return SteamRemoteStorageNative.filePersisted(file);
/*     */   }
/*     */   
/*     */   public int getFileSize(String file) {
/* 149 */     return SteamRemoteStorageNative.getFileSize(file);
/*     */   }
/*     */   
/*     */   public long getFileTimestamp(String file) {
/* 153 */     return SteamRemoteStorageNative.getFileTimestamp(file);
/*     */   }
/*     */   
/*     */   public RemoteStoragePlatform[] getSyncPlatforms(String file) {
/* 157 */     int mask = SteamRemoteStorageNative.getSyncPlatforms(file);
/* 158 */     return RemoteStoragePlatform.byMask(mask);
/*     */   }
/*     */   
/*     */   public int getFileCount() {
/* 162 */     return SteamRemoteStorageNative.getFileCount();
/*     */   }
/*     */   
/*     */   public String getFileNameAndSize(int index, int[] sizes) {
/* 166 */     return SteamRemoteStorageNative.getFileNameAndSize(index, sizes);
/*     */   }
/*     */   
/*     */   public boolean getQuota(long[] totalBytes, long[] availableBytes) {
/* 170 */     return SteamRemoteStorageNative.getQuota(totalBytes, availableBytes);
/*     */   }
/*     */   
/*     */   public boolean isCloudEnabledForAccount() {
/* 174 */     return SteamRemoteStorageNative.isCloudEnabledForAccount();
/*     */   }
/*     */   
/*     */   public boolean isCloudEnabledForApp() {
/* 178 */     return SteamRemoteStorageNative.isCloudEnabledForApp();
/*     */   }
/*     */   
/*     */   public void setCloudEnabledForApp(boolean enabled) {
/* 182 */     SteamRemoteStorageNative.setCloudEnabledForApp(enabled);
/*     */   }
/*     */   
/*     */   public SteamAPICall ugcDownload(SteamUGCHandle fileHandle, int priority) {
/* 186 */     return new SteamAPICall(SteamRemoteStorageNative.ugcDownload(this.callback, fileHandle.handle, priority));
/*     */   }
/*     */   
/*     */   public boolean getUGCDownloadProgress(SteamUGCHandle fileHandle, int[] bytesDownloaded, int[] bytesExpected) {
/* 190 */     return SteamRemoteStorageNative.getUGCDownloadProgress(fileHandle.handle, bytesDownloaded, bytesExpected);
/*     */   }
/*     */   
/*     */   public int ugcRead(SteamUGCHandle fileHandle, ByteBuffer buffer, int dataToRead, int offset, UGCReadAction action) {
/* 194 */     return SteamRemoteStorageNative.ugcRead(fileHandle.handle, buffer, buffer
/* 195 */         .position(), dataToRead, offset, action.ordinal());
/*     */   }
/*     */   
/*     */   public int getCachedUGCCount() {
/* 199 */     return SteamRemoteStorageNative.getCachedUGCCount();
/*     */   }
/*     */   
/*     */   public SteamUGCHandle getCachedUGCHandle(int cachedContent) {
/* 203 */     return new SteamUGCHandle(SteamRemoteStorageNative.getCachedUGCHandle(cachedContent));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SteamAPICall publishWorkshopFile(String file, String previewFile, int consumerAppID, String title, String description, PublishedFileVisibility visibility, String[] tags, WorkshopFileType workshopFileType) {
/* 211 */     return new SteamAPICall(SteamRemoteStorageNative.publishWorkshopFile(this.callback, file, previewFile, consumerAppID, title, description, visibility
/*     */           
/* 213 */           .ordinal(), tags, (tags != null) ? tags.length : 0, workshopFileType.ordinal()));
/*     */   }
/*     */   
/*     */   public SteamPublishedFileUpdateHandle createPublishedFileUpdateRequest(SteamPublishedFileID publishedFileID) {
/* 217 */     return new SteamPublishedFileUpdateHandle(
/* 218 */         SteamRemoteStorageNative.createPublishedFileUpdateRequest(publishedFileID.handle));
/*     */   }
/*     */   
/*     */   public boolean updatePublishedFileFile(SteamPublishedFileUpdateHandle updateHandle, String file) {
/* 222 */     return SteamRemoteStorageNative.updatePublishedFileFile(updateHandle.handle, file);
/*     */   }
/*     */   
/*     */   public boolean updatePublishedFilePreviewFile(SteamPublishedFileUpdateHandle updateHandle, String previewFile) {
/* 226 */     return SteamRemoteStorageNative.updatePublishedFilePreviewFile(updateHandle.handle, previewFile);
/*     */   }
/*     */   
/*     */   public boolean updatePublishedFileTitle(SteamPublishedFileUpdateHandle updateHandle, String title) {
/* 230 */     return SteamRemoteStorageNative.updatePublishedFileTitle(updateHandle.handle, title);
/*     */   }
/*     */   
/*     */   public boolean updatePublishedFileDescription(SteamPublishedFileUpdateHandle updateHandle, String description) {
/* 234 */     return SteamRemoteStorageNative.updatePublishedFileDescription(updateHandle.handle, description);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean updatePublishedFileVisibility(SteamPublishedFileUpdateHandle updateHandle, PublishedFileVisibility visibility) {
/* 240 */     return SteamRemoteStorageNative.updatePublishedFileVisibility(updateHandle.handle, visibility.ordinal());
/*     */   }
/*     */   
/*     */   public boolean updatePublishedFileTags(SteamPublishedFileUpdateHandle updateHandle, String[] tags) {
/* 244 */     return SteamRemoteStorageNative.updatePublishedFileTags(updateHandle.handle, tags, (tags != null) ? tags.length : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public SteamAPICall commitPublishedFileUpdate(SteamPublishedFileUpdateHandle updateHandle) {
/* 249 */     return new SteamAPICall(SteamRemoteStorageNative.commitPublishedFileUpdate(this.callback, updateHandle.handle));
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamRemoteStorage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */