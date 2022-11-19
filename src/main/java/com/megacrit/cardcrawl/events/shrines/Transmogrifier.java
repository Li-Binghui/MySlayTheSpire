/*    */ package com.megacrit.cardcrawl.events.shrines;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class Transmogrifier extends AbstractImageEvent {
/*    */   public static final String ID = "Transmorgrifier";
/* 14 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Transmorgrifier");
/* 15 */   public static final String NAME = eventStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 17 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/* 19 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 20 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/* 21 */   private static final String IGNORE = DESCRIPTIONS[2];
/* 22 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*    */   
/*    */   private enum CUR_SCREEN {
/* 25 */     INTRO, COMPLETE;
/*    */   }
/*    */   
/*    */   public Transmogrifier() {
/* 29 */     super(NAME, DIALOG_1, "images/events/shrine1.jpg");
/* 30 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/* 31 */     this.imageEventText.setDialogOption(OPTIONS[1]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom() {
/* 36 */     CardCrawlGame.music.playTempBGM("SHRINE");
/*    */   }
/*    */   
/*    */   public void update() {
/* 40 */     super.update();
/* 41 */     if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 42 */       AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/* 43 */       AbstractDungeon.player.masterDeck.removeCard(c);
/* 44 */       AbstractDungeon.transformCard(c, false, AbstractDungeon.miscRng);
/* 45 */       AbstractCard transCard = AbstractDungeon.getTransformedCard();
/* 46 */       logMetricTransformCard("Transmorgrifier", "Transformed", c, transCard);
/* 47 */       AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(transCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */       
/* 49 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 55 */     switch (this.screen) {
/*    */       
/*    */       case INTRO:
/* 58 */         switch (buttonPressed) {
/*    */           case 0:
/* 60 */             this.screen = CUR_SCREEN.COMPLETE;
/* 61 */             this.imageEventText.updateBodyText(DIALOG_2);
/* 62 */             AbstractDungeon.gridSelectScreen.open(
/* 63 */                 CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck
/* 64 */                   .getPurgeableCards()), 1, OPTIONS[2], false, true, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 71 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 72 */             this.imageEventText.clearRemainingOptions();
/*    */             break;
/*    */           case 1:
/* 75 */             this.screen = CUR_SCREEN.COMPLETE;
/* 76 */             logMetricIgnored("Transmorgrifier");
/* 77 */             this.imageEventText.updateBodyText(IGNORE);
/* 78 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 79 */             this.imageEventText.clearRemainingOptions();
/*    */             break;
/*    */         } 
/*    */         break;
/*    */       case COMPLETE:
/* 84 */         openMap();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\Transmogrifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */