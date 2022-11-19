/*    */ package com.megacrit.cardcrawl.core;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
/*    */ import java.util.Properties;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class BuildSettings
/*    */ {
/*    */   private final Properties prop;
/*    */   public static final String defaultFilename = "build.properties";
/*    */   
/*    */   BuildSettings(Reader reader) throws IOException {
/* 23 */     this.prop = new Properties();
/* 24 */     this.prop.load(reader);
/*    */   }
/*    */   
/*    */   String getDistributor() throws BuildSettingsException {
/* 28 */     String distributor = this.prop.getProperty("distributor");
/* 29 */     if (distributor != null) {
/* 30 */       return distributor;
/*    */     }
/* 32 */     throw new BuildSettingsException("The key 'distributor' is null in file=build.properties");
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\core\BuildSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */