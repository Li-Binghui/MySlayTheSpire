/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class BloodyIdol extends AbstractRelic {
/*    */   public static final String ID = "Bloody Idol";
/*    */   
/*    */   public BloodyIdol() {
/* 12 */     super("Bloody Idol", "bloodyChalice.png", RelicTier.SPECIAL, LandingSound.HEAVY);
/*    */   }
/*    */   private static final int HEAL_AMOUNT = 5;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0] + '\005' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onGainGold() {
/* 22 */     flash();
/* 23 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 24 */     AbstractDungeon.player.heal(5, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new BloodyIdol();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\BloodyIdol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */