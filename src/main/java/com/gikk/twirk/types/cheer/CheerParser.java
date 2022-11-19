/*    */ package com.gikk.twirk.types.cheer;
/*    */ 
/*    */ import com.gikk.twirk.types.TagMap;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CheerParser
/*    */ {
/* 16 */   private static final Pattern PATTERN = Pattern.compile("([a-zA-Z]+([1-9][0-9]+\\b(?<=\\w)))");
/*    */   
/*    */   public static List<Cheer> parseCheer(TagMap tagMap, String content) {
/* 19 */     List<Cheer> list = new ArrayList<>();
/*    */     
/* 21 */     int bits = tagMap.getAsInt("bits");
/* 22 */     if (bits == -1) {
/* 23 */       return list;
/*    */     }
/*    */     
/* 26 */     Matcher matcher = PATTERN.matcher(content);
/* 27 */     int bitsFound = 0;
/* 28 */     while (matcher.find() && bitsFound < bits) {
/* 29 */       int bit = Integer.parseInt(matcher.group(2));
/* 30 */       bitsFound += bit;
/* 31 */       Cheer cheer = new CheerImpl(bit, matcher.group(1));
/* 32 */       list.add(cheer);
/*    */     } 
/*    */     
/* 35 */     return list;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\cheer\CheerParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */