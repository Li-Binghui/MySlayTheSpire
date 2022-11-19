/*    */ package com.megacrit.cardcrawl.cards.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.StanceCheckAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class DEPRECATEDBalancedViolence
/*    */   extends AbstractCard {
/*    */   public static final String ID = "DEPRECATEDBalancedViolence";
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DEPRECATEDBalancedViolence");
/*    */   
/*    */   public DEPRECATEDBalancedViolence() {
/* 21 */     super("DEPRECATEDBalancedViolence", cardStrings.NAME, null, 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     this.baseDamage = 7;
/* 32 */     this.baseBlock = 4;
/* 33 */     this.baseMagicNumber = 2;
/* 34 */     this.magicNumber = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 39 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/* 40 */     addToBot((AbstractGameAction)new StanceCheckAction("Wrath", (AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL)));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 45 */     addToBot((AbstractGameAction)new StanceCheckAction("Calm", (AbstractGameAction)new DrawCardAction(this.magicNumber)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 50 */     if (!this.upgraded) {
/* 51 */       upgradeName();
/* 52 */       upgradeDamage(3);
/* 53 */       upgradeBlock(2);
/* 54 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return new DEPRECATEDBalancedViolence();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDBalancedViolence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */