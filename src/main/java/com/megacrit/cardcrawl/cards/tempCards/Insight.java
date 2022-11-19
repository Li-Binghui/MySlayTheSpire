/*    */ package com.megacrit.cardcrawl.cards.tempCards;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
/*    */ 
/*    */ public class Insight extends AbstractCard {
/* 15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Insight"); public static final String ID = "Insight";
/*    */   
/*    */   public Insight() {
/* 18 */     super("Insight", cardStrings.NAME, "colorless/skill/insight", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
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
/* 29 */     this.baseMagicNumber = 2;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/* 31 */     this.exhaust = true;
/* 32 */     this.selfRetain = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     if (Settings.FAST_MODE) {
/* 38 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightBulbEffect(p.hb)));
/*    */     } else {
/* 40 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightBulbEffect(p.hb), 0.2F));
/*    */     } 
/*    */     
/* 43 */     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 48 */     if (!this.upgraded) {
/* 49 */       upgradeName();
/* 50 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 56 */     return new Insight();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\tempCards\Insight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */