/*    */ package com.megacrit.cardcrawl.events.exordium;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.curses.Doubt;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*    */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*    */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*    */ import com.megacrit.cardcrawl.localization.EventStrings;
/*    */ import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*    */ 
/*    */ public class Sssserpent
/*    */   extends AbstractImageEvent {
/*    */   public static final String ID = "Liars Game";
/* 18 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Liars Game");
/* 19 */   public static final String NAME = eventStrings.NAME;
/* 20 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 21 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/* 23 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 24 */   private static final String AGREE_DIALOG = DESCRIPTIONS[1];
/* 25 */   private static final String DISAGREE_DIALOG = DESCRIPTIONS[2];
/* 26 */   private static final String GOLD_RAIN_MSG = DESCRIPTIONS[3];
/* 27 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*    */   private static final int GOLD_REWARD = 175;
/*    */   private static final int A_2_GOLD_REWARD = 150;
/*    */   private int goldReward;
/*    */   private AbstractCard curse;
/*    */   
/*    */   private enum CUR_SCREEN {
/* 34 */     INTRO, AGREE, DISAGREE, COMPLETE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom() {
/* 39 */     if (Settings.AMBIANCE_ON) {
/* 40 */       CardCrawlGame.sound.play("EVENT_SERPENT");
/*    */     }
/*    */   }
/*    */   
/*    */   public Sssserpent() {
/* 45 */     super(NAME, DIALOG_1, "images/events/liarsGame.jpg");
/*    */     
/* 47 */     if (AbstractDungeon.ascensionLevel >= 15) {
/* 48 */       this.goldReward = 150;
/*    */     } else {
/* 50 */       this.goldReward = 175;
/*    */     } 
/* 52 */     this.curse = (AbstractCard)new Doubt();
/*    */     
/* 54 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.goldReward + OPTIONS[1], CardLibrary.getCopy(this.curse.cardID));
/* 55 */     this.imageEventText.setDialogOption(OPTIONS[2]);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 60 */     switch (this.screen) {
/*    */       case INTRO:
/* 62 */         if (buttonPressed == 0) {
/* 63 */           this.imageEventText.updateBodyText(AGREE_DIALOG);
/* 64 */           this.imageEventText.removeDialogOption(1);
/* 65 */           this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/* 66 */           this.screen = CUR_SCREEN.AGREE;
/* 67 */           AbstractEvent.logMetricGainGoldAndCard("Liars Game", "AGREE", this.curse, this.goldReward);
/*    */         } else {
/* 69 */           this.imageEventText.updateBodyText(DISAGREE_DIALOG);
/* 70 */           this.imageEventText.removeDialogOption(1);
/* 71 */           this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 72 */           this.screen = CUR_SCREEN.DISAGREE;
/* 73 */           AbstractEvent.logMetricIgnored("Liars Game");
/*    */         } 
/*    */         return;
/*    */       case AGREE:
/* 77 */         AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(this.curse, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*    */         
/* 79 */         AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldReward));
/* 80 */         AbstractDungeon.player.gainGold(this.goldReward);
/* 81 */         this.imageEventText.updateBodyText(GOLD_RAIN_MSG);
/* 82 */         this.imageEventText.updateDialogOption(0, OPTIONS[4]);
/* 83 */         this.screen = CUR_SCREEN.COMPLETE;
/*    */         return;
/*    */     } 
/* 86 */     openMap();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\Sssserpent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */