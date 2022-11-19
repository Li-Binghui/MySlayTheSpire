/*    */ package com.megacrit.cardcrawl.relics;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
/*    */ 
/*    */ public class OddlySmoothStone extends AbstractRelic {
/*    */   public static final String ID = "Oddly Smooth Stone";
/*    */   
/*    */   public OddlySmoothStone() {
/* 13 */     super("Oddly Smooth Stone", "smooth_stone.png", RelicTier.COMMON, LandingSound.SOLID);
/*    */   }
/*    */   private static final int CON_AMT = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     flash();
/* 24 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DexterityPower((AbstractCreature)AbstractDungeon.player, 1), 1));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 35 */     return new OddlySmoothStone();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\OddlySmoothStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */