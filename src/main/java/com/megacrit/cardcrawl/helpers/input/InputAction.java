/*    */ package com.megacrit.cardcrawl.helpers.input;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.HashMap;
import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class InputAction
/*    */ {
/* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("InputKeyNames");
/* 14 */   public static final Map<String, String> TEXT_CONVERSIONS = uiStrings.TEXT_DICT;
/*    */   
/*    */   private int keycode;
/* 17 */   private static final HashMap<Integer, Integer> equivalentKeys = new HashMap<>();
/*    */   
/*    */   static {
/* 20 */     equivalentKeys.put(Integer.valueOf(7), Integer.valueOf(144));
/* 21 */     equivalentKeys.put(Integer.valueOf(8), Integer.valueOf(145));
/* 22 */     equivalentKeys.put(Integer.valueOf(9), Integer.valueOf(146));
/* 23 */     equivalentKeys.put(Integer.valueOf(10), Integer.valueOf(147));
/* 24 */     equivalentKeys.put(Integer.valueOf(11), Integer.valueOf(148));
/* 25 */     equivalentKeys.put(Integer.valueOf(12), Integer.valueOf(149));
/* 26 */     equivalentKeys.put(Integer.valueOf(13), Integer.valueOf(150));
/* 27 */     equivalentKeys.put(Integer.valueOf(14), Integer.valueOf(151));
/* 28 */     equivalentKeys.put(Integer.valueOf(15), Integer.valueOf(152));
/* 29 */     equivalentKeys.put(Integer.valueOf(16), Integer.valueOf(153));
/*    */     
/* 31 */     equivalentKeys.put(Integer.valueOf(144), Integer.valueOf(7));
/* 32 */     equivalentKeys.put(Integer.valueOf(145), Integer.valueOf(8));
/* 33 */     equivalentKeys.put(Integer.valueOf(146), Integer.valueOf(9));
/* 34 */     equivalentKeys.put(Integer.valueOf(147), Integer.valueOf(10));
/* 35 */     equivalentKeys.put(Integer.valueOf(148), Integer.valueOf(11));
/* 36 */     equivalentKeys.put(Integer.valueOf(149), Integer.valueOf(12));
/* 37 */     equivalentKeys.put(Integer.valueOf(150), Integer.valueOf(13));
/* 38 */     equivalentKeys.put(Integer.valueOf(151), Integer.valueOf(14));
/* 39 */     equivalentKeys.put(Integer.valueOf(152), Integer.valueOf(15));
/* 40 */     equivalentKeys.put(Integer.valueOf(153), Integer.valueOf(16));
/*    */   }
/*    */   
/*    */   public InputAction(int keycode) {
/* 44 */     this.keycode = keycode;
/*    */   }
/*    */   
/*    */   public int getKey() {
/* 48 */     return this.keycode;
/*    */   }
/*    */   
/*    */   public String getKeyString() {
/* 52 */     String keycodeStr = Input.Keys.toString(this.keycode);
/* 53 */     return TEXT_CONVERSIONS.getOrDefault(keycodeStr, keycodeStr);
/*    */   }
/*    */   
/*    */   public boolean isJustPressed() {
/* 57 */     boolean alternatePressed = (equivalentKeys.containsKey(Integer.valueOf(this.keycode)) && Gdx.input.isKeyJustPressed(((Integer)equivalentKeys
/* 58 */         .get(Integer.valueOf(this.keycode))).intValue()));
/* 59 */     return (alternatePressed || Gdx.input.isKeyJustPressed(this.keycode));
/*    */   }
/*    */   
/*    */   public boolean isPressed() {
/* 63 */     boolean alternatePressed = (equivalentKeys.containsKey(Integer.valueOf(this.keycode)) && Gdx.input.isKeyPressed(((Integer)equivalentKeys
/* 64 */         .get(Integer.valueOf(this.keycode))).intValue()));
/* 65 */     return (alternatePressed || Gdx.input.isKeyPressed(this.keycode));
/*    */   }
/*    */   
/*    */   public void remap(int newKeycode) {
/* 69 */     this.keycode = newKeycode;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\input\InputAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */