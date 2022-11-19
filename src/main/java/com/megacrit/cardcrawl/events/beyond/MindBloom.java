/*     */ package com.megacrit.cardcrawl.events.beyond;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/*     */ 
/*     */ public class MindBloom
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "MindBloom";
/*  29 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("MindBloom");
/*  30 */   public static final String NAME = eventStrings.NAME;
/*  31 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  32 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  34 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  35 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/*  36 */   private static final String DIALOG_3 = DESCRIPTIONS[2];
/*     */   
/*  38 */   private CurScreen screen = CurScreen.INTRO;
/*     */   
/*     */   private enum CurScreen {
/*  41 */     INTRO, FIGHT, LEAVE;
/*     */   }
/*     */   
/*     */   public MindBloom() {
/*  45 */     super(NAME, DIALOG_1, "images/events/mindBloom.jpg");
/*     */ 
/*     */     
/*  48 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*     */ 
/*     */     
/*  51 */     this.imageEventText.setDialogOption(OPTIONS[3]);
/*     */ 
/*     */     
/*  54 */     if (AbstractDungeon.floorNum % 50 <= 40) {
/*  55 */       this.imageEventText.setDialogOption(OPTIONS[1], CardLibrary.getCopy("Normality"));
/*     */     } else {
/*  57 */       this.imageEventText.setDialogOption(OPTIONS[2], CardLibrary.getCopy("Doubt"));
/*     */     }  } protected void buttonEffect(int buttonPressed) {
/*     */     ArrayList<String> list;
/*     */     int effectCount;
/*     */     List<String> upgradedCards, obtainedRelic;
/*     */     Doubt doubt;
/*  63 */     switch (this.screen) {
/*     */       case INTRO:
/*  65 */         switch (buttonPressed) {
/*     */           case 0:
/*  67 */             this.imageEventText.updateBodyText(DIALOG_2);
/*  68 */             this.screen = CurScreen.FIGHT;
/*  69 */             logMetric("MindBloom", "Fight");
/*  70 */             CardCrawlGame.music.playTempBgmInstantly("MINDBLOOM", true);
/*  71 */             list = new ArrayList<>();
/*  72 */             list.add("The Guardian");
/*  73 */             list.add("Hexaghost");
/*  74 */             list.add("Slime Boss");
/*  75 */             Collections.shuffle(list, new Random(AbstractDungeon.miscRng.randomLong()));
/*  76 */             (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter(list.get(0));
/*  77 */             (AbstractDungeon.getCurrRoom()).rewards.clear();
/*  78 */             if (AbstractDungeon.ascensionLevel >= 13) {
/*  79 */               AbstractDungeon.getCurrRoom().addGoldToRewards(25);
/*     */             } else {
/*  81 */               AbstractDungeon.getCurrRoom().addGoldToRewards(50);
/*     */             } 
/*  83 */             AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
/*  84 */             enterCombatFromImage();
/*  85 */             AbstractDungeon.lastCombatMetricKey = "Mind Bloom Boss Battle";
/*     */             break;
/*     */           case 1:
/*  88 */             this.imageEventText.updateBodyText(DIALOG_3);
/*  89 */             this.screen = CurScreen.LEAVE;
/*  90 */             effectCount = 0;
/*  91 */             upgradedCards = new ArrayList<>();
/*  92 */             obtainedRelic = new ArrayList<>();
/*  93 */             for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/*  94 */               if (c.canUpgrade()) {
/*  95 */                 effectCount++;
/*  96 */                 if (effectCount <= 20) {
/*  97 */                   float x = MathUtils.random(0.1F, 0.9F) * Settings.WIDTH;
/*  98 */                   float y = MathUtils.random(0.2F, 0.8F) * Settings.HEIGHT;
/*     */                   
/* 100 */                   AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c
/* 101 */                         .makeStatEquivalentCopy(), x, y));
/* 102 */                   AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
/*     */                 } 
/* 104 */                 upgradedCards.add(c.cardID);
/* 105 */                 c.upgrade();
/* 106 */                 AbstractDungeon.player.bottledCardUpgradeCheck(c);
/*     */               } 
/*     */             } 
/* 109 */             AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, 
/*     */ 
/*     */                 
/* 112 */                 RelicLibrary.getRelic("Mark of the Bloom").makeCopy());
/* 113 */             obtainedRelic.add("Mark of the Bloom");
/* 114 */             logMetric("MindBloom", "Upgrade", null, null, null, upgradedCards, obtainedRelic, null, null, 0, 0, 0, 0, 0, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 130 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*     */             break;
/*     */           case 2:
/* 133 */             if (AbstractDungeon.floorNum % 50 <= 40) {
/* 134 */               this.imageEventText.updateBodyText(DIALOG_2);
/* 135 */               this.screen = CurScreen.LEAVE;
/* 136 */               List<String> cardsAdded = new ArrayList<>();
/* 137 */               cardsAdded.add("Normality");
/* 138 */               cardsAdded.add("Normality");
/* 139 */               logMetric("MindBloom", "Gold", cardsAdded, null, null, null, null, null, null, 0, 0, 0, 0, 999, 0);
/* 140 */               AbstractDungeon.effectList.add(new RainingGoldEffect(999));
/* 141 */               AbstractDungeon.player.gainGold(999);
/* 142 */               AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)new Normality(), Settings.WIDTH * 0.6F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 147 */               AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)new Normality(), Settings.WIDTH * 0.3F, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 152 */               this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*     */               break;
/*     */             } 
/* 155 */             this.imageEventText.updateBodyText(DIALOG_2);
/* 156 */             this.screen = CurScreen.LEAVE;
/* 157 */             doubt = new Doubt();
/* 158 */             logMetricObtainCardAndHeal("MindBloom", "Heal", (AbstractCard)doubt, AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 163 */             AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
/* 164 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)doubt, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */             
/* 166 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 173 */         this.imageEventText.clearRemainingOptions();
/*     */         return;
/*     */       case LEAVE:
/* 176 */         openMap();
/*     */         return;
/*     */     } 
/* 179 */     openMap();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\beyond\MindBloom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */