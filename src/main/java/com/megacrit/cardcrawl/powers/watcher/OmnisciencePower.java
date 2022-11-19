/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class OmnisciencePower extends AbstractPower {
/*    */   public static final String POWER_ID = "OmnisciencePower";
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("OmnisciencePower");
/*    */   
/*    */   public OmnisciencePower(AbstractCreature owner, int newAmount) {
/* 13 */     this.name = powerStrings.NAME;
/* 14 */     this.ID = "OmnisciencePower";
/* 15 */     this.owner = owner;
/* 16 */     this.amount = newAmount;
/* 17 */     updateDescription();
/* 18 */     loadRegion("devotion");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 23 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\OmnisciencePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */