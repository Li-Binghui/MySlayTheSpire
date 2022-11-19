/*     */ package de.robojumper.ststwitch;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.files.FileHandle;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Optional;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class TwitchConfig
/*     */ {
/*  19 */   private static final Logger logger = LogManager.getLogger(TwitchConfig.class.getName());
/*     */   public static final String configFilename = "twitchconfig.txt";
/*     */   private static final float DEFAULT_VOTE_TIMER = 35.0F;
/*     */   private boolean enabled;
/*     */   private String username;
/*     */   private String token;
/*     */   private String channel;
/*     */   private float timer;
/*     */   
/*     */   private TwitchConfig(Boolean enabled, String username, String token, String channel, float timer) {
/*  29 */     this.enabled = enabled.booleanValue();
/*  30 */     this.username = username;
/*  31 */     this.token = token;
/*  32 */     this.channel = channel;
/*  33 */     this.timer = timer;
/*     */   }
/*     */   
/*     */   public static Optional<TwitchConfig> readConfig() {
/*  37 */     String username = "";
/*  38 */     String token = "";
/*  39 */     String channel = "";
/*  40 */     String unparsedTimer = "";
/*  41 */     String unparsedEnabled = "";
/*     */     
/*  43 */     try (InputStream in = new FileInputStream("twitchconfig.txt")) {
/*  44 */       Properties prop = new Properties();
/*  45 */       prop.load(in);
/*     */ 
/*     */       
/*  48 */       if (!validateProps(prop)) {
/*  49 */         return (Optional)Optional.empty();
/*     */       }
/*     */ 
/*     */       
/*  53 */       username = prop.getProperty("user").trim();
/*  54 */       token = prop.getProperty("token").trim();
/*  55 */       channel = prop.getProperty("channel").trim();
/*  56 */       unparsedTimer = prop.getProperty("timer").trim();
/*  57 */       unparsedEnabled = prop.getProperty("enabled").trim();
/*  58 */     } catch (IOException e) {
/*  59 */       logger.info("Did not connect to twitch chat.");
/*  60 */       logger.error(e);
/*     */     } 
/*     */ 
/*     */     
/*  64 */     boolean enabled = Boolean.parseBoolean(unparsedEnabled);
/*     */ 
/*     */     
/*  67 */     float timer = 35.0F;
/*  68 */     if (!unparsedTimer.equals("")) {
/*  69 */       float parsed = 0.0F;
/*     */       try {
/*  71 */         parsed = Float.parseFloat(unparsedTimer);
/*  72 */       } catch (NumberFormatException e) {
/*  73 */         logger.info("Could not parse timer=" + unparsedTimer);
/*     */       } 
/*     */       
/*  76 */       if (parsed > 0.0F && parsed < 604800.0F) {
/*  77 */         timer = parsed;
/*     */       } else {
/*  79 */         logger.info("Twitch config 'timer' not a valid int between 0 and 604800, using default: 35.0");
/*     */       } 
/*     */     } else {
/*     */       
/*  83 */       logger.info("Twitch config 'timer' value empty, using default: 35.0");
/*     */     } 
/*     */ 
/*     */     
/*  87 */     if (channel.equals("")) {
/*  88 */       channel = "#" + username.toLowerCase();
/*  89 */     } else if (!channel.startsWith("#")) {
/*  90 */       channel = "#" + channel;
/*     */     } 
/*     */ 
/*     */     
/*  94 */     channel = channel.toLowerCase();
/*     */     
/*  96 */     if (!token.startsWith("oauth:")) {
/*  97 */       token = "oauth:" + token;
/*     */     }
/*  99 */     return Optional.of(new TwitchConfig(Boolean.valueOf(enabled), username, token, channel, timer));
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean validateProps(Properties prop) {
/* 104 */     Set<String> propNames = prop.stringPropertyNames();
/* 105 */     Set<String> requiredProps = new HashSet<>(Arrays.asList(new String[] { "user", "token", "channel", "timer", "enabled" }));
/* 106 */     for (String reqProp : requiredProps) {
/* 107 */       if (!propNames.contains(reqProp)) {
/*     */         
/* 109 */         logger.info("WARNING: twitchconfig.txt does not contain the property '" + reqProp + "'");
/* 110 */         return false;
/*     */       } 
/*     */     } 
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createConfig() {
/* 120 */     FileHandle exampleConfig = Gdx.files.internal("twitchconfig.txt.example");
/* 121 */     if (exampleConfig.exists()) {
/* 122 */       FileHandle configTarget = Gdx.files.local("twitchconfig.txt");
/* 123 */       if (!configTarget.exists()) {
/* 124 */         logger.info("The 'twitchconfig.txt' does not exist, creating it from this example : " + configTarget
/*     */             
/* 126 */             .path());
/* 127 */         exampleConfig.copyTo(configTarget);
/* 128 */       } else if (configTarget.length() == 0L) {
/* 129 */         logger.info("The 'twitchconfig.txt' is empty, creating it from this example : " + configTarget
/* 130 */             .path());
/* 131 */         exampleConfig.copyTo(configTarget);
/*     */       } else {
/* 133 */         logger.info("The 'twitchconfig.txt' already exists, not overwriting it.");
/*     */       } 
/*     */     } else {
/* 136 */       logger.warn("The '" + exampleConfig.name() + "' does not appear to exist: " + exampleConfig.path());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean configFileExists() {
/* 141 */     return Gdx.files.local("twitchconfig.txt").exists();
/*     */   }
/*     */   
/*     */   String getUsername() {
/* 145 */     return this.username;
/*     */   }
/*     */   
/*     */   String getToken() {
/* 149 */     return this.token;
/*     */   }
/*     */   
/*     */   String getChannel() {
/* 153 */     return this.channel;
/*     */   }
/*     */   
/*     */   public float getTimer() {
/* 157 */     return this.timer;
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/* 161 */     return this.enabled;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\de\robojumper\ststwitch\TwitchConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */