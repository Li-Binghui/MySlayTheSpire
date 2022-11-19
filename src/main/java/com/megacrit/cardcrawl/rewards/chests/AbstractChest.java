/*     */ package com.megacrit.cardcrawl.rewards.chests;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public abstract class AbstractChest
/*     */ {
/*  21 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AbstractChest");
/*  22 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  24 */   private static final Logger logger = LogManager.getLogger(AbstractChest.class.getName());
/*  25 */   public static final float CHEST_LOC_X = Settings.WIDTH / 2.0F + 348.0F * Settings.scale;
/*  26 */   public static final float CHEST_LOC_Y = AbstractDungeon.floorY + 192.0F * Settings.scale;
/*     */   
/*     */   private static final int RAW_W = 512;
/*     */   
/*     */   protected Hitbox hb;
/*     */   
/*     */   protected Texture img;
/*     */   
/*     */   protected Texture openedImg;
/*     */   
/*     */   public boolean isOpen = false;
/*     */   
/*     */   public int COMMON_CHANCE;
/*     */   
/*     */   public int UNCOMMON_CHANCE;
/*     */   
/*     */   public int RARE_CHANCE;
/*     */   
/*     */   public int GOLD_CHANCE;
/*     */   
/*     */   public int GOLD_AMT;
/*     */   public RelicReward relicReward;
/*     */   public boolean goldReward = false;
/*     */   public boolean cursed = false;
/*     */   
/*     */   protected boolean keyRequirement() {
/*  52 */     this.isOpen = true;
/*  53 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomizeReward() {
/*  58 */     int roll = AbstractDungeon.treasureRng.random(0, 99);
/*     */ 
/*     */     
/*  61 */     if (roll < this.GOLD_CHANCE) {
/*  62 */       this.goldReward = true;
/*     */     }
/*     */ 
/*     */     
/*  66 */     if (roll < this.COMMON_CHANCE) {
/*  67 */       this.relicReward = RelicReward.COMMON_RELIC;
/*  68 */     } else if (roll < this.UNCOMMON_CHANCE + this.COMMON_CHANCE) {
/*  69 */       this.relicReward = RelicReward.UNCOMMON_RELIC;
/*     */     } else {
/*  71 */       this.relicReward = RelicReward.RARE_RELIC;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(boolean bossChest) {
/*  79 */     AbstractDungeon.overlayMenu.proceedButton.setLabel(TEXT[0]);
/*  80 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  81 */       r.onChestOpen(bossChest);
/*     */     }
/*  83 */     CardCrawlGame.sound.play("CHEST_OPEN");
/*     */     
/*  85 */     if (this.goldReward) {
/*  86 */       if (Settings.isDailyRun) {
/*  87 */         AbstractDungeon.getCurrRoom().addGoldToRewards(this.GOLD_AMT);
/*     */       } else {
/*  89 */         AbstractDungeon.getCurrRoom().addGoldToRewards(
/*  90 */             Math.round(AbstractDungeon.treasureRng.random(this.GOLD_AMT * 0.9F, this.GOLD_AMT * 1.1F)));
/*     */       } 
/*     */     }
/*     */     
/*  94 */     if (this.cursed) {
/*  95 */       AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(
/*  96 */             AbstractDungeon.returnRandomCurse(), this.hb.cX, this.hb.cY));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     switch (this.relicReward) {
/*     */       case COMMON_RELIC:
/* 105 */         AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.COMMON);
/*     */         break;
/*     */       case UNCOMMON_RELIC:
/* 108 */         AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.UNCOMMON);
/*     */         break;
/*     */       case RARE_RELIC:
/* 111 */         AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractRelic.RelicTier.RARE);
/*     */         break;
/*     */       default:
/* 114 */         logger.info("ERROR: Unspecified reward: " + this.relicReward.name());
/*     */         break;
/*     */     } 
/*     */     
/* 118 */     if (Settings.isFinalActAvailable && !Settings.hasSapphireKey) {
/* 119 */       AbstractDungeon.getCurrRoom().addSapphireKey(
/* 120 */           (AbstractDungeon.getCurrRoom()).rewards.get((AbstractDungeon.getCurrRoom()).rewards.size() - 1));
/*     */     }
/*     */     
/* 123 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 124 */       r.onChestOpenAfter(bossChest);
/*     */     }
/*     */     
/* 127 */     AbstractDungeon.combatRewardScreen.open();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum RelicReward
/*     */   {
/* 134 */     COMMON_RELIC, UNCOMMON_RELIC, RARE_RELIC;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 141 */     this.hb.update();
/* 142 */     if (((this.hb.hovered && InputHelper.justClickedLeft) || CInputActionSet.select.isJustPressed()) && !AbstractDungeon.isScreenUp && !this.isOpen && 
/* 143 */       keyRequirement()) {
/* 144 */       InputHelper.justClickedLeft = false;
/* 145 */       open(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {}
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 154 */     sb.setColor(Color.WHITE);
/* 155 */     float angle = 0.0F;
/*     */     
/* 157 */     if (this.isOpen && this.openedImg == null) {
/* 158 */       angle = 180.0F;
/*     */     }
/*     */     
/* 161 */     float xxx = Settings.WIDTH / 2.0F + 348.0F * Settings.scale;
/*     */     
/* 163 */     if (!this.isOpen || angle == 180.0F) {
/* 164 */       sb.draw(this.img, CHEST_LOC_X - 256.0F, CHEST_LOC_Y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, angle, 0, 0, 512, 512, false, false);
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
/* 182 */       if (this.hb.hovered) {
/* 183 */         sb.setBlendFunction(770, 1);
/* 184 */         sb.setColor(new Color(1.0F, 1.0F, 1.0F, 0.3F));
/* 185 */         sb.draw(this.img, CHEST_LOC_X - 256.0F, CHEST_LOC_Y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, angle, 0, 0, 512, 512, false, false);
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
/* 202 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */     } else {
/* 205 */       sb.draw(this.openedImg, xxx - 256.0F, CHEST_LOC_Y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, angle, 0, 0, 512, 512, false, false);
/*     */     } 
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
/* 224 */     if (Settings.isControllerMode && !this.isOpen) {
/* 225 */       sb.setColor(Color.WHITE);
/* 226 */       sb.draw(CInputActionSet.select
/* 227 */           .getKeyImg(), CHEST_LOC_X - 32.0F - 150.0F * Settings.scale, CHEST_LOC_Y - 32.0F - 210.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
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
/* 245 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\rewards\chests\AbstractChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */