/*     */ package com.megacrit.cardcrawl.desktop;
/*     */ 
/*     */ import com.badlogic.gdx.ApplicationListener;
/*     */ import com.badlogic.gdx.Files;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
/*     */ import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.DisplayConfig;
/*     */ import com.megacrit.cardcrawl.core.ExceptionHandler;
/*     */ import com.megacrit.cardcrawl.core.SystemStats;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Locale;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DesktopLauncher
/*     */ {
/*     */   static {
/*  22 */     System.setProperty("log4j.configurationFile", "log4j2.xml");
/*     */   }
/*  24 */   private static final Logger logger = LogManager.getLogger(DesktopLauncher.class.getName());
/*     */   
/*     */   public static void main(String[] arg) {
/*  27 */     logger.info("time: " + System.currentTimeMillis());
/*  28 */     logger.info("version: " + CardCrawlGame.TRUE_VERSION_NUM);
/*  29 */     logger.info("libgdx:  1.9.5");
/*  30 */     logger.info("default_locale: " + Locale.getDefault());
/*  31 */     logger.info("default_charset: " + Charset.defaultCharset());
/*  32 */     logger.info("default_encoding: " + System.getProperty("file.encoding"));
/*  33 */     logger.info("java_version: " + System.getProperty("java.version"));
/*  34 */     logger.info("os_arch: " + System.getProperty("os.arch"));
/*  35 */     logger.info("os_name: " + System.getProperty("os.name"));
/*  36 */     logger.info("os_version: " + System.getProperty("os.version"));
/*  37 */     SystemStats.logMemoryStats();
/*  38 */     SystemStats.logDiskStats();
/*     */ 
/*     */     
/*     */     try {
/*  42 */       LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
/*  43 */       config.setDisplayModeCallback = new STSDisplayModeCallback();
/*     */       
/*  45 */       config.addIcon("images/ui/icon.png", Files.FileType.Internal);
/*  46 */       config.resizable = false;
/*  47 */       config.title = "My Slay the Spire";
/*  48 */       loadSettings(config);
/*  49 */       logger.info("Launching application...");
/*  50 */       new LwjglApplication((ApplicationListener)new CardCrawlGame(config.preferencesDirectory), config);
/*  51 */     } catch (Exception e) {
/*  52 */       logger.info("Exception occurred while initializing application!");
/*  53 */       ExceptionHandler.handleException(e, logger);
/*  54 */       Gdx.app.exit();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void loadSettings(LwjglApplicationConfiguration config) {
/*  59 */     DisplayConfig displayConf = DisplayConfig.readConfig();
/*     */ 
/*     */     
/*  62 */     if (displayConf.getWidth() < 800 || displayConf.getHeight() < 450) {
/*  63 */       logger.info("[ERROR] Display Config set lower than minimum allowed, resetting config.");
/*  64 */       config.width = 1280;
/*  65 */       config.height = 720;
/*  66 */       DisplayConfig.writeDisplayConfigFile(1280, 720, displayConf
/*     */ 
/*     */           
/*  69 */           .getMaxFPS(), displayConf
/*  70 */           .getIsFullscreen(), displayConf
/*  71 */           .getWFS(), displayConf
/*  72 */           .getIsVsync());
/*     */     } else {
/*  74 */       config.height = displayConf.getHeight();
/*  75 */       config.width = displayConf.getWidth();
/*     */     } 
/*     */     
/*  78 */     config.foregroundFPS = displayConf.getMaxFPS();
/*  79 */     config.backgroundFPS = config.foregroundFPS;
/*     */     
/*  81 */     if (displayConf.getIsFullscreen()) {
/*  82 */       logger.info("[FULLSCREEN_MODE]");
/*  83 */       System.setProperty("org.lwjgl.opengl.Display.enableOSXFullscreenModeAPI", "true");
/*  84 */       System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
/*  85 */       config.fullscreen = true;
/*  86 */       config.height = displayConf.getHeight();
/*  87 */       config.width = displayConf.getWidth();
/*  88 */       logger.info("Running the game in: " + config.width + " x " + config.height);
/*     */     } else {
/*  90 */       config.fullscreen = false;
/*     */ 
/*     */       
/*  93 */       if (displayConf.getWFS() && config.width == (LwjglApplicationConfiguration.getDesktopDisplayMode()).width && config.height == 
/*  94 */         (LwjglApplicationConfiguration.getDesktopDisplayMode()).height) {
/*  95 */         logger.info("[BORDERLESS_FULLSCREEN_MODE]");
/*  96 */         System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
/*  97 */         config.width = (LwjglApplicationConfiguration.getDesktopDisplayMode()).width;
/*  98 */         config.height = (LwjglApplicationConfiguration.getDesktopDisplayMode()).height;
/*  99 */         logger.info("Running the game in: " + config.width + " x " + config.height);
/*     */       } else {
/* 101 */         logger.info("[WINDOWED_MODE]");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     if (config.fullscreen && (displayConf.getWidth() > (LwjglApplicationConfiguration.getDesktopDisplayMode()).width || displayConf
/* 109 */       .getHeight() > (LwjglApplicationConfiguration.getDesktopDisplayMode()).height)) {
/* 110 */       logger.info("[ERROR] Monitor resolution is lower than config, resetting config.");
/* 111 */       config.width = (LwjglApplicationConfiguration.getDesktopDisplayMode()).width;
/* 112 */       config.height = (LwjglApplicationConfiguration.getDesktopDisplayMode()).height;
/* 113 */       DisplayConfig.writeDisplayConfigFile(config.width, config.height, displayConf
/*     */ 
/*     */           
/* 116 */           .getMaxFPS(), false, false, displayConf
/*     */ 
/*     */           
/* 119 */           .getIsVsync());
/*     */     } 
/*     */ 
/*     */     
/* 123 */     config.vSyncEnabled = displayConf.getIsVsync();
/* 124 */     logger.info("Settings successfully loaded");
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\desktop\DesktopLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */