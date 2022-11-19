/*    */ package com.megacrit.cardcrawl.events.exordium;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class GoopPuddle extends AbstractImageEvent {
/*    */   public static final String ID = "World of Goop";
/* 17 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("World of Goop");
/* 18 */   public static final String NAME = eventStrings.NAME;
/* 19 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 20 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/* 22 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 23 */   private static final String GOLD_DIALOG = DESCRIPTIONS[1];
/* 24 */   private static final String LEAVE_DIALOG = DESCRIPTIONS[2];
/*    */   
/* 26 */   private CurScreen screen = CurScreen.INTRO;
/* 27 */   private int damage = 11;
/* 28 */   private int gold = 75;
/*    */   private int goldLoss;
/*    */   
/*    */   private enum CurScreen {
/* 32 */     INTRO, RESULT;
/*    */   }
/*    */   
/*    */   public GoopPuddle() {
/* 36 */     super(NAME, DIALOG_1, "images/events/goopPuddle.jpg");
/*    */     
/* 38 */     if (AbstractDungeon.ascensionLevel >= 15) {
/* 39 */       this.goldLoss = AbstractDungeon.miscRng.random(35, 75);
/*    */     } else {
/* 41 */       this.goldLoss = AbstractDungeon.miscRng.random(20, 50);
/*    */     } 
/*    */     
/* 44 */     if (this.goldLoss > AbstractDungeon.player.gold) {
/* 45 */       this.goldLoss = AbstractDungeon.player.gold;
/*    */     }
/* 47 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.gold + OPTIONS[1] + this.damage + OPTIONS[2]);
/* 48 */     this.imageEventText.setDialogOption(OPTIONS[3] + this.goldLoss + OPTIONS[4]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom() {
/* 53 */     if (Settings.AMBIANCE_ON) {
/* 54 */       CardCrawlGame.sound.play("EVENT_SPIRITS");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 60 */     switch (this.screen) {
/*    */       case INTRO:
/* 62 */         switch (buttonPressed) {
/*    */           case 0:
/* 64 */             this.imageEventText.updateBodyText(GOLD_DIALOG);
/* 65 */             this.imageEventText.clearAllDialogs();
/* 66 */             AbstractDungeon.player.damage(new DamageInfo((AbstractCreature)AbstractDungeon.player, this.damage));
/* 67 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 72 */             AbstractDungeon.effectList.add(new RainingGoldEffect(this.gold));
/* 73 */             AbstractDungeon.player.gainGold(this.gold);
/* 74 */             this.imageEventText.setDialogOption(OPTIONS[5]);
/* 75 */             this.screen = CurScreen.RESULT;
/* 76 */             AbstractEvent.logMetricGainGoldAndDamage("World of Goop", "Gather Gold", this.gold, this.damage);
/*    */             break;
/*    */           case 1:
/* 79 */             this.imageEventText.updateBodyText(LEAVE_DIALOG);
/* 80 */             AbstractDungeon.player.loseGold(this.goldLoss);
/* 81 */             this.imageEventText.clearAllDialogs();
/* 82 */             this.imageEventText.setDialogOption(OPTIONS[5]);
/* 83 */             this.screen = CurScreen.RESULT;
/* 84 */             logMetricLoseGold("World of Goop", "Left Gold", this.goldLoss);
/*    */             break;
/*    */         } 
/*    */         return;
/*    */     } 
/* 89 */     openMap();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\GoopPuddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */