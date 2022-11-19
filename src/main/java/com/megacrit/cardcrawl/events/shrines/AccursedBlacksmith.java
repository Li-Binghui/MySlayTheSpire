/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.WarpedTongs;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
/*     */ 
/*     */ public class AccursedBlacksmith extends AbstractImageEvent {
/*     */   public static final String ID = "Accursed Blacksmith";
/*  19 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Accursed Blacksmith");
/*  20 */   public static final String NAME = eventStrings.NAME;
/*  21 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  22 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  24 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  25 */   private static final String FORGE_RESULT = DESCRIPTIONS[1];
/*  26 */   private static final String RUMMAGE_RESULT = DESCRIPTIONS[2];
/*  27 */   private static final String CURSE_RESULT2 = DESCRIPTIONS[4];
/*  28 */   private static final String LEAVE_RESULT = DESCRIPTIONS[5];
/*     */   
/*  30 */   private int screenNum = 0;
/*     */   private boolean pickCard = false;
/*     */   
/*     */   public AccursedBlacksmith() {
/*  34 */     super(NAME, DIALOG_1, "images/events/blacksmith.jpg");
/*     */     
/*  36 */     if (AbstractDungeon.player.masterDeck.hasUpgradableCards().booleanValue()) {
/*  37 */       this.imageEventText.setDialogOption(OPTIONS[0]);
/*     */     } else {
/*  39 */       this.imageEventText.setDialogOption(OPTIONS[4], true);
/*     */     } 
/*     */     
/*  42 */     this.imageEventText.setDialogOption(OPTIONS[1], CardLibrary.getCopy("Pain"), (AbstractRelic)new WarpedTongs());
/*  43 */     this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  48 */     if (Settings.AMBIANCE_ON) {
/*  49 */       CardCrawlGame.sound.play("EVENT_FORGE");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  55 */     super.update();
/*     */ 
/*     */     
/*  58 */     if (this.pickCard && 
/*  59 */       !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/*  60 */       AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/*  61 */       c.upgrade();
/*  62 */       logMetricCardUpgrade("Accursed Blacksmith", "Forge", c);
/*  63 */       AbstractDungeon.player.bottledCardUpgradeCheck(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
/*  64 */       AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
/*  65 */       AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*  66 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*  67 */       this.pickCard = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     Pain pain;
/*  74 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  77 */         switch (buttonPressed) {
/*     */           case 0:
/*  79 */             this.pickCard = true;
/*  80 */             AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
/*  81 */                 .getUpgradableCards(), 1, OPTIONS[3], true, false, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  88 */             this.imageEventText.updateBodyText(FORGE_RESULT);
/*  89 */             this.screenNum = 2;
/*  90 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/*     */             break;
/*     */           
/*     */           case 1:
/*  94 */             this.screenNum = 2;
/*  95 */             this.imageEventText.updateBodyText(RUMMAGE_RESULT + CURSE_RESULT2);
/*  96 */             pain = new Pain();
/*  97 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)pain, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*     */             
/*  99 */             AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic)new WarpedTongs());
/*     */ 
/*     */ 
/*     */             
/* 103 */             logMetricObtainCardAndRelic("Accursed Blacksmith", "Rummage", (AbstractCard)pain, (AbstractRelic)new WarpedTongs());
/* 104 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/*     */             break;
/*     */           
/*     */           case 2:
/* 108 */             this.screenNum = 2;
/* 109 */             logMetricIgnored("Accursed Blacksmith");
/* 110 */             this.imageEventText.updateBodyText(LEAVE_RESULT);
/* 111 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/*     */             break;
/*     */         } 
/* 114 */         this.imageEventText.clearRemainingOptions();
/*     */         return;
/*     */     } 
/* 117 */     openMap();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\AccursedBlacksmith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */