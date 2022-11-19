/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.relics.Circlet;
/*     */ import com.megacrit.cardcrawl.relics.NlothsGift;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Nloth
/*     */   extends AbstractImageEvent {
/*     */   public static final String ID = "N'loth";
/*  19 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("N'loth");
/*  20 */   public static final String NAME = eventStrings.NAME;
/*  21 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  22 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  24 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  25 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/*  26 */   private static final String DIALOG_3 = DESCRIPTIONS[2];
/*     */   
/*  28 */   private int screenNum = 0;
/*     */   private AbstractRelic choice1;
/*     */   
/*     */   public Nloth() {
/*  32 */     super(NAME, DIALOG_1, "images/events/nloth.jpg");
/*     */ 
/*     */     
/*  35 */     ArrayList<AbstractRelic> relics = new ArrayList<>();
/*  36 */     relics.addAll(AbstractDungeon.player.relics);
/*  37 */     Collections.shuffle(relics, new Random(AbstractDungeon.miscRng.randomLong()));
/*     */     
/*  39 */     this.choice1 = relics.get(0);
/*  40 */     this.choice2 = relics.get(1);
/*  41 */     this.gift = (AbstractRelic)new NlothsGift();
/*     */     
/*  43 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.choice1.name + OPTIONS[1], (AbstractRelic)new NlothsGift());
/*  44 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.choice2.name + OPTIONS[1], (AbstractRelic)new NlothsGift());
/*  45 */     this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */   }
/*     */   private AbstractRelic choice2; private AbstractRelic gift;
/*     */   
/*     */   public void onEnterRoom() {
/*  50 */     if (Settings.AMBIANCE_ON) {
/*  51 */       CardCrawlGame.sound.play("EVENT_SERPENT");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  57 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  60 */         switch (buttonPressed) {
/*     */           case 0:
/*  62 */             this.imageEventText.updateBodyText(DIALOG_2);
/*  63 */             if (AbstractDungeon.player.hasRelic("Nloth's Gift")) {
/*  64 */               this.gift = (AbstractRelic)new Circlet();
/*  65 */               AbstractEvent.logMetricRelicSwap("N'loth", "Traded Relic", this.gift, this.choice1);
/*  66 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), this.gift);
/*     */             
/*     */             }
/*     */             else {
/*     */               
/*  71 */               AbstractEvent.logMetricRelicSwap("N'loth", "Traded Relic", this.gift, this.choice1);
/*  72 */               AbstractDungeon.player.loseRelic(this.choice1.relicId);
/*  73 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), this.gift);
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  79 */             this.screenNum = 1;
/*  80 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/*  81 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */           case 1:
/*  84 */             this.imageEventText.updateBodyText(DIALOG_2);
/*  85 */             if (AbstractDungeon.player.hasRelic("Nloth's Gift")) {
/*  86 */               this.gift = (AbstractRelic)new Circlet();
/*  87 */               AbstractEvent.logMetricRelicSwap("N'loth", "Traded Relic", this.gift, this.choice2);
/*  88 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), this.gift);
/*     */             
/*     */             }
/*     */             else {
/*     */               
/*  93 */               AbstractEvent.logMetricRelicSwap("N'loth", "Traded Relic", this.gift, this.choice2);
/*  94 */               AbstractDungeon.player.loseRelic(this.choice2.relicId);
/*  95 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), this.gift);
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 100 */             this.screenNum = 1;
/* 101 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/* 102 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */           case 2:
/* 105 */             AbstractEvent.logMetricIgnored("N'loth");
/* 106 */             this.imageEventText.updateBodyText(DIALOG_3);
/* 107 */             this.screenNum = 1;
/* 108 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/* 109 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */         } 
/* 112 */         this.imageEventText.updateBodyText(DIALOG_3);
/* 113 */         this.screenNum = 1;
/* 114 */         this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/* 115 */         this.imageEventText.clearRemainingOptions();
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 121 */         openMap();
/*     */         return;
/*     */     } 
/*     */     
/* 125 */     openMap();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\Nloth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */