/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Pocketwatch
/*    */   extends AbstractRelic {
/*    */   public static final String ID = "Pocketwatch";
/*    */   
/*    */   public Pocketwatch() {
/* 15 */     super("Pocketwatch", "pocketwatch.png", RelicTier.RARE, LandingSound.FLAT);
/*    */   }
/*    */   private static final int AMT = 3; private boolean firstTurn = true;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 25 */     this.counter = 0;
/* 26 */     this.firstTurn = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStartPostDraw() {
/* 31 */     if (this.counter <= 3 && !this.firstTurn) {
/* 32 */       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 3));
/*    */     } else {
/* 34 */       this.firstTurn = false;
/*    */     } 
/* 36 */     this.counter = 0;
/* 37 */     beginLongPulse();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayCard(AbstractCard card, AbstractMonster m) {
/* 42 */     this.counter++;
/* 43 */     if (this.counter > 3) {
/* 44 */       stopPulse();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 50 */     this.counter = -1;
/* 51 */     stopPulse();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 56 */     return new Pocketwatch();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\Pocketwatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */