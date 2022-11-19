/*     */ package javazoom.jl.decoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class BitReserve
/*     */ {
/*     */   private static final int BUFSIZE = 32768;
/*     */   private static final int BUFSIZE_MASK = 32767;
/*     */   private int offset;
/*     */   private int totbit;
/*     */   private int buf_byte_idx;
/*  48 */   private final int[] buf = new int[32768];
/*     */ 
/*     */   
/*     */   BitReserve() {
/*  52 */     this.offset = 0;
/*  53 */     this.totbit = 0;
/*  54 */     this.buf_byte_idx = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hsstell() {
/*  61 */     return this.totbit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hgetbits(int N) {
/*  69 */     this.totbit += N;
/*     */     
/*  71 */     int val = 0;
/*     */     
/*  73 */     int pos = this.buf_byte_idx;
/*  74 */     if (pos + N < 32768) {
/*  75 */       while (N-- > 0) {
/*  76 */         val <<= 1;
/*  77 */         val |= (this.buf[pos++] != 0) ? 1 : 0;
/*     */       } 
/*     */     } else {
/*  80 */       while (N-- > 0) {
/*  81 */         val <<= 1;
/*  82 */         val |= (this.buf[pos] != 0) ? 1 : 0;
/*  83 */         pos = pos + 1 & 0x7FFF;
/*     */       } 
/*  85 */     }  this.buf_byte_idx = pos;
/*  86 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hget1bit() {
/* 102 */     this.totbit++;
/* 103 */     int val = this.buf[this.buf_byte_idx];
/* 104 */     this.buf_byte_idx = this.buf_byte_idx + 1 & 0x7FFF;
/* 105 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void hputbuf(int val) {
/* 130 */     int ofs = this.offset;
/* 131 */     this.buf[ofs++] = val & 0x80;
/* 132 */     this.buf[ofs++] = val & 0x40;
/* 133 */     this.buf[ofs++] = val & 0x20;
/* 134 */     this.buf[ofs++] = val & 0x10;
/* 135 */     this.buf[ofs++] = val & 0x8;
/* 136 */     this.buf[ofs++] = val & 0x4;
/* 137 */     this.buf[ofs++] = val & 0x2;
/* 138 */     this.buf[ofs++] = val & 0x1;
/*     */     
/* 140 */     if (ofs == 32768) {
/* 141 */       this.offset = 0;
/*     */     } else {
/* 143 */       this.offset = ofs;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rewindNbits(int N) {
/* 151 */     this.totbit -= N;
/* 152 */     this.buf_byte_idx -= N;
/* 153 */     if (this.buf_byte_idx < 0) this.buf_byte_idx += 32768;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void rewindNbytes(int N) {
/* 160 */     int bits = N << 3;
/* 161 */     this.totbit -= bits;
/* 162 */     this.buf_byte_idx -= bits;
/* 163 */     if (this.buf_byte_idx < 0) this.buf_byte_idx += 32768; 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\BitReserve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */