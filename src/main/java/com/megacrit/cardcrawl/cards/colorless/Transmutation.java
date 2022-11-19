/*    */ package com.megacrit.cardcrawl.cards.colorless;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.TransmutationAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class Transmutation extends AbstractCard {
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Transmutation"); public static final String ID = "Transmutation";
/*    */   
/*    */   public Transmutation() {
/* 16 */     super("Transmutation", cardStrings.NAME, "colorless/skill/transmutation", -1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.RARE, CardTarget.SELF);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     if (this.energyOnUse < EnergyPanel.totalCount) {
/* 34 */       this.energyOnUse = EnergyPanel.totalCount;
/*    */     }
/*    */     
/* 37 */     addToBot((AbstractGameAction)new TransmutationAction(p, this.upgraded, this.freeToPlayOnce, this.energyOnUse));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 42 */     return new Transmutation();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 50 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\colorless\Transmutation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */