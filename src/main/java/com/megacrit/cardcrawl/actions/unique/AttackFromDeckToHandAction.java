/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class AttackFromDeckToHandAction
/*    */   extends AbstractGameAction {
/* 15 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AttackFromDeckToHandAction");
/* 16 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public AttackFromDeckToHandAction(int amount) {
/* 20 */     this.p = AbstractDungeon.player;
/* 21 */     setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, amount);
/* 22 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 23 */     this.duration = Settings.ACTION_DUR_MED;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     if (this.duration == Settings.ACTION_DUR_MED) {
/* 29 */       CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 30 */       for (AbstractCard c : this.p.drawPile.group) {
/* 31 */         if (c.type == AbstractCard.CardType.ATTACK) {
/* 32 */           tmp.addToRandomSpot(c);
/*    */         }
/*    */       } 
/*    */       
/* 36 */       if (tmp.size() == 0) {
/* 37 */         this.isDone = true; return;
/*    */       } 
/* 39 */       if (tmp.size() == 1) {
/* 40 */         AbstractCard card = tmp.getTopCard();
/*    */         
/* 42 */         if (this.p.hand.size() == 10) {
/* 43 */           this.p.drawPile.moveToDiscardPile(card);
/* 44 */           this.p.createHandIsFullDialog();
/*    */         } else {
/* 46 */           card.unhover();
/* 47 */           card.lighten(true);
/* 48 */           card.setAngle(0.0F);
/* 49 */           card.drawScale = 0.12F;
/* 50 */           card.targetDrawScale = 0.75F;
/* 51 */           card.current_x = CardGroup.DRAW_PILE_X;
/* 52 */           card.current_y = CardGroup.DRAW_PILE_Y;
/* 53 */           this.p.drawPile.removeCard(card);
/* 54 */           AbstractDungeon.player.hand.addToTop(card);
/* 55 */           AbstractDungeon.player.hand.refreshHandLayout();
/* 56 */           AbstractDungeon.player.hand.applyPowers();
/*    */         } 
/* 58 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 62 */       AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
/* 63 */       tickDuration();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 69 */     if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
/* 70 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 71 */         c.unhover();
/*    */         
/* 73 */         if (this.p.hand.size() == 10) {
/* 74 */           this.p.drawPile.moveToDiscardPile(c);
/* 75 */           this.p.createHandIsFullDialog();
/*    */         } else {
/* 77 */           this.p.drawPile.removeCard(c);
/* 78 */           this.p.hand.addToTop(c);
/*    */         } 
/* 80 */         this.p.hand.refreshHandLayout();
/* 81 */         this.p.hand.applyPowers();
/*    */       } 
/* 83 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 84 */       this.p.hand.refreshHandLayout();
/*    */     } 
/*    */     
/* 87 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\AttackFromDeckToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */