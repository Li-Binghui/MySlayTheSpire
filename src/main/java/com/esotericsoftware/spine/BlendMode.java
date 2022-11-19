/*    */ package com.esotericsoftware.spine;
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
/*    */ public enum BlendMode
/*    */ {
/* 37 */   normal(770, 1, 771),
/* 38 */   additive(770, 1, 1),
/* 39 */   multiply(774, 774, 771),
/* 40 */   screen(1, 1, 769);
/*    */   
/*    */   int source;
/*    */   int sourcePMA;
/*    */   
/*    */   BlendMode(int source, int sourcePremultipledAlpha, int dest) {
/* 46 */     this.source = source;
/* 47 */     this.sourcePMA = sourcePremultipledAlpha;
/* 48 */     this.dest = dest;
/*    */   }
/*    */   int dest; public static BlendMode[] values;
/*    */   public int getSource(boolean premultipliedAlpha) {
/* 52 */     return premultipliedAlpha ? this.sourcePMA : this.source;
/*    */   }
/*    */   
/*    */   public int getDest() {
/* 56 */     return this.dest;
/*    */   }
/*    */   static {
/* 59 */     values = values();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\BlendMode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */