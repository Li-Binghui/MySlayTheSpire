/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PlatedArmorPower;
/*    */ 
/*    */ public class ThreadAndNeedle extends AbstractRelic {
/*    */   public static final String ID = "Thread and Needle";
/*    */   
/*    */   public ThreadAndNeedle() {
/* 14 */     super("Thread and Needle", "threadAndNeedle.png", RelicTier.RARE, LandingSound.CLINK);
/*    */   }
/*    */   private static final int ARMOR_AMT = 4;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\004' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 24 */     flash();
/* 25 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new PlatedArmorPower((AbstractCreature)AbstractDungeon.player, 4), 4));
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
/* 36 */     return new ThreadAndNeedle();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\ThreadAndNeedle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */