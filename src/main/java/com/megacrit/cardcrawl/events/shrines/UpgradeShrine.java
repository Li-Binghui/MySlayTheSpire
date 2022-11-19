/*    */ package com.megacrit.cardcrawl.events.shrines;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*    */ import com.megacrit.cardcrawl.localization.EventStrings;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
/*    */ 
/*    */ public class UpgradeShrine
/*    */   extends AbstractImageEvent {
/*    */   public static final String ID = "Upgrade Shrine";
/* 16 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Upgrade Shrine");
/* 17 */   public static final String NAME = eventStrings.NAME;
/* 18 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 19 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/* 21 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 22 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/* 23 */   private static final String IGNORE = DESCRIPTIONS[2];
/*    */   
/* 25 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*    */   
/*    */   private enum CUR_SCREEN {
/* 28 */     INTRO, COMPLETE;
/*    */   }
/*    */   
/*    */   public UpgradeShrine() {
/* 32 */     super(NAME, DIALOG_1, "images/events/shrine2.jpg");
/* 33 */     if (AbstractDungeon.player.masterDeck.hasUpgradableCards().booleanValue()) {
/* 34 */       this.imageEventText.setDialogOption(OPTIONS[0]);
/*    */     } else {
/* 36 */       this.imageEventText.setDialogOption(OPTIONS[3], true);
/*    */     } 
/* 38 */     this.imageEventText.setDialogOption(OPTIONS[1]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom() {
/* 43 */     CardCrawlGame.music.playTempBGM("SHRINE");
/*    */   }
/*    */   
/*    */   public void update() {
/* 47 */     super.update();
/* 48 */     if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 49 */       AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
/* 50 */       c.upgrade();
/* 51 */       logMetricCardUpgrade("Upgrade Shrine", "Upgraded", c);
/* 52 */       AbstractDungeon.player.bottledCardUpgradeCheck(c);
/* 53 */       AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy()));
/* 54 */       AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/* 55 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 61 */     switch (this.screen) {
/*    */       
/*    */       case INTRO:
/* 64 */         switch (buttonPressed) {
/*    */           case 0:
/* 66 */             this.screen = CUR_SCREEN.COMPLETE;
/* 67 */             (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 68 */             this.imageEventText.updateBodyText(DIALOG_2);
/* 69 */             AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
/* 70 */                 .getUpgradableCards(), 1, OPTIONS[2], true, false, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 77 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 78 */             this.imageEventText.clearRemainingOptions();
/*    */             break;
/*    */           case 1:
/* 81 */             this.screen = CUR_SCREEN.COMPLETE;
/* 82 */             (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 83 */             logMetricIgnored("Upgrade Shrine");
/* 84 */             this.imageEventText.updateBodyText(IGNORE);
/* 85 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 86 */             this.imageEventText.clearRemainingOptions();
/*    */             break;
/*    */         } 
/*    */         break;
/*    */       case COMPLETE:
/* 91 */         openMap();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\UpgradeShrine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */