/*    */ package com.megacrit.cardcrawl.events.beyond;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.vfx.FadeWipeParticle;
/*    */ 
/*    */ public class SecretPortal
/*    */   extends AbstractImageEvent {
/*    */   public static final String ID = "SecretPortal";
/* 16 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("SecretPortal");
/* 17 */   public static final String NAME = eventStrings.NAME;
/* 18 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 19 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/*    */   public static final String EVENT_CHOICE_TOOK_PORTAL = "Took Portal";
/* 22 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 23 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/* 24 */   private static final String DIALOG_3 = DESCRIPTIONS[2];
/*    */   
/* 26 */   private CurScreen screen = CurScreen.INTRO;
/*    */   
/*    */   private enum CurScreen {
/* 29 */     INTRO, ACCEPT, LEAVE;
/*    */   }
/*    */   
/*    */   public SecretPortal() {
/* 33 */     super(NAME, DIALOG_1, "images/events/secretPortal.jpg");
/* 34 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/* 35 */     this.imageEventText.setDialogOption(OPTIONS[1]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom() {
/* 40 */     if (Settings.AMBIANCE_ON) {
/* 41 */       CardCrawlGame.sound.play("EVENT_PORTAL");
/*    */     }
/*    */   }
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/*    */     MapRoomNode node;
/* 47 */     switch (this.screen) {
/*    */       case INTRO:
/* 49 */         switch (buttonPressed) {
/*    */           case 0:
/* 51 */             this.imageEventText.updateBodyText(DIALOG_2);
/* 52 */             this.screen = CurScreen.ACCEPT;
/* 53 */             logMetric("SecretPortal", "Took Portal");
/* 54 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 55 */             CardCrawlGame.screenShake.mildRumble(5.0F);
/* 56 */             CardCrawlGame.sound.play("ATTACK_MAGIC_SLOW_2");
/*    */             break;
/*    */           case 1:
/* 59 */             this.imageEventText.updateBodyText(DIALOG_3);
/* 60 */             this.screen = CurScreen.LEAVE;
/* 61 */             logMetricIgnored("SecretPortal");
/* 62 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/*    */             break;
/*    */         } 
/*    */ 
/*    */         
/* 67 */         this.imageEventText.clearRemainingOptions();
/*    */         return;
/*    */       
/*    */       case ACCEPT:
/* 71 */         (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/* 72 */         node = new MapRoomNode(-1, 15);
/* 73 */         node.room = (AbstractRoom)new MonsterRoomBoss();
/* 74 */         AbstractDungeon.nextRoom = node;
/* 75 */         CardCrawlGame.music.fadeOutTempBGM();
/* 76 */         AbstractDungeon.pathX.add(Integer.valueOf(1));
/* 77 */         AbstractDungeon.pathY.add(Integer.valueOf(15));
/* 78 */         AbstractDungeon.topLevelEffects.add(new FadeWipeParticle());
/* 79 */         AbstractDungeon.nextRoomTransitionStart();
/*    */         return;
/*    */     } 
/* 82 */     openMap();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\beyond\SecretPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */