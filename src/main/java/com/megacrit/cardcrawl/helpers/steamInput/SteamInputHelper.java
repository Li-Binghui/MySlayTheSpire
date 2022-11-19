/*    */ package com.megacrit.cardcrawl.helpers.steamInput;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;

/*    */ import com.badlogic.gdx.utils.Array;
/*    */ import com.codedisaster.steamworks.SteamAPI;
/*    */ import com.codedisaster.steamworks.SteamController;
/*    */ import com.codedisaster.steamworks.SteamControllerActionSetHandle;
/*    */ import com.codedisaster.steamworks.SteamControllerHandle;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*    */ import java.util.ArrayList;
/*    */ import net.java.games.input.Controller;
import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SteamInputHelper
/*    */ {
/* 23 */   private static final Logger logger = LogManager.getLogger(SteamInputHelper.class.getName());
/* 24 */   public static Array<Controller> controllers = null;
/* 25 */   public static ArrayList<SteamInputAction> actions = new ArrayList<>();
/* 26 */   public static CInputHelper.ControllerModel model = null;
/*    */   
/*    */   public static boolean alive = false;
/*    */   
/*    */   public static SteamController controller;
/*    */   public static SteamControllerHandle[] controllerHandles;
/*    */   public static SteamControllerHandle handle;
/* 33 */   public static int numControllers = 0;
/*    */ 
/*    */   
/*    */   public static SteamControllerActionSetHandle setHandle;
/*    */ 
/*    */   
/*    */   public SteamInputHelper() {
/* 40 */     logger.error("Initializing Steam controller...");
/*    */     
/* 42 */     if (!SteamAPI.isSteamRunning()) {
/* 43 */       logger.info("Steam isn't running? SteamInput is disabled.");
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 48 */     controller = new SteamController();
/* 49 */     alive = controller.init();
/*    */     
/* 51 */     if (!alive) {
/* 52 */       logger.info("Steam controller failed to initialize.");
/*    */       
/*    */       return;
/*    */     } 
/* 56 */     controllerHandles = new SteamControllerHandle[16];
/*    */     
/* 58 */     logger.info("Starting input detection...");
/* 59 */     Thread controllerDetectThread = new Thread(new SteamInputDetect());
/* 60 */     CardCrawlGame.sInputDetectThread = controllerDetectThread;
/* 61 */     controllerDetectThread.setName("InputDetect");
/* 62 */     controllerDetectThread.start();
/*    */     
/* 64 */     model = CInputHelper.ControllerModel.XBOX_ONE;
/* 65 */     ImageMaster.loadControllerImages(CInputHelper.ControllerModel.XBOX_ONE);
/*    */   }
/*    */   
/*    */   public static void initActions(SteamControllerHandle controllerHandle) {
/* 69 */     handle = controllerHandle;
/* 70 */     SteamInputActionSet.load(controller);
/*    */     
/* 72 */     for (SteamInputAction a : actions) {
/* 73 */       a.init(controller, handle);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void updateFirst() {
/* 78 */     controller.runFrame();
/*    */     
/* 80 */     for (SteamInputAction a : actions) {
/* 81 */       a.update();
/*    */     }
/*    */   }
/*    */   
/*    */   public static void setCursor(Hitbox hb) {
/* 86 */     if (hb != null) {
/* 87 */       Gdx.input.setCursorPosition((int)hb.cX, Settings.HEIGHT - (int)hb.cY);
/*    */     }
/*    */   }
/*    */   
/*    */   public static boolean isJustPressed(int keycode) {
/* 92 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\steamInput\SteamInputHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */