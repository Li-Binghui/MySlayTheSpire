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
/*    */ public final class Crc16
/*    */ {
/* 28 */   private static short polynomial = -32763;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   private short crc = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void add_bits(int bitstring, int length) {
/* 42 */     int bitmask = 1 << length - 1;
/*    */     do {
/* 44 */       if (((((this.crc & 0x8000) == 0) ? 1 : 0) ^ (((bitstring & bitmask) == 0) ? 1 : 0)) != 0)
/* 45 */       { this.crc = (short)(this.crc << 1);
/* 46 */         this.crc = (short)(this.crc ^ polynomial); }
/*    */       else
/* 48 */       { this.crc = (short)(this.crc << 1); } 
/* 49 */     } while ((bitmask >>>= 1) != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short checksum() {
/* 56 */     short sum = this.crc;
/* 57 */     this.crc = -1;
/* 58 */     return sum;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\Crc16.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */