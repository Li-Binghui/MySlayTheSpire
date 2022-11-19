/*    */ package com.megacrit.cardcrawl.cards.deprecated;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.RetreatingHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class DEPRECATEDRetreatingHand extends AbstractCard {
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("RetreatingHand"); public static final String ID = "RetreatingHand";
/*    */   
/*    */   public DEPRECATEDRetreatingHand() {
/* 17 */     super("RetreatingHand", cardStrings.NAME, null, 0, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
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
/* 28 */     this.isEthereal = true;
/* 29 */     this.baseBlock = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 34 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
/* 35 */     addToBot((AbstractGameAction)new RetreatingHandAction(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnGlowCheck() {
/* 40 */     if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat
/* 41 */       .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
/* 42 */         .size() - 1)).type == CardType.ATTACK) {
/* 43 */       this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
/*    */     } else {
/* 45 */       this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       this.isEthereal = false;
/* 54 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 55 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 61 */     return new DEPRECATEDRetreatingHand();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDRetreatingHand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */