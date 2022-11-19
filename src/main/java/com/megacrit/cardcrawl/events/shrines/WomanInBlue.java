/*     */ package com.megacrit.cardcrawl.events.shrines;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ 
/*     */ public class WomanInBlue
/*     */   extends AbstractImageEvent {
/*     */   public static final String ID = "The Woman in Blue";
/*  18 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("The Woman in Blue");
/*  19 */   public static final String NAME = eventStrings.NAME;
/*  20 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  21 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*  23 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*     */   private static final int cost1 = 20;
/*     */   private static final int cost2 = 30;
/*     */   private static final int cost3 = 40;
/*     */   private static final float PUNCH_DMG_PERCENT = 0.05F;
/*  28 */   private CurScreen screen = CurScreen.INTRO;
/*     */   
/*     */   private enum CurScreen {
/*  31 */     INTRO, RESULT;
/*     */   }
/*     */   
/*     */   public WomanInBlue() {
/*  35 */     super(NAME, DIALOG_1, "images/events/ladyInBlue.jpg");
/*  36 */     this.noCardsInRewards = true;
/*  37 */     this.imageEventText.setDialogOption(OPTIONS[0] + '\024' + OPTIONS[3]);
/*  38 */     this.imageEventText.setDialogOption(OPTIONS[1] + '\036' + OPTIONS[3]);
/*  39 */     this.imageEventText.setDialogOption(OPTIONS[2] + '(' + OPTIONS[3]);
/*  40 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  41 */       this.imageEventText.setDialogOption(OPTIONS[5] + 
/*  42 */           MathUtils.ceil(AbstractDungeon.player.maxHealth * 0.05F) + OPTIONS[6]);
/*     */     } else {
/*  44 */       this.imageEventText.setDialogOption(OPTIONS[4]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  50 */     switch (this.screen) {
/*     */       case INTRO:
/*  52 */         switch (buttonPressed) {
/*     */           
/*     */           case 0:
/*  55 */             AbstractDungeon.player.loseGold(20);
/*  56 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*  57 */             logMetric("Bought 1 Potion");
/*  58 */             (AbstractDungeon.getCurrRoom()).rewards.clear();
/*  59 */             (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
/*  60 */             (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*  61 */             AbstractDungeon.combatRewardScreen.open();
/*     */             break;
/*     */           
/*     */           case 1:
/*  65 */             AbstractDungeon.player.loseGold(30);
/*  66 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*  67 */             logMetric("Bought 2 Potions");
/*  68 */             (AbstractDungeon.getCurrRoom()).rewards.clear();
/*  69 */             (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
/*  70 */             (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
/*  71 */             (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*  72 */             AbstractDungeon.combatRewardScreen.open();
/*     */             break;
/*     */           
/*     */           case 2:
/*  76 */             AbstractDungeon.player.loseGold(40);
/*  77 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*  78 */             logMetric("Bought 3 Potions");
/*  79 */             (AbstractDungeon.getCurrRoom()).rewards.clear();
/*  80 */             (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
/*  81 */             (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
/*  82 */             (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
/*  83 */             (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*  84 */             AbstractDungeon.combatRewardScreen.open();
/*     */             break;
/*     */           case 3:
/*  87 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/*  88 */             CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.MED, false);
/*     */ 
/*     */ 
/*     */             
/*  92 */             CardCrawlGame.sound.play("BLUNT_FAST");
/*  93 */             if (AbstractDungeon.ascensionLevel >= 15) {
/*  94 */               AbstractDungeon.player.damage(new DamageInfo(null, 
/*     */ 
/*     */                     
/*  97 */                     MathUtils.ceil(AbstractDungeon.player.maxHealth * 0.05F), DamageInfo.DamageType.HP_LOSS));
/*     */             }
/*     */             
/* 100 */             logMetric("Bought 0 Potions");
/*     */             break;
/*     */         } 
/* 103 */         this.imageEventText.clearAllDialogs();
/* 104 */         this.imageEventText.setDialogOption(OPTIONS[4]);
/* 105 */         this.screen = CurScreen.RESULT;
/*     */         return;
/*     */     } 
/* 108 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void logMetric(String actionTaken) {
/* 114 */     AbstractEvent.logMetric("The Woman in Blue", actionTaken);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\WomanInBlue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */