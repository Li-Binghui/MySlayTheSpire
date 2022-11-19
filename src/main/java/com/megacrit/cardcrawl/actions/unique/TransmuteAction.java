/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TransmuteAction
/*    */   extends AbstractGameAction
/*    */ {
/* 16 */   private AbstractPlayer p = AbstractDungeon.player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 25 */       AbstractDungeon.actionManager.cleanCardQueue();
/* 26 */       if (this.p.hand.group.isEmpty()) {
/* 27 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 30 */       CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 31 */       tmp.group.addAll(this.p.hand.group);
/* 32 */       this.p.hand.clear();
/* 33 */       for (AbstractCard c : tmp.group) {
/* 34 */         AbstractDungeon.transformCard(c);
/* 35 */         AbstractCard transformedCard = AbstractDungeon.getTransformedCard();
/* 36 */         this.p.hand.addToTop(transformedCard);
/*    */       } 
/*    */       
/* 39 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/* 43 */     this.p.hand.refreshHandLayout();
/* 44 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\TransmuteAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */