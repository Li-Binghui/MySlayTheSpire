/*    */ package com.megacrit.cardcrawl.events.exordium;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class BigFish
/*    */   extends AbstractImageEvent {
/*    */   public static final String ID = "Big Fish";
/* 18 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Big Fish");
/* 19 */   public static final String NAME = eventStrings.NAME;
/* 20 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 21 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/* 23 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 24 */   private static final String BANANA_RESULT = DESCRIPTIONS[1];
/* 25 */   private static final String DONUT_RESULT = DESCRIPTIONS[2];
/*    */   
/* 27 */   private static final String BOX_RESULT = DESCRIPTIONS[4];
/* 28 */   private static final String BOX_BAD = DESCRIPTIONS[5];
/*    */   
/* 30 */   private int healAmt = 0;
/*    */   private static final int MAX_HP_AMT = 5;
/* 32 */   private CurScreen screen = CurScreen.INTRO;
/*    */   
/*    */   private enum CurScreen {
/* 35 */     INTRO, RESULT;
/*    */   }
/*    */   
/*    */   public BigFish() {
/* 39 */     super(NAME, DIALOG_1, "images/events/fishing.jpg");
/* 40 */     this.healAmt = AbstractDungeon.player.maxHealth / 3;
/* 41 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.healAmt + OPTIONS[1]);
/* 42 */     this.imageEventText.setDialogOption(OPTIONS[2] + '\005' + OPTIONS[3]);
/* 43 */     this.imageEventText.setDialogOption(OPTIONS[4], CardLibrary.getCopy("Regret"));
/*    */   }
/*    */   protected void buttonEffect(int buttonPressed) {
/*    */     Regret regret;
/*    */     AbstractRelic r;
/* 48 */     switch (this.screen) {
/*    */       case INTRO:
/* 50 */         switch (buttonPressed) {
/*    */           
/*    */           case 0:
/* 53 */             AbstractDungeon.player.heal(this.healAmt, true);
/* 54 */             this.imageEventText.updateBodyText(BANANA_RESULT);
/* 55 */             AbstractEvent.logMetricHeal("Big Fish", "Banana", this.healAmt);
/*    */             break;
/*    */           
/*    */           case 1:
/* 59 */             AbstractDungeon.player.increaseMaxHp(5, true);
/* 60 */             this.imageEventText.updateBodyText(DONUT_RESULT);
/* 61 */             AbstractEvent.logMetricMaxHPGain("Big Fish", "Donut", 5);
/*    */             break;
/*    */           
/*    */           default:
/* 65 */             this.imageEventText.updateBodyText(BOX_RESULT + BOX_BAD);
/* 66 */             regret = new Regret();
/* 67 */             r = AbstractDungeon.returnRandomScreenlessRelic(
/* 68 */                 AbstractDungeon.returnRandomRelicTier());
/* 69 */             AbstractEvent.logMetricObtainCardAndRelic("Big Fish", "Box", (AbstractCard)regret, r);
/* 70 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(
/*    */                   
/* 72 */                   CardLibrary.getCopy(((AbstractCard)regret).cardID), (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*    */ 
/*    */             
/* 75 */             AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), r);
/*    */             break;
/*    */         } 
/* 78 */         this.imageEventText.clearAllDialogs();
/* 79 */         this.imageEventText.setDialogOption(OPTIONS[5]);
/* 80 */         this.screen = CurScreen.RESULT;
/*    */         return;
/*    */     } 
/* 83 */     openMap();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void logMetric(String actionTaken) {
/* 89 */     AbstractEvent.logMetric("Big Fish", actionTaken);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\BigFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */