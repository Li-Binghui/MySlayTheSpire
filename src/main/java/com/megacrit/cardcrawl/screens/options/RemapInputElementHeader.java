/*    */ package com.megacrit.cardcrawl.screens.options;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*    */ 
/*    */ public class RemapInputElementHeader
/*    */   extends RemapInputElement {
/*    */   private String keyboardText;
/*    */   private String controllerText;
/*    */   
/*    */   public RemapInputElementHeader(String commandText, String keyboardText, String controllerText) {
/* 13 */     super(null, commandText, null);
/* 14 */     this.keyboardText = keyboardText;
/* 15 */     this.controllerText = controllerText;
/* 16 */     this.isHeader = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getKeyColumnText() {
/* 26 */     return this.keyboardText;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getControllerColumnText() {
/* 31 */     return this.controllerText;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Color getTextColor() {
/* 36 */     return Settings.GOLD_COLOR;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void hoverStarted(Hitbox hitbox) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startClicking(Hitbox hitbox) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean keyDown(int keycode) {
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\RemapInputElementHeader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */