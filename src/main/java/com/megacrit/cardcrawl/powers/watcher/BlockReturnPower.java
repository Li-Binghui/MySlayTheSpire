/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class BlockReturnPower extends AbstractPower {
/*    */   public static final String POWER_ID = "BlockReturnPower";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BlockReturnPower");
/*    */   
/*    */   public BlockReturnPower(AbstractCreature owner, int blockAmt) {
/* 18 */     this.name = powerStrings.NAME;
/* 19 */     this.ID = "BlockReturnPower";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = blockAmt;
/* 22 */     updateDescription();
/* 23 */     loadRegion("talk_to_hand");
/* 24 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 28 */     this.fontScale = 8.0F;
/* 29 */     this.amount += stackAmount;
/* 30 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 35 */     if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
/*    */       
/* 37 */       flash();
/* 38 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, this.amount, Settings.FAST_MODE));
/*    */     } 
/* 40 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 45 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\BlockReturnPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */