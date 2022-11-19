/*    */ package com.megacrit.cardcrawl.events.city;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Shame;
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
/*    */ public class Addict extends AbstractImageEvent {
/*    */   public static final String ID = "Addict";
/* 17 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Addict");
/* 18 */   public static final String NAME = eventStrings.NAME;
/* 19 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 20 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/*    */   private static final int GOLD_COST = 85;
/* 23 */   private int screenNum = 0;
/*    */   
/*    */   public Addict() {
/* 26 */     super(NAME, DESCRIPTIONS[0], "images/events/addict.jpg");
/*    */     
/* 28 */     if (AbstractDungeon.player.gold >= 85) {
/* 29 */       this.imageEventText.setDialogOption(OPTIONS[0] + 'U' + OPTIONS[1], (AbstractDungeon.player.gold < 85));
/*    */     }
/*    */     else {
/*    */       
/* 33 */       this.imageEventText.setDialogOption(OPTIONS[2] + 'U' + OPTIONS[3], (AbstractDungeon.player.gold < 85));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 38 */     this.imageEventText.setDialogOption(OPTIONS[4], CardLibrary.getCopy("Shame"));
/* 39 */     this.imageEventText.setDialogOption(OPTIONS[5]);
/*    */   }
/*    */   protected void buttonEffect(int buttonPressed) {
/*    */     Shame shame;
/*    */     AbstractRelic relic;
/* 44 */     switch (this.screenNum) {
/*    */       case 0:
/* 46 */         switch (buttonPressed) {
/*    */           case 0:
/* 48 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/* 49 */             if (AbstractDungeon.player.gold >= 85) {
/* 50 */               AbstractRelic abstractRelic = AbstractDungeon.returnRandomScreenlessRelic(
/* 51 */                   AbstractDungeon.returnRandomRelicTier());
/* 52 */               AbstractEvent.logMetricObtainRelicAtCost("Addict", "Obtained Relic", abstractRelic, 85);
/* 53 */               AbstractDungeon.player.loseGold(85);
/* 54 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, abstractRelic);
/* 55 */               this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/* 56 */               this.imageEventText.clearRemainingOptions();
/*    */             } 
/*    */             break;
/*    */           case 1:
/* 60 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/*    */             
/* 62 */             shame = new Shame();
/* 63 */             relic = AbstractDungeon.returnRandomScreenlessRelic(
/* 64 */                 AbstractDungeon.returnRandomRelicTier());
/* 65 */             AbstractEvent.logMetricObtainCardAndRelic("Addict", "Stole Relic", (AbstractCard)shame, relic);
/* 66 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)shame, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*    */             
/* 68 */             AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, relic);
/*    */             
/* 70 */             this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/* 71 */             this.imageEventText.clearRemainingOptions();
/*    */             break;
/*    */           
/*    */           default:
/* 75 */             this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/* 76 */             this.imageEventText.clearRemainingOptions();
/* 77 */             openMap();
/*    */             break;
/*    */         } 
/* 80 */         this.screenNum = 1;
/*    */         break;
/*    */       case 1:
/* 83 */         openMap();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\Addict.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */