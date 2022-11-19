/*     */ package com.megacrit.cardcrawl.metrics;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.Net;
/*     */ import com.badlogic.gdx.net.HttpRequestBuilder;
/*     */ import com.google.gson.Gson;
/*     */ import com.megacrit.cardcrawl.core.ExceptionHandler;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.GameDictionary;
/*     */ import java.io.Serializable;
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.security.MessageDigest;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BotDataUploader
/*     */   implements Runnable
/*     */ {
/*  25 */   private static final Logger logger = LogManager.getLogger(BotDataUploader.class.getName());
/*  26 */   private Gson gson = new Gson();
/*  27 */   private String url = System.getenv("STS_DATA_UPLOAD_URL");
/*     */   
/*     */   private GameDataType type;
/*     */   
/*     */   private String header;
/*     */   private ArrayList<String> data;
/*     */   
/*     */   public enum GameDataType
/*     */   {
/*  36 */     BANDITS, CRASH_DATA, DAILY_DATA, DEMO_EMBARK, VICTORY_DATA, CARD_DATA, ENEMY_DATA, RELIC_DATA, POTION_DATA, DAILY_MOD_DATA, BLIGHT_DATA, KEYWORD_DATA;
/*     */   }
/*     */   
/*     */   public void setValues(GameDataType type, String header, ArrayList<String> data) {
/*  40 */     this.type = type;
/*  41 */     this.header = header;
/*  42 */     this.data = data;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPost(HashMap<String, Serializable> eventData) {
/*  47 */     if (this.url == null) {
/*     */       return;
/*     */     }
/*  50 */     HashMap<String, Serializable> event = new HashMap<>();
/*     */     
/*     */     try {
/*  53 */       String hostname = InetAddress.getLocalHost().getHostName();
/*  54 */       MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
/*  55 */       messageDigest.update(hostname.getBytes());
/*  56 */     } catch (UnknownHostException|java.security.NoSuchAlgorithmException e) {
/*  57 */       e.printStackTrace();
/*  58 */       ExceptionHandler.handleException(e, logger);
/*     */     } 
/*  60 */     eventData.put("STS_DATA_UPLOAD_KEY", System.getenv("STS_DATA_UPLOAD_KEY"));
/*  61 */     event.putAll(eventData);
/*     */     
/*  63 */     String data = this.gson.toJson(event);
/*  64 */     logger.info("UPLOADING TO LEADER BOARD: " + data);
/*  65 */     HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
/*     */ 
/*     */     
/*  68 */     Net.HttpRequest httpRequest = requestBuilder.newRequest().method("POST").url(this.url).header("Content-Type", "application/json").header("Accept", "application/json").build();
/*  69 */     httpRequest.setContent(data);
/*  70 */     Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener()
/*     */         {
/*     */           public void handleHttpResponse(Net.HttpResponse httpResponse) {
/*  73 */             if (Settings.isDev) {
/*  74 */               BotDataUploader.logger.info("Bot Data Upload: http request response: " + httpResponse
/*  75 */                   .getStatus().getStatusCode() + " " + httpResponse
/*  76 */                   .getResultAsString());
/*     */             }
/*     */           }
/*     */ 
/*     */           
/*     */           public void failed(Throwable t) {
/*  82 */             if (Settings.isDev) {
/*  83 */               BotDataUploader.logger.info("Bot Data Upload: http request failed: " + t.toString());
/*     */             }
/*     */           }
/*     */ 
/*     */           
/*     */           public void cancelled() {
/*  89 */             if (Settings.isDev) {
/*  90 */               BotDataUploader.logger.info("Bot Data Upload: http request cancelled.");
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private void sendData(String table, String header, ArrayList<String> entries) {
/*  97 */     HashMap<String, Serializable> data = new HashMap<>(entries.size() + 1);
/*  98 */     data.put("event type", table + " data update");
/*  99 */     data.put("header", header);
/* 100 */     data.put("data", entries);
/* 101 */     sendPost(data);
/*     */   }
/*     */   
/*     */   public static void uploadDataAsync(GameDataType type, String header, ArrayList<String> data) {
/* 105 */     BotDataUploader poster = new BotDataUploader();
/* 106 */     poster.setValues(type, header, data);
/* 107 */     Thread t = new Thread(poster);
/* 108 */     t.setName("LeaderboardPoster");
/* 109 */     t.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 114 */     switch (this.type) {
/*     */       case CARD_DATA:
/* 116 */         sendData("card", this.header, this.data);
/*     */         return;
/*     */       case RELIC_DATA:
/* 119 */         sendData("relic", this.header, this.data);
/*     */         return;
/*     */       case ENEMY_DATA:
/* 122 */         sendData("enemy", this.header, this.data);
/*     */         return;
/*     */       case POTION_DATA:
/* 125 */         sendData("potion", this.header, this.data);
/*     */         return;
/*     */       case DAILY_MOD_DATA:
/* 128 */         sendData("daily mod", this.header, this.data);
/*     */         return;
/*     */       case BLIGHT_DATA:
/* 131 */         sendData("blight", this.header, this.data);
/*     */         return;
/*     */       case KEYWORD_DATA:
/* 134 */         sendData("keywords", this.header, this.data);
/*     */         return;
/*     */     } 
/* 137 */     logger.info("Unspecified/deprecated LeaderboardPosterType: " + this.type.name() + " in run()");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void uploadKeywordData() {
/* 143 */     TreeMap<String, String> keywords = GameDictionary.keywords;
/* 144 */     if (keywords.isEmpty()) {
/* 145 */       GameDictionary.initialize();
/*     */     }
/*     */     
/* 148 */     ArrayList<String> data = new ArrayList<>();
/* 149 */     for (String name : keywords.keySet()) {
/* 150 */       data.add(String.format("%s\t%s", new Object[] { name, keywords.get(name) }));
/*     */     } 
/* 152 */     uploadDataAsync(GameDataType.KEYWORD_DATA, "name\ttext", data);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\metrics\BotDataUploader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */