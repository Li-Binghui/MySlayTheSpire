/*     */ package com.megacrit.cardcrawl.events.city;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BloodVial;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.List;
/*     */ 
/*     */ public class Vampires
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "Vampires";
/*  21 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Vampires");
/*  22 */   public static final String NAME = eventStrings.NAME;
/*  23 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  24 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*  25 */   private static final String ACCEPT_BODY = DESCRIPTIONS[2];
/*  26 */   private static final String EXIT_BODY = DESCRIPTIONS[3];
/*  27 */   private static final String GIVE_VIAL = DESCRIPTIONS[4];
/*     */   private static final float HP_DRAIN = 0.3F;
/*     */   private int maxHpLoss;
/*  30 */   private int screenNum = 0;
/*     */   private boolean hasVial;
/*     */   private List<String> bites;
/*     */   
/*     */   public Vampires() {
/*  35 */     super(NAME, "test", "images/events/vampires.jpg");
/*     */     
/*  37 */     this.body = AbstractDungeon.player.getVampireText();
/*     */     
/*  39 */     this.maxHpLoss = MathUtils.ceil(AbstractDungeon.player.maxHealth * 0.3F);
/*  40 */     if (this.maxHpLoss >= AbstractDungeon.player.maxHealth) {
/*  41 */       this.maxHpLoss = AbstractDungeon.player.maxHealth - 1;
/*     */     }
/*  43 */     this.bites = new ArrayList<>();
/*  44 */     this.hasVial = AbstractDungeon.player.hasRelic("Blood Vial");
/*     */     
/*  46 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.maxHpLoss + OPTIONS[1], (AbstractCard)new Bite());
/*  47 */     if (this.hasVial) {
/*  48 */       String vialName = (new BloodVial()).name;
/*  49 */       this.imageEventText.setDialogOption(OPTIONS[3] + vialName + OPTIONS[4], (AbstractCard)new Bite());
/*     */     } 
/*     */     
/*  52 */     this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  57 */     switch (this.screenNum) {
/*     */       
/*     */       case 0:
/*  60 */         switch (buttonPressed) {
/*     */ 
/*     */           
/*     */           case 0:
/*  64 */             CardCrawlGame.sound.play("EVENT_VAMP_BITE");
/*  65 */             this.imageEventText.updateBodyText(ACCEPT_BODY);
/*     */ 
/*     */             
/*  68 */             AbstractDungeon.player.decreaseMaxHealth(this.maxHpLoss);
/*     */             
/*  70 */             replaceAttacks();
/*  71 */             logMetricObtainCardsLoseMapHP("Vampires", "Became a vampire", this.bites, this.maxHpLoss);
/*     */             
/*  73 */             this.screenNum = 1;
/*  74 */             this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/*  75 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */ 
/*     */           
/*     */           case 1:
/*  80 */             if (this.hasVial) {
/*  81 */               CardCrawlGame.sound.play("EVENT_VAMP_BITE");
/*  82 */               this.imageEventText.updateBodyText(GIVE_VIAL);
/*  83 */               AbstractDungeon.player.loseRelic("Blood Vial");
/*  84 */               replaceAttacks();
/*  85 */               logMetricObtainCardsLoseRelic("Vampires", "Became a vampire (Vial)", this.bites, (AbstractRelic)new BloodVial());
/*  86 */               this.screenNum = 1;
/*  87 */               this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/*  88 */               this.imageEventText.clearRemainingOptions();
/*     */               return;
/*     */             } 
/*     */             break;
/*     */         } 
/*     */         
/*  94 */         logMetricIgnored("Vampires");
/*  95 */         this.imageEventText.updateBodyText(EXIT_BODY);
/*  96 */         this.screenNum = 2;
/*  97 */         this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/*  98 */         this.imageEventText.clearRemainingOptions();
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 104 */         openMap();
/*     */         return;
/*     */     } 
/*     */     
/* 108 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void replaceAttacks() {
/* 114 */     ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;
/*     */     int i;
/* 116 */     for (i = masterDeck.size() - 1; i >= 0; i--) {
/* 117 */       AbstractCard card = masterDeck.get(i);
/* 118 */       if (card.tags.contains(AbstractCard.CardTags.STARTER_STRIKE)) {
/* 119 */         AbstractDungeon.player.masterDeck.removeCard(card);
/*     */       }
/*     */     } 
/*     */     
/* 123 */     for (i = 0; i < 5; i++) {
/* 124 */       Bite bite = new Bite();
/* 125 */       AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)bite, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/* 126 */       this.bites.add(((AbstractCard)bite).cardID);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\city\Vampires.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */