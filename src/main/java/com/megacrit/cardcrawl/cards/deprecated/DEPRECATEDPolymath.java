/*    */ package com.megacrit.cardcrawl.cards.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.optionCards.ChooseCalm;
/*    */ import com.megacrit.cardcrawl.cards.optionCards.ChooseWrath;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class DEPRECATEDPolymath extends AbstractCard {
/*    */   public static final String ID = "Polymath";
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Polymath");
/*    */   
/*    */   public DEPRECATEDPolymath() {
/* 19 */     super("Polymath", cardStrings.NAME, null, 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
/* 35 */     stanceChoices.add(new ChooseWrath());
/* 36 */     stanceChoices.add(new ChooseCalm());
/* 37 */     addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 43 */     if (!this.upgraded) {
/* 44 */       upgradeName();
/* 45 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 51 */     return new DEPRECATEDPolymath();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDPolymath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */