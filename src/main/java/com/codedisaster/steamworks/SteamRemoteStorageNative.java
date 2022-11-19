package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

final class SteamRemoteStorageNative {
  static native long createCallback(SteamRemoteStorageCallbackAdapter paramSteamRemoteStorageCallbackAdapter);
  
  static native boolean fileWrite(String paramString, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  static native int fileRead(String paramString, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  static native long fileWriteAsync(long paramLong, String paramString, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  static native long fileReadAsync(long paramLong, String paramString, int paramInt1, int paramInt2);
  
  static native boolean fileReadAsyncComplete(long paramLong1, ByteBuffer paramByteBuffer, long paramLong2, int paramInt);
  
  static native boolean fileForget(String paramString);
  
  static native boolean fileDelete(String paramString);
  
  static native long fileShare(long paramLong, String paramString);
  
  static native boolean setSyncPlatforms(String paramString, int paramInt);
  
  static native long fileWriteStreamOpen(String paramString);
  
  static native boolean fileWriteStreamWriteChunk(long paramLong, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  static native boolean fileWriteStreamClose(long paramLong);
  
  static native boolean fileWriteStreamCancel(long paramLong);
  
  static native boolean fileExists(String paramString);
  
  static native boolean filePersisted(String paramString);
  
  static native int getFileSize(String paramString);
  
  static native long getFileTimestamp(String paramString);
  
  static native int getSyncPlatforms(String paramString);
  
  static native int getFileCount();
  
  static native String getFileNameAndSize(int paramInt, int[] paramArrayOfint);
  
  static native boolean getQuota(long[] paramArrayOflong1, long[] paramArrayOflong2);
  
  static native boolean isCloudEnabledForAccount();
  
  static native boolean isCloudEnabledForApp();
  
  static native void setCloudEnabledForApp(boolean paramBoolean);
  
  static native long ugcDownload(long paramLong1, long paramLong2, int paramInt);
  
  static native boolean getUGCDownloadProgress(long paramLong, int[] paramArrayOfint1, int[] paramArrayOfint2);
  
  static native int ugcRead(long paramLong, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  static native int getCachedUGCCount();
  
  static native long getCachedUGCHandle(int paramInt);
  
  static native long publishWorkshopFile(long paramLong, String paramString1, String paramString2, int paramInt1, String paramString3, String paramString4, int paramInt2, String[] paramArrayOfString, int paramInt3, int paramInt4);
  
  static native long createPublishedFileUpdateRequest(long paramLong);
  
  static native boolean updatePublishedFileFile(long paramLong, String paramString);
  
  static native boolean updatePublishedFilePreviewFile(long paramLong, String paramString);
  
  static native boolean updatePublishedFileTitle(long paramLong, String paramString);
  
  static native boolean updatePublishedFileDescription(long paramLong, String paramString);
  
  static native boolean updatePublishedFileVisibility(long paramLong, int paramInt);
  
  static native boolean updatePublishedFileTags(long paramLong, String[] paramArrayOfString, int paramInt);
  
  static native long commitPublishedFileUpdate(long paramLong1, long paramLong2);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamRemoteStorageNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */