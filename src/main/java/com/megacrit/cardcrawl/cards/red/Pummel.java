/*    */ package com.megacrit.cardcrawl.cards.red;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Pummel extends AbstractCard {
/* 15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Pummel"); public static final String ID = "Pummel";
/*    */   
/*    */   public Pummel() {
/* 18 */     super("Pummel", cardStrings.NAME, "red/attack/pummel", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 29 */     this.baseDamage = 2;
/* 30 */     this.exhaust = true;
/* 31 */     this.baseMagicNumber = 4;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     for (int i = 1; i < this.magicNumber; i++) {
/* 38 */       addToBot((AbstractGameAction)new PummelDamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn)));
/*    */     }
/*    */     
/* 41 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 46 */     if (!this.upgraded) {
/* 47 */       upgradeName();
/* 48 */       upgradeMagicNumber(1);
/* 49 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 50 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 56 */     return new Pummel();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\Pummel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */