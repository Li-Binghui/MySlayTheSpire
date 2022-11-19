/*    */ package com.megacrit.cardcrawl.cards.green;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
/*    */ 
/*    */ public class DieDieDie extends AbstractCard {
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Die Die Die"); public static final String ID = "Die Die Die";
/*    */   
/*    */   public DieDieDie() {
/* 23 */     super("Die Die Die", cardStrings.NAME, "green/attack/die_die_die", 1, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.GREEN, CardRarity.RARE, CardTarget.ALL_ENEMY);
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
/* 34 */     this.exhaust = true;
/* 35 */     this.baseDamage = 13;
/* 36 */     this.isMultiDamage = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 41 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderLongFlashEffect(Color.LIGHT_GRAY)));
/* 42 */     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new DieDieDieEffect(), 0.7F));
/* 43 */     addToBot((AbstractGameAction)new ShakeScreenAction(0.0F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.HIGH));
/* 44 */     addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 49 */     if (!this.upgraded) {
/* 50 */       upgradeName();
/* 51 */       upgradeDamage(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 57 */     return new DieDieDie();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\green\DieDieDie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */