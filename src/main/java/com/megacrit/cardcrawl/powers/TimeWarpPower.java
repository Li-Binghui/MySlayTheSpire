/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect;
/*    */ 
/*    */ public class TimeWarpPower extends AbstractPower {
/* 17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Time Warp"); public static final String POWER_ID = "Time Warp";
/* 18 */   public static final String NAME = powerStrings.NAME;
/* 19 */   public static final String[] DESC = powerStrings.DESCRIPTIONS; private static final int STR_AMT = 2;
/*    */   private static final int COUNTDOWN_AMT = 12;
/*    */   
/*    */   public TimeWarpPower(AbstractCreature owner) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "Time Warp";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = 0;
/* 27 */     updateDescription();
/* 28 */     loadRegion("time");
/* 29 */     this.type = PowerType.BUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 34 */     CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 39 */     this.description = DESC[0] + '\f' + DESC[1] + '\002' + DESC[2];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 44 */     flashWithoutSound();
/* 45 */     this.amount++;
/* 46 */     if (this.amount == 12) {
/* 47 */       this.amount = 0;
/* 48 */       playApplyPowerSfx();
/* 49 */       AbstractDungeon.actionManager.callEndTurnEarlySequence();
/*    */       
/* 51 */       CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
/* 52 */       AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));
/* 53 */       AbstractDungeon.topLevelEffectsQueue.add(new TimeWarpTurnEndEffect());
/*    */       
/* 55 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 56 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, new StrengthPower((AbstractCreature)m, 2), 2));
/*    */       }
/*    */     } 
/* 59 */     updateDescription();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\TimeWarpPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */