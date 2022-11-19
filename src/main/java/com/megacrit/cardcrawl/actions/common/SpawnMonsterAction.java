/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpawnMonsterAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private boolean used = false;
/*    */   private static final float DURATION = 0.1F;
/*    */   private AbstractMonster m;
/*    */   private boolean minion;
/*    */   private int targetSlot;
/*    */   private boolean useSmartPositioning;
/*    */   
/*    */   public SpawnMonsterAction(AbstractMonster m, boolean isMinion) {
/* 27 */     this(m, isMinion, -99);
/* 28 */     this.useSmartPositioning = true;
/*    */   }
/*    */   
/*    */   public SpawnMonsterAction(AbstractMonster m, boolean isMinion, int slot) {
/* 32 */     this.actionType = ActionType.SPECIAL;
/* 33 */     this.duration = 0.1F;
/* 34 */     this.m = m;
/* 35 */     this.minion = isMinion;
/* 36 */     this.targetSlot = slot;
/* 37 */     this.useSmartPositioning = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 42 */     if (!this.used) {
/*    */       
/* 44 */       for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 45 */         r.onSpawnMonster(this.m);
/*    */       }
/* 47 */       this.m.init();
/* 48 */       this.m.applyPowers();
/*    */       
/* 50 */       if (this.useSmartPositioning) {
/* 51 */         int position = 0;
/* 52 */         for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 53 */           if (this.m.drawX > mo.drawX) {
/* 54 */             position++;
/*    */           }
/*    */         } 
/* 57 */         (AbstractDungeon.getCurrRoom()).monsters.addMonster(position, this.m);
/*    */       } else {
/* 59 */         (AbstractDungeon.getCurrRoom()).monsters.addMonster(this.targetSlot, this.m);
/*    */       } 
/* 61 */       this.m.showHealthBar();
/* 62 */       if (ModHelper.isModEnabled("Lethality")) {
/* 63 */         addToBot(new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)this.m, (AbstractPower)new StrengthPower((AbstractCreature)this.m, 3), 3));
/*    */       }
/*    */       
/* 66 */       if (ModHelper.isModEnabled("Time Dilation")) {
/* 67 */         addToBot(new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)this.m, (AbstractPower)new SlowPower((AbstractCreature)this.m, 0)));
/*    */       }
/*    */       
/* 70 */       if (this.minion) {
/* 71 */         addToTop(new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)this.m, (AbstractPower)new MinionPower((AbstractCreature)this.m)));
/*    */       }
/* 73 */       this.used = true;
/*    */     } 
/*    */     
/* 76 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\SpawnMonsterAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */