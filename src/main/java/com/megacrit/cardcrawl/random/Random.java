/*     */ package com.megacrit.cardcrawl.random;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Random
/*     */ {
/*  14 */   private static final Logger logger = LogManager.getLogger(Random.class.getName());
/*     */   public RandomXS128 random;
/*  16 */   public int counter = 0;
/*     */   
/*     */   public Random() {
/*  19 */     this(Long.valueOf(MathUtils.random(9999L)), MathUtils.random(99));
/*     */   }
/*     */   
/*     */   public Random(Long seed) {
/*  23 */     this.random = new RandomXS128(seed.longValue());
/*     */   }
/*     */   
/*     */   public Random(Long seed, int counter) {
/*  27 */     this.random = new RandomXS128(seed.longValue());
/*  28 */     for (int i = 0; i < counter; i++) {
/*  29 */       random(999);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Random copy() {
/*  40 */     Random copied = new Random();
/*  41 */     copied.random = new RandomXS128(this.random.getState(0), this.random.getState(1));
/*  42 */     copied.counter = this.counter;
/*  43 */     return copied;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCounter(int targetCounter) {
/*  52 */     if (this.counter < targetCounter) {
/*  53 */       int count = targetCounter - this.counter;
/*  54 */       for (int i = 0; i < count; i++) {
/*  55 */         randomBoolean();
/*     */       }
/*     */     } else {
/*  58 */       logger.info("ERROR: Counter is already higher than target counter!");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int random(int range) {
/*  64 */     this.counter++;
/*  65 */     return this.random.nextInt(range + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int random(int start, int end) {
/*  70 */     this.counter++;
/*  71 */     return start + this.random.nextInt(end - start + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public long random(long range) {
/*  76 */     this.counter++;
/*  77 */     return (long)(this.random.nextDouble() * range);
/*     */   }
/*     */ 
/*     */   
/*     */   public long random(long start, long end) {
/*  82 */     this.counter++;
/*  83 */     return start + (long)(this.random.nextDouble() * (end - start));
/*     */   }
/*     */ 
/*     */   
/*     */   public long randomLong() {
/*  88 */     this.counter++;
/*  89 */     return this.random.nextLong();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean randomBoolean() {
/*  94 */     this.counter++;
/*  95 */     return this.random.nextBoolean();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean randomBoolean(float chance) {
/* 100 */     this.counter++;
/* 101 */     return (this.random.nextFloat() < chance);
/*     */   }
/*     */ 
/*     */   
/*     */   public float random() {
/* 106 */     this.counter++;
/* 107 */     return this.random.nextFloat();
/*     */   }
/*     */ 
/*     */   
/*     */   public float random(float range) {
/* 112 */     this.counter++;
/* 113 */     return this.random.nextFloat() * range;
/*     */   }
/*     */ 
/*     */   
/*     */   public float random(float start, float end) {
/* 118 */     this.counter++;
/* 119 */     return start + this.random.nextFloat() * (end - start);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\random\Random.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */