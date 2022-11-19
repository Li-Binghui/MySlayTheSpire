/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class BeatOfDeathPower extends AbstractPower {
/*    */   public static final String POWER_ID = "BeatOfDeath";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BeatOfDeath");
/* 16 */   public static final String NAME = powerStrings.NAME;
/* 17 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public BeatOfDeathPower(AbstractCreature owner, int amount) {
/* 20 */     this.name = NAME;
/* 21 */     this.ID = "BeatOfDeath";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = amount;
/* 24 */     this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
/* 25 */     loadRegion("beat");
/* 26 */     this.type = PowerType.BUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 31 */     flash();
/* 32 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 42 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\BeatOfDeathPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */