/*    */ package com.megacrit.cardcrawl.cards.blue;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.ChannelAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.orbs.Lightning;
/*    */ 
/*    */ public class Zap extends AbstractCard {
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Zap"); public static final String ID = "Zap";
/*    */   
/*    */   public Zap() {
/* 16 */     super("Zap", cardStrings.NAME, "blue/skill/zap", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.BASIC, CardTarget.SELF);
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
/* 27 */     this.showEvokeValue = true;
/* 28 */     this.showEvokeOrbCount = 1;
/* 29 */     this.baseMagicNumber = 1;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     for (int i = 0; i < this.magicNumber; i++) {
/* 36 */       addToBot((AbstractGameAction)new ChannelAction((AbstractOrb)new Lightning()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 42 */     if (!this.upgraded) {
/* 43 */       upgradeName();
/* 44 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 50 */     return new Zap();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\Zap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */