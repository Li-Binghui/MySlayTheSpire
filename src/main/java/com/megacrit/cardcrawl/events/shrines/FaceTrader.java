/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.helpers.RelicLibrary;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FaceTrader
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "FaceTrader";
/*  26 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("FaceTrader");
/*  27 */   public static final String NAME = eventStrings.NAME;
/*  28 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  29 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   private static int goldReward;
/*     */   private static int damage;
/*  32 */   private CurScreen screen = CurScreen.INTRO;
/*     */   
/*     */   private enum CurScreen {
/*  35 */     INTRO, MAIN, RESULT;
/*     */   }
/*     */   
/*     */   public FaceTrader() {
/*  39 */     super(NAME, DESCRIPTIONS[0], "images/events/facelessTrader.jpg");
/*     */     
/*  41 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  42 */       goldReward = 50;
/*     */     } else {
/*  44 */       goldReward = 75;
/*     */     } 
/*     */     
/*  47 */     damage = AbstractDungeon.player.maxHealth / 10;
/*  48 */     if (damage == 0) {
/*  49 */       damage = 1;
/*     */     }
/*     */     
/*  52 */     this.imageEventText.setDialogOption(OPTIONS[4]);
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     AbstractRelic r;
/*  57 */     switch (this.screen) {
/*     */       case INTRO:
/*  59 */         switch (buttonPressed) {
/*     */           case 0:
/*  61 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*  62 */             this.imageEventText.updateDialogOption(0, OPTIONS[0] + damage + OPTIONS[5] + goldReward + OPTIONS[1]);
/*     */ 
/*     */             
/*  65 */             this.imageEventText.setDialogOption(OPTIONS[2]);
/*  66 */             this.imageEventText.setDialogOption(OPTIONS[3]);
/*  67 */             this.screen = CurScreen.MAIN;
/*     */             break;
/*     */         } 
/*     */         return;
/*     */       case MAIN:
/*  72 */         switch (buttonPressed) {
/*     */           
/*     */           case 0:
/*  75 */             logMetricGainGoldAndDamage("FaceTrader", "Touch", goldReward, damage);
/*  76 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/*  77 */             AbstractDungeon.effectList.add(new RainingGoldEffect(goldReward));
/*  78 */             AbstractDungeon.player.gainGold(goldReward);
/*     */             
/*  80 */             AbstractDungeon.player.damage(new DamageInfo(null, damage));
/*  81 */             CardCrawlGame.sound.play("ATTACK_POISON");
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/*  86 */             r = getRandomFace();
/*  87 */             logMetricObtainRelic("FaceTrader", "Trade", r);
/*  88 */             AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, r);
/*  89 */             this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 2:
/*  94 */             logMetric("Leave");
/*  95 */             this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 100 */         this.imageEventText.clearAllDialogs();
/* 101 */         this.imageEventText.setDialogOption(OPTIONS[3]);
/* 102 */         this.screen = CurScreen.RESULT;
/*     */         return;
/*     */     } 
/* 105 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private AbstractRelic getRandomFace() {
/* 111 */     ArrayList<String> ids = new ArrayList<>();
/* 112 */     if (!AbstractDungeon.player.hasRelic("CultistMask")) {
/* 113 */       ids.add("CultistMask");
/*     */     }
/* 115 */     if (!AbstractDungeon.player.hasRelic("FaceOfCleric")) {
/* 116 */       ids.add("FaceOfCleric");
/*     */     }
/* 118 */     if (!AbstractDungeon.player.hasRelic("GremlinMask")) {
/* 119 */       ids.add("GremlinMask");
/*     */     }
/* 121 */     if (!AbstractDungeon.player.hasRelic("NlothsMask")) {
/* 122 */       ids.add("NlothsMask");
/*     */     }
/* 124 */     if (!AbstractDungeon.player.hasRelic("SsserpentHead")) {
/* 125 */       ids.add("SsserpentHead");
/*     */     }
/* 127 */     if (ids.size() <= 0) {
/* 128 */       ids.add("Circlet");
/*     */     }
/*     */     
/* 131 */     Collections.shuffle(ids, new Random(AbstractDungeon.miscRng.randomLong()));
/* 132 */     return RelicLibrary.getRelic(ids.get(0)).makeCopy();
/*     */   }
/*     */   
/*     */   public void logMetric(String actionTaken) {
/* 136 */     AbstractEvent.logMetric("FaceTrader", actionTaken);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\FaceTrader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */