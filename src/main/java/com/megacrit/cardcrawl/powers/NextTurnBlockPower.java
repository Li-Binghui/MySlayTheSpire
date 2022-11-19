/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class NextTurnBlockPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Next Turn Block";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Next Turn Block");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public NextTurnBlockPower(AbstractCreature owner, int armorAmt, String newName) {
/* 19 */     this.name = newName;
/* 20 */     this.ID = "Next Turn Block";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = armorAmt;
/* 23 */     updateDescription();
/* 24 */     loadRegion("defenseNext");
/*    */   }
/*    */   
/*    */   public NextTurnBlockPower(AbstractCreature owner, int armorAmt) {
/* 28 */     this(owner, armorAmt, NAME);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 33 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 38 */     flash();
/* 39 */     AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.owner.hb.cX, this.owner.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
/* 40 */     addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/* 41 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Next Turn Block"));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\NextTurnBlockPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */