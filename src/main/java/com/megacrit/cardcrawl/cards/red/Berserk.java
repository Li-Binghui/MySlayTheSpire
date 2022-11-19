/*    */ package com.megacrit.cardcrawl.cards.red;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.BerserkPower;
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ 
/*    */ public class Berserk extends AbstractCard {
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Berserk"); public static final String ID = "Berserk";
/*    */   
/*    */   public Berserk() {
/* 17 */     super("Berserk", cardStrings.NAME, "red/power/berserk", 0, cardStrings.DESCRIPTION, CardType.POWER, CardColor.RED, CardRarity.RARE, CardTarget.SELF);
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
/* 28 */     this.baseMagicNumber = 2;
/* 29 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)p, this.magicNumber, false), this.magicNumber));
/* 35 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new BerserkPower((AbstractCreature)p, 1), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 40 */     if (!this.upgraded) {
/* 41 */       upgradeName();
/* 42 */       upgradeMagicNumber(-1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 48 */     return new Berserk();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\Berserk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */