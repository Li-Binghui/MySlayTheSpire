/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class BackAttackPower extends AbstractPower {
/*    */   public static final String POWER_ID = "BackAttack";
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BackAttack");
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public BackAttackPower(AbstractCreature owner) {
/* 15 */     this.name = NAME;
/* 16 */     this.type = PowerType.BUFF;
/* 17 */     this.ID = "BackAttack";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = -1;
/* 20 */     updateDescription();
/*    */     
/* 22 */     if (owner.hb.cX < Settings.WIDTH / 2.0F) {
/* 23 */       loadRegion("backAttack");
/*    */     } else {
/* 25 */       loadRegion("backAttack2");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 31 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\BackAttackPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */