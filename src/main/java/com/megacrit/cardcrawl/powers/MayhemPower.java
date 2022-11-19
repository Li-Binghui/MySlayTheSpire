/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class MayhemPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Mayhem";
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Mayhem");
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public MayhemPower(AbstractCreature owner, int amount) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Mayhem";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = amount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("mayhem");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     if (this.amount == 1) {
/* 28 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 30 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 36 */     flash();
/* 37 */     for (int i = 0; i < this.amount; i++) {
/*    */       
/* 39 */       addToBot(new AbstractGameAction()
/*    */           {
/*    */             public void update() {
/* 42 */               addToBot((AbstractGameAction)new PlayTopCardAction(
/*    */                     
/* 44 */                     (AbstractCreature)(AbstractDungeon.getCurrRoom()).monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
/*    */ 
/*    */ 
/*    */ 
/*    */               
/* 49 */               this.isDone = true;
/*    */             }
/*    */           });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\MayhemPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */