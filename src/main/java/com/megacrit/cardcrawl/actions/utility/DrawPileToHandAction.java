/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DrawPileToHandAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractPlayer p;
/*    */   private AbstractCard.CardType typeToCheck;
/*    */   
/*    */   public DrawPileToHandAction(int amount, AbstractCard.CardType type) {
/* 17 */     this.p = AbstractDungeon.player;
/* 18 */     setValues((AbstractCreature)this.p, (AbstractCreature)this.p, amount);
/* 19 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 20 */     this.duration = Settings.ACTION_DUR_MED;
/* 21 */     this.typeToCheck = type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (this.duration == Settings.ACTION_DUR_MED) {
/*    */       
/* 28 */       if (this.p.drawPile.isEmpty()) {
/* 29 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 33 */       CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 34 */       for (AbstractCard c : this.p.drawPile.group) {
/* 35 */         if (c.type == this.typeToCheck) {
/* 36 */           tmp.addToRandomSpot(c);
/*    */         }
/*    */       } 
/*    */       
/* 40 */       if (tmp.size() == 0) {
/* 41 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 45 */       for (int i = 0; i < this.amount; i++) {
/* 46 */         if (!tmp.isEmpty()) {
/* 47 */           tmp.shuffle();
/* 48 */           AbstractCard card = tmp.getBottomCard();
/* 49 */           tmp.removeCard(card);
/* 50 */           if (this.p.hand.size() == 10) {
/* 51 */             this.p.drawPile.moveToDiscardPile(card);
/* 52 */             this.p.createHandIsFullDialog();
/*    */           } else {
/* 54 */             card.unhover();
/* 55 */             card.lighten(true);
/* 56 */             card.setAngle(0.0F);
/* 57 */             card.drawScale = 0.12F;
/* 58 */             card.targetDrawScale = 0.75F;
/* 59 */             card.current_x = CardGroup.DRAW_PILE_X;
/* 60 */             card.current_y = CardGroup.DRAW_PILE_Y;
/* 61 */             this.p.drawPile.removeCard(card);
/* 62 */             AbstractDungeon.player.hand.addToTop(card);
/* 63 */             AbstractDungeon.player.hand.refreshHandLayout();
/* 64 */             AbstractDungeon.player.hand.applyPowers();
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/* 69 */       this.isDone = true;
/*    */     } 
/* 71 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\DrawPileToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */