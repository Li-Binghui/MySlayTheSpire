/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class OmniscienceAction
/*    */   extends AbstractGameAction {
/* 14 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("WishAction")).TEXT;
/*    */   private AbstractPlayer player;
/*    */   private int playAmt;
/*    */   
/*    */   public OmniscienceAction(int numberOfCards) {
/* 19 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 20 */     this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
/* 21 */     this.player = AbstractDungeon.player;
/* 22 */     this.playAmt = numberOfCards;
/*    */   }
/*    */   
/*    */   public void update() {
/* 26 */     if (this.duration == this.startDuration) {
/* 27 */       if (this.player.drawPile.isEmpty()) {
/* 28 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 31 */       CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 32 */       for (AbstractCard c : this.player.drawPile.group) {
/* 33 */         temp.addToTop(c);
/*    */       }
/* 35 */       temp.sortAlphabetically(true);
/* 36 */       temp.sortByRarityPlusStatusCardType(false);
/* 37 */       AbstractDungeon.gridSelectScreen.open(temp, 1, TEXT[0], false);
/* 38 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/* 42 */     if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 43 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 44 */         c.exhaust = true;
/* 45 */         AbstractDungeon.player.drawPile.group.remove(c);
/* 46 */         (AbstractDungeon.getCurrRoom()).souls.remove(c);
/*    */ 
/*    */ 
/*    */         
/* 50 */         addToBot((AbstractGameAction)new NewQueueCardAction(c, true, false, true));
/*    */ 
/*    */         
/* 53 */         for (int i = 0; i < this.playAmt - 1; i++) {
/* 54 */           AbstractCard tmp = c.makeStatEquivalentCopy();
/* 55 */           tmp.purgeOnUse = true;
/* 56 */           addToBot((AbstractGameAction)new NewQueueCardAction(tmp, true, false, true));
/*    */         } 
/*    */       } 
/* 59 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 60 */       AbstractDungeon.player.hand.refreshHandLayout();
/*    */     } 
/* 62 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\OmniscienceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */