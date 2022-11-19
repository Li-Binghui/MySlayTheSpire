/*    */ package com.megacrit.cardcrawl.stances;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
/*    */ 
/*    */ public class CalmStance extends AbstractStance {
/* 17 */   private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString("Calm"); public static final String STANCE_ID = "Calm";
/* 18 */   private static long sfxId = -1L;
/*    */   
/*    */   public CalmStance() {
/* 21 */     this.ID = "Calm";
/* 22 */     this.name = stanceString.NAME;
/* 23 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 28 */     this.description = stanceString.DESCRIPTION[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateAnimation() {
/* 33 */     if (!Settings.DISABLE_EFFECTS) {
/*    */       
/* 35 */       this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 36 */       if (this.particleTimer < 0.0F) {
/* 37 */         this.particleTimer = 0.04F;
/* 38 */         AbstractDungeon.effectsQueue.add(new CalmParticleEffect());
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 43 */     this.particleTimer2 -= Gdx.graphics.getDeltaTime();
/* 44 */     if (this.particleTimer2 < 0.0F) {
/* 45 */       this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
/* 46 */       AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Calm"));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterStance() {
/* 52 */     if (sfxId != -1L) {
/* 53 */       stopIdleSfx();
/*    */     }
/*    */     
/* 56 */     CardCrawlGame.sound.play("STANCE_ENTER_CALM");
/* 57 */     sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_CALM");
/* 58 */     AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SKY, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onExitStance() {
/* 63 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainEnergyAction(2));
/* 64 */     stopIdleSfx();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopIdleSfx() {
/* 69 */     if (sfxId != -1L) {
/* 70 */       CardCrawlGame.sound.stop("STANCE_LOOP_CALM", sfxId);
/* 71 */       sfxId = -1L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\stances\CalmStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */