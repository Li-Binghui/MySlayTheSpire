/*    */ package com.megacrit.cardcrawl.cards.tempCards;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class Shiv extends AbstractCard {
/*    */   public static final String ID = "Shiv";
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Shiv");
/*    */   public static final int ATTACK_DMG = 4;
/*    */   
/*    */   public Shiv() {
/* 20 */     super("Shiv", cardStrings.NAME, "colorless/attack/shiv", 0, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
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
/* 31 */     if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("Accuracy")) {
/* 32 */       this.baseDamage = 4 + (AbstractDungeon.player.getPower("Accuracy")).amount;
/*    */     } else {
/* 34 */       this.baseDamage = 4;
/*    */     } 
/* 36 */     this.exhaust = true;
/*    */   }
/*    */   public static final int UPG_ATTACK_DMG = 2;
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 41 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 46 */     return new Shiv();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\tempCards\Shiv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */