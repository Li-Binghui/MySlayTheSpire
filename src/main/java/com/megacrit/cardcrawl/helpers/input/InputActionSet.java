/*     */ package com.megacrit.cardcrawl.helpers.input;
/*     */ 
/*     */

import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
/*     */ 
/*     */ 
/*     */ public class InputActionSet
/*     */ {
/*   9 */   public static Prefs prefs = SaveHelper.getPrefs("STSInputSettings");
/*     */   
/*     */   public static InputAction confirm;
/*     */   
/*     */   public static InputAction cancel;
/*     */   
/*     */   public static InputAction topPanel;
/*     */   
/*     */   public static InputAction proceed;
/*     */   
/*     */   public static InputAction settings;
/*     */   
/*     */   public static InputAction map;
/*     */   
/*     */   public static InputAction masterDeck;
/*     */   
/*     */   public static InputAction drawPile;
/*     */   
/*     */   public static InputAction discardPile;
/*     */   
/*     */   public static InputAction exhaustPile;
/*     */   
/*     */   public static InputAction endTurn;
/*     */   public static InputAction peek;
/*     */   public static InputAction up;
/*     */   public static InputAction down;
/*     */   public static InputAction left;
/*     */   public static InputAction right;
/*     */   public static InputAction releaseCard;
/*     */   public static InputAction selectCard_1;
/*     */   public static InputAction selectCard_2;
/*     */   public static InputAction selectCard_3;
/*     */   public static InputAction selectCard_4;
/*     */   public static InputAction selectCard_5;
/*     */   public static InputAction selectCard_6;
/*     */   public static InputAction selectCard_7;
/*     */   public static InputAction selectCard_8;
/*     */   public static InputAction selectCard_9;
/*     */   public static InputAction selectCard_10;
/*     */   public static InputAction[] selectCardActions;
/*     */   private static final String CONFIRM_KEY = "CONFIRM";
/*     */   private static final String CANCEL_KEY = "CANCEL";
/*     */   private static final String MAP_KEY = "MAP";
/*     */   private static final String DECK_KEY = "DECK";
/*     */   private static final String DRAW_PILE_KEY = "DRAW_PILE";
/*     */   private static final String DISCARD_PILE_KEY = "DISCARD_PILE";
/*     */   private static final String EXHAUST_PILE_KEY = "EXHAUST_PILE";
/*     */   private static final String END_TURN_KEY = "END_TURN";
/*     */   private static final String PEEK_KEY = "PEEK";
/*     */   private static final String UP_KEY = "UP";
/*     */   private static final String DOWN_KEY = "DOWN";
/*     */   private static final String LEFT_KEY = "LEFT";
/*     */   private static final String RIGHT_KEY = "RIGHT";
/*     */   private static final String DROP_CARD = "DROP_CARD";
/*     */   private static final String CARD_1_KEY = "CARD_1";
/*     */   private static final String CARD_2_KEY = "CARD_2";
/*     */   private static final String CARD_3_KEY = "CARD_3";
/*     */   private static final String CARD_4_KEY = "CARD_4";
/*     */   private static final String CARD_5_KEY = "CARD_5";
/*     */   private static final String CARD_6_KEY = "CARD_6";
/*     */   private static final String CARD_7_KEY = "CARD_7";
/*     */   private static final String CARD_8_KEY = "CARD_8";
/*     */   private static final String CARD_9_KEY = "CARD_9";
/*     */   private static final String CARD_10_KEY = "CARD_10";
/*     */   
/*     */   public static void load() {
/*  75 */     confirm = new InputAction(prefs.getInteger("CONFIRM", 66));
/*  76 */     cancel = new InputAction(prefs.getInteger("CANCEL", 131));
/*     */ 
/*     */     
/*  79 */     map = new InputAction(prefs.getInteger("MAP", 41));
/*  80 */     masterDeck = new InputAction(prefs.getInteger("DECK", 32));
/*  81 */     drawPile = new InputAction(prefs.getInteger("DRAW_PILE", 29));
/*  82 */     discardPile = new InputAction(prefs.getInteger("DISCARD_PILE", 47));
/*  83 */     exhaustPile = new InputAction(prefs.getInteger("EXHAUST_PILE", 52));
/*  84 */     endTurn = new InputAction(prefs.getInteger("END_TURN", 33));
/*  85 */     peek = new InputAction(prefs.getInteger("PEEK", 62));
/*     */     
/*  87 */     up = new InputAction(prefs.getInteger("UP", 19));
/*  88 */     down = new InputAction(prefs.getInteger("DOWN", 20));
/*  89 */     left = new InputAction(prefs.getInteger("LEFT", 21));
/*  90 */     right = new InputAction(prefs.getInteger("RIGHT", 22));
/*  91 */     releaseCard = new InputAction(prefs.getInteger("DROP_CARD", 20));
/*     */     
/*  93 */     selectCard_1 = new InputAction(prefs.getInteger("CARD_1", 8));
/*  94 */     selectCard_2 = new InputAction(prefs.getInteger("CARD_2", 9));
/*  95 */     selectCard_3 = new InputAction(prefs.getInteger("CARD_3", 10));
/*  96 */     selectCard_4 = new InputAction(prefs.getInteger("CARD_4", 11));
/*  97 */     selectCard_5 = new InputAction(prefs.getInteger("CARD_5", 12));
/*  98 */     selectCard_6 = new InputAction(prefs.getInteger("CARD_6", 13));
/*  99 */     selectCard_7 = new InputAction(prefs.getInteger("CARD_7", 14));
/* 100 */     selectCard_8 = new InputAction(prefs.getInteger("CARD_8", 15));
/* 101 */     selectCard_9 = new InputAction(prefs.getInteger("CARD_9", 16));
/* 102 */     selectCard_10 = new InputAction(prefs.getInteger("CARD_10", 7));
/*     */     
/* 104 */     selectCardActions = new InputAction[] { selectCard_1, selectCard_2, selectCard_3, selectCard_4, selectCard_5, selectCard_6, selectCard_7, selectCard_8, selectCard_9, selectCard_10 };
/*     */   }
/*     */ 
/*     */   
/*     */   public static void save() {
/* 109 */     prefs.putInteger("CONFIRM", confirm.getKey());
/* 110 */     prefs.putInteger("CANCEL", cancel.getKey());
/*     */ 
/*     */     
/* 113 */     prefs.putInteger("MAP", map.getKey());
/* 114 */     prefs.putInteger("DECK", masterDeck.getKey());
/* 115 */     prefs.putInteger("DRAW_PILE", drawPile.getKey());
/* 116 */     prefs.putInteger("DISCARD_PILE", discardPile.getKey());
/* 117 */     prefs.putInteger("EXHAUST_PILE", exhaustPile.getKey());
/* 118 */     prefs.putInteger("END_TURN", endTurn.getKey());
/* 119 */     prefs.putInteger("PEEK", peek.getKey());
/*     */     
/* 121 */     prefs.putInteger("UP", up.getKey());
/* 122 */     prefs.putInteger("DOWN", down.getKey());
/* 123 */     prefs.putInteger("LEFT", left.getKey());
/* 124 */     prefs.putInteger("RIGHT", right.getKey());
/* 125 */     prefs.putInteger("DROP_CARD", releaseCard.getKey());
/*     */     
/* 127 */     prefs.putInteger("CARD_1", selectCard_1.getKey());
/* 128 */     prefs.putInteger("CARD_2", selectCard_2.getKey());
/* 129 */     prefs.putInteger("CARD_3", selectCard_3.getKey());
/* 130 */     prefs.putInteger("CARD_4", selectCard_4.getKey());
/* 131 */     prefs.putInteger("CARD_5", selectCard_5.getKey());
/* 132 */     prefs.putInteger("CARD_6", selectCard_6.getKey());
/* 133 */     prefs.putInteger("CARD_7", selectCard_7.getKey());
/* 134 */     prefs.putInteger("CARD_8", selectCard_8.getKey());
/* 135 */     prefs.putInteger("CARD_9", selectCard_9.getKey());
/* 136 */     prefs.putInteger("CARD_10", selectCard_10.getKey());
/* 137 */     prefs.flush();
/*     */   }
/*     */   
/*     */   public static void resetToDefaults() {
/* 141 */     confirm.remap(66);
/* 142 */     cancel.remap(131);
/*     */ 
/*     */     
/* 145 */     map.remap(41);
/* 146 */     masterDeck.remap(32);
/* 147 */     drawPile.remap(29);
/* 148 */     discardPile.remap(47);
/* 149 */     exhaustPile.remap(52);
/* 150 */     endTurn.remap(33);
/* 151 */     peek.remap(62);
/*     */     
/* 153 */     up.remap(19);
/* 154 */     down.remap(20);
/* 155 */     left.remap(21);
/* 156 */     right.remap(22);
/* 157 */     releaseCard.remap(20);
/*     */     
/* 159 */     selectCard_1.remap(8);
/* 160 */     selectCard_2.remap(9);
/* 161 */     selectCard_3.remap(10);
/* 162 */     selectCard_4.remap(11);
/* 163 */     selectCard_5.remap(12);
/* 164 */     selectCard_6.remap(13);
/* 165 */     selectCard_7.remap(14);
/* 166 */     selectCard_8.remap(15);
/* 167 */     selectCard_9.remap(16);
/* 168 */     selectCard_10.remap(7);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\input\InputActionSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */