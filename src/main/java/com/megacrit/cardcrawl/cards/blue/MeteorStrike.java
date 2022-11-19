/*    */ package com.megacrit.cardcrawl.cards.blue;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.defect.ChannelAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.orbs.Plasma;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
/*    */ 
/*    */ public class MeteorStrike extends AbstractCard {
/* 19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Meteor Strike"); public static final String ID = "Meteor Strike";
/*    */   
/*    */   public MeteorStrike() {
/* 22 */     super("Meteor Strike", cardStrings.NAME, "blue/attack/meteor_strike", 5, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.BLUE, CardRarity.RARE, CardTarget.ENEMY);
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
/* 33 */     this.baseDamage = 24;
/* 34 */     this.baseMagicNumber = 3;
/* 35 */     this.magicNumber = this.baseMagicNumber;
/* 36 */     this.tags.add(CardTags.STRIKE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 41 */     if (m != null) {
/* 42 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
/*    */     }
/*    */     
/* 45 */     addToBot((AbstractGameAction)new WaitAction(0.8F));
/* 46 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */     
/* 48 */     for (int i = 0; i < this.magicNumber; i++) {
/* 49 */       addToBot((AbstractGameAction)new ChannelAction((AbstractOrb)new Plasma()));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 55 */     if (!this.upgraded) {
/* 56 */       upgradeName();
/* 57 */       upgradeDamage(6);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 63 */     return new MeteorStrike();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\blue\MeteorStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */