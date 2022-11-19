/*     */ package com.megacrit.cardcrawl.events.exordium;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Parasite;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.relics.OddMushroom;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class Mushrooms extends AbstractEvent {
/*  26 */   private static final Logger logger = LogManager.getLogger(Mushrooms.class.getName());
/*     */   public static final String ID = "Mushrooms";
/*  28 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Mushrooms");
/*  29 */   public static final String NAME = eventStrings.NAME;
/*  30 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  31 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*     */   public static final String ENC_NAME = "The Mushroom Lair";
/*  34 */   private Texture fgImg = ImageMaster.loadImage("images/events/fgShrooms.png");
/*  35 */   private Texture bgImg = ImageMaster.loadImage("images/events/bgShrooms.png");
/*     */   private static final float HEAL_AMT = 0.25F;
/*  37 */   private static final String HEAL_MSG = DESCRIPTIONS[0];
/*  38 */   private static final String FIGHT_MSG = DESCRIPTIONS[1];
/*  39 */   private int screenNum = 0;
/*     */ 
/*     */   
/*     */   public Mushrooms() {
/*  43 */     this.body = DESCRIPTIONS[2];
/*     */     
/*  45 */     this.roomEventText.addDialogOption(OPTIONS[0]);
/*  46 */     int temp = (int)(AbstractDungeon.player.maxHealth * 0.25F);
/*  47 */     this.roomEventText.addDialogOption(OPTIONS[1] + temp + OPTIONS[2], CardLibrary.getCopy("Parasite"));
/*  48 */     (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.EVENT;
/*  49 */     this.hasDialog = true;
/*  50 */     this.hasFocus = true;
/*     */   }
/*     */   
/*     */   public void update() {
/*  54 */     super.update();
/*  55 */     if (!RoomEventDialog.waitForInput)
/*  56 */       buttonEffect(this.roomEventText.getSelectedOption()); 
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     Parasite parasite;
/*     */     int healAmt;
/*  62 */     switch (buttonPressed) {
/*     */       
/*     */       case 0:
/*  65 */         if (this.screenNum == 0) {
/*  66 */           (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("The Mushroom Lair");
/*     */           
/*  68 */           this.roomEventText.updateBodyText(FIGHT_MSG);
/*  69 */           this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/*  70 */           this.roomEventText.removeDialogOption(1);
/*  71 */           AbstractEvent.logMetric("Mushrooms", "Fought Mushrooms");
/*  72 */           this.screenNum += 2;
/*  73 */         } else if (this.screenNum == 1) {
/*     */           
/*  75 */           openMap();
/*  76 */         } else if (this.screenNum == 2) {
/*  77 */           if (Settings.isDailyRun) {
/*  78 */             AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(25));
/*     */           } else {
/*  80 */             AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(20, 30));
/*     */           } 
/*     */           
/*  83 */           if (AbstractDungeon.player.hasRelic("Odd Mushroom")) {
/*  84 */             AbstractDungeon.getCurrRoom().addRelicToRewards((AbstractRelic)new Circlet());
/*     */           } else {
/*  86 */             AbstractDungeon.getCurrRoom().addRelicToRewards((AbstractRelic)new OddMushroom());
/*     */           } 
/*     */           
/*  89 */           enterCombat();
/*  90 */           AbstractDungeon.lastCombatMetricKey = "The Mushroom Lair";
/*     */         } 
/*     */         return;
/*     */       
/*     */       case 1:
/*  95 */         parasite = new Parasite();
/*  96 */         healAmt = (int)(AbstractDungeon.player.maxHealth * 0.25F);
/*  97 */         AbstractEvent.logMetricObtainCardAndHeal("Mushrooms", "Healed and dodged fight", (AbstractCard)parasite, healAmt);
/*  98 */         AbstractDungeon.player.heal(healAmt);
/*  99 */         AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)parasite, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */         
/* 101 */         this.roomEventText.updateBodyText(HEAL_MSG);
/* 102 */         this.roomEventText.updateDialogOption(0, OPTIONS[4]);
/* 103 */         this.roomEventText.removeDialogOption(1);
/* 104 */         this.screenNum = 1;
/*     */         return;
/*     */     } 
/* 107 */     logger.info("ERROR: case " + buttonPressed + " should never be called");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 114 */     sb.setColor(Color.WHITE);
/* 115 */     sb.draw(this.bgImg, 0.0F, -10.0F, Settings.WIDTH, 1080.0F * Settings.scale);
/* 116 */     sb.draw(this.fgImg, 0.0F, -20.0F * Settings.scale, Settings.WIDTH, 1080.0F * Settings.scale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 121 */     super.dispose();
/* 122 */     System.out.println("DISPOSING MUSHROOM ASSETS>");
/* 123 */     if (this.bgImg != null) {
/* 124 */       this.bgImg.dispose();
/* 125 */       this.bgImg = null;
/*     */     } 
/* 127 */     if (this.fgImg != null) {
/* 128 */       this.fgImg.dispose();
/* 129 */       this.fgImg = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\Mushrooms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */