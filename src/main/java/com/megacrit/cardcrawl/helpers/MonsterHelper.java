/*      */ package com.megacrit.cardcrawl.helpers;
/*      */ 
/*      */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.MysteriousSphere;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.metrics.BotDataUploader;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MonsterHelper
/*      */ {
/*   84 */   private static final Logger logger = LogManager.getLogger(MonsterHelper.class.getName());
/*      */ 
/*      */   
/*   87 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RunHistoryMonsterNames");
/*   88 */   public static final String[] MIXED_COMBAT_NAMES = uiStrings.TEXT;
/*      */   
/*      */   public static final String BLUE_SLAVER_ENC = "Blue Slaver";
/*      */   
/*      */   public static final String CULTIST_ENC = "Cultist";
/*      */   
/*      */   public static final String JAW_WORM_ENC = "Jaw Worm";
/*      */   
/*      */   public static final String LOOTER_ENC = "Looter";
/*      */   
/*      */   public static final String TWO_LOUSE_ENC = "2 Louse";
/*      */   
/*      */   public static final String SMALL_SLIMES_ENC = "Small Slimes";
/*      */   
/*      */   public static final String GREMLIN_GANG_ENC = "Gremlin Gang";
/*      */   
/*      */   public static final String RED_SLAVER_ENC = "Red Slaver";
/*      */   
/*      */   public static final String LARGE_SLIME_ENC = "Large Slime";
/*      */   
/*      */   public static final String LVL_1_THUGS_ENC = "Exordium Thugs";
/*      */   
/*      */   public static final String LVL_1_WILDLIFE_ENC = "Exordium Wildlife";
/*      */   
/*      */   public static final String THREE_LOUSE_ENC = "3 Louse";
/*      */   
/*      */   public static final String TWO_FUNGI_ENC = "2 Fungi Beasts";
/*      */   
/*      */   public static final String LOTS_OF_SLIMES_ENC = "Lots of Slimes";
/*      */   
/*      */   public static final String GREMLIN_NOB_ENC = "Gremlin Nob";
/*      */   
/*      */   public static final String LAGAVULIN_ENC = "Lagavulin";
/*      */   
/*      */   public static final String THREE_SENTRY_ENC = "3 Sentries";
/*      */   
/*      */   public static final String LAGAVULIN_EVENT_ENC = "Lagavulin Event";
/*      */   
/*      */   public static final String MUSHROOMS_EVENT_ENC = "The Mushroom Lair";
/*      */   
/*      */   public static final String GUARDIAN_ENC = "The Guardian";
/*      */   
/*      */   public static final String HEXAGHOST_ENC = "Hexaghost";
/*      */   
/*      */   public static final String SLIME_BOSS_ENC = "Slime Boss";
/*      */   
/*      */   public static final String TWO_THIEVES_ENC = "2 Thieves";
/*      */   
/*      */   public static final String THREE_BYRDS_ENC = "3 Byrds";
/*      */   
/*      */   public static final String CHOSEN_ENC = "Chosen";
/*      */   
/*      */   public static final String SHELL_PARASITE_ENC = "Shell Parasite";
/*      */   
/*      */   public static final String SPHERE_GUARDIAN_ENC = "Spheric Guardian";
/*      */   
/*      */   public static final String CULTIST_CHOSEN_ENC = "Cultist and Chosen";
/*      */   
/*      */   public static final String THREE_CULTISTS_ENC = "3 Cultists";
/*      */   
/*      */   public static final String FOUR_BYRDS_ENC = "4 Byrds";
/*      */   
/*      */   public static final String CHOSEN_FLOCK_ENC = "Chosen and Byrds";
/*      */   
/*      */   public static final String SENTRY_SPHERE_ENC = "Sentry and Sphere";
/*      */   
/*      */   public static final String SNAKE_PLANT_ENC = "Snake Plant";
/*      */   
/*      */   public static final String SNECKO_ENC = "Snecko";
/*      */   
/*      */   public static final String TANK_HEALER_ENC = "Centurion and Healer";
/*      */   public static final String PARASITE_AND_FUNGUS = "Shelled Parasite and Fungi";
/*      */   public static final String STAB_BOOK_ENC = "Book of Stabbing";
/*      */   public static final String GREMLIN_LEADER_ENC = "Gremlin Leader";
/*      */   public static final String SLAVERS_ENC = "Slavers";
/*      */   public static final String MASKED_BANDITS_ENC = "Masked Bandits";
/*      */   public static final String COLOSSEUM_SLAVER_ENC = "Colosseum Slavers";
/*      */   public static final String COLOSSEUM_NOB_ENC = "Colosseum Nobs";
/*      */   public static final String AUTOMATON_ENC = "Automaton";
/*      */   public static final String CHAMP_ENC = "Champ";
/*      */   public static final String COLLECTOR_ENC = "Collector";
/*      */   public static final String THREE_DARKLINGS_ENC = "3 Darklings";
/*      */   public static final String THREE_SHAPES_ENC = "3 Shapes";
/*      */   public static final String ORB_WALKER_ENC = "Orb Walker";
/*      */   public static final String TRANSIENT_ENC = "Transient";
/*      */   public static final String REPTOMANCER_ENC = "Reptomancer";
/*      */   public static final String SPIRE_GROWTH_ENC = "Spire Growth";
/*      */   public static final String MAW_ENC = "Maw";
/*      */   public static final String FOUR_SHAPES_ENC = "4 Shapes";
/*      */   public static final String SPHERE_TWO_SHAPES_ENC = "Sphere and 2 Shapes";
/*      */   public static final String JAW_WORMS_HORDE = "Jaw Worm Horde";
/*      */   public static final String SNECKO_WITH_MYSTICS = "Snecko and Mystics";
/*      */   public static final String WRITHING_MASS_ENC = "Writhing Mass";
/*      */   public static final String TWO_ORB_WALKER_ENC = "2 Orb Walkers";
/*      */   public static final String NEMESIS_ENC = "Nemesis";
/*      */   public static final String GIANT_HEAD_ENC = "Giant Head";
/*      */   public static final String MYSTERIOUS_SPHERE_ENC = "Mysterious Sphere";
/*      */   public static final String MIND_BLOOM_BOSS = "Mind Bloom Boss Battle";
/*      */   public static final String TIME_EATER_ENC = "Time Eater";
/*      */   public static final String AWAKENED_ENC = "Awakened One";
/*      */   public static final String DONU_DECA_ENC = "Donu and Deca";
/*      */   public static final String THE_HEART_ENC = "The Heart";
/*      */   public static final String SHIELD_SPEAR_ENC = "Shield and Spear";
/*      */   public static final String EYES_ENC = "The Eyes";
/*      */   public static final String APOLOGY_SLIME_ENC = "Apologetic Slime";
/*      */   public static final String OLD_REPTO_ONE_ENC = "Flame Bruiser 1 Orb";
/*      */   public static final String OLD_REPTO_TWO_ENC = "Flame Bruiser 2 Orb";
/*      */   public static final String OLD_SLAVER_PARASITE = "Slaver and Parasite";
/*      */   public static final String OLD_SNECKO_MYSTICS = "Snecko and Mystics";
/*      */   
/*      */   public static String getEncounterName(String key) {
/*  199 */     if (key == null) {
/*  200 */       return "";
/*      */     }
/*      */     
/*  203 */     switch (key) {
/*      */       case "Flame Bruiser 1 Orb":
/*      */       case "Flame Bruiser 2 Orb":
/*  206 */         return MIXED_COMBAT_NAMES[25];
/*      */       case "Slaver and Parasite":
/*  208 */         return MIXED_COMBAT_NAMES[26];
/*      */       case "Snecko and Mystics":
/*  210 */         return MIXED_COMBAT_NAMES[27];
/*      */     } 
/*      */     
/*  213 */     switch (key) {
/*      */       case "Blue Slaver":
/*  215 */         return SlaverBlue.NAME;
/*      */       case "Cultist":
/*  217 */         return Cultist.NAME;
/*      */       case "Jaw Worm":
/*  219 */         return JawWorm.NAME;
/*      */       case "Looter":
/*  221 */         return Looter.NAME;
/*      */       case "Gremlin Gang":
/*  223 */         return MIXED_COMBAT_NAMES[0];
/*      */       case "Red Slaver":
/*  225 */         return SlaverRed.NAME;
/*      */       case "Large Slime":
/*  227 */         return MIXED_COMBAT_NAMES[1];
/*      */       case "Exordium Thugs":
/*  229 */         return MIXED_COMBAT_NAMES[2];
/*      */       case "Exordium Wildlife":
/*  231 */         return MIXED_COMBAT_NAMES[3];
/*      */       case "3 Louse":
/*  233 */         return LouseNormal.NAME;
/*      */       case "2 Louse":
/*  235 */         return LouseNormal.NAME;
/*      */       case "2 Fungi Beasts":
/*  237 */         return FungiBeast.NAME;
/*      */       case "Lots of Slimes":
/*  239 */         return MIXED_COMBAT_NAMES[4];
/*      */       case "Small Slimes":
/*  241 */         return MIXED_COMBAT_NAMES[5];
/*      */       case "Gremlin Nob":
/*  243 */         return GremlinNob.NAME;
/*      */       case "Lagavulin":
/*  245 */         return Lagavulin.NAME;
/*      */       case "3 Sentries":
/*  247 */         return MIXED_COMBAT_NAMES[23];
/*      */       case "Lagavulin Event":
/*  249 */         return Lagavulin.NAME;
/*      */       case "The Mushroom Lair":
/*  251 */         return FungiBeast.NAME;
/*      */       case "The Guardian":
/*  253 */         return TheGuardian.NAME;
/*      */       case "Hexaghost":
/*  255 */         return Hexaghost.NAME;
/*      */       case "Slime Boss":
/*  257 */         return SlimeBoss.NAME;
/*      */     } 
/*      */ 
/*      */     
/*  261 */     switch (key) {
/*      */       case "2 Thieves":
/*  263 */         return MIXED_COMBAT_NAMES[6];
/*      */       case "3 Byrds":
/*  265 */         return MIXED_COMBAT_NAMES[7];
/*      */       case "4 Byrds":
/*  267 */         return MIXED_COMBAT_NAMES[8];
/*      */       case "Chosen":
/*  269 */         return Chosen.NAME;
/*      */       case "Shell Parasite":
/*  271 */         return ShelledParasite.NAME;
/*      */       case "Spheric Guardian":
/*  273 */         return SphericGuardian.NAME;
/*      */       case "Cultist and Chosen":
/*  275 */         return MIXED_COMBAT_NAMES[24];
/*      */       case "3 Cultists":
/*  277 */         return MIXED_COMBAT_NAMES[9];
/*      */       case "Chosen and Byrds":
/*  279 */         return MIXED_COMBAT_NAMES[10];
/*      */       case "Sentry and Sphere":
/*  281 */         return MIXED_COMBAT_NAMES[11];
/*      */       case "Snake Plant":
/*  283 */         return SnakePlant.NAME;
/*      */       case "Snecko":
/*  285 */         return Snecko.NAME;
/*      */       case "Centurion and Healer":
/*  287 */         return MIXED_COMBAT_NAMES[12];
/*      */       case "Shelled Parasite and Fungi":
/*  289 */         return MIXED_COMBAT_NAMES[13];
/*      */       case "Book of Stabbing":
/*  291 */         return BookOfStabbing.NAME;
/*      */       case "Gremlin Leader":
/*  293 */         return GremlinLeader.NAME;
/*      */       case "Slavers":
/*  295 */         return Taskmaster.NAME;
/*      */       case "Masked Bandits":
/*  297 */         return MIXED_COMBAT_NAMES[14];
/*      */       case "Colosseum Nobs":
/*  299 */         return MIXED_COMBAT_NAMES[15];
/*      */       case "Colosseum Slavers":
/*  301 */         return MIXED_COMBAT_NAMES[16];
/*      */       case "Automaton":
/*  303 */         return BronzeAutomaton.NAME;
/*      */       case "Champ":
/*  305 */         return Champ.NAME;
/*      */       case "Collector":
/*  307 */         return TheCollector.NAME;
/*      */     } 
/*      */ 
/*      */     
/*  311 */     switch (key) {
/*      */       case "Reptomancer":
/*  313 */         return Reptomancer.NAME;
/*      */       case "Transient":
/*  315 */         return Transient.NAME;
/*      */       case "3 Darklings":
/*  317 */         return Darkling.NAME;
/*      */       case "3 Shapes":
/*  319 */         return MIXED_COMBAT_NAMES[17];
/*      */       case "Jaw Worm Horde":
/*  321 */         return MIXED_COMBAT_NAMES[18];
/*      */       case "Orb Walker":
/*  323 */         return OrbWalker.NAME;
/*      */       case "Spire Growth":
/*  325 */         return SpireGrowth.NAME;
/*      */       case "Maw":
/*  327 */         return Maw.NAME;
/*      */       case "4 Shapes":
/*  329 */         return MIXED_COMBAT_NAMES[19];
/*      */       case "Sphere and 2 Shapes":
/*  331 */         return MIXED_COMBAT_NAMES[20];
/*      */       case "2 Orb Walkers":
/*  333 */         return MIXED_COMBAT_NAMES[21];
/*      */       case "Nemesis":
/*  335 */         return Nemesis.NAME;
/*      */       case "Writhing Mass":
/*  337 */         return WrithingMass.NAME;
/*      */       case "Giant Head":
/*  339 */         return GiantHead.NAME;
/*      */       case "Mysterious Sphere":
/*  341 */         return MysteriousSphere.NAME;
/*      */       case "Time Eater":
/*  343 */         return TimeEater.NAME;
/*      */       case "Awakened One":
/*  345 */         return AwakenedOne.NAME;
/*      */       case "Donu and Deca":
/*  347 */         return MIXED_COMBAT_NAMES[22];
/*      */     } 
/*      */ 
/*      */     
/*  351 */     switch (key) {
/*      */       case "The Heart":
/*  353 */         return CorruptHeart.NAME;
/*      */       case "Shield and Spear":
/*  355 */         return MIXED_COMBAT_NAMES[28];
/*      */     } 
/*  357 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static MonsterGroup getEncounter(String key) {
/*  369 */     switch (key) {
/*      */       case "Blue Slaver":
/*  371 */         return new MonsterGroup((AbstractMonster)new SlaverBlue(0.0F, 0.0F));
/*      */       case "Cultist":
/*  373 */         return new MonsterGroup((AbstractMonster)new Cultist(0.0F, -10.0F));
/*      */       case "Jaw Worm":
/*  375 */         return new MonsterGroup((AbstractMonster)new JawWorm(0.0F, 25.0F));
/*      */       case "Looter":
/*  377 */         return new MonsterGroup((AbstractMonster)new Looter(0.0F, 0.0F));
/*      */       case "Gremlin Gang":
/*  379 */         return spawnGremlins();
/*      */       case "Red Slaver":
/*  381 */         return new MonsterGroup((AbstractMonster)new SlaverRed(0.0F, 0.0F));
/*      */       case "Large Slime":
/*  383 */         if (AbstractDungeon.miscRng.randomBoolean()) {
/*  384 */           return new MonsterGroup((AbstractMonster)new AcidSlime_L(0.0F, 0.0F));
/*      */         }
/*  386 */         return new MonsterGroup((AbstractMonster)new SpikeSlime_L(0.0F, 0.0F));
/*      */       
/*      */       case "Exordium Thugs":
/*  389 */         return bottomHumanoid();
/*      */       case "Exordium Wildlife":
/*  391 */         return bottomWildlife();
/*      */       case "3 Louse":
/*  393 */         return new MonsterGroup(new AbstractMonster[] {
/*  394 */               getLouse(-350.0F, 25.0F), getLouse(-125.0F, 10.0F), getLouse(80.0F, 30.0F) });
/*      */       case "2 Louse":
/*  396 */         return new MonsterGroup(new AbstractMonster[] { getLouse(-200.0F, 10.0F), getLouse(80.0F, 30.0F) });
/*      */       case "2 Fungi Beasts":
/*  398 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new FungiBeast(-400.0F, 30.0F), (AbstractMonster)new FungiBeast(-40.0F, 20.0F) });
/*      */       
/*      */       case "Lots of Slimes":
/*  401 */         return spawnManySmallSlimes();
/*      */       case "Small Slimes":
/*  403 */         return spawnSmallSlimes();
/*      */       case "Gremlin Nob":
/*  405 */         return new MonsterGroup((AbstractMonster)new GremlinNob(0.0F, 0.0F));
/*      */       case "Lagavulin":
/*  407 */         return new MonsterGroup((AbstractMonster)new Lagavulin(true));
/*      */       case "3 Sentries":
/*  409 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Sentry(-330.0F, 25.0F), (AbstractMonster)new Sentry(-85.0F, 10.0F), (AbstractMonster)new Sentry(140.0F, 30.0F) });
/*      */       
/*      */       case "Lagavulin Event":
/*  412 */         return new MonsterGroup((AbstractMonster)new Lagavulin(false));
/*      */       case "The Mushroom Lair":
/*  414 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new FungiBeast(-450.0F, 30.0F), (AbstractMonster)new FungiBeast(-145.0F, 20.0F), (AbstractMonster)new FungiBeast(180.0F, 15.0F) });
/*      */ 
/*      */ 
/*      */       
/*      */       case "The Guardian":
/*  419 */         return new MonsterGroup((AbstractMonster)new TheGuardian());
/*      */       case "Hexaghost":
/*  421 */         return new MonsterGroup((AbstractMonster)new Hexaghost());
/*      */       case "Slime Boss":
/*  423 */         return new MonsterGroup((AbstractMonster)new SlimeBoss());
/*      */     } 
/*      */ 
/*      */     
/*  427 */     switch (key) {
/*      */       case "2 Thieves":
/*  429 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Looter(-200.0F, 15.0F), (AbstractMonster)new Mugger(80.0F, 0.0F) });
/*      */       case "3 Byrds":
/*  431 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Byrd(-360.0F, 
/*  432 */                 MathUtils.random(25.0F, 70.0F)), (AbstractMonster)new Byrd(-80.0F, 
/*      */                 
/*  434 */                 MathUtils.random(25.0F, 70.0F)), (AbstractMonster)new Byrd(200.0F, MathUtils.random(25.0F, 70.0F)) });
/*      */       case "4 Byrds":
/*  436 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Byrd(-470.0F, 
/*  437 */                 MathUtils.random(25.0F, 70.0F)), (AbstractMonster)new Byrd(-210.0F, 
/*      */                 
/*  439 */                 MathUtils.random(25.0F, 70.0F)), (AbstractMonster)new Byrd(50.0F, MathUtils.random(25.0F, 70.0F)), (AbstractMonster)new Byrd(310.0F, 
/*      */                 
/*  441 */                 MathUtils.random(25.0F, 70.0F)) });
/*      */       case "Chosen":
/*  443 */         return new MonsterGroup((AbstractMonster)new Chosen());
/*      */       case "Shell Parasite":
/*  445 */         return new MonsterGroup((AbstractMonster)new ShelledParasite());
/*      */       case "Spheric Guardian":
/*  447 */         return new MonsterGroup((AbstractMonster)new SphericGuardian());
/*      */       case "Cultist and Chosen":
/*  449 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Cultist(-230.0F, 15.0F, false), (AbstractMonster)new Chosen(100.0F, 25.0F) });
/*      */       case "3 Cultists":
/*  451 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Cultist(-465.0F, -20.0F, false), (AbstractMonster)new Cultist(-130.0F, 15.0F, false), (AbstractMonster)new Cultist(200.0F, -5.0F) });
/*      */ 
/*      */ 
/*      */       
/*      */       case "Chosen and Byrds":
/*  456 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Byrd(-170.0F, 
/*  457 */                 MathUtils.random(25.0F, 70.0F)), (AbstractMonster)new Chosen(80.0F, 0.0F) });
/*      */       case "Sentry and Sphere":
/*  459 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Sentry(-305.0F, 30.0F), (AbstractMonster)new SphericGuardian() });
/*      */       case "Snake Plant":
/*  461 */         return new MonsterGroup((AbstractMonster)new SnakePlant(-30.0F, -30.0F));
/*      */       case "Snecko":
/*  463 */         return new MonsterGroup((AbstractMonster)new Snecko());
/*      */       case "Centurion and Healer":
/*  465 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Centurion(-200.0F, 15.0F), (AbstractMonster)new Healer(120.0F, 0.0F) });
/*      */       case "Shelled Parasite and Fungi":
/*  467 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new ShelledParasite(-260.0F, 15.0F), (AbstractMonster)new FungiBeast(120.0F, 0.0F) });
/*      */       
/*      */       case "Book of Stabbing":
/*  470 */         return new MonsterGroup((AbstractMonster)new BookOfStabbing());
/*      */       case "Gremlin Leader":
/*  472 */         return new MonsterGroup(new AbstractMonster[] {
/*  473 */               spawnGremlin(GremlinLeader.POSX[0], GremlinLeader.POSY[0]), spawnGremlin(GremlinLeader.POSX[1], GremlinLeader.POSY[1]), (AbstractMonster)new GremlinLeader()
/*      */             });
/*      */       
/*      */       case "Slavers":
/*  477 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new SlaverBlue(-385.0F, -15.0F), (AbstractMonster)new Taskmaster(-133.0F, 0.0F), (AbstractMonster)new SlaverRed(125.0F, -30.0F) });
/*      */ 
/*      */ 
/*      */       
/*      */       case "Masked Bandits":
/*  482 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new BanditPointy(-320.0F, 0.0F), (AbstractMonster)new BanditLeader(-75.0F, -6.0F), (AbstractMonster)new BanditBear(150.0F, -6.0F) });
/*      */ 
/*      */ 
/*      */       
/*      */       case "Colosseum Nobs":
/*  487 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Taskmaster(-270.0F, 15.0F), (AbstractMonster)new GremlinNob(130.0F, 0.0F) });
/*      */       case "Colosseum Slavers":
/*  489 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new SlaverBlue(-270.0F, 15.0F), (AbstractMonster)new SlaverRed(130.0F, 0.0F) });
/*      */       case "Automaton":
/*  491 */         return new MonsterGroup((AbstractMonster)new BronzeAutomaton());
/*      */       case "Champ":
/*  493 */         return new MonsterGroup((AbstractMonster)new Champ());
/*      */       case "Collector":
/*  495 */         return new MonsterGroup((AbstractMonster)new TheCollector());
/*      */     } 
/*      */ 
/*      */     
/*  499 */     switch (key) {
/*      */       case "Flame Bruiser 1 Orb":
/*  501 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Reptomancer(), (AbstractMonster)new SnakeDagger(Reptomancer.POSX[0], Reptomancer.POSY[0]) });
/*      */ 
/*      */ 
/*      */       
/*      */       case "Flame Bruiser 2 Orb":
/*      */       case "Reptomancer":
/*  507 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new SnakeDagger(Reptomancer.POSX[1], Reptomancer.POSY[1]), (AbstractMonster)new Reptomancer(), (AbstractMonster)new SnakeDagger(Reptomancer.POSX[0], Reptomancer.POSY[0]) });
/*      */ 
/*      */       
/*      */       case "Transient":
/*  511 */         return new MonsterGroup((AbstractMonster)new Transient());
/*      */       case "3 Darklings":
/*  513 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Darkling(-440.0F, 10.0F), (AbstractMonster)new Darkling(-140.0F, 30.0F), (AbstractMonster)new Darkling(180.0F, -5.0F) });
/*      */ 
/*      */ 
/*      */       
/*      */       case "3 Shapes":
/*  518 */         return spawnShapes(true);
/*      */       case "Jaw Worm Horde":
/*  520 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new JawWorm(-490.0F, -5.0F, true), (AbstractMonster)new JawWorm(-150.0F, 20.0F, true), (AbstractMonster)new JawWorm(175.0F, 5.0F, true) });
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case "Snecko and Mystics":
/*  526 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Healer(-475.0F, -10.0F), (AbstractMonster)new Snecko(-130.0F, -13.0F), (AbstractMonster)new Healer(175.0F, -10.0F) });
/*      */       
/*      */       case "Orb Walker":
/*  529 */         return new MonsterGroup((AbstractMonster)new OrbWalker(-30.0F, 20.0F));
/*      */       case "Spire Growth":
/*  531 */         return new MonsterGroup((AbstractMonster)new SpireGrowth());
/*      */       case "Maw":
/*  533 */         return new MonsterGroup((AbstractMonster)new Maw(-70.0F, 20.0F));
/*      */       case "4 Shapes":
/*  535 */         return spawnShapes(false);
/*      */       case "Sphere and 2 Shapes":
/*  537 */         return new MonsterGroup(new AbstractMonster[] {
/*  538 */               getAncientShape(-435.0F, 10.0F), getAncientShape(-210.0F, 0.0F), (AbstractMonster)new SphericGuardian(110.0F, 10.0F)
/*      */             });
/*      */       case "2 Orb Walkers":
/*  541 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new OrbWalker(-250.0F, 32.0F), (AbstractMonster)new OrbWalker(150.0F, 26.0F) });
/*      */       case "Nemesis":
/*  543 */         return new MonsterGroup((AbstractMonster)new Nemesis());
/*      */       case "Writhing Mass":
/*  545 */         return new MonsterGroup((AbstractMonster)new WrithingMass());
/*      */       case "Giant Head":
/*  547 */         return new MonsterGroup((AbstractMonster)new GiantHead());
/*      */       case "Mysterious Sphere":
/*  549 */         return new MonsterGroup(new AbstractMonster[] {
/*  550 */               getAncientShape(-475.0F, 10.0F), getAncientShape(-250.0F, 0.0F), (AbstractMonster)new OrbWalker(150.0F, 30.0F)
/*      */             });
/*      */       
/*      */       case "Time Eater":
/*  554 */         return new MonsterGroup((AbstractMonster)new TimeEater());
/*      */       case "Awakened One":
/*  556 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Cultist(-590.0F, 10.0F, false), (AbstractMonster)new Cultist(-298.0F, -10.0F, false), (AbstractMonster)new AwakenedOne(100.0F, 15.0F) });
/*      */ 
/*      */       
/*      */       case "Donu and Deca":
/*  560 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new Deca(), (AbstractMonster)new Donu() });
/*      */     } 
/*      */ 
/*      */     
/*  564 */     switch (key) {
/*      */       case "The Heart":
/*  566 */         return new MonsterGroup((AbstractMonster)new CorruptHeart());
/*      */       case "Shield and Spear":
/*  568 */         return new MonsterGroup(new AbstractMonster[] { (AbstractMonster)new SpireShield(), (AbstractMonster)new SpireSpear() });
/*      */     } 
/*      */     
/*  571 */     return new MonsterGroup((AbstractMonster)new ApologySlime());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static float randomYOffset(float y) {
/*  581 */     return y + MathUtils.random(-20.0F, 20.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static float randomXOffset(float x) {
/*  591 */     return x + MathUtils.random(-20.0F, 20.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static AbstractMonster getGremlin(String key, float xPos, float yPos) {
/*  602 */     switch (key) {
/*      */       case "GremlinWarrior":
/*  604 */         return (AbstractMonster)new GremlinWarrior(xPos, yPos);
/*      */       case "GremlinThief":
/*  606 */         return (AbstractMonster)new GremlinThief(xPos, yPos);
/*      */       case "GremlinFat":
/*  608 */         return (AbstractMonster)new GremlinFat(xPos, yPos);
/*      */       case "GremlinTsundere":
/*  610 */         return (AbstractMonster)new GremlinTsundere(xPos, yPos);
/*      */       case "GremlinWizard":
/*  612 */         return (AbstractMonster)new GremlinWizard(xPos, yPos);
/*      */     } 
/*  614 */     logger.info("UNKNOWN GREMLIN: " + key);
/*  615 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static AbstractMonster getAncientShape(float x, float y) {
/*  620 */     switch (AbstractDungeon.miscRng.random(2)) {
/*      */       case 0:
/*  622 */         return (AbstractMonster)new Spiker(x, y);
/*      */       case 1:
/*  624 */         return (AbstractMonster)new Repulsor(x, y);
/*      */     } 
/*  626 */     return (AbstractMonster)new Exploder(x, y);
/*      */   }
/*      */ 
/*      */   
/*      */   public static AbstractMonster getShape(String key, float xPos, float yPos) {
/*  631 */     switch (key) {
/*      */       case "Repulsor":
/*  633 */         return (AbstractMonster)new Repulsor(xPos, yPos);
/*      */       case "Spiker":
/*  635 */         return (AbstractMonster)new Spiker(xPos, yPos);
/*      */       case "Exploder":
/*  637 */         return (AbstractMonster)new Exploder(xPos, yPos);
/*      */     } 
/*  639 */     logger.info("UNKNOWN SHAPE: " + key);
/*  640 */     return null;
/*      */   }
/*      */   
/*      */   private static MonsterGroup spawnShapes(boolean weak) {
/*      */     AbstractMonster[] retVal;
/*  645 */     ArrayList<String> shapePool = new ArrayList<>();
/*  646 */     shapePool.add("Repulsor");
/*  647 */     shapePool.add("Repulsor");
/*  648 */     shapePool.add("Exploder");
/*  649 */     shapePool.add("Exploder");
/*  650 */     shapePool.add("Spiker");
/*  651 */     shapePool.add("Spiker");
/*      */ 
/*      */ 
/*      */     
/*  655 */     if (weak) {
/*  656 */       retVal = new AbstractMonster[3];
/*      */     } else {
/*  658 */       retVal = new AbstractMonster[4];
/*      */     } 
/*      */ 
/*      */     
/*  662 */     int index = AbstractDungeon.miscRng.random(shapePool.size() - 1);
/*  663 */     String key = shapePool.get(index);
/*  664 */     shapePool.remove(index);
/*  665 */     retVal[0] = getShape(key, -480.0F, 6.0F);
/*      */ 
/*      */     
/*  668 */     index = AbstractDungeon.miscRng.random(shapePool.size() - 1);
/*  669 */     key = shapePool.get(index);
/*  670 */     shapePool.remove(index);
/*  671 */     retVal[1] = getShape(key, -240.0F, -6.0F);
/*      */ 
/*      */     
/*  674 */     index = AbstractDungeon.miscRng.random(shapePool.size() - 1);
/*  675 */     key = shapePool.get(index);
/*  676 */     shapePool.remove(index);
/*  677 */     retVal[2] = getShape(key, 0.0F, -12.0F);
/*      */     
/*  679 */     if (!weak) {
/*      */       
/*  681 */       index = AbstractDungeon.miscRng.random(shapePool.size() - 1);
/*  682 */       key = shapePool.get(index);
/*  683 */       shapePool.remove(index);
/*  684 */       retVal[3] = getShape(key, 240.0F, 12.0F);
/*      */     } 
/*      */     
/*  687 */     return new MonsterGroup(retVal);
/*      */   }
/*      */   
/*      */   private static MonsterGroup spawnSmallSlimes() {
/*  691 */     AbstractMonster[] retVal = new AbstractMonster[2];
/*      */ 
/*      */     
/*  694 */     if (AbstractDungeon.miscRng.randomBoolean()) {
/*  695 */       retVal[0] = (AbstractMonster)new SpikeSlime_S(-230.0F, 32.0F, 0);
/*  696 */       retVal[1] = (AbstractMonster)new AcidSlime_M(35.0F, 8.0F);
/*      */     } else {
/*  698 */       retVal[0] = (AbstractMonster)new AcidSlime_S(-230.0F, 32.0F, 0);
/*  699 */       retVal[1] = (AbstractMonster)new SpikeSlime_M(35.0F, 8.0F);
/*      */     } 
/*      */     
/*  702 */     return new MonsterGroup(retVal);
/*      */   }
/*      */ 
/*      */   
/*      */   private static MonsterGroup spawnManySmallSlimes() {
/*  707 */     ArrayList<String> slimePool = new ArrayList<>();
/*  708 */     slimePool.add("SpikeSlime_S");
/*  709 */     slimePool.add("SpikeSlime_S");
/*  710 */     slimePool.add("SpikeSlime_S");
/*  711 */     slimePool.add("AcidSlime_S");
/*  712 */     slimePool.add("AcidSlime_S");
/*      */     
/*  714 */     AbstractMonster[] retVal = new AbstractMonster[5];
/*      */     
/*  716 */     int index = AbstractDungeon.miscRng.random(slimePool.size() - 1);
/*  717 */     String key = slimePool.get(index);
/*  718 */     slimePool.remove(index);
/*      */ 
/*      */     
/*  721 */     if (key.equals("SpikeSlime_S")) {
/*  722 */       retVal[0] = (AbstractMonster)new SpikeSlime_S(-480.0F, 30.0F, 0);
/*      */     } else {
/*  724 */       retVal[0] = (AbstractMonster)new AcidSlime_S(-480.0F, 30.0F, 0);
/*      */     } 
/*      */     
/*  727 */     index = AbstractDungeon.miscRng.random(slimePool.size() - 1);
/*  728 */     key = slimePool.get(index);
/*  729 */     slimePool.remove(index);
/*      */ 
/*      */     
/*  732 */     if (key.equals("SpikeSlime_S")) {
/*  733 */       retVal[1] = (AbstractMonster)new SpikeSlime_S(-320.0F, 2.0F, 0);
/*      */     } else {
/*  735 */       retVal[1] = (AbstractMonster)new AcidSlime_S(-320.0F, 2.0F, 0);
/*      */     } 
/*      */     
/*  738 */     index = AbstractDungeon.miscRng.random(slimePool.size() - 1);
/*  739 */     key = slimePool.get(index);
/*  740 */     slimePool.remove(index);
/*      */ 
/*      */     
/*  743 */     if (key.equals("SpikeSlime_S")) {
/*  744 */       retVal[2] = (AbstractMonster)new SpikeSlime_S(-160.0F, 32.0F, 0);
/*      */     } else {
/*  746 */       retVal[2] = (AbstractMonster)new AcidSlime_S(-160.0F, 32.0F, 0);
/*      */     } 
/*      */     
/*  749 */     index = AbstractDungeon.miscRng.random(slimePool.size() - 1);
/*  750 */     key = slimePool.get(index);
/*  751 */     slimePool.remove(index);
/*  752 */     if (key.equals("SpikeSlime_S")) {
/*  753 */       retVal[3] = (AbstractMonster)new SpikeSlime_S(10.0F, -12.0F, 0);
/*      */     } else {
/*  755 */       retVal[3] = (AbstractMonster)new AcidSlime_S(10.0F, -12.0F, 0);
/*      */     } 
/*      */     
/*  758 */     index = AbstractDungeon.miscRng.random(slimePool.size() - 1);
/*  759 */     key = slimePool.get(index);
/*  760 */     slimePool.remove(index);
/*  761 */     if (key.equals("SpikeSlime_S")) {
/*  762 */       retVal[4] = (AbstractMonster)new SpikeSlime_S(200.0F, 9.0F, 0);
/*      */     } else {
/*  764 */       retVal[4] = (AbstractMonster)new AcidSlime_S(200.0F, 9.0F, 0);
/*      */     } 
/*      */     
/*  767 */     return new MonsterGroup(retVal);
/*      */   }
/*      */   
/*      */   private static MonsterGroup spawnGremlins() {
/*  771 */     ArrayList<String> gremlinPool = new ArrayList<>();
/*  772 */     gremlinPool.add("GremlinWarrior");
/*  773 */     gremlinPool.add("GremlinWarrior");
/*  774 */     gremlinPool.add("GremlinThief");
/*  775 */     gremlinPool.add("GremlinThief");
/*  776 */     gremlinPool.add("GremlinFat");
/*  777 */     gremlinPool.add("GremlinFat");
/*  778 */     gremlinPool.add("GremlinTsundere");
/*  779 */     gremlinPool.add("GremlinWizard");
/*      */     
/*  781 */     AbstractMonster[] retVal = new AbstractMonster[4];
/*      */ 
/*      */     
/*  784 */     int index = AbstractDungeon.miscRng.random(gremlinPool.size() - 1);
/*  785 */     String key = gremlinPool.get(index);
/*  786 */     gremlinPool.remove(index);
/*  787 */     retVal[0] = getGremlin(key, -320.0F, 25.0F);
/*      */ 
/*      */     
/*  790 */     index = AbstractDungeon.miscRng.random(gremlinPool.size() - 1);
/*  791 */     key = gremlinPool.get(index);
/*  792 */     gremlinPool.remove(index);
/*  793 */     retVal[1] = getGremlin(key, -160.0F, -12.0F);
/*      */ 
/*      */     
/*  796 */     index = AbstractDungeon.miscRng.random(gremlinPool.size() - 1);
/*  797 */     key = gremlinPool.get(index);
/*  798 */     gremlinPool.remove(index);
/*  799 */     retVal[2] = getGremlin(key, 25.0F, -35.0F);
/*      */ 
/*      */     
/*  802 */     index = AbstractDungeon.miscRng.random(gremlinPool.size() - 1);
/*  803 */     key = gremlinPool.get(index);
/*  804 */     gremlinPool.remove(index);
/*  805 */     retVal[3] = getGremlin(key, 205.0F, 40.0F);
/*      */     
/*  807 */     return new MonsterGroup(retVal);
/*      */   }
/*      */   
/*      */   private static AbstractMonster spawnGremlin(float x, float y) {
/*  811 */     ArrayList<String> gremlinPool = new ArrayList<>();
/*  812 */     gremlinPool.add("GremlinWarrior");
/*  813 */     gremlinPool.add("GremlinWarrior");
/*  814 */     gremlinPool.add("GremlinThief");
/*  815 */     gremlinPool.add("GremlinThief");
/*  816 */     gremlinPool.add("GremlinFat");
/*  817 */     gremlinPool.add("GremlinFat");
/*  818 */     gremlinPool.add("GremlinTsundere");
/*  819 */     gremlinPool.add("GremlinWizard");
/*      */     
/*  821 */     return getGremlin(gremlinPool.get(AbstractDungeon.miscRng.random(0, gremlinPool.size() - 1)), x, y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static MonsterGroup bottomHumanoid() {
/*  830 */     AbstractMonster[] monsters = new AbstractMonster[2];
/*  831 */     monsters[0] = bottomGetWeakWildlife(randomXOffset(-160.0F), randomYOffset(20.0F));
/*  832 */     monsters[1] = bottomGetStrongHumanoid(randomXOffset(130.0F), randomYOffset(20.0F));
/*      */     
/*  834 */     return new MonsterGroup(monsters);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static MonsterGroup bottomWildlife() {
/*  843 */     int numMonster = 2;
/*  844 */     AbstractMonster[] monsters = new AbstractMonster[numMonster];
/*      */     
/*  846 */     if (numMonster == 2) {
/*  847 */       monsters[0] = bottomGetStrongWildlife(randomXOffset(-150.0F), randomYOffset(20.0F));
/*  848 */       monsters[1] = bottomGetWeakWildlife(randomXOffset(150.0F), randomYOffset(20.0F));
/*      */     
/*      */     }
/*  851 */     else if (numMonster == 3) {
/*  852 */       monsters[0] = bottomGetWeakWildlife(randomXOffset(-200.0F), randomYOffset(20.0F));
/*  853 */       monsters[1] = bottomGetWeakWildlife(randomXOffset(0.0F), randomYOffset(20.0F));
/*  854 */       monsters[2] = bottomGetWeakWildlife(randomXOffset(200.0F), randomYOffset(20.0F));
/*      */     } 
/*      */     
/*  857 */     return new MonsterGroup(monsters);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static AbstractMonster bottomGetStrongHumanoid(float x, float y) {
/*  868 */     ArrayList<AbstractMonster> monsters = new ArrayList<>();
/*  869 */     monsters.add(new Cultist(x, y));
/*  870 */     monsters.add(getSlaver(x, y));
/*  871 */     monsters.add(new Looter(x, y));
/*      */     
/*  873 */     AbstractMonster output = monsters.get(AbstractDungeon.miscRng.random(0, monsters.size() - 1));
/*  874 */     return output;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static AbstractMonster bottomGetStrongWildlife(float x, float y) {
/*  885 */     ArrayList<AbstractMonster> monsters = new ArrayList<>();
/*  886 */     monsters.add(new FungiBeast(x, y));
/*  887 */     monsters.add(new JawWorm(x, y));
/*      */     
/*  889 */     AbstractMonster output = monsters.get(AbstractDungeon.miscRng.random(0, monsters.size() - 1));
/*  890 */     return output;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static AbstractMonster bottomGetWeakWildlife(float x, float y) {
/*  901 */     ArrayList<AbstractMonster> monsters = new ArrayList<>();
/*  902 */     monsters.add(getLouse(x, y));
/*  903 */     monsters.add(new SpikeSlime_M(x, y));
/*  904 */     monsters.add(new AcidSlime_M(x, y));
/*      */     
/*  906 */     return monsters.get(AbstractDungeon.miscRng.random(0, monsters.size() - 1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static AbstractMonster getSlaver(float x, float y) {
/*  917 */     if (AbstractDungeon.miscRng.randomBoolean()) {
/*  918 */       return (AbstractMonster)new SlaverRed(x, y);
/*      */     }
/*  920 */     return (AbstractMonster)new SlaverBlue(x, y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static AbstractMonster getLouse(float x, float y) {
/*  932 */     if (AbstractDungeon.miscRng.randomBoolean()) {
/*  933 */       return (AbstractMonster)new LouseNormal(x, y);
/*      */     }
/*  935 */     return (AbstractMonster)new LouseDefensive(x, y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void uploadEnemyData() {
/*  943 */     ArrayList<String> derp = new ArrayList<>();
/*  944 */     ArrayList<EnemyData> data = new ArrayList<>();
/*      */ 
/*      */ 
/*      */     
/*  948 */     data.add(new EnemyData("Blue Slaver", 1, EnemyData.MonsterType.WEAK));
/*  949 */     data.add(new EnemyData("Cultist", 1, EnemyData.MonsterType.WEAK));
/*  950 */     data.add(new EnemyData("Jaw Worm", 1, EnemyData.MonsterType.WEAK));
/*  951 */     data.add(new EnemyData("2 Louse", 1, EnemyData.MonsterType.WEAK));
/*  952 */     data.add(new EnemyData("Small Slimes", 1, EnemyData.MonsterType.WEAK));
/*      */ 
/*      */     
/*  955 */     data.add(new EnemyData("Gremlin Gang", 1, EnemyData.MonsterType.STRONG));
/*  956 */     data.add(new EnemyData("Large Slime", 1, EnemyData.MonsterType.STRONG));
/*  957 */     data.add(new EnemyData("Looter", 1, EnemyData.MonsterType.STRONG));
/*  958 */     data.add(new EnemyData("Lots of Slimes", 1, EnemyData.MonsterType.STRONG));
/*  959 */     data.add(new EnemyData("Exordium Thugs", 1, EnemyData.MonsterType.STRONG));
/*  960 */     data.add(new EnemyData("Exordium Wildlife", 1, EnemyData.MonsterType.STRONG));
/*  961 */     data.add(new EnemyData("Red Slaver", 1, EnemyData.MonsterType.STRONG));
/*  962 */     data.add(new EnemyData("3 Louse", 1, EnemyData.MonsterType.STRONG));
/*  963 */     data.add(new EnemyData("2 Fungi Beasts", 1, EnemyData.MonsterType.STRONG));
/*      */ 
/*      */     
/*  966 */     data.add(new EnemyData("Gremlin Nob", 1, EnemyData.MonsterType.ELITE));
/*  967 */     data.add(new EnemyData("Lagavulin", 1, EnemyData.MonsterType.ELITE));
/*  968 */     data.add(new EnemyData("3 Sentries", 1, EnemyData.MonsterType.ELITE));
/*      */ 
/*      */     
/*  971 */     data.add(new EnemyData("Lagavulin Event", 1, EnemyData.MonsterType.EVENT));
/*  972 */     data.add(new EnemyData("The Mushroom Lair", 1, EnemyData.MonsterType.EVENT));
/*      */ 
/*      */     
/*  975 */     data.add(new EnemyData("The Guardian", 1, EnemyData.MonsterType.BOSS));
/*  976 */     data.add(new EnemyData("Hexaghost", 1, EnemyData.MonsterType.BOSS));
/*  977 */     data.add(new EnemyData("Slime Boss", 1, EnemyData.MonsterType.BOSS));
/*      */ 
/*      */ 
/*      */     
/*  981 */     data.add(new EnemyData("Chosen", 2, EnemyData.MonsterType.WEAK));
/*  982 */     data.add(new EnemyData("Shell Parasite", 2, EnemyData.MonsterType.WEAK));
/*  983 */     data.add(new EnemyData("Spheric Guardian", 2, EnemyData.MonsterType.WEAK));
/*  984 */     data.add(new EnemyData("3 Byrds", 2, EnemyData.MonsterType.WEAK));
/*  985 */     data.add(new EnemyData("2 Thieves", 2, EnemyData.MonsterType.WEAK));
/*      */ 
/*      */ 
/*      */     
/*  989 */     data.add(new EnemyData("Chosen and Byrds", 2, EnemyData.MonsterType.STRONG));
/*  990 */     data.add(new EnemyData("Sentry and Sphere", 2, EnemyData.MonsterType.STRONG));
/*  991 */     data.add(new EnemyData("Snake Plant", 2, EnemyData.MonsterType.STRONG));
/*  992 */     data.add(new EnemyData("Snecko", 2, EnemyData.MonsterType.STRONG));
/*  993 */     data.add(new EnemyData("Centurion and Healer", 2, EnemyData.MonsterType.STRONG));
/*  994 */     data.add(new EnemyData("Cultist and Chosen", 2, EnemyData.MonsterType.STRONG));
/*  995 */     data.add(new EnemyData("3 Cultists", 2, EnemyData.MonsterType.STRONG));
/*  996 */     data.add(new EnemyData("Shelled Parasite and Fungi", 2, EnemyData.MonsterType.STRONG));
/*      */ 
/*      */     
/*  999 */     data.add(new EnemyData("Gremlin Leader", 2, EnemyData.MonsterType.ELITE));
/* 1000 */     data.add(new EnemyData("Slavers", 2, EnemyData.MonsterType.ELITE));
/* 1001 */     data.add(new EnemyData("Book of Stabbing", 2, EnemyData.MonsterType.ELITE));
/*      */ 
/*      */     
/* 1004 */     data.add(new EnemyData("Masked Bandits", 2, EnemyData.MonsterType.EVENT));
/* 1005 */     data.add(new EnemyData("Colosseum Nobs", 2, EnemyData.MonsterType.EVENT));
/* 1006 */     data.add(new EnemyData("Colosseum Slavers", 2, EnemyData.MonsterType.EVENT));
/*      */ 
/*      */     
/* 1009 */     data.add(new EnemyData("Automaton", 2, EnemyData.MonsterType.BOSS));
/* 1010 */     data.add(new EnemyData("Champ", 2, EnemyData.MonsterType.BOSS));
/* 1011 */     data.add(new EnemyData("Collector", 2, EnemyData.MonsterType.BOSS));
/*      */ 
/*      */ 
/*      */     
/* 1015 */     data.add(new EnemyData("Orb Walker", 3, EnemyData.MonsterType.WEAK));
/* 1016 */     data.add(new EnemyData("3 Darklings", 3, EnemyData.MonsterType.WEAK));
/* 1017 */     data.add(new EnemyData("3 Shapes", 3, EnemyData.MonsterType.WEAK));
/*      */ 
/*      */     
/* 1020 */     data.add(new EnemyData("Transient", 3, EnemyData.MonsterType.STRONG));
/* 1021 */     data.add(new EnemyData("4 Shapes", 3, EnemyData.MonsterType.STRONG));
/* 1022 */     data.add(new EnemyData("Maw", 3, EnemyData.MonsterType.STRONG));
/* 1023 */     data.add(new EnemyData("Jaw Worm Horde", 3, EnemyData.MonsterType.STRONG));
/* 1024 */     data.add(new EnemyData("Sphere and 2 Shapes", 3, EnemyData.MonsterType.STRONG));
/* 1025 */     data.add(new EnemyData("Spire Growth", 3, EnemyData.MonsterType.STRONG));
/* 1026 */     data.add(new EnemyData("Writhing Mass", 3, EnemyData.MonsterType.STRONG));
/*      */ 
/*      */     
/* 1029 */     data.add(new EnemyData("Giant Head", 3, EnemyData.MonsterType.ELITE));
/* 1030 */     data.add(new EnemyData("Nemesis", 3, EnemyData.MonsterType.ELITE));
/* 1031 */     data.add(new EnemyData("Reptomancer", 3, EnemyData.MonsterType.ELITE));
/*      */ 
/*      */     
/* 1034 */     data.add(new EnemyData("Mysterious Sphere", 3, EnemyData.MonsterType.EVENT));
/* 1035 */     data.add(new EnemyData("Mind Bloom Boss Battle", 3, EnemyData.MonsterType.EVENT));
/* 1036 */     data.add(new EnemyData("2 Orb Walkers", 3, EnemyData.MonsterType.EVENT));
/*      */ 
/*      */     
/* 1039 */     data.add(new EnemyData("Awakened One", 3, EnemyData.MonsterType.BOSS));
/* 1040 */     data.add(new EnemyData("Donu and Deca", 3, EnemyData.MonsterType.BOSS));
/* 1041 */     data.add(new EnemyData("Time Eater", 3, EnemyData.MonsterType.BOSS));
/*      */ 
/*      */ 
/*      */     
/* 1045 */     data.add(new EnemyData("Shield and Spear", 4, EnemyData.MonsterType.ELITE));
/*      */ 
/*      */     
/* 1048 */     data.add(new EnemyData("The Heart", 4, EnemyData.MonsterType.BOSS));
/*      */     
/* 1050 */     for (EnemyData d : data) {
/* 1051 */       derp.add(d.gameDataUploadData());
/*      */     }
/*      */     
/* 1054 */     BotDataUploader.uploadDataAsync(BotDataUploader.GameDataType.ENEMY_DATA, EnemyData.gameDataUploadHeader(), derp);
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\MonsterHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */