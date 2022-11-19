/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */

import com.badlogic.gdx.InputProcessor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.ui.panels.RenamePopup;
import com.megacrit.cardcrawl.ui.panels.SeedPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class TypeHelper
/*    */   implements InputProcessor {
/* 13 */   private static final Logger logger = LogManager.getLogger(TypeHelper.class.getName());
/*    */   private boolean seed;
/*    */   
/*    */   public TypeHelper() {
/* 17 */     this.seed = false;
/*    */   }
/*    */   
/*    */   public TypeHelper(boolean seed) {
/* 21 */     this.seed = seed;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean keyDown(int keycode) {
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean keyUp(int keycode) {
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean keyTyped(char character) {
/* 36 */     String charStr = String.valueOf(character);
/* 37 */     logger.info(charStr);
/* 38 */     if (charStr.length() != 1) {
/* 39 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 43 */     if (this.seed) {
/* 44 */       if (SeedPanel.isFull()) {
/* 45 */         return false;
/*    */       }
/*    */       
/* 48 */       if (InputHelper.isPasteJustPressed()) {
/* 49 */         return false;
/*    */       }
/*    */       
/* 52 */       String converted = SeedHelper.getValidCharacter(charStr, SeedPanel.textField);
/* 53 */       if (converted != null) {
/* 54 */         SeedPanel.textField += converted;
/*    */       }
/*    */     }
/*    */     else {
/*    */       
/* 59 */       if (FontHelper.getSmartWidth(FontHelper.cardTitleFont, RenamePopup.textField, 1.0E7F, 0.0F, 0.82F) >= 240.0F * Settings.scale)
/*    */       {
/* 61 */         return false;
/*    */       }
/*    */       
/* 64 */       if (Character.isDigit(character) || Character.isLetter(character)) {
/* 65 */         RenamePopup.textField += charStr;
/*    */       }
/*    */     } 
/* 68 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
/* 73 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
/* 78 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean touchDragged(int screenX, int screenY, int pointer) {
/* 83 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean mouseMoved(int screenX, int screenY) {
/* 88 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean scrolled(int amount) {
/* 93 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\TypeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */