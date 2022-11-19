/*    */ package com.megacrit.cardcrawl.events.shrines;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*    */ import com.megacrit.cardcrawl.events.GenericEventDialog;
/*    */ import com.megacrit.cardcrawl.helpers.PotionHelper;
/*    */ import com.megacrit.cardcrawl.localization.EventStrings;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class Lab
/*    */   extends AbstractImageEvent {
/*    */   public static final String ID = "Lab";
/* 16 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Lab");
/* 17 */   public static final String NAME = eventStrings.NAME;
/* 18 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 19 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/* 21 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 22 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*    */   
/*    */   private enum CUR_SCREEN {
/* 25 */     INTRO, COMPLETE;
/*    */   }
/*    */   
/*    */   public Lab() {
/* 29 */     super(NAME, DIALOG_1, "images/events/lab.jpg");
/* 30 */     this.noCardsInRewards = true;
/* 31 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom() {
/* 36 */     if (Settings.AMBIANCE_ON) {
/* 37 */       CardCrawlGame.sound.play("EVENT_LAB");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 43 */     switch (this.screen) {
/*    */       
/*    */       case INTRO:
/* 46 */         GenericEventDialog.hide();
/* 47 */         (AbstractDungeon.getCurrRoom()).rewards.clear();
/*    */         
/* 49 */         (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
/* 50 */         (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
/* 51 */         if (AbstractDungeon.ascensionLevel < 15) {
/* 52 */           (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem(PotionHelper.getRandomPotion()));
/*    */         }
/*    */         
/* 55 */         this.screen = CUR_SCREEN.COMPLETE;
/* 56 */         (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 57 */         AbstractDungeon.combatRewardScreen.open();
/* 58 */         logMetric("Lab", "Got Potions");
/*    */         break;
/*    */       case COMPLETE:
/* 61 */         openMap();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\shrines\Lab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */