/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class PummelDamageAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.01F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*    */   
/*    */   public PummelDamageAction(AbstractCreature target, DamageInfo info) {
/* 19 */     this.info = info;
/* 20 */     setValues(target, info);
/* 21 */     this.actionType = ActionType.DAMAGE;
/* 22 */     this.attackEffect = AttackEffect.BLUNT_LIGHT;
/* 23 */     this.duration = 0.01F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     if (this.duration == 0.01F && this.target != null && this.target.currentHealth > 0) {
/*    */       
/* 30 */       if (this.info.type != DamageInfo.DamageType.THORNS && 
/* 31 */         this.info.owner.isDying) {
/* 32 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */       
/* 37 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX + 
/*    */             
/* 39 */             MathUtils.random(-100.0F * Settings.xScale, 100.0F * Settings.xScale), this.target.hb.cY + 
/* 40 */             MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale), this.attackEffect));
/*    */     } 
/*    */ 
/*    */     
/* 44 */     tickDuration();
/*    */     
/* 46 */     if (this.isDone && this.target != null && this.target.currentHealth > 0) {
/* 47 */       this.target.damage(this.info);
/*    */       
/* 49 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 50 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/* 52 */       addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */     } 
/*    */     
/* 55 */     if (this.target == null)
/* 56 */       this.isDone = true; 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\PummelDamageAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */