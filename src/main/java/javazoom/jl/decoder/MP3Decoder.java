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
/*     */ public class MP3Decoder
/*     */ {
/*     */   private OutputBuffer output;
/*     */   private SynthesisFilter filter1;
/*     */   private SynthesisFilter filter2;
/*     */   private LayerIIIDecoder l3decoder;
/*     */   private LayerIIDecoder l2decoder;
/*     */   private LayerIDecoder l1decoder;
/*     */   private int outputFrequency;
/*     */   private int outputChannels;
/*     */   private boolean initialized;
/*     */   public static final int DECODER_ERROR = 512;
/*     */   public static final int UNKNOWN_ERROR = 512;
/*     */   public static final int UNSUPPORTED_LAYER = 513;
/*     */   public static final int ILLEGAL_SUBBAND_ALLOCATION = 514;
/*     */   
/*     */   public OutputBuffer decodeFrame(Header header, Bitstream stream) throws DecoderException {
/*  70 */     if (!this.initialized) initialize(header);
/*     */     
/*  72 */     int layer = header.layer();
/*     */     
/*  74 */     FrameDecoder decoder = retrieveDecoder(header, stream, layer);
/*     */     
/*  76 */     decoder.decodeFrame();
/*     */     
/*  78 */     return this.output;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutputBuffer(OutputBuffer out) {
/*  85 */     this.output = out;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOutputFrequency() {
/*  95 */     return this.outputFrequency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOutputChannels() {
/* 106 */     return this.outputChannels;
/*     */   }
/*     */   
/*     */   protected DecoderException newDecoderException(int errorcode) {
/* 110 */     return new DecoderException(errorcode, null);
/*     */   }
/*     */   
/*     */   protected DecoderException newDecoderException(int errorcode, Throwable throwable) {
/* 114 */     return new DecoderException(errorcode, throwable);
/*     */   }
/*     */   
/*     */   protected FrameDecoder retrieveDecoder(Header header, Bitstream stream, int layer) throws DecoderException {
/* 118 */     FrameDecoder decoder = null;
/*     */ 
/*     */ 
/*     */     
/* 122 */     switch (layer) {
/*     */       case 3:
/* 124 */         if (this.l3decoder == null) {
/* 125 */           this.l3decoder = new LayerIIIDecoder(stream, header, this.filter1, this.filter2, this.output, 0);
/*     */         }
/* 127 */         decoder = this.l3decoder;
/*     */         break;
/*     */       case 2:
/* 130 */         if (this.l2decoder == null) {
/* 131 */           this.l2decoder = new LayerIIDecoder();
/* 132 */           this.l2decoder.create(stream, header, this.filter1, this.filter2, this.output, 0);
/*     */         } 
/* 134 */         decoder = this.l2decoder;
/*     */         break;
/*     */       case 1:
/* 137 */         if (this.l1decoder == null) {
/* 138 */           this.l1decoder = new LayerIDecoder();
/* 139 */           this.l1decoder.create(stream, header, this.filter1, this.filter2, this.output, 0);
/*     */         } 
/* 141 */         decoder = this.l1decoder;
/*     */         break;
/*     */     } 
/*     */     
/* 145 */     if (decoder == null) throw newDecoderException(513, null);
/*     */     
/* 147 */     return decoder;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initialize(Header header) throws DecoderException {
/* 153 */     float scalefactor = 32700.0F;
/*     */     
/* 155 */     int mode = header.mode();
/* 156 */     header.layer();
/* 157 */     int channels = (mode == 3) ? 1 : 2;
/*     */ 
/*     */     
/* 160 */     if (this.output == null) throw new RuntimeException("Output buffer was not set.");
/*     */     
/* 162 */     this.filter1 = new SynthesisFilter(0, scalefactor, null);
/*     */ 
/*     */     
/* 165 */     if (channels == 2) this.filter2 = new SynthesisFilter(1, scalefactor, null);
/*     */     
/* 167 */     this.outputChannels = channels;
/* 168 */     this.outputFrequency = header.frequency();
/*     */     
/* 170 */     this.initialized = true;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\MP3Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */