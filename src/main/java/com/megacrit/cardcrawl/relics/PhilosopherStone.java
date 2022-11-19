/*    */ package com.megacrit.cardcrawl.relics;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ public class PhilosopherStone extends AbstractRelic {
/*    */   public static final String ID = "Philosopher's Stone";
/*    */   
/*    */   public PhilosopherStone() {
/* 16 */     super("Philosopher's Stone", "philosopherStone.png", RelicTier.BOSS, LandingSound.CLINK);
/*    */   }
/*    */   public static final int STR = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 26 */     this.description = getUpdatedDescription();
/* 27 */     this.tips.clear();
/* 28 */     this.tips.add(new PowerTip(this.name, this.description));
/* 29 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 34 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 35 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)m, this));
/* 36 */       m.addPower((AbstractPower)new StrengthPower((AbstractCreature)m, 1));
/*    */     } 
/* 38 */     AbstractDungeon.onModifyPower();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpawnMonster(AbstractMonster monster) {
/* 43 */     monster.addPower((AbstractPower)new StrengthPower((AbstractCreature)monster, 1));
/* 44 */     AbstractDungeon.onModifyPower();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 49 */     AbstractDungeon.player.energy.energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 54 */     AbstractDungeon.player.energy.energyMaster--;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 59 */     return new PhilosopherStone();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\PhilosopherStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */