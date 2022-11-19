/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CursedTome
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "Cursed Tome";
/*  22 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Cursed Tome");
/*  23 */   public static final String NAME = eventStrings.NAME;
/*  24 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  25 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */ 
/*     */   
/*  28 */   private static final String INTRO_MSG = DESCRIPTIONS[0];
/*  29 */   private static final String READ_1 = DESCRIPTIONS[1];
/*  30 */   private static final String READ_2 = DESCRIPTIONS[2];
/*  31 */   private static final String READ_3 = DESCRIPTIONS[3];
/*  32 */   private static final String READ_4 = DESCRIPTIONS[4];
/*  33 */   private static final String OBTAIN_MSG = DESCRIPTIONS[5];
/*  34 */   private static final String IGNORE_MSG = DESCRIPTIONS[6];
/*  35 */   private static final String STOP_MSG = DESCRIPTIONS[7];
/*     */ 
/*     */   
/*  38 */   private static final String OPT_READ = OPTIONS[0];
/*  39 */   private static final String OPT_CONTINUE_1 = OPTIONS[1];
/*  40 */   private static final String OPT_CONTINUE_2 = OPTIONS[2];
/*  41 */   private static final String OPT_CONTINUE_3 = OPTIONS[3];
/*  42 */   private static final String OPT_STOP = OPTIONS[4];
/*  43 */   private static final String OPT_LEAVE = OPTIONS[7];
/*     */   
/*     */   private static final int DMG_BOOK_OPEN = 1;
/*     */   
/*     */   private static final int DMG_SECOND_PAGE = 2;
/*     */   
/*     */   private static final int DMG_THIRD_PAGE = 3;
/*     */   private static final int DMG_STOP_READING = 3;
/*     */   private static final int DMG_OBTAIN_BOOK = 10;
/*     */   private static final int A_2_DMG_OBTAIN_BOOK = 15;
/*     */   private int finalDmg;
/*     */   private int damageTaken;
/*  55 */   private CurScreen screen = CurScreen.INTRO;
/*     */   
/*     */   private enum CurScreen {
/*  58 */     INTRO, PAGE_1, PAGE_2, PAGE_3, LAST_PAGE, END;
/*     */   }
/*     */   
/*     */   public CursedTome() {
/*  62 */     super(NAME, INTRO_MSG, "images/events/cursedTome.jpg");
/*  63 */     this.noCardsInRewards = true;
/*  64 */     this.damageTaken = 0;
/*  65 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  66 */       this.finalDmg = 15;
/*     */     } else {
/*  68 */       this.finalDmg = 10;
/*     */     } 
/*     */     
/*  71 */     this.imageEventText.setDialogOption(OPT_READ);
/*  72 */     this.imageEventText.setDialogOption(OPT_LEAVE);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  77 */     switch (this.screen) {
/*     */       case INTRO:
/*  79 */         this.imageEventText.clearAllDialogs();
/*  80 */         if (buttonPressed == 0) {
/*  81 */           CardCrawlGame.sound.play("EVENT_TOME");
/*  82 */           this.imageEventText.clearAllDialogs();
/*  83 */           this.imageEventText.setDialogOption(OPT_CONTINUE_1);
/*  84 */           this.imageEventText.updateBodyText(READ_1);
/*  85 */           this.screen = CurScreen.PAGE_1; break;
/*     */         } 
/*  87 */         this.imageEventText.clearAllDialogs();
/*  88 */         this.imageEventText.setDialogOption(OPT_LEAVE);
/*  89 */         this.imageEventText.updateBodyText(IGNORE_MSG);
/*  90 */         this.screen = CurScreen.END;
/*  91 */         logMetricIgnored("Cursed Tome");
/*     */         break;
/*     */       
/*     */       case PAGE_1:
/*  95 */         CardCrawlGame.sound.play("EVENT_TOME");
/*  96 */         AbstractDungeon.player.damage(new DamageInfo(null, 1, DamageInfo.DamageType.HP_LOSS));
/*  97 */         this.damageTaken++;
/*  98 */         this.imageEventText.clearAllDialogs();
/*  99 */         this.imageEventText.setDialogOption(OPT_CONTINUE_2);
/* 100 */         this.imageEventText.updateBodyText(READ_2);
/* 101 */         this.screen = CurScreen.PAGE_2;
/*     */         break;
/*     */       case PAGE_2:
/* 104 */         CardCrawlGame.sound.play("EVENT_TOME");
/* 105 */         AbstractDungeon.player.damage(new DamageInfo(null, 2, DamageInfo.DamageType.HP_LOSS));
/* 106 */         this.damageTaken += 2;
/* 107 */         this.imageEventText.clearAllDialogs();
/* 108 */         this.imageEventText.setDialogOption(OPT_CONTINUE_3);
/* 109 */         this.imageEventText.updateBodyText(READ_3);
/* 110 */         this.screen = CurScreen.PAGE_3;
/*     */         break;
/*     */       case PAGE_3:
/* 113 */         CardCrawlGame.sound.play("EVENT_TOME");
/* 114 */         AbstractDungeon.player.damage(new DamageInfo(null, 3, DamageInfo.DamageType.HP_LOSS));
/* 115 */         this.damageTaken += 3;
/* 116 */         this.imageEventText.clearAllDialogs();
/* 117 */         this.imageEventText.setDialogOption(OPTIONS[5] + this.finalDmg + OPTIONS[6]);
/* 118 */         this.imageEventText.setDialogOption(OPT_STOP);
/* 119 */         this.imageEventText.updateBodyText(READ_4);
/* 120 */         this.screen = CurScreen.LAST_PAGE;
/*     */         break;
/*     */       case LAST_PAGE:
/* 123 */         if (buttonPressed == 0) {
/* 124 */           AbstractDungeon.player.damage(new DamageInfo(null, this.finalDmg, DamageInfo.DamageType.HP_LOSS));
/* 125 */           this.damageTaken += this.finalDmg;
/* 126 */           this.imageEventText.updateBodyText(OBTAIN_MSG);
/* 127 */           randomBook();
/* 128 */           this.imageEventText.clearAllDialogs();
/* 129 */           this.imageEventText.setDialogOption(OPT_LEAVE); break;
/*     */         } 
/* 131 */         AbstractDungeon.player.damage(new DamageInfo(null, 3, DamageInfo.DamageType.HP_LOSS));
/* 132 */         this.damageTaken += 3;
/* 133 */         this.imageEventText.updateBodyText(STOP_MSG);
/* 134 */         logMetricTakeDamage("Cursed Tome", "Stopped", this.damageTaken);
/* 135 */         this.imageEventText.clearAllDialogs();
/* 136 */         this.imageEventText.setDialogOption(OPT_LEAVE);
/* 137 */         this.screen = CurScreen.END;
/*     */         break;
/*     */       
/*     */       case END:
/* 141 */         this.imageEventText.updateDialogOption(0, OPT_LEAVE);
/* 142 */         this.imageEventText.clearRemainingOptions();
/* 143 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void randomBook() {
/* 152 */     ArrayList<AbstractRelic> possibleBooks = new ArrayList<>();
/*     */     
/* 154 */     if (!AbstractDungeon.player.hasRelic("Necronomicon")) {
/* 155 */       possibleBooks.add(RelicLibrary.getRelic("Necronomicon").makeCopy());
/*     */     }
/*     */     
/* 158 */     if (!AbstractDungeon.player.hasRelic("Enchiridion")) {
/* 159 */       possibleBooks.add(RelicLibrary.getRelic("Enchiridion").makeCopy());
/*     */     }
/*     */     
/* 162 */     if (!AbstractDungeon.player.hasRelic("Nilry's Codex")) {
/* 163 */       possibleBooks.add(RelicLibrary.getRelic("Nilry's Codex").makeCopy());
/*     */     }
/*     */     
/* 166 */     if (possibleBooks.size() == 0) {
/* 167 */       possibleBooks.add(RelicLibrary.getRelic("Circlet").makeCopy());
/*     */     }
/*     */     
/* 170 */     AbstractRelic r = possibleBooks.get(AbstractDungeon.miscRng.random(possibleBooks.size() - 1));
/* 171 */     logMetricTakeDamage("Cursed Tome", "Obtained Book", this.damageTaken);
/*     */     
/* 173 */     (AbstractDungeon.getCurrRoom()).rewards.clear();
/* 174 */     AbstractDungeon.getCurrRoom().addRelicToRewards(r);
/* 175 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 176 */     AbstractDungeon.combatRewardScreen.open();
/* 177 */     this.screen = CurScreen.END;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\CursedTome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */