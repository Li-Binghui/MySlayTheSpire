/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.ScryAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class ForesightPower extends AbstractPower {
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("WireheadingPower"); public static final String POWER_ID = "WireheadingPower";
/*    */   
/*    */   public ForesightPower(AbstractCreature owner, int scryAmt) {
/* 16 */     this.name = powerStrings.NAME;
/* 17 */     this.ID = "WireheadingPower";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = scryAmt;
/* 20 */     updateDescription();
/* 21 */     loadRegion("wireheading");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 26 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 31 */     if (AbstractDungeon.player.drawPile.size() <= 0)
/*    */     {
/*    */       
/* 34 */       addToTop((AbstractGameAction)new EmptyDeckShuffleAction());
/*    */     }
/* 36 */     flash();
/* 37 */     addToBot((AbstractGameAction)new ScryAction(this.amount));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\ForesightPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */