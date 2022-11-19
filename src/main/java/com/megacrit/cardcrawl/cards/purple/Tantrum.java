/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Tantrum extends AbstractCard {
/*    */   public static final String ID = "Tantrum";
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Tantrum");
/*    */   
/*    */   public Tantrum() {
/* 19 */     super("Tantrum", cardStrings.NAME, "purple/attack/tantrum", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 30 */     this.baseDamage = 3;
/* 31 */     this.baseMagicNumber = 3;
/* 32 */     this.magicNumber = 3;
/* 33 */     this.shuffleBackIntoDrawPile = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     for (int i = 0; i < this.magicNumber; i++) {
/* 39 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */     }
/* 41 */     addToBot((AbstractGameAction)new ChangeStanceAction("Wrath"));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 46 */     if (!this.upgraded) {
/* 47 */       upgradeName();
/* 48 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 54 */     return new Tantrum();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\Tantrum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */