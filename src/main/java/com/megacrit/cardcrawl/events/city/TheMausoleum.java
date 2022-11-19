/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.curses.Writhe;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ 
/*     */ public class TheMausoleum extends AbstractImageEvent {
/*     */   public static final String ID = "The Mausoleum";
/*  16 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("The Mausoleum");
/*  17 */   public static final String NAME = eventStrings.NAME;
/*  18 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  19 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  21 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  22 */   private static final String CURSED_RESULT = DESCRIPTIONS[1];
/*  23 */   private static final String NORMAL_RESULT = DESCRIPTIONS[2];
/*  24 */   private static final String NOPE_RESULT = DESCRIPTIONS[3];
/*  25 */   private CurScreen screen = CurScreen.INTRO;
/*     */   private static final int PERCENT = 50;
/*     */   private static final int A_2_PERCENT = 100;
/*     */   private int percent;
/*     */   
/*     */   private enum CurScreen {
/*  31 */     INTRO, RESULT;
/*     */   }
/*     */   
/*     */   public TheMausoleum() {
/*  35 */     super(NAME, DIALOG_1, "images/events/mausoleum.jpg");
/*     */     
/*  37 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  38 */       this.percent = 100;
/*     */     } else {
/*  40 */       this.percent = 50;
/*     */     } 
/*     */     
/*  43 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.percent + OPTIONS[1], CardLibrary.getCopy("Writhe"));
/*  44 */     this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  49 */     if (Settings.AMBIANCE_ON) {
/*  50 */       CardCrawlGame.sound.play("EVENT_GHOSTS");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  56 */     super.update();
/*     */   }
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     boolean result;
/*     */     AbstractRelic r;
/*  61 */     switch (this.screen) {
/*     */       case INTRO:
/*  63 */         switch (buttonPressed) {
/*     */           
/*     */           case 0:
/*  66 */             result = AbstractDungeon.miscRng.randomBoolean();
/*  67 */             if (AbstractDungeon.ascensionLevel >= 15) {
/*  68 */               result = true;
/*     */             }
/*     */             
/*  71 */             if (result) {
/*  72 */               this.imageEventText.updateBodyText(CURSED_RESULT);
/*  73 */               AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)new Writhe(), (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*     */             } else {
/*     */               
/*  76 */               this.imageEventText.updateBodyText(NORMAL_RESULT);
/*     */             } 
/*     */             
/*  79 */             CardCrawlGame.sound.play("BLUNT_HEAVY");
/*  80 */             CardCrawlGame.screenShake.rumble(2.0F);
/*  81 */             r = AbstractDungeon.returnRandomScreenlessRelic(
/*  82 */                 AbstractDungeon.returnRandomRelicTier());
/*  83 */             AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), r);
/*     */             
/*  85 */             if (result) {
/*  86 */               logMetricObtainCardAndRelic("The Mausoleum", "Opened", (AbstractCard)new Writhe(), r); break;
/*     */             } 
/*  88 */             logMetricObtainRelic("The Mausoleum", "Opened", r);
/*     */             break;
/*     */           
/*     */           default:
/*  92 */             this.imageEventText.updateBodyText(NOPE_RESULT);
/*  93 */             logMetricIgnored("The Mausoleum");
/*     */             break;
/*     */         } 
/*  96 */         this.imageEventText.clearAllDialogs();
/*  97 */         this.imageEventText.setDialogOption(OPTIONS[2]);
/*  98 */         this.screen = CurScreen.RESULT;
/*     */         return;
/*     */     } 
/* 101 */     openMap();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\TheMausoleum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */