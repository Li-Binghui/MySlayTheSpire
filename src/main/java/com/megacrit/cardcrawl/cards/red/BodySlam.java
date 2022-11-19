/*    */ package com.megacrit.cardcrawl.cards.red;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class BodySlam extends AbstractCard {
/*    */   public static final String ID = "Body Slam";
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Body Slam");
/*    */   
/*    */   public BodySlam() {
/* 19 */     super("Body Slam", cardStrings.NAME, "red/attack/body_slam", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ENEMY);
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
/* 30 */     this.baseDamage = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     this.baseDamage = p.currentBlock;
/* 36 */     calculateCardDamage(m);
/* 37 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 38 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 39 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 44 */     this.baseDamage = AbstractDungeon.player.currentBlock;
/* 45 */     super.applyPowers();
/*    */     
/* 47 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 48 */     this.rawDescription += cardStrings.UPGRADE_DESCRIPTION;
/* 49 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onMoveToDiscard() {
/* 54 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 55 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 60 */     super.calculateCardDamage(mo);
/* 61 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 62 */     this.rawDescription += cardStrings.UPGRADE_DESCRIPTION;
/* 63 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 68 */     if (!this.upgraded) {
/* 69 */       upgradeName();
/* 70 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 76 */     return new BodySlam();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\BodySlam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */