/*    */ package com.megacrit.cardcrawl.ui.campfire;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.vfx.campfire.CampfireSmithEffect;
/*    */ 
/*    */ public class SmithOption extends AbstractCampfireOption {
/* 10 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Smith Option");
/* 11 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public SmithOption(boolean active) {
/* 14 */     this.label = TEXT[0];
/* 15 */     this.usable = active;
/* 16 */     updateUsability(active);
/*    */   }
/*    */   
/*    */   public void updateUsability(boolean canUse) {
/* 20 */     this.description = canUse ? TEXT[1] : TEXT[2];
/* 21 */     this.img = ImageMaster.CAMPFIRE_SMITH_BUTTON;
/*    */   }
/*    */ 
/*    */   
/*    */   public void useOption() {
/* 26 */     if (this.usable)
/* 27 */       AbstractDungeon.effectList.add(new CampfireSmithEffect()); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\campfire\SmithOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */