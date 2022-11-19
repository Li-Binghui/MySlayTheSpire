/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NightmarePower;
/*    */ 
/*    */ public class NightmareAction extends AbstractGameAction {
/* 15 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CopyAction");
/* 16 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   private AbstractPlayer p;
/*    */   public static int numDiscarded;
/* 20 */   private static final float DURATION = Settings.ACTION_DUR_XFAST;
/*    */   
/*    */   public NightmareAction(AbstractCreature target, AbstractCreature source, int amount) {
/* 23 */     setValues(target, source, amount);
/* 24 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 25 */     this.duration = DURATION;
/* 26 */     this.p = (AbstractPlayer)target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 31 */     if (this.duration == DURATION) {
/* 32 */       if (this.p.hand.isEmpty()) {
/* 33 */         this.isDone = true; return;
/*    */       } 
/* 35 */       if (this.p.hand.size() == 1) {
/* 36 */         addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.p, (AbstractCreature)this.p, (AbstractPower)new NightmarePower((AbstractCreature)this.p, this.amount, this.p.hand.getBottomCard())));
/* 37 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 40 */       AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
/* 41 */       tickDuration();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 47 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 48 */       AbstractCard tmpCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard();
/* 49 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.p, (AbstractCreature)this.p, (AbstractPower)new NightmarePower((AbstractCreature)this.p, this.amount, tmpCard)));
/* 50 */       AbstractDungeon.player.hand.addToHand(tmpCard);
/*    */       
/* 52 */       AbstractDungeon.handCardSelectScreen.selectedCards.clear();
/* 53 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*    */     } 
/*    */     
/* 56 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\NightmareAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */