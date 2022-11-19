/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.FocusPower;
/*    */ 
/*    */ public class DataDisk extends AbstractRelic {
/*    */   public static final String ID = "DataDisk";
/*    */   
/*    */   public DataDisk() {
/* 14 */     super("DataDisk", "dataDisk.png", RelicTier.COMMON, LandingSound.FLAT);
/*    */   }
/*    */   private static final int INT = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 24 */     flash();
/* 25 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new FocusPower((AbstractCreature)AbstractDungeon.player, 1), 1));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 36 */     return new DataDisk();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\DataDisk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */