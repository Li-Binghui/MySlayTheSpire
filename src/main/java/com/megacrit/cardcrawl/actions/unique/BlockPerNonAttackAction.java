/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BlockPerNonAttackAction extends AbstractGameAction {
/*    */   private int blockPerCard;
/*    */   
/*    */   public BlockPerNonAttackAction(int blockAmount) {
/* 15 */     this.blockPerCard = blockAmount;
/* 16 */     setValues((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player);
/* 17 */     this.actionType = ActionType.BLOCK;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
/* 24 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 25 */       if (c.type != AbstractCard.CardType.ATTACK) {
/* 26 */         cardsToExhaust.add(c);
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 32 */     for (AbstractCard c : cardsToExhaust) {
/* 33 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.blockPerCard));
/*    */     }
/*    */     
/* 36 */     for (AbstractCard c : cardsToExhaust) {
/* 37 */       addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
/*    */     }
/* 39 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\BlockPerNonAttackAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */