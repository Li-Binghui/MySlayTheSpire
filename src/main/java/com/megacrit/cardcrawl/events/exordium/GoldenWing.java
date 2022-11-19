/*     */ package com.megacrit.cardcrawl.events.exordium;
/*     */ 
/*     */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*     */ 
/*     */ public class GoldenWing
/*     */   extends AbstractImageEvent {
/*     */   public static final String ID = "Golden Wing";
/*  23 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Golden Wing");
/*  24 */   public static final String NAME = eventStrings.NAME;
/*  25 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  26 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  28 */   private int damage = 7;
/*  29 */   private static final String INTRO = DESCRIPTIONS[0];
/*  30 */   private static final String AGREE_DIALOG = DESCRIPTIONS[1];
/*  31 */   private static final String SPECIAL_OPTION = DESCRIPTIONS[2];
/*  32 */   private static final String DISAGREE_DIALOG = DESCRIPTIONS[3];
/*     */   
/*     */   private boolean canAttack;
/*     */   
/*     */   private boolean purgeResult = false;
/*     */   private static final int MIN_GOLD = 50;
/*  38 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO; private static final int MAX_GOLD = 80; private static final int REQUIRED_DAMAGE = 10;
/*     */   private int goldAmount;
/*     */   
/*  41 */   private enum CUR_SCREEN { INTRO, PURGE, MAP; }
/*     */ 
/*     */   
/*     */   public GoldenWing() {
/*  45 */     super(NAME, INTRO, "images/events/goldenWing.jpg");
/*     */     
/*  47 */     this.canAttack = CardHelper.hasCardWithXDamage(10);
/*  48 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.damage + OPTIONS[1]);
/*  49 */     if (this.canAttack) {
/*  50 */       this.imageEventText.setDialogOption(OPTIONS[2] + '2' + OPTIONS[3] + 'P' + OPTIONS[4]);
/*     */     } else {
/*  52 */       this.imageEventText.setDialogOption(OPTIONS[5] + '\n' + OPTIONS[6], !this.canAttack);
/*     */     } 
/*  54 */     this.imageEventText.setDialogOption(OPTIONS[7]);
/*     */   }
/*     */   
/*     */   public void update() {
/*  58 */     super.update();
/*  59 */     purgeLogic();
/*     */     
/*  61 */     if (this.waitForInput) {
/*  62 */       buttonEffect(GenericEventDialog.getSelectedOption());
/*     */     }
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  67 */     switch (this.screen) {
/*     */       case INTRO:
/*  69 */         switch (buttonPressed) {
/*     */           case 0:
/*  71 */             this.imageEventText.updateBodyText(AGREE_DIALOG);
/*  72 */             AbstractDungeon.player.damage(new DamageInfo((AbstractCreature)AbstractDungeon.player, this.damage));
/*  73 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  78 */             this.screen = CUR_SCREEN.PURGE;
/*  79 */             this.imageEventText.updateDialogOption(0, OPTIONS[8]);
/*  80 */             this.imageEventText.removeDialogOption(1);
/*  81 */             this.imageEventText.removeDialogOption(1);
/*     */             return;
/*     */           case 1:
/*  84 */             if (this.canAttack) {
/*  85 */               this.goldAmount = AbstractDungeon.miscRng.random(50, 80);
/*  86 */               AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldAmount));
/*  87 */               AbstractDungeon.player.gainGold(this.goldAmount);
/*  88 */               AbstractEvent.logMetricGainGold("Golden Wing", "Gained Gold", this.goldAmount);
/*  89 */               this.imageEventText.updateBodyText(SPECIAL_OPTION);
/*  90 */               this.screen = CUR_SCREEN.MAP;
/*  91 */               this.imageEventText.updateDialogOption(0, OPTIONS[7]);
/*  92 */               this.imageEventText.removeDialogOption(1);
/*  93 */               this.imageEventText.removeDialogOption(1);
/*     */             } 
/*     */             return;
/*     */         } 
/*  97 */         this.imageEventText.updateBodyText(DISAGREE_DIALOG);
/*  98 */         AbstractEvent.logMetricIgnored("Golden Wing");
/*  99 */         this.screen = CUR_SCREEN.MAP;
/* 100 */         this.imageEventText.updateDialogOption(0, OPTIONS[7]);
/* 101 */         this.imageEventText.removeDialogOption(1);
/* 102 */         this.imageEventText.removeDialogOption(1);
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case PURGE:
/* 108 */         AbstractDungeon.gridSelectScreen.open(
/* 109 */             CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[9], false, false, false, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 116 */         this.imageEventText.updateDialogOption(0, OPTIONS[7]);
/* 117 */         this.purgeResult = true;
/* 118 */         this.screen = CUR_SCREEN.MAP;
/*     */         return;
/*     */       
/*     */       case MAP:
/* 122 */         openMap();
/*     */         return;
/*     */     } 
/*     */     
/* 126 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void purgeLogic() {
/* 132 */     if (this.purgeResult && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 133 */       AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/* 134 */       AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/* 135 */       AbstractEvent.logMetricCardRemovalAndDamage("Golden Wing", "Card Removal", c, this.damage);
/* 136 */       AbstractDungeon.player.masterDeck.removeCard(c);
/* 137 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*     */       
/* 139 */       this.purgeResult = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\GoldenWing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */