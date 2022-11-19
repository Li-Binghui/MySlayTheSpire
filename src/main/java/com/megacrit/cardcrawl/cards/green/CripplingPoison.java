/*    */ package com.megacrit.cardcrawl.cards.green;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PoisonPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class CripplingPoison extends AbstractCard {
/* 15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Crippling Poison"); public static final String ID = "Crippling Poison";
/*    */   
/*    */   public CripplingPoison() {
/* 18 */     super("Crippling Poison", cardStrings.NAME, "green/skill/crippling_poison", 2, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.GREEN, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
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
/* 29 */     this.baseMagicNumber = 4;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/* 31 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 37 */       flash();
/* 38 */       for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
/* 39 */         if (!monster.isDead && !monster.isDying) {
/* 40 */           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)monster, (AbstractCreature)p, (AbstractPower)new PoisonPower((AbstractCreature)monster, (AbstractCreature)p, this.magicNumber), this.magicNumber));
/* 41 */           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)monster, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)monster, 2, false), 2));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeMagicNumber(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 57 */     return new CripplingPoison();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\green\CripplingPoison.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */