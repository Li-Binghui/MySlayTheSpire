/*    */ package com.megacrit.cardcrawl.helpers.steamInput;
/*    */ 
/*    */

import com.codedisaster.steamworks.SteamController;
import com.codedisaster.steamworks.SteamControllerDigitalActionData;
import com.codedisaster.steamworks.SteamControllerDigitalActionHandle;
import com.codedisaster.steamworks.SteamControllerHandle;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.controller.CInputAction;
/*    */ 
/*    */ public class SteamInputAction
/*    */ {
/*    */   public SteamController controller;
/*    */   public SteamControllerHandle controllerHandle;
/*    */   public SteamControllerDigitalActionHandle actionHandle;
/* 15 */   public SteamControllerDigitalActionData actionData = new SteamControllerDigitalActionData();
/*    */   private CInputAction ref;
/*    */   
/*    */   public SteamInputAction(SteamControllerDigitalActionHandle handle, CInputAction actionRef) {
/* 19 */     this.actionHandle = handle;
/* 20 */     this.ref = actionRef;
/*    */   }
/*    */   
/*    */   public void init(SteamController controller, SteamControllerHandle controllerHandle) {
/* 24 */     this.controller = controller;
/* 25 */     this.controllerHandle = controllerHandle;
/*    */   }
/*    */   
/*    */   public void update() {
/* 29 */     if (this.controller == null || this.actionHandle == null) {
/*    */       return;
/*    */     }
/*    */     
/* 33 */     this.controller.getDigitalActionData(SteamInputHelper.handle, this.actionHandle, this.actionData);
/*    */     
/* 35 */     if (this.actionData.getActive() && this.actionData.getState()) {
/* 36 */       if (!this.ref.pressed) {
/* 37 */         this.ref.pressed = true;
/* 38 */         this.ref.justPressed = true;
/*    */       } else {
/* 40 */         if (Settings.CONTROLLER_ENABLED && !Settings.isControllerMode) {
/* 41 */           Settings.isControllerMode = true;
/*    */         }
/* 43 */         this.ref.justPressed = false;
/*    */       } 
/* 45 */     } else if (this.ref.pressed) {
/* 46 */       this.ref.pressed = false;
/* 47 */       this.ref.justPressed = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\steamInput\SteamInputAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */