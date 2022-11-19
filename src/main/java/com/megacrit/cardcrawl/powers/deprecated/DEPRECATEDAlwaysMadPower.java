/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class DEPRECATEDAlwaysMadPower extends AbstractPower {
/*    */   public static final String POWER_ID = "AlwaysMad";
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("AlwaysMad");
/* 11 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public DEPRECATEDAlwaysMadPower(AbstractCreature owner) {
/* 14 */     this.name = powerStrings.NAME;
/* 15 */     this.ID = "AlwaysMad";
/* 16 */     this.owner = owner;
/* 17 */     updateDescription();
/* 18 */     loadRegion("anger");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 23 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDAlwaysMadPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */