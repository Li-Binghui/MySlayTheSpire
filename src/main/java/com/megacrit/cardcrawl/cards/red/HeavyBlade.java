/*    */ package com.megacrit.cardcrawl.cards.red;
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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
/*    */ 
/*    */ public class HeavyBlade extends AbstractCard {
/* 19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Heavy Blade"); public static final String ID = "Heavy Blade";
/*    */   
/*    */   public HeavyBlade() {
/* 22 */     super("Heavy Blade", cardStrings.NAME, "red/attack/heavy_blade", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ENEMY);
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
/* 33 */     this.baseDamage = 14;
/* 34 */     this.baseMagicNumber = 3;
/* 35 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 40 */     if (m != null) {
/* 41 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
/*    */     }
/* 43 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 48 */     AbstractPower strength = AbstractDungeon.player.getPower("Strength");
/* 49 */     if (strength != null) {
/* 50 */       strength.amount *= this.magicNumber;
/*    */     }
/*    */     
/* 53 */     super.applyPowers();
/*    */     
/* 55 */     if (strength != null) {
/* 56 */       strength.amount /= this.magicNumber;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 62 */     AbstractPower strength = AbstractDungeon.player.getPower("Strength");
/* 63 */     if (strength != null) {
/* 64 */       strength.amount *= this.magicNumber;
/*    */     }
/*    */     
/* 67 */     super.calculateCardDamage(mo);
/*    */     
/* 69 */     if (strength != null) {
/* 70 */       strength.amount /= this.magicNumber;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 76 */     if (!this.upgraded) {
/* 77 */       upgradeName();
/* 78 */       upgradeMagicNumber(2);
/* 79 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 80 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 86 */     return new HeavyBlade();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\HeavyBlade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */