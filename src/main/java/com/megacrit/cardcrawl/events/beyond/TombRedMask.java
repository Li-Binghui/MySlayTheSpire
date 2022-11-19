/*    */ package com.megacrit.cardcrawl.events.beyond;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.RedMask;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
/*    */ 
/*    */ public class TombRedMask
/*    */   extends AbstractImageEvent {
/*    */   public static final String ID = "Tomb of Lord Red Mask";
/* 15 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Tomb of Lord Red Mask");
/* 16 */   public static final String NAME = eventStrings.NAME;
/* 17 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 18 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/*    */   private static final int GOLD_AMT = 222;
/*    */   
/* 22 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 23 */   private static final String MASK_RESULT = DESCRIPTIONS[1];
/* 24 */   private static final String RELIC_RESULT = DESCRIPTIONS[2];
/* 25 */   private CurScreen screen = CurScreen.INTRO;
/*    */   
/*    */   private enum CurScreen {
/* 28 */     INTRO, RESULT;
/*    */   }
/*    */   
/*    */   public TombRedMask() {
/* 32 */     super(NAME, DIALOG_1, "images/events/redMaskTomb.jpg");
/*    */     
/* 34 */     if (AbstractDungeon.player.hasRelic("Red Mask")) {
/* 35 */       this.imageEventText.setDialogOption(OPTIONS[0]);
/*    */     } else {
/* 37 */       this.imageEventText.setDialogOption(OPTIONS[1], true);
/* 38 */       this.imageEventText.setDialogOption(OPTIONS[2] + AbstractDungeon.player.gold + OPTIONS[3], (AbstractRelic)new RedMask());
/*    */     } 
/* 40 */     this.imageEventText.setDialogOption(OPTIONS[4]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 46 */     switch (this.screen) {
/*    */       case INTRO:
/* 48 */         if (buttonPressed == 0) {
/* 49 */           AbstractDungeon.effectList.add(new RainingGoldEffect(222));
/* 50 */           AbstractDungeon.player.gainGold(222);
/* 51 */           this.imageEventText.updateBodyText(MASK_RESULT);
/* 52 */           logMetricGainGold("Tomb of Lord Red Mask", "Wore Mask", 222);
/* 53 */         } else if (buttonPressed == 1 && !AbstractDungeon.player.hasRelic("Red Mask")) {
/* 54 */           RedMask redMask = new RedMask();
/* 55 */           logMetricObtainRelicAtCost("Tomb of Lord Red Mask", "Paid", (AbstractRelic)redMask, AbstractDungeon.player.gold);
/* 56 */           AbstractDungeon.player.loseGold(AbstractDungeon.player.gold);
/* 57 */           AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), (AbstractRelic)redMask);
/* 58 */           this.imageEventText.updateBodyText(RELIC_RESULT);
/*    */         } else {
/* 60 */           openMap();
/* 61 */           this.imageEventText.clearAllDialogs();
/* 62 */           this.imageEventText.setDialogOption(OPTIONS[4]);
/* 63 */           logMetricIgnored("Tomb of Lord Red Mask");
/*    */         } 
/* 65 */         this.imageEventText.clearAllDialogs();
/* 66 */         this.imageEventText.setDialogOption(OPTIONS[4]);
/* 67 */         this.screen = CurScreen.RESULT;
/*    */         return;
/*    */     } 
/* 70 */     openMap();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\beyond\TombRedMask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */