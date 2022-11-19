/*    */ package com.megacrit.cardcrawl.cards.curses;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class Doubt extends AbstractCard {
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Doubt"); public static final String ID = "Doubt";
/*    */   
/*    */   public Doubt() {
/* 19 */     super("Doubt", cardStrings.NAME, "curse/doubt", -2, cardStrings.DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.CURSE, CardTarget.NONE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     if (this.dontTriggerOnUseCard) {
/* 34 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, 1, true), 1));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void triggerWhenDrawn() {
/* 45 */     addToBot((AbstractGameAction)new SetDontTriggerAction(this, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 50 */     this.dontTriggerOnUseCard = true;
/* 51 */     AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 61 */     return new Doubt();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\curses\Doubt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */