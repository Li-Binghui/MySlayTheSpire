/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.TutorialStrings;

import java.util.ArrayList;
import java.util.Collections;
/*    */ 
/*    */ public class GameTips
/*    */ {
/* 12 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Random Tips");
/* 13 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*    */   
/* 15 */   private ArrayList<String> tips = new ArrayList<>();
/*    */   
/*    */   public GameTips() {
/* 18 */     initialize();
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 22 */     Collections.addAll(this.tips, tutorialStrings.TEXT);
/*    */ 
/*    */     
/* 25 */     if (!Settings.isConsoleBuild) {
/* 26 */       Collections.addAll(this.tips, (CardCrawlGame.languagePack.getTutorialString("PC Tips")).TEXT);
/*    */     }
/*    */ 
/*    */     
/* 30 */     Collections.shuffle(this.tips);
/*    */   }
/*    */   
/*    */   public String getTip() {
/* 34 */     String retVal = this.tips.remove(MathUtils.random(this.tips.size() - 1));
/* 35 */     if (this.tips.isEmpty()) {
/* 36 */       initialize();
/*    */     }
/*    */     
/* 39 */     return retVal;
/*    */   }
/*    */   
/*    */   public String getPotionTip() {
/* 43 */     return LABEL[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\GameTips.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */