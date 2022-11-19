/*    */ package com.megacrit.cardcrawl.cards.colorless;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
/*    */ 
/*    */ public class Bite extends AbstractCard {
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Bite"); public static final String ID = "Bite";
/*    */   
/*    */   public Bite() {
/* 21 */     super("Bite", cardStrings.NAME, "colorless/attack/bite", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
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
/* 32 */     this.baseDamage = 7;
/* 33 */     this.baseMagicNumber = 2;
/* 34 */     this.magicNumber = this.baseMagicNumber;
/* 35 */     this.tags.add(CardTags.HEALING);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 40 */     if (m != null) {
/* 41 */       if (Settings.FAST_MODE) {
/* 42 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR
/*    */                 
/* 44 */                 .cpy()), 0.1F));
/*    */       } else {
/*    */         
/* 47 */         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR
/*    */                 
/* 49 */                 .cpy()), 0.3F));
/*    */       } 
/*    */     }
/*    */     
/* 53 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 54 */     addToBot((AbstractGameAction)new HealAction((AbstractCreature)p, (AbstractCreature)p, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 59 */     if (!this.upgraded) {
/* 60 */       upgradeName();
/* 61 */       upgradeDamage(1);
/* 62 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 68 */     return new Bite();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\colorless\Bite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */