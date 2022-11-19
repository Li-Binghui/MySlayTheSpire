/*    */ package com.megacrit.cardcrawl.actions.animations;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class VFXAction extends AbstractGameAction {
/*    */   private AbstractGameEffect effect;
/*    */   private float startingDuration;
/*    */   private boolean isTopLevelEffect = false;
/*    */   
/*    */   public VFXAction(AbstractGameEffect effect) {
/* 14 */     this((AbstractCreature)null, effect, 0.0F);
/*    */   }
/*    */   
/*    */   public VFXAction(AbstractGameEffect effect, float duration) {
/* 18 */     this((AbstractCreature)null, effect, duration);
/*    */   }
/*    */   
/*    */   public VFXAction(AbstractCreature source, AbstractGameEffect effect, float duration) {
/* 22 */     setValues(source, source);
/* 23 */     this.effect = effect;
/* 24 */     this.duration = duration;
/* 25 */     this.startingDuration = duration;
/* 26 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */   
/*    */   public VFXAction(AbstractCreature source, AbstractGameEffect effect, float duration, boolean topLevel) {
/* 30 */     setValues(source, source);
/* 31 */     this.effect = effect;
/* 32 */     this.duration = duration;
/* 33 */     this.startingDuration = duration;
/* 34 */     this.actionType = ActionType.WAIT;
/* 35 */     this.isTopLevelEffect = topLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 40 */     if (this.duration == this.startingDuration) {
/* 41 */       if (this.isTopLevelEffect) {
/* 42 */         AbstractDungeon.topLevelEffects.add(this.effect);
/*    */       } else {
/* 44 */         AbstractDungeon.effectList.add(this.effect);
/*    */       } 
/*    */     }
/*    */     
/* 48 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\animations\VFXAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */