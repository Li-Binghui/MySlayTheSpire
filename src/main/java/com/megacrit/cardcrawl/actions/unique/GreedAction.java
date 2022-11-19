/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GreedAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private int increaseGold;
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.1F;
/*    */   
/*    */   public GreedAction(AbstractCreature target, DamageInfo info, int goldAmount) {
/* 21 */     this.info = info;
/* 22 */     setValues(target, info);
/* 23 */     this.increaseGold = goldAmount;
/* 24 */     this.actionType = ActionType.DAMAGE;
/* 25 */     this.duration = 0.1F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 30 */     if (this.duration == 0.1F && 
/* 31 */       this.target != null) {
/* 32 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY));
/*    */       
/* 34 */       this.target.damage(this.info);
/*    */       
/* 36 */       if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && 
/* 37 */         !this.target.hasPower("Minion")) {
/* 38 */         AbstractDungeon.player.gainGold(this.increaseGold);
/* 39 */         for (int i = 0; i < this.increaseGold; i++) {
/* 40 */           AbstractDungeon.effectList.add(new GainPennyEffect(this.source, this.target.hb.cX, this.target.hb.cY, this.source.hb.cX, this.source.hb.cY, true));
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 45 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 46 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 51 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\GreedAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */