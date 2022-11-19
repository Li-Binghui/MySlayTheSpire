/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class Test6
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Test 6";
/*    */   
/*    */   public Test6() {
/* 14 */     super("Test 6", "test6.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */   private static final int GOLD_REQ = 100; private static final int BLOCK_AMT = 3;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1] + 'd' + this.DESCRIPTIONS[2];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onPlayerEndTurn() {
/* 25 */     if (hasEnoughGold()) {
/* 26 */       flash();
/* 27 */       this.pulse = false;
/* 28 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 3 * AbstractDungeon.player.gold / 100));
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 33 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 39 */     if (hasEnoughGold()) {
/* 40 */       this.pulse = true;
/* 41 */       beginPulse();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 47 */     this.pulse = false;
/*    */   }
/*    */   
/*    */   private boolean hasEnoughGold() {
/* 51 */     return (AbstractDungeon.player.gold >= 100);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 56 */     return new Test6();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Test6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */