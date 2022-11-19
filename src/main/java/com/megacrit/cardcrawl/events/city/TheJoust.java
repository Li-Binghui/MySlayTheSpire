/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ 
/*     */ public class TheJoust
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "The Joust";
/*  14 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("The Joust");
/*  15 */   public static final String NAME = eventStrings.NAME;
/*  16 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  17 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  19 */   private static final String HALT_MSG = DESCRIPTIONS[0];
/*  20 */   private static final String EXPL_MSG = DESCRIPTIONS[1];
/*  21 */   private static final String BET_AGAINST = DESCRIPTIONS[2];
/*  22 */   private static final String BET_FOR = DESCRIPTIONS[3];
/*  23 */   private static final String COMBAT_MSG = DESCRIPTIONS[4];
/*  24 */   private static final String NOODLES_WIN = DESCRIPTIONS[5];
/*  25 */   private static final String NOODLES_LOSE = DESCRIPTIONS[6];
/*  26 */   private static final String BET_WON_MSG = DESCRIPTIONS[7];
/*  27 */   private static final String BET_LOSE_MSG = DESCRIPTIONS[8];
/*     */   
/*     */   private boolean betFor;
/*     */   private boolean ownerWins;
/*  31 */   private CUR_SCREEN screen = CUR_SCREEN.HALT;
/*     */   
/*     */   private static final int WIN_OWNER = 250;
/*  34 */   private float joustTimer = 0.0F; private static final int WIN_MURDERER = 100; private static final int BET_AMT = 50;
/*  35 */   private int clangCount = 0;
/*     */   
/*     */   private enum CUR_SCREEN {
/*  38 */     HALT, EXPLANATION, PRE_JOUST, JOUST, COMPLETE;
/*     */   }
/*     */   
/*     */   public TheJoust() {
/*  42 */     super(NAME, HALT_MSG, "images/events/joust.jpg");
/*  43 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  48 */     super.update();
/*  49 */     if (this.joustTimer != 0.0F) {
/*  50 */       this.joustTimer -= Gdx.graphics.getDeltaTime();
/*  51 */       if (this.joustTimer < 0.0F) {
/*  52 */         this.clangCount++;
/*  53 */         if (this.clangCount == 1) {
/*  54 */           CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, false);
/*  55 */           CardCrawlGame.sound.play("BLUNT_HEAVY");
/*  56 */           this.joustTimer = 1.0F;
/*  57 */         } else if (this.clangCount == 2) {
/*  58 */           CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
/*  59 */           CardCrawlGame.sound.play("BLUNT_FAST");
/*  60 */           this.joustTimer = 0.25F;
/*  61 */         } else if (this.clangCount == 3) {
/*  62 */           CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.LONG, false);
/*  63 */           CardCrawlGame.sound.play("BLUNT_HEAVY");
/*  64 */           this.joustTimer = 0.0F;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     String tmp;
/*  72 */     switch (this.screen) {
/*     */       case HALT:
/*  74 */         this.imageEventText.updateBodyText(EXPL_MSG);
/*  75 */         this.imageEventText.updateDialogOption(0, OPTIONS[1] + '2' + OPTIONS[2] + 'd' + OPTIONS[3]);
/*  76 */         this.imageEventText.setDialogOption(OPTIONS[4] + '2' + OPTIONS[5] + 'ú' + OPTIONS[3]);
/*  77 */         this.screen = CUR_SCREEN.EXPLANATION;
/*     */         break;
/*     */       case EXPLANATION:
/*  80 */         if (buttonPressed == 0) {
/*  81 */           this.betFor = false;
/*  82 */           this.imageEventText.updateBodyText(BET_AGAINST);
/*     */         } else {
/*  84 */           this.betFor = true;
/*  85 */           this.imageEventText.updateBodyText(BET_FOR);
/*     */         } 
/*  87 */         AbstractDungeon.player.loseGold(50);
/*  88 */         this.imageEventText.updateDialogOption(0, OPTIONS[6]);
/*  89 */         this.imageEventText.clearRemainingOptions();
/*  90 */         this.screen = CUR_SCREEN.PRE_JOUST;
/*     */         break;
/*     */       case PRE_JOUST:
/*  93 */         this.imageEventText.updateBodyText(COMBAT_MSG);
/*  94 */         this.imageEventText.updateDialogOption(0, OPTIONS[6]);
/*  95 */         this.ownerWins = AbstractDungeon.miscRng.randomBoolean(0.3F);
/*  96 */         this.screen = CUR_SCREEN.JOUST;
/*  97 */         this.joustTimer = 0.01F;
/*     */         break;
/*     */       case JOUST:
/* 100 */         this.imageEventText.updateDialogOption(0, OPTIONS[7]);
/*     */ 
/*     */         
/* 103 */         if (this.ownerWins) {
/* 104 */           tmp = NOODLES_WIN;
/* 105 */           if (this.betFor) {
/* 106 */             tmp = tmp + BET_WON_MSG;
/* 107 */             AbstractDungeon.player.gainGold(250);
/* 108 */             CardCrawlGame.sound.play("GOLD_GAIN");
/* 109 */             logMetricGainAndLoseGold("The Joust", "Bet on Owner", 250, 50);
/*     */           } else {
/* 111 */             tmp = tmp + BET_LOSE_MSG;
/* 112 */             logMetricLoseGold("The Joust", "Bet on Owner", 50);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 117 */           tmp = NOODLES_LOSE;
/* 118 */           if (this.betFor) {
/* 119 */             tmp = tmp + BET_LOSE_MSG;
/* 120 */             logMetricLoseGold("The Joust", "Bet on Murderer", 50);
/*     */           } else {
/* 122 */             tmp = tmp + BET_WON_MSG;
/* 123 */             AbstractDungeon.player.gainGold(100);
/* 124 */             CardCrawlGame.sound.play("GOLD_GAIN");
/* 125 */             logMetricGainAndLoseGold("The Joust", "Bet on Murderer", 100, 50);
/*     */           } 
/*     */         } 
/* 128 */         this.imageEventText.updateBodyText(tmp);
/* 129 */         this.screen = CUR_SCREEN.COMPLETE;
/*     */         break;
/*     */       case COMPLETE:
/* 132 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\TheJoust.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */