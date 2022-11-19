/*    */ package com.megacrit.cardcrawl.ui.campfire;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.campfire.CampfireLiftEffect;
/*    */ 
/*    */ public class LiftOption extends AbstractCampfireOption {
/* 10 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Lift Option");
/* 11 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public LiftOption(boolean active) {
/* 14 */     this.label = TEXT[0];
/* 15 */     this.usable = active;
/* 16 */     this.description = active ? TEXT[1] : TEXT[2];
/* 17 */     this.img = ImageMaster.CAMPFIRE_TRAIN_BUTTON;
/*    */   }
/*    */ 
/*    */   
/*    */   public void useOption() {
/* 22 */     if (this.usable)
/* 23 */       AbstractDungeon.effectList.add(new CampfireLiftEffect()); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\campfire\LiftOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */