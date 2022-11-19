/*    */ package com.megacrit.cardcrawl.cards.tempCards;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
/*    */ 
/*    */ public class ThroughViolence extends AbstractCard {
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("ThroughViolence"); public static final String ID = "ThroughViolence";
/*    */   
/*    */   public ThroughViolence() {
/* 21 */     super("ThroughViolence", cardStrings.NAME, "colorless/attack/through_violence", 0, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
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
/* 32 */     this.baseDamage = 20;
/* 33 */     this.selfRetain = true;
/* 34 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 39 */     if (m != null) {
/* 40 */       if (Settings.FAST_MODE) {
/* 41 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.VIOLET)));
/*    */       } else {
/* 43 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.VIOLET), 0.4F));
/*    */       } 
/*    */     }
/* 46 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       upgradeDamage(10);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 59 */     return new ThroughViolence();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\tempCards\ThroughViolence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */