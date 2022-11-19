/*     */ package com.megacrit.cardcrawl.events.beyond;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class SensoryStone
/*     */   extends AbstractImageEvent {
/*     */   public static final String ID = "SensoryStone";
/*  19 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("SensoryStone");
/*  20 */   public static final String NAME = eventStrings.NAME;
/*  21 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  22 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  24 */   private static final String INTRO_TEXT = DESCRIPTIONS[0];
/*  25 */   private static final String INTRO_TEXT_2 = DESCRIPTIONS[1];
/*  26 */   private static final String MEMORY_1_TEXT = DESCRIPTIONS[2];
/*  27 */   private static final String MEMORY_2_TEXT = DESCRIPTIONS[3];
/*  28 */   private static final String MEMORY_3_TEXT = DESCRIPTIONS[4];
/*  29 */   private static final String MEMORY_4_TEXT = DESCRIPTIONS[5];
/*     */   
/*  31 */   private CurScreen screen = CurScreen.INTRO;
/*     */   private int choice;
/*     */   
/*     */   private enum CurScreen {
/*  35 */     INTRO, INTRO_2, ACCEPT, LEAVE;
/*     */   }
/*     */   
/*     */   public SensoryStone() {
/*  39 */     super(NAME, INTRO_TEXT, "images/events/sensoryStone.jpg");
/*  40 */     this.noCardsInRewards = true;
/*  41 */     this.imageEventText.setDialogOption(OPTIONS[5]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  46 */     if (Settings.AMBIANCE_ON) {
/*  47 */       CardCrawlGame.sound.play("EVENT_SENSORY");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  53 */     switch (this.screen) {
/*     */       case INTRO:
/*  55 */         this.imageEventText.updateBodyText(INTRO_TEXT_2);
/*  56 */         this.imageEventText.updateDialogOption(0, OPTIONS[0]);
/*  57 */         this.imageEventText.setDialogOption(OPTIONS[1] + '\005' + OPTIONS[3]);
/*  58 */         this.imageEventText.setDialogOption(OPTIONS[2] + '\n' + OPTIONS[3]);
/*  59 */         this.screen = CurScreen.INTRO_2;
/*     */         return;
/*     */       case INTRO_2:
/*  62 */         getRandomMemory();
/*  63 */         switch (buttonPressed) {
/*     */           case 0:
/*  65 */             this.screen = CurScreen.ACCEPT;
/*  66 */             logMetric("SensoryStone", "Memory 1");
/*  67 */             this.choice = 1;
/*  68 */             reward(this.choice);
/*  69 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*     */             break;
/*     */           case 1:
/*  72 */             this.screen = CurScreen.ACCEPT;
/*  73 */             logMetricTakeDamage("SensoryStone", "Memory 2", 5);
/*  74 */             this.choice = 2;
/*  75 */             reward(this.choice);
/*  76 */             AbstractDungeon.player.damage(new DamageInfo(null, 5, DamageInfo.DamageType.HP_LOSS));
/*  77 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*     */             break;
/*     */           case 2:
/*  80 */             this.screen = CurScreen.ACCEPT;
/*  81 */             logMetricTakeDamage("SensoryStone", "Memory 3", 10);
/*  82 */             this.choice = 3;
/*  83 */             reward(this.choice);
/*  84 */             AbstractDungeon.player.damage(new DamageInfo(null, 10, DamageInfo.DamageType.HP_LOSS));
/*  85 */             this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*     */             break;
/*     */         } 
/*     */         
/*  89 */         this.imageEventText.clearRemainingOptions();
/*     */         return;
/*     */       case ACCEPT:
/*  92 */         reward(this.choice); break;
/*     */     } 
/*  94 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void getRandomMemory() {
/* 103 */     ArrayList<String> memories = new ArrayList<>();
/* 104 */     memories.add(MEMORY_1_TEXT);
/* 105 */     memories.add(MEMORY_2_TEXT);
/* 106 */     memories.add(MEMORY_3_TEXT);
/* 107 */     memories.add(MEMORY_4_TEXT);
/* 108 */     Collections.shuffle(memories, new Random(AbstractDungeon.miscRng.randomLong()));
/* 109 */     this.imageEventText.updateBodyText(memories.get(0));
/*     */   }
/*     */   
/*     */   private void reward(int num) {
/* 113 */     (AbstractDungeon.getCurrRoom()).rewards.clear();
/* 114 */     for (int i = 0; i < num; i++) {
/* 115 */       AbstractDungeon.getCurrRoom().addCardReward(new RewardItem(AbstractCard.CardColor.COLORLESS));
/*     */     }
/* 117 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 118 */     AbstractDungeon.combatRewardScreen.open();
/* 119 */     this.screen = CurScreen.LEAVE;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\beyond\SensoryStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */