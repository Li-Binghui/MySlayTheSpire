/*     */ package net.arikia.dev.drpc;
/*     */ 
/*     */ import com.sun.jna.Library;
/*     */ import com.sun.jna.Native;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
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
/*     */ public final class DiscordRPC
/*     */ {
/*     */   private static final String DLL_VERSION = "3.3.0";
/*     */   private static boolean isInitialized = false;
/*     */   
/*     */   public static void discordInitialize(String applicationId, DiscordEventHandlers handlers, boolean autoRegister) {
/*  27 */     discordInitialize(applicationId, handlers, autoRegister, null, null);
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
/*     */   public static void discordInitialize(String applicationId, DiscordEventHandlers handlers, boolean autoRegister, String steamId) {
/*  39 */     discordInitialize(applicationId, handlers, autoRegister, steamId, null);
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
/*     */   public static void discordInitialize(String applicationId, DiscordEventHandlers handlers, boolean autoRegister, String steamId, File dllUnpackPath) {
/*  51 */     if (dllUnpackPath == null) {
/*  52 */       isInitialized = loadDLL();
/*     */     } else {
/*  54 */       isInitialized = loadDLL(dllUnpackPath);
/*     */     } 
/*  56 */     DLL.INSTANCE.Discord_Initialize(applicationId, handlers, autoRegister ? 1 : 0, steamId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void discordRegister(String applicationId, String command) {
/*  67 */     DLL.INSTANCE.Discord_Register(applicationId, command);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void discordRegisterSteam(String applicationId, String steamId) {
/*  78 */     DLL.INSTANCE.Discord_RegisterSteamGame(applicationId, steamId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void discordUpdateEventHandlers(DiscordEventHandlers handlers) {
/*  87 */     DLL.INSTANCE.Discord_UpdateHandlers(handlers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void discordShutdown() {
/*  94 */     DLL.INSTANCE.Discord_Shutdown();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void discordRunCallbacks() {
/* 102 */     DLL.INSTANCE.Discord_RunCallbacks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void discordUpdatePresence(DiscordRichPresence presence) {
/* 111 */     DLL.INSTANCE.Discord_UpdatePresence(presence);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void discordClearPresence() {
/* 119 */     DLL.INSTANCE.Discord_ClearPresence();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void discordRespond(String userId, DiscordReply reply) {
/* 129 */     DLL.INSTANCE.Discord_Respond(userId, reply.reply);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean loadDLL() {
/*     */     String finalPath;
/*     */     String tempPath;
/* 139 */     if (OSUtil.isMac()) {
/* 140 */       String name = System.mapLibraryName("discord-rpc");
/* 141 */       File homeDir = new File(System.getProperty("user.home") + "/Library/Application Support/");
/* 142 */       finalPath = "/darwin/" + name;
/* 143 */       tempPath = homeDir + "/discord-rpc/" + name;
/* 144 */     } else if (OSUtil.isWindows()) {
/* 145 */       String name = System.mapLibraryName("discord-rpc");
/* 146 */       File homeDir = new File(System.getenv("TEMP"));
/* 147 */       boolean is64bit = System.getProperty("sun.arch.data.model").equals("64");
/* 148 */       finalPath = is64bit ? ("/win-x64/" + name) : ("win-x86/" + name);
/* 149 */       tempPath = homeDir + "\\discord-rpc\\" + name;
/*     */     } else {
/* 151 */       String name = System.mapLibraryName("discord-rpc");
/* 152 */       File homeDir = new File(System.getProperty("user.home"), ".discord-rpc");
/* 153 */       finalPath = "/linux/" + name;
/* 154 */       tempPath = homeDir + "/" + name;
/*     */     } 
/*     */     
/* 157 */     return loadLib(finalPath, new File(tempPath));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean loadDLL(File tempPath) {
/*     */     String name;
/*     */     String finalPath;
/* 165 */     if (OSUtil.isMac()) {
/* 166 */       name = System.mapLibraryName("discord-rpc");
/* 167 */       finalPath = "/darwin/" + name;
/* 168 */     } else if (OSUtil.isWindows()) {
/* 169 */       name = System.mapLibraryName("discord-rpc");
/* 170 */       boolean is64bit = System.getProperty("sun.arch.data.model").equals("64");
/* 171 */       finalPath = is64bit ? ("/win-x64/" + name) : ("win-x86/" + name);
/*     */     } else {
/* 173 */       name = System.mapLibraryName("discord-rpc");
/* 174 */       finalPath = "/linux/" + name;
/*     */     } 
/*     */     
/* 177 */     return loadLib(finalPath, new File(tempPath, name));
/*     */   }
/*     */   
/*     */   private static boolean loadLib(String source, File target) {
/* 181 */     try(InputStream in = DiscordRPC.class.getResourceAsStream(source); OutputStream out = openOutputStream(target)) {
/* 182 */       copyFile(in, out);
/* 183 */       target.deleteOnExit();
/* 184 */     } catch (IOException e) {
/* 185 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 188 */     System.load(target.getAbsolutePath());
/* 189 */     return true;
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
/*     */   public enum DiscordReply
/*     */   {
/* 202 */     NO(0),
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     YES(1),
/*     */ 
/*     */ 
/*     */     
/* 211 */     IGNORE(2);
/*     */ 
/*     */     
/*     */     public final int reply;
/*     */ 
/*     */ 
/*     */     
/*     */     DiscordReply(int reply) {
/* 219 */       this.reply = reply;
/*     */     } } private static interface DLL extends Library { void Discord_Initialize(String param1String1, DiscordEventHandlers param1DiscordEventHandlers, int param1Int, String param1String2);
/*     */     void Discord_Register(String param1String1, String param1String2);
/*     */     void Discord_RegisterSteamGame(String param1String1, String param1String2);
/*     */     void Discord_UpdateHandlers(DiscordEventHandlers param1DiscordEventHandlers);
/*     */     void Discord_Shutdown();
/* 225 */     public static final DLL INSTANCE = (DLL)Native.loadLibrary("discord-rpc", DLL.class);
/*     */ 
/*     */     
/*     */     void Discord_RunCallbacks();
/*     */ 
/*     */     
/*     */     void Discord_UpdatePresence(DiscordRichPresence param1DiscordRichPresence);
/*     */ 
/*     */     
/*     */     void Discord_ClearPresence();
/*     */ 
/*     */     
/*     */     void Discord_Respond(String param1String, int param1Int); }
/*     */ 
/*     */   
/*     */   private static void copyFile(InputStream input, OutputStream output) throws IOException {
/* 241 */     byte[] buffer = new byte[4096];
/*     */     int n;
/* 243 */     while (-1 != (n = input.read(buffer))) {
/* 244 */       output.write(buffer, 0, n);
/*     */     }
/*     */   }
/*     */   
/*     */   private static FileOutputStream openOutputStream(File file) throws IOException {
/* 249 */     if (file.exists()) {
/* 250 */       if (file.isDirectory()) {
/* 251 */         throw new IOException("File '" + file + "' exists but is a directory");
/*     */       }
/* 253 */       if (!file.canWrite()) {
/* 254 */         throw new IOException("File '" + file + "' cannot be written to");
/*     */       }
/*     */     } else {
/* 257 */       File parent = file.getParentFile();
/* 258 */       if (parent != null && 
/* 259 */         !parent.mkdirs() && !parent.isDirectory()) {
/* 260 */         throw new IOException("Directory '" + parent + "' could not be created");
/*     */       }
/*     */     } 
/*     */     
/* 264 */     return new FileOutputStream(file);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\net\arikia\dev\drpc\DiscordRPC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */