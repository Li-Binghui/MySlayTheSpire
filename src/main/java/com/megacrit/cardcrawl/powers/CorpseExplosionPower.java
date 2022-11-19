/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class CorpseExplosionPower extends AbstractPower {
/*    */   public static final String POWER_ID = "CorpseExplosionPower";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("CorpseExplosionPower");
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public CorpseExplosionPower(AbstractCreature owner) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "CorpseExplosionPower";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = 1;
/* 22 */     this.type = PowerType.DEBUFF;
/* 23 */     updateDescription();
/* 24 */     loadRegion("cExplosion");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDeath() {
/* 29 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() && 
/* 30 */       this.owner.currentHealth <= 0) {
/* 31 */       addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, 
/*    */ 
/*    */             
/* 34 */             DamageInfo.createDamageMatrix(this.owner.maxHealth * this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 43 */     if (this.amount == 1) {
/* 44 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 46 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\CorpseExplosionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */