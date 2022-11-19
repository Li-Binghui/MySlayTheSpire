/*     */ package com.megacrit.cardcrawl.integrations;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.Net;
/*     */ import com.badlogic.gdx.net.HttpRequestBuilder;
/*     */ import com.google.gson.Gson;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import java.io.Reader;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class SteelSeries
/*     */ {
/*  23 */   private final Logger logger = LogManager.getLogger(SteelSeries.class.getName());
/*  24 */   private final String gameName = "SLAY_THE_SPIRE";
/*     */   public Boolean isEnabled;
/*     */   private String url;
/*  27 */   private long timeAtLastHealthcheck = 0L;
/*     */   
/*     */   public SteelSeries() {
/*  30 */     String program_data = System.getenv("PROGRAMDATA");
/*  31 */     Path winPath = Paths.get(program_data + "/SteelSeries/SteelSeries Engine 3/coreProps.json", new String[0]);
/*  32 */     Path macPath = Paths.get("/Library/Application Support/SteelSeries Engine 3/coreProps.json", new String[0]);
/*  33 */     Boolean winExists = Boolean.valueOf(Files.exists(winPath, new java.nio.file.LinkOption[0]));
/*  34 */     Boolean macExists = Boolean.valueOf(Files.exists(macPath, new java.nio.file.LinkOption[0]));
/*  35 */     this.isEnabled = Boolean.valueOf((winExists.booleanValue() || macExists.booleanValue()));
/*  36 */     this.logger.info("enabled=" + this.isEnabled);
/*  37 */     if (!this.isEnabled.booleanValue())
/*     */       return; 
/*  39 */     String _url = winExists.booleanValue() ? getUrl(winPath) : getUrl(macPath);
/*  40 */     if (_url != null) {
/*  41 */       this.url = "http://" + _url;
/*     */     } else {
/*  43 */       this.logger.info("ERROR: url is null!");
/*     */     } 
/*  45 */     register();
/*  46 */     create_event_handler();
/*     */   }
/*     */   
/*     */   private String getUrl(Path path) {
/*  50 */     Gson gson = new Gson();
/*     */     try {
/*  52 */       Reader reader = Files.newBufferedReader(path);
/*  53 */       Map<?, ?> map = (Map<?, ?>)gson.fromJson(reader, Map.class);
/*  54 */       reader.close();
/*  55 */       return (String)map.get("address");
/*  56 */     } catch (Exception e) {
/*  57 */       e.printStackTrace();
/*  58 */       this.isEnabled = Boolean.valueOf(false);
/*     */       
/*  60 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/*  65 */     if (System.currentTimeMillis() - this.timeAtLastHealthcheck > 14000L) {
/*  66 */       doHealthCheck();
/*  67 */       this.timeAtLastHealthcheck = System.currentTimeMillis();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doHealthCheck() {
/*  76 */     if (!this.isEnabled.booleanValue())
/*     */       return; 
/*  78 */     Map<String, Object> data = new HashMap<>();
/*  79 */     data.put("game", "SLAY_THE_SPIRE");
/*  80 */     sendPost(this.url + "/game_heartbeat", data);
/*     */   }
/*     */   
/*     */   private void register() {
/*  84 */     if (!this.isEnabled.booleanValue())
/*     */       return; 
/*  86 */     Map<String, Object> data = new HashMap<>();
/*  87 */     data.put("game", "SLAY_THE_SPIRE");
/*  88 */     data.put("game_display_name", "Slay the Spire");
/*  89 */     data.put("developer", "MEGACRIT");
/*  90 */     sendPost(this.url + "/game_metadata", data);
/*     */   }
/*     */   
/*     */   private Map<String, Object> create_event_map(AbstractPlayer.PlayerClass character, Map<String, Integer> color) {
/*  94 */     Map<String, Object> c1 = new HashMap<>();
/*  95 */     c1.put("red", Integer.valueOf(0));
/*  96 */     c1.put("green", Integer.valueOf(0));
/*  97 */     c1.put("blue", Integer.valueOf(0));
/*  98 */     Map<String, Object> gradient = new HashMap<>();
/*  99 */     gradient.put("zero", c1);
/* 100 */     gradient.put("hundred", color);
/* 101 */     Map<String, Object> colorConfig = new HashMap<>();
/* 102 */     colorConfig.put("gradient", gradient);
/*     */     
/* 104 */     Map<String, Object> keyboardHandler = new HashMap<>();
/* 105 */     keyboardHandler.put("device-type", "keyboard");
/* 106 */     keyboardHandler.put("zone", "all");
/* 107 */     keyboardHandler.put("color", colorConfig);
/* 108 */     keyboardHandler.put("mode", "percent");
/*     */     
/* 110 */     Map<String, Object> mouseHandler = new HashMap<>();
/* 111 */     mouseHandler.put("device-type", "mouse");
/* 112 */     mouseHandler.put("zone", "all");
/* 113 */     mouseHandler.put("color", colorConfig);
/* 114 */     mouseHandler.put("mode", "percent");
/*     */     
/* 116 */     List<Map<String, Object>> handlers = new ArrayList<>();
/* 117 */     handlers.add(keyboardHandler);
/* 118 */     handlers.add(mouseHandler);
/* 119 */     Map<String, Object> data = new HashMap<>();
/* 120 */     data.put("game", "SLAY_THE_SPIRE");
/* 121 */     data.put("event", character.toString());
/* 122 */     data.put("min_value", Integer.valueOf(0));
/* 123 */     data.put("max_value", Integer.valueOf(100));
/* 124 */     data.put("icon_id", Integer.valueOf(0));
/* 125 */     data.put("handlers", handlers);
/* 126 */     return data;
/*     */   }
/*     */   
/*     */   private void create_event_handler() {
/* 130 */     if (!this.isEnabled.booleanValue())
/*     */       return; 
/* 132 */     Map<String, Integer> ironclad_color = new HashMap<>();
/* 133 */     ironclad_color.put("red", Integer.valueOf(255));
/* 134 */     ironclad_color.put("green", Integer.valueOf(0));
/* 135 */     ironclad_color.put("blue", Integer.valueOf(0));
/* 136 */     sendPost(this.url + "/bind_game_event", create_event_map(AbstractPlayer.PlayerClass.IRONCLAD, ironclad_color));
/*     */     
/* 138 */     Map<String, Integer> silent_color = new HashMap<>();
/* 139 */     silent_color.put("red", Integer.valueOf(0));
/* 140 */     silent_color.put("green", Integer.valueOf(255));
/* 141 */     silent_color.put("blue", Integer.valueOf(0));
/* 142 */     sendPost(this.url + "/bind_game_event", create_event_map(AbstractPlayer.PlayerClass.THE_SILENT, silent_color));
/*     */     
/* 144 */     Map<String, Integer> defect_color = new HashMap<>();
/* 145 */     defect_color.put("red", Integer.valueOf(0));
/* 146 */     defect_color.put("green", Integer.valueOf(0));
/* 147 */     defect_color.put("blue", Integer.valueOf(255));
/* 148 */     sendPost(this.url + "/bind_game_event", create_event_map(AbstractPlayer.PlayerClass.DEFECT, defect_color));
/*     */     
/* 150 */     Map<String, Integer> watcher_color = new HashMap<>();
/* 151 */     watcher_color.put("red", Integer.valueOf(148));
/* 152 */     watcher_color.put("green", Integer.valueOf(0));
/* 153 */     watcher_color.put("blue", Integer.valueOf(211));
/* 154 */     sendPost(this.url + "/bind_game_event", create_event_map(AbstractPlayer.PlayerClass.WATCHER, watcher_color));
/*     */   }
/*     */   
/*     */   public void event_character_chosen(AbstractPlayer.PlayerClass character) {
/* 158 */     if (!this.isEnabled.booleanValue())
/*     */       return; 
/* 160 */     Map<String, Object> value = new HashMap<>();
/* 161 */     value.put("value", Integer.valueOf(100));
/* 162 */     Map<String, Object> data = new HashMap<>();
/* 163 */     data.put("game", "SLAY_THE_SPIRE");
/* 164 */     data.put("event", character.toString());
/* 165 */     data.put("data", value);
/* 166 */     sendPost(this.url + "/game_event", data);
/*     */   }
/*     */   
/*     */   private void sendPost(String url, Map<String, Object> data) {
/* 170 */     Gson gson = new Gson();
/* 171 */     String content = gson.toJson(data);
/* 172 */     this.logger.info("HTTP Request: url=" + url + " data=" + content);
/* 173 */     HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     Net.HttpRequest httpRequest = requestBuilder.newRequest().method("POST").url(url).header("Content-Type", "application/json").header("Accept", "application/json").header("User-Agent", "sts/" + CardCrawlGame.TRUE_VERSION_NUM).build();
/* 179 */     httpRequest.setContent(content);
/* 180 */     Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener()
/*     */         {
/*     */           public void handleHttpResponse(Net.HttpResponse httpResponse) {
/* 183 */             SteelSeries.this.logger.info("http request status: " + httpResponse
/* 184 */                 .getStatus().getStatusCode() + " response: " + httpResponse
/* 185 */                 .getResultAsString());
/*     */           }
/*     */ 
/*     */           
/*     */           public void failed(Throwable t) {
/* 190 */             SteelSeries.this.logger.info("http request failed: " + t.toString());
/*     */           }
/*     */ 
/*     */           
/*     */           public void cancelled() {
/* 195 */             SteelSeries.this.logger.info("http request cancelled.");
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\SteelSeries.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */