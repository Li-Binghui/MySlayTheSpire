/*    */ package com.megacrit.cardcrawl.cards.green;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.EscapePlanAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EscapePlan extends AbstractCard {
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Escape Plan"); public static final String ID = "Escape Plan";
/*    */   
/*    */   public EscapePlan() {
/* 16 */     super("Escape Plan", cardStrings.NAME, "green/skill/escape_plan", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
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
/* 27 */     this.baseBlock = 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 32 */     addToBot((AbstractGameAction)new DrawCardAction(1, (AbstractGameAction)new EscapePlanAction(this.block)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 37 */     if (!this.upgraded) {
/* 38 */       upgradeName();
/* 39 */       upgradeBlock(2);
/* 40 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 46 */     return new EscapePlan();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\green\EscapePlan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */