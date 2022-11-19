/*    */ package com.megacrit.cardcrawl.cards.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class DEPRECATEDChooseCourage extends AbstractCard {
/*    */   public static final String ID = "Joy";
/* 13 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Joy");
/*    */   
/*    */   public DEPRECATEDChooseCourage() {
/* 16 */     super("Joy", cardStrings.NAME, "red/skill/warcry", -2, cardStrings.DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.NONE);
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
/* 33 */     addToBot((AbstractGameAction)new ChangeStanceAction("Calm"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 43 */     return new DEPRECATEDChooseCourage();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDChooseCourage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */