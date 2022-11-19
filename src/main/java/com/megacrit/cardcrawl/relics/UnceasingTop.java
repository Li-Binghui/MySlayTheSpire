/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ 
/*    */ public class UnceasingTop extends AbstractRelic {
/*    */   public static final String ID = "Unceasing Top";
/*    */   private boolean canDraw = false;
/*    */   private boolean disabledUntilEndOfTurn = false;
/*    */   
/*    */   public UnceasingTop() {
/* 16 */     super("Unceasing Top", "top.png", RelicTier.RARE, LandingSound.CLINK);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 26 */     this.canDraw = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 31 */     this.canDraw = true;
/* 32 */     this.disabledUntilEndOfTurn = false;
/*    */   }
/*    */   
/*    */   public void disableUntilTurnEnds() {
/* 36 */     this.disabledUntilEndOfTurn = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRefreshHand() {
/* 41 */     if (AbstractDungeon.actionManager.actions.isEmpty() && AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.actionManager.turnHasEnded && this.canDraw && 
/* 42 */       !AbstractDungeon.player.hasPower("No Draw") && !AbstractDungeon.isScreenUp)
/*    */     {
/* 44 */       if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !this.disabledUntilEndOfTurn && (
/* 45 */         AbstractDungeon.player.discardPile.size() > 0 || AbstractDungeon.player.drawPile.size() > 0)) {
/* 46 */         flash();
/* 47 */         addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 48 */         addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 1));
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 55 */     return new UnceasingTop();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\UnceasingTop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */