/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.BattleStartEffect;
/*     */ 
/*     */ public class PlayerTurnEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static final float DUR = 2.0F;
/*  22 */   private static final float HEIGHT_DIV_2 = Settings.HEIGHT / 2.0F;
/*  23 */   private static final float WIDTH_DIV_2 = Settings.WIDTH / 2.0F;
/*     */   
/*     */   private Color bgColor;
/*     */   
/*  27 */   private static final float TARGET_HEIGHT = 150.0F * Settings.scale;
/*     */   private static final float BG_RECT_EXPAND_SPEED = 3.0F;
/*  29 */   private float currentHeight = 0.0F;
/*     */   
/*     */   private String turnMessage;
/*     */   
/*  33 */   private static final float MAIN_MSG_OFFSET_Y = 20.0F * Settings.scale;
/*  34 */   private static final float TURN_MSG_OFFSET_Y = -30.0F * Settings.scale;
/*  35 */   private Color turnMessageColor = new Color(0.7F, 0.7F, 0.7F, 0.0F);
/*     */   
/*     */   public PlayerTurnEffect() {
/*  38 */     this.duration = 2.0F;
/*  39 */     this.startingDuration = 2.0F;
/*     */     
/*  41 */     this.bgColor = new Color(AbstractDungeon.fadeColor.r / 2.0F, AbstractDungeon.fadeColor.g / 2.0F, AbstractDungeon.fadeColor.b / 2.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.color = Settings.GOLD_COLOR.cpy();
/*  48 */     this.color.a = 0.0F;
/*  49 */     if (Settings.usesOrdinal) {
/*  50 */       this.turnMessage = Integer.toString(GameActionManager.turn) + getOrdinalNaming(GameActionManager.turn) + BattleStartEffect.TURN_TXT;
/*     */     }
/*  52 */     else if (Settings.language == Settings.GameLanguage.VIE) {
/*  53 */       this.turnMessage = BattleStartEffect.TURN_TXT + " " + Integer.toString(GameActionManager.turn);
/*     */     } else {
/*  55 */       this.turnMessage = Integer.toString(GameActionManager.turn) + BattleStartEffect.TURN_TXT;
/*     */     } 
/*  57 */     AbstractDungeon.player.energy.recharge();
/*  58 */     for (AbstractRelic r : AbstractDungeon.player.relics) {
/*  59 */       r.onEnergyRecharge();
/*     */     }
/*  61 */     for (AbstractPower p : AbstractDungeon.player.powers) {
/*  62 */       p.onEnergyRecharge();
/*     */     }
/*  64 */     CardCrawlGame.sound.play("TURN_EFFECT");
/*  65 */     AbstractDungeon.getMonsters().showIntent();
/*  66 */     this.scale = 1.0F;
/*     */   }
/*     */   
/*     */   public static String getOrdinalNaming(int i) {
/*  70 */     (new String[10])[0] = "th"; (new String[10])[1] = "st"; (new String[10])[2] = "nd"; (new String[10])[3] = "rd"; (new String[10])[4] = "th"; (new String[10])[5] = "th"; (new String[10])[6] = "th"; (new String[10])[7] = "th"; (new String[10])[8] = "th"; (new String[10])[9] = "th"; return (i % 100 == 11 || i % 100 == 12 || i % 100 == 13) ? "th" : (new String[10])[i % 10];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  76 */     if (this.duration > 1.5F) {
/*  77 */       this.currentHeight = MathUtils.lerp(this.currentHeight, TARGET_HEIGHT, Gdx.graphics
/*     */ 
/*     */           
/*  80 */           .getDeltaTime() * 3.0F);
/*  81 */     } else if (this.duration < 0.5F) {
/*  82 */       this.currentHeight = MathUtils.lerp(this.currentHeight, 0.0F, Gdx.graphics.getDeltaTime() * 3.0F);
/*     */     } 
/*     */ 
/*     */     
/*  86 */     if (this.duration > 1.5F) {
/*  87 */       this.scale = Interpolation.exp10In.apply(1.0F, 3.0F, (this.duration - 1.5F) * 2.0F);
/*  88 */       this.color.a = Interpolation.exp10In.apply(1.0F, 0.0F, (this.duration - 1.5F) * 2.0F);
/*  89 */     } else if (this.duration < 0.5F) {
/*  90 */       this.scale = Interpolation.pow3In.apply(0.9F, 1.0F, this.duration * 2.0F);
/*  91 */       this.color.a = Interpolation.pow3In.apply(0.0F, 1.0F, this.duration * 2.0F);
/*     */     } 
/*  93 */     this.color.a *= 0.75F;
/*  94 */     this.turnMessageColor.a = this.color.a;
/*     */ 
/*     */     
/*  97 */     if (Settings.FAST_MODE) {
/*  98 */       this.duration -= Gdx.graphics.getDeltaTime();
/*     */     }
/* 100 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 101 */     if (this.duration < 0.0F) {
/* 102 */       this.isDone = true;
/* 103 */       for (AbstractPower p : AbstractDungeon.player.powers) {
/* 104 */         p.atEnergyGain();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 111 */     if (!this.isDone) {
/* 112 */       sb.setColor(this.bgColor);
/* 113 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, HEIGHT_DIV_2 - this.currentHeight / 2.0F, Settings.WIDTH, this.currentHeight);
/*     */       
/* 115 */       sb.setBlendFunction(770, 1);
/* 116 */       FontHelper.renderFontCentered(sb, FontHelper.bannerNameFont, BattleStartEffect.PLAYER_TURN_MSG, WIDTH_DIV_2, HEIGHT_DIV_2 + MAIN_MSG_OFFSET_Y, this.color, this.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 125 */       FontHelper.renderFontCentered(sb, FontHelper.turnNumFont, this.turnMessage, WIDTH_DIV_2, HEIGHT_DIV_2 + TURN_MSG_OFFSET_Y, this.turnMessageColor, this.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\PlayerTurnEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */