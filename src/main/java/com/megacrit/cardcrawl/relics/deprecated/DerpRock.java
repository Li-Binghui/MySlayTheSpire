/*    */ package com.megacrit.cardcrawl.relics.deprecated;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ public class DerpRock extends AbstractRelic {
/*    */   public static final String ID = "Derp Rock";
/*    */   
/*    */   public DerpRock() {
/* 13 */     super("Derp Rock", "derpRock.png", RelicTier.STARTER, LandingSound.HEAVY);
/*    */   }
/*    */   public static final int CHARGE_AMT = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 24 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new DerpRock();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\deprecated\DerpRock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */