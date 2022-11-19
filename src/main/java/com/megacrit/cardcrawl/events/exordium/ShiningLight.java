/*     */ package com.megacrit.cardcrawl.events.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/*     */ 
/*     */ public class ShiningLight extends AbstractImageEvent {
/*     */   public static final String ID = "Shining Light";
/*  24 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Shining Light");
/*  25 */   public static final String NAME = eventStrings.NAME;
/*  26 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  27 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  29 */   private static final String INTRO = DESCRIPTIONS[0];
/*  30 */   private static final String AGREE_DIALOG = DESCRIPTIONS[1];
/*  31 */   private static final String DISAGREE_DIALOG = DESCRIPTIONS[2];
/*     */   
/*  33 */   private int damage = 0;
/*     */   
/*     */   private static final float HP_LOSS_PERCENT = 0.2F;
/*     */   private static final float A_2_HP_LOSS_PERCENT = 0.3F;
/*  37 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*     */   
/*     */   private enum CUR_SCREEN {
/*  40 */     INTRO, COMPLETE;
/*     */   }
/*     */   
/*     */   public ShiningLight() {
/*  44 */     super(NAME, INTRO, "images/events/shiningLight.jpg");
/*     */     
/*  46 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  47 */       this.damage = MathUtils.round(AbstractDungeon.player.maxHealth * 0.3F);
/*     */     } else {
/*  49 */       this.damage = MathUtils.round(AbstractDungeon.player.maxHealth * 0.2F);
/*     */     } 
/*     */     
/*  52 */     if (AbstractDungeon.player.masterDeck.hasUpgradableCards().booleanValue()) {
/*  53 */       this.imageEventText.setDialogOption(OPTIONS[0] + this.damage + OPTIONS[1]);
/*     */     } else {
/*  55 */       this.imageEventText.setDialogOption(OPTIONS[3], true);
/*     */     } 
/*     */     
/*  58 */     this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnterRoom() {
/*  63 */     if (Settings.AMBIANCE_ON) {
/*  64 */       CardCrawlGame.sound.play("EVENT_SHINING");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  70 */     switch (this.screen) {
/*     */       case INTRO:
/*  72 */         if (buttonPressed == 0) {
/*  73 */           this.imageEventText.updateBodyText(AGREE_DIALOG);
/*  74 */           this.imageEventText.removeDialogOption(1);
/*  75 */           this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/*  76 */           this.screen = CUR_SCREEN.COMPLETE;
/*  77 */           AbstractDungeon.player.damage(new DamageInfo((AbstractCreature)AbstractDungeon.player, this.damage));
/*  78 */           AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  83 */           upgradeCards();
/*     */         } else {
/*  85 */           this.imageEventText.updateBodyText(DISAGREE_DIALOG);
/*  86 */           this.imageEventText.removeDialogOption(1);
/*  87 */           this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/*  88 */           this.screen = CUR_SCREEN.COMPLETE;
/*  89 */           AbstractEvent.logMetricIgnored("Shining Light");
/*     */         } 
/*     */         return;
/*     */     } 
/*  93 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void upgradeCards() {
/*  99 */     AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/* 100 */     ArrayList<AbstractCard> upgradableCards = new ArrayList<>();
/* 101 */     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
/* 102 */       if (c.canUpgrade()) {
/* 103 */         upgradableCards.add(c);
/*     */       }
/*     */     } 
/*     */     
/* 107 */     List<String> cardMetrics = new ArrayList<>();
/*     */     
/* 109 */     Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
/*     */     
/* 111 */     if (!upgradableCards.isEmpty())
/*     */     {
/* 113 */       if (upgradableCards.size() == 1) {
/* 114 */         ((AbstractCard)upgradableCards.get(0)).upgrade();
/* 115 */         cardMetrics.add(((AbstractCard)upgradableCards.get(0)).cardID);
/* 116 */         AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
/* 117 */         AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard)upgradableCards.get(0)).makeStatEquivalentCopy()));
/*     */       } else {
/* 119 */         ((AbstractCard)upgradableCards.get(0)).upgrade();
/* 120 */         ((AbstractCard)upgradableCards.get(1)).upgrade();
/* 121 */         cardMetrics.add(((AbstractCard)upgradableCards.get(0)).cardID);
/* 122 */         cardMetrics.add(((AbstractCard)upgradableCards.get(1)).cardID);
/* 123 */         AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
/* 124 */         AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(1));
/* 125 */         AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard)upgradableCards
/*     */               
/* 127 */               .get(0)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - 190.0F * Settings.scale, Settings.HEIGHT / 2.0F));
/*     */ 
/*     */         
/* 130 */         AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(((AbstractCard)upgradableCards
/*     */               
/* 132 */               .get(1)).makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + 190.0F * Settings.scale, Settings.HEIGHT / 2.0F));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 137 */     AbstractEvent.logMetric("Shining Light", "Entered Light", null, null, null, cardMetrics, null, null, null, this.damage, 0, 0, 0, 0, 0);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\ShiningLight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */