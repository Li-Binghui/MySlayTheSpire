/*    */ package com.megacrit.cardcrawl.cards.green;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.DamagePerAttackPlayedAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Finisher extends AbstractCard {
/* 15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Finisher"); public static final String ID = "Finisher";
/*    */   
/*    */   public Finisher() {
/* 18 */     super("Finisher", cardStrings.NAME, "green/attack/finisher", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.GREEN, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 29 */     this.baseDamage = 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new DamagePerAttackPlayedAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 40 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 45 */     super.applyPowers();
/*    */     
/* 47 */     int count = 0;
/* 48 */     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
/* 49 */       if (c.type == CardType.ATTACK) {
/* 50 */         count++;
/*    */       }
/*    */     } 
/*    */     
/* 54 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 55 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
/*    */     
/* 57 */     if (count == 1) {
/* 58 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
/*    */     } else {
/* 60 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
/*    */     } 
/* 62 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMoveToDiscard() {
/* 67 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 68 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 73 */     if (!this.upgraded) {
/* 74 */       upgradeName();
/* 75 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 81 */     return new Finisher();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\green\Finisher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */