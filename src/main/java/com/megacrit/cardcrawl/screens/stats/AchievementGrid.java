/*     */ package com.megacrit.cardcrawl.screens.stats;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.localization.AchievementStrings;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class AchievementGrid
/*     */ {
/*  12 */   private static final AchievementStrings achievementStrings = CardCrawlGame.languagePack.getAchievementString("AchievementGrid");
/*     */   
/*  14 */   public static final String[] NAMES = achievementStrings.NAMES;
/*  15 */   public static final String[] TEXT = achievementStrings.TEXT;
/*     */   
/*  17 */   public ArrayList<AchievementItem> items = new ArrayList<>();
/*  18 */   private static final float SPACING = 200.0F * Settings.scale;
/*     */   
/*     */   private static final int ITEMS_PER_ROW = 5;
/*     */   
/*     */   public static final String SHRUG_KEY = "SHRUG_IT_OFF";
/*     */   
/*     */   public static final String PURITY_KEY = "PURITY";
/*     */   
/*     */   public static final String COME_AT_ME_KEY = "COME_AT_ME";
/*     */   
/*     */   public static final String THE_PACT_KEY = "THE_PACT";
/*     */   
/*     */   public static final String ADRENALINE_KEY = "ADRENALINE";
/*     */   public static final String POWERFUL_KEY = "POWERFUL";
/*     */   public static final String JAXXED_KEY = "JAXXED";
/*     */   public static final String IMPERVIOUS_KEY = "IMPERVIOUS";
/*     */   public static final String BARRICADED_KEY = "BARRICADED";
/*     */   public static final String CATALYST_KEY = "CATALYST";
/*     */   public static final String PLAGUE_KEY = "PLAGUE";
/*     */   public static final String NINJA_KEY = "NINJA";
/*     */   public static final String INFINITY_KEY = "INFINITY";
/*     */   public static final String YOU_ARE_NOTHING_KEY = "YOU_ARE_NOTHING";
/*     */   public static final String PERFECT_KEY = "PERFECT";
/*     */   public static final String ONE_RELIC_KEY = "ONE_RELIC";
/*     */   public static final String SPEED_CLIMBER_KEY = "SPEED_CLIMBER";
/*     */   public static final String ASCEND_0_KEY = "ASCEND_0";
/*     */   public static final String ASCEND_10_KEY = "ASCEND_10";
/*     */   public static final String ASCEND_20_KEY = "ASCEND_20";
/*     */   public static final String MINMALIST_KEY = "MINIMALIST";
/*     */   public static final String DONUT_KEY = "DONUT";
/*     */   public static final String COMMON_SENSE_KEY = "COMMON_SENSE";
/*     */   public static final String FOCUSED_KEY = "FOCUSED";
/*     */   public static final String LUCKY_DAY_KEY = "LUCKY_DAY";
/*     */   public static final String NEON_KEY = "NEON";
/*     */   public static final String TRANSIENT_KEY = "TRANSIENT";
/*     */   public static final String GUARDIAN_KEY = "GUARDIAN";
/*     */   public static final String GHOST_GUARDIAN_KEY = "GHOST_GUARDIAN";
/*     */   public static final String SLIME_BOSS_KEY = "SLIME_BOSS";
/*     */   public static final String AUTOMATON_KEY = "AUTOMATON";
/*     */   public static final String COLLECTOR_KEY = "COLLECTOR";
/*     */   public static final String CHAMP_KEY = "CHAMP";
/*     */   public static final String CROW_KEY = "CROW";
/*     */   public static final String SHAPES_KEY = "SHAPES";
/*     */   public static final String TIME_EATER_KEY = "TIME_EATER";
/*     */   public static final String RUBY_KEY = "RUBY";
/*     */   public static final String EMERALD_KEY = "EMERALD";
/*     */   public static final String SAPPHIRE_KEY = "SAPPHIRE";
/*     */   public static final String AMETHYST_KEY = "AMETHYST";
/*     */   public static final String RUBY_PLUS_KEY = "RUBY_PLUS";
/*     */   public static final String EMERALD_PLUS_KEY = "EMERALD_PLUS";
/*     */   public static final String SAPPHIRE_PLUS_KEY = "SAPPHIRE_PLUS";
/*     */   public static final String AMETHYST_PLUS_KEY = "AMETHYST_PLUS";
/*     */   public static final String THE_ENDING_KEY = "THE_ENDING";
/*     */   public static final String ETERNAL_ONE_KEY = "ETERNAL_ONE";
/*     */   
/*     */   public AchievementGrid() {
/*  74 */     this.items.add(new AchievementItem(NAMES[0], TEXT[0], "shrugItOff", "SHRUG_IT_OFF"));
/*  75 */     this.items.add(new AchievementItem(NAMES[1], TEXT[1], "purity", "PURITY"));
/*  76 */     this.items.add(new AchievementItem(NAMES[2], TEXT[2], "comeAtMe", "COME_AT_ME"));
/*  77 */     this.items.add(new AchievementItem(NAMES[3], TEXT[3], "thePact", "THE_PACT"));
/*  78 */     this.items.add(new AchievementItem(NAMES[4], TEXT[4], "adrenaline", "ADRENALINE"));
/*  79 */     this.items.add(new AchievementItem(NAMES[5], TEXT[5], "powerful", "POWERFUL"));
/*  80 */     this.items.add(new AchievementItem(NAMES[6], TEXT[6], "jaxxed", "JAXXED"));
/*  81 */     this.items.add(new AchievementItem(NAMES[7], TEXT[7], "impervious", "IMPERVIOUS"));
/*  82 */     this.items.add(new AchievementItem(NAMES[8], TEXT[8], "barricaded", "BARRICADED"));
/*  83 */     this.items.add(new AchievementItem(NAMES[9], TEXT[9], "catalyst", "CATALYST"));
/*  84 */     this.items.add(new AchievementItem(NAMES[10], TEXT[10], "plague", "PLAGUE"));
/*  85 */     this.items.add(new AchievementItem(NAMES[11], TEXT[11], "ninja", "NINJA"));
/*  86 */     this.items.add(new AchievementItem(NAMES[12], TEXT[12], "infinity", "INFINITY"));
/*  87 */     this.items.add(new AchievementItem(NAMES[35], TEXT[35], "focused", "FOCUSED"));
/*  88 */     this.items.add(new AchievementItem(NAMES[36], TEXT[36], "neon", "NEON"));
/*  89 */     this.items.add(new AchievementItem(NAMES[13], TEXT[13], "youAreNothing", "YOU_ARE_NOTHING"));
/*  90 */     this.items.add(new AchievementItem(NAMES[31], TEXT[31], "minimalist", "MINIMALIST"));
/*  91 */     this.items.add(new AchievementItem(NAMES[32], TEXT[32], "donut", "DONUT"));
/*  92 */     this.items.add(new AchievementItem(NAMES[14], TEXT[14], "perfect", "PERFECT"));
/*  93 */     this.items.add(new AchievementItem(NAMES[27], TEXT[27], "onerelic", "ONE_RELIC"));
/*  94 */     this.items.add(new AchievementItem(NAMES[28], TEXT[28], "speed", "SPEED_CLIMBER"));
/*  95 */     this.items.add(new AchievementItem(NAMES[34], TEXT[34], "commonSense", "COMMON_SENSE"));
/*  96 */     this.items.add(new AchievementItem(NAMES[38], TEXT[38], "luckyDay", "LUCKY_DAY"));
/*  97 */     this.items.add(new AchievementItem(NAMES[29], TEXT[29], "0", "ASCEND_0"));
/*  98 */     this.items.add(new AchievementItem(NAMES[30], TEXT[30], "10", "ASCEND_10"));
/*  99 */     this.items.add(new AchievementItem(NAMES[40], TEXT[40], "20", "ASCEND_20"));
/* 100 */     this.items.add(new AchievementItem(NAMES[39], TEXT[39], "transient", "TRANSIENT"));
/* 101 */     this.items.add(new AchievementItem(NAMES[15], TEXT[15], "guardian", "GUARDIAN", true));
/* 102 */     this.items.add(new AchievementItem(NAMES[16], TEXT[16], "ghostGuardian", "GHOST_GUARDIAN", true));
/* 103 */     this.items.add(new AchievementItem(NAMES[17], TEXT[17], "slimeBoss", "SLIME_BOSS", true));
/* 104 */     this.items.add(new AchievementItem(NAMES[18], TEXT[18], "automaton", "AUTOMATON", true));
/* 105 */     this.items.add(new AchievementItem(NAMES[19], TEXT[19], "collector", "COLLECTOR", true));
/* 106 */     this.items.add(new AchievementItem(NAMES[20], TEXT[20], "champ", "CHAMP", true));
/* 107 */     this.items.add(new AchievementItem(NAMES[21], TEXT[21], "awakenedOne", "CROW", true));
/* 108 */     this.items.add(new AchievementItem(NAMES[22], TEXT[22], "shapes", "SHAPES", true));
/* 109 */     this.items.add(new AchievementItem(NAMES[23], TEXT[23], "timeEater", "TIME_EATER", true));
/* 110 */     this.items.add(new AchievementItem(NAMES[24], TEXT[24], "ironclad", "RUBY"));
/* 111 */     this.items.add(new AchievementItem(NAMES[25], TEXT[25], "silent", "EMERALD"));
/* 112 */     this.items.add(new AchievementItem(NAMES[33], TEXT[33], "sapphire", "SAPPHIRE"));
/* 113 */     this.items.add(new AchievementItem(NAMES[46], TEXT[46], "amethyst", "AMETHYST"));
/* 114 */     this.items.add(new AchievementItem(NAMES[41], TEXT[41], "rubyPlus", "RUBY_PLUS", true));
/* 115 */     this.items.add(new AchievementItem(NAMES[42], TEXT[42], "emeraldPlus", "EMERALD_PLUS", true));
/* 116 */     this.items.add(new AchievementItem(NAMES[43], TEXT[43], "sapphirePlus", "SAPPHIRE_PLUS", true));
/* 117 */     this.items.add(new AchievementItem(NAMES[47], TEXT[47], "amethyst_plus", "AMETHYST_PLUS", true));
/* 118 */     this.items.add(new AchievementItem(NAMES[44], TEXT[44], "theEnding", "THE_ENDING", true));
/* 119 */     this.items.add(new AchievementItem(NAMES[45], TEXT[45], "eternal_one", "ETERNAL_ONE", true));
/*     */     
/* 121 */     if (UnlockTracker.allAchievementsExceptPlatinumUnlocked()) {
/* 122 */       UnlockTracker.unlockAchievement("ETERNAL_ONE");
/*     */     }
/*     */   }
/*     */   
/*     */   public void update() {
/* 127 */     for (AchievementItem i : this.items) {
/* 128 */       i.update();
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, float renderY) {
/* 133 */     for (int i = 0; i < this.items.size(); i++) {
/* 134 */       ((AchievementItem)this.items.get(i)).render(sb, 560.0F * Settings.scale + (i % 5) * SPACING, renderY - (i / 5) * SPACING + 680.0F * Settings.yScale);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshImg() {
/* 142 */     for (AchievementItem i : this.items)
/* 143 */       i.reloadImg(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\stats\AchievementGrid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */