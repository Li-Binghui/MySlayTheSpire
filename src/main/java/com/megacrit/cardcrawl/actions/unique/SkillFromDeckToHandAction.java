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
/*    */ public class SkillFromDeckToHandAction
/*    */   extends AbstractGameAction {
/* 15 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SkillFromDeckToHandAction");
/* 16 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public SkillFromDeckToHandAction(int amount) {
/* 21 */     this.p = AbstractDungeon.player;
/* 22 */     setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, amount);
/* 23 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 24 */     this.duration = Settings.ACTION_DUR_MED;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     if (this.duration == Settings.ACTION_DUR_MED) {
/* 30 */       CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 31 */       for (AbstractCard c : this.p.drawPile.group) {
/* 32 */         if (c.type == AbstractCard.CardType.SKILL) {
/* 33 */           tmp.addToRandomSpot(c);
/*    */         }
/*    */       } 
/*    */       
/* 37 */       if (tmp.size() == 0) {
/* 38 */         this.isDone = true; return;
/*    */       } 
/* 40 */       if (tmp.size() == 1) {
/* 41 */         AbstractCard card = tmp.getTopCard();
/*    */         
/* 43 */         if (this.p.hand.size() == 10) {
/* 44 */           this.p.drawPile.moveToDiscardPile(card);
/* 45 */           this.p.createHandIsFullDialog();
/*    */         } else {
/* 47 */           card.unhover();
/* 48 */           card.lighten(true);
/* 49 */           card.setAngle(0.0F);
/* 50 */           card.drawScale = 0.12F;
/* 51 */           card.targetDrawScale = 0.75F;
/* 52 */           card.current_x = CardGroup.DRAW_PILE_X;
/* 53 */           card.current_y = CardGroup.DRAW_PILE_Y;
/* 54 */           this.p.drawPile.removeCard(card);
/* 55 */           AbstractDungeon.player.hand.addToTop(card);
/* 56 */           AbstractDungeon.player.hand.refreshHandLayout();
/* 57 */           AbstractDungeon.player.hand.applyPowers();
/*    */         } 
/* 59 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 63 */       AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
/* 64 */       tickDuration();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 70 */     if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
/* 71 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 72 */         c.unhover();
/*    */         
/* 74 */         if (this.p.hand.size() == 10) {
/* 75 */           this.p.drawPile.moveToDiscardPile(c);
/* 76 */           this.p.createHandIsFullDialog();
/*    */         } else {
/* 78 */           this.p.drawPile.removeCard(c);
/* 79 */           this.p.hand.addToTop(c);
/*    */         } 
/* 81 */         this.p.hand.refreshHandLayout();
/* 82 */         this.p.hand.applyPowers();
/*    */       } 
/* 84 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 85 */       this.p.hand.refreshHandLayout();
/*    */     } 
/*    */     
/* 88 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\SkillFromDeckToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */