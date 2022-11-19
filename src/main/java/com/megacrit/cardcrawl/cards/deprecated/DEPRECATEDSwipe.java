/*    */ package com.megacrit.cardcrawl.cards.deprecated;
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
/*    */ public class DEPRECATEDSwipe extends AbstractCard {
/* 14 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Swipe"); public static final String ID = "Swipe";
/*    */   
/*    */   public DEPRECATEDSwipe() {
/* 17 */     super("Swipe", cardStrings.NAME, "red/attack/cleave", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.COMMON, CardTarget.ENEMY);
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
/* 28 */     this.baseDamage = 14;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn)));
/*    */ 
/*    */     
/* 36 */     for (AbstractMonster mo : (AbstractDungeon.getMonsters()).monsters) {
/* 37 */       if (mo != m) {
/* 38 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mo, new DamageInfo((AbstractCreature)p, this.damage / 2, this.damageTypeForTurn)));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 45 */     if (!this.upgraded) {
/* 46 */       upgradeName();
/* 47 */       upgradeDamage(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 53 */     return new DEPRECATEDSwipe();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\deprecated\DEPRECATEDSwipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */