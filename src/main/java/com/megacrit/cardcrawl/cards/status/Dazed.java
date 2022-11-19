/*    */ package com.megacrit.cardcrawl.cards.status;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Dazed extends AbstractCard {
/*    */   public static final String ID = "Dazed";
/* 11 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Dazed");
/*    */   
/*    */   public Dazed() {
/* 14 */     super("Dazed", cardStrings.NAME, "status/dazed", -2, cardStrings.DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
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
/* 25 */     this.isEthereal = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 38 */     return new Dazed();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\status\Dazed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */