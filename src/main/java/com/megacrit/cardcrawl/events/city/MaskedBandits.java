/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.RoomEventDialog;
/*     */ import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.relics.Circlet;
/*     */ import com.megacrit.cardcrawl.relics.RedMask;
/*     */ import com.megacrit.cardcrawl.vfx.GainPennyEffect;
/*     */ 
/*     */ public class MaskedBandits extends AbstractEvent {
/*  17 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Masked Bandits"); public static final String ID = "Masked Bandits";
/*  18 */   public static final String NAME = eventStrings.NAME;
/*  19 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  20 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  22 */   private static final String PAID_MSG_1 = DESCRIPTIONS[0];
/*  23 */   private static final String PAID_MSG_2 = DESCRIPTIONS[1];
/*  24 */   private static final String PAID_MSG_3 = DESCRIPTIONS[2];
/*  25 */   private static final String PAID_MSG_4 = DESCRIPTIONS[3];
/*     */   
/*  27 */   private CurScreen screen = CurScreen.INTRO;
/*     */   
/*     */   private enum CurScreen {
/*  30 */     INTRO, PAID_1, PAID_2, PAID_3, END;
/*     */   }
/*     */ 
/*     */   
/*     */   public MaskedBandits() {
/*  35 */     this.body = DESCRIPTIONS[4];
/*     */     
/*  37 */     this.roomEventText.addDialogOption(OPTIONS[0]);
/*  38 */     this.roomEventText.addDialogOption(OPTIONS[1]);
/*     */     
/*  40 */     this.hasDialog = true;
/*  41 */     this.hasFocus = true;
/*  42 */     (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("Masked Bandits");
/*     */   }
/*     */   
/*     */   public void update() {
/*  46 */     super.update();
/*     */     
/*  48 */     if (!RoomEventDialog.waitForInput) {
/*  49 */       buttonEffect(this.roomEventText.getSelectedOption());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  55 */     switch (this.screen) {
/*     */       case INTRO:
/*  57 */         switch (buttonPressed) {
/*     */           case 0:
/*  59 */             stealGold();
/*  60 */             AbstractDungeon.player.loseGold(AbstractDungeon.player.gold);
/*  61 */             this.roomEventText.updateBodyText(PAID_MSG_1);
/*  62 */             this.roomEventText.updateDialogOption(0, OPTIONS[2]);
/*  63 */             this.roomEventText.clearRemainingOptions();
/*  64 */             this.screen = CurScreen.PAID_1;
/*     */             return;
/*     */           
/*     */           case 1:
/*  68 */             logMetric("Masked Bandits", "Fought Bandits");
/*     */             
/*  70 */             if (Settings.isDailyRun) {
/*  71 */               AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(30));
/*     */             } else {
/*  73 */               AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(25, 35));
/*     */             } 
/*  75 */             if (AbstractDungeon.player.hasRelic("Red Mask")) {
/*  76 */               AbstractDungeon.getCurrRoom().addRelicToRewards((AbstractRelic)new Circlet());
/*     */             } else {
/*  78 */               AbstractDungeon.getCurrRoom().addRelicToRewards((AbstractRelic)new RedMask());
/*     */             } 
/*     */             
/*  81 */             enterCombat();
/*  82 */             AbstractDungeon.lastCombatMetricKey = "Masked Bandits";
/*     */             return;
/*     */         } 
/*     */         break;
/*     */       case PAID_1:
/*  87 */         this.roomEventText.updateBodyText(PAID_MSG_2);
/*  88 */         this.screen = CurScreen.PAID_2;
/*  89 */         this.roomEventText.updateDialogOption(0, OPTIONS[2]);
/*     */         break;
/*     */       case PAID_2:
/*  92 */         this.roomEventText.updateBodyText(PAID_MSG_3);
/*  93 */         this.screen = CurScreen.PAID_3;
/*  94 */         this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/*     */         break;
/*     */       case PAID_3:
/*  97 */         this.roomEventText.updateBodyText(PAID_MSG_4);
/*  98 */         this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/*  99 */         this.screen = CurScreen.END;
/* 100 */         openMap();
/*     */         break;
/*     */       case END:
/* 103 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void stealGold() {
/* 109 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 110 */     if (((AbstractCreature)abstractPlayer).gold == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 114 */     logMetricLoseGold("Masked Bandits", "Paid Fearfully", ((AbstractCreature)abstractPlayer).gold);
/* 115 */     CardCrawlGame.sound.play("GOLD_JINGLE");
/*     */     
/* 117 */     for (int i = 0; i < ((AbstractCreature)abstractPlayer).gold; i++) {
/* 118 */       AbstractMonster abstractMonster = (AbstractDungeon.getCurrRoom()).monsters.getRandomMonster();
/* 119 */       AbstractDungeon.effectList.add(new GainPennyEffect((AbstractCreature)abstractMonster, ((AbstractCreature)abstractPlayer).hb.cX, ((AbstractCreature)abstractPlayer).hb.cY, ((AbstractCreature)abstractMonster).hb.cX, ((AbstractCreature)abstractMonster).hb.cY, false));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\MaskedBandits.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */