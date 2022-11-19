/*    */ package com.gikk.twirk.types.emote;
/*    */ 
/*    */

import com.gikk.twirk.enums.EMOTE_SIZE;

import java.util.LinkedList;
/*    */ 
/*    */ class EmoteImpl
/*    */   implements Emote
/*    */ {
/*    */   private static final String EMOTE_URL_BASE = "http://static-cdn.jtvnw.net/emoticons/v1/";
/*    */   private int emoteID;
/* 11 */   private final LinkedList<EmoteIndices> indices = new LinkedList<>();
/*    */   private String pattern;
/*    */   
/*    */   public EmoteImpl setEmoteID(int emoteID) {
/* 15 */     this.emoteID = emoteID;
/* 16 */     return this;
/*    */   }
/*    */   
/*    */   public EmoteImpl setPattern(String pattern) {
/* 20 */     this.pattern = pattern;
/* 21 */     return this;
/*    */   }
/*    */   
/*    */   public EmoteImpl addIndices(int begin, int end) {
/* 25 */     this.indices.add(new EmoteIndices(begin, end));
/* 26 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEmoteID() {
/* 31 */     return this.emoteID;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPattern() {
/* 36 */     return this.pattern;
/*    */   }
/*    */ 
/*    */   
/*    */   public LinkedList<EmoteIndices> getIndices() {
/* 41 */     return this.indices;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEmoteImageUrl(EMOTE_SIZE imageSize) {
/* 47 */     return "http://static-cdn.jtvnw.net/emoticons/v1/" + this.emoteID + imageSize.value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     StringBuilder out = new StringBuilder(this.emoteID + " " + ((this.pattern == null) ? "NULL" : this.pattern) + "[ ");
/*    */     
/* 55 */     for (EmoteIndices index : this.indices) {
/* 56 */       out.append(index.toString());
/*    */     }
/* 58 */     out.append(" ]");
/*    */ 
/*    */     
/* 61 */     return out.toString();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\emote\EmoteImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */