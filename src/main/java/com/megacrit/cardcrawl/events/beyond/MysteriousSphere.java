/*    */ package com.megacrit.cardcrawl.events.beyond;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*    */ import com.megacrit.cardcrawl.events.RoomEventDialog;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.helpers.MonsterHelper;
/*    */ import com.megacrit.cardcrawl.localization.EventStrings;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ public class MysteriousSphere extends AbstractEvent {
/*    */   public static final String ID = "Mysterious Sphere";
/* 15 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Mysterious Sphere");
/* 16 */   public static final String NAME = eventStrings.NAME;
/* 17 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 18 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/* 20 */   private static final String INTRO_MSG = DESCRIPTIONS[0];
/* 21 */   private CurScreen screen = CurScreen.INTRO;
/*    */   
/*    */   private enum CurScreen {
/* 24 */     INTRO, PRE_COMBAT, END;
/*    */   }
/*    */ 
/*    */   
/*    */   public MysteriousSphere() {
/* 29 */     initializeImage("images/events/sphereClosed.png", 1120.0F * Settings.xScale, AbstractDungeon.floorY - 50.0F * Settings.scale);
/*    */ 
/*    */ 
/*    */     
/* 33 */     this.body = INTRO_MSG;
/* 34 */     this.roomEventText.addDialogOption(OPTIONS[0]);
/* 35 */     this.roomEventText.addDialogOption(OPTIONS[1]);
/*    */     
/* 37 */     this.hasDialog = true;
/* 38 */     this.hasFocus = true;
/* 39 */     (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("2 Orb Walkers");
/*    */   }
/*    */   
/*    */   public void update() {
/* 43 */     super.update();
/*    */     
/* 45 */     if (!RoomEventDialog.waitForInput) {
/* 46 */       buttonEffect(this.roomEventText.getSelectedOption());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/* 52 */     switch (this.screen) {
/*    */       case INTRO:
/* 54 */         switch (buttonPressed) {
/*    */           case 0:
/* 56 */             this.screen = CurScreen.PRE_COMBAT;
/* 57 */             this.roomEventText.updateBodyText(DESCRIPTIONS[1]);
/* 58 */             this.roomEventText.updateDialogOption(0, OPTIONS[2]);
/* 59 */             this.roomEventText.clearRemainingOptions();
/* 60 */             logMetric("Mysterious Sphere", "Fight");
/*    */             return;
/*    */           case 1:
/* 63 */             this.screen = CurScreen.END;
/* 64 */             this.roomEventText.updateBodyText(DESCRIPTIONS[2]);
/* 65 */             this.roomEventText.updateDialogOption(0, OPTIONS[1]);
/* 66 */             this.roomEventText.clearRemainingOptions();
/* 67 */             logMetricIgnored("Mysterious Sphere");
/*    */             return;
/*    */         } 
/*    */         break;
/*    */       case PRE_COMBAT:
/* 72 */         if (Settings.isDailyRun) {
/* 73 */           AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(50));
/*    */         } else {
/* 75 */           AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(45, 55));
/*    */         } 
/* 77 */         AbstractDungeon.getCurrRoom().addRelicToRewards(
/* 78 */             AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.RARE));
/*    */ 
/*    */         
/* 81 */         if (this.img != null) {
/* 82 */           this.img.dispose();
/* 83 */           this.img = null;
/*    */         } 
/*    */         
/* 86 */         this.img = ImageMaster.loadImage("images/events/sphereOpen.png");
/*    */         
/* 88 */         enterCombat();
/* 89 */         AbstractDungeon.lastCombatMetricKey = "2 Orb Walkers";
/*    */         break;
/*    */       case END:
/* 92 */         openMap();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\beyond\MysteriousSphere.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */