/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class CacheAction extends AbstractGameAction {
/* 11 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CacheAction");
/* 12 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public CacheAction(int amount) {
/* 16 */     this.p = AbstractDungeon.player;
/* 17 */     setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, amount);
/* 18 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 19 */     this.duration = Settings.ACTION_DUR_MED;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == Settings.ACTION_DUR_MED) {
/* 25 */       if (AbstractDungeon.player.drawPile.size() <= 1) {
/* 26 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 30 */       if (this.amount == 1) {
/* 31 */         AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.drawPile, this.amount, TEXT[0], false);
/*    */       } else {
/* 33 */         if (AbstractDungeon.player.drawPile.size() > this.amount) {
/* 34 */           this.amount = AbstractDungeon.player.drawPile.size();
/*    */         }
/* 36 */         AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.drawPile, this.amount, TEXT[1], false);
/*    */       } 
/* 38 */       tickDuration();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 44 */     if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
/* 45 */       for (int i = AbstractDungeon.gridSelectScreen.selectedCards.size() - 1; i > -1; i--) {
/* 46 */         ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(i)).unhover();
/* 47 */         this.p.drawPile.moveToDeck(AbstractDungeon.gridSelectScreen.selectedCards.get(i), false);
/*    */       } 
/* 49 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*    */     } 
/*    */     
/* 52 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\CacheAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */