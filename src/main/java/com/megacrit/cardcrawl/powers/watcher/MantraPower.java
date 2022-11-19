/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class MantraPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Mantra";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Mantra");
/* 15 */   private final int PRAYER_REQUIRED = 10;
/*    */   
/*    */   public MantraPower(AbstractCreature owner, int amount) {
/* 18 */     this.name = powerStrings.NAME;
/* 19 */     this.ID = "Mantra";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amount;
/* 22 */     updateDescription();
/* 23 */     loadRegion("mantra");
/* 24 */     this.type = PowerType.BUFF;
/* 25 */     AbstractDungeon.actionManager.mantraGained += amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 30 */     CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 35 */     this.description = powerStrings.DESCRIPTIONS[0] + '\n' + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 40 */     super.stackPower(stackAmount);
/* 41 */     if (this.amount >= 10) {
/* 42 */       addToTop((AbstractGameAction)new ChangeStanceAction("Divinity"));
/* 43 */       this.amount -= 10;
/* 44 */       if (this.amount <= 0)
/* 45 */         addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Mantra")); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\MantraPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */