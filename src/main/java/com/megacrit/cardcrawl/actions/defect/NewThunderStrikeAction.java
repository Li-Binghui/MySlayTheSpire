/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
/*    */ 
/*    */ public class NewThunderStrikeAction extends AttackDamageRandomEnemyAction {
/*    */   public NewThunderStrikeAction(AbstractCard card) {
/* 15 */     super(card);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (!Settings.FAST_MODE) {
/* 25 */       addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */     }
/*    */     
/* 28 */     super.update();
/*    */     
/* 30 */     if (this.target != null) {
/* 31 */       addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(this.target.drawX, this.target.drawY)));
/* 32 */       addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect)));
/* 33 */       addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\NewThunderStrikeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */