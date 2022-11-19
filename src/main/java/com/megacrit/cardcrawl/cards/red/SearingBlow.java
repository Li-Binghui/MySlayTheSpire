/*    */ package com.megacrit.cardcrawl.cards.red;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
/*    */ 
/*    */ public class SearingBlow extends AbstractCard {
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Searing Blow"); public static final String ID = "Searing Blow";
/*    */   
/*    */   public SearingBlow() {
/* 19 */     this(0);
/*    */   }
/*    */   
/*    */   public SearingBlow(int upgrades) {
/* 23 */     super("Searing Blow", cardStrings.NAME, "red/attack/searing_blow", 2, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
/* 34 */     this.baseDamage = 12;
/* 35 */     this.timesUpgraded = upgrades;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 40 */     if (m != null) {
/* 41 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new SearingBlowEffect(m.hb.cX, m.hb.cY, this.timesUpgraded), 0.2F));
/*    */     }
/*    */     
/* 44 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 49 */     upgradeDamage(4 + this.timesUpgraded);
/* 50 */     this.timesUpgraded++;
/* 51 */     this.upgraded = true;
/* 52 */     this.name = cardStrings.NAME + "+" + this.timesUpgraded;
/* 53 */     initializeTitle();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUpgrade() {
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 63 */     return new SearingBlow(this.timesUpgraded);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\red\SearingBlow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */