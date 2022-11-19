/*    */ package net.arikia.dev.drpc;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class OSUtil
/*    */ {
/*    */   public static boolean isMac() {
/* 12 */     return System.getProperty("os.name").toLowerCase()
/* 13 */       .startsWith("mac");
/*    */   }
/*    */   
/*    */   public static boolean isWindows() {
/* 17 */     return System.getProperty("os.name").toLowerCase()
/* 18 */       .startsWith("win");
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\net\arikia\dev\drpc\OSUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */