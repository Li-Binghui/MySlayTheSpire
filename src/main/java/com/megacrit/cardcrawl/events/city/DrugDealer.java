/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.JAX;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.relics.MutagenicStrength;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*     */ 
/*     */ public class DrugDealer
/*     */   extends AbstractImageEvent
/*     */ {
/*  24 */   private static final Logger logger = LogManager.getLogger(DrugDealer.class.getName());
/*     */   public static final String ID = "Drug Dealer";
/*  26 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Drug Dealer");
/*  27 */   public static final String NAME = eventStrings.NAME;
/*  28 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  29 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  31 */   private int screenNum = 0;
/*     */   private boolean cardsSelected = false;
/*     */   
/*     */   public DrugDealer() {
/*  35 */     super(NAME, DESCRIPTIONS[0], "images/events/drugDealer.jpg");
/*     */     
/*  37 */     this.imageEventText.setDialogOption(OPTIONS[0], CardLibrary.getCopy("J.A.X."));
/*  38 */     if (AbstractDungeon.player.masterDeck.getPurgeableCards().size() >= 2) {
/*  39 */       this.imageEventText.setDialogOption(OPTIONS[1]);
/*     */     } else {
/*  41 */       this.imageEventText.setDialogOption(OPTIONS[4], true);
/*     */     } 
/*  43 */     this.imageEventText.setDialogOption(OPTIONS[2], (AbstractRelic)new MutagenicStrength());
/*     */   }
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     JAX jAX;
/*     */     Circlet circlet = null;
/*  48 */     switch (this.screenNum) {
/*     */       case 0:
/*  50 */         switch (buttonPressed) {
/*     */           case 0:
/*  52 */             jAX = new JAX();
/*  53 */             logMetricObtainCard("Drug Dealer", "Obtain J.A.X.", (AbstractCard)jAX);
/*  54 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*  55 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)jAX, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*     */             
/*  57 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  58 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           case 1:
/*  61 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/*  62 */             transform();
/*  63 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  64 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           case 2:
/*  67 */             this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
/*     */             
/*  69 */             if (!AbstractDungeon.player.hasRelic("MutagenicStrength")) {
/*  70 */               MutagenicStrength mutagenicStrength = new MutagenicStrength();
/*  71 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, (AbstractRelic)mutagenicStrength);
/*     */             } else {
/*  73 */               circlet = new Circlet();
/*  74 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, (AbstractRelic)circlet);
/*     */             } 
/*  76 */             logMetricObtainRelic("Drug Dealer", "Inject Mutagens", (AbstractRelic)circlet);
/*  77 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  78 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           default:
/*  81 */             logger.info("ERROR: Unhandled case " + buttonPressed);
/*     */             break;
/*     */         } 
/*  84 */         this.screenNum = 1;
/*     */         break;
/*     */       case 1:
/*  87 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/*  93 */     super.update();
/*  94 */     if (!this.cardsSelected) {
/*  95 */       List<String> transformedCards = new ArrayList<>();
/*  96 */       List<String> obtainedCards = new ArrayList<>();
/*  97 */       if (AbstractDungeon.gridSelectScreen.selectedCards.size() == 2) {
/*  98 */         this.cardsSelected = true;
/*  99 */         float displayCount = 0.0F;
/* 100 */         Iterator<AbstractCard> i = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
/* 101 */         while (i.hasNext()) {
/* 102 */           AbstractCard card = i.next();
/* 103 */           card.untip();
/* 104 */           card.unhover();
/* 105 */           transformedCards.add(card.cardID);
/* 106 */           AbstractDungeon.player.masterDeck.removeCard(card);
/* 107 */           AbstractDungeon.transformCard(card, false, AbstractDungeon.miscRng);
/*     */           
/* 109 */           AbstractCard c = AbstractDungeon.getTransformedCard();
/* 110 */           obtainedCards.add(c.cardID);
/*     */           
/* 112 */           if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.TRANSFORM && c != null) {
/* 113 */             AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(c
/*     */                   
/* 115 */                   .makeCopy(), Settings.WIDTH / 3.0F + displayCount, Settings.HEIGHT / 2.0F, false));
/*     */ 
/*     */ 
/*     */             
/* 119 */             displayCount += Settings.WIDTH / 6.0F;
/*     */           } 
/*     */         } 
/* 122 */         AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 123 */         logMetricTransformCards("Drug Dealer", "Became Test Subject", transformedCards, obtainedCards);
/* 124 */         (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void transform() {
/* 130 */     if (!AbstractDungeon.isScreenUp) {
/* 131 */       AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
/* 132 */           .getPurgeableCards(), 2, OPTIONS[5], false, false, false, false);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 140 */       AbstractDungeon.dynamicBanner.hide();
/* 141 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/* 142 */       AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
/* 143 */           .getPurgeableCards(), 2, OPTIONS[5], false, false, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\DrugDealer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */