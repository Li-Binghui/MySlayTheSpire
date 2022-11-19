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
/*     */ public final class Header
/*     */ {
/*  33 */   public static final int[][] frequencies = new int[][] { { 22050, 24000, 16000, 1 }, { 44100, 48000, 32000, 1 }, { 11025, 12000, 8000, 1 } };
/*     */   
/*     */   public static final int MPEG2_LSF = 0;
/*     */   
/*     */   public static final int MPEG25_LSF = 2;
/*     */   
/*     */   public static final int MPEG1 = 1;
/*     */   
/*     */   public static final int STEREO = 0;
/*     */   
/*     */   public static final int JOINT_STEREO = 1;
/*     */   
/*     */   public static final int DUAL_CHANNEL = 2;
/*     */   public static final int SINGLE_CHANNEL = 3;
/*     */   public static final int FOURTYFOUR_POINT_ONE = 0;
/*     */   public static final int FOURTYEIGHT = 1;
/*     */   public static final int THIRTYTWO = 2;
/*     */   private int h_layer;
/*     */   private int h_protection_bit;
/*     */   private int h_bitrate_index;
/*     */   private int h_padding_bit;
/*     */   private int h_mode_extension;
/*     */   private int h_version;
/*     */   private int h_mode;
/*     */   private int h_sample_frequency;
/*     */   private int h_number_of_subbands;
/*     */   private int h_intensity_stereo_bound;
/*     */   private boolean h_copyright;
/*     */   private boolean h_original;
/*  62 */   private double[] h_vbr_time_per_frame = new double[] { -1.0D, 384.0D, 1152.0D, 1152.0D };
/*     */   
/*     */   private boolean h_vbr;
/*     */   private int h_vbr_frames;
/*     */   private int h_vbr_scale;
/*     */   private int h_vbr_bytes;
/*     */   private byte[] h_vbr_toc;
/*  69 */   private byte syncmode = Bitstream.INITIAL_SYNC;
/*     */   
/*     */   private Crc16 crc;
/*     */   
/*     */   public short checksum;
/*     */   public int framesize;
/*     */   public int nSlots;
/*  76 */   private int _headerstring = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  82 */     StringBuffer buffer = new StringBuffer(200);
/*  83 */     buffer.append("Layer ");
/*  84 */     buffer.append(layer_string());
/*  85 */     buffer.append(" frame ");
/*  86 */     buffer.append(mode_string());
/*  87 */     buffer.append(' ');
/*  88 */     buffer.append(version_string());
/*  89 */     if (!checksums()) buffer.append(" no"); 
/*  90 */     buffer.append(" checksums");
/*  91 */     buffer.append(' ');
/*  92 */     buffer.append(sample_frequency_string());
/*  93 */     buffer.append(',');
/*  94 */     buffer.append(' ');
/*  95 */     buffer.append(bitrate_string());
/*     */     
/*  97 */     String s = buffer.toString();
/*  98 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void read_header(Bitstream stream, Crc16[] crcp) throws BitstreamException {
/* 107 */     boolean sync = false;
/*     */     while (true) {
/* 109 */       int headerstring = stream.syncHeader(this.syncmode);
/* 110 */       this._headerstring = headerstring;
/* 111 */       if (this.syncmode == Bitstream.INITIAL_SYNC) {
/* 112 */         this.h_version = headerstring >>> 19 & 0x1;
/* 113 */         if ((headerstring >>> 20 & 0x1) == 0)
/* 114 */           if (this.h_version == 0) {
/* 115 */             this.h_version = 2;
/*     */           } else {
/* 117 */             throw stream.newBitstreamException(256);
/* 118 */           }   if ((this.h_sample_frequency = headerstring >>> 10 & 0x3) == 3) throw stream.newBitstreamException(256); 
/*     */       } 
/* 120 */       this.h_layer = 4 - (headerstring >>> 17) & 0x3;
/* 121 */       this.h_protection_bit = headerstring >>> 16 & 0x1;
/* 122 */       this.h_bitrate_index = headerstring >>> 12 & 0xF;
/* 123 */       this.h_padding_bit = headerstring >>> 9 & 0x1;
/* 124 */       this.h_mode = headerstring >>> 6 & 0x3;
/* 125 */       this.h_mode_extension = headerstring >>> 4 & 0x3;
/* 126 */       if (this.h_mode == 1) {
/* 127 */         this.h_intensity_stereo_bound = (this.h_mode_extension << 2) + 4;
/*     */       } else {
/* 129 */         this.h_intensity_stereo_bound = 0;
/* 130 */       }  if ((headerstring >>> 3 & 0x1) == 1) this.h_copyright = true; 
/* 131 */       if ((headerstring >>> 2 & 0x1) == 1) this.h_original = true;
/*     */       
/* 133 */       if (this.h_layer == 1) {
/* 134 */         this.h_number_of_subbands = 32;
/*     */       } else {
/* 136 */         int channel_bitrate = this.h_bitrate_index;
/*     */         
/* 138 */         if (this.h_mode != 3) if (channel_bitrate == 4) {
/* 139 */             channel_bitrate = 1;
/*     */           } else {
/* 141 */             channel_bitrate -= 4;
/* 142 */           }   if (channel_bitrate == 1 || channel_bitrate == 2) {
/* 143 */           if (this.h_sample_frequency == 2)
/* 144 */           { this.h_number_of_subbands = 12; }
/*     */           else
/* 146 */           { this.h_number_of_subbands = 8; } 
/* 147 */         } else if (this.h_sample_frequency == 1 || (channel_bitrate >= 3 && channel_bitrate <= 5)) {
/* 148 */           this.h_number_of_subbands = 27;
/*     */         } else {
/* 150 */           this.h_number_of_subbands = 30;
/*     */         } 
/* 152 */       }  if (this.h_intensity_stereo_bound > this.h_number_of_subbands) this.h_intensity_stereo_bound = this.h_number_of_subbands;
/*     */       
/* 154 */       calculate_framesize();
/*     */       
/* 156 */       int framesizeloaded = stream.read_frame_data(this.framesize);
/* 157 */       if (this.framesize >= 0 && framesizeloaded != this.framesize)
/*     */       {
/* 159 */         throw stream.newBitstreamException(261); } 
/* 160 */       if (stream.isSyncCurrentPosition(this.syncmode)) {
/* 161 */         if (this.syncmode == Bitstream.INITIAL_SYNC) {
/* 162 */           this.syncmode = Bitstream.STRICT_SYNC;
/* 163 */           stream.set_syncword(headerstring & 0xFFF80CC0);
/*     */         } 
/* 165 */         sync = true;
/*     */       } else {
/* 167 */         stream.unreadFrame();
/* 168 */       }  if (sync) {
/* 169 */         stream.parse_frame();
/* 170 */         if (this.h_protection_bit == 0) {
/*     */           
/* 172 */           this.checksum = (short)stream.get_bits(16);
/* 173 */           if (this.crc == null) this.crc = new Crc16(); 
/* 174 */           this.crc.add_bits(headerstring, 16);
/* 175 */           crcp[0] = this.crc;
/*     */         } else {
/* 177 */           crcp[0] = null;
/* 178 */         }  if (this.h_sample_frequency == 0);
/*     */         return;
/*     */       } 
/*     */     } 
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
/*     */   void parseVBR(byte[] firstframe) throws BitstreamException {
/* 194 */     String xing = "Xing";
/* 195 */     byte[] tmp = new byte[4];
/* 196 */     int offset = 0;
/*     */     
/* 198 */     if (this.h_version == 1) {
/* 199 */       if (this.h_mode == 3)
/* 200 */       { offset = 17; }
/*     */       else
/* 202 */       { offset = 32; } 
/* 203 */     } else if (this.h_mode == 3) {
/* 204 */       offset = 9;
/*     */     } else {
/* 206 */       offset = 17;
/*     */     }  try {
/* 208 */       System.arraycopy(firstframe, offset, tmp, 0, 4);
/*     */       
/* 210 */       if (xing.equals(new String(tmp))) {
/*     */         
/* 212 */         this.h_vbr = true;
/* 213 */         this.h_vbr_frames = -1;
/* 214 */         this.h_vbr_bytes = -1;
/* 215 */         this.h_vbr_scale = -1;
/* 216 */         this.h_vbr_toc = new byte[100];
/*     */         
/* 218 */         int length = 4;
/*     */         
/* 220 */         byte[] flags = new byte[4];
/* 221 */         System.arraycopy(firstframe, offset + length, flags, 0, flags.length);
/* 222 */         length += flags.length;
/*     */         
/* 224 */         if ((flags[3] & 0x1) != 0) {
/* 225 */           System.arraycopy(firstframe, offset + length, tmp, 0, tmp.length);
/* 226 */           this.h_vbr_frames = tmp[0] << 24 & 0xFF000000 | tmp[1] << 16 & 0xFF0000 | tmp[2] << 8 & 0xFF00 | tmp[3] & 0xFF;
/*     */           
/* 228 */           length += 4;
/*     */         } 
/*     */         
/* 231 */         if ((flags[3] & 0x2) != 0) {
/* 232 */           System.arraycopy(firstframe, offset + length, tmp, 0, tmp.length);
/* 233 */           this.h_vbr_bytes = tmp[0] << 24 & 0xFF000000 | tmp[1] << 16 & 0xFF0000 | tmp[2] << 8 & 0xFF00 | tmp[3] & 0xFF;
/*     */           
/* 235 */           length += 4;
/*     */         } 
/*     */         
/* 238 */         if ((flags[3] & 0x4) != 0) {
/* 239 */           System.arraycopy(firstframe, offset + length, this.h_vbr_toc, 0, this.h_vbr_toc.length);
/* 240 */           length += this.h_vbr_toc.length;
/*     */         } 
/*     */         
/* 243 */         if ((flags[3] & 0x8) != 0) {
/* 244 */           System.arraycopy(firstframe, offset + length, tmp, 0, tmp.length);
/* 245 */           this.h_vbr_scale = tmp[0] << 24 & 0xFF000000 | tmp[1] << 16 & 0xFF0000 | tmp[2] << 8 & 0xFF00 | tmp[3] & 0xFF;
/*     */           
/* 247 */           length += 4;
/*     */         }
/*     */       
/*     */       } 
/* 251 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 252 */       throw new BitstreamException("XingVBRHeader Corrupted", e);
/*     */     } 
/*     */ 
/*     */     
/* 256 */     String vbri = "VBRI";
/* 257 */     offset = 32;
/*     */     try {
/* 259 */       System.arraycopy(firstframe, offset, tmp, 0, 4);
/*     */       
/* 261 */       if (vbri.equals(new String(tmp)))
/*     */       {
/* 263 */         this.h_vbr = true;
/* 264 */         this.h_vbr_frames = -1;
/* 265 */         this.h_vbr_bytes = -1;
/* 266 */         this.h_vbr_scale = -1;
/* 267 */         this.h_vbr_toc = new byte[100];
/*     */         
/* 269 */         int length = 10;
/* 270 */         System.arraycopy(firstframe, offset + length, tmp, 0, tmp.length);
/* 271 */         this.h_vbr_bytes = tmp[0] << 24 & 0xFF000000 | tmp[1] << 16 & 0xFF0000 | tmp[2] << 8 & 0xFF00 | tmp[3] & 0xFF;
/* 272 */         length += 4;
/*     */         
/* 274 */         System.arraycopy(firstframe, offset + length, tmp, 0, tmp.length);
/* 275 */         this.h_vbr_frames = tmp[0] << 24 & 0xFF000000 | tmp[1] << 16 & 0xFF0000 | tmp[2] << 8 & 0xFF00 | tmp[3] & 0xFF;
/* 276 */         length += 4;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 281 */     catch (ArrayIndexOutOfBoundsException e) {
/* 282 */       throw new BitstreamException("VBRIVBRHeader Corrupted", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int version() {
/* 291 */     return this.h_version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int layer() {
/* 298 */     return this.h_layer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int bitrate_index() {
/* 305 */     return this.h_bitrate_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int sample_frequency() {
/* 312 */     return this.h_sample_frequency;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int frequency() {
/* 319 */     return frequencies[this.h_version][this.h_sample_frequency];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int mode() {
/* 326 */     return this.h_mode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checksums() {
/* 333 */     if (this.h_protection_bit == 0) {
/* 334 */       return true;
/*     */     }
/* 336 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean copyright() {
/* 343 */     return this.h_copyright;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean original() {
/* 350 */     return this.h_original;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean vbr() {
/* 358 */     return this.h_vbr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int vbr_scale() {
/* 366 */     return this.h_vbr_scale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] vbr_toc() {
/* 374 */     return this.h_vbr_toc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checksum_ok() {
/* 381 */     return (this.checksum == this.crc.checksum());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean padding() {
/* 389 */     if (this.h_padding_bit == 0) {
/* 390 */       return false;
/*     */     }
/* 392 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int slots() {
/* 399 */     return this.nSlots;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int mode_extension() {
/* 406 */     return this.h_mode_extension;
/*     */   }
/*     */   
/* 409 */   private static final int[][][] bitrates = new int[][][] { { { 0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0 }, { 0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0 }, { 0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0 } }, { { 0, 32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000, 0 }, { 0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000, 0 }, { 0, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 0 } }, { { 0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0 }, { 0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0 }, { 0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0 } } };
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
/*     */   public int calculate_framesize() {
/* 442 */     if (this.h_layer == 1) {
/* 443 */       this.framesize = 12 * bitrates[this.h_version][0][this.h_bitrate_index] / frequencies[this.h_version][this.h_sample_frequency];
/* 444 */       if (this.h_padding_bit != 0) this.framesize++; 
/* 445 */       this.framesize <<= 2;
/* 446 */       this.nSlots = 0;
/*     */     } else {
/* 448 */       this.framesize = 144 * bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index] / frequencies[this.h_version][this.h_sample_frequency];
/* 449 */       if (this.h_version == 0 || this.h_version == 2) this.framesize >>= 1; 
/* 450 */       if (this.h_padding_bit != 0) this.framesize++;
/*     */       
/* 452 */       if (this.h_layer == 3) {
/* 453 */         if (this.h_version == 1) {
/* 454 */           this.nSlots = this.framesize - ((this.h_mode == 3) ? 17 : 32) - ((this.h_protection_bit != 0) ? 0 : 2) - 4;
/*     */         }
/*     */         else {
/*     */           
/* 458 */           this.nSlots = this.framesize - ((this.h_mode == 3) ? 9 : 17) - ((this.h_protection_bit != 0) ? 0 : 2) - 4;
/*     */         } 
/*     */       } else {
/*     */         
/* 462 */         this.nSlots = 0;
/*     */       } 
/* 464 */     }  this.framesize -= 4;
/* 465 */     return this.framesize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int max_number_of_frames(int streamsize) {
/* 475 */     if (this.h_vbr == true)
/* 476 */       return this.h_vbr_frames; 
/* 477 */     if (this.framesize + 4 - this.h_padding_bit == 0) {
/* 478 */       return 0;
/*     */     }
/* 480 */     return streamsize / (this.framesize + 4 - this.h_padding_bit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int min_number_of_frames(int streamsize) {
/* 490 */     if (this.h_vbr == true)
/* 491 */       return this.h_vbr_frames; 
/* 492 */     if (this.framesize + 5 - this.h_padding_bit == 0) {
/* 493 */       return 0;
/*     */     }
/* 495 */     return streamsize / (this.framesize + 5 - this.h_padding_bit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float ms_per_frame() {
/* 504 */     if (this.h_vbr == true) {
/* 505 */       double tpf = this.h_vbr_time_per_frame[layer()] / frequency();
/* 506 */       if (this.h_version == 0 || this.h_version == 2) tpf /= 2.0D; 
/* 507 */       return (float)(tpf * 1000.0D);
/*     */     } 
/* 509 */     float[][] ms_per_frame_array = { { 8.707483F, 8.0F, 12.0F }, { 26.12245F, 24.0F, 36.0F }, { 26.12245F, 24.0F, 36.0F } };
/* 510 */     return ms_per_frame_array[this.h_layer - 1][this.h_sample_frequency];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float total_ms(int streamsize) {
/* 521 */     return max_number_of_frames(streamsize) * ms_per_frame();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSyncHeader() {
/* 529 */     return this._headerstring;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String layer_string() {
/* 537 */     switch (this.h_layer) {
/*     */       case 1:
/* 539 */         return "I";
/*     */       case 2:
/* 541 */         return "II";
/*     */       case 3:
/* 543 */         return "III";
/*     */     } 
/* 545 */     return null;
/*     */   }
/*     */ 
/*     */   
/* 549 */   private static final String[][][] bitrate_str = new String[][][] { { { "free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "forbidden" }, { "free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden" }, { "free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden" } }, { { "free format", "32 kbit/s", "64 kbit/s", "96 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "288 kbit/s", "320 kbit/s", "352 kbit/s", "384 kbit/s", "416 kbit/s", "448 kbit/s", "forbidden" }, { "free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s", "384 kbit/s", "forbidden" }, { "free format", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s", "forbidden" } }, { { "free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "forbidden" }, { "free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden" }, { "free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden" } } };
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
/*     */   public String bitrate_string() {
/* 579 */     if (this.h_vbr == true) {
/* 580 */       return Integer.toString(bitrate() / 1000) + " kb/s";
/*     */     }
/* 582 */     return bitrate_str[this.h_version][this.h_layer - 1][this.h_bitrate_index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int bitrate() {
/* 590 */     if (this.h_vbr == true) {
/* 591 */       return (int)((this.h_vbr_bytes * 8) / ms_per_frame() * this.h_vbr_frames) * 1000;
/*     */     }
/* 593 */     return bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int bitrate_instant() {
/* 601 */     return bitrates[this.h_version][this.h_layer - 1][this.h_bitrate_index];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sample_frequency_string() {
/* 609 */     switch (this.h_sample_frequency) {
/*     */       case 2:
/* 611 */         if (this.h_version == 1)
/* 612 */           return "32 kHz"; 
/* 613 */         if (this.h_version == 0) {
/* 614 */           return "16 kHz";
/*     */         }
/*     */         
/* 617 */         return "8 kHz";
/*     */       case 0:
/* 619 */         if (this.h_version == 1)
/* 620 */           return "44.1 kHz"; 
/* 621 */         if (this.h_version == 0) {
/* 622 */           return "22.05 kHz";
/*     */         }
/*     */         
/* 625 */         return "11.025 kHz";
/*     */       case 1:
/* 627 */         if (this.h_version == 1)
/* 628 */           return "48 kHz"; 
/* 629 */         if (this.h_version == 0) {
/* 630 */           return "24 kHz";
/*     */         }
/*     */         
/* 633 */         return "12 kHz";
/*     */     } 
/* 635 */     return null;
/*     */   }
/*     */   
/*     */   public int getSampleRate() {
/* 639 */     switch (this.h_sample_frequency) {
/*     */       case 2:
/* 641 */         if (this.h_version == 1)
/* 642 */           return 32000; 
/* 643 */         if (this.h_version == 0) {
/* 644 */           return 16000;
/*     */         }
/*     */         
/* 647 */         return 8000;
/*     */       case 0:
/* 649 */         if (this.h_version == 1)
/* 650 */           return 44100; 
/* 651 */         if (this.h_version == 0) {
/* 652 */           return 22050;
/*     */         }
/*     */         
/* 655 */         return 11025;
/*     */       case 1:
/* 657 */         if (this.h_version == 1)
/* 658 */           return 48000; 
/* 659 */         if (this.h_version == 0) {
/* 660 */           return 24000;
/*     */         }
/*     */         
/* 663 */         return 12000;
/*     */     } 
/* 665 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String mode_string() {
/* 672 */     switch (this.h_mode) {
/*     */       case 0:
/* 674 */         return "Stereo";
/*     */       case 1:
/* 676 */         return "Joint stereo";
/*     */       case 2:
/* 678 */         return "Dual channel";
/*     */       case 3:
/* 680 */         return "Single channel";
/*     */     } 
/* 682 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String version_string() {
/* 690 */     switch (this.h_version) {
/*     */       case 1:
/* 692 */         return "MPEG-1";
/*     */       case 0:
/* 694 */         return "MPEG-2 LSF";
/*     */       case 2:
/* 696 */         return "MPEG-2.5 LSF";
/*     */     } 
/* 698 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int number_of_subbands() {
/* 706 */     return this.h_number_of_subbands;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int intensity_stereo_bound() {
/* 715 */     return this.h_intensity_stereo_bound;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\Header.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */