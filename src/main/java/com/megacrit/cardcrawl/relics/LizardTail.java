/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class LizardTail extends AbstractRelic {
/*    */   public LizardTail() {
/* 10 */     super("Lizard Tail", "lizardTail.png", RelicTier.RARE, LandingSound.MAGICAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public static final String ID = "Lizard Tail";
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 20 */     if (setCounter == -2) {
/* 21 */       usedUp();
/* 22 */       this.counter = -2;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTrigger() {
/* 28 */     flash();
/* 29 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 30 */     int healAmt = AbstractDungeon.player.maxHealth / 2;
/*    */     
/* 32 */     if (healAmt < 1) {
/* 33 */       healAmt = 1;
/*    */     }
/*    */     
/* 36 */     AbstractDungeon.player.heal(healAmt, true);
/* 37 */     setCounter(-2);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 42 */     return new LizardTail();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\LizardTail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */