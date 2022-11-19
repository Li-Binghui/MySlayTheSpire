/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
/*    */ 
/*    */ public class FlyingSleeves extends AbstractCard {
/* 19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("FlyingSleeves"); public static final String ID = "FlyingSleeves";
/*    */   
/*    */   public FlyingSleeves() {
/* 22 */     super("FlyingSleeves", cardStrings.NAME, "purple/attack/flying_sleeves", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.COMMON, CardTarget.ENEMY);
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
/* 33 */     this.baseDamage = 4;
/* 34 */     this.selfRetain = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 39 */     if (m != null) {
/* 40 */       addToBot((AbstractGameAction)new SFXAction("ATTACK_WHIFF_2", 0.3F));
/* 41 */       addToBot((AbstractGameAction)new SFXAction("ATTACK_FAST", 0.2F));
/* 42 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, 200.0F, 290.0F, 3.0F, Color.VIOLET, Color.PINK)));
/*    */     } 
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
/* 54 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 55 */     if (m != null) {
/* 56 */       addToBot((AbstractGameAction)new SFXAction("ATTACK_WHIFF_1", 0.2F));
/* 57 */       addToBot((AbstractGameAction)new SFXAction("ATTACK_FAST", 0.2F));
/* 58 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, -200.0F, 250.0F, 3.0F, Color.VIOLET, Color.PINK)));
/*    */     } 
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
/* 70 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 75 */     return new FlyingSleeves();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 80 */     if (!this.upgraded) {
/* 81 */       upgradeName();
/* 82 */       upgradeDamage(2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\FlyingSleeves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */