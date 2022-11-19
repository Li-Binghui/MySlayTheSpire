/*    */ package javazoom.jl.decoder;
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
/*    */ public class BitstreamException
/*    */   extends JavaLayerException
/*    */ {
/* 35 */   private int errorcode = 256;
/*    */   
/*    */   public BitstreamException(String msg, Throwable t) {
/* 38 */     super(msg, t);
/*    */   }
/*    */   
/*    */   public BitstreamException(int errorcode, Throwable t) {
/* 42 */     this(getErrorString(errorcode), t);
/* 43 */     this.errorcode = errorcode;
/*    */   }
/*    */   
/*    */   public int getErrorCode() {
/* 47 */     return this.errorcode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getErrorString(int errorcode) {
/* 54 */     return "Bitstream errorcode " + Integer.toHexString(errorcode);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\BitstreamException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */