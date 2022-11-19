/*    */ package com.megacrit.cardcrawl.cards.blue;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.FTLAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class FTL extends AbstractCard {
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("FTL"); public static final String ID = "FTL";
/*    */   
/*    */   public FTL() {
/* 17 */     super("FTL", cardStrings.NAME, "blue/attack/ftl", 0, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 28 */     this.baseDamage = 5;
/* 29 */     this.baseMagicNumber = 3;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     addToBot((AbstractGameAction)new FTLAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), this.magicNumber));
/* 36 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 37 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 42 */     super.applyPowers();
/*    */     
/* 44 */     int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
/*    */     
/* 46 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 47 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
/*    */     
/* 49 */     if (count == 1) {
/* 50 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
/*    */     } else {
/* 52 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
/*    */     } 
/* 54 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMoveToDiscard() {
/* 59 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 60 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnGlowCheck() {
/* 65 */     this.glowColor = (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() < this.magicNumber) ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 72 */     if (!this.upgraded) {
/* 73 */       upgradeName();
/* 74 */       upgradeDamage(1);
/* 75 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 81 */     return new FTL();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\FTL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */