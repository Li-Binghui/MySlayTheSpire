/*    */ package com.megacrit.cardcrawl.cards.green;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.FlechetteAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Flechettes extends AbstractCard {
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Flechettes"); public static final String ID = "Flechettes";
/*    */   
/*    */   public Flechettes() {
/* 17 */     super("Flechettes", cardStrings.NAME, "green/attack/flechettes", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.GREEN, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 28 */     this.baseDamage = 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new FlechetteAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn)));
/* 34 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 35 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 40 */     super.applyPowers();
/*    */     
/* 42 */     int count = 0;
/* 43 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 44 */       if (c.type == CardType.SKILL) {
/* 45 */         count++;
/*    */       }
/*    */     } 
/*    */     
/* 49 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 50 */     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
/* 51 */     if (count == 1) {
/* 52 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
/*    */     } else {
/* 54 */       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
/*    */     } 
/* 56 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMoveToDiscard() {
/* 61 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 62 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 67 */     if (!this.upgraded) {
/* 68 */       upgradeName();
/* 69 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 75 */     return new Flechettes();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\green\Flechettes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */