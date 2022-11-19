/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.stances.AbstractStance;
/*    */ 
/*    */ public class FlurryOfBlows extends AbstractCard {
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("FlurryOfBlows"); public static final String ID = "FlurryOfBlows";
/*    */   
/*    */   public FlurryOfBlows() {
/* 19 */     super("FlurryOfBlows", cardStrings.NAME, "purple/attack/flurry_of_blows", 0, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.COMMON, CardTarget.ENEMY);
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
/* 30 */     this.baseDamage = 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerExhaustedCardsOnStanceChange(AbstractStance newStance) {
/* 40 */     addToBot((AbstractGameAction)new DiscardToHandAction(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 45 */     if (!this.upgraded) {
/* 46 */       upgradeName();
/* 47 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 53 */     return new FlurryOfBlows();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\FlurryOfBlows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */