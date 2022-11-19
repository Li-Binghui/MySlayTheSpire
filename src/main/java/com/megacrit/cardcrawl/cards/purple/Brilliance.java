/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Brilliance extends AbstractCard {
/* 15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Brilliance"); public static final String ID = "Brilliance";
/*    */   
/*    */   public Brilliance() {
/* 18 */     super("Brilliance", cardStrings.NAME, "purple/attack/brilliance", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.RARE, CardTarget.ENEMY);
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
/* 29 */     this.baseDamage = 12;
/* 30 */     this.baseMagicNumber = 0;
/* 31 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 36 */     int realBaseDamage = this.baseDamage;
/* 37 */     this.baseMagicNumber = AbstractDungeon.actionManager.mantraGained;
/* 38 */     this.baseDamage += this.baseMagicNumber;
/* 39 */     super.applyPowers();
/* 40 */     this.baseDamage = realBaseDamage;
/* 41 */     this.isDamageModified = (this.damage != this.baseDamage);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 47 */     this.baseMagicNumber = AbstractDungeon.actionManager.mantraGained;
/* 48 */     int realBaseDamage = this.baseDamage;
/* 49 */     this.baseDamage += this.baseMagicNumber;
/* 50 */     super.calculateCardDamage(mo);
/* 51 */     this.baseDamage = realBaseDamage;
/* 52 */     this.isDamageModified = (this.damage != this.baseDamage);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 57 */     this.damage += this.magicNumber;
/* 58 */     calculateCardDamage(m);
/* 59 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 64 */     if (!this.upgraded) {
/* 65 */       upgradeName();
/* 66 */       upgradeDamage(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 72 */     return new Brilliance();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\Brilliance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */