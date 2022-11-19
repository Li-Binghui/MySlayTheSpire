/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MadnessAction
/*    */   extends AbstractGameAction
/*    */ {
/* 15 */   private AbstractPlayer p = AbstractDungeon.player;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 22 */       boolean betterPossible = false;
/* 23 */       boolean possible = false;
/* 24 */       for (AbstractCard c : this.p.hand.group) {
/* 25 */         if (c.costForTurn > 0) {
/* 26 */           betterPossible = true; continue;
/* 27 */         }  if (c.cost > 0) {
/* 28 */           possible = true;
/*    */         }
/*    */       } 
/* 31 */       if (betterPossible || possible) {
/* 32 */         findAndModifyCard(betterPossible);
/*    */       }
/*    */     } 
/*    */     
/* 36 */     tickDuration();
/*    */   }
/*    */   
/*    */   private void findAndModifyCard(boolean better) {
/* 40 */     AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
/* 41 */     if (better) {
/* 42 */       if (c.costForTurn > 0) {
/*    */         
/* 44 */         c.cost = 0;
/* 45 */         c.costForTurn = 0;
/* 46 */         c.isCostModified = true;
/* 47 */         c.superFlash(Color.GOLD.cpy());
/*    */       } else {
/*    */         
/* 50 */         findAndModifyCard(better);
/*    */       }
/*    */     
/* 53 */     } else if (c.cost > 0) {
/*    */       
/* 55 */       c.cost = 0;
/* 56 */       c.costForTurn = 0;
/* 57 */       c.isCostModified = true;
/* 58 */       c.superFlash(Color.GOLD.cpy());
/*    */     } else {
/*    */       
/* 61 */       findAndModifyCard(better);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\MadnessAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */