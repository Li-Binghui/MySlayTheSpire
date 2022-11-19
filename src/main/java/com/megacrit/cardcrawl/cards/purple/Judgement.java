/*    */ package com.megacrit.cardcrawl.cards.purple;
/*    */ import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.GiantTextEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
/*    */ 
/*    */ public class Judgement extends AbstractCard {
/* 17 */   public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Judgement"); public static final String ID = "Judgement";
/*    */   
/*    */   public Judgement() {
/* 20 */     super("Judgement", cardStrings.NAME, "purple/skill/judgment", 1, cardStrings.DESCRIPTION, CardType.SKILL, CardColor.PURPLE, CardRarity.RARE, CardTarget.ENEMY);
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
/* 31 */     this.baseMagicNumber = 30;
/* 32 */     this.magicNumber = 30;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     if (m != null) {
/* 38 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.GOLD.cpy())));
/* 39 */       addToBot((AbstractGameAction)new WaitAction(0.8F));
/* 40 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new GiantTextEffect(m.hb.cX, m.hb.cY)));
/*    */     } 
/* 42 */     addToBot((AbstractGameAction)new JudgementAction((AbstractCreature)m, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       upgradeMagicNumber(10);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 55 */     return new Judgement();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\purple\Judgement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */