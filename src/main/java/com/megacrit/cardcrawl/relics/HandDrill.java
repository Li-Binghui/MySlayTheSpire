/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ 
/*    */ public class HandDrill extends AbstractRelic {
/*    */   public static final String ID = "HandDrill";
/*    */   
/*    */   public HandDrill() {
/* 15 */     super("HandDrill", "drill.png", RelicTier.SHOP, LandingSound.FLAT);
/*    */   }
/*    */   public static final int VULNERABLE_AMT = 2;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockBroken(AbstractCreature m) {
/* 25 */     flash();
/* 26 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction(m, this));
/* 27 */     addToBot((AbstractGameAction)new ApplyPowerAction(m, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new VulnerablePower(m, 2, false), 2));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 37 */     return new HandDrill();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\HandDrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */