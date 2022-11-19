/*     */ package com.megacrit.cardcrawl.helpers.controller;
/*     */ 
/*     */

import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
/*     */ 
/*     */ public class CInputActionSet {
/*   7 */   public static Prefs prefs = SaveHelper.getPrefs("STSInputSettings_Controller");
/*     */   
/*     */   public static CInputAction select;
/*     */   
/*     */   public static CInputAction cancel;
/*     */   
/*     */   public static CInputAction topPanel;
/*     */   
/*     */   public static CInputAction proceed;
/*     */   
/*     */   public static CInputAction peek;
/*     */   
/*     */   public static CInputAction pageLeftViewDeck;
/*     */   
/*     */   public static CInputAction pageRightViewExhaust;
/*     */   
/*     */   public static CInputAction map;
/*     */   
/*     */   public static CInputAction settings;
/*     */   
/*     */   public static CInputAction drawPile;
/*     */   
/*     */   public static CInputAction discardPile;
/*     */   
/*     */   public static CInputAction up;
/*     */   
/*     */   public static CInputAction down;
/*     */   
/*     */   public static CInputAction left;
/*     */   
/*     */   public static CInputAction right;
/*     */   
/*     */   public static CInputAction inspectUp;
/*     */   
/*     */   public static CInputAction inspectDown;
/*     */   
/*     */   public static CInputAction inspectLeft;
/*     */   public static CInputAction inspectRight;
/*     */   public static CInputAction altUp;
/*     */   public static CInputAction altDown;
/*     */   public static CInputAction altLeft;
/*     */   public static CInputAction altRight;
/*     */   
/*     */   public static void load() {
/*  51 */     select = new CInputAction(prefs.getInteger("SELECT", 0));
/*  52 */     cancel = new CInputAction(prefs.getInteger("CANCEL", 1));
/*  53 */     topPanel = new CInputAction(prefs.getInteger("VIEW", 2));
/*  54 */     proceed = new CInputAction(prefs.getInteger("PROCEED", 3));
/*  55 */     peek = new CInputAction(prefs.getInteger("PEEK", 8));
/*  56 */     pageLeftViewDeck = new CInputAction(prefs.getInteger("PAGE_LEFT_KEY", 4));
/*  57 */     pageRightViewExhaust = new CInputAction(prefs.getInteger("PAGE_RIGHT_KEY", 5));
/*  58 */     map = new CInputAction(prefs.getInteger("MAP", 6));
/*  59 */     settings = new CInputAction(prefs.getInteger("SETTINGS", 7));
/*     */ 
/*     */     
/*  62 */     up = new CInputAction(prefs.getInteger("LS_Y_POSITIVE", -1000));
/*  63 */     down = new CInputAction(prefs.getInteger("LS_Y_NEGATIVE", 1000));
/*  64 */     left = new CInputAction(prefs.getInteger("LS_X_NEGATIVE", -1001));
/*  65 */     right = new CInputAction(prefs.getInteger("LS_X_POSITIVE", 1001));
/*  66 */     inspectUp = new CInputAction(prefs.getInteger("RS_Y_POSITIVE", -1002));
/*  67 */     inspectDown = new CInputAction(prefs.getInteger("RS_Y_NEGATIVE", 1002));
/*  68 */     inspectLeft = new CInputAction(prefs.getInteger("RS_X_POSITIVE", -1003));
/*  69 */     inspectRight = new CInputAction(prefs.getInteger("RS_X_NEGATIVE", 1003));
/*  70 */     altUp = new CInputAction(prefs.getInteger("D_NORTH", -2000));
/*  71 */     altDown = new CInputAction(prefs.getInteger("D_SOUTH", 2000));
/*  72 */     altLeft = new CInputAction(prefs.getInteger("D_WEST", -2001));
/*  73 */     altRight = new CInputAction(prefs.getInteger("D_EAST", 2001));
/*     */ 
/*     */     
/*  76 */     drawPile = new CInputAction(prefs.getInteger("DRAW_PILE", 1004));
/*  77 */     discardPile = new CInputAction(prefs.getInteger("DISCARD_PILE", -1004));
/*     */     
/*  79 */     CInputHelper.actions.clear();
/*  80 */     CInputHelper.actions.add(select);
/*  81 */     CInputHelper.actions.add(cancel);
/*  82 */     CInputHelper.actions.add(topPanel);
/*  83 */     CInputHelper.actions.add(proceed);
/*  84 */     CInputHelper.actions.add(peek);
/*  85 */     CInputHelper.actions.add(pageLeftViewDeck);
/*  86 */     CInputHelper.actions.add(pageRightViewExhaust);
/*  87 */     CInputHelper.actions.add(map);
/*  88 */     CInputHelper.actions.add(settings);
/*  89 */     CInputHelper.actions.add(up);
/*  90 */     CInputHelper.actions.add(down);
/*  91 */     CInputHelper.actions.add(left);
/*  92 */     CInputHelper.actions.add(right);
/*  93 */     CInputHelper.actions.add(inspectUp);
/*  94 */     CInputHelper.actions.add(inspectDown);
/*  95 */     CInputHelper.actions.add(inspectLeft);
/*  96 */     CInputHelper.actions.add(inspectRight);
/*  97 */     CInputHelper.actions.add(altUp);
/*  98 */     CInputHelper.actions.add(altDown);
/*  99 */     CInputHelper.actions.add(altLeft);
/* 100 */     CInputHelper.actions.add(altRight);
/* 101 */     CInputHelper.actions.add(drawPile);
/* 102 */     CInputHelper.actions.add(discardPile);
/*     */   }
/*     */   private static final String LS_Y_POSITIVE = "LS_Y_POSITIVE"; private static final String LS_Y_NEGATIVE = "LS_Y_NEGATIVE"; private static final String LS_X_POSITIVE = "LS_X_POSITIVE"; private static final String LS_X_NEGATIVE = "LS_X_NEGATIVE"; private static final String RS_Y_POSITIVE = "RS_Y_POSITIVE"; private static final String RS_Y_NEGATIVE = "RS_Y_NEGATIVE"; private static final String RS_X_POSITIVE = "RS_X_POSITIVE"; private static final String RS_X_NEGATIVE = "RS_X_NEGATIVE"; private static final String D_NORTH = "D_NORTH"; private static final String D_SOUTH = "D_SOUTH"; private static final String D_WEST = "D_WEST"; private static final String D_EAST = "D_EAST"; private static final String SELECT_KEY = "SELECT"; private static final String CANCEL_KEY = "CANCEL"; private static final String TOP_PANEL_KEY = "VIEW"; private static final String PROCEED_KEY = "PROCEED"; private static final String PEEK_KEY = "PEEK"; private static final String PAGE_LEFT_KEY = "PAGE_LEFT_KEY"; private static final String PAGE_RIGHT_KEY = "PAGE_RIGHT_KEY"; private static final String MAP_KEY = "MAP"; private static final String SETTINGS_KEY = "SETTINGS"; private static final String DRAW_PILE_KEY = "DRAW_PILE"; private static final String DISCARD_PILE_KEY = "DISCARD_PILE";
/*     */   public static void save() {
/* 106 */     prefs.putInteger("SELECT", select.getKey());
/* 107 */     prefs.putInteger("CANCEL", cancel.getKey());
/* 108 */     prefs.putInteger("VIEW", topPanel.getKey());
/* 109 */     prefs.putInteger("PROCEED", proceed.getKey());
/* 110 */     prefs.putInteger("PEEK", peek.getKey());
/* 111 */     prefs.putInteger("PAGE_LEFT_KEY", pageLeftViewDeck.getKey());
/* 112 */     prefs.putInteger("PAGE_RIGHT_KEY", pageRightViewExhaust.getKey());
/* 113 */     prefs.putInteger("MAP", map.getKey());
/* 114 */     prefs.putInteger("SETTINGS", settings.getKey());
/* 115 */     prefs.putInteger("LS_Y_POSITIVE", up.getKey());
/* 116 */     prefs.putInteger("LS_Y_NEGATIVE", down.getKey());
/* 117 */     prefs.putInteger("LS_X_NEGATIVE", left.getKey());
/* 118 */     prefs.putInteger("LS_X_POSITIVE", right.getKey());
/* 119 */     prefs.putInteger("RS_Y_POSITIVE", inspectUp.getKey());
/* 120 */     prefs.putInteger("RS_Y_NEGATIVE", inspectDown.getKey());
/* 121 */     prefs.putInteger("RS_X_POSITIVE", inspectLeft.getKey());
/* 122 */     prefs.putInteger("RS_X_NEGATIVE", inspectRight.getKey());
/* 123 */     prefs.putInteger("DRAW_PILE", drawPile.getKey());
/* 124 */     prefs.putInteger("DISCARD_PILE", discardPile.getKey());
/* 125 */     prefs.flush();
/*     */   }
/*     */   
/*     */   public static void resetToDefaults() {
/* 129 */     select.remap(0);
/* 130 */     cancel.remap(1);
/* 131 */     topPanel.remap(2);
/* 132 */     proceed.remap(3);
/* 133 */     peek.remap(8);
/* 134 */     pageLeftViewDeck.remap(4);
/* 135 */     pageRightViewExhaust.remap(5);
/* 136 */     map.remap(6);
/* 137 */     settings.remap(7);
/* 138 */     up.remap(-1000);
/* 139 */     down.remap(1000);
/* 140 */     left.remap(-1001);
/* 141 */     right.remap(1001);
/* 142 */     inspectUp.remap(1002);
/* 143 */     inspectDown.remap(-1002);
/* 144 */     inspectLeft.remap(-1003);
/* 145 */     inspectRight.remap(1003);
/* 146 */     altUp.remap(-2000);
/* 147 */     altDown.remap(2000);
/* 148 */     altLeft.remap(-2001);
/* 149 */     altRight.remap(2001);
/* 150 */     drawPile.remap(1004);
/* 151 */     discardPile.remap(-1004);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\controller\CInputActionSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */