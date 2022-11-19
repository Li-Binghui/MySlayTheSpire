/*    */ package com.megacrit.cardcrawl.cards.curses;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Normality extends AbstractCard {
/*    */   public static final String ID = "Normality";
/* 12 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Normality");
/*    */   
/*    */   private static final int PLAY_LIMIT = 3;
/*    */   
/*    */   public Normality() {
/* 17 */     super("Normality", cardStrings.NAME, "curse/normality", -2, cardStrings.DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.CURSE, CardTarget.NONE);
/*    */   }
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
/*    */   public boolean canPlay(AbstractCard card) {
/* 30 */     if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 3) {
/* 31 */       card.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
/* 32 */       return false;
/*    */     } 
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 44 */     super.applyPowers();
/*    */     
/* 46 */     if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0) {
/* 47 */       this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1] + '\003' + cardStrings.EXTENDED_DESCRIPTION[2];
/* 48 */     } else if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
/* 49 */       this
/* 50 */         .rawDescription = cardStrings.EXTENDED_DESCRIPTION[1] + '\003' + cardStrings.EXTENDED_DESCRIPTION[3] + AbstractDungeon.actionManager.cardsPlayedThisTurn.size() + cardStrings.EXTENDED_DESCRIPTION[4];
/*    */     } else {
/* 52 */       this
/* 53 */         .rawDescription = cardStrings.EXTENDED_DESCRIPTION[1] + '\003' + cardStrings.EXTENDED_DESCRIPTION[3] + AbstractDungeon.actionManager.cardsPlayedThisTurn.size() + cardStrings.EXTENDED_DESCRIPTION[5];
/*    */     } 
/* 55 */     initializeDescription();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 65 */     return new Normality();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\curses\Normality.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */