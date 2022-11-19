/*     */ package com.megacrit.cardcrawl.core;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.text.NumberFormat;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class SystemStats
/*     */ {
/*  10 */   private static final Logger logger = LogManager.getLogger(SystemStats.class.getName());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String diskRoot = "/";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getMaxMemory() {
/*  22 */     return Runtime.getRuntime().maxMemory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getAllocatedMemory() {
/*  32 */     return Runtime.getRuntime().totalMemory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getUnallocatedMemory() {
/*  41 */     return getMaxMemory() - getAllocatedMemory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getFreeMemory() {
/*  50 */     return Runtime.getRuntime().freeMemory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getUsedMemory() {
/*  59 */     return getAllocatedMemory() - getFreeMemory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long getTotalFreeMemory() {
/*  68 */     return getFreeMemory() + getMaxMemory() - getAllocatedMemory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static long toMb(long sizeInBytes) {
/*  77 */     return sizeInBytes / 1024L / 1024L;
/*     */   }
/*     */   
/*     */   private static String prettyBytes(long sizeInBytes, NumberFormat format) {
/*  81 */     return format.format(toMb(sizeInBytes));
/*     */   }
/*     */   
/*     */   public static void logMemoryStats() {
/*  85 */     StringBuilder sb = new StringBuilder();
/*  86 */     NumberFormat format = NumberFormat.getInstance();
/*  87 */     sb.append("Free Memory: ").append(prettyBytes(getFreeMemory(), format)).append("Mb\n");
/*  88 */     sb.append("Max Memory: ").append(prettyBytes(getMaxMemory(), format)).append("Mb\n");
/*  89 */     sb.append("Allocated Memory: ").append(prettyBytes(getAllocatedMemory(), format)).append("Mb\n");
/*  90 */     sb.append("Unallocated Memory: ").append(prettyBytes(getUnallocatedMemory(), format)).append("Mb\n");
/*  91 */     sb.append("Total Free Memory: ").append(prettyBytes(getTotalFreeMemory(), format)).append("Mb\n");
/*  92 */     sb.append("Used Memory: ").append(prettyBytes(getUsedMemory(), format)).append("Mb\n");
/*  93 */     logger.info("MEMORY STATS:\n" + sb.toString());
/*     */   }
/*     */   
/*     */   public static long getDiskSpaceTotal() {
/*  97 */     return (new File("/")).getTotalSpace();
/*     */   }
/*     */   
/*     */   public static long getDiskSpaceFree() {
/* 101 */     return (new File("/")).getFreeSpace();
/*     */   }
/*     */   
/*     */   public static long getDiskSpaceUsable() {
/* 105 */     return (new File("/")).getUsableSpace();
/*     */   }
/*     */   
/*     */   public static void logDiskStats() {
/* 109 */     StringBuilder sb = new StringBuilder();
/* 110 */     NumberFormat format = NumberFormat.getInstance();
/* 111 */     sb.append("Total Space: ").append(prettyBytes(getDiskSpaceTotal(), format)).append("Mb\n");
/* 112 */     sb.append("Usable Space: ").append(prettyBytes(getDiskSpaceUsable(), format)).append("Mb\n");
/* 113 */     sb.append("Free Space: ").append(prettyBytes(getDiskSpaceFree(), format)).append("Mb\n");
/* 114 */     logger.info("DISK STATS:\n" + sb.toString());
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\core\SystemStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */