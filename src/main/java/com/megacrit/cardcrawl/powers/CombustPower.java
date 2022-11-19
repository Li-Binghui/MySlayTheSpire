/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class CombustPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Combust";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Combust");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private int hpLoss;
/*    */   
/*    */   public CombustPower(AbstractCreature owner, int hpLoss, int damageAmount) {
/* 21 */     this.name = NAME;
/* 22 */     this.ID = "Combust";
/* 23 */     this.owner = owner;
/* 24 */     this.amount = damageAmount;
/* 25 */     this.hpLoss = hpLoss;
/* 26 */     updateDescription();
/* 27 */     loadRegion("combust");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 32 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 33 */       flash();
/* 34 */       addToBot((AbstractGameAction)new LoseHPAction(this.owner, this.owner, this.hpLoss, AbstractGameAction.AttackEffect.FIRE));
/* 35 */       addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, 
/*    */ 
/*    */             
/* 38 */             DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 46 */     this.fontScale = 8.0F;
/* 47 */     this.amount += stackAmount;
/* 48 */     this.hpLoss++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 53 */     this.description = DESCRIPTIONS[0] + this.hpLoss + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\CombustPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */