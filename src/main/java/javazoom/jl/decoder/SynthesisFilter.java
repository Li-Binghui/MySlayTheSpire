/*      */ package javazoom.jl.decoder;
/*      */ 
/*      */

import java.io.*;
import java.lang.reflect.Array;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class SynthesisFilter
/*      */ {
/*      */   private float[] v1;
/*      */   private float[] v2;
/*      */   private float[] actual_v;
/*      */   private int actual_write_pos;
/*      */   private float[] samples;
/*      */   private int channel;
/*      */   private float scalefactor;
/*      */   
/*      */   public SynthesisFilter(int channelnumber, float factor, float[] eq0) {
/*   69 */     if (d == null) {
/*   70 */       d = load_d();
/*   71 */       d16 = splitArray(d, 16);
/*      */     } 
/*      */     
/*   74 */     this.v1 = new float[512];
/*   75 */     this.v2 = new float[512];
/*   76 */     this.samples = new float[32];
/*   77 */     this.channel = channelnumber;
/*   78 */     this.scalefactor = factor;
/*      */     
/*   80 */     reset();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reset() {
/*  100 */     for (int p = 0; p < 512; p++) {
/*  101 */       this.v2[p] = 0.0F; this.v1[p] = 0.0F;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  106 */     for (int p2 = 0; p2 < 32; p2++) {
/*  107 */       this.samples[p2] = 0.0F;
/*      */     }
/*  109 */     this.actual_v = this.v1;
/*  110 */     this.actual_write_pos = 15;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void input_sample(float sample, int subbandnumber) {
/*  117 */     this.samples[subbandnumber] = sample;
/*      */   }
/*      */   
/*      */   public void input_samples(float[] s) {
/*  121 */     for (int i = 31; i >= 0; i--) {
/*  122 */       this.samples[i] = s[i];
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void compute_new_v() {
/*  153 */     float[] s = this.samples;
/*      */     
/*  155 */     float s0 = s[0];
/*  156 */     float s1 = s[1];
/*  157 */     float s2 = s[2];
/*  158 */     float s3 = s[3];
/*  159 */     float s4 = s[4];
/*  160 */     float s5 = s[5];
/*  161 */     float s6 = s[6];
/*  162 */     float s7 = s[7];
/*  163 */     float s8 = s[8];
/*  164 */     float s9 = s[9];
/*  165 */     float s10 = s[10];
/*  166 */     float s11 = s[11];
/*  167 */     float s12 = s[12];
/*  168 */     float s13 = s[13];
/*  169 */     float s14 = s[14];
/*  170 */     float s15 = s[15];
/*  171 */     float s16 = s[16];
/*  172 */     float s17 = s[17];
/*  173 */     float s18 = s[18];
/*  174 */     float s19 = s[19];
/*  175 */     float s20 = s[20];
/*  176 */     float s21 = s[21];
/*  177 */     float s22 = s[22];
/*  178 */     float s23 = s[23];
/*  179 */     float s24 = s[24];
/*  180 */     float s25 = s[25];
/*  181 */     float s26 = s[26];
/*  182 */     float s27 = s[27];
/*  183 */     float s28 = s[28];
/*  184 */     float s29 = s[29];
/*  185 */     float s30 = s[30];
/*  186 */     float s31 = s[31];
/*      */     
/*  188 */     float p0 = s0 + s31;
/*  189 */     float p1 = s1 + s30;
/*  190 */     float p2 = s2 + s29;
/*  191 */     float p3 = s3 + s28;
/*  192 */     float p4 = s4 + s27;
/*  193 */     float p5 = s5 + s26;
/*  194 */     float p6 = s6 + s25;
/*  195 */     float p7 = s7 + s24;
/*  196 */     float p8 = s8 + s23;
/*  197 */     float p9 = s9 + s22;
/*  198 */     float p10 = s10 + s21;
/*  199 */     float p11 = s11 + s20;
/*  200 */     float p12 = s12 + s19;
/*  201 */     float p13 = s13 + s18;
/*  202 */     float p14 = s14 + s17;
/*  203 */     float p15 = s15 + s16;
/*      */     
/*  205 */     float pp0 = p0 + p15;
/*  206 */     float pp1 = p1 + p14;
/*  207 */     float pp2 = p2 + p13;
/*  208 */     float pp3 = p3 + p12;
/*  209 */     float pp4 = p4 + p11;
/*  210 */     float pp5 = p5 + p10;
/*  211 */     float pp6 = p6 + p9;
/*  212 */     float pp7 = p7 + p8;
/*  213 */     float pp8 = (p0 - p15) * cos1_32;
/*  214 */     float pp9 = (p1 - p14) * cos3_32;
/*  215 */     float pp10 = (p2 - p13) * cos5_32;
/*  216 */     float pp11 = (p3 - p12) * cos7_32;
/*  217 */     float pp12 = (p4 - p11) * cos9_32;
/*  218 */     float pp13 = (p5 - p10) * cos11_32;
/*  219 */     float pp14 = (p6 - p9) * cos13_32;
/*  220 */     float pp15 = (p7 - p8) * cos15_32;
/*      */     
/*  222 */     p0 = pp0 + pp7;
/*  223 */     p1 = pp1 + pp6;
/*  224 */     p2 = pp2 + pp5;
/*  225 */     p3 = pp3 + pp4;
/*  226 */     p4 = (pp0 - pp7) * cos1_16;
/*  227 */     p5 = (pp1 - pp6) * cos3_16;
/*  228 */     p6 = (pp2 - pp5) * cos5_16;
/*  229 */     p7 = (pp3 - pp4) * cos7_16;
/*  230 */     p8 = pp8 + pp15;
/*  231 */     p9 = pp9 + pp14;
/*  232 */     p10 = pp10 + pp13;
/*  233 */     p11 = pp11 + pp12;
/*  234 */     p12 = (pp8 - pp15) * cos1_16;
/*  235 */     p13 = (pp9 - pp14) * cos3_16;
/*  236 */     p14 = (pp10 - pp13) * cos5_16;
/*  237 */     p15 = (pp11 - pp12) * cos7_16;
/*      */     
/*  239 */     pp0 = p0 + p3;
/*  240 */     pp1 = p1 + p2;
/*  241 */     pp2 = (p0 - p3) * cos1_8;
/*  242 */     pp3 = (p1 - p2) * cos3_8;
/*  243 */     pp4 = p4 + p7;
/*  244 */     pp5 = p5 + p6;
/*  245 */     pp6 = (p4 - p7) * cos1_8;
/*  246 */     pp7 = (p5 - p6) * cos3_8;
/*  247 */     pp8 = p8 + p11;
/*  248 */     pp9 = p9 + p10;
/*  249 */     pp10 = (p8 - p11) * cos1_8;
/*  250 */     pp11 = (p9 - p10) * cos3_8;
/*  251 */     pp12 = p12 + p15;
/*  252 */     pp13 = p13 + p14;
/*  253 */     pp14 = (p12 - p15) * cos1_8;
/*  254 */     pp15 = (p13 - p14) * cos3_8;
/*      */     
/*  256 */     p0 = pp0 + pp1;
/*  257 */     p1 = (pp0 - pp1) * cos1_4;
/*  258 */     p2 = pp2 + pp3;
/*  259 */     p3 = (pp2 - pp3) * cos1_4;
/*  260 */     p4 = pp4 + pp5;
/*  261 */     p5 = (pp4 - pp5) * cos1_4;
/*  262 */     p6 = pp6 + pp7;
/*  263 */     p7 = (pp6 - pp7) * cos1_4;
/*  264 */     p8 = pp8 + pp9;
/*  265 */     p9 = (pp8 - pp9) * cos1_4;
/*  266 */     p10 = pp10 + pp11;
/*  267 */     p11 = (pp10 - pp11) * cos1_4;
/*  268 */     p12 = pp12 + pp13;
/*  269 */     p13 = (pp12 - pp13) * cos1_4;
/*  270 */     p14 = pp14 + pp15;
/*  271 */     p15 = (pp14 - pp15) * cos1_4;
/*      */ 
/*      */ 
/*      */     
/*  275 */     float new_v4, new_v12, new_v19 = -(new_v4 = (new_v12 = p7) + p5) - p6;
/*  276 */     float new_v27 = -p6 - p7 - p4;
/*  277 */     float new_v10, new_v14, new_v6 = (new_v10 = (new_v14 = p15) + p11) + p13;
/*  278 */     float new_v2, new_v17 = -(new_v2 = p15 + p13 + p9) - p14;
/*  279 */     float tmp1, new_v21 = (tmp1 = -p14 - p15 - p10 - p11) - p13;
/*  280 */     float new_v29 = -p14 - p15 - p12 - p8;
/*  281 */     float new_v25 = tmp1 - p12;
/*  282 */     float new_v31 = -p0;
/*  283 */     float new_v0 = p1;
/*  284 */     float new_v8, new_v23 = -(new_v8 = p3) - p2;
/*      */     
/*  286 */     p0 = (s0 - s31) * cos1_64;
/*  287 */     p1 = (s1 - s30) * cos3_64;
/*  288 */     p2 = (s2 - s29) * cos5_64;
/*  289 */     p3 = (s3 - s28) * cos7_64;
/*  290 */     p4 = (s4 - s27) * cos9_64;
/*  291 */     p5 = (s5 - s26) * cos11_64;
/*  292 */     p6 = (s6 - s25) * cos13_64;
/*  293 */     p7 = (s7 - s24) * cos15_64;
/*  294 */     p8 = (s8 - s23) * cos17_64;
/*  295 */     p9 = (s9 - s22) * cos19_64;
/*  296 */     p10 = (s10 - s21) * cos21_64;
/*  297 */     p11 = (s11 - s20) * cos23_64;
/*  298 */     p12 = (s12 - s19) * cos25_64;
/*  299 */     p13 = (s13 - s18) * cos27_64;
/*  300 */     p14 = (s14 - s17) * cos29_64;
/*  301 */     p15 = (s15 - s16) * cos31_64;
/*      */     
/*  303 */     pp0 = p0 + p15;
/*  304 */     pp1 = p1 + p14;
/*  305 */     pp2 = p2 + p13;
/*  306 */     pp3 = p3 + p12;
/*  307 */     pp4 = p4 + p11;
/*  308 */     pp5 = p5 + p10;
/*  309 */     pp6 = p6 + p9;
/*  310 */     pp7 = p7 + p8;
/*  311 */     pp8 = (p0 - p15) * cos1_32;
/*  312 */     pp9 = (p1 - p14) * cos3_32;
/*  313 */     pp10 = (p2 - p13) * cos5_32;
/*  314 */     pp11 = (p3 - p12) * cos7_32;
/*  315 */     pp12 = (p4 - p11) * cos9_32;
/*  316 */     pp13 = (p5 - p10) * cos11_32;
/*  317 */     pp14 = (p6 - p9) * cos13_32;
/*  318 */     pp15 = (p7 - p8) * cos15_32;
/*      */     
/*  320 */     p0 = pp0 + pp7;
/*  321 */     p1 = pp1 + pp6;
/*  322 */     p2 = pp2 + pp5;
/*  323 */     p3 = pp3 + pp4;
/*  324 */     p4 = (pp0 - pp7) * cos1_16;
/*  325 */     p5 = (pp1 - pp6) * cos3_16;
/*  326 */     p6 = (pp2 - pp5) * cos5_16;
/*  327 */     p7 = (pp3 - pp4) * cos7_16;
/*  328 */     p8 = pp8 + pp15;
/*  329 */     p9 = pp9 + pp14;
/*  330 */     p10 = pp10 + pp13;
/*  331 */     p11 = pp11 + pp12;
/*  332 */     p12 = (pp8 - pp15) * cos1_16;
/*  333 */     p13 = (pp9 - pp14) * cos3_16;
/*  334 */     p14 = (pp10 - pp13) * cos5_16;
/*  335 */     p15 = (pp11 - pp12) * cos7_16;
/*      */     
/*  337 */     pp0 = p0 + p3;
/*  338 */     pp1 = p1 + p2;
/*  339 */     pp2 = (p0 - p3) * cos1_8;
/*  340 */     pp3 = (p1 - p2) * cos3_8;
/*  341 */     pp4 = p4 + p7;
/*  342 */     pp5 = p5 + p6;
/*  343 */     pp6 = (p4 - p7) * cos1_8;
/*  344 */     pp7 = (p5 - p6) * cos3_8;
/*  345 */     pp8 = p8 + p11;
/*  346 */     pp9 = p9 + p10;
/*  347 */     pp10 = (p8 - p11) * cos1_8;
/*  348 */     pp11 = (p9 - p10) * cos3_8;
/*  349 */     pp12 = p12 + p15;
/*  350 */     pp13 = p13 + p14;
/*  351 */     pp14 = (p12 - p15) * cos1_8;
/*  352 */     pp15 = (p13 - p14) * cos3_8;
/*      */     
/*  354 */     p0 = pp0 + pp1;
/*  355 */     p1 = (pp0 - pp1) * cos1_4;
/*  356 */     p2 = pp2 + pp3;
/*  357 */     p3 = (pp2 - pp3) * cos1_4;
/*  358 */     p4 = pp4 + pp5;
/*  359 */     p5 = (pp4 - pp5) * cos1_4;
/*  360 */     p6 = pp6 + pp7;
/*  361 */     p7 = (pp6 - pp7) * cos1_4;
/*  362 */     p8 = pp8 + pp9;
/*  363 */     p9 = (pp8 - pp9) * cos1_4;
/*  364 */     p10 = pp10 + pp11;
/*  365 */     p11 = (pp10 - pp11) * cos1_4;
/*  366 */     p12 = pp12 + pp13;
/*  367 */     p13 = (pp12 - pp13) * cos1_4;
/*  368 */     p14 = pp14 + pp15;
/*  369 */     p15 = (pp14 - pp15) * cos1_4;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  374 */     float new_v11, new_v13, new_v15, new_v5 = (new_v11 = (new_v13 = (new_v15 = p15) + p7) + p11) + p5 + p13;
/*  375 */     float new_v9, new_v7 = (new_v9 = p15 + p11 + p3) + p13;
/*  376 */     float new_v1, new_v16 = -(new_v1 = (tmp1 = p13 + p15 + p9) + p1) - p14;
/*  377 */     float new_v3, new_v18 = -(new_v3 = tmp1 + p5 + p7) - p6 - p14;
/*      */     
/*  379 */     float new_v22 = (tmp1 = -p10 - p11 - p14 - p15) - p13 - p2 - p3;
/*  380 */     float new_v20 = tmp1 - p13 - p5 - p6 - p7;
/*  381 */     float new_v24 = tmp1 - p12 - p2 - p3;
/*  382 */     float tmp2, new_v26 = tmp1 - p12 - (tmp2 = p4 + p6 + p7);
/*  383 */     float new_v30 = (tmp1 = -p8 - p12 - p14 - p15) - p0;
/*  384 */     float new_v28 = tmp1 - tmp2;
/*      */ 
/*      */ 
/*      */     
/*  388 */     float[] dest = this.actual_v;
/*      */     
/*  390 */     int pos = this.actual_write_pos;
/*      */     
/*  392 */     dest[0 + pos] = new_v0;
/*  393 */     dest[16 + pos] = new_v1;
/*  394 */     dest[32 + pos] = new_v2;
/*  395 */     dest[48 + pos] = new_v3;
/*  396 */     dest[64 + pos] = new_v4;
/*  397 */     dest[80 + pos] = new_v5;
/*  398 */     dest[96 + pos] = new_v6;
/*  399 */     dest[112 + pos] = new_v7;
/*  400 */     dest[128 + pos] = new_v8;
/*  401 */     dest[144 + pos] = new_v9;
/*  402 */     dest[160 + pos] = new_v10;
/*  403 */     dest[176 + pos] = new_v11;
/*  404 */     dest[192 + pos] = new_v12;
/*  405 */     dest[208 + pos] = new_v13;
/*  406 */     dest[224 + pos] = new_v14;
/*  407 */     dest[240 + pos] = new_v15;
/*      */ 
/*      */     
/*  410 */     dest[256 + pos] = 0.0F;
/*      */ 
/*      */     
/*  413 */     dest[272 + pos] = -new_v15;
/*  414 */     dest[288 + pos] = -new_v14;
/*  415 */     dest[304 + pos] = -new_v13;
/*  416 */     dest[320 + pos] = -new_v12;
/*  417 */     dest[336 + pos] = -new_v11;
/*  418 */     dest[352 + pos] = -new_v10;
/*  419 */     dest[368 + pos] = -new_v9;
/*  420 */     dest[384 + pos] = -new_v8;
/*  421 */     dest[400 + pos] = -new_v7;
/*  422 */     dest[416 + pos] = -new_v6;
/*  423 */     dest[432 + pos] = -new_v5;
/*  424 */     dest[448 + pos] = -new_v4;
/*  425 */     dest[464 + pos] = -new_v3;
/*  426 */     dest[480 + pos] = -new_v2;
/*  427 */     dest[496 + pos] = -new_v1;
/*      */ 
/*      */     
/*  430 */     dest = (this.actual_v == this.v1) ? this.v2 : this.v1;
/*      */     
/*  432 */     dest[0 + pos] = -new_v0;
/*      */     
/*  434 */     dest[16 + pos] = new_v16;
/*  435 */     dest[32 + pos] = new_v17;
/*  436 */     dest[48 + pos] = new_v18;
/*  437 */     dest[64 + pos] = new_v19;
/*  438 */     dest[80 + pos] = new_v20;
/*  439 */     dest[96 + pos] = new_v21;
/*  440 */     dest[112 + pos] = new_v22;
/*  441 */     dest[128 + pos] = new_v23;
/*  442 */     dest[144 + pos] = new_v24;
/*  443 */     dest[160 + pos] = new_v25;
/*  444 */     dest[176 + pos] = new_v26;
/*  445 */     dest[192 + pos] = new_v27;
/*  446 */     dest[208 + pos] = new_v28;
/*  447 */     dest[224 + pos] = new_v29;
/*  448 */     dest[240 + pos] = new_v30;
/*  449 */     dest[256 + pos] = new_v31;
/*      */ 
/*      */     
/*  452 */     dest[272 + pos] = new_v30;
/*  453 */     dest[288 + pos] = new_v29;
/*  454 */     dest[304 + pos] = new_v28;
/*  455 */     dest[320 + pos] = new_v27;
/*  456 */     dest[336 + pos] = new_v26;
/*  457 */     dest[352 + pos] = new_v25;
/*  458 */     dest[368 + pos] = new_v24;
/*  459 */     dest[384 + pos] = new_v23;
/*  460 */     dest[400 + pos] = new_v22;
/*  461 */     dest[416 + pos] = new_v21;
/*  462 */     dest[432 + pos] = new_v20;
/*  463 */     dest[448 + pos] = new_v19;
/*  464 */     dest[464 + pos] = new_v18;
/*  465 */     dest[480 + pos] = new_v17;
/*  466 */     dest[496 + pos] = new_v16;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  487 */   private float[] _tmpOut = new float[32]; private static final double MY_PI = 3.141592653589793D;
/*      */   
/*      */   private void compute_pcm_samples0(OutputBuffer buffer) {
/*  490 */     float[] vp = this.actual_v;
/*      */     
/*  492 */     float[] tmpOut = this._tmpOut;
/*  493 */     int dvp = 0;
/*      */ 
/*      */     
/*  496 */     for (int i = 0; i < 32; i++) {
/*      */       
/*  498 */       float[] dp = d16[i];
/*  499 */       float pcm_sample = (vp[0 + dvp] * dp[0] + vp[15 + dvp] * dp[1] + vp[14 + dvp] * dp[2] + vp[13 + dvp] * dp[3] + vp[12 + dvp] * dp[4] + vp[11 + dvp] * dp[5] + vp[10 + dvp] * dp[6] + vp[9 + dvp] * dp[7] + vp[8 + dvp] * dp[8] + vp[7 + dvp] * dp[9] + vp[6 + dvp] * dp[10] + vp[5 + dvp] * dp[11] + vp[4 + dvp] * dp[12] + vp[3 + dvp] * dp[13] + vp[2 + dvp] * dp[14] + vp[1 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  504 */       tmpOut[i] = pcm_sample;
/*      */       
/*  506 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples1(OutputBuffer buffer) {
/*  511 */     float[] vp = this.actual_v;
/*      */     
/*  513 */     float[] tmpOut = this._tmpOut;
/*  514 */     int dvp = 0;
/*      */ 
/*      */     
/*  517 */     for (int i = 0; i < 32; i++) {
/*  518 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  521 */       float pcm_sample = (vp[1 + dvp] * dp[0] + vp[0 + dvp] * dp[1] + vp[15 + dvp] * dp[2] + vp[14 + dvp] * dp[3] + vp[13 + dvp] * dp[4] + vp[12 + dvp] * dp[5] + vp[11 + dvp] * dp[6] + vp[10 + dvp] * dp[7] + vp[9 + dvp] * dp[8] + vp[8 + dvp] * dp[9] + vp[7 + dvp] * dp[10] + vp[6 + dvp] * dp[11] + vp[5 + dvp] * dp[12] + vp[4 + dvp] * dp[13] + vp[3 + dvp] * dp[14] + vp[2 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  526 */       tmpOut[i] = pcm_sample;
/*      */       
/*  528 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples2(OutputBuffer buffer) {
/*  533 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  536 */     float[] tmpOut = this._tmpOut;
/*  537 */     int dvp = 0;
/*      */ 
/*      */     
/*  540 */     for (int i = 0; i < 32; i++) {
/*  541 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  544 */       float pcm_sample = (vp[2 + dvp] * dp[0] + vp[1 + dvp] * dp[1] + vp[0 + dvp] * dp[2] + vp[15 + dvp] * dp[3] + vp[14 + dvp] * dp[4] + vp[13 + dvp] * dp[5] + vp[12 + dvp] * dp[6] + vp[11 + dvp] * dp[7] + vp[10 + dvp] * dp[8] + vp[9 + dvp] * dp[9] + vp[8 + dvp] * dp[10] + vp[7 + dvp] * dp[11] + vp[6 + dvp] * dp[12] + vp[5 + dvp] * dp[13] + vp[4 + dvp] * dp[14] + vp[3 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  549 */       tmpOut[i] = pcm_sample;
/*      */       
/*  551 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples3(OutputBuffer buffer) {
/*  556 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  559 */     float[] tmpOut = this._tmpOut;
/*  560 */     int dvp = 0;
/*      */ 
/*      */     
/*  563 */     for (int i = 0; i < 32; i++) {
/*  564 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  567 */       float pcm_sample = (vp[3 + dvp] * dp[0] + vp[2 + dvp] * dp[1] + vp[1 + dvp] * dp[2] + vp[0 + dvp] * dp[3] + vp[15 + dvp] * dp[4] + vp[14 + dvp] * dp[5] + vp[13 + dvp] * dp[6] + vp[12 + dvp] * dp[7] + vp[11 + dvp] * dp[8] + vp[10 + dvp] * dp[9] + vp[9 + dvp] * dp[10] + vp[8 + dvp] * dp[11] + vp[7 + dvp] * dp[12] + vp[6 + dvp] * dp[13] + vp[5 + dvp] * dp[14] + vp[4 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  572 */       tmpOut[i] = pcm_sample;
/*      */       
/*  574 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples4(OutputBuffer buffer) {
/*  579 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  582 */     float[] tmpOut = this._tmpOut;
/*  583 */     int dvp = 0;
/*      */ 
/*      */     
/*  586 */     for (int i = 0; i < 32; i++) {
/*  587 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  590 */       float pcm_sample = (vp[4 + dvp] * dp[0] + vp[3 + dvp] * dp[1] + vp[2 + dvp] * dp[2] + vp[1 + dvp] * dp[3] + vp[0 + dvp] * dp[4] + vp[15 + dvp] * dp[5] + vp[14 + dvp] * dp[6] + vp[13 + dvp] * dp[7] + vp[12 + dvp] * dp[8] + vp[11 + dvp] * dp[9] + vp[10 + dvp] * dp[10] + vp[9 + dvp] * dp[11] + vp[8 + dvp] * dp[12] + vp[7 + dvp] * dp[13] + vp[6 + dvp] * dp[14] + vp[5 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  595 */       tmpOut[i] = pcm_sample;
/*      */       
/*  597 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples5(OutputBuffer buffer) {
/*  602 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  605 */     float[] tmpOut = this._tmpOut;
/*  606 */     int dvp = 0;
/*      */ 
/*      */     
/*  609 */     for (int i = 0; i < 32; i++) {
/*  610 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  613 */       float pcm_sample = (vp[5 + dvp] * dp[0] + vp[4 + dvp] * dp[1] + vp[3 + dvp] * dp[2] + vp[2 + dvp] * dp[3] + vp[1 + dvp] * dp[4] + vp[0 + dvp] * dp[5] + vp[15 + dvp] * dp[6] + vp[14 + dvp] * dp[7] + vp[13 + dvp] * dp[8] + vp[12 + dvp] * dp[9] + vp[11 + dvp] * dp[10] + vp[10 + dvp] * dp[11] + vp[9 + dvp] * dp[12] + vp[8 + dvp] * dp[13] + vp[7 + dvp] * dp[14] + vp[6 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  618 */       tmpOut[i] = pcm_sample;
/*      */       
/*  620 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples6(OutputBuffer buffer) {
/*  625 */     float[] vp = this.actual_v;
/*      */     
/*  627 */     float[] tmpOut = this._tmpOut;
/*  628 */     int dvp = 0;
/*      */ 
/*      */     
/*  631 */     for (int i = 0; i < 32; i++) {
/*  632 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  635 */       float pcm_sample = (vp[6 + dvp] * dp[0] + vp[5 + dvp] * dp[1] + vp[4 + dvp] * dp[2] + vp[3 + dvp] * dp[3] + vp[2 + dvp] * dp[4] + vp[1 + dvp] * dp[5] + vp[0 + dvp] * dp[6] + vp[15 + dvp] * dp[7] + vp[14 + dvp] * dp[8] + vp[13 + dvp] * dp[9] + vp[12 + dvp] * dp[10] + vp[11 + dvp] * dp[11] + vp[10 + dvp] * dp[12] + vp[9 + dvp] * dp[13] + vp[8 + dvp] * dp[14] + vp[7 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  640 */       tmpOut[i] = pcm_sample;
/*      */       
/*  642 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples7(OutputBuffer buffer) {
/*  647 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  650 */     float[] tmpOut = this._tmpOut;
/*  651 */     int dvp = 0;
/*      */ 
/*      */     
/*  654 */     for (int i = 0; i < 32; i++) {
/*  655 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  658 */       float pcm_sample = (vp[7 + dvp] * dp[0] + vp[6 + dvp] * dp[1] + vp[5 + dvp] * dp[2] + vp[4 + dvp] * dp[3] + vp[3 + dvp] * dp[4] + vp[2 + dvp] * dp[5] + vp[1 + dvp] * dp[6] + vp[0 + dvp] * dp[7] + vp[15 + dvp] * dp[8] + vp[14 + dvp] * dp[9] + vp[13 + dvp] * dp[10] + vp[12 + dvp] * dp[11] + vp[11 + dvp] * dp[12] + vp[10 + dvp] * dp[13] + vp[9 + dvp] * dp[14] + vp[8 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  663 */       tmpOut[i] = pcm_sample;
/*      */       
/*  665 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples8(OutputBuffer buffer) {
/*  670 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  673 */     float[] tmpOut = this._tmpOut;
/*  674 */     int dvp = 0;
/*      */ 
/*      */     
/*  677 */     for (int i = 0; i < 32; i++) {
/*  678 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  681 */       float pcm_sample = (vp[8 + dvp] * dp[0] + vp[7 + dvp] * dp[1] + vp[6 + dvp] * dp[2] + vp[5 + dvp] * dp[3] + vp[4 + dvp] * dp[4] + vp[3 + dvp] * dp[5] + vp[2 + dvp] * dp[6] + vp[1 + dvp] * dp[7] + vp[0 + dvp] * dp[8] + vp[15 + dvp] * dp[9] + vp[14 + dvp] * dp[10] + vp[13 + dvp] * dp[11] + vp[12 + dvp] * dp[12] + vp[11 + dvp] * dp[13] + vp[10 + dvp] * dp[14] + vp[9 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  686 */       tmpOut[i] = pcm_sample;
/*      */       
/*  688 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples9(OutputBuffer buffer) {
/*  693 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  696 */     float[] tmpOut = this._tmpOut;
/*  697 */     int dvp = 0;
/*      */ 
/*      */     
/*  700 */     for (int i = 0; i < 32; i++) {
/*  701 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  704 */       float pcm_sample = (vp[9 + dvp] * dp[0] + vp[8 + dvp] * dp[1] + vp[7 + dvp] * dp[2] + vp[6 + dvp] * dp[3] + vp[5 + dvp] * dp[4] + vp[4 + dvp] * dp[5] + vp[3 + dvp] * dp[6] + vp[2 + dvp] * dp[7] + vp[1 + dvp] * dp[8] + vp[0 + dvp] * dp[9] + vp[15 + dvp] * dp[10] + vp[14 + dvp] * dp[11] + vp[13 + dvp] * dp[12] + vp[12 + dvp] * dp[13] + vp[11 + dvp] * dp[14] + vp[10 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  709 */       tmpOut[i] = pcm_sample;
/*      */       
/*  711 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples10(OutputBuffer buffer) {
/*  716 */     float[] vp = this.actual_v;
/*      */     
/*  718 */     float[] tmpOut = this._tmpOut;
/*  719 */     int dvp = 0;
/*      */ 
/*      */     
/*  722 */     for (int i = 0; i < 32; i++) {
/*  723 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  726 */       float pcm_sample = (vp[10 + dvp] * dp[0] + vp[9 + dvp] * dp[1] + vp[8 + dvp] * dp[2] + vp[7 + dvp] * dp[3] + vp[6 + dvp] * dp[4] + vp[5 + dvp] * dp[5] + vp[4 + dvp] * dp[6] + vp[3 + dvp] * dp[7] + vp[2 + dvp] * dp[8] + vp[1 + dvp] * dp[9] + vp[0 + dvp] * dp[10] + vp[15 + dvp] * dp[11] + vp[14 + dvp] * dp[12] + vp[13 + dvp] * dp[13] + vp[12 + dvp] * dp[14] + vp[11 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  731 */       tmpOut[i] = pcm_sample;
/*      */       
/*  733 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples11(OutputBuffer buffer) {
/*  738 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  741 */     float[] tmpOut = this._tmpOut;
/*  742 */     int dvp = 0;
/*      */ 
/*      */     
/*  745 */     for (int i = 0; i < 32; i++) {
/*  746 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  749 */       float pcm_sample = (vp[11 + dvp] * dp[0] + vp[10 + dvp] * dp[1] + vp[9 + dvp] * dp[2] + vp[8 + dvp] * dp[3] + vp[7 + dvp] * dp[4] + vp[6 + dvp] * dp[5] + vp[5 + dvp] * dp[6] + vp[4 + dvp] * dp[7] + vp[3 + dvp] * dp[8] + vp[2 + dvp] * dp[9] + vp[1 + dvp] * dp[10] + vp[0 + dvp] * dp[11] + vp[15 + dvp] * dp[12] + vp[14 + dvp] * dp[13] + vp[13 + dvp] * dp[14] + vp[12 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  754 */       tmpOut[i] = pcm_sample;
/*      */       
/*  756 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples12(OutputBuffer buffer) {
/*  761 */     float[] vp = this.actual_v;
/*      */     
/*  763 */     float[] tmpOut = this._tmpOut;
/*  764 */     int dvp = 0;
/*      */ 
/*      */     
/*  767 */     for (int i = 0; i < 32; i++) {
/*  768 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  771 */       float pcm_sample = (vp[12 + dvp] * dp[0] + vp[11 + dvp] * dp[1] + vp[10 + dvp] * dp[2] + vp[9 + dvp] * dp[3] + vp[8 + dvp] * dp[4] + vp[7 + dvp] * dp[5] + vp[6 + dvp] * dp[6] + vp[5 + dvp] * dp[7] + vp[4 + dvp] * dp[8] + vp[3 + dvp] * dp[9] + vp[2 + dvp] * dp[10] + vp[1 + dvp] * dp[11] + vp[0 + dvp] * dp[12] + vp[15 + dvp] * dp[13] + vp[14 + dvp] * dp[14] + vp[13 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  776 */       tmpOut[i] = pcm_sample;
/*      */       
/*  778 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples13(OutputBuffer buffer) {
/*  783 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  786 */     float[] tmpOut = this._tmpOut;
/*  787 */     int dvp = 0;
/*      */ 
/*      */     
/*  790 */     for (int i = 0; i < 32; i++) {
/*  791 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  794 */       float pcm_sample = (vp[13 + dvp] * dp[0] + vp[12 + dvp] * dp[1] + vp[11 + dvp] * dp[2] + vp[10 + dvp] * dp[3] + vp[9 + dvp] * dp[4] + vp[8 + dvp] * dp[5] + vp[7 + dvp] * dp[6] + vp[6 + dvp] * dp[7] + vp[5 + dvp] * dp[8] + vp[4 + dvp] * dp[9] + vp[3 + dvp] * dp[10] + vp[2 + dvp] * dp[11] + vp[1 + dvp] * dp[12] + vp[0 + dvp] * dp[13] + vp[15 + dvp] * dp[14] + vp[14 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  799 */       tmpOut[i] = pcm_sample;
/*      */       
/*  801 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples14(OutputBuffer buffer) {
/*  806 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  809 */     float[] tmpOut = this._tmpOut;
/*  810 */     int dvp = 0;
/*      */ 
/*      */     
/*  813 */     for (int i = 0; i < 32; i++) {
/*  814 */       float[] dp = d16[i];
/*      */ 
/*      */       
/*  817 */       float pcm_sample = (vp[14 + dvp] * dp[0] + vp[13 + dvp] * dp[1] + vp[12 + dvp] * dp[2] + vp[11 + dvp] * dp[3] + vp[10 + dvp] * dp[4] + vp[9 + dvp] * dp[5] + vp[8 + dvp] * dp[6] + vp[7 + dvp] * dp[7] + vp[6 + dvp] * dp[8] + vp[5 + dvp] * dp[9] + vp[4 + dvp] * dp[10] + vp[3 + dvp] * dp[11] + vp[2 + dvp] * dp[12] + vp[1 + dvp] * dp[13] + vp[0 + dvp] * dp[14] + vp[15 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  822 */       tmpOut[i] = pcm_sample;
/*      */       
/*  824 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void compute_pcm_samples15(OutputBuffer buffer) {
/*  829 */     float[] vp = this.actual_v;
/*      */ 
/*      */     
/*  832 */     float[] tmpOut = this._tmpOut;
/*  833 */     int dvp = 0;
/*      */ 
/*      */     
/*  836 */     for (int i = 0; i < 32; i++) {
/*      */       
/*  838 */       float[] dp = d16[i];
/*  839 */       float pcm_sample = (vp[15 + dvp] * dp[0] + vp[14 + dvp] * dp[1] + vp[13 + dvp] * dp[2] + vp[12 + dvp] * dp[3] + vp[11 + dvp] * dp[4] + vp[10 + dvp] * dp[5] + vp[9 + dvp] * dp[6] + vp[8 + dvp] * dp[7] + vp[7 + dvp] * dp[8] + vp[6 + dvp] * dp[9] + vp[5 + dvp] * dp[10] + vp[4 + dvp] * dp[11] + vp[3 + dvp] * dp[12] + vp[2 + dvp] * dp[13] + vp[1 + dvp] * dp[14] + vp[0 + dvp] * dp[15]) * this.scalefactor;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  844 */       tmpOut[i] = pcm_sample;
/*  845 */       dvp += 16;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void compute_pcm_samples(OutputBuffer buffer) {
/*  851 */     switch (this.actual_write_pos) {
/*      */       case 0:
/*  853 */         compute_pcm_samples0(buffer);
/*      */         break;
/*      */       case 1:
/*  856 */         compute_pcm_samples1(buffer);
/*      */         break;
/*      */       case 2:
/*  859 */         compute_pcm_samples2(buffer);
/*      */         break;
/*      */       case 3:
/*  862 */         compute_pcm_samples3(buffer);
/*      */         break;
/*      */       case 4:
/*  865 */         compute_pcm_samples4(buffer);
/*      */         break;
/*      */       case 5:
/*  868 */         compute_pcm_samples5(buffer);
/*      */         break;
/*      */       case 6:
/*  871 */         compute_pcm_samples6(buffer);
/*      */         break;
/*      */       case 7:
/*  874 */         compute_pcm_samples7(buffer);
/*      */         break;
/*      */       case 8:
/*  877 */         compute_pcm_samples8(buffer);
/*      */         break;
/*      */       case 9:
/*  880 */         compute_pcm_samples9(buffer);
/*      */         break;
/*      */       case 10:
/*  883 */         compute_pcm_samples10(buffer);
/*      */         break;
/*      */       case 11:
/*  886 */         compute_pcm_samples11(buffer);
/*      */         break;
/*      */       case 12:
/*  889 */         compute_pcm_samples12(buffer);
/*      */         break;
/*      */       case 13:
/*  892 */         compute_pcm_samples13(buffer);
/*      */         break;
/*      */       case 14:
/*  895 */         compute_pcm_samples14(buffer);
/*      */         break;
/*      */       case 15:
/*  898 */         compute_pcm_samples15(buffer);
/*      */         break;
/*      */     } 
/*      */     
/*  902 */     if (buffer != null) buffer.appendSamples(this.channel, this._tmpOut);
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void calculate_pcm_samples(OutputBuffer buffer) {
/*  920 */     compute_new_v();
/*  921 */     compute_pcm_samples(buffer);
/*      */     
/*  923 */     this.actual_write_pos = this.actual_write_pos + 1 & 0xF;
/*  924 */     this.actual_v = (this.actual_v == this.v1) ? this.v2 : this.v1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  932 */     for (int p = 0; p < 32; p++) {
/*  933 */       this.samples[p] = 0.0F;
/*      */     }
/*      */   }
/*      */   
/*  937 */   private static final float cos1_64 = (float)(1.0D / 2.0D * Math.cos(0.04908738521234052D));
/*  938 */   private static final float cos3_64 = (float)(1.0D / 2.0D * Math.cos(0.14726215563702155D));
/*  939 */   private static final float cos5_64 = (float)(1.0D / 2.0D * Math.cos(0.2454369260617026D));
/*  940 */   private static final float cos7_64 = (float)(1.0D / 2.0D * Math.cos(0.3436116964863836D));
/*  941 */   private static final float cos9_64 = (float)(1.0D / 2.0D * Math.cos(0.44178646691106466D));
/*  942 */   private static final float cos11_64 = (float)(1.0D / 2.0D * Math.cos(0.5399612373357456D));
/*  943 */   private static final float cos13_64 = (float)(1.0D / 2.0D * Math.cos(0.6381360077604268D));
/*  944 */   private static final float cos15_64 = (float)(1.0D / 2.0D * Math.cos(0.7363107781851077D));
/*  945 */   private static final float cos17_64 = (float)(1.0D / 2.0D * Math.cos(0.8344855486097889D));
/*  946 */   private static final float cos19_64 = (float)(1.0D / 2.0D * Math.cos(0.9326603190344698D));
/*  947 */   private static final float cos21_64 = (float)(1.0D / 2.0D * Math.cos(1.030835089459151D));
/*  948 */   private static final float cos23_64 = (float)(1.0D / 2.0D * Math.cos(1.1290098598838318D));
/*  949 */   private static final float cos25_64 = (float)(1.0D / 2.0D * Math.cos(1.227184630308513D));
/*  950 */   private static final float cos27_64 = (float)(1.0D / 2.0D * Math.cos(1.325359400733194D));
/*  951 */   private static final float cos29_64 = (float)(1.0D / 2.0D * Math.cos(1.423534171157875D));
/*  952 */   private static final float cos31_64 = (float)(1.0D / 2.0D * Math.cos(1.521708941582556D));
/*  953 */   private static final float cos1_32 = (float)(1.0D / 2.0D * Math.cos(0.09817477042468103D));
/*  954 */   private static final float cos3_32 = (float)(1.0D / 2.0D * Math.cos(0.2945243112740431D));
/*  955 */   private static final float cos5_32 = (float)(1.0D / 2.0D * Math.cos(0.4908738521234052D));
/*  956 */   private static final float cos7_32 = (float)(1.0D / 2.0D * Math.cos(0.6872233929727672D));
/*  957 */   private static final float cos9_32 = (float)(1.0D / 2.0D * Math.cos(0.8835729338221293D));
/*  958 */   private static final float cos11_32 = (float)(1.0D / 2.0D * Math.cos(1.0799224746714913D));
/*  959 */   private static final float cos13_32 = (float)(1.0D / 2.0D * Math.cos(1.2762720155208536D));
/*  960 */   private static final float cos15_32 = (float)(1.0D / 2.0D * Math.cos(1.4726215563702154D));
/*  961 */   private static final float cos1_16 = (float)(1.0D / 2.0D * Math.cos(0.19634954084936207D));
/*  962 */   private static final float cos3_16 = (float)(1.0D / 2.0D * Math.cos(0.5890486225480862D));
/*  963 */   private static final float cos5_16 = (float)(1.0D / 2.0D * Math.cos(0.9817477042468103D));
/*  964 */   private static final float cos7_16 = (float)(1.0D / 2.0D * Math.cos(1.3744467859455345D));
/*  965 */   private static final float cos1_8 = (float)(1.0D / 2.0D * Math.cos(0.39269908169872414D));
/*  966 */   private static final float cos3_8 = (float)(1.0D / 2.0D * Math.cos(1.1780972450961724D));
/*  967 */   private static final float cos1_4 = (float)(1.0D / 2.0D * Math.cos(0.7853981633974483D));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  973 */   private static float[] d = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  979 */   private static float[][] d16 = (float[][])null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static float[] load_d() {
/*      */     try {
/*  987 */       Class<Float> elemType = float.class;
/*  988 */       Object o = deserializeArray(SynthesisFilter.class.getResourceAsStream("/sfd.ser"), elemType, 512);
/*  989 */       return (float[])o;
/*  990 */     } catch (IOException ex) {
/*  991 */       throw new ExceptionInInitializerError(ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Object deserializeArray(InputStream in, Class<?> elemType, int length) throws IOException {
/* 1004 */     if (elemType == null) throw new NullPointerException("elemType");
/*      */     
/* 1006 */     if (length < -1) throw new IllegalArgumentException("length");
/*      */     
/* 1008 */     Object obj = deserialize(in);
/*      */     
/* 1010 */     Class<?> cls = obj.getClass();
/*      */     
/* 1012 */     if (!cls.isArray()) throw new InvalidObjectException("object is not an array");
/*      */     
/* 1014 */     Class<?> arrayElemType = cls.getComponentType();
/* 1015 */     if (arrayElemType != elemType) throw new InvalidObjectException("unexpected array component type");
/*      */     
/* 1017 */     if (length != -1) {
/* 1018 */       int arrayLength = Array.getLength(obj);
/* 1019 */       if (arrayLength != length) throw new InvalidObjectException("array length mismatch");
/*      */     
/*      */     } 
/* 1022 */     return obj;
/*      */   }
/*      */   public static Object deserialize(InputStream in) throws IOException {
/*      */     Object obj;
/* 1026 */     if (in == null) throw new NullPointerException("in");
/*      */     
/* 1028 */     ObjectInputStream objIn = new ObjectInputStream(in);
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1033 */       obj = objIn.readObject();
/* 1034 */     } catch (ClassNotFoundException ex) {
/* 1035 */       throw new InvalidClassException(ex.toString());
/*      */     } 
/* 1037 */     return obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static float[][] splitArray(float[] array, int blockSize) {
/* 1050 */     int size = array.length / blockSize;
/* 1051 */     float[][] split = new float[size][];
/* 1052 */     for (int i = 0; i < size; i++)
/* 1053 */       split[i] = subArray(array, i * blockSize, blockSize); 
/* 1054 */     return split;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static float[] subArray(float[] array, int offs, int len) {
/* 1066 */     if (offs + len > array.length) len = array.length - offs;
/*      */     
/* 1068 */     if (len < 0) len = 0;
/*      */     
/* 1070 */     float[] subarray = new float[len];
/* 1071 */     for (int i = 0; i < len; i++)
/* 1072 */       subarray[i] = array[offs + i]; 
/* 1073 */     return subarray;
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\javazoom\jl\decoder\SynthesisFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */