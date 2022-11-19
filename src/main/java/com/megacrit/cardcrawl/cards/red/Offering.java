/*    */ package com.megacrit.cardcrawl.cards.red;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
/*    */ 
/*    */ public class Offering extends AbstractCard {
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Offering"); public static final String ID = "Offering";
/*    */   
/*    */   public Offering() {
/* 20 */     super("Offering", cardStrings.NAME, "red/skill/offering", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.RED, CardRarity.RARE, CardTarget.SELF);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     this.exhaust = true;
/* 31 */     this.baseMagicNumber = 3;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     if (Settings.FAST_MODE) {
/* 38 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new OfferingEffect(), 0.1F));
/*    */     } else {
/* 40 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new OfferingEffect(), 0.5F));
/*    */     } 
/* 42 */     addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)p, (AbstractCreature)p, 6));
/* 43 */     addToBot((AbstractGameAction)new GainEnergyAction(2));
/* 44 */     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeMagicNumber(2);
/* 52 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 53 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 59 */     return new Offering();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\Offering.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */