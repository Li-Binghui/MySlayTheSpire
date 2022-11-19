/*    */ package com.megacrit.cardcrawl.cards.curses;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class AscendersBane extends AbstractCard {
/*    */   public static final String ID = "AscendersBane";
/* 11 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("AscendersBane");
/*    */   
/*    */   public AscendersBane() {
/* 14 */     super("AscendersBane", cardStrings.NAME, "curse/ascenders_bane", -2, cardStrings.DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 24 */     this.isEthereal = true;
/*    */   }
/*    */ 
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
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 39 */     return new AscendersBane();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\curses\AscendersBane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */