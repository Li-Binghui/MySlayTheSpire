/*    */ package com.megacrit.cardcrawl.cards.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class DEPRECATEDStepAndStrike extends AbstractCard {
/* 15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("StepAndStrike"); public static final String ID = "StepAndStrike";
/*    */   
/*    */   public DEPRECATEDStepAndStrike() {
/* 18 */     super("StepAndStrike", cardStrings.NAME, "purple/attack/step_and_strike", 3, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
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
/* 29 */     this.baseDamage = 8;
/* 30 */     this.baseBlock = 8;
/* 31 */     this.tags.add(CardTags.STRIKE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void switchedStance() {
/* 36 */     setCostForTurn(this.costForTurn - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerWhenDrawn() {
/* 41 */     super.triggerWhenDrawn();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 47 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
/* 48 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 53 */     if (!this.upgraded) {
/* 54 */       upgradeName();
/* 55 */       upgradeDamage(2);
/* 56 */       upgradeBlock(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 62 */     return new DEPRECATEDStepAndStrike();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDStepAndStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */