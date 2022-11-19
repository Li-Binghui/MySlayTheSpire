/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class Prefs
/*    */ {
/* 10 */   public Map<String, String> data = new HashMap<>();
/*    */   public String filepath;
/*    */   
/*    */   public String getString(String key) {
/* 14 */     return this.data.getOrDefault(key, "");
/*    */   }
/*    */   
/*    */   public String getString(String key, String def) {
/* 18 */     return this.data.getOrDefault(key, def);
/*    */   }
/*    */   
/*    */   public void putString(String key, String value) {
/* 22 */     this.data.put(key, value);
/*    */   }
/*    */   
/*    */   public int getInteger(String key) {
/* 26 */     if (this.data.containsKey(key)) {
/* 27 */       return Integer.valueOf(((String)this.data.get(key)).trim()).intValue();
/*    */     }
/* 29 */     return -999;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getInteger(String key, int def) {
/* 34 */     if (this.data.containsKey(key)) {
/* 35 */       return Integer.valueOf(((String)this.data.get(key)).trim()).intValue();
/*    */     }
/* 37 */     return def;
/*    */   }
/*    */ 
/*    */   
/*    */   public void putInteger(String key, int value) {
/* 42 */     this.data.put(key, Integer.toString(value));
/*    */   }
/*    */   
/*    */   public float getFloat(String key, float def) {
/* 46 */     if (this.data.containsKey(key)) {
/* 47 */       return Float.valueOf(((String)this.data.get(key)).trim()).floatValue();
/*    */     }
/* 49 */     return def;
/*    */   }
/*    */ 
/*    */   
/*    */   public void putFloat(String key, float value) {
/* 54 */     this.data.put(key, Float.toString(value));
/*    */   }
/*    */   
/*    */   public long getLong(String key, long def) {
/* 58 */     if (this.data.containsKey(key)) {
/* 59 */       return Long.valueOf(((String)this.data.get(key)).trim()).longValue();
/*    */     }
/* 61 */     return def;
/*    */   }
/*    */ 
/*    */   
/*    */   public void putLong(String key, long value) {
/* 66 */     this.data.put(key, Long.toString(value));
/*    */   }
/*    */   
/*    */   public boolean getBoolean(String key, boolean def) {
/* 70 */     if (this.data.containsKey(key)) {
/* 71 */       return Boolean.valueOf(((String)this.data.get(key)).trim()).booleanValue();
/*    */     }
/* 73 */     return def;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getBoolean(String key) {
/* 78 */     return Boolean.valueOf(((String)this.data.get(key)).trim()).booleanValue();
/*    */   }
/*    */   
/*    */   public void putBoolean(String key, boolean value) {
/* 82 */     this.data.put(key, Boolean.toString(value));
/*    */   }
/*    */   
/*    */   public void flush() {
/* 86 */     Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
/* 87 */     AsyncSaver.save(this.filepath, gson.toJson(this.data));
/*    */   }
/*    */   
/*    */   public Map<String, String> get() {
/* 91 */     return this.data;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\Prefs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */