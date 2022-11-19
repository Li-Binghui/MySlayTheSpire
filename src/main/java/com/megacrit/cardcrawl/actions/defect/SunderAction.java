/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ 
/*    */ public class SunderAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private int energyGainAmt;
/*    */   private DamageInfo info;
/*    */   
/*    */   public SunderAction(AbstractCreature target, DamageInfo info, int energyAmt) {
/* 20 */     this.info = info;
/* 21 */     setValues(target, info);
/* 22 */     this.energyGainAmt = energyAmt;
/* 23 */     this.actionType = ActionType.DAMAGE;
/* 24 */     this.duration = Settings.ACTION_DUR_FASTER;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     if (this.duration == Settings.ACTION_DUR_FASTER && 
/* 30 */       this.target != null) {
/* 31 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY));
/*    */       
/* 33 */       this.target.damage(this.info);
/*    */       
/* 35 */       if (((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) {
/* 36 */         addToBot((AbstractGameAction)new GainEnergyAction(this.energyGainAmt));
/*    */       }
/*    */       
/* 39 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 40 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 45 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\SunderAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */