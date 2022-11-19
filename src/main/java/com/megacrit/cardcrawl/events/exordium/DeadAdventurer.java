/*     */ package com.megacrit.cardcrawl.events.exordium;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.RoomEventDialog;
/*     */ import com.megacrit.cardcrawl.helpers.EffectHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MonsterHelper;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Random;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class DeadAdventurer extends AbstractEvent {
/*  25 */   private static final Logger logger = LogManager.getLogger(DeadAdventurer.class.getName());
/*     */   public static final String ID = "Dead Adventurer";
/*  27 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Dead Adventurer");
/*  28 */   public static final String NAME = eventStrings.NAME;
/*  29 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  30 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*     */   private static final int GOLD_REWARD = 30;
/*     */   private static final int ENCOUNTER_CHANCE_START = 25;
/*     */   private static final int A_2_CHANCE_START = 35;
/*     */   private static final int ENCOUNTER_CHANCE_RAMP = 25;
/*  36 */   private static final String FIGHT_MSG = DESCRIPTIONS[0];
/*  37 */   private static final String ESCAPE_MSG = DESCRIPTIONS[1];
/*     */   
/*  39 */   private int numRewards = 0;
/*  40 */   private int encounterChance = 0;
/*  41 */   private ArrayList<String> rewards = new ArrayList<>();
/*  42 */   private float x = 800.0F * Settings.xScale; private float y = AbstractDungeon.floorY;
/*  43 */   private int enemy = 0;
/*  44 */   private CUR_SCREEN screen = CUR_SCREEN.INTRO;
/*  45 */   private static final Color DARKEN_COLOR = new Color(0.5F, 0.5F, 0.5F, 1.0F);
/*     */   
/*     */   public static final String LAGAVULIN_FIGHT = "Lagavulin Dead Adventurers Fight";
/*     */   private Texture adventurerImg;
/*  49 */   private int goldRewardMetric = 0;
/*  50 */   private AbstractRelic relicRewardMetric = null;
/*     */   
/*     */   private enum CUR_SCREEN {
/*  53 */     INTRO, FAIL, SUCCESS, ESCAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public DeadAdventurer() {
/*  58 */     this.rewards.add("GOLD");
/*  59 */     this.rewards.add("NOTHING");
/*  60 */     this.rewards.add("RELIC");
/*  61 */     Collections.shuffle(this.rewards, new Random(AbstractDungeon.miscRng.randomLong()));
/*     */     
/*  63 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  64 */       this.encounterChance = 35;
/*     */     } else {
/*  66 */       this.encounterChance = 25;
/*     */     } 
/*     */     
/*  69 */     this.enemy = AbstractDungeon.miscRng.random(0, 2);
/*  70 */     this.adventurerImg = ImageMaster.loadImage("images/npcs/nopants.png");
/*  71 */     this.body = DESCRIPTIONS[2];
/*     */     
/*  73 */     switch (this.enemy) {
/*     */       case 0:
/*  75 */         this.body += DESCRIPTIONS[3];
/*     */         break;
/*     */       case 1:
/*  78 */         this.body += DESCRIPTIONS[4];
/*     */         break;
/*     */       default:
/*  81 */         this.body += DESCRIPTIONS[5];
/*     */         break;
/*     */     } 
/*  84 */     this.body += DESCRIPTIONS[6];
/*     */     
/*  86 */     this.roomEventText.addDialogOption(OPTIONS[0] + this.encounterChance + OPTIONS[4]);
/*  87 */     this.roomEventText.addDialogOption(OPTIONS[1]);
/*     */     
/*  89 */     this.hasDialog = true;
/*  90 */     this.hasFocus = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  95 */     super.update();
/*  96 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.EVENT || 
/*  97 */       (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMPLETE) {
/*  98 */       this.imgColor = Color.WHITE.cpy();
/*     */     } else {
/* 100 */       this.imgColor = DARKEN_COLOR;
/*     */     } 
/*     */     
/* 103 */     if (!RoomEventDialog.waitForInput) {
/* 104 */       buttonEffect(this.roomEventText.getSelectedOption());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/* 110 */     switch (this.screen) {
/*     */       case INTRO:
/* 112 */         switch (buttonPressed) {
/*     */           case 0:
/* 114 */             if (AbstractDungeon.miscRng.random(0, 99) < this.encounterChance) {
/*     */               
/* 116 */               this.screen = CUR_SCREEN.FAIL;
/* 117 */               this.roomEventText.updateBodyText(FIGHT_MSG);
/* 118 */               this.roomEventText.updateDialogOption(0, OPTIONS[2]);
/* 119 */               this.roomEventText.removeDialogOption(1);
/* 120 */               if (Settings.isDailyRun) {
/* 121 */                 AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(30));
/*     */               } else {
/* 123 */                 AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(25, 35));
/*     */               } 
/*     */               
/* 126 */               (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter(getMonster());
/* 127 */               (AbstractDungeon.getCurrRoom()).eliteTrigger = true;
/*     */               
/*     */               break;
/*     */             } 
/* 131 */             randomReward();
/*     */             break;
/*     */           
/*     */           case 1:
/* 135 */             this.screen = CUR_SCREEN.ESCAPE;
/* 136 */             this.roomEventText.updateBodyText(ESCAPE_MSG);
/* 137 */             this.roomEventText.updateDialogOption(0, OPTIONS[1]);
/* 138 */             this.roomEventText.removeDialogOption(1);
/*     */             break;
/*     */         } 
/*     */         return;
/*     */       case SUCCESS:
/* 143 */         openMap();
/*     */         return;
/*     */       case FAIL:
/* 146 */         for (String s : this.rewards) {
/* 147 */           if (s.equals("GOLD")) {
/* 148 */             AbstractDungeon.getCurrRoom().addGoldToRewards(30); continue;
/* 149 */           }  if (s.equals("RELIC")) {
/* 150 */             AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractDungeon.returnRandomRelicTier());
/*     */           }
/*     */         } 
/* 153 */         enterCombat();
/* 154 */         AbstractDungeon.lastCombatMetricKey = getMonster();
/* 155 */         this.numRewards++;
/* 156 */         logMetric(this.numRewards);
/*     */         return;
/*     */       case ESCAPE:
/* 159 */         logMetric(this.numRewards);
/* 160 */         openMap();
/*     */         return;
/*     */     } 
/* 163 */     logger.info("WHY YOU CALLED?");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getMonster() {
/* 169 */     switch (this.enemy) {
/*     */       case 0:
/* 171 */         return "3 Sentries";
/*     */       case 1:
/* 173 */         return "Gremlin Nob";
/*     */     } 
/* 175 */     return "Lagavulin Event";
/*     */   }
/*     */   
/*     */   private void randomReward() {
/*     */     AbstractRelic r;
/* 180 */     this.numRewards++;
/* 181 */     this.encounterChance += 25;
/* 182 */     switch ((String)this.rewards.remove(0)) {
/*     */       case "GOLD":
/* 184 */         this.roomEventText.updateBodyText(DESCRIPTIONS[7]);
/* 185 */         EffectHelper.gainGold((AbstractCreature)AbstractDungeon.player, this.x, this.y, 30);
/* 186 */         AbstractDungeon.player.gainGold(30);
/* 187 */         this.goldRewardMetric = 30;
/*     */         break;
/*     */       case "NOTHING":
/* 190 */         this.roomEventText.updateBodyText(DESCRIPTIONS[8]);
/*     */         break;
/*     */       case "RELIC":
/* 193 */         this.roomEventText.updateBodyText(DESCRIPTIONS[9]);
/* 194 */         r = AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
/* 195 */         this.relicRewardMetric = r;
/* 196 */         AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.x, this.y, r);
/*     */         break;
/*     */       default:
/* 199 */         logger.info("HOW IS THIS POSSSIBLLEEEE");
/*     */         break;
/*     */     } 
/*     */     
/* 203 */     if (this.numRewards == 3) {
/* 204 */       this.roomEventText.updateBodyText(DESCRIPTIONS[10]);
/* 205 */       this.roomEventText.updateDialogOption(0, OPTIONS[1]);
/* 206 */       this.roomEventText.removeDialogOption(1);
/* 207 */       this.screen = CUR_SCREEN.SUCCESS;
/* 208 */       logMetric(this.numRewards);
/*     */     } else {
/* 210 */       logger.info("SHOULD NOT DISMISS");
/* 211 */       this.roomEventText.updateDialogOption(0, OPTIONS[3] + this.encounterChance + OPTIONS[4]);
/* 212 */       this.roomEventText.updateDialogOption(1, OPTIONS[1]);
/* 213 */       this.screen = CUR_SCREEN.INTRO;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void logMetric(int numAttempts) {
/* 218 */     if (this.relicRewardMetric != null) {
/* 219 */       AbstractEvent.logMetricGainGoldAndRelic("Dead Adventurer", "Searched '" + numAttempts + "' times", this.relicRewardMetric, this.goldRewardMetric);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 225 */       AbstractEvent.logMetricGainGold("Dead Adventurer", "Searched '" + numAttempts + "' times", this.goldRewardMetric);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 232 */     super.render(sb);
/* 233 */     sb.setColor(Color.WHITE);
/* 234 */     sb.draw(this.adventurerImg, this.x - 146.0F, this.y - 86.5F, 146.0F, 86.5F, 292.0F, 173.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 292, 173, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 255 */     super.dispose();
/* 256 */     if (this.adventurerImg != null) {
/* 257 */       this.adventurerImg.dispose();
/* 258 */       this.adventurerImg = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\exordium\DeadAdventurer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */