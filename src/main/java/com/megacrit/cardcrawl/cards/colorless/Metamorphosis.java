/*    */ package com.megacrit.cardcrawl.cards.colorless;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Metamorphosis extends AbstractCard {
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Metamorphosis"); public static final String ID = "Metamorphosis";
/*    */   
/*    */   public Metamorphosis() {
/* 16 */     super("Metamorphosis", cardStrings.NAME, "colorless/skill/metamorphosis", 2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.RARE, CardTarget.NONE);
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
/* 27 */     this.baseMagicNumber = 3;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/* 29 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     for (int i = 0; i < this.magicNumber; i++) {
/* 35 */       AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();
/* 36 */       if (card.cost > 0) {
/* 37 */         card.cost = 0;
/* 38 */         card.costForTurn = 0;
/* 39 */         card.isCostModified = true;
/*    */       } 
/* 41 */       addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(card, 1, true, true));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       upgradeMagicNumber(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 55 */     return new Metamorphosis();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\colorless\Metamorphosis.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */