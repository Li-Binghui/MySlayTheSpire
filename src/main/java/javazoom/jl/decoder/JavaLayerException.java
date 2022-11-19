/*    */ package javazoom.jl.decoder;
/*    */ 
/*    */ import java.io.PrintStream;
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
/*    */ public class JavaLayerException
/*    */   extends Exception
/*    */ {
/*    */   private Throwable exception;
/*    */   
/*    */   public JavaLayerException() {}
/*    */   
/*    */   public JavaLayerException(String msg) {
/* 34 */     super(msg);
/*    */   }
/*    */   
/*    */   public JavaLayerException(String msg, Throwable t) {
/* 38 */     super(msg);
/* 39 */     this.exception = t;
/*    */   }
/*    */   
/*    */   public Throwable getException() {
/* 43 */     return this.exception;
/*    */   }
/*    */   
/*    */   public void printStackTrace() {
/* 47 */     printStackTrace(System.err);
/*    */   }
/*    */   
/*    */   public void printStackTrace(PrintStream ps) {
/* 51 */     if (this.exception == null) {
/* 52 */       super.printStackTrace(ps);
/*    */     } else {
/* 54 */       this.exception.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\JavaLayerException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */