/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.RipAndTearEffect;
/*    */ 
/*    */ public class NewRipAndTearAction extends AttackDamageRandomEnemyAction {
/*    */   public NewRipAndTearAction(AbstractCard card) {
/* 14 */     super(card);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (!Settings.FAST_MODE) {
/* 24 */       addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */     }
/*    */     
/* 27 */     super.update();
/* 28 */     if (Settings.FAST_MODE) {
/* 29 */       addToTop((AbstractGameAction)new WaitAction(0.05F));
/*    */     } else {
/* 31 */       addToTop((AbstractGameAction)new WaitAction(0.2F));
/*    */     } 
/*    */     
/* 34 */     if (this.target != null)
/* 35 */       addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new RipAndTearEffect(this.target.hb.cX, this.target.hb.cY, Color.RED, Color.GOLD))); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\NewRipAndTearAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */