/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class BurningBlood extends AbstractRelic {
/*    */   public static final String ID = "Burning Blood";
/*    */   
/*    */   public BurningBlood() {
/* 13 */     super("Burning Blood", "burningBlood.png", RelicTier.STARTER, LandingSound.MAGICAL);
/*    */   }
/*    */   private static final int HEALTH_AMT = 6;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\006' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 23 */     flash();
/* 24 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 25 */     AbstractPlayer p = AbstractDungeon.player;
/* 26 */     if (p.currentHealth > 0) {
/* 27 */       p.heal(6);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new BurningBlood();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\BurningBlood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */