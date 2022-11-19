/*    */ package com.gikk.twirk.types.cheer;
/*    */ 
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CheerImpl
/*    */   implements Cheer
/*    */ {
/*    */   private static final String ROOT_URL = "static-cdn.jtvnw.net/bits/<theme>/<type>/<color>/<size>";
/*    */   private final int bits;
/*    */   private final String message;
/*    */   
/*    */   public CheerImpl(int bits, String message) {
/* 15 */     this.bits = bits;
/* 16 */     this.message = message;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBits() {
/* 21 */     return this.bits;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 26 */     return this.message;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 31 */     if (!(obj instanceof Cheer)) {
/* 32 */       return false;
/*    */     }
/* 34 */     Cheer other = (Cheer)obj;
/* 35 */     return (this.bits == other.getBits() && this.message.equals(other.getMessage()));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 40 */     int hash = 5;
/* 41 */     hash = 17 * hash + this.bits;
/* 42 */     hash = 17 * hash + Objects.hashCode(this.message);
/* 43 */     return hash;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getImageURL(CheerTheme theme, CheerType type, CheerSize size) {
/*    */     String color;
/* 49 */     if (this.bits < 100) {
/* 50 */       color = "gray";
/*    */     }
/* 52 */     else if (this.bits < 1000) {
/* 53 */       color = "purple";
/* 54 */     } else if (this.bits < 5000) {
/* 55 */       color = "green";
/* 56 */     } else if (this.bits < 10000) {
/* 57 */       color = "blue";
/*    */     } else {
/* 59 */       color = "red";
/*    */     } 
/* 61 */     StringBuilder b = new StringBuilder("static-cdn.jtvnw.net/bits/<theme>/<type>/<color>/<size>");
/* 62 */     b.append("/").append(theme.getValue())
/* 63 */       .append("/").append(type.getValue())
/* 64 */       .append("/").append(color)
/* 65 */       .append("/").append(size.getValue());
/* 66 */     return b.toString();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\cheer\CheerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */