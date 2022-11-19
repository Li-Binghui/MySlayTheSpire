/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BendAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractPlayer p;
/* 14 */   private ArrayList<AbstractCard> cannotChoose = new ArrayList<>();
/*    */   
/*    */   public BendAction() {
/* 17 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 18 */     this.p = AbstractDungeon.player;
/* 19 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*    */ 
/*    */       
/* 27 */       for (AbstractCard c : this.p.hand.group) {
/* 28 */         if (c.type != AbstractCard.CardType.ATTACK || c.cost <= 0) {
/* 29 */           this.cannotChoose.add(c);
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 34 */       if (this.cannotChoose.size() == this.p.hand.group.size()) {
/* 35 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 39 */       if (this.p.hand.group.size() - this.cannotChoose.size() == 1) {
/* 40 */         for (AbstractCard c : this.p.hand.group) {
/* 41 */           if (c.type == AbstractCard.CardType.ATTACK) {
/* 42 */             c.modifyCostForCombat(-1);
/* 43 */             this.isDone = true;
/*    */ 
/*    */             
/*    */             return;
/*    */           } 
/*    */         } 
/*    */       }
/*    */       
/* 51 */       this.p.hand.group.removeAll(this.cannotChoose);
/*    */       
/* 53 */       if (this.p.hand.group.size() > 1) {
/* 54 */         AbstractDungeon.handCardSelectScreen.open("Upgrade.", 1, false);
/* 55 */         tickDuration(); return;
/*    */       } 
/* 57 */       if (this.p.hand.group.size() == 1) {
/* 58 */         this.p.hand.getTopCard().modifyCostForCombat(-1);
/* 59 */         returnCards();
/* 60 */         this.isDone = true;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 65 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 66 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 67 */         c.modifyCostForCombat(-1);
/* 68 */         this.p.hand.addToTop(c);
/*    */       } 
/*    */       
/* 71 */       returnCards();
/* 72 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/* 73 */       AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
/* 74 */       this.isDone = true;
/*    */     } 
/*    */     
/* 77 */     tickDuration();
/*    */   }
/*    */ 
/*    */   
/*    */   private void returnCards() {
/* 82 */     for (AbstractCard c : this.cannotChoose) {
/* 83 */       this.p.hand.addToTop(c);
/*    */     }
/* 85 */     this.p.hand.refreshHandLayout();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\BendAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */