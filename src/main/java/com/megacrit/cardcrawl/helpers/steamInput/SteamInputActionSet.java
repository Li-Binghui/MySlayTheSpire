/*    */ package com.megacrit.cardcrawl.helpers.steamInput;
/*    */ 
/*    */ import com.codedisaster.steamworks.SteamController;
/*    */ import com.megacrit.cardcrawl.helpers.controller.CInputAction;
/*    */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SteamInputActionSet
/*    */ {
/*    */   public static SteamInputAction select;
/*    */   public static SteamInputAction cancel;
/*    */   public static SteamInputAction topPanel;
/*    */   public static SteamInputAction proceed;
/*    */   public static SteamInputAction peek;
/*    */   public static SteamInputAction pageLeftViewDeck;
/*    */   public static SteamInputAction pageRightViewExhaust;
/*    */   public static SteamInputAction map;
/*    */   public static SteamInputAction settings;
/*    */   public static SteamInputAction drawPile;
/*    */   public static SteamInputAction discardPile;
/*    */   public static SteamInputAction up;
/*    */   
/*    */   public static void load(SteamController setController) {
/* 26 */     controller = setController;
/*    */     
/* 28 */     controller.activateActionSet(SteamInputHelper.controllerHandles[0], controller
/*    */         
/* 30 */         .getActionSetHandle("AllControls"));
/*    */     
/* 32 */     select = SetAction("select", CInputActionSet.select);
/* 33 */     cancel = SetAction("cancel", CInputActionSet.cancel);
/* 34 */     topPanel = SetAction("top_panel", CInputActionSet.topPanel);
/* 35 */     proceed = SetAction("proceed", CInputActionSet.proceed);
/*    */     
/* 37 */     peek = SetAction("peek", CInputActionSet.peek);
/* 38 */     pageLeftViewDeck = SetAction("page_left", CInputActionSet.pageLeftViewDeck);
/* 39 */     pageRightViewExhaust = SetAction("page_right", CInputActionSet.pageRightViewExhaust);
/* 40 */     map = SetAction("map", CInputActionSet.map);
/* 41 */     settings = SetAction("settings", CInputActionSet.settings);
/*    */     
/* 43 */     up = SetAction("up", CInputActionSet.up);
/* 44 */     down = SetAction("down", CInputActionSet.down);
/* 45 */     left = SetAction("left", CInputActionSet.left);
/* 46 */     right = SetAction("right", CInputActionSet.right);
/*    */     
/* 48 */     inspectUp = SetAction("scroll_up", CInputActionSet.inspectUp);
/* 49 */     inspectDown = SetAction("scroll_down", CInputActionSet.inspectDown);
/* 50 */     inspectLeft = SetAction("scroll_left", CInputActionSet.inspectLeft);
/* 51 */     inspectRight = SetAction("scroll_right", CInputActionSet.inspectRight);
/*    */     
/* 53 */     altUp = SetAction("alt_up", CInputActionSet.altUp);
/* 54 */     altDown = SetAction("alt_down", CInputActionSet.altDown);
/* 55 */     altLeft = SetAction("alt_left", CInputActionSet.altLeft);
/* 56 */     altRight = SetAction("alt_right", CInputActionSet.altRight);
/*    */     
/* 58 */     drawPile = SetAction("draw_pile", CInputActionSet.drawPile);
/* 59 */     discardPile = SetAction("discard_pile", CInputActionSet.discardPile);
/*    */     
/* 61 */     SteamInputHelper.actions.clear();
/* 62 */     SteamInputHelper.actions.add(select);
/* 63 */     SteamInputHelper.actions.add(cancel);
/* 64 */     SteamInputHelper.actions.add(topPanel);
/* 65 */     SteamInputHelper.actions.add(proceed);
/*    */     
/* 67 */     SteamInputHelper.actions.add(peek);
/* 68 */     SteamInputHelper.actions.add(pageLeftViewDeck);
/* 69 */     SteamInputHelper.actions.add(pageRightViewExhaust);
/* 70 */     SteamInputHelper.actions.add(map);
/* 71 */     SteamInputHelper.actions.add(settings);
/*    */     
/* 73 */     SteamInputHelper.actions.add(up);
/* 74 */     SteamInputHelper.actions.add(down);
/* 75 */     SteamInputHelper.actions.add(left);
/* 76 */     SteamInputHelper.actions.add(right);
/*    */     
/* 78 */     SteamInputHelper.actions.add(inspectUp);
/* 79 */     SteamInputHelper.actions.add(inspectDown);
/* 80 */     SteamInputHelper.actions.add(inspectLeft);
/* 81 */     SteamInputHelper.actions.add(inspectRight);
/*    */     
/* 83 */     SteamInputHelper.actions.add(altUp);
/* 84 */     SteamInputHelper.actions.add(altDown);
/* 85 */     SteamInputHelper.actions.add(altLeft);
/* 86 */     SteamInputHelper.actions.add(altRight);
/*    */     
/* 88 */     SteamInputHelper.actions.add(drawPile);
/* 89 */     SteamInputHelper.actions.add(discardPile);
/*    */   }
/*    */   public static SteamInputAction down; public static SteamInputAction left; public static SteamInputAction right; public static SteamInputAction inspectUp; public static SteamInputAction inspectDown; public static SteamInputAction inspectLeft; public static SteamInputAction inspectRight; public static SteamInputAction altUp; public static SteamInputAction altDown; public static SteamInputAction altLeft; public static SteamInputAction altRight; public static SteamController controller;
/*    */   private static SteamInputAction SetAction(String name, CInputAction ref) {
/* 93 */     return new SteamInputAction(controller.getDigitalActionHandle(name), ref);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\steamInput\SteamInputActionSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */