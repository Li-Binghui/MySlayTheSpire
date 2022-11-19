/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class WaveOfTheHandPower extends AbstractPower {
/* 16 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("WaveOfTheHandPower"); public static final String POWER_ID = "WaveOfTheHandPower";
/*    */   
/*    */   public WaveOfTheHandPower(AbstractCreature owner, int newAmount) {
/* 19 */     this.name = powerStrings.NAME;
/* 20 */     this.ID = "WaveOfTheHandPower";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = newAmount;
/* 23 */     updateDescription();
/* 24 */     loadRegion("wave_of_the_hand");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onGainedBlock(float blockAmount) {
/* 29 */     if (blockAmount > 0.0F) {
/* 30 */       flash();
/* 31 */       AbstractPlayer abstractPlayer = AbstractDungeon.player;
/* 32 */       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 33 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)abstractPlayer, (AbstractPower)new WeakPower((AbstractCreature)mo, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.NONE));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 47 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "WaveOfTheHandPower"));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 52 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\WaveOfTheHandPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */