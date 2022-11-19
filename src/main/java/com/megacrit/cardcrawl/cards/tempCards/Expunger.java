/*    */ package com.megacrit.cardcrawl.cards.tempCards;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.ExpungeVFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
/*    */ 
/*    */ public class Expunger
/*    */   extends AbstractCard {
/*    */   public static final String ID = "Expunger";
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Expunger");
/*    */   
/*    */   public Expunger() {
/* 21 */     super("Expunger", cardStrings.NAME, "colorless/attack/expunger", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
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
/* 32 */     this.baseDamage = 9;
/*    */   }
/*    */   
/*    */   public void setX(int amount) {
/* 36 */     this.magicNumber = amount;
/*    */     
/* 38 */     if (this.upgraded) {
/* 39 */       this.magicNumber++;
/*    */     }
/*    */     
/* 42 */     this.baseMagicNumber = this.magicNumber;
/*    */     
/* 44 */     this.rawDescription = (this.baseMagicNumber == 1) ? cardStrings.EXTENDED_DESCRIPTION[1] : cardStrings.EXTENDED_DESCRIPTION[0];
/*    */ 
/*    */     
/* 47 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 52 */     for (int i = 0; i < this.magicNumber; i++) {
/* 53 */       addToBot((AbstractGameAction)new ExpungeVFXAction(m));
/* 54 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 60 */     if (!this.upgraded) {
/* 61 */       upgradeName();
/* 62 */       upgradeDamage(6);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 68 */     return new Expunger();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeStatEquivalentCopy() {
/* 74 */     AbstractCard card = super.makeStatEquivalentCopy();
/* 75 */     card.baseMagicNumber = this.baseMagicNumber;
/* 76 */     card.magicNumber = this.magicNumber;
/* 77 */     card.description = (ArrayList)this.description.clone();
/* 78 */     return card;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\tempCards\Expunger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */