/*    */ package com.megacrit.cardcrawl.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PoisonPower;
/*    */ 
/*    */ public class TwistedFunnel extends AbstractRelic {
/*    */   public static final String ID = "TwistedFunnel";
/*    */   
/*    */   public TwistedFunnel() {
/* 14 */     super("TwistedFunnel", "funnel.png", RelicTier.SHOP, LandingSound.HEAVY);
/*    */   }
/*    */   private static final int POISON_AMT = 4;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 24 */     flash();
/* 25 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 26 */       if (!m.isDead && !m.isDying) {
/* 27 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new PoisonPower((AbstractCreature)m, (AbstractCreature)AbstractDungeon.player, 4), 4));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 38 */     return new TwistedFunnel();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\TwistedFunnel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */