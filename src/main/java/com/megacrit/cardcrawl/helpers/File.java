/*     */ package com.megacrit.cardcrawl.helpers;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class File
/*     */ {
/*  22 */   private static final Logger logger = LogManager.getLogger(File.class.getName());
/*     */   private String filepath;
/*     */   private byte[] data;
/*     */   
/*     */   public File(String filepath, String data) {
/*  27 */     this.filepath = filepath;
/*  28 */     this.data = data.getBytes(StandardCharsets.UTF_8);
/*     */   }
/*     */   
/*     */   public String getFilepath() {
/*  32 */     return this.filepath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save() {
/*  39 */     int MAX_RETRIES = 5;
/*  40 */     String localStoragePath = Gdx.files.getLocalStoragePath();
/*  41 */     Path destination = FileSystems.getDefault().getPath(localStoragePath + this.filepath, new String[0]);
/*  42 */     Path backup = FileSystems.getDefault().getPath(localStoragePath + this.filepath + ".backUp", new String[0]);
/*  43 */     Path parent = destination.getParent();
/*  44 */     logger.debug("Attempting to save file=" + destination);
/*     */     
/*  46 */     if (Files.exists(parent, new java.nio.file.LinkOption[0])) {
/*  47 */       if (Files.exists(destination, new java.nio.file.LinkOption[0])) {
/*     */         
/*  49 */         copyAndValidate(destination, backup, 5);
/*     */ 
/*     */         
/*  52 */         deleteFile(destination);
/*     */       } 
/*     */     } else {
/*     */       try {
/*  56 */         Files.createDirectories(parent, (FileAttribute<?>[])new FileAttribute[0]);
/*  57 */       } catch (IOException e) {
/*  58 */         logger.info("Failed to create directory", e);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  63 */     boolean success = writeAndValidate(destination, this.data, 5);
/*     */     
/*  65 */     if (success)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  76 */       logger.debug("Successfully saved file=" + destination.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   private static void copyAndValidate(Path source, Path target, int retry) {
/*  81 */     byte[] sourceData = new byte[0];
/*     */     try {
/*  83 */       sourceData = Files.readAllBytes(source);
/*  84 */       Files.copy(source, target, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*  85 */     } catch (IOException e) {
/*  86 */       if (retry <= 0) {
/*  87 */         logger.info("Failed to copy " + source
/*  88 */             .toString() + " to " + target.toString() + ", but the retry expired", e);
/*     */         
/*     */         return;
/*     */       } 
/*  92 */       logger.info("Failed to copy file=" + source.toString(), e);
/*     */       
/*  94 */       sleep(300);
/*  95 */       copyAndValidate(source, target, retry - 1);
/*     */     } 
/*  97 */     Exception err = validateWrite(target, sourceData);
/*  98 */     if (err != null) {
/*  99 */       if (retry <= 0) {
/* 100 */         logger.info("Failed to copy " + source
/* 101 */             .toString() + " to " + target.toString() + ", but the retry expired", err);
/*     */         
/*     */         return;
/*     */       } 
/* 105 */       logger.info("Failed to copy file=" + source.toString(), err);
/*     */       
/* 107 */       sleep(300);
/* 108 */       copyAndValidate(source, target, retry - 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void sleep(int milliseconds) {
/*     */     try {
/* 114 */       Thread.sleep(milliseconds);
/* 115 */     } catch (InterruptedException e) {
/* 116 */       logger.info(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void deleteFile(Path filepath) {
/*     */     try {
/* 122 */       Files.delete(filepath);
/* 123 */     } catch (IOException e) {
/* 124 */       logger.info("Failed to delete", e);
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
/*     */   private static Exception validateWrite(Path filepath, byte[] inMemoryBytes) {
/*     */     byte[] writtenBytes;
/*     */     try {
/* 140 */       writtenBytes = Files.readAllBytes(filepath);
/* 141 */     } catch (IOException e) {
/* 142 */       return e;
/*     */     } 
/* 144 */     boolean valid = Arrays.equals(writtenBytes, inMemoryBytes);
/* 145 */     if (!valid) {
/* 146 */       return new FileWriteValidationError("Not valid: written=" + 
/* 147 */           Arrays.toString(writtenBytes) + " vs inMemory=" + Arrays.toString(inMemoryBytes));
/*     */     }
/*     */     
/* 150 */     return null;
/*     */   }
/*     */   
/*     */   static boolean writeAndValidate(Path filepath, byte[] data, int retry) {
/*     */     try {
/* 155 */       Files.write(filepath, data, new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.SYNC });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 164 */     catch (Exception ex) {
/* 165 */       if (retry <= 0) {
/* 166 */         logger.info("Failed to write file " + filepath.toString() + ", but the retry expired.", ex);
/* 167 */         return false;
/*     */       } 
/* 169 */       logger.info("Failed to validate source=" + filepath.toString() + ", retrying...", ex);
/*     */       
/* 171 */       sleep(300);
/* 172 */       return writeAndValidate(filepath, data, retry - 1);
/*     */     } 
/* 174 */     Exception err = validateWrite(filepath, data);
/* 175 */     if (err != null) {
/* 176 */       if (retry <= 0) {
/* 177 */         logger.info("Failed to write file " + filepath.toString() + ", but the retry expired.", err);
/* 178 */         return false;
/*     */       } 
/* 180 */       logger.info("Failed to validate source=" + filepath.toString() + ", retrying...", err);
/*     */       
/* 182 */       sleep(300);
/* 183 */       return writeAndValidate(filepath, data, retry - 1);
/*     */     } 
/* 185 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\File.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */