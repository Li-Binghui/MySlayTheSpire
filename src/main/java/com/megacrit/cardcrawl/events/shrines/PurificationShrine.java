/*    */ package com.megacrit.cardcrawl.events.shrines;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
/*    */ 
/*    */ public class PurificationShrine
/*    */   extends AbstractImageEvent {
/*    */   public static final String ID = "Purifier";
/* 14 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Purifier");
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
/*    */   public PurificationShrine() {
/* 29 */     super(NAME, DIALOG_1, "images/events/shrine3.jpg");
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
/* 42 */       CardCrawlGame.sound.play("CARD_EXHAUST");
/* 43 */       logMetricCardRemoval("Purifier", "Purged", AbstractDungeon.gridSelectScreen.selectedCards.get(0));
/* 44 */       AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(AbstractDungeon.gridSelectScreen.selectedCards
/*    */             
/* 46 */             .get(0), (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*    */ 
/*    */       
/* 49 */       AbstractDungeon.player.masterDeck.removeCard(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
/* 50 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 56 */     switch (this.screen) {
/*    */       
/*    */       case INTRO:
/* 59 */         switch (buttonPressed) {
/*    */           case 0:
/* 61 */             this.screen = CUR_SCREEN.COMPLETE;
/* 62 */             this.imageEventText.updateBodyText(DIALOG_2);
/* 63 */             AbstractDungeon.gridSelectScreen.open(
/* 64 */                 CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck
/* 65 */                   .getPurgeableCards()), 1, OPTIONS[2], false, false, false, true);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 72 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 73 */             this.imageEventText.clearRemainingOptions();
/*    */             break;
/*    */           case 1:
/* 76 */             this.screen = CUR_SCREEN.COMPLETE;
/* 77 */             logMetricIgnored("Purifier");
/* 78 */             this.imageEventText.updateBodyText(IGNORE);
/* 79 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 80 */             this.imageEventText.clearRemainingOptions();
/*    */             break;
/*    */         } 
/*    */         break;
/*    */       case COMPLETE:
/* 85 */         openMap();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\PurificationShrine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */