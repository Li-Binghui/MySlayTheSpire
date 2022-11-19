/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.utils.Array;
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
/*     */ public class IkConstraint
/*     */   implements Updatable
/*     */ {
/*     */   final IkConstraintData data;
/*     */   final Array<Bone> bones;
/*     */   Bone target;
/*  42 */   float mix = 1.0F;
/*     */   
/*     */   int bendDirection;
/*     */   int level;
/*     */   
/*     */   public IkConstraint(IkConstraintData data, Skeleton skeleton) {
/*  48 */     if (data == null) throw new IllegalArgumentException("data cannot be null."); 
/*  49 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null."); 
/*  50 */     this.data = data;
/*  51 */     this.mix = data.mix;
/*  52 */     this.bendDirection = data.bendDirection;
/*     */     
/*  54 */     this.bones = new Array(data.bones.size);
/*  55 */     for (BoneData boneData : data.bones)
/*  56 */       this.bones.add(skeleton.findBone(boneData.name)); 
/*  57 */     this.target = skeleton.findBone(data.target.name);
/*     */   }
/*     */ 
/*     */   
/*     */   public IkConstraint(IkConstraint constraint, Skeleton skeleton) {
/*  62 */     if (constraint == null) throw new IllegalArgumentException("constraint cannot be null."); 
/*  63 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null."); 
/*  64 */     this.data = constraint.data;
/*  65 */     this.bones = new Array(constraint.bones.size);
/*  66 */     for (Bone bone : constraint.bones)
/*  67 */       this.bones.add(skeleton.bones.get(bone.data.index)); 
/*  68 */     this.target = (Bone)skeleton.bones.get(constraint.target.data.index);
/*  69 */     this.mix = constraint.mix;
/*  70 */     this.bendDirection = constraint.bendDirection;
/*     */   }
/*     */   
/*     */   public void apply() {
/*  74 */     update();
/*     */   }
/*     */   
/*     */   public void update() {
/*  78 */     Bone target = this.target;
/*  79 */     Array<Bone> bones = this.bones;
/*  80 */     switch (bones.size) {
/*     */       case 1:
/*  82 */         apply((Bone)bones.first(), target.worldX, target.worldY, this.mix);
/*     */         break;
/*     */       case 2:
/*  85 */         apply((Bone)bones.first(), (Bone)bones.get(1), target.worldX, target.worldY, this.bendDirection, this.mix);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Array<Bone> getBones() {
/*  91 */     return this.bones;
/*     */   }
/*     */   
/*     */   public Bone getTarget() {
/*  95 */     return this.target;
/*     */   }
/*     */   
/*     */   public void setTarget(Bone target) {
/*  99 */     this.target = target;
/*     */   }
/*     */   
/*     */   public float getMix() {
/* 103 */     return this.mix;
/*     */   }
/*     */   
/*     */   public void setMix(float mix) {
/* 107 */     this.mix = mix;
/*     */   }
/*     */   
/*     */   public int getBendDirection() {
/* 111 */     return this.bendDirection;
/*     */   }
/*     */   
/*     */   public void setBendDirection(int bendDirection) {
/* 115 */     this.bendDirection = bendDirection;
/*     */   }
/*     */   
/*     */   public IkConstraintData getData() {
/* 119 */     return this.data;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 123 */     return this.data.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void apply(Bone bone, float targetX, float targetY, float alpha) {
/* 129 */     Bone pp = bone.parent;
/* 130 */     float id = 1.0F / (pp.a * pp.d - pp.b * pp.c);
/* 131 */     float x = targetX - pp.worldX, y = targetY - pp.worldY;
/* 132 */     float tx = (x * pp.d - y * pp.b) * id - bone.x, ty = (y * pp.a - x * pp.c) * id - bone.y;
/* 133 */     float rotationIK = MathUtils.atan2(ty, tx) * 57.295776F - bone.shearX - bone.rotation;
/* 134 */     if (bone.scaleX < 0.0F) rotationIK += 180.0F; 
/* 135 */     if (rotationIK > 180.0F)
/* 136 */     { rotationIK -= 360.0F; }
/* 137 */     else if (rotationIK < -180.0F) { rotationIK += 360.0F; }
/* 138 */      bone.updateWorldTransform(bone.x, bone.y, bone.rotation + rotationIK * alpha, bone.scaleX, bone.scaleY, bone.shearX, bone.shearY);
/*     */   }
/*     */   
/*     */   public static void apply(Bone parent, Bone child, float targetX, float targetY, int bendDir, float alpha) {
/*     */     // Byte code:
/*     */     //   0: fload #5
/*     */     //   2: fconst_0
/*     */     //   3: fcmpl
/*     */     //   4: ifne -> 12
/*     */     //   7: aload_1
/*     */     //   8: invokevirtual updateWorldTransform : ()V
/*     */     //   11: return
/*     */     //   12: aload_0
/*     */     //   13: getfield x : F
/*     */     //   16: fstore #6
/*     */     //   18: aload_0
/*     */     //   19: getfield y : F
/*     */     //   22: fstore #7
/*     */     //   24: aload_0
/*     */     //   25: getfield scaleX : F
/*     */     //   28: fstore #8
/*     */     //   30: aload_0
/*     */     //   31: getfield scaleY : F
/*     */     //   34: fstore #9
/*     */     //   36: aload_1
/*     */     //   37: getfield scaleX : F
/*     */     //   40: fstore #10
/*     */     //   42: fload #8
/*     */     //   44: fconst_0
/*     */     //   45: fcmpg
/*     */     //   46: ifge -> 65
/*     */     //   49: fload #8
/*     */     //   51: fneg
/*     */     //   52: fstore #8
/*     */     //   54: sipush #180
/*     */     //   57: istore #11
/*     */     //   59: iconst_m1
/*     */     //   60: istore #13
/*     */     //   62: goto -> 71
/*     */     //   65: iconst_0
/*     */     //   66: istore #11
/*     */     //   68: iconst_1
/*     */     //   69: istore #13
/*     */     //   71: fload #9
/*     */     //   73: fconst_0
/*     */     //   74: fcmpg
/*     */     //   75: ifge -> 88
/*     */     //   78: fload #9
/*     */     //   80: fneg
/*     */     //   81: fstore #9
/*     */     //   83: iload #13
/*     */     //   85: ineg
/*     */     //   86: istore #13
/*     */     //   88: fload #10
/*     */     //   90: fconst_0
/*     */     //   91: fcmpg
/*     */     //   92: ifge -> 108
/*     */     //   95: fload #10
/*     */     //   97: fneg
/*     */     //   98: fstore #10
/*     */     //   100: sipush #180
/*     */     //   103: istore #12
/*     */     //   105: goto -> 111
/*     */     //   108: iconst_0
/*     */     //   109: istore #12
/*     */     //   111: aload_1
/*     */     //   112: getfield x : F
/*     */     //   115: fstore #14
/*     */     //   117: aload_0
/*     */     //   118: getfield a : F
/*     */     //   121: fstore #18
/*     */     //   123: aload_0
/*     */     //   124: getfield b : F
/*     */     //   127: fstore #19
/*     */     //   129: aload_0
/*     */     //   130: getfield c : F
/*     */     //   133: fstore #20
/*     */     //   135: aload_0
/*     */     //   136: getfield d : F
/*     */     //   139: fstore #21
/*     */     //   141: fload #8
/*     */     //   143: fload #9
/*     */     //   145: fsub
/*     */     //   146: invokestatic abs : (F)F
/*     */     //   149: ldc 1.0E-4
/*     */     //   151: fcmpg
/*     */     //   152: ifgt -> 159
/*     */     //   155: iconst_1
/*     */     //   156: goto -> 160
/*     */     //   159: iconst_0
/*     */     //   160: istore #22
/*     */     //   162: iload #22
/*     */     //   164: ifne -> 197
/*     */     //   167: fconst_0
/*     */     //   168: fstore #15
/*     */     //   170: fload #18
/*     */     //   172: fload #14
/*     */     //   174: fmul
/*     */     //   175: aload_0
/*     */     //   176: getfield worldX : F
/*     */     //   179: fadd
/*     */     //   180: fstore #16
/*     */     //   182: fload #20
/*     */     //   184: fload #14
/*     */     //   186: fmul
/*     */     //   187: aload_0
/*     */     //   188: getfield worldY : F
/*     */     //   191: fadd
/*     */     //   192: fstore #17
/*     */     //   194: goto -> 239
/*     */     //   197: aload_1
/*     */     //   198: getfield y : F
/*     */     //   201: fstore #15
/*     */     //   203: fload #18
/*     */     //   205: fload #14
/*     */     //   207: fmul
/*     */     //   208: fload #19
/*     */     //   210: fload #15
/*     */     //   212: fmul
/*     */     //   213: fadd
/*     */     //   214: aload_0
/*     */     //   215: getfield worldX : F
/*     */     //   218: fadd
/*     */     //   219: fstore #16
/*     */     //   221: fload #20
/*     */     //   223: fload #14
/*     */     //   225: fmul
/*     */     //   226: fload #21
/*     */     //   228: fload #15
/*     */     //   230: fmul
/*     */     //   231: fadd
/*     */     //   232: aload_0
/*     */     //   233: getfield worldY : F
/*     */     //   236: fadd
/*     */     //   237: fstore #17
/*     */     //   239: aload_0
/*     */     //   240: getfield parent : Lcom/esotericsoftware/spine/Bone;
/*     */     //   243: astore #23
/*     */     //   245: aload #23
/*     */     //   247: getfield a : F
/*     */     //   250: fstore #18
/*     */     //   252: aload #23
/*     */     //   254: getfield b : F
/*     */     //   257: fstore #19
/*     */     //   259: aload #23
/*     */     //   261: getfield c : F
/*     */     //   264: fstore #20
/*     */     //   266: aload #23
/*     */     //   268: getfield d : F
/*     */     //   271: fstore #21
/*     */     //   273: fconst_1
/*     */     //   274: fload #18
/*     */     //   276: fload #21
/*     */     //   278: fmul
/*     */     //   279: fload #19
/*     */     //   281: fload #20
/*     */     //   283: fmul
/*     */     //   284: fsub
/*     */     //   285: fdiv
/*     */     //   286: fstore #24
/*     */     //   288: fload_2
/*     */     //   289: aload #23
/*     */     //   291: getfield worldX : F
/*     */     //   294: fsub
/*     */     //   295: fstore #25
/*     */     //   297: fload_3
/*     */     //   298: aload #23
/*     */     //   300: getfield worldY : F
/*     */     //   303: fsub
/*     */     //   304: fstore #26
/*     */     //   306: fload #25
/*     */     //   308: fload #21
/*     */     //   310: fmul
/*     */     //   311: fload #26
/*     */     //   313: fload #19
/*     */     //   315: fmul
/*     */     //   316: fsub
/*     */     //   317: fload #24
/*     */     //   319: fmul
/*     */     //   320: fload #6
/*     */     //   322: fsub
/*     */     //   323: fstore #27
/*     */     //   325: fload #26
/*     */     //   327: fload #18
/*     */     //   329: fmul
/*     */     //   330: fload #25
/*     */     //   332: fload #20
/*     */     //   334: fmul
/*     */     //   335: fsub
/*     */     //   336: fload #24
/*     */     //   338: fmul
/*     */     //   339: fload #7
/*     */     //   341: fsub
/*     */     //   342: fstore #28
/*     */     //   344: fload #16
/*     */     //   346: aload #23
/*     */     //   348: getfield worldX : F
/*     */     //   351: fsub
/*     */     //   352: fstore #25
/*     */     //   354: fload #17
/*     */     //   356: aload #23
/*     */     //   358: getfield worldY : F
/*     */     //   361: fsub
/*     */     //   362: fstore #26
/*     */     //   364: fload #25
/*     */     //   366: fload #21
/*     */     //   368: fmul
/*     */     //   369: fload #26
/*     */     //   371: fload #19
/*     */     //   373: fmul
/*     */     //   374: fsub
/*     */     //   375: fload #24
/*     */     //   377: fmul
/*     */     //   378: fload #6
/*     */     //   380: fsub
/*     */     //   381: fstore #29
/*     */     //   383: fload #26
/*     */     //   385: fload #18
/*     */     //   387: fmul
/*     */     //   388: fload #25
/*     */     //   390: fload #20
/*     */     //   392: fmul
/*     */     //   393: fsub
/*     */     //   394: fload #24
/*     */     //   396: fmul
/*     */     //   397: fload #7
/*     */     //   399: fsub
/*     */     //   400: fstore #30
/*     */     //   402: fload #29
/*     */     //   404: fload #29
/*     */     //   406: fmul
/*     */     //   407: fload #30
/*     */     //   409: fload #30
/*     */     //   411: fmul
/*     */     //   412: fadd
/*     */     //   413: f2d
/*     */     //   414: invokestatic sqrt : (D)D
/*     */     //   417: d2f
/*     */     //   418: fstore #31
/*     */     //   420: aload_1
/*     */     //   421: getfield data : Lcom/esotericsoftware/spine/BoneData;
/*     */     //   424: getfield length : F
/*     */     //   427: fload #10
/*     */     //   429: fmul
/*     */     //   430: fstore #32
/*     */     //   432: iload #22
/*     */     //   434: ifeq -> 565
/*     */     //   437: fload #32
/*     */     //   439: fload #8
/*     */     //   441: fmul
/*     */     //   442: fstore #32
/*     */     //   444: fload #27
/*     */     //   446: fload #27
/*     */     //   448: fmul
/*     */     //   449: fload #28
/*     */     //   451: fload #28
/*     */     //   453: fmul
/*     */     //   454: fadd
/*     */     //   455: fload #31
/*     */     //   457: fload #31
/*     */     //   459: fmul
/*     */     //   460: fsub
/*     */     //   461: fload #32
/*     */     //   463: fload #32
/*     */     //   465: fmul
/*     */     //   466: fsub
/*     */     //   467: fconst_2
/*     */     //   468: fload #31
/*     */     //   470: fmul
/*     */     //   471: fload #32
/*     */     //   473: fmul
/*     */     //   474: fdiv
/*     */     //   475: fstore #35
/*     */     //   477: fload #35
/*     */     //   479: ldc -1.0
/*     */     //   481: fcmpg
/*     */     //   482: ifge -> 492
/*     */     //   485: ldc -1.0
/*     */     //   487: fstore #35
/*     */     //   489: goto -> 502
/*     */     //   492: fload #35
/*     */     //   494: fconst_1
/*     */     //   495: fcmpl
/*     */     //   496: ifle -> 502
/*     */     //   499: fconst_1
/*     */     //   500: fstore #35
/*     */     //   502: fload #35
/*     */     //   504: f2d
/*     */     //   505: invokestatic acos : (D)D
/*     */     //   508: d2f
/*     */     //   509: iload #4
/*     */     //   511: i2f
/*     */     //   512: fmul
/*     */     //   513: fstore #34
/*     */     //   515: fload #31
/*     */     //   517: fload #32
/*     */     //   519: fload #35
/*     */     //   521: fmul
/*     */     //   522: fadd
/*     */     //   523: fstore #18
/*     */     //   525: fload #32
/*     */     //   527: fload #34
/*     */     //   529: invokestatic sin : (F)F
/*     */     //   532: fmul
/*     */     //   533: fstore #19
/*     */     //   535: fload #28
/*     */     //   537: fload #18
/*     */     //   539: fmul
/*     */     //   540: fload #27
/*     */     //   542: fload #19
/*     */     //   544: fmul
/*     */     //   545: fsub
/*     */     //   546: fload #27
/*     */     //   548: fload #18
/*     */     //   550: fmul
/*     */     //   551: fload #28
/*     */     //   553: fload #19
/*     */     //   555: fmul
/*     */     //   556: fadd
/*     */     //   557: invokestatic atan2 : (FF)F
/*     */     //   560: fstore #33
/*     */     //   562: goto -> 1067
/*     */     //   565: fload #8
/*     */     //   567: fload #32
/*     */     //   569: fmul
/*     */     //   570: fstore #18
/*     */     //   572: fload #9
/*     */     //   574: fload #32
/*     */     //   576: fmul
/*     */     //   577: fstore #19
/*     */     //   579: fload #18
/*     */     //   581: fload #18
/*     */     //   583: fmul
/*     */     //   584: fstore #35
/*     */     //   586: fload #19
/*     */     //   588: fload #19
/*     */     //   590: fmul
/*     */     //   591: fstore #36
/*     */     //   593: fload #27
/*     */     //   595: fload #27
/*     */     //   597: fmul
/*     */     //   598: fload #28
/*     */     //   600: fload #28
/*     */     //   602: fmul
/*     */     //   603: fadd
/*     */     //   604: fstore #37
/*     */     //   606: fload #28
/*     */     //   608: fload #27
/*     */     //   610: invokestatic atan2 : (FF)F
/*     */     //   613: fstore #38
/*     */     //   615: fload #36
/*     */     //   617: fload #31
/*     */     //   619: fmul
/*     */     //   620: fload #31
/*     */     //   622: fmul
/*     */     //   623: fload #35
/*     */     //   625: fload #37
/*     */     //   627: fmul
/*     */     //   628: fadd
/*     */     //   629: fload #35
/*     */     //   631: fload #36
/*     */     //   633: fmul
/*     */     //   634: fsub
/*     */     //   635: fstore #20
/*     */     //   637: ldc -2.0
/*     */     //   639: fload #36
/*     */     //   641: fmul
/*     */     //   642: fload #31
/*     */     //   644: fmul
/*     */     //   645: fstore #39
/*     */     //   647: fload #36
/*     */     //   649: fload #35
/*     */     //   651: fsub
/*     */     //   652: fstore #40
/*     */     //   654: fload #39
/*     */     //   656: fload #39
/*     */     //   658: fmul
/*     */     //   659: ldc 4.0
/*     */     //   661: fload #40
/*     */     //   663: fmul
/*     */     //   664: fload #20
/*     */     //   666: fmul
/*     */     //   667: fsub
/*     */     //   668: fstore #21
/*     */     //   670: fload #21
/*     */     //   672: fconst_0
/*     */     //   673: fcmpl
/*     */     //   674: iflt -> 808
/*     */     //   677: fload #21
/*     */     //   679: f2d
/*     */     //   680: invokestatic sqrt : (D)D
/*     */     //   683: d2f
/*     */     //   684: fstore #41
/*     */     //   686: fload #39
/*     */     //   688: fconst_0
/*     */     //   689: fcmpg
/*     */     //   690: ifge -> 698
/*     */     //   693: fload #41
/*     */     //   695: fneg
/*     */     //   696: fstore #41
/*     */     //   698: fload #39
/*     */     //   700: fload #41
/*     */     //   702: fadd
/*     */     //   703: fneg
/*     */     //   704: fconst_2
/*     */     //   705: fdiv
/*     */     //   706: fstore #41
/*     */     //   708: fload #41
/*     */     //   710: fload #40
/*     */     //   712: fdiv
/*     */     //   713: fstore #42
/*     */     //   715: fload #20
/*     */     //   717: fload #41
/*     */     //   719: fdiv
/*     */     //   720: fstore #43
/*     */     //   722: fload #42
/*     */     //   724: invokestatic abs : (F)F
/*     */     //   727: fload #43
/*     */     //   729: invokestatic abs : (F)F
/*     */     //   732: fcmpg
/*     */     //   733: ifge -> 741
/*     */     //   736: fload #42
/*     */     //   738: goto -> 743
/*     */     //   741: fload #43
/*     */     //   743: fstore #44
/*     */     //   745: fload #44
/*     */     //   747: fload #44
/*     */     //   749: fmul
/*     */     //   750: fload #37
/*     */     //   752: fcmpg
/*     */     //   753: ifgt -> 808
/*     */     //   756: fload #37
/*     */     //   758: fload #44
/*     */     //   760: fload #44
/*     */     //   762: fmul
/*     */     //   763: fsub
/*     */     //   764: f2d
/*     */     //   765: invokestatic sqrt : (D)D
/*     */     //   768: d2f
/*     */     //   769: iload #4
/*     */     //   771: i2f
/*     */     //   772: fmul
/*     */     //   773: fstore #26
/*     */     //   775: fload #38
/*     */     //   777: fload #26
/*     */     //   779: fload #44
/*     */     //   781: invokestatic atan2 : (FF)F
/*     */     //   784: fsub
/*     */     //   785: fstore #33
/*     */     //   787: fload #26
/*     */     //   789: fload #9
/*     */     //   791: fdiv
/*     */     //   792: fload #44
/*     */     //   794: fload #31
/*     */     //   796: fsub
/*     */     //   797: fload #8
/*     */     //   799: fdiv
/*     */     //   800: invokestatic atan2 : (FF)F
/*     */     //   803: fstore #34
/*     */     //   805: goto -> 1067
/*     */     //   808: fconst_0
/*     */     //   809: fstore #41
/*     */     //   811: ldc 3.4028235E38
/*     */     //   813: fstore #42
/*     */     //   815: fconst_0
/*     */     //   816: fstore #43
/*     */     //   818: fconst_0
/*     */     //   819: fstore #44
/*     */     //   821: fconst_0
/*     */     //   822: fstore #45
/*     */     //   824: fconst_0
/*     */     //   825: fstore #46
/*     */     //   827: fconst_0
/*     */     //   828: fstore #47
/*     */     //   830: fconst_0
/*     */     //   831: fstore #48
/*     */     //   833: fload #31
/*     */     //   835: fload #18
/*     */     //   837: fadd
/*     */     //   838: fstore #25
/*     */     //   840: fload #25
/*     */     //   842: fload #25
/*     */     //   844: fmul
/*     */     //   845: fstore #21
/*     */     //   847: fload #21
/*     */     //   849: fload #46
/*     */     //   851: fcmpl
/*     */     //   852: ifle -> 866
/*     */     //   855: fconst_0
/*     */     //   856: fstore #45
/*     */     //   858: fload #21
/*     */     //   860: fstore #46
/*     */     //   862: fload #25
/*     */     //   864: fstore #47
/*     */     //   866: fload #31
/*     */     //   868: fload #18
/*     */     //   870: fsub
/*     */     //   871: fstore #25
/*     */     //   873: fload #25
/*     */     //   875: fload #25
/*     */     //   877: fmul
/*     */     //   878: fstore #21
/*     */     //   880: fload #21
/*     */     //   882: fload #42
/*     */     //   884: fcmpg
/*     */     //   885: ifge -> 900
/*     */     //   888: ldc 3.1415927
/*     */     //   890: fstore #41
/*     */     //   892: fload #21
/*     */     //   894: fstore #42
/*     */     //   896: fload #25
/*     */     //   898: fstore #43
/*     */     //   900: fload #18
/*     */     //   902: fneg
/*     */     //   903: fload #31
/*     */     //   905: fmul
/*     */     //   906: fload #35
/*     */     //   908: fload #36
/*     */     //   910: fsub
/*     */     //   911: fdiv
/*     */     //   912: f2d
/*     */     //   913: invokestatic acos : (D)D
/*     */     //   916: d2f
/*     */     //   917: fstore #49
/*     */     //   919: fload #18
/*     */     //   921: fload #49
/*     */     //   923: invokestatic cos : (F)F
/*     */     //   926: fmul
/*     */     //   927: fload #31
/*     */     //   929: fadd
/*     */     //   930: fstore #25
/*     */     //   932: fload #19
/*     */     //   934: fload #49
/*     */     //   936: invokestatic sin : (F)F
/*     */     //   939: fmul
/*     */     //   940: fstore #26
/*     */     //   942: fload #25
/*     */     //   944: fload #25
/*     */     //   946: fmul
/*     */     //   947: fload #26
/*     */     //   949: fload #26
/*     */     //   951: fmul
/*     */     //   952: fadd
/*     */     //   953: fstore #21
/*     */     //   955: fload #21
/*     */     //   957: fload #42
/*     */     //   959: fcmpg
/*     */     //   960: ifge -> 979
/*     */     //   963: fload #49
/*     */     //   965: fstore #41
/*     */     //   967: fload #21
/*     */     //   969: fstore #42
/*     */     //   971: fload #25
/*     */     //   973: fstore #43
/*     */     //   975: fload #26
/*     */     //   977: fstore #44
/*     */     //   979: fload #21
/*     */     //   981: fload #46
/*     */     //   983: fcmpl
/*     */     //   984: ifle -> 1003
/*     */     //   987: fload #49
/*     */     //   989: fstore #45
/*     */     //   991: fload #21
/*     */     //   993: fstore #46
/*     */     //   995: fload #25
/*     */     //   997: fstore #47
/*     */     //   999: fload #26
/*     */     //   1001: fstore #48
/*     */     //   1003: fload #37
/*     */     //   1005: fload #42
/*     */     //   1007: fload #46
/*     */     //   1009: fadd
/*     */     //   1010: fconst_2
/*     */     //   1011: fdiv
/*     */     //   1012: fcmpg
/*     */     //   1013: ifgt -> 1043
/*     */     //   1016: fload #38
/*     */     //   1018: fload #44
/*     */     //   1020: iload #4
/*     */     //   1022: i2f
/*     */     //   1023: fmul
/*     */     //   1024: fload #43
/*     */     //   1026: invokestatic atan2 : (FF)F
/*     */     //   1029: fsub
/*     */     //   1030: fstore #33
/*     */     //   1032: fload #41
/*     */     //   1034: iload #4
/*     */     //   1036: i2f
/*     */     //   1037: fmul
/*     */     //   1038: fstore #34
/*     */     //   1040: goto -> 1067
/*     */     //   1043: fload #38
/*     */     //   1045: fload #48
/*     */     //   1047: iload #4
/*     */     //   1049: i2f
/*     */     //   1050: fmul
/*     */     //   1051: fload #47
/*     */     //   1053: invokestatic atan2 : (FF)F
/*     */     //   1056: fsub
/*     */     //   1057: fstore #33
/*     */     //   1059: fload #45
/*     */     //   1061: iload #4
/*     */     //   1063: i2f
/*     */     //   1064: fmul
/*     */     //   1065: fstore #34
/*     */     //   1067: fload #15
/*     */     //   1069: fload #14
/*     */     //   1071: invokestatic atan2 : (FF)F
/*     */     //   1074: iload #13
/*     */     //   1076: i2f
/*     */     //   1077: fmul
/*     */     //   1078: fstore #35
/*     */     //   1080: aload_0
/*     */     //   1081: getfield rotation : F
/*     */     //   1084: fstore #36
/*     */     //   1086: fload #33
/*     */     //   1088: fload #35
/*     */     //   1090: fsub
/*     */     //   1091: ldc 57.295776
/*     */     //   1093: fmul
/*     */     //   1094: iload #11
/*     */     //   1096: i2f
/*     */     //   1097: fadd
/*     */     //   1098: fload #36
/*     */     //   1100: fsub
/*     */     //   1101: fstore #33
/*     */     //   1103: fload #33
/*     */     //   1105: ldc 180.0
/*     */     //   1107: fcmpl
/*     */     //   1108: ifle -> 1121
/*     */     //   1111: fload #33
/*     */     //   1113: ldc 360.0
/*     */     //   1115: fsub
/*     */     //   1116: fstore #33
/*     */     //   1118: goto -> 1136
/*     */     //   1121: fload #33
/*     */     //   1123: ldc -180.0
/*     */     //   1125: fcmpg
/*     */     //   1126: ifge -> 1136
/*     */     //   1129: fload #33
/*     */     //   1131: ldc 360.0
/*     */     //   1133: fadd
/*     */     //   1134: fstore #33
/*     */     //   1136: aload_0
/*     */     //   1137: fload #6
/*     */     //   1139: fload #7
/*     */     //   1141: fload #36
/*     */     //   1143: fload #33
/*     */     //   1145: fload #5
/*     */     //   1147: fmul
/*     */     //   1148: fadd
/*     */     //   1149: aload_0
/*     */     //   1150: getfield scaleX : F
/*     */     //   1153: aload_0
/*     */     //   1154: getfield scaleY : F
/*     */     //   1157: fconst_0
/*     */     //   1158: fconst_0
/*     */     //   1159: invokevirtual updateWorldTransform : (FFFFFFF)V
/*     */     //   1162: aload_1
/*     */     //   1163: getfield rotation : F
/*     */     //   1166: fstore #36
/*     */     //   1168: fload #34
/*     */     //   1170: fload #35
/*     */     //   1172: fadd
/*     */     //   1173: ldc 57.295776
/*     */     //   1175: fmul
/*     */     //   1176: aload_1
/*     */     //   1177: getfield shearX : F
/*     */     //   1180: fsub
/*     */     //   1181: iload #13
/*     */     //   1183: i2f
/*     */     //   1184: fmul
/*     */     //   1185: iload #12
/*     */     //   1187: i2f
/*     */     //   1188: fadd
/*     */     //   1189: fload #36
/*     */     //   1191: fsub
/*     */     //   1192: fstore #34
/*     */     //   1194: fload #34
/*     */     //   1196: ldc 180.0
/*     */     //   1198: fcmpl
/*     */     //   1199: ifle -> 1212
/*     */     //   1202: fload #34
/*     */     //   1204: ldc 360.0
/*     */     //   1206: fsub
/*     */     //   1207: fstore #34
/*     */     //   1209: goto -> 1227
/*     */     //   1212: fload #34
/*     */     //   1214: ldc -180.0
/*     */     //   1216: fcmpg
/*     */     //   1217: ifge -> 1227
/*     */     //   1220: fload #34
/*     */     //   1222: ldc 360.0
/*     */     //   1224: fadd
/*     */     //   1225: fstore #34
/*     */     //   1227: aload_1
/*     */     //   1228: fload #14
/*     */     //   1230: fload #15
/*     */     //   1232: fload #36
/*     */     //   1234: fload #34
/*     */     //   1236: fload #5
/*     */     //   1238: fmul
/*     */     //   1239: fadd
/*     */     //   1240: aload_1
/*     */     //   1241: getfield scaleX : F
/*     */     //   1244: aload_1
/*     */     //   1245: getfield scaleY : F
/*     */     //   1248: aload_1
/*     */     //   1249: getfield shearX : F
/*     */     //   1252: aload_1
/*     */     //   1253: getfield shearY : F
/*     */     //   1256: invokevirtual updateWorldTransform : (FFFFFFF)V
/*     */     //   1259: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #146	-> 0
/*     */     //   #147	-> 7
/*     */     //   #148	-> 11
/*     */     //   #150	-> 12
/*     */     //   #152	-> 42
/*     */     //   #153	-> 49
/*     */     //   #154	-> 54
/*     */     //   #155	-> 59
/*     */     //   #157	-> 65
/*     */     //   #158	-> 68
/*     */     //   #160	-> 71
/*     */     //   #161	-> 78
/*     */     //   #162	-> 83
/*     */     //   #164	-> 88
/*     */     //   #165	-> 95
/*     */     //   #166	-> 100
/*     */     //   #168	-> 108
/*     */     //   #169	-> 111
/*     */     //   #170	-> 141
/*     */     //   #171	-> 162
/*     */     //   #172	-> 167
/*     */     //   #173	-> 170
/*     */     //   #174	-> 182
/*     */     //   #176	-> 197
/*     */     //   #177	-> 203
/*     */     //   #178	-> 221
/*     */     //   #180	-> 239
/*     */     //   #181	-> 245
/*     */     //   #182	-> 252
/*     */     //   #183	-> 259
/*     */     //   #184	-> 266
/*     */     //   #185	-> 273
/*     */     //   #186	-> 306
/*     */     //   #187	-> 344
/*     */     //   #188	-> 354
/*     */     //   #189	-> 364
/*     */     //   #190	-> 402
/*     */     //   #192	-> 432
/*     */     //   #193	-> 437
/*     */     //   #194	-> 444
/*     */     //   #195	-> 477
/*     */     //   #196	-> 485
/*     */     //   #197	-> 492
/*     */     //   #198	-> 502
/*     */     //   #199	-> 515
/*     */     //   #200	-> 525
/*     */     //   #201	-> 535
/*     */     //   #202	-> 562
/*     */     //   #203	-> 565
/*     */     //   #204	-> 572
/*     */     //   #205	-> 579
/*     */     //   #206	-> 615
/*     */     //   #207	-> 637
/*     */     //   #208	-> 654
/*     */     //   #209	-> 670
/*     */     //   #210	-> 677
/*     */     //   #211	-> 686
/*     */     //   #212	-> 698
/*     */     //   #213	-> 708
/*     */     //   #214	-> 722
/*     */     //   #215	-> 745
/*     */     //   #216	-> 756
/*     */     //   #217	-> 775
/*     */     //   #218	-> 787
/*     */     //   #219	-> 805
/*     */     //   #222	-> 808
/*     */     //   #223	-> 821
/*     */     //   #224	-> 833
/*     */     //   #225	-> 840
/*     */     //   #226	-> 847
/*     */     //   #227	-> 855
/*     */     //   #228	-> 858
/*     */     //   #229	-> 862
/*     */     //   #231	-> 866
/*     */     //   #232	-> 873
/*     */     //   #233	-> 880
/*     */     //   #234	-> 888
/*     */     //   #235	-> 892
/*     */     //   #236	-> 896
/*     */     //   #238	-> 900
/*     */     //   #239	-> 919
/*     */     //   #240	-> 932
/*     */     //   #241	-> 942
/*     */     //   #242	-> 955
/*     */     //   #243	-> 963
/*     */     //   #244	-> 967
/*     */     //   #245	-> 971
/*     */     //   #246	-> 975
/*     */     //   #248	-> 979
/*     */     //   #249	-> 987
/*     */     //   #250	-> 991
/*     */     //   #251	-> 995
/*     */     //   #252	-> 999
/*     */     //   #254	-> 1003
/*     */     //   #255	-> 1016
/*     */     //   #256	-> 1032
/*     */     //   #258	-> 1043
/*     */     //   #259	-> 1059
/*     */     //   #262	-> 1067
/*     */     //   #263	-> 1080
/*     */     //   #264	-> 1086
/*     */     //   #265	-> 1103
/*     */     //   #266	-> 1111
/*     */     //   #267	-> 1121
/*     */     //   #268	-> 1136
/*     */     //   #269	-> 1162
/*     */     //   #270	-> 1168
/*     */     //   #271	-> 1194
/*     */     //   #272	-> 1202
/*     */     //   #273	-> 1212
/*     */     //   #274	-> 1227
/*     */     //   #275	-> 1259
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   59	6	11	os1	I
/*     */     //   62	3	13	s2	I
/*     */     //   105	3	12	os2	I
/*     */     //   170	27	15	cy	F
/*     */     //   182	15	16	cwx	F
/*     */     //   194	3	17	cwy	F
/*     */     //   477	85	35	cos	F
/*     */     //   562	3	33	a1	F
/*     */     //   515	50	34	a2	F
/*     */     //   787	21	33	a1	F
/*     */     //   805	3	34	a2	F
/*     */     //   686	122	41	q	F
/*     */     //   715	93	42	r0	F
/*     */     //   722	86	43	r1	F
/*     */     //   745	63	44	r	F
/*     */     //   1032	11	33	a1	F
/*     */     //   1040	3	34	a2	F
/*     */     //   586	481	35	aa	F
/*     */     //   593	474	36	bb	F
/*     */     //   606	461	37	dd	F
/*     */     //   615	452	38	ta	F
/*     */     //   647	420	39	c1	F
/*     */     //   654	413	40	c2	F
/*     */     //   811	256	41	minAngle	F
/*     */     //   815	252	42	minDist	F
/*     */     //   818	249	43	minX	F
/*     */     //   821	246	44	minY	F
/*     */     //   824	243	45	maxAngle	F
/*     */     //   827	240	46	maxDist	F
/*     */     //   830	237	47	maxX	F
/*     */     //   833	234	48	maxY	F
/*     */     //   919	148	49	angle	F
/*     */     //   0	1260	0	parent	Lcom/esotericsoftware/spine/Bone;
/*     */     //   0	1260	1	child	Lcom/esotericsoftware/spine/Bone;
/*     */     //   0	1260	2	targetX	F
/*     */     //   0	1260	3	targetY	F
/*     */     //   0	1260	4	bendDir	I
/*     */     //   0	1260	5	alpha	F
/*     */     //   18	1242	6	px	F
/*     */     //   24	1236	7	py	F
/*     */     //   30	1230	8	psx	F
/*     */     //   36	1224	9	psy	F
/*     */     //   42	1218	10	csx	F
/*     */     //   68	1192	11	os1	I
/*     */     //   111	1149	12	os2	I
/*     */     //   71	1189	13	s2	I
/*     */     //   117	1143	14	cx	F
/*     */     //   203	1057	15	cy	F
/*     */     //   221	1039	16	cwx	F
/*     */     //   239	1021	17	cwy	F
/*     */     //   123	1137	18	a	F
/*     */     //   129	1131	19	b	F
/*     */     //   135	1125	20	c	F
/*     */     //   141	1119	21	d	F
/*     */     //   162	1098	22	u	Z
/*     */     //   245	1015	23	pp	Lcom/esotericsoftware/spine/Bone;
/*     */     //   288	972	24	id	F
/*     */     //   297	963	25	x	F
/*     */     //   306	954	26	y	F
/*     */     //   325	935	27	tx	F
/*     */     //   344	916	28	ty	F
/*     */     //   383	877	29	dx	F
/*     */     //   402	858	30	dy	F
/*     */     //   420	840	31	l1	F
/*     */     //   432	828	32	l2	F
/*     */     //   1059	201	33	a1	F
/*     */     //   1067	193	34	a2	F
/*     */     //   1080	180	35	os	F
/*     */     //   1086	174	36	rotation	F
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\IkConstraint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */