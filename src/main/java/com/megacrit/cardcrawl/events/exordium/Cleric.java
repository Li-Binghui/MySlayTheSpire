/*     */ package com.megacrit.cardcrawl.events.exordium;
/*     */ 
/*     */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
/*     */ 
/*     */ public class Cleric
/*     */   extends AbstractImageEvent {
/*     */   public static final String ID = "The Cleric";
/*  16 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("The Cleric");
/*  17 */   public static final String NAME = eventStrings.NAME;
/*  18 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  19 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   public static final int HEAL_COST = 35;
/*     */   private static final int PURIFY_COST = 50;
/*     */   private static final int A_2_PURIFY_COST = 75;
/*  23 */   private int purifyCost = 0;
/*     */   
/*     */   private static final float HEAL_AMT = 0.25F;
/*     */   
/*  27 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  28 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/*  29 */   private static final String DIALOG_3 = DESCRIPTIONS[2];
/*  30 */   private static final String DIALOG_4 = DESCRIPTIONS[3];
/*     */   
/*     */   private int healAmt;
/*     */   
/*     */   public Cleric() {
/*  35 */     super(NAME, DIALOG_1, "images/events/cleric.jpg");
/*     */     
/*  37 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  38 */       this.purifyCost = 75;
/*     */     } else {
/*  40 */       this.purifyCost = 50;
/*     */     } 
/*     */     
/*  43 */     int gold = AbstractDungeon.player.gold;
/*  44 */     if (gold >= 35) {
/*  45 */       this.healAmt = (int)(AbstractDungeon.player.maxHealth * 0.25F);
/*  46 */       this.imageEventText.setDialogOption(OPTIONS[0] + this.healAmt + OPTIONS[8], (gold < 35));
/*     */     } else {
/*  48 */       this.imageEventText.setDialogOption(OPTIONS[1] + '#' + OPTIONS[2], (gold < 35));
/*     */     } 
/*     */     
/*  51 */     if (gold >= 50) {
/*  52 */       this.imageEventText.setDialogOption(OPTIONS[3] + this.purifyCost + OPTIONS[4], (gold < this.purifyCost));
/*     */     } else {
/*  54 */       this.imageEventText.setDialogOption(OPTIONS[5], (gold < this.purifyCost));
/*     */     } 
/*     */     
/*  57 */     this.imageEventText.setDialogOption(OPTIONS[6]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  62 */     super.update();
/*     */ 
/*     */     
/*  65 */     if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/*  66 */       AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/*  67 */       AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*  68 */       AbstractEvent.logMetricCardRemovalAtCost("The Cleric", "Card Removal", c, this.purifyCost);
/*  69 */       AbstractDungeon.player.masterDeck.removeCard(c);
/*  70 */       AbstractDungeon.gridSelectScreen.selectedCards.remove(c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  76 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  79 */         switch (buttonPressed) {
/*     */           case 0:
/*  81 */             AbstractDungeon.player.loseGold(35);
/*  82 */             AbstractDungeon.player.heal(this.healAmt);
/*  83 */             showProceedScreen(DIALOG_2);
/*  84 */             AbstractEvent.logMetricHealAtCost("The Cleric", "Healed", 35, this.healAmt);
/*     */             return;
/*     */           case 1:
/*  87 */             if (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards())
/*  88 */               .size() > 0) {
/*  89 */               AbstractDungeon.player.loseGold(this.purifyCost);
/*  90 */               AbstractDungeon.gridSelectScreen.open(
/*  91 */                   CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck
/*  92 */                     .getPurgeableCards()), 1, OPTIONS[7], false, false, false, true);
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 100 */             showProceedScreen(DIALOG_3);
/*     */             return;
/*     */         } 
/* 103 */         showProceedScreen(DIALOG_4);
/* 104 */         AbstractEvent.logMetric("The Cleric", "Leave");
/*     */         return;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 110 */     openMap();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\Cleric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */