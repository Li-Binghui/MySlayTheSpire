/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.curses.Regret;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.events.RoomEventDialog;
/*     */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ 
/*     */ public class GoldShrine extends AbstractImageEvent {
/*     */   public static final String ID = "Golden Shrine";
/*  17 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Golden Shrine");
/*  18 */   public static final String NAME = eventStrings.NAME;
/*  19 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  20 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*     */   private static final int GOLD_AMT = 100;
/*     */   
/*     */   private static final int CURSE_GOLD_AMT = 275;
/*     */   private static final int A_2_GOLD_AMT = 50;
/*     */   private int goldAmt;
/*  27 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  28 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/*  29 */   private static final String DIALOG_3 = DESCRIPTIONS[2];
/*  30 */   private static final String IGNORE = DESCRIPTIONS[3];
/*     */   
/*  32 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*     */   
/*     */   private enum CUR_SCREEN {
/*  35 */     INTRO, COMPLETE;
/*     */   }
/*     */   
/*     */   public GoldShrine() {
/*  39 */     super(NAME, DIALOG_1, "images/events/goldShrine.jpg");
/*     */     
/*  41 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  42 */       this.goldAmt = 50;
/*     */     } else {
/*  44 */       this.goldAmt = 100;
/*     */     } 
/*     */     
/*  47 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.goldAmt + OPTIONS[1]);
/*  48 */     this.imageEventText.setDialogOption(OPTIONS[2], CardLibrary.getCopy("Regret"));
/*  49 */     this.imageEventText.setDialogOption(OPTIONS[3]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  54 */     CardCrawlGame.music.playTempBGM("SHRINE");
/*     */   }
/*     */   
/*     */   public void update() {
/*  58 */     super.update();
/*     */     
/*  60 */     if (!RoomEventDialog.waitForInput) {
/*  61 */       buttonEffect(this.roomEventText.getSelectedOption());
/*     */     }
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     Regret regret;
/*  67 */     switch (this.screen) {
/*     */       
/*     */       case INTRO:
/*  70 */         switch (buttonPressed) {
/*     */           case 0:
/*  72 */             this.screen = CUR_SCREEN.COMPLETE;
/*  73 */             logMetricGainGold("Golden Shrine", "Pray", this.goldAmt);
/*  74 */             this.imageEventText.updateBodyText(DIALOG_2);
/*  75 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  76 */             AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldAmt));
/*  77 */             AbstractDungeon.player.gainGold(this.goldAmt);
/*  78 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           case 1:
/*  81 */             this.screen = CUR_SCREEN.COMPLETE;
/*  82 */             regret = new Regret();
/*  83 */             logMetricGainGoldAndCard("Golden Shrine", "Desecrate", (AbstractCard)regret, 275);
/*  84 */             AbstractDungeon.effectList.add(new RainingGoldEffect(275));
/*  85 */             AbstractDungeon.player.gainGold(275);
/*  86 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)regret, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */             
/*  88 */             this.imageEventText.updateBodyText(DIALOG_3);
/*  89 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  90 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           case 2:
/*  93 */             this.screen = CUR_SCREEN.COMPLETE;
/*  94 */             logMetricIgnored("Golden Shrine");
/*  95 */             this.imageEventText.updateBodyText(IGNORE);
/*  96 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  97 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case COMPLETE:
/* 102 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\GoldShrine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */