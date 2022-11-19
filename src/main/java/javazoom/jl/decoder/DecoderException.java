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
/*    */ public class DecoderException
/*    */   extends JavaLayerException
/*    */ {
/* 23 */   private int errorcode = 512;
/*    */   
/*    */   public DecoderException(String msg, Throwable t) {
/* 26 */     super(msg, t);
/*    */   }
/*    */   
/*    */   public DecoderException(int errorcode, Throwable t) {
/* 30 */     this(getErrorString(errorcode), t);
/* 31 */     this.errorcode = errorcode;
/*    */   }
/*    */   
/*    */   public int getErrorCode() {
/* 35 */     return this.errorcode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getErrorString(int errorcode) {
/* 42 */     return "Decoder errorcode " + Integer.toHexString(errorcode);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\DecoderException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */