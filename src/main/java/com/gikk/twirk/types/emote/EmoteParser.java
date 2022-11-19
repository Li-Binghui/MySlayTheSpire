/*    */ package com.gikk.twirk.types.emote;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmoteParser
/*    */ {
/*    */   private static final String EMOTES_IDENTIFIER = "emotes=";
/*    */   
/*    */   public static List<Emote> parseEmotes(String content, String tag) {
/* 22 */     List<Emote> emotes = new LinkedList<>();
/*    */     
/* 24 */     int begin = tag.indexOf("emotes=");
/* 25 */     int end = tag.indexOf(';', begin);
/* 26 */     if (begin == -1 || begin + "emotes=".length() == end) {
/* 27 */       return emotes;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     String emotesString = tag.substring(begin + "emotes=".length(), end);
/*    */     
/* 41 */     EmoteImpl emote = new EmoteImpl();
/* 42 */     StringBuilder str = new StringBuilder();
/* 43 */     String emoteID = "", beginIndex = "";
/* 44 */     for (char c : emotesString.toCharArray()) {
/* 45 */       switch (c) {
/*    */         case ':':
/* 47 */           emoteID = str.toString();
/* 48 */           str.setLength(0);
/*    */           break;
/*    */         case '-':
/* 51 */           beginIndex = str.toString();
/* 52 */           str.setLength(0);
/*    */           break;
/*    */         case ',':
/* 55 */           addIndices(emote, beginIndex, str.toString());
/* 56 */           str.setLength(0);
/*    */           break;
/*    */         case '/':
/* 59 */           finalizeEmote(content, emotes, emote, emoteID, beginIndex, str.toString());
/* 60 */           emote = new EmoteImpl();
/* 61 */           str.setLength(0);
/*    */           break;
/*    */         default:
/* 64 */           str.append(c);
/*    */           break;
/*    */       } 
/*    */     } 
/* 68 */     finalizeEmote(content, emotes, emote, emoteID, beginIndex, str.toString());
/*    */     
/* 70 */     return emotes;
/*    */   }
/*    */   
/*    */   private static void finalizeEmote(String content, List<Emote> emotes, EmoteImpl emote, String emoteID, String beginIndex, String endIndex) {
/* 74 */     int begin = Integer.parseInt(beginIndex);
/* 75 */     int end = Integer.parseInt(endIndex) + 1;
/* 76 */     emote.addIndices(begin, end);
/*    */     
/* 78 */     emote.setEmoteID(Integer.parseInt(emoteID));
/* 79 */     emote.setPattern(content.substring(begin, end));
/* 80 */     emotes.add(emote);
/*    */   }
/*    */   
/*    */   private static void addIndices(EmoteImpl emote, String beginIndex, String endIndex) {
/* 84 */     int begin = Integer.parseInt(beginIndex);
/* 85 */     int end = Integer.parseInt(endIndex) + 1;
/* 86 */     emote.addIndices(begin, end);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\emote\EmoteParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */