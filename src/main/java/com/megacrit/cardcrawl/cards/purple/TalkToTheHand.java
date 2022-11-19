/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;
/*    */ 
/*    */ public class TalkToTheHand extends AbstractCard {
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("TalkToTheHand"); public static final String ID = "TalkToTheHand";
/*    */   
/*    */   public TalkToTheHand() {
/* 19 */     super("TalkToTheHand", cardStrings.NAME, "purple/attack/talk_to_the_hand", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 30 */     this.baseDamage = 5;
/* 31 */     this.baseMagicNumber = 2;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/* 33 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 39 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new BlockReturnPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 44 */     if (!this.upgraded) {
/* 45 */       upgradeName();
/* 46 */       upgradeDamage(2);
/* 47 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 53 */     return new TalkToTheHand();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\TalkToTheHand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */