/*    */ package com.megacrit.cardcrawl.cards.red;
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
/*    */ import com.megacrit.cardcrawl.vfx.StarBounceEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
/*    */ 
/*    */ public class Carnage extends AbstractCard {
/* 19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Carnage"); public static final String ID = "Carnage";
/*    */   
/*    */   public Carnage() {
/* 22 */     super("Carnage", cardStrings.NAME, "red/attack/carnage", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 33 */     this.baseDamage = 20;
/* 34 */     this.isEthereal = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 39 */     if (Settings.FAST_MODE) {
/* 40 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED)));
/* 41 */       for (int i = 0; i < 5; i++) {
/* 42 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(m.hb.cX, m.hb.cY)));
/*    */       }
/*    */     } else {
/* 45 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViolentAttackEffect(m.hb.cX, m.hb.cY, Color.RED), 0.4F));
/* 46 */       for (int i = 0; i < 5; i++) {
/* 47 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new StarBounceEffect(m.hb.cX, m.hb.cY)));
/*    */       }
/*    */     } 
/* 50 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 55 */     if (!this.upgraded) {
/* 56 */       upgradeName();
/* 57 */       upgradeDamage(8);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 63 */     return new Carnage();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\Carnage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */