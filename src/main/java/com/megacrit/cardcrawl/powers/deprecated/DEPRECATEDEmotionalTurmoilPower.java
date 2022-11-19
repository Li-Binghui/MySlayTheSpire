/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.deprecated.DEPRECATEDRandomStanceAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class DEPRECATEDEmotionalTurmoilPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("EmotionalTurmoilPower"); public static final String POWER_ID = "EmotionalTurmoilPower";
/*    */   
/*    */   public DEPRECATEDEmotionalTurmoilPower(AbstractCreature owner) {
/* 14 */     this.name = powerStrings.NAME;
/* 15 */     this.ID = "EmotionalTurmoilPower";
/* 16 */     this.owner = owner;
/* 17 */     updateDescription();
/* 18 */     loadRegion("draw");
/* 19 */     this.type = PowerType.BUFF;
/* 20 */     this.isTurnBased = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 25 */     addToBot((AbstractGameAction)new DEPRECATEDRandomStanceAction());
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     this.description = powerStrings.DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDEmotionalTurmoilPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */