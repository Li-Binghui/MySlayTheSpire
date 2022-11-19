/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class SporeCloudPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Spore Cloud"); public static final String POWER_ID = "Spore Cloud";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public SporeCloudPower(AbstractCreature owner, int vulnAmt) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Spore Cloud";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = vulnAmt;
/* 20 */     updateDescription();
/* 21 */     loadRegion("sporeCloud");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 26 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDeath() {
/* 32 */     if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
/*    */       return;
/*    */     }
/* 35 */     CardCrawlGame.sound.play("SPORE_CLOUD_RELEASE");
/* 36 */     flashWithoutSound();
/* 37 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, null, new VulnerablePower((AbstractCreature)AbstractDungeon.player, this.amount, true), this.amount));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\SporeCloudPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */