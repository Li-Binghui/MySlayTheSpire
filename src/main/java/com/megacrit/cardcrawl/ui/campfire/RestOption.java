/*    */ package com.megacrit.cardcrawl.ui.campfire;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSleepEffect;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSleepScreenCoverEffect;
/*    */ 
/*    */ 
/*    */ public class RestOption
/*    */   extends AbstractCampfireOption
/*    */ {
/* 17 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Rest Option");
/* 18 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   public RestOption(boolean active) {
/*    */     int healAmt;
/* 21 */     this.label = TEXT[0];
/* 22 */     this.usable = active;
/*    */ 
/*    */     
/* 25 */     if (ModHelper.isModEnabled("Night Terrors")) {
/* 26 */       healAmt = (int)(AbstractDungeon.player.maxHealth * 1.0F);
/*    */     } else {
/* 28 */       healAmt = (int)(AbstractDungeon.player.maxHealth * 0.3F);
/*    */     } 
/*    */     
/* 31 */     if (Settings.isEndless && AbstractDungeon.player.hasBlight("FullBelly")) {
/* 32 */       healAmt /= 2;
/*    */     }
/*    */     
/* 35 */     if (ModHelper.isModEnabled("Night Terrors")) {
/* 36 */       this.description = TEXT[1] + healAmt + ")" + LocalizedStrings.PERIOD;
/* 37 */       if (AbstractDungeon.player.hasRelic("Regal Pillow")) {
/* 38 */         this.description += "\n+15" + TEXT[2] + (AbstractDungeon.player.getRelic("Regal Pillow")).name + LocalizedStrings.PERIOD;
/*    */       }
/*    */     } else {
/*    */       
/* 42 */       this.description = TEXT[3] + healAmt + ")" + LocalizedStrings.PERIOD;
/* 43 */       if (AbstractDungeon.player.hasRelic("Regal Pillow")) {
/* 44 */         this.description += "\n+15" + TEXT[2] + (AbstractDungeon.player.getRelic("Regal Pillow")).name + LocalizedStrings.PERIOD;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 49 */     updateUsability(active);
/*    */   }
/*    */   
/*    */   public void updateUsability(boolean canUse) {
/* 53 */     if (!canUse) {
/* 54 */       this.description = TEXT[4];
/*    */     }
/* 56 */     this.img = ImageMaster.CAMPFIRE_REST_BUTTON;
/*    */   }
/*    */ 
/*    */   
/*    */   public void useOption() {
/* 61 */     CardCrawlGame.sound.play("SLEEP_BLANKET");
/* 62 */     AbstractDungeon.effectList.add(new CampfireSleepEffect());
/*    */     
/* 64 */     for (int i = 0; i < 30; i++) {
/* 65 */       AbstractDungeon.topLevelEffects.add(new CampfireSleepScreenCoverEffect());
/*    */     }
/* 67 */     CardCrawlGame.metricData.campfire_rested++;
/* 68 */     CardCrawlGame.metricData.addCampfireChoiceData("REST");
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\campfire\RestOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */