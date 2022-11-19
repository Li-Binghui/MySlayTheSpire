/*    */ package com.megacrit.cardcrawl.desktop;
/*    */ 

/*    */

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglGraphics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class STSDisplayModeCallback
/*    */   extends LwjglApplicationConfiguration
/*    */   implements LwjglGraphics.SetDisplayModeCallback
/*    */ {
/* 15 */   private static final Logger logger = LogManager.getLogger(STSDisplayModeCallback.class.getName());
/*    */ 
/*    */   
/*    */   public LwjglApplicationConfiguration onFailure(LwjglApplicationConfiguration initialConfig) {
/* 19 */     logger.error("Failure to display LwjglApplication. InitialConfig=" + initialConfig.toString());
/* 20 */     initialConfig.width = 1280;
/* 21 */     initialConfig.height = 720;
/* 22 */     initialConfig.fullscreen = false;
/* 23 */     return initialConfig;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\desktop\STSDisplayModeCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */