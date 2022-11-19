/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class RunicCapacitor extends AbstractRelic {
/*    */   public static final String ID = "Runic Capacitor";
/*    */   
/*    */   public RunicCapacitor() {
/* 13 */     super("Runic Capacitor", "runicCapacitor.png", RelicTier.SHOP, LandingSound.SOLID);
/*    */   }
/*    */   private boolean firstTurn = true;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 23 */     this.firstTurn = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 28 */     if (this.firstTurn) {
/* 29 */       flash();
/* 30 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 31 */       addToBot((AbstractGameAction)new IncreaseMaxOrbAction(3));
/* 32 */       this.firstTurn = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 38 */     return new RunicCapacitor();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\RunicCapacitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */