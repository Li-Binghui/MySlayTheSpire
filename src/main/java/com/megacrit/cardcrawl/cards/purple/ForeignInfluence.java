/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ForeignInfluenceAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class ForeignInfluence extends AbstractCard {
/* 12 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ForeignInfluence"); public static final String ID = "ForeignInfluence";
/*    */   
/*    */   public ForeignInfluence() {
/* 15 */     super("ForeignInfluence", cardStrings.NAME, "purple/skill/foreign_influence", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.NONE);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     addToBot((AbstractGameAction)new ForeignInfluenceAction(this.upgraded));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 35 */     if (!this.upgraded) {
/* 36 */       upgradeName();
/* 37 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 38 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 44 */     return new ForeignInfluence();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\ForeignInfluence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */