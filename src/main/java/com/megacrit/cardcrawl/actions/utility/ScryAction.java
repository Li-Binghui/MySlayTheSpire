/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class ScryAction
/*    */   extends AbstractGameAction
/*    */ {
/* 15 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ReprogramAction");
/* 16 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   private float startingDuration;
/*    */   
/*    */   public ScryAction(int numCards) {
/* 20 */     this.amount = numCards;
/*    */     
/* 22 */     if (AbstractDungeon.player.hasRelic("GoldenEye")) {
/* 23 */       AbstractDungeon.player.getRelic("GoldenEye").flash();
/* 24 */       this.amount += 2;
/*    */     } 
/*    */     
/* 27 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 28 */     this.startingDuration = Settings.ACTION_DUR_FAST;
/* 29 */     this.duration = this.startingDuration;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 34 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 35 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 39 */     if (this.duration == this.startingDuration) {
/* 40 */       for (AbstractPower p : AbstractDungeon.player.powers) {
/* 41 */         p.onScry();
/*    */       }
/* 43 */       if (AbstractDungeon.player.drawPile.isEmpty()) {
/* 44 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 47 */       CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 48 */       if (this.amount != -1) {
/* 49 */         for (int i = 0; i < Math.min(this.amount, AbstractDungeon.player.drawPile.size()); i++) {
/* 50 */           tmpGroup.addToTop(AbstractDungeon.player.drawPile.group
/* 51 */               .get(AbstractDungeon.player.drawPile.size() - i - 1));
/*    */         }
/*    */       } else {
/* 54 */         for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 55 */           tmpGroup.addToBottom(c);
/*    */         }
/*    */       } 
/* 58 */       AbstractDungeon.gridSelectScreen.open(tmpGroup, this.amount, true, TEXT[0]);
/*    */     }
/* 60 */     else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 61 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 62 */         AbstractDungeon.player.drawPile.moveToDiscardPile(c);
/*    */       }
/* 64 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */     } 
/* 66 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 67 */       c.triggerOnScry();
/*    */     }
/* 69 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ScryAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */