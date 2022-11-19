/*    */ package com.megacrit.cardcrawl.cards.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class DEPRECATEDIntrospection extends AbstractCard {
/*    */   public static final String ID = "Introspection";
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Introspection");
/*    */   
/*    */   public DEPRECATEDIntrospection() {
/* 17 */     super("Introspection", cardStrings.NAME, null, 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.COMMON, CardTarget.SELF);
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
/* 28 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)p, (AbstractCreature)p, "Frail"));
/* 34 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)p, (AbstractCreature)p, "Vulnerable"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 41 */     if (!this.upgraded) {
/* 42 */       upgradeName();
/* 43 */       this.exhaust = false;
/* 44 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 45 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 51 */     return new DEPRECATEDIntrospection();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDIntrospection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */