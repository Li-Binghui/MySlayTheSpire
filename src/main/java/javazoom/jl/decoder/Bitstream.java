/*     */ package javazoom.jl.decoder;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.io.UnsupportedEncodingException;
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
/*     */ public final class Bitstream
/*     */ {
/*  50 */   static byte INITIAL_SYNC = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   static byte STRICT_SYNC = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int BUFFER_INT_SIZE = 433;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private final int[] framebuffer = new int[433];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int framesize;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private byte[] frame_bytes = new byte[1732];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int wordpointer;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int bitindex;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int syncword;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   private int header_pos = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private Float replayGainScale;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean single_ch_mode;
/*     */ 
/*     */   
/* 107 */   private final int[] bitmask = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071 };
/*     */ 
/*     */ 
/*     */   
/*     */   private final PushbackInputStream source;
/*     */ 
/*     */   
/* 114 */   private final Header header = new Header();
/*     */   
/* 116 */   private final byte[] syncbuf = new byte[4];
/*     */   
/* 118 */   private Crc16[] crc = new Crc16[1];
/*     */   
/* 120 */   private byte[] rawid3v2 = null; private boolean firstframe = true; public static final int BITSTREAM_ERROR = 256;
/*     */   public static final int UNKNOWN_ERROR = 256;
/*     */   public static final int UNKNOWN_SAMPLE_RATE = 257;
/*     */   public static final int STREAM_ERROR = 258;
/*     */   public static final int UNEXPECTED_EOF = 259;
/*     */   public static final int STREAM_EOF = 260;
/*     */   public static final int INVALIDFRAME = 261;
/*     */   public static final int BITSTREAM_LAST = 511;
/*     */   
/*     */   public Bitstream(InputStream in) {
/* 130 */     if (in == null) throw new NullPointerException("in"); 
/* 131 */     in = new BufferedInputStream(in);
/* 132 */     loadID3v2(in);
/* 133 */     this.firstframe = true;
/*     */     
/* 135 */     this.source = new PushbackInputStream(in, 1732);
/*     */     
/* 137 */     closeFrame();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int header_pos() {
/* 147 */     return this.header_pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadID3v2(InputStream in) {
/* 156 */     int size = -1;
/*     */ 
/*     */     
/* 159 */     try { in.mark(10);
/* 160 */       size = readID3v2Header(in);
/* 161 */       this.header_pos = size; }
/* 162 */     catch (IOException e)
/*     */     
/*     */     { 
/*     */       try {
/* 166 */         in.reset();
/* 167 */       } catch (IOException iOException) {} } finally { try { in.reset(); } catch (IOException e) {} }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 172 */       if (size > 0) {
/* 173 */         this.rawid3v2 = new byte[size];
/* 174 */         in.read(this.rawid3v2, 0, this.rawid3v2.length);
/* 175 */         parseID3v2Frames(this.rawid3v2);
/*     */       } 
/* 177 */     } catch (IOException e) {}
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
/*     */   private int readID3v2Header(InputStream in) throws IOException {
/* 189 */     byte[] id3header = new byte[4];
/* 190 */     int size = -10;
/* 191 */     in.read(id3header, 0, 3);
/*     */     
/* 193 */     if (id3header[0] == 73 && id3header[1] == 68 && id3header[2] == 51) {
/* 194 */       in.read(id3header, 0, 3);
/* 195 */       in.read(id3header, 0, 4);
/* 196 */       size = (id3header[0] << 21) + (id3header[1] << 14) + (id3header[2] << 7) + id3header[3];
/*     */     } 
/* 198 */     return size + 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getRawID3v2() {
/* 206 */     if (this.rawid3v2 == null) {
/* 207 */       return null;
/*     */     }
/* 209 */     ByteArrayInputStream bain = new ByteArrayInputStream(this.rawid3v2);
/* 210 */     return bain;
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseID3v2Frames(byte[] bframes) {
/* 215 */     if (bframes == null)
/* 216 */       return;  if (!"ID3".equals(new String(bframes, 0, 3)))
/* 217 */       return;  int v2version = bframes[3] & 0xFF;
/* 218 */     if (v2version < 2 || v2version > 4) {
/*     */       return;
/*     */     }
/*     */     try {
        /* 222 */
        Float replayGain = null, replayGainPeak = null;
        /*     */
        /* 224 */
        String value = null;
        int i;
        /* 225 */
        int size;
        for (i = 10; i < bframes.length && bframes[i] > 0; i += size) {
            /* 226 */
            if (v2version == 3 || v2version == 4) {
                /*     */
                /* 228 */
                String code = new String(bframes, i, 4);
                /* 229 */
                size = bframes[i + 4] << 24 & 0xFF000000 | bframes[i + 5] << 16 & 0xFF0000 | bframes[i + 6] << 8 & 0xFF00 | bframes[i + 7] & 0xFF;
                /*     */
                /* 231 */
                i += 10;
                /* 232 */
                if (code.equals("TXXX")) {
                    /* 233 */
                    value = parseText(bframes, i, size, 1);
                    /* 234 */
                    String[] values = value.split("\000");
                    /* 235 */
                    if (values.length == 2) {
                        /* 236 */
                        String name = values[0];
                        /* 237 */
                        value = values[1];
                        /* 238 */
                        if (name.equals("replaygain_track_peak"))
                            /* 239 */ {
                            replayGainPeak = Float.valueOf(Float.parseFloat(value));
                            /* 240 */
                            if (replayGain != null)
                                /* 241 */ break;
                        } else if (name.equals("replaygain_track_gain"))
                            /* 242 */ {
                            replayGain = Float.valueOf(Float.parseFloat(value.replace(" dB", "")) + 3.0F);
                            /* 243 */
                            if (replayGainPeak != null)
                                /*     */ break;
                        }
                        /*     */
                        /*     */
                    }
                    /*     */
                }
                /*     */
            } else {
                /* 249 */
                String scode = new String(bframes, i, 3);
                /* 250 */
                size = 0 + (bframes[i + 3] << 16) + (bframes[i + 4] << 8) + bframes[i + 5];
                /* 251 */
                i += 6;
                /* 252 */
                if (scode.equals("TXXX")) {
                    /* 253 */
                    value = parseText(bframes, i, size, 1);
                    /* 254 */
                    String[] values = value.split("\000");
                    /* 255 */
                    if (values.length == 2) {
                        /* 256 */
                        String name = values[0];
                        /* 257 */
                        value = values[1];
                        /* 258 */
                        if (name.equals("replaygain_track_peak"))
                            /* 259 */ {
                            replayGainPeak = Float.valueOf(Float.parseFloat(value));
                            /* 260 */
                            if (replayGain != null)
                                /* 261 */ break;
                        } else if (name.equals("replaygain_track_gain"))
                            /* 262 */ {
                            replayGain = Float.valueOf(Float.parseFloat(value.replace(" dB", "")) + 3.0F);
                            /* 263 */
                            if (replayGainPeak != null)
                                /*     */ break;
                        }
                        /*     */
                        /*     */
                    }
                    /*     */
                }
                /*     */
            }
            /* 269 */
        }
        if (replayGain != null && replayGainPeak != null) {
            /* 270 */
            this.replayGainScale = Float.valueOf((float) Math.pow(10.0D, (replayGain.floatValue() / 20.0F)));
            /*     */
            /* 272 */
            this.replayGainScale = Float.valueOf(Math.min(1.0F / replayGainPeak.floatValue(), this.replayGainScale.floatValue()));
            /*     */
        }
        /* 274 */
    } catch (RuntimeException ignored) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private String parseText(byte[] bframes, int offset, int size, int skip) {
/* 279 */     String value = null;
/*     */     try {
/* 281 */       String[] ENC_TYPES = { "ISO-8859-1", "UTF16", "UTF-16BE", "UTF-8" };
/* 282 */       value = new String(bframes, offset + skip, size - skip, ENC_TYPES[bframes[offset]]);
/* 283 */     } catch (UnsupportedEncodingException e) {}
/*     */     
/* 285 */     return value;
/*     */   }
/*     */   
/*     */   public Float getReplayGainScale() {
/* 289 */     return this.replayGainScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws BitstreamException {
/*     */     try {
/* 298 */       this.source.close();
/* 299 */     } catch (IOException ex) {
/* 300 */       throw newBitstreamException(258, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Header readFrame() throws BitstreamException {
/* 309 */     Header result = null;
/*     */     try {
/* 311 */       result = readNextFrame();
/*     */       
/* 313 */       if (this.firstframe == true) {
/* 314 */         result.parseVBR(this.frame_bytes);
/* 315 */         this.firstframe = false;
/*     */       } 
/* 317 */     } catch (BitstreamException ex) {
/* 318 */       if (ex.getErrorCode() == 261) {
/*     */ 
/*     */         
/*     */         try {
/* 322 */           closeFrame();
/* 323 */           result = readNextFrame();
/* 324 */         } catch (BitstreamException e) {
/* 325 */           if (e.getErrorCode() != 260)
/* 326 */             throw newBitstreamException(e.getErrorCode(), e); 
/*     */         } 
/* 328 */       } else if (ex.getErrorCode() != 260) {
/* 329 */         throw newBitstreamException(ex.getErrorCode(), ex);
/*     */       } 
/* 331 */     }  return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Header readNextFrame() throws BitstreamException {
/* 340 */     if (this.framesize == -1) nextFrame(); 
/* 341 */     return this.header;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void nextFrame() throws BitstreamException {
/* 350 */     this.header.read_header(this, this.crc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unreadFrame() throws BitstreamException {
/* 359 */     if (this.wordpointer == -1 && this.bitindex == -1 && this.framesize > 0) {
/* 360 */       try { this.source.unread(this.frame_bytes, 0, this.framesize); }
/* 361 */       catch (IOException ex)
/* 362 */       { throw newBitstreamException(258); }
/*     */     
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeFrame() {
/* 370 */     this.framesize = -1;
/* 371 */     this.wordpointer = -1;
/* 372 */     this.bitindex = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSyncCurrentPosition(int syncmode) throws BitstreamException {
/* 379 */     int read = readBytes(this.syncbuf, 0, 4);
/* 380 */     int headerstring = this.syncbuf[0] << 24 & 0xFF000000 | this.syncbuf[1] << 16 & 0xFF0000 | this.syncbuf[2] << 8 & 0xFF00 | this.syncbuf[3] << 0 & 0xFF;
/*     */ 
/*     */     
/*     */     try {
/* 384 */       this.source.unread(this.syncbuf, 0, read);
/* 385 */     } catch (IOException ex) {}
/*     */ 
/*     */     
/* 388 */     boolean sync = false;
/* 389 */     switch (read) {
/*     */       case 0:
/* 391 */         sync = true;
/*     */         break;
/*     */       case 4:
/* 394 */         sync = isSyncMark(headerstring, syncmode, this.syncword);
/*     */         break;
/*     */     } 
/*     */     
/* 398 */     return sync;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readBits(int n) {
/* 405 */     return get_bits(n);
/*     */   }
/*     */ 
/*     */   
/*     */   public int readCheckedBits(int n) {
/* 410 */     return get_bits(n);
/*     */   }
/*     */   
/*     */   protected BitstreamException newBitstreamException(int errorcode) {
/* 414 */     return new BitstreamException(errorcode, null);
/*     */   }
/*     */   
/*     */   protected BitstreamException newBitstreamException(int errorcode, Throwable throwable) {
/* 418 */     return new BitstreamException(errorcode, throwable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int syncHeader(byte syncmode) throws BitstreamException {
/*     */     boolean sync;
/* 430 */     int bytesRead = readBytes(this.syncbuf, 0, 3);
/*     */     
/* 432 */     if (bytesRead != 3) throw newBitstreamException(260, null);
/*     */     
/* 434 */     int headerstring = this.syncbuf[0] << 16 & 0xFF0000 | this.syncbuf[1] << 8 & 0xFF00 | this.syncbuf[2] << 0 & 0xFF;
/*     */     
/*     */     do {
/* 437 */       headerstring <<= 8;
/*     */       
/* 439 */       if (readBytes(this.syncbuf, 3, 1) != 1) throw newBitstreamException(260, null);
/*     */       
/* 441 */       headerstring |= this.syncbuf[3] & 0xFF;
/*     */       
/* 443 */       sync = isSyncMark(headerstring, syncmode, this.syncword);
/* 444 */     } while (!sync);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 449 */     return headerstring;
/*     */   }
/*     */   
/*     */   public boolean isSyncMark(int headerstring, int syncmode, int word) {
/* 453 */     boolean sync = false;
/*     */     
/* 455 */     if (syncmode == INITIAL_SYNC) {
/* 456 */       sync = ((headerstring & 0xFFE00000) == -2097152);
/*     */     } else {
/* 458 */       sync = ((headerstring & 0xFFF80C00) == word && (((headerstring & 0xC0) == 192)) == this.single_ch_mode);
/*     */     } 
/*     */     
/* 461 */     if (sync) sync = ((headerstring >>> 10 & 0x3) != 3);
/*     */     
/* 463 */     if (sync) sync = ((headerstring >>> 17 & 0x3) != 0);
/*     */     
/* 465 */     if (sync) sync = ((headerstring >>> 19 & 0x3) != 1);
/*     */     
/* 467 */     return sync;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int read_frame_data(int bytesize) throws BitstreamException {
/* 474 */     int numread = 0;
/* 475 */     numread = readFully(this.frame_bytes, 0, bytesize);
/* 476 */     this.framesize = bytesize;
/* 477 */     this.wordpointer = -1;
/* 478 */     this.bitindex = -1;
/* 479 */     return numread;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void parse_frame() throws BitstreamException {
/* 487 */     int b = 0;
/* 488 */     byte[] byteread = this.frame_bytes;
/* 489 */     int bytesize = this.framesize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int k;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 501 */     for (k = 0; k < bytesize; k += 4) {
/* 502 */       byte b0 = 0;
/* 503 */       byte b1 = 0;
/* 504 */       byte b2 = 0;
/* 505 */       byte b3 = 0;
/* 506 */       b0 = byteread[k];
/* 507 */       if (k + 1 < bytesize) b1 = byteread[k + 1]; 
/* 508 */       if (k + 2 < bytesize) b2 = byteread[k + 2]; 
/* 509 */       if (k + 3 < bytesize) b3 = byteread[k + 3]; 
/* 510 */       this.framebuffer[b++] = b0 << 24 & 0xFF000000 | b1 << 16 & 0xFF0000 | b2 << 8 & 0xFF00 | b3 & 0xFF;
/*     */     } 
/* 512 */     this.wordpointer = 0;
/* 513 */     this.bitindex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int get_bits(int number_of_bits) {
/* 521 */     int returnvalue = 0;
/* 522 */     int sum = this.bitindex + number_of_bits;
/*     */ 
/*     */ 
/*     */     
/* 526 */     if (this.wordpointer < 0) this.wordpointer = 0;
/*     */ 
/*     */     
/* 529 */     if (sum <= 32) {
/*     */       
/* 531 */       returnvalue = this.framebuffer[this.wordpointer] >>> 32 - sum & this.bitmask[number_of_bits];
/*     */       
/* 533 */       if ((this.bitindex += number_of_bits) == 32) {
/* 534 */         this.bitindex = 0;
/* 535 */         this.wordpointer++;
/*     */       } 
/* 537 */       return returnvalue;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 544 */     int Right = this.framebuffer[this.wordpointer] & 0xFFFF;
/* 545 */     this.wordpointer++;
/* 546 */     int Left = this.framebuffer[this.wordpointer] & 0xFFFF0000;
/* 547 */     returnvalue = Right << 16 & 0xFFFF0000 | Left >>> 16 & 0xFFFF;
/*     */     
/* 549 */     returnvalue >>>= 48 - sum;
/* 550 */     returnvalue &= this.bitmask[number_of_bits];
/* 551 */     this.bitindex = sum - 32;
/* 552 */     return returnvalue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void set_syncword(int syncword0) {
/* 559 */     this.syncword = syncword0 & 0xFFFFFF3F;
/* 560 */     this.single_ch_mode = ((syncword0 & 0xC0) == 192);
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
/*     */   private int readFully(byte[] b, int offs, int len) throws BitstreamException {
/* 573 */     int nRead = 0;
/*     */     try {
/* 575 */       while (len > 0) {
/* 576 */         int bytesread = this.source.read(b, offs, len);
/* 577 */         if (bytesread == -1) {
/* 578 */           while (len-- > 0) {
/* 579 */             b[offs++] = 0;
/*     */           }
/*     */           break;
/*     */         } 
/* 583 */         nRead += bytesread;
/* 584 */         offs += bytesread;
/* 585 */         len -= bytesread;
/*     */       } 
/* 587 */     } catch (IOException ex) {
/* 588 */       throw newBitstreamException(258, ex);
/*     */     } 
/* 590 */     return nRead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int readBytes(byte[] b, int offs, int len) throws BitstreamException {
/* 597 */     int totalBytesRead = 0;
/*     */     try {
/* 599 */       while (len > 0) {
/* 600 */         int bytesread = this.source.read(b, offs, len);
/* 601 */         if (bytesread == -1)
/* 602 */           break;  totalBytesRead += bytesread;
/* 603 */         offs += bytesread;
/* 604 */         len -= bytesread;
/*     */       } 
/* 606 */     } catch (IOException ex) {
/* 607 */       throw newBitstreamException(258, ex);
/*     */     } 
/* 609 */     return totalBytesRead;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\Bitstream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */