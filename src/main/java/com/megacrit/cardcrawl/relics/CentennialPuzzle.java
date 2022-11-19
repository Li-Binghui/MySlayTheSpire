/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class CentennialPuzzle extends AbstractRelic {
/*    */   public static final String ID = "Centennial Puzzle";
/*    */   private static final int NUM_CARDS = 3;
/*    */   private static boolean usedThisCombat = false;
/*    */   
/*    */   public CentennialPuzzle() {
/* 16 */     super("Centennial Puzzle", "centennialPuzzle.png", RelicTier.COMMON, LandingSound.HEAVY);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 26 */     usedThisCombat = false;
/* 27 */     this.pulse = true;
/* 28 */     beginPulse();
/*    */   }
/*    */ 
/*    */   
/*    */   public void wasHPLost(int damageAmount) {
/* 33 */     if (damageAmount > 0 && 
/* 34 */       (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && 
/* 35 */       !usedThisCombat) {
/* 36 */       flash();
/* 37 */       this.pulse = false;
/* 38 */       addToTop((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 3));
/* 39 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 40 */       usedThisCombat = true;
/* 41 */       this.grayscale = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void justEnteredRoom(AbstractRoom room) {
/* 49 */     this.grayscale = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 54 */     this.pulse = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 59 */     return new CentennialPuzzle();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\CentennialPuzzle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */