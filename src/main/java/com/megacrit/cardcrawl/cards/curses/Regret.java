/*    */ package com.megacrit.cardcrawl.cards.curses;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardQueueItem;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Regret extends AbstractCard {
/* 15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Regret"); public static final String ID = "Regret";
/*    */   
/*    */   public Regret() {
/* 18 */     super("Regret", cardStrings.NAME, "curse/regret", -2, cardStrings.DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.CURSE, CardTarget.NONE);
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
/* 32 */     if (this.dontTriggerOnUseCard) {
/* 33 */       addToTop((AbstractGameAction)new LoseHPAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.magicNumber, AbstractGameAction.AttackEffect.FIRE));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 39 */     this.dontTriggerOnUseCard = true;
/* 40 */     this.magicNumber = this.baseMagicNumber = AbstractDungeon.player.hand.size();
/* 41 */     AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 50 */     return new Regret();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\curses\Regret.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */