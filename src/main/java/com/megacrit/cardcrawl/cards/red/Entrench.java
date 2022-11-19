/*    */ package com.megacrit.cardcrawl.cards.red;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.DoubleYourBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Entrench extends AbstractCard {
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Entrench"); public static final String ID = "Entrench";
/*    */   
/*    */   public Entrench() {
/* 16 */     super("Entrench", cardStrings.NAME, "red/skill/entrench", 2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF);
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
/* 30 */     addToBot((AbstractGameAction)new DoubleYourBlockAction((AbstractCreature)p));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfPlayerTurn() {
/* 35 */     addToTop((AbstractGameAction)new ExhaustAllEtherealAction());
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 40 */     if (!this.upgraded) {
/* 41 */       upgradeName();
/* 42 */       upgradeBaseCost(1);
/* 43 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 44 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 50 */     return new Entrench();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\Entrench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */