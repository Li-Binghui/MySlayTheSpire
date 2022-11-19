/*    */ package com.megacrit.cardcrawl.relics;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class GamblingChip extends AbstractRelic {
/*    */   public static final String ID = "Gambling Chip";
/*    */   
/*    */   public GamblingChip() {
/* 12 */     super("Gambling Chip", "gamblingChip.png", RelicTier.RARE, LandingSound.FLAT);
/*    */   }
/*    */   private boolean activated = false;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStartPreDraw() {
/* 22 */     this.activated = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStartPostDraw() {
/* 27 */     if (!this.activated) {
/* 28 */       this.activated = true;
/* 29 */       flash();
/* 30 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 31 */       addToBot((AbstractGameAction)new GamblingChipAction((AbstractCreature)AbstractDungeon.player));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 37 */     return new GamblingChip();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\GamblingChip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */