/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ public class PoisonPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Poison";
/* 16 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Poison");
/* 17 */   public static final String NAME = powerStrings.NAME;
/* 18 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private AbstractCreature source;
/*    */   
/*    */   public PoisonPower(AbstractCreature owner, AbstractCreature source, int poisonAmt) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "Poison";
/* 25 */     this.owner = owner;
/* 26 */     this.source = source;
/* 27 */     this.amount = poisonAmt;
/*    */     
/* 29 */     if (this.amount >= 9999) {
/* 30 */       this.amount = 9999;
/*    */     }
/*    */     
/* 33 */     updateDescription();
/* 34 */     loadRegion("poison");
/* 35 */     this.type = PowerType.DEBUFF;
/*    */     
/* 37 */     this.isTurnBased = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 42 */     CardCrawlGame.sound.play("POWER_POISON", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 47 */     if (this.owner == null || this.owner.isPlayer) {
/* 48 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 50 */       this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 56 */     super.stackPower(stackAmount);
/*    */     
/* 58 */     if (this.amount > 98 && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
/* 59 */       UnlockTracker.unlockAchievement("CATALYST");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 65 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && 
/* 66 */       !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 67 */       flashWithoutSound();
/* 68 */       addToBot((AbstractGameAction)new PoisonLoseHpAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.POISON));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\PoisonPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */