/*    */ package com.megacrit.cardcrawl.cards.blue;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Stack extends AbstractCard {
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Stack"); public static final String ID = "Stack";
/*    */   
/*    */   public Stack() {
/* 16 */     super("Stack", cardStrings.NAME, "blue/skill/stack", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.COMMON, CardTarget.SELF);
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
/* 27 */     this.baseBlock = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 32 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
/*    */     
/* 34 */     if (!this.upgraded) {
/* 35 */       this.rawDescription = cardStrings.DESCRIPTION;
/*    */     } else {
/* 37 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/*    */     } 
/* 39 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 44 */     this.baseBlock = AbstractDungeon.player.discardPile.size();
/* 45 */     if (this.upgraded) {
/* 46 */       this.baseBlock += 3;
/*    */     }
/* 48 */     super.applyPowers();
/*    */     
/* 50 */     if (!this.upgraded) {
/* 51 */       this.rawDescription = cardStrings.DESCRIPTION;
/*    */     } else {
/* 53 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/*    */     } 
/* 55 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
/* 56 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 61 */     if (!this.upgraded) {
/* 62 */       upgradeName();
/* 63 */       upgradeBlock(3);
/* 64 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 65 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 71 */     return new Stack();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\Stack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */