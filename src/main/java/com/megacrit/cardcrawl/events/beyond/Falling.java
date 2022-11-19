/*     */ package com.megacrit.cardcrawl.events.beyond;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.helpers.CardHelper;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
/*     */ 
/*     */ public class Falling
/*     */   extends AbstractImageEvent {
/*     */   public static final String ID = "Falling";
/*  16 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Falling");
/*  17 */   public static final String NAME = eventStrings.NAME;
/*  18 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  19 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  21 */   private static final String DIALOG_1 = DESCRIPTIONS[0]; private boolean attack; private boolean skill;
/*  22 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/*     */   private boolean power;
/*  24 */   private CurScreen screen = CurScreen.INTRO;
/*     */   private AbstractCard attackCard;
/*     */   private AbstractCard skillCard;
/*     */   private AbstractCard powerCard;
/*     */   
/*  29 */   private enum CurScreen { INTRO, CHOICE, RESULT; }
/*     */ 
/*     */   
/*     */   public Falling() {
/*  33 */     super(NAME, DIALOG_1, "images/events/falling.jpg");
/*  34 */     setCards();
/*  35 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  40 */     if (Settings.AMBIANCE_ON) {
/*  41 */       CardCrawlGame.sound.play("EVENT_FALLING");
/*     */     }
/*     */   }
/*     */   
/*     */   private void setCards() {
/*  46 */     this.attack = CardHelper.hasCardWithType(AbstractCard.CardType.ATTACK);
/*  47 */     this.skill = CardHelper.hasCardWithType(AbstractCard.CardType.SKILL);
/*  48 */     this.power = CardHelper.hasCardWithType(AbstractCard.CardType.POWER);
/*     */     
/*  50 */     if (this.attack) {
/*  51 */       this.attackCard = CardHelper.returnCardOfType(AbstractCard.CardType.ATTACK, AbstractDungeon.miscRng);
/*     */     }
/*  53 */     if (this.skill) {
/*  54 */       this.skillCard = CardHelper.returnCardOfType(AbstractCard.CardType.SKILL, AbstractDungeon.miscRng);
/*     */     }
/*  56 */     if (this.power) {
/*  57 */       this.powerCard = CardHelper.returnCardOfType(AbstractCard.CardType.POWER, AbstractDungeon.miscRng);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  63 */     switch (this.screen) {
/*     */       case INTRO:
/*  65 */         this.screen = CurScreen.CHOICE;
/*  66 */         this.imageEventText.updateBodyText(DIALOG_2);
/*     */         
/*  68 */         this.imageEventText.clearAllDialogs();
/*     */         
/*  70 */         if (!this.skill && !this.power && !this.attack) {
/*  71 */           this.imageEventText.setDialogOption(OPTIONS[8]);
/*     */         } else {
/*  73 */           if (this.skill) {
/*  74 */             this.imageEventText.setDialogOption(OPTIONS[1] + 
/*  75 */                 FontHelper.colorString(this.skillCard.name, "r"), this.skillCard
/*  76 */                 .makeStatEquivalentCopy());
/*     */           } else {
/*  78 */             this.imageEventText.setDialogOption(OPTIONS[2], true);
/*     */           } 
/*  80 */           if (this.power) {
/*  81 */             this.imageEventText.setDialogOption(OPTIONS[3] + 
/*  82 */                 FontHelper.colorString(this.powerCard.name, "r"), this.powerCard
/*  83 */                 .makeStatEquivalentCopy());
/*     */           } else {
/*  85 */             this.imageEventText.setDialogOption(OPTIONS[4], true);
/*     */           } 
/*  87 */           if (this.attack) {
/*  88 */             this.imageEventText.setDialogOption(OPTIONS[5] + 
/*  89 */                 FontHelper.colorString(this.attackCard.name, "r"), this.attackCard
/*  90 */                 .makeStatEquivalentCopy());
/*     */           } else {
/*  92 */             this.imageEventText.setDialogOption(OPTIONS[6], true);
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       case CHOICE:
/*  97 */         this.screen = CurScreen.RESULT;
/*  98 */         this.imageEventText.clearAllDialogs();
/*  99 */         this.imageEventText.setDialogOption(OPTIONS[7]);
/* 100 */         switch (buttonPressed) {
/*     */           case 0:
/* 102 */             if (!this.skill && !this.power && !this.attack) {
/* 103 */               this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
/* 104 */               logMetricIgnored("Falling"); break;
/*     */             } 
/* 106 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/* 107 */             AbstractDungeon.effectList.add(new PurgeCardEffect(this.skillCard));
/* 108 */             AbstractDungeon.player.masterDeck.removeCard(this.skillCard);
/* 109 */             logMetricCardRemoval("Falling", "Removed Skill", this.skillCard);
/*     */             break;
/*     */           
/*     */           case 1:
/* 113 */             this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
/* 114 */             AbstractDungeon.effectList.add(new PurgeCardEffect(this.powerCard));
/* 115 */             AbstractDungeon.player.masterDeck.removeCard(this.powerCard);
/* 116 */             logMetricCardRemoval("Falling", "Removed Power", this.powerCard);
/*     */             break;
/*     */           case 2:
/* 119 */             this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
/* 120 */             AbstractDungeon.effectList.add(new PurgeCardEffect(this.attackCard));
/* 121 */             logMetricCardRemoval("Falling", "Removed Attack", this.attackCard);
/* 122 */             AbstractDungeon.player.masterDeck.removeCard(this.attackCard);
/*     */             break;
/*     */         } 
/*     */         
/*     */         return;
/*     */     } 
/* 128 */     openMap();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\beyond\Falling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */