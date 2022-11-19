/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
/*    */ 
/*    */ public class FlechetteAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   
/*    */   public FlechetteAction(AbstractCreature target, DamageInfo info) {
/* 18 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 19 */     this.info = info;
/* 20 */     this.actionType = ActionType.BLOCK;
/* 21 */     this.target = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 27 */       if (c.type == AbstractCard.CardType.SKILL) {
/* 28 */         addToTop((AbstractGameAction)new DamageAction(this.target, this.info, true));
/* 29 */         if (this.target != null && this.target.hb != null) {
/* 30 */           addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
/*    */         }
/*    */       } 
/*    */     } 
/* 34 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\FlechetteAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */