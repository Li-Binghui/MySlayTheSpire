package com.megacrit.cardcrawl.integrations.steam;

import com.codedisaster.steamworks.SteamPublishedFileID;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUGCCallback;
import com.codedisaster.steamworks.SteamUGCDetails;
import com.codedisaster.steamworks.SteamUGCQuery;

public class SteamUGC implements SteamUGCCallback {
  public void onUGCQueryCompleted(SteamUGCQuery query, int numResultsReturned, int totalMatchingResults, boolean isCachedData, SteamResult result) {}
  
  public void onSubscribeItem(SteamPublishedFileID publishedFileID, SteamResult result) {}
  
  public void onUnsubscribeItem(SteamPublishedFileID publishedFileID, SteamResult result) {}
  
  public void onRequestUGCDetails(SteamUGCDetails details, SteamResult result) {}
  
  public void onCreateItem(SteamPublishedFileID publishedFileID, boolean needsToAcceptWLA, SteamResult result) {}
  
  public void onSubmitItemUpdate(SteamPublishedFileID publishedFileID, boolean needsToAcceptWLA, SteamResult result) {}
  
  public void onDownloadItemResult(int appID, SteamPublishedFileID publishedFileID, SteamResult result) {}
  
  public void onUserFavoriteItemsListChanged(SteamPublishedFileID publishedFileID, boolean wasAddRequest, SteamResult result) {}
  
  public void onSetUserItemVote(SteamPublishedFileID publishedFileID, boolean voteUp, SteamResult result) {}
  
  public void onGetUserItemVote(SteamPublishedFileID publishedFileID, boolean votedUp, boolean votedDown, boolean voteSkipped, SteamResult result) {}
  
  public void onStartPlaytimeTracking(SteamResult result) {}
  
  public void onStopPlaytimeTracking(SteamResult result) {}
  
  public void onStopPlaytimeTrackingForAllItems(SteamResult result) {}
  
  public void onDeleteItem(SteamPublishedFileID publishedFileID, SteamResult result) {}
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\steam\SteamUGC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */