/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SteamAuth
/*    */ {
/*    */   public enum BeginAuthSessionResult
/*    */   {
/*  9 */     OK,
/* 10 */     InvalidTicket,
/* 11 */     DuplicateRequest,
/* 12 */     InvalidVersion,
/* 13 */     GameMismatch,
/* 14 */     ExpiredTicket;
/*    */     
/* 16 */     private static final BeginAuthSessionResult[] values = values();
/*    */     
/*    */     static BeginAuthSessionResult byOrdinal(int authSessionResponse) {
/* 19 */       return values[authSessionResponse];
/*    */     }
/*    */     static {
/*    */     
/*    */     } }
/* 24 */   public enum AuthSessionResponse { OK,
/* 25 */     UserNotConnectedToSteam,
/* 26 */     NoLicenseOrExpired,
/* 27 */     VACBanned,
/* 28 */     LoggedInElseWhere,
/* 29 */     VACCheckTimedOut,
/* 30 */     AuthTicketCanceled,
/* 31 */     AuthTicketInvalidAlreadyUsed,
/* 32 */     AuthTicketInvalid,
/* 33 */     PublisherIssuedBan;
/*    */     
/* 35 */     private static final AuthSessionResponse[] values = values(); static {
/*    */     
/*    */     } static AuthSessionResponse byOrdinal(int authSessionResponse) {
/* 38 */       return values[authSessionResponse];
/*    */     } }
/*    */ 
/*    */   
/*    */   public enum UserHasLicenseForAppResult {
/* 43 */     HasLicense,
/* 44 */     DoesNotHaveLicense,
/* 45 */     NoAuth;
/*    */     
/* 47 */     private static final UserHasLicenseForAppResult[] values = values(); static {
/*    */     
/*    */     } static UserHasLicenseForAppResult byOrdinal(int result) {
/* 50 */       return values[result];
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamAuth.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */