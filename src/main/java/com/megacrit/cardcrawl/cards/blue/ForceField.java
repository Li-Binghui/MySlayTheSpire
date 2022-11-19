/*    */ package com.megacrit.cardcrawl.cards.blue;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class ForceField extends AbstractCard {
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Force Field"); public static final String ID = "Force Field";
/*    */   
/*    */   public ForceField() {
/* 16 */     super("Force Field", cardStrings.NAME, "blue/skill/forcefield", 4, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
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
/* 27 */     this.baseBlock = 12;
/*    */     
/* 29 */     if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null) {
/* 30 */       configureCostsOnNewCard();
/*    */     }
/*    */   }
/*    */   
/*    */   public void configureCostsOnNewCard() {
/* 35 */     for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
/* 36 */       if (c.type == CardType.POWER) {
/* 37 */         updateCost(-1);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnCardPlayed(AbstractCard c) {
/* 44 */     if (c.type == CardType.POWER) {
/* 45 */       updateCost(-1);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 51 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 56 */     if (!this.upgraded) {
/* 57 */       upgradeName();
/* 58 */       upgradeBlock(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 64 */     return new ForceField();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\ForceField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */