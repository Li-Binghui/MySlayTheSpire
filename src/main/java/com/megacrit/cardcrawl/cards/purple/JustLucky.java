/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.ScryAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
/*    */ 
/*    */ public class JustLucky extends AbstractCard {
/* 19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("JustLucky"); public static final String ID = "JustLucky";
/*    */   
/*    */   public JustLucky() {
/* 22 */     super("JustLucky", cardStrings.NAME, "purple/attack/just_lucky", 0, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.PURPLE, CardRarity.COMMON, CardTarget.ENEMY);
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
/* 33 */     this.baseDamage = 3;
/* 34 */     this.baseBlock = 2;
/* 35 */     this.baseMagicNumber = 1;
/* 36 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 42 */     addToBot((AbstractGameAction)new ScryAction(this.magicNumber));
/* 43 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.3F));
/* 44 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
/* 45 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 50 */     if (!this.upgraded) {
/* 51 */       upgradeName();
/* 52 */       upgradeDamage(1);
/* 53 */       upgradeBlock(1);
/* 54 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return new JustLucky();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\JustLucky.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */