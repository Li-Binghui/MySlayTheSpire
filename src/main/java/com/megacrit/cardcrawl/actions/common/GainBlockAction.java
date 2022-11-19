/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class GainBlockAction extends AbstractGameAction {
/*    */   private static final float DUR = 0.25F;
/*    */   
/*    */   public GainBlockAction(AbstractCreature target, int amount) {
/* 14 */     this.target = target;
/* 15 */     this.amount = amount;
/* 16 */     this.actionType = ActionType.BLOCK;
/* 17 */     this.duration = 0.25F;
/* 18 */     this.startDuration = 0.25F;
/*    */   }
/*    */   
/*    */   public GainBlockAction(AbstractCreature target, AbstractCreature source, int amount) {
/* 22 */     setValues(target, source, amount);
/* 23 */     this.actionType = ActionType.BLOCK;
/* 24 */     this.duration = 0.25F;
/* 25 */     this.startDuration = 0.25F;
/*    */   }
/*    */   
/*    */   public GainBlockAction(AbstractCreature target, int amount, boolean superFast) {
/* 29 */     this(target, amount);
/* 30 */     if (superFast) {
/* 31 */       this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
/*    */     }
/*    */   }
/*    */   
/*    */   public GainBlockAction(AbstractCreature target, AbstractCreature source, int amount, boolean superFast) {
/* 36 */     this(target, source, amount);
/* 37 */     if (superFast) {
/* 38 */       this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 44 */     if (!this.target.isDying && !this.target.isDead && 
/* 45 */       this.duration == this.startDuration) {
/* 46 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SHIELD));
/* 47 */       this.target.addBlock(this.amount);
/*    */ 
/*    */       
/* 50 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 51 */         c.applyPowers();
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 56 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\GainBlockAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */