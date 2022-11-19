/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class RedMask extends AbstractRelic {
/*    */   public static final String ID = "Red Mask";
/*    */   
/*    */   public RedMask() {
/* 15 */     super("Red Mask", "redMask.png", RelicTier.SPECIAL, LandingSound.SOLID);
/*    */   }
/*    */   private static final int WEAK = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 25 */     flash();
/* 26 */     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 27 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)mo, this));
/* 28 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower((AbstractCreature)mo, 1, false), 1, true));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 34 */     return new RedMask();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\RedMask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */