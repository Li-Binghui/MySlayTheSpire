/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
/*    */ 
/*    */ public class ExplosivePower extends AbstractPower {
/*    */   public static final String POWER_ID = "Explosive";
/* 19 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Explosive");
/* 20 */   public static final String NAME = powerStrings.NAME;
/* 21 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private static final int DAMAGE_AMOUNT = 30;
/*    */   
/*    */   public ExplosivePower(AbstractCreature owner, int damage) {
/* 25 */     this.name = NAME;
/* 26 */     this.ID = "Explosive";
/* 27 */     this.owner = owner;
/* 28 */     this.amount = damage;
/* 29 */     updateDescription();
/* 30 */     loadRegion("explosive");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 35 */     if (this.amount == 1) {
/* 36 */       this.description = DESCRIPTIONS[3] + '\036' + DESCRIPTIONS[2];
/*    */     } else {
/* 38 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + '\036' + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void duringTurn() {
/* 44 */     if (this.amount == 1 && !this.owner.isDying) {
/* 45 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ExplosionSmallEffect(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
/* 46 */       addToBot((AbstractGameAction)new SuicideAction((AbstractMonster)this.owner));
/* 47 */       DamageInfo damageInfo = new DamageInfo(this.owner, 30, DamageInfo.DamageType.THORNS);
/* 48 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, damageInfo, AbstractGameAction.AttackEffect.FIRE, true));
/*    */     } else {
/* 50 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Explosive", 1));
/* 51 */       updateDescription();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ExplosivePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */