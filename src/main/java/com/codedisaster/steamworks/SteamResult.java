/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum SteamResult
/*     */ {
/*   8 */   OK(1),
/*   9 */   Fail(2),
/*  10 */   NoConnection(3),
/*     */   
/*  12 */   InvalidPassword(5),
/*  13 */   LoggedInElsewhere(6),
/*  14 */   InvalidProtocolVer(7),
/*  15 */   InvalidParam(8),
/*  16 */   FileNotFound(9),
/*  17 */   Busy(10),
/*  18 */   InvalidState(11),
/*  19 */   InvalidName(12),
/*  20 */   InvalidEmail(13),
/*  21 */   DuplicateName(14),
/*  22 */   AccessDenied(15),
/*  23 */   Timeout(16),
/*  24 */   Banned(17),
/*  25 */   AccountNotFound(18),
/*  26 */   InvalidSteamID(19),
/*  27 */   ServiceUnavailable(20),
/*  28 */   NotLoggedOn(21),
/*  29 */   Pending(22),
/*  30 */   EncryptionFailure(23),
/*  31 */   InsufficientPrivilege(24),
/*  32 */   LimitExceeded(25),
/*  33 */   Revoked(26),
/*  34 */   Expired(27),
/*  35 */   AlreadyRedeemed(28),
/*  36 */   DuplicateRequest(29),
/*  37 */   AlreadyOwned(30),
/*  38 */   IPNotFound(31),
/*  39 */   PersistFailed(32),
/*  40 */   LockingFailed(33),
/*  41 */   LogonSessionReplaced(34),
/*  42 */   ConnectFailed(35),
/*  43 */   HandshakeFailed(36),
/*  44 */   IOFailure(37),
/*  45 */   RemoteDisconnect(38),
/*  46 */   ShoppingCartNotFound(39),
/*  47 */   Blocked(40),
/*  48 */   Ignored(41),
/*  49 */   NoMatch(42),
/*  50 */   AccountDisabled(43),
/*  51 */   ServiceReadOnly(44),
/*  52 */   AccountNotFeatured(45),
/*  53 */   AdministratorOK(46),
/*  54 */   ContentVersion(47),
/*  55 */   TryAnotherCM(48),
/*  56 */   PasswordRequiredToKickSession(49),
/*  57 */   AlreadyLoggedInElsewhere(50),
/*  58 */   Suspended(51),
/*  59 */   Cancelled(52),
/*  60 */   DataCorruption(53),
/*  61 */   DiskFull(54),
/*  62 */   RemoteCallFailed(55),
/*  63 */   PasswordUnset(56),
/*  64 */   ExternalAccountUnlinked(57),
/*  65 */   PSNTicketInvalid(58),
/*  66 */   ExternalAccountAlreadyLinked(59),
/*  67 */   RemoteFileConflict(60),
/*  68 */   IllegalPassword(61),
/*  69 */   SameAsPreviousValue(62),
/*  70 */   AccountLogonDenied(63),
/*  71 */   CannotUseOldPassword(64),
/*  72 */   InvalidLoginAuthCode(65),
/*  73 */   AccountLogonDeniedNoMail(66),
/*  74 */   HardwareNotCapableOfIPT(67),
/*  75 */   IPTInitError(68),
/*  76 */   ParentalControlRestricted(69),
/*  77 */   FacebookQueryError(70),
/*  78 */   ExpiredLoginAuthCode(71),
/*  79 */   IPLoginRestrictionFailed(72),
/*  80 */   AccountLockedDown(73),
/*  81 */   AccountLogonDeniedVerifiedEmailRequired(74),
/*  82 */   NoMatchingURL(75),
/*  83 */   BadResponse(76),
/*  84 */   RequirePasswordReEntry(77),
/*  85 */   ValueOutOfRange(78),
/*  86 */   UnexpectedError(79),
/*  87 */   Disabled(80),
/*  88 */   InvalidCEGSubmission(81),
/*  89 */   RestrictedDevice(82),
/*  90 */   RegionLocked(83),
/*  91 */   RateLimitExceeded(84),
/*  92 */   AccountLoginDeniedNeedTwoFactor(85),
/*  93 */   ItemDeleted(86),
/*  94 */   AccountLoginDeniedThrottle(87),
/*  95 */   TwoFactorCodeMismatch(88),
/*  96 */   TwoFactorActivationCodeMismatch(89),
/*  97 */   AccountAssociatedToMultiplePartners(90),
/*  98 */   NotModified(91),
/*  99 */   NoMobileDevice(92),
/* 100 */   TimeNotSynced(93),
/* 101 */   SmsCodeFailed(94),
/* 102 */   AccountLimitExceeded(95),
/* 103 */   AccountActivityLimitExceeded(96),
/* 104 */   PhoneActivityLimitExceeded(97),
/* 105 */   RefundToWallet(98),
/* 106 */   EmailSendFailure(99),
/* 107 */   NotSettled(100),
/* 108 */   NeedCaptcha(101),
/* 109 */   GSLTDenied(102),
/* 110 */   GSOwnerDenied(103),
/* 111 */   InvalidItemType(104),
/* 112 */   IPBanned(105),
/* 113 */   GLSTExpired(106),
/* 114 */   InsufficientFunds(107),
/* 115 */   TooManyPending(108),
/* 116 */   NoSiteLicensesFound(109),
/* 117 */   WGNetworkSendExceeded(110),
/* 118 */   AccountNotFriends(111),
/* 119 */   LimitedUserAccount(112),
/* 120 */   CantRemoveItem(113),
/* 121 */   AccountDeleted(114),
/* 122 */   ExistingUserCancelledLicense(115),
/* 123 */   CommunityCooldown(116),
/* 124 */   NoLauncherSpecified(117),
/* 125 */   MustAgreeToSSA(118),
/* 126 */   LauncherMigrated(119),
/* 127 */   SteamRealmMismatch(120),
/* 128 */   InvalidSignature(121),
/* 129 */   ParseFailure(122),
/* 130 */   NoVerifiedPhone(123),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   UnknownErrorCode_NotImplementedByAPI(0);
/*     */   
/*     */   private final int code;
/*     */   private static final SteamResult[] valuesLookupTable;
/*     */   
/*     */   SteamResult(int code) {
/* 141 */     this.code = code;
/*     */   }
/*     */   
/*     */   public static SteamResult byValue(int resultCode) {
/* 145 */     if (resultCode < valuesLookupTable.length) {
/* 146 */       return valuesLookupTable[resultCode];
/*     */     }
/* 148 */     return UnknownErrorCode_NotImplementedByAPI;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 153 */     SteamResult[] values = values();
/* 154 */     int maxResultCode = 0;
/*     */     
/* 156 */     for (SteamResult value : values) {
/* 157 */       maxResultCode = Math.max(maxResultCode, value.code);
/*     */     }
/*     */     
/* 160 */     valuesLookupTable = new SteamResult[maxResultCode + 1];
/*     */     
/* 162 */     for (SteamResult value : values)
/* 163 */       valuesLookupTable[value.code] = value; 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamResult.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */