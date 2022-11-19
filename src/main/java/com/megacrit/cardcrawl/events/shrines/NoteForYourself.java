/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.cards.red.IronWave;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ 
/*     */ public class NoteForYourself
/*     */   extends AbstractImageEvent {
/*     */   public static final String ID = "NoteForYourself";
/*  17 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("NoteForYourself");
/*  18 */   public static final String NAME = eventStrings.NAME;
/*  19 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  20 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*  21 */   private AbstractCard obtainCard = null;
/*  22 */   public AbstractCard saveCard = null;
/*     */   
/*     */   private boolean cardSelect = false;
/*  25 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  26 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*     */   
/*     */   private enum CUR_SCREEN {
/*  29 */     INTRO, CHOOSE, COMPLETE;
/*     */   }
/*     */   
/*     */   public NoteForYourself() {
/*  33 */     super(NAME, DIALOG_1, "images/events/selfNote.jpg");
/*  34 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*  35 */     initializeObtainCard();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  40 */     switch (this.screen) {
/*     */       
/*     */       case INTRO:
/*  43 */         this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*  44 */         this.screen = CUR_SCREEN.CHOOSE;
/*  45 */         this.imageEventText.updateDialogOption(0, OPTIONS[1] + this.obtainCard.name + OPTIONS[2], this.obtainCard);
/*  46 */         this.imageEventText.setDialogOption(OPTIONS[3]);
/*     */         break;
/*     */       case CHOOSE:
/*  49 */         this.screen = CUR_SCREEN.COMPLETE;
/*  50 */         (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*  51 */         switch (buttonPressed) {
/*     */           
/*     */           case 0:
/*  54 */             for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  55 */               r.onObtainCard(this.obtainCard);
/*     */             }
/*  57 */             AbstractDungeon.player.masterDeck.addToTop(this.obtainCard);
/*     */             
/*  59 */             for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  60 */               r.onMasterDeckChange();
/*     */             }
/*     */             
/*  63 */             this.cardSelect = true;
/*  64 */             AbstractDungeon.gridSelectScreen.open(
/*  65 */                 CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck
/*  66 */                   .getPurgeableCards()), 1, DESCRIPTIONS[2], false, false, false, false);
/*     */             break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           default:
/*  75 */             logMetricIgnored("NoteForYourself");
/*     */             break;
/*     */         } 
/*     */         
/*  79 */         this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
/*  80 */         this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/*  81 */         this.imageEventText.clearRemainingOptions();
/*  82 */         this.screen = CUR_SCREEN.COMPLETE;
/*  83 */         (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*     */         break;
/*     */       case COMPLETE:
/*  86 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  93 */     super.update();
/*     */     
/*  95 */     if (this.cardSelect && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/*  96 */       AbstractCard storeCard = AbstractDungeon.gridSelectScreen.selectedCards.remove(0);
/*  97 */       logMetricObtainCardAndLoseCard("NoteForYourself", "Took Card", this.obtainCard, storeCard);
/*  98 */       AbstractDungeon.player.masterDeck.removeCard(storeCard);
/*  99 */       this.saveCard = storeCard;
/* 100 */       this.cardSelect = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initializeObtainCard() {
/* 105 */     this.obtainCard = CardLibrary.getCard(CardCrawlGame.playerPref.getString("NOTE_CARD", "Iron Wave"));
/* 106 */     if (this.obtainCard == null) {
/* 107 */       this.obtainCard = (AbstractCard)new IronWave();
/*     */     }
/* 109 */     this.obtainCard = this.obtainCard.makeCopy();
/* 110 */     for (int i = 0; i < CardCrawlGame.playerPref.getInteger("NOTE_UPGRADE", 0); i++)
/* 111 */       this.obtainCard.upgrade(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\NoteForYourself.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */