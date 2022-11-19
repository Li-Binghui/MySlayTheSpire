/*    */ package com.megacrit.cardcrawl.cards.blue;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.NewThunderStrikeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ public class ThunderStrike extends AbstractCard {
/*    */   public static final String ID = "Thunder Strike";
/* 15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Thunder Strike");
/*    */   
/*    */   public ThunderStrike() {
/* 18 */     super("Thunder Strike", cardStrings.NAME, "blue/attack/thunder_strike", 3, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.BLUE, CardRarity.RARE, CardTarget.ALL_ENEMY);
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
/* 29 */     this.baseMagicNumber = 0;
/* 30 */     this.magicNumber = 0;
/* 31 */     this.baseDamage = 7;
/* 32 */     this.tags.add(CardTags.STRIKE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     this.baseMagicNumber = 0;
/* 38 */     for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
/* 39 */       if (o instanceof com.megacrit.cardcrawl.orbs.Lightning) {
/* 40 */         this.baseMagicNumber++;
/*    */       }
/*    */     } 
/* 43 */     this.magicNumber = this.baseMagicNumber;
/*    */     
/* 45 */     for (int i = 0; i < this.magicNumber; i++) {
/* 46 */       addToBot((AbstractGameAction)new NewThunderStrikeAction(this));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 52 */     super.applyPowers();
/*    */     
/* 54 */     this.baseMagicNumber = 0;
/* 55 */     this.magicNumber = 0;
/* 56 */     for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
/* 57 */       if (o instanceof com.megacrit.cardcrawl.orbs.Lightning) {
/* 58 */         this.baseMagicNumber++;
/*    */       }
/*    */     } 
/*    */     
/* 62 */     if (this.baseMagicNumber > 0) {
/* 63 */       this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
/* 64 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMoveToDiscard() {
/* 70 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 71 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 76 */     super.calculateCardDamage(mo);
/* 77 */     if (this.baseMagicNumber > 0) {
/* 78 */       this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
/*    */     }
/* 80 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 85 */     if (!this.upgraded) {
/* 86 */       upgradeName();
/* 87 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 93 */     return new ThunderStrike();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\ThunderStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */