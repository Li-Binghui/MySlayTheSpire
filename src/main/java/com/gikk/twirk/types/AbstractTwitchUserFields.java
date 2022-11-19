/*     */ package com.gikk.twirk.types;
/*     */ 
/*     */

import com.gikk.twirk.enums.USER_TYPE;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.Userstate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractTwitchUserFields
/*     */ {
/*  14 */   private static final int[] DEFAULT_COLORS = new int[] { 16711680, 255, 65280, 11674146, 16744272, 10145074, 16729344, 3050327, 14329120, 13789470, 6266528, 2003199, 16738740, 9055202, 65407 };
/*     */   
/*     */   public String[] badges;
/*     */   
/*     */   public int bits;
/*     */   
/*     */   public String userName;
/*     */   
/*     */   public String displayName;
/*     */   public int color;
/*     */   public long userID;
/*     */   public int[] emoteSets;
/*     */   public boolean isOwner;
/*     */   public boolean isMod;
/*     */   public boolean isSub;
/*     */   public boolean isTurbo;
/*     */   public USER_TYPE userType;
/*     */   public Userstate userstate;
/*     */   public String rawLine;
/*     */   
/*     */   protected void parseUserProperties(TwitchMessage message) {
/*  35 */     String channelOwner = message.getTarget().substring(1);
/*  36 */     TagMap r = message.getTagMap();
/*     */ 
/*     */     
/*  39 */     String temp = message.getPrefix();
/*  40 */     String testLogin = r.getAsString("login");
/*  41 */     if (testLogin.isEmpty()) {
/*  42 */       this.userName = temp.contains("!") ? temp.substring(1, temp.indexOf("!")) : "";
/*     */     } else {
/*  44 */       this.userName = testLogin;
/*     */     } 
/*     */     
/*  47 */     temp = r.getAsString("display-name");
/*  48 */     this
/*  49 */       .displayName = temp.isEmpty() ? (Character.toUpperCase(this.userName.charAt(0)) + this.userName.substring(1)) : temp;
/*     */     
/*  51 */     temp = r.getAsString("badges");
/*  52 */     this.badges = temp.isEmpty() ? new String[0] : temp.split(",");
/*     */     
/*  54 */     this.isMod = r.getAsBoolean("mod");
/*  55 */     this.isSub = r.getAsBoolean("subscriber");
/*  56 */     this.isTurbo = r.getAsBoolean("turbo");
/*  57 */     this.userID = r.getAsLong("user-id");
/*  58 */     this.color = r.getAsInt("color");
/*  59 */     this.color = (this.color == -1) ? getDefaultColor() : this.color;
/*     */     
/*  61 */     this.emoteSets = parseEmoteSets(r.getAsString("emote-sets"));
/*  62 */     this.userType = parseUserType(r.getAsString("user-type"), this.displayName, channelOwner, (this.isSub || this.isTurbo));
/*     */     
/*  64 */     this.isOwner = (this.userType == USER_TYPE.OWNER);
/*  65 */     this.rawLine = message.getRaw();
/*     */   }
/*     */   
/*     */   private int[] parseEmoteSets(String emoteSet) {
/*  69 */     if (emoteSet.isEmpty()) {
/*  70 */       return new int[0];
/*     */     }
/*     */     
/*  73 */     String[] sets = emoteSet.split(",");
/*  74 */     int[] out = new int[sets.length];
/*     */     
/*  76 */     for (int i = 0; i < sets.length; i++) {
/*  77 */       out[i] = Integer.parseInt(sets[i]);
/*     */     }
/*     */     
/*  80 */     return out;
/*     */   }
/*     */   
/*     */   private USER_TYPE parseUserType(String userType, String sender, String channelOwner, boolean isSub) {
/*  84 */     if (sender.equalsIgnoreCase(channelOwner))
/*  85 */       return USER_TYPE.OWNER; 
/*  86 */     if (userType.equals("mod"))
/*  87 */       return USER_TYPE.MOD; 
/*  88 */     if (userType.equals("global_mod"))
/*  89 */       return USER_TYPE.GLOBAL_MOD; 
/*  90 */     if (userType.equals("admin"))
/*  91 */       return USER_TYPE.ADMIN; 
/*  92 */     if (userType.equals("staff"))
/*  93 */       return USER_TYPE.STAFF; 
/*  94 */     if (isSub) {
/*  95 */       return USER_TYPE.SUBSCRIBER;
/*     */     }
/*  97 */     return USER_TYPE.DEFAULT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getDefaultColor() {
/* 103 */     if (this.displayName.isEmpty()) {
/* 104 */       return DEFAULT_COLORS[(int)System.currentTimeMillis() % DEFAULT_COLORS.length];
/*     */     }
/*     */     
/* 107 */     int n = this.displayName.charAt(0) + this.displayName.charAt(this.displayName.length() - 1);
/* 108 */     return DEFAULT_COLORS[n % DEFAULT_COLORS.length];
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\AbstractTwitchUserFields.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */