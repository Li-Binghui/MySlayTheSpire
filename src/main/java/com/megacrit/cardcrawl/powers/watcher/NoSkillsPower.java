/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class NoSkillsPower extends AbstractPower {
/*    */   public static final String POWER_ID = "NoSkills";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("NoSkills");
/*    */   
/*    */   public NoSkillsPower(AbstractCreature owner) {
/* 16 */     this.name = powerStrings.NAME;
/* 17 */     this.ID = "NoSkills";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = 1;
/* 20 */     updateDescription();
/* 21 */     loadRegion("entangle");
/* 22 */     this.isTurnBased = true;
/* 23 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 28 */     CardCrawlGame.sound.play("POWER_ENTANGLED", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 33 */     this.description = powerStrings.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlayCard(AbstractCard card) {
/* 38 */     return (card.type != AbstractCard.CardType.SKILL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 43 */     if (isPlayer)
/* 44 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "NoSkills")); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\NoSkillsPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */