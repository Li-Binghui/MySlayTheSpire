/*    */ package com.megacrit.cardcrawl.ui.campfire;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.campfire.CampfireRecallEffect;
/*    */ 
/*    */ public class RecallOption
/*    */   extends AbstractCampfireOption {
/* 10 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Recall Option");
/* 11 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void useOption() {
/* 21 */     AbstractDungeon.effectList.add(new CampfireRecallEffect());
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\campfire\RecallOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */