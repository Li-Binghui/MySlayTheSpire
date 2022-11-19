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
/*     */ public class OutputChannels
/*     */ {
/*     */   public static final int BOTH_CHANNELS = 0;
/*     */   public static final int LEFT_CHANNEL = 1;
/*     */   public static final int RIGHT_CHANNEL = 2;
/*     */   public static final int DOWNMIX_CHANNELS = 3;
/*  46 */   public static final OutputChannels LEFT = new OutputChannels(1);
/*  47 */   public static final OutputChannels RIGHT = new OutputChannels(2);
/*  48 */   public static final OutputChannels BOTH = new OutputChannels(0);
/*  49 */   public static final OutputChannels DOWNMIX = new OutputChannels(3);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int outputChannels;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static OutputChannels fromInt(int code) {
/*  61 */     switch (code) {
/*     */       case 1:
/*  63 */         return LEFT;
/*     */       case 2:
/*  65 */         return RIGHT;
/*     */       case 0:
/*  67 */         return BOTH;
/*     */       case 3:
/*  69 */         return DOWNMIX;
/*     */     } 
/*  71 */     throw new IllegalArgumentException("Invalid channel code: " + code);
/*     */   }
/*     */ 
/*     */   
/*     */   private OutputChannels(int channels) {
/*  76 */     this.outputChannels = channels;
/*     */     
/*  78 */     if (channels < 0 || channels > 3) throw new IllegalArgumentException("channels");
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getChannelsOutputCode() {
/*  88 */     return this.outputChannels;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getChannelCount() {
/*  98 */     int count = (this.outputChannels == 0) ? 2 : 1;
/*  99 */     return count;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 103 */     boolean equals = false;
/*     */     
/* 105 */     if (o instanceof OutputChannels) {
/* 106 */       OutputChannels oc = (OutputChannels)o;
/* 107 */       equals = (oc.outputChannels == this.outputChannels);
/*     */     } 
/*     */     
/* 110 */     return equals;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 114 */     return this.outputChannels;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\OutputChannels.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */