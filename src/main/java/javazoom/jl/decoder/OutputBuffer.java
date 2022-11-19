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
/*     */ public class OutputBuffer
/*     */ {
/*     */   public static final int BUFFERSIZE = 2304;
/*     */   private static final int MAXCHANNELS = 2;
/*     */   private Float replayGainScale;
/*     */   private int channels;
/*     */   private byte[] buffer;
/*     */   private int[] channelPointer;
/*     */   private boolean isBigEndian;
/*     */   
/*     */   public OutputBuffer(int channels, boolean isBigEndian) {
/*  45 */     this.channels = channels;
/*  46 */     this.isBigEndian = isBigEndian;
/*  47 */     this.buffer = new byte[2304 * channels];
/*  48 */     this.channelPointer = new int[channels];
/*  49 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void append(int channel, short value) {
/*     */     byte firstByte, secondByte;
/*  58 */     if (this.isBigEndian) {
/*  59 */       firstByte = (byte)(value >>> 8 & 0xFF);
/*  60 */       secondByte = (byte)(value & 0xFF);
/*     */     } else {
/*  62 */       firstByte = (byte)(value & 0xFF);
/*  63 */       secondByte = (byte)(value >>> 8 & 0xFF);
/*     */     } 
/*  65 */     this.buffer[this.channelPointer[channel]] = firstByte;
/*  66 */     this.buffer[this.channelPointer[channel] + 1] = secondByte;
/*  67 */     this.channelPointer[channel] = this.channelPointer[channel] + this.channels * 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendSamples(int channel, float[] f) {
/*  75 */     if (this.replayGainScale != null) {
/*  76 */       for (int i = 0; i < 32; ) {
/*  77 */         short s = clip(f[i++] * this.replayGainScale.floatValue());
/*  78 */         append(channel, s);
/*     */       } 
/*     */     } else {
/*  81 */       for (int i = 0; i < 32; ) {
/*  82 */         short s = clip(f[i++]);
/*  83 */         append(channel, s);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte[] getBuffer() {
/*  89 */     return this.buffer;
/*     */   }
/*     */   
/*     */   public int reset() {
/*     */     try {
/*  94 */       int index = this.channels - 1;
/*  95 */       return this.channelPointer[index] - index * 2;
/*     */     } finally {
/*     */       
/*  98 */       for (int i = 0; i < this.channels; i++)
/*  99 */         this.channelPointer[i] = i * 2; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setReplayGainScale(Float replayGainScale) {
/* 104 */     this.replayGainScale = replayGainScale;
/*     */   }
/*     */   
/*     */   public boolean isStereo() {
/* 108 */     return (this.channelPointer[1] == 2);
/*     */   }
/*     */ 
/*     */   
/*     */   private final short clip(float sample) {
/* 113 */     return (sample > 32767.0F) ? Short.MAX_VALUE : ((sample < -32768.0F) ? Short.MIN_VALUE : (short)(int)sample);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\OutputBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */