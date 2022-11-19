/*    */ package com.megacrit.cardcrawl.cards.colorless;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.SkillFromDeckToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class SecretTechnique extends AbstractCard {
/* 12 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Secret Technique"); public static final String ID = "Secret Technique";
/*    */   
/*    */   public SecretTechnique() {
/* 15 */     super("Secret Technique", cardStrings.NAME, "colorless/skill/secret_technique", 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.RARE, CardTarget.NONE);
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
/* 26 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 31 */     addToBot((AbstractGameAction)new SkillFromDeckToHandAction(1));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
/* 36 */     boolean canUse = super.canUse(p, m);
/* 37 */     if (!canUse) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     boolean hasSkill = false;
/* 42 */     for (AbstractCard c : p.drawPile.group) {
/* 43 */       if (c.type == CardType.SKILL) {
/* 44 */         hasSkill = true;
/*    */       }
/*    */     } 
/*    */     
/* 48 */     if (!hasSkill) {
/* 49 */       this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
/* 50 */       canUse = false;
/*    */     } 
/*    */     
/* 53 */     return canUse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 58 */     if (!this.upgraded) {
/* 59 */       upgradeName();
/* 60 */       this.exhaust = false;
/* 61 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 62 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 68 */     return new SecretTechnique();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\colorless\SecretTechnique.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */