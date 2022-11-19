/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class TheBombPower extends AbstractPower {
/*    */   public static final String POWER_ID = "TheBomb";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TheBomb");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private int damage;
/*    */   private static int bombIdOffset;
/*    */   
/*    */   public TheBombPower(AbstractCreature owner, int turns, int damage) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "TheBomb" + bombIdOffset;
/* 24 */     bombIdOffset++;
/* 25 */     this.owner = owner;
/* 26 */     this.amount = turns;
/* 27 */     this.damage = damage;
/* 28 */     updateDescription();
/* 29 */     loadRegion("the_bomb");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 34 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 35 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this, 1));
/* 36 */       if (this.amount == 1) {
/* 37 */         addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, 
/*    */ 
/*    */               
/* 40 */               DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 49 */     if (this.amount == 1) {
/* 50 */       this.description = String.format(DESCRIPTIONS[1], new Object[] { Integer.valueOf(this.damage) });
/*    */     } else {
/* 52 */       this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount), Integer.valueOf(this.damage) });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\TheBombPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */