/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class WarpedTongs extends AbstractRelic {
/*    */   public WarpedTongs() {
/* 11 */     super("WarpedTongs", "tongs.png", RelicTier.SPECIAL, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public static final String ID = "WarpedTongs";
/*    */   
/*    */   public void atTurnStartPostDraw() {
/* 21 */     flash();
/* 22 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 23 */     addToBot((AbstractGameAction)new UpgradeRandomCardAction());
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 28 */     return new WarpedTongs();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\WarpedTongs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */