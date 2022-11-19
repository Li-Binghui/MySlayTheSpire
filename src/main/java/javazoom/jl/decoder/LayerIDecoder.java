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
/*     */ public class LayerIDecoder
/*     */   implements FrameDecoder
/*     */ {
/*     */   protected Bitstream stream;
/*     */   protected Header header;
/*     */   protected SynthesisFilter filter1;
/*     */   protected SynthesisFilter filter2;
/*     */   protected OutputBuffer buffer;
/*     */   protected int which_channels;
/*     */   protected int mode;
/*     */   protected int num_subbands;
/*     */   protected Subband[] subbands;
/*  35 */   protected Crc16 crc = null;
/*     */   
/*     */   public LayerIDecoder() {
/*  38 */     this.crc = new Crc16();
/*     */   }
/*     */ 
/*     */   
/*     */   public void create(Bitstream stream0, Header header0, SynthesisFilter filtera, SynthesisFilter filterb, OutputBuffer buffer0, int which_ch0) {
/*  43 */     this.stream = stream0;
/*  44 */     this.header = header0;
/*  45 */     this.filter1 = filtera;
/*  46 */     this.filter2 = filterb;
/*  47 */     this.buffer = buffer0;
/*  48 */     this.which_channels = which_ch0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void decodeFrame() throws DecoderException {
/*  54 */     this.num_subbands = this.header.number_of_subbands();
/*  55 */     this.subbands = new Subband[32];
/*  56 */     this.mode = this.header.mode();
/*     */     
/*  58 */     createSubbands();
/*     */     
/*  60 */     readAllocation();
/*  61 */     readScaleFactorSelection();
/*     */     
/*  63 */     if (this.crc != null || this.header.checksum_ok()) {
/*  64 */       readScaleFactors();
/*     */       
/*  66 */       readSampleData();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void createSubbands() {
/*  73 */     if (this.mode == 3) {
/*  74 */       for (int i = 0; i < this.num_subbands; i++)
/*  75 */         this.subbands[i] = new SubbandLayer1(i); 
/*  76 */     } else if (this.mode == 1) {
/*  77 */       int i; for (i = 0; i < this.header.intensity_stereo_bound(); i++)
/*  78 */         this.subbands[i] = new SubbandLayer1Stereo(i); 
/*  79 */       for (; i < this.num_subbands; i++)
/*  80 */         this.subbands[i] = new SubbandLayer1IntensityStereo(i); 
/*     */     } else {
/*  82 */       for (int i = 0; i < this.num_subbands; i++)
/*  83 */         this.subbands[i] = new SubbandLayer1Stereo(i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void readAllocation() throws DecoderException {
/*  88 */     for (int i = 0; i < this.num_subbands; i++) {
/*  89 */       this.subbands[i].read_allocation(this.stream, this.header, this.crc);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void readScaleFactorSelection() {}
/*     */ 
/*     */   
/*     */   protected void readScaleFactors() {
/*  98 */     for (int i = 0; i < this.num_subbands; i++)
/*  99 */       this.subbands[i].read_scalefactor(this.stream, this.header); 
/*     */   }
/*     */   
/*     */   protected void readSampleData() {
/* 103 */     boolean read_ready = false;
/* 104 */     boolean write_ready = false;
/* 105 */     int mode = this.header.mode();
/*     */     while (true) {
/*     */       int i;
/* 108 */       for (i = 0; i < this.num_subbands; i++)
/* 109 */         read_ready = this.subbands[i].read_sampledata(this.stream); 
/*     */       do {
/* 111 */         for (i = 0; i < this.num_subbands; i++) {
/* 112 */           write_ready = this.subbands[i].put_next_sample(this.which_channels, this.filter1, this.filter2);
/*     */         }
/* 114 */         this.filter1.calculate_pcm_samples(this.buffer);
/* 115 */         if (this.which_channels != 0 || mode == 3)
/* 116 */           continue;  this.filter2.calculate_pcm_samples(this.buffer);
/* 117 */       } while (!write_ready || 
/* 118 */         !read_ready);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   static final float[] scalefactors = new float[] { 2.0F, 1.587401F, 1.2599211F, 1.0F, 0.7937005F, 0.62996054F, 0.5F, 0.39685026F, 0.31498027F, 0.25F, 0.19842513F, 0.15749013F, 0.125F, 0.099212565F, 0.07874507F, 0.0625F, 0.049606282F, 0.039372534F, 0.03125F, 0.024803141F, 0.019686267F, 0.015625F, 0.012401571F, 0.009843133F, 0.0078125F, 0.0062007853F, 0.0049215667F, 0.00390625F, 0.0031003926F, 0.0024607833F, 0.001953125F, 0.0015501963F, 0.0012303917F, 9.765625E-4F, 7.7509816E-4F, 6.1519584E-4F, 4.8828125E-4F, 3.8754908E-4F, 3.0759792E-4F, 2.4414062E-4F, 1.9377454E-4F, 1.5379896E-4F, 1.2207031E-4F, 9.688727E-5F, 7.689948E-5F, 6.1035156E-5F, 4.8443635E-5F, 3.844974E-5F, 3.0517578E-5F, 2.4221818E-5F, 1.922487E-5F, 1.5258789E-5F, 1.2110909E-5F, 9.612435E-6F, 7.6293945E-6F, 6.0554544E-6F, 4.8062175E-6F, 3.8146973E-6F, 3.0277272E-6F, 2.4031087E-6F, 1.9073486E-6F, 1.5138636E-6F, 1.2015544E-6F, 0.0F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static abstract class Subband
/*     */   {
/*     */     public abstract void read_allocation(Bitstream param1Bitstream, Header param1Header, Crc16 param1Crc16) throws DecoderException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract void read_scalefactor(Bitstream param1Bitstream, Header param1Header);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract boolean read_sampledata(Bitstream param1Bitstream);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract boolean put_next_sample(int param1Int, SynthesisFilter param1SynthesisFilter1, SynthesisFilter param1SynthesisFilter2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class SubbandLayer1
/*     */     extends Subband
/*     */   {
/* 164 */     public static final float[] table_factor = new float[] { 0.0F, 0.6666667F, 0.2857143F, 0.13333334F, 0.06451613F, 0.031746034F, 0.015748031F, 0.007843138F, 0.0039138943F, 0.0019550342F, 9.770396E-4F, 4.884005E-4F, 2.4417043E-4F, 1.2207776E-4F, 6.103702E-5F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     public static final float[] table_offset = new float[] { 0.0F, -0.6666667F, -0.85714287F, -0.93333334F, -0.9677419F, -0.984127F, -0.992126F, -0.99607843F, -0.99804306F, -0.9990225F, -0.9995115F, -0.9997558F, -0.9998779F, -0.99993896F, -0.9999695F };
/*     */     
/*     */     protected int subbandnumber;
/*     */     
/*     */     protected int samplenumber;
/*     */     
/*     */     protected int allocation;
/*     */     
/*     */     protected float scalefactor;
/*     */     
/*     */     protected int samplelength;
/*     */     
/*     */     protected float sample;
/*     */     
/*     */     protected float factor;
/*     */     
/*     */     protected float offset;
/*     */ 
/*     */     
/*     */     public SubbandLayer1(int subbandnumber) {
/* 190 */       this.subbandnumber = subbandnumber;
/* 191 */       this.samplenumber = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void read_allocation(Bitstream stream, Header header, Crc16 crc) throws DecoderException {
/* 198 */       if ((this.allocation = stream.get_bits(4)) == 15) {
/* 199 */         throw new DecoderException(514, null);
/*     */       }
/*     */ 
/*     */       
/* 203 */       if (crc != null) crc.add_bits(this.allocation, 4); 
/* 204 */       if (this.allocation != 0) {
/* 205 */         this.samplelength = this.allocation + 1;
/* 206 */         this.factor = table_factor[this.allocation];
/* 207 */         this.offset = table_offset[this.allocation];
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void read_scalefactor(Bitstream stream, Header header) {
/* 215 */       if (this.allocation != 0) this.scalefactor = LayerIDecoder.scalefactors[stream.get_bits(6)];
/*     */     
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean read_sampledata(Bitstream stream) {
/* 222 */       if (this.allocation != 0) this.sample = stream.get_bits(this.samplelength); 
/* 223 */       if (++this.samplenumber == 12) {
/* 224 */         this.samplenumber = 0;
/* 225 */         return true;
/*     */       } 
/* 227 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean put_next_sample(int channels, SynthesisFilter filter1, SynthesisFilter filter2) {
/* 234 */       if (this.allocation != 0 && channels != 2) {
/* 235 */         float scaled_sample = (this.sample * this.factor + this.offset) * this.scalefactor;
/* 236 */         filter1.input_sample(scaled_sample, this.subbandnumber);
/*     */       } 
/* 238 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class SubbandLayer1IntensityStereo
/*     */     extends SubbandLayer1
/*     */   {
/*     */     protected float channel2_scalefactor;
/*     */ 
/*     */ 
/*     */     
/*     */     public SubbandLayer1IntensityStereo(int subbandnumber) {
/* 252 */       super(subbandnumber);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void read_allocation(Bitstream stream, Header header, Crc16 crc) throws DecoderException {
/* 259 */       super.read_allocation(stream, header, crc);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void read_scalefactor(Bitstream stream, Header header) {
/* 266 */       if (this.allocation != 0) {
/* 267 */         this.scalefactor = LayerIDecoder.scalefactors[stream.get_bits(6)];
/* 268 */         this.channel2_scalefactor = LayerIDecoder.scalefactors[stream.get_bits(6)];
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean read_sampledata(Bitstream stream) {
/* 276 */       return super.read_sampledata(stream);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean put_next_sample(int channels, SynthesisFilter filter1, SynthesisFilter filter2) {
/* 283 */       if (this.allocation != 0) {
/* 284 */         this.sample = this.sample * this.factor + this.offset;
/* 285 */         if (channels == 0) {
/* 286 */           float sample1 = this.sample * this.scalefactor, sample2 = this.sample * this.channel2_scalefactor;
/* 287 */           filter1.input_sample(sample1, this.subbandnumber);
/* 288 */           filter2.input_sample(sample2, this.subbandnumber);
/* 289 */         } else if (channels == 1) {
/* 290 */           float sample1 = this.sample * this.scalefactor;
/* 291 */           filter1.input_sample(sample1, this.subbandnumber);
/*     */         } else {
/* 293 */           float sample2 = this.sample * this.channel2_scalefactor;
/* 294 */           filter1.input_sample(sample2, this.subbandnumber);
/*     */         } 
/*     */       } 
/* 297 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class SubbandLayer1Stereo
/*     */     extends SubbandLayer1
/*     */   {
/*     */     protected int channel2_allocation;
/*     */     
/*     */     protected float channel2_scalefactor;
/*     */     
/*     */     protected int channel2_samplelength;
/*     */     protected float channel2_sample;
/*     */     protected float channel2_factor;
/*     */     protected float channel2_offset;
/*     */     
/*     */     public SubbandLayer1Stereo(int subbandnumber) {
/* 315 */       super(subbandnumber);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void read_allocation(Bitstream stream, Header header, Crc16 crc) throws DecoderException {
/* 322 */       this.allocation = stream.get_bits(4);
/* 323 */       this.channel2_allocation = stream.get_bits(4);
/* 324 */       if (crc != null) {
/* 325 */         crc.add_bits(this.allocation, 4);
/* 326 */         crc.add_bits(this.channel2_allocation, 4);
/*     */       } 
/* 328 */       if (this.allocation != 0) {
/* 329 */         this.samplelength = this.allocation + 1;
/* 330 */         this.factor = table_factor[this.allocation];
/* 331 */         this.offset = table_offset[this.allocation];
/*     */       } 
/* 333 */       if (this.channel2_allocation != 0) {
/* 334 */         this.channel2_samplelength = this.channel2_allocation + 1;
/* 335 */         this.channel2_factor = table_factor[this.channel2_allocation];
/* 336 */         this.channel2_offset = table_offset[this.channel2_allocation];
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void read_scalefactor(Bitstream stream, Header header) {
/* 344 */       if (this.allocation != 0) this.scalefactor = LayerIDecoder.scalefactors[stream.get_bits(6)]; 
/* 345 */       if (this.channel2_allocation != 0) this.channel2_scalefactor = LayerIDecoder.scalefactors[stream.get_bits(6)];
/*     */     
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean read_sampledata(Bitstream stream) {
/* 352 */       boolean returnvalue = super.read_sampledata(stream);
/* 353 */       if (this.channel2_allocation != 0) this.channel2_sample = stream.get_bits(this.channel2_samplelength); 
/* 354 */       return returnvalue;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean put_next_sample(int channels, SynthesisFilter filter1, SynthesisFilter filter2) {
/* 361 */       super.put_next_sample(channels, filter1, filter2);
/* 362 */       if (this.channel2_allocation != 0 && channels != 1) {
/* 363 */         float sample2 = (this.channel2_sample * this.channel2_factor + this.channel2_offset) * this.channel2_scalefactor;
/* 364 */         if (channels == 0) {
/* 365 */           filter2.input_sample(sample2, this.subbandnumber);
/*     */         } else {
/* 367 */           filter1.input_sample(sample2, this.subbandnumber);
/*     */         } 
/* 369 */       }  return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\LayerIDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */