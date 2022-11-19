/*    */ package com.megacrit.cardcrawl.relics;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class InkBottle extends AbstractRelic {
/*    */   public static final String ID = "InkBottle";
/*    */   
/*    */   public InkBottle() {
/* 14 */     super("InkBottle", "ink_bottle.png", RelicTier.UNCOMMON, LandingSound.CLINK);
/* 15 */     this.counter = 0;
/*    */   }
/*    */   private static final int COUNT = 10;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 25 */     this.counter++;
/*    */     
/* 27 */     if (this.counter == 10) {
/* 28 */       this.counter = 0;
/* 29 */       flash();
/* 30 */       this.pulse = false;
/* 31 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 32 */       addToBot((AbstractGameAction)new DrawCardAction(1));
/* 33 */     } else if (this.counter == 9) {
/* 34 */       beginPulse();
/* 35 */       this.pulse = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 41 */     if (this.counter == 9) {
/* 42 */       beginPulse();
/* 43 */       this.pulse = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 49 */     return new InkBottle();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\InkBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */