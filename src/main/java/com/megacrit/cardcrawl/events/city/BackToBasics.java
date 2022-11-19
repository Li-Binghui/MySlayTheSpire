/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.List;
/*     */ 
/*     */ 
/*     */ public class BackToBasics
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "Back to Basics";
/*  22 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Back to Basics");
/*  23 */   public static final String NAME = eventStrings.NAME;
/*  24 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  25 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  27 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  28 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/*  29 */   private static final String DIALOG_3 = DESCRIPTIONS[2];
/*  30 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*     */   
/*  32 */   private List<String> cardsUpgraded = new ArrayList<>();
/*     */   
/*     */   private enum CUR_SCREEN {
/*  35 */     INTRO, COMPLETE;
/*     */   }
/*     */   
/*     */   public BackToBasics() {
/*  39 */     super(NAME, DIALOG_1, "images/events/backToBasics.jpg");
/*  40 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*  41 */     this.imageEventText.setDialogOption(OPTIONS[1]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  46 */     if (Settings.AMBIANCE_ON) {
/*  47 */       CardCrawlGame.sound.play("EVENT_ANCIENT");
/*     */     }
/*  49 */     this.cardsUpgraded.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  54 */     super.update();
/*     */ 
/*     */     
/*  57 */     if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/*  58 */       AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/*  59 */       AbstractDungeon.effectList.add(new PurgeCardEffect(c));
/*  60 */       AbstractEvent.logMetricCardRemoval("Back to Basics", "Elegance", c);
/*  61 */       AbstractDungeon.player.masterDeck.removeCard(c);
/*  62 */       AbstractDungeon.gridSelectScreen.selectedCards.remove(c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  68 */     switch (this.screen) {
/*     */       
/*     */       case INTRO:
/*  71 */         if (buttonPressed == 0) {
/*  72 */           if (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards())
/*  73 */             .size() > 0) {
/*  74 */             this.imageEventText.updateBodyText(DIALOG_2);
/*  75 */             AbstractDungeon.gridSelectScreen.open(
/*  76 */                 CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck
/*  77 */                   .getPurgeableCards()), 1, OPTIONS[2], false);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/*  82 */           this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  83 */           this.imageEventText.clearRemainingOptions();
/*     */         } else {
/*  85 */           this.imageEventText.updateBodyText(DIALOG_3);
/*  86 */           upgradeStrikeAndDefends();
/*  87 */           this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  88 */           this.imageEventText.clearRemainingOptions();
/*     */         } 
/*  90 */         this.screen = CUR_SCREEN.COMPLETE;
/*     */         break;
/*     */       case COMPLETE:
/*  93 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void upgradeStrikeAndDefends() {
/*  99 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 100 */       if ((c.hasTag(AbstractCard.CardTags.STARTER_DEFEND) || c.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) && 
/* 101 */         c.canUpgrade()) {
/* 102 */         c.upgrade();
/* 103 */         this.cardsUpgraded.add(c.cardID);
/* 104 */         AbstractDungeon.player.bottledCardUpgradeCheck(c);
/* 105 */         AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c
/*     */               
/* 107 */               .makeStatEquivalentCopy(), 
/* 108 */               MathUtils.random(0.1F, 0.9F) * Settings.WIDTH, 
/* 109 */               MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT));
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     AbstractEvent.logMetricUpgradeCards("Back to Basics", "Simplicity", this.cardsUpgraded);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\BackToBasics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */