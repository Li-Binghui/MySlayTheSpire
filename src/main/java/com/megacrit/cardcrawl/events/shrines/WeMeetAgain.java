/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class WeMeetAgain
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "WeMeetAgain";
/*  23 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("WeMeetAgain");
/*  24 */   public static final String NAME = eventStrings.NAME;
/*  25 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  26 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*     */   private static final int MAX_GOLD = 150;
/*     */   
/*     */   private AbstractPotion potionOption;
/*     */   private AbstractCard cardOption;
/*     */   private int goldAmount;
/*     */   private AbstractRelic givenRelic;
/*  34 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  35 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*     */   
/*     */   private enum CUR_SCREEN {
/*  38 */     INTRO, COMPLETE;
/*     */   }
/*     */   
/*     */   public WeMeetAgain() {
/*  42 */     super(NAME, DIALOG_1, "images/events/weMeetAgain.jpg");
/*     */ 
/*     */     
/*  45 */     this.potionOption = AbstractDungeon.player.getRandomPotion();
/*  46 */     if (this.potionOption != null) {
/*  47 */       this.imageEventText.setDialogOption(OPTIONS[0] + FontHelper.colorString(this.potionOption.name, "r") + OPTIONS[6]);
/*     */     } else {
/*  49 */       this.imageEventText.setDialogOption(OPTIONS[1], true);
/*     */     } 
/*     */ 
/*     */     
/*  53 */     this.goldAmount = getGoldAmount();
/*  54 */     if (this.goldAmount != 0) {
/*  55 */       this.imageEventText.setDialogOption(OPTIONS[2] + this.goldAmount + OPTIONS[9] + OPTIONS[6]);
/*     */     } else {
/*  57 */       this.imageEventText.setDialogOption(OPTIONS[3], true);
/*     */     } 
/*     */ 
/*     */     
/*  61 */     this.cardOption = getRandomNonBasicCard();
/*  62 */     if (this.cardOption != null) {
/*  63 */       this.imageEventText.setDialogOption(OPTIONS[4] + this.cardOption.name + OPTIONS[6], this.cardOption
/*     */           
/*  65 */           .makeStatEquivalentCopy());
/*     */     } else {
/*  67 */       this.imageEventText.setDialogOption(OPTIONS[5], true);
/*     */     } 
/*     */ 
/*     */     
/*  71 */     this.imageEventText.setDialogOption(OPTIONS[7]);
/*     */   }
/*     */   
/*     */   private AbstractCard getRandomNonBasicCard() {
/*  75 */     ArrayList<AbstractCard> list = new ArrayList<>();
/*  76 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/*  77 */       if (c.rarity != AbstractCard.CardRarity.BASIC && c.type != AbstractCard.CardType.CURSE) {
/*  78 */         list.add(c);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  83 */     if (list.isEmpty()) {
/*  84 */       return null;
/*     */     }
/*     */     
/*  87 */     Collections.shuffle(list, new Random(AbstractDungeon.miscRng.randomLong()));
/*  88 */     return list.get(0);
/*     */   }
/*     */   
/*     */   private int getGoldAmount() {
/*  92 */     if (AbstractDungeon.player.gold < 50) {
/*  93 */       return 0;
/*     */     }
/*  95 */     if (AbstractDungeon.player.gold > 150) {
/*  96 */       return AbstractDungeon.miscRng.random(50, 150);
/*     */     }
/*  98 */     return AbstractDungeon.miscRng.random(50, AbstractDungeon.player.gold);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/* 105 */     switch (this.screen) {
/*     */       
/*     */       case INTRO:
/* 108 */         this.screen = CUR_SCREEN.COMPLETE;
/* 109 */         switch (buttonPressed) {
/*     */           
/*     */           case 0:
/* 112 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1] + DESCRIPTIONS[5]);
/* 113 */             AbstractDungeon.player.removePotion(this.potionOption);
/* 114 */             relicReward();
/* 115 */             logMetricObtainRelic("WeMeetAgain", "Gave Potion", this.givenRelic);
/*     */             break;
/*     */           
/*     */           case 1:
/* 119 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2] + DESCRIPTIONS[5]);
/* 120 */             AbstractDungeon.player.loseGold(this.goldAmount);
/* 121 */             relicReward();
/* 122 */             logMetricObtainRelicAtCost("WeMeetAgain", "Paid Gold", this.givenRelic, this.goldAmount);
/*     */             break;
/*     */           
/*     */           case 2:
/* 126 */             this.imageEventText.updateBodyText(DESCRIPTIONS[3] + DESCRIPTIONS[5]);
/* 127 */             AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(this.cardOption, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */             
/* 129 */             AbstractDungeon.player.masterDeck.removeCard(this.cardOption);
/* 130 */             relicReward();
/* 131 */             logMetricRemoveCardAndObtainRelic("WeMeetAgain", "Gave Card", this.cardOption, this.givenRelic);
/*     */             break;
/*     */           
/*     */           case 3:
/* 135 */             this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
/* 136 */             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);
/* 137 */             CardCrawlGame.sound.play("BLUNT_HEAVY");
/* 138 */             logMetricIgnored("WeMeetAgain");
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 145 */         this.imageEventText.updateDialogOption(0, OPTIONS[8]);
/* 146 */         this.imageEventText.clearRemainingOptions();
/*     */         break;
/*     */       case COMPLETE:
/* 149 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void relicReward() {
/* 155 */     this.givenRelic = AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
/* 156 */     AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH * 0.28F, Settings.HEIGHT / 2.0F, this.givenRelic);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\WeMeetAgain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */