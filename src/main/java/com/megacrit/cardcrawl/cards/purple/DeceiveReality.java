/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.tempCards.Safety;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class DeceiveReality extends AbstractCard {
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DeceiveReality"); public static final String ID = "DeceiveReality";
/*    */   
/*    */   public DeceiveReality() {
/* 17 */     super("DeceiveReality", cardStrings.NAME, "purple/skill/deceive_reality", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
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
/* 28 */     this.baseBlock = 4;
/* 29 */     this.cardsToPreview = (AbstractCard)new Safety();
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
/* 35 */     addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 40 */     if (!this.upgraded) {
/* 41 */       upgradeName();
/* 42 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 48 */     return new DeceiveReality();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\DeceiveReality.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */