/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class DevotionPower extends AbstractPower {
/*    */   public static final String POWER_ID = "DevotionPower";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DevotionPower");
/*    */   
/*    */   public DevotionPower(AbstractCreature owner, int newAmount) {
/* 17 */     this.name = powerStrings.NAME;
/* 18 */     this.ID = "DevotionPower";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = newAmount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("devotion");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 32 */     flash();
/* 33 */     if (!AbstractDungeon.player.hasPower("Mantra") && this.amount >= 10) {
/* 34 */       addToBot((AbstractGameAction)new ChangeStanceAction("Divinity"));
/*    */     } else {
/* 36 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new MantraPower(this.owner, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\DevotionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */