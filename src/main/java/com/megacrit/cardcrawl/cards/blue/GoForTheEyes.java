/*    */ package com.megacrit.cardcrawl.cards.blue;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.ForTheEyesAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class GoForTheEyes extends AbstractCard {
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Go for the Eyes"); public static final String ID = "Go for the Eyes";
/*    */   
/*    */   public GoForTheEyes() {
/* 19 */     super("Go for the Eyes", cardStrings.NAME, "blue/attack/go_for_the_eyes", 0, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.BLUE, CardRarity.COMMON, CardTarget.ENEMY);
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
/* 30 */     this.baseDamage = 3;
/* 31 */     this.baseMagicNumber = 1;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/* 38 */     addToBot((AbstractGameAction)new ForTheEyesAction(this.magicNumber, m));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnGlowCheck() {
/* 43 */     this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
/* 44 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 45 */       if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
/* 46 */         this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 54 */     if (!this.upgraded) {
/* 55 */       upgradeDamage(1);
/* 56 */       upgradeMagicNumber(1);
/* 57 */       upgradeName();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 63 */     return new GoForTheEyes();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\GoForTheEyes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */