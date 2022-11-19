/*    */ package com.megacrit.cardcrawl.cards.deprecated;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class DEPRECATEDNothingness extends AbstractCard {
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Nothingness");
/*    */   public static final String ID = "Nothingness";
/* 16 */   public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
/*    */   
/*    */   private static final int COST = 1;
/*    */   
/*    */   public DEPRECATEDNothingness() {
/* 21 */     super("Nothingness", cardStrings.NAME, "colorless/skill/purity", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
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
/*    */   public static int countCards() {
/* 34 */     int count = 0;
/* 35 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 36 */       if (isEmpty(c)) {
/* 37 */         count++;
/*    */       }
/*    */     } 
/* 40 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 41 */       if (isEmpty(c)) {
/* 42 */         count++;
/*    */       }
/*    */     } 
/* 45 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 46 */       if (isEmpty(c)) {
/* 47 */         count++;
/*    */       }
/*    */     } 
/* 50 */     return count;
/*    */   }
/*    */   
/*    */   public static boolean isEmpty(AbstractCard c) {
/* 54 */     return c.hasTag(CardTags.EMPTY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 59 */     if (this.upgraded) {
/* 60 */       addToBot((AbstractGameAction)new ScryAction(countCards()));
/*    */     }
/* 62 */     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, countCards()));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 67 */     return new DEPRECATEDNothingness();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 72 */     if (!this.upgraded) {
/* 73 */       upgradeName();
/* 74 */       this.rawDescription = UPGRADE_DESCRIPTION;
/* 75 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDNothingness.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */