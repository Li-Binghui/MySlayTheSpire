/*    */ package com.megacrit.cardcrawl.cards.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.GainStrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ public class DEPRECATEDPeace extends AbstractCard {
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Peace"); public static final String ID = "Peace";
/*    */   
/*    */   public DEPRECATEDPeace() {
/* 20 */     super("Peace", cardStrings.NAME, null, 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
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
/* 31 */     this.baseMagicNumber = 5;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/* 33 */     this.selfRetain = true;
/* 34 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 39 */     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 40 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)mo, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 50 */     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 51 */       if (!mo.hasPower("Artifact")) {
/* 52 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new GainStrengthPower((AbstractCreature)mo, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
/*    */       }
/*    */     } 
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
/*    */   public AbstractCard makeCopy() {
/* 66 */     return new DEPRECATEDPeace();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 71 */     if (!this.upgraded) {
/* 72 */       upgradeName();
/* 73 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDPeace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */