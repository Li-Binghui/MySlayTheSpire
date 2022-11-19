/*    */ package com.megacrit.cardcrawl.cards.optionCards;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class ChooseWrath extends AbstractCard {
/*    */   public static final String ID = "Wrath";
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Wrath");
/*    */   
/*    */   public ChooseWrath() {
/* 16 */     super("Wrath", cardStrings.NAME, "colorless/skill/wrath", -2, cardStrings.DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onChoseThisOption() {
/* 33 */     addToBot((AbstractGameAction)new ChangeStanceAction("Wrath"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 42 */     return new ChooseWrath();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\optionCards\ChooseWrath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */