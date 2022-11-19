/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ConjureBladeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Expunger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class ConjureBlade extends AbstractCard {
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ConjureBlade"); public static final String ID = "ConjureBlade";
/*    */   
/*    */   public ConjureBlade() {
/* 16 */     super("ConjureBlade", cardStrings.NAME, "purple/skill/conjure_blade", -1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.RARE, CardTarget.SELF);
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
/* 27 */     this.cardsToPreview = (AbstractCard)new Expunger();
/* 28 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     if (this.upgraded) {
/* 34 */       addToBot((AbstractGameAction)new ConjureBladeAction(p, this.freeToPlayOnce, this.energyOnUse + 1));
/*    */     } else {
/* 36 */       addToBot((AbstractGameAction)new ConjureBladeAction(p, this.freeToPlayOnce, this.energyOnUse));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 42 */     if (!this.upgraded) {
/* 43 */       upgradeName();
/* 44 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 45 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 51 */     return new ConjureBlade();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\ConjureBlade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */