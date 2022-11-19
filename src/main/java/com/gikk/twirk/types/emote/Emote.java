/*    */ package com.gikk.twirk.types.emote;
/*    */ 
/*    */ import com.gikk.twirk.enums.EMOTE_SIZE;
/*    */ import java.util.LinkedList;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Emote
/*    */ {
/*    */   int getEmoteID();
/*    */   
/*    */   LinkedList<EmoteIndices> getIndices();
/*    */   
/*    */   String getPattern();
/*    */   
/*    */   String getEmoteImageUrl(EMOTE_SIZE paramEMOTE_SIZE);
/*    */   
/*    */   public static class EmoteIndices
/*    */   {
/*    */     public final int beingIndex;
/*    */     public final int endIndex;
/*    */     
/*    */     public EmoteIndices(int begin, int end) {
/* 65 */       this.beingIndex = begin;
/* 66 */       this.endIndex = end;
/*    */     }
/*    */     
/*    */     public String toString() {
/* 70 */       return "(" + this.beingIndex + "," + this.endIndex + ")";
/*    */     }
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\emote\Emote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */