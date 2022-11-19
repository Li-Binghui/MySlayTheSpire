/*     */ package com.megacrit.cardcrawl.events.beyond;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AnimatedNpc;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.RoomEventDialog;
import com.megacrit.cardcrawl.helpers.HeartAnimListener;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.DamageHeartEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.NumberFormat;
import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpireHeart
/*     */   extends AbstractEvent
/*     */ {
/*  34 */   private static final Logger logger = LogManager.getLogger(SpireHeart.class.getName());
/*     */   public static final String ID = "Spire Heart";
/*  36 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Spire Heart");
/*  37 */   public static final String NAME = eventStrings.NAME;
/*  38 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  39 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*     */   private CUR_SCREEN screen;
/*     */   private AnimatedNpc npc;
/*     */   private float x;
/*     */   private float y;
/*     */   private boolean startHeartAnimation;
/*     */   private float eventFadeTimer;
/*     */   private Color fadeColor;
/*     */   private long globalDamageDealt;
/*     */   private long totalDamageDealt;
/*     */   private int damageDealt;
/*     */   private int winstreak;
/*     */   private static final String HEART_DMG_KEY = "test_stat";
/*     */   
/*     */   private enum CUR_SCREEN
/*     */   {
/*  56 */     INTRO, MIDDLE, MIDDLE_2, DEATH, GO_TO_ENDING; } public SpireHeart() { String winStreakStatId; this.screen = CUR_SCREEN.INTRO; this.npc = null; this.x = 1300.0F * Settings.xScale;
/*     */     this.y = Settings.HEIGHT / 2.0F + 40.0F * Settings.scale;
/*     */     this.startHeartAnimation = false;
/*     */     this.eventFadeTimer = 3.0F;
/*     */     this.fadeColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  61 */     this.npc = new AnimatedNpc(1300.0F * Settings.xScale, AbstractDungeon.floorY - 80.0F * Settings.scale, "images/npcs/heart/skeleton.atlas", "images/npcs/heart/skeleton.json", "idle");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     this.npc.setTimeScale(1.5F);
/*  68 */     this.npc.addListener(new HeartAnimListener());
/*     */     
/*  70 */     this.body = DESCRIPTIONS[0];
/*  71 */     this.roomEventText.clear();
/*  72 */     this.roomEventText.addDialogOption(OPTIONS[0]);
/*  73 */     this.hasDialog = true;
/*  74 */     this.hasFocus = true;
/*     */ 
/*     */     
/*  77 */     this.globalDamageDealt = CardCrawlGame.publisherIntegration.getGlobalStat("test_stat");
/*  78 */     GameOverScreen.resetScoreChecks();
/*  79 */     this.damageDealt = GameOverScreen.calcScore(true);
/*  80 */     CardCrawlGame.publisherIntegration.incrementStat("test_stat", this.damageDealt);
/*     */ 
/*     */ 
/*     */     
/*  84 */     if (Settings.isBeta) {
/*  85 */       winStreakStatId = AbstractDungeon.player.getWinStreakKey() + "_BETA";
/*     */     } else {
/*  87 */       winStreakStatId = AbstractDungeon.player.getWinStreakKey();
/*     */     } 
/*  89 */     CardCrawlGame.publisherIntegration.incrementStat(winStreakStatId, 1);
/*  90 */     logger.info("WIN STREAK  " + CardCrawlGame.publisherIntegration.getStat(winStreakStatId));
/*     */     
/*  92 */     this.totalDamageDealt = CardCrawlGame.publisherIntegration.getStat("test_stat");
/*     */ 
/*     */     
/*  95 */     boolean skipUpload = (Settings.isModded || !Settings.isStandardRun());
/*  96 */     if (!skipUpload) {
/*  97 */       String leaderboardWinStreakStatId; this.winstreak = CardCrawlGame.publisherIntegration.getStat(winStreakStatId);
/*     */       
/*  99 */       if (Settings.isBeta) {
/* 100 */         leaderboardWinStreakStatId = AbstractDungeon.player.getLeaderboardWinStreakKey() + "_BETA";
/*     */       } else {
/* 102 */         leaderboardWinStreakStatId = AbstractDungeon.player.getLeaderboardWinStreakKey();
/*     */       } 
/* 104 */       CardCrawlGame.publisherIntegration.uploadLeaderboardScore(leaderboardWinStreakStatId, this.winstreak);
/*     */     } 
/*     */ 
/*     */     
/* 108 */     CardCrawlGame.playerPref.putInteger("DMG_DEALT", this.damageDealt + CardCrawlGame.playerPref
/*     */         
/* 110 */         .getInteger("DMG_DEALT", 0));
/* 111 */     if (this.totalDamageDealt <= 0L) {
/* 112 */       this.totalDamageDealt = CardCrawlGame.playerPref.getInteger("DMG_DEALT", 0);
/*     */     } }
/*     */ 
/*     */   
/*     */   private void goToFinalAct() {
/* 117 */     this.fadeColor.a = 0.0F;
/* 118 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.DOOR_UNLOCK;
/* 119 */     CardCrawlGame.mainMenuScreen.doorUnlockScreen.open(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 124 */     super.update();
/* 125 */     if (!RoomEventDialog.waitForInput) {
/* 126 */       buttonEffect(this.roomEventText.getSelectedOption());
/*     */     }
/*     */     
/* 129 */     if (this.startHeartAnimation && this.hasFocus) {
/* 130 */       this.eventFadeTimer -= Gdx.graphics.getDeltaTime();
/*     */       
/* 132 */       if (this.eventFadeTimer < 0.0F) {
/* 133 */         this.eventFadeTimer = 0.0F;
/*     */       }
/*     */       
/* 136 */       this.npc.skeleton.setY(Interpolation.pow2Out
/* 137 */           .apply(Settings.HEIGHT, AbstractDungeon.floorY - 80.0F * Settings.scale, this.eventFadeTimer / 3.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 142 */       this.fadeColor.a = MathHelper.slowColorLerpSnap(this.fadeColor.a, 1.0F);
/* 143 */       this.npc.setTimeScale(Interpolation.fade.apply(0.8F, 1.5F, this.eventFadeTimer / 3.0F));
/*     */     }  } protected void buttonEffect(int buttonPressed) { Color color;
/*     */     AbstractGameAction.AttackEffect[] effects;
/*     */     int HITS, damagePerTick, remainder, damages[], i;
/*     */     float tmp;
/*     */     int j;
/* 149 */     switch (this.screen) {
/*     */       case INTRO:
/* 151 */         this.screen = CUR_SCREEN.MIDDLE;
/* 152 */         this.roomEventText.updateBodyText(AbstractDungeon.player.getSpireHeartText());
/* 153 */         this.roomEventText.updateDialogOption(0, OPTIONS[1]);
/*     */         return;
/*     */       case MIDDLE:
/* 156 */         this.screen = CUR_SCREEN.MIDDLE_2;
/* 157 */         this.roomEventText.updateBodyText(DESCRIPTIONS[1] + this.damageDealt + DESCRIPTIONS[2]);
/* 158 */         this.roomEventText.updateDialogOption(0, OPTIONS[0]);
/*     */         
/* 160 */         color = AbstractDungeon.player.getSlashAttackColor();
/* 161 */         effects = AbstractDungeon.player.getSpireHeartSlashEffect();
/*     */         
/* 163 */         HITS = effects.length;
/* 164 */         damagePerTick = this.damageDealt / HITS;
/* 165 */         remainder = this.damageDealt % HITS;
/*     */         
/* 167 */         damages = new int[HITS];
/* 168 */         for (i = 0; i < HITS; i++) {
/* 169 */           damages[i] = damagePerTick;
/* 170 */           if (remainder > 0) {
/* 171 */             damages[i] = damages[i] + 1;
/* 172 */             remainder--;
/*     */           } 
/*     */         } 
/*     */         
/* 176 */         tmp = 0.0F;
/* 177 */         AbstractDungeon.effectList.add(new BorderFlashEffect(color, true));
/* 178 */         for (j = 0; j < HITS; j++) {
/* 179 */           tmp += MathUtils.random(0.05F, 0.2F);
/* 180 */           AbstractDungeon.effectList.add(new DamageHeartEffect(tmp, this.x, this.y, effects[j], damages[j]));
/*     */         } 
/*     */         return;
/*     */       case MIDDLE_2:
/* 184 */         if (Settings.isFinalActAvailable && Settings.hasRubyKey && Settings.hasEmeraldKey && Settings.hasSapphireKey) {
/*     */           
/* 186 */           this.startHeartAnimation = true;
/* 187 */           CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.XLONG, false);
/* 188 */           CardCrawlGame.screenShake.rumble(5.0F);
/* 189 */           this.screen = CUR_SCREEN.GO_TO_ENDING;
/*     */ 
/*     */           
/* 192 */           this.roomEventText.updateBodyText(DESCRIPTIONS[11] + DESCRIPTIONS[12] + DESCRIPTIONS[13] + DESCRIPTIONS[14]);
/*     */           
/* 194 */           this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/*     */         } else {
/* 196 */           this.screen = CUR_SCREEN.DEATH;
/* 197 */           NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
/*     */           
/* 199 */           if (this.globalDamageDealt <= 0L) {
/* 200 */             this.roomEventText.updateBodyText(DESCRIPTIONS[3] + numberFormat
/* 201 */                 .format(this.totalDamageDealt) + DESCRIPTIONS[4] + DESCRIPTIONS[7]);
/*     */           }
/*     */           else {
/*     */             
/* 205 */             this.roomEventText.updateBodyText(DESCRIPTIONS[3] + numberFormat
/* 206 */                 .format(this.totalDamageDealt) + DESCRIPTIONS[4] + DESCRIPTIONS[5] + numberFormat
/* 207 */                 .format(this.globalDamageDealt) + DESCRIPTIONS[6] + DESCRIPTIONS[7]);
/*     */           } 
/* 209 */           this.roomEventText.updateDialogOption(0, OPTIONS[2]);
/*     */         } 
/*     */         return;
/*     */       case DEATH:
/* 213 */         AbstractDungeon.player.isDying = true;
/* 214 */         this.hasFocus = false;
/* 215 */         this.roomEventText.hide();
/* 216 */         AbstractDungeon.player.isDead = true;
/* 217 */         AbstractDungeon.deathScreen = new DeathScreen(null);
/*     */         return;
/*     */       case GO_TO_ENDING:
/* 220 */         this.roomEventText.clear();
/* 221 */         this.hasFocus = false;
/* 222 */         this.roomEventText.hide();
/* 223 */         goToFinalAct();
/*     */         return;
/*     */     } 
/* 226 */     logger.info("WHY YOU CALLED?"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 233 */     sb.setColor(this.fadeColor);
/* 234 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 235 */     if (this.npc != null) {
/* 236 */       this.npc.render(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 242 */     super.dispose();
/* 243 */     if (this.npc != null) {
/* 244 */       this.npc.dispose();
/* 245 */       this.npc = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\events\beyond\SpireHeart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */