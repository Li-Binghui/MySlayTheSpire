/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class NoxiousFumesPower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Noxious Fumes"); public static final String POWER_ID = "Noxious Fumes";
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public NoxiousFumesPower(AbstractCreature owner, int poisonAmount) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Noxious Fumes";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = poisonAmount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("fumes");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 27 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 28 */       flash();
/* 29 */       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 30 */         if (!m.isDead && !m.isDying) {
/* 31 */           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, this.owner, new PoisonPower((AbstractCreature)m, this.owner, this.amount), this.amount));
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 38 */     this.fontScale = 8.0F;
/* 39 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 44 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\NoxiousFumesPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */