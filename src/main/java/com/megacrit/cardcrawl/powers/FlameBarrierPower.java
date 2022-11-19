/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class FlameBarrierPower
/*    */   extends AbstractPower
/*    */ {
/* 17 */   private static final Logger logger = LogManager.getLogger(FlameBarrierPower.class.getName());
/*    */   public static final String POWER_ID = "Flame Barrier";
/* 19 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Flame Barrier");
/* 20 */   public static final String NAME = powerStrings.NAME;
/* 21 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public FlameBarrierPower(AbstractCreature owner, int thornsDamage) {
/* 24 */     this.name = NAME;
/* 25 */     this.ID = "Flame Barrier";
/* 26 */     this.owner = owner;
/* 27 */     this.amount = thornsDamage;
/* 28 */     updateDescription();
/* 29 */     loadRegion("flameBarrier");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 33 */     if (this.amount == -1) {
/* 34 */       logger.info(this.name + " does not stack");
/*    */       return;
/*    */     } 
/* 37 */     this.fontScale = 8.0F;
/* 38 */     this.amount += stackAmount;
/* 39 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 44 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
/*    */       
/* 46 */       flash();
/* 47 */       addToTop((AbstractGameAction)new DamageAction(info.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*    */     } 
/* 49 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 54 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, "Flame Barrier"));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 59 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\FlameBarrierPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */