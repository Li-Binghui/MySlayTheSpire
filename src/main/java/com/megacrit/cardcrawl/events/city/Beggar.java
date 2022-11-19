/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */

import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
/*     */ 
/*     */ public class Beggar extends AbstractImageEvent {
/*     */   public static final String ID = "Beggar";
/*  13 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Beggar");
/*  14 */   public static final String NAME = eventStrings.NAME;
/*  15 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  16 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*     */   private CurScreen screen;
/*     */   public static final int GOLD_COST = 75;
/*  20 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  21 */   private static final String CANCEL_DIALOG = DESCRIPTIONS[1];
/*  22 */   private static final String PURGE_DIALOG = DESCRIPTIONS[2];
/*  23 */   private static final String POST_PURGE_DIALOG = DESCRIPTIONS[3];
/*     */   
/*     */   public enum CurScreen {
/*  26 */     INTRO, LEAVE, GAVE_MONEY;
/*     */   }
/*     */   
/*     */   public Beggar() {
/*  30 */     super(NAME, DIALOG_1, "images/events/beggar.jpg");
/*     */     
/*  32 */     if (AbstractDungeon.player.gold >= 75) {
/*  33 */       this.imageEventText.setDialogOption(OPTIONS[0] + 'K' + OPTIONS[1], (AbstractDungeon.player.gold < 75));
/*     */     }
/*     */     else {
/*     */       
/*  37 */       this.imageEventText.setDialogOption(OPTIONS[2] + 'K' + OPTIONS[3], (AbstractDungeon.player.gold < 75));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.imageEventText.setDialogOption(OPTIONS[5]);
/*  43 */     this.hasDialog = true;
/*  44 */     this.hasFocus = true;
/*  45 */     this.screen = CurScreen.INTRO;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  50 */     super.update();
/*     */ 
/*     */     
/*  53 */     if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/*  54 */       CardCrawlGame.sound.play("CARD_EXHAUST");
/*  55 */       logMetricCardRemovalAtCost("Beggar", "Gave Gold", AbstractDungeon.gridSelectScreen.selectedCards
/*     */ 
/*     */           
/*  58 */           .get(0), 75);
/*     */       
/*  60 */       AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(AbstractDungeon.gridSelectScreen.selectedCards
/*     */             
/*  62 */             .get(0), (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*     */ 
/*     */       
/*  65 */       AbstractDungeon.player.masterDeck.removeCard(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
/*  66 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*     */       
/*  68 */       openMap();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  74 */     switch (this.screen) {
/*     */       case INTRO:
/*  76 */         if (buttonPressed == 0) {
/*  77 */           this.imageEventText.loadImage("images/events/cleric.jpg");
/*  78 */           this.imageEventText.updateBodyText(PURGE_DIALOG);
/*  79 */           AbstractDungeon.player.loseGold(75);
/*  80 */           this.imageEventText.clearAllDialogs();
/*  81 */           this.imageEventText.setDialogOption(OPTIONS[4]);
/*  82 */           this.screen = CurScreen.GAVE_MONEY; break;
/*     */         } 
/*  84 */         this.imageEventText.updateBodyText(CANCEL_DIALOG);
/*  85 */         this.imageEventText.clearAllDialogs();
/*  86 */         this.imageEventText.setDialogOption(OPTIONS[5]);
/*  87 */         this.screen = CurScreen.LEAVE;
/*  88 */         logMetricIgnored("Beggar");
/*     */         break;
/*     */       
/*     */       case GAVE_MONEY:
/*  92 */         AbstractDungeon.gridSelectScreen.open(
/*  93 */             CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[6], false, false, false, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 100 */         this.imageEventText.updateBodyText(POST_PURGE_DIALOG);
/* 101 */         this.imageEventText.clearAllDialogs();
/* 102 */         this.imageEventText.setDialogOption(OPTIONS[5]);
/* 103 */         this.screen = CurScreen.LEAVE;
/*     */         break;
/*     */       case LEAVE:
/* 106 */         this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/* 107 */         this.imageEventText.clearRemainingOptions();
/* 108 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\Beggar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */