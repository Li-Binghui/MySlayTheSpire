/*    */ package com.megacrit.cardcrawl.events.shrines;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class Duplicator extends AbstractImageEvent {
/*    */   public static final String ID = "Duplicator";
/* 14 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Duplicator");
/* 15 */   public static final String NAME = eventStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 17 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */ 
/*    */   
/* 20 */   private int screenNum = 0;
/* 21 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 22 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/* 23 */   private static final String IGNORE = DESCRIPTIONS[2];
/*    */   
/*    */   public Duplicator() {
/* 26 */     super(NAME, DIALOG_1, "images/events/shrine4.jpg");
/* 27 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/* 28 */     this.imageEventText.setDialogOption(OPTIONS[1]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom() {
/* 33 */     CardCrawlGame.music.playTempBGM("SHRINE");
/*    */   }
/*    */   
/*    */   public void update() {
/* 37 */     super.update();
/* 38 */     if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && 
/* 39 */       !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 40 */       AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeStatEquivalentCopy();
/* 41 */       logMetricObtainCard("Duplicator", "Copied", c);
/* 42 */       c.inBottleFlame = false;
/* 43 */       c.inBottleLightning = false;
/* 44 */       c.inBottleTornado = false;
/* 45 */       AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */       
/* 47 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 54 */     switch (this.screenNum) {
/*    */       case 0:
/* 56 */         switch (buttonPressed) {
/*    */           case 0:
/* 58 */             this.imageEventText.updateBodyText(DIALOG_2);
/* 59 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 60 */             this.imageEventText.clearRemainingOptions();
/* 61 */             use();
/* 62 */             this.screenNum = 2;
/*    */             break;
/*    */           case 1:
/* 65 */             this.screenNum = 2;
/* 66 */             this.imageEventText.updateBodyText(IGNORE);
/* 67 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 68 */             this.imageEventText.clearRemainingOptions();
/* 69 */             logMetricIgnored("Duplicator");
/*    */             break;
/*    */         } 
/*    */         break;
/*    */       case 1:
/* 74 */         this.screenNum = 2;
/*    */         break;
/*    */       case 2:
/* 77 */         openMap();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use() {
/* 85 */     AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, 1, OPTIONS[2], false, false, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void logMetric(String result) {
/* 96 */     AbstractEvent.logMetric("Duplicator", result);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\Duplicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */