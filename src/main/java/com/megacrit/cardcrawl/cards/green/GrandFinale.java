/*    */ package com.megacrit.cardcrawl.cards.green;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
/*    */ 
/*    */ public class GrandFinale extends AbstractCard {
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Grand Finale"); public static final String ID = "Grand Finale";
/*    */   
/*    */   public GrandFinale() {
/* 20 */     super("Grand Finale", cardStrings.NAME, "green/attack/grand_finale", 0, cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.GREEN, CardRarity.RARE, CardTarget.ALL_ENEMY);
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
/* 31 */     this.baseDamage = 50;
/* 32 */     this.isMultiDamage = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     if (Settings.FAST_MODE) {
/* 38 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new GrandFinalEffect(), 0.7F));
/*    */     } else {
/* 40 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new GrandFinalEffect(), 1.0F));
/*    */     } 
/* 42 */     addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnGlowCheck() {
/* 47 */     this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
/* 48 */     if (AbstractDungeon.player.drawPile.isEmpty()) {
/* 49 */       this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
/* 55 */     boolean canUse = super.canUse(p, m);
/* 56 */     if (!canUse) {
/* 57 */       return false;
/*    */     }
/*    */     
/* 60 */     if (p.drawPile.size() > 0) {
/* 61 */       this.cantUseMessage = cardStrings.UPGRADE_DESCRIPTION;
/* 62 */       return false;
/*    */     } 
/* 64 */     return canUse;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 69 */     if (!this.upgraded) {
/* 70 */       upgradeName();
/* 71 */       upgradeDamage(10);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 77 */     return new GrandFinale();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\green\GrandFinale.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */