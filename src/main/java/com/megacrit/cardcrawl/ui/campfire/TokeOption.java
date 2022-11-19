/*    */ package com.megacrit.cardcrawl.ui.campfire;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.campfire.CampfireTokeEffect;
/*    */ 
/*    */ public class TokeOption extends AbstractCampfireOption {
/* 10 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Toke Option");
/* 11 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public TokeOption(boolean active) {
/* 14 */     this.label = TEXT[0];
/* 15 */     this.usable = active;
/* 16 */     this.description = TEXT[1];
/* 17 */     this.img = ImageMaster.CAMPFIRE_TOKE_BUTTON;
/*    */   }
/*    */ 
/*    */   
/*    */   public void useOption() {
/* 22 */     if (this.usable)
/* 23 */       AbstractDungeon.effectList.add(new CampfireTokeEffect()); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\campfire\TokeOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */