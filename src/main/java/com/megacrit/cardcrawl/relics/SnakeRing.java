/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class SnakeRing extends AbstractRelic {
/*    */   public static final String ID = "Ring of the Snake";
/*    */   
/*    */   public SnakeRing() {
/* 13 */     super("Ring of the Snake", "snake_ring.png", RelicTier.STARTER, LandingSound.FLAT);
/*    */   }
/*    */   private static final int NUM_CARDS = 2;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\002' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 24 */     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 2));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new SnakeRing();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\SnakeRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */