/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */

import java.util.Collection;
import java.util.EnumSet;
/*     */ 
/*     */ public class SteamUGC
/*     */   extends SteamInterface
/*     */ {
/*     */   public enum UserUGCList {
/*  10 */     Published,
/*  11 */     VotedOn,
/*  12 */     VotedUp,
/*  13 */     VotedDown,
/*  14 */     WillVoteLater,
/*  15 */     Favorited,
/*  16 */     Subscribed,
/*  17 */     UsedOrPlayed,
/*  18 */     Followed;
/*     */   }
/*     */   
/*     */   public enum MatchingUGCType {
/*  22 */     Items(0),
/*  23 */     ItemsMtx(1),
/*  24 */     ItemsReadyToUse(2),
/*  25 */     Collections(3),
/*  26 */     Artwork(4),
/*  27 */     Videos(5),
/*  28 */     Screenshots(6),
/*  29 */     AllGuides(7),
/*  30 */     WebGuides(8),
/*  31 */     IntegratedGuides(9),
/*  32 */     UsableInGame(10),
/*  33 */     ControllerBindings(11),
/*  34 */     GameManagedItems(12),
/*  35 */     All(-1);
/*     */     
/*     */     private final int value;
/*     */     
/*     */     MatchingUGCType(int value) {
/*  40 */       this.value = value;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum UserUGCListSortOrder {
/*  45 */     CreationOrderDesc,
/*  46 */     CreationOrderAsc,
/*  47 */     TitleAsc,
/*  48 */     LastUpdatedDesc,
/*  49 */     SubscriptionDateDesc,
/*  50 */     VoteScoreDesc,
/*  51 */     ForModeration;
/*     */   }
/*     */   
/*     */   public enum UGCQueryType {
/*  55 */     RankedByVote,
/*  56 */     RankedByPublicationDate,
/*  57 */     AcceptedForGameRankedByAcceptanceDate,
/*  58 */     RankedByTrend,
/*  59 */     FavoritedByFriendsRankedByPublicationDate,
/*  60 */     CreatedByFriendsRankedByPublicationDate,
/*  61 */     RankedByNumTimesReported,
/*  62 */     CreatedByFollowedUsersRankedByPublicationDate,
/*  63 */     NotYetRated,
/*  64 */     RankedByTotalVotesAsc,
/*  65 */     RankedByVotesUp,
/*  66 */     RankedByTextSearch,
/*  67 */     RankedByTotalUniqueSubscriptions,
/*  68 */     RankedByPlaytimeTrend,
/*  69 */     RankedByTotalPlaytime,
/*  70 */     RankedByAveragePlaytimeTrend,
/*  71 */     RankedByLifetimeAveragePlaytime,
/*  72 */     RankedByPlaytimeSessionsTrend,
/*  73 */     RankedByLifetimePlaytimeSessions;
/*     */   }
/*     */   
/*     */   public enum ItemUpdateStatus {
/*  77 */     Invalid,
/*  78 */     PreparingConfig,
/*  79 */     PreparingContent,
/*  80 */     UploadingContent,
/*  81 */     UploadingPreviewFile,
/*  82 */     CommittingChanges;
/*     */     
/*  84 */     private static final ItemUpdateStatus[] values = values();
/*     */     
/*     */     static ItemUpdateStatus byOrdinal(int value) {
/*  87 */       return values[value];
/*     */     }
/*     */     static {
/*     */     
/*     */     } }
/*     */   
/*     */   public static class ItemUpdateInfo { long bytesProcessed;
/*     */     long bytesTotal;
/*     */     
/*     */     public long getBytesProcessed() {
/*  97 */       return this.bytesProcessed;
/*     */     }
/*     */     
/*     */     public long getBytesTotal() {
/* 101 */       return this.bytesTotal;
/*     */     } }
/*     */ 
/*     */   
/*     */   public enum ItemState {
/* 106 */     None(0),
/* 107 */     Subscribed(1),
/* 108 */     LegacyItem(2),
/* 109 */     Installed(4),
/* 110 */     NeedsUpdate(8),
/* 111 */     Downloading(16),
/* 112 */     DownloadPending(32);
/*     */     
/*     */     private final int bits;
/* 115 */     private static final ItemState[] values = values(); static {
/*     */     
/*     */     } ItemState(int bits) {
/* 118 */       this.bits = bits;
/*     */     }
/*     */     
/*     */     static Collection<ItemState> fromBits(int bits) {
/* 122 */       EnumSet<ItemState> itemStates = EnumSet.noneOf(ItemState.class);
/*     */       
/* 124 */       for (ItemState itemState : values) {
/* 125 */         if ((bits & itemState.bits) == itemState.bits) {
/* 126 */           itemStates.add(itemState);
/*     */         }
/*     */       } 
/*     */       
/* 130 */       return itemStates;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum ItemStatistic {
/* 135 */     NumSubscriptions,
/* 136 */     NumFavorites,
/* 137 */     NumFollowers,
/* 138 */     NumUniqueSubscriptions,
/* 139 */     NumUniqueFavorites,
/* 140 */     NumUniqueFollowers,
/* 141 */     NumUniqueWebsiteViews,
/* 142 */     ReportScore,
/* 143 */     NumSecondsPlayed,
/* 144 */     NumPlaytimeSessions,
/* 145 */     NumComments,
/* 146 */     NumSecondsPlayedDuringTimePeriod,
/* 147 */     NumPlaytimeSessionsDuringTimePeriod;
/*     */   }
/*     */   
/*     */   public enum ItemPreviewType {
/* 151 */     Image(0),
/* 152 */     YouTubeVideo(1),
/* 153 */     Sketchfab(2),
/* 154 */     EnvironmentMap_HorizontalCross(3),
/* 155 */     EnvironmentMap_LatLong(4),
/* 156 */     ReservedMax(255),
/*     */     
/* 158 */     UnknownPreviewType_NotImplementedByAPI(-1);
/*     */     
/*     */     private final int value;
/* 161 */     private static final ItemPreviewType[] values = values(); static {
/*     */     
/*     */     } ItemPreviewType(int value) {
/* 164 */       this.value = value;
/*     */     }
/*     */     
/*     */     static ItemPreviewType byValue(int value) {
/* 168 */       for (ItemPreviewType type : values) {
/* 169 */         if (type.value == value) {
/* 170 */           return type;
/*     */         }
/*     */       } 
/* 173 */       return UnknownPreviewType_NotImplementedByAPI;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ItemInstallInfo
/*     */   {
/*     */     private String folder;
/*     */     private int sizeOnDisk;
/*     */     
/*     */     public String getFolder() {
/* 184 */       return this.folder;
/*     */     }
/*     */     
/*     */     public int getSizeOnDisk() {
/* 188 */       return this.sizeOnDisk;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ItemDownloadInfo
/*     */   {
/*     */     long bytesDownloaded;
/*     */     long bytesTotal;
/*     */     
/*     */     public long getBytesDownloaded() {
/* 198 */       return this.bytesDownloaded;
/*     */     }
/*     */     
/*     */     public long getBytesTotal() {
/* 202 */       return this.bytesTotal;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ItemAdditionalPreview
/*     */   {
/*     */     private String urlOrVideoID;
/*     */     private String originalFileName;
/*     */     private int previewType;
/*     */     
/*     */     public String getUrlOrVideoID() {
/* 214 */       return this.urlOrVideoID;
/*     */     }
/*     */     
/*     */     public String getOriginalFileName() {
/* 218 */       return this.originalFileName;
/*     */     }
/*     */     
/*     */     public ItemPreviewType getPreviewType() {
/* 222 */       return ItemPreviewType.byValue(this.previewType);
/*     */     }
/*     */   }
/*     */   
/*     */   public SteamUGC(SteamUGCCallback callback) {
/* 227 */     super(SteamUGCNative.createCallback(new SteamUGCCallbackAdapter(callback)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SteamUGCQuery createQueryUserUGCRequest(int accountID, UserUGCList listType, MatchingUGCType matchingType, UserUGCListSortOrder sortOrder, int creatorAppID, int consumerAppID, int page) {
/* 234 */     return new SteamUGCQuery(SteamUGCNative.createQueryUserUGCRequest(accountID, listType.ordinal(), matchingType
/* 235 */           .value, sortOrder.ordinal(), creatorAppID, consumerAppID, page));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SteamUGCQuery createQueryAllUGCRequest(UGCQueryType queryType, MatchingUGCType matchingType, int creatorAppID, int consumerAppID, int page) {
/* 241 */     return new SteamUGCQuery(SteamUGCNative.createQueryAllUGCRequest(queryType.ordinal(), matchingType.value, creatorAppID, consumerAppID, page));
/*     */   }
/*     */ 
/*     */   
/*     */   public SteamUGCQuery createQueryUGCDetailsRequest(SteamPublishedFileID publishedFileID) {
/* 246 */     long[] fileIDs = new long[1];
/* 247 */     fileIDs[0] = publishedFileID.handle;
/* 248 */     return new SteamUGCQuery(SteamUGCNative.createQueryUGCDetailsRequest(fileIDs, 1));
/*     */   }
/*     */   
/*     */   public SteamUGCQuery createQueryUGCDetailsRequest(Collection<SteamPublishedFileID> publishedFileIDs) {
/* 252 */     int size = publishedFileIDs.size();
/* 253 */     long[] fileIDs = new long[size];
/*     */     
/* 255 */     int index = 0;
/* 256 */     for (SteamPublishedFileID fileID : publishedFileIDs) {
/* 257 */       fileIDs[index++] = fileID.handle;
/*     */     }
/*     */     
/* 260 */     return new SteamUGCQuery(SteamUGCNative.createQueryUGCDetailsRequest(fileIDs, size));
/*     */   }
/*     */   
/*     */   public SteamAPICall sendQueryUGCRequest(SteamUGCQuery query) {
/* 264 */     return new SteamAPICall(SteamUGCNative.sendQueryUGCRequest(this.callback, query.handle));
/*     */   }
/*     */   
/*     */   public boolean getQueryUGCResult(SteamUGCQuery query, int index, SteamUGCDetails details) {
/* 268 */     return SteamUGCNative.getQueryUGCResult(query.handle, index, details);
/*     */   }
/*     */   
/*     */   public String getQueryUGCPreviewURL(SteamUGCQuery query, int index) {
/* 272 */     return SteamUGCNative.getQueryUGCPreviewURL(query.handle, index);
/*     */   }
/*     */   
/*     */   public String getQueryUGCMetadata(SteamUGCQuery query, int index) {
/* 276 */     return SteamUGCNative.getQueryUGCMetadata(query.handle, index);
/*     */   }
/*     */   
/*     */   public long getQueryUGCStatistic(SteamUGCQuery query, int index, ItemStatistic statType) {
/* 280 */     return SteamUGCNative.getQueryUGCStatistic(query.handle, index, statType.ordinal());
/*     */   }
/*     */   
/*     */   public int getQueryUGCNumAdditionalPreviews(SteamUGCQuery query, int index) {
/* 284 */     return SteamUGCNative.getQueryUGCNumAdditionalPreviews(query.handle, index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getQueryUGCAdditionalPreview(SteamUGCQuery query, int index, int previewIndex, ItemAdditionalPreview previewInfo) {
/* 290 */     return SteamUGCNative.getQueryUGCAdditionalPreview(query.handle, index, previewIndex, previewInfo);
/*     */   }
/*     */   
/*     */   public int getQueryUGCNumKeyValueTags(SteamUGCQuery query, int index) {
/* 294 */     return SteamUGCNative.getQueryUGCNumKeyValueTags(query.handle, index);
/*     */   }
/*     */   
/*     */   public boolean getQueryUGCKeyValueTag(SteamUGCQuery query, int index, int keyValueTagIndex, String[] keyAndValue) {
/* 298 */     return SteamUGCNative.getQueryUGCKeyValueTag(query.handle, index, keyValueTagIndex, keyAndValue);
/*     */   }
/*     */   
/*     */   public boolean releaseQueryUserUGCRequest(SteamUGCQuery query) {
/* 302 */     return SteamUGCNative.releaseQueryUserUGCRequest(query.handle);
/*     */   }
/*     */   
/*     */   public boolean addRequiredTag(SteamUGCQuery query, String tagName) {
/* 306 */     return SteamUGCNative.addRequiredTag(query.handle, tagName);
/*     */   }
/*     */   
/*     */   public boolean addExcludedTag(SteamUGCQuery query, String tagName) {
/* 310 */     return SteamUGCNative.addExcludedTag(query.handle, tagName);
/*     */   }
/*     */   
/*     */   public boolean setReturnOnlyIDs(SteamUGCQuery query, boolean returnOnlyIDs) {
/* 314 */     return SteamUGCNative.setReturnOnlyIDs(query.handle, returnOnlyIDs);
/*     */   }
/*     */   
/*     */   public boolean setReturnKeyValueTags(SteamUGCQuery query, boolean returnKeyValueTags) {
/* 318 */     return SteamUGCNative.setReturnKeyValueTags(query.handle, returnKeyValueTags);
/*     */   }
/*     */   
/*     */   public boolean setReturnLongDescription(SteamUGCQuery query, boolean returnLongDescription) {
/* 322 */     return SteamUGCNative.setReturnLongDescription(query.handle, returnLongDescription);
/*     */   }
/*     */   
/*     */   public boolean setReturnMetadata(SteamUGCQuery query, boolean returnMetadata) {
/* 326 */     return SteamUGCNative.setReturnMetadata(query.handle, returnMetadata);
/*     */   }
/*     */   
/*     */   public boolean setReturnChildren(SteamUGCQuery query, boolean returnChildren) {
/* 330 */     return SteamUGCNative.setReturnChildren(query.handle, returnChildren);
/*     */   }
/*     */   
/*     */   public boolean setReturnAdditionalPreviews(SteamUGCQuery query, boolean returnAdditionalPreviews) {
/* 334 */     return SteamUGCNative.setReturnAdditionalPreviews(query.handle, returnAdditionalPreviews);
/*     */   }
/*     */   
/*     */   public boolean setReturnTotalOnly(SteamUGCQuery query, boolean returnTotalOnly) {
/* 338 */     return SteamUGCNative.setReturnTotalOnly(query.handle, returnTotalOnly);
/*     */   }
/*     */   
/*     */   public boolean setReturnPlaytimeStats(SteamUGCQuery query, int days) {
/* 342 */     return SteamUGCNative.setReturnPlaytimeStats(query.handle, days);
/*     */   }
/*     */   
/*     */   public boolean setLanguage(SteamUGCQuery query, String language) {
/* 346 */     return SteamUGCNative.setLanguage(query.handle, language);
/*     */   }
/*     */   
/*     */   public boolean setAllowCachedResponse(SteamUGCQuery query, int maxAgeSeconds) {
/* 350 */     return SteamUGCNative.setAllowCachedResponse(query.handle, maxAgeSeconds);
/*     */   }
/*     */   
/*     */   public boolean setCloudFileNameFilter(SteamUGCQuery query, String matchCloudFileName) {
/* 354 */     return SteamUGCNative.setCloudFileNameFilter(query.handle, matchCloudFileName);
/*     */   }
/*     */   
/*     */   public boolean setMatchAnyTag(SteamUGCQuery query, boolean matchAnyTag) {
/* 358 */     return SteamUGCNative.setMatchAnyTag(query.handle, matchAnyTag);
/*     */   }
/*     */   
/*     */   public boolean setSearchText(SteamUGCQuery query, String searchText) {
/* 362 */     return SteamUGCNative.setSearchText(query.handle, searchText);
/*     */   }
/*     */   
/*     */   public boolean setRankedByTrendDays(SteamUGCQuery query, int days) {
/* 366 */     return SteamUGCNative.setRankedByTrendDays(query.handle, days);
/*     */   }
/*     */   
/*     */   public boolean addRequiredKeyValueTag(SteamUGCQuery query, String key, String value) {
/* 370 */     return SteamUGCNative.addRequiredKeyValueTag(query.handle, key, value);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public SteamAPICall requestUGCDetails(SteamPublishedFileID publishedFileID, int maxAgeSeconds) {
/* 375 */     return new SteamAPICall(SteamUGCNative.requestUGCDetails(this.callback, publishedFileID.handle, maxAgeSeconds));
/*     */   }
/*     */   
/*     */   public SteamAPICall createItem(int consumerAppID, SteamRemoteStorage.WorkshopFileType fileType) {
/* 379 */     return new SteamAPICall(SteamUGCNative.createItem(this.callback, consumerAppID, fileType.ordinal()));
/*     */   }
/*     */   
/*     */   public SteamUGCUpdateHandle startItemUpdate(int consumerAppID, SteamPublishedFileID publishedFileID) {
/* 383 */     return new SteamUGCUpdateHandle(SteamUGCNative.startItemUpdate(consumerAppID, publishedFileID.handle));
/*     */   }
/*     */   
/*     */   public boolean setItemTitle(SteamUGCUpdateHandle update, String title) {
/* 387 */     return SteamUGCNative.setItemTitle(update.handle, title);
/*     */   }
/*     */   
/*     */   public boolean setItemDescription(SteamUGCUpdateHandle update, String description) {
/* 391 */     return SteamUGCNative.setItemDescription(update.handle, description);
/*     */   }
/*     */   
/*     */   public boolean setItemUpdateLanguage(SteamUGCUpdateHandle update, String language) {
/* 395 */     return SteamUGCNative.setItemUpdateLanguage(update.handle, language);
/*     */   }
/*     */   
/*     */   public boolean setItemMetadata(SteamUGCUpdateHandle update, String metaData) {
/* 399 */     return SteamUGCNative.setItemMetadata(update.handle, metaData);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setItemVisibility(SteamUGCUpdateHandle update, SteamRemoteStorage.PublishedFileVisibility visibility) {
/* 405 */     return SteamUGCNative.setItemVisibility(update.handle, visibility.ordinal());
/*     */   }
/*     */   
/*     */   public boolean setItemTags(SteamUGCUpdateHandle update, String[] tags) {
/* 409 */     return SteamUGCNative.setItemTags(update.handle, tags, tags.length);
/*     */   }
/*     */   
/*     */   public boolean setItemContent(SteamUGCUpdateHandle update, String contentFolder) {
/* 413 */     return SteamUGCNative.setItemContent(update.handle, contentFolder);
/*     */   }
/*     */   
/*     */   public boolean setItemPreview(SteamUGCUpdateHandle update, String previewFile) {
/* 417 */     return SteamUGCNative.setItemPreview(update.handle, previewFile);
/*     */   }
/*     */   
/*     */   public boolean removeItemKeyValueTags(SteamUGCUpdateHandle update, String key) {
/* 421 */     return SteamUGCNative.removeItemKeyValueTags(update.handle, key);
/*     */   }
/*     */   
/*     */   public boolean addItemKeyValueTag(SteamUGCUpdateHandle update, String key, String value) {
/* 425 */     return SteamUGCNative.addItemKeyValueTag(update.handle, key, value);
/*     */   }
/*     */   
/*     */   public SteamAPICall submitItemUpdate(SteamUGCUpdateHandle update, String changeNote) {
/* 429 */     if (changeNote == null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 434 */       changeNote = "";
/*     */     }
/* 436 */     return new SteamAPICall(SteamUGCNative.submitItemUpdate(this.callback, update.handle, changeNote));
/*     */   }
/*     */   
/*     */   public ItemUpdateStatus getItemUpdateProgress(SteamUGCUpdateHandle update, ItemUpdateInfo updateInfo) {
/* 440 */     long[] values = new long[2];
/* 441 */     ItemUpdateStatus status = ItemUpdateStatus.byOrdinal(SteamUGCNative.getItemUpdateProgress(update.handle, values));
/* 442 */     updateInfo.bytesProcessed = values[0];
/* 443 */     updateInfo.bytesTotal = values[1];
/* 444 */     return status;
/*     */   }
/*     */   
/*     */   public SteamAPICall setUserItemVote(SteamPublishedFileID publishedFileID, boolean voteUp) {
/* 448 */     return new SteamAPICall(SteamUGCNative.setUserItemVote(this.callback, publishedFileID.handle, voteUp));
/*     */   }
/*     */   
/*     */   public SteamAPICall getUserItemVote(SteamPublishedFileID publishedFileID) {
/* 452 */     return new SteamAPICall(SteamUGCNative.getUserItemVote(this.callback, publishedFileID.handle));
/*     */   }
/*     */   
/*     */   public SteamAPICall addItemToFavorites(int appID, SteamPublishedFileID publishedFileID) {
/* 456 */     return new SteamAPICall(SteamUGCNative.addItemToFavorites(this.callback, appID, publishedFileID.handle));
/*     */   }
/*     */   
/*     */   public SteamAPICall removeItemFromFavorites(int appID, SteamPublishedFileID publishedFileID) {
/* 460 */     return new SteamAPICall(SteamUGCNative.removeItemFromFavorites(this.callback, appID, publishedFileID.handle));
/*     */   }
/*     */   
/*     */   public SteamAPICall subscribeItem(SteamPublishedFileID publishedFileID) {
/* 464 */     return new SteamAPICall(SteamUGCNative.subscribeItem(this.callback, publishedFileID.handle));
/*     */   }
/*     */   
/*     */   public SteamAPICall unsubscribeItem(SteamPublishedFileID publishedFileID) {
/* 468 */     return new SteamAPICall(SteamUGCNative.unsubscribeItem(this.callback, publishedFileID.handle));
/*     */   }
/*     */   
/*     */   public int getNumSubscribedItems() {
/* 472 */     return SteamUGCNative.getNumSubscribedItems();
/*     */   }
/*     */   
/*     */   public int getSubscribedItems(SteamPublishedFileID[] publishedFileIds) {
/* 476 */     long[] ids = new long[publishedFileIds.length];
/* 477 */     int nb = SteamUGCNative.getSubscribedItems(ids, publishedFileIds.length);
/*     */     
/* 479 */     for (int i = 0; i < nb; i++) {
/* 480 */       publishedFileIds[i] = new SteamPublishedFileID(ids[i]);
/*     */     }
/*     */     
/* 483 */     return nb;
/*     */   }
/*     */   
/*     */   public Collection<ItemState> getItemState(SteamPublishedFileID publishedFileID) {
/* 487 */     return ItemState.fromBits(SteamUGCNative.getItemState(publishedFileID.handle));
/*     */   }
/*     */   
/*     */   public boolean getItemInstallInfo(SteamPublishedFileID publishedFileID, ItemInstallInfo installInfo) {
/* 491 */     return SteamUGCNative.getItemInstallInfo(publishedFileID.handle, installInfo);
/*     */   }
/*     */   
/*     */   public boolean getItemDownloadInfo(SteamPublishedFileID publishedFileID, ItemDownloadInfo downloadInfo) {
/* 495 */     long[] values = new long[2];
/* 496 */     if (SteamUGCNative.getItemDownloadInfo(publishedFileID.handle, values)) {
/* 497 */       downloadInfo.bytesDownloaded = values[0];
/* 498 */       downloadInfo.bytesTotal = values[1];
/* 499 */       return true;
/*     */     } 
/* 501 */     return false;
/*     */   }
/*     */   
/*     */   public SteamAPICall deleteItem(SteamPublishedFileID publishedFileID) {
/* 505 */     return new SteamAPICall(SteamUGCNative.deleteItem(this.callback, publishedFileID.handle));
/*     */   }
/*     */   
/*     */   public boolean downloadItem(SteamPublishedFileID publishedFileID, boolean highPriority) {
/* 509 */     return SteamUGCNative.downloadItem(publishedFileID.handle, highPriority);
/*     */   }
/*     */   
/*     */   public boolean initWorkshopForGameServer(int workshopDepotID, String folder) {
/* 513 */     return SteamUGCNative.initWorkshopForGameServer(workshopDepotID, folder);
/*     */   }
/*     */   
/*     */   public void suspendDownloads(boolean suspend) {
/* 517 */     SteamUGCNative.suspendDownloads(suspend);
/*     */   }
/*     */   
/*     */   public SteamAPICall startPlaytimeTracking(SteamPublishedFileID[] publishedFileIDs) {
/* 521 */     long[] ids = new long[publishedFileIDs.length];
/*     */     
/* 523 */     for (int i = 0; i < ids.length; i++) {
/* 524 */       ids[i] = (publishedFileIDs[i]).handle;
/*     */     }
/*     */     
/* 527 */     return new SteamAPICall(SteamUGCNative.startPlaytimeTracking(this.callback, ids, ids.length));
/*     */   }
/*     */   
/*     */   public SteamAPICall stopPlaytimeTracking(SteamPublishedFileID[] publishedFileIDs) {
/* 531 */     long[] ids = new long[publishedFileIDs.length];
/*     */     
/* 533 */     for (int i = 0; i < ids.length; i++) {
/* 534 */       ids[i] = (publishedFileIDs[i]).handle;
/*     */     }
/*     */     
/* 537 */     return new SteamAPICall(SteamUGCNative.stopPlaytimeTracking(this.callback, ids, ids.length));
/*     */   }
/*     */   
/*     */   public SteamAPICall stopPlaytimeTrackingForAllItems() {
/* 541 */     return new SteamAPICall(SteamUGCNative.stopPlaytimeTrackingForAllItems(this.callback));
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUGC.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */