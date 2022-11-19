/*    */ package com.megacrit.cardcrawl.cards.red;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.BarricadePower;
/*    */ 
/*    */ public class Barricade extends AbstractCard {
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Barricade"); public static final String ID = "Barricade";
/*    */   
/*    */   public Barricade() {
/* 17 */     super("Barricade", cardStrings.NAME, "red/power/barricade", 3, cardStrings.DESCRIPTION, CardType.POWER, CardColor.RED, CardRarity.RARE, CardTarget.SELF);
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
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 31 */     boolean powerExists = false;
/* 32 */     for (AbstractPower pow : p.powers) {
/* 33 */       if (pow.ID.equals("Barricade")) {
/* 34 */         powerExists = true;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 39 */     if (!powerExists) {
/* 40 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new BarricadePower((AbstractCreature)p)));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 46 */     if (!this.upgraded) {
/* 47 */       upgradeName();
/* 48 */       upgradeBaseCost(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 54 */     return new Barricade();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\Barricade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */