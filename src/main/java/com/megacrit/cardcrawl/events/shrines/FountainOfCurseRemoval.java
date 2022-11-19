/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FountainOfCurseRemoval
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "Fountain of Cleansing";
/*  21 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Fountain of Cleansing");
/*  22 */   public static final String NAME = eventStrings.NAME;
/*  23 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  24 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  26 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  27 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/*  28 */   private static final String DIALOG_3 = DESCRIPTIONS[2];
/*     */   
/*  30 */   private int screenNum = 0;
/*     */   
/*     */   public FountainOfCurseRemoval() {
/*  33 */     super(NAME, DIALOG_1, "images/events/fountain.jpg");
/*     */     
/*  35 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*  36 */     this.imageEventText.setDialogOption(OPTIONS[1]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  42 */     CardCrawlGame.music.playTempBGM("SHRINE");
/*  43 */     if (Settings.AMBIANCE_ON)
/*  44 */       CardCrawlGame.sound.play("EVENT_FOUNTAIN"); 
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     List<String> curses;
/*     */     int i;
/*  50 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  53 */         switch (buttonPressed) {
/*     */           case 0:
/*  55 */             this.imageEventText.updateBodyText(DIALOG_2);
/*  56 */             curses = new ArrayList<>();
/*  57 */             this.screenNum = 1;
/*     */             
/*  59 */             for (i = AbstractDungeon.player.masterDeck.group.size() - 1; i >= 0; i--) {
/*     */               
/*  61 */               if (((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).type == AbstractCard.CardType.CURSE && 
/*  62 */                 !((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).inBottleFlame && 
/*  63 */                 !((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).inBottleLightning && ((AbstractCard)AbstractDungeon.player.masterDeck.group
/*  64 */                 .get(i)).cardID != "AscendersBane" && ((AbstractCard)AbstractDungeon.player.masterDeck.group
/*  65 */                 .get(i)).cardID != "CurseOfTheBell" && ((AbstractCard)AbstractDungeon.player.masterDeck.group
/*  66 */                 .get(i)).cardID != "Necronomicurse") {
/*     */                 
/*  68 */                 AbstractDungeon.effectList.add(new PurgeCardEffect(AbstractDungeon.player.masterDeck.group
/*  69 */                       .get(i)));
/*  70 */                 curses.add(((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).cardID);
/*  71 */                 AbstractDungeon.player.masterDeck.removeCard(AbstractDungeon.player.masterDeck.group
/*  72 */                     .get(i));
/*     */               } 
/*     */             } 
/*  75 */             logMetricRemoveCards("Fountain of Cleansing", "Removed Curses", curses);
/*  76 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/*  77 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */         } 
/*  80 */         logMetricIgnored("Fountain of Cleansing");
/*  81 */         this.imageEventText.updateBodyText(DIALOG_3);
/*  82 */         this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/*  83 */         this.imageEventText.clearRemainingOptions();
/*  84 */         this.screenNum = 1;
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/*  90 */         openMap();
/*     */         return;
/*     */     } 
/*     */     
/*  94 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void logMetric(String cardGiven) {
/* 100 */     AbstractEvent.logMetric("Fountain of Cleansing", cardGiven);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\FountainOfCurseRemoval.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */