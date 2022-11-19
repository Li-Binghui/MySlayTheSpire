/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.helpers.MonsterHelper;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ 
/*     */ public class Colosseum
/*     */   extends AbstractImageEvent {
/*     */   public static final String ID = "Colosseum";
/*  15 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Colosseum");
/*  16 */   public static final String NAME = eventStrings.NAME;
/*  17 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  18 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  20 */   private CurScreen screen = CurScreen.INTRO;
/*     */   
/*     */   private enum CurScreen {
/*  23 */     INTRO, FIGHT, LEAVE, POST_COMBAT;
/*     */   }
/*     */   
/*     */   public Colosseum() {
/*  27 */     super(NAME, DESCRIPTIONS[0], "images/events/colosseum.jpg");
/*     */ 
/*     */     
/*  30 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  35 */     switch (this.screen) {
/*     */       case INTRO:
/*  37 */         switch (buttonPressed) {
/*     */           case 0:
/*  39 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1] + DESCRIPTIONS[2] + 'ၨ' + DESCRIPTIONS[3]);
/*  40 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/*  41 */             this.screen = CurScreen.FIGHT;
/*     */             break;
/*     */         } 
/*     */         return;
/*     */       case FIGHT:
/*  46 */         switch (buttonPressed) {
/*     */           case 0:
/*  48 */             this.screen = CurScreen.POST_COMBAT;
/*  49 */             logMetric("Fight");
/*  50 */             (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("Colosseum Slavers");
/*     */             
/*  52 */             (AbstractDungeon.getCurrRoom()).rewards.clear();
/*  53 */             (AbstractDungeon.getCurrRoom()).rewardAllowed = false;
/*  54 */             enterCombatFromImage();
/*  55 */             AbstractDungeon.lastCombatMetricKey = "Colosseum Slavers";
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/*  60 */         this.imageEventText.clearRemainingOptions();
/*     */         return;
/*     */       case POST_COMBAT:
/*  63 */         (AbstractDungeon.getCurrRoom()).rewardAllowed = true;
/*  64 */         switch (buttonPressed) {
/*     */           case 1:
/*  66 */             this.screen = CurScreen.LEAVE;
/*  67 */             logMetric("Fought Nobs");
/*  68 */             (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("Colosseum Nobs");
/*     */             
/*  70 */             (AbstractDungeon.getCurrRoom()).rewards.clear();
/*  71 */             AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
/*  72 */             AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.UNCOMMON);
/*  73 */             AbstractDungeon.getCurrRoom().addGoldToRewards(100);
/*  74 */             (AbstractDungeon.getCurrRoom()).eliteTrigger = true;
/*  75 */             enterCombatFromImage();
/*  76 */             AbstractDungeon.lastCombatMetricKey = "Colosseum Nobs";
/*     */             return;
/*     */         } 
/*  79 */         logMetric("Fled From Nobs");
/*  80 */         openMap();
/*     */         return;
/*     */ 
/*     */       
/*     */       case LEAVE:
/*  85 */         openMap();
/*     */         return;
/*     */     } 
/*  88 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void logMetric(String actionTaken) {
/*  94 */     AbstractEvent.logMetric("Colosseum", actionTaken);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reopen() {
/*  99 */     if (this.screen != CurScreen.LEAVE) {
/* 100 */       AbstractDungeon.resetPlayer();
/* 101 */       AbstractDungeon.player.drawX = Settings.WIDTH * 0.25F;
/* 102 */       AbstractDungeon.player.preBattlePrep();
/* 103 */       enterImageFromCombat();
/* 104 */       this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
/* 105 */       this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/* 106 */       this.imageEventText.setDialogOption(OPTIONS[3]);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\Colosseum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */