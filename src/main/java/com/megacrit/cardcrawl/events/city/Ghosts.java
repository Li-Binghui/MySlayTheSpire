/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.colorless.Apparition;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Ghosts
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "Ghosts";
/*  19 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Ghosts");
/*  20 */   public static final String NAME = eventStrings.NAME;
/*  21 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  22 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*  23 */   private static final String INTRO_BODY_M = DESCRIPTIONS[0];
/*  24 */   private static final String ACCEPT_BODY = DESCRIPTIONS[2];
/*  25 */   private static final String EXIT_BODY = DESCRIPTIONS[3];
/*     */   private static final float HP_DRAIN = 0.5F;
/*  27 */   private int screenNum = 0, hpLoss = 0;
/*     */   
/*     */   public Ghosts() {
/*  30 */     super(NAME, "test", "images/events/ghost.jpg");
/*  31 */     this.body = INTRO_BODY_M;
/*     */     
/*  33 */     this.hpLoss = MathUtils.ceil(AbstractDungeon.player.maxHealth * 0.5F);
/*  34 */     if (this.hpLoss >= AbstractDungeon.player.maxHealth) {
/*  35 */       this.hpLoss = AbstractDungeon.player.maxHealth - 1;
/*     */     }
/*     */     
/*  38 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  39 */       this.imageEventText.setDialogOption(OPTIONS[3] + this.hpLoss + OPTIONS[1], (AbstractCard)new Apparition());
/*     */     } else {
/*  41 */       this.imageEventText.setDialogOption(OPTIONS[0] + this.hpLoss + OPTIONS[1], (AbstractCard)new Apparition());
/*     */     } 
/*     */     
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
/*     */   protected void buttonEffect(int buttonPressed) {
/*  56 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  59 */         switch (buttonPressed) {
/*     */ 
/*     */           
/*     */           case 0:
/*  63 */             this.imageEventText.updateBodyText(ACCEPT_BODY);
/*     */ 
/*     */             
/*  66 */             AbstractDungeon.player.decreaseMaxHealth(this.hpLoss);
/*     */             
/*  68 */             becomeGhost();
/*     */ 
/*     */             
/*  71 */             this.screenNum = 1;
/*  72 */             this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/*  73 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */         } 
/*     */         
/*  77 */         logMetricIgnored("Ghosts");
/*  78 */         this.imageEventText.updateBodyText(EXIT_BODY);
/*  79 */         this.screenNum = 2;
/*  80 */         this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/*  81 */         this.imageEventText.clearRemainingOptions();
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/*  87 */         openMap();
/*     */         return;
/*     */     } 
/*     */     
/*  91 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void becomeGhost() {
/*  97 */     List<String> cards = new ArrayList<>();
/*  98 */     int amount = 5;
/*  99 */     if (AbstractDungeon.ascensionLevel >= 15) {
/* 100 */       amount -= 2;
/*     */     }
/* 102 */     for (int i = 0; i < amount; i++) {
/* 103 */       Apparition apparition = new Apparition();
/* 104 */       cards.add(((AbstractCard)apparition).cardID);
/* 105 */       AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)apparition, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */     } 
/*     */ 
/*     */     
/* 109 */     logMetricObtainCardsLoseMapHP("Ghosts", "Became a Ghost", cards, this.hpLoss);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\Ghosts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */