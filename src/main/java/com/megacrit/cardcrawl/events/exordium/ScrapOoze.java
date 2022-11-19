/*    */ package com.megacrit.cardcrawl.events.exordium;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class ScrapOoze
/*    */   extends AbstractImageEvent {
/* 16 */   private static final Logger logger = LogManager.getLogger(ScrapOoze.class.getName());
/*    */   public static final String ID = "Scrap Ooze";
/* 18 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Scrap Ooze");
/* 19 */   public static final String NAME = eventStrings.NAME;
/* 20 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/* 21 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*    */   
/* 23 */   private int relicObtainChance = 25;
/* 24 */   private int dmg = 3;
/* 25 */   private int totalDamageDealt = 0;
/* 26 */   private int screenNum = 0;
/* 27 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/* 28 */   private static final String FAIL_MSG = DESCRIPTIONS[1];
/* 29 */   private static final String SUCCESS_MSG = DESCRIPTIONS[2];
/* 30 */   private static final String ESCAPE_MSG = DESCRIPTIONS[3];
/*    */   
/*    */   public ScrapOoze() {
/* 33 */     super(NAME, DIALOG_1, "images/events/scrapOoze.jpg");
/*    */     
/* 35 */     if (AbstractDungeon.ascensionLevel >= 15) {
/* 36 */       this.dmg = 5;
/*    */     }
/*    */     
/* 39 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.dmg + OPTIONS[1] + this.relicObtainChance + OPTIONS[2]);
/* 40 */     this.imageEventText.setDialogOption(OPTIONS[3]);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterRoom() {
/* 45 */     if (Settings.AMBIANCE_ON) {
/* 46 */       CardCrawlGame.sound.play("EVENT_OOZE");
/*    */     }
/*    */   }
/*    */   
/*    */   protected void buttonEffect(int buttonPressed) {
/*    */     int random;
/* 52 */     switch (this.screenNum) {
/*    */       case 0:
/* 54 */         switch (buttonPressed) {
/*    */           case 0:
/* 56 */             AbstractDungeon.player.damage(new DamageInfo(null, this.dmg));
/* 57 */             CardCrawlGame.sound.play("ATTACK_POISON");
/* 58 */             this.totalDamageDealt += this.dmg;
/* 59 */             random = AbstractDungeon.miscRng.random(0, 99);
/*    */             
/* 61 */             if (random >= 99 - this.relicObtainChance) {
/* 62 */               this.imageEventText.updateBodyText(SUCCESS_MSG);
/* 63 */               AbstractRelic r = AbstractDungeon.returnRandomScreenlessRelic(
/* 64 */                   AbstractDungeon.returnRandomRelicTier());
/* 65 */               AbstractEvent.logMetricObtainRelicAndDamage("Scrap Ooze", "Success", r, this.totalDamageDealt);
/* 66 */               this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/* 67 */               this.imageEventText.removeDialogOption(1);
/* 68 */               this.screenNum = 1;
/* 69 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, r);
/*    */               
/*    */               break;
/*    */             } 
/*    */             
/* 74 */             this.imageEventText.updateBodyText(FAIL_MSG);
/* 75 */             this.relicObtainChance += 10;
/* 76 */             this.dmg++;
/* 77 */             this.imageEventText.updateDialogOption(0, OPTIONS[4] + this.dmg + OPTIONS[1] + this.relicObtainChance + OPTIONS[2]);
/*    */ 
/*    */             
/* 80 */             this.imageEventText.updateDialogOption(1, OPTIONS[3]);
/*    */             break;
/*    */ 
/*    */           
/*    */           case 1:
/* 85 */             AbstractEvent.logMetricTakeDamage("Scrap Ooze", "Fled", this.totalDamageDealt);
/* 86 */             this.imageEventText.updateBodyText(ESCAPE_MSG);
/* 87 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/* 88 */             this.imageEventText.removeDialogOption(1);
/* 89 */             this.screenNum = 1;
/*    */             break;
/*    */         } 
/* 92 */         logger.info("ERROR: case " + buttonPressed + " should never be called");
/*    */         break;
/*    */ 
/*    */       
/*    */       case 1:
/* 97 */         openMap();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\ScrapOoze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */