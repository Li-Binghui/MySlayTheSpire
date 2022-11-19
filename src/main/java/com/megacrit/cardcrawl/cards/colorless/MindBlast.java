/*    */ package com.megacrit.cardcrawl.cards.colorless;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
/*    */ 
/*    */ public class MindBlast extends AbstractCard {
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Mind Blast"); public static final String ID = "Mind Blast";
/*    */   
/*    */   public MindBlast() {
/* 21 */     super("Mind Blast", cardStrings.NAME, "colorless/attack/mind_blast", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 32 */     this.isInnate = true;
/* 33 */     this.baseDamage = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal)));
/* 39 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
/*    */     
/* 41 */     this.rawDescription = cardStrings.DESCRIPTION;
/* 42 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 47 */     this.baseDamage = AbstractDungeon.player.drawPile.size();
/* 48 */     super.applyPowers();
/* 49 */     this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
/* 50 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 55 */     super.calculateCardDamage(mo);
/* 56 */     this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
/* 57 */     initializeDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 62 */     if (!this.upgraded) {
/* 63 */       upgradeName();
/* 64 */       upgradeBaseCost(1);
/* 65 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 71 */     return new MindBlast();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\colorless\MindBlast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */