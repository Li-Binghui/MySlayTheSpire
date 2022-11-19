package com.codedisaster.steamworks;

final class SteamUGCNative {
  static native long createCallback(SteamUGCCallbackAdapter paramSteamUGCCallbackAdapter);
  
  static native long createQueryUserUGCRequest(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
  
  static native long createQueryAllUGCRequest(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  static native long createQueryUGCDetailsRequest(long[] paramArrayOflong, int paramInt);
  
  static native long sendQueryUGCRequest(long paramLong1, long paramLong2);
  
  static native boolean getQueryUGCResult(long paramLong, int paramInt, SteamUGCDetails paramSteamUGCDetails);
  
  static native String getQueryUGCPreviewURL(long paramLong, int paramInt);
  
  static native String getQueryUGCMetadata(long paramLong, int paramInt);
  
  static native long getQueryUGCStatistic(long paramLong, int paramInt1, int paramInt2);
  
  static native int getQueryUGCNumAdditionalPreviews(long paramLong, int paramInt);
  
  static native boolean getQueryUGCAdditionalPreview(long paramLong, int paramInt1, int paramInt2, SteamUGC.ItemAdditionalPreview paramItemAdditionalPreview);
  
  static native int getQueryUGCNumKeyValueTags(long paramLong, int paramInt);
  
  static native boolean getQueryUGCKeyValueTag(long paramLong, int paramInt1, int paramInt2, String[] paramArrayOfString);
  
  static native boolean releaseQueryUserUGCRequest(long paramLong);
  
  static native boolean addRequiredTag(long paramLong, String paramString);
  
  static native boolean addExcludedTag(long paramLong, String paramString);
  
  static native boolean setReturnOnlyIDs(long paramLong, boolean paramBoolean);
  
  static native boolean setReturnKeyValueTags(long paramLong, boolean paramBoolean);
  
  static native boolean setReturnLongDescription(long paramLong, boolean paramBoolean);
  
  static native boolean setReturnMetadata(long paramLong, boolean paramBoolean);
  
  static native boolean setReturnChildren(long paramLong, boolean paramBoolean);
  
  static native boolean setReturnAdditionalPreviews(long paramLong, boolean paramBoolean);
  
  static native boolean setReturnTotalOnly(long paramLong, boolean paramBoolean);
  
  static native boolean setReturnPlaytimeStats(long paramLong, int paramInt);
  
  static native boolean setLanguage(long paramLong, String paramString);
  
  static native boolean setAllowCachedResponse(long paramLong, int paramInt);
  
  static native boolean setCloudFileNameFilter(long paramLong, String paramString);
  
  static native boolean setMatchAnyTag(long paramLong, boolean paramBoolean);
  
  static native boolean setSearchText(long paramLong, String paramString);
  
  static native boolean setRankedByTrendDays(long paramLong, int paramInt);
  
  static native boolean addRequiredKeyValueTag(long paramLong, String paramString1, String paramString2);
  
  static native long requestUGCDetails(long paramLong1, long paramLong2, int paramInt);
  
  static native long createItem(long paramLong, int paramInt1, int paramInt2);
  
  static native long startItemUpdate(int paramInt, long paramLong);
  
  static native boolean setItemTitle(long paramLong, String paramString);
  
  static native boolean setItemDescription(long paramLong, String paramString);
  
  static native boolean setItemUpdateLanguage(long paramLong, String paramString);
  
  static native boolean setItemMetadata(long paramLong, String paramString);
  
  static native boolean setItemVisibility(long paramLong, int paramInt);
  
  static native boolean setItemTags(long paramLong, String[] paramArrayOfString, int paramInt);
  
  static native boolean setItemContent(long paramLong, String paramString);
  
  static native boolean setItemPreview(long paramLong, String paramString);
  
  static native boolean removeItemKeyValueTags(long paramLong, String paramString);
  
  static native boolean addItemKeyValueTag(long paramLong, String paramString1, String paramString2);
  
  static native long submitItemUpdate(long paramLong1, long paramLong2, String paramString);
  
  static native int getItemUpdateProgress(long paramLong, long[] paramArrayOflong);
  
  static native long setUserItemVote(long paramLong1, long paramLong2, boolean paramBoolean);
  
  static native long getUserItemVote(long paramLong1, long paramLong2);
  
  static native long addItemToFavorites(long paramLong1, int paramInt, long paramLong2);
  
  static native long removeItemFromFavorites(long paramLong1, int paramInt, long paramLong2);
  
  static native long subscribeItem(long paramLong1, long paramLong2);
  
  static native long unsubscribeItem(long paramLong1, long paramLong2);
  
  static native int getNumSubscribedItems();
  
  static native int getSubscribedItems(long[] paramArrayOflong, int paramInt);
  
  static native int getItemState(long paramLong);
  
  static native boolean getItemInstallInfo(long paramLong, SteamUGC.ItemInstallInfo paramItemInstallInfo);
  
  static native boolean getItemDownloadInfo(long paramLong, long[] paramArrayOflong);
  
  static native long deleteItem(long paramLong1, long paramLong2);
  
  static native boolean downloadItem(long paramLong, boolean paramBoolean);
  
  static native boolean initWorkshopForGameServer(int paramInt, String paramString);
  
  static native void suspendDownloads(boolean paramBoolean);
  
  static native long startPlaytimeTracking(long paramLong, long[] paramArrayOflong, int paramInt);
  
  static native long stopPlaytimeTracking(long paramLong, long[] paramArrayOflong, int paramInt);
  
  static native long stopPlaytimeTrackingForAllItems(long paramLong);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUGCNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */