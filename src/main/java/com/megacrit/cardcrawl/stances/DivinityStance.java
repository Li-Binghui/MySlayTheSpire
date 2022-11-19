/*    */ package com.megacrit.cardcrawl.stances;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
/*    */ 
/*    */ public class DivinityStance extends AbstractStance {
/* 20 */   private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString("Divinity"); public static final String STANCE_ID = "Divinity";
/* 21 */   private static long sfxId = -1L;
/*    */   
/*    */   public DivinityStance() {
/* 24 */     this.ID = "Divinity";
/* 25 */     this.name = stanceString.NAME;
/* 26 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateAnimation() {
/* 31 */     if (!Settings.DISABLE_EFFECTS) {
/* 32 */       this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 33 */       if (this.particleTimer < 0.0F) {
/* 34 */         this.particleTimer = 0.2F;
/* 35 */         AbstractDungeon.effectsQueue.add(new DivinityParticleEffect());
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 40 */     this.particleTimer2 -= Gdx.graphics.getDeltaTime();
/* 41 */     if (this.particleTimer2 < 0.0F) {
/* 42 */       this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
/* 43 */       AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Divinity"));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 49 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChangeStanceAction("Neutral"));
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 54 */     if (type == DamageInfo.DamageType.NORMAL) {
/* 55 */       return damage * 3.0F;
/*    */     }
/* 57 */     return damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 62 */     this.description = stanceString.DESCRIPTION[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterStance() {
/* 67 */     if (sfxId != -1L) {
/* 68 */       stopIdleSfx();
/*    */     }
/*    */     
/* 71 */     CardCrawlGame.sound.play("STANCE_ENTER_DIVINITY");
/* 72 */     sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_DIVINITY");
/* 73 */     AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.PINK, true));
/* 74 */     AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Divinity"));
/*    */     
/* 76 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainEnergyAction(3));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onExitStance() {
/* 81 */     stopIdleSfx();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopIdleSfx() {
/* 86 */     if (sfxId != -1L) {
/* 87 */       CardCrawlGame.sound.stop("STANCE_LOOP_DIVINITY", sfxId);
/* 88 */       sfxId = -1L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\stances\DivinityStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */