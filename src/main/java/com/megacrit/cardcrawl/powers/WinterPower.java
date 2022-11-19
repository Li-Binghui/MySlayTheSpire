/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
/*    */ 
/*    */ public class WinterPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Winter";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Winter");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public WinterPower(AbstractCreature owner, int orbAmt) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "Winter";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = orbAmt;
/* 23 */     updateDescription();
/* 24 */     loadRegion("winter");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 29 */     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 30 */       for (AbstractOrb o : AbstractDungeon.player.orbs) {
/* 31 */         if (o instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot) {
/* 32 */           flash();
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 37 */       for (int i = 0; i < this.amount; i++) {
/* 38 */         addToBot((AbstractGameAction)new ChannelAction((AbstractOrb)new Frost(), false));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 45 */     this.fontScale = 8.0F;
/* 46 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 51 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\WinterPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */