/*    */ package com.megacrit.cardcrawl.cards.green;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Tactician extends AbstractCard {
/* 12 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Tactician"); public static final String ID = "Tactician";
/*    */   
/*    */   public Tactician() {
/* 15 */     super("Tactician", cardStrings.NAME, "green/skill/tactician", -2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.GREEN, CardRarity.UNCOMMON, CardTarget.NONE);
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
/* 26 */     this.baseMagicNumber = 1;
/* 27 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
/* 37 */     this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnManualDiscard() {
/* 43 */     addToTop((AbstractGameAction)new GainEnergyAction(this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 48 */     return new Tactician();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 53 */     if (!this.upgraded) {
/* 54 */       upgradeName();
/* 55 */       upgradeMagicNumber(1);
/* 56 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 57 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\green\Tactician.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */