/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
/*    */ import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
/*    */ import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class Wish extends AbstractCard {
/*    */   public static final String ID = "Wish";
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Wish");
/*    */   
/*    */   public Wish() {
/* 20 */     super("Wish", cardStrings.NAME, "purple/skill/wish", 3, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.RARE, CardTarget.NONE);
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
/* 31 */     this.baseDamage = 3;
/* 32 */     this.baseMagicNumber = 25;
/* 33 */     this.magicNumber = 25;
/* 34 */     this.baseBlock = 6;
/* 35 */     this.exhaust = true;
/*    */     
/* 37 */     this.tags.add(CardTags.HEALING);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 42 */     ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
/* 43 */     stanceChoices.add(new BecomeAlmighty());
/* 44 */     stanceChoices.add(new FameAndFortune());
/* 45 */     stanceChoices.add(new LiveForever());
/*    */     
/* 47 */     if (this.upgraded) {
/* 48 */       for (AbstractCard c : stanceChoices) {
/* 49 */         c.upgrade();
/*    */       }
/*    */     }
/* 52 */     addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void applyPowers() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 67 */     if (!this.upgraded) {
/* 68 */       upgradeName();
/* 69 */       upgradeDamage(1);
/* 70 */       upgradeMagicNumber(5);
/* 71 */       upgradeBlock(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 77 */     return new Wish();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\Wish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */