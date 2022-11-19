/*     */ package com.megacrit.cardcrawl.core;
/*     */ 
/*     */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DisplayConfig
/*     */ {
/*  17 */   private static final Logger logger = LogManager.getLogger(DisplayConfig.class.getName());
/*     */   private static final String DISPLAY_CONFIG_LOC = "info.displayconfig";
/*     */   private static final int DEFAULT_W = 1280;
/*     */   private static final int DEFAULT_H = 720;
/*     */   private static final int DEFAULT_FPS_LIMIT = 60;
/*     */   private static final boolean DEFAULT_FS = false;
/*     */   private static final boolean DEFAULT_WFS = false;
/*     */   private static final boolean DEFAULT_VSYNC = true;
/*     */   private int width;
/*     */   private int height;
/*     */   private int fps_limit;
/*     */   private boolean isFullscreen;
/*     */   private boolean wfs;
/*     */   private boolean vsync;
/*     */   
/*     */   private DisplayConfig(int width, int height, int fps_limit, boolean isFullscreen, boolean wfs, boolean vsync) {

/*  33 */     this.width = width;
/*  34 */     this.height = height;
/*  35 */     this.fps_limit = fps_limit;
              //todo 默认全屏
              //isFullscreen=true;
/*  36 */     this.isFullscreen = isFullscreen;
/*  37 */     this.wfs = wfs;
/*  38 */     this.vsync = vsync;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  47 */     HashMap<String, Object> hm = new HashMap<>();
/*  48 */     hm.put("width", Integer.valueOf(this.width));
/*  49 */     hm.put("height", Integer.valueOf(this.height));
/*  50 */     hm.put("fps_limit", Integer.valueOf(this.fps_limit));
/*  51 */     hm.put("isFullscreen", Boolean.valueOf(this.isFullscreen));
/*  52 */     hm.put("wfs", Boolean.valueOf(this.wfs));
/*  53 */     hm.put("vsync", Boolean.valueOf(this.vsync));
/*  54 */     return hm.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DisplayConfig readConfig() {
/*     */     DisplayConfig dc;
/*  61 */     logger.info("Reading info.displayconfig");
/*  62 */     ArrayList<String> configLines = readDisplayConfFile();
/*     */     
/*  64 */     if (configLines.size() < 4) {
/*  65 */       createNewConfig();
/*  66 */       return readConfig();
/*     */     } 
/*  68 */     if (configLines.size() == 5) {
/*  69 */       appendFpsLimit(configLines);
/*  70 */       return readConfig();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  80 */       dc = new DisplayConfig(Integer.parseInt(((String)configLines.get(0)).trim()), Integer.parseInt(((String)configLines.get(1)).trim()), Integer.parseInt(((String)configLines.get(2)).trim()), Boolean.parseBoolean(((String)configLines.get(3)).trim()), Boolean.parseBoolean(((String)configLines.get(4)).trim()), Boolean.parseBoolean(((String)configLines.get(5)).trim()));
/*  81 */     } catch (Exception e) {
/*     */       
/*  83 */       logger.info("Failed to parse the info.displayconfig going to recreate it with defaults.");
/*  84 */       createNewConfig();
/*  85 */       return readConfig();
/*     */     } 
/*  87 */     logger.info("DisplayConfig successfully read.");
/*  88 */     return dc;
/*     */   }
/*     */   
/*     */   private static ArrayList<String> readDisplayConfFile() {
/*  92 */     ArrayList<String> configLines = new ArrayList<>();
/*  93 */     Scanner s = null;
/*     */     try {
/*  95 */       s = new Scanner(new File("info.displayconfig"));
/*  96 */       while (s.hasNextLine()) {
/*  97 */         configLines.add(s.nextLine());
/*     */       }
/*  99 */     } catch (FileNotFoundException e) {
/*     */       
/* 101 */       logger.info("File info.displayconfig not found, creating with defaults.");
/*     */       
/* 103 */       createNewConfig();
/* 104 */       return readDisplayConfFile();
/*     */     } finally {
/* 106 */       if (s != null) {
/* 107 */         s.close();
/*     */       }
/*     */     } 
/* 110 */     return configLines;
/*     */   }
/*     */   
/*     */   public static void writeDisplayConfigFile(int w, int h, int fps, boolean fs, boolean wfs, boolean vs) {
/* 114 */     PrintWriter writer = null;
/*     */     try {
/* 116 */       writer = new PrintWriter("info.displayconfig", "UTF-8");
/* 117 */       writer.println(Integer.toString(w));
/* 118 */       writer.println(Integer.toString(h));
/* 119 */       writer.println(Integer.toString(fps));
/* 120 */       writer.println(Boolean.toString(fs));
/* 121 */       writer.println(Boolean.toString(wfs));
/* 122 */       writer.println(Boolean.toString(vs));
/* 123 */     } catch (FileNotFoundException|java.io.UnsupportedEncodingException e) {
/* 124 */       ExceptionHandler.handleException(e, logger);
/*     */     } finally {
/* 126 */       if (writer != null) {
/* 127 */         writer.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void createNewConfig() {
/* 133 */     logger.info("Creating new config with default values...");
              //todo 修改默认尺寸
/* 134 */     writeDisplayConfigFile(1280, 720, 60, false, false, true);
//              writeDisplayConfigFile(1600, 1200, 60, false, false, true);
/*     */   }
/*     */   
/*     */   private static void appendFpsLimit(ArrayList<String> configLines) {
/* 138 */     logger.info("Updating config...");
/*     */     try {
/* 140 */       writeDisplayConfigFile(
/* 141 */           Integer.parseInt(((String)configLines.get(0)).trim()), 
/* 142 */           Integer.parseInt(((String)configLines.get(1)).trim()), 60, 
/*     */           
/* 144 */           Boolean.parseBoolean(((String)configLines.get(2)).trim()), 
/* 145 */           Boolean.parseBoolean(((String)configLines.get(3)).trim()), true);
/*     */     }
/* 147 */     catch (Exception e) {
/*     */       
/* 149 */       logger.info("Failed to parse the info.displayconfig going to recreate it with defaults.");
/*     */ 
/*     */       
/* 152 */       createNewConfig();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 157 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 161 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getMaxFPS() {
/* 165 */     return this.fps_limit;
/*     */   }
/*     */   
/*     */   public boolean getIsFullscreen() {
/* 169 */     return this.isFullscreen;
/*     */   }
/*     */   
/*     */   public boolean getWFS() {
/* 173 */     return this.wfs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsVsync() {
/* 180 */     return this.vsync;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\core\DisplayConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */